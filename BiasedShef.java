package com.learn.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiasedShef {
	
	public static void main(String args[]){
		Customer customer = new Customer();
		Map<String, Object> data = new HashMap<String, Object>();
		
		List<Customer> customerList = new ArrayList<Customer>();
		customer = readInput(Constants.NUMBER_OF_CUSTOMERS);
		data.put(Constants.NUMBER_OF_CUSTOMERS, customer.getNumberOfCustomers());
		int noOfCustomers = customer.getNumberOfCustomers();
		System.out.println("Please enter customer params for "+customer.getNumberOfCustomers()+" customers separated by space as follows");
		System.out.println("0 3 7 1");
		System.out.println("The first param being costomer arrival time");
		System.out.println("The second param is the order preparation time");
		System.out.println("The third param is the time for which costomer is ready to wait for the order");
		System.out.println("The forth param shows if he/she is a preffered customer");
		for(int i = 0;i<noOfCustomers;i++){
			if(i==0){
				System.out.println("Please enter the first set of params separated by space");
				customerList.add(readInput(Constants.CUSTOMER_EVALUATION_PARAMETERS));
			}
			else if(0<i && i<(noOfCustomers-1)){
				System.out.println("Please enter the "+(i+1)+"th set of params separated by space");
				customerList.add(readInput(Constants.CUSTOMER_EVALUATION_PARAMETERS));
			}
			else if(i==noOfCustomers-1){
				System.out.println("Please enter the last set of params separated by space");
				customerList.add(readInput(Constants.CUSTOMER_EVALUATION_PARAMETERS));
			}
			else{
				System.out.println("Please do not enter more than "+noOfCustomers+" params, Please try again");
			}
		}
		data.put(Constants.CUSTOMER_EVALUATION_PARAMETERS, customerList);
		
		System.out.println("Number of preffered Customers served : "+numberOfPrefferedCustomersServed(customerList));
		System.out.println("Number of unPreffered Customers served : "+numberOfUNPrefferedCustomersServed(customerList));
		
	}
	
	public static int numberOfPrefferedCustomersServed(List<Customer> customerList){
		List<Customer> prefferedCustomerList = new ArrayList<Customer>();
		int previousOrderPreparationTime = 0;
		int currentOrderPreparationTime = 0;
		int currentArrivalTime = 0;
		int currentWaitingTime = 0;
		int count = 1;
		for(Customer c: customerList){
			if(c.getPreferredCustomer() == 1 ){
				if(count == 1){
					
					if(c.getProcessingTime()<= c.getReadyToWaitTime()){
						count = count+1;
						previousOrderPreparationTime = c.getProcessingTime();
						prefferedCustomerList.add(c);
					}
				}
				else if(count != 1){
					currentOrderPreparationTime = c.getProcessingTime();
					currentWaitingTime = c.getReadyToWaitTime();
					currentArrivalTime = c.getArrivalTime();
					if ((currentArrivalTime + currentOrderPreparationTime
							- previousOrderPreparationTime) > currentWaitingTime) {
						previousOrderPreparationTime = c.getProcessingTime();
						prefferedCustomerList.add(c);
					}
				}
			}	
		}
		return prefferedCustomerList.size();
	}
	public static int numberOfUNPrefferedCustomersServed(List<Customer> customerList){
		List<Customer> unPrefferedCustomerList = new ArrayList<Customer>();
		int previousOrderPreparationTime = 0;
		int currentOrderPreparationTime = 0;
		int currentArrivalTime = 0;
		int currentWaitingTime = 0;
		int count = 1;
		for(Customer c: customerList){
			if(c.getPreferredCustomer() == 0 ){
				if(count == 1){
					
					if(c.getProcessingTime()<= c.getReadyToWaitTime()){
						unPrefferedCustomerList.add(c);
						count = count+1;
						previousOrderPreparationTime = c.getProcessingTime();
					}
				}
				else if(count != 1){
					currentOrderPreparationTime = c.getProcessingTime();
					currentWaitingTime = c.getReadyToWaitTime();
					currentArrivalTime = c.getArrivalTime();
					if ((currentArrivalTime + currentOrderPreparationTime
							- previousOrderPreparationTime) > currentWaitingTime) {
						previousOrderPreparationTime = c.getProcessingTime();
						unPrefferedCustomerList.add(c);
					}
				}
			}	
		}
		return unPrefferedCustomerList.size();
	}
	
	public static Customer readInput(String inputCaregory){
		Customer customer = new Customer();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line=null;
		switch(inputCaregory){
			case Constants.NUMBER_OF_CUSTOMERS:
			try {
				System.out.println("Please type the number of "+inputCaregory);
				line = br.readLine();
				customer.setNumberOfCustomers(Integer.parseInt(line));
			} catch (IOException e) {
				e.printStackTrace();
			}
				break;
			case Constants.CUSTOMER_EVALUATION_PARAMETERS:
			try {
				
				line = br.readLine();
				String[] values = line.split(" ");
				int[] intValues = new int[values.length];
				for(int i=0;i<values.length;i++){
					intValues[i] = Integer.parseInt(values[i]);
					if (i==0) {
						customer.setArrivalTime(intValues[i]);
					}
					else if(i==1){
						customer.setProcessingTime(intValues[i]);
					}
					else if(i==2){
						customer.setReadyToWaitTime(intValues[i]);
					}
					else if(i==3){
						customer.setPreferredCustomer(intValues[i]);
					}
					else{
						System.out.println("Invalid input");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
				
				break;
		}
			
		
		return customer;
	}

	
}

interface Constants{
	public static String NUMBER_OF_CUSTOMERS = "customers";
	public static String CUSTOMER_EVALUATION_PARAMETERS = "customerEvluationParameters";
}
class Customer{
	private int numberOfCustomers;
	private int arrivalTime;
	private int processingTime;
	private int readyToWaitTime;
	private int preferredCustomer;
	Customer(){
		
	}
	
	public void setNumberOfCustomers(int numberOfCustomers) {
		this.numberOfCustomers = numberOfCustomers;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}
	public int getReadyToWaitTime() {
		return readyToWaitTime;
	}
	public void setReadyToWaitTime(int readyToWaitTime) {
		this.readyToWaitTime = readyToWaitTime;
	}
	public int getPreferredCustomer() {
		return preferredCustomer;
	}
	public void setPreferredCustomer(int preferredCustomer) {
		this.preferredCustomer = preferredCustomer;
	}
	public  int getNumberOfCustomers() {
		return this.numberOfCustomers;
	}
	
	public String toString(){
		return 	"arrivalTime:"+arrivalTime+
				", processingTime:"+processingTime+
				", readyToWaitTime:"+readyToWaitTime+
				", preferredCustomer:"+preferredCustomer;
	}
	
}
