package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    @FindBy(id = "user_count")
    public WebElement userCount;

    @FindBy(id = "book_count")
    public WebElement bookCount;

    @FindBy(id = "borrowed_books")
    public WebElement borrowedBookCount;


    public DashboardPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public Integer getUsersCount(){

        String userCountText = this.userCount.getText();
        Integer userCount = Integer.parseInt(userCountText);
        return userCount;
    }

    public Integer getBookCount(){
        String bookCountText = this.bookCount.getText();
        Integer bookCount = Integer.parseInt(bookCountText);
        return bookCount;
    }

    public Integer getBorrowedBookCount(){
        String borrowedBookCountText = this.borrowedBookCount.getText();
        Integer borrowedBookCount = Integer.parseInt(borrowedBookCountText);
        return borrowedBookCount;
    }




}
