
public class MergeSort {
	private int[] array;
	private int[] helper;

	private int size;

	public void sort(int[] inputArr) {
		if (inputArr == null || inputArr.length == 0) {
			return;
	    }
		////////////////
		size = inputArr.length;
		array=inputArr;
		helper=new int[size];
		mergesort(0,size-1);
		
		
		///////////////
	  }

	  private void mergesort(int low, int high) {
	    // check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
			int middle = (low + high) / 2;

			mergesort(low, middle);
			mergesort(middle + 1, high);
			merge(low, middle, high);
	    }
	  }

	  private void merge(int low, int middle, int high) {

	    // Copy both parts into the helper array
	    for (int i = low; i <= high; i++) {
	    	helper[i] = array[i];
	    }
	    int i = low;
	    int j = middle+1;
	    int k = high;

	    // Copy the smallest value from either the left or the right side back
	    // to the origi	nal array
	    int top=low;
	   for(;i<middle+1 && j<k+1 ;){
		   if(helper[i]<=helper[j]){
			   array[top]=helper[i];
			   i++;
		   }
		   else{
			   array[top]=helper[j];
			   j++;
		   }
		   top++;
	   }
	    if(i==middle+1){
	    	for(;j<k+1;j++, top++){
	    		array[top]=helper[j];
	    	}
	    }
	    else{
	    	for(;i<middle+1;i++, top++){
	    		array[top]=helper[i];
	    	}
	    }
	    // Copy the rest of the left side of the array into the target array
	    
	  }

}
