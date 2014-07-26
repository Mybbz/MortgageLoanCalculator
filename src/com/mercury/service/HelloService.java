package com.mercury.service;

import com.mercury.beans.User;
import com.mercury.beans.UserInfo;
import com.mercury.dao.HelloDao;

public class HelloService {
	private HelloDao hd;
		
	public HelloDao getHd() {
		return hd;
	}
	public void setHd(HelloDao hd) {
		this.hd = hd;
	}
    
	public void saveUser(User user){
		hd.save(user);
		
	}
	
	public String validate(String name){
		return hd.findByName(name);
	}
	
	public double findByYear(int year){
		return hd.findByYear(year);
	}
	
	
}
