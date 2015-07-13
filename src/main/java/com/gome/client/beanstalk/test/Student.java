package com.gome.client.beanstalk.test;

import java.io.Serializable;
import java.util.Date;

public class Student {

	public Student() {
	}

	/**
	 * 
	 */

	private long userId;
	private String userName;
	private Date hireDate;
	private boolean isLimit;

	public Student(long userId, String userName, Date hireDate, boolean isLimit) {
		this.userId = userId;
		this.userName = userName;
		this.hireDate = hireDate;
		this.isLimit = isLimit;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public boolean isLimit() {
		return isLimit;
	}

	public void setLimit(boolean isLimit) {
		this.isLimit = isLimit;
	}

}
