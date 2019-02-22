import java.util.Scanner;


//공개방 만들기
class OpenRoom {
	Scanner scanner = new Scanner(System.in);
	
	//타이틀 설정 및 정원 설정
	public String title() {
		  
		  String name = null; //방 제목
		  int count; //방 인원
		  String boss; //방 개설자
		 

		do { 
          System.out.println("대화명을 입력하세요.");
          System.out.print(">>> ");
          name = scanner.nextLine(); 
            if (name.isEmpty()) { 
              System.out.println("대화명은 한글자 이상 입력해야 합니다.\n\n");
             } 
		} while (name.isEmpty());
		return name; 
	}

	
}

//비공개방 만들기



//방리스트 보기


public class ListOrder {

	public static void main(String[] args) {
		

	}

}
