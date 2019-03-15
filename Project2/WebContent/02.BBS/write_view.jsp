<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src ="https://cdn.ckeditor.com/4.11.3/standard/ckeditor.js"></script>

<script src="http://code.jquery.com/jquery.js"></script>
<script>
function form_check() {
	
	if($('#bName').val().length == 0){
		alert("이름을 적어주세요.");
		$('#bName').focus();
		return;
	}
	
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
	
	$("#reg_frm").submit();
	
}

</script>

</head>
<body>

<table width = "700" cellpadding ="0" cellspacing = "0" border="1">
	<form action = "write.do" method="post" id = "reg_frm">
		<tr>
			<td>이름</td>
			<td> <input type = "text" id="bName" name = "bName" size = "50"> </td>
		</tr>
		<tr>
			<td>제목</td>
			<td> <input type = "text" id="bTitle" name = "bTitle" size = "50"> </td>
		</tr>
		<tr>
			<td>내용</td>
			<td> <textarea id = "bContent" name = "bContent" rows="10" cols="80">This is my textarea to be replaced with CKEditor</textarea></td>
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

</body>
</html>