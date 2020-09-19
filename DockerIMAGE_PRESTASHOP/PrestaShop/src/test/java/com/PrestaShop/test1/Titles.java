package com.PrestaShop.test1;

import static com.PrestaShop.DataResources.GetUrlAdminPanel.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.DataResources.GetLoginAndPassword;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 1.")
@Feature("Проверка титулов страниц сайта.")
public class Titles extends InitialConfiguration {

	private String title = "";
	private String titleRefresh = "";
	
	private UserPage userPage;

	@Step("Ввод логина и пароля, нажатие кнопки логин.")
	@Story("Ввод логина и пароля, нажатие кнопки логин.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = GetLoginAndPassword.class, description = "Вход в админ панель.", alwaysRun = true)
	public void signIn(String login, String password) {

		setURL(getUrlAdminPanel());
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.inputLogin(login).inputPassword(password).clickOnLoginButton();
		userPage = new UserPage(getDriver());
	}

	@Step("Проверка титула страницы на вкладке \"Dashboard\".")
	@Story("Проверка титула страницы на вкладке \"Dashboard\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Dashboard\" после обновления страницы.", alwaysRun = true)
	public void checkTitleDashboard() {

		title = userPage.clickOnDashboard().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Заказы\".")
	@Story("Проверка титула страницы на вкладке \"Заказы\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Заказы\" после обновления страницы.", alwaysRun = true)
	public void checkTitleOrder() {

		title = userPage.clickOnOrders().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Каталог\".")
	@Story("Проверка титула страницы на вкладке \"Каталог\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Каталог\" после обновления страницы.", alwaysRun = true)
	public void checkTitleCatalog() {

		title = userPage.clickOnCatalog().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Клиенты\".")
	@Story("Проверка титула страницы на вкладке \"Клиенты\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Клиенты\" после обновления страницы.", alwaysRun = true)
	public void checkTitleCustomer() {

		title = userPage.clickOnCustomer().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Служба поддержки\".")
	@Story("Проверка титула страницы на вкладке \"Служба поддержки\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Служба поддержки\" после обновления страницы.", alwaysRun = true)
	public void checkTitleCustomerService() {

		title = userPage.clickOnCustomerService().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Статистика\".")
	@Story("Проверка титула страницы на вкладке \"Статистика\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Статистика\" после обновления страницы.", alwaysRun = true)
	public void checkTitleStatistics() {

		title = userPage.clickOnStatistics().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Modules\".")
	@Story("Проверка титула страницы на вкладке \"Modules\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Modules\" после обновления страницы.", alwaysRun = true)
	public void checkTitleModules() {

		title = userPage.clickOnModules().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Design\".")
	@Story("Проверка титула страницы на вкладке \"Design\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Design\" после обновления страницы.", alwaysRun = true)
	public void checkTitleDesign() {

		title = userPage.clickOnDesign().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Delivery\".")
	@Story("Проверка титула страницы на вкладке \"Delivery\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Delivery\" после обновления страницы.", alwaysRun = true)
	public void checkTitleDelivery() {

		title = userPage.clickOnDelivery().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Способ оплаты\".")
	@Story("Проверка титула страницы на вкладке \"Способ оплаты\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Способ оплаты\" после обновления страницы.", alwaysRun = true)
	public void checkTitlePayment() {

		title = userPage.clickOnPayment().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"International\".")
	@Story("Проверка титула страницы на вкладке \"International\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"International\" после обновления страницы.", alwaysRun = true)
	public void checkTitleInternational() {

		title = userPage.clickOnInternational().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Shop Parameters\".")
	@Story("Проверка титула страницы на вкладке \"Shop Parameters\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Shop Parameters\" после обновления страницы.", alwaysRun = true)
	public void checkTitleShopParameters() {

		title = userPage.clickOnShopParameters().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Проверка титула страницы на вкладке \"Конфигурация\".")
	@Story("Проверка титула страницы на вкладке \"Конфигурация\".")
	@Test(dependsOnMethods = "signIn", groups = "title", description = "Проверка титула страницы на вкладке \"Конфигурация\" после обновления страницы.", alwaysRun = true)
	public void checkTitleAdvancedParameters() {

		title = userPage.clickOnAdvancedParameters().getTitle();
		titleRefresh = userPage.refreshPage().getTitle();

		Asserts.assertVariable(title, titleRefresh);
	}

	@Step("Выход из админ панели сайта.")
	@Story("Выход из админ панели сайта.")
	@Test(dependsOnMethods = "signIn", dependsOnGroups = "title", description = "Выход с аккаунта.", alwaysRun = true)
	public void logOut() {

		userPage.clickOnImageProfile().clickOnLogOut();
	}
}
