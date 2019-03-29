<%@ page import = "java.util.Enumeration" %>
<%@ page import = "com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import = "com.oreilly.servlet.MultipartRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%! String path; %>
    <%
    	path = request.getRealPath("fileFolder");
    	
    	int size = 1024 * 1024 * 10; //10M
    	int cnt = 0;
    	String file = "";
    	String oriFile="";
    	
    	String uploadPath = request.getSession().getServletContext().getRealPath(path);
    	;
    	
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
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
file upload success!

</body>
</html>