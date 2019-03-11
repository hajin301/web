package com.study.jsp;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontCon() {
        super();
   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
		
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("actionDo");
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String command = uri.substring(conPath.length());
		System.out.println("command : " + command);
		
		if (command.equals("/loginResult.do")) {
			loginResult(request, response);
		}else if (command.equals("/modifyResult.do")) {
			modifyResult(request, response);
		}else if (command.equals("/joinResult.do")) {
			joinResult(request, response);
		}else if (command.equals("/logout.do")) {
			logout(request, response);

		}
	}

	public void loginResult(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("loginResult");
		java.sql.Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "scott";
		String upw = "tiger";
		
        String id, pw, name, phone, gender;
		
		id = request.getParameter("id");
		pw = request.getParameter("pw"); 
		name = "";
		phone = "";
		gender = "";
		
		String query = "select * from Member1 where id = ? and pw = ?";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, upw);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				//id = resultSet.getString("id");
				//pw = resultSet.getString("pw");
				name = resultSet.getString("name");
				phone = resultSet.getString("phone");
				gender = resultSet.getString("gender");
				
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			
			response.sendRedirect("loginResult.jsp");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void modifyResult(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("modifyResult");
		java.sql.Connection con;
		PreparedStatement pstmt;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "scott";
		String upw = "tiger";

		String id, pw, name, phone1, phone2, phone3, gender;
		
		HttpSession session;
		
		
		
	}
	
	public void joinResult(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("joinResult");
		 java.sql.Connection con = null;
		 PreparedStatement pstmt = null;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "scott";
		String upw = "tiger";
		
		String id, pw, name, phone1, phone2, phone3, gender;
		
		request.setCharacterEncoding("UTF-8");
		   
		   id = request.getParameter("id");
		   pw = request.getParameter("pw");
		   name = request.getParameter("name");
		   phone1 = request.getParameter("phone1");
		   phone2 = request.getParameter("phone2");
		   phone3 = request.getParameter("phone3");
		   gender = request.getParameter("gender");

		   //여기서 파라미터로 넘어온 값들 체크
		   //이후 정상이면 다음 진행
		   
		   String query = "insert into Member1 values(?, ?, ?, ?, ?)";
		   
		   try {
		   Class.forName(driver);
		   con = DriverManager.getConnection(url, uid, upw);
		   pstmt = con.prepareStatement(query);
		   pstmt.setString(1, id);
		   pstmt.setString(2, pw);
		   pstmt.setString(3, name);
		   pstmt.setString(4, phone1+"-"+phone2+"-" + phone3);
		   pstmt.setString(5, gender);
		   int updateCount = pstmt.executeUpdate();
		   
		   if(updateCount == 1) {
			   System.out.println("insert sucess");
			   response.sendRedirect("joinResult.jsp");
		   } else {
			   System.out.println("insert fail");
			   response.sendRedirect("join.jsp");
		   }
		   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }finally {
			   try {
				   if(pstmt != null)
					   pstmt.close();
				   if(con != null)
					   con.close();
			   } catch(Exception e) {}
		   }
		
		}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("logout");
		actionDo(request, response);
		
	}

}

