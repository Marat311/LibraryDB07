@us04
  Feature: as a librarian I want to know all students who borrowed books

    @db
    Scenario: verify who is most popular user who reads the most
      Given Establish the database connection
      When I execute query to find most popular user
      Then verify "Test Student 344" is the user who reads the most