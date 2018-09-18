package com.example.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Employee;
import com.google.gson.Gson;

@WebServlet(name="EmployeeServlet", urlPatterns="/employee")
public class EmployeeServlet extends HttpServlet {

	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Employee employee = new Employee(1, "Karan", "IT", 5000);
        String employeeJsonString = this.gson.toJson(employee);
 
        PrintWriter out = resp.getWriter();
        //text/html
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);//把内容发送到浏览器
        out.flush();   

	}

	//接受用户发送来的json字符串
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		Employee emp = gson.fromJson(reader, Employee.class);
		System.out.print(emp);
	}
	
}
