package com.bwf.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eduask.gd.Identifying;

@WebServlet("/createyzm")
public class CreateYZMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//绘制验证码
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//第一步：实例化类对象，传入request，response
		Identifying idf = new Identifying(request, response);		
		//设置session名字
		idf.setSessionName("yzm");		
		//绘制出图片
		idf.create();
	}

}
