package com.PrestaShop.DataResources;

import static com.PrestaShop.Report.Report.addAttachmentToReport;
import static com.PrestaShop.DataResources.ProcessingData.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.annotations.DataProvider;

public class GetLoginAndPassword{
	
	private final String loginAdmin = "LoginAdmin";
	private final String passwordAdmin = "PasswordAdmin";
	private final String pathToLoginData = path + "DataLoginInAdmin.properties";
	
	@DataProvider
	public Object[][] getLoginAndPassword() {

		String login = "";
		String password = "";
		try {
			login = getProperty(new FileInputStream(pathToLoginData), loginAdmin);
			password = getProperty(new FileInputStream(pathToLoginData), passwordAdmin);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}
		return new Object[][] { { login, password } };
	}
}
