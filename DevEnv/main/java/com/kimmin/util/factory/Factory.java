package com.kimmin.util.factory;

import com.kimmin.util.pc.Producer;

/**
 * Created by min.jin on 2016/2/2.
 */
public class Factory<T extends Product>{

    private Factory(){}

    private Product innerProduct=null;

    private static class SingletonFactory{
        private static Factory instance=new Factory();
    }

    public static Factory getInstance(){
        return SingletonFactory.instance;
    }

    public T produce(){
        return innerProduct.constructor();
    }


    public void setProductClass(Product product){
        innerProduct=product;
    }

}
