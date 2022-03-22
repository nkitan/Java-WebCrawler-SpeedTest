package com.ankit.speedtest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.ankit.speedtest.ChromeProxy;

public class SeleniumChrome extends TestWebDriver {
		private ChromeOptions options;
		
		SeleniumChrome(Boolean headless, String url, Boolean useProxy) {
			this.options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "/home/notroot/projects/Hotflats_BitBucket/speedtest/speedtest/drivers/chromedriver");
			super.id = "[Selenium Chrome]";
			if(headless) {
				super.id += "[Headless]";
				this.options.addArguments("--headless");
			}
			
			super.start();
			if(useProxy) {
				ChromeProxy proxy = new ChromeProxy();
				super.id += "[Proxy]";
				this.driver = proxy.getDriver("default,commonfloor.com", headless);
			} else {
				this.driver = new ChromeDriver(this.options);
			}
			
			super.initialize(url);
			super.crawl();
			super.end();
		}
		
		public String toString() {
			return super.id;
		}
}
