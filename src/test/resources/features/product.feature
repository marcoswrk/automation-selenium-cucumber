Feature: Scenario for the product page
  As a user
  I should be able to
  Go to the product page and see what is available

  Scenario: Access the product and product details page
    Given i accessed the Products page
    When i click on the first item view product button
    Then i get successfully redirected to the product details page

  Scenario: Search Product
    Given  i accessed the Products page for search
    When  i put a product name and and click search button
    Then  i get all the products related search visible

  Scenario: Verify Subscription in home page
    When  i put my email and click over the arrow
    Then i receive a message for successfully subscribing

  Scenario: Verify Subscription in cart page
    Given  i accessed the cart page
    When  i put my email and click over the arrow at cart page
    Then i receive a message for successfully subscribing via cart page

  Scenario: Add Products in Cart
    Given i have already added the first product to my cart
    And i click on the continue shopping button
    When i add a second product to my cart
    And i proceed to the checkout cart
    Then i should see both products and prices in my shopping cart
