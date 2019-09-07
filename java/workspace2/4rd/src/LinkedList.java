
public class LinkedList {
	private Node head; // 리스트의 헤드를 가리키는 포인터	point to head of the list
	private int count = 0; // 리스트의 노드갯수	the number of nodes in the list

	private class Node {	// 내부클래스 Node	inner class Node
		int data;
		Node next;

		Node(int input) {
			data = input;
			next = null;
		}
	}

	public void insert(int input) {		// 맨앞에 input 데이터를 가지는 노드 추가	insert a node at the first of the list
		//////////////// todo
		
		Node node = new Node(input);
		node.next=head;
		head=node;
		count++;





		///////////////
	}
	public void insert(int position, int input){	// position 위치에 input 데이터를 가지는 노드 추가	insert a node at the given position
		if(position > count || position <0) {
			System.out.println("position value - out of range.");
			return;
		}
		////////////////////// todo
		
		if(position==0){
			insert(input);
		}
		else{
			Node node = new Node(input);
			Node new_one = head;
			for(int i=1;i<count;i++){
				if(i==position){
					node.next = new_one.next;
					new_one.next = node;
					break;
				}
				new_one = new_one.next;
			}
			count++;
		}





		//////////////////////
	}
	public boolean isEmpty() { // 리스트가 비어있는지 검사	check whether the list is empty or not
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	public void delete() {	// 맨앞의 노드를 삭제
		/////////////////// todo
		System.out.print("Delete " + head.data + "\n");
		head=head.next;
		count--;




		///////////////////
	}
	public void delete(int position) {	// position 위치의 노드를 삭제		delete a Node at the given position
		if(position > count || position <0){
			System.out.println("position value - out of range.");
			return;
		}
		/////////////////// todo

		if(position==0){
			delete();
		}
		else{
			Node new_one = head;
			for(int i=1;i<count;i++){
				if(i==position){
					System.out.print("Delete " + new_one.next.data + "\n");
					new_one.next = new_one.next.next;
					break;
				}
				new_one = new_one.next;
			}
			count--;
		}





		////////////////////
	}
	public void print_list() {
		Node temp = head;
		for (int i = 0; i < count; i++) { // 노드 출력
			System.out.print(temp.data + "  ");
			temp = temp.next;
		}
		System.out.println("");
	}
}
