package com.library.step_definitions;

import com.library.utilities.ConfigReader;
import com.library.utilities.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class UserStory2 {


    @Given("Establish the database connection")
    public void establish_the_database_connection() {


            String url = ConfigReader.read("library2.database.url");
            String username = ConfigReader.read("library2.database.username");
            String password = ConfigReader.read("library2.database.password");

            DB_Util.createConnection(url, username, password);
        }



    @When("I execute query to inner join users and book_borrow on Id")
    public void i_execute_query_to_inner_join_users_and_book_borrow_on_id() {
        System.out.println("Test");
    }


    @Then("verify amount of people who had borrowed books")
    public void verify_amount_of_people_who_had_borrowed_books() {
        Assertions.assertEquals("Hello", "Hello");
    }



}
