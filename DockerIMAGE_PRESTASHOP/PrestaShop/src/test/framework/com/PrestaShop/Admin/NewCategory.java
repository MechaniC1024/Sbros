package com.PrestaShop.Admin;

import static com.PrestaShop.Report.Report.addAttachmentToReport;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NewCategory {

	private RemoteWebDriver driver;

	@CacheLookup
	@FindBy(xpath = "//input[@id = 'name_1']")
	private WebElement nameFieldCategories;

	@CacheLookup
	@FindBy(xpath = "//button[@id = 'category_form_submit_btn']")
	private WebElement buttonSave;

	public NewCategory(RemoteWebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
	}

	public Category clickSaveButton() {

		buttonSave.click();
		return new Category(driver);
	}

	public NewCategory setNameCategoty(String nameCategory) {

		nameFieldCategories.sendKeys(nameCategory);
		addAttachmentToReport("Название новой категории.", nameCategory);
		return this;
	}
}
