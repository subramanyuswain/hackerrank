package com.learn.hackerrank;

public class MiniMaxSum {

	public static void main(String[] args) {
		int[] nums = {1,3,5,7,9};
		int[] result = solution(nums);
		System.out.println(result.toString());
	}

	private static int[] solution(int[] nums) {
		int leastSum = 0;
		int mostSum = 0;
		if(nums.length==0)
			return null;
		else {
			int least = nums[0];
			int most = nums[0];
			for(int i: nums) {
				if(i>most)
					most=i;
				if(i<least)
					least=i;
			}
			
			for(int i: nums) {
				//Least sum
				if(i != most) {
					leastSum = leastSum+i;
				}
				if(i != least) {
					mostSum = mostSum+i;
				}
			}
		}
		System.out.println("Least Sum : "+leastSum);
		System.out.println("Most Sum : "+mostSum);
		int[] result= {leastSum, mostSum};
		return result;
	}
	
	

}
