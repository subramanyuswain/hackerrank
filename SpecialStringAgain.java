package com.learn.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class SpecialStringAgain {

	public static void main(String[] args) {
		String input = "asasd";
		int size = 5;
		String input1 = "mnonopoo";
		int size1 = 8;
		List<String> subStringArray = getSubStrings(input,size);
		System.out.println("Sub Strings : "+subStringArray.toString());
		List<String> specialStrings = getSpecialStrings(subStringArray);
		System.out.println("Special Strings : "+specialStrings.toString());
	}

	private static List<String> getSpecialStrings(List<String> subStringArray) {
		List<String> result = new ArrayList<>();
		for(String element: subStringArray) {
			if(verifySpecialString(element)) {
				result.add(element);
			}
		}
		return result;
	}

	private static boolean verifySpecialString(String element) {
		boolean result = false;
		if(checkEqualityWithReverse(element)) {
			if(element.length()==1)
				result=true;
			else
				result = checkSpecial(element);
		}
		return result;
	}

	private static boolean checkSpecial(String arg) {
		boolean result = false;
		String[] split = arg.split("");
		String firstLetter = split[0];
		for(String letter: split) {
			if(!letter.equals(firstLetter)) {
				result=false;
				break;
			}
			else {
				result=true;
			}
		}
		if(!result && (arg.length()%2 > 0)) {
			int mid = arg.length()/2;
			for(int i=0; i<arg.length();i++) {
				String letter = split[i];
				if(letter.equals(firstLetter) && i!=mid) {
					result=true;
				}
				else
					result=false;
			}
		}
		return result;
	}

	private static List<String> getSubStrings(String input, int size) {
		int pairSize = size;
		List<String> result = new ArrayList<>();
		while(pairSize>0) {
			int windowSize = pairSize;
			for(int i=0; i< size;i++) {
				int end = windowSize;
				if(end<=size)
					result.add(input.substring(i, windowSize));
				else if(end==size)
					continue;
				windowSize++;
			}
			pairSize--;
		}
		return result;
	}
	
	private static boolean checkEqualityWithReverse(String str) {
		boolean response = false;
		String reverse = "";
		if(str.length()>1) {
			for(int i = str.length() - 1; i >= 0; i--){
	            reverse = reverse + str.charAt(i);
	        }
		}
		else
			reverse=str;
		response = (str.equals(reverse)?true:false);
		return response;
	}
	

}