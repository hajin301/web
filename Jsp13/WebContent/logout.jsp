<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
Cookie[] cookies = request.getCookies();
if(cookies != null) {
	for(int i =0; i<cookies.length; i++) {
		cookies[i].setMaxAge(0); //쿠키 유효기간 0으로 해서 저장값 없어지게 만듦
		response.addCookie(cookies[i]);
	}
}
%>
</body>
</html>