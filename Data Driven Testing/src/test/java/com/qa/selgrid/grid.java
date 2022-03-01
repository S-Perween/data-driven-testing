package com.qa.selgrid;

import java.net.MalformedURLException;
import java.net.URL;
import datatest.call;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class grid  {
	public static void main(String[] args) throws MalformedURLException {
		
		
		//DesiredCapabilities cap= new DesiredCapabilities().firefox();
		DesiredCapabilities cap = new DesiredCapabilities();
		
		
	
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);
		
		ChromeOptions options = new ChromeOptions();
		
		 options.merge(cap); 
		 
			
		String huburl="http://192.168.1.14:4545/wd/hub";
		
		
		
		WebDriver driver= new RemoteWebDriver(new URL(huburl), cap);
		
		
		driver.get("https://www.shopclues.com/");
		driver.getTitle();
		
		driver.close();

	}
		
		
		
	
		
		
		

	}

