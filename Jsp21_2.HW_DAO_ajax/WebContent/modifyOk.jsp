<%@page import="com.study.hw.jsp.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="dto" class="com.study.hw.jsp.MemberDTO"  scope = "page" />
<jsp:setProperty name="dto"  property="*" />

<%
	String id = (String)session.getAttribute("id");
	dto.setId(id);
	
	MemberDAO dao = MemberDAO.getInstance();
	int ri = dao.updateMember(dto);
	
	String json_data = "";
	if (ri == 1) { // 정보가 수정되었습니다.
		json_data = "{\"code\" : \"success\", \"desc\" : \"정보가 수정되었습니다.\"}";
	} else {
		json_data = "{\"code\" : \"fail\", \"desc\" : \"정보수정에 실패했습니다.\"}"; 
	}
	response.setCharacterEncoding("UTF-8");
	out.println(json_data);
%>
