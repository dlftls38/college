


public class BST {
	private Node root;
	
	private class Node{
		private int key;
		private Node left_child;
		private Node right_child;
		private Node parent;
		Node(int key){
			this.key = key;
		}
	}
	
	public void insert(int key){
		if(this.search(key) != null){
			System.out.println(key + " is already in the tree");
			return;
		}
		Node newNode = new Node(key);	// assume the data is same as key
		if(root == null){
			root = newNode;
		} else {
			Node temp = root;
			while(true){
				if(temp.left_child == null && (key < temp.key)){
					temp.left_child = newNode;
					newNode.parent = temp;
					break;
				} else if(temp.right_child == null && (key > temp.key)){
					temp.right_child = newNode;
					newNode.parent = temp;
					break;
				} else if(temp.left_child == null && temp.right_child == null){
					if(key > temp.key){
						temp.right_child = newNode;
						newNode.parent = temp;
					}
					else{
						temp.left_child = newNode;
						newNode.parent = temp;
					}
					break;
				}
				if(key > temp.key){
					temp = temp.right_child;
				} else {
					temp = temp.left_child;
				}
			}
			
		}
	}
	public void delete(int key){
		Node temp = search(key);
		
		if(temp == null){
			System.out.println("There is no " + key);
			return;
		}
		/////////////////////////////////////////

		if(temp.right_child!=null){
			Node now = temp.right_child;
			boolean flag = false;
			while(now.left_child != null){
				flag = true;
				now = now.left_child;
			}
			temp.key = now.key;
			if(flag)
				now.parent.left_child = now.right_child;
			else{
				now.parent.right_child =now.right_child;
			}
		}
		else if(temp.left_child != null){
			Node now = temp.left_child;
			boolean flag = false;
			while(now.right_child != null){
				flag = true;
				now = now.right_child;
			}
			temp.key = now.key;
			if(flag)
				now.parent.right_child = now.left_child;
			else{
				now.parent.left_child = now.left_child;
			}
		}
		else{
			if(temp == root){
				root = null;
			}
			else{
				if(temp.key>temp.parent.key){
					temp.parent.right_child = null;
				}
				else if(temp.key<temp.parent.key){
					temp.parent.left_child = null;
				}
			}
		}
		
		
		
		
		////////////////////////////////////////
		
	}
	
	
	public void printPreorder(Node node){
		//////////////////////////////
		if(node == null){
			System.out.println("I can't print.");
			return ;
		}
		System.out.print(node.key+" ");
		if(node.left_child != null)
			printPreorder(node.left_child);
		if(node.right_child != null)
			printPreorder(node.right_child);
		
		
		
		//////////////////////////////
	}
	
	public Node search(int key){
		Node temp = root;
		while(true){
			if(temp == null){
				return null;
			} else {
				if(key > temp.key){
					temp = temp.right_child;
				} else if(key < temp.key){
					temp = temp.left_child;
				} else {
					return temp;
				}
			}
		}
	}
	
	public Node getRoot(){
		return root;
	}
}
