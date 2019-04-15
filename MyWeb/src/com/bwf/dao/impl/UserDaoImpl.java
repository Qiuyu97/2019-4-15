package com.bwf.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.bwf.bean.User;
import com.bwf.dao.IUserDao;

@Repository(value="userDao")
public class UserDaoImpl implements IUserDao{

	//私有属性
	private JdbcTemplate conn;
	
	//构造方法
	public UserDaoImpl() {
		//可以手工编写JDBC
		//也可以使用C3P0
		
		//Spring框架考虑到程序肯定会使用数据库的，
		//因此提供了一套自己的数据库连接操作，也包含连接池
		
		
		//第一步：创建对象
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		
		//调用set方法，对类中属性赋值
		datasource.setDriverClassName("com.mysql.jdbc.Driver");//驱动(mysql的驱动)	[必须导入jar包]	
		datasource.setUrl("jdbc:mysql://localhost:3306/bwf");//连接协议
		datasource.setUsername("root");//用户名
		datasource.setPassword("123456");//密码
		
		//如果连接的数据库不知道数据库的编码格式？
		//datasource.setUrl("jdbc:mysql://localhost:3306/库名?useUnicode=true&amp;charsetEncoding=utf8");//协议方式的
		
		
		//第二步：创建连接池connection对象，将数据库连接的资源对象传入
		this.conn = new JdbcTemplate(datasource);
					
	}
	
	
	
	/**
	 * 按照用户名检索用户信息
	 * 返回结果：user 或   null
	 * */
	@Override
	public User selectAslogid(User user) {

			
		
		//第三步：调用对象方法 .query(select)  .update(insert,update,delete)
		
		
		//自动执行语句，然后按照检索得到的结果，自动组合成我们提供的RowMapper映射对象类型，
		//返回的是一个当前对象集合
		//Spring中，对于检索query方法，默认会自动封装类型成为一个结果集合，利用List进行返回
		//当执行方法的时候，必须提供组合的类型是什么
		// RowMapper：行的映射，也就是 list中每一个元素的映射，User.class提供的是一个用户类的【反射】对象
		//query方法执行完毕后，会自动去实例化new，应为每一个结果都是一个新的
		
		//★★★★★★
		//注意：
		//	实体模型bean中，文件名对应表名，首字母大写
		//	必须对应：属性的名字 必须和 表的列 一致
		//否则无法执行数据填充
		
		//映射一个行对象
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
		//调用方法，
		//（"语句，支持占位符",映射对象提供集合实例化，参数替换占位符）
		List<User> list = conn.query("select * from `user` where `logid`=?;",rowMapper,user.getLogid());
		
		//如果有多个参数：
		//第三个参数，就是一个Object类型的数组，然后元素的顺序和问号对好
		//list = conn.query("select * from `user` where `id`=? and `logid`=? and `pwd`=?;",
		//		rowMapper,
		//		new Object[] {3,"admin","123456"});
		
		
		//判断是否查询到
		if(list.size()>0) {
			//返回这个User对象
			return list.get(0);
		}
		
		//否则返回null
		return null;
	}

	
	
	//insert数据
	@Override
	public Integer insert(User user) {		
		//执行
		return this.conn.update("insert into `user` values (null,'"+user.getLogid()+"','"+user.getPwd()+"',default);");
	}



	@Override
	public User select_login(User user) {
		
		
		//================================
		//第三重防护
		
		
		//模型映射对象
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
		//调用方法得到检索的结果集合
		List<User> list = this.conn.query("select * from `user` where `logid`=? and `pwd`=?;", rowMapper, new Object[] {user.getLogid(),user.getPwd()});
		
		//判断
		if(list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}

}
