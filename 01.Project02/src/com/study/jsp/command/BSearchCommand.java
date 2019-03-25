package com.study.jsp.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.Service;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.BPageInfo;

public class BSearchCommand implements Service{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		
		String find_field = request.getParameter("find_field");
		String find_name = request.getParameter("find_name");
		
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		BDao dao = new BDao();
		ArrayList<BDto> list = dao.BSearch(find_field, find_name);
		
		request.setAttribute("List", list);
		request.setAttribute("page", nowPage);
				
	}
	
	
}
