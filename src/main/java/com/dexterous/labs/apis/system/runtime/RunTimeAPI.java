package com.dexterous.labs.apis.system.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * This ambition of this class is to provide system level method calls at runtime 
 * like running shell script or commands.
 */
public class RunTimeAPI 
{
	
	static Runtime runtime;
	static Process process;
	static StringBuilder stringbuilder;
	static BufferedReader bufferedReader;
	static InputStreamReader inputstreamreader;
	
	public static Runtime getRunTimeInstance() {
		 return runtime.getRuntime();
    }
	
	public static String executeShellCommand(String command,String processID) {
		
		try {
			
			command = command.replaceAll("pid", processID);
			
			process = runtime.getRuntime().exec(String.format("cmd /c %s", command));
			
			inputstreamreader = new InputStreamReader(process.getInputStream());
		    bufferedReader = new BufferedReader(inputstreamreader);
			stringbuilder = new StringBuilder();
	        String line = null;
	        
		    while ((line = bufferedReader.readLine()) != null)
		    	stringbuilder.append(line).append("\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stringbuilder.toString();
	}
	
	public static Process invokeApplication(String applicationPath) {
		
		try {
			process = runtime.getRuntime().exec(applicationPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return process;
		
	}
}
