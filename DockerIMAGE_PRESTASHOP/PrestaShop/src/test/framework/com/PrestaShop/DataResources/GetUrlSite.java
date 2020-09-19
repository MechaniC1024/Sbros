package com.PrestaShop.DataResources;

import static com.PrestaShop.Report.Report.addAttachmentToReport;
import static com.PrestaShop.DataResources.ProcessingData.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GetUrlSite {
	
	private static final String urlSite = "UrlSite";
	
	public static String getUrlSite() {
		String url = "";
		try {
			url = getProperty(new FileInputStream(pathToURLs), urlSite);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}
		return url;
	}
}
