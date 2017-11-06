package com.semanticsquare.thrillio.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class IOUtil {
	public static void read(String[] data, String filename) {
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))){
			String line = null;
			int count = 0;
			while((line = in.readLine()) != null) {
				data[count] = line;
				count++;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
