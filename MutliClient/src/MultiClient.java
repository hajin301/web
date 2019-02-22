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
	String id; //���̵�
	String pw; // ���
	String name; //�̸�
	
	//������
	public UserInfo(String id, String pw, String name) {	
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
//	
//	public int hashCode() {
//		return id.hashCode();
//	}
//	
//	public boolean equals(Object obj) {
//	
//	    UserInfo cmp = (UserInfo)obj;
//	
//	    if(id.compareTo(cmp.id)==0) //�μ�Ʈ�� id�� ���� UserInfo�� ����� ���̵� ������
//		    return true; //true�� ��ȯ��
//	    else
//		    return false; // �ٸ��� false�� ��ȯ��
//    }	
}

//���̺� ȸ������ ���� ����
class UserData  {
	
	//HashSet<UserInfo> infoStorage = new HashSet<UserInfo> () ;	

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
					
				System.out.println("ȸ�������� ���ּ���.");
				
				System.out.println("���̵�: ");
				String ID = MenuViewer.keyboard.next();
				
				 //���̵� �ߺ� �˻�
				sql = "select * from TB_USERLIST where id = '" + ID + "'";
				//System.out.println(sql);
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();				
				
			//while (true) {
				
				if(rs.next()==true) { //ID�� DB ���̵� ���ٸ� �̹� ���Ե� ���̵�
				        System.out.println("�ߺ��� ���̵��Դϴ�. \n �ٽ� �Է��� �ּ���.");
				        }
				else {
					System.out.println("��� ������ ���̵��Դϴ�.");
					
					System.out.println("�н�����: ");
					String PW = MenuViewer.keyboard.next();
					
					System.out.println("�̸�: ");
					String NAME = MenuViewer.keyboard.next();

					sql = "insert into TB_USERLIST values(?,?,?)";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1,ID); //���̵�
					pstmt.setString(2,PW); //�н�����
					pstmt.setString(3,NAME); //�̸�
					
					rs = pstmt.executeQuery();
					System.out.println("������ �Ϸ� �Ǿ����ϴ�.");
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


//�α���
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
    
	private String searchB(String idB) throws ClassNotFoundException {
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

       System.out.println("�α��� �� �ּ���.");
	   System.out.println("���̵�: ");
	   String id = MenuViewer.keyboard.next();
	   System.out.println("��й�ȣ: ");
	   String pw = MenuViewer.keyboard.next();

	   String info = search(id,pw);

		if (info==null) { //���̵� null �̶��
		    System.out.println("���Ե��� ���� ���̵� �Դϴ�.");
    		System.out.println("���� �� ������ �ּ���.");
 		    System.out.println("1.�α��� | 2. ȸ������ | 3. ����");
 		    System.out.println("����>>");
		} else { //���̵� DB�� ���� ���
			
			String info1 = searchB(id); //������Ʈ�� �ִ��� �α��� �� üũ
			
			if(info1 != null) {
				System.out.println("������ �Ұ����� ���̵��Դϴ�.");
	    		System.out.println("���� �� ������ �ּ���.");
	 		    System.out.println("1.�α��� | 2. ȸ������ | 3. ����");
			}else if(info1 == null){
				try {
					String ServerIP = "localhost";
					Socket socket = new Socket(ServerIP, 9999); //���ϻ���
					System.out.println("�α����� �Ǿ����ϴ�.");
				
					System.out.println("********************************");
					System.out.println("���� �� ��ɾ �Է��� �ּ���.");
					System.out.println("********************************");
					System.out.println
				        ("1) /openRoom(������_����) | 2) /secretRoom(�������_����) ");
					System.out.println
						("3) /roomList (�渮��Ʈ) | 4) /chat (��ȭ��_����)");
					
					System.out.println("��ɾ� ���� >> ");
								
					//�������� ������ �޽����� ������� �ֿܼ� ����ϴ� ������
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

//�Է� Ŭ����
class MenuViewer {
	
	public static Scanner keyboard = new Scanner(System.in);

}

public class MultiClient {
		
	public static void main(String[] args) throws UnknownHostException, 
	IOException, SQLException {
		
		UserData data = new UserData();
		UserJoin join = new UserJoin();
		
		int choice;
		System.out.println("���� �� ������ �ּ���.");
		System.out.println("1.�α��� | 2. ȸ������ | 3. ����");
		System.out.println("����>>"); 
		
		while(true) {
			
			choice = MenuViewer.keyboard.nextInt();
			MenuViewer.keyboard.nextLine();
			
			try {
				if(choice<1 || choice>3) 
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
				
			    switch(choice) {
				  case 1 : join.JoinData(); // �α���
				           break;
				  case 2 : data.dataInfo(); //ȸ������
					       System.out.println("���� �� ������ �ּ���.");
					       System.out.println("1.�α��� | 2. ȸ������ | 3. ����");
					       System.out.println("����>>"); 
				           break;
				  case 3 : System.out.println("���α׷��� �����մϴ�.");
				           return; //����
				 }
			    
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}

 }


