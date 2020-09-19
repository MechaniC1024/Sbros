package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ConfirmationOfAnOrder{
	
	private RemoteWebDriver driver;
	
	@CacheLookup
	@FindBy(xpath = "//h3[@class='h1 card-title']")
	private WebElement titleOrderConfirmed;

	@CacheLookup
	@FindBy(xpath = "//div[@class='col-xs-5 text-sm-right text-xs-left']")
	private WebElement price;

	@CacheLookup
	@FindBy(xpath = "//div[@class='col-xs-2']")
	private WebElement quantity;

	@CacheLookup
	@FindBy(xpath = "//div[@class='col-sm-4 col-xs-9 details']/span")
	private WebElement name;

	@CacheLookup
	@FindBy(xpath = "//section/a")
	private WebElement allProducts;

	public ConfirmationOfAnOrder(RemoteWebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}
		
	public String getOrderNameProduct() {
		
		return name.getText().toUpperCase();
	}
	
	public String getOrderPriceOrder() {
		
		return price.getText().substring(0, price.getText().length() - 2).replace(',', '.');
	}
	
	public String getOrderQuantityProduct() {
		
		return quantity.getText();
	}
	
	public String getOrderTitle() {		
		
		return titleOrderConfirmed.getText().substring(1, titleOrderConfirmed.getText().length());
	}
		
	public AllProduct selectAllProduct() {

		allProducts.click();
		return new AllProduct(driver);
	}

}