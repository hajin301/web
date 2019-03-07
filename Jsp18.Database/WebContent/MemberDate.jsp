
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	Connection connection;
	Statement statement;
	ResultSet resultSet;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";
	String query = "select * from Member1"; //오라클 데이터 작성 수 commit;을 해야 함
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

	try {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, uid, upw);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);

		while(resultSet.next()) {

			String id = resultSet.getString("id");
			String pw = resultSet.getString("pw");
			String name = resultSet.getString("name");
			String phone = resultSet.getString("phone");

			out.println("아이디 : " + id +
		   				 ", 비밀번호 : "  + pw +
		   				 ", 이름 : " + name +
		   				 ", 전화번호 : " + phone + "<br>");

			}
	}catch(Exception e) {
	}finally {
		try {
			if(resultSet != null) resultSet.close();
			if(statement != null) statement.close();
			if(connection != null) connection.close();
		} catch(Exception e) {}
	}
%>

<%
	try {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, uid, upw);
		statement = connection.createStatement();
	
		String query1 = "insert into Member1 values('g', '1234', 'Mr.hong','010-2345-3555')"; //데이터 삽입하기
		//statement.executeUpdate(query1);
		String query2 = "commit";
		//statement.executeUpdate(query2);
		
	}catch(Exception e) {
	}finally {
		try {
			if(resultSet != null) resultSet.close();
			if(statement != null) statement.close();
			if(connection != null) connection.close();
		} catch(Exception e) {}
	}
%>

<%
	try {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, uid, upw);
		statement = connection.createStatement();
	
		String query1 = "delete from Member1 where id = 'Mr.hong'"; //데이터 삽입하기
		statement.executeUpdate(query1);
		String query2 = "commit";
		statement.executeUpdate(query2);
		
	}catch(Exception e) {
	}finally {
		try {
			if(resultSet != null) resultSet.close();
			if(statement != null) statement.close();
			if(connection != null) connection.close();
		} catch(Exception e) {}
	}
%>

</body>
</html>