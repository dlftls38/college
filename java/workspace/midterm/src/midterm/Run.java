package midterm;
import java.util.*;
public class Run {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ParkingLotManager manager = new ParkingLotManager();
		int select;
		while(true){
			System.out.println("=========================================================================");
			System.out.print("무엇을 하시겠습니까? (1.입차/2.출차/3.회원관리/4.매출관리/5.기록보기/6.주차현황/7.종료) : ");
			select = s.nextInt();
			manager.work(select);
			if(select==7) { break; }
		}
		s.close();
	}

}
