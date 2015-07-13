package com.gome.client.beanstalk.pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanstalkShardPool{
	protected static Log log = LogFactory.getLog(BeanstalkShardPool.class);
	
	private static AtomicLong atomicLong=new AtomicLong(0);
	
	private static Map<String, BeanstalkPool> pools=new ConcurrentHashMap<String, BeanstalkPool>();
	
	private BeanstalkShardPool(){}
	
	private synchronized static BeanstalkPool getPool(PoolConfig poolConfig){
		String hashKey=poolConfig.getHost()+":"+poolConfig.getPort()+"@"+poolConfig.getTube();
		BeanstalkPool pool=pools.get(hashKey);
		if(pool==null){
			pool=new BeanstalkPool(new BeanstalkClientFactory(poolConfig), poolConfig);
			pools.put(hashKey, pool);
		}
		return pool;
	}
	
	public synchronized static BeanstalkClient getClient(PoolConfig poolConfig) throws Exception{
		String[] hosts=poolConfig.getHost().split(",");
		int hashkey=hashIP(String.valueOf(atomicLong.getAndAdd(1))) % hosts.length;//hashIp
		System.out.println("hashIp:-----"+hosts[hashkey]);
		BeanstalkPool pool =getPool(new PoolConfig(hosts[hashkey], poolConfig.getPort(), poolConfig.getTube())); 
		return pool.borrowObject();
	}
	
	public synchronized static void freeClient(BeanstalkClient client){
		String hashKey=client.addr+":"+client.port+"@"+client.tube; 
		BeanstalkPool pool=pools.get(hashKey);
		if(pool!=null)pool.returnObject(client);
	}
	
	private static int hashIP(String key) {
		int hashCode = 0;
		int i = 0;
		int len = key.length();
		for (; i < len; i++) {
			hashCode = ((hashCode * 33) + key.codePointAt(i)) & 0x7fffffff;
		}
		return hashCode;
	}
}
