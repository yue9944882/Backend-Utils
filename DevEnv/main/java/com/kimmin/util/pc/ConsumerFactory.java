package com.kimmin.util.pc;

import com.kimmin.util.factory.Factory;
import com.kimmin.util.factory.Product;

/**
 * Created by min.jin on 2016/2/2.
 */


public class ConsumerFactory{

    private ConsumerFactory(){}

    private static class SingletonFactory{
        private static ConsumerFactory instance=new ConsumerFactory();
    }

    public static ConsumerFactory getInstance(){
        return SingletonFactory.instance;
    }

    /** Fake Producing **/
    public Consumer produce(){

    }

}
