package com.algo.cracking.code.hard;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class MaxSumSubMatrix {
	
	/***Given N*N matrix with positive and negative numbers. Find sub metrix with largest sum**/
	
	/****
	 * 		0	1	2	3	 
	 * 	------------------- -
	 * 0|	-3	2	-2	1	 
	 * 1|	2	3	1	1	 	
	 * 2|	-2	-4	2	-4	 
	 * 3|	3	4	-5	3	 
	 *  |
	 * ***
	 * Efficiency Improvements :Keep a hashmap of pre calculated matrix and use them
	 *  For example if  we need to calculate sum of matrix row =0 to 2 and  col = 0 to 2.  So we will do add  following
	 * 1. Get sum of matrix row =0 to 1 and  col = 0 to 1.
	 * 2. Add row=0, col=2 and Row=1,col=2
	 * 3. Add row=2, col=0 and Row=2,col=1
	 * 4. Add row=2, col=2
	 **/

	
	int maxSum = Integer.MIN_VALUE;
	int[][] maxSumMatrixIndex =  new int[3][2];

	public int[][] findMaxSumSubMatrix( int[][] givenMatrix,boolean effImprovements){
		for (int startRow =0 ;startRow<givenMatrix.length ;++startRow) {
			for (int endRow = startRow+1 ; endRow < givenMatrix.length ;++ endRow) {
				for (int startCol = 0 ; startCol <givenMatrix[0].length ;++ startCol) {
					for (int endCol = startCol+1 ; endCol <givenMatrix[0].length ;++ endCol) {
						int currentSum = effImprovements ? calculateSumDynamicProgramming(  givenMatrix, startRow, endRow, startCol, endCol) : calculateSum_bruteForce(  givenMatrix, startRow, endRow, startCol, endCol);
						if (currentSum > maxSum) {
							maxSum = currentSum;
							maxSumMatrixIndex[0][0] = startRow;
							maxSumMatrixIndex[0][1] = endRow;
							maxSumMatrixIndex[1][0] = startCol;
							maxSumMatrixIndex[1][1] = endCol;	
							maxSumMatrixIndex[2][0] = maxSum;	
						}
					}
			
				}
			
			}
		}
		return maxSumMatrixIndex;
	}// method

	private int calculateSum_bruteForce(int[][] givenMatrix,int startRow, int endRow,int  startCol, int endCol) {
		int sum = 0;
		for (int r = startRow ;r<=endRow ;++r) {
			for (int c = startCol; c <=endCol ;++ c)   sum+=	givenMatrix[r][c];
		}	
		return sum;	
	}
	
	
	Map<String, Integer> preCalculatedSumMap = new HashMap<String, Integer> ();
	private int calculateSumDynamicProgramming(int[][] givenMatrix,int startRow, int endRow,int  startCol, int endCol) {
		int sum = 0;
		// Check if the smaller sub matrix has been calculated in previous steps, thus we do not need to recalculate same matrix twice.
		String preKey= startRow+","+(endRow-1 )+","+ startCol+","+(endCol-1);
		Integer preCalculatedValue =preCalculatedSumMap.get(preKey);
		if (preCalculatedValue != null) {
			sum += preCalculatedValue.intValue();
			//2. add row values
			for (int c = startCol ; c< endCol ; ++c)  	sum+=givenMatrix[endRow][c];
			//3.add column values
			for (int r = startRow ; r< endRow ; ++r)  	sum+=givenMatrix[r][endCol]; 
			//4.  add 	final value
			sum+=givenMatrix[endRow][endCol]; 
		}else{
			for (int r = startRow ;r<=endRow ;++r) {
				for (int c = startCol; c <=endCol ;++ c)   sum+=	givenMatrix[r][c];
			}	
		}
		String key= startRow+","+endRow+","+ startCol+","+endCol;
		// Save the sum of the matrix so that  we can use it for later
		preCalculatedSumMap.put(key,sum);
		return sum;	
	}
	
	@Test
	public void happyPath_bruteforce(){
		MaxSumSubMatrix obj = new MaxSumSubMatrix();
		int[][] matrix  = getTestMatrix1();
		
		int[][] expectedMaxSumMatrixIndex = new int[3][2];
		expectedMaxSumMatrixIndex[0][0]=0;
		expectedMaxSumMatrixIndex[0][1]=1;
		expectedMaxSumMatrixIndex[1][0]=1;
		expectedMaxSumMatrixIndex[1][1]=3;
		expectedMaxSumMatrixIndex[2][0]=6; // Total sum of max sub array
		
		int[][] actualMaxSumMatrixIndex = obj.findMaxSumSubMatrix(matrix,false);
		Assert.assertArrayEquals(expectedMaxSumMatrixIndex, actualMaxSumMatrixIndex);
		 
		
	}
	
	@Test
	public void happyPath_efficient(){
		MaxSumSubMatrix obj = new MaxSumSubMatrix();
		int[][] matrix  = getTestMatrix1();
		int[][] expectedMaxSumMatrixIndex = new int[3][2];
		expectedMaxSumMatrixIndex[0][0]=0;
		expectedMaxSumMatrixIndex[0][1]=1;
		expectedMaxSumMatrixIndex[1][0]=1;
		expectedMaxSumMatrixIndex[1][1]=3;
		expectedMaxSumMatrixIndex[2][0]=6; // Total sum of max sub array
		
	 	int[][] actualMaxSumMatrixIndexEfficient= obj.findMaxSumSubMatrix(matrix,true);
		Assert.assertArrayEquals(expectedMaxSumMatrixIndex, actualMaxSumMatrixIndexEfficient);
		
	}
	
	
	
	private int[][]   getTestMatrix1 (){
		int[][] matrix  =  new int[4][4];
		matrix[0][0]= -3;
		matrix[0][1]= 2;
		matrix[0][2]= -2;
		matrix[0][3]= 1;
		
		matrix[1][0]= 2;
		matrix[1][1]= 3;
		matrix[1][2]= 1;
		matrix[1][3]= 1;	
		
		matrix[2][0]= -2;
		matrix[2][1]= -4;
		matrix[2][2]= 2;
		matrix[2][3]= -4;
		
		
		matrix[3][0]= 3;
		matrix[3][1]= 4;
		matrix[3][2]= -5;
		matrix[3][3]= 3;
		return matrix;	
		
	}
	
}
