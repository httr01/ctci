package com.algo.cracking.code.moderate;

import org.junit.Assert;
import org.junit.Test;

public class MaxSumSubArray {
	/***Given an array of int, find sub array which has max sum of sequence of integers.
	 * Here we are returning index of  such sub array.
	 * Example 
	 * {-6,3,-2,4,-3,-10};	
	 * response index array = {1,3}  because sum of  sub array 3.-2,4 is biggest.
	 */
	public int[] findMaxSumSubArray(int[] valueArr){
		if (valueArr.length ==0 ) return null;
		int maxSum= valueArr[0]-1;
		int maxSumStartIndex =-1;
		int maxSumEndIndex   = -1;
		int index = 0;
		int currSum = -1;
		int currSumStartIndex =-1;
		int currSumEndIndex   =-1;
		boolean startNewSeq = true;
		while (index <valueArr.length) {
			 int newSum = currSum + valueArr [index];
			 if (startNewSeq || 
	  			 (newSum < 0 && newSum > currSum)
	               ) {
			    startNewSeq =  false;
			    currSum = valueArr[index];
			    currSumStartIndex =index;
			    currSumEndIndex   =index;
			 }else {
				
				if (newSum < 0 ) {
					currSumEndIndex = index -1; // We can put additional check here that this could never < 0 
					startNewSeq =  true;

				}else {
					currSum = newSum;
					currSumEndIndex = index ;
				}
			}
			if (currSum > maxSum){
				maxSum = currSum;
				maxSumStartIndex = currSumStartIndex;
				maxSumEndIndex = currSumEndIndex;
			}
	  	++index;
	    }
		//return the indexes
		int[]  indexArr  = {maxSumStartIndex, maxSumEndIndex};
		return indexArr;
	} 
	
	@Test
	public void test_happyPath1 (){
		int[] dataArr = {6,3,-2,4,-3,-10};	
		int[] maxIndexArr = {0,3};
		Assert.assertArrayEquals(maxIndexArr, findMaxSumSubArray(dataArr));
	}
	
	@Test
	public void test_happyPath2(){
		int[] dataArr = {-6,3,-2,4,-3,-10};	
		int[] maxIndexArr = {1,3};
		Assert.assertArrayEquals(maxIndexArr, findMaxSumSubArray(dataArr));
	}	
	
	@Test
	public void test_happyPath3(){
		int[] dataArr = {-6,1,-2,4,-3,-10};	
		int[] maxIndexArr = {3,3};
		Assert.assertArrayEquals(maxIndexArr, findMaxSumSubArray(dataArr));
	}	
	
	@Test
	public void test_boundryCase_oneArrayElementNegative(){
		int[] dataArr = {-6 };	
		int[] maxIndexArr = {0,0};
		Assert.assertArrayEquals(maxIndexArr, findMaxSumSubArray(dataArr));
	}	
	
	@Test
	public void test_boundryCase_oneArrayElementPositive(){
		int[] dataArr = {6 };	
		int[] maxIndexArr = {0,0};
		Assert.assertArrayEquals(maxIndexArr, findMaxSumSubArray(dataArr));
	}	
}
