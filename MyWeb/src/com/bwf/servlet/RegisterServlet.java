package com.bwf.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bwf.bean.User;
import com.bwf.service.IUserService;
import com.bwf.spring.BeanManager;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.html");
	}

	//注册
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//封装对象，调用方法		
		User user = (User)BeanManager.getBean("user");
		user.setLogid(request.getParameter("logid"));
		user.setPwd(request.getParameter("pwd"));
		
		
		//业务逻辑
		IUserService userService = (IUserService)BeanManager.getBean("userService");
		
		try {
			
			//调用业务逻辑，得到结果
			Integer rows = userService.userRegister(user);
			
		} catch (NoSuchAlgorithmException e) {
			//处理异常
			response.getWriter().print("注册失败！请联系管理员！");
			return;//结束
		}
		
		
		//业务需求，编程登录状态
		user.setPwd(request.getParameter("pwd"));//恢复密码		
		try {			
			user = userService.userLogin(user);
			
		} catch (NoSuchAlgorithmException e) {
			//处理异常
			response.getWriter().print("注册成功！状态改变失败！请联系管理员！");
			return;//结束
		}
		
		
		//判断结果
		if(user==null) {
			response.getWriter().print("无法登录！请联系管理员！");
			return;
		}
		else {
			//记录session
			request.getSession().setAttribute("user", user);
		}
		
		//进入主页
		response.sendRedirect("index.jsp");
		
	}

}
