package com.algo.cracking.code.moderate;

import org.junit.Assert;
import org.junit.Test;

/** Calculate how many trailing zero in Factorial (N)**/
public class FactorialZero {

	/** We will make factors of each number and  we will count pair of (5,2). Each pair will produce one zero.
	 * In other words :    simplify into factors and  count how many times we see 2 and  5. **/
	public int countZeroInFactorialOf(int n) {
		int howManyTimes_2_inFactors =0;
		int howManyTimes_5_inFactors =0;
		for(int index =1 ; index<=n ;++index ){
			howManyTimes_2_inFactors +=  howManyTimes_factors_ofX(index ,2);
			howManyTimes_5_inFactors +=  howManyTimes_factors_ofX(index ,5);
		}
		if (howManyTimes_5_inFactors < howManyTimes_2_inFactors)  
				return   howManyTimes_5_inFactors ;
		else return  howManyTimes_2_inFactors;
	}
	/**
	 * @param  factorOfX :  We count how many times we see factorOfX in the factor of the number n. 
	 * Improvements : This could be improved by saving (n,factorOfX ) in a hash as key and looking it up before we calculate count.
	 * ***/
	private  int howManyTimes_factors_ofX (int n ,  int factorOfX ) {
		int currentNum = n;
		int count =0;
		while (currentNum>= factorOfX) {
			if (currentNum % factorOfX == 0 ) ++count; //  factor of factorOfX
			currentNum = currentNum/factorOfX;        
		}
		return count;
	}
	
	@Test
	public void test_HappyPath() {
		Assert.assertEquals(1, countZeroInFactorialOf(5));
		Assert.assertEquals(1, countZeroInFactorialOf(6));
		Assert.assertEquals(1, countZeroInFactorialOf(7));
		Assert.assertEquals(1, countZeroInFactorialOf(8));
		Assert.assertEquals(1, countZeroInFactorialOf(9));
		Assert.assertEquals(2, countZeroInFactorialOf(10));
		Assert.assertEquals(3, countZeroInFactorialOf(15));
		Assert.assertEquals(4, countZeroInFactorialOf(20));
	}
	
	@Test
	public void test_BoundaryCases() {
		Assert.assertEquals(0, countZeroInFactorialOf(0));
		Assert.assertEquals(0, countZeroInFactorialOf(-10));
	}
	
}
