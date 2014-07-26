package com.mercury.dao;

import java.util.List;

import com.mercury.beans.User;

public interface HelloDao {
	
	public void save(User user);
	public String findByName(String name);
	public double findByYear(int year);
}
