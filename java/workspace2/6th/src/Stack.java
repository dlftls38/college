public class Stack {       
    private Node top; 
    private class Node {
        private String data;
        private Node next;
        Node(String data){
        	this.data = data;
        }
    }
    
    // Constructor (�깮�꽦�옄)
    public Stack() {
        top = null;
    }

    /*
     * Stack �븞�뿉 �엳�뒗 elements �쓽 媛쒖닔瑜� 諛섑솚�븳�떎.
     */
    public int size(){
    	Node node = this.top;
    	int count=0;
    	while(node!=null){
    		count++;
    		node=node.next;
    	}
    	return count;
    }

    /*
     * Stack�씠 鍮꾩뼱�엳�뒗吏�瑜� 諛섑솚�븳�떎.
     */
    public boolean isEmpty(){
    	if(top==null){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    /*
     * Stack�쓽 top �옄由ъ뿉 data瑜� 媛�吏��뒗 Node瑜� �궫�엯�븳�떎.
     */
    public void push(String data){
    	Node node = new Node(data);
		node.next = this.top;
		this.top = node;
    }
   
    /*
     * Stack�쓽 top�쓣 諛섑솚�븳�떎. (�젣嫄� x)
     */    
    public String top() {
    	System.out.println("Top data = " + top.data);
    	if(this.top!=null){
    		return top.data;
    	}
    	else{
    		return " ";
    	}
    }


    /*
     * Stack�쓽 top�쓣 �젣嫄고븯硫�, �젣嫄고븳 Node瑜� 諛섑솚�븳�떎.
     */    
    public Node pop(){
    	Node node = this.top;
    	this.top = this.top.next;
    	return node;
    }
   

    /*
     * Stack �쓣 top遺��꽣 留덉�留� Node源뚯� 異쒕젰�븳�떎.
     */    
    public void printStack(){
    	Node node = this.top;
    	while(node!=null){
    		System.out.print(node.data + " ");
    		node = node.next;
    	}
    	System.out.println();
    }


}
