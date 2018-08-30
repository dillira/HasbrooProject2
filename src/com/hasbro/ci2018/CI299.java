package com.hasbro.ci2018;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.SeleniumDriver;

import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Product;
import com.hasbro.security.Specifications;

import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

public class CI299 extends TestsuiteBase{
	
	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
    static boolean fail=false;
	static boolean isTestPass=true;
    static int y=0;
	String loginVal;
	Boolean flaglogin=false;

	
	
	
	public static By UnitOfMeasureText                         = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Unit Of Measure')]");
	public static By UnitOfMeassureDropDown                    = By.xpath("//td[contains(text(),'Unit Of Measure')]//following::select[1]");
	public static By UnitOfMeasureSelectedValue                = By.xpath("//td[contains(text(),'Unit Of Measure')]//following::td[1]");
	public static By NewButton                                 = By.linkText("New");
	public static By SearchMaterailTextBox                     = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By SearchButton                              = By.xpath("//*[@id='SearchButton1']");
	public static By ActionsDropDown                           = By.xpath("//*[@id='materialSupplierActions']");
	public static By SeasonChangedLabelViewMaterial            = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Exchange Rate Season')]");
	public static By SeasonChangedLabelCreateOrUpdateMaterial  = By.xpath("//a[text()='Exchange Rate Season:']");
	public static By MaterialDescription                       = By.xpath("//td[contains(text(),'General Attributes:')]//following::td[contains(text(),'Material Description')]");
	public static By MatchPriceColumn                          = By.xpath("//td[text()='Match Price']");
	//MatchPriceColumn XPath of Match price has taken from dev server for verification
	public static By ProductNumberSearch                       = By.xpath("//td[contains(text(),'Product Number')]//following::input[1]");
	public static By SearchProductButton                       = By.xpath("//a[text()='Search']");
	public static By BOMType                                   = By.id("bomId");
	public static By BOMView                                   = By.xpath("//div[@id='DETAILS_PAGEDisplay']/table/tbody/tr[3]/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr/td[4]/a/img");
	public static By BOMViewChange                             = By.xpath("//div[@id='overDiv']/table/tbody/tr/td"); 
	public static By BOMMatchCostSummaryIcon                   = By.xpath("//a[@name='BOM Match Cost Summary']//preceding::img[1]");
	//BOMMatchCostSummaryIcon XPath of Match price has taken from dev server for verification
	public static By HeaderAttributeIcon                       = By.xpath("//div[@id='genDetails_plus']//following::img[1]");  
	public static By ViewIcon                                  = By.xpath("//div[@id='hbPlasticsViewDisplay']//preceding::img[1]");  
	public static By AddingARowTable                           = By.xpath("//*[@id='overDiv']/table/tbody/tr/td/table/tbody/tr/td/font");
	public static By ViewChangeTable                           = By.xpath("//*[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td");
	public static By FullPlasticSelection                      = By.linkText("Internal: Full Plastic");
	public static By FullLaborSelection                        = By.linkText("Internal: Full General/Deco Labor");
	public static By ResinMaterialDescription;                 
    public static By ResinType;
	public static By SupplierName;                           
	public static By UnitPrice;
	public static By Unit;
	public static By DropDownOfUnit;
	public static By OperationType;
    public static By MaterialType;
	public static By ProductType;
	public static By BOMMode;
	public static By AddingSecondRow;
	public static By OperationTypeDropDown;
	
