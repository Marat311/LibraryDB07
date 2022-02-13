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
    DashboardPage dashboardPage = new DashboardPage();
    BooksPage bookpage = new BooksPage();
    List<String> bookInfo;
    List<Map<String, String>> bookCategories;

    String bookName;

    @Given("I am in the homepage of library app")
    public void i_am_in_the_homepage_of_library_app() {
        loginPage.goTo();
        loginPage.login();
    }
    @When("I navigate to {string} page")
    public void i_navigate_to_page(String nameOfModule) {
        BrowserUtil.waitFor(1);
        dashboardPage.navigateTo(nameOfModule);

    }

    @When("I open a book called {string}")
    public void i_open_a_book(String book) {
        BrowserUtil.waitFor(2);
        bookpage.searchBox.sendKeys(book);
        bookName = book;

    }
    @When("I execute query to get the book information from books table")
    public void i_execute_query_to_get_the_book_information_from_books_table() {
        DB_Util.runQuery("select name, author, year FROM books where name = '" + bookName + "'") ;
        bookInfo = DB_Util.getRowDataAsList(1);
        System.out.println("book info = " + bookInfo);



    }

    @Then("verify book db and ui information must match")
    public void verify_book_db_and_ui_information_must_match() {

        System.out.println("Last Step");
//        for (Map<String, String> map : booksName) {
//            for (Map<String, String> stringMap : bookName) {
//                Assert.assertEquals(map, stringMap);
//            }
//
//        }
//        for (Map<String, String> map : bookName) {
//                    for (Map.Entry<String, String> entry : map.entrySet()) {
//                        Assert.assertEquals(bookpage.getBookInfo(entry.getKey()), entry.getValue());
//                    }
//        }
    }



    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {
        DB_Util.runQuery("select name from book_categories");
        bookCategories = DB_Util.getAllRowAsListOfMap();

    }
    @Then("verify book categories must match {string} table from db")
    public void verify_book_categories_must_match_table_from_db(String value) {

        List<String> book_categories = bookpage.book_categoriesValue(bookpage.getBookInfo(value));
       int num = 0;
        for (Map<String, String> map : bookCategories) {
            Assert.assertEquals(map.get("name"),book_categories.get(num) );
            num++;


        }

    }





}
