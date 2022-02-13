package com.library.step_definitions;

import com.library.pages.DashboardPage;
import com.library.utilities.DB_Util;
import com.library.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;


public class BooksBorrowStepDefs {
    int countFromUI;

    @When("I execute query to left outer inner join books and book_borrow on Book_Id")
    public void i_execute_query_to_left_outer_inner_join_books_and_book_borrow_on_book_id() {
        //combining 3 tables
        String query1 = "select b.book_category_id, bb.book_id from books b left outer join book_borrow bb on b.id=bb.book_id left outer join book_categories bc on bc.id=b.book_category_id";

        DB_Util.runQuery(query1);
    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedResult) {
//        String queryPractice = "select book_borrow.book_id, books.book_category_id, book_categories.name\n" +
//                "from book_borrow inner join books on book_borrow.book_id = books.id\n" +
//                "                 inner join book_categories on books.book_category_id = book_categories.id";
//
//DB_Util.runQuery(queryPractice);
//List<String> totalGenersBorrowed = DB_Util.getColumnDataAsList(3);
//Set<String> removeDups = new HashSet<>(totalGenersBorrowed);
//
//int maxcount = 0;
//String mostPopular = "";
//        for (String removeDup : removeDups) {
//            int count = 0 ;
//            for (String s : totalGenersBorrowed) {
//
//                if(removeDup.equals(s)){
//                    count++;
//                }
//                if(maxcount<count){
//                    maxcount = count;
//                    mostPopular = s;
//                }
//            }
//        }
//
//        System.out.println("mostPopular = " + mostPopular);
//

        //getting the list of book_category_ids
        List<String> id = DB_Util.getColumnDataAsList("book_category_id");

        Map<Integer, Integer> idFrequency = new HashMap<>();

        //adding each book_id as a key and the corresponding book_category_id as a value to MAP
        for (String each : id) {
            idFrequency.put(Integer.parseInt(each), Collections.frequency(id, each)); //<book_category_id, frequency of that id>
        }

        int idOfTheMostPopularGenre = 0;

        int maxValueInMap = (Collections.max(idFrequency.values()));  // This will return max value in the HashMap
        for (Map.Entry<Integer, Integer> entry : idFrequency.entrySet()) {  // Iterate through HashMap

            if (entry.getValue() == maxValueInMap) {
                System.out.println("ID of the most popular genre is: " + entry.getKey());// Print the key with max value
                idOfTheMostPopularGenre = entry.getKey();
            }
        }

        //now that we know the ID of the most popular genre -  we could find the name
        String query2 = "select name from book_categories where id=" + idOfTheMostPopularGenre;
        DB_Util.runQuery(query2);

        String actualResult = DB_Util.getCellValue(1, 1);
        System.out.println("The name of the most popular genre is: " + actualResult);

        Assert.assertEquals(expectedResult, actualResult);

    }

    @When("I take borrowed books number")
    public void i_take_borrowed_books_number() {

        System.out.println("UI part");
        DashboardPage dashboardPage = new DashboardPage();


        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 3000);
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.borrowedBookCount));

        countFromUI = dashboardPage.getBorrowedBookCount();
        System.out.println("countFromUI = " + countFromUI);

    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        System.out.println(" " + "DB and assertions");

        String query = "select count(*) from book_borrow where returned_date is null";
        DB_Util.runQuery(query);
        String actual = DB_Util.getFirstRowFirstColumn();

        System.out.println("actual = " + actual);

        Assert.assertEquals(actual, "" + countFromUI);


    }


}
