package com.kimmin.netty.demo.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by min.jin on 2016/3/3.
 */


public class NIOTimeServer {

    public static void main(String[] args) throws IOException{

        ServerSocketChannel acceptorSvr = ServerSocketChannel.open();
        acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"),10000));
        acceptorSvr.configureBlocking(false);

        Selector selector = Selector.open();
        //new Thread(new React)
        //SelectionKey key = acceptorSvr.register(selector,SelectionKey.OP_ACCEPT,ioHandler);


    }

}
