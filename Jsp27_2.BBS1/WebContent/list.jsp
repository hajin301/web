<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width = "700" cellpadding = "0" cellspacing ="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
			<td>비공개</td>
		</tr>
		
		<c:forEach items = "${list }" var = "dto">
			<tr>
				<td>${dto.bId}</td>
				<td>${dto.bName}</td>
				<td>
					<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> <!--댓글쓰면 들여쓰기 되는 원리 -->
			    	<a href = "content_view.do?bId=${dto.bId}&kind=view">${dto.bTitle }</a>
				</td>
				<td>${dto.bDate}</td>
				<td>${dto.bGroup}</td> 
				<td>${dto.bHit}</td> 
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="3"> <a href = "write_view.do">글작성</a></td>
			<td colspan="4"> <a href = "write_secret.do">비밀글작성</a></td>
		</tr>
	</table>
</body>
</html>