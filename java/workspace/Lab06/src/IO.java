import java.io.*;
import java.util.*;
public class IO {
	ArrayList<friend> list = new ArrayList<friend>();
	Scanner s = new Scanner(System.in);
	String name;
	String number;
	String address;
	int i;
	void start(int index){
		if(index==1){
			System.out.print("이름 => ");
			name = s.next();
			System.out.print("전화번호 => ");
			number = s.next();
			System.out.print("주소 => ");
			address = s.nextLine();
			address = s.nextLine();
			list.add(new friend(name,number,address));
			System.out.println("친구 1명을 등록하였습니다.");
		}
		else if(index==2){
			for(i=0;i<list.size();i++){
				System.out.println(list.get(i).name + "\t" + list.get(i).number + "\t" + list.get(i).address);
			}
		}
		else if(index==3){
			FileWriter fw = null;
			BufferedWriter bw = null;
			try{
				fw = new FileWriter("C:\\Users\\user\\Desktop\\silsb.txt", false);
				bw = new BufferedWriter(fw);
				
				String something = null;
				
				for(i=0;i<list.size();i++){
					something = list.get(i).name;
					bw.write(something);
					something = "\t";
					bw.write(something);
					something = list.get(i).number;
					bw.write(something);
					something = "\t";
					bw.write(something);
					something = list.get(i).address;
					bw.write(something);
					bw.newLine();
				}
				
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				if(bw != null) try{bw.close();}catch(IOException e){}
				if(fw != null) try{fw.close();}catch(IOException e){}
			}
			System.out.println("데이터가 텍스트 파일로 저장되었습니다.");
		}
		else{
			System.out.println("프로그램을 종료합니다.");
		}
	}
}
