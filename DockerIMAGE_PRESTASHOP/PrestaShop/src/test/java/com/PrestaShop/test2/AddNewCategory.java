package com.PrestaShop.test2;

import static com.PrestaShop.DataResources.GetUrlAdminPanel.*;
import static com.PrestaShop.Report.Report.*;

import java.util.List;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.DataResources.GetLoginAndPassword;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 2.")
@Feature("Проверка титулов страниц сайта.")
public class AddNewCategory extends InitialConfiguration {

	private String newCategory = "Jacket";
	private String buttonAlertExpected = "Создано";

	private UserPage userPage;
	private Category category;

	@Step("Ввод логина и пароля, нажатие кнопки логин.")
	@Story("Ввод логина и пароля, нажатие кнопки логин.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = GetLoginAndPassword.class, description = "Вход в админ панель.", alwaysRun = true)
	public void signIn(String login, String password) {

		setURL(getUrlAdminPanel());

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.inputLogin(login).inputPassword(password).clickOnLoginButton();
		userPage = new UserPage(getDriver());
	}

	@Step("Переход в раздел категории товаров.")
	@Story("Переход в раздел категории товаров.")
	@Test(dependsOnMethods = "signIn", description = "Переход в раздел категории товаров.", alwaysRun = true)
	public void categorySection() {

		category = userPage.selectCatalog().goToCategories();
	}

	@Step("Создание новой категории.")
	@Story("Создание новой категории.")
	@Test(dependsOnMethods = "categorySection", description = "Добавление новой категории.", alwaysRun = true)
	public void addNewCategory() {

		String buttonAlertActual = category.addNewCategory().setNameCategoty(newCategory).clickSaveButton()
				.getTextButtonAlert();

		Asserts.assertVariable(buttonAlertActual, buttonAlertExpected);
		category.clickOnCloseButtonAlert();
	}

	@Step("Проверка созданной новой категории.")
	@Story("Проверка созданной новой категории.")
	@Test(dependsOnMethods = "addNewCategory", description = "Проверка созданной категории.", alwaysRun = true)
	public void checkingNewCategory() {

		List<String> list = category.setFilterByNameCategory(newCategory).filterSearch().getListCategories();

		addAttachmentToReport("Проверка новой категории.", newCategory);
		Asserts.assertContainsVariable(list, newCategory);
	}

	@Step("Выход из админ панели сайта.")
	@Story("Выход из админ панели сайта.")
	@Test(dependsOnMethods = "checkingNewCategory", description = "Выход с аккаунта.", alwaysRun = true)
	public void logOut() {

		userPage.clickOnImageProfile().clickOnLogOut();
	}
}