package com.ankit.speedtest;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class HTMLUnit extends TestWebDriver {
	HTMLUnit(Boolean js, String url){
		super.driver = new HtmlUnitDriver(js) {
	        @Override
	        protected WebClient newWebClient(BrowserVersion version) {
	            WebClient webClient = super.newWebClient(version);
	            webClient.getOptions().setThrowExceptionOnScriptError(false);
	            return webClient;
	        }
		};
		
		super.id = "[HTMLUnit]";
		
		if(js) {
			super.id = "[HTMLUnit JS]";
		}

		super.start();
		super.initialize(url);
		super.crawl();
		super.end();
	}
	
	public String toString() {
		return super.id;
	}
}
