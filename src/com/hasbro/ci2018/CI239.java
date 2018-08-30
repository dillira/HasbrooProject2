package com.hasbro.ci2018;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Product;

import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

public class CI239 extends TestsuiteBase {
	

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
    static boolean fail=false;
	static boolean isTestPass=true;
    static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	
	
	
	public static By CostingActionDropDown              = By.xpath("//*[@id='costingActions']");
	public static By CostingActionDropDownToUpdate      = By.xpath("//td[contains(text(),'Cost Sheet Details:')]//following::select[1]");
	public static By ToolingNeeded                      = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Tooling Needed')]");
	public static By ToolingNeededCheckBox              = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Tooling Needed')]//following::input[2]");
	public static By ToolingNeededValue                 = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Tooling Needed')]//following::td[1]");
	public static By ComponentCost                      = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Component Cost')]");
	public static By ComponentCostCheckBox              = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Component Cost')]//following::input[2]");
	public static By ComponentCostDefaultValue          = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Component Cost')]//following::td[1]");	
	public static By ComponentNumber                    = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Component Number')]");
	public static By ComponentNumberValue               = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Component Number')]//following::td[1]");
	public static By ComponentNumberTextBoxValue        = By.xpath("//a[@name='General Attributes']//following::td[contains(text(),'Component Number')]//following::input[1]");
	public static By CreateCostSheetHeading             = By.xpath("//td[contains(text(),'Create Product Cost Sheet')]");
	public static By CostSheetIdentificationText        = By.xpath("//td[contains(text(),'Cost Sheet Identification')]");
	public static By SaveButton                         = By.xpath("//a[text()='Save']");
	public static By ErrorMessage                       = By.xpath("//td[@class='ERROR']");
	
	public static By TabNameOrValue;
	
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcCI(String[] data) throws Exception{
		    count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
		
          try{
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
				}
			}
			if(runmodes[count].equalsIgnoreCase("y")){
				if(y==0){
					log.debug("Inside testcase for CI 239");
					log.info("col0 :" + data[0]);
					log.info("col2 :" + data[2]);
					log.info("col3 :" + data[3]);
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			
		switch(data[2])
		{
		
		case "Verify Tooling Needed/Component Cost/Component Number added in General Attributes in Create Cost Sheet Page":
		Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsDefaultValuesInCreateCostSheetScreen(data);
		//This Function verifies the TooolingNeeded,ComponentCost and ComponentNumber and its Functionalities in create Cost Sheet page
		break;
		
		case "Verify Tooling Needed/Component Cost/Component Number added in General Attributes in Cost Sheet Page":
		Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsDefaultValuesInCostSheetScreen(data);
		//This Function verifies the TooolingNeeded,ComponentCost and ComponentNumber and its Functionalities in Cost Sheet page
		break;
		
		case "Verify Tooling Needed/Component Cost/Component Number added in General Attributes inUpdate Cost Sheet Page":
		Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsDefaultValuesInUpdateCostSheetScreen(data);
		//This Function verifies the TooolingNeeded,ComponentCost and ComponentNumber and its Functionalities in Update Cost Sheet page
		break;
		
		case "Component Number Functionality Verification":
		VerifyComponentNumberFunctionality(data);
		//This Function verifies Component Number Functionality
		break;
	    
		
		default:
		fail=true;
		log.info("Default is executed");
		}
		}
		}
		catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

   public static boolean Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsDefaultValuesInCreateCostSheetScreen(String [] data) throws Exception{
	   try{
		   CI296.navigateToProduct(data);
		   //Navigating to product search Page
		   SearchExistingProduct(data);
		   //Searching the Existing product
		   NavigateToCostSheet(data);
		   //Navigating To CostSheet Tab
		   SelectYearAndSpecification(data);
		   //Selecting Year and Specification
		   NavigateCreateCostSheetPage(data);
		   //Navigating to Create Cost Sheet page
		   AssertionVerificationForFieldAppearanceInCreateCostSCreen(data);
		   //Assertion verification on element Appears
		   AssertionVerificationForDefaultValueInCreateCostSheetPage(data);
		   //Assertion verification on Default values
		   log.info("Tooling Needed/Component Cost/Component Number appears in General Atrributes and its default Values Verified in Create Cost Sheet Page.TEST CASE VERIFIED SUCCESSFULLY");
	   }
	   catch(Exception e){
		   fail=true;
		   log.error("Exception in Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsFunctionalities"+e);
		   throw e;
	   }
	   return true;
	   }
 
