package com.abhishek.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhishek.AbstractComponent.AbstractComponent;

public class ChedckOutPage extends AbstractComponent {
	
	
	WebDriver driver;
	
	public ChedckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results")
	WebElement result;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectcountry;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		WaitforElementToAppear(result);
		selectcountry.click();		
		
	}
	
	public ConfirmationPage SubmitOrder() {
		
		submit.click();
		return new ConfirmationPage(driver);
		
	}
	
	

}
