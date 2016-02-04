package com.kimmin.util.test;

/**
 * Created by min.jin on 2016/2/3.
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;


public class ForkJoinTest {
    public static void main(String[] args){
        ForkJoinPool forkjoinpool=new ForkJoinPool();
        CountTask task=new CountTask(1,6);

        Future result=forkjoinpool.submit(task);
        try{
            System.out.println(result.get());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

class CountTask extends RecursiveTask<Integer>{
    private static final int THRESHOLD=2;
    private int start;
    private int end;

    public CountTask(int start,int end){
        this.start=start;
        this.end=end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (end - start) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            rightTask.join();
            sum = rightResult + leftResult;
        }
        return sum;
    }



}