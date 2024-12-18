#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase order from E-commerce website 

Background:  
Given I landed on loging page
#Title of your feature
#I want to use this template for my feature file

  #@tag1
  #Scenario: Title of your scenario                        
  # This is used to run single test single time and doesn't support parameterization

  @Regression
  Scenario Outline: Positive test of submiting the order		
  #This is same a scenario but it support parameterization and must contain example table for data driven testing
   
    Given I logged in with username <name> and password <password>
    When I added following product to cart 
    | productname 		|
    | <productname>		|
    And checkout the product and submit the order
    | productname 		  |
    | <productname>			|
    Then "Thankyou for the order." is displayed on confirmation page

    Examples: 
      | name				  | password | productname			|
      | qua@gmail.com | Qa@12345 | ADIDAS ORIGINAL  |
      
