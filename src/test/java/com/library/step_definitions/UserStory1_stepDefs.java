package com.library.step_definitions;

import com.library.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserStory1_stepDefs {

    List<String> listOfIDs = new ArrayList<>();

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        DB_Util.runQuery("select Id from users");
        listOfIDs = DB_Util.getColumnDataAsList(1);

    }


    @Then("verify all users has unique ID")
    public void verifyAllUsersHasUniqueID() {
        Set<String> uniqueIDs = new HashSet<>(listOfIDs);
        System.out.println("listOfIDs.size() = " + listOfIDs.size());
        System.out.println("uniqueIDs.size() = " + uniqueIDs.size());

        Assert.assertEquals(listOfIDs.size(),  uniqueIDs.size() );

        int count = 0;

        for (String s : listOfIDs) {
            count = 0;
            for (String f : listOfIDs) {
                if (s.equals(f)) {
                    count++;
                }
            }
            Assert.assertEquals(count, 1);

        }
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

        Assert.assertEquals(expectedList, actualList);
    }

}
