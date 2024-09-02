package com.abhishek.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abhishek.AbstractComponent.AbstractComponent;

public class HomePage extends AbstractComponent {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	
	@FindBy(css="#toast-container")
	WebElement toaster;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProductList()
	{
		WaitforElementToAppearBy(productBy);
		return products;
	}
	
	public WebElement getProductName(String productname) {
		
		WebElement prod = getProductList().stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productname) {
		WebElement prodname =getProductName(productname);
		prodname.findElement(addTocart).click();
		WaitforElementToAppear(toaster);
		WaitforElementToDisappear(spinner);
		
		
	}

}
