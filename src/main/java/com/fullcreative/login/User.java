package com.fullcreative.login;

import java.io.Serializable;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User implements Serializable {
	
	@PrimaryKey
	@Persistent
	private String userName;
	@Persistent
	private String password;
	@Persistent
	private String mailID;
	@Persistent
	private String dOB;
	@Persistent
	private String mobileNo;
	
	

	public User(String userName, String password, String mailID, String dOB, String mobileNo) {
		super();
		//this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.mailID = mailID;
		this.dOB = dOB;
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", mailID=" + mailID
				+ ", dOB=" + dOB + ", mobileNo=" + mobileNo + "]";
	}

}
