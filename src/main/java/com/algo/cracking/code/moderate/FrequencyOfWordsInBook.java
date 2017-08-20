package com.algo.cracking.code.moderate;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class FrequencyOfWordsInBook {
	/**We can make following improvements
	 * 1. if  a buffer cuts a word in half and that could cause it not to be counted**/
	public int countFreq(String word, String fileWithPath) throws Exception{ 
		int CAPACITY = 50;
		FileChannel inChannel = new FileInputStream(fileWithPath).getChannel();
		ByteBuffer buffer = ByteBuffer.allocateDirect(CAPACITY);
		int indexOfWord =0;
		int freqWord=0;
		while(inChannel.read(buffer) > 0){
			indexOfWord =0;
			Charset charset = Charset.forName("US-ASCII");
			buffer.rewind();
			CharBuffer cb = charset.decode(buffer);
			String data = cb.toString();
			buffer.flip();
			int index = data.indexOf(word,indexOfWord);
			while(    index > 0 ) {
				if (index >=0)   {
					++freqWord;
					indexOfWord = index+1;
				}
				index = data.indexOf(word,indexOfWord);
			}
		}
		inChannel.close();
		return freqWord;
	}
 	
	@Test
	public void test_happyPath1() throws Exception{
		 String fileWithPath = "./src/main/resources/book1.txt";
		 Assert.assertEquals(2, countFreq("books",fileWithPath));
		 
	}
}
