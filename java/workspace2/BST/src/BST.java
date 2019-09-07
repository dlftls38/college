
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
		
		//////////////////////////////////// todo
		if(root==null){
			root=newNode;
		}
		else{
			Node node = root;
			while(true){
				if(node.key<=key){
					if(node.right_child==null){
						newNode.parent=node;
						node.right_child=newNode;
						break;
					}
					else{
						node=node.right_child;
					}
				}
				else{
					if(node.left_child==null){
						newNode.parent=node;
						node.left_child=newNode;
						break;
					}
					else{
						node=node.left_child;
					}
				}
				
			}
		}

		
		////////////////////////////////////
		
	}
	public void delete(int key){
		
		//////////////////////////////////// todo
		if(this.search(key) == null){
			System.out.println(key + " is not in the tree");
			return;
		}
		Node node = root;
		while(node!=null){
			if(node.key==key){
				break;
			}
			if(node.key<key){
				node=node.right_child;
			}
			else{
				node=node.left_child;
			}
		}
		int count=0;
		if(node.right_child!=null){
			count++;
		}
		if(node.left_child!=null){
			count++;
		}
		if(count==0){
			if(node.parent.left_child.key==key){
				node.parent.left_child=null;
			}
			else{
				node.parent.right_child=null;
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
			node=node.left_child;
			while(node.right_child!=null){
				node=node.right_child;
			}
			remember.key = node.key;
			if(node.left_child!=null){
				node.key=node.left_child.key;
				node.right_child=node.left_child.right_child;
				node.left_child=node.left_child.left_child;
			}
			else{
				
				if(node.parent.left_child!=null){
					if(node.parent.left_child.key==node.key){
						node.parent.left_child=null;
					}
				}
				if(node.parent.right_child!=null){
					if(node.parent.right_child.key==node.key){
						node.parent.right_child=null;
					}
				}
			}
		}
		
		
		////////////////////////////////////
	}

	public Node search(int key){
		///////////////////////////
		Node node = root;
		while(node!=null){
			if(node.key==key){
				break;
			}
			if(node.key<key){
				node=node.right_child;
			}
			else{
				node=node.left_child;
			}
		}
		if(node!=null){
			return node;
		}
		else{
			return null;
		}
		///////////////////////////
		
	}
	public void printPreorder(Node node){
		
		if(node==null){
			return;
		}
		System.out.printf("%d ",node.key);
		printPreorder(node.left_child);
		printPreorder(node.right_child);
		
	}
	public void printInorder(Node node){

		if(node==null){
			return;
		}
		printInorder(node.left_child);
		System.out.printf("%d ",node.key);
		printInorder(node.right_child);
		
	}
	public void printPostorder(Node node){

		if(node==null){
			return;
		}
		printPostorder(node.left_child);
		printPostorder(node.right_child);
		System.out.printf("%d ",node.key);
		
	}
	public Node getRoot(){
		return root;
	}
}
