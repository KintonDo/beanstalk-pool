package com.gome.client.beanstalk.test;

import java.util.Date;

import com.gome.client.beanstalk.BeanstalkJob;
import com.gome.client.beanstalk.pool.BeanstalkClient;
import com.gome.client.beanstalk.pool.BeanstalkPool;
import com.gome.client.beanstalk.pool.BeanstalkShardPool;
import com.gome.client.beanstalk.pool.PoolConfig;

/**
 * <p>
 * User: doujintong
 * <p>
 * Date: 2015-4-30
 * <p>
 * Version: 1.0
 */
public class PoolTest {

	public static void main(String[] args) throws Exception {
		testBeanstalkShardPool();
	}
	
	private static void  testBeanstalkPool() throws Exception{
		PoolConfig config=new PoolConfig("10.144.34.1", 11300, "test");
		BeanstalkClient client = BeanstalkPool.getClient(config);         //单节点
		client.put(new Student(1001, "douwanglong", new Date(), false));
		BeanstalkJob job = client.reserve(6000);
		Student s = job.get();
		System.out.println(s.getUserName());
		System.out.println(s.getHireDate());
		BeanstalkPool.selfPool.freeClient(client);
	}
	
	 
	private static void  testBeanstalkShardPool() throws Exception{
		PoolConfig config=new PoolConfig("10.144.34.1,10.144.34.2", 11300, "test"); //集群hash存储
		BeanstalkClient client = BeanstalkShardPool.getClient(config);
		client.put(new Student(1001, "douwanglong", new Date(), false));
		BeanstalkJob job = client.reserve(6000);
		Student s = job.get();
		System.out.println(s.getUserName());
		System.out.println(s.getHireDate());
		BeanstalkShardPool.freeClient(client);
	}
}
