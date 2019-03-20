<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<title>Insert title here</title>

</head>
<body>
<ul class="nav nav-pills">
  <li class="nav-item">
    <a class="nav-link active" href="#">Active</a>
  </li>
  <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
    <div class="dropdown-menu">
      <a class="dropdown-item" href="#">Action</a>
      <a class="dropdown-item" href="#">Another action</a>
      <a class="dropdown-item" href="#">Something else here</a>
      <div class="dropdown-divider"></div>
      <a class="dropdown-item" href="#">Separated link</a>
    </div>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">Link</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
  </li>
</ul>

	<form id = "login_frm" method = "post">
		<div class="form-group">
		<label for="exampleInputID">아이디</label>
		<input type = "text" class="form-control" id="exampleInputID" aria-describedby="IDHelp" placeholder="Enter ID"
		      		   value = "<% if(session.getAttribute("id") != null)
		      			         out.println(session.getAttribute("id"));
		      			         %>"> <br>
		</div>
		
		<div class="form-group">
		<label for="exampleInputPassword1">비밀번호</label>	
		<input type = "password" name = "pw" id="pw" size="20"><br><p>
		</div>
		<input type = "button" value = "로그인" onclick="submit_ajax()"> &nbsp;&nbsp;
		<input type = "button" value = "회원가입" onclick ="javascript:window.location = 'join.jsp'"> &nbsp;&nbsp;
		
	</form>
<div class="login-sns sns-wrap-over" id="sns_outlogin">
    <div class="sns-head">소셜계정으로 로그인</div>
    <div class="sns-wrap">
                <a href="http://amina.co.kr/demo/plugin/social/popup.php?provider=naver&amp;url=%2Fdemo%2F%3Fpv%3DBasic" class="sns-icon social_link sns-naver" title="네이버">
            <span class="ico"></span>
            <span class="txt">네이버<i> 로그인</i></span>
        </a>
                        <a href="http://amina.co.kr/demo/plugin/social/popup.php?provider=kakao&amp;url=%2Fdemo%2F%3Fpv%3DBasic" class="sns-icon social_link sns-kakao" title="카카오">
            <span class="ico"></span>
            <span class="txt">카카오<i> 로그인</i></span>
        </a>
                        <a href="http://amina.co.kr/demo/plugin/social/popup.php?provider=facebook&amp;url=%2Fdemo%2F%3Fpv%3DBasic" class="sns-icon social_link sns-facebook" title="페이스북">
            <span class="ico"></span>
            <span class="txt">페이스북<i> 로그인</i></span>
        </a>
                        <a href="http://amina.co.kr/demo/plugin/social/popup.php?provider=google&amp;url=%2Fdemo%2F%3Fpv%3DBasic" class="sns-icon social_link sns-google" title="구글">
            <span class="ico"></span>
          
</body>
</html>