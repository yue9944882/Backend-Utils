package com.kimmin.util.pc;

import com.kimmin.util.factory.Product;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by min.jin on 2016/2/2.
 */
public class ExchangeConsumer<T> extends Thread implements Consumer<T> {
    private Exchanger<T> exchanger=null;
    private long timeRange=0;
    private TimeUnit timeUnit=null;

    public ExchangeConsumer(Exchanger<T> ec, long time, TimeUnit unit){
        exchanger=ec;
        timeRange=time;
        timeUnit=unit;
    }
    @Override
    public T consume(){
        try{
            return exchanger.exchange(null,timeRange,timeUnit);
        }catch(TimeoutException te){
            /** If no element is retrieved back **/
            return null;
        }catch(InterruptedException ie){
            /** If interrupted while waiting for return value **/
            return null;
        }
    }
}