	public static String secondRowValue;
	public static String resinMaterialDescription;
	public static String resinType;
	public static String supplierName;
	public static String unitPrice;
	public static String unit;
	public static String operationType;
	public static String dropDownOfUnit;
	public static String operationTypeDropDown; 
	public static int rowValue;
	
	
	
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
					log.debug("Inside testcase for CI296");
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
				case "Verification of 'Unit Of Measure' added in General Atrribute for Resin Material":
				TC_1_2_3_4_5_VerificationOfUnitMeasureFieldAdded(data);
				break;
				case "Verification of 'Unit Of Measure' added in General Atrribute for Labor Material":
				TC_1_2_3_4_5_VerificationOfUnitMeasureFieldAdded(data);	
				break;
				case "Verification of 'Unit Of Measure' added in General Atrribute for Metal Material":
				TC_1_2_3_4_5_VerificationOfUnitMeasureFieldAdded(data);
				break;
				case "Verification of 'Unit Of Measure' not added in General Atrribute for Fabric Material":
				TC_1_2_3_4_5_VerificationOfUnitMeasureFieldAdded(data);	
				break;
				case "Verification of 'Unit Of Measure' not added in General Atrribute for Trim Material":
				TC_1_2_3_4_5_VerificationOfUnitMeasureFieldAdded(data);	
				break;
				case "Verification of 'Exchange Rate Season' Label  added in General Atrribute for Resin Material":
				TC_6_7_8_9_10_VerificationOfSeasonLabelChange(data);	
				break;
				case "Verification of 'Exchange Rate Season' Label  added in General Atrribute for Labor Material":
				TC_6_7_8_9_10_VerificationOfSeasonLabelChange(data);	
				break;
				case "Verification of 'Exchange Rate Season' Label  added in General Atrribute for Metal Material":
				TC_6_7_8_9_10_VerificationOfSeasonLabelChange(data);	
				break;
				case "Verification of 'Exchange Rate Season' Label  not added in General Atrribute for Fabric Material":
				TC_6_7_8_9_10_VerificationOfSeasonLabelChange(data);	
				break;
				case "Verification of 'Exchange Rate Season' Label  not  added in General Atrribute for Trim Material":
				TC_6_7_8_9_10_VerificationOfSeasonLabelChange(data);	
				break;
				case "Verification of 'Material Description' Label  added in General Atrribute for Resin Material":
				TC_11_12_VerificationOfSeasonLabelChange(data);
				break;
				case "Verification of 'Material Description' Label  added in General Atrribute for Labor Material":
				TC_11_12_VerificationOfSeasonLabelChange(data);
				break;
				case "Removal Of  'Match Price' Column from each BOM Section in both Internal Product and Internal Retail Item BOM":
				TC_13_RemovalOfMatchPriceColumn(data);
				break;
				case "Removal Of  'BOM Match Cost' Part from each BOM Section in both Internal Product  Retail Item BOM":
				TC_14_RemovalOfBOMMatchCostGroup(data);
				break;
				case "Adding a Material Into BOM and Verification Of Particular Columns values":
				TC_15_BOMValueAutoPopulation(data);
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
	
public static boolean TC_1_2_3_4_5_VerificationOfUnitMeasureFieldAdded(String [] data) throws Exception{
		try{
			CI296.navigateToMaterial(data);
			//Navigating to material search Page
			navigateToCreateMaterialPage(data);
			//Navigating to create material page
			UnitOfMeasureAssertVerificationInCreateMaterialPage(data);
			//verifying the Unit or Measure text ,drop down and its values present or not in create Material Page
			SearchExistingMaterial(data);
			//navigating to view Material page of existing material and searching the existing material
			UnitOfMeasureAssertVerificationInViewMaterialPage(data);
			//verifying the Unit or Measure text ,drop down and its values present or not in view Material Page.
			UnitOfMeasureAssertVerificationInUpdateMaterialPage(data);
			//Verifying the Unit of Measure Drop down and its values in update Material screen 
			if(CommonFunctions.error==false){
				log.info("TC_1_2_3_4_5_VerificationOfUnitMeasureFieldAdded Verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_1_2_3_4_5_VerificationOfUnitMeasureFieldAdded"+e);
		    throw e;
		}
		 return true;
	}



	public static boolean TC_6_7_8_9_10_VerificationOfSeasonLabelChange(String [] data) throws Exception{
		try{
			CI296.navigateToMaterial(data);
			//Navigating to material search Page
			navigateToCreateMaterialPage(data);
			//Navigating to create material page
			AssertionVerificationOfSeasonLabelChangeInCreateMaterial(data);
			//verifying Exchange Rate Season label is present or not in create Material Page
			SearchExistingMaterial(data);
			//navigating to view Material page of existing material and searching the existing material
			AssertionVerificationOfSeasonLabelChangeInViewMaterial(data);
			//verifying Exchange rate season is present or not in view Material Page.
			AssertionVerificationOfSeasonLabelChangeInUpdateMaterial(data);
			//Verifying the Exchange Rate Season  in update Material screen 
			if(CommonFunctions.error==false){
				log.info("TC_6_7_8_9_10_VerificationOfSeasonLabelChange Verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_6_7_8_9_10_VerificationOfSeasonLabelChange"+e);
			throw e;
		}
		 return true;
	}
	
	public static boolean TC_11_12_VerificationOfSeasonLabelChange(String [] data) throws Exception{
		try{
			CI296.navigateToMaterial(data);
			//Navigating to material search Page
			navigateToCreateMaterialPage(data);
			//Navigating to create material page
			AssertionVerificationOfDescriptionChangeInCreateMaterial(data);
			//verifying Material Description label is present or not in create Material Page
			SearchExistingMaterial(data);
			//navigating to view Material page of existing material and searching the existing material
			AssertionVerificationOfDescriptionChangeInViewMaterial(data);
			//verifying Material Description is present or not in view Material Page.
			AssertionVerificationOfDescriptionChangeInUpdateMaterial(data);
			//Verifying the Material Description in update Material screen 
			if(CommonFunctions.error==false){
				log.info("TC_11_12_VerificationOfSeasonLabelChange Verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_11_12_VerificationOfSeasonLabelChange"+e);
			throw e;
		}
		 return true;
	}
	public static boolean TC_13_RemovalOfMatchPriceColumn(String [] data) throws Exception{
		try{
			CI296.navigateToProduct(data);
			//Navigating to product search Page
			SearchExistingProduct(data);
			//Searching the Existing product
			NavigateToSpecificationtab(data);
			//navigating to Specifications tab
			AddSpecification(data);
			//Adding a Specification
			CommonProjectFunctions.clickMaterialsTab();
			//Navigating to materials tab.
			ProductInteralBOMVerification(data);
			//Verifying whether Match price Column is appearing or not for Product Internal
			RetailItemBOMVerification(data);
			//Verifying whether Match price Column is appearing or not for Retail Item Internal
			if(CommonFunctions.error==false){
				log.info("TC_13_RemovalOfMatchPriceColumn Verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_13_RemovalOfMatchPriceColumn"+e);
			throw e;
		}
		 return true;
	}
	
	public static boolean TC_14_RemovalOfBOMMatchCostGroup(String [] data) throws Exception{
		try{
			CI296.navigateToProduct(data);
			//Navigating to product search Page
			SearchExistingProduct(data);
			//Searching the Existing product
			NavigateToSpecificationtab(data);
			//navigating to Specifications tab
			AddSpecification(data);
			//Adding a Specification
			CommonProjectFunctions.clickMaterialsTab();
			//Navigating to materials tab.
			AssertionVerificationOfRemovalOfBOMMatchCost(data);
			//Verifying whether Match price Column is appearing or not for Retail Item Internal
			if(CommonFunctions.error==false ){
				log.info("TC_14_RemovalOfBOMMatchCostGroup Verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			
		}}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_14_RemovalOfBOMMatchCostGroup"+e);
		    throw e;
		   }
		 return true;
	}
	public static boolean TC_15_BOMValueAutoPopulation(String [] data) throws Exception{
		try{
			CI296.navigateToProduct(data);
			//Navigating to product search Page
			SearchExistingProduct(data);
			//Searching the Existing product
			NavigateToSpecificationtab(data);
			//navigating to Specifications tab
			AddSpecification(data);
			//Adding a Specification
			CommonProjectFunctions.clickMaterialsTab();
			//Navigating to materials tab.
			AddingMaterialInToBOMAndAssertionVerfication(data);
			//Verifying whether Match price Column is appearing or not for Retail Item Internal
			if(CommonFunctions.error==false){
				log.info("TC_15_BOMValueAutoPopulation Verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			
		}}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_15_BOMValueAutoPopulation"+e);
		    throw e;
		   }
		 return true;
	}
	public static boolean UnitOfMeasureAssertVerificationInViewMaterialPage(String [] data){
		try{
		
			if(data[6].contains(data[5]))
				//Verifying the same requirement type of Material contains the our selected material or not
		{
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(UnitOfMeasureText,"Unit Of measure Text in general Attribute"), "Condition failed.Asserition failed");
				//Verifying whether UnitOfMeasurement Text is present is /not using Assertion
			log.info("Unit Of Measure Text appears in view Material Page");
			for(int j=8;j<34;j++){
				CommonFunctions.waitForElementTobeClickable(UnitOfMeasureSelectedValue);
				CI296.GettingText(UnitOfMeasureSelectedValue);
				if(CI296.textForVerification.equals(data[j])){
					
			    CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[j], "Actual and Expected are not matched.assertion failed.please check");
			    log.info("Selected Vaue in the Unit of Mesaure Drop-Down is "+CI296.textForVerification);
			    break;
				}
				else{
					
					log.info("Values are not matched for "+data[j]+".Checking next one");
				}
			 }
			}
			else{
				CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(UnitOfMeasureText,"Unit Of measure Text in general Attribute"), "Condition failed.Asserition failed");
				//Verifying whether UnitOfMeasurement Text is present is /not using Assertion
				log.info("Unit of measure not appears in view Material Page.Scenario Verified Successfully");
			}
				
		}
		catch(Exception e){
			fail=true;
			log.error("Exception while verifying in verifying in view materail Page" +e);
			throw e;
		}
		return true;
	}
	public static boolean SearchExistingMaterial(String [] data) throws Exception {
		try{
			 driver.navigate().refresh();
			 CI296.navigateToMaterial(data);
			 MaterialType=By.linkText(data[5]);
			 CommonFunctions.clickButtonOrLink(MaterialType, "HyperLink", data[5]);
			 CommonFunctions.waitForPageLoaded();
			 CommonFunctions.waitForElementTobeClickable(SearchMaterailTextBox);
			 CommonFunctions.enterTextInTextboxUpdated(SearchMaterailTextBox, data[34], "MaterialSearch");
			 CommonFunctions.clickButtonOrLink(SearchButton, "Search");
			 CommonFunctions.waitForPageLoaded();
			 wait.until(ExpectedConditions.titleIs(data[35]));
			 CI296.getPageTitle();
			 CommonFunctions.AssertEqualsVerification(CI296.pageTitle, data[35], "Actual and expected page Title are not matched.please verify");
			 }
		catch(Exception e){
			fail=true;
			log.error("Exception while searching the Material" +e);
			throw e;
		}
		return true;
	}
	
	public static boolean UnitOfMeasureAssertVerificationInCreateMaterialPage(String [] data){
		   try{
		    if(data[6].contains(data[5])){
			//Verifying the same requirement type of Material contains the our selected material or not
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(UnitOfMeasureText,"Unit Of measure Text in general Attribute"), "Condition failed.Asserition failed");
			//Verifying whether UnitOfMeasurement Text is present is /not using Assertion
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementDisplayed(UnitOfMeassureDropDown,"Unit Of measure DropDown in general Attribute"), "Condition failed.Asserition failed");
			//Verifying whether UnitOfMeasurement DropDown is present is /not using Assertion
			log.info("Unit of Measure Text box and its drop down appears");
			Select dropDown = new Select(driver.findElement(UnitOfMeassureDropDown));
			List<WebElement> options = dropDown.getOptions();
			int NumberOfDroPDownValues=options.size();
			String NumberOfValues=Integer.toString(NumberOfDroPDownValues);
			CommonFunctions.AssertEqualsVerification(NumberOfValues,data[7], "Actual and expected are matched.Assertion Verified successfully");
			//Verifying the Number Of values within the Drop down
			for(int i=0,j=1;i<NumberOfDroPDownValues;i++,j++){
		   // String excelData="data["+j+"]";
		    String valuesOfDropDown =options.get(i).getText();
		    CommonFunctions.AssertEqualsVerification(valuesOfDropDown, data[7+j], "Actual and expected are matched.Assertion Verified successfully");
			}
			}
			else{
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(UnitOfMeasureText,"Unit Of measure Text in general Attribute"), "Condition failed.Asserition failed");
			//Verifying whether UnitOfMeasurement Text is present is /not using Assertion
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(UnitOfMeassureDropDown,"Unit Of measure DropDown in general Attribute"), "Condition failed.Asserition failed");
			//Verifying whether UnitOfMeasurement DropDown is present is /not using Assertion
			log.info("Unit of measure and its dropDown not appears.Scenario Verified Successfully");
		    }
	}
		catch(Exception e){
		fail=true;
		log.error("Exception in VerifyUnitOfMeasureField"+e);
		throw e;
		}
		 return true;
	}

	public static boolean SearchExistingProduct(String [] data) throws Exception{
		try{
			ProductType=By.linkText(data[40]);
			CommonFunctions.waitForElementTobeClickable(ProductType);
			CommonFunctions.clickButtonOrLink(ProductType, "HyperLink", "ReatilItem");
			//Choosing the product type as Retail Item
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(ProductNumberSearch);
			CommonFunctions.enterTextInTextboxUpdated(ProductNumberSearch, data[41], "product number");
			//Entering the Product Number
			CommonFunctions.clickButtonOrLink(SearchProductButton, "Button", "Search");
			//wait.until(ExpectedConditions.titleIs(data[4]));
			//waiting for the expected Pagetitle to appear
			CommonFunctions.waitForPageLoaded();
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in SearchExistingProduct"+e);
			throw e;
		}
		 return true;
	}
	
	public static boolean NavigateToSpecificationtab(String [] data) throws Exception{
		try{
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.waitForElementTobeClickable(Specifications.specificationsTablink);
		CommonFunctions.clickButtonOrLink(Specifications.specificationsTablink, "link", "specifications tab");
		//navigating the Specifications tab
		//wait.until(ExpectedConditions.titleIs(data[4]));
        CommonFunctions.waitForPageLoaded();
        CommonFunctions.waitForElementTobeClickable(Product.detailPageSeasonDD);
        CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[42],"Season year");
        //selecting the year
        CommonFunctions.handleAlertPopUp1();
        CommonFunctions.waitForPageLoaded();
	}
		catch(Exception e){
			fail=true;
			log.error("Exception in NavigateToSpecificationtab"+e);
	        throw e;
		}
		return true;
	}
	public static boolean UnitOfMeasureAssertVerificationInUpdateMaterialPage(String [] data) throws Exception{
		try{
		     CommonFunctions.waitForElementTobeClickable(ActionsDropDown);
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionsDropDown, data[39], "Update Supplier");
		     CommonFunctions.waitForPageLoaded();
		     wait.until(ExpectedConditions.titleIs(data[38]));
		     CI296.getPageTitle();
		     CommonFunctions.AssertEqualsVerification(CI296.pageTitle, data[38], "Actual and expected are not matched.please verify");
		     UnitOfMeasureAssertVerificationInCreateMaterialPage(data);
		   }
		catch(Exception e){
			fail=true;
			log.error("Exception in UnitOfMeasureAssertVerificationInUpdateMaterialPage"+e);
			throw e;
		}
		 return true;
	}
	
	public static boolean navigateToCreateMaterialPage(String [] data) throws Exception{
		try{
		    CommonFunctions.clickButtonOrLink(NewButton, "HeperLink", "new");
		    //clicking on new Button
		    CommonFunctions.waitForPageLoaded();
		    CI296.getPageTitle();
		    CommonFunctions.AssertTrueVerification(CI296.pageTitle.equals(data[4]), "Condition Failed.Assertion Failed.please check");
		    //verification of Page Name
		    log.info(CI296.pageTitle+"Page Appears");
		    MaterialType=By.linkText(data[5]);
		    CommonFunctions.clickButtonOrLink(MaterialType, "HyperLink", data[5]);
		    CommonFunctions.waitForPageLoaded();
		    CI296.getPageTitle();
		    CommonFunctions.AssertTrueVerification(CI296.pageTitle.equals(data[4]), "Actual And Expected are not mtached.Assertion Failed.please check");
		    //verification of Page Name
		    log.info(CI296.pageTitle+"Page Appears");
		   }
		catch(Exception e){
			fail=true;
			log.error("Exception in navigateToCreateMaterialPage"+e);
			throw e;
		}
		 return true;
	}
	public static String AddSpecification(String[] data) throws Exception{
		try{
			//Add Specification
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
			Select dropDownSpec = new Select(SeleniumDriver.driver.findElement(InternalBOMSoftG.selectSpecification));
			List<WebElement> elementCount = dropDownSpec.getOptions();
			int count =elementCount.size();
			if(count>=2)
			{
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(InternalBOMSoftG.selectSpecification, data[43],"Drop down value selection");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			
			}
			else
			{//creating a Specification
				InternalBOMSoftG.strSpec= CommonProjectFunctions.Create_Specifications(data[17],data[18]);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			}
			log.info("Specification is: "+InternalBOMSoftG.strSpec);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddSpecification()", e);
			throw e;
		}
		return InternalBOMSoftG.strSpec;
	}
	public static boolean AssertionVerificationOfSeasonLabelChangeInUpdateMaterial(String[] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(ActionsDropDown);
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionsDropDown, data[39], "Update Supplier");
		     //Selecting the value in drop down 
		     CommonFunctions.waitForPageLoaded();
		     wait.until(ExpectedConditions.titleIs(data[38]));
		     CI296.getPageTitle();
		     CommonFunctions.AssertEqualsVerification(CI296.pageTitle, data[38], "Actual and expected are not matched.please verify");
			AssertionVerificationOfSeasonLabelChangeInCreateMaterial(data);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationOfSeasonLabelChangeInCreateMaterial"+e);
			throw e;
			}
			 return true;
		}
	
	public static boolean AssertionVerificationOfSeasonLabelChangeInCreateMaterial(String [] data){
		   try{
		    if(data[6].contains(data[5])){
			//Verifying the same requirement type of Material contains the our selected material or not
			CI296.GettingText(SeasonChangedLabelCreateOrUpdateMaterial);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[36], "Actual And Expected label name are not matched.please Verify");
			//Verifying Exchange Rate Season Label name Using Assertion
			log.info("Exchange Rate Season label Verified Successfully");
			}
			else{
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(SeasonChangedLabelCreateOrUpdateMaterial,"Exchange Rate Found Element"), "Condition failed.Asserition failed");
			//Verifying Exchange Rate Season Label name is not appearing Using Assertion
				log.info("Exchange Rate Season Label not appears.Scenario Verified Successfully");
		    }
	}
		catch(Exception e){
		fail=true;
		log.error("Exception in AssertionVerificationOfSeasonLabelChangeInCreateMaterial"+e);
	    throw e;
		}
		 return true;
	}
	public static boolean AssertionVerificationOfSeasonLabelChangeInViewMaterial(String [] data){
		   try{
		    if(data[6].contains(data[5])){
			//Verifying the same requirement type of Material contains the our selected material or not
			CI296.GettingText(SeasonChangedLabelViewMaterial);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification.trim(), data[37], "Actual And Expected label name are not matched.please Verify");
			//Verifying Exchange Rate Season Label name Using Assertion
			log.info("Exchange Rate Season label Verified Successfully");
			}
			else{
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(SeasonChangedLabelViewMaterial,"Exchange Rate Found Element"), "Condition failed.Asserition failed");
			//Verifying Exchange Rate Season Label name is not appearing Using Assertion
				log.info("Exchange Rate Season Label not appears.Scenario Verified Successfully");
		    }
	}
		   
		catch(Exception e){
		fail=true;
		log.error("Exception in AssertionVerificationOfSeasonLabelChangeInCreateMaterial"+e);
		throw e;
		}
		 return true;
	}
	
	public static boolean AssertionVerificationOfDescriptionChangeInCreateMaterial(String [] data){
		   try{
		    if(data[6].contains(data[5])){
			//Verifying the same requirement type of Material contains the our selected material or not
			CI296.GettingText(MaterialDescription);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification.trim(), data[36], "Actual And Expected label name are not matched.please Verify");
			//Verifying Material Description label Using Assertion
			log.info("Material Description label Verified Successfully");
			}
			else{
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(MaterialDescription,"MaterialDescription Element"), "Condition failed.Asserition failed");
			//Verifying Material Description label is not appearing Using Assertion
				log.info("MaterialDescription not appears.Scenario Verified Successfully");
		    }
	}
			catch(Exception e){
				fail=true;
				log.error("Exception in AssertionVerificationOfResinOrLabelLabelChangeInCreateMaterial"+e);
			    throw e;
				}
				 return true;
			}
	public static boolean AssertionVerificationOfDescriptionChangeInUpdateMaterial(String[] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(ActionsDropDown);
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionsDropDown, data[39], "Update Supplier");
		     CommonFunctions.waitForPageLoaded();
		     wait.until(ExpectedConditions.titleIs(data[38]));
		     CI296.getPageTitle();
		     CommonFunctions.AssertEqualsVerification(CI296.pageTitle, data[38], "Actual and expected are not matched.please verify");
		     AssertionVerificationOfDescriptionChangeInCreateMaterial(data);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationOfDescriptionChangeInUpdateMaterial"+e);
			throw e;
			}
			 return true;
		}	   
	public static boolean AssertionVerificationOfDescriptionChangeInViewMaterial(String [] data){
		   try{
		    if(data[6].contains(data[5])){
			//Verifying the same requirement type of Material contains the our selected material or not
			CI296.GettingText(MaterialDescription);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification.trim(), data[37], "Actual And Expected label name are not matched.please Verify");
			//Verifying Material description  Label name Using Assertion
			log.info("Material Description label Verified Successfully");
			}
			else{
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(MaterialDescription," MaterailDescription"), "Condition failed.Asserition failed");
			//Verifying Material description  Label name Using Assertion
				log.info("Material Description label not appears.Scenario Verified Successfully");
		    }
	}
		   
		catch(Exception e){
		fail=true;
		log.error("Exception in AssertionVerificationOfDescriptionChangeInViewMaterial"+e);
		throw e;
		}
		 return true;
	}
	public static boolean ProductInteralBOMVerification(String[] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(BOMType);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMType, data[44], "Selecting a BOM");
			//Selecting the Existing BOM type as Product Internal.BOM Should Have been created Already.
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
		
			for(int j=46;j<=47;j++)
			{
			CommonFunctions.waitForElementTobeClickable(BOMView);
		    CommonFunctions.clickButtonOrLink(BOMView, "Image", "Image");
		    //Clicking BOM view option to change the view
		    CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(BOMViewChange);
			BOMMode=By.linkText(data[j]);
			CommonFunctions.clickButtonOrLink(BOMMode, "link Text", " one of the View in BOM");
			CommonFunctions.handleAlertPopUp1();
			//Changing the view where ever the 'Match Price' was appearing.
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(MatchPriceColumn, "Match Price Column"), "Match Price Column Found.Assertion failed.");
			//Verifying whether Match Price is column is appearing or not.
			}
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in ProductInteralBOMVerification"+e);
			throw e;
			}
			 return true;
		}
	public static boolean RetailItemBOMVerification(String[] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(BOMType);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMType, data[45], "Selecting a BOM");
			//Selecting the Retail Item Product BOM.BOM Should Have been created Already.
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
			for(int j=47;j<=50;j++)
			{
			
			CommonFunctions.waitForElementTobeClickable(BOMView);
			CommonFunctions.clickButtonOrLink(BOMView, "Image", "Image");
			CommonFunctions.waitForPageLoaded();
			//Clicking BOM view option to change the view
			CommonFunctions.waitForElementTobeClickable(BOMViewChange);
			BOMMode=By.linkText(data[j]);
			CommonFunctions.clickButtonOrLink(BOMMode, "link Text", " one of the View in BOM");
			//Changing the view where ever the 'Match Price' was appearing.
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(MatchPriceColumn, "Match Price Column"), "Match Price Column Found.Assertion failed.");
			//Verifying whether Match Price is column is appearing or not.
			}
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in RetailItemBOMVerification"+e);
			throw e;
			}
			 return true;
		}
	public static boolean AssertionVerificationOfRemovalOfBOMMatchCost(String[] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(BOMType);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMType, data[45], "Selecting a BOM");
			//Selecting the Retail Item Product BOM.BOM Should Have been created Already.
			CommonFunctions.handleAlertPopUp1();
			wait.until(ExpectedConditions.titleIs(data[35]));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(HeaderAttributeIcon);
			CommonFunctions.clickButtonOrLink(HeaderAttributeIcon, "Header Attribute");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(BOMMatchCostSummaryIcon, "BOMMatchCostSummaryIcon"), "BOMMatchCostSummaryIcon found.Assertion failed");
			//Verifying the the element is Not visible in SB server since it has been removed as per the requirement
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in RetailItemBOMVerification");
		    throw e;
			}
			 return true;
		}
	public static boolean AddingMaterialInToBOMAndAssertionVerfication(String[] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(BOMType);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMType, data[45], "Selecting a BOM");
			//Selecting the Retail Item Product BOM.BOM Should Have been created Already.
			CommonFunctions.handleAlertPopUp1();
			wait.until(ExpectedConditions.titleIs(data[35]));
			//Waiting till the expected page Name Appears 
			CommonFunctions.waitForPageLoaded();
			ClickOnUpdateButton();
			//Clicking 'update' Button
		    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
		    CommonFunctions.waitForPageLoaded();
		    CommonFunctions.waitForElementTobeClickable(CI296.AddRowsButton);
		    ChangeFullPlasticview();
			//Changing the view from Full Plastic view
		    AddingRow();
			//Adding a Row
			RowIdentification();
			//Identifying the row above Created
			SaveAndChecInAfterSearchForResinMaterial(data);
			//Save and check in
			ClickOnUpdateButton();
			//Clicking on Update Button
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CI296.AddRowsButton);
			ChangeFullPlasticview();
			//Changing the view from Full Plastic view
			AssertionVerificationOfResin(data);
			//Assertion verification after save and Check in
			ManualResinMaterialUpdationOfMaterialInBOM(data);
			//Entering Manual Values in to the Resin  material
			CommonFunctions.clickButtonOrLink(CI296.saveAndCheckIn, "btn", "Save and Check In");
			//Save And Check in
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
			ClickOnUpdateButton();
			//Clicking on Update Button
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CI296.AddRowsButton);
			ChangeFullPlasticview();
			//Changing the view from Full Plastic view
			AssertionVerificationOfResin(data);
			if(CommonFunctions.error==false){
				log.info("System shall override any manual entry values in the respective BOM Columns if a library material is populated test case verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			}
			ChangeFullLaborview();
			//Changing to Full labor view
			AddingRow();
			//Adding a Row
			RowIdentification();
			//Identifying the row above Created
			SaveAndChecInAfterSearchForLaborMaterial(data);
			//Save and check in
			ClickOnUpdateButton();
			//Clicking on Update Button
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CI296.AddRowsButton);
			ChangeFullLaborview();
			//Changing the view from FullLaborSelection
			CI296.GettingText(OperationType);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[58], "Actual And expected Operation Type Are not matched.Please check.Assertion failed.");
			//Verifying the Operation Type
			if(CommonFunctions.error==false){
				log.info("Values are verified successfully");
			}
			else{
				log.error("Assertion error appears");
			}
			CommonFunctions.waitForElementTobeClickable(OperationType);
			action.moveToElement(driver.findElement(OperationType)).doubleClick().perform();
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(OperationTypeDropDown, data[63], "Operation Drop Down value");
			log.info("Operation drop down value has been selected manually");
			CommonFunctions.clickButtonOrLink(CI296.saveAndCheckIn, "btn", "Save and Check In");
			//Save And Check in
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
			ClickOnUpdateButton();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CI296.AddRowsButton);
			ChangeFullLaborview();
			//Changing the view from FullLaborSelection
			CI296.GettingText(OperationType);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[58], "Actual And expected Operation Type Are not matched.Please check.Assertion failed.");
			//Verifying the Operation Type
			if(CommonFunctions.error==false){
				log.info("System shall override any manual entry values in the respective BOM Columns if a library material is populated test case verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			}
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in AddingMaterialInToBOMAndAssertionVerfication"+e);
		    throw e;
			}
			 return true;
		}
	
	public static boolean RowIdentification(){
		try{
			
			//Adding a row And identifying the xpath of the added elements for the fields that we need to verify as per requirement
			for(rowValue=9;rowValue<=100;rowValue++){
				 secondRowValue= "//td[@id='r"+rowValue+"_materialDescription']";
				 AddingSecondRow=By.xpath(secondRowValue);
				 String Text=driver.findElement(AddingSecondRow).getText();
				 if(Text.trim().equals("")){
					 resinMaterialDescription="//td[@id='r"+rowValue+"_hbDescription']";
					 ResinMaterialDescription=By.xpath(resinMaterialDescription);
					 resinType="//td[@id='r"+rowValue+"_MATERIAL_hbResinType']";
					 ResinType=By.xpath(resinType);
					 supplierName="//td[@id='r"+rowValue+"_hbSupplier']";
					 SupplierName=By.xpath(supplierName);
					 unitPrice="//td[@id='r"+rowValue+"_hbUnitPrice']";
					 UnitPrice=By.xpath(unitPrice);
					 unit="//td[@id='r"+rowValue+"_hbUnit']";
					 Unit=By.xpath(unit);
					 dropDownOfUnit="//td[@id='r"+rowValue+"_hbUnit']/div/select[1]";
					 DropDownOfUnit=By.xpath(dropDownOfUnit);
					 operationType="//td[@id='r"+rowValue+"_hbOperationType']";
					 OperationType=By.xpath(operationType);
					 operationTypeDropDown="//td[@id='r"+rowValue+"_hbOperationType']/div/select[1]";
					 OperationTypeDropDown=By.xpath(operationTypeDropDown);
					 CommonFunctions.waitForElementTobeClickable(AddingSecondRow);
					 action.moveToElement(driver.findElement(AddingSecondRow)).doubleClick().perform();
						//double Clicking on Material Text Box
						break;
				 }
				 else{
					 log.info("This Element is Already filled.checking another Element");
				 }
			}
		}
			catch (Exception e){
				fail=true;
				log.error("Exception while RowIdentification "+e);
				throw e;
			}
			return true;
		}
	public static boolean ChangeFullPlasticview() throws Exception{
		try{
			    CommonFunctions.waitForElementTobeClickable(ViewIcon);
			    CommonFunctions.clickButtonOrLink(ViewIcon, "Image", "View Image");
			    //Clicking On View Icon
			    CommonFunctions.waitForElementTobeClickable(ViewChangeTable);
			    CommonFunctions.waitForElementTobeClickable(FullPlasticSelection);
			    CommonFunctions.clickButtonOrLink(FullPlasticSelection,"Hyper Link","Full Plastic view");
			    CommonFunctions.waitForPageLoaded();
		}
		catch (Exception e){
			fail=true;
			log.error("Exception ChangeFullPlasticview "+e);
			throw e;
		}
		return true;
	}
	
	public static boolean AssertionVerificationOfResin(String [] data){
		try{
			CommonFunctions.waitForVisibilityOfElement(ResinMaterialDescription);
			CI296.GettingText(ResinMaterialDescription);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[52], "Actual And expected Descriptions Are not matched.Please check.Assertion failed.");
			//Verifying the Material Description
			CI296.GettingText(ResinType);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[53], "Actual And expected Resintypes Are not matched.Please check.Assertion failed.");
			//verifying the resin types
			CI296.GettingText(SupplierName);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[54], "Actual And expected Supplier Names Are not matched.Please check.Assertion failed.");
			//verifying the Supplier Names
			CI296.GettingText(UnitPrice);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[55], "Actual And expected Unit Prices Are not matched.Please check.Assertion failed.");
			//verifying the Unit Price
			CI296.GettingText(Unit);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[56], "Actual And expected Units Are not matched.Please check.Assertion failed.");
			//verifying the unit
			if(CommonFunctions.error==false){
				log.info("values Are Verified Successfully");
			}
			else{
				log.error("Assertion Error Apppears");
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception while AssertionVerificationOfResin"+e);
			throw e;
		}
		return true;
	}
	
	public static boolean ManualResinMaterialUpdationOfMaterialInBOM(String [] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(ResinMaterialDescription);
			action.moveToElement(driver.findElement(ResinMaterialDescription)).doubleClick().perform();
			action.sendKeys(data[59]).perform();
			//Updating a value manually in Description
			log.info("Material Description value Manually entered in BOM");
			CommonFunctions.waitForElementTobeClickable(SupplierName);
			action.moveToElement(driver.findElement(SupplierName)).doubleClick().perform();
			action.sendKeys(data[60]).perform();
	        //Updating a Value Manually in Supplier name
			log.info("Supplier Name value Manually entered in BOM");
			CommonFunctions.waitForElementTobeClickable(UnitPrice);
			action.moveToElement(driver.findElement(UnitPrice)).doubleClick().perform();
			action.sendKeys(data[61]).perform();
			//Updating a Value Manually in Unit price
			log.info("Unit Price value Manually entered in BOM");
			CommonFunctions.waitForElementTobeClickable(Unit);
			action.moveToElement(driver.findElement(Unit)).doubleClick().perform();
			CommonFunctions.waitForElementTobeClickable(DropDownOfUnit);
	        CommonFunctions.selectFromDropDownByVisibleTextUpdated(DropDownOfUnit, data[62],"Selecting DropDown");
	    	//Updating a Value Manually in Unit
	    	log.info("Unt drop down value selected Unit Manually in BOM");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception while ManualUpdationOfMaterialInBOM"+e);
			throw e;
		}
		return true;
	}
	public static boolean ClickOnUpdateButton() throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.updateBtn);
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update");
			//Clicking Again update Button
			CommonFunctions.waitForPageLoaded();
		}
		catch (Exception e){
			fail=true;
			log.error("Exception ChangeFullPlasticview "+e);
			throw e;
		}
		return true;
	}
	public static boolean ChangeFullLaborview() throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(ViewIcon);
			CommonFunctions.clickButtonOrLink(ViewIcon, "Image", "View Image");
			//Clicking On View Icon
		    CommonFunctions.waitForElementTobeClickable(ViewChangeTable);
			CommonFunctions.waitForElementTobeClickable(FullLaborSelection);
			CommonFunctions.clickButtonOrLink(FullLaborSelection,"Hyper Link","Full Plastic view");
			//Changing the view from FullLaborSelection
			CommonFunctions.waitForPageLoaded();
		}
		catch (Exception e){
			fail=true;
			log.error("Exception ChangeFullPlasticview "+e);
			throw e;
		}
		return true;
	}

	public static boolean AddingRow() throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(CI296.AddRowsButton);
			CommonFunctions.clickButtonOrLink(CI296.AddRowsButton, "Add Rows Button");
			//Clicking to add rows Button
			CommonFunctions.waitForElementTobeClickable(AddingARowTable);
			CommonFunctions.waitForElementTobeClickable(CI296.InsertAfterLink);
			CommonFunctions.clickButtonOrLink(CI296.InsertAfterLink, "link", "Add row link");
			//Clicking Insert After Link
			CommonFunctions.waitForPageLoaded();
		}
		catch (Exception e){
			fail=true;
			log.error("Exception AddingRow "+e);
			throw e;
		}
		return true;
	}
	public static boolean SaveAndChecInAfterSearchForResinMaterial(String [] data) throws Exception{
		try{
			CommonFunctions.clickButtonOrLink(CI296.btnMaterials, "btn", "Material");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			driver.switchTo().frame("materialframe");
			//Entering material Name
		    CommonFunctions.waitForElementTobeClickable(CI296.MaterialNameText);
			CommonFunctions.enterTextInTextbox(CI296.MaterialNameText, data[51]);
			//Entering a Material Name
			CommonFunctions.waitForElementTobeClickable(CI296.btnSearchMaterial);
			CommonFunctions.clickButtonOrLink(CI296.btnSearchMaterial, "Search");
			CommonFunctions.waitForPageLoaded();
			//Click on Search
			log.info("This Entered is resin Material As per the requirement");
			CI296.SupplierBasedDynamicElementIdentification=".//a[contains(text(),'"+data[51]+"')]//preceding::img[1]";
			CI296.SupplierBasedDynamicElement=By.xpath(CI296.SupplierBasedDynamicElementIdentification);
			CommonFunctions.waitForElementTobeClickable(CI296.SupplierBasedDynamicElement);
			//Click on 'Insert Material' image
			CommonFunctions.clickButtonOrLink(CI296.SupplierBasedDynamicElement, "HyperLink");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(CI296.saveAndCheckIn, "btn", "Save and Check In");
			//clicking save and check in
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
		}
		catch (Exception e){
			fail=true;
			log.error("Exception SaveAndChecInAfterSearchForResinMaterial "+e);
			throw e;
		}
		return true;
	}
	public static boolean SaveAndChecInAfterSearchForLaborMaterial(String [] data) throws Exception{
		try{
			CommonFunctions.clickButtonOrLink(CI296.btnMaterials, "btn", "Material");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			driver.switchTo().frame("materialframe");
			//Entering material Name
		    CommonFunctions.waitForElementTobeClickable(CI296.MaterialNameText);
			CommonFunctions.enterTextInTextbox(CI296.MaterialNameText, data[57]);
			//Entering a Material Name
			CommonFunctions.waitForElementTobeClickable(CI296.btnSearchMaterial);
			CommonFunctions.clickButtonOrLink(CI296.btnSearchMaterial, "Search");
			CommonFunctions.waitForPageLoaded();
			//Click on Search
			log.info("This Entered is resin Material As per the requirement");
			CI296.SupplierBasedDynamicElementIdentification=".//a[contains(text(),'"+data[57]+"')]//preceding::img[1]";
			CI296.SupplierBasedDynamicElement=By.xpath(CI296.SupplierBasedDynamicElementIdentification);
			CommonFunctions.waitForElementTobeClickable(CI296.SupplierBasedDynamicElement);
			//Click on 'Insert Material' image
			CommonFunctions.clickButtonOrLink(CI296.SupplierBasedDynamicElement, "HyperLink");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(CI296.saveAndCheckIn, "btn", "Save and Check In");
			//clicking save and check in
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
		}
		catch (Exception e){
			fail=true;
			log.error("Exception SaveAndChecInAfterSearchForLaborMaterial "+e);
			throw e;
		}
		return true;
	}
	
 @BeforeMethod
	  public void Refresh(){
	  if(driver!=null&& runmodes[count+1].equalsIgnoreCase("y")){
			  driver.navigate().refresh();
			  log.info("Driver is refreshed");
			}
		  else{
			  log.info("Next test case as set to no.So Drive won't be refreshed OR No Driver is launched");
		  }
	  }
	
	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "SKIP");
		    
		else if(fail ||CommonFunctions.error){
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
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
