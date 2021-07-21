#Tags: @Regression
#Area: Users

@Regression
Feature: User Access Management

	# TC002
  Scenario: Add a new user with a job
    Given I create the user creation service
	  When I set name "Toy"
	  And I set name "Singer"
	  Then I submit the user creation request
	  And I validate the user has been created
	  
	# TC003
	Scenario: Delete a existing user
    Given I create the user creation service
	  Then I submit the user deletion request
	  And I validate the user has been deleted
	  
	# TC004
	Scenario: Register a new user without password
    Given I create the user registration service
	  When I set email "challenge@automation.com"
	  Then I submit the user registration request
	  And I validate the user registration was not successful
	  