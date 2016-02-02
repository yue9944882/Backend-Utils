package com.kimmin.util.pc;

/**
 * Created by min.jin on 2016/2/2.
 */


public abstract class ProducerFactory <T extends Producer>{

    private ProducerFactory(){};

    private static class SingletonFactory{
        private static  ProducerFactory instance=new ProducerFactory();
    }

    public static ProducerFactory getInstance(){
        return SingletonFactory.instance;
    }


}
