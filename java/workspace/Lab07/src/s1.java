import java.util.*;
import java.io.*;
public class s1 {
	static String address;
	public static void main(String[] args) {
		s2 st = new s2();
		Scanner s = new Scanner(System.in);
		String folder;
		String filename;
		boolean exist;
		int select;
		File file = null;
		System.out.print("����� ������ ��θ� �Է��ϼ���. >> ");
		folder = s.next();
		file = new File(folder);
		File[] subFiles = file.listFiles();
		for(int i=0; i<subFiles.length; i++){
			File f = subFiles[i];
			System.out.print(f.getName() + "  ");
		}
		System.out.println();
		System.out.print("�о���� ���ϸ��� �Է��ϼ��� >> ");
		filename = s.next();
		exist = false;
		for(int i=0; i<subFiles.length; i++){
			File f = subFiles[i];
			if(f.getName().equals(filename)){
				exist=true;
				break;
			}
		}
		if(exist==true){
		    try {
		        BufferedReader in = new BufferedReader(new FileReader(folder + "\\"+ filename));
		        String some;
		        int count=1;
		        while ((some = in.readLine()) != null) {
		          st.write(some,count);
		          count++;
		          if(count==4){
		        	  count=1;
		          }
		        }
		        in.close();
		      } catch (IOException e) {
		      }
		}
		else{
			System.out.println("������ �������� �ʽ��ϴ�! �Է��� ���ϸ����� �������� �����մϴ�.");
			System.out.println("���α׷��� �����մϴ�.\n");
		}
		address = folder + "\\"+ filename;
		while(true){
			System.out.print("1. ���  2. ����  3. ��Ϻ���  4. ����  >> ");
			select = s.nextInt();
			st.start(select);
			if(select==4){
				break;
			}
		}
	}
}
