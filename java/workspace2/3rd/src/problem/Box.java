package problem;
import java.util.ArrayList;

public class Box<T> {

		//TODO #1
		//create member variables, T, ArrayList using T, and count (private, not public)
		//private 으로 T(타입)와 T를 입력받는 ArrayList 그리고 count를 멤버변수로 만듭니다
	
	private T t;
	private ArrayList<T> t_list;
	private int count;
	
	Box(){
		
		//TODO #2 
		///Generator fuction initiallizes member variables
		///생성자에서는 멤버변수를 초기화 시킵니다
		
		this.count=0;
		this.t=null;
		this.t_list = new ArrayList<T> ();
	}
	public void add(T t){
	
		///TODO #3
		///Tips : add the parameter to ArrayList using "this" notation, and increase count
		///힌트 : 멤버변수로 있는 ArrayList에 넘겨받은 값을 추가시킵니다(헷갈림 방지 this 사용)
		///추가후에는 count값을 증가시킵니다.
		
		this.t_list.add(t);
		count++;
	}
	
	public void remove(T t){ 
		
	
		///TODO #4
		///Tips : Check if there are same t repeatedly, Stop when there is no t.
		///When you remove a t, you also have to decrease count  
		///힌트 : 멤버변수로 있는 ArrayList안에 넘겨 받은 값이 있는지
		/// '반복적'으로 확인하여 '없을 때 까지' 값을 지웁니다.
		/// 값을 지울때에는 count값을 함께 감소시킵니다.
		
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
		///멤버 변수로로 있는 ArrayList의 내용과 count값을 출력합니다
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
