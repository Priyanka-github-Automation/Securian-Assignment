package classes.page.assignment.securian;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class PageLocators extends Base{
	
	@FindBy(id="current-age")
	WebElement currentAge;
	
	@FindBy(id="retirement-age")
	WebElement retirementAge;
	
	@FindBy(id="current-income")
	WebElement currentIncome;
	
	@FindBy(id="spouse-income")
	WebElement spouseIncome;
	
	@FindBy(id="current-total-savings")
	WebElement totalSavings;
	
	@FindBy(id="current-annual-savings")
	WebElement annualSavings;
	
	@FindBy(id="savings-increase-rate")
	WebElement rateIncrease;
	
	@FindBy(xpath="//label[@for='yes-social-benefits']")
	WebElement socialBenefitsYes;
	
	@FindBy(xpath="//label[@for='no-social-benefits']")
	WebElement socialBenefitsNo;
	
	@FindBy(xpath="//label[@for='single']")
	WebElement maritalNo;
	
	@FindBy(xpath="//label[@for='married']")
	WebElement maritalYes;
	
	@FindBy(id="social-security-override-container")
	WebElement ssnOverrideAmt;
	
	@FindBy(linkText="Adjust default values")
	WebElement defaultValues;
	
	@FindBy(id="additional-income")
	WebElement additionalIncome;
	
	@FindBy(id="retirement-duration")
	WebElement retirementDuration;
	
	@FindBy(xpath="//label[@for='include-inflation']")
	WebElement inflationYes;
	
	@FindBy(xpath="//label[@for='exclude-inflation']")
	WebElement inflationNo;
	
	@FindBy(id="retirement-annual-income")
	WebElement retirementAnnualIncome;
	
	@FindBy(id="pre-retirement-roi")
	WebElement preRetirementRoi;
	
	@FindBy(id="post-retirement-roi")
	WebElement postRetirementRoi;
	
	@FindBy(xpath="//button[normalize-space()='Save changes']")
	WebElement saveChangesBtn;
	
	@FindBy(xpath="//button[normalize-space()='Calculate']")
	WebElement calculateResultsBtn;
	
	//Initializing PageFactory elements
	
	public PageLocators()
	{
		PageFactory.initElements(driver, this);
		
	}
	
	// Fills the form based on data coming from data provider
	public void form(String cAge,String rAge,String cIncome, String sIncome, String tSavings, 
    		String aSavings, String rateInc, String socialBenefits, String maritalStatus,
    		String ssnAmt, String addIncome, String retirementDur, String inflation, String retirementAnIncome, 
    		String preRetirement, String postRetirement)
	{
		
		//Actions class is used to enter values in some elements which are not interactable otherwise
		Actions act =  new Actions(driver);
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

	//Validates the SSN benefits toggle functionality
	public boolean validationOne(String socialBenefits) {
		
		boolean flag = false;
		if (socialBenefits.equalsIgnoreCase("yes")) {
			socialBenefitsYes.click();
			if (ssnOverrideAmt.isDisplayed())
				flag= true;
			else
				flag= false;
			
		}
		return flag;
		
	}

	
	
	

}
