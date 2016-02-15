package com.kimmin.demo;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by min.jin on 2016/2/15.
 */
public class GuavaExecutorDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ListeningExecutorService listeningExecutorService=MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        ListenableFuture<Integer> listenableFuture=listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });


        /** via Callback **/
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                System.out.println("Success!!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Failed!");
            }
        });

        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                System.out.println("Success2!!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Failed2!!");
            }
        });

        /** via addListener **/
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("_Success!!");
            }
        },listeningExecutorService);

        System.out.println(listenableFuture.get());

        listeningExecutorService.shutdown();
    }

}
