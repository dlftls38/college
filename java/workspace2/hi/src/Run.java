public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSort q = new QuickSort();
		int a[]={324,12,25,436,24,1,4326};
		for(int i=0;i<7;i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
		q.sort(a);
		for(int i=0;i<7;i++){
			System.out.print(a[i] + " ");
		}
	}

}