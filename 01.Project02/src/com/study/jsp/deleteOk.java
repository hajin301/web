package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.Service;
import com.study.jsp.dao.BDao;

public class deleteOk implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("deleteOk");
		
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
  	    session = request.getSession();
  	    
  	    String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		MemberDTO dto = new MemberDTO();
	    dto.setId(id);
	    dto.setPw(pw);
	    
	    PrintWriter writer = response.getWriter();
	    response.setContentType("text/html; charset=UTF-8");
	    
	    MemberDAO dao = MemberDAO.getInstance();
		int ri = dao.updateMember(dto);
		int checkNum = dao.userCheck(id, pw);
		System.out.println(id);
		System.out.println(pw);
		String json_data = "";
		if(checkNum == MemberDAO.MEMBER_LOGIN_PW_NO_GOOD) {
			System.out.println("fail");
			json_data = "{\"code\" : \"fail\", \"desc\" : \"비밀번호를 다시 입력해 주세요.\"}";
		}else { 
	    		System.out.println("delete success");
	    		dao.deleteOK(id);
				json_data = "{\"code\" : \"success\", \"desc\" : \"정보가 삭제되었습니다.\"}";
		}

		
		response.setContentType("application/json); charset=UTF-8");
  	    writer.println(json_data);
  	    writer.close();
	
	}
}
