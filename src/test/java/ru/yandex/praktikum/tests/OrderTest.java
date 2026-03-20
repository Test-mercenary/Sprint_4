package ru.yandex.praktikum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pageobjects.MainPage;
import ru.yandex.praktikum.pageobjects.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String orderButtonType;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String orderButtonType,
                     String name,
                     String surname,
                     String address,
                     String metro,
                     String phone,
                     String date,
                     String rentalPeriod,
                     String color,
                     String comment) {
        this.orderButtonType = orderButtonType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"top", "Андрей", "Иванов", "Москва, Ленина 10", "Черкизовская", "89991112233", "20.03.2026", "сутки", "black", "Позвоните заранее"},
                {"bottom", "Мария", "Петрова", "Москва, Пушкина 15", "Сокольники", "89994445566", "21.03.2026", "двое суток", "grey", "Код домофона 45"}
        };
    }

    @Test
    public void createOrderTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.acceptCookies();

        if ("top".equals(orderButtonType)) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        orderPage.fillFirstStep(name, surname, address, metro, phone);
        orderPage.fillSecondStep(date, rentalPeriod, color, comment);
        orderPage.createOrder();

        Assert.assertTrue(orderPage.isSuccessOrderDisplayed());
    }
}