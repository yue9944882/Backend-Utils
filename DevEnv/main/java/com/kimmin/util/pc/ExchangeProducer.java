package com.kimmin.util.pc;

import com.kimmin.util.factory.Product;

import java.util.concurrent.Exchanger;

/**
 * Created by min.jin on 2016/2/2.
 */

/** Usage : Inheritance from this class **/


public class ExchangeProducer<T> extends Thread implements Producer<T> {

    private Exchanger<T> exchanger=null;

    public ExchangeProducer(Exchanger<T> ec){
        exchanger=ec;
    }

    public void prepare(){
        /** It Depends **/
    }

    @Override
    public boolean produce(T t){
        try{
            exchanger.exchange(t);
            return true;
        }catch(InterruptedException ie){
            /** If interrupted while waiting for return value **/
            return false;
        }
    }


}
