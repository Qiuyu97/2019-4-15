package com.bwf.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bwf.bean.User;
import com.bwf.service.IUserService;
import com.bwf.spring.BeanManager;

@WebServlet("/rname")
public class RNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get：非法访问
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//友好：js提示，跳转
		//默认
		response.sendRedirect("register.html");
	}

	//重名
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//装箱:
		User user = (User)BeanManager.getBean("user");
		user.setLogid(request.getParameter("logid"));
		
		//调用Service方法，得到结果
		IUserService userService = (IUserService)BeanManager.getBean("userService");
		
		user = userService.rName(user);
		
		//处理结果
		if(user==null) {
			response.getWriter().print("0");//可以注册
		}
		else {
			response.getWriter().print("1");//重名
		}
		
	}

}
