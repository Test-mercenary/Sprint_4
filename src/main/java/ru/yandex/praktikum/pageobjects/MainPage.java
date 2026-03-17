package ru.yandex.praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;

    // Кнопка принятия cookies
    private final By cookieButton = By.id("rcc-confirm-button");

    // Верхняя кнопка «Заказать»
    private final By topOrderButton = By.xpath(".//button[text()='Заказать']");

    // Нижняя кнопка «Заказать»
    private final By bottomOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        if (!driver.findElements(cookieButton).isEmpty()) {
            driver.findElement(cookieButton).click();
        }
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(bottomOrderButton));
        driver.findElement(bottomOrderButton).click();
    }

    public void clickQuestion(int index) {
        By question = By.id("accordion__heading-" + index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(question));
        driver.findElement(question).click();
    }

    public String getAnswerText(int index) {
        By answer = By.id("accordion__panel-" + index);
        return driver.findElement(answer).getText();
    }
}