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
	 * header 와 trailer는  포인터로 사용했습니다.
	 * 우선 header와 trailer를 잇고 size는 0으로 초기화시킵니다.
	 */
	public DoublyLinkedList() {
		this.header = new Node(null,null);
		this.trailer = new Node(null,null);
		this.header.next = this.trailer;
		this.trailer.prev = this.header;
		this.size = 0;
	}
	/*
	 * 앞에 새 노드를 추가하는 함수로 header는 그저 포인터이므로 header 다음 노드에 새 노드를 추가합니다.
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
	 * 끝에 새 노드를 추가하는 함수로 trailer는 그저 포인터이므로 trailer 앞에 새 노드를 추가합니다. 
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
	 * 우선 position이 size에 맞지않는 범위에 있다면 예외처리를해줍니다.
	 * position이 0일경우엔 간단히 addFirst를 사용합니다
	 * position이 size의 중간에서 앞인지 뒤인지를 확인후 앞이면 header에서 출발, 뒤면 trailer에서 출발하여 position에 도달하여 그곳에 새 노드를 추가합니다.
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
	 * 만약 size가 0 이라면 Empty를 출력해주고 아니라면
	 * 첫번째 위치에 있는 노드를 없애줍니다
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
	 * 만약 사이즈가 0이라면 Empty를 출력해주고
	 * 아니라면 끝위치에 있는 노드를 없애줍니다
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
	 * 우선 position이 size의 값에 맞지않는 범위에있다면 예외처리를 해주고
	 * size가 0이라면 Empty를 출력해주고 아니라면
	 * position이 size의 중간값의 앞인지 뒤인지를 조건문으로 처리해준후
	 * 앞이면 header부터 뒤면 trailer부터 출발해 position에 도달해 그 위치의 노드를 삭제해줍니다
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
	 * header위치 부터 출발해 노드의 data들을 출력해줍니다
	 */
	public void printList() {
		Node node = this.header;
		for(int i=0;i<size;i++){
			node = node.next;
			System.out.print("(" + node.name + ", " + node.phone + ") ");
			if(i!=size-1){
				System.out.print("ㅡ> ");
			}
		}
		if(size!=0)
			System.out.println("");
	}
	/*
	 * trailer위치 부터 출발해 노드의 data들을 출력해줍니다
	 */
	public void printListBack() {
		Node node = this.trailer;
		for(int i=0;i<size;i++){
			node = node.prev;
			System.out.print("(" + node.name + ", " + node.phone + ") ");
			if(i!=size-1){
				System.out.print("ㅡ> ");
			}
		}
		if(size!=0)
			System.out.println("");
	}
}
