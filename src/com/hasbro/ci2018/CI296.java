package com.hasbro.ci2018;
/**
 * @author Anjali Gupta
 *
 */
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.hasbro.security.BOMInternalMainUser;
import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Material;
import com.hasbro.security.Product;
import com.hasbro.views.LineSheetView;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;


public class CI296 extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
    static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	public static Actions action;
	static String strBOM;
	static String BOMname;
	static String pageTitle;
	static String textForVerification;

	public static By btnSearch =  By.id("SearchButton2");
	public static By firstMNameinList =  By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[2]/td[4]/a");
	public static By materialDetails =  By.xpath("//td[contains(text(),'Material Details: ')]");
	public static By ddSupplier =  By.id("supplierLinkId");
	public static By ddMaterialSupplierActions =  By.id("materialSupplierActions");
	public static By txtPrice =  By.xpath("//td[contains(text(),'Price')]//following::input[1]");
	public static By btnSave =  By.xpath("//a[text()='Save']");  
	public static By firstAction =  By.xpath("//table/tbody/tr[2]/td[3]/a[text()='Actions']"); 
	public static By view =  By.xpath("//div[@id='secondaryActionOptions']/div[2]/a"); 
	public static By txtName =  By.xpath("//td[contains(text(),'Name')]//following::input[1]"); 
	public static By ddMBS =  By.xpath("//td[contains(text(),'Material BOM Status')]//following::select[1]"); 
	public static By btnMaterials =  By.xpath("//a[text()='Materials']"); 
	public static By materialType =  By.id("materialFlexTypeId");
	public static By createdDate =  By.id("LCSMATERIAL_CREATESTAMPA2FromInput"); 
	public static By btnSearchMaterial =  By.xpath("//td[@class='button']/a[text()='Search']"); 
	public static By imgInsertMat =  By.xpath("//img[contains(@src,'linker.gif')]");
	public static By SourcingTab=By.xpath("//a[text()='Sourcing']");
	public static String InactiveSupplierDropDownelement;
	public static By InactiveSupplierDropDown;
	public static By saveAndCheckIn =  By.xpath("//a[text()='Save and Check In']");
	public static By MaterialPrice = By.xpath(".//td[@id='materialPrice']");
	public static By MaterialName= By.xpath(".//td[contains(text(),'Name')]//following::input[1]");
	public static By ResinDescription= By.xpath(".//td[contains(text(),'*Resin Description')]//following::input[1]");
	public static By SeasonLink=By.xpath("//a[text()='Exchange Rate Season:']");
	public static By SourcingOption= By.xpath("//a[text()='Sourcing']");
	public static By ActionButton=By.xpath("//*[@id='materialSupplierActions']");
	public static By ErrorText=By.xpath("//*[@id='contentDiv']/table/tbody/tr[1]//following::td[1][@class='ERROR']");
	public static By MaterialNameText=By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By AddedSupplierVerification=By.xpath("//a[text()='GAIN POWER INDUSTRIES (HONG KONG) L IMITED']");
	public static By AddedInValidSupplierVerification=By.xpath("//a[text()='FUNSKOOL (INDIA) LTD']");
	public static By SupplierVerification=By.xpath(".//*[@id='null']/div/div[2]/table/tbody/tr/td[2]"); 
	public static By MaterialSupplierActiveCheckBox=By.xpath(".//*[contains(text(),'Material Supplier Active')]//following::input[2]");
	public static By SaveButton = By.xpath(".//a[text()='Save']");
	public static By SearchResultsForMaterial=By.xpath("//span[contains(text(),'Search Results for Material')]");
	public static By ClickFirstOption=By.xpath("//a[contains(text(),'Actions')]//following::a[1]");
	public static By NewButton=By.xpath("//a[text()='New']");
	public static By ResinLink=By.xpath("//a[text()='Resin']");
	public static By MaterialDescription=By.xpath(".//td[contains(text(),'*Material Description')]//following::input[1]");
    public static By ChooseButton=By.xpath("//a[text()='(choose)']");
    public static By CreateButton=By.xpath("//a[text()='Create']");
    public static By ProductLink=By.linkText("Product");
    public static By InsertAfterLink=By.linkText("Insert After");
    public static By ProductTextBox=By.xpath("//td[contains(text(),'Product Number')]//following::input[1]");
    public static By ProductSearchResults=By.xpath("//span[contains(text(),'Search Results for Product')]");
    public static By ChooseRetailItem=By.xpath("//td[contains(text(),'Retail Item')]//preceding::a[1]");
    public static By SaveAndCheckInCutton=By.xpath("//a[text()='Save and Check In']");
	public static By ViewOfBOM=By.xpath(".//*[@id='hbPlasticsTabEditorTableDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[4]/a/img");
	public static By Viewtable=By.xpath(".//*[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td");
