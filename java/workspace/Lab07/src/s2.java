import java.util.*;
import java.io.*;
public class s2 {
	ArrayList<info> list = new ArrayList<info>();
	Scanner s = new Scanner(System.in);
	String id;
	String psw;
	String com;
	int input,i;
	void write(String some, int count){
		if(count==1){
			this.id = some;
		}
		else if(count==2){
			this.psw = some;
		}
		else{
			this.com = some;
			list.add(new info(id,psw,com));
		}
	}
	void start(int select){
		if (select==1){
			System.out.print("ID 입력 >> ");
			id = s.next();
			System.out.print("passward 입력 >> ");
			psw = s.next();
			System.out.print("comment 입력 >> ");
			com = s.next();
			list.add(new info(id,psw,com));
			System.out.println("등록되었습니다.");
			BufferedWriter bw = null;
			try{
				bw = new BufferedWriter(new FileWriter(s1.address, false));
				
				String something = null;
				
				for(i=0;i<list.size();i++){
					something = list.get(i).id;
					bw.write(something);
					bw.newLine();
					something = list.get(i).psw;
					bw.write(something);
					bw.newLine();
					something = list.get(i).com;
					bw.write(something);
					bw.newLine();
				}
				
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				if(bw != null) try{bw.close();}catch(IOException e){}
			}
		}
		else if (select==2){
			System.out.print("삭제할 항목의 번호를 입력하세요. >> ");
			input = s.nextInt();
			list.remove(input-1);
			BufferedWriter bw = null;
			try{
				bw = new BufferedWriter(new FileWriter(s1.address, false));
				
				String something = null;
				
				for(i=0;i<list.size();i++){
					something = list.get(i).id;
					bw.write(something);
					bw.newLine();
					something = list.get(i).psw;
					bw.write(something);
					bw.newLine();
					something = list.get(i).com;
					bw.write(something);
					bw.newLine();
				}
				
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				if(bw != null) try{bw.close();}catch(IOException e){}
			}
			System.out.println("삭제되었습니다.");
		}
		else if(select==3){
			if(list.size()==0){
				System.out.println("등록된 정보가 없습니다.");
			}
			else{
				System.out.println("번호  ID  passward  comment");
				for(int i=0; i<list.size(); i++){
					System.out.print((i+1) + "번   " + list.get(i).id + "  " + list.get(i).psw + "      " + list.get(i).com);
					System.out.println();
				}	
			}
		}
		else{
			System.out.print("프로그램을 종료합니다.");
		}
	}
}
