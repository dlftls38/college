package problem;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Box<Integer> bi = new Box<Integer>();
		
		
		bi.add(1);
		bi.add(2);
		bi.add(3);
		bi.add(2);
		bi.add(1);
		
		bi.print();
		
		
		bi.remove(2);
		bi.print();
		
		
		
		Box<String> bs = new Box<String>();

		
		bs.add("apple");
		bs.add("orange");
		bs.add("banana");
		bs.add("apple");
		bs.add("strawberry");
		
		bs.print();
		
		
		bs.remove("apple");
		bs.print();

	}

}