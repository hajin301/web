<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");
String bMenu = (String)session.getAttribute("bMenu");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<title>Insert title here</title>
<style>
body, ul, li {
	margin:0px; padding:0px; list-style:none;
}

div#header_wrap{
	width:100%; height:100px; float:left;
	background:#eeeeee; margin-bottom:10px;
}

header{
	width:980px; height:100px; margin:0px auto;
	background:#cccccc;
}

nav#mainmenu{
	width:80%; height:50px; margin:0px auto;
    position:relative; top:25px;
}

ul#mainmenu{
	width:100%; height:100%;
}

ul#mainmenu_list li {
	width:25%; height:100%; float:left;
	background:blue; border-right:solid 1px white;
	box-sizing:border-box; text-align:center;
	line-height:50px;
}

ul#mainmenu_list li:last-child{
	border:none;
}

span#menu_icon{
	display:none;
}

div#visual_wrap{
	width:100%; float:left;
	margin-top:10px;
}

section#visual{
	width:980px; margin:0px; auto;
	background:green;
}

section#visual img{
	width:100%; display:block;
}

div#contents_wrap{
	width:100%; min-height:600px; float:left;
	margin-top:10px;
}

section#contents{
	width:980px; min-height:600px; margin:0px auto;
	background:#cccccc;
}

section.contents_box{
	width:20%; height:200px; float:left;
	background:blue; margin-left:4%; margin-top:30px;
}
@media screen and (max-width:980px) {
	header, section#visual, section#contents{
		width:90%;
	}
}

@media screen and (max-width:768px) {
	section.contents_box {
		width:40%; margin-left:6.6%;
	}
}

@media screen and (max-width:480px) {
	section.contents_box{
		width:100%; margin-left:0px;
	}
	
	ul#mainmenu_list{
		display:none;
	}
	span#menu_icon{
		display:block;
	}
	
}
</style>
</head>
<body>

<input type="hidden" id="idck" value="<%=name%>">
<div id="name" style="display:none">
	<p><span><%= name %></span> 님 안녕하세요.<p>
</div>

<p> 

    <div class="container" id="loginOk" style="display:none">
		<div class="row">
    		<div class="col-sm-8 test1"></div>
    		<div class="col-sm-4 login" >
    			<a href="../01.HW/modify.jsp"><span>정보수정</span></a> &nbsp;
    			<span> / </span> &nbsp;
				<a href="../01.HW/logout.jsp"><span>로그아웃</span></a>
    		</div>
  		</div>
	</div>
	
	<div class="container" id="loginNo">
		<div class="row">
    		<div class="col-sm-8 test1"></div>
    		<div class="col-sm-4 login">
    			<a href="../01.HW/login.jsp"><span>로그인</span></a> &nbsp;
    			<span> / </span> &nbsp;
				<a href="../01.HW/logout.jsp"><span>회원가입</span></a>
    		</div>
  		</div>
	</div>
	
<ul class="nav nav-pills">
  <li class="nav-item">
    <a class="nav-link active" href="../03.main/mainpage.jsp">HOME</a>
  </li>
  <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">게시판</a>
    <div class="dropdown-menu" name="Menu">
       <input type="button"  class="dropdown-item" value="전체보기" onclick="javascript:window.location ='list.do'" name="All" >
       <input type="button" class="dropdown-item" value="공지사항" onclick="javascript:window.location ='board1.do'" name="Noti">
       <input type="button" class="dropdown-item" Value="자유게시판" onclick="javascript:window.location ='board2.do'" name="Free">
       <input type="button" class="dropdown-item" value="자료실" onclick="javascript:window.location ='board3.do'" name="Data">        
    </div>
  </li>
</ul>

<div id="header_wrap">
	<header>
		<nav id="mainmenu">
			<ul id="mainmenu_list">
				<li>메뉴1</li>
				<li>메뉴2</li>
				<li>메뉴3</li>
				<li>메뉴4</li>
			</ul>
			<span id = "menu_icon">메뉴아이콘</span>
		</nav>
	</header>
</div>
<div id="visual_wrap">
		<section>
			<img src="visual01.jpg" alt="">
		</section>
</div>
	
<div id="contents_wrap">
		<section id="contents">
			<section class="contents_box">
				contents_1
			</section>
			<section class="contents_box">
				contents_2
			</section>
			<section class="contents_box">
				contents_3
			</section>
			<section class="contents_box">
				contents_4
			</section>
		</section>
</div>

<footer>

</footer>	

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	

</body>
</html>