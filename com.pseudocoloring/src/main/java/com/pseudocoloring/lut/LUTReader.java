package com.pseudocoloring.lut;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class LUTReader {
	
	private BufferedReader bufferReader;
	
	public LUTReader(String lutFile) throws FileNotFoundException{
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		bufferReader = new BufferedReader(new FileReader("C:/Users/Vlad/Desktop/ImageJ/luts/"+ lutFile + ".lut"));
	}
	
	public String getLine(int line) throws IOException{
		String data = "";
		for(int i=0;i<=line; i++){
			data = bufferReader.readLine();
		}
		return data;
	}
}