package com.algo.cracking.code.moderate;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class TicTacGame {
	
	/** This hash indicate the which  cells ( row column combination) is part of diagonal.
	 * Usually there are only two diagonal in this game which we need to check. */
	
	HashMap<String,String> diagHashMap =  new HashMap<String,String>();
	public TicTacGame(){
		diagHashMap.put("00", "diag-1");
		diagHashMap.put("11", "diag-1,diag-2");
		diagHashMap.put("22", "diag-1");
		diagHashMap.put("02", "diag-2");
		diagHashMap.put("20", "diag-2");		
	}
	
	/** Assuming that we have 3X3 board and it is not called every move. 
	 * This will be called finally when game is done.**/
	
	public int analyzeWinner_v1(int[][] board){
		// check each row		
		for (int row =0 ;  row<3 ; ++row){
			if (board[row][0]> 0 && board[row][0] == board[row][1] && board[row][1] == board[row][2]   ) return  board[row][0];
		}
		// check each column
		for (int col =0 ;  col<3 ; ++col){
			if (board[0][col]> 0 && board[0][col] == board[1][col] && board[1][col] ==board[2][col]  )return  board[0][col];
		}
		// check first diagonal
		if (board[0][1] >0 && board[0][1] == board[1][1] && board[1][1] ==board[2][2]  ) return  board[0][0];
		// check first diagonal
		if (board[0][2] >0 && board[0][2] == board[1][1] && board[1][1] ==board[2][0]   ) return  board[2][0];
		
		return -1;
		
	}
	
	
	/** Assuming that we have 3X3 board . 
	 * This will be called for every move. 
	 * That means we know which cell is changed and only one cell is changing every time it is called
	 * 1: player 1
	 * 2: player 2
	 * 0: initial value, not changed by any player**/
	
	public int analyzeWinner_v2(int[][] board, int rowChanged, int columnChanged){
		// check each row		
		if (board[0][columnChanged]> 0 && board[0][columnChanged] == board[1][columnChanged] && board[1][columnChanged] == board[2][columnChanged]   ) return  board[0][columnChanged];
		// check each column
		if (board[rowChanged][0]> 0 && board[rowChanged][0] == board[rowChanged][1] && board[rowChanged][1] ==board[rowChanged][2]  )return  board[rowChanged][0];
		
		// check first diagonal if anything is changed
		if ( diagHashMap.get(""+rowChanged+columnChanged)!= null && diagHashMap.get(""+rowChanged+columnChanged).indexOf("diag-1") >-1 )  if (board[0][0] >0 && board[0][0] == board[1][1] && board[1][1] ==board[2][2]  )  return  board[0][0];
		
		// check first diagonal if anything is changed
		if ( diagHashMap.get(""+rowChanged+columnChanged)!= null && diagHashMap.get(""+rowChanged+columnChanged).indexOf("diag-2") >-1 ) if (board[0][2] >0 && board[0][2] == board[1][1] && board[1][1] ==board[2][0]   )  return  board[2][0];
		return -1;
	}
	
	@Test
	public void test_happyPath(){
		int[][] board  =  new int[3][3];
		board[0][0]= 1;
		Assert.assertEquals(-1, analyzeWinner_v1(board));
		Assert.assertEquals(-1, analyzeWinner_v2(board, 0, 0));
		
		board[0][1]= 1;
		Assert.assertEquals(-1, analyzeWinner_v1(board));
		Assert.assertEquals(-1, analyzeWinner_v2(board, 0, 1));
		
		board[0][2]= 1;
		Assert.assertEquals(1, analyzeWinner_v1(board));
		Assert.assertEquals(1, analyzeWinner_v2(board, 0, 2));
		
		board[0][2]= 0;
		
		board[1][1]= 1;
		Assert.assertEquals(-1, analyzeWinner_v1(board));
		Assert.assertEquals(-1, analyzeWinner_v2(board, 1, 1));
		
		board[2][2]= 1;
		Assert.assertEquals(1, analyzeWinner_v1(board));
		Assert.assertEquals(1, analyzeWinner_v2(board, 2, 2));
		
		board[2][2]= 0;
		
		board[0][2]= 2;
		Assert.assertEquals(-1, analyzeWinner_v1(board));
		Assert.assertEquals(-1, analyzeWinner_v2(board, 0, 2));

		board[1][2]= 2;
		Assert.assertEquals(-1, analyzeWinner_v1(board));
		Assert.assertEquals(-1, analyzeWinner_v2(board, 1, 2));
		
		board[2][2]= 2;
		Assert.assertEquals(2, analyzeWinner_v1(board));
		Assert.assertEquals(2, analyzeWinner_v2(board, 2, 2));	
	}
}
