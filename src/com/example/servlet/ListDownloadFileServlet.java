package com.example.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(name="ListDownloadFileServlet", urlPatterns="/list-download")
public class ListDownloadFileServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		String downloadPath = getServletContext().getRealPath("/");
		downloadPath = downloadPath + "WEB-INF" + File.separator + "download" + File.separator;
		System.out.println(downloadPath);
		File file = new File(downloadPath);//创建文件夹
		File[] files;
		if(file.isDirectory()){
			files = file.listFiles();//列出文件夹中的内容
			req.setAttribute("files",files);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/list-download.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
