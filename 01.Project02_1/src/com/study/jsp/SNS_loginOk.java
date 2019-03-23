package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SNS_loginOk implements Service {
       
	public SNS_loginOk() {
     
    }
	
    @Override
	public void execute(HttpServletRequest request, 
			            HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("SNS_loginOk");
		
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html; charchet=UTF8");

		String id = request.getParameter("SId");
		String email = request.getParameter("SEmail");
		
		System.out.println(id);
		
	    MemberDAO dao = MemberDAO.getInstance();
	    int checkNum = dao.SNS_userCheck(id, email);
	    String json_data = "";
	    
		if(dao.SNS_userCheck(id, email) == MemberDAO.MEMBER_LOGIN_IS_NOT) {
			System.out.println("일치하는 아이디 없음");
			json_data = "{\"code\" : \"fail\", \"desc\" : \"아이디가 존재하지 않습니다.\"}";
			response.sendRedirect("../01.HW/join.jsp");
	    } else if (dao.SNS_userCheck(id,email) == MemberDAO.MEMBER_LOGIN_SUCCESS ){	    	
	    	  MemberDTO dto = dao.getMember(id);
	    	  System.out.println("로그인 성공");	
	    	  json_data = "{\"code\" : \"success\", \"desc\" : \"로그인 성공\"}"; 
	    	   if(dto == null) {
	    		   System.out.println("아이디가 없음");
	    		   json_data = "{\"code\" : \"fail\", \"desc\" : \"존재하지 않는 아이디 입니다.\"}";
	    	   } else {
	    		   System.out.println("로그인 성공");
	    		   String name = dto.getName();
				 	HttpSession session = request.getSession();
	    		    session.setAttribute("id", id);
				 	session.setAttribute("name", name);
				 	session.setAttribute("ValidMem", "yes");
				 	response.sendRedirect("./01.HW/main.jsp");
	    	   }
	    	   response.setContentType("application/json; charset=UTF-8");
	    	   writer.println(json_data);
	    	   writer.close();
				 
	    	}
	    }
	}


