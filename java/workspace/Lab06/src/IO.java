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
			System.out.print("�̸� => ");
			name = s.next();
			System.out.print("��ȭ��ȣ => ");
			number = s.next();
			System.out.print("�ּ� => ");
			address = s.nextLine();
			address = s.nextLine();
			list.add(new friend(name,number,address));
			System.out.println("ģ�� 1���� ����Ͽ����ϴ�.");
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
			System.out.println("�����Ͱ� �ؽ�Ʈ ���Ϸ� ����Ǿ����ϴ�.");
		}
		else{
			System.out.println("���α׷��� �����մϴ�.");
		}
	}
}
