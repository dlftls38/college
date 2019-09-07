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
		System.out.print("출력할 폴더의 경로를 입력하세요. >> ");
		folder = s.next();
		file = new File(folder);
		File[] subFiles = file.listFiles();
		for(int i=0; i<subFiles.length; i++){
			File f = subFiles[i];
			System.out.print(f.getName() + "  ");
		}
		System.out.println();
		System.out.print("읽어들일 파일명을 입력하세요 >> ");
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
			System.out.println("파일이 존재하지 않습니다! 입력한 파일명으로 새파일을 생성합니다.");
			System.out.println("프로그램을 시작합니다.\n");
		}
		address = folder + "\\"+ filename;
		while(true){
			System.out.print("1. 등록  2. 삭제  3. 목록보기  4. 종료  >> ");
			select = s.nextInt();
			st.start(select);
			if(select==4){
				break;
			}
		}
	}
}
