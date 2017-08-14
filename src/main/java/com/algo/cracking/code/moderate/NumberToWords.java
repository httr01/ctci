package com.algo.cracking.code.moderate;

import org.junit.Assert;
import org.junit.Test;

public class NumberToWords {


String [] tensNames = { "ten " , "twenty " , "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninety "};
double[] zeros = {Math.pow(10,3) , Math.pow(10,6) , Math.pow(10,9), Math.pow(10,12),Math.pow(10,15)}; 

/** 
 * We are not looking into singular and plural in this algo.
 * We are not using "and"  at this time. Example 978 : nine hundred and seventy six. 
 * Instead we will say :978 : nine hundred seventy six. 
 * 
 * **/
public String toWords(int value) {
	int[]  denominations = new int[5];
	String[]  denominationsNames ={"thousands " , "millions " , "billions " , "trillions " , "quadrillion" };
	    int currentValue= value;
	    StringBuilder sb =  new 	StringBuilder();
	    int i = 5;
	    while (i >= 1 ){
			denominations[i-1] = (int) (currentValue/ zeros[i-1]);
			currentValue -=   denominations[i-1]  *  zeros[i-1];
			if (denominations[i-1] > 0 ) sb .append(threeDigitsToWords(denominations[i-1])) .append( denominationsNames[i-1] );
			--i;
	    }
	    if (currentValue > 0)  sb.append(threeDigitsToWords(currentValue));
  return sb.toString();
}


/** three digit integer to string value **/
private String threeDigitsToWords(int val ) {
	int cVal = val;
	StringBuffer sb =  new StringBuffer();
	if (val > 99)    { 
		sb.append( twentyOrLessToString(  cVal / 100   ) ) .append("hundred ");
		cVal -= 100 *  (cVal/100 );
	}	
	if (cVal < 20) {

	  sb.append( twentyOrLessToString(  cVal   ) ) ;
	} else {
		// how many tens
		 sb.append( tensNames[ cVal  /10   -1 ] );
		// how many units
		sb.append(twentyOrLessToString ( cVal % 10)  );
	}
	return sb.toString();
}

private String twentyOrLessToString (int v){
	String[] values = { "one " , "two ", "three ", "four ", "five ", "six " , "seven ", "eight ", "nine ", "ten ", "eleven ", "twelve ", "thrteen", "forteen", "fifteen ", "sixteen " , "seventeen ", "eignteen ", "nineteen "};
	return  values[v-1];
}

@Test
public   void test_happycase1() {
	Assert.assertEquals( "four thousands one hundred twenty three", toWords (4123).trim());
	
}
@Test
public   void test_happycase2() {
	Assert.assertEquals( "eighty four thousands one hundred twenty three", toWords (84123).trim());
	
}

@Test
public   void test_happycase3() {
	Assert.assertEquals( "eight hundred eighty four thousands one hundred twenty three", toWords (884123).trim());
}
@Test
public   void test_happycase4() {
	Assert.assertEquals( "six millions eight hundred eighty four thousands one hundred twenty three", toWords (6884123).trim());
}

@Test
public   void test_happycase5() {
	Assert.assertEquals( "nine hundred ninety six millions eight hundred eighty four thousands one hundred twenty three", toWords (996884123).trim());
}


@Test
public   void test_happycase6() {
	Assert.assertEquals( "one billions nine hundred ninety six millions eight hundred eighty four thousands one hundred twenty three", toWords (1996884123).trim());
}

}