   public static boolean Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsDefaultValuesInCostSheetScreen(String [] data) throws Exception{
	   try{
		   CI296.navigateToProduct(data);
		   //Navigating to product search Page
		   SearchExistingProduct(data);
		   //Searching the Existing product
		   NavigateToCostSheet(data);
		   //Navigating To CostSheet Tab
		   SelectYearAndSpecification(data);
		   //Selecting Year and Specification
		  AssertionVerificationForFieldAppearanceInCostSheetScreen(data);
		   //Assertion Verification For the Field Appearance
		    log.info("Tooling Needed/Component Cost/Component Number appears in General Atrributes and its default Values Verified in  Cost Sheet Page.TEST CASE VERIFIED SUCCESSFULLY");
	   }
	   catch(Exception e){
		   fail=true;
		   log.error("Exception in Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsFunctionalities"+e);
		   throw e;
	   }
	   return true;
	   }
   

   public static boolean Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsDefaultValuesInUpdateCostSheetScreen(String [] data) throws Exception{
	   try{
		   CI296.navigateToProduct(data);
		   //Navigating to product search Page
		   SearchExistingProduct(data);
		   //Searching the Existing product
		   NavigateToCostSheet(data);
		   //Navigating To CostSheet Tab
		   SelectYearAndSpecification(data);
		   //Selecting Year and Specification
		   NavigateToUpdateCostSheetScreen(data);
		   //Navigating to Update Cost Sheet Screen 
		   AssertionVerificationForFieldAppearanceInUpdateCostSheetScreen(data);
		   //Assertion Verification For the Field Appearance
		   log.info("Tooling Needed/Component Cost/Component Number appears in General Atrributes and its default Values Verified in  Update Cost Sheet Page.TEST CASE VERIFIED SUCCESSFULLY");
	   }
	   catch(Exception e){
		   fail=true;
		   log.error("Exception in Verify_TooolingNeeded_ComponentCost_ComponentNumber_InGeneralAttributesAndItsFunctionalities"+e);
		   throw e;
	   }
	   return true;
	   }
   
