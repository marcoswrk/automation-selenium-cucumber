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

  Scenario: Place Order and Register while Checkout and Account deletion
    Given i have added a product to my cart
    And i register myself on the website
    When i do the checkout and place my order
    And i get a confirmation message for the order
    Then i delete my account

  Scenario: Register before checkout place order and delete account
    Given i register on the website
    And add products to my cart
    When i proceed to finish my order
    Then i get the order finished and delete my account

  Scenario: Login before checkout place order and delete account
    Given i do login
    And add products to cart and check out
    When i comment on text area and confirm order
    Then i get a confirmation message and delete account

  Scenario: Remove products from cart
    Given i navigate to the products page
    When i add a product and see it displayed
    Then i click on the x button and verify that the product is removed

  Scenario: View category products
    Given i verified the categories visible on the left side of products page
    When i click on the Women category: Dress
    Then i verify that the page displayed contains the expected text

  Scenario: View brand products
    Given i verified the brand visible on the left side of products page
    When i click on a brand name
    Then i verify that the page displayed contains the expected brand products

  Scenario: Search Products and Verify Cart After Login
    Given i searched for products
    When i added this products to my cart
    And logged in the website
    Then i go to the cart page and the products are still added

  Scenario: Add review on product
    Given i click on the products button
    When i click on view product button
    And submit a review for the product
    Then i get a success message for the review

  Scenario: Add to cart from Recommended items
    Given i scroll to the bottom of the home page
    When i add a recommended product
    Then i get the product displayed in the cart page

  Scenario: Verify address details in checkout page
    Given i create my account and add products to my cart
    When i proceed to the checkout page
    Then i confirm address and billing address are correct and delete my account

  Scenario: Download Invoice after purchase order
    Given i add products to cart and register on the website
    When i get successfully proceeded to confirm my order
    Then i get to successfully download the invoice and delete my account

  Scenario: Verify Scroll Up using 'Arrow' button
    Given i navigate to the bottom page
    When i click o the arrow button at the botton right of the page
    Then i verify that page is scrolled up
