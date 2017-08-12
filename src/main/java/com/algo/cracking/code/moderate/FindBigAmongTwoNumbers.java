package com.algo.cracking.code.moderate;

import java.util.BitSet;

import org.junit.Assert;
import org.junit.Test;

/** find big among two numbers without using comparing operator ***/
public class FindBigAmongTwoNumbers {

	public int findBigFrom_v1(int x,int y){
		int[] arr = {x,y};
		return arr[(x-y) >>>31 ];
		
	}

	public int findBigFrom_v2(int x,int y){
		//( (x-y) >>>31 ): This is most significant bit. So indicate sign bit. If X is bigger this MSB will be 0.
		return x -  ( (x-y) >>>31 ) * ( x-y) ;
	}
	@Test
	public void test_happypath_1(){
		Assert.assertEquals(4, findBigFrom_v1(4,3));
		Assert.assertEquals(4, findBigFrom_v2(4,3));
	}

	@Test
	public void test_happypath_2(){
		Assert.assertEquals(4, findBigFrom_v1(2,4));
		Assert.assertEquals(4, findBigFrom_v2(2,4));
	}
	@Test
	public void test_happypath_3(){
		Assert.assertEquals(4, findBigFrom_v1(4,4));
		Assert.assertEquals(4, findBigFrom_v2(4,4));
	}
}
