package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.Service;
import com.study.jsp.dao.BDao;

public class BDeleteCommand implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		dao.delete(bId);
	}
}
