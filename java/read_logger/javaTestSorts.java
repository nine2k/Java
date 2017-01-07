
/* Used to time the operation of selection sort and the built-in Quicksort.
 * Written by Alan McLeod
 */

public class javaTestSorts {

    public static int[] generateRandom(int n, int max) {
        int[] randNum = new int[n];
        for (int i = 0; i < n; i++)
            randNum[i] = (int) (max * Math.random());
        return randNum;
    } // end generateRandom

    public static void swap(int[] A, int pos1, int pos2) {
        int temp = A[pos1];
        A[pos1] = A[pos2];
        A[pos2] = temp;
    } // end swap

    public static void selectionSort(int[] A) {
        int i, j, least;
        for (i = 0; i < A.length; i++) {
            least = i;
            for (j = i + 1; j < A.length; j++)
                if (A[j] < A[least])
                    least = j;
            swap(A, least, i);
        } // end for
    } // end selectionSort

    public static void main(String[] args) {
        
    	long start, finish;
        final int SIZE = 5000;
        int maxNum = 50000;

        int[] randomData = new int[SIZE];
        randomData = generateRandom(SIZE, maxNum);

        start = System.currentTimeMillis();
        selectionSort(randomData);
        finish = System.currentTimeMillis() - start;

        System.out.println("Selection sort took " + finish + " msec. to complete for " +
                SIZE + " data points.");
		
        // Comment out the code above and un-comment the code below to test the quicksort
        // method already built in to Java.  Increase the array size to 50000 for quicksort.
        // (Hint: use the <Ctrl>/ key combination after selecting lines of code to toggle
        // commenting).
//        start = System.currentTimeMillis();
//        Arrays.sort(randomData);
//        finish = System.currentTimeMillis() - start;
//
//        System.out.println("Quicksort took " + finish + " msec. to complete for " +
//                SIZE + " data points.");
		
    } // end main
    
} // end TestSortsExperiment

