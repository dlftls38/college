
public class QuickSort {
	private int[] array;
    private int length;
 
    public void sort(int[] inputArr) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        
        /////////////////////
        
        length = inputArr.length;
        array = inputArr;
        quickSort(0,length-1);
        
        
        /////////////////////
    }
 
    private void quickSort(int low, int high) {
    	////////////////////////
    	int pivot_index = low;
    	int pivot = array[low];
    	int i = low;
        int j = high;
        
        
        // Divide into two arrays
        while (i<=j) {
        	while(i<j){
        		if(array[i]<=pivot){
        			i++;
        		}
        		else{
        			break;
        		}
        	}
        	while(i<=j){
        		if(array[j]>pivot){
        			j--;
        		}
        		else{
        			break;
        		}
        	}
            if(i<j){
            	swap(i,j);
            }
            else{
            	break;
            }
        }
        swap(pivot_index,j);
        // call quickSort() method recursively
        if(low<j-1){
        	quickSort(low,j-1);
        }
        if(j+1<high){
        	quickSort(j+1,high);
        }
        
    	
    	//////////////////////////
    }
 
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
     
  
}
