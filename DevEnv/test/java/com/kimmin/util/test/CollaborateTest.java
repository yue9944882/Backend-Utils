package com.kimmin.util.test;

import com.kimmin.util.queue.ByteArrayPersistentQueue;
import com.kimmin.util.queue.DiskBackedInMemoryBlockingQueue;
import com.kimmin.util.queue.MappedFileQueue;
import com.kimmin.util.queue.PersistentQueue;

/**
 * Created by min.jin on 2016/2/1.
 */
public class CollaborateTest {
    public static void main(String[] args){
        try {
            int maxOnDiskFileSizeMB = 1024 * 30;
            PersistentQueue<Integer> produceq = new DiskBackedInMemoryBlockingQueue<Integer>(300, new MappedFileQueue<>("D:/dq/", "IncomingQueue", maxOnDiskFileSizeMB));
            ByteArrayPersistentQueue consumeq = new ByteArrayPersistentQueue("D:/dq/","IncomingQueue",maxOnDiskFileSizeMB);
            byte b=1;
            for(int i=0;i<10;i++){
                byte[] tmp=new byte[1];
                tmp[0]=b++;
                consumeq.produce(tmp);
            }
            while(true){
                byte[] bytes=consumeq.consume();
                if(bytes!=null)System.out.println("Get Data Length "+new Integer(bytes[0]));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
