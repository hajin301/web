package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.MemberDAO;
import com.study.jsp.Service;
import com.study.jsp.dao.BDao;
import java.util.Enumeration;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;


public class BFileCommand implements Service{
	
	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) 
	{
		
		System.out.println("fileFormOk.do");
		String path;
		
		 path = request.getRealPath("fileFolder");
	    	
	    	int size = 1024 * 1024 * 10; //10M
	    	String file = "";
	    	String oriFile="";
		
	    	try {
	    		MultipartRequest multi = new MultipartRequest(request, path, size,
	    				                     "UTF-8", new DefaultFileRenamePolicy());
	    		
	    		Enumeration files = multi.getFileNames();
	    		String str = (String)files.nextElement();
	    		
	    		file = multi.getFilesystemName(str);
	    		oriFile = multi.getOriginalFileName(str);
	    		
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    		
	}

}
