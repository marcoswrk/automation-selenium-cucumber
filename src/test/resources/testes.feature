Feature: Automacao Selenium
  As a user
  I should be able to
  Register myself on the website

  Scenario: Register User
    Given i click on the signup button for register
    When i register my credentials
    Then i get the signup completed

  Scenario: Login User with correct data
    Given i click on the login button
    When i register my credentials
    Then i get the login completed

  Scenario: Login User with incorrect data
    Given i click on the login button to login
    When i register wrong credentials
    Then i get a warning to correct email and password

  Scenario: Logout User
    Given i am already logged
    When i click on the logout button
    Then i get successfully logged out

  Scenario: Register User with existing email
    Given i click on the signup button
    When  i register an already used email
    Then  i get an error for the email