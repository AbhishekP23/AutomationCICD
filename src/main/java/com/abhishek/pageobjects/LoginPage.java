package com.abhishek.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhishek.AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement passwrd;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="#toast-container")
	WebElement errormessage;
	
	public void goTo(){
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public HomePage logIn(String username, String Pswrd) {
		
		userName.sendKeys(username);
		passwrd.sendKeys(Pswrd);
		submit.click();
		HomePage homepage = new HomePage(driver);
		return homepage;
		
	}
	
	public String errorMessage() {
		WaitforElementToAppear(errormessage);
		return errormessage.getText();
	}

}
