<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script language="javaScript" src ="members.js"></script>
<script>
function form_check() {
	
	if(document.reg_frm.id.value.length == 0){
		alert("아이디는 필수사항입니다.");
		reg_frm.id.focus();
		return;
	}
	
	if(document.reg_frm.id.value.length < 4){
		alert("아이디는 4글자 이상이어야 합니다.");
		reg_frm.id.focus();
		return;
	}
	
	if(document.reg_frm.pw.value.length == 0){
		alert("비밀번호는 필수사항입니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.pw.value != document.reg_frm.pw_check.value){
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.name.value.length == 0){
		alert("이름은 필수사항입니다.");
		reg_frm.name.focus();
		return;
	}
	
	if(document.reg_frm.eMail.value.length == 0){
		alert("메일은 필수사항입니다.");
		reg_frm.eMail.focus();
		return;
	}
	
	document.reg_frm.submit();
}

function updateInfoConfirm() {

	if(document.reg_frm.pw.value == "") {
		alert("비밀번호를 입력하세요.");
		document.reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.pw.value != document.reg_frm.pw_check.value){
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.eMail.value.length == 0){
		alert("메일은 필수사항입니다.");
		reg_frm.eMail.focus();
		return;
	}
	
	document.reg_frm.submit();
}

</script>

</head>

<body>
<form id = "reg_frm">
	아이디: <input type = "text"  id = "id" name = "id" size = "20"> <br>
	비밀번호: <input type = "password" id = "pw" name = "pw" size = "20"> <br>
	비밀번호 확인: <input type = "password"  id ="pw_check" name = "pw_check" size = "20"> <br>
	이름: <input type = "text" id = "name" name = "name" size = "20"> <br>
	메일: <input type = "text" id = "eMail" name = "eMail" size = "20"> <br>
	주소: <input type = "text" id = "address" name = "address" size = "50"> <br> <p>
	
	<input type ="button" value = "회원가입" onclick ="form_check()">&nbsp;&nbsp;&nbsp;
	<input type = "reset" value = "로그인" onclick = "javascript:window.location = 'login.jsp'">
	
</form>
</body>
</html>