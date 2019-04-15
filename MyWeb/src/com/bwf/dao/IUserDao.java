package com.bwf.dao;

import com.bwf.bean.User;

public interface IUserDao {

	//查询用户信息，按照用户名
	User selectAslogid(User user);
	
	//数据库插入数据
	Integer insert(User user);
	
	//查询，按照用户名密码：登录用的
	User select_login(User user);
	
}
