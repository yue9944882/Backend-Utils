package com.kimmin.util.pc;

/**
 * Created by min.jin on 2016/2/2.
 */


public class ConsumerFactory <T>{

    private ConsumerFactory(){};

    private static class SingletonFactory{
        private static  ConsumerFactory instance=new ConsumerFactory();
    }

    public static ConsumerFactory getInstance(){
        return SingletonFactory.instance;
    }

    public T produceConsumer(){
        return new T();
    }

}
