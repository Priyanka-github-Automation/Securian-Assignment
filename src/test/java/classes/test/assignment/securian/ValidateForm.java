package classes.test.assignment.securian;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;





public class ValidateForm {
	
	WebDriver driver = null;
	
	//This method initializes the browser based on parameter value coming from testng.xml file and opens the AUT 
	@BeforeMethod
	@Parameters({"browsername","url", "chromepath", "geckopath"})
	public void setup(String browsername, String url, String chromepath, String geckopath) {
		
		
		switch (browsername) {
		
		case "firefox":
			System.setProperty("webdriver.gecko.driver", geckopath);
			driver = new FirefoxDriver();
			break;
			
		case "chrome":
			System.setProperty("webdriver.chrome.driver", chromepath);
			driver = new ChromeDriver();
			break;
		}
		
		
		System.setProperty("webdriver.chrome.driver", "/Users/priyankakodeboina/Documents/Selenium/Webdrivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.securian.com/insights-tools/retirement-calculator.html");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	
	//This method quits the browser after execution of the test method
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	//Data provider with 2 sets of test data
	@DataProvider(name = "test-data")
	public Object[][] dataProvFunc(){
	    return new Object[][]{
	           {"40","68","100000","75000","500000","10","1","yes","married","4000","500","20","yes","75","8","5"},
	           { "35","68","175000","90000","50000","10","1","no","married","4000","500","20","yes","75","8","5"}
	    };
	}
	
	
	//This method locates all the elements in the form, fills them and submits the form
	@Test(dataProvider ="test-data")
	    public void enterValues(String cAge, String rAge, String cIncome, String sIncome, String tSavings, 
	    		String aSavings, String rateInc, String socialBenefits, String maritalStatus,
	    		String ssnAmt, String addIncome, String retirementDur, String inflation, String retirementAnIncome, 
	    		String preRetirement, String postRetirement ) {
		
		//Actions class is used to enter values in some elements which are not interactable otherwise
		Actions act =  new Actions(driver);
		
		
		//locate form elements
		
		WebElement currentAge = driver.findElement(By.id("current-age"));
		WebElement retirementAge = driver.findElement(By.id("retirement-age"));
		WebElement currentIncome = driver.findElement(By.id("current-income"));
		WebElement spouseIncome = driver.findElement(By.id("spouse-income"));
		WebElement totalSavings = driver.findElement(By.id("current-total-savings"));
		WebElement annualSavings = driver.findElement(By.id("current-annual-savings"));
		WebElement rateIncrease = driver.findElement(By.id("savings-increase-rate"));
		WebElement socialBenefitsYes = driver.findElement(By.xpath("//label[@for='yes-social-benefits']"));
		WebElement socialBenefitsNo = driver.findElement(By.xpath("//label[@for='no-social-benefits']"));
		WebElement maritalNo = driver.findElement(By.xpath("//label[@for='single']"));
		WebElement maritalYes = driver.findElement(By.xpath("//label[@for='married']"));
		WebElement ssnOverrideAmt = driver.findElement(By.id("social-security-override-container"));
		WebElement defaultValues = driver.findElement(By.linkText("Adjust default values"));
		WebElement additionalIncome = driver.findElement(By.id("additional-income"));
		WebElement retirementDuration = driver.findElement(By.id("retirement-duration"));
		WebElement inflationYes = driver.findElement(By.xpath("//label[@for='include-inflation']"));
		WebElement inflationNo = driver.findElement(By.xpath("//label[@for='exclude-inflation']"));
		WebElement retirementAnnualIncome = driver.findElement(By.id("retirement-annual-income"));
		WebElement preRetirementRoi = driver.findElement(By.id("pre-retirement-roi"));
		WebElement postRetirementRoi = driver.findElement(By.id("post-retirement-roi"));
		WebElement saveChangesBtn = driver.findElement(By.xpath("//button[normalize-space()='Save changes']"));
		WebElement calculateResultsBtn = driver.findElement(By.xpath("//button[normalize-space()='Calculate']"));
		
		
		//Fills the form with data from data provider
		currentAge.sendKeys(cAge);
		retirementAge.sendKeys(rAge);
		act.sendKeys(currentIncome, cIncome).build().perform();
		act.sendKeys(spouseIncome, sIncome).build().perform();
		act.sendKeys(totalSavings, tSavings).build().perform();
		annualSavings.sendKeys(aSavings);
		rateIncrease.sendKeys(rateInc);
		
		//Additional social security fields get populated based on input for social security benefits, yes or no
		if (socialBenefits.equalsIgnoreCase("yes")) {
			socialBenefitsYes.click();
			if(maritalStatus.equalsIgnoreCase("married")) {
				maritalYes.click();
			}
			else if(maritalStatus.equalsIgnoreCase("single")) {
				maritalNo.click();
			}
			act.sendKeys(ssnOverrideAmt, ssnAmt).build().perform();
				
		}
		else if(socialBenefits.equalsIgnoreCase("No")) {
			socialBenefitsNo.click();
		}
			
		defaultValues.click();
		act.sendKeys(additionalIncome, addIncome).build().perform();
		retirementDuration.sendKeys(retirementDur);
		
		if(inflation.equalsIgnoreCase("yes")) {
			inflationYes.click();
		}
		else if(inflation.equalsIgnoreCase("no")) {
			inflationNo.click();
		}
		
		act.sendKeys(retirementAnnualIncome, retirementAnIncome).build().perform();
		preRetirementRoi.sendKeys(preRetirement);
		postRetirementRoi.sendKeys(postRetirement);
		saveChangesBtn.click();
		
		//Submit the form
		calculateResultsBtn.click();
	
	}
	
	

}
