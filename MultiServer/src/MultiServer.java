import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class MultiServer {
	
	ServerSocket serverSocket = null;
	Socket socket = null;
	HashMap<String, PrintWriter> clientMap; //클라이언트 출력스트림 저장용
	HashMap<String, String> createRoom; // 공개방 만들기 (타이틀, 정원)
	HashMap<String, Integer> MemberNum; // 정원 설정하기
	
	HashMap<String, String> chatUser; // 대화방 이용자
	
	//생성자
	public MultiServer() {
		
		clientMap = new HashMap<String, PrintWriter>();//클라이언트의 출력스트림을 저장할 해쉬맵 생성
		Collections.synchronizedMap(clientMap);
		createRoom = new HashMap<String, String>(); //공개방 만들기
		Collections.synchronizedMap(createRoom);
		MemberNum = new HashMap<String, Integer>(); //정원 설정
		Collections.synchronizedMap(MemberNum);
		
		chatUser = new HashMap<String, String>();
		Collections.synchronizedMap(chatUser); //대화방 이용자
		
	}
	
	public void init() 
	{
		try {
			serverSocket = new ServerSocket(9999); //서버소켓 객체 생성
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {  //반복해서 서버에 접속하는 사람을 accept하기 위해 while 반복문 씀
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
			
			    Thread mst = new MultiServerT(socket);//쓰레드 생성
				mst.start(); //쓰레드 시동
			}
			
	     }catch(Exception e) {
	    	    e.printStackTrace();
	     } finally {
	    	    try {
	    	    	serverSocket.close();
	    	    }catch(Exception e) {
					 e.printStackTrace();
				}
	     }
	}
	
	//모든 접속자 리스트 보기
	public void list(PrintWriter out) 
	{
		//키값인 String의 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();
		String name = "사용자 리스트 [";
		while(it.hasNext()) {
			name += (String)it.next() + ",";
		}
		
		name = name.substring(0, name.length()-1) + "]";
		out.println(name);
	}
		
	
	//접속된 모든 클라이언트에게 메시지 전달
	private void sendAllMsg(String user, String msg) 
	{	
		//출력스트림을 순차적으로 얻어와 해당 메시지를 출력
		Iterator<String> it = clientMap.keySet().iterator();
			
		    while(it.hasNext()) {
			    try {
					PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
					
					if(user.equals(""))
					   it_out.println(msg); //사용자 이름이 없다면 내용만 출력
					else
						it_out.println("[" + user + "]" + msg); //사용자 이름이 있다면 이름도 같이 출력
				}catch (Exception e) {
					System.out.println("예외:" + e);
				}
			}		
	  }	
	
	//공개방 만들기 -------------------------
	
	public void OpenRoom (String User, String Title, int Num) {
		
		createRoom.put(Title, User); //대화명 설정
		chatUser.put(User, Title); //대화 참가자
		MemberNum.put(Title, Num); //정원 넣기


	}
			
	//방리스트 보기
	public void RoomList(PrintWriter out) {
		
		//키값인 String의 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = createRoom.keySet().iterator();

		String title = "대화방 리스트 : ";
		
		    while(it.hasNext()) {
			   title += (String)it.next() + ",";
		    }
		    title = title.substring(0, title.length()-1);
		    out.println(title); 			
	
	}
	
	//채팅 시작
	public void ChatUser (String Title, String User, String msg) {
	
		Iterator<String> it = chatUser.keySet().iterator();
		
        while(it.hasNext()) {
        	if(chatUser.containsValue(Title)) {
        		try {
        			Object obj = chatUser.get(User);
        			PrintWriter leader = (PrintWriter) obj;
					leader.println( (it.next()) + "가 방에 참여했습니다."); 
					leader.println("[" + User + "]" + msg);
					
					Object obj1 = chatUser.get(it.next());
			        PrintWriter member = (PrintWriter) obj1;
				    member.println("[" + User + "]" + msg);    
				    
        		}catch (Exception e) {  
        			System.out.println("예외:" + e);
        		}
        	}	
        }	
	}
	
	//귓속말 조건+고정
	public void sendMsg(String user, String msg) throws Exception{
		// /to 홍길동 안녕?
		
			 String[] whisperMsg = msg.split(" ", 3);
			 if(clientMap.containsKey(whisperMsg[1])) {
				try {
					PrintWriter From = 
							(PrintWriter) clientMap.get(user); //보내는 사람
					         From.println("('" + whisperMsg[1] + "'님에게 보낸 귓속말)" + whisperMsg[2]); //보내는 사람 출력
					PrintWriter To =
							(PrintWriter) clientMap.get(whisperMsg[1]); // 받는 사람
					        To.println("('" + user + "'님의 귓속말) " + whisperMsg[2]); //받는 사람 출력
				}catch (Exception e) {
					System.out.println("예외:" + e);
				}

		     }else {
		    	 PrintWriter whisperFrom = 
							(PrintWriter) clientMap.get(user); //보내는 사람
					         whisperFrom.println(whisperMsg[1]+ "은 접속자가 아닙니다."); //보내는 사람 출력
					         whisperFrom.println("다시 입력해 주세요."); //보내는 사람 출력         
		     }

	}

	public static void main(String[] args) {
		// 서버 객체 생성
		MultiServer ms = new MultiServer();
		 //Thread는 Static에서 하나만 생기기 때문에 new로 객체를 생성해서 Thread를 여러개 생성하기 위해
		ms.init();
		
	}
	
	// 내부 클래스
	//클라이언트로부터 읽어온 메시지를 다른 클라이언트 (socket)에 보내는 역할
	
	class MultiServerT extends Thread {
		
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;
		
		//생성자
		
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(),true);
			    in = new BufferedReader(new InputStreamReader(
			    		                     this.socket.getInputStream() ));
			}catch(Exception e) {
				System.out.println("예외: " + e);
			}
		}
		
		//쓰레드를 사용하기 위해 run() 메서드 재정의
		
		public void run() {
			
			String name = "";//클라이언트로부터 받은 이름을 저장할 변수
			
			try {
				name = in.readLine(); //클라이언트로부터 받은 이름을 읽는다.
				sendAllMsg("",name + "님이 입장하셨습니다.");
				clientMap.put(name, out); //해쉬맵에 키를 name으로 출력. 스트림 객체를 저장
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");
				
				String s = ""; //내용
				String title = "";//타이틀
				int num; //정원
				
				while (in!=null) { //  입력 스트림이 null이 아니면 반복
					s = in.readLine(); //일반 대화
					System.out.println(s);
					
//					StringTokenizer tokens = new StringTokenizer(s.substring(1));
					
//					if(s.equals("q") || s.equals("Q"))
//						break;
					
					if(s.equals("/list")) { //입력스트림이 /list와 같다면 list 출력						
						list(out);
					}
					else if(s.contains("/to")) {   //귓속말 기능
													
						   sendMsg(name, s);
						  
						   String[] whisperMsg = s.split(" ", 3);
						   String orderKey = whisperMsg[0];
						   String name1 = whisperMsg[1];
						   String S2 = whisperMsg[2];
						   
						   Object obj = clientMap.get(name1);
						   PrintWriter pw = (PrintWriter) obj;
						 
						   out.println("귓속말을 고정하려면 명령어 [/f] 를 적어주세요.");
						    
							S2 = in.readLine();
							
							if (S2.equalsIgnoreCase("/f")) {
								out.println("[    '" + name1 + "'님에게 귓속말이 고정되었습니다.   ]");
								out.println("[    해체 명령어는 '/e' 입니다.      ]");
								
								while (in != null) {
									S2 = in.readLine();
									if (obj != null) {
										System.out.println(">" + S2);
										if(S2.equalsIgnoreCase("/e")) {
											out.println("[ 귓속말 고정이 해제되었습니다.]");
											break;
										}
										pw.println("('" + name + "'님의 귓속말)" + S2);
										out.println("('" + name1 + "'님에게 보낸 귓속말)" + S2);
									}
								} 
							}		
				     }else if(s.contains("/openRoom")) { //공개방 개설
				    	out.println("\n---공개방 개설---\n");
						out.println("대화명을 입력해 주세요.");
					    title = in.readLine();
					    out.println("[" + title + "] "+ "대화방이 생성되었습니다.");
					    out.println("정원을 설정해 주세요.");
					    num = Integer.parseInt(in.readLine());
					    out.println("정원은" + num + "명입니다.");
					    out.println("[대화명: " + title +", 정원: "+ num+"]");
					    OpenRoom(name, title, num);
				     
				    }else if(s.contains("/roomList")) { //룸리스트 보기
						RoomList(out);
					
				    }else if(s.contains("/chat")) { //대화방 참여하기
						out.println("참여하고자 하는 대화방명을 입력해 주세요.");
						title = in.readLine();
						if(chatUser.containsValue(title)) {
						   out.println("["+title+"]" + "대화방에 참여했습니다.");
						}
						 ChatUser (name, title, s);
//						   
//						 String[] whisperMsg = s.split(" ", 3);
//						 String orderKey = whisperMsg[0];
//						 String name1 = whisperMsg[1];
//						 String S2 = whisperMsg[2];
						 
					}else
					    sendAllMsg(name, s); //그렇지 않으면 모든 사람에게 메시지 출력
						  
				}
				 //System.out.println("Bye...");
			
		        }catch(Exception e) {
		        	System.out.println("예외:" + e);
		        }finally {
		        	//예외가 발생할 때 퇴장. 해쉬맵에서 해당 데이터 제거
		        	//보통 종료하거나 나가면 java.net.SocketException:예외발생
		        	clientMap.remove(name);
		        	sendAllMsg("", name + "님이 퇴장하셨습니다.");
		        	System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");
		        	try {
		        		in.close();
		        		out.close();
		        		
		        		socket.close();
		        	}catch(Exception e) {
		        		e.printStackTrace();
		        	}
		        }
		
	      }

     }
}



