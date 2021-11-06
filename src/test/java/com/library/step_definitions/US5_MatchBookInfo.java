package com.library.step_definitions;

import com.library.pages.BooksPage;
import com.library.pages.DashboardPage;
import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtil;
import com.library.utilities.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;


import java.util.List;
import java.util.Map;

public class US5_MatchBookInfo {

    LoginPage loginPage = new LoginPage();
    DashboardPage dp = new DashboardPage();
    BooksPage b = new BooksPage();
    List<Map<String, String>> booksName;
    List<Map<String, String>> bookCategories;

    @Given("I am in the homepage of library app")
    public void i_am_in_the_homepage_of_library_app() {
        loginPage.goTo();
        loginPage.login();
    }
    @When("I navigate to {string} page")
    public void i_navigate_to_page(String nameOfModule) {
        BrowserUtil.waitFor(1);
        dp.navigateTo(nameOfModule);

    }
    @When("I open a book called {string}")
    public void i_open_a_book_called_harry_potter(String bookName) {
        b.findBook(bookName);

    }
    @When("I execute query to get the book information from books table")
    public void i_execute_query_to_get_the_book_information_from_books_table() {
        DB_Util.runQuery("select name, author, year FROM books where name = 'Harry Potter' and year=2000 and author='Djoan Rowling'") ;
        booksName = DB_Util.getAllRowAsListOfMap();
        System.out.println("booksName = " + booksName);


    }

    @Then("verify book db and ui information must match")
    public void verify_book_db_and_ui_information_must_match(List<Map<String,String>> bookName) {

        for (Map<String, String> map : booksName) {
            for (Map<String, String> stringMap : bookName) {
                Assert.assertEquals(map, stringMap);
            }

        }
        for (Map<String, String> map : bookName) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        Assert.assertEquals(b.getBookInfo(entry.getKey()), entry.getValue());
                    }
        }
    }



    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {
        DB_Util.runQuery("select * from book_categories");
        bookCategories = DB_Util.getAllRowAsListOfMap();
        System.out.println("list = " + bookCategories);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        for (Map<String, String> map : bookCategories) {
            Assert.assertEquals(map.get("name"),b.getBookInfo("Book Category"));
        }

    }





}
