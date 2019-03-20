package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.Service;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BContentCommand implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String bId = request.getParameter("bId");
		String bKind = request.getParameter("kind");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId,bKind);
		
		request.setAttribute("content_view", dto);
	}
}
