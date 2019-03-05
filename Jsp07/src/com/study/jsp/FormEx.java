package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FormEx")
public class FormEx extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		System.out.println("doPost");
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		String[] hobbys = request.getParameterValues("hobby");
		String major = request.getParameter("major");
		String protocol = request.getParameter("protocol");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println("<html><head><style>span{font-weight:bold; color: red}</style></head><body>");
		writer.println("<span>아이디 : </span>" + id + "<br>");
		writer.println("<span>비밀번호 : </span>" + pw + "<br>");
		writer.println("<span>취미 : </span>" + Arrays.deepToString(hobbys) + "<br>");
		writer.println("<span>전공 : </span>" + major + "<br>");
		writer.println("<span>프로토콜 : </span>" + protocol);
		writer.println("</body></html>");

		
	}

}
