package doubly;

public class DoublyLinkedList {
	private Node header;
	private Node trailer;
	private int size;
	private class Node {
    	String name;
    	String phone;
    	Node prev; 
	    Node next;

	    Node(String name, String phone) {
	        this.name = name;
	        this.phone = phone;
	        prev = null;
	        next = null;
	    }
	}
	/*
	 * header �� trailer��  �����ͷ� ����߽��ϴ�.
	 * �켱 header�� trailer�� �հ� size�� 0���� �ʱ�ȭ��ŵ�ϴ�.
	 */
	public DoublyLinkedList() {
		this.header = new Node(null,null);
		this.trailer = new Node(null,null);
		this.header.next = this.trailer;
		this.trailer.prev = this.header;
		this.size = 0;
	}
	/*
	 * �տ� �� ��带 �߰��ϴ� �Լ��� header�� ���� �������̹Ƿ� header ���� ��忡 �� ��带 �߰��մϴ�.
	 */
	public void addFirst(String name, String phone) {
		Node node = new Node(name,phone);
		this.header.next.prev = node;
		node.next = this.header.next;
		node.prev = this.header;
		this.header.next = node;
		this.size++;
	}
	/*
	 * ���� �� ��带 �߰��ϴ� �Լ��� trailer�� ���� �������̹Ƿ� trailer �տ� �� ��带 �߰��մϴ�. 
	 */
	public void addLast(String name, String phone) {
		Node node = new Node(name,phone);
		this.trailer.prev.next = node;
		node.prev = this.trailer.prev;
		node.next = this.trailer;
		this.trailer.prev = node;
		this.size++;
	}
	/*
	 * �켱 position�� size�� �����ʴ� ������ �ִٸ� ����ó�������ݴϴ�.
	 * position�� 0�ϰ�쿣 ������ addFirst�� ����մϴ�
	 * position�� size�� �߰����� ������ �������� Ȯ���� ���̸� header���� ���, �ڸ� trailer���� ����Ͽ� position�� �����Ͽ� �װ��� �� ��带 �߰��մϴ�.
	 */
	public void addAt(int position, String name, String phone) {
		if(position > size || position <0) {
			System.out.println("position value - out of range.");
			return;
		}
		if(position == 0){
			addFirst(name,phone);
		}
		else{
			Node node = new Node(name,phone);
			if(size/2<position){
				Node moving_node = this.trailer;
				for(int i=0;i<size-position;i++){
					moving_node = moving_node.prev;
				}
				moving_node.prev.next = node;
				node.prev = moving_node.prev;
				node.next = moving_node;
				moving_node.prev = node;
			}
			else{
				Node moving_node = this.header;
				for(int i=0;i<position;i++){
					moving_node = moving_node.next;
				}
				moving_node.next.prev = node;
				node.prev = moving_node;
				node.next = moving_node.next;
				moving_node.next = node;
			}
			this.size++;
		}
	}
	/*
	 * ���� size�� 0 �̶�� Empty�� ������ְ� �ƴ϶��
	 * ù��° ��ġ�� �ִ� ��带 �����ݴϴ�
	 */
	public void removeFirst() {
		if(size!=0){
			System.out.println("Remove (" + header.next.name + ", " + header.next.phone + ")");
			this.header.next = this.header.next.next;
			this.header.next.prev = this.header;
			this.size--;
		}
		else{
			System.out.println("Empty");
		}
	}
	/*
	 * ���� ����� 0�̶�� Empty�� ������ְ�
	 * �ƴ϶�� ����ġ�� �ִ� ��带 �����ݴϴ�
	 */
	public void removeLast() {
		if(size!=0){
			System.out.println("Remove (" + trailer.prev.name + ", " + trailer.prev.phone + ")");
			this.trailer.prev = this.trailer.prev.prev;
			this.trailer.prev.next = this.trailer;
			this.size--;
		}
		else{
			System.out.println("Empty");
		}
	}
	/*
	 * �켱 position�� size�� ���� �����ʴ� �������ִٸ� ����ó���� ���ְ�
	 * size�� 0�̶�� Empty�� ������ְ� �ƴ϶��
	 * position�� size�� �߰����� ������ �������� ���ǹ����� ó��������
	 * ���̸� header���� �ڸ� trailer���� ����� position�� ������ �� ��ġ�� ��带 �������ݴϴ�
	 */
	public void removeAt(int position) {
		if(position >= size || position <0){
			System.out.println("position value - out of range.");
			return;
		}
		if(size!=0){
			if(size/2<position){
				Node moving_node = this.trailer;
				for(int i=0;i<size-position;i++){
					moving_node = moving_node.prev;
				}
				System.out.println("Remove (" + moving_node.name + ", " + moving_node.phone + ")");
				moving_node.prev.next = moving_node.next;
				moving_node.next.prev = moving_node.prev;
			}
			else{
				Node moving_node = this.header;
				for(int i=0;i<position;i++){
					moving_node = moving_node.next;
				}
				System.out.println("Remove (" + moving_node.next.name + ", " + moving_node.next.phone + ")");
				moving_node.next.next.prev = moving_node;
				moving_node.next = moving_node.next.next;
			}
			this.size--;
		}
		else{
			System.out.println("Empty");
		}
	}
	/*
	 * header��ġ ���� ����� ����� data���� ������ݴϴ�
	 */
	public void printList() {
		Node node = this.header;
		for(int i=0;i<size;i++){
			node = node.next;
			System.out.print("(" + node.name + ", " + node.phone + ") ");
			if(i!=size-1){
				System.out.print("��> ");
			}
		}
		if(size!=0)
			System.out.println("");
	}
	/*
	 * trailer��ġ ���� ����� ����� data���� ������ݴϴ�
	 */
	public void printListBack() {
		Node node = this.trailer;
		for(int i=0;i<size;i++){
			node = node.prev;
			System.out.print("(" + node.name + ", " + node.phone + ") ");
			if(i!=size-1){
				System.out.print("��> ");
			}
		}
		if(size!=0)
			System.out.println("");
	}
}
