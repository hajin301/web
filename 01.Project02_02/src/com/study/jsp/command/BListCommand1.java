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

public class BListCommand1 implements Service {
	
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
		
		BDao dao1 = new BDao();
		BPageInfo pinfo = dao1.articlePage(nPage, request);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<BDto> dtos1 = dao1.list1(nPage);
		request.setAttribute("list1", dtos1);
		
	}
}
