package com.library.step_definitions;

import com.library.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BooksStepDefs {
    @When("I execute query to find most popular user")
    public void i_execute_query_to_find_most_popular_user() {

        DB_Util.runQuery("select u.full_name, count(*) from users u inner join book_borrow bb on u.id = bb.user_id\n" +
                "group by u.full_name order by count(*) desc");


    }

    @Then("verify {string} is the user who reads the most")
    public void verify_is_the_user_who_reads_the_most(String expectedUser) {

        String actualUser = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedUser, actualUser);
    }


}
