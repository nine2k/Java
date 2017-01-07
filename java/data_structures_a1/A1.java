import java.io.IOException;
import java.util.Random;
/**
 * @author Emily Bao
 */

public class A1 {
	public static long ATimeStart;
	public static long ATimeEnd;
	public static long BTimeStart;
	public static long BTimeEnd;
	public static double [] ADuration = new double [6];
	public static double [] BDuration = new double [6];
	public static Random generator = new Random(System.currentTimeMillis());
	
	public static String algorA(int []arr, int x){
		
		for(int item: arr) {
      	   if (item==x)		 
      		 return "Yes";	 
      	   }
		return "No";
	}//end algorithm A
	
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
	
	public static int []quickSort(int[] arr, int low, int high) { 
		if (arr != null && arr.length != 0 && low < high){
			// pick the pivot
			int middle = low + (high - low) / 2;
			int pivot = arr[middle];
 
			// make left < pivot and right > pivot
			int i = low, j = high;
			while (i <= j) {
				while (arr[i] < pivot) 
					i++;		 
				while (arr[j] > pivot) 
					j--;
				if (i <= j) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					i++;
					j--;
				}
			}

		if (low < j)
			quickSort(arr, low, j);
 
		if (high > i)
			quickSort(arr, i, high);
		}//end if
		return arr;
	}
	
	public static int getRole(int upperBound){
		int num;
		num=generator.nextInt(upperBound);
		if (num%2 !=0){
			num=num+1;
		}
		return num;	
	}
	
	public static int [] arrSet(int [] arr, int arrSize, int upperBound){
		for (int i=0;i<arrSize;i++){
			arr[i]=getRole(upperBound);
		}
		return arr;
	}
	
	
	public static int [] targetVal (int [] tarVal, int[] arr, int TarArrSize,int upperBound){
		//For k target values in set
		for (int i=0; i<TarArrSize/2; i++){
			tarVal[i]=arr[generator.nextInt(upperBound/10)];
		}
		//for k values not in set
		for (int i=TarArrSize/2;i<TarArrSize;i++){
			tarVal[i]=getRole(upperBound-1)+1;
		}
		return tarVal;
	}

	
	public static void printMethod (int num, int kSize){
		System.out.printf("%d      %.3f seconds       %.3f seconds\n",kSize, ADuration[num],BDuration[num]);
	}
	
	public static double search500TimesB(int [] tarVal, int []arr, int num){
		double result=0;
		for (int i=0; i<500; i++){
			result+= searchBDuration(tarVal, arr, num);
		}
		
		result = result/500;
		return result;
	}
	
	public static double searchBDuration (int [] tarVal, int []arr, int num){
		arr=quickSort(arr,0,arr.length-1);
		for (int i=0;i<tarVal.length;i++){
			BTimeStart=System.nanoTime();	
			algorB(arr,tarVal[i]);
			BTimeEnd=System.nanoTime();
			BDuration[num]+=(double)(BTimeEnd-BTimeStart)/1000000000.0; //nanoseconds to seconds
		}
		
		return BDuration[num];
	}
	
	public static double search500TimesA(int [] tarVal, int []unsorted, int num){
		double result=0;
		for (int i=0; i<500; i++){
			result+= searchADuration(tarVal, unsorted, num);
		}
		result = result/500;
		return result;
	}
	
	public static double searchADuration (int [] tarVal, int []unsorted, int num){

		for (int i=0;i<tarVal.length;i++){
			ATimeStart=System.nanoTime();
			algorA(unsorted,tarVal[i]);
			ATimeEnd=System.nanoTime();
			ADuration[num]+=(double)((ATimeEnd-ATimeStart)/1000000000.0); //nanoseconds to seconds
		}
		return ADuration[num];
	}
	public static void main(String[]args){
		//Array for set
		int [] Arr1 = new int[1000];
		int [] Arr2 = new int[2000];
		int [] Arr3 = new int[5000];
		int [] Arr4 = new int[10000];
		//Array for target values
		int [] targetVal10 = new int[10];
		int [] targetVal100 = new int[100];
		int [] targetVal500= new int[500];
		int [] targetVal1000 = new int[1000];
		int [] targetVal1500 = new int[1500];
		int [] targetVal2000 = new int[2000];
		//Array for time

		
		Arr1= arrSet(Arr1, 1000, 10000);
		Arr2=arrSet(Arr2, 2000, 20000);
		Arr3=arrSet(Arr3, 5000, 50000);
		Arr4=arrSet(Arr4, 10000,100000);
		
		
		//For n=1000
		System.out.println("Results for n = 1000");
		System.out.println("k	 Algorithm A	    Algorithm B");
		
		targetVal10=targetVal(targetVal10,Arr1,10,10000);
		targetVal100=targetVal(targetVal100, Arr1, 100, 10000);
		targetVal500=targetVal(targetVal500, Arr1, 500,10000);
		targetVal1000=targetVal(targetVal1000, Arr1, 1000,10000);
		targetVal1500=targetVal(targetVal1500, Arr1, 1500,10000);
		targetVal2000=targetVal(targetVal2000, Arr1, 2000,10000);
		
		//Makes an unsorted copy of Array 1 (n=1000)
		int [] Arr1Unsorted = new int[1000];
		for (int i=0; i<1000; i++){
			Arr1Unsorted[i]=Arr1[i];
		}
		
		ADuration[0]=search500TimesA(targetVal10,Arr1Unsorted,0);
		BDuration[0]=search500TimesB(targetVal10,Arr1,0);
		
		printMethod(0,10);
		
		ADuration[1]=search500TimesA(targetVal100,Arr1Unsorted,1);
		BDuration[1]=search500TimesB(targetVal100,Arr1,1);
		
		printMethod(1,100);

		ADuration[2]=search500TimesA(targetVal500,Arr1Unsorted,2);
		BDuration[2]=search500TimesB(targetVal500,Arr1,2);
		
		printMethod(2,500);
		
		ADuration[3]=search500TimesA(targetVal1000,Arr1Unsorted,3);
		BDuration[3]=search500TimesB(targetVal1000,Arr1,3);
		
		printMethod(3,1000);
		
		//For n=2000
		System.out.println("Results for n = 2000");
		System.out.println("k	 Algorithm A	Algorithm B");
		
		targetVal10=targetVal(targetVal10,Arr2,10,20000);
		targetVal100=targetVal(targetVal100, Arr2, 100, 20000);
		targetVal500=targetVal(targetVal500, Arr2, 500,20000);
		targetVal1000=targetVal(targetVal1000, Arr2, 1000,20000);
		targetVal1500=targetVal(targetVal1500, Arr2, 1500,20000);
		targetVal2000=targetVal(targetVal2000, Arr2, 2000,20000);
		
		//Makes an unsorted copy of Array 1 (n=1000)
		int [] Arr2Unsorted = new int[2000];
		for (int i=0; i<2000; i++){
			Arr2Unsorted[i]=Arr2[i];
		}
		
		ADuration[0]=search500TimesA(targetVal10,Arr2Unsorted,0);
		BDuration[0]=search500TimesB(targetVal10,Arr2,0);	
		printMethod(0,10);
		
		ADuration[1]=search500TimesA(targetVal100,Arr2Unsorted,1);
		BDuration[1]=search500TimesB(targetVal100,Arr2,1);
		printMethod(1,100);

		ADuration[2]=search500TimesA(targetVal500,Arr2Unsorted,2);
		BDuration[2]=search500TimesB(targetVal500,Arr2,2);
		printMethod(2,500);
		
		ADuration[3]=search500TimesA(targetVal1000,Arr2Unsorted,3);
		BDuration[3]=search500TimesB(targetVal1000,Arr2,3);
		
		printMethod(3,1000);
		
		
		//For n=5000
		System.out.println("Results for n = 5000");
		System.out.println("k	 Algorithm A	Algorithm B");
		
		targetVal10=targetVal(targetVal10,Arr3,10,50000);
		targetVal100=targetVal(targetVal100, Arr3, 100, 50000);
		targetVal500=targetVal(targetVal500, Arr3, 500,50000);
		targetVal1000=targetVal(targetVal1000, Arr3, 1000,50000);
		targetVal1500=targetVal(targetVal1500, Arr3, 1500,50000);
		targetVal2000=targetVal(targetVal2000, Arr3, 2000,50000);
		
		//Makes an unsorted copy of Array 1 (n=1000)
		int [] Arr3Unsorted = new int[5000];
		for (int i=0; i<5000; i++){
			Arr3Unsorted[i]=Arr3[i];
		}
		
		ADuration[0]=search500TimesA(targetVal10,Arr3Unsorted,0);
		BDuration[0]=search500TimesB(targetVal10,Arr3,0);	
		printMethod(0,10);
		
		ADuration[1]=search500TimesA(targetVal100,Arr3Unsorted,1);
		BDuration[1]=search500TimesB(targetVal100,Arr3,1);
		printMethod(1,100);

		ADuration[2]=search500TimesA(targetVal500,Arr3Unsorted,2);
		BDuration[2]=search500TimesB(targetVal500,Arr3,2);
		printMethod(2,500);
		
		ADuration[3]=search500TimesA(targetVal1000,Arr3Unsorted,3);
		BDuration[3]=search500TimesB(targetVal1000,Arr3,3);
		printMethod(3,1000);
		
		//For n=10000
		System.out.println("Results for n = 10000");
		System.out.println("k	 Algorithm A	Algorithm B");
		
		targetVal10=targetVal(targetVal10,Arr4,10,100000);
		targetVal100=targetVal(targetVal100, Arr4, 100, 100000);
		targetVal500=targetVal(targetVal500, Arr4, 500,100000);
		targetVal1000=targetVal(targetVal1000, Arr4, 1000,100000);
		targetVal1500=targetVal(targetVal1500, Arr4, 1500,100000);
		targetVal2000=targetVal(targetVal2000, Arr4, 2000,100000);
		
		//Makes an unsorted copy of Array 1 (n=10000)
		int [] Arr4Unsorted = new int[10000];
		for (int i=0; i<10000; i++){
			Arr4Unsorted[i]=Arr4[i];
		}
		
		ADuration[0]=search500TimesA(targetVal10,Arr4Unsorted,0);
		BDuration[0]=search500TimesB(targetVal10,Arr4,0);	
		printMethod(0,10);
		
		ADuration[1]=search500TimesA(targetVal100,Arr4Unsorted,1);
		BDuration[1]=search500TimesB(targetVal100,Arr4,1);
		printMethod(1,100);

		ADuration[2]=search500TimesA(targetVal500,Arr4Unsorted,2);
		BDuration[2]=search500TimesB(targetVal500,Arr4,2);
		printMethod(2,500);
		
		ADuration[3]=search500TimesA(targetVal1000,Arr4Unsorted,3);
		BDuration[3]=search500TimesB(targetVal1000,Arr4,3);
		printMethod(3,1000);
	}//end main


}//end class
