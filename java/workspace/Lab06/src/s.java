import java.util.*;
public class s {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		IO run = new IO();
		int select;
		while(true){
			System.out.print("1)ģ����� 2)��ü���� 3)������ ���� 4)���� => ");
			select = s.nextInt();
			run.start(select);
			if(select==4){
				break;
			}
			System.out.println();
		}
	}

}
