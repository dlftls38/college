package doubly;

public class run {

	public static void main(String[] args) {
		DoublyLinkedList list1 = new DoublyLinkedList(); 
		list1.addAt(1,"j","0");
		list1.printList();
		list1.addFirst("a","1");
		list1.printList();
		list1.addFirst("b","2");
		list1.printList();
		list1.addFirst("c","3");
		list1.printList();
		list1.addAt(2,"d","4");
		list1.printList();
		list1.addAt(1,"e","5");
		list1.printList();
		list1.removeAt(3);
		list1.printList();
		list1.removeAt(0);
		list1.printList();
		list1.addLast("f","6");
		list1.printList();
		list1.removeFirst();
		list1.printList();
		list1.removeLast();
		list1.printList();
		list1.removeLast();
		list1.printList();
		list1.removeLast();
		list1.printList();
		list1.removeLast();
		list1.printList();
		list1.addAt(0,"g","7");
		list1.printList();
		list1.removeAt(1);
		list1.printList();
		list1.removeAt(0);
		list1.printList();
		list1.addLast("h","8");
		list1.printList();
		list1.addLast("i","9");
		list1.printList();
		list1.removeAt(2);
		list1.printList();
		list1.printListBack();
	}

}
