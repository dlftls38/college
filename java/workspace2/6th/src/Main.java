public class Main {

	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push("1");
		stack.printStack();
		stack.push("2");
		stack.printStack();
		stack.push("3");
		stack.printStack();
		stack.push("4");
		stack.printStack();
		stack.push("5");
		stack.printStack();
		stack.pop();
		stack.printStack();
		stack.pop();
		stack.printStack();
		stack.push("6");
		stack.printStack();
		stack.top();
	}
}
