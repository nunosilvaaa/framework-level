#Tags: @Regression
#Area: Product Subscription

@Regression
Feature: Product Subscription

	# TC004
  Scenario: Validate the product price for a full special support plan for 6 months simulation
    Given I open Chrome and launch the application
	  When I select type "Special"
	  And I select support plan "Full"
	  And I write monthly duration of "6"
	  Then I click in calculate price button
	  And I validate price is "2249.10 $"