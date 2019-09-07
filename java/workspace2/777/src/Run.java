
public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue_prob que = new Queue_prob();
		que.printQueue();
		que.enqueue(1);
		que.printQueue();
		que.enqueue(2);
		que.printQueue();
		que.enqueue(3);
		que.printQueue();
		que.enqueue(4);
		que.printQueue();
		que.enqueue(5);
		que.printQueue();
		System.out.println();
		
		que.dequeue();
		que.printQueue();
		que.dequeue();
		que.printQueue();
		que.dequeue();
		que.printQueue();
		que.enqueue(6);
		que.printQueue();
	}

}
