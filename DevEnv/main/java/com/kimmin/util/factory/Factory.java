package com.kimmin.util.factory;

import com.kimmin.util.pc.Producer;

/**
 * Created by min.jin on 2016/2/2.
 */

/** Template Only **/
/** Because of private constructor this class cannot be inherited **/

public class Factory<T extends Product>{

    /** Singleton & Multi-thread initialization protection **/
    private Factory(){}

    private Product innerProduct=null;

    private static class SingletonFactory{
        private static Factory instance=new Factory();
    }

    public static Factory getInstance(){
        return SingletonFactory.instance;
    }

    /** Fake Producing **/
    public T produce(){ return null; }

}
