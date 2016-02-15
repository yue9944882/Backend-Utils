package com.kimmin.demo;

import java.util.concurrent.*;

/**
 * Created by min.jin on 2016/2/15.
 */
public class ExecutorDemo {


    public static void main(String[] args){

        //ExecutorService fixed= Executors.newFixedThreadPool(4);
        ExecutorService single=Executors.newSingleThreadExecutor();
        //ExecutorService cached=Executors.newCachedThreadPool();
        //ExecutorService sched=Executors.newScheduledThreadPool(4);
        //ExecutorService singlesched=Executors.newSingleThreadScheduledExecutor();


        Callable<String> callable=Executors.callable(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    try{
                        System.out.println(i);
                    }catch(Throwable e){
                        e.printStackTrace();
                    }
                }
            }
        },"success");

        Future<String> f=single.submit(callable);
        try {
            System.out.println(f.get());
            single.shutdown();
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

}
