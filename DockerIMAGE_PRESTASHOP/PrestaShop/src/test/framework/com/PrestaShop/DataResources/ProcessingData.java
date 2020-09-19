package com.PrestaShop.DataResources;

import static com.PrestaShop.Report.Report.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ProcessingData {

	public static final String sep = File.separator;
	public static final String path = "src" + sep + "test" + sep + "resources" + sep + "ProjectData" + sep;

	public static final String pathToURLs = path + "URLsData.properties";
	public static final String pathToCustomerData = path + "CustomerData.properties";

	public static String getProperty(InputStream fileProper, String key) {

		Properties prop = new Properties();

		try {
			prop.load(fileProper);
		} catch (IOException e) {
			addAttachmentToReport("Exception: IOException", e.getMessage());
		}

		String strProp = prop.getProperty(key);

		return strProp;
	}

	public static List<String> getListProperty(InputStream fileProper, String key) {

		Properties prop = new Properties();

		try {
			prop.load(fileProper);
		} catch (IOException e) {
			addAttachmentToReport("Exception: IOException", e.getMessage());
		}

		String strData = prop.getProperty(key);
		List<String> listData = Arrays.asList(strData.split(","));

		return listData;
	}
}