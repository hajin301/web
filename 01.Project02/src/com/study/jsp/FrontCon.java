package com.study.jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BContentCommand;
import com.study.jsp.command.BDeleteCommand;
import com.study.jsp.command.BListCommand;
import com.study.jsp.command.BListCommand1;
import com.study.jsp.command.BListCommand2;
import com.study.jsp.command.BListCommand3;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommand;
import com.study.jsp.command.BSearchCommand;
import com.study.jsp.command.BWriteCommand;

@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String command = uri.substring(conPath.length());
		System.out.println("command : " + command);
		String file = command.substring(0,7);
		System.out.println("file :" + file);
		
		String viewPage = null;
		HttpSession session = null;
		session = request.getSession();
		int curPage = 1;
		if (session.getAttribute("cpage") != null) {
			curPage = (int)session.getAttribute("cpage");
		}
		
		if(file.equals("/01.HW/")) {
			if(command.equals("/01.HW/loginOk.do")||command.equals("/loginOk.do")) {
				Service service = new loginOk();
				service.execute(request, response);
			} 
			else if (command.equals("/01.HW/modifyOk.do")||command.equals("/modifyOk.do")) {
				Service service = new modifyOk();
				service.execute(request, response);
			} 
			else if (command.equals("/01.HW/joinOk.do")||command.equals("/joinOk.do")) {
				Service service = new joinOk();
				service.execute(request, response);
			} 
			else if (command.equals("/01.HW/logout.do")||command.equals("/logout.do")) {
				logoutOk(request, response);
			}
			else if (command.equals("/01.HW/deleteOk.do")||command.equals("/deleteOk.do")) {
				Service service = new deleteOk();
				service.execute(request, response);
			}

		}else if(file.equals("/02.BB/")) {
			//BBS 시작
			if(command.equals("/02.BB/write_view.do")||command.equals("/write_view.do")) {
				viewPage = "write_view.jsp";
			}
			else if(command.equals("/02.BB/write_view.do")||command.equals("/loginOk.do")) {
				Service service = new loginOk();
				service.execute(request, response);
			}
			else if (command.equals("/02.BB/write.do")||command.equals("/write.do")) {
				Service service = new BWriteCommand();
				service.execute(request, response);
				viewPage = "list.do";
			}else if(command.equals("/02.BB/list.do")||command.equals("/list.do")) { 
				Service service = new BListCommand();
				service .execute(request, response);
				viewPage = "list.jsp";
			}else if(command.equals("/02.BB/board1.do")||command.equals("/board1.do")) { 
				Service service = new BListCommand1();
				service .execute(request, response);
				viewPage = "board1.jsp";
			}else if(command.equals("/02.BB/board2.do")||command.equals("/board2.do")) { 
				Service service = new BListCommand2();
				service .execute(request, response);
				viewPage = "board2.jsp";
			}else if(command.equals("/02.BB/board3.do")||command.equals("/board3.do")) { 
				Service service = new BListCommand3();
				service .execute(request, response);
				viewPage = "board3.jsp";
			}else if(command.equals("/02.BB/content_view.do")||command.equals("/content_view.do")) {
				Service service = new BContentCommand();
				service.execute(request, response);
				viewPage = "content_view.jsp";
			}else if(command.equals("/02.BB/modify_view.do")||command.equals("/modify_view.do")) {
				Service service = new BContentCommand();
				service.execute(request, response);
				viewPage = "modify_view.jsp";
			}else if(command.equals("/02.BB/Bmodify.do")||command.equals("/Bmodify.do")) {
				Service service = new BModifyCommand();
				service.execute(request, response);
				viewPage = "list.do";
			}else if (command.equals("/02.BB/delete.do")||command.equals("/delete.do")) {
				Service service = new BDeleteCommand();
				service.execute(request, response);
				viewPage = "list.do?page=" + curPage;
			}else if (command.equals("/02.BB/BSearch.do")||command.equals("/BSearch.do")) {
				Service service = new BSearchCommand();
				service.execute(request, response);
				viewPage = "Search_view.jsp";
			}else if (command.equals("/02.BB/reply_view.do")||command.equals("/reply_view.do")) {
				Service service = new BReplyViewCommand();
				service.execute(request, response);
				viewPage = "reply_view.jsp";
			}else if (command.equals("/02.BB/reply.do")||command.equals("/reply.do")) {
				Service service = new BReplyCommand();
				service.execute(request, response);
				viewPage = "list.do?page=" + curPage;
			}
	
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}else {
			//SNS 시작
			if (command.equals("/SNS_joinOk.do")) {
				Service service = new SNS_joinOk();
				service.execute(request, response);	
			} else if (command.equals("/SNS_loginOk.do")) {
				Service service = new SNS_loginOk();
				service.execute(request, response);	
			}
			
		}
	}
	
	private void logoutOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("logout");
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
	}
	

}
