<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<%!
int result;
%>

<%
if(request.getMethod().equals("GET")) {
	response.sendRedirect("error40A.html");
} //페이지를 다른 곳으로 넘길 때 사용

request.setCharacterEncoding("UTF-8");

String snum1 = request.getParameter("num1");
String snum2 = request.getParameter("num2");
String cal = request.getParameter("cal");

int num1 = Integer.parseInt(snum1);
int num2 = Integer.parseInt(snum2);
%>

<%
if(cal.equals("+")) {
	result = num1 + num2;
}else if(cal.equals("-")) {
	result = num1 - num2;
}else if(cal.equals("x")) {
	result = num1 * num2;
}else if(cal.equals("/")) {
	result = num1 / num2;
}
%>

<%=num1%> <%=cal%> <%=num2 %> <%= "=" %> <%=result%>

</body>
</html>