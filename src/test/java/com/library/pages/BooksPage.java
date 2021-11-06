package com.library.pages;

import com.library.utilities.BrowserUtil;
import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class BooksPage {





    public BooksPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void findBook(String bookName){
        String editBook = "/../td";
        BrowserUtil.checkVisibilityOfElement(By.xpath("(//tr/td[text()='"+bookName+"'])[1]"+editBook+""), 3);

        Driver.getDriver().findElement(By.xpath("(//tr/td[text()='"+bookName+"'])[1]"+editBook+"")).click();

    }

    public String getBookInfo(String info) {

          String value = "";
        if (info.equals("author")) {
            value = Driver.getDriver().findElement(By.xpath("(//input[@type = 'text'])[" + 4 + "]")).getAttribute("value");
        } else if (info.equals("year")) {
            value= Driver.getDriver().findElement(By.xpath("(//input[@type = 'text'])[" + 3 + "]")).getAttribute("value");

        } else if (info.equals("name")) {
            value= Driver.getDriver().findElement(By.xpath("(//input[@type = 'text'])[" + 1 + "]")).getAttribute("value");

        } else if (info.equals("ISBN")) {
            value = Driver.getDriver().findElement(By.xpath("(//input[@type = 'text'])[" + 2 + "]")).getAttribute("value");

        }else if (info.equals("Book Category")){
          //  Select select = new Select(Driver.getDriver().findElement(By.xpath("//select[@id='book_categories']")));
          //  select.getOptions();
            value = Driver.getDriver().findElement(By.xpath("//select[@id='book_categories']")).getText();
        }

        return value;
    }

}
