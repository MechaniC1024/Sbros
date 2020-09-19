package com.PrestaShop.DataResources;

import static com.PrestaShop.Report.Report.addAttachmentToReport;
import static com.PrestaShop.DataResources.ProcessingData.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class GetCustomerAddress {

	private final String customerAddress = "Address";

	@DataProvider
	public Object[][] getCustomerAddress() {

		List<String> listData = new ArrayList<>();

		try {
			listData = getListProperty(new FileInputStream(pathToCustomerData), customerAddress);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}

		Object arrayData[] = listData.toArray();
		return new Object[][] { arrayData };
	}
}
