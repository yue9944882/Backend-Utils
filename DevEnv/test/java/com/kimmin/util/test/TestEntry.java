package com.kimmin.util.test;




import com.google.common.base.Optional;
import com.kimmin.util.pc.ExchangeConsumer;
import com.kimmin.util.pc.ExchangeProducer;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by min.jin on 2016/2/2.
 */
public class TestEntry {

    public static void main(String[] args){

        ExecutorService fixed=Executors.newFixedThreadPool(4);
        ExecutorService single=Executors.newSingleThreadExecutor();
        ExecutorService cached=Executors.newCachedThreadPool();
        ExecutorService sched=Executors.newScheduledThreadPool(4);
        ExecutorService singlesched=Executors.newSingleThreadScheduledExecutor();
        

    }


}
