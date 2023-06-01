package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");

	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	public String getProductHeaderName() {
		return eleUtil.doGetELementText(productHeader);
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();

	}
	
//	{Brand=Apple, Availability=In Stock, extaxprice=$2,000.00, Product Code=Product 18, 
//	productname=MacBook Pro, Reward Points=800, productprice=$2,000.00}


	public Map<String, String> getProductInfo() {
//		productInfoMap = new HashMap<String, String>();
//		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap = new TreeMap<String, String>();


		
		getProductMetaData();
		getProductPriceData();
		productInfoMap.put("productname", getProductHeaderName());
		return productInfoMap;

	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock

	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaList) {
			String metaText = e.getText();
			String metaInfo[] = metaText.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);

		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> PriceList = eleUtil.getElements(productPriceData);
		String priceValue = PriceList.get(0).getText();// $2000.00
		String exTaxPrice = PriceList.get(1).getText();
		String exTaxPriceValue = exTaxPrice.split(":")[1].trim();// $2000.00

		productInfoMap.put("productprice", priceValue);
		productInfoMap.put("extaxprice", exTaxPriceValue);

	}

}
