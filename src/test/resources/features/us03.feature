@user3
Feature: As a librarian, I want to know genre of books are being borrowed the most.

  @db
  Scenario: verify the the common book genre that’s being borrowed
    Given Establish the database connection
    When I execute query to left outer inner join books and book_borrow on Book_Id
    Then verify "Action and Adventure" is the most popular book genre.