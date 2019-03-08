package com.study.jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	

//		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		private String uid = "scott";
//		private String upw = "tiger";
		
	    private DataSource dataSource;
		
		public MemberDAO() {
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				Context context = new InitialContext();
				System.out.println("111111");
				dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
				System.out.println("222222");
			}catch(Exception e) {
				System.out.println("3333333");
				e.printStackTrace();
			}
			
		}
		
		public ArrayList<MemberDTO> memberSelect() {
			
			ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
			
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			System.out.println("44444");

			try {
				System.out.println("55555");

				//con = DriverManager.getConnection(url, uid, upw);
				con = dataSource.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery("select * from Member1");
				
				System.out.println("66666");

				while(rs.next()) {
					String id = rs.getString("id");
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					String phone = rs.getString("phone");
					String gender = rs.getString("gender");
					
					MemberDTO dto = new MemberDTO(id, pw, name, phone, gender);
					dtos.add(dto);
				}
			}catch(Exception e) {
				System.out.println("777777");

				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(con != null) con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			return dtos;
		}
		
		
		public int memberInsert(MemberDTO dto) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String query = "insert into Member1 values(?,?,?,?,?)";
			int nResult = 0;
			
			try {
				//con = DriverManager.getConnection(url, uid, upw);
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,  dto.getId());
				pstmt.setString(2,  dto.getPw());
				pstmt.setString(3,  dto.getName());
				pstmt.setString(4,  dto.getPhone());
				pstmt.setString(5,  dto.getGender());
				pstmt.executeUpdate();
				nResult = 1;
			
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return nResult;
		}
		

}
