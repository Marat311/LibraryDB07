package com.library.step_definitions;

import com.library.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class BooksBorrowStepDefs {

    @When("I execute query to left outer inner join books and book_borrow on Book_Id")
    public void i_execute_query_to_left_outer_inner_join_books_and_book_borrow_on_book_id() {
        String query="SELECT  b.name ,bw.id FROM books b LEFT OUTER JOIN  book_borrow bw on b.id = bw.book_id ;";
        DB_Util.runQuery(query);
    }
    @Then("verify what is the most popular book genre.")
    public void verify_what_is_the_most_popular_book_genre() {
        int bookFromBookTable = DB_Util.getColumnDataAsList(1).size();
        int bookFromBook_BorrowTable = DB_Util.getColumnDataAsList(2).size();
        assertEquals(bookFromBookTable, bookFromBook_BorrowTable );
    }
}
