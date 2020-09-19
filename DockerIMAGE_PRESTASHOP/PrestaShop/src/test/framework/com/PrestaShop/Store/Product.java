package com.PrestaShop.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.Wait.Wait.*;

public class Product{
	
	private RemoteWebDriver driver;
		
	@CacheLookup
	@FindBy(xpath = "//h1[@class = 'h1']")
	private WebElement nameProduct;
	
	@CacheLookup
	@FindBy(xpath = "//ol/li[last()]//span")
	private WebElement nameProductTitle;

	@CacheLookup
	@FindBy(xpath = "//span[@itemprop = 'price']")
	private WebElement priceProduct;
	
	@CacheLookup
	@FindBy(xpath = "//a[@href = '#product-details']")
	private WebElement productDetails;

	@CacheLookup
	@FindBy(xpath = "//button[@data-button-action = 'add-to-cart']")
	private WebElement addToCart;
	
	private By quantityProduct = By.xpath("//div[@class = 'product-quantities']/span");
	
	public Product(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	private void productDetails() {

		productDetails.click();
	}
	
	private WebElement getWebElementQuantityProduct() {

		return waitingForVisibilityOfElementLocated(driver, this.quantityProduct, 20);
	}
	
	public String getNameProduct() {
		
		return this.nameProduct.getText();
	}
	
	public String getPriceProduct() {
		
		return this.priceProduct.getText().substring(0, priceProduct.getText().length() - 2).replace(',', '.');
	}
	
	public String getQuantityProduct() {
		
		productDetails();
		WebElement quantityProductElement = getWebElementQuantityProduct();
		String quantity = quantityProductElement.getText().substring(0, quantityProductElement.getText().length() - 7);
		
		return quantity;
	}	
	
	public Integer getQuantityProductInt() {
		
		return Integer.parseInt(getQuantityProduct());
	}	

	public ProductDesign addToCard() {

		addToCart.click();
		return new ProductDesign(driver);
	}		
}
