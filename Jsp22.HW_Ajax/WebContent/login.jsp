<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null) { %>
	<jsp:forward page = "main.jsp"></jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src = "http://code.jquery.com/jquery.js"></script>
<script>

function submit_ajax() {
	var queryString = $("#log_frm").serialize();
	$.ajax({
	   url: 'loginProcess',
	   type: 'post',
	   dataType: 'text',
	   success: function(json) {
		   var result = JSON.parse(json);
		   
		   if(result.code="success") {
			   alert(result.desc)
			   window.location.replace("login.jsp");
		   }else {
			   alert(result.desc);
		   }
	   }
	});
}

</script>

</head>
<body>
	<form id = "log_frm">
	아이디: <input type = "text" name = "id"
	      		   value = "<% if(session.getAttribute("id") != null)
	      			              out.println(session.getAttribute("id"));
	      			              %>"> <br>
	비밀번호 : <input type = "password" name = "pw"><br><p>
	<input type = "submit" value = "로그인" onclick = "submit_ajax()"> &nbsp;&nbsp;
	<input type = "button" value = "회원가입" onclick ="javascript:window.location = 'join.jsp'"> &nbsp;&nbsp;
	</form>
</body>
</html>