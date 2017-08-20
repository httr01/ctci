package com.algo.cracking.code.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class LetterNumberArray {
/***Given an array with letter and numbers. Fins longest subarray which have equal letter and numbers.
 * Example    4,6,7,A,V,3,S,0,7,8
	Answer  : A,V,3,S,0,7  or  6,7,A,V,3,S 
	
**/
	/**Return start and end index of the largest sub array **/
	public int[] findLargestSubArrayWithEqualLettersAnbdNumbers( char[] myarr) {
		
		Map<Integer, ArrayList<Integer>> mapDiffToIndexesMap = 	new HashMap<Integer, ArrayList<Integer>>();
		int[] diffArr = new int[myarr.length];
		int  currentDiff = 0; // diff on # of char and # number till not seen in given array
		int countDigit=0;//  count number of digit
		int countChar = 0;// count how many char	
		int myarrIndex = 0; //  index for given array
		for (char c: myarr) {
			if (Character.isDigit(c)) ++ countDigit; else ++ countChar;
			currentDiff = Math.abs(countDigit-countChar); 
			diffArr[myarrIndex]= currentDiff;
			if (mapDiffToIndexesMap.get(currentDiff) ==  null ) {
				ArrayList<Integer> diffToIndexList =  new ArrayList<Integer>();
				diffToIndexList.add( myarrIndex);
				mapDiffToIndexesMap.put(currentDiff, diffToIndexList );
			}else {
				// add in existing ArrayList
				mapDiffToIndexesMap.get(currentDiff).add( myarrIndex);
			}
			++myarrIndex;
		}
		// now loop via diffArr
		int index = 0;
		int largestSubstrSize = -1;
		int[] substrIndex =  new int[2];	//  index of largest sub array will be stored here
		for (int diff: diffArr) {
			if (mapDiffToIndexesMap.get(diff) != null) {
				ArrayList<Integer> diffToIndexList = mapDiffToIndexesMap.get(diff);
				int maxIndexTillEqualCharAndNumber = diffToIndexList.get(diffToIndexList.size()-1) ; // get max index
				if (maxIndexTillEqualCharAndNumber > index  && maxIndexTillEqualCharAndNumber - index > largestSubstrSize ) {
					largestSubstrSize = maxIndexTillEqualCharAndNumber - index;
					substrIndex[0]= index;
					substrIndex[1]= maxIndexTillEqualCharAndNumber-1;
				}

			}
			++ index;
		}
		return substrIndex;
	}
	
	@Test
	public void test_happyPath_1(){
		char[] myarr = {'5','3','6','A','L','2','B','C','8'};
		int[] expectedIndex ={0,7};
		Assert.assertArrayEquals(expectedIndex, findLargestSubArrayWithEqualLettersAnbdNumbers(myarr));	
	}
	
	
	@Test
	public void test_happyPath_2(){
		char[] myarr = {'5','3','6','A','L','2','B','C','B'};
		int[] expectedIndex = {0,7};
		Assert.assertArrayEquals(expectedIndex, findLargestSubArrayWithEqualLettersAnbdNumbers(myarr));	
	}

	@Test
	public void test_happyPath_3(){
		char[] myarr = {'5','3','6','A','L','2','B','C','B'};
		int[] expectedIndex = {0,7};
		Assert.assertArrayEquals(expectedIndex, findLargestSubArrayWithEqualLettersAnbdNumbers(myarr));	
	}
	
}

