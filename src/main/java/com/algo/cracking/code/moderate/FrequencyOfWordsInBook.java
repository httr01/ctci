package com.algo.cracking.code.moderate;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class FrequencyOfWordsInBook {

	public int countFreq(String word, String fileWithPath) throws Exception{
		
		int CAPACITY = 1000;
		FileChannel inChannel = new FileInputStream(fileWithPath).getChannel();
		ByteBuffer buffer = ByteBuffer.allocateDirect(CAPACITY);
		while(inChannel.read(buffer) > 0){
			String data = buffer.toString();
		    buffer.clear(); // do something with the data and clear/compact it.
		}
		inChannel.close();
		return 0;
	}
	
	private int countWordFrequency(String word, String data, Map<String,Integer> mapOfWords  ){
		// todo
		return 0; 
	}
	
	
	@Test
	public void test_happyPath1(){
		 String fileWithPath = "./src/main/resources/book1.txt";
		 Assert.assertEquals(5, countFreq(fileWithPath));
		 
	}
}
