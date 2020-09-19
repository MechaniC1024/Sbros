package com.PrestaShop.test1;

import static com.PrestaShop.DataResources.GetUrlAdminPanel.*;

import org.testng.annotations.Test;

import com.PrestaShop.Admin.*;
import com.PrestaShop.DataResources.GetLoginAndPassword;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;

import io.qameta.allure.*;

@Epic("Тестовый набор 1.")
@Feature("Вход и выход с админ панель сайта.")
public class LoginAndLogout extends InitialConfiguration {

	@Step("Ввод логина и пароля, нажатие кнопки логин.")
	@Story("Ввод логина и пароля, нажатие кнопки логин.")
	@Test(dataProvider = "getLoginAndPassword", dataProviderClass = GetLoginAndPassword.class, description = "Вход в админ панель сайта.", alwaysRun = true)
	public void login(String login, String password) {

		setURL(getUrlAdminPanel());
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.inputLogin(login).inputPassword(password).clickOnLoginButton();
	}

	@Step("Выход из админ панели сайта.")
	@Story("Выход из админ панели сайта.")
	@Test(dependsOnMethods = "login", description = "Выход из админ панели сайта.", alwaysRun = true)
	public void logout() {

		UserPage userPage = new UserPage(getDriver());
		userPage.clickOnImageProfile().clickOnLogOut();

	}
}
