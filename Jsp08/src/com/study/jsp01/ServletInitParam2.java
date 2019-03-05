package com.study.jsp01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns= {"/ServletInitParam2"},
            initParams= {@WebInitParam(name="id",value="abcdef"),
            		     @WebInitParam(name="pw", value="1234"),
            		     @WebInitParam(name="path", value="C:\\javalec\\workspace")})
public class ServletInitParam2 extends HttpServlet {
	   


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		System.out.println("doGet");
		
		String id = getInitParameter("id");
		String pw = getInitParameter("pw");
		String path = getInitParameter("path");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println("<html><head><style>span{font-weight:bold; color: red}</style></head><body>");
		writer.println("<span>아이디 : </span>" + id + "<br>");
		writer.println("<span>비밀번호 : </span>" + pw + "<br>");
		writer.println("<span>path : </span>" + path);
		writer.println("</body></html>");
		
		writer.close();
		
	}




}
