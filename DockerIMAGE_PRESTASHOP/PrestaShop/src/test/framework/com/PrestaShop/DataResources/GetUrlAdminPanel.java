package com.PrestaShop.DataResources;

import static com.PrestaShop.Report.Report.addAttachmentToReport;
import static com.PrestaShop.DataResources.ProcessingData.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GetUrlAdminPanel {

	private static final String urlAdmin = "UrlAdmin";

	public static String getUrlAdminPanel() {
		String url = "";
		try {
			url = getProperty(new FileInputStream(pathToURLs), urlAdmin);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}
		return url;
	}
}
