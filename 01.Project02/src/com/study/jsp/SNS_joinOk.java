package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SNS_joinOk implements Service {
       
    public SNS_joinOk() {
     
    }
    
    @Override
	public void execute(HttpServletRequest request, 
			            HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("SNS_joinOk");
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("SId");
		String name = request.getParameter("SName");
		String eMail = request.getParameter("SEmail");
		System.out.println("이름:"+name);
		MemberDTO dto = new MemberDTO();
	    dto.setId(id);
	    dto.setName(name);
	    dto.seteMail(eMail);
	    dto.setrDate(new Timestamp(System.currentTimeMillis()));
	    System.out.println("아이디" + dto.getId());
	    MemberDAO dao = MemberDAO.getInstance();
	    
	    HttpSession session = request.getSession();
	    String json_data="";
	    
	    if(dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT) {
	    	
	    	json_data = "{\"code\": \"fail\", \"desc\" : \"아이디가 이미 존재 합니다.\" }";
	    	System.out.println("이미존재하는 아이디");
	    	response.sendRedirect("SNS_loginOk.do");
	    }else {
	    	int ri = dao.insertMember(dto);
	    	if (ri == MemberDAO.MEMBER_JOIN_SUCCESSS) {
	    		session.setAttribute("id", dto.getId());
	    		session.setAttribute("name", dto.getName());
	    		session.setAttribute("eMail", dto.geteMail());
	    		session.setAttribute("validMem", "yes");
	    		System.out.println("이름" + dto.getName());
	    		json_data = "{\"code\": \"success\", \"desc\" : \"회원가입을 축하 합니다.\" }";
	    		response.sendRedirect("SNS_loginOk.do");
	    	} else {
	    		json_data = 
						"{\"code\": \"fail\", \"desc\" : \"에러가 발생하여 회원가입에 실패했습니다.\" }";
	    	}
	    	
	    	
	    	response.setContentType("application/json; charset=UTF-8");
	    	PrintWriter writer = response.getWriter();
	    	writer.println(json_data);
	    	writer.close();
	    }
	}

}
