<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%! String name, id; %>
<%
	name = (String)session.getAttribute("name");
	id = (String)session.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
#name {
display:none;
}

</style>

<!-- <Script>

$(document).ready(function() {
	var id = $('#id').val();
	var idck = $('#idck').val();
	if(id == idck) {
		alert(id);
		alert(idck);
		alert('1');
		$('#idOk').css('display','block');
		$('#idNo').css('display','none');
	} else {
		alert(idck);
		alert('2');
    }
});
</script> -->

<script>
	function doDisplay() {
		var id = $('#id').val();
		var idck = $('#idck').val();
		if(id == idck) {	
			$('#IdOk').css('display','block');
			$('#IdNo').css('display','none');
		} 
	}
</script>
</head>

<body onload="doDisplay()">
<div id="name">
<p><span><%= name %></span> 님 안녕하세요.<p>
</div>

 	<div class="container">
		<div class="row">
    		<div class="col-sm-8 test1"></div>
    		<div class="col-sm-4 login">
    			<a href="../01.HW/login.jsp" class="login1"><span>로그인</span></a> &nbsp;
				<span> / </span> &nbsp;
				<a href="../01.HW/logout.jsp" class="login1"><span>로그아웃</span></a>
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
      <a class="dropdown-item" href="list.do">전체보기</a>
      <a class="dropdown-item" href="board1.do">공지사항</a>
      <a class="dropdown-item" href="board2.do">자유게시판</a>
      <a class="dropdown-item" href="board3.do">자료실</a>
    </div>
  </li>
</ul>

<p>
<p>
<p>
<p>
<p>	
	<table width ="800" id="tb" cellpadding="0" cellspacing="0" border="1">
		<input type="hidden" id="id" value="${content_view.id}">
		<input type="hidden" id="idck" value="<%=id%>">
		<script>
/* 		var id = $('#id').val();
		var idck = $('#idck').val(); */
		
		/* var id = $('#id').val();
		var idck = $('#idck').val();
		alert(id);
		alert(idck); */
		
		
/* 		window.onload = function() {
			if(id.equals(idck)) {
				alert('1');
				$('#idOk').css('display','block');
				$('#idNo').css('display','none');
			} else {
				alert('2');
				$('#idOk').css('display','none');
				$('#idNo').css('display','block');
			}
		}  */
		
/* 		window.onload = function() {
			var id = $('#id').val();
			var idck = $('#idck').val();
			if(id == idck) {
				alert(id);
				alert(idck);
				alert('1');
				if($('#idOk').css("display")=="none")
					$('#idOk').show();
			} else {
				alert(idck);
				alert('2');
		    }
		} */
				
		</script>
		<tr>
			<td>번호</td>
			<td>${content_view.bId}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${content_view.bHit}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${content_view.bName}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${content_view.bTitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
			 	${content_view.bContent}
			</td>
		</tr>
		<tr>
		
		<Script>
     /*     function toggle_layer() {
			var id = $('#id').val();
			var idck = $('#idck').val();
			if(id == idck) {
				alert(id);
				alert(idck);
				alert('1');
				if($('#idOk').css("display")=="none")
					$('#idOk').show();
			} else {
				alert(idck);
				alert('2');
		    } */
		</script>
		
 		<td colspan="2" >
 			<div id ="IdOk" style="display:none">	
 				<a href = "modify_view.do?bId=${content_view.bId}&kind=Bmodify">수정</a> &nbsp;&nbsp;
				<a href = "list.do?page=<%=session.getAttribute("cpage") %>">목록보기</a> &nbsp;&nbsp;
				<a href = "delete.do?bId=${content_view.bId}">삭제</a> &nbsp;&nbsp;
				<a href = "reply_view.do?bId=${content_view.bId}">답변</a>
 			</div>
 			<div id ="IdNo">
 				<a href = "list.do?page=<%=session.getAttribute("cpage") %>">목록보기</a> &nbsp;&nbsp;
				<a href = "reply_view.do?bId=${content_view.bId}">답변</a>
 			</div>
 					
		</td>  	
		</tr>
	</table>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
	
</body>
</html>