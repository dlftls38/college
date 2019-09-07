package test;

public class a {
	public static int convert(String str, int digit, int now, int length){
		digit*=10;
		digit+=str.charAt(now)-48;
		if(length==now+1){
			return digit;
		}
		else{
			return convert(str, digit, now+1, length);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(convert("13531",0,0,"13531".length()));
	}

}