//	public static By costingFullOption=By.xpath(".//a[text()='Costing: Full']");
	public static By costingFullOption=By.linkText("Costing: Full");
	public static By materialSupplierActiveStatus=By.xpath(".//*[@id='hbMatSupplierActive']");
	public static By addNewbomTab= By.xpath(".//*[@id='ADD_PAGETab']/a");
	public static By bomtypeid= By.id("bomTypeId");
	public static By initializebom= By.xpath("//a[contains(text(),'Initialize BOM')]");
	public static By bomId= By.xpath("//*[@id='bomId']");
	
	//Add supplier Window
	public static By SupplierOption= By.xpath("//a[text()='Supplier']");
	public static By SupplierName =By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By FirstSearchButton =By.xpath(".//*[@id='SearchButton1']");
	public static By CheckSearchResultCheckBox=By.xpath("//*[@id='chooserResultsDiv']/table[1]/tbody/tr[5]/td[1]/div[3]/table[1]/tbody/tr[2]/td[1]/input[1]");
	public static By SelectAllCheckBox=By.xpath(".//*[@id='selectAllCheckBox']");
	public static By SelectButton =By.xpath("//*[@id='chooserResultsDiv']/table[1]/tbody/tr[4]/td[1]/a[1]");
	public static By SupplierCategoryDropDown=By.xpath("//td[contains(text(),'Supplier Category')]//following::select[2]");
	public static By SupplierCategoryDropDownFirstValue=By.xpath("//td[contains(text(),'Supplier Category')]//following::select[2]/option[1]");
	public static By RemoveButton = By.xpath("//td[contains(text(),'Supplier Category')]//following::a[2]");
	public static By VendorSupplier= By.xpath("//*[contains(@id,'T')]/table[1]/tbody/tr[2]/td[1]/input[1]");
	public static By SelectButtonOfVendor =By.xpath(".//*[@id='chooserResultsDiv']/table/tbody/tr[8]/td/a");
	public static By NameTextBox=By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By VMRVendorCode=By.xpath("//td[contains(text(),'VMR Vendor Code')]//following::input[1]");
	public static By FactoryLink=By.xpath("//a[text()='Factory']");
	public static By AddRowsButton=By.xpath("//img[@id='menuImage1']");
	public static By RowTwoMaterial=By.xpath(".//*[@id='r9_materialDescription']");
	public static By SupplierBasedDynamicElement;
	public static String SupplierBasedDynamicElementIdentification;
	public static boolean elementIsVisible=false;
	
	
	

	
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
			log.debug("Inside testcase for CI296");
			log.info("col0 :" + data[0]);
			log.info("col2 :" + data[2]);
			log.info("col3 :" + data[3]);

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
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}

			switch (data[2]) {
			/******************/
		
			case "Material Supplier Update":
				log.info("In side :-> " + data[2]);	
			    TC1_MaterialSupplierUpdate(data);
				log.info("Out side :-> " + data[2]);
				break;
            case "Verification of Adding Valid Supplier Category to the Material": 
				log.info("In side :-> " + data[2]);
				TC2_validSupplierCategoryVerification(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Verification of Adding InValid Supplier Category to the Material": 
				log.info("In side :-> " + data[2]);	
			    TC3_InvalidSupplierCategoryVerification(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Verification of Adding Hasbro Internal Supplier to the Material":
			    log.info("In side :-> " + data[2]);	
			    TC4_HasbroInternalSupplierVerification(data);
			    log.info("Out side :-> " + data[2]);
			    break;
			case "Verification Of Adding Factory Supplier to the Material":
			    log.info("In side :-> " + data[2]);
			    TC5_factorySupplierVerification(data);
			    log.info("Out side :-> " + data[2]);
			    break;
			case "Material Insertion in BOM":
				log.info("In side :-> " + data[2]);
			    TC6_MaterialInsertionInBOM(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Restriction of Invalid Supplier Material to add in BOM":
				log.info("In side :-> " + data[2]);
				TC7_RestictionOfInvalidSupplierMaterialInsertionInBOM(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Material search Functionality and Insertion in BOM": 
				log.info("In side :-> " + data[2]);
				TC8_MaterialSearchFunctionalityAndInsertionInBOM(data);
				log.info("Out side :-> " + data[2]);
				 break;
		    case "Verification of Added Valid Supplier Category to the Material":
				log.info("In side :-> " + data[2]);
				TC9_AddedvalidSupplierCategoryVerification(data);
				log.info("Out side :-> " + data[2]);
				 break; 
			case "OEM Vendor have access without being added as a supplier":  
		    	log.info("In side :-> " + data[2]);
		        TC10_OEMVendorAccessOnMaterialSupplier(data);
		    	log.info("Out side :-> " + data[2]);
				 break;
		   case "OEM Vendor have access  to use all the Material Supplier in their BOM":
		    	log.info("In side :-> " + data[2]);
		         TC11_OEMVendorAccessInBOM(data);
		    	log.info("Out side :-> " + data[2]);
				 break;
		   case "OEM Vendor have access  to use all the valid Material Supplier in their BOM":
		    	log.info("In side :-> " + data[2]);
		         TC12_OEMVendorAccessInBOMForValidSuppliers(data);
		    	log.info("Out side :-> " + data[2]);
		    	break;
		    	
			case "Deactivate the material supplier link":
			     log.info("In side :-> " + data[2]);
		         TC13_DeactivateMaterialSupplierLink(data);
			     log.info("Out side :-> " + data[2]);
		         break;
			case "OEM Vendor have no access  to use all the InActive Material Supplier in their BOM":
		    	log.info("In side :-> " + data[2]);
		    	TC14_NoAccessforInActiveMaterialSupplier(data);
		    	log.info("Out side :-> " + data[2]);
				 break;
			default:
				fail=true;
				log.info("Default is executed");
			}

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	
	
	
	
	
	
    //First Test Case Description: This Test Case says about User shall be able to Update and Save material suppliers.
	//and Test case 1 Automated in below function( TC1_MaterialSupplierUpdate(String[] data)) 
	//Note: it has a Defect So we are not running this test case
	
	public static boolean TC1_MaterialSupplierUpdate(String[] data) throws Exception{
		try{
			navigateToMaterial(data);
			
			//Click on Search
			enterMaterialNameInTextbox(MaterialName, data[4]);
			CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
			CommonFunctions.waitForPageLoaded();
			//Click first material in list
			//CommonFunctions.clickButtonOrLink(firstMNameinList, "btn", "Material name");
			getPageTitle();
			CommonFunctions.S_ASSERT.assertTrue(pageTitle.equals(data[5]),"The Expected page is not correct.please verify");
			log.info(pageTitle +" "+"Page Appears");
			CommonFunctions.waitForVisibilityOfElement(materialDetails);
			//Verify Suppliers dropdown will only display Hasbro Internal
			Select select = new Select(driver.findElement(ddSupplier));
			List<WebElement> options = select.getOptions();
			System.out.println(options.size());
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			CommonFunctions.S_ASSERT.assertTrue(bVal,"Condition is Passed");
			log.info("Hasbro Internal is present in dropdown");
            //Verify Supplier dropdown size as 2
			CommonFunctions.S_ASSERT.assertEquals(options.size(), 2);
			log.info("In dropdown only Hasbro Internal is present ");
			//Verification 2: Select a Supplier from the dropdown --> Go to Actions --> 
			//Update Material Supplier
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(ddSupplier, data[6], "Supplier");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[7],"ERROR:The page is not correct.please verify");
			log.info(pageTitle +" "+"Page Appears");
			//Click on Action
			CommonFunctions.selectFromDropDownByVisibleText(ddMaterialSupplierActions,data[8]);
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"The Expected page is not correct.please verify");
			log.info(pageTitle +" "+"Page Appears");
			driver.findElement(txtPrice).clear();
			//Enter price
			CommonFunctions.enterTextInTextboxUpdated(txtPrice, data[10], "Price");
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[8],"ERROR:Price is not updated correctly.please check");
			GettingText(MaterialPrice);
			CommonFunctions.S_ASSERT.assertEquals(textForVerification,data[9],"ERROR:Price is not updated correctly.please check");
			log.info("Price value is updated and is "+textForVerification);
			log.info("***MATERIAL SUPPLIER UPDATE TEST CASE HAS BEEN VERIFIED SUCCESSFULLY***");
			}
		 catch(Exception e){ 
			fail=true;
			log.error("Exception in MaterialSupplier Updation", e);
		    throw e;
		}
		return true;
	}
	
	//Second Test Case Description: This test Case says About Adding a Valid Supplier Category to the Material and Verifying the same in Supplier Drop-Down.
	//And 2nd Test case automated in Below function(TC2_validSupplierCategoryVerification(String [] data))
	
	public static boolean TC2_validSupplierCategoryVerification(String [] data) throws Exception{
		try{
			navigateToMaterial(data);
			//Navigating to Materials tab
			enterMaterialNameInTextbox(MaterialName, data[4]);
			//Entering a correct material 
			CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
			//clicking on search
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"The Expected page is not correct.please verify");
			//verifying the page name
			log.info(pageTitle +" "+"Page Appears");
			//printing the page name
		    CommonFunctions.gettingParentWindow();
		    //getting parent window
		     CommonFunctions.waitForElementTobeClickable(ActionButton);
		   //waiting for visibility of Action drop-down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[8], "Supplier");
			//clicking on Add supplier in Action drop-down
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.switchingChildWindow();
			//Switching from parent window to child window
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"ERROR:Page name is not correct.please check the swithched window");
			//verifying the page name
			log.info(pageTitle+" Page Appears");
			//printing the page name
			CommonFunctions.waitForElementTobeClickable(SupplierOption);
			//waiting for the Supplier Button in child window
			CommonFunctions.clickButtonOrLink(SupplierOption, "link", "Supplier");
			//waiting for the Supplier Category drop down first option
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(SupplierName);
			//waiting for the element of supplier Name
			CommonFunctions.enterTextInTextbox(SupplierName, data[20]);
			// Entering the data in name
			CommonFunctions.clickButtonOrLink(FirstSearchButton, "link", "SearchButton");
			//clicking on search button
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(CheckSearchResultCheckBox);
			//waiting for the check box to appear as per the data[20]
			CommonFunctions.clickButtonOrLink(CheckSearchResultCheckBox, "checkBox", "SelectAllCheckBoxes");
			//Clicking on the check box 
			CommonFunctions.waitForElementTobeClickable(SelectButton);
			//waiting for select button
			CommonFunctions.clickButtonOrLink(SelectButton, "Button", "SelectButton");
			//clicking on select Button
		    CommonFunctions.switchParentWindow();
		    //Switching into parent window again
		    getPageTitle();
		    //getting the page title
		    CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:Page Name is Not correct.Please Verify");
		    //verifying the page title
		    log.info(pageTitle+" Page Appears");
		    //Printing the page title 
		    CommonFunctions.waitForPageLoaded();
		    //Waiting till the page to be loaded
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Switching to content frame in Content frame
			CommonFunctions.waitForElementTobeClickable(ddSupplier);
			//checking for dd supplier drop down
			Select select = new Select(driver.findElement(ddSupplier));
		    List<WebElement> options = select.getOptions();
			int Count = options.size();
		    for(int i=0;i<Count;i++)
		    {
		    String values=options.get(i).getText();
		    if(values.equals(data[20]))
		    	//reading and comparing the value of added supplier in supplier drop-down 
		    {
		    log.info(data[20] +" is matched in the supplier DropDown for i="+i);
		    Assert.assertEquals(values, data[20]);
		    log.info("***VALID SUPPLIER CATEGORY VERIFICATION TEST CASE HAS BEEN VERIFIED SUCCESSFULLY");
			}
		    else{
		    	log.info(data[20] +" is not matched within the Supplier dropdown for the value of i="+i);
		    	}
		    }
		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception while Verifying valid Supplier Category",e);
			throw e;
			}
		return true;
		}
	
	//Third Test Case Description: This test Case says About Adding a InValid Supplier Category to the Material,getting and printing the error
	// 3rd Test Case Automated in Below Function(TC3_InvalidSupplierCategoryVerification(String [] data))
	
	public static boolean TC3_InvalidSupplierCategoryVerification(String [] data) throws Exception{
		try{
			navigateToMaterial(data);
			//Navigating to Materials tab
			enterMaterialNameInTextbox(MaterialName, data[4]);
			//Entering a correct material 
			CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
			//clicking on search
			CommonFunctions.waitForPageLoaded();
	        //Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"The Expected page is not correct.please verify");
			//verifying the page name
			log.info(pageTitle +" "+"Page Appears");
			//printing the page name
		    CommonFunctions.gettingParentWindow();
		    //getting parent window
		    CommonFunctions.waitForElementTobeClickable(ActionButton);
		    //waiting for visibility of Action drop-down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[8], "Supplier");
			//clicking on Add supplier in Action dropdown
			CommonFunctions.waitForPageLoaded();
			 //Waiting till the page to be loaded
			CommonFunctions.switchingChildWindow();
			//Switching from parent window to child window
			CommonFunctions.waitForPageLoaded();
			 //Waiting till the page to be loaded foe child window
			getPageTitle();
			//getting a page Title of Child window
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"ERROR:Page noame is not correct.please check the swithced window");
			//verifying the page name
			log.info(pageTitle+" Page Appears");
		    //Printing the pageName
			CommonFunctions.waitForElementTobeClickable(SupplierOption);
			//waiting for the Supplier Button in child window
			CommonFunctions.clickButtonOrLink(SupplierOption, "link", "Supplier");
			//Clicking the Supplier Button in child window
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(SupplierCategoryDropDown);
			//waiting for the Supplier Category drop down
			CommonFunctions.waitForElementTobeClickable(SupplierCategoryDropDownFirstValue);
			//waiting for the Supplier Category drop down first option
			CommonFunctions.clickButtonOrLink(SupplierCategoryDropDownFirstValue, "option", "Supplier category DropDown Option");
			//clicking the Supplier Category drop down first option
			Actions actionObj = new Actions(driver);
			actionObj.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
			//Selecting all the values in Supplier Category droop down
			CommonFunctions.clickButtonOrLink(RemoveButton, "link", "Remove Link");
			//clicking on remove link
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.enterTextInTextbox(VMRVendorCode, data[21]);
			//Entering the value in 
			CommonFunctions.waitForElementTobeClickable(FirstSearchButton);
			//waiting for search button
			CommonFunctions.clickButtonOrLink(FirstSearchButton, "Button", "SearchButton");
			//Clicking on search button
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(VendorSupplier);
			// waiting for vendor supplier WebElement
			CommonFunctions.clickButtonOrLink(VendorSupplier, "checkbox", "checkBoxOfVendorSupplier");
			//clicking on Funskool check box
			CommonFunctions.clickButtonOrLink(SelectButtonOfVendor, "button", "selectButton");
			//clicking on select
			CommonFunctions.switchParentWindow();
			//Switching to parent window again since child window has been closed after clicking 'select'
		    getPageTitle();
		    //getting the page title of the parent window
		    CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:Page Name is Not correct.Please Verify");
		    //Verifying the page name of the parent window
		    log.info(pageTitle+" Page Appears");
		    //printing the page name
		    CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Switching into content frame
			getErrorMessage();
			//Validating whether we are getting error/not after selecting the vendor supplier
			Assert.assertEquals(textForVerification, data[36]);
			log.info("***INVALID SUPPLIER CATEGORY VERIFICATION TEST CASE HAS BEEN VERIFIED SUCCESSFULLY***");
	        }
			catch(Exception e){ 
				fail=true;
				log.error("Exception While Invalid Supplier Category Verification ", e);
				throw e;
				}
			return true;
			}
	
	
	
	//Fourth Test Case Description:This test Case says About Adding a Hasbro Internal Supplier to the Material and Verifying the same in Supplier Drop-Down.
	//And 4th Test case automated in Below function(TC4_HasbroInternalSupplierVerification(String [] data))
	
	public static boolean TC4_HasbroInternalSupplierVerification(String [] data) throws Exception{
		try{
			navigateToMaterial(data);
			//Navigating to Materials tab
			enterMaterialNameInTextbox(MaterialName, data[4]);
			//Entering a correct material 
			CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
			//clicking on search
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"The Expected page is not correct.please verify");
			//verifying the page name
			log.info(pageTitle +" "+"Page Appears");
			//printing the page name
		    CommonFunctions.gettingParentWindow();
		    //getting parent window
		     CommonFunctions.waitForElementTobeClickable(ActionButton);
		   //waiting for visibility of Action drop-down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[8], "Supplier");
			//clicking on Add supplier in Action drop-down
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.switchingChildWindow();
			//Switching from parent window to child window
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"ERROR:Page name is not correct.please check the swithched window");
			//verifying the page name
			log.info(pageTitle+" Page Appears");
			//printing the page name
			CommonFunctions.waitForElementTobeClickable(SupplierOption);
			//waiting for the Supplier Button in child window
			CommonFunctions.clickButtonOrLink(SupplierOption, "link", "Supplier");
			//waiting for the Supplier Category drop down first option
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(SupplierCategoryDropDown);
			//waiting for the Supplier Category drop down
			CommonFunctions.waitForElementTobeClickable(SupplierCategoryDropDownFirstValue);
			//waiting for the Supplier Category drop down first option
			CommonFunctions.clickButtonOrLink(SupplierCategoryDropDownFirstValue, "option", "Supplier category DropDown Option");
			//clicking the Supplier Category drop down first option
			Actions actionObj = new Actions(driver);
			actionObj.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
			//Selecting all the values in Supplier Category droop down
			CommonFunctions.clickButtonOrLink(RemoveButton, "link", "Remove Link");
			//clicking on remove link
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(SupplierName);
			//waiting for the element of supplier Name
			CommonFunctions.enterTextInTextbox(SupplierName, data[20]);
			// Entering the data in name
			CommonFunctions.clickButtonOrLink(FirstSearchButton, "link", "SearchButton");
			//clicking on search button
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(CheckSearchResultCheckBox);
			//waiting for the check box to appear as per the data[20]
			CommonFunctions.clickButtonOrLink(CheckSearchResultCheckBox, "checkBox", "SelectAllCheckBoxes");
			//Clicking on the check box 
			CommonFunctions.waitForElementTobeClickable(SelectButton);
			//waiting for select button
			CommonFunctions.clickButtonOrLink(SelectButton, "Button", "SelectButton");
			//clicking on select Button
		    CommonFunctions.switchParentWindow();
		    //Switching into parent window again
		    getPageTitle();
		    //getting the page title
		    CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:Page Name is Not correct.Please Verify");
		    //verifying the page title
		    log.info(pageTitle+" Page Appears");
		    //Printing the page title 
		    CommonFunctions.waitForPageLoaded();
		    //Waiting till the page to be loaded
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Switching to content frame in Content frame
			CommonFunctions.waitForElementTobeClickable(ddSupplier);
			//checking for dd supplier drop down
			Select select = new Select(driver.findElement(ddSupplier));
		    List<WebElement> options = select.getOptions();
			int Count = options.size();
		    for(int i=0;i<Count;i++)
		    {
		    	String values=options.get(i).getText();
		    if(values.equals(data[20]))
		    	//reading and comparing the value of added supplier in supplier drop-down 
		    {
		    log.info(data[20] +" is matched in the supplier DropDown for i="+i);
		    Assert.assertEquals(values, data[20]);
		    log.info("***HASBRO INTERNAL SUPPLIER VERIFICATION TEST CASE HAS BEEN VERIFIED SUCCESSFULLY");
			}
		    else{
		    	log.info(data[20] +" is not matched within the Supplier dropdown for the value of i="+i);
		    }
		    }
	}
		catch(Exception e){ 
			fail=true;
			log.error("Exception while adding Hasbro internal Supplier", e);
			throw e;
		}
		return true;
	
	}
	
	//Fifth Test Case Description: This test Case says About Adding a Factory Supplier Category to the Material,getting and printing the error
	// 5th Test Case Automated in Below Function(TC5_factorySupplierVerification(String[] data))
	
	public static boolean TC5_factorySupplierVerification(String[] data) throws Exception{
		try{
			navigateToMaterial(data);
			//Navigating to Materials tab
			enterMaterialNameInTextbox(MaterialName, data[4]);
			//Entering a correct material 
			CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
			//clicking on search
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"The Expected page is not correct.please verify");
			//verifying the page name
			log.info(pageTitle +" "+"Page Appears");
			//printing the page name
		    CommonFunctions.gettingParentWindow();
		  //getting parent window
		     CommonFunctions.waitForElementTobeClickable(ActionButton);
		   //waiting for visibility of Action drop-down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[8], "Supplier");
			//clicking on Add supplier in Action drop-down
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.switchingChildWindow();
			//Switching from parent window to child window
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"ERROR:Page name is not correct.please check the swithched window");
			//verifying the page name
			log.info(pageTitle+" Page Appears");
			//Printing the Page Name
			CommonFunctions.waitForElementTobeClickable(FactoryLink);
			//Clicking on factory link
			CommonFunctions.clickButtonOrLink(FactoryLink, "Factory Link");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(SupplierName);
			//waiting for the element of supplier Name
			CommonFunctions.enterTextInTextbox(SupplierName, data[25]);
			//Enteringing Factory Suplier Name into the Text box
			CommonFunctions.clickButtonOrLink(FirstSearchButton, "link", "SearchButton");
			//clicking on search button
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(CheckSearchResultCheckBox);
			//waiting for the check box to appear as per the data[25]
			CommonFunctions.clickButtonOrLink(CheckSearchResultCheckBox, "checkBox", "SelectAllCheckBoxes");
			//Clicking on the check box 
			CommonFunctions.waitForElementTobeClickable(SelectButton);
			//waiting for select button
			CommonFunctions.clickButtonOrLink(SelectButton, "Button", "SelectButton");
			//clicking on select Button
		    CommonFunctions.switchParentWindow();
		    //Switching into parent window again
		    getPageTitle();
		    //getting the page title
		    CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:Page Name is Not correct.Please Verify");
		    //verifying the page title
		    log.info(pageTitle+" Page Appears");
		    //Printing the page title 
		    CommonFunctions.waitForPageLoaded();
		    //Waiting till the page to be loaded
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Switching to content frame in Content frame
			getErrorMessage();
			Assert.assertEquals(textForVerification,data[36]);
			log.info("INVALID SUPPLIER CATEGORY HAS BEEN VERIFIED SUCCESSFULLY");
			//Validating whether we are getting error/not after selecting the factory supplier
			}
	catch(Exception e){ 
		fail=true;
		log.error("Exception while addng factory Supplier", e);
		throw e;
	}
	return true;
		
	}
	//Sixth Test Case Description:Creating a BOM , Adding a material details within the BOM and verifying the same.
	//This test Case is Automated in below Method(TC6_MaterialInsertionInBOM(String[] data)
	
	public static boolean TC6_MaterialInsertionInBOM(String[] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[11]);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			Assert.assertEquals(pageTitle,data[3]);
			log.info(pageTitle+" Page Appears");
			//Click on Filter
			CommonFunctions.clickButtonOrLink(LineSheetView.Filter, "btn");
			//Select line sheet view filters
			CommonFunctions.waitForElementTobeClickable(LineSheetView.Filterdropdown);
			CommonFunctions.selectFromDropDownByVisibleText(LineSheetView.Filterdropdown, data[12]);
			//click on Run
			CommonFunctions.waitForElementTobeClickable(LineSheetView.Run);
			CommonFunctions.clickButtonOrLink(LineSheetView.Run, "link", "Run");
			//Click on first Action
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(firstAction);
			CommonFunctions.clickButtonOrLink(firstAction, "link", "Action");
			CommonFunctions.waitForElementTobeClickable(view);
			//Click on view
			CommonFunctions.clickButtonOrLink(view, "link", "View");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			Assert.assertEquals(pageTitle, data[5]);
			//Add BOM
			addBOM(data);
			//Update BOM
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.updateBtn);
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update");
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddRowsButton));
			//CommonFunctions.waitForElementTobeClickable(AddRowsButton);
			//Click on Pleastic
			//Switch to mainframe
			//driver.switchTo().frame("mainFrame");
			//Component or Location
			InternalBOMSoftG.action = new Actions(driver);
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.meterial)).doubleClick().perform();
	        //Click on Materials
			CommonFunctions.clickButtonOrLink(btnMaterials, "btn", "Material");
			CommonFunctions.waitForPageLoaded();
			searchMaterial(data);
			//Click on 'Insert Material' image
			CommonFunctions.clickButtonOrLink(imgInsertMat, "Img", "Insert Material");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			driver.switchTo().frame("mainFrame");
			//Click on saveAndCheckIn button
			CommonFunctions.clickButtonOrLink(saveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
			//getPageTitle();
			//Assert.assertEquals(pageTitle, data[5]);
			//Verification : The BOM will be successfully saved and checked in and it is on BOM page
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.headerAttributes);
			GettingText(InternalBOMSoftG.headerAttributes);
			Assert.assertEquals(textForVerification,data[22]);
			log.info("The BOM will be successfully saved and checked in. Header attribute is displayed for the same.");
			
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in MaterialInsertionInBOM", e);
			throw e;
		}
		return true;
	}
	
	public static boolean TC7_RestictionOfInvalidSupplierMaterialInsertionInBOM(String[] data) throws Exception{
		try{
			SearchExistingMaterial(data);
			//Searching the Existing material
			OEMManufacturerAppearanceVerificationInMaterail(data);
			//Verifying whether the OEM Manufacturer is present or Not
			navigateToProduct(data);
			//Navigating to product
			CI299.SearchExistingProduct(data);
			//Search Existing product
			wait.until(ExpectedConditions.titleIs(data[38]));
			getPageTitle();
			addBOM(data);
			//Adding a BOM
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.updateBtn);
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update");
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddRowsButton));
			//CommonFunctions.waitForElementTobeClickable(AddRowsButton);
			InternalBOMSoftG.action = new Actions(driver);
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.meterial)).doubleClick().perform();
	        //Click on Materials
			CommonFunctions.clickButtonOrLink(btnMaterials, "btn", "Material");
			CommonFunctions.waitForPageLoaded();
			searchMaterial(data);
			SupplierBasedDynamicElementIdentification=".//a[contains(text(),'"+data[35]+"')]//preceding::img[1]";
			SupplierBasedDynamicElement=By.xpath(SupplierBasedDynamicElementIdentification);
	
			//waiting for the Invisibilty of  the WebElement
			 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(SupplierBasedDynamicElement, "SupplierBasedDynamicElement"), "Condition Falied.Asserton Failed");
			 log.info("The Material that contains OEM Manufacturer supplier is not visible to add material In BOM");
			 log.info("NoAccessforInValidMaterialSupplier. TEST CASE VERIFIED SUCCESSFULLY");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in MaterialInsertionInBOM", e);
			throw e;
		}
		return true;
	}
	
	
	
	
	//Seventh Test case Description:Creating a BOM , Adding a material details within the BOM and verifying search Functionality in material Text Box.
	//This Test case is Automated in Below Function(TC7_MaterialSearchFunctionalityAndInsertionInBOM(String[] data))
	
	public static boolean TC8_MaterialSearchFunctionalityAndInsertionInBOM(String[] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[11]);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			Assert.assertEquals(pageTitle,data[3]);
			log.info(pageTitle+" Page Appears");
			//Click on Filter
			CommonFunctions.clickButtonOrLink(LineSheetView.Filter, "btn");
			//Select line sheet view filters
			CommonFunctions.selectFromDropDownByVisibleText(LineSheetView.Filterdropdown, data[12]);
			//click on Run
			CommonFunctions.clickButtonOrLink(LineSheetView.Run, "link", "Run");
			//Click on first Action
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(firstAction, "link", "Action");
			CommonFunctions.waitForElementTobeClickable(view);
			//Click on view
			CommonFunctions.clickButtonOrLink(view, "link", "View");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			Assert.assertEquals(pageTitle, data[5]);
			//Add BOM
			addBOM(data);
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.updateBtn);
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update");
			
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddRowsButton));
			//CommonFunctions.waitForElementTobeClickable(AddRowsButton);
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.MaterialTextBox1);
			CommonFunctions.waitForElementTobeClickable(AddRowsButton);
			//Click on Pleastic
			//Switch to mainframe
			//driver.switchTo().frame("mainFrame");
			//Component or Location
			verifyMaterialSeachFunctionality(data);
	        CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.headerAttributes);
			GettingText(InternalBOMSoftG.headerAttributes);
			Assert.assertEquals(textForVerification,data[22]);
			log.info("The BOM will be successfully saved and checked in. Header attribute is displayed for the same.");
			log.info("***MATERIAL SEARCH FUNCTIONALITY HAS BEEN VERIFIED SUCCESSFULLY***");
			}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in Material Search Functionality", e);
			throw e;
		}
		return true;
	}
	
	//Eight Test Case Description: This Test Case says about User shall be add the supplier which has a valid category.
	//if it is already added then we should shot able to see the in the supplier drop-down.w should able in sourcing tab material Supplier part
	//and Test case 8 Automated in below function( TC8_AddedvalidSupplierCategoryVerification(String[] data)) 
	public static boolean TC9_AddedvalidSupplierCategoryVerification(String [] data) throws Exception{
		try{
			navigateToMaterial(data);
			//Navigating to Materials tab
			enterMaterialNameInTextbox(MaterialName, data[4]);
			//Entering a correct material 
			CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
			//clicking on search
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"The Expected page is not correct.please verify");
			//verifying the page name
			log.info(pageTitle +" "+"Page Appears");
			//printing the page name
		    CommonFunctions.gettingParentWindow();
		    //getting parent window
		     CommonFunctions.waitForElementTobeClickable(ActionButton);
		   //waiting for visibility of Action drop-down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[8], "Supplier");
			//clicking on Add supplier in Action drop-down
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.switchingChildWindow();
			//Switching from parent window to child window
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			getPageTitle();
			//getting a page Title
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"ERROR:Page name is not correct.please check the swithched window");
			//verifying the page name
			log.info(pageTitle+" Page Appears");
			//printing the page name
			CommonFunctions.waitForElementTobeClickable(SupplierOption);
			//waiting for the Supplier Button in child window
			CommonFunctions.clickButtonOrLink(SupplierOption, "link", "Supplier");
			//waiting for the Supplier Category drop down first option
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(SupplierName);
			//waiting for the element of supplier Name
			CommonFunctions.enterTextInTextbox(SupplierName, data[20]);
			// Entering the data in name
			CommonFunctions.clickButtonOrLink(FirstSearchButton, "link", "SearchButton");
			//clicking on search button
			CommonFunctions.waitForPageLoaded();
			//Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(CheckSearchResultCheckBox);
			//waiting for the check box to appear as per the data[20]
			CommonFunctions.clickButtonOrLink(CheckSearchResultCheckBox, "checkBox", "SelectAllCheckBoxes");
			//Clicking on the check box 
			CommonFunctions.waitForElementTobeClickable(SelectButton);
			//waiting for select button
			CommonFunctions.clickButtonOrLink(SelectButton, "Button", "SelectButton");
			//clicking on select Button
		    CommonFunctions.switchParentWindow();
		    //Switching into parent window again
		    getPageTitle();
		    //getting the page title
		    CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:Page Name is Not correct.Please Verify");
		    //verifying the page title
		    log.info(pageTitle+" Page Appears");
		    //Printing the page title 
		    CommonFunctions.waitForPageLoaded();
		    //Waiting till the page to be loaded
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Switching to content frame in Content frame
			CommonFunctions.waitForPageLoaded();
		    //Waiting till the page to be loaded
			CommonFunctions.waitForElementTobeClickable(ddSupplier);
			//checking for dd supplier drop down
			Select select = new Select(driver.findElement(ddSupplier));
		    List<WebElement> options = select.getOptions();
			int Count = options.size();
		    for(int i=0;i<Count;i++)
		    {
		    	String values=options.get(i).getText();
		    if(values.equals(data[20]))
		    	//reading and comparing the value of added supplier in supplier drop-down 
		    {
		    log.info(data[20] +" is matched in the supplier DropDown for i="+i);
		    Assert.assertEquals(values, data[20]);
		    log.info("***VALID SUPPLIER CATEGORY VERIFICATION TEST CASE HAS BEEN VERIFIED SUCCESSFULLY");
			}
		    else{
		    	log.info(data[20] +" is not matched within the Supplier dropdown for the value of i="+i);
		    }
		    }
		    
		   //This function verifies whether already added supplier is present or not in the in Sourcing   
		    SupplierVerificationInSourcing(data);
		}
		
		catch(Exception e){ 
			fail=true;
			log.error("Exception while Verifying valid Supplier Category",e);
		    throw e;
			}
		return true;
		}
	
	//Ninth Test case Description=This Test Case says about DeactivateMaterialSupplierLink9unchecking the Material Supplier Link)
		//it is Automated In TC9_DeactivateMaterialSupplierLink(data)in below Function.
	//Pre-requisite: user Should Be logged with Administrator User
		
		public static boolean TC13_DeactivateMaterialSupplierLink(String [] data) throws Exception{
			try{
				navigateToMaterial(data);
				//Navigating to Materials tab
				enterMaterialNameInTextbox(MaterialName, data[4]);
				//Entering a correct material 
				CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
				//clicking on search
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				getPageTitle();
				//getting a page Title
				CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"The Expected page is not correct.please verify");
				//verifying the page name
				log.info(pageTitle +" "+"Page Appears");
				//printing the page name
			    CommonFunctions.gettingParentWindow();
			    //getting parent window
			     CommonFunctions.waitForElementTobeClickable(ActionButton);
			   //waiting for visibility of Action drop-down
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[8], "Supplier");
				//clicking on Add supplier in Action drop-down
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.switchingChildWindow();
				//Switching from parent window to child window
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				getPageTitle();
				//getting a page Title
				CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"ERROR:Page name is not correct.please check the swithched window");
				//verifying the page name
				log.info(pageTitle+" Page Appears");
				//printing the page name
				CommonFunctions.waitForElementTobeClickable(SupplierOption);
				//waiting for the Supplier Button in child window
				CommonFunctions.clickButtonOrLink(SupplierOption, "link", "Supplier");
				//waiting for the Supplier Category drop down first option
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.waitForElementTobeClickable(SupplierName);
				//waiting for the element of supplier Name
				CommonFunctions.enterTextInTextbox(SupplierName, data[20]);
				// Entering the data in name
				CommonFunctions.clickButtonOrLink(FirstSearchButton, "link", "SearchButton");
				//clicking on search button
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.waitForElementTobeClickable(CheckSearchResultCheckBox);
				//waiting for the check box to appear as per the data[20]
				CommonFunctions.clickButtonOrLink(CheckSearchResultCheckBox, "checkBox", "SelectAllCheckBoxes");
				//Clicking on the check box 
				CommonFunctions.waitForElementTobeClickable(SelectButton);
				//waiting for select button
				CommonFunctions.clickButtonOrLink(SelectButton, "Button", "SelectButton");
				//clicking on select Button
			    CommonFunctions.switchParentWindow();
			    //Switching into parent window again
			    getPageTitle();
			    //getting the page title
			    CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:Page Name is Not correct.Please Verify");
			    //verifying the page title
			    log.info(pageTitle+" Page Appears");
			    //Printing the page title 
			    CommonFunctions.waitForPageLoaded();
			    //Waiting till the page to be loaded
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//Switching to content frame in Content frame
				CommonFunctions.waitForElementTobeClickable(ddSupplier);
				//checking for dd supplier drop down
				Select select = new Select(driver.findElement(ddSupplier));
			    List<WebElement> options = select.getOptions();
				int Count = options.size();
			    for(int i=0;i<Count;i++)
			    {
			    if(options.get(i).getText().equals(data[20]))
			    	//reading and comparing the value of added supplier in supplier drop-down 
			    {
			    log.info(data[20] +" is matched in the supplier DropDown for i="+i);
			    //selecting the Supplier  Which is Added now in supplier drop-down
			    CommonFunctions.selectFromDropDownByVisibleTextUpdated(ddSupplier, data[20], "DropDownValue");
			    CommonFunctions.waitForPageLoaded();
			    break;
			   
			  }
			    else{
			    	log.info(data[20] +" is not matched within the Supplier dropdown for the value of i="+i);
			    	}
			    }
			      //unchecking the Material Supplier Active Check Box
			       deActivateSupplier(data);
		            getPageTitle();
		            //Verifying the PageTitle
				    CommonFunctions.S_ASSERT.assertEquals(pageTitle, data[27],"ERROR:The Page Name is not Correct.Please verify");
				    log.info(pageTitle+" Page Appears");
			      }
			catch(Exception e){ 
				fail=true;
				log.error("Exception while Verifying valid Supplier Category",e);
				throw e;
				}
			return true;
			}
		
		
		
		
		/*Tenth Test Case Description and steps following to automate this  Test case
		 * Description:OEM Vendor should have access to view all the Material Supplier without being added as a Supplier to those Materials
		 * Test Steps:
		 * 1.Logging in with Matetial Admin.
		 * 2.Creating a New Material
		 * 3.Adding a Supplier as 'Hasbro Internal'.so it should allow to add
		 * 4.Again Adding one Vendor Supplier(FUNSKOOL (INDIA) LTD using VMR Code(ZI-3525) for Vendor Supplier).so it throws a error as per new Functionality implementation.
		 * Conclusion we made based on 3rd Step: using that above step throwed error while adding vendor Supplier we are making a Conclusion that no vendor supplier has been added in above created product in step 1 and we can't also(Making this a verification point for our script).So now if OEM vendor user is able read the supplier drop-down values then our test case will pass as per our UTC-296.  
		 * 5.Logging out from the Material admin user
		 * 6.Logging in with OEM vendor User
		 * 7.navigating into above creating product
		 * 8.Reading the values of the Supplier drop-down.
		 * */
		
		public static boolean TC10_OEMVendorAccessOnMaterialSupplier(String [] data) throws Exception{
			try{
				createMeaterial(data);
				getPageTitle();
				CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:This page Name is not correct.please Verify");
				HasbroInternalSupplierSelection(data);
				    getPageTitle();
					//getting PageTitle
			        CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:This page Name is not correct.please Verify");
			        OEMVendorSupplierSelection(data);
						driver.switchTo().defaultContent();
						driver.switchTo().frame("contentframe");
						//Switching into content frame
						getErrorMessageForInvalidSUpplier();
						Assert.assertEquals(textForVerification, data[36]);
						//Validating whether we are getting error/not after selecting the vendor supplier
						log.info("***NOT ALLOWED TO ADD ANY OEM VENDOR***");
						log.info("CONCLUSION: NO VENDOR SUPPLER HAS EEN ADDED AND CAN'T ADD ALSO AS PER THE NEW FUNCTIONALITY IMPLEMENTATION");
						CommonProjectFunctions.logOut();
					    openBrowser();
						launchApp(data[28],data[29]);
						//logging in with new user
						CommonFunctions.waitForPageLoaded();
						navigateToMaterial(data);
						//Navigating to Materials tab
						enterMaterialNameInTextbox(MaterialName, data[4]);
						//Entering a correct material 
						CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
						//clicking on search
						CommonFunctions.waitForPageLoaded();
						//Waiting till the page to be loaded
			        
						try{ if(driver.findElement(SearchResultsForMaterial).isDisplayed()){
								CommonFunctions.waitForElementTobeClickable(ClickFirstOption);
								CommonFunctions.clickButtonOrLink(ClickFirstOption, "link", "First Value Supplier");
								CommonFunctions.waitForPageLoaded();
							}
						}
						    catch(Exception e){
						    	log.info("given test data has Only one Result. Details Page Appears");
						    }
						getPageTitle();
						CommonFunctions.S_ASSERT.assertEquals(pageTitle, data[27],"ERROR:Page Name is Not correct.Please Verify");
			          CommonFunctions.waitForElementTobeClickable(ddSupplier);
				//Printing the values One By One from supplier drop-down
			      Select select = new Select(driver.findElement(ddSupplier));
			     List<WebElement> options = select.getOptions();
				 int Count = options.size();
			     for(int i=0;i<Count;i++)
			     {
				 String values=options.get(i).getText();
				 Assert.assertNotEquals(values, data[37]);
				 log.info("Value Available in the Drop-down is" +values);
			     }
				log.info("***OEM VENDOR HAVE ACCESS TO VIEW ALL THE MATERAIL SUPPLIER WITHOUT BEING ADDED AS A SUPPLIER TO THOSE MATERIALS***");
				log.info("***TEST CASE VERIFIED SUCCESSFULLY***");
		
			}
			catch(Exception e){ 
				fail=true;
				log.error("Exception while Verifying valid Supplier Category",e);
				throw e;
				}
			return true;
			}
		
		
		//eleventh test case description: this Test case Says About Adding Material which don't have OEM vendor as a supplier in BOM Using OEM Vendor user
		  //This test case Automated in TC11_OEMVendorAccessInBOM(String[] data) function
		
		public static boolean TC11_OEMVendorAccessInBOM(String[] data) throws Exception{
			try{
				//Navigating to a Product serach page
			    navigateToProduct(data);
			  //Entering a Product in text Box
			    CommonFunctions.waitForElementTobeClickable(ProductTextBox);
				CommonFunctions.enterTextInTextboxUpdated(ProductTextBox, data[31], "productTextBox");
				//Clicking Search Button
				CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
			    CommonFunctions.waitForPageLoaded();
			    //After clicking search if it displays more than results it will execute try block else catch block  
				try{
				if(driver.findElement(ProductSearchResults).isDisplayed()){
					getPageTitle();
					CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR: The Expected page is not correct.please verify");
					//Finding and Clicking Retails item Option
					CommonFunctions.waitForElementTobeClickable(ChooseRetailItem);
					CommonFunctions.clickButtonOrLink(ChooseRetailItem, "link", "ProductItem");
					CommonFunctions.waitForPageLoaded();
				}
				}
				catch(Exception e){
					log.info("Searched Product has only one result");
					CommonFunctions.waitForPageLoaded();
				}
				//Browse to Material Tab
				navigateToMaterialTab(data);
				//Click on Add New BOM tab
				CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
				//Enter BOM Type
				CommonFunctions.enterTextInTextbox(bomtypeid, data[13]);
				//Click Initialize bom
				CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
				CommonFunctions.waitForPageLoaded();
				getPageTitle();
				Assert.assertEquals(pageTitle, data[9]);
				//Select color way
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				//Select Wave
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.wave);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.wave, data[18]);
				//Select Currency
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.currency);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.currency, data[19]);
				//getting parent window
				CommonFunctions.gettingParentWindow();
				//Clicking factory link
				CommonFunctions.clickButtonOrLink(InternalBOMSoftG.Factory, "Linktext", "FactoryLink");
				//Switching child window
				CommonFunctions.switchingChildWindow();
				CommonFunctions.waitForPageLoaded();
				//Entering the Supplier Name In text box 
				CommonFunctions.enterTextInTextboxUpdated(SupplierName, data[20], "Supplier Name");
				log.info("This Entered Material Has been created In Automation after CI-296 implentation. so this material don't have OEM vendor as a Supplier");
				//Clicking Serach Button
				CommonFunctions.clickButtonOrLink(FirstSearchButton, "link", "SearchButton");
				//clicking on search button
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.clickButtonOrLink(ChooseButton, "link", "Choose Link");
				//Clicking Choose Button
			    CommonFunctions.switchParentWindow();
			    //Switching into parent window again
			    CommonFunctions.waitForPageLoaded();
			    getPageTitle();
			    //getting the page title
			    Assert.assertEquals(pageTitle, data[9]);
			    //Verify the PageTitle
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//click on Create
			    CommonFunctions.waitForElementTobeClickable(Product.createBtn);
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				try{
				//navigating to main Frame of the page
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(AddRowsButton));
		        InternalBOMSoftG.action = new Actions(driver);
			 	//Double-clicking on Material drop-down
			    CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.meterial);
				InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.meterial)).doubleClick().perform();
				}
				catch(Exception e){
					fail=true;
					log.error(e);
					throw e;
				}
				//Click on Materials
				CommonFunctions.clickButtonOrLink(btnMaterials, "btn", "Material");
				CommonFunctions.waitForPageLoaded();
				//Searching the Material
				searchMaterial(data);
				//Click on 'Insert Material' image
				CommonFunctions.waitForElementTobeClickable(imgInsertMat);
				//Clicking on First Image Insert
				CommonFunctions.clickButtonOrLink(imgInsertMat, "Img", "Insert Material");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				driver.switchTo().frame("mainFrame");
				//Click on saveAndCheckIn button
				CommonFunctions.clickButtonOrLink(saveAndCheckIn, "btn", "Save and Check In");
				//handling pop-up
				CommonFunctions.handleAlertPopUp1();
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//Verifying Header Attribute whether it is appearing or not
		     	CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.headerAttributes);
		     	//getting the exact text if the Header attribute
				GettingText(InternalBOMSoftG.headerAttributes);
				//Verifying the Same text
				Assert.assertEquals(textForVerification,data[22]);
				log.info("The BOM successfully saved and checked in. Header attribute is displayed for the same.");
				strBOM=new Select(driver.findElement(InternalBOMSoftG.BOMId)).getFirstSelectedOption().getText();
				System.out.println("BOM name in detail page after check in: " + strBOM);
			    CommonProjectFunctions.logOut();
			  
				//Logging out
				openBrowser();
				//logging in with new user
				launchApp(data[28],data[29]);
				//navigating to the Product home Page
				navigateToProduct(data);
				//Entering the Product Name
				CommonFunctions.waitForElementTobeClickable(ProductTextBox);
				CommonFunctions.enterTextInTextboxUpdated(ProductTextBox, data[31], "productTextBox");
				//Clicking Search utton
				CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
				CommonFunctions.waitForPageLoaded();
				   //After clicking search if it displays more than results it will execute try block else catch block  
				try{
					if(driver.findElement(ProductSearchResults).isDisplayed()){
						getPageTitle();
						//Finding and Clicking Retails item Option
						CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR: The Expected page is not correct.please verify");
						CommonFunctions.waitForElementTobeClickable(ChooseRetailItem);
						CommonFunctions.clickButtonOrLink(ChooseRetailItem, "link", "ProductItem");
						CommonFunctions.waitForPageLoaded();
					}
					}
					catch(Exception e){
						log.info("Searched Product has only one result");
						CommonFunctions.waitForPageLoaded();
					}
				//Navigate into the Materials tab
				navigateToMaterialTab(data);
				//Selecting the above Cretaed BOm 
				CommonFunctions.waitForElementTobeClickable(bomId);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(bomId, strBOM, "Recently created BOM");
				CommonFunctions.waitForPageLoaded();
				//Click 'Update' Button
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.updateBtn);
				CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update");
				//navigating to the Another frame
				WebDriverWait wait1 = new WebDriverWait(driver,30);
				wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(AddRowsButton);
				InternalBOMSoftG.action = new Actions(driver);
				//double click on description and entering some Text (making Changes in existing one)
				InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.compOrLoca)).doubleClick().perform();
				InternalBOMSoftG.action.sendKeys(data[15]).perform();
				//Click Add Rows Button and 
				CommonFunctions.waitForElementTobeClickable(AddRowsButton);
				//adding new Row
				CommonFunctions.clickButtonOrLink(AddRowsButton, "link", "Add row link");
				CommonFunctions.waitForElementTobeClickable(InsertAfterLink);
				CommonFunctions.clickButtonOrLink(InsertAfterLink, "HyperLink");
				CommonFunctions.waitForPageLoaded();
				//Double clicking on material text box 
				CommonFunctions.waitForElementTobeClickable(RowTwoMaterial);
				InternalBOMSoftG.action.moveToElement(driver.findElement(RowTwoMaterial)).doubleClick().perform();
				//Clicking Materials Button
				CommonFunctions.clickButtonOrLink(btnMaterials, "btn", "Material");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				driver.switchTo().frame("materialframe");
				//Select material Type
			/*	CommonFunctions.selectFromDropDownByVisibleText(materialType,data[16]);
				//Enter Created date
				CommonFunctions.enterTextInTextbox(createdDate, data[17]);*/
				//Entering a material text 
				CommonFunctions.waitForElementTobeClickable(MaterialNameText);
				CommonFunctions.enterTextInTextbox(MaterialNameText, data[35]);
				log.info("This Entered Material Has been created In Automation after CI-296 implentation. so this material don't have OEM vendor as a Supplier");
				//Click on search
				//Clicking search Button
				CommonFunctions.waitForElementTobeClickable(btnSearchMaterial);
				CommonFunctions.clickButtonOrLink(btnSearchMaterial, "Search");
				CommonFunctions.waitForPageLoaded();
				//Click on 'Insert Material' image
				CommonFunctions.waitForElementTobeClickable(imgInsertMat);
				CommonFunctions.clickButtonOrLink(imgInsertMat, "Img", "Insert Material");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				driver.switchTo().frame("mainFrame");
				//Click on saveAndCheckIn button
				CommonFunctions.clickButtonOrLink(saveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp1();
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.headerAttributes);
				GettingText(InternalBOMSoftG.headerAttributes);
				Assert.assertEquals(textForVerification,data[22]);
				log.info("New Poduct Added in the existing BOM");
				log.info("OEMVENDOR ACCESS IN BOM TEST CASE VERIFIED SUCCESSFULLY");
				}
			catch (Exception e){
	            fail=true;
				log.error("Error In OEMVendorAccessInBOM"+e);
				throw e;
			}
				return true;
			}
		
		//Twelfth test case description: This Test case says about OEM Vendor should have access to use all the Material Supplier in their BOM without being added as a Supplier to those Materials
		//This test case Automated in below Function(OEMVendorAccessInBOMForValidSuppliers)
		public static boolean TC12_OEMVendorAccessInBOMForValidSuppliers(String[] data) throws Exception{
			try{
				navigateToProduct(data);
				//Entering the Product Name
				CommonFunctions.waitForElementTobeClickable(ProductTextBox);
				CommonFunctions.enterTextInTextboxUpdated(ProductTextBox, data[31], "productTextBox");
				//Clicking Search button
				CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
				CommonFunctions.waitForPageLoaded();
				   //After clicking search if it displays more than results it will execute try block else catch block  
				try{
					if(driver.findElement(ProductSearchResults).isDisplayed()){
						getPageTitle();
						//Finding and Clicking Retails item Option
						CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR: The Expected page is not correct.please verify");
						CommonFunctions.waitForElementTobeClickable(ChooseRetailItem);
						CommonFunctions.clickButtonOrLink(ChooseRetailItem, "link", "ProductItem");
						CommonFunctions.waitForPageLoaded();
					}
					}
					catch(Exception e){
						log.info("Searched Product has only one result");
						CommonFunctions.waitForPageLoaded();
					}
				//Navigate into the Materials tab
				navigateToMaterialTab(data);
				//Selecting the above Cretaed BOm 
				CommonFunctions.waitForElementTobeClickable(bomId);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(bomId, data[34], "Recently created BOM");
				CommonFunctions.waitForPageLoaded();
				//Click 'Update' Button
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.updateBtn);
				CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update");
				//navigating to the Another frame
				WebDriverWait wait1 = new WebDriverWait(driver,30);
				wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(AddRowsButton);
				InternalBOMSoftG.action = new Actions(driver);
				//double click on description and entering some Text (making Changes in existing one)
				try{	
					GettingText(InternalBOMSoftG.meterial);
					if(textForVerification!="")
					{
						log.info("Unable to double click.material already has been entered");
						log.info("Adding a row to enter the Material");
						//Click Add Rows Button and 
						CommonFunctions.waitForElementTobeClickable(AddRowsButton);
						//adding new Row
						CommonFunctions.clickButtonOrLink(AddRowsButton, "link", "Add row link");
						CommonFunctions.waitForElementTobeClickable(InsertAfterLink);
						CommonFunctions.clickButtonOrLink(InsertAfterLink, "link", "Add row link");
						CommonFunctions.waitForPageLoaded();
						//Double clicking on material text box 
						CommonFunctions.waitForElementTobeClickable(RowTwoMaterial);
						InternalBOMSoftG.action.moveToElement(driver.findElement(RowTwoMaterial)).doubleClick().perform();
				}
				}
				catch(Exception e){
					InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.meterial)).doubleClick().perform();
					log.info("Double Clicking on the first element");
					//Clicking Materials Button
					}
				//InternalBOMSoftG.action.sendKeys(data[15]).perform();
				CommonFunctions.clickButtonOrLink(btnMaterials, "btn", "Material");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				driver.switchTo().frame("materialframe");
				//Entering material Name
			    CommonFunctions.waitForElementTobeClickable(MaterialNameText);
				CommonFunctions.enterTextInTextbox(MaterialNameText, data[33]);
				log.info("This Entered Material Has contains Other Valid material supplier Also As per the requirement");
				//Click on search
				//Clicking search Button
				//clicking serach
				CommonFunctions.waitForElementTobeClickable(btnSearchMaterial);
				CommonFunctions.clickButtonOrLink(btnSearchMaterial, "Search");
				CommonFunctions.waitForPageLoaded();
		
				SupplierBasedDynamicElementIdentification=".//a[contains(text(),'"+data[35]+"')]//preceding::img[1]";
				SupplierBasedDynamicElement=By.xpath(SupplierBasedDynamicElementIdentification);
				CommonFunctions.waitForElementTobeClickable(SupplierBasedDynamicElement);
				//Click on 'Insert Material' image
				CommonFunctions.clickButtonOrLink(SupplierBasedDynamicElement, "HyperLink");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				driver.switchTo().frame("mainFrame");
				//Click on saveAndCheckIn button
				CommonFunctions.clickButtonOrLink(saveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp1();
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//verifying the header attribute 
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.headerAttributes);
				GettingText(InternalBOMSoftG.headerAttributes);
				//verifying the header text 
				Assert.assertEquals(textForVerification,data[22]);
				log.info("New Poduct Added in the existing BOM");
				log.info("OEMVEDOR ACCESS WITH OTHER VALID SUPPLER IN BOM TEST CASE VERIFIED SUCCESSFULLY");
				}
			 catch(Exception e){ 
				fail=true;
				log.error("Exception in OEMVendorAccessInBOMForValidSuppliers", e);
				throw e;
			}
			return true;
		}
		//Thirteenth test Case description: This Test case says About OEM Vendor should not have access to use the INACTIVE Material Supplier in their BOM
		//this Test case has been automated in below Function(TC13_NoAccessforInActiveMaterialSupplier(String [] data))
		
		
		
		public static boolean TC14_NoAccessforInActiveMaterialSupplier(String[] data) throws Exception{
			try{
				navigateToMaterial(data);
				//Click on Search
				enterMaterialNameInTextbox(MaterialName, data[4]);
				CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
				CommonFunctions.waitForPageLoaded();
				//Click first material in list
				//CommonFunctions.clickButtonOrLink(firstMNameinList, "btn", "Material name");
				getPageTitle();
				CommonFunctions.S_ASSERT.assertTrue(pageTitle.equals(data[5]),"The Expected page is not correct.please verify");
				log.info(pageTitle +" "+"Page Appears");
				CommonFunctions.waitForElementTobeClickable(SourcingTab);
				CommonFunctions.clickButtonOrLink(SourcingTab,"Link" , "Sourcing");
				CommonFunctions.waitForPageLoaded();
				InactiveSupplierDropDownelement="//a[contains(text(),'"+data[6]+"')]//preceding::select[1]";
				InactiveSupplierDropDown=By.xpath(InactiveSupplierDropDownelement);
				CommonFunctions.waitForElementTobeClickable(InactiveSupplierDropDown);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(InactiveSupplierDropDown, data[8], "Supplier dropdown value Selection");
				getPageTitle();
				CommonFunctions.S_ASSERT.assertTrue(pageTitle.equals(data[9]),"The Expected page is not correct.please verify");
				log.info(pageTitle +" "+"Page Appears");
				GettingText(materialSupplierActiveStatus);
				Assert.assertEquals(textForVerification,data[14]);
				log.info("Material Supplier Active Status is "+textForVerification);
				log.info("CONCLUSION : THE MATERIAL SHOULD NOT BE VISIBLE WHILE ADDING IN BOM WHICH HAS A SUPPLLER "+data[6]);
				CommonProjectFunctions.logOut();
				
				openBrowser();
				launchApp(data[28],data[29]);
				CommonFunctions.waitForPageLoaded();
			    driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				if(driver.findElements(ProductLink).size()==0) 
				{
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
				}
				//Click on Color link
				driver.findElement(ProductLink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				getPageTitle();
				CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[7],"ERROR: The Expected page is not correct.please verify");
				log.info(pageTitle +" "+"Page Appears");
				CommonFunctions.waitForElementTobeClickable(ProductTextBox);
				CommonFunctions.enterTextInTextboxUpdated(ProductTextBox, data[31], "productTextBox");
				//Clicking Search button
				CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
				CommonFunctions.waitForPageLoaded();
				   //After clicking search if it displays more than results it will execute try block else catch block  
				try{
					if(driver.findElement(ProductSearchResults).isDisplayed()){
						getPageTitle();
						//Finding and Clicking Retails item Option
						CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[27],"ERROR: The Expected page is not correct.please verify");
						CommonFunctions.waitForElementTobeClickable(ChooseRetailItem);
						CommonFunctions.clickButtonOrLink(ChooseRetailItem, "link", "ProductItem");
						CommonFunctions.waitForPageLoaded();
					}
					}
					catch(Exception e){
						log.info("Searched Product has only one result");
						CommonFunctions.waitForPageLoaded();
					}
				//Navigate into the Materials tab
				navigateToMaterialTab(data);
				//Selecting the above Cretaed BOm 
				CommonFunctions.waitForElementTobeClickable(bomId);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(bomId, data[34], "Recently created BOM");
				CommonFunctions.waitForPageLoaded();
				//Click 'Update' Button
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.updateBtn);
				CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update");
				//navigating to the Another frame
				WebDriverWait wait1 = new WebDriverWait(driver,30);
				wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForPageLoaded();
				//Adding a Row to add materials
				CommonFunctions.waitForElementTobeClickable(AddRowsButton);
				InternalBOMSoftG.action = new Actions(driver);
				//double click on description and entering some Text (making Changes in existing one)
				try{	
					GettingText(InternalBOMSoftG.meterial);
					if(textForVerification!="")
					{
						log.info("Unable to double click.material already has been entered");
						log.info("Adding a row to enter the Material");
						//Click Add Rows Button and 
						CommonFunctions.waitForElementTobeClickable(AddRowsButton);
						//adding new Row
						CommonFunctions.clickButtonOrLink(AddRowsButton, "link", "Add row link");
						CommonFunctions.waitForElementTobeClickable(InsertAfterLink);
						CommonFunctions.clickButtonOrLink(InsertAfterLink, "link", "Add row link");
						CommonFunctions.waitForPageLoaded();
						//Double clicking on material text box 
						CommonFunctions.waitForElementTobeClickable(RowTwoMaterial);
						InternalBOMSoftG.action.moveToElement(driver.findElement(RowTwoMaterial)).doubleClick().perform();
				}
				}
				catch(Exception e){
					InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.meterial)).doubleClick().perform();
					log.info("Double Clicking on the first element");
					//Clicking Materials Button
					}
				//InternalBOMSoftG.action.sendKeys(data[15]).perform();
				CommonFunctions.clickButtonOrLink(btnMaterials, "btn", "Material");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				driver.switchTo().frame("materialframe");
		//Entering a material Name
				CommonFunctions.waitForElementTobeClickable(MaterialNameText);
				CommonFunctions.enterTextInTextbox(MaterialNameText, data[33]);
				log.info("This Entered Material Has contains Other Valid material supplier Also As per the requirement");
				//Click on search
				//Clicking search Button
				CommonFunctions.waitForElementTobeClickable(btnSearchMaterial);
				CommonFunctions.clickButtonOrLink(btnSearchMaterial, "Search");
				CommonFunctions.waitForPageLoaded();
				//Click on 'Insert Material' image
				SupplierBasedDynamicElementIdentification=".//a[contains(text(),'"+data[35]+"')]//preceding::img[1]";
				SupplierBasedDynamicElement=By.xpath(SupplierBasedDynamicElementIdentification);
		
				//waiting for the Invisibilty of  the WebElement
				 CommonFunctions.AssertTrueVerification(CommonFunctions.isElementNotPresent(SupplierBasedDynamicElement, "SupplierBasedDynamicElement"), "Condition Falied.Asserton Failed");
				 log.info("The Material is not displayed since the Material Supplier Active Value was updated As No");
				 log.info("NoAccessforInActiveMaterialSupplier TEST CASE VERIFIED SUCCESSFULLY");
				
					
			}
			 catch(Exception e){ 
				fail=true;
				log.error("Exception in NoAccessforInActiveMaterialSupplier", e);
				throw e;
			}
			return true;
		}
		
		public static boolean elementVisibilty(){
		try{
			
			if(driver.findElement(SupplierBasedDynamicElement).isDisplayed()){
				log.info("elemrent is Visbile");
				elementIsVisible=true;
			}
	}
			catch(Exception e){
				log.info("Element is Not visbile "+e);
				elementIsVisible= false;
			}
			return elementIsVisible;
		}
		
		
		
			public static boolean HandleMaterialSelectionPopUP(){	
			boolean flag=false;
		try
		{
			if(driver.findElement(InternalBOMSoftG.MaterialOptionAlertBox).isDisplayed())
			
				log.info("This is a Common Option to search");
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.FirstValueOfMaterial);
				CommonFunctions.clickButtonOrLink(InternalBOMSoftG.FirstValueOfMaterial, "FirstMaterialOptionForLog");
				flag=true;
				
		}
			catch(Exception e){
			    log.info("This is unique search test data since No Alert is Present");
				return flag;
			}
			
			return flag;
		}	
			
			
			
			
			
			
			
			
			
			
 public static void getErrorMessage(){
    	//this function is to verify whether we are getting a  error Message/or not while adding a vendor supplier which doesn't belong to valid supplier category
	try
	{
	CommonFunctions.waitForElementTobeClickable(ErrorText);	
	GettingText(ErrorText);
	log.info(textForVerification);
	log.info("TEST CASE VERIFIED SUCCESSFULLY");
}
	catch(Exception e){
		fail=true;
		log.error("Vendor Suppplier might have been already added in sourcing tab Material Supplier or it could have been a factory Supplier.Please check" +e);
		throw e;
	}

}
 
 public static void getErrorMessageForInvalidSUpplier(){
 	//this function is to verify whether we are getting a  error Message/or not while adding a vendor supplier which doesn't belong to valid supplier category
	try
	{
	CommonFunctions.waitForElementTobeClickable(ErrorText);	
	GettingText(ErrorText);
	log.info(textForVerification);

}
	catch(Exception e){
		fail=true;
		log.error("Our expected result is to get error. but it didn't.so closing the Browser.Test case Failed" +e);
	    driver.quit();
		
	}

}

