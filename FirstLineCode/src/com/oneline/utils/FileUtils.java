
  
package com.oneline.utils;  

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;

public class FileUtils {

	public static void save(Context context ,String data ,String fileName){
		try {
			FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
			try {
				bufferedWriter.write(data);
			} catch (IOException e) {
				e.printStackTrace();  
			} finally{
				if(bufferedWriter != null) {
					try {
						bufferedWriter.close();
					} catch (IOException e) {
						e.printStackTrace();  
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();  
		}
	}
	
	public static String getFile(Context context ,String fileName){
		BufferedReader bufferedReader = null;
		StringBuilder content = null;
		try {
			content = new StringBuilder();
			FileInputStream fileInputStream = context.openFileInput(fileName);
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String line = null;
			
			while ((line = bufferedReader.readLine())!=null) {
				content.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();  
				}
			}
		}
		return content.toString();
	}
}
  