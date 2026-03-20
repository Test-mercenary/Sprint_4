package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.Constants;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.get(Constants.BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}