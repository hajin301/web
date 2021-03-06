package com.study.jsp.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.Service;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.BPageInfo;

public class BListCommand3 implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		int nPage = 1;
		
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		}catch (Exception e) {
		}
		
		BDao dao3 = new BDao();
		BPageInfo pinfo = dao3.articlePage(nPage, request);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		
		ArrayList<BDto> dtos3 = dao3.list3(nPage);
		request.setAttribute("list3", dtos3);
		
	}
}
