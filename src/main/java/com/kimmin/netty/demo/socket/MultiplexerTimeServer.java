package com.kimmin.netty.demo.socket;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by min.jin on 2016/3/3.
 */


public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel servChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int port){
        try{
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            servChannel.configureBlocking(false);
            servChannel.socket().bind(new InetSocketAddress(port),10000);
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Time server already started!");
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void stop(){
        this.stop = true;
    }

    public void run(){
        while(!stop){
            try{
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while(iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    try{
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get();
                    String body = new String(bytes,"UTF-8");
                    System.out.println("Time Server receive order: "+body);
                    String currentTime = "QUERY TIME ORDER"
                            .equalsIgnoreCase(body)?new java.util.Date(
                            System.currentTimeMillis()).toString()
                            :"BAD ORDER";
                    doWrite(sc, currentTime);
                }else if(readBytes < 0){
                    key.channel();
                    sc.close();
                }else{;}
            }
        }
        if(selector != null){
            try{
                selector.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    private void doWrite(SocketChannel channel, String response) throws IOException{
        if(response != null && response.trim().length() >0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }

}
