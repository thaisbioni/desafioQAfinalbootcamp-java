package org.openweathermap.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static final String DRIVER_CHROME = "C:\\dev\\chromedriver.exe";

    static WebDriver driver;

    public static void abrirChrome(String url) {
        System.setProperty("webdriver.chrome.driver", DRIVER_CHROME);
        driver = new ChromeDriver();
        driver.get(url);
        System.out.println("Navegador aberto");
    }

    public static void fecharChrome() {
        driver.quit();
        System.out.println("Navegador fechado");
    }

}