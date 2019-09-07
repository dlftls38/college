
public class SingleLinkedList {
	private Node head;
	private int count = 0; 

	private class Node {
		int data;
		Node next;

		Node(int input) {
			data = input;
			next = null;
		}
	}

	public void insert(int input) {
		if (head == null) { 
			head = new Node(input);
			count++;
			return;
		}	
		Node temp = new Node(input);
		temp.next = head;
		head = temp;
		//
		count++;
	}
	public void insert(int position, int input){
		if(position > count || position <0) {
			System.out.println("position value - out of range.");
			return;
		}
		if(position == 0)
			insert(input);
		else{
			Node temp = head;
			for(int i=1; i<position; i++){
				temp = temp.next;
			}
			Node newNode = new Node(input);
			newNode.next = temp.next;
			temp.next = newNode;
			count++;
		}
	}
	public boolean isEmpty() { 
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	public void delete() {
		if (isEmpty()) { 
			System.out.println("Empty");
		} else {
			System.out.println("Delete " + head.data);
			head = head.next;
			count--;
		}
	}
	public void delete(int position) {
		if(position > count || position <0){
			System.out.println("position value - out of range.");
			return;
		}
		if(position == 0)
			delete();
		else {
			Node temp = head;
			for(int i=1; i<position; i++){
				temp = temp.next;
			}
			System.out.println("Delete " + temp.next.data);
			temp.next = temp.next.next;
			count--;
		}
	}
	public void print_list() {
		Node temp = head;
		for (int i = 0; i < count; i++) {
			System.out.print(temp.data + "  ");
			temp = temp.next;
		}
		System.out.println("");
	}
	public int getSize(){
		return count;
	}
}
