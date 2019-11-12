package com.learn.hackerrank;

public class MilitaryTimeConversion {
	
	public static final String MIDNIGHT = "12:00:00AM";
	public static final String MIDNIGHT24HR = "00:00:00";
	public static final String COLON = ":";

	public static void main(String[] args) {
		//String input = "07:05:45AM";
		//String expectedOutput = "07:05:45";
		//String input = "07:05:45PM";
		//String expectedOutput = "19:05:45";
		String input = MIDNIGHT;
		String expectedOutput = MIDNIGHT24HR;
		
		
		String output = convertTimeto24HRformat(input);
		System.out.println(output);
		System.out.println("output.equals(expectedOutput) : "+output.equals(expectedOutput));
	}

	private static String convertTimeto24HRformat(String input) {
		String result = null;
		if(null != input) {
			String amOrPm = input.contains("AM")?"AM":"PM";
			String[] timeValues = input.split(":");
			printArray(timeValues);
			int hr = Integer.valueOf(timeValues[0]);
			int min = Integer.valueOf(timeValues[1]);
			int sec = Integer.valueOf(timeValues[2].substring(0, 2));
			System.out.println(hr+":"+min+":"+sec+" "+amOrPm);
			if(null!=amOrPm && amOrPm.contains("AM") && !input.contains(MIDNIGHT)) {
				result=input.replace("AM", "");
			}
			else if(input.contains(MIDNIGHT)){
				result = MIDNIGHT24HR;
			}
			else {
				result = String.valueOf((hr+12))+COLON
						+computeTimeString(min)+COLON
						+computeTimeString(sec);
			}
		}
		
		return result;
	}
	private static String computeTimeString(int time) {
		String result = null;
		if(time/10>=1)
			result=String.valueOf(time);
		else
			result = "0"+time;
		return result;
	}
	public static void printArray(String[] input) {
		for(String x: input)
			System.out.print(x+":");
		System.out.println();
	}
}
