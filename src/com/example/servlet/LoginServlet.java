package com.example.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="LoginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//读cookie信息
		Cookie[] cs = req.getCookies();
		String username= null;
		for(Cookie c: cs){
			if("username".equals(c.getName())){
				username = URLDecoder.decode(c.getValue());
				req.setAttribute("username", username);
				break;
			}
		}
		
		//服务器内部地址
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//req.setCharacterEncoding("utf-8");
		//resp.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		String userpassword = req.getParameter("userpassword");
		System.out.println(username +"   "+ userpassword);
		//登录验证....
		//....
		
		//设置登录成功的标志
		req.getSession().setAttribute("logined", "yes");
		
		Cookie unameCookie = new Cookie("username",  URLEncoder.encode(username));
		unameCookie.setMaxAge(60*60);//秒为单位，
		resp.addCookie(unameCookie);//写cookie到浏览器中
		
		resp.sendRedirect("welcome");//  浏览器访问的相对地址 
	}
	
}
