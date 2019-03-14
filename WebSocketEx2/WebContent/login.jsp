<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
</head>
<script>
function form_check() {
	
	if($('#id').val().length == 0){
		alert("아이디는 필수사항입니다.");
		$('#id').focus();
		return;
	}
	
	if($('#pw').val().length == 0){
		alert("비밀번호는 필수사항입니다.");
		$('#pw').focus();
		return;
	}
	
	submit();
}

function submit() {
	document.login_form.submit();
}
</script>
<body>

<form name = login_form action="client.jsp" method="post">
	아이디 : <input type="text" name="id" id="id"
	           value="<% if(session.getAttribute("id") != null)
	        	        out.println(session.getAttribute("id"));
	        	        %>"><br>
	비밀번호 : <input type="password" name="pw" id="pw"><br><p>
	<input type="button" value="로그인" onclick="form_check();"> &nbsp;&nbsp;
	<input type="button" value="회원가입">
</form>

</body>
</html>