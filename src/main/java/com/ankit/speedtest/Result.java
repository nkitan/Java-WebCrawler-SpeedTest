package com.ankit.speedtest;

public class Result {
	private String id;
	private String result;
	private String stats;
	
	Result(String id, String result, String stats){
		this.id = id;
		this.result = result;
		this.stats = stats;
	}
	
	public String toPrint() {
		return this.id + ":" + this.stats;
	}
	
	public String toString() {
		return this.id + "- \n RESULT: " + this.result + "\n " + this.stats;
	}
}
