package com.ankit.speedtest;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

import com.sun.management.OperatingSystemMXBean;

public class Stats {
	private long start;
	private long end;
	
	Stats(){
	}
	
	protected String getTimeElapsed() {
		return "Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(this.end - this.start) + "s";
	}
		
	protected String getResourceUsage() {
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		return "PROC: " + osBean.getProcessCpuLoad() * 100.0 + "%" + "\n" + "AVG: " + osBean.getSystemLoadAverage() * 100.0 + "%";
	}
	
	protected void start() {
		this.start = System.currentTimeMillis();
	}
	
	protected void end() {
		this.end = System.currentTimeMillis();
	}
	
	public String getStats() {
		return this.getTimeElapsed() + "\n" + this.getResourceUsage();
	}
}
