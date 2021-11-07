package com.library.step_definitions;

import com.library.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class UserStory1_stepDefs {

    List<String> listOfIDs = new ArrayList<>();

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        DB_Util.runQuery("select Id from users");
        listOfIDs = DB_Util.getColumnDataAsList(1);


    }

    @Then("verify the result set")
    public void verify_the_result_set() {

        int count = 0;

        for (String s : listOfIDs) {
            for (String f : listOfIDs) {
                if (s.equals(f)) {
                    count++;
                }
            }
        }
        System.out.println("count" + count + " , list of IDs" + listOfIDs.size());
    }

    List<String> actualList = new ArrayList<>();
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        DB_Util.runQuery("select * from users");
        actualList = DB_Util.getAllColumnNamesAsList();
        System.out.println("actualList = " + actualList);

    }



    @Then("verify the below columns are listed in result")
    public void verifyTheBelowColumnsAreListedInResult(List<String> expectedList) {
        System.out.println("expectedList = " + expectedList);

        Assertions.assertEquals(expectedList, actualList);
    }
}
