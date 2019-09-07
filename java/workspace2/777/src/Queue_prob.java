
public class Queue_prob {
	private Node front;
	private Node back;
	
	private class Node{
		private int data;
		private Node next;
		Node(int data){
			this.data = data;
		}
	}
	public Queue_prob() {
		front = null;
		back = null;
	}

	public void enqueue(int data){
		Node node = new Node(data);
		if(front==null){
			front = node;
			back = node;
		}
		else{
			node.next = back;
			back = node;
		}
	}

	public Node dequeue(){
		if(front==null){
			return null;
		}
		else{
			Node node = back;
			int size=0;
			while(node!=null){
				node=node.next;
				size++;
			}
			Node deNode;
			if(size==2){
				front = back;
				front.next=null;
				deNode = front;
			}
			else if(size==1 || size==1){
				front=null;
				back=null;
				deNode = null;
			}
			else{
				node = back;
				for(int i=2;i<size;i++){
					node = node.next;
				}
				deNode = node.next;
				front = node;
				front.next=null;
			}
			
			return deNode;
		}
	}

	public void printQueue(){
		Node node = back;
		int size=0;
		while(node!=null){
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println();
	}

	public boolean isEmpty(){
		if(front == null){
			return true;
		} else {
			return false;
		}
	}

}
