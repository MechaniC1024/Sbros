package com.PrestaShop.test3;

import static com.PrestaShop.DataResources.GetUrlAdminPanel.*;
import static com.PrestaShop.DataResources.GetUrlSite.*;
import static com.PrestaShop.Report.Report.addAttachmentToReport;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.DataResources.*;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.Store.*;

import io.qameta.allure.*;

@Epic("Тестовый набор 3.")
@Feature("Создание и проверка товара.")
public class CreatingANewProduct extends InitialConfiguration {

	private String nameProduct = GenerationProductData.generateNameProduct();
	private String priceProduct = GenerationProductData.generatePriceProduct();
	private String quantityProduct = GenerationProductData.generateQuantityProduct();
	
	private UserPage userPage;
	private Products product;
	private CreateProduct createProduct;

	@Step("Ввод логина и пароля, нажатие кнопки логин.")
	@Story("Ввод логина и пароля, нажатие кнопки логин.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = GetLoginAndPassword.class, description = "Вход в админ панель сайта.", alwaysRun = true)
	public void signIn(String login, String password) {

		setURL(getUrlAdminPanel());
		LoginPage loginPage = new LoginPage(getDriver());

		loginPage.inputLogin(login).inputPassword(password).clickOnLoginButton();
		userPage = new UserPage(getDriver());
	}

	@Step("Переход в раздел товаров.")
	@Story("Переход в раздел товаров.")
	@Test(dependsOnMethods = "signIn", description = "Переход в раздел товаров.", alwaysRun = true)
	public void productSection() {

		product = userPage.selectCatalog().goToProduct();
	}

	@Step("Создание нового товара по сгенерированным данным.")
	@Story("Создание нового товара по сгенерированным данным.")
	@Test(dependsOnMethods = "productSection", description = "Создание нового товара.", alwaysRun = true)
	public void newProduct() {

		createProduct = product.clickOnNewProductButton().inputNameNewProduct(nameProduct)
				.inputQuantityNewProduct(quantityProduct).inputPriceNewProduct(priceProduct);

		addAttachmentToReport("Название нового продукта.", nameProduct);
		addAttachmentToReport("Цена нового продукта.", priceProduct);
		addAttachmentToReport("Количество нового продукта.", quantityProduct);
	}

	@Step("Сохранение и активация товара.")
	@Story("Сохранение и активация товара.")
	@Test(dependsOnMethods = "productSection", description = "Сохранение и активация нового товара.", alwaysRun = true)
	public void productActivation() {

		createProduct.clickOnActivateANewProduct().closeSuccessfulUpdate().clickOnButtonSaveNewProduct()
				.closeSuccessfulUpdate();

	}

	@Step("Выход из админ панели сайта.")
	@Story("Выход из админ панели сайта.")
	@Test(dependsOnMethods = "productActivation", description = "Выход из админ панели сайта.", alwaysRun = true)
	public void logOut() {

		userPage.clickOnImageProfile().clickOnLogOut();
	}

	@Step("Проверка созданного товара на странице маганзина.")
	@Story("Проверка созданного товара на странице маганзина.")
	@Test(dependsOnMethods = "logOut", description = "Проверка созданного товара.", alwaysRun = true)
	public void checkNewProduct() {

		setURL(getUrlSite());
		HomePage homePage = new HomePage(getDriver());
		Product prod = homePage.clickOnAllProduct().clickOnProduct(nameProduct);

		String nameProductActual = prod.getNameProduct();
		String priceProductActual = prod.getPriceProduct();
		String quantityProduct = prod.getQuantityProduct();

		Asserts.assertVariable(nameProductActual, nameProduct.toUpperCase(), "Разное название товара.");
		Asserts.assertVariable(priceProductActual, priceProduct, "Разная цена товара.");
		Asserts.assertVariable(quantityProduct, quantityProduct, "Разное количество товара.");

		addAttachmentToReport("Название нового продукта на странице магазина.", nameProduct);
		addAttachmentToReport("Цена нового продукта на странице магазина.", priceProduct);
		addAttachmentToReport("Количество нового продукта на странице магазина.", quantityProduct);
	}
}