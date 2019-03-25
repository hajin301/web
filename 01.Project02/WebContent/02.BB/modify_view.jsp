<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("ValidMem") == null) {
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
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
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
	oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
	
	//document.modify_form.submit();
	$("#modify_form").submit();
}
</script>
<script>
function file_check() {
		
	if($('#bFile').val().length == 0){
		alert("파일을 선택해 주세요.");
		$('#bFile').focus();
		return;
	}
	
	$("#file_frm").submit();
	
}
</script>
</head>
<body>

	<div id="name">
		<p><span><%= name %></span> 님 안녕하세요.<p>
	</div>

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
		  <a class="dropdown-item" href="list.do">전체보기</a>
	      <a class="dropdown-item" href="board1.do">공지사항</a>
	      <a class="dropdown-item" href="board2.do">자유게시판</a>
	      <a class="dropdown-item" href="board3.do">자료실</a>
	    </div>
	  </li>
	</ul>
	
	<p>
	<p>

	<table width ="700" cellpadding="0" id="tb" cellspacing="0" border="1">
		<form action="Bmodify.do" method="post" name="modify_form" id="modify_form">
		  <input type = "hidden" name = "bId" value = "${content_view.bId}">
		  <input type="hidden" name="bName" value=<%=name %>>
		  
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
				<td><%=name %> </td>
				<%-- <td> <input type = "text" name = "bName" value="${content_view.bName }"> </td> --%>
			</tr>
			<tr>
			 	<td>그룹선택</td>
				<td> 
					<select id="menu">
							<option>공지사항</option>
							<option>자유게시판</option>
							<option>자료실</option>
					</select>
				  </input>
				</td>
		    </tr>
			<tr>
				<td>제목</td>
				<td> <input type = "text" name = "bTitle" value="${content_view.bTitle }"> </td>
			</tr>
			<tr>
					<td> 파일 </td> 
					<td><input type = "file" name = "filename" id="bFile"><input type = "button" value = "File Upload" onclick ="file_check()"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="bContent" id="ir1" rows = "10" cols="100" >${content_view.bContent}</textarea>
					<script type="text/javascript">
						var oEditors = [];
						nhn.husky.EZCreator.createInIFrame({
    					oAppRef: oEditors,
    					elPlaceHolder: "ir1",
    					sSkinURI: "./naver-editor/SmartEditor2Skin.html",
    					fCreator: "createSEditor2"
						});
					</script>
				</td>
			</tr>
			<tr>
				<td colspan="2"> 
				    <a href = "JavaScript:form_check();">수정완료</a>&nbsp;&nbsp;
					<a href = "content_view.do?bId=${content_view.bId}&kind=Bmodify">취소</a> &nbsp;&nbsp;
					<a href = "list.do?page=<%= session.getAttribute("cpage")%>">목록보기</a> &nbsp;&nbsp;
				</td>
			</tr>
		</form>
	</table>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
	
</body>
</html>