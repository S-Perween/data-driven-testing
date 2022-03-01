package datatest;

import datatest.read;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;



public class call {
 public static WebDriver driver;
 
	
 
 @Test
public void callf() throws Exception {
	 signin();
     search("Nike shoes");
	 robo("Headphones");
	 
}
 public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

     //Convert web driver object to TakeScreenshot
     TakesScreenshot scrShot =((TakesScreenshot)webdriver);

     //Call getScreenshotAs method to create image file
     File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

     //Move image file to new destination
     File DestFile=new File(fileWithPath);

     //Copy file at destination
     FileUtils.copyFile(SrcFile, DestFile);

 }

 
public static void signin() throws Exception {
	read obj = new read(".//dataexcel.xlsx");
	int count = obj.getRowCount("Sheet1");
	String arr[] = new String[count];
	for(int j=0; j<count; j++) {
		String email = obj.getCellData("Sheet1", 0, 2+j);
		arr[j] = email;
	}
	
	Map<String, Object> prefs = new HashMap<String, Object>();
	
	//add key and value to map as follow to switch off browser notification
	//Pass the argument 1 to allow and 2 to block
	prefs.put("profile.default_content_setting_values.notifications", 1);
	
	//Create an instance of ChromeOptions 
	ChromeOptions options = new ChromeOptions();
	
	// set ExperimentalOption - prefs 
	options.setExperimentalOption("prefs", prefs);
	
	//Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will switch off this browser notification on the chrome browser
	WebDriver driver = new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.get("https://www.shopclues.com/");
	driver.findElement(By.linkText("Sign In")).click();
	Thread.sleep(3000);
	
	
	for(int j=0; j<count-1; j++) {
		WebElement msg=driver.findElement(By.cssSelector("input#main_user_login"));
		msg.sendKeys(arr[j]);
		System.out.println(msg.getText().isBlank());
		
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login via OTP")).click();
		Thread.sleep(1000);
		takeSnapShot(driver, ".//screenshot//SignInAttempt-"+j+".png") ;
		driver.findElement(By.cssSelector("input#main_user_login")).clear();
		Thread.sleep(2000);
	}
	driver.quit();
}

public static void search(String name) throws Exception {
	Map<String, Object> prefs = new HashMap<String, Object>();
	
	//add key and value to map as follow to switch off browser notification
	//Pass the argument 1 to allow and 2 to block
	prefs.put("profile.default_content_setting_values.notifications", 1);
	
	//Create an instance of ChromeOptions 
	ChromeOptions options = new ChromeOptions();
	
	// set ExperimentalOption - prefs 
	options.setExperimentalOption("prefs", prefs);
	
	//Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will switch off this browser notification on the chrome browser
	WebDriver driver = new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.get("https://www.shopclues.com/");
	Thread.sleep(3000);
	WebElement searching= driver.findElement(By.xpath("//*[@id=\"search\"]/child::input[1]"));
	searching.sendKeys(name);
		
    Thread.sleep(2000);
 	Robot r = new Robot();
	
	driver.findElement(By.linkText("Search")).click();
	driver.findElement(By.xpath("//div[@class='img_section']/descendant::img[1]")).click();
	Thread.sleep(3000);
	
	r.keyPress(KeyEvent.VK_CONTROL);
	r.keyPress(KeyEvent.VK_TAB);
	r.keyRelease(KeyEvent.VK_CONTROL);
	r.keyRelease(KeyEvent.VK_TAB);
	ArrayList<String> tabs=	new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
	Thread.sleep(1000);
	
	takeSnapShot(driver, ".//screenshot//Nike-Shoe.png");
	WebElement productname = driver.findElement(By.xpath("//*[@class=\"prd_mid_info \"]/descendant::h1[1]"));
	WebElement price= driver.findElement(By.xpath("//*[@class=\"f_price\"]"));
	String final_price = price.getText().substring(1);
	String val = "Product Name = " + productname.getText() + ". The Final Price = Rs." + final_price;
	Reporter.log(val);
	driver.quit();
	}
     
public static void robo(String name) throws Exception {
Map<String, Object> prefs = new HashMap<String, Object>();
	
	//add key and value to map as follow to switch off browser notification
	//Pass the argument 1 to allow and 2 to block
	prefs.put("profile.default_content_setting_values.notifications", 1);
	
	//Create an instance of ChromeOptions 
	ChromeOptions options = new ChromeOptions();
	
	// set ExperimentalOption - prefs 
	options.setExperimentalOption("prefs", prefs);
	
	//Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will switch off this browser notification on the chrome browser
	WebDriver driver = new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.get("https://www.shopclues.com/");
	Thread.sleep(3000);
	WebElement searching= driver.findElement(By.xpath("//*[@id=\"search\"]/child::input[1]"));
	searching.sendKeys(name);
		
    Thread.sleep(2000);
 	Robot r = new Robot();
 	r.keyPress(KeyEvent.VK_ENTER);
 	r.keyRelease(KeyEvent.VK_ENTER);

	Thread.sleep(2000);
	WebElement searched= driver.findElement(By.xpath("//*[@id=\"instdcnt\"]"));
	System.out.println("The number of items found = "+searched.getText());
  
  driver.quit();
}
}
