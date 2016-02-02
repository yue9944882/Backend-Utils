package com.kimmin.util.test;

import com.kimmin.util.factory.Producible;

/**
 * Created by min.jin on 2016/2/2.
 */
public class Bean implements Producible<Bean>{
    public Bean(){}
    public Bean(int num){ data=num; }

    private int data=0;

    //Getter and Setter
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Bean produce(){
        return new Bean(2);
    }

}
