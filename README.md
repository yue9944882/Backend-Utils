# BackendUtil


Personal Java Backend Toolset for Developling Backend Server &amp; Service



----------

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

----------

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

----------
