package com.PrestaShop.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.PrestaShop.Wait.Wait.*;
import static com.PrestaShop.Report.Report.*;

public class UserPage {

	private RemoteWebDriver driver;

	private By loading = By.xpath("//span[@id='ajax_running']");

	@CacheLookup
	@FindBy(xpath = "//span[@class = 'employee_avatar_small']/img")
	private WebElement imageProfile;

	@CacheLookup
	@FindBy(xpath = "	//div[@class='img-circle person']/i")
	private WebElement imageProfileSmall;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '1']/a")
	private WebElement dashboard;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '3']/a")
	private WebElement orders;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '9']/a")
	protected WebElement catalog;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '23']/a")
	private WebElement customer;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '27']/a")
	private WebElement customerService;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '31']/a")
	private WebElement statistics;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '42']/a")
	private WebElement modules;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '46']/a")
	private WebElement design;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '52']/a")
	private WebElement delivery;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '55']/a")
	private WebElement payment;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '58']/a")
	private WebElement international;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '73']/a")
	private WebElement shopParameters;

	@CacheLookup
	@FindBy(xpath = "//li[@data-submenu = '95']/a")
	private WebElement advancedParameters;

	public UserPage(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	private void waitingLoading() {

		if (!driver.getCapabilities().getBrowserName().equals("chrome")) {

			waitingForVisibilityOfElementLocated(driver, loading, 20);
			waitingForInvisibilityOfElementLocated(driver, loading, 20);
		}
	}

	public Profile clickOnImageProfile() {

		try {
			imageProfile.click();
		} catch (NoSuchElementException e) {
			imageProfileSmall.click();
		}

		return new Profile(driver);

	}

	private Actions moveToParagraph(WebElement element) {

		waitingLoading();

		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();

		return actions;

	}

	public Catalog selectCatalog() {

		return new Catalog(driver, moveToParagraph(catalog));
	}

	private void clickOnParagraph(WebElement element) {
		element.click();
	}	
	
	public String getTitle() {

		String title = driver.getTitle();
		addAttachmentToReport("Заголовок страницы.", title);
		return title;		
	}
	
	public UserPage refreshPage() {
		
		driver.navigate().refresh();		
		return this;		
	}
	
	public UserPage clickOnDashboard() {

		clickOnParagraph(dashboard);
		return this;		
	}
	
	public UserPage clickOnOrders() {

		clickOnParagraph(orders);
		return this;
	}

	public UserPage clickOnCatalog() {

		clickOnParagraph(catalog);
		return this;
	}

	public UserPage clickOnCustomer() {

		clickOnParagraph(customer);
		return this;
	}

	public UserPage clickOnCustomerService() {

		clickOnParagraph(customerService);
		return this;
	}

	public UserPage clickOnStatistics() {

		clickOnParagraph(statistics);
		return this;
	}

	public UserPage clickOnModules() {

		clickOnParagraph(modules);
		return this;
	}

	public UserPage clickOnDesign() {

		clickOnParagraph(design);
		return this;
	}

	public UserPage clickOnDelivery() {

		clickOnParagraph(delivery);
		return this;
	}

	public UserPage clickOnPayment() {

		clickOnParagraph(payment);
		return this;
	}

	public UserPage clickOnInternational() {

		clickOnParagraph(international);
		return this;
	}

	public UserPage clickOnShopParameters() {

		clickOnParagraph(shopParameters);
		return this;
	}

	public UserPage clickOnAdvancedParameters() {

		clickOnParagraph(advancedParameters);
		return this;
	}
}
