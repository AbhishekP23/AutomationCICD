package com.abhishek;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abhishek.TestComponents.BaseTest;
import com.abhishek.pageobjects.CartPage;
import com.abhishek.pageobjects.ChedckOutPage;
import com.abhishek.pageobjects.ConfirmationPage;
import com.abhishek.pageobjects.HomePage;
import com.abhishek.pageobjects.LoginPage;
import com.abhishek.pageobjects.OrderPage;

public class SubmitOrder extends BaseTest {
	//LoginPage loginpage;
	String productname = "ZARA COAT 3";
	
	
	@Test
	public void submitOrder() throws IOException {
		
		
		String country="india";
		//LoginPage loginpage =luanchApplication();
		HomePage hompage =loginpage.logIn("abhi@example.com", "Abhi@123");
		hompage.getProductList();
		hompage.addToCart(productname);
		CartPage cartpage = hompage.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		ChedckOutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry(country);
		ConfirmationPage confirmationpage = checkoutpage.SubmitOrder();
		String message = confirmationpage.getConfirmationMessage();
		
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"} )
	public void orderHistory() {
		
		HomePage homepage = loginpage.logIn("abhi@example.com", "Abhi@123");
		OrderPage orderpage = homepage.goToOrdersPage();
		//orderpage.VerifyOrderDisplay(productname);
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productname));
	}
	

}
