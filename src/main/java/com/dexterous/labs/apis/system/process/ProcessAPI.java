package com.dexterous.labs.apis.system.process;

import java.io.File;
import java.util.Map;

import com.dexterous.labs.apis.system.filesystem.properties.PropertiesAPI;
import com.dexterous.labs.apis.system.runtime.RunTimeAPI;

/**
 * 
 */
public class ProcessAPI 
{	
	Process process;
	PropertiesAPI prop;
	RunTimeAPI runtimeapi;
	ProcessBuilder processbuilder;
	
	public String getProcessId(Process invokedApplicationProcess) {
		return String.valueOf(invokedApplicationProcess.pid());
	}
	public void abortProcess(Process invokedApplicationProcess) {
		invokedApplicationProcess.destroy();
	}
	
	
	public String getApplicationPathByName(String applicationName) {
		
		processbuilder = new ProcessBuilder();
		
		String path = null;
		
		Map<String,String> map = processbuilder.environment();
		
		for(Map.Entry<String, String> entry : map.entrySet())
		{
			if(entry.getKey().toUpperCase().startsWith(applicationName.toUpperCase()))
			{
				path = entry.getValue() + File.separator + applicationName + ".exe";
				System.out.println("path =========================================> "+path);
			}
		}
		return path;
	}
	
	public String getApplicationPort(String applicationName) {
		
		String pid,query,string,path;
		
		prop = new PropertiesAPI();
		prop.loadProperties("windowcmd");
		path = getApplicationPathByName("chromedriver");
		process = RunTimeAPI.invokeApplication(path);
		pid = getProcessId(process);
		query = prop.getProperty("PortNumberFetchFromPID"); 
		string = RunTimeAPI.executeShellCommand(query,pid);
		string = string.trim().split(":")[1].split(" ")[0].trim();
		return string;
		
	}
}
