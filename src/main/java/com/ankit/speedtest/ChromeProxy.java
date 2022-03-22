package com.ankit.speedtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.BlacklistEntry;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class ChromeProxy {
	public WebDriver getDriver(String blackListTypes, Boolean headless) {
		BrowserMobProxy proxyServer = new BrowserMobProxyServer();
		
		// start proxy
		proxyServer.start(0);

		// get selenium proxy object
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
		
		// Add blacklist entries
		ArrayList<BlacklistEntry> blackListEntries = new ArrayList<BlacklistEntry>();
		blackListEntries.addAll(getBlacklistEntries(blackListTypes));
		proxyServer.clearBlacklist();
		proxyServer.setBlacklist(blackListEntries);
		
		// set chromeOptions to route through proxy
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
		chromeOptions.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
		
		// ignore ssl certs
		chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--allow-insecure-localhost");
        chromeOptions.addArguments("--ignore-urlfetcher-cert-requests");
			
		if (headless) {
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-gpu");
		}
			
		chromeOptions.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
		System.setProperty("webdriver.chrome.driver", "/home/notroot/projects/Hotflats_BitBucket/speedtest/speedtest/drivers/chromedriver");
		WebDriver driver = new ChromeDriver(chromeOptions);
		return driver;
	}

	ArrayList<BlacklistEntry> getBlacklistEntries(String blackListTypes) {
		ArrayList<BlacklistEntry> blackListEntries = new ArrayList<BlacklistEntry>();
		List<String> blackListTypesList = Arrays.asList(blackListTypes.split(","));
		if (blackListTypesList.contains("default")) {
			blackListEntries.add(new BlacklistEntry("https?://.*\\.google-analytics\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.doubleclick\\.net.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.youtube\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.ytimg\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.jpg.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.JPG.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.jpeg.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.png.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.gif.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.mp4.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.flv.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.mpeg.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.ico.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*doublelcick\\.net.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*google-analytics\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.facebook\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.facebook\\.net.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.twitter\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.googletagmanager\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.googleadservices\\.com/.*", 404));
		}
		if (blackListTypesList.contains("commonfloor.com")) {
			blackListEntries.add(new BlacklistEntry(".*\\.css.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.woff.*", 404));// disable
			blackListEntries.add(new BlacklistEntry("https?://.*/analytics/insert-user-session-data", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*googleapis\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*/public/assets/analytics_action.js", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*/analytics.js", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.visualwebsiteoptimizer.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*/cfassets/serp/js/cfGmaps.js", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*vizury\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry(".*/src/infobox.js", 404));
			blackListEntries.add(new BlacklistEntry(".*/gtm.js.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.crazyegg\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.addtoany\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.errorception\\.com.*", 404));
		}
		if (blackListTypesList.contains("housing.com")) {
			// blackListEntries.add(new BlacklistEntry(".*\\.css.*",
			// 404));//pagination fails if css is missing
			blackListEntries.add(new BlacklistEntry(".*\\.woff.*", 404));// disable
																			// fonts
			blackListEntries.add(new BlacklistEntry(".*\\.woff2.*", 404));// disable
																			// fonts
			blackListEntries.add(new BlacklistEntry(".*\\.ttf", 404));// disable
																		// fonts
			blackListEntries.add(new BlacklistEntry("https?://.*mxpanel\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*mxpnl\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*pubmatic\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*adnxs\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*mixpanel\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*go-mpulse\\.net.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://login\\.housing.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://footer\\.housing.com.*", 404));
			// blackListEntries.add(new
			// BlacklistEntry("https?://.*\\.housingcdn.com.*icomoon.*",
			// 404));//needed for pagination
			blackListEntries.add(new BlacklistEntry("https?://iris\\.housing.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://rails\\.housing.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://tagmanager\\.adtech\\.housing.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://regions\\.housing.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://\\.go-mpulse.net.*", 404));
		}
		if (blackListTypesList.contains("99acres.com")) {
			// blackListEntries.add(new BlacklistEntry(".*\\.css.*", 404));
			blackListEntries.add(new BlacklistEntry(".*\\.woff.*", 404));// disable
																			// fonts
			blackListEntries.add(new BlacklistEntry(".*\\.woff2.*", 404));// disable
																			// fonts
			blackListEntries.add(new BlacklistEntry(".*\\.ttf", 404));// disable
																		// fonts
			blackListEntries.add(new BlacklistEntry("https?://s\\.99acres.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*s2d6.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://www\\.99acres\\.com/do/Beacon/index.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.scorecardresearch\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.zedo\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.vizury\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.ieplads\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.gsecondscreen\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.sociomantic\\.com/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.criteo\\.net/.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*.99acres\\.com/do/siteindex/track.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.google\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.yahoo\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*\\.gstatic\\.com.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*99acres.com/do/zedoCookie/isParentChild.*", 404));
			blackListEntries
					.add(new BlacklistEntry("https?://.*99acres.com/do/zedoConfiguration/isActiveBanner.*", 404));
			blackListEntries.add(new BlacklistEntry("https?://.*maps.googleapis.com.*", 404));
			blackListEntries.add(new BlacklistEntry("http://www.99acres.com/do/tracking/trackUserAction.*", 404));
			blackListEntries.add(new BlacklistEntry(".*http://www.99acres.com/do/propertydescription/doTrackings.*",
					404));
			blackListEntries.add(new BlacklistEntry("http://www.99acres.com/do.*buyer/ShowBuyerForms/showMMALayer/.*",
					404));
			blackListEntries.add(new BlacklistEntry(
					"http://www.99acres.com/do/propertydescription/ajaxLoggedInChanges.*", 404));
			blackListEntries.add(new BlacklistEntry("http://www.99acres.com/do/HPCPBanners/ajaxBannerDetails.*", 404));
			// block javascript other than from 99acres.com
			blackListEntries.add(new BlacklistEntry("(?!http://static\\.99acres\\.com/js/).*\\.js.*", 404));
		}

		if (blackListTypesList.contains("alljs")) {
			blackListEntries.add(new BlacklistEntry(".*\\.js.*", 404));
		}
		if (blackListTypesList.contains("allcss")) {
			blackListEntries.add(new BlacklistEntry(".*\\.css.*", 404));
		}
		return blackListEntries;
	}

}
