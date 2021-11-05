package com.library.pages;

import com.library.utilities.ConfigReader;
import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputEmail")
    public WebElement usernameBox;

    @FindBy(id = "inputPassword")
    public WebElement passwordBox;

    @FindBy(xpath = "//button")
    public WebElement loginButton;

    public LoginPage (){

        PageFactory.initElements(Driver.getDriver(), this);

    }

    public void goTo() {

        Driver.getDriver().get(ConfigReader.read("libraryCT.url"));

    }

    public void login() {

        this.usernameBox.sendKeys(ConfigReader.read("libraryCT.username"));
        this.passwordBox.sendKeys(ConfigReader.read("libraryCT.password"));
        this.loginButton.click();

    }

}
