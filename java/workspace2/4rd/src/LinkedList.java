
public class LinkedList {
	private Node head; // ����Ʈ�� ��带 ����Ű�� ������	point to head of the list
	private int count = 0; // ����Ʈ�� ��尹��	the number of nodes in the list

	private class Node {	// ����Ŭ���� Node	inner class Node
		int data;
		Node next;

		Node(int input) {
			data = input;
			next = null;
		}
	}

	public void insert(int input) {		// �Ǿտ� input �����͸� ������ ��� �߰�	insert a node at the first of the list
		//////////////// todo
		
		Node node = new Node(input);
		node.next=head;
		head=node;
		count++;





		///////////////
	}
	public void insert(int position, int input){	// position ��ġ�� input �����͸� ������ ��� �߰�	insert a node at the given position
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
	public boolean isEmpty() { // ����Ʈ�� ����ִ��� �˻�	check whether the list is empty or not
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	public void delete() {	// �Ǿ��� ��带 ����
		/////////////////// todo
		System.out.print("Delete " + head.data + "\n");
		head=head.next;
		count--;




		///////////////////
	}
	public void delete(int position) {	// position ��ġ�� ��带 ����		delete a Node at the given position
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
		for (int i = 0; i < count; i++) { // ��� ���
			System.out.print(temp.data + "  ");
			temp = temp.next;
		}
		System.out.println("");
	}
}
