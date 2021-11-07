package com.library.pages;

import com.library.utilities.BrowserUtil;
import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


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

        }else if (info.equals("book_categories")){
           Select select = new Select(Driver.getDriver().findElement(By.xpath("//select[@id='"+info+"']")));
            List <WebElement> selectOptions= select.getOptions();
            for (WebElement option : selectOptions) {
                value+= option.getText()+",";
            }

        }

        return value;
    }

    public List<String> book_categoriesValue(String data){
        String []books = data.split(",");
        List <String> actualR = new ArrayList<>();
        for (int i =1; i<books.length; i++){
            actualR.add(books[i]);
        }
        return actualR;
    }

   /* public Map<String, String> getUniqueValue(){
        BiFunction<String, String, Map<String, String>> mergeArraysIntoList = (a, b) -> {

            Map<String, String> dataValue = new LinkedHashMap<>();

            for (int each : a)
                list.add(each);

            for (int each : b)
                list.add(each);

            return list;
        };
    }

    */

    public List<String> getBookInformation (String info){
        List<String> value= new ArrayList<>();
        Driver.getDriver().findElement(By.xpath("//label[text()='Search:']/input")).sendKeys("Djoan Rowling");

        List<WebElement>list = Driver.getDriver().findElements(By.xpath("//td[text()='"+info+"']"));
        for (WebElement webElement : list) {
            value.add(webElement.getText());
        }
        return value;

    }

}
