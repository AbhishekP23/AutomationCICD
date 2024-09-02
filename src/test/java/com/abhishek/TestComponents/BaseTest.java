package com.abhishek.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.abhishek.pageobjects.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginpage ;
	public WebDriver initialize() throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\abhishek\\resources\\GlobalData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browsername = prop.getProperty("browser");
		
		if(browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			//WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless")){
			options.addArguments("headless");
			}		
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}
		if(browsername.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}

	@BeforeMethod
	public LoginPage luanchApplication() throws IOException {
		
		driver = initialize();
		loginpage = new LoginPage(driver);
		loginpage.goTo();
		return loginpage;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
