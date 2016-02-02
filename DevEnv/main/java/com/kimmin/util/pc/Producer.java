package com.kimmin.util.pc;

/**
 * Created by min.jin on 2016/2/2.
 */
public interface Producer<T> {
    boolean produce(T t);
}
