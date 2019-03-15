package co.study.js.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.study.js.dao.BDao;
import co.study.js.dto.BDto;

public class BContentCommand implements BCommand {
	
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
