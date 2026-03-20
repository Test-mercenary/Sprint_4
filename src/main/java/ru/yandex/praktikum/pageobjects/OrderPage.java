package ru.yandex.praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private final WebDriver driver;

    // Поле «Имя»
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");

    // Поле «Фамилия»
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле «Адрес: куда привезти заказ»
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле «Станция метро»
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");

    // Поле «Телефон: на него позвонит курьер»
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка «Далее»
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Поле «Когда привезти самокат»
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Выпадающий список «Срок аренды»
    private final By rentalPeriodField = By.className("Dropdown-control");

    // Чекбокс «чёрный жемчуг»
    private final By blackColorCheckbox = By.id("black");

    // Чекбокс «серая безысходность»
    private final By greyColorCheckbox = By.id("grey");

    // Поле «Комментарий для курьера»
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка «Заказать» на втором шаге
    private final By createOrderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");

    // Кнопка подтверждения «Да»
    private final By confirmButton = By.xpath(".//button[text()='Да']");

    // Заголовок окна успешного оформления заказа
    private final By successOrderHeader = By.xpath(".//div[contains(@class,'Order_ModalHeader') and contains(text(),'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setMetro(String metro) {
        driver.findElement(metroField).click();
        driver.findElement(metroField).sendKeys(metro);
        driver.findElement(By.xpath(".//div[@class='select-search__select']//button/div[text()='" + metro + "']")).click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']//div[text()='" + rentalPeriod + "']")).click();
    }

    public void chooseColor(String color) {
        if ("black".equals(color)) {
            driver.findElement(blackColorCheckbox).click();
        } else if ("grey".equals(color)) {
            driver.findElement(greyColorCheckbox).click();
        }
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void fillFirstStep(String name, String surname, String address, String metro, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(metro);
        setPhone(phone);
        clickNextButton();
    }

    public void fillSecondStep(String date, String rentalPeriod, String color, String comment) {
        setDate(date);
        setRentalPeriod(rentalPeriod);
        chooseColor(color);
        setComment(comment);
    }

    public void createOrder() {
        driver.findElement(createOrderButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));

        driver.findElement(confirmButton).click();
    }

    public boolean isSuccessOrderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successOrderHeader)).isDisplayed();
    }
}