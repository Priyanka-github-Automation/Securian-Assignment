# Securian-Assignment

Selenium Webdriver - Java - Maven - TestNG

Requirement

Login to Securian retirement savings calculator page 
User should be able to submit form with all required fields filled in 
Additional Social security fields should display/hide based on Social security benefits toggle
User should be able to update default calculator values

Automated the above requirement using Selenium Webdriver, Java, Maven project and TestNG framework.

Prerequisites to run on your local machine

Java version 7 or higher.
Maven installed on machine to compile and run from the command line or an IDE like Eclipse to compile and run.
Chrome driver and gecko driver executable files.
How to Run

clone the project from https://github.com/Priyanka-github-Automation/Securian-Assignment.git to your local machine.
In the testng.xml file, change the values of parameters "chromepath" and "geckopath" to match the path of browser drivers' executable files in your local machine.
To run from the command line, in the terminal, navigate to the corresponding folder and give the maven command 'mvn test'.
To run in an IDE, import the project into your IDE, open the TestNG.xml file in the path src/test/resources and run as TestNG Suite
