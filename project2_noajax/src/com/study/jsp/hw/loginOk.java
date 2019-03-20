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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDTO dto = new MemberDTO();
	    dto.setId(id);
	    dto.setPw(pw);

	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter writer = response.getWriter();
	    
	    MemberDAO dao = MemberDAO.getInstance();
	    
		if(dao.userCheck(id,pw) == MemberDAO.MEMBER_LOGIN_IS_NOT) {
	    
	    	//html 출력
	    	writer.println("<html><head></head><body>");
	    	writer.println("<script language=\"javascript\">");
	    	writer.println("    alert(\"아이디가 존재하지 않습니다.\");");
	    	writer.println("   history.go(-1);");
	    	writer.println("</script>");
	    	writer.println("</body></html>");
	    	writer.close();
	    	
	    } else if (dao.userCheck(id,pw) == MemberDAO.MEMBER_LOGIN_PW_NO_GOOD ){
	    	
	    		writer.println("<html><head></head><body>");
		    	writer.println("<script language=\"javascript\">");
		    	writer.println("    alert(\"비밀번호가 틀립니다.\");");
		    	writer.println("    document.location.href=\"login.jsp\";");
		    	writer.println("</script>");
		    	writer.println("</body></html>");
		    	writer.close();
	    		
	    } else if (dao.userCheck(id,pw) == MemberDAO.MEMBER_LOGIN_SUCCESS ){
	    	
	    	   HttpSession session = request.getSession();
	    	   dto = dao.getMember(id);
	    	   
	    	   if(dto == null) {
	    		   writer.println("<html><head></head><body>");
	    		   writer.println("<script language=\"javascript\">");
	    		   writer.println("    alert(\"존재하지 않는 회원 입니다.\");");
	    		   writer.println("    history.go(-1);");
	    		   writer.println("</script>");
	    		   writer.println("</body></html>");
	    		   writer.close();
		    	
	    	   } else {
	    		   String name = dto.getName();
				 	session.setAttribute("id", id);
				 	session.setAttribute("name", name);
				 	session.setAttribute("ValidMem", "yes");
				 	response.sendRedirect("main.jsp");
	    		   
	    	   }
				 
	    	}
	    }
	}


