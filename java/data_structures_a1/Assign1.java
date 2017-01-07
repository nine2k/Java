import java.io.IOException;
import java.util.Random;

/**
 * @author Emily Bao
 */

public class Assign1 {

	public static long ATimeStart;
	public static long ATimeEnd;
	public static long BTimeStart;
	public static long BTimeEnd;
	public static Random generator = new Random(System.currentTimeMillis());
	
	/**
	 * Algorithm A: (linear search)
	 * Preferable for a very small number of searches
	 */
	public static String algorA(int []arr, int x){
		
		for(int item: arr) {
      	   if (item==x)		 
      		 return "Yes";	 
      	   }
		return "No";
	}//end algorithm A
	
	/**
	 * Algorithm B: (binary search)
	 * Preferable for many searches of the same set
	 */

	public static String algorB(int []arr, int x){
		
		int size = arr.length;
		int low=0;
		int high=size-1;
		
		while(high>=low){
			int middle=(low+high)/2;
			if(arr[middle]==x){
				
				return "Yes";
			}
			if(arr[middle]<x){
				low=middle+1;
			}//end if
			
			if(arr[middle]>x){
				high=middle-1;
			}//end if
		}//end while
		
		return "No";
	}//end algorithm B
	
	public static int getRole(int upperBound){
		int num;
		num=generator.nextInt(upperBound);
		if (num%2 !=0){
			num=num+1;
		}
		return num;	
	}
	
	
	public static void main(String[]args)throws IOException{
		int [] Arr1 = new int[1000];
		int [] Arr2 = new int[2000];
		int [] Arr3 = new int[5000];
		int [] Arr4 = new int[10000];
		int [] targetVal10 = new int[10];
		int [] targetVal100 = new int[100];
		int [] targetVal500= new int[500];
		int [] targetVal1000 = new int[1000];
		int [] targetVal1500 = new int[1500];
		int [] targetVal2000 = new int[2000];
		//int SSet[] = {1000,2000,5000,10000};

		for (int i=0; i<1000; i++){
			Arr1[i]=getRole(10000);		
		}
		
		for (int i=0; i<2000; i++){
			Arr2[i]=getRole(20000);		
		}
		
		for (int i=0; i<5000; i++){
			Arr3[i]=getRole(50000);		
		}
		
		for (int i=0; i<10000; i++){
			Arr4[i]=getRole(100000);		
		}
		
		long [] ADuration = new long [5];
		long [] BDuration = new long [5];
		
		
		//for n=1000
		
		System.out.println("Results for n = 1000");
		System.out.println("k	 Algorithm A	Algorithm B");
		
		//For k target values in set (10)
		for (int k=0; k<5; k++){
			targetVal10[k]=Arr1[generator.nextInt(1000)];
		}
		//for k values not in set (10)
		for (int k=5; k<10; k++){
			targetVal10[k]=getRole(1000)+1;
		}
		
		//for k values not in set (500)
		for (int k=0; k<50; k++){
			targetVal500[k]=Arr1[generator.nextInt(100)];
		}
		//for k values not in set (500)
		for (int k=50; k<100; k++){
			targetVal500[k]=getRole(1000)+1;
		}
		
		ADuration[0]=0;
		BDuration[0]=0;
		int [] Arr1Unsorted = new int[100];
		
		
		for (int i=0; i<100; i++){
			Arr1Unsorted[i]=Arr1[i];
		}
		
		for (int u=0; u<500; u++){
			for (int i=0; i<10; i++){
				//for each target k value
				    ATimeStart=System.nanoTime();
					algorA(Arr1Unsorted,targetVal10[i]);
					ATimeEnd=System.nanoTime(); 
					quickSort(Arr1,0,Arr1.length-1);
					BTimeStart=System.nanoTime();
					algorB(Arr1,targetVal10[i]);
					BTimeEnd=System.nanoTime();
					ADuration[0]+=(ATimeEnd-ATimeStart);
					BDuration[0]+=(BTimeEnd-BTimeStart);
			}
			
			for (int i=0; i<100; i++){
				//for each target k value
					ATimeStart=System.nanoTime();
					algorA(Arr1,targetVal500[i]);
					ATimeEnd=System.nanoTime(); 
					BTimeStart=System.nanoTime();
					algorB(Arr1,targetVal500[i]);
					BTimeEnd=System.nanoTime();
					ADuration[1]+=(ATimeEnd-ATimeStart)/1000;
					BDuration[1]+=(BTimeEnd-BTimeStart)/1000;
			}
		}
		System.out.println("10" + "     " + ADuration[0]/500+" milliseconds" + "   " + BDuration[0]/500+" milliseconds");

		System.out.println("100" + "     " + ADuration[1]/500+" seconds" + "       " + BDuration[1]/500+" seconds");
		
		//for n=2000
		/*
		System.out.println("\n");
		System.out.println("Results for n = 2000");
		System.out.println("k	 Algorithm A	Algorithm B");
		//For k target values in set
		for (int k=0; k<5; k++){
			targetVal10[k]=Arr2[generator.nextInt(2000)];
		}
		//for k values not in set
		for (int k=5; k<10; k++){
			targetVal10[k]=getRole2()+1;
		}
		
		//for k values not in set (500)
		for (int k=0; k<250; k++){
			targetVal500[k]=Arr2[generator.nextInt(2000)];
		}
		//for k values not in set (500)
		for (int k=250; k<500; k++){
			targetVal500[k]=getRole2()+1;
		}
		
		for (int u=0; u<500; u++){
			for (int i=0; i<10; i++){
				//for each target k value
				algorA(Arr2,targetVal10[i]);
				ATimeEnd=System.nanoTime(); 
				algorB(quickSort(Arr2,0,Arr2.length-1),targetVal10[i]);
				BTimeEnd=System.nanoTime();
				ADuration[0]+=(ATimeEnd-ATimeStart)/1000;
				BDuration[0]+=(BTimeEnd-BTimeStart)/1000;
			}
			
			for (int i=0; i<500; i++){
				//for each target k value
				algorA(Arr2,targetVal500[i]);
				ATimeEnd=System.nanoTime(); 
				algorB(quickSort(Arr2,0,Arr2.length-1),targetVal500[i]);
				BTimeEnd=System.nanoTime();
				ADuration[1]+=(ATimeEnd-ATimeStart)/1000;
				BDuration[1]+=(BTimeEnd-BTimeStart)/1000;
			}
		}
		System.out.println(10 + "     " + ADuration[0]/500+" seconds" + "       " + BDuration[0]/500+" seconds");
		System.out.println(500 + "     " + ADuration[1]/500+" seconds" + "       " + BDuration[1]/500+" seconds");
		
		//for n=5000
	
		System.out.println("\n");
		System.out.println("Results for n = 5000");
		System.out.println("k	 Algorithm A	Algorithm B");
		//For k target values in set
		for (int k=0; k<5; k++){
			targetVal10[k]=Arr3[generator.nextInt(5000)];
		}
		//for k values not in set
		for (int k=5; k<10; k++){
			targetVal10[k]=getRole3()+1;
		}
		
		//for k values not in set (500)
		for (int k=0; k<250; k++){
			targetVal500[k]=Arr3[generator.nextInt(5000)];
		}
		//for k values not in set (500)
		for (int k=250; k<500; k++){
			targetVal500[k]=getRole3()+1;
		}
		
		for (int u=0; u<500; u++){
			for (int i=0; i<10; i++){
				//for each target k value
				algorA(Arr3,targetVal10[i]);
				ATimeEnd=System.nanoTime(); 
				algorB(quickSort(Arr3,0,Arr3.length-1),targetVal10[i]);
				BTimeEnd=System.nanoTime();
				ADuration[0]+=(ATimeEnd-ATimeStart)/1000;
				BDuration[0]+=(BTimeEnd-BTimeStart)/1000;	
			}
			
			for (int i=0; i<500; i++){
				//for each target k value
				algorA(Arr3,targetVal500[i]);
				ATimeEnd=System.nanoTime(); 
				algorB(quickSort(Arr3,0,Arr3.length-1),targetVal500[i]);
				BTimeEnd=System.nanoTime();
				ADuration[1]+=(ATimeEnd-ATimeStart)/1000;
				BDuration[1]+=(BTimeEnd-BTimeStart)/1000;	
			}
		}
		System.out.println("10" + "     " + ADuration[0]/500+" seconds" + "       " + BDuration[0]/500+" seconds");
		System.out.println("500" + "     " + ADuration[1]/500+" seconds" + "       " + BDuration[1]/500+" seconds");
		
		//for n=10000
		System.out.println("\n");
		System.out.println("Results for n = 10000");
		System.out.println("k	 Algorithm A	Algorithm B");
		//For k target values in set
		for (int k=0; k<5; k++){
			targetVal10[k]=Arr4[generator.nextInt(5000)];
		}
		//for k values not in set
		for (int k=5; k<10; k++){
			targetVal10[k]=getRole4()+1;
		}
		
		//for k values not in set (500)
		for (int k=0; k<250; k++){
			targetVal500[k]=Arr4[generator.nextInt(5000)];
		}
		//for k values not in set (500)
		for (int k=250; k<500; k++){
			targetVal500[k]=getRole4()+1;
		}
		
		for (int u=0; u<500; u++){
			for (int i=0; i<10; i++){
				//for each target k value
				algorA(Arr4,targetVal10[i]);
				ATimeEnd=System.nanoTime(); 
				algorB(quickSort(Arr4,0,Arr4.length-1),targetVal10[i]);
				BTimeEnd=System.nanoTime();
				ADuration[0]+=(ATimeEnd-ATimeStart)/1000;
				BDuration[0]+=(BTimeEnd-BTimeStart)/1000;
				
			}
			
			for (int i=0; i<500; i++){
				//for each target k value
				algorA(Arr4,targetVal500[i]);
				ATimeEnd=System.nanoTime(); 
				algorB(quickSort(Arr4,0,Arr4.length-1),targetVal500[i]);
				BTimeEnd=System.nanoTime();
				ADuration[1]+=(ATimeEnd-ATimeStart)/1000;
				BDuration[1]+=(BTimeEnd-BTimeStart)/1000;
				
			}
		}
		System.out.println("10" + "     " + ADuration[0]/500+" seconds" + "       " + BDuration[0]/500+" seconds");
		System.out.println("500" + "     " + ADuration[1]/500+" seconds" + "       " + BDuration[1]/500+" seconds");
		*/
		}//end main
	
	
	public static int []quickSort(int[] arr, int low, int high) {
		
		 
		if (arr != null && arr.length != 0 && low < high){
			// pick the pivot
			int middle = low + (high - low) / 2;
			int pivot = arr[middle];
 
			// make left < pivot and right > pivot
			int i = low, j = high;
			while (i <= j) {
				while (arr[i] < pivot) {
					i++;
				}
	 
				while (arr[j] > pivot) {
					j--;
				}
	 
				if (i <= j) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					i++;
					j--;
				}
			}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j);
 
		if (high > i)
			quickSort(arr, i, high);
		}//end if
		return arr;
	}//end quicksort
	
	
	
}//end Assign1 class
