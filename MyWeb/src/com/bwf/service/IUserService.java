package com.bwf.service;

import java.security.NoSuchAlgorithmException;

import com.bwf.bean.User;

public interface IUserService {

	//重名
	//接收对象，返回对象或null
	User rName(User user);
	
	//注册
	Integer userRegister(User user) throws NoSuchAlgorithmException;
	
	//登录
	User userLogin(User user) throws NoSuchAlgorithmException;
	
	
}
