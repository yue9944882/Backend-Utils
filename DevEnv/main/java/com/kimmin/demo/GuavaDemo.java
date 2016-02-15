package com.kimmin.demo;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by min.jin on 2016/2/15.
 */
public class GuavaDemo {

    public static void main(String[] args){

        IdentityHashMap<String,String> map=Maps.newIdentityHashMap();
        //LinkedHashMap<String,String> map=Maps.newLinkedHashMap();
        //TreeMap<String,String> map=Maps.newTreeMap();
        //Map<String,String> map=Maps.newHashMap();
        //ConcurrentMap<String,String> map=Maps.newConcurrentMap();

        /** Joiner Demo **/
        Joiner.on('|').join("123","456");

        StringBuilder sb=new StringBuilder("Results:");
        Joiner.on('|').appendTo(sb,"123","456");

        Joiner.on('|').skipNulls().join("123","456");

        Joiner.on('|').useForNull("NaN").join("123","456");

        Joiner.on('|').withKeyValueSeparator("#").join(ImmutableMap.of("123","456","_123","_456"));

        /** Splitter Demo **/
        Splitter.on('|').split("1|2|3|4");

        Splitter.onPattern("|").split("1|2|3|4");

        Splitter.fixedLength(3).split("1|2|3|4"); // Ret: 1|2 , |3| , 4

        /** CharMatcher Demo **/

        ;;;

    }





}
