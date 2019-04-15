package com.bwf.service.impl;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bwf.bean.User;
import com.bwf.dao.IUserDao;
import com.bwf.service.IUserService;
import com.bwf.util.Encrypt;

@Service(value="userService")
public class UserServiceImpl implements IUserService{

	
	//dao层对象
	@Autowired
	private IUserDao userDao;
	
	
	/**
	 * 判断是否重名：
	 * 重名返回：对象
	 * 没有重名：null
	 */
	@Override
	public User rName(User user) {
		//没有具体业务需求：企业开中中，这里应该提供关键字检索功能		
		//提供到dao层，得到数据库操作结果
		return this.userDao.selectAslogid(user);
	}


	@Override
	public Integer userRegister(User user) throws NoSuchAlgorithmException {
		
		//业务逻辑：加密MD5
		user.setPwd(Encrypt.toMD5(user.getPwd()));
		
		//调用Dao层方法，得到结果	
		return this.userDao.insert(user);
	}


	//登录
	@Override
	public User userLogin(User user) throws NoSuchAlgorithmException {
		//业务逻辑：加密MD5
		user.setPwd(Encrypt.toMD5(user.getPwd()));
		
		return this.userDao.select_login(user);
	}

}
