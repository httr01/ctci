package com.algo.cracking.code.moderate;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

 

public class SmallestDiffPair {
	
/** Given two int array, find pair of int( each from different array)  with smallest difference **/
	
	public int[] findPairWithSmallestDifference(int[] arr1 , int[] arr2 ) {
		int[] sortedArr1 = sort(arr1);
		int[] sortedArr2 = sort(arr2);
		int minDiff = Integer.MAX_VALUE;
		int[]  minDiffValues =  new int[2];
		int index2 =0;
		for (int index1=0 ;index1< sortedArr1.length; ++index1) {
			if (index2 == sortedArr2.length-1) index2=0;
			int tempSelectedArr1Value = -1;
			int tempSelectedArr2Value = -1;
			while (index2<sortedArr2.length && sortedArr1[index1] >sortedArr2[index2]) ++index2; 
			if (index2 == sortedArr2.length) --index2;
			int tempMindiff = Math.abs(sortedArr1[index1] -sortedArr2[index2]);
			tempSelectedArr1Value = sortedArr1[index1];
			tempSelectedArr2Value =sortedArr2[index2];
			if (index2 > 0  && Math.abs(sortedArr1[index1] -sortedArr2[index2-1])<tempMindiff )  {
				 tempMindiff =Math.abs( sortedArr1[index1] - sortedArr2[index2-1]);
				 tempSelectedArr2Value =sortedArr2[index2-1];
			}			
			if (tempMindiff< minDiff) {
				minDiff = tempMindiff;
				minDiffValues[0] = tempSelectedArr1Value;
				minDiffValues[1] = tempSelectedArr2Value;
			}

		} 
		return minDiffValues;

	} 

	public int[] sort (int[] arr) {
		 
		Arrays.sort(arr);
		return arr; 
	}
	
	
	@Test
	public void test_happyPath1(){
		int[]  arr1 = {7,12,4,19,120};
		int[]  arr2 = {51,9,8,100, 23};
		int[]  resultArr = findPairWithSmallestDifference(arr1,arr2);
		Assert.assertEquals(7, resultArr[0]);
		Assert.assertEquals(8, resultArr[1]);
	}
	
	
	@Test
	public void test_happyPath2(){
		int[]  arr1 = {71,12,4,19,120};
		int[]  arr2 = {51,9,8,100, 23};
		int[]  resultArr = findPairWithSmallestDifference(arr1,arr2);
		Assert.assertEquals(12, resultArr[0]);
		Assert.assertEquals(9, resultArr[1]);
	}
}
