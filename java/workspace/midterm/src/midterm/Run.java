package midterm;
import java.util.*;
public class Run {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ParkingLotManager manager = new ParkingLotManager();
		int select;
		while(true){
			System.out.println("=========================================================================");
			System.out.print("������ �Ͻðڽ��ϱ�? (1.����/2.����/3.ȸ������/4.�������/5.��Ϻ���/6.������Ȳ/7.����) : ");
			select = s.nextInt();
			manager.work(select);
			if(select==7) { break; }
		}
		s.close();
	}

}
