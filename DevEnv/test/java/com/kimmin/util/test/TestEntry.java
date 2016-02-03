package com.kimmin.util.test;


import com.kimmin.util.pc.ExchangeConsumer;
import com.kimmin.util.pc.ExchangeProducer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Created by min.jin on 2016/2/2.
 */
public class TestEntry {
    public static void main(String[] args){

        Exchanger e=new Exchanger();
        MyProducer mp=new MyProducer(e);
        MyConsumer mc=new MyConsumer(e);
        mp.start();
        mc.start();

    }

}


class MyProducer extends ExchangeProducer<Integer>{

    private BlockingQueue<Integer> bq=new ArrayBlockingQueue<Integer>(100);

    public MyProducer(Exchanger<Integer> ec){
        super(ec);
    }

    private Random random=new Random(100);

    @Override
    public void prepare(){
        //bq.offer(random.nextInt());
    }

    @Override
    public void run(){
        prepare();
        while(true){
            super.produce(random.nextInt());
            prepare();
        }
    }
}

class MyConsumer extends ExchangeConsumer<Integer>{

    public MyConsumer(Exchanger<Integer> ec){
        super(ec,1, TimeUnit.SECONDS);
    }

    @Override
    public void run(){
        int cnt=0;
        while(true){
            Integer i=super.consume();
            while(i==null){
                try{
                    sleep(100);
                    i=super.consume();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("Get Value :"+cnt);
            cnt++;
        }
    }
}