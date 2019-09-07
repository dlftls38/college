
public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MergeSort merge = new MergeSort();
		QuickSort quick = new QuickSort();
		
		int[] data1 = { 45, 39, 98, 15, 52, 44, 33, 28, 40, 38, 77, 68, 11, 43 };
		int[] data2 = { 101, 39, 98, 15, 52, 44, 33, 28, 40, 5, 77, 68, 11, 43 };

		
		System.out.println("Original data1 : ");
		for (int i = 0; i < data1.length; i++)
			System.out.print(data1[i] + " ");
		System.out.println();

		
		System.out.println("\nSored data(Merge Sort) : ");
		merge.sort(data1);
		for (int i = 0; i < data1.length; i++)
			System.out.print(data1[i] + " ");
		System.out.println();
		
		
		System.out.println("\nOriginal data2 : ");
		for (int i = 0; i < data2.length; i++)
			System.out.print(data2[i] + " ");
		System.out.println();
		
		
		System.out.println("\nSored data(Quick Sort) : ");
		quick.sort(data2);
		for (int i = 0; i < data2.length; i++)
			System.out.print(data2[i] + " ");
		System.out.println();
	}

}
