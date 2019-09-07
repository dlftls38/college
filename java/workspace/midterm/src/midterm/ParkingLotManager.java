package midterm;
import java.util.*;
public class ParkingLotManager {
	Scanner s = new Scanner(System.in);
	ArrayList<Car> member_list = new ArrayList<Car>();
	ArrayList<ParkedCar> parking_list = new ArrayList<ParkedCar>();
	ArrayList<String> inCar_carNumber = new ArrayList<String>();
	ArrayList<String> inCar_carSort = new ArrayList<String>();
	ArrayList<Integer> inCar_hour = new ArrayList<Integer>();
	ArrayList<Integer> inCar_minute = new ArrayList<Integer>();
	ArrayList<String> outCar_carNumber = new ArrayList<String>();
	ArrayList<String> outCar_carSort = new ArrayList<String>();
	ArrayList<Integer> outCar_hour = new ArrayList<Integer>();
	ArrayList<Integer> outCar_minute = new ArrayList<Integer>();
	String carNumber;
	String carSort;
	int inTime_hour;
	int inTime_minute;
	int outTime_hour;
	int outTime_minute;
	int total;
	boolean exist;
	int i,position;
	int profit=0;
	int select;
	void work(int select){
		if(select==1){ inCar(); }
		else if(select==2){ outCar(); }
		else if(select==3){ manage_Member(); }
		else if(select==4){ manage_Profit(); }
		else if(select==5){ looking_InOut(); }
		else if(select==6){ parking_State(); }
		else{ System.out.println("안녕히 가십시오."); }
	}
	void inCar(){
		System.out.print("입차하실 차량번호 4자리를 입력해주십시오 : ");
		carNumber = s.next();
		exist = false;
		for(i=0;i<member_list.size();i++){
			if(member_list.get(i).carNumber.equals(carNumber)){
				exist = true;
				position = i;
				break;
			}
		}
		if(exist==false){
			System.out.print("비회원이시군요 입차하실 차종을 입력해주십시오 : ");
			carSort = s.next();
		}
		else{
			System.out.println("회원이시군요!");
			carSort = member_list.get(position).carSort;
		}
		
		System.out.print("입차시간을 입력해 주십시오. 시간 : ");
		inTime_hour = s.nextInt();
		System.out.print("입차시간을 입력해 주십시오. 분 : ");
		inTime_minute = s.nextInt();
		inCar_carNumber.add(carNumber);
		inCar_carSort.add(carSort);
		inCar_hour.add(inTime_hour);
		inCar_minute.add(inTime_minute);
		parking_list.add(new ParkedCar(carNumber, carSort, inTime_hour, inTime_minute, outTime_hour, outTime_minute, exist));
		System.out.println("입차 되었습니다.");
	}
	void outCar(){
		System.out.print("출차하실 차량번호 4자리를 입력해주십시오 : ");
		carNumber = s.next();
		System.out.print("출차 시간을 입력해 주십시오 시간 : ");
		outTime_hour = s.nextInt();
		System.out.print("출차 시간을 입력해 주십시오 분 : ");
		outTime_minute = s.nextInt();
		exist=false;
		for(i=0;i<parking_list.size();i++){
			if(parking_list.get(i).carNumber.equals(carNumber)){
				position=i;
				break;
			}
		}
		carNumber = parking_list.get(position).carNumber;
		inTime_hour = parking_list.get(position).inTime_hour;
		inTime_minute = parking_list.get(position).inTime_minute;
		for(i=0;i<parking_list.size();i++){
			if(parking_list.get(i).member==true){
				System.out.println("회원이시군요! 주차요금은 0원입니다.");
				exist=true;
				break;
			}
		}
		if(exist==false){
			System.out.print("비회원이시군요. 주차요금은 ");
			if(parking_list.get(position).carSort.equals("소형")){
				total = ((outTime_hour-inTime_hour)*60+(outTime_minute-inTime_minute))/10*100;
			}
			else if(parking_list.get(position).carSort.equals("중형")){
				total = ((outTime_hour-inTime_hour)*60+(outTime_minute-inTime_minute))/10*200;
			}
			else{
				total = ((outTime_hour-inTime_hour)*60+(outTime_minute-inTime_minute))/10*300;
			}
			System.out.println(total + "원입니다.");
			profit+=total;
		}
		outCar_carNumber.add(carNumber);
		outCar_carSort.add(carSort);
		outCar_hour.add(outTime_hour);
		outCar_minute.add(outTime_minute);
		parking_list.remove(position);
	}
	void manage_Member(){
		System.out.print("어서 오십시오 무엇을 도와드릴까요? (1.회원등록/2.회원삭제/3.회원목록 출력) :");
		select = s.nextInt();
		if(select==1){
			System.out.print("회원등록을 위해 차량번호를 입력해주십시오 : ");
			carNumber = s.next();
			System.out.print("회원등록을 위해 차종을 입력해주십시오 : ");
			carSort = s.next();
			System.out.println("회원등록이 완료되었습니다");
			member_list.add(new Car(carNumber,carSort));
			profit+=100000;
		}
		else if(select==2){
			System.out.print("회원삭제을 위해 차량번호를 입력해주십시오 : ");
			carNumber = s.next();
			for(i=0;i<member_list.size();i++){
				if(member_list.get(i).carNumber.equals(carNumber)){
					member_list.remove(i);
					break;
				}
			}
			System.out.println("회원삭제가 완료되었습니다");
		}
		else{
			System.out.println("회원 목록 - 차량번호  차종");
			for(i=0;i<member_list.size();i++){
				System.out.println("         " + member_list.get(i).carNumber + "   " + member_list.get(i).carSort);
			}
		}
	}
	void manage_Profit(){
		System.out.println("현재까지의 매출은 " + profit + "원입니다.");
	}
	void looking_InOut(){
		System.out.println("입차  차량번호  차종  시간");
		for(i=0;i<inCar_carNumber.size();i++){
			System.out.println((i+1) + "   " + inCar_carNumber.get(i) + "   " + inCar_carSort.get(i) + "  " + inCar_hour.get(i) + "시 " + inCar_minute.get(i) + "분");
		}
		System.out.println();
		System.out.println("출차  차량번호  차종  시간");
		for(i=0;i<outCar_carNumber.size();i++){
			System.out.println((i+1) + "   " + outCar_carNumber.get(i) + "   " + outCar_carSort.get(i) + "  " + outCar_hour.get(i) + "시 " + outCar_minute.get(i) + "분");
		}
	}
	void parking_State(){
		System.out.println("주차현황 차량번호 차종 회원/비회원");
		for(i=0;i<parking_list.size();i++){
			System.out.print((i+1) + "      " + parking_list.get(i).carNumber + "  " + parking_list.get(i).carSort + "  ");
			if(parking_list.get(i).member){
				System.out.println("회원");
			}
			else{
				System.out.println("비회원");
			}
		}
	}
}
