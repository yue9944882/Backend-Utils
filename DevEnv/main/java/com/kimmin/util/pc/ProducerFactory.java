package com.kimmin.util.pc;

import com.kimmin.util.factory.Product;

/**
 * Created by min.jin on 2016/2/2.
 */


public class ProducerFactory <T extends Producer>{

    private ProducerFactory(){}

    private static class SingletonFactory{
        private static ProducerFactory instance=new ProducerFactory();
    }

    public static ProducerFactory getInstance(){
        return SingletonFactory.instance;
    }

    /** Fake Producing **/
    public Producer produce(){
        return null;
    }

}
