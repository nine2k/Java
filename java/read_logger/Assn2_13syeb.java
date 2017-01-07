/*
 * Emily Bao
 * This program reads the "Logger.csv" file which consists of 1000 sets of eight numbers.
 * It would create a report both to the console and a file called "out.txt"
 * The report would indicate the average current and time of operation for each motor that runs:
 * normally (0.01-8.0 amps)
 * not normally (above 8.0 amps) 
 * 
 * The report would also indicate if a motor did not run at all over the entire logged interval
 * Current data is displayed to a limit of 3 digits after the decimal place.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assn2_13syeb {
	//data is the parameter passed to the method "PrintToFile" which prints to out.txt
	public static String data;
	//creates an array to store the logger.csv data (stores everything except the time)
	double [][]anArray = new double [1000][7];
	

	public static void main(String[]args){	
		Assn2_13syeb obj = new Assn2_13syeb();
		obj.run(); //calls the run method
		obj.PassMotor(); //calls the PassMotor method
	}//end main
	
	
	//This function splits and stores the data from logger.csv into an array
	public void run() {

		String csvFile = "Logger.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ","; 
		try {

			br = new BufferedReader(new FileReader(csvFile));
		
			int count = 0;
			while (count <1000) {

				line = br.readLine();
				//splits each line by comma
				String[] current = line.split(cvsSplitBy);

				for (int i=0;i<7;i++){
					anArray[count][i]=Double.parseDouble(current[i+1]);
				}

				count++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//This method writes output to out.txt
	public void PrintToFile(String data){
        try{

    		File f = new File("out.txt");
    		
    		//if file does not exist, then create it
    		if(!f.exists()){
    			f.createNewFile();
    		}
    		
    		//true = append file
    		FileWriter fileWritter = new FileWriter(f.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.close();
    	        
	        
    	}catch(IOException e){
    		e.printStackTrace();
    	}
       
	}

	//Passes each motors (each col of the array) to the avg method
	public void PassMotor(){

		int motor1=0;
		int motor2=1;
		int motor3=2;
		int motor4=3;
		int motor5=4;
		int motor6=5;
		int motor7=6;
		
		System.out.println("Motor Use Summary:");
		PrintToFile("Motor Use Summary:\n");
		System.out.println("Motor 1: ");
		PrintToFile("Motor 1: ");
		avg(motor1);
		System.out.println("\nMotor 2: ");
		PrintToFile("\nMotor 2: ");
		avg(motor2);
		System.out.println("\nMotor 3: ");
		PrintToFile("\nMotor 3: ");
		avg(motor3);
		System.out.println("\nMotor 4: ");
		PrintToFile("\nMotor 4: ");
		avg(motor4);
		System.out.println("\nMotor 5: ");
		PrintToFile("\nMotor 5: ");
		avg(motor5);
		System.out.println("\nMotor 6: ");
		PrintToFile("\nMotor 6: ");
		avg(motor6);
		System.out.println("\nMotor 7: ");
		PrintToFile("\nMotor 7: ");
		avg(motor7);

	}
/*
 *  This method checks for motor currents from (0.01-8.0 amps), (above 8.0 amps) 
 *  and if a motor did not run at all over the entire logged interval
 *  
 *  It then calculates the average current and time of operation for each motor
 *  from (0.01-8.0 amps)
 *  and from (above 8.0 amps) 
 */
	public void avg (int motor){
		double total = 0;
		double total8 = 0;
		boolean flag = false;
		boolean flag1 = false;
		boolean start = true;
		int count = 0;
		int count8 = 0;
		int startVal;
		int endVal;
		double average;
		boolean run = false;
		boolean run1 = false;
		boolean run2 = false;

		
		if (start==true){
			for (int i=0;i<1000;i++){

			
				if (anArray[i][motor]>=0.01 && anArray[i][motor]<8.0){
					run=true;
					flag = true;
					count++;
					total=total+anArray[i][motor];
		
				} //end if
				
				else if (anArray[i][motor]<0.01){
					run1=true;
					
					//check for case larger than 0.01 and smaller than 8.0
					if (flag==true){

						average=(total/count);
						startVal=i-count;
						endVal=i-1;
						double newAvg = (double)Math.round(average * 1000d) / 1000d;//rounds average to 3 decimal places to pass to method "PrintToFile"
						
						System.out.printf("%.3f" + " amps, starting at " + startVal + " seconds, to " + endVal + " seconds\n", average);
						PrintToFile(newAvg + " amps, starting at " + startVal + " seconds, to " + endVal + " seconds \n");
						
						count=0;
						total=0;
						average=0.0;
						startVal=0;
						endVal=0;
						
						flag=false;
					}

					//check for case larger than 8.0
					if (flag1==true){
						
						average=(total8/count8);
						startVal=i-count8;
						endVal=i-1;
						double newAvg1 = (double)Math.round(average * 1000d) / 1000d;
						System.out.printf("***Current Exceeded!: "+ "%.3f" + " amps, starting at " + startVal + " seconds, to " + endVal + " seconds\n",average);
						PrintToFile("***Current Exceeded!: " + newAvg1 + " amps, starting at " + startVal + " seconds, to " + endVal + " seconds \n");
						count8=0;
						total=0;
						average=0.0;
						startVal=0;
						endVal=0;
						flag1=false;
					}
					
				}//end else if

				else if (anArray[i][motor]>8.0){
					run2=true;
					flag1 = true;
					count8++;

					total8=total8+anArray[i][motor];
					
					
				}//end else if

			} //end for
			
			//checks if motor did not go 2 of the above cases (larger than 0.01 and smaller than 8.0, and above 8.0)
			if (run!=true && run1==true && run2!=true){
				System.out.println("***Motor did not run at all over the entire logged interval!***");
				PrintToFile("***Motor did not run at all over the entire logged interval!***");
			}//end else if 
	}
	
	}//end avg method

}//end class
