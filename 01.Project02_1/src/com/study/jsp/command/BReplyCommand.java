package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.Service;
import com.study.jsp.dao.BDao;

public class BReplyCommand implements Service{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		String Id = request.getParameter("Id");

		
		BDao dao = new BDao();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent, Id);
		
	}

}
