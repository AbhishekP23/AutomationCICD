package com.abhishek;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abhishek.TestComponents.BaseTest;
import com.abhishek.TestComponents.Retry;
import com.abhishek.pageobjects.CartPage;
import com.abhishek.pageobjects.ChedckOutPage;
import com.abhishek.pageobjects.ConfirmationPage;
import com.abhishek.pageobjects.HomePage;


public class ErrorVaalidationTest extends BaseTest {
	
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() {
		
		loginpage.logIn("abhi@exam.com", "Abhi@123");
		String message = loginpage.errorMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Incorrect email or password."));
		
	}
	
	
	@Test
	public void productErrorValidation() throws IOException {
		
		
		String productname="ZARA COAT 3";
		HomePage hompage =loginpage.logIn("mansa@Abhi.com", "Mansa@23");
		hompage.getProductList();
		hompage.addToCart(productname);
		CartPage cartpage = hompage.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
		
	}

}
