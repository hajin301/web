<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");
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
.login{
	color:black; text-align: right;
}

.nav1{
	text-align: right;
}

#write{
float:right;margin-bottom:10px; margin-top:0px;
margin-right:10px;
}
#name p {
color:black; text-align:right;margin-right:120px;margin-top:10px;
}
#name span {
color:blue;
}

#name {
display:none;
}
</style>

</head>
<body>
<script>
/* function login_write(){
	
	window.location.replace("../01.HW/login.jsp");
}
 */
</script>

<div id="name">
<p><span><%= name %></span> 님 안녕하세요.<p>
</div>
<p>
	<div class="container">
		<div class="row">
    		<div class="col-sm-8 test1"></div>
    		<div class="col-sm-4 login">
    			<a href="../01.HW/login.jsp"><span>로그인</span></a> &nbsp;
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

<script>
function find_check() {
	if($('#find_name').val().length == 0){
		alert("검색어를 입력하세요.");
		$('#find_name').focus();
		return;
	}
}

</script>


<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand">전체보기</a>
  <form action="Search_view.do" method="post" class="form-inline">	
				<select id="Menu" name="find_field">
				        <option value="total" name="total">전체검색</option>
						<option value="bTitle" name="bTitle" >제목</option>
						<option value="bContent" name="bContent">내용</option>
						<option value ="bName" name="bName">작성자</option>
						<option value="bTitCon" name="bTitCon">제목+내용</option>
				</select> 
	&nbsp;&nbsp;<input name="find_name" id="find_name" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">	
    &nbsp;<button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="find_check()">검색</button>
  </form>
</nav>

<p>
	<table class="table table-striped" name="write_frm" width = "700" cellpadding = "0" cellspacing ="0" border="1">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">작성자</th>
			<th scope="col">제목</th>
			<th scope="col">날짜</th>
			<th scope="col">조회수</th>
		</tr>
		
		<c:forEach items = "${BSearch }" var = "dto">
			<tr>
				<td scope="row" >${dto.bId}</td>
				<td>${dto.bName}</td>
				<td>
					<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> <!--댓글쓰면 들여쓰기 되는 원리 -->
			    	<a href = "content_view.do?bId=${dto.bId}&kind=view">${dto.bTitle }</a>
				</td>
				<td>${dto.bDate}</td>
				<td>${dto.bHit}</td> 
				
			</tr>
		</c:forEach>
		
		<tr>
			<!-- <td colspan="5"> <a href = "write_view.do">글작성</a></td> -->
			<input type ="button" value = "글작성" id="write" onclick ="javascript:window.location = 'write_view.jsp'">&nbsp;&nbsp;&nbsp;
		</tr>
		
		<nav aria-label="page navigation example">
		<div class="pagination">
		<tr>
			<td colspan="5" >
			<!-- 처음 -->
			<c:choose>
				<c:when test = "${(page.curPage -1) < 1}">
					[ &lt;&lt; ]
				</c:when>
				<c:otherwise>
					<a href = "Search_view.do?page=1">[ &lt;&lt; ]</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 이전 -->
			<c:choose>
				<c:when test="${(page.curPage -1) < 1 }">
					[ &lt;]
				</c:when>
				<c:otherwise>
					<a href="Search_view.do?page=${page.curPage -1 }">[ &lt;]</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var = "fEach" begin="${page.startPage }" end = "${page.endPage }" step="1">
				<c:choose>
				<c:when test = "${page.curPage == fEach }">
					[${fEach}] &nbsp;
				</c:when>
				<c:otherwise>
					<a href = "Search_view.do?page=${fEach}">[${fEach} ]</a> &nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test = "${(page.curPage + 1) > page.totalPage }">
				[&gt;]
			</c:when>
			<c:otherwise>
				<a href = "Search_view.do?page=${page.curPage +1 }">[&gt;]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage }">
				[&gt;&gt;]
			</c:when>
			<c:otherwise>
				<a href = "Search_view.do?page=${page.totalPage}">[&gt;&gt;]</a>
			</c:otherwise>
			</c:choose>
		</tr>
		</div>
		</nav>
	</table>
	
<!-- totalCount: ${page.totalCount}<br>
	listCount: ${page.listCount}<br>
	totalPage: ${page.totalPage}<br>
	curPage: ${page.curPage}<br>
	pageCount: ${page.pageCount}<br>
	startPage: ${page.startPage}<br>
	endPage: ${page.endPage}<br> -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
</body>
</html>