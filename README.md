# BackendUtil


Personal Java Backend Toolset for Developling Backend Server &amp; Service


##1. PersistentQueue 持久化队列##


### MappedFileQueue ###
	Usage: 
		To map a queue onto disk storage supporting data protection when unexpected termination occured.
	
	Dependencies:
		java.nio.*;
		java.nio.channels.*;
		sun.nio.ch.*;

	Entry Function:
	  	Constructor - MappedFileQueue(String dir,String queueName,int maxSize)
		Produce(i.e.PUSH) - public boolean produce(T t)
		Comsume(i.e.PULL) - public T consume()
		Shutdown - to shutdown the queue without cleaning data reservation
		ConsumeFromDiskFile - detailed manipulation of file IO when Consume 
		ProduceToDiskFile - detailed manipulation of file IO when Produce
MappedFileQueue规定了映射到磁盘上的队列默认的分页方式（128Mb一个页文件）和简单索引文件（记录WriteBufferIndex），另定义守护进程不断扫描目标文件目录，如果有已经彻底使用过的分页文件，则直接进行删除。

### DiskBackedInMemoryQueue ###


	Usage: 
		Encapsulating the A queue via a in-memory blocking queue supporting blocking concurrency.
	
	Dependencies:
		N/A

	Entry Function:
	  	Constructor - DiskBackedInMemoryBlockingQueue(int maxMemoryElementCount, PersistentQueue persistentQueue)
		Produce(i.e.PUSH) - public boolean produce(T t)
		Consume(i.e.PULL) - public T consume()
		Shutdown - To shutdown the queue without cleaning data reservation
DiskBackedInMemoryQueue用来进一步封装MappedFileQueue，在文件持久化队列的基础上添加一个并发阻塞队列，更好支持多线程下的操作，而且添加了容量查询函数，空间占用情况查询函数。

#####Example: #####

PersistentQueue<Integer> pq=new DiskBackedInMemoryBlockingQueue<Integer>(300,new MappedFileQueue<>("D:/dq/", "IncomingQueue", maxOnDiskFileSizeMB));

pq.produce(new Integer(1000));

Integer i=pq.consume();

### ByteArrayPersistentQueue ###

	Usage: 
    	Enabling a persistent queue to consume the latest coming bytes and use spare time to consume the old.

	Dependencies:
		java.nio.*;
		java.nio.channels.*;
		sun.nio.ch.*;		

	Entry Function:
    	Constructor - ByteArrayPersistentQueue(String dir,String qName,int maxFileSize)
    	Produce(i.e.PUSH) - public boolean produce(T t)
    	Consume(i.e.PULL) - public T consume()
    	Shutdown - To shutdown the queue without cleaning data reservation
和MappedFileQueue很相似，但是在分页文件扫描的策略改进为“如果持久化队列之中仍有未被消费的历史对象，则优先消费新加入队列的对象，在无新对象的情况下消费旧对象”

##2. Producer & Consumer 异步消费模式##

### Exchange Producer & Consumer ###
	Usage: 
		1.Inherite from class ExchangeProducer and ExchangeConsumer seperately , and override the method PREPARE,RUN via your own implementation. 		
		2.Exchanger can only be shared by a couple of Producer and Consumer.
		3.BETTER do the merging or aggregating in PREPARE method,and put it into a inner collection

	Entry Function:
	  	public ExchangeProducer(Exchanger<T> ec)
		public class ExchangeConsumer<T> extends Thread implements Consumer<T>