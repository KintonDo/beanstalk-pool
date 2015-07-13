package com.gome.client.beanstalk.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class PoolConfig extends GenericObjectPoolConfig {
	
	private String host;
	private int port;
	private String tube;

	
	public PoolConfig(){
		
	}
	public PoolConfig(String host, int port, String tube) {
		super();
		this.host = host;
		this.port = port;
		this.tube = tube;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getTube() {
		return tube;
	}
	public void setTube(String tube) {
		this.tube = tube;
	}
}
