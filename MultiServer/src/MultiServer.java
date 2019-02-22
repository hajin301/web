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
	HashMap<String, PrintWriter> clientMap; //Ŭ���̾�Ʈ ��½�Ʈ�� �����
	HashMap<String, String> createRoom; // ������ ����� (Ÿ��Ʋ, ����)
	HashMap<String, Integer> MemberNum; // ���� �����ϱ�
	
	HashMap<String, String> chatUser; // ��ȭ�� �̿���
	
	//������
	public MultiServer() {
		
		clientMap = new HashMap<String, PrintWriter>();//Ŭ���̾�Ʈ�� ��½�Ʈ���� ������ �ؽ��� ����
		Collections.synchronizedMap(clientMap);
		createRoom = new HashMap<String, String>(); //������ �����
		Collections.synchronizedMap(createRoom);
		MemberNum = new HashMap<String, Integer>(); //���� ����
		Collections.synchronizedMap(MemberNum);
		
		chatUser = new HashMap<String, String>();
		Collections.synchronizedMap(chatUser); //��ȭ�� �̿���
		
	}
	
	public void init() 
	{
		try {
			serverSocket = new ServerSocket(9999); //�������� ��ü ����
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while(true) {  //�ݺ��ؼ� ������ �����ϴ� ����� accept�ϱ� ���� while �ݺ��� ��
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
			
			    Thread mst = new MultiServerT(socket);//������ ����
				mst.start(); //������ �õ�
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
	
	//��� ������ ����Ʈ ����
	public void list(PrintWriter out) 
	{
		//Ű���� String�� ��½�Ʈ���� ���������� ���ͼ� �ش� �޽����� ����Ѵ�.
		Iterator<String> it = clientMap.keySet().iterator();
		String name = "����� ����Ʈ [";
		while(it.hasNext()) {
			name += (String)it.next() + ",";
		}
		
		name = name.substring(0, name.length()-1) + "]";
		out.println(name);
	}
		
	
	//���ӵ� ��� Ŭ���̾�Ʈ���� �޽��� ����
	private void sendAllMsg(String user, String msg) 
	{	
		//��½�Ʈ���� ���������� ���� �ش� �޽����� ���
		Iterator<String> it = clientMap.keySet().iterator();
			
		    while(it.hasNext()) {
			    try {
					PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
					
					if(user.equals(""))
					   it_out.println(msg); //����� �̸��� ���ٸ� ���븸 ���
					else
						it_out.println("[" + user + "]" + msg); //����� �̸��� �ִٸ� �̸��� ���� ���
				}catch (Exception e) {
					System.out.println("����:" + e);
				}
			}		
	  }	
	
	//������ ����� -------------------------
	
	public void OpenRoom (String User, String Title, int Num) {
		
		createRoom.put(Title, User); //��ȭ�� ����
		chatUser.put(User, Title); //��ȭ ������
		MemberNum.put(Title, Num); //���� �ֱ�


	}
			
	//�渮��Ʈ ����
	public void RoomList(PrintWriter out) {
		
		//Ű���� String�� ��½�Ʈ���� ���������� ���ͼ� �ش� �޽����� ����Ѵ�.
		Iterator<String> it = createRoom.keySet().iterator();

		String title = "��ȭ�� ����Ʈ : ";
		
		    while(it.hasNext()) {
			   title += (String)it.next() + ",";
		    }
		    title = title.substring(0, title.length()-1);
		    out.println(title); 			
	
	}
	
	//ä�� ����
	public void ChatUser (String Title, String User, String msg) {
	
		Iterator<String> it = chatUser.keySet().iterator();
		
        while(it.hasNext()) {
        	if(chatUser.containsValue(Title)) {
        		try {
        			Object obj = chatUser.get(User);
        			PrintWriter leader = (PrintWriter) obj;
					leader.println( (it.next()) + "�� �濡 �����߽��ϴ�."); 
					leader.println("[" + User + "]" + msg);
					
					Object obj1 = chatUser.get(it.next());
			        PrintWriter member = (PrintWriter) obj1;
				    member.println("[" + User + "]" + msg);    
				    
        		}catch (Exception e) {  
        			System.out.println("����:" + e);
        		}
        	}	
        }	
	}
	
	//�ӼӸ� ����+����
	public void sendMsg(String user, String msg) throws Exception{
		// /to ȫ�浿 �ȳ�?
		
			 String[] whisperMsg = msg.split(" ", 3);
			 if(clientMap.containsKey(whisperMsg[1])) {
				try {
					PrintWriter From = 
							(PrintWriter) clientMap.get(user); //������ ���
					         From.println("('" + whisperMsg[1] + "'�Կ��� ���� �ӼӸ�)" + whisperMsg[2]); //������ ��� ���
					PrintWriter To =
							(PrintWriter) clientMap.get(whisperMsg[1]); // �޴� ���
					        To.println("('" + user + "'���� �ӼӸ�) " + whisperMsg[2]); //�޴� ��� ���
				}catch (Exception e) {
					System.out.println("����:" + e);
				}

		     }else {
		    	 PrintWriter whisperFrom = 
							(PrintWriter) clientMap.get(user); //������ ���
					         whisperFrom.println(whisperMsg[1]+ "�� �����ڰ� �ƴմϴ�."); //������ ��� ���
					         whisperFrom.println("�ٽ� �Է��� �ּ���."); //������ ��� ���         
		     }

	}

	public static void main(String[] args) {
		// ���� ��ü ����
		MultiServer ms = new MultiServer();
		 //Thread�� Static���� �ϳ��� ����� ������ new�� ��ü�� �����ؼ� Thread�� ������ �����ϱ� ����
		ms.init();
		
	}
	
	// ���� Ŭ����
	//Ŭ���̾�Ʈ�κ��� �о�� �޽����� �ٸ� Ŭ���̾�Ʈ (socket)�� ������ ����
	
	class MultiServerT extends Thread {
		
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;
		
		//������
		
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(),true);
			    in = new BufferedReader(new InputStreamReader(
			    		                     this.socket.getInputStream() ));
			}catch(Exception e) {
				System.out.println("����: " + e);
			}
		}
		
		//�����带 ����ϱ� ���� run() �޼��� ������
		
		public void run() {
			
			String name = "";//Ŭ���̾�Ʈ�κ��� ���� �̸��� ������ ����
			
			try {
				name = in.readLine(); //Ŭ���̾�Ʈ�κ��� ���� �̸��� �д´�.
				sendAllMsg("",name + "���� �����ϼ̽��ϴ�.");
				clientMap.put(name, out); //�ؽ��ʿ� Ű�� name���� ���. ��Ʈ�� ��ü�� ����
				System.out.println("���� ������ ���� " + clientMap.size() + "�� �Դϴ�.");
				
				String s = ""; //����
				String title = "";//Ÿ��Ʋ
				int num; //����
				
				while (in!=null) { //  �Է� ��Ʈ���� null�� �ƴϸ� �ݺ�
					s = in.readLine(); //�Ϲ� ��ȭ
					System.out.println(s);
					
//					StringTokenizer tokens = new StringTokenizer(s.substring(1));
					
//					if(s.equals("q") || s.equals("Q"))
//						break;
					
					if(s.equals("/list")) { //�Է½�Ʈ���� /list�� ���ٸ� list ���						
						list(out);
					}
					else if(s.contains("/to")) {   //�ӼӸ� ���
													
						   sendMsg(name, s);
						  
						   String[] whisperMsg = s.split(" ", 3);
						   String orderKey = whisperMsg[0];
						   String name1 = whisperMsg[1];
						   String S2 = whisperMsg[2];
						   
						   Object obj = clientMap.get(name1);
						   PrintWriter pw = (PrintWriter) obj;
						 
						   out.println("�ӼӸ��� �����Ϸ��� ��ɾ� [/f] �� �����ּ���.");
						    
							S2 = in.readLine();
							
							if (S2.equalsIgnoreCase("/f")) {
								out.println("[    '" + name1 + "'�Կ��� �ӼӸ��� �����Ǿ����ϴ�.   ]");
								out.println("[    ��ü ��ɾ�� '/e' �Դϴ�.      ]");
								
								while (in != null) {
									S2 = in.readLine();
									if (obj != null) {
										System.out.println(">" + S2);
										if(S2.equalsIgnoreCase("/e")) {
											out.println("[ �ӼӸ� ������ �����Ǿ����ϴ�.]");
											break;
										}
										pw.println("('" + name + "'���� �ӼӸ�)" + S2);
										out.println("('" + name1 + "'�Կ��� ���� �ӼӸ�)" + S2);
									}
								} 
							}		
				     }else if(s.contains("/openRoom")) { //������ ����
				    	out.println("\n---������ ����---\n");
						out.println("��ȭ���� �Է��� �ּ���.");
					    title = in.readLine();
					    out.println("[" + title + "] "+ "��ȭ���� �����Ǿ����ϴ�.");
					    out.println("������ ������ �ּ���.");
					    num = Integer.parseInt(in.readLine());
					    out.println("������" + num + "���Դϴ�.");
					    out.println("[��ȭ��: " + title +", ����: "+ num+"]");
					    OpenRoom(name, title, num);
				     
				    }else if(s.contains("/roomList")) { //�븮��Ʈ ����
						RoomList(out);
					
				    }else if(s.contains("/chat")) { //��ȭ�� �����ϱ�
						out.println("�����ϰ��� �ϴ� ��ȭ����� �Է��� �ּ���.");
						title = in.readLine();
						if(chatUser.containsValue(title)) {
						   out.println("["+title+"]" + "��ȭ�濡 �����߽��ϴ�.");
						}
						 ChatUser (name, title, s);
//						   
//						 String[] whisperMsg = s.split(" ", 3);
//						 String orderKey = whisperMsg[0];
//						 String name1 = whisperMsg[1];
//						 String S2 = whisperMsg[2];
						 
					}else
					    sendAllMsg(name, s); //�׷��� ������ ��� ������� �޽��� ���
						  
				}
				 //System.out.println("Bye...");
			
		        }catch(Exception e) {
		        	System.out.println("����:" + e);
		        }finally {
		        	//���ܰ� �߻��� �� ����. �ؽ��ʿ��� �ش� ������ ����
		        	//���� �����ϰų� ������ java.net.SocketException:���ܹ߻�
		        	clientMap.remove(name);
		        	sendAllMsg("", name + "���� �����ϼ̽��ϴ�.");
		        	System.out.println("���� ������ ���� " + clientMap.size() + "�� �Դϴ�.");
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



