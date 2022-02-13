package com.library.step_definitions;


import com.library.utilities.ConfigReader;
import com.library.utilities.DB_Util;
import com.library.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;





import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {


   /* @BeforeAll
    public static void connect(){

        String url = ConfigReader.read("library2.database.url");
        String username = ConfigReader.read("library2.database.username");
        String password = ConfigReader.read("library2.database.password");

        DB_Util.createConnection(url, username, password);
    }
    */


    //close the connection

    @Before("@db")
    public static void dbSetup(){

        String url = ConfigReader.read("library2.database.url");
        String username = ConfigReader.read("library2.database.username");
        String password = ConfigReader.read("library2.database.password");

        DB_Util.createConnection(url, username, password);

        System.out.println("DB connection created");

    }

    @After("@db")
    public static void tearDown(){
        DB_Util.destroy();
    }

    @Before("@ui")
    public void setupDriver(){
        //setup implicit wait
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();

    }

    @After("@ui")
    public void tearDown(Scenario scenario){

        //check if scenario failed or not

        if(scenario.isFailed()){
            //this is how we take screenshot in selenium
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", scenario.getName());

        }

        Driver.closeBrowser();
    }
}
