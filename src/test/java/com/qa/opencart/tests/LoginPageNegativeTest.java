package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {
	
	
	@DataProvider
	public Object[][] incorrectLoginTestData() {
		
		return new Object[][] {
			
			{"abds@gmail.com", "ab@123"},
			{"test@@gmail.com", "ab@123"},
			{"auto", "test"},
			{"#@$@$#@$", "@#!@#!@#! "},
			
		};
	}
	
	@Test(dataProvider = "incorrectLoginTestData")
	public void loginWithWrongCredentialsTest(String userName, String password) {
		Assert.assertTrue(loginPage.doLoginWithWrongCredentials(userName, password));
	}
	
	
	
	
	
	
	
	
	

}
