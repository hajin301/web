package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/HellowWorld")
public class HellowWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		System.out.println("HelloworldId~~");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		  writer.println("<html>");
		  writer.println("<head>");
		  writer.println("<style>");
		  writer.println("h1{color:pink}");
		  writer.println("</style>");
		  writer.println("</head>");
		  writer.println("<body>");
		  writer.println("<h1>HelloWorld~~~</h1>");
		  writer.println("</body>");
		  writer.println("</html>");
		  
		  writer.println("HI");
		  
		  writer.close();
		  
		  
		
	}



}
