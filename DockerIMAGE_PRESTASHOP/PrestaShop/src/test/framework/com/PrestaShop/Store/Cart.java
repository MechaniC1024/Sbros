package com.PrestaShop.Store;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Cart {

	private RemoteWebDriver driver;

	@CacheLookup
	@FindBy(xpath = "//div[@class='product-line-info']/a")
	private WebElement nameProduct;

	@CacheLookup
	@FindBy(xpath = "//div[@class='product-line-grid-body col-md-4 col-xs-8']/div[2]/span")
	private WebElement priceProduct;

	@CacheLookup
	@FindBy(xpath = "//span[@class='label js-subtotal']")
	private WebElement quantityProduct;

	@CacheLookup
	@FindBy(xpath = "//div[@class = 'text-xs-center']/a")
	private WebElement ordering;

	public Cart(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	public String getNameProductInCart() {

		return nameProduct.getText().toUpperCase();
	}

	public String getPriceProductInCart() {

		return priceProduct.getText().substring(0, priceProduct.getText().length() - 2).replace(',', '.');
	}

	public String getQuantityProductInCart() {

		return quantityProduct.getText().substring(0, quantityProduct.getText().length() - 4);
	}

	public PersonalOrderData clickOnPlaceOrder() {

		ordering.click();
		return new PersonalOrderData(driver);
	}
}
