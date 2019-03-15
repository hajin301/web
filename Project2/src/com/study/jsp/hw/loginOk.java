package com.study.jsp.hw;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginOk implements Service {
       
	public loginOk() {
     
    }
	
    @Override
	public void execute(HttpServletRequest request, 
			            HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("loginOk");
		
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html; charchet=UTF8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
	    MemberDAO dao = MemberDAO.getInstance();
	    int checkNum = dao.userCheck(id,  pw);
	    String json_data = "";
	    
		if(dao.userCheck(id,pw) == MemberDAO.MEMBER_LOGIN_IS_NOT) {
			json_data = "{\"code\" : \"fail\", \"desc\" : \"아이디가 존재하지 않습니다.\"}";
	    } else if (dao.userCheck(id,pw) == MemberDAO.MEMBER_LOGIN_PW_NO_GOOD ){
	    	json_data = "{\"code\" : \"success\", \"desc\" : \"비밀번호가 맞지 않습니다.\"}";	
	    } else if (dao.userCheck(id,pw) == MemberDAO.MEMBER_LOGIN_SUCCESS ){
	    	
	    	  MemberDTO dto = dao.getMember(id);
	    	  	json_data = "{\"code\" : \"success\", \"desc\" : \"로그인 성공\"}"; 
	    	   if(dto == null) {
	    		   json_data = "{\"code\" : \"fail\", \"desc\" : \"존재하지 않는 아이디 입니다.\"}";
	    	   } else {
	    		   String name = dto.getName();
				 	HttpSession session = request.getSession();
	    		    session.setAttribute("id", id);
				 	session.setAttribute("name", name);
				 	session.setAttribute("ValidMem", "yes");
	    	   }
	    	   response.setContentType("application/json); charset=UTF-8");
	    	   writer.println(json_data);
	    	   writer.close();
				 
	    	}
	    }
	}


