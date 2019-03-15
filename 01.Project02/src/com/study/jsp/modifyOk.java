package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class modifyOk implements Service {
       
    public modifyOk() {
     
    }
    
    @Override
	public void execute(HttpServletRequest request, 
			            HttpServletResponse response)
			throws ServletException, IOException 
	{
		
    	System.out.println("modifyOk");
    	
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
  	    session = request.getSession();
  	    String id = (String)session.getAttribute("id");
  	    
		String pw = request.getParameter("pw");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDTO dto = new MemberDTO();
	    dto.setId(id);
	    dto.setPw(pw);
	    dto.seteMail(eMail);
	    dto.setAddress(address);
	    
	   
	    PrintWriter writer = response.getWriter();
	    response.setContentType("text/html; charset=UTF-8");
	    
	    MemberDAO dao = MemberDAO.getInstance();
		int ri = dao.updateMember(dto);
		String json_data = "";
		
		if (ri == 1) {
			json_data = "{\"code\" : \"success\", \"desc\" : \"정보가 수정되었습니다.\"}";
	    } else {
	    	json_data = "{\"code\" : \"fail\", \"desc\" : \"정보수정에 실패했습니다.\"}"; 
	    }
		
		session.setAttribute("eMail", dto.geteMail());
		session.setAttribute("address", dto.getAddress());
		
		response.setContentType("application/json); charset=UTF-8");
  	    writer.println(json_data);
  	    writer.close();
		
	}

}
