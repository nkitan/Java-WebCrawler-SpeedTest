package com.ankit.speedtest;

import java.io.FileWriter;
import java.io.IOException;

public class App {
	public static void writeToFile(String filename, Result[] results) {
	    try {
		     FileWriter file = new FileWriter(filename);
		      
		     for(Result result: results) {
			    file.write(result != null ? result.toPrint() : "");
		     }
		      
		     file.close();
		     System.out.println("Result Written in " + filename);
		     
		   } catch (IOException e) {
		     System.out.println("error occured");
		     e.printStackTrace();
		   }
	  }
	  
    public static void main( String[] args ) {
		String url = "https://www.commonfloor.com/listing-search?city=Bangalore&search_intent=sale";
	    	// to disable tests for a certain driver, set its TestDrivers value to false
		Boolean[] TestDrivers = new Boolean[]{ true, true, true, true, true, true, true};
		Result[] results = new Result[7];
		int i = 0;

		// Selenium with Chrome
		if(TestDrivers[0]) {
			SeleniumChrome seleniumChrome = new SeleniumChrome(false, url, false);
			results[i] = new Result(seleniumChrome.toString(), seleniumChrome.getResult(), seleniumChrome.getStats());
			i++;
		}
		
		// Selenium + Chrome Headless
		if(TestDrivers[1]) {
			SeleniumChrome seleniumChromeHeadless = new SeleniumChrome(true, url, false);
			results[i] = new Result(seleniumChromeHeadless.toString(), seleniumChromeHeadless.getResult(), seleniumChromeHeadless.getStats());
			i++;
		}
		
		// Selenium + Chrome + Proxy
		if(TestDrivers[2]) {
			SeleniumChrome seleniumChromeProxy = new SeleniumChrome(false, url, true);
			results[i] = new Result(seleniumChromeProxy.toString(), seleniumChromeProxy.getResult(), seleniumChromeProxy.getStats());
			i++;
		}
		
		// Selenium + Chrome Headless + Proxy
		if(TestDrivers[3]) {
			SeleniumChrome seleniumChromeHeadlessProxy = new SeleniumChrome(true, url, true);
			results[i] = new Result(seleniumChromeHeadlessProxy.toString(), seleniumChromeHeadlessProxy.getResult(), seleniumChromeHeadlessProxy.getStats());
			i++;
		}
		
		// HTMLUnit + JavaScript
		if(TestDrivers[4]) {
			HTMLUnit htmlwithjs = new HTMLUnit(true, url);
			results[i] = new Result(htmlwithjs.toString(), htmlwithjs.getResult(), htmlwithjs.getStats());
			i++;
		}
		
		// HTMLUnit without JavaScript
		if(TestDrivers[5]) {
			HTMLUnit htmlwithoutjs = new HTMLUnit(false, url);
			results[i] = new Result(htmlwithoutjs.toString(), htmlwithoutjs.getResult(), htmlwithoutjs.getStats());
			i++;

		}
		
		// JSoup
		if(TestDrivers[6]) {
			JSoup jsoup = new JSoup(url);
			results[i] = new Result(jsoup.toString(), jsoup.getResult(), jsoup.getStats());
			i++;
		}
		
		String filename = System.currentTimeMillis() + ".txt";
		writeToFile(filename, results);
    }
}
