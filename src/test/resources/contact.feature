Feature: Scenario for the Contact us Page
  As a user
  I should be able to
  Contact the company by the contact us tab

  Scenario: Access Contact page
    Given i click on the Contact us button
    When i fill out all information
    Then i get a success message for submitting the forms


  Scenario: Access the product and product details page
    Given i accessed the Products page
    When i click on the first item view product button
    Then i get successfully redirected to the product details page