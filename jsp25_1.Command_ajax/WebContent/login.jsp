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
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
function submit_ajax() {
		
	var queryString = $("#login_frm").serialize();	
		$.ajax({
			url: 'loginOk.do',
			type: 'POST',
			data: queryString,
			dataType: 'text',
			success: function(json) {
				//consle.log(json);
				var result = JSON.parse(json);
				if(result.code == "success") {
					alert(result.desc)
					window.location.replace("main.jsp");
				} else {
					alert(result.desc);
				}
			}
		});
	}

</script>

</head>
<body>
	<form id = "login_frm" method = "post">
	아이디: <input type = "text" name = "id" id="id"
	      		   value = "<% if(session.getAttribute("id") != null)
	      			              out.println(session.getAttribute("id"));
	      			              %>"> <br>
	비밀번호 : <input type = "password" name = "pw" id="pw" size="20"><br><p>
	<input type = "button" value = "로그인" onclick="submit_ajax()"> &nbsp;&nbsp;
	<input type = "button" value = "회원가입" onclick ="javascript:window.location = 'join.jsp'"> &nbsp;&nbsp;
	</form>
</body>
</html>