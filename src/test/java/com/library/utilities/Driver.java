package com.library.utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static WebDriver obj;

    private Driver() {}

    public static WebDriver getDriver() {

        String browserName = ConfigReader.read("browser");

        if (obj == null) {

            //according to the browser type set up driver correctly

            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    obj = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    obj = new FirefoxDriver();
                    break;

                default:
                    obj = null;
                    System.out.println("UNKNOWN BROWSER TYPE! " + browserName);

            }
            return obj;
        } else {
            //System.out.println("The object already exists");
            return obj;
        }

    }

    /**
     * Quitting the browser and setting the value of
     * WebDriver instance to null because you can re-use already quitted driver
     */
    public static void closeBrowser() {

        // check if we have WebDriver instance or not
        // basically checking if obj is null or not
        // if not null
        // quit the browser
        // make it null , because once quit it can not be used
        if (obj != null) {
            obj.quit();
            // so when ask for it again , it gives us not quited fresh driver
            obj = null;
        }


    }
}
