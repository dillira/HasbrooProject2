package com.hasbro.ci2018;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Product;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

public class CI307 extends TestsuiteBase {
	

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
    static boolean fail=false;
	static boolean isTestPass=true;
    static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	
	public static By Factory                                      = By.xpath("//a[text()='Factory:']");
	public static By NameTextBox                                  = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By ChooseButton                                 = By.xpath("//a[text()='(choose)']");
	public static By ViewChangeTable                              = By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table");
	public static By HeaderAttribute                              = By.xpath("//div[@id='genDetails_plus']/a[1]/img");
	public static By ViewChangeOnBOMDetail                        = By.xpath(".//*[@id='DETAILS_PAGEDisplay']/table/tbody/tr[3]/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr/td[4]/a/img");
	public static By ViewChangeTableOnBOM                         = By.xpath(".//*[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table");
	public static By ExpectedUsageValue                           = By.xpath("//a[contains(text(),'General / Deco Labor')]//following::tr[1]/td[9]");
	//WebElements for Purchased Parts
	public static By ViewChangeOfPurchasedParts                   = By.xpath("//div[@id='hbPurchasedPartsTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By UnitDropDownClickPurchasedParts              = By.xpath("//td[@id='r3_hbUnit']");
	public static By UnitDropDownSeletionPurchasedParts           = By.xpath("//td[@id='r3_hbUnit']/div/select");
	public static By UsagePerKPurchasedParts                      = By.xpath("//td[@id='r3_hbUsagePerK']");
	public static By UsagePerKTextBoxPurchasedParts               = By.xpath("//td[@id='r3_hbUsagePerK']/div/input");
	public static By UnitDropDownClickPurchasedPartsSecondRow     = By.xpath("//td[@id='r9_hbUnit']");
	public static By UnitDropDownSeletionPurchasedPartsSecondRow  = By.xpath("//td[@id='r9_hbUnit']/div/select");
	public static By UsagePerKPurchasedPartsSecondRow             = By.xpath("//td[@id='r9_hbUsagePerK']");
	public static By UsagePerKTextBoxPurchasedPartsSecondRow      = By.xpath("//td[@id='r9_hbUsagePerK']/div/input");
	public static By AddRowButtonOfPurchasedParts                 = By.xpath("//img[@id='menuImage3']");
	public static By AddRowTable                                  = By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table/tbody/tr/td/font");                
	
	//WebElements for Molding Labor
	public static By ViewChangeOfMoldingLabor                     = By.xpath("//div[@id='hbMoldingLaborTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By MoldingLaborUpColumn                         = By.xpath("//td[@id='r8_hbUp']");
	public static By MoldingLaborUpColumntextBox                  = By.xpath("//td[@id='r8_hbUp']/div/input");
	public static By TotalCavKMoldingLabor                        = By.xpath("//td[@id='r8_hbTotalCav']");
	public static By TotalCavKMoldingLaborTextBox                 = By.xpath("//td[@id='r8_hbTotalCav']/div/input");
	//public static By MoldingLaborUpColumnSecondRow              = By.xpath("//td[@id='r10_hbUp']");
	//public static By MoldingLaborUpColumntextBoxSecondRow       = By.xpath("//td[@id='r10_hbUp']/div/input");
	//public static By TotalCavKMoldingLaborSecondRow             = By.xpath("//td[@id='r10_hbTotalCav']");
	//public static By TotalCavKMoldingLaborTextBoxSecondRow      = By.xpath("//td[@id='r10_hbTotalCav']/div/input");
	public static By ToolTypeOfMoldingLabor                       = By.xpath("//td[@id='r8_hbToolType']");
	public static By ToolTypeDropDownOfMoldingLabor               = By.xpath("//td[@id='r8_hbToolType']/div/select");
	public static By MaterialOfMoldinglabor                       = By.xpath("//td[@id='r8_materialDescription']");
	public static By MaterialTextBoxOfMoldinglabor                = By.xpath("//td[@id='r8_materialDescription']/div/textarea");
	public static By AddRowButtonOfMoldingLabor                   = By.xpath("//img[@id='menuImage8']");
	
	//WebElements for GenralDecoLabor
	public static By ViewChangeOFGeneralDecoLabor                 = By.xpath("//div[@id='hbGeneralOrDecoLaborTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By MaterialOfGeneralDecoLabor                   = By.xpath("//td[@id='r7_materialDescription']");
	public static By MaterialTextBoxOfGeneralDecoLabor            = By.xpath("//td[@id='r7_materialDescription']/div/textarea");
	public static By OperationTypeOfGeneralDecoLabor              = By.xpath("//td[@id='r7_hbOperationType']");
	public static By OperationDropDownOfGeneralDecoLabor          = By.xpath("//td[@id='r7_hbOperationType']/div/select");
	public static By UsagePerKInGeneralDecoLabor                  = By.xpath("//td[@id='r7_hbUsagePerK']");
	public static By UsagePerKTextBoxInGeneralDecoLabor           = By.xpath("//td[@id='r7_hbUsagePerK']/div/input");
	
	
	public static By ViewSelection;
	
	
	
	
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
					log.info("col2 :" + data[1]);
					log.info("col3 :" + data[2]);
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			
		switch(data[2])
		{
		case "Expected Usage Calculation for INJ Tool Type":
		ExceptionUsageCalculation(data);
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
	
	public static boolean ExceptionUsageCalculation(String[] data) throws Exception{
	try{
		CI296.navigateToProduct(data);
		//Navigating to an existing product
		SearchExistingProduct(data);
		//Searching the Existing Product
		//Browse to Material Tab
		CI296.navigateToMaterialTab(data);
		//Click on Add New BOM tab
		creatingRetailItemVendorBOM(data);
		//Creating a BOM
		FillPurchasedParts(data);
		//Fill the Purchased Parts
		FillMoldingLabor(data);
		//Filing Molding labor Parts
		SaveAndCheckIn();
		//Save And Check in BOM
		UpdateBOM();
		//Update BOM
		FillGeneralDecoLabor(data);
		//Filling General Deco Labor Value
		SaveAndCheckIn();
		//Save And Check in BOM
		AssertionVerificationOfExpectedUsage(data);
		//Assertion verification of Expected Usage
	}
	catch(Exception e)
	{
	fail=true;
	log.error("Exception in ExceptionUsageCalculation" +e);
	throw e;
	}
	return true;
}
	public static boolean AssertionVerificationOfExpectedUsage(String [] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(HeaderAttribute);
			CommonFunctions.clickButtonOrLink(HeaderAttribute, "Icon", "Plus button");
			//Clicking on Header Attribute
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(ViewChangeOnBOMDetail);
			CommonFunctions.clickButtonOrLink(ViewChangeOnBOMDetail, "Image", "ViewChangeOnBOM");
			//Clicking on View Change Button
			CommonFunctions.waitForElementTobeClickable(ViewChangeTableOnBOM);
			ViewSelection=By.linkText(data[39]);
			CommonFunctions.waitForElementTobeClickable(ViewSelection);
			CommonFunctions.clickButtonOrLink(ViewSelection, "Hyper Link", "Costing: Full General / Deco Labor");
			//Selecting the view
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(ExpectedUsageValue);
			CI296.GettingText(ExpectedUsageValue);
			CommonFunctions.AssertEqualsVerification(CI296.textForVerification, data[42], "Expected and Actual Expected Usage Values are not Matched.Assertion failed.Please check");
			//Assertion Verification of Expected usage
			log.info("Expected Usage value for "+data[29]+"Solid Type has been verified Successfully.TEST CASE VERIFIED SUCCESSFULLY");
			}
		catch (Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationOfExpectedUsage"+e);
			throw e;
		}
		return true;
	}
	
	
	
	public static boolean FillGeneralDecoLabor(String [] data) throws Exception{
	    try{
	    	CommonFunctions.waitForElementTobeClickable(ViewChangeOFGeneralDecoLabor);
	    	CommonFunctions.clickButtonOrLink(ViewChangeOFGeneralDecoLabor, "Image", "ChangeViewImage");
	    	//Clicking the View change Image
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(ViewChangeTable));
	    	ViewSelection=By.linkText(data[39]);
	    	CommonFunctions.waitForElementTobeClickable(ViewSelection);
	    	CommonFunctions.clickButtonOrLink(ViewSelection, "HyperLink", "Costing: Full General /Deco Labor");
	    	//Changing the view
	    	CommonFunctions.waitForPageLoaded();
	    	CommonFunctions.waitForElementTobeClickable(MaterialOfGeneralDecoLabor);
	    	CommonFunctions.clickButtonOrLink(MaterialOfGeneralDecoLabor, "textBox", "MaterialTextBox");
	    	CommonFunctions.waitForElementTobeClickable(MaterialTextBoxOfGeneralDecoLabor);
	    	CommonFunctions.enterTextInTextboxUpdated(MaterialTextBoxOfGeneralDecoLabor, data[16], "Material Text box");
	    	//Entering MaterailName Into Material text Box
	    	CommonFunctions.waitForElementTobeClickable(OperationTypeOfGeneralDecoLabor);
	    	CommonFunctions.clickButtonOrLink(OperationTypeOfGeneralDecoLabor, "DropDown", "OperationTypeDropDown");
	    	CommonFunctions.waitForElementTobeClickable(OperationDropDownOfGeneralDecoLabor);
	    	CommonFunctions.selectFromDropDownByVisibleTextUpdated(OperationDropDownOfGeneralDecoLabor,data[40], "OperationType Drop down");
	    	//Selecting one of the Operation Type Value
	    	CommonFunctions.waitForElementTobeClickable(UsagePerKInGeneralDecoLabor);
	    	CommonFunctions.clickButtonOrLink(UsagePerKInGeneralDecoLabor, "TextBox", "General decoLaborText Box");
	    	CommonFunctions.waitForElementTobeClickable(UsagePerKTextBoxInGeneralDecoLabor);
	    	CommonFunctions.enterTextInTextboxUpdated(UsagePerKTextBoxInGeneralDecoLabor, data[41], "GeneralDecoLaborValue");
	    	//Entering the General DecoLabor Value
	    }
	    catch(Exception e){
	    	fail=true;
	    	log.error("Exception in UpdateBOM"+e);
	    	throw e;
	    }
	    return true;
	    }
		
	
    public static boolean UpdateBOM() throws Exception{
    try{
    	CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.updateBtn);
		CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update");
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
		CommonFunctions.waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CI296.AddRowsButton));
    }
    catch(Exception e){
    	fail=true;
    	log.error("Exception in UpdateBOM"+e);
    	throw e;
    }
    return true;
    }
	
	public static String creatingRetailItemVendorBOM(String [] data) throws Exception{
		try{
			CommonFunctions.clickButtonOrLink(CI296.addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(CI296.bomtypeid, data[13]);
			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(CI296.initializebom,"btn", "Initialize bom");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.titleIs(data[9]));
			wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.headingCreateBOM));
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			//Select colorway
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.wave);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.wave, data[18]);
			//Select Wave
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.currency);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.currency, data[19]);
			//Select Currency
			CommonFunctions.gettingParentWindow();
			CommonFunctions.waitForElementTobeClickable(Factory);
			CommonFunctions.clickButtonOrLink(Factory, "HyperLink", "Factory");
			//Clicking Factory HyperLink
		    CommonFunctions.switchingChildWindow();
			CommonFunctions.waitForElementTobeClickable(NameTextBox);
			CommonFunctions.enterTextInTextboxUpdated(NameTextBox, data[7], "Name text Box");
			//Entering the Value in Name text Box
			CommonFunctions.waitForElementTobeClickable(CI296.FirstSearchButton);
			CommonFunctions.clickButtonOrLink(CI296.FirstSearchButton, "Serach Button");
			//Clicking First Serach Buuton
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(ChooseButton);
			CommonFunctions.clickButtonOrLink(ChooseButton, "Link", "Choose");
			//Clicking Choose Button
		    CommonFunctions.switchParentWindow();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(Product.createBtn);
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			//click on Create
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(CI296.AddRowsButton));
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in creatingRetailItemVendorBOM"+e);
			throw e;
		}
		return CI296.BOMname;
	}
		
	public static boolean SearchExistingProduct(String [] data) throws Exception{
		try{
			CI299.ProductType=By.linkText(data[4]);
			CommonFunctions.waitForElementTobeClickable(CI299.ProductType);
			CommonFunctions.clickButtonOrLink(CI299.ProductType, "HyperLink", "ReatilItem");
			//Choosing the product type as Retail Item
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CI299.ProductNumberSearch);
			CommonFunctions.enterTextInTextboxUpdated(CI299.ProductNumberSearch, data[5], "product number");
			//Entering the Product Number
			CommonFunctions.clickButtonOrLink(CI299.SearchProductButton, "Button", "Search");
			wait.until(ExpectedConditions.titleIs(data[6]));
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
	
	public static boolean FillPurchasedParts(String [] data) throws Exception{
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(ViewChangeOfPurchasedParts));
			CommonFunctions.clickButtonOrLink(ViewChangeOfPurchasedParts, "View Button");
			//Clicking on View change Image
			wait.until(ExpectedConditions.visibilityOfElementLocated(ViewChangeTable));
			//Waiting till View change table appears
			ViewSelection=By.linkText(data[20]);
			CommonFunctions.clickButtonOrLink(ViewSelection,":Costing Full Plastic");
			//Selecting Costing Full
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(UnitDropDownClickPurchasedParts);
			CommonFunctions.clickButtonOrLink(UnitDropDownClickPurchasedParts, "Dropdown", "unitPriceDropDown");
			CommonFunctions.waitForElementTobeClickable(UnitDropDownSeletionPurchasedParts);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(UnitDropDownSeletionPurchasedParts, data[21], "Unit Drop down selection");
			//Selecting the Unit value in drop-down
			CommonFunctions.waitForElementTobeClickable(UsagePerKPurchasedParts);
			CommonFunctions.clickButtonOrLink(UsagePerKPurchasedParts, "textBox", "UserPerK");
			CommonFunctions.waitForElementTobeClickable(UsagePerKTextBoxPurchasedParts);
			CommonFunctions.enterTextInTextboxUpdated(UsagePerKTextBoxPurchasedParts, data[22], "UserPerK");
			//Entering UserPerK Value
			CommonFunctions.waitForElementTobeClickable(AddRowButtonOfPurchasedParts);
			CommonFunctions.clickButtonOrLink(AddRowButtonOfPurchasedParts, "Icon", "Add row icon");
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddRowTable));
			ViewSelection=By.linkText(data[30]);
			CommonFunctions.clickButtonOrLink(ViewSelection,":Costing Full Plastic");
			//Adding a Second Row in purchased Cost
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(UnitDropDownClickPurchasedPartsSecondRow);
			CommonFunctions.clickButtonOrLink(UnitDropDownClickPurchasedPartsSecondRow, "Dropdown", "unitPriceDropDown");
			CommonFunctions.waitForElementTobeClickable(UnitDropDownSeletionPurchasedPartsSecondRow);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(UnitDropDownSeletionPurchasedPartsSecondRow, data[23], "Unit Drop down selection");
			//Selecting the Unit value in drop-down in the added row
			CommonFunctions.waitForElementTobeClickable(UsagePerKPurchasedPartsSecondRow);
			CommonFunctions.clickButtonOrLink(UsagePerKPurchasedPartsSecondRow, "textBox", "UserPerK");
			CommonFunctions.waitForElementTobeClickable(UsagePerKTextBoxPurchasedPartsSecondRow);
			CommonFunctions.enterTextInTextboxUpdated(UsagePerKTextBoxPurchasedPartsSecondRow, data[24], "UserPerK");
			//Entering UserPerK Value in the added row
			
		}
		catch(Exception e)
		{
		fail=true;
		log.error("Exception in FillPurchasedCost"+e);
		throw e;
		}
		return true;
		}
	
	public static boolean FillMoldingLabor(String [] data) throws Exception{
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(ViewChangeOfMoldingLabor));
			CommonFunctions.clickButtonOrLink(ViewChangeOfMoldingLabor, "View Button");
			//Clicking on View change Image
			wait.until(ExpectedConditions.visibilityOfElementLocated(ViewChangeTable));
			//Waiting till View change table appears
			ViewSelection=By.linkText(data[28]);
			CommonFunctions.clickButtonOrLink(ViewSelection,":Costing Full Molding Labor");
			//Selecting Costing Full
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(ToolTypeOfMoldingLabor);
			CommonFunctions.clickButtonOrLink(ToolTypeOfMoldingLabor,"DropDown","Tool Type");
			CommonFunctions.waitForElementTobeClickable(ToolTypeDropDownOfMoldingLabor);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(ToolTypeDropDownOfMoldingLabor, data[29], "ToolType");
			//Selecting a Value From Tool Type Drop-down
			CommonFunctions.waitForElementTobeClickable(MoldingLaborUpColumn);
			CommonFunctions.clickButtonOrLink(MoldingLaborUpColumn,"DropDown","Tool Type");
			CommonFunctions.waitForElementTobeClickable(MoldingLaborUpColumntextBox);
			CommonFunctions.enterTextInTextboxUpdated(MoldingLaborUpColumntextBox, data[10], "Up Column Value");
			//Entering Up column value
			CommonFunctions.waitForElementTobeClickable(TotalCavKMoldingLabor);
			CommonFunctions.clickButtonOrLink(TotalCavKMoldingLabor,"DropDown","Tool Type");
			CommonFunctions.waitForElementTobeClickable(TotalCavKMoldingLaborTextBox);
			CommonFunctions.enterTextInTextboxUpdated(TotalCavKMoldingLaborTextBox, data[12], "Up Column Value");
			//Entering Totla cav column value
			CommonFunctions.waitForElementTobeClickable(MaterialOfMoldinglabor);
			action.moveToElement(driver.findElement(MaterialOfMoldinglabor)).doubleClick().perform();
			CommonFunctions.clickButtonOrLink(CI296.btnMaterials, "btn", "Material");
			//Clicking on the Material Button
			CommonFunctions.waitForPageLoaded();
			CI296.searchMaterial(data);
			//Searching the Material
			//Pre-requisite:The material That we are adding should have COO same as the Funs kool
			CommonFunctions.waitForElementTobeClickable(CI296.imgInsertMat);
			//Clicking on First Image Insert
			CommonFunctions.clickButtonOrLink(CI296.imgInsertMat, "Img", "Insert Material");
			CommonFunctions.waitForPageLoaded();
			
		}
		catch(Exception e)
		{
		fail=true;
		log.error("Exception in FillMoldingLabor"+e);
		throw e;
		}
		return true;
		}
	
	public static boolean SaveAndCheckIn() throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			driver.switchTo().frame("mainFrame");
			//Click on saveAndCheckIn button
			CommonFunctions.clickButtonOrLink(CI296.saveAndCheckIn, "btn", "Save and Check In");
			//handling pop-up
			CommonFunctions.handleAlertPopUp1();
		    driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			//Verifying Header Attribute whether it is appearing or not
	     	CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.headerAttributes);
			
		}
		catch(Exception e)
		{
		fail=true;
		log.error("Exception in SaveAndCheckIn"+e);
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
