package com.PrestaShop.test4;

import static com.PrestaShop.DataResources.GetRandomPayment.*;
import static com.PrestaShop.DataResources.GetUrlSite.*;
import static com.PrestaShop.Report.Report.addAttachmentToReport;

import org.testng.annotations.Test;

import com.PrestaShop.Asserts.Asserts;
import com.PrestaShop.DataResources.GetCustomerAddress;
import com.PrestaShop.DataResources.GetCustomerData;
import com.PrestaShop.InitialConfiguration.InitialConfiguration;
import com.PrestaShop.Store.*;

import io.qameta.allure.*;

@Epic("Тестовый набор 4.")
@Feature("Проверка покупки товара.")
public class Ordering extends InitialConfiguration {

	private String nameProduct;
	private String priceProduct;
	private String quantityAllProduct;
	private String quantityProduct = "1";

	private Product product;
	private Cart cart;
	private PersonalOrderData orderData;
	private DeliveryAddress deliveryAddress;
	private DeliveryMethod deliveryMethod;
	private PaymentOfAnOrder payment;
	private ConfirmationOfAnOrder order;

	@Step("Выбор случайного товара из предложенных товаров.")
	@Story("Выбор случайного товара из предложенных товаров.")
	@Test(description = "Выбор случайного товара.", alwaysRun = true)
	public void choiceOfRandomGoods() {

		setURL(getUrlSite());

		HomePage homePage = new HomePage(getDriver());

		product = homePage.checkingTheVersionOfTheSite().clickOnAllProduct().clickOnProduct();
	}

	@Step("Получаем свойства выбранного товара.")
	@Story("Получаем свойства выбранного товара.")
	@Test(dependsOnMethods = "choiceOfRandomGoods", description = "Взятие свойств товара.", alwaysRun = true)
	public void productProperties() {

		nameProduct = product.getNameProduct();
		priceProduct = product.getPriceProduct();
		quantityAllProduct = product.getQuantityProduct();

		addAttachmentToReport("Имя товара.", nameProduct);
		addAttachmentToReport("Цена товара.", priceProduct);
		addAttachmentToReport("Количество товара.", quantityAllProduct);

	}

	@Step("Добавляем выбранный товар в корзину.")
	@Story("Добавляем выбранный товар в корзину.")
	@Test(dependsOnMethods = "productProperties", description = "Добавление товара в корзину.", alwaysRun = true)
	public void addingProductToCart() {

		cart = product.addToCard().clearanceOfProduct();
	}

	@Step("Проверяем свойства продукта добавленного в корзину.")
	@Story("Проверяем свойства продукта добавленного в корзину.")
	@Test(dependsOnMethods = "addingProductToCart", description = "Проверка свойств продукта.", alwaysRun = true)
	public void checkingPropertiesProduct() {

		Asserts.assertVariable(cart.getNameProductInCart().toUpperCase(), nameProduct.toUpperCase(),
				"Разные названия товара.");
		Asserts.assertVariable(cart.getPriceProductInCart(), priceProduct, "Разная цена на товар.");
		Asserts.assertVariable(cart.getQuantityProductInCart(), quantityProduct, "Разное количество товара.");

		orderData = cart.clickOnPlaceOrder();

		addAttachmentToReport("Имя товара.", nameProduct);
		addAttachmentToReport("Цена товара.", priceProduct);
		addAttachmentToReport("Количество товара.", quantityAllProduct);

	}

	@Step("Личные данные заказчика для оформления товара.")
	@Story("Личные данные заказчика для оформления товара.")
	@Test(dependsOnMethods = "checkingPropertiesProduct", description = "Ввод личных данных заказчика.", dataProvider = "getCustomerData", dataProviderClass = GetCustomerData.class, alwaysRun = true)
	public void inputCustomerData(String firstName, String lastName, String email) {

		deliveryAddress = orderData.inputFirstName(firstName).inputLastName(lastName).inputEmail(email)
				.clickOnButtonContinueInform();
	}

	@Step("Адресс заказчика для доставки товара.")
	@Story("Адресс заказчика для доставки товара.")
	@Test(dependsOnMethods = "inputCustomerData", description = "Ввод адресса доставки товара.", dataProvider = "getCustomerAddress", dataProviderClass = GetCustomerAddress.class, alwaysRun = true)
	public void inputDeliveryAddressData(String address, String postcode, String city) {

		deliveryMethod = deliveryAddress.addAddress().inputAddress(address).inputPostcode(postcode).inputCity(city)
				.clickOnButtonContinueAddress();
	}

	@Step("Доставка товара.")
	@Story("Доставка товара.")
	@Test(dependsOnMethods = "inputDeliveryAddressData", description = "Доставка товара.", alwaysRun = true)
	public void deliveryMethod() {
		payment = deliveryMethod.clickOnButtonContinueDelivery();
	}

	@Step("Выбор случайного метода оплаты.")
	@Story("Выбор случайного метода оплаты.")
	@Test(dependsOnMethods = "deliveryMethod", description = "Оплата товара.", alwaysRun = true)
	public void paymentMethod() {

		order = payment.paymentMethod(getRandomPayment()).termsOfAgreement().clickOnButtonOrder();
	}

	@Step("Проверка свойств товара в заказе.")
	@Story("Проверка свойств товара в заказе.")
	@Test(dependsOnMethods = "paymentMethod", description = "Проверка свойств товара в заказе.", alwaysRun = true)
	public void checkingPropertiesOrder() {

		Asserts.assertVariable(order.getOrderTitle(), "ВАШ ЗАКАЗ ПОДТВЕРЖДЁН", "Разное сообщение о заказе.");
		Asserts.assertVariable(order.getOrderQuantityProduct(), quantityProduct, "Разное количество товара.");
		Asserts.assertContainsVariable(order.getOrderNameProduct(), nameProduct.toUpperCase(),
				"Разное название продукта.");
		Asserts.assertVariable(order.getOrderPriceOrder(), priceProduct, "Разная цена товара.");
	}

	@Step("Проверка изменения количества товара после выполнения заказа.")
	@Story("Проверка изменения количества товара после выполнения заказа.")
	@Test(dependsOnMethods = "checkingPropertiesOrder", description = "Проверка количества товара после заказа.", alwaysRun = true)
	public void checkingQuantityProduct() {

		product = order.selectAllProduct().clickOnRandomProductAgain();

		Integer quantity = product.getQuantityProductInt();
		Integer balance = Integer.parseInt(quantityAllProduct) - Integer.parseInt(quantityProduct);

		Asserts.assertEqualVariable(quantity, balance,
				"Количество товаров не изменилось или изменилось более чем на указанное количество.");
	}
}