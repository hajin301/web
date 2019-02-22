import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


class UserInfo implements Serializable {
	
	Scanner s = new Scanner(System.in);
	String id; //아이디
	String pw; // 비번
	String name; //이름
	
	//생성자
	public UserInfo(String id, String pw, String name) {	
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public int hashCode() {
		return id.hashCode();
	}
	
	public boolean equals(Object obj) {
	
	    UserInfo cmp = (UserInfo)obj;
	
	    if(id.compareTo(cmp.id)==0) //인서트된 id와 기존 UserInfo에 저장된 아이디가 같으면
		    return true; //true를 반환함
	    else
		    return false; // 다르면 false를 반환함
    }	
}

//테이블에 회원가입 내용 저장
class UserData  {
	
	HashSet<UserInfo> infoStorage = new HashSet<UserInfo> () ;	

	public UserInfo loginInfo() throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

  	  try {
  		   Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",
					"tiger");
	
			       sql = "create table TB_USERLIST(id varchar2(20) ,                " +
					     "                         password varchar2(10) NOT NULL,  " +
					     "                         name varchar2(20)) ";			
//---------------------------------------------------------------
					
				System.out.println("회원가입을 해주세요.");
				
				System.out.println("아이디: ");
				String ID = MenuViewer.keyboard.next();
				
				 //아이디 중복 검사
				sql = "select * from TB_USERLIST where id = '" + ID + "'";
				//System.out.println(sql);
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();				
				
			//while (true) {
				
				if(rs.next()==true) { //ID와 DB 아이디가 같다면 이미 가입된 아이디
				        System.out.println("중복된 아이디입니다. \n 다시 입력해 주세요.");
				        }
				else {
					System.out.println("사용 가능한 아이디입니다.");
					
					System.out.println("패스워드: ");
					String PW = MenuViewer.keyboard.next();
					
					System.out.println("이름: ");
					String NAME = MenuViewer.keyboard.next();

					sql = "insert into TB_USERLIST values(?,?,?)";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1,ID); //아이디
					pstmt.setString(2,PW); //패스워드
					pstmt.setString(3,NAME); //이름
					
					rs = pstmt.executeQuery();
					System.out.println("가입이 완료 되었습니다.");
					return new UserInfo(ID,PW,NAME);
					
				 }
					
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	return null;
	}
	
  	public void dataInfo () throws ClassNotFoundException, SQLException {
  		  UserInfo info = null;
  		  info = loginInfo();


  	  }
}
//블랙리스트 차단
class BlackList {

    
}

//로그인
class UserJoin {

    private String search (String id, String pw) throws ClassNotFoundException {	
   
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
		    con = DriverManager.getConnection(
				         "jdbc:oracle:thin:@localhost:1521:xe",
				         "scott",
				         "tiger");

		    String sql = "create table TB_USERLIST(id varchar2(20) UNIQUE,        " +
				     "                         password varchar2(10) NOT NULL,  " +
				     "                         name varchar2(20)) ";			
//---------------------------------------------------------------
			sql = "select * from TB_USERLIST";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			     rs.getString(1);
			     rs.getString(2);
			   if(id.compareTo(rs.getString(1))==0 && pw.compareTo(rs.getString(2))==0) {
				   return sql;
			    }
		    }

	     }catch (SQLException sqle) {
		        sqle.printStackTrace();
	     } finally {
	    	 
	    	 try {
			     if(rs != null) rs.close();
			     if(pstmt != null) pstmt.close();
			     if(con != null) con.close();
			
	    	 }catch (Exception e) {
			      e.printStackTrace();
	    	 }
	      }
			  return null;
     }
    
    //블랙리스트 찾기
    private String searchB (String idB) throws ClassNotFoundException {	
    	   
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
		    con = DriverManager.getConnection(
				         "jdbc:oracle:thin:@localhost:1521:xe",
				         "scott",
				         "tiger");

		    String BL = "create table BlackList(id varchar2(20) UNIQUE,        " +
				     "                         password varchar2(10) NOT NULL,  " +
				     "                         name varchar2(20)) ";			
//---------------------------------------------------------------
		    BL = "select * from BlackList where id = '" + idB + "'";
			pstmt = con.prepareStatement(BL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			     rs.getString(1);
			   if(idB.compareTo(rs.getString(1))==0) {
				   return BL;
			    }
		    }
			
    	}catch (SQLException sqle) {
	        sqle.printStackTrace();
        } finally {
    	 
    	   try {
		       if(rs != null) rs.close();
		       if(pstmt != null) pstmt.close();
		       if(con != null) con.close();
		
	        }catch (Exception e) {
		        e.printStackTrace();
	        }
        }
	        return null;
      }  
    
    public void JoinData () throws ClassNotFoundException {

       System.out.println("로그인 해 주세요.");
	   System.out.println("아이디: ");
	   String id = MenuViewer.keyboard.next();
	   System.out.println("비밀번호: ");
	   String pw = MenuViewer.keyboard.next();

	   String info = search(id,pw);

		if (info==null) { //아이디가 null 이라면
		    System.out.println("가입되지 않은 아이디 입니다.");
    		System.out.println("다음 중 선택해 주세요.");
 		    System.out.println("1.로그인 | 2. 회원가입 | 3. 종료");
 		    System.out.println("선택>>");
		} else { //아이디가 DB에 있을 경우
			
			String info1 = searchB(id); //블랙리스트에 있는지 로그인 전 체크
			
			if(info1 != null) {
				System.out.println("접속이 불가능한 아이디입니다.");
	    		System.out.println("다음 중 선택해 주세요.");
	 		    System.out.println("1.로그인 | 2. 회원가입 | 3. 종료");
			}else {
				try {
					String ServerIP = "localhost";
					Socket socket = new Socket(ServerIP, 9999); //소켓생성
					System.out.println("로그인이 되었습니다.");
				
					System.out.println("********************************");
					System.out.println("다음 중 명령어를 입력해 주세요.");
					System.out.println("********************************");
					System.out.println
				        ("1) /openRoom(공개방) | 2) /secretRoom(비공개방) ");
					System.out.println
						("3) /viewList (방 리스트 보기) | 4) /chat (대화방 참여)");

					System.out.println("명령어 선택 >> ");
								
					//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
					Thread receiver = new Receiver6(socket);
					receiver.start();
				
					new ChatWin(socket, id);
			
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
    }	
}			

//입력 클래식
class MenuViewer {
	
	public static Scanner keyboard = new Scanner(System.in);

}

public class MultiClient7 {
		
	public static void main(String[] args) throws UnknownHostException, 
	IOException, SQLException {
		
		UserData data = new UserData();
		UserJoin join = new UserJoin();
		
		int choice;
		System.out.println("다음 중 선택해 주세요.");
		System.out.println("1.로그인 | 2. 회원가입 | 3. 종료");
		System.out.println("선택>>"); 
		
		while(true) {
			
			choice = MenuViewer.keyboard.nextInt();
			MenuViewer.keyboard.nextLine();
			
			try {
				if(choice<1 || choice>3) 
					System.out.println("잘못입력하셨습니다.");
				
			    switch(choice) {
				  case 1 : join.JoinData(); // 로그인
				           break;
				  case 2 : data.dataInfo(); //회원가입
					       System.out.println("다음 중 선택해 주세요.");
					       System.out.println("1.로그인 | 2. 회원가입 | 3. 종료");
					       System.out.println("선택>>"); 
				           break;
				  case 3 : System.out.println("프로그램을 종료합니다.");
				           return; //종료
				 }
			    
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}

 }

