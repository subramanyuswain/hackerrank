package com.learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

//Read input
//Write Sorting Algorithm to return output in Descending order

public class KLargestElements {
	
	public static void main(String[] args) throws IOException {
		Input input = new Input();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputs = br.readLine();
	}

}

class Input{
	int numberOfTestCases;
	List<TestCases> testCases;
	public int getNumberOfTestCases() {
		return numberOfTestCases;
	}
	public void setNumberOfTestCases(int numberOfTestCases) {
		this.numberOfTestCases = numberOfTestCases;
	}
	public List<TestCases> getTestCases() {
		return testCases;
	}
	public void setTestCases(List<TestCases> testCases) {
		this.testCases = testCases;
	}
	@Override
	public String toString() {
		return "Input [numberOfTestCases=" + numberOfTestCases + ", testCases=" + testCases + "]";
	}	 
}

class TestCases{
	int noOfInputs;
	int noOfOutputs;
	List<Integer> dataList;
	public int getNoOfInputs() {
		return noOfInputs;
	}
	public void setNoOfInputs(int noOfInputs) {
		this.noOfInputs = noOfInputs;
	}
	public int getNoOfOutputs() {
		return noOfOutputs;
	}
	public void setNoOfOutputs(int noOfOutputs) {
		this.noOfOutputs = noOfOutputs;
	}
	public List<Integer> getDataList() {
		return dataList;
	}
	public void setDataList(List<Integer> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "TestCases [noOfInputs=" + noOfInputs + ", noOfOutputs=" + noOfOutputs + ", dataList=" + dataList + "]";
	}
	
}

