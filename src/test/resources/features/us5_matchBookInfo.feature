@us5
Feature: As a data consumer, I want UI and DB book information are match.

  @db
  Scenario: verify book information with DB
    Given I am in the homepage of library app
    When I navigate to "Books" page
    And I open a book called "Chordeiles minor"
    When Establish the database connection
    And I execute query to get the book information from books table
    Then verify book db and ui information must match


@db @ui
  Scenario: verify book categories with DB
    Given I am in the homepage of library app
    When I navigate to "Books" page
    And I open a book called "Harry Potter"
    When Establish the database connection
    And I execute query to get book categories
    Then verify book categories must match "book_categories" table from db
