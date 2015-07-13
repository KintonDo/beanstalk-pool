package com.gome.client.beanstalk.pool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class BeanstalkPool extends GenericObjectPool<BeanstalkClient>{
	
	protected static Log log = LogFactory.getLog(BeanstalkPool.class);	
	public static BeanstalkPool selfPool=null;
	
	public BeanstalkPool(BeanstalkClientFactory factory){
		super(factory);
	}
	
	public BeanstalkPool(BeanstalkClientFactory factory,PoolConfig config) {
		super(factory,config);	
	}
	
	private synchronized static BeanstalkPool getPool(PoolConfig poolConfig){
		if(selfPool==null){
			selfPool=new BeanstalkPool(new BeanstalkClientFactory(poolConfig), poolConfig);
		}
		return selfPool; 
	} 
	
	public synchronized static BeanstalkClient getClient(PoolConfig poolConfig) throws Exception{
		BeanstalkPool pool=getPool(poolConfig);
		return pool.borrowObject();
	}	
	public synchronized void freeClient(BeanstalkClient client){
		selfPool.returnObject(client);
	}
}
