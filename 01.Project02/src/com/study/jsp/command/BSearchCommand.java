package com.study.jsp.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
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
			throws ServletException, IOException 
	{
		
		String find_field = request.getParameter("find_field");
		String find_name = request.getParameter("find_name");

		int nPage = 1;
		
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		}catch (Exception e) {
		
		}
		
		BDao dao4 = new BDao();
		BPageInfo pinfo = dao4.articlePage(nPage, request);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<BDto> dtos4 = dao4.BSearch(find_field, find_name, nPage);
		request.setAttribute("BSearch", dtos4);
				
	}
	
	
}
