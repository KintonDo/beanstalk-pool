package com.gome.client.beanstalk.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class BeanstalkClientFactory extends BasePooledObjectFactory<BeanstalkClient>{
	private PoolConfig poolConfig;
	
	public BeanstalkClientFactory(PoolConfig poolConfig){this.poolConfig=poolConfig;}
	
	@Override
	public BeanstalkClient create() throws Exception {
		return new BeanstalkClient(this.poolConfig);
	}
	@Override
	public PooledObject<BeanstalkClient> wrap(BeanstalkClient client) {
		return new DefaultPooledObject<BeanstalkClient>(client);
	}
	
	@Override
    public void passivateObject(PooledObject<BeanstalkClient> pooledObject)throws Exception  {
			super.passivateObject(pooledObject);
    }
	
	
	@Override
	public void activateObject(PooledObject<BeanstalkClient> p)throws Exception {
		super.activateObject(p);
	}

	@Override
	public void destroyObject(PooledObject<BeanstalkClient> p) throws Exception {
		super.destroyObject(p);
		BeanstalkClient c=p.getObject();
		if(c!=null){
			if(c.con!=null){
				c.con.close();
			}
		}
	}

	@Override
	public PooledObject<BeanstalkClient> makeObject() throws Exception {
		return super.makeObject();
	}

	@Override
	public boolean validateObject(PooledObject<BeanstalkClient> p) {
		BeanstalkClient c=p.getObject();
		return c.con!=null && c.con.isOpen();
	}
}