   public static boolean VerifyComponentNumberFunctionality(String [] data) throws Exception{
	   try{
		   CI296.navigateToProduct(data);
		   //Navigating to product search Page
		   SearchExistingProduct(data);
		   //Searching the Existing product
		   NavigateToCostSheet(data);
		   //Navigating To CostSheet Tab
		   SelectYearAndSpecification(data);
		   //Selecting Year and Specification
		   NavigateToUpdateCostSheetScreen(data);
		   //Navigating to Update Cost Sheet Screen 
		   AssertionVerificationOfComponentNumberFunctionality(data);
		   //Assertion Verification For the Field Appearance
		   log.info("Error Message Appears as expected.TEST CASE VERIFIED SUCCESSFULLY");
	   }
	   catch(Exception e){
		   fail=true;
		   log.error("Exception in VerifyComponentNumberFunctionality"+e);
		   throw e;
	   }
	   return true;
	   }
   
   
	public static boolean SearchExistingProduct(String [] data) throws Exception{
		try{
			CI299.ProductType=By.linkText(data[4]);
			CommonFunctions.waitForElementTobeClickable(CI299.ProductType);
			CommonFunctions.clickButtonOrLink(CI299.ProductType, "HyperLink", "Assortment/Solid");
			//Choosing the product type as Retail Item
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CI299.ProductNumberSearch);
			CommonFunctions.enterTextInTextboxUpdated(CI299.ProductNumberSearch, data[5], "product number");
			//Entering the Product Number
			CommonFunctions.clickButtonOrLink(CI299.SearchProductButton, "Button", "Search");
			wait.until(ExpectedConditions.titleIs(data[16]));
			//waiting for the expected Page title to appear
			CommonFunctions.waitForPageLoaded();
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in SearchExistingProduct"+e);
			throw e;
		}
		 return true;
	}


	public static boolean NavigateToCostSheet(String [] data) throws Exception{
		try{
			TabNameOrValue=By.linkText(data[6]);
			CommonFunctions.waitForElementTobeClickable(TabNameOrValue);
		    CommonFunctions.clickButtonOrLink(TabNameOrValue, "Hyper Link", "Sourcing");
			//Clicking on Sourcing tab
			CommonFunctions.waitForPageLoaded();
			TabNameOrValue=By.linkText(data[7]);
			CommonFunctions.waitForElementTobeClickable(TabNameOrValue);
			 CommonFunctions.clickButtonOrLink(TabNameOrValue, "Hyper Link", "Cost Sheet");
			//Clicking on Costing tab
			wait.until(ExpectedConditions.titleIs(data[8]));
			CommonFunctions.waitForPageLoaded();
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in NavigateToCostSheet"+e);
			throw e;
		}
		 return true;
		}
	
 public static boolean SelectYearAndSpecification(String [] data) throws Exception{
	 try{
		 CommonFunctions.waitForElementTobeClickable(Product.detailPageSeasonDD);
         CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[9],"Season year");
         //Selecting the Year
         CommonFunctions.waitForPageLoaded();
         CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
         CommonFunctions.selectFromDropDownByVisibleTextUpdated(InternalBOMSoftG.selectSpecification, data[10],"Drop down value selection");
         //Selecting the Specification
		CommonFunctions.waitForPageLoaded();
         
	 }
	 
	 catch(Exception e){
			fail=true;
			log.error("Exception in SelectYearAndSpecification"+e);
			throw e;
	 }
	 return true;
}
	public static boolean NavigateCreateCostSheetPage(String [] data) throws Exception{
		 try{
			 CommonFunctions.waitForElementTobeClickable(CostingActionDropDown);
			 CommonFunctions.selectFromDropDownByVisibleTextUpdated(CostingActionDropDown, data[11], "Create Cost Sheet");
			 //Clicking Create Cost Sheet Value In Drop down
			 wait.until(ExpectedConditions.titleIs(data[12]));
			 CommonFunctions.waitForPageLoaded();
			 TabNameOrValue=By.linkText(data[13]);
			 CommonFunctions.waitForElementTobeClickable(TabNameOrValue);
			 CommonFunctions.clickButtonOrLink(TabNameOrValue, "Hyper Link", "Slecting Vendor");
			//Clicking on Vendor Option
			 CommonFunctions.waitForPageLoaded();
			 wait.until(ExpectedConditions.titleIs(data[12]));
			 CommonFunctions.waitForElementTobeClickable(CreateCostSheetHeading);
			 }
	 catch(Exception e){
			fail=true;
			log.error("Exception in NavigateCreateCostSheetPage"+e);
			throw e;
	 }
	 return true;
 }
	public static boolean AssertionVerificationForFieldAppearanceInCreateCostSCreen(String [] data) throws Exception{
		 try{
		
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ToolingNeeded, "Tooling Need text"), "Tooling Need text is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling needed text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ToolingNeededCheckBox, "Tooling Need check Box"), "Tooling Need Check Box is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling needed text Check box is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentCost, "Component Cost text"), "Component Cost text is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling Component Cost text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentCostDefaultValue, "ComponentCostDefaultValue"), "ComponentCostDefaultValue is Not appears.Assertion Failed.Please verify");
			//Verifying the tooling Component Cost Default value element is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentNumber, "Component Number text"), "Component Number text is Not appears.Assertion Failed.Please verify");
			//Verifying the tooling Component Number text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentNumberValue, "Component Number Value"), "ComponentNumberValue is Not appears.Assertion Failed.Please verify");
			//Verifying the tooling Component Number Value is appears or not
			 }
	 catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationForFieldAppearanceInCreateCostSheetPage"+e);
			throw e;
	 }
	 return true;
}
	
	public static boolean AssertionVerificationForFieldAppearanceInCostSheetScreen(String [] data) throws Exception{
		 try{
			 CommonFunctions.waitForElementTobeClickable(CostSheetIdentificationText);
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ToolingNeeded, "Tooling Need text"), "Tooling Need text is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling needed text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ToolingNeededValue, "Tooling Need check Box"), "Tooling Need Check Box is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling needed text Check box is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentCost, "Component Cost text"), "Component Cost text is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling Component Cost text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentCostDefaultValue, "ComponentCostDefaultValue"), "ComponentCostDefaultValue is Not appears.Assertion Failed.Please verify");
			//Verifying the tooling Component Cost Default value element is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentNumber, "Component Number text"), "Component Number text is Not appears.Assertion Failed.Please verify");
			//Verifying the tooling Component Number text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentNumberValue, "Component Number Value"), "ComponentNumberValue is Not appears.Assertion Failed.Please verify");
				//Verifying the tooling Component Number Value is appears or not
			 }
	 catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationForFieldAppearanceInCostSheetScreen"+e);
			throw e;
	 }
	 return true;
}


	public static boolean AssertionVerificationForDefaultValueInCreateCostSheetPage(String [] data) throws Exception{
		 try{
			 CI296.GettingText(ComponentCostDefaultValue);
			 CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[14], "Default value of Component Cost is wrong.Assertion Failed.Please verify");
			 //Verifying the Default Value of Component Cost
			 CI296.GettingText(ComponentNumberValue);
			 CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[15], "Default value of Component Number is wrong.Assertion Failed.Please verify");
			//Verifying the Default Value of Component Number
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotChecked(ToolingNeededCheckBox,"Tooling Needed Check Box"), "Tooling Needed Check Box is checked.Assertion failed.please check");
			 //Verifying Check box is Checked or not
			 }
	 catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationForFieldAppearanceInCreateCostSheetPage"+e);
			throw e;
	 }
	 return true;
}
 
	public static boolean AssertionVerificationForFieldAppearanceInUpdateCostSheetScreen(String [] data) throws Exception{
		 try{
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ToolingNeeded, "Tooling Need text"), "Tooling Need text is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling needed text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ToolingNeededCheckBox, "Tooling Need check Box"), "Tooling Need Check Box is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling needed text Check box is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentCost, "Component Cost text"), "Component Cost text is Not appears.Assertion Failed.Please verify");
			 //Verifying the tooling Component Cost text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentCostCheckBox, "ComponentCostDefaultValue"), "ComponentCostDefaultValue is Not appears.Assertion Failed.Please verify");
			//Verifying the tooling Component Cost Check Box element is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentNumber, "Component Number text"), "Component Number text is Not appears.Assertion Failed.Please verify");
			//Verifying the tooling Component Number text is appears or not
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(ComponentNumberTextBoxValue, "Component Number Value"), "ComponentNumberValue is Not appears.Assertion Failed.Please verify");
			//Verifying the tooling Component Number Value is appears or not
			 }
	 catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationForFieldAppearanceInUpdateCostSheetScreen"+e);
			throw e;
	 }
	 return true;
}
	
	public static boolean AssertionVerificationOfComponentNumberFunctionality(String [] data) throws Exception{
		 try{
			 CommonFunctions.waitForElementTobeClickable(ComponentNumberTextBoxValue);
			 CommonFunctions.clearTextBox(ComponentNumberTextBoxValue, "Component Number Text Box");
			 //Clearing the Component Number Text box
			 CommonFunctions.waitForElementTobeClickable(ComponentCostCheckBox);
			 CommonFunctions.clickButtonOrLink(ComponentCostCheckBox, "Component Cost Check Box");
			 //Check the Component Cost check Box
			 CommonFunctions.clickButtonOrLink(SaveButton, "Save Button");
			 CommonFunctions.waitForPageLoaded();
			 //Clicking on Save Button
			 CI296.GettingText(ErrorMessage);
			 CommonFunctions.AssertEqualsVerification(CI296.textForVerification.trim(), data[17], "Error Message Might not be Appeared/Error message is wrong.Please vrify.Assertion failed ");
			 }
	 catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationForFieldAppearanceInUpdateCostSheetScreen"+e);
			throw e;
	 }
	 return true;
}


	public static boolean NavigateToUpdateCostSheetScreen(String [] data) throws Exception{
		 try{
			 CommonFunctions.waitForElementTobeClickable(CostingActionDropDownToUpdate);
			 CommonFunctions.selectFromDropDownByVisibleTextUpdated(CostingActionDropDownToUpdate, data[11], "Action Dro down");
			 //Selecting Update Option in Actions Drop-down
			 wait.until(ExpectedConditions.titleIs(data[12]));
			 CommonFunctions.waitForPageLoaded();
			 }
	 catch(Exception e){
			fail=true;
			log.error("Exception in NavigateToUpdateCostScreen"+e);
			throw e;
	 }
	 return true;
}






@BeforeMethod
	  public void Refresh (){
	
		  if(driver!=null && runmodes[count+1].equalsIgnoreCase("y")){
			  driver.navigate().refresh();
			  log.info("Driver is refreshed");
		  }
		  else{
			  log.info("No Driver is Launched");
		  }
}
	
	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail ||CommonFunctions.error){
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
			CommonFunctions.error=false;
		}
		else
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
		CommonFunctions.error=false;
		
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteCIExcelXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteCIExcelXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteCIExcelXls,"Testcases", Utility.getRowNum(suiteCIExcelXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteCIExcelXls,"Testcases", Utility.getRowNum(suiteCIExcelXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteCIExcelXls, this.getClass().getSimpleName());
	}

	

}
