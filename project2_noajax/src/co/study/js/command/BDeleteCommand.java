package co.study.js.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.study.js.dao.BDao;
import co.study.js.dto.BDto;

public class BDeleteCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		dao.delete(bId);
	}
}
