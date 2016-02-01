package com.kimmin.util.test;

import com.kimmin.util.queue.DiskBackedInMemoryBlockingQueue;
import com.kimmin.util.queue.MappedFileQueue;
import com.kimmin.util.queue.PersistentQueue;
import com.sun.xml.internal.ws.api.WSService;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.util.Random;



/**
 * Created by min.jin on 2016/1/29.
 */

public class FileIOTest {

    public static void main(String[] args){

        int maxOnDiskFileSizeMB=1024*4;
        try {
            PersistentQueue<Integer> pq=new DiskBackedInMemoryBlockingQueue<Integer>(300,new MappedFileQueue<>("D:/dq/", "IncomingQueue", maxOnDiskFileSizeMB));

            /*for(int i=0;i<10000000;i++){
                pq.produce(i);
            }*/

            Thread ct=new CountThread(pq,-1);
            ct.start();

            for(int i=0;i<10;i++){
                Thread it=new InputThread(pq,i);
                it.start();
            }

            for(int i=0;i<10;i++){
                Thread t=new TakeThread(pq,i);
                t.start();
            }

            //pq.shutdown();
            //System.out.println("TestEnded");
            //System.out.println("Overflow Cnt :"+pq.getOverflowCount());

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

class TakeThread extends Thread{

    private PersistentQueue<Integer> persistentQueue=null;
    private int index=-1;

    public TakeThread(PersistentQueue<Integer> pq,int idx){
        persistentQueue=pq;
        index=idx;
    }

    @Override
    public void run(){
        Integer i=persistentQueue.consume();
        while(true){
            if(i!=null) {
                System.out.println("Thread " + index + " take out a value:" + i);
            }
            i = persistentQueue.consume();
        }
    }
}

class CountThread extends Thread{
    private PersistentQueue<Integer> persistentQueue=null;
    private int index=-1;

    public CountThread(PersistentQueue<Integer> pq,int idx){
        persistentQueue=pq;
        index=idx;
    }

    @Override
    public void run(){
        try{
            while(!persistentQueue.isEmpty()){
                System.out.println("HeartBeat: Current Size --"+persistentQueue.getUsedCapacity());
                sleep(1000);
            }
            //persistentQueue.shutdown();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

class InputThread extends Thread{
    private PersistentQueue<Integer> persistentQueue=null;
    private int index=-1;
    public InputThread(PersistentQueue<Integer> pq,int idx){
        persistentQueue=pq;
        index=idx;
    }

    @Override
    public void run(){
        Random random=new Random(index);
        for(int i=0;i<10000;i++){
            persistentQueue.produce(new Integer(random.nextInt()));
            try{
                sleep(50);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}