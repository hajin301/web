<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	background:red; border-right:solid 1px white;
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
	background:yellow; margin-top:10px;
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
	background:yellow; margin-top:10px;
}

section#contents{
	width:980px; min-height:600px; margin:0px auto;
	background:green;
}

section.contents_box{
	width:20%; height:200px; float:left;
	background:red; margin-left:4%; margin-top:30px;
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
</div>
</body>
</html>