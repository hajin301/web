package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.Service;
import com.study.jsp.dao.BDao;

public class BWriteCommand implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{ 
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String Id = request.getParameter("Id");
		String bMenu = request.getParameter("bMenu");
		
		BDao dao = new BDao();
		dao.write(bName, bTitle, bContent, Id, bMenu);
		
		
	}

}