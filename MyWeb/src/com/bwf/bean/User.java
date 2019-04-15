package com.bwf.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

//注解，配合xml自动解析的：实体模型层： bean / entity / pojo / model 都可以起的名字
@Component(value="user")
public class User {

	//实体模型最终将提供数据库表格与程序对象的 ORM
	//它必须提供get，set
	private int id;
	private String logid;
	private String pwd;
	private Date createtime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	//----------------
	//提供测试可以重写toString()
	@Override
	public String toString() {
		return "User [id=" + id + ", logid=" + logid + ", pwd=" + pwd + ", createtime=" + createtime + "]";
	}
	
}
