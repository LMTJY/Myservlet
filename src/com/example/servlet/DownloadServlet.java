package com.example.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="DownloadServlet", urlPatterns="/download")
public class DownloadServlet extends HttpServlet {
private final int ARBITARY_SIZE = 1048;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		String fileName = req.getParameter("filename");
		System.out.println(fileName);
		//resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO_8859_1") );
		try(	InputStream in = req.getServletContext().getResourceAsStream("/WEB-INF/download/"+fileName);
				OutputStream out = resp.getOutputStream()){
			byte[] buffer = new byte[ARBITARY_SIZE];
			int numBytesRead;
			while((numBytesRead = in.read(buffer))>0){
				out.write(buffer, 0, numBytesRead);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
