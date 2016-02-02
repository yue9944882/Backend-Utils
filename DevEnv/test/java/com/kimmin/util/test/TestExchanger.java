package com.kimmin.util.test;


import java.sql.Time;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;



/**
 * Created by min.jin on 2016/2/2.
 */
public class TestExchanger {

    public static void main(String[] args){
        Exchanger<Integer> exchanger=new Exchanger<>();
        A a=new A(exchanger);
        B b=new B(exchanger);
        a.start();
        b.start();
    }
}

class A extends Thread{
    private Exchanger<Integer> foo;
    public A(Exchanger<Integer> e){
        foo=e;
    }

    public void bar(){
        try{
            //foo.exchange(new Integer(1));
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        bar();
    }
}

class B extends Thread{
    private Exchanger<Integer> foo;
    public B(Exchanger<Integer> e){
        foo=e;
    }
    public void bar(){
        try{
            Integer i=foo.exchange(null,3, TimeUnit.SECONDS);
            System.out.println("A exchanged and get:"+i);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }catch (TimeoutException te){
            te.printStackTrace();
        }
    }
    @Override
    public void run(){
        bar();
    }
}