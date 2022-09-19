package com.rishabh.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Messaging {
	private String sid="ACf5a54ce8bf762e109a704e864c1b6f38";
	private String token="15d6040b8ea4966fe317b9cc8d5ab45f";
	private String fromPhoneNumber="+19854418745";
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getFromPhoneNumber() {
		return fromPhoneNumber;
	}
	public void setFromPhoneNumber(String fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}
	
}
