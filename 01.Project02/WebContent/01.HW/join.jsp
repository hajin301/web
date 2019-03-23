<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
#join_form {
margin:0px auto; margin-top:40px; margin-left:350px;
}
.test2{
 margin: 50px
}
.test2 input{
 width:400px; background-color:red; border:0px;
}
#join_form input {
margin-top:15px;
}
</style>

<script src="http://code.jquery.com/jquery.js"></script>
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
<script>
	
	function form_check() 
	{
		if($('#id').val().length == 0){
			alert("아이디는 필수사항입니다.");
			$('#id').focus();
			return;
		}
		
		if($('#id').val().length < 4) {
			alert("아이디는 4글자 이상 입력하셔야 합니다.");
			$('#id').focus();
			return;
		}
		
		if($('#pw').val().length == 0){
			alert("비밀번호는 필수사항입니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#pw').val() != $('#pw_check').val()){
			alert("비밀번호가 일치하지 않습니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#name').val().length == 0){
			alert("이름은 필수사항입니다.");
			$('#name').focus();
			return;
		}
		
		if($('#eMail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		}
		
		submit_ajax1();
	}
	
	function submit_ajax1() {
		var queryString = $("#join_form").serialize();
		
		$.ajax({
			//url: 'joinOk.jsp',
			url: 'joinOk.do',
			type: 'POST',
			data: queryString,
			dataType: 'text',
			success: function(json) {
				//consle.log(json);
				var result = JSON.parse(json);
				if(result.code == "success") {
					alert(result.desc)
					window.location.replace("login.jsp");
				} else {
					alert(result.desc);
				}
			}
		});
	}
	
</script>

<body>
<form id = "join_form" method = "post">
	아이디 : &nbsp;<input type = "text" name = "id" id="id" size = "20"> <br>
	비밀번호 : &nbsp;<input type = "password" name = "pw" id="pw" size = "20"> <br>
	비밀번호 확인 : &nbsp;<input type = "password" name = "pw_check" id="pw_check" size = "20"> <br>
	이름 : &nbsp;<input type = "text" name = "name" id="name" size = "20"> <br>
	메일 : &nbsp;<input type = "text" name = "eMail" id="eMail" size = "20"> <br>
	주소 : &nbsp;<input type = "text" name = "address" id="address" size = "50"> <br> <p>
	<p>
	<p>
	<input type ="button" value = "회원가입" onclick ="form_check()">&nbsp;&nbsp;&nbsp;
	<input type = "reset" value = "로그인" onclick = "javascript:window.location = 'login.jsp'">
	
    <div class="container test2">
	<input type = "button" class="btn btn-primary" value = "   구글계정으로 로그인   "  onclick="javascript:window.location = '../01.googleJoin.jsp'">
	<input type = "button" class="btn btn-primary" value = "  카카오계정으로 로그인 "> 
	<input type = "button" class="btn btn-primary" value = "  네이버계정으로 로그인 "> 
    <input type = "button" class="btn btn-primary" value = "페이스북계정으로 로그인">
    </div>
	
</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	


</body>
</html>