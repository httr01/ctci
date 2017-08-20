package com.algo.cracking.code.hard;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**Shuffle deck of card perfect randomly.**/
public class RandomCardShuffle {

	Random random =  new Random();
	/**Recursive approach**/
	public String[] shuffle( String[] shuffleDeck , int index) {
		if (index ==52 ) return shuffleDeck;
		else if (index ==0 ) {
			return shuffle(shuffleDeck, ++index);
		}else{
			int rIndex = random.nextInt(index+1); //  because java returns inclusive of 0 and exclusive of max number.
			//shuffle index  and rIndex
			String temp = shuffleDeck[index];
			shuffleDeck[index] = shuffleDeck[rIndex];
			shuffleDeck[rIndex] = temp;
			return shuffle(shuffleDeck, ++ index);
		}

	}
	
	@Test
	public void tets_happyPath (){
		String[] shuffleDeck = {"Diamond-A","Diamond-2","Diamond-3","Diamond-4","Diamond-5","Diamond-6","Diamond-7","Diamond-8","Diamond-9","Diamond-10","Diamond-J","Diamond-Q","Diamond-K",
				"Heart-A","Heart-2","Heart-3","Heart-4","Heart-5","Heart-6","Heart-7","Heart-8","Heart-9","Heart-10","Heart-J","Heart-Q","Heart-K",
				"Club-A","Club-2","Club-3","Club-4","Club-5","Club-6","Club-7","Club-8","Club-9","Club-10","Club-J","Club-Q","Club-K",
				"Spade-A","Spade-2","Spade-3","Spade-4","Spade-5","Spade-6","Spade-7","Spade-8","Spade-9","Spade-10","Spade-J","Spade-Q","Spade-K"
		};
		String[] copyShuffleDeck = Arrays.copyOf(shuffleDeck, shuffleDeck.length);
		String[] finalDeck = shuffle(shuffleDeck, 0);
		Assert.assertFalse("Original deck should not be equal to final after shuffle.", Arrays.toString(finalDeck).equals(Arrays.toString(copyShuffleDeck)));
		System.out.println(Arrays.toString(finalDeck));
		
	}
	
	
}
