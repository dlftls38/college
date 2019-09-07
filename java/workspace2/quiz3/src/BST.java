
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
		Node node = root;
		while(node.key!=key){
			if(node.key<key){
				node=node.right_child;
			}
			else{
				node=node.left_child;
			}
		}
		int count=0;
		if(node.left_child!=null){
			count++;
		}
		if(node.right_child!=null){
			count++;
		}
		if(count==0){
			if(node==root){
				root=null;
			}
			else{
				if(node.parent.left_child==node){
					node.parent.left_child=null;
				}
				else{
					node.parent.right_child=null;
				}
			}
		}
		else if(count==1){
			if(node.left_child!=null){
				node.key=node.left_child.key;
				node.right_child=node.left_child.right_child;
				node.left_child=node.left_child.left_child;
			}
			else{
				node.key=node.right_child.key;
				node.left_child=node.right_child.left_child;
				node.right_child=node.right_child.right_child;
			}
		}
		else{
			Node remember = node;
			node=node.right_child;
			while(true){
				if(node.left_child!=null){
					node=node.left_child;
				}
				else{
					break;
				}
			}
			remember.key=node.key;
			if(node.right_child==null){
				if(node.parent.left_child==node){
					node.parent.left_child=null;
				}
				else{
					node.parent.right_child=null;
				}
			}
			else{
				if(node.parent.left_child==node){
					node.parent.left_child=node.right_child;
				}
				else{
					node.parent.right_child=node.right_child;
				}
			}
		}
		////////////////////////////////////////
		
	}
	
	
	public void printPreorder(Node node){
		//////////////////////////////
		if(node==null){
			return;
		}
		System.out.print(node.key + " ");
		printPreorder(node.left_child);
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
