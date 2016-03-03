package com.kimmin.netty.demo.socket;

/**
 * Created by min.jin on 2016/3/3.
 */


public class AIOTimeServer {





    public static void main(String[] args){
        int port = 10000;
        if(args != null
                && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }
    }

}



