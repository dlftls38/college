package problem;
import java.util.ArrayList;

public class Box<T> {

		//TODO #1
		//create member variables, T, ArrayList using T, and count (private, not public)
		//private ���� T(Ÿ��)�� T�� �Է¹޴� ArrayList �׸��� count�� ��������� ����ϴ�
	
	private T t;
	private ArrayList<T> t_list;
	private int count;
	
	Box(){
		
		//TODO #2 
		///Generator fuction initiallizes member variables
		///�����ڿ����� ��������� �ʱ�ȭ ��ŵ�ϴ�
		
		this.count=0;
		this.t=null;
		this.t_list = new ArrayList<T> ();
	}
	public void add(T t){
	
		///TODO #3
		///Tips : add the parameter to ArrayList using "this" notation, and increase count
		///��Ʈ : ��������� �ִ� ArrayList�� �Ѱܹ��� ���� �߰���ŵ�ϴ�(�򰥸� ���� this ���)
		///�߰��Ŀ��� count���� ������ŵ�ϴ�.
		
		this.t_list.add(t);
		count++;
	}
	
	public void remove(T t){ 
		
	
		///TODO #4
		///Tips : Check if there are same t repeatedly, Stop when there is no t.
		///When you remove a t, you also have to decrease count  
		///��Ʈ : ��������� �ִ� ArrayList�ȿ� �Ѱ� ���� ���� �ִ���
		/// '�ݺ���'���� Ȯ���Ͽ� '���� �� ����' ���� ����ϴ�.
		/// ���� ���ﶧ���� count���� �Բ� ���ҽ�ŵ�ϴ�.
		
		for(int i=0;i<this.count;i++){
			if(this.t_list.get(i)==t){
				this.t_list.remove(i);
				i--;
				count--;
			}
		}
	}
	
	public void print(){
		///TODO #5
		///Print contents in member variable ArrayList and count
		///��� �����η� �ִ� ArrayList�� ����� count���� ����մϴ�
		System.out.print("[");
		for(int i=0;i<this.count;i++){
			if(i==this.count-1){
				System.out.print(this.t_list.get(i));
			}
			else{
				System.out.print(this.t_list.get(i) + ", ");
			}
		}
		System.out.print("] Count : " + this.count + "\n");
	}
	
}
