<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null) { %>
	<jsp:forward page = "main.jsp"></jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/jquery.js"></script>
	
	<!-- google -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="899739515511-32bs4aqab8rdkkjhkihvu52nh1dejjim.apps.googleusercontent.com">
	<!-- kakao -->
	<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<title>Insert title here</title>
<style>
.test1 {
width: 300px;
}
.test2{
 margin: 50px
}

.test2 input{
 width:400px; background-color:red; border:0px; margin-left:300px;
}


</style>
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
<p>
	<div class="container">
		<div class="row">
    		<div class="col-sm-8"></div>
    		<div class="col-sm-4">
    		</div>
  		</div>
	</div>
<p>

<ul class="nav nav-pills">
  <li class="nav-item">
    <a class="nav-link active" href="../03.main/mainpage.jsp">HOME</a>
  </li>
  <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">게시판</a>
    <div class="dropdown-menu">
      <a class="dropdown-item" href=>공지사항 게시판</a>
      <a class="dropdown-item" href="../02.BB/list.do?page=<%=session.getAttribute("cpage") %>">자유게시판</a>
      <a class="dropdown-item" href="#">자료실</a>
    </div>
  </li>
</ul>
	
<p>
		
<%-- 	 <form id = "login_frm" method = "post">
	아이디: <input type = "text" name = "id" id="id"
	      		   value = "<% if(session.getAttribute("id") != null)
	      			              out.println(session.getAttribute("id"));
	      			              %>"> <br>
	비밀번호 : <input type = "password" name = "pw" id="pw" size="20"><br><p>
	<input type = "button" value = "로그인" onclick="submit_ajax()"> &nbsp;&nbsp;
	<input type = "button" value = "회원가입" onclick ="javascript:window.location = 'join.jsp'"> &nbsp;&nbsp;
	</form>  --%> 
	
	<div class ="container test1">
	    <form id = "login_frm" method = "post">
			<!-- <label for="exampleInputID">아이디</label> -->
			<input type = "text" name="id" class="form-control" id="id" aria-describedby="IDHelp" placeholder="Enter ID"
			      		   value = "<% if(session.getAttribute("id") != null)
			      			         out.println(session.getAttribute("id"));
			      			         %>"> <br>
			<!-- <label for="exampleInputPassword">비밀번호</label>	 -->
			<input type = "password" name = "pw" size="20" class="form-control" id="pw" placeholder="Password"><br><p>
			<input type = "button" class="btn btn-primary" value = "로그인" onclick="submit_ajax()"> &nbsp;&nbsp;
			<input type = "button" class="btn btn-primary" value = "회원가입" onclick ="javascript:window.location = 'join.jsp'"> &nbsp;&nbsp;
			
		</form>
	</div>

	<div class="container test2">
	<input type = "submit" class="btn btn-primary" value = "   구글계정으로 로그인   " onclick="javascript:window.location = '../01.googlelogin.jsp'"> &nbsp;&nbsp; <p><p>
	<input type = "submit" class="btn btn-primary" value = "  카카오계정으로 로그인 "> &nbsp;&nbsp; <p>
	<input type = "submit" class="btn btn-primary" value = "  네이버계정으로 로그인 "> &nbsp;&nbsp; <p>
    <input type = "submit" class="btn btn-primary" value = "페이스북계정으로 로그인">
    </div>
    
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
</body>
</html>