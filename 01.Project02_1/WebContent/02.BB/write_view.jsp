<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("ValidMem") == null) {
	 /* response.sendRedirect("list.do?page="); */
	 response.sendRedirect("../01.HW/login.jsp");
%>
<%

}
String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src ="https://cdn.ckeditor.com/4.11.3/standard/ckeditor.js"></script>

<script src="http://code.jquery.com/jquery.js"></script>
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<style>
#name p {
color:black; text-align:right;margin-right:120px;margin-top:10px;
}
#name span {
color:blue;
}
#tb {
margin:0px auto;
}
</style>
<script>
function form_check() {
	
	if($('#bTitle').val().length == 0){
		alert("제목을 적어주세요.");
		$('#bTitle').focus();
		return;
	}
	
	if($('#bContent').val().length == 0){
		alert("내용을 적어주세요.");
		$('#bContent').focus();
		return;
	}
	
	$("#reg_frm").submit();
	
}

function file_check() {
	
	if($('#bTitle').val().length == 0){
		alert("제목을 적어주세요.");
		$('#bTitle').focus();
		return;
	}
	
	if($('#bContent').val().length == 0){
		alert("내용을 입력해 주세요.");
		$('#bContent').focus();
		return;
	}
	
	if($('#bFile').val().length == 0){
		alert("파일을 선택해 주세요.");
		$('#bFile').focus();
		return;
	}

	$("#file_frm").submit();
	
}

/* if(session.getAttribute("ValidMem") == null) {
	 $('#login1').css('display', 'block');
	 $('#modify1').css('display', 'none');
}else{
	 $('#name').css('display', 'none');
	 $('#login1').css('display', 'none');
	 $('#modify1').css('display', 'block');
} */
	
</script>

</head>
<body>

<div id="name">
<p><span><%= name %></span> 님 안녕하세요.<p>
</div>
	
<!-- 	<div class="container">
		<div class="row">
    		<div class="col-sm-8 test1"></div>
    		<div class="col-sm-4 login">
    			<a href="../01.HW/login.jsp"><span class="login1">로그인</span></a> &nbsp;
    			<span> / </span> &nbsp;
				<a href="../01.HW/join.jsp"><span class="login1">회원가입</span></a>
    		</div>
  		</div>
	</div> -->
	
	<div class="container">
		<div class="row">
    		<div class="col-sm-8 test1"></div>
    		<div class="col-sm-4 login" >
    			<a href="../01.HW/modify.jsp"><span>정보수정</span></a> &nbsp;
    			<span> / </span> &nbsp;
				<a href="../01.HW/logout.jsp"><span>로그아웃</span></a>
    		</div>
  		</div>
	</div>

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
<p>

<table width = "700" id="tb" cellpadding ="0" cellspacing = "0" border="1">
	<form action = "write.do" method="post" id = "reg_frm">
		<input type="hidden" name="bId" value="${write_view.bId}">
		<input type="hidden" name="bGroup" value="${write_view.bGroup}">
		<input type="hidden" name="bName" value=<%=name %>>
	    <input type="hidden" name="Id" value="<%=id %>">
				
		<tr>
			<td>이름</td>
			<td><%=name%></td>
			<%-- <td><input type = "text" id="bName" name="bName"  size="50" value=<%=name%>></td> --%>
		</tr> 
		
		<tr>
			<td>제목</td>
			<td> <input type = "text" id="bTitle" name = "bTitle" size = "50"> </td>
		</tr>
		<tr>
		 	<td>비밀번호</td>
			<td> <input type = "text" id="bTitle" name = "bTitle" size = "50"> </td>
		</tr>
		<tr>
			<td> 파일 </td> 
			<td><input type = "file" name = "filename" id="bFile"><input type = "button" value = "File Upload" onclick ="file_check()"></td>
		</tr>

		<tr>
			<td>내용</td>
			<td> <textarea id = "bContent" name = "bContent" rows="10" cols="80"> </textarea></td>
			<script>
			CKEDITOR.replace('bContent');
			</script>
		</tr>
		<tr>
			<td colspan = "2">
				<input type = "button" value = "입력" onclick ="form_check()"> &nbsp;&nbsp;
				<a href = "list.do">목록보기</a>
			</td>
		</tr>
	</form>
</table>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
</body>
</html>