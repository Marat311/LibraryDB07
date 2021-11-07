package com.library.step_definitions;

import com.library.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class BooksBorrowStepDefs {

    @When("I execute query to left outer inner join books and book_borrow on Book_Id")
    public void i_execute_query_to_left_outer_inner_join_books_and_book_borrow_on_book_id() {
        //combining 3 tables
        String query = "select b.book_category_id, bb.book_id from books b left outer join book_borrow bb on b.id=bb.book_id left outer join book_categories bc on bc.id=b.book_category_id";
        DB_Util.runQuery(query);
    }
    @Then("verify what is the most popular book genre.")
    public void verify_what_is_the_most_popular_book_genre() {
        //getting the list of book_category_ids
        List<String> id = DB_Util.getColumnDataAsList("book_category_id");
        Map<Integer, Integer> idFrequency = new HashMap<>();
        //adding each book_id as a key and the corresponding book_category_id as a value to MAP
        for (String each : id) {
            idFrequency.put(Integer.parseInt(each), Collections.frequency(id,each)); //<book_category_id, frequency of that id>
        }

        int maxValueInMap=(Collections.max(idFrequency.values()));  // This will return max value in the HashMap
        for (Map.Entry<Integer, Integer> entry : idFrequency.entrySet()) {  // Iterate through HashMap
            if (entry.getValue()==maxValueInMap) {
                System.out.println("ID of the most popular genre is: " + entry.getKey());     // Print the book_category_id with max value
            }
        }
        //now that we know the ID of the most popular genre -  we could find the name
        String query2 = "select name from book_categories where id=1";
        DB_Util.runQuery(query2);

        String mostPopularGenre = DB_Util.getCellValue(1,1);
        System.out.println("The name of the most popular genre is: " + mostPopularGenre);

    }
}
