package com.bwf.service.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckYZMServlet
 */
@WebServlet("/checkyzm")
public class CheckYZMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.html");
	}

	//判断验证码
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//提取数据
		String request_yzm = request.getParameter("yzm");
		String session_yzm = request.getSession().getAttribute("yzm").toString();
		
		//统一大小写判断
		if(request_yzm.toUpperCase().equals(session_yzm.toUpperCase())) {
			response.getWriter().print("1");//对
		}
		else {
			response.getWriter().print("0");//错
		}
		
	}

}
