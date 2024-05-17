package org.example.utils.webdriver;

import org.example.exceptions.WebDriverNotFoundException;
import org.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtil {
    public static WebDriver getWebDriver(DriverType type) throws WebDriverNotFoundException {
        if(type == DriverType.CHROME_DRIVER) {
            return getChromeWebDriver();
        } else if (type == DriverType.EDGE_DRIVER) {
            return getEdgeWebDriver();
        } else if (type == DriverType.FIREFOX_DRIVER) {
            return getFirefoxWebDriver();
        }

        throw new WebDriverNotFoundException();
    }

    private static WebDriver getChromeWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary(Constants.CHROME_DRIVER_PATH);

        return new ChromeDriver();
    }

    private static WebDriver getFirefoxWebDriver() {
        return null;
    }

    private static WebDriver getEdgeWebDriver() {
        return null;
    }
}
