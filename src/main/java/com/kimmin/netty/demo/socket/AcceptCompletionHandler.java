//package com.kimmin.netty.demo.socket;
//
//import java.nio.channels.AsynchronousServerSocketChannel;
//import java.nio.channels.CompletionHandler;
//
///**
// * Created by min.jin on 2016/3/3.
// */
//
//public class AcceptCompletionHandler implements CompletionHandler{
//
//    public void completed(AsynchronousServerSocketChannel result, AsyncTimeServerHandler attachment){
//        attachment.asynchronousServerSocketChannel.accept(attachment,this);
//    }
//
//    public void failed(Throwable exc, AsyncTimeServerHandler attachment){
//        exc.printStackTrace();
//        attachment.latch.countDown();
//    }
//
//}
