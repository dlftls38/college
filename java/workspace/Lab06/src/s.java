import java.util.*;
public class s {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		IO run = new IO();
		int select;
		while(true){
			System.out.print("1)친구등록 2)전체보기 3)데이터 저장 4)종료 => ");
			select = s.nextInt();
			run.start(select);
			if(select==4){
				break;
			}
			System.out.println();
		}
	}

}
