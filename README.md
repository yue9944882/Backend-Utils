# BackendUtil


Personal Java Backend Toolset for Developling Backend Server &amp; Service


##1. PersistentQueue 持久化队列##


### MappedFileQueue ###
	Usage: 
		To map a queue onto disk storage supporting data protection when unexpected termination occured.
	
	Entry Function:
	  	Constructor - MappedFileQueue(String dir,String queueName,int maxSize)
		Produce(i.e.PUSH) - public boolean produce(T t)
		Comsume(i.e.PULL) - public T consume()
		Shutdown - to shutdown the queue without cleaning data reservation
		ConsumeFromDiskFile - detailed manipulation of file IO when Consume 
		ProduceToDiskFile - detailed manipulation of file IO when Produce



### DiskBackedInMemoryQueue ###


	Usage: 
		Encapsulating the A queue via a in-memory blocking queue supporting blocking concurrency.
	Entry Function:
	  	Constructor - DiskBackedInMemoryBlockingQueue(int maxMemoryElementCount, PersistentQueue persistentQueue)
		Produce(i.e.PUSH) - public boolean produce(T t)
		Consume(i.e.PULL) - public T consume()
		Shutdown - To shutdown the queue without cleaning data reservation

#####Example: #####

PersistentQueue<Integer> pq=new DiskBackedInMemoryBlockingQueue<Integer>(300,new MappedFileQueue<>("D:/dq/", "IncomingQueue", maxOnDiskFileSizeMB));

pq.produce(new Integer(1000));

Integer i=pq.consume();

### ByteArrayPersistentQueue ###

	Usage: 
    	Enabling a persistent queue to consume the latest coming bytes and use spare time to consume the old.
	Entry Function:
    	Constructor - ByteArrayPersistentQueue(String dir,String qName,int maxFileSize)
    	Produce(i.e.PUSH) - public boolean produce(T t)
    	Consume(i.e.PULL) - public T consume()
    	Shutdown - To shutdown the queue without cleaning data reservation

##2. Producer & Consumer 异步消费模式##

### Exchange Producer & Consumer ###
	Usage: 
		1.Inherite from class ExchangeProducer and ExchangeConsumer seperately , and override the method PREPARE,RUN via your own implementation. 		
		2.Exchanger can only be shared by a couple of Producer and Consumer.
		3.BETTER do the merging or aggregating in PREPARE method,and put it into a inner collection.

	Entry Function:
	  	public ExchangeProducer(Exchanger<T> ec)
		public class ExchangeConsumer<T> extends Thread implements Consumer<T>