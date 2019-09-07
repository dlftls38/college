package midterm;

public class ParkedCar extends Car{
	int inTime_hour;
	int inTime_minute;
	int outTime_hour;
	int outTime_minute;
	boolean member;
	ParkedCar(String carNumber, String carSort, int inTime_hour, int inTime_minute, int outTime_hour, int outTime_minute,boolean member){
		super(carNumber, carSort);
		this.inTime_hour = inTime_hour;
		this.inTime_minute = inTime_minute;
		this.outTime_hour = outTime_hour;
		this.outTime_minute = outTime_minute;
		this.member = member;
	}
}
