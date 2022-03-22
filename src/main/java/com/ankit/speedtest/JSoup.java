package com.ankit.speedtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoup extends Stats {
	private String url;
	private List<String> urls;
	private String result;
	
	JSoup(String url){
		this.url = url;
		this.urls = new ArrayList<String>();
		System.out.println("-- WebDriver Created --");
		super.start();
		this.initialize();
		this.crawl();
		super.end();
	}
	
	private void crawl() {
		for(String url: this.urls) {
			try {
				Document doc = Jsoup.connect(url).get();
				this.result += doc.getElementsByClass("textshowhide").first().text();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void initialize() {
        Document doc;
		try {
			doc = Jsoup.connect(this.url).get();
	        Elements ids = doc.getElementsByClass("st_title");
	        
	        int counter = 0;
	        for (Element id : ids) {  
	        	counter++;
	        	Element temp = id.getElementsByTag("h2").first();
	        	this.urls.add(temp.select("a[href]").first().attr("abs:href"));
	        	
	        	if(counter > 99) {
	        		break;
	        	}
	        }  
	        
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public String getResult() {
		return this.result;
	}
	
	public String toString() {
		return "[JSoup]";
	}
}
