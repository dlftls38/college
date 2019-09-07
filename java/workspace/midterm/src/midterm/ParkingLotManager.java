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
		else{ System.out.println("�ȳ��� ���ʽÿ�."); }
	}
	void inCar(){
		System.out.print("�����Ͻ� ������ȣ 4�ڸ��� �Է����ֽʽÿ� : ");
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
			System.out.print("��ȸ���̽ñ��� �����Ͻ� ������ �Է����ֽʽÿ� : ");
			carSort = s.next();
		}
		else{
			System.out.println("ȸ���̽ñ���!");
			carSort = member_list.get(position).carSort;
		}
		
		System.out.print("�����ð��� �Է��� �ֽʽÿ�. �ð� : ");
		inTime_hour = s.nextInt();
		System.out.print("�����ð��� �Է��� �ֽʽÿ�. �� : ");
		inTime_minute = s.nextInt();
		inCar_carNumber.add(carNumber);
		inCar_carSort.add(carSort);
		inCar_hour.add(inTime_hour);
		inCar_minute.add(inTime_minute);
		parking_list.add(new ParkedCar(carNumber, carSort, inTime_hour, inTime_minute, outTime_hour, outTime_minute, exist));
		System.out.println("���� �Ǿ����ϴ�.");
	}
	void outCar(){
		System.out.print("�����Ͻ� ������ȣ 4�ڸ��� �Է����ֽʽÿ� : ");
		carNumber = s.next();
		System.out.print("���� �ð��� �Է��� �ֽʽÿ� �ð� : ");
		outTime_hour = s.nextInt();
		System.out.print("���� �ð��� �Է��� �ֽʽÿ� �� : ");
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
				System.out.println("ȸ���̽ñ���! ��������� 0���Դϴ�.");
				exist=true;
				break;
			}
		}
		if(exist==false){
			System.out.print("��ȸ���̽ñ���. ��������� ");
			if(parking_list.get(position).carSort.equals("����")){
				total = ((outTime_hour-inTime_hour)*60+(outTime_minute-inTime_minute))/10*100;
			}
			else if(parking_list.get(position).carSort.equals("����")){
				total = ((outTime_hour-inTime_hour)*60+(outTime_minute-inTime_minute))/10*200;
			}
			else{
				total = ((outTime_hour-inTime_hour)*60+(outTime_minute-inTime_minute))/10*300;
			}
			System.out.println(total + "���Դϴ�.");
			profit+=total;
		}
		outCar_carNumber.add(carNumber);
		outCar_carSort.add(carSort);
		outCar_hour.add(outTime_hour);
		outCar_minute.add(outTime_minute);
		parking_list.remove(position);
	}
	void manage_Member(){
		System.out.print("� ���ʽÿ� ������ ���͵帱���? (1.ȸ�����/2.ȸ������/3.ȸ����� ���) :");
		select = s.nextInt();
		if(select==1){
			System.out.print("ȸ������� ���� ������ȣ�� �Է����ֽʽÿ� : ");
			carNumber = s.next();
			System.out.print("ȸ������� ���� ������ �Է����ֽʽÿ� : ");
			carSort = s.next();
			System.out.println("ȸ������� �Ϸ�Ǿ����ϴ�");
			member_list.add(new Car(carNumber,carSort));
			profit+=100000;
		}
		else if(select==2){
			System.out.print("ȸ�������� ���� ������ȣ�� �Է����ֽʽÿ� : ");
			carNumber = s.next();
			for(i=0;i<member_list.size();i++){
				if(member_list.get(i).carNumber.equals(carNumber)){
					member_list.remove(i);
					break;
				}
			}
			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�");
		}
		else{
			System.out.println("ȸ�� ��� - ������ȣ  ����");
			for(i=0;i<member_list.size();i++){
				System.out.println("         " + member_list.get(i).carNumber + "   " + member_list.get(i).carSort);
			}
		}
	}
	void manage_Profit(){
		System.out.println("��������� ������ " + profit + "���Դϴ�.");
	}
	void looking_InOut(){
		System.out.println("����  ������ȣ  ����  �ð�");
		for(i=0;i<inCar_carNumber.size();i++){
			System.out.println((i+1) + "   " + inCar_carNumber.get(i) + "   " + inCar_carSort.get(i) + "  " + inCar_hour.get(i) + "�� " + inCar_minute.get(i) + "��");
		}
		System.out.println();
		System.out.println("����  ������ȣ  ����  �ð�");
		for(i=0;i<outCar_carNumber.size();i++){
			System.out.println((i+1) + "   " + outCar_carNumber.get(i) + "   " + outCar_carSort.get(i) + "  " + outCar_hour.get(i) + "�� " + outCar_minute.get(i) + "��");
		}
	}
	void parking_State(){
		System.out.println("������Ȳ ������ȣ ���� ȸ��/��ȸ��");
		for(i=0;i<parking_list.size();i++){
			System.out.print((i+1) + "      " + parking_list.get(i).carNumber + "  " + parking_list.get(i).carSort + "  ");
			if(parking_list.get(i).member){
				System.out.println("ȸ��");
			}
			else{
				System.out.println("��ȸ��");
			}
		}
	}
}
