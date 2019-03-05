package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostMethod")
public class PostMethod extends HttpServlet {
       

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
		  writer.println("<h1>HELLOW</h1>");
		  writer.println("</body>");
		  writer.println("</html>");
		  
		  writer.println("HI");
		  
		  writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
	     System.out.println("doPost"); //post를 실행하기 위해서 html로 작성해서 웹브라우져로 오픈
	     response.setContentType("text/html; charset=UTF-8"); //웹브라우져 html 파싱하기 위해
			PrintWriter writer = response.getWriter();
			
			  writer.println("<html>");
			  writer.println("<head>");
			  writer.println("<style>");
			  writer.println("h1{color:pink}");
			  writer.println("</style>");
			  writer.println("</head>");
			  writer.println("<body>");
			  writer.println("<h1>Post 방식입니다.<br>따라서 doGet 메서드가 호출됩니다.</h1>");
			  writer.println("</body>");
			  writer.println("</html>");
			  
			  writer.println("HI");
			  
			  writer.close();
	
	}

}
