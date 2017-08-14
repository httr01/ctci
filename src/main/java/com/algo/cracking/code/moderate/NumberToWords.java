package com.algo.cracking.code.moderate;

import org.junit.Assert;
import org.junit.Test;

public class NumberToWords {


String [] tensNames = { "ten " , "Twenty " , "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
double[] zeros = {Math.pow(10,3) , Math.pow(10,6) , Math.pow(10,9), Math.pow(10,12),Math.pow(10,15)}; 

public String toWords(int value) {
	int[]  denominations = new int[5];
	String[]  denominationsNames ={"Thousands " , "Millions " , "Billions " , "Trillions " , "Quadrillion " };
	    int currentValue= value;
	    StringBuilder sb =  new 	StringBuilder();
	    int i = 5;
	    while (i >= 1 ){
			denominations[i-1] = (int) (currentValue/ zeros[i-1]);
			currentValue -=   denominations[i-1]  *  zeros[i-1];
			if (denominations[i-1] > 0 ) sb .append(" ").append(threeDigitsToWords(denominations[i-1])) .append(" ").append( denominationsNames[i-1] );
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
		sb.append( twentyOrLessToString(  cVal / 100   ) ) .append(" hundred ");
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
	String[] values = { "one " , "two ", "three ", "four ", "five ", "six" , "seven ", "eight ", "nine ", "ten ", "eleven ", "twelve ", "thrteen", "forteen", "fifteen ", "sixteen " , "seventeen ", "eignteen ", "nineteen "};
	return  values[v-1];
}

@Test
public   void test_happycase1() {

	Assert.assertEquals( "four  Thousands one  hundred Twenty three", toWords (4123).trim());
	
}
@Test
public   void test_happycase2() {

	Assert.assertEquals( "Eighty four  Thousands one  hundred Twenty three", toWords (84123).trim());
	
}
@Test
public   void test_happycase3() {
	System.out.println(toWords (884123).trim());
	Assert.assertEquals( "eight hundred Eighty four  Thousands one  hundred Twenty three", toWords (884123).trim());
	
}

}
