
public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST bst = new BST();
		bst.insert(8);
		bst.insert(4);
		bst.insert(11);
		bst.insert(2);
		bst.insert(3);
		bst.insert(1);
		bst.insert(6);
		bst.insert(5);
		bst.insert(7);
		bst.insert(13);
		bst.insert(9);
		bst.insert(10);
		bst.insert(12);
		
		System.out.print("Search(5) -> ");
		if(bst.search(5) == null)
			System.out.println("There is no 5 ");
		else
			System.out.println("I found 5 ");
		System.out.print("Preorder : ");
		bst.printPreorder(bst.getRoot());
		System.out.println();
		System.out.print("inorder : ");
		bst.printInorder(bst.getRoot());
		System.out.println();
		System.out.print("Postorder : ");
		bst.printPostorder(bst.getRoot());
		System.out.println();
		bst.delete(8);
		System.out.print("After delete(8) : ");
		bst.printPreorder(bst.getRoot());
		System.out.println();
		bst.delete(5);
		System.out.print("After delete(5) : ");
		bst.printPreorder(bst.getRoot());
		System.out.println();
		bst.delete(2);
		System.out.print("After delete(2) : ");
		bst.printPreorder(bst.getRoot());
		System.out.println();
		
		System.out.print("Search(8) -> ");
		if(bst.search(8) == null)
			System.out.println("There is no 8");
		else
			System.out.println("I found 8");
		System.out.print("Preorder : ");
		bst.printPreorder(bst.getRoot());

	}

}
