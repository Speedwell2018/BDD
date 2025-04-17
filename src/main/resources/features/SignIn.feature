Feature: Shopping Automation
Scenario: Testing the authentication
Given I go to theWebSite
When I click on Sign In button
And I specify my credentials and click Login
Then I can log into the website

Scenario: Testing the purchase of two items
    Given I go to theWebSite
    When I add one element to the cart
    And I proceed to checkout
    And I confirm address, shipping, payment and final order
    Then The elements are bought
