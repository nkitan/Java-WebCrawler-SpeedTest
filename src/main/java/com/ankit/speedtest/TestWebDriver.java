package com.ankit.speedtest;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestWebDriver extends Stats {
	protected List<String> urls;
	protected WebDriver driver;
	protected String id;
	protected String result;
	protected Integer skips;

	protected TestWebDriver(){
		this.urls = new ArrayList<String>();
		this.skips = 0;
		System.out.println("-- WebDriver Created --");
	}

	protected void initialize(String url) {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	
		int counter = 0;
		driver.get(url);

		List<WebElement> ids = driver.findElements(By.className("st_title"));
		for(WebElement id: ids) {
			counter++;
			WebElement temp = id.findElement(By.tagName("h2")).findElement(By.tagName("a"));
        	this.urls.add(temp.getAttribute("href"));
        
        	if(counter > 99) {
        		break;
        	}
		}
	
		System.out.println("URLS Parsed: " + counter);
	}

	protected void crawl() {
		for(String url: this.urls) {
			driver.get(url);
			try {
				this.result += driver.findElement(By.className("textshowhide")).getText();
			} catch (Exception e) {
				System.out.println("[SKIPPED]");
				this.skips++;
			}
		}
	
		driver.close();
	}
		
	
	public String getResult() {
		return this.result + "\n SKIPS: " + this.skips;
	}
}
