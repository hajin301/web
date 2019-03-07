<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");

Object obj1 = session.getAttribute("name");
String sId = (String)obj1;

if(sId == null) {
	response.sendRedirect("login.html");
}else {
	out.println(sId + "님 안녕하세요." + "<br>");
}

%>

<a href = "logout.jsp">로그아웃</a> <p>
<a href = "sessiontest.jsp">쿠키 테스트</a>
</body>
</html>