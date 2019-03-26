package com.study.jsp.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.study.jsp.dto.BDto;
import com.study.jsp.dto.BPageInfo;


public class BDao {

	DataSource dataSource = null;
	
	private static BDao instance = new BDao();
	
	public static BDao getInstance() {
		return instance;
	}
	
	int listCount = 10; // 한 페이지당 보여줄 게시물의 갯수
	int pageCount = 10; // 하단에 보여줄 페이지 리스트의 갯수
	
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write(String bName, String bTitle, String bContent, String Id, String bMenu ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
			               " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, Id, bMenu) " +
					       " values " +
			               " (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, ?, ? )";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,  bName);
			pstmt.setString(2,  bTitle);
			pstmt.setString(3,  bContent);
			pstmt.setString(4,  Id);
			pstmt.setString(5,  bMenu);
			int rn = pstmt.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (Exception e2){
				e2.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<BDto> list(int curPage) {
	
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage -1 ) * listCount + 1;
		int nEnd = (curPage -1 ) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
			               "  from ( " +
			               "    select rownum num, A.* " +
			               "      from ( " +
			               "         select * " +
			               "            from mvc_board " +
					       "           order by bGroup desc, bStep asc ) A " +
			               "       where rownum <= ? ) B " +
					       "where B.num >= ? ";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String Id = resultSet.getString("Id");
				String bMenu = resultSet.getString("bMenu");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
						            bHit, bGroup, bStep, bIndent, Id, bMenu);
				
				dtos.add(dto);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
		
	}
	
	public ArrayList<BDto> list1(int curPage) {
		
		ArrayList<BDto> dtos1 = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage -1 ) * listCount + 1;
		int nEnd = (curPage -1 ) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
		               "  from ( " +
		               "    select rownum num, A.* " +
		               "      from ( " +
		               "         select * " +
		               "            from mvc_board " +
		               "            where bMenu = '공지사항' " +
				       "           order by bGroup desc, bStep asc ) A " +
		               "       where rownum <= ? ) B " +
				       "where B.num >= ? ";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String Id = resultSet.getString("Id");
				String bMenu = resultSet.getString("bMenu");
				
				BDto dto1 = new BDto(bId, bName, bTitle, bContent, bDate,
						            bHit, bGroup, bStep, bIndent, Id, bMenu);
				
				dtos1.add(dto1);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos1;
		
	}
	
public ArrayList<BDto> list2(int curPage) {
		
		ArrayList<BDto> dtos2 = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage -1 ) * listCount + 1;
		int nEnd = (curPage -1 ) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
		               "  from ( " +
		               "    select rownum num, A.* " +
		               "      from ( " +
		               "         select * " +
		               "            from mvc_board " +
		               "            where bMenu = '자유게시판' " +
				       "           order by bGroup desc, bStep asc ) A " +
		               "       where rownum <= ? ) B " +
				       "where B.num >= ? ";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String Id = resultSet.getString("Id");
				String bMenu = resultSet.getString("bMenu");
				
				BDto dto2 = new BDto(bId, bName, bTitle, bContent, bDate,
						            bHit, bGroup, bStep, bIndent, Id, bMenu);
				
				dtos2.add(dto2);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos2;
		
	}

public ArrayList<BDto> list3(int curPage) {
	
	ArrayList<BDto> dtos3 = new ArrayList<BDto>();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	
	int nStart = (curPage -1 ) * listCount + 1;
	int nEnd = (curPage -1 ) * listCount + listCount;
	
	try {
		con = dataSource.getConnection();
		
		String query = "select * " +
	               "  from ( " +
	               "    select rownum num, A.* " +
	               "      from ( " +
	               "         select * " +
	               "            from mvc_board " +
	               "            where bMenu = '자료실' " +
			       "           order by bGroup desc, bStep asc ) A " +
	               "       where rownum <= ? ) B " +
			       "where B.num >= ? ";
		
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, nEnd);
		pstmt.setInt(2, nStart);
		resultSet = pstmt.executeQuery();
		
		while (resultSet.next()) {
			int bId = resultSet.getInt("bId");
			String bName = resultSet.getString("bName");
			String bTitle = resultSet.getString("bTitle");
			String bContent = resultSet.getString("bContent");
			Timestamp bDate = resultSet.getTimestamp("bDate");
			int bHit = resultSet.getInt("bHit");
			int bGroup = resultSet.getInt("bGroup");
			int bStep = resultSet.getInt("bStep");
			int bIndent = resultSet.getInt("bIndent");
			String Id = resultSet.getString("Id");
			String bMenu = resultSet.getString("bMenu");
			
			BDto dto3 = new BDto(bId, bName, bTitle, bContent, bDate,
					            bHit, bGroup, bStep, bIndent, Id, bMenu);
			
			dtos3.add(dto3);
			
		}
		
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(resultSet != null) resultSet.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return dtos3;
	
}

	public BDto contentView(String strID, String kind) {
		
		if(kind.equals("view")) {
			upHit(strID);
		}
		
		upHit(strID);
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString ("bName");
				String bTitle = resultSet.getString ("bTitle");
				String bContent = resultSet.getString ("bContent");
				Timestamp bDate = resultSet.getTimestamp ("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String Id = resultSet.getString("Id");
				String bMenu = resultSet.getString("bMenu");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
					       bHit, bGroup, bStep, bIndent, Id, bMenu);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (resultSet != null) resultSet.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void Bmodify(String bId, String bName, String bTitle, String bContent) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			String query = "update mvc_board " +
			               "   set bName = ?, " +
			               "       bTitle = ?, " +
			               "       bContent = ? " +
			               "where bId = ?";
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setString(4, bId);
				int rn = pstmt.executeUpdate();

			}catch(Exception e) {
				e.printStackTrace();
			
			}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			
	}
	
	private void upHit(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
	
			int rn = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
	
	public BDto reply_view(String str) {
		
		BDto dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			if (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString ("bName");
				String bTitle = resultSet.getString ("bTitle");
				String bContent = resultSet.getString ("bContent");
				Timestamp bDate = resultSet.getTimestamp ("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String Id = resultSet.getString("Id");
				String bMenu = resultSet.getString("bMenu");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
						       bHit, bGroup, bStep, bIndent, Id, bMenu);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
		
		
	}
	
	public void reply(String bId, String bName, String bTitle, String bContent, 
			String bGroup, String bStep, String bIndent, String Id)
	{
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
			               " (bId, bName, bTitle, bContent, bGroup, bStep, bIndent, Id) " +
					       " values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
		
			pstmt.setString(1,  bName);
			pstmt.setString(2,  bTitle);
			pstmt.setString(3,  bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) +1);
			pstmt.setString(7,  Id);
		
			
			int rn = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (Exception e2){
				e2.printStackTrace();
			}
		}	
	}
	
	private void replyShape(String strGroup, String strStep) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "update mvc_board " +
		               		"   set bStep = bStep + 1 " +
		               		"  where bGroup = ? and bStep > ?"; 
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public BPageInfo articlePage(int curPage, HttpServletRequest request)
			throws ServletException, IOException 
	{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
			
		request.setCharacterEncoding("UTF-8");
		String bmenu = request.getParameter("bMenu");
		
		BDto dto = new BDto();
		dto.setbMenu(bmenu);
		
	    HttpSession session = request.getSession();
	    session.setAttribute("bMenu", dto.getbMenu());
		String bmenu1 = (String)session.getAttribute("bmenu");
	    
	    //System.out.println(bmenu);
	    
		int listCount = 10; // 한 페이지당 보여줄 게시물의 갯수
		int pageCount = 10; // 하단에 보여줄 페이지 리스트의 갯수
		
		// 총 게시물의 갯수
		int totalCount = 0;
		try {
			
			con = dataSource.getConnection();
			
			String query = /* "select count(*) as total from mvc_board"; */
					  "select " + 
					  " count(case when bmenu='자유게시판' then 1 end) as free, " +
					  " count(case when bmenu='  자료실' then 1 end) as data, " +
					  " count(case when bmenu='공지사항' then 1 end) as noti, " + 
					  " count(*) as total" + 
					  " from mvc_board" ;
					 
			
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
//				      if(bmenu1.equals("자유게시판")) {
//				 	  	 totalCount = resultSet.getInt("free");
//					  }else if(bmenu1.equals("자료실")) {
//					     totalCount = resultSet.getInt("data");
//					  }else if(bmenu1.equals("공지사항")) {
//					     totalCount = resultSet.getInt("noti"); 
//					  }else if(resultSet.next()) {
//						 totalCount = resultSet.getInt("total");
//					  }
			  } 
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (resultSet != null) resultSet.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		// 총 페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		// 현재 페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		// 시작 페이지
		int startPage = ((myCurPage-1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if( endPage > totalPage)
			endPage = totalPage;
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
		
	}
	
	public ArrayList<BDto> BSearch(String find_field, String find_name, int curPage) 
	{
		ArrayList<BDto> dtos4 = new ArrayList<BDto>();
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage -1 ) * listCount + 1;
		int nEnd = (curPage -1 ) * listCount + listCount;
		
		if(find_field.equals("bTitle")) {
		
			try {
				con = dataSource.getConnection();
				
				String query = "select * " +
			               "  from ( " +
			               "    select rownum num, A.* " +
			               "      from ( " +
			               "         select * " +
			               "            from mvc_board " +
			               "            where btitle like ? " +
					       "           order by bGroup desc, bStep asc ) A " +
			               "       where rownum <= ? ) B " +
					       "where B.num >= ? ";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,  "%" + find_name + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString ("bName");
					String bTitle = resultSet.getString ("bTitle");
					String bContent = resultSet.getString ("bContent");
					Timestamp bDate = resultSet.getTimestamp ("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					String Id = resultSet.getString("Id");
					String bMenu = resultSet.getString("bMenu");
					
					dto = new BDto(bId, bName, bTitle, bContent, bDate,
							       bHit, bGroup, bStep, bIndent, Id, bMenu);
					dtos4.add(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}else if(find_field.equals("bContent")) {
			
			try {
				con = dataSource.getConnection();
				
				String query = /* " select * from mvc_board where bcontent like ?"; */
				
				"select * " +
	               "  from ( " +
	               "    select rownum num, A.* " +
	               "      from ( " +
	               "         select * " +
	               "            from mvc_board " +
	               "            where bcontent like ? " +
			       "           order by bGroup desc, bStep asc ) A " +
	               "       where rownum <= ? ) B " +
			       "where B.num >= ? ";
		

				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,  "%" + find_name + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString ("bName");
					String bTitle = resultSet.getString ("bTitle");
					String bContent = resultSet.getString ("bContent");
					Timestamp bDate = resultSet.getTimestamp ("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					String Id = resultSet.getString("Id");
					String bMenu = resultSet.getString("bMenu");
					
					dto = new BDto(bId, bName, bTitle, bContent, bDate,
							       bHit, bGroup, bStep, bIndent, Id, bMenu);
					dtos4.add(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}else if(find_field.equals("total")) {
			
			try {
				con = dataSource.getConnection();
				
				String query = /* " select * from mvc_board where bcontent like ?"; */
				
				"select * " +
	               "  from ( " +
	               "    select rownum num, A.* " +
	               "      from ( " +
	               "         select * " +
	               "            from mvc_board " +
	               "            where btitle || bname || bcontent like ? " +
			       "           order by bGroup desc, bStep asc ) A " +
	               "       where rownum <= ? ) B " +
			       "where B.num >= ? ";
		
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,  "%" + find_name + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString ("bName");
					String bTitle = resultSet.getString ("bTitle");
					String bContent = resultSet.getString ("bContent");
					Timestamp bDate = resultSet.getTimestamp ("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					String Id = resultSet.getString("Id");
					String bMenu = resultSet.getString("bMenu");
					
					dto = new BDto(bId, bName, bTitle, bContent, bDate,
							       bHit, bGroup, bStep, bIndent, Id, bMenu);
					dtos4.add(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}else if(find_field.equals("bName")) {
			
			try {
				con = dataSource.getConnection();
				
				String query = /* " select * from mvc_board where bcontent like ?"; */
				
				"select * " +
	               "  from ( " +
	               "    select rownum num, A.* " +
	               "      from ( " +
	               "         select * " +
	               "            from mvc_board " +
	               "            where bname like ? " +
			       "           order by bGroup desc, bStep asc ) A " +
	               "       where rownum <= ? ) B " +
			       "where B.num >= ? ";
		
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,  "%" + find_name + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString ("bName");
					String bTitle = resultSet.getString ("bTitle");
					String bContent = resultSet.getString ("bContent");
					Timestamp bDate = resultSet.getTimestamp ("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					String Id = resultSet.getString("Id");
					String bMenu = resultSet.getString("bMenu");
					
					dto = new BDto(bId, bName, bTitle, bContent, bDate,
							       bHit, bGroup, bStep, bIndent, Id, bMenu);
					dtos4.add(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}else if(find_field.equals("bTitCon")) {
			
			try {
				con = dataSource.getConnection();
				
				String query = /* " select * from mvc_board where bcontent like ?"; */
				
				"select * " +
	               "  from ( " +
	               "    select rownum num, A.* " +
	               "      from ( " +
	               "         select * " +
	               "            from mvc_board " +
	               "            where btitle || bcontent like ? " +
			       "           order by bGroup desc, bStep asc ) A " +
	               "       where rownum <= ? ) B " +
			       "where B.num >= ? ";
		
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,  "%" + find_name + "%");
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				resultSet = pstmt.executeQuery();
				
				while (resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString ("bName");
					String bTitle = resultSet.getString ("bTitle");
					String bContent = resultSet.getString ("bContent");
					Timestamp bDate = resultSet.getTimestamp ("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					String Id = resultSet.getString("Id");
					String bMenu = resultSet.getString("bMenu");
					
					dto = new BDto(bId, bName, bTitle, bContent, bDate,
							       bHit, bGroup, bStep, bIndent, Id, bMenu);
					dtos4.add(dto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (con != null) con.close();
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}
			return dtos4;				
	
	}
		
}