public static String GettingText(By by){
	//this function is to get the error Message while adding a vendor supplier which doesn't belong to valid supplier category
	WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 60);
	try{
		wait.until(ExpectedConditions.elementToBeClickable(by));
		textForVerification=SeleniumDriver.driver.findElement(by).getText();
		}
	catch(Exception e){
		fail=true;
		log.error("Exception in waitForElementTobeClicable()", e);
		throw e;
		}
	return textForVerification;
	
	}
	
	public static boolean searchMaterial(String [] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			driver.switchTo().frame("materialframe");
			//Select material Type
		/*	CommonFunctions.selectFromDropDownByVisibleText(materialType,data[16]);
			//Enter Created date
			CommonFunctions.enterTextInTextbox(createdDate, data[17]);*/
			
			CommonFunctions.waitForElementTobeClickable(MaterialNameText);
			CommonFunctions.enterTextInTextbox(MaterialNameText, data[16]);
			//Click on search
			CommonFunctions.waitForElementTobeClickable(btnSearchMaterial);
			CommonFunctions.clickButtonOrLink(btnSearchMaterial, "Search");
			CommonFunctions.waitForPageLoaded();
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in navigateToMaterialTab()", e);
			throw e;
		}
	}
	
	public static boolean navigateToMaterialTab(String [] data) throws Exception{
		try{
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[11]);
			InternalBOMSoftG.strSpec=AddSpecification(data);
			//Click on Material tab
			CommonProjectFunctions.clickMaterialsTab();
		//	getPageTitle();
			//Assert.assertEquals(pageTitle, data[7]);
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in navigateToMaterialTab()", e);
			throw e;
		}

	}
	public static String addBOM(String[] data) throws Exception{
		try{
			//Browse to Material Tab
			navigateToMaterialTab(data);
			//Click on Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(bomtypeid, data[13]);
			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.titleIs(data[9]));
		    fillCreatebom(data);
			//Get the name of the selected BOM
			strBOM=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			//Remove the trailing and leading white space
			strBOM=strBOM.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:"+strBOM);
		 }
		catch(Exception e){
			fail=true;
			log.error("Exception in addBOM()", e);
            throw e;
		}
		return strBOM;
	}
	public static String fillCreatebom(String[] data) throws Exception{
		try{
			strBOM = fillCreateBOM(data);
			//CommonFunctions.waitForElementTobeClickable(AddRowsButton);
			System.out.println("BOM Name returned in create page:  " + strBOM);
			fillEditBOM(data);
			strBOM=new Select(driver.findElement(InternalBOMSoftG.BOMId)).getFirstSelectedOption().getText();
			System.out.println("BOM name in detail page after check in: " + strBOM);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreatebom()", e);
			throw e;
		}
		return strBOM;
	}
	
	public static boolean fillEditBOM(String[] data) throws Exception{
		try {
			//Switch to mainframe
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//driver.switchTo().frame("mainFrame");
			//CommonFunctions.waitForPageLoaded();
			//CommonFunctions.waitForElementTobeClickable(AddRowsButton);
			//Component or Location
			InternalBOMSoftG.action = new Actions(driver);
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.inputCompOrLoca, data[15]);
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.checmicalDesc)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.inputChemical, data[15]);
			
			/*CommonFunctions.enterTextInTextbox(InternalBOMSoftG.inputCompOrLoca, data[15]);
			InternalBOMSoftG.action.sendKeys(data[15]).perform();
			CommonFunctions.waitForPageLoaded();
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.checmicalDesc)).doubleClick().perform();
			InternalBOMSoftG.action.sendKeys(data[15]).perform();*/
			CommonFunctions.waitForPageLoaded();
			
			
			/*//Quanity
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.inputquantity, data[12]);*/
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp1();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			//wait.until(ExpectedConditions.titleIs(data[38]));
			//Switch to contentFrame
			
		
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.headerAttributes);

		}catch(Exception e){
			fail=true;
			log.error("Exception in fillEditBOM()", e);
			throw e;
		}
		return true;
	}
	
	public static String fillCreateBOM(String[] data) throws Exception{
		try{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(InternalBOMSoftG.headingCreateBOM));
			CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.headingCreateBOM);
			BOMname="bom"+CommonFunctions.getRandomString(4);
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.name,BOMname);
			//Select colorway
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			//Select Wave
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.wave);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.wave, data[18]);
			//Select Currency
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.currency);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.currency, data[19]);
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddRowsButton));
	        }
		  catch(Exception e)
		  {
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			throw e;
		}
		return BOMname;
	}
	public static boolean createMeaterial(String [] data) throws Exception{
		try{
			navigateToMaterial(data);
			//Navigating to Materials tab
			CommonFunctions.waitForElementTobeClickable(NewButton);
			CommonFunctions.clickButtonOrLink(NewButton, "Link", "New");
			//clicking New Button to create a Material
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[7],"Page Name is Not Correct.Please verify");
			//Checking the Page title
			CommonFunctions.waitForElementTobeClickable(ResinLink);
			CommonFunctions.clickButtonOrLink(ResinLink, "Link", "resin");
			//clicking resin to create a resin tyoe of material
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(MaterialName);
			enterMaterialNameInTextbox(MaterialName, data[4]);
			//This Material Name needs to be changed every time In excel before Running the Test case
			CommonFunctions.enterTextInTextbox(MaterialDescription, data[15]);
			//Entering a Material Name
			CommonFunctions.gettingParentWindow();
			//getting parent window Name
			CommonFunctions.waitForElementTobeClickable(SeasonLink);
			CommonFunctions.clickButtonOrLink(SeasonLink, "Link", "Season");
			//Clicking season link to select Season value
			CommonFunctions.switchingChildWindow();
			//Switching into child Window(Season Window)
			getPageTitle();
			CommonFunctions.S_ASSERT.assertEquals(pageTitle, data[28],"ERROR: page Name Is Not correct.Please Verify");
			CommonFunctions.waitForElementTobeClickable(MaterialName);
		
			CommonFunctions.enterTextInTextbox(MaterialName, data[11]);
			//Entering the season name in Season text Box
			CommonFunctions.waitForElementTobeClickable(FirstSearchButton);
			CommonFunctions.clickButtonOrLink(FirstSearchButton, "First Serach Buuton");
			//clicking on search Button
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(ChooseButton);
			CommonFunctions.clickButtonOrLink(ChooseButton, "link", "ChooseLink");
			//clicking on Choose Button
			CommonFunctions.switchParentWindow();
			//Child window closed so switching to Parent window
			 CommonFunctions.waitForPageLoaded();
			    //Waiting till the page to be loaded
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//Switching to content frame in Content frame
			CommonFunctions.waitForElementTobeClickable(CreateButton);
			CommonFunctions.clickButtonOrLink(CreateButton, "Create");
			//Clicking create button 
			CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.info("Materails has not been Created"+e);
			throw e;
		}
		return true;
	}
	
	public static boolean navigateToMaterial(String[] data) throws Exception
	{
		try{
			//Added refresh code below as to create another material BOM require this as we need to close library + sign
			if(driver.findElements(Material.materialLink).size()==0) {
				//driver.navigate().refresh();
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			if(driver.findElements(Material.materialLink).size()==0) 
			{
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
			}
			//Click on Color link
			driver.findElement(Material.materialLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[3],"ERROR: The Expected page is not correct.please verify");
			log.info(pageTitle +" "+"Page Appears");
			CommonFunctions.waitForVisibilityOfElement(Material.materialHeadning);
          }catch(Exception e){
			fail=true;
			log.error("Exception in navigateUptoCreateDiffrntTypesOfMaterial()", e);
			throw e;
		}
		return true;
	}
	
	public static boolean navigateToProduct(String[] data) throws Exception
	{
		try{
			//Added refresh code below as to create another material BOM require this as we need to close library + sign
		   driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			if(driver.findElements(ProductLink).size()==0) 
			{
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
			}
			//Click on Color link
			driver.findElement(ProductLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[3],"ERROR: The Expected page is not correct.please verify");
			log.info(pageTitle +" "+"Page Appears");
		
          }catch(Exception e){
			fail=true;
			log.error("Exception in navigateUptoCreateDiffrntTypesOfMaterial()", e);
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
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(InternalBOMSoftG.selectSpecification, data[32],"Drop down value selection");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			
			}
			else
			{
				InternalBOMSoftG.strSpec= CommonProjectFunctions.Create_Specifications(data[17],data[18]);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			}
			log.info("Specification is: "+InternalBOMSoftG.strSpec);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			throw e;
		}
		return InternalBOMSoftG.strSpec;
	}

  public static String getPageTitle(){
	  //generic function to getPageTitle
	  try{
	  pageTitle=driver.getTitle();
	  log.info(pageTitle);
		 }
	  catch(Exception e){
		  fail=true;
		  log.error("Unable to get a pagetitle"+e);
		  throw e;
	  }
		 return pageTitle;
	  
  }
  
  public static boolean verifyMaterialSeachFunctionality(String [] data) throws Exception{
	  try
	  {
		  InternalBOMSoftG.action = new Actions(driver);
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.MaterialTextBox1);
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.MaterialTextBox1)).doubleClick().perform();
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.MaterialTextBox1)).sendKeys(data[23]).perform();
			//Entering a value to the 1st Material text box using Actions Class
			InternalBOMSoftG.action.sendKeys(Keys.TAB).perform();
		    //Pressing a TAB
			CommonFunctions.waitForPageLoaded();
			HandleMaterialSelectionPopUP();
			//Verifying whether given test data have multiple option to select/not and if it has multiple options selecting one value    
			CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.MaterialTextBox2);
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.MaterialTextBox2)).doubleClick().perform();
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.MaterialTextBox2)).sendKeys(data[24]).perform();
			//Entering a value to the 2nd Material text box using Actions Class
			InternalBOMSoftG.action.sendKeys(Keys.TAB).perform();
		    //Pressing a TAB
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(3000);
			HandleMaterialSelectionPopUP();
			//Verifying whether given test data have multiple option to select/not and if it has multiple options selecting one value    
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(saveAndCheckIn, "btn", "Save and Check In");
			//Clicking save and check in
			CommonFunctions.handleAlertPopUp1();
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("contentframe");
			//WebDriverWait wait3 = new WebDriverWait(driver,30);
			//wait3.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("contentframe"));
			 CommonFunctions.waitForPageLoaded();
		    
  }
	  catch(Exception e){
		  fail=true;
		  log.error("Error in verifyMaterialSeachFunctionality"+e);
		  throw e;
	  }
	  return true;
  }
  
  
	public static void enterMaterialNameInTextbox(By by, String inputValue) throws Exception{
	try{
			//check the presence of web element if available then select the value from drop down
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				int numberOfLetter=inputValue.length();
				System.out.println(numberOfLetter);
				if(inputValue.contains(".0")){
					 SeleniumDriver.driver.findElement(by).sendKeys(inputValue.substring(0, numberOfLetter-2));
						SeleniumDriver.log.info("Entered" + " " + inputValue + " " + "in the text field.");
						
				}
				else
				{
			    SeleniumDriver.driver.findElement(by).sendKeys(inputValue);
				SeleniumDriver.log.info("Entered" + " " + inputValue + " " + "in the text field.");
				}
			
			}
			
		}
		catch(Exception e){
			fail=true;
			log.error("Error while Entering the text value" +e);
		    throw e;
		}
	}
	
	public static boolean SupplierVerificationInSourcing(String [] data) throws Exception{
		//This function verifies whether already added supplier fis present or not in the in Sourcing    
		boolean flag=false;
		try{
			CommonFunctions.waitForElementTobeClickable(SourcingOption);
			CommonFunctions.clickButtonOrLink(SourcingOption, "Sourcing Options");
			CommonFunctions.waitForPageLoaded();
			//Below Function is used to Copy the data[20] value into the clip board
			StringSelection selec= new StringSelection(data[20]);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selec, selec);
			//initializing the Robot class
			Robot robot = new Robot();
			//pressing 'CONTROL' button
			robot.keyPress(KeyEvent.VK_CONTROL);
			//Pressing 'F' button along with above control .so Now 'Ctrl+f' has been pressed to search the already added supplier 
			robot.keyPress(KeyEvent.VK_F);
			//Releasing 'F' button.
			robot.delay(1000);
			//keeping a delay for a robot for 1 sec.
	        robot.keyRelease(KeyEvent.VK_F);
	      //Pressing 'V' button along with above control .so Now 'Ctrl+V' has been pressed to paste the value available in the clipboard 
			robot.keyPress(KeyEvent.VK_V);
			//Releasing 'V' button.
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		  //Releasing 'Ctrl' button.
		    robot.delay(2000);
		  //keeping a delay for a robot for 2 secs.
		    CommonFunctions.waitForElementTobeClickable(AddedSupplierVerification);
		  //now the Already added supplier is Visible and Clicking the same
		    CommonFunctions.clickButtonOrLink(AddedSupplierVerification, "valid Supplier");
		    CommonFunctions.waitForPageLoaded();
		  //getting the Text of The added Supplier
		    GettingText(SupplierVerification);
		    //Verifying the Text of the already added Supplier
		    CommonFunctions.S_ASSERT.assertEquals(textForVerification, data[20],"ERROR: Verification Text is not correct");
		    log.info("***ADDED SUPPLIER TEST CASE HAS BEEN VERIFIED SUCCESSFULLY***");
            flag=true;
		}
    catch(Exception e){
    	fail=true;
    	log.error("Error While Verifying the Already added Supplier",e);
    	throw e;
    }
		return flag;
	}
	public static boolean HasbroInternalSupplierSelection(String [] data) throws Exception{
		try{
			 CommonFunctions.waitForElementTobeClickable(ActionButton);
			   //waiting for visibility of Action drop-down
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[8], "Supplier");
				//clicking on Add supplier in Action drop-down
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.switchingChildWindow();
				//Switching from parent window to child window
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				getPageTitle();
				//getting a page Title
				CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"ERROR:Page name is not correct.please check the swithched window");
				//verifying the page name
				log.info(pageTitle+" Page Appears");
				//printing the page name
				CommonFunctions.waitForElementTobeClickable(SupplierOption);
				//waiting for the Supplier Button in child window
				CommonFunctions.clickButtonOrLink(SupplierOption, "link", "Supplier");
				//waiting for the Supplier Category drop down first option
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.waitForElementTobeClickable(SupplierCategoryDropDown);
				//waiting for the Supplier Category drop down
				CommonFunctions.waitForElementTobeClickable(SupplierCategoryDropDownFirstValue);
				//waiting for the Supplier Category drop down first option
				CommonFunctions.clickButtonOrLink(SupplierCategoryDropDownFirstValue, "option", "Supplier category DropDown Option");
				//clicking the Supplier Category drop down first option
				Actions actionObj = new Actions(driver);
				actionObj.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
				//Selecting all the values in Supplier Category droop down
				CommonFunctions.clickButtonOrLink(RemoveButton, "link", "Remove Link");
				//clicking on remove link
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SupplierName);
				//waiting for the element of supplier Name
				CommonFunctions.enterTextInTextbox(SupplierName, data[20]);
				// Entering the data in name
				CommonFunctions.clickButtonOrLink(FirstSearchButton, "link", "SearchButton");
				//clicking on search button
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.waitForElementTobeClickable(CheckSearchResultCheckBox);
				//waiting for the check box to appear as per the data[20]
				CommonFunctions.clickButtonOrLink(CheckSearchResultCheckBox, "checkBox", "SelectAllCheckBoxes");
				//Clicking on the check box 
				CommonFunctions.waitForElementTobeClickable(SelectButton);
				//waiting for select button
				CommonFunctions.clickButtonOrLink(SelectButton, "Button", "SelectButton");
				//clicking on select Button
			    CommonFunctions.switchParentWindow();
			    //Switching into parent window again
			    CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception while adding Hasbro internal");
			throw e;
		}
		return true;
	}
	
	public static boolean OEMVendorSupplierSelection(String [] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Switching to content frame in Content frame
	        CommonFunctions.waitForElementTobeClickable(ActionButton);
			   //waiting for visibility of Action drop-down
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[8], "Supplier");
				//clicking on Add supplier in Action drop-down
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.switchingChildWindow();
				//Switching from parent window to child window
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				getPageTitle();
				//getting a page Title
				CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[9],"ERROR:Page name is not correct.please check the swithched window");
				//verifying the page name
				log.info(pageTitle+" Page Appears");
				//printing the page name
				CommonFunctions.waitForElementTobeClickable(SupplierOption);
				//waiting for the Supplier Button in child window
				CommonFunctions.clickButtonOrLink(SupplierOption, "link", "Supplier");
				//waiting for the Supplier Category drop down first option
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.waitForElementTobeClickable(SupplierCategoryDropDown);
				//waiting for the Supplier Category drop down
				CommonFunctions.waitForElementTobeClickable(SupplierCategoryDropDownFirstValue);
				//waiting for the Supplier Category drop down first option
				CommonFunctions.clickButtonOrLink(SupplierCategoryDropDownFirstValue, "option", "Supplier category DropDown Option");
				//clicking the Supplier Category drop down first option
				Actions actionObj= new Actions(driver);
			    actionObj.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
				//Selecting all the values in Supplier Category droop down
				CommonFunctions.clickButtonOrLink(RemoveButton, "link", "Remove Link");
				//clicking on remove link
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.enterTextInTextbox(VMRVendorCode, data[21]);
				//Entering FUNSKOOL (INDIA) LTD VMR Code for Vendor Supplier in case in future if it changes that corresponding VMR code needs to be updated in excel.
				CommonFunctions.waitForElementTobeClickable(FirstSearchButton);
				//waiting for search button
				CommonFunctions.clickButtonOrLink(FirstSearchButton, "Button", "SearchButton");
				//Clicking on search button
				CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
				CommonFunctions.waitForElementTobeClickable(VendorSupplier);
				// waiting for vendor supplier WebElement
				CommonFunctions.clickButtonOrLink(VendorSupplier, "checkbox", "checkBoxOfVendorSupplier");
				//clicking on Funskool check box
				CommonFunctions.clickButtonOrLink(SelectButtonOfVendor, "button", "selectButton");
				//clicking on select
				CommonFunctions.switchParentWindow();
				//Switching to parent window again since child window has been closed after clicking 'select'
			    getPageTitle();
			    //getting the page title of the parent window
			    CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[5],"ERROR:Page Name is Not correct.Please Verify");
			    //Verifying the page name of the parent window
			    log.info(pageTitle+" Page Appears");
			    //printing the page name
			    CommonFunctions.waitForPageLoaded();
				//Waiting till the page to be loaded
		}
		catch(Exception e){
			fail=true;
			log.error("Exception while adding OEM Vendor");
			throw e;
		}
		return true;
	}
	public static boolean deActivateSupplier(String [] data) throws Exception{
		try
		{
              // Selecting Update Supplier Material Option in Action drop-down  
			CommonFunctions.waitForPageLoaded();
			 CommonFunctions.waitForElementTobeClickable(ActionButton);
			    CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionButton, data[26], "Supplier");
			    CommonFunctions.waitForPageLoaded();
			    getPageTitle();
			    //verifying the Page Title
			    CommonFunctions.S_ASSERT.assertEquals(pageTitle,data[26],"ERROR:The Page Name is not Correct.Please verify");
			    CommonFunctions.waitForElementTobeClickable(MaterialSupplierActiveCheckBox);
			    
			    //verifying Whtether Material Supplier Link check box is checked or not .if it is Selected then it will go the 'if' condition and will uncheck.
			    if(driver.findElement(MaterialSupplierActiveCheckBox).isSelected())
			    {
			    	CommonFunctions.clickButtonOrLink(MaterialSupplierActiveCheckBox, "checkBox", "MaterialSupplierActiveCheckBox");
			    	//Clicking 'SAVE' Button
			    	CommonFunctions.clickButtonOrLink(SaveButton, "Button", "SaveButton");
		            CommonFunctions.waitForPageLoaded();
			    }
		}
		catch(Exception e){
			fail=true;
			log.error("Exceptin while de-activating the Supplier");
			throw e;
		}
		return true;
	}
  @BeforeMethod
  
  public void Refresh(){
	  if(driver!=null && runmodes[count+1].equalsIgnoreCase("y")){
		  driver.navigate().refresh();
		  log.info("Driver is refreshed");
	  }
	  else{
		  log.info("No Driver is Launched");
	  }
  }
	
	public static boolean SearchExistingMaterial(String [] data) throws Exception {
		try{
             CI296.navigateToMaterial(data);
			 CI299.MaterialType=By.linkText(data[42]);
			 CommonFunctions.clickButtonOrLink(CI299.MaterialType, "HyperLink", data[42]);
			 CommonFunctions.waitForPageLoaded();
			 CommonFunctions.waitForElementTobeClickable(CI299.SearchMaterailTextBox);
			 CommonFunctions.enterTextInTextboxUpdated(CI299.SearchMaterailTextBox, data[4], "MaterialSearch");
			 CommonFunctions.clickButtonOrLink(CI299.SearchButton, "Search");
			 CommonFunctions.waitForPageLoaded();
			 wait.until(ExpectedConditions.titleIs(data[5]));
			 }
		catch(Exception e){
			fail=true;
			log.error("Exception while searching the Material" +e);
			throw e;
		}
		return true;
	}
	public static boolean OEMManufacturerAppearanceVerificationInMaterail(String [] data) throws Exception {
		try{
			
			CommonFunctions.waitForElementTobeClickable(SourcingOption);
			CommonFunctions.clickButtonOrLink(SourcingOption, "HyperLink", "SourcingOption");
			CommonFunctions.waitForPageLoaded();
			/*StringSelection selec= new StringSelection(data[20]);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selec, selec);
			//initializing the Robot class
			Robot robot = new Robot();
			//pressing 'CONTROL' button
			robot.keyPress(KeyEvent.VK_CONTROL);
			//Pressing 'F' button along with above control .so Now 'Ctrl+f' has been pressed to search the already added supplier 
			robot.keyPress(KeyEvent.VK_F);
			//Releasing 'F' button.
			robot.delay(1000);
			//keeping a delay for a robot for 1 sec.
	        robot.keyRelease(KeyEvent.VK_F);
	        //Pressing 'V' button along with above control .so Now 'Ctrl+V' has been pressed to paste the value available in the clipboard 
			robot.keyPress(KeyEvent.VK_V);
			//Releasing 'V' button.
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		   //Releasing 'Ctrl' button.
		    robot.delay(2000);
		   //keeping a delay for a robot for 2 secs.*/
		    CommonFunctions.waitForElementTobeClickable(AddedSupplierVerification);
		    //now the Already added supplier is Visible and Clicking the same
			CommonFunctions.clickButtonOrLink(AddedInValidSupplierVerification, "Invalid Supplier");
			CommonFunctions.waitForPageLoaded();
			//getting the Text of The added Supplier
			GettingText(SupplierVerification);
			//Verifying the Text of the already added Supplier
			CommonFunctions.AssertEqualsVerification(textForVerification, data[20], "Actual And Expected text is Not match.please verify");
			log.info("CONCLUSION: INVALID SUUPLIER IS ALREADY ADDED IN THIS MATERIAL");
			driver.navigate().refresh();
	         
			 }
		catch(Exception e){
			fail=true;
			log.error("Exception while searching the Material" +e);
			throw e;
		}
		return true;
	}

	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "SKIP");
		
		else if(fail||CommonFunctions.error){
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

