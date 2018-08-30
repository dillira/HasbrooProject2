package com.hasbro.security;

/*
 * 
 * Make sure Assortment/Solid Product is created with Funskool source created
 * In above product make sure 5 colorway created
 * 
 * 
 * 
 * 
 * 
 * 
 */


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

/**
 * @author anjali Gupta
 *
 */

public class RFQ extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String rfqCreateName ;
	static String rfqRSName ="RFQ E4946-108 B" ;	
	static String rfqCancelledName ;		
	static String rfqCompleteName ;		
	static String rfqDeleteName;
	static String rfqDraftName;
	public static String username;
	static String rfqsearchName = null;
	static Boolean deletedRFQ=false;
	static Boolean searchRFQ=false;
	static boolean varData;
	static boolean approxV;
	static boolean waveDate;
	static boolean approxVProduct;
		
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static int i;

	public static By rfqTab= By.xpath("//a[contains(text(),'RFQ')]");
	public static By selectSource= By.id("sourcingConfigId");
	public static By productAction= By.xpath(".//*[@id='prodseasonActions']");
	public static By createRFQ= By.xpath(".//*[@id='prodseasonActions']//following::option[contains(text(),'Create RFQ')]");
	public static By selectVendorHeading= By.xpath("//td[contains(text(),'Please select vendors to continue')]");
	public static By selectVendorSelect= By.xpath("//a[contains(text(),'Select')]");
	public static By rfqID= By.xpath("//td[contains(text(),'RFQ ID')]");
	public static By colorwayInput= By.xpath("//td[contains(text(),'*Colorway')]//following::select[1]");
	public static By waveInput= By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By requestDate= By.xpath("//td[contains(text(),'Request Date')]//following::input[1]");
	public static By requestResponseCutoffDate= By.xpath("//td[contains(text(),'Requested Response Cut-Off Date')]//following::input[1]");
	public static By requestResponseCutoffDatedp= By.xpath("//td[contains(text(),'Requested Response Cut-Off Date')]//following::td[1]");	
	public static By create=By.xpath("//a[contains(text(),'Create')]");
	public static By updateRFQ= By.xpath("//td[contains(text(),'Update RFQ')]");
	public static By expandButton = By.xpath(".//*[@id='menuImage0']//following::img[1]");
	public static By costsheetList= By.xpath("//td[contains(text(),'Cost Sheet List')]");
	public static By costingActions = By.xpath(".//*[@id='costingActions_r0']");
	public static By createCostSheetTemplate = By.xpath(".//*[@id='costingActions_r0']//following::option[contains(text(),'Create Cost Sheet Templates')]");
	public static By createProductCostSheetHeading = By.xpath("//span[contains(text(),'Create Product Cost Sheet')]");
	public static By cstype = By.xpath("//*[contains(text(),'massChangeRow')]//following::select[1]");
	public static By csname = By.xpath(".//*[@id='massChangeButton']//following::td[contains(text(),'Name')]//following::input[3]");
	public static By csTempColorways= By.xpath("//select[contains(@id,'colorwayGroupOptions')]");
	public static By csWave= By.xpath("//input[contains(@id,'hbWave')]//following::select[1]");
	public static By csQuoteCurrency= By.xpath("//input[contains(@id,'hBCurrency')]//following::select[1]");
	public static By btnSave= By.id("saveButton");
	public static By closeBtn= By.xpath("//div[@id='divHeader']//img[contains(@src,'closebutton.png')]");
	public static By btnDone= By.xpath(".//*[@id='hideColButton']//following::a[@href='javascript:saveAndDone(false)']");
	public static By rfqName= By.xpath(".//*[@id='ptcrfqName']");
	public static By csSave= By.xpath("//a[@href='javascript:BulkCreateCostSheet.createCostSheets()']");
	public static By lnkAdd =  By.xpath("//a[text()='Add']");
	public static By btnDoneAndSubmit= By.xpath("//*[@id='hideColButton']//following::a[@href='javascript:submitRFQ()']");
	public static By rfqResult =  By.xpath("//div[@id='rfqResults']//following::a[contains(text(),'Name')]//ancestor::table[1]/tbody/tr/td[2]");
	public static By imgClose =By.xpath("//img[contains(@src,'deleteXsmall.png')]");
	public static By rfqAction =By.xpath(".//*[@id='rfqRequestActionOptions']");
	public static By rfqUpdate =By.xpath(".//*[@id='rfqRequestActionOptions']/option[contains(text(),'Update')]");
	public static By rfqstatusedit= By.xpath("//td[contains(text(),'Request Status')]//following::select[1]");
	public static By rfqstatusdetail= By.xpath(".//*[@id='status']");
	public static By rfqnamedetail= By.xpath("//td[contains(text(),'RFQ Identification')]//following::td[1]//following::td[1]");	
	public static By submitReqToVendor= By.xpath("//*[@id='rfqRequestActionOptions']/option[contains(text(),'Submit Request to Vendors')]");
	public static By quoteCostSheetList= By.xpath("//td[contains(text(),'Cost Sheet List')]");
	public static By rfqRefreshbtn= By.xpath(".//*[@id='refreshRFQButton']");
	public static By rfqSidebar= By.xpath("//a[contains(text(),'RFQ')]");
	public static By rfqResultTab= By.xpath(".//*[@id='firstTab']/a");
	public static By rfqDelete =By.xpath(".//*[@id='rfqRequestActionOptions']/option[contains(text(),'Delete')]");
	public static By rfqNameColumn= By.xpath("//div[@id='rfqResults']//following::a[contains(text(),'Name')]//ancestor::table[1]//tbody/tr/td[2]");
	public static By rfqCommenttextarea= By.xpath("//td[contains(text(),'Comments to Vendor')]//following::textarea[1]");
	public static By rfqCommenttextareadp= By.xpath(".//*[@id='commentsToVendor']");
	public static By actionView= By.xpath(".//*[@id='actionOptions']/div/a[contains(text(),'View')]");
	public static By vendors=By.xpath("//td[contains(text(),'Vendors')]");
	public static By removeVendorAction=By.xpath("//select/option[contains(text(),'Remove Vendor')]");
	public static By cancelUpdatebtn= By.xpath("//td[contains(text(),'Products')]//following::a[contains(text(),'Cancel')]");
	public static By rfqLineItemAttribute =By.xpath("//td[contains(text(),'RFQ Line Item Attributes:')]");
	public static By approxVolume= By.xpath(".//*[@id='r0_approxVolume']");
	public static By approxVolumeInput= By.xpath("//*[@id='r0_approxVolume']//following::input[1]");
	public static By weeklyRequirement=By.xpath(".//*[@id='r0_hbWeeklyReq']");
	public static By approxVolumedetailpage =By.xpath(".//*[@id='approxVolume']");
	public static By products= By.xpath("//td[contains(text(),'Products')]");
	public static By costSheetTemplateList = By.xpath("//td[contains(text(),'Cost Sheet Template List')]");
	public static By quote =By.xpath("//td[contains(text(),'Quote') and contains(text(),'FUNSKOOL (INDIA) LTD')]");
	public static By associatedDocument = By.xpath("//td[contains(text(),'Associated Documents:')]");
	public static By associatedDocumentActions = By.xpath("//td[contains(text(),'Reference Documents')]//preceding::td[contains(text(),'Actions')]//following::select[1]");
	public static By createNewRefDocument = By.xpath("//td[contains(text(),'Reference Documents')]//preceding::td[contains(text(),'Actions')]//following::option[contains(text(),'Create New Reference Document')]");
	public static By psAssortmentType= By.xpath("//a[contains(text(),'Reports')]//preceding::a[contains(text(),'Assortment / Solid')]");
	public static By createNewDocument = By.xpath("//td[contains(text(),'Create New Document')]");
	public static By documentName= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By productNo = By.xpath("//a[contains(text(),'Product Number:')]");
	public static By productNumber = By.xpath("//*[@id='LCSPRODUCT_ptc_str_1']");
	public static By search= By.id("SearchButton1");
	public static By choose = By.xpath("//a[contains(text(),'choose')]");
	public static By season = By.xpath("//a[contains(text(),'Season:')]");
	public static By seasonNumber = By.xpath("//*[@id='LCSSEASON_ptc_str_2']");
	public static By pckgStatus= By.xpath("//td[contains(text(),'*Packaging Status')]//following::select[1]");
	public static By wave = By.xpath("//td[contains(text(),'*Wave')]//following::select[1]");
	public static By SecPackageStyle= By.xpath("//td[contains(text(),'*Secondary Package Style')]//following::select[1]");
	public static By ShipUnitStyle= By.xpath("//td[contains(text(),'*Shipping Unit Style')]//following::select[1]");
	public static By SecPackageSoldAs= By.xpath("//td[contains(text(),'*Secondary Package Sold As')]//following::select[1]");
	public static By ShipUnitSoldAs= By.xpath("//td[contains(text(),'*Shipping Unit Sold As')]//following::select[1]");
	public static By SecPckgPieces= By.xpath("//td[contains(text(),'*Secondary Package Pieces')]//following::input[1]");
	public static By ShipUnitPckgPics= By.xpath("//td[contains(text(),'*Shipping Unit Package Pieces')]//following::input[1]");
	public static By SecPckgDepthIN= By.xpath("//td[contains(text(),'*Secondary Package Depth IN')]//following::input[1]");
	public static By ShipUnitPckgDepthIN= By.xpath("//td[contains(text(),'*Shipping Unit Package Depth IN')]//following::input[1]");
	public static By SecPckgWidthIN= By.xpath("//td[contains(text(),'*Secondary Package Width IN')]//following::input[1]");
	public static By ShipUnitPckgWidthIN= By.xpath("//td[contains(text(),'*Shipping Unit Package Width IN')]//following::input[1]");
	public static By SecPckgHeightIN= By.xpath("//td[contains(text(),'*Secondary Package Height IN')]//following::input[1]");
	public static By ShipUnitPckgHeightIN= By.xpath("//td[contains(text(),'*Shipping Unit Package Height IN')]//following::input[1]");
	public static By createDocument= By.xpath("//a[text()='Create']");
	public static By vendorComments = By.xpath("//td[contains(text(),'Vendor Comments')]");
	public static By quoteActions = By.xpath("//td[contains(text(),'Quote') and contains(text(),'FUNSKOOL (INDIA) LTD')]//following::td[contains(text(),'Actions')]/select[1]");
	public static By quoteUpdate = By.xpath("//td[contains(text(),'Quote') and contains(text(),'FUNSKOOL (INDIA) LTD')]//following::td[contains(text(),'Actions')]/select[1]/option[contains(text(),'Update')]");
	public static By commentFromVendor = By.xpath("//td[contains(text(),'Comments From Vendor')]");
	public static By commentFromVendorInput = By.xpath("//td[contains(text(),'Comments From Vendor')]//following::textarea[1]");
	public static By save = By.xpath("//a[contains(text(),'Save')]");
	public static By commentFromVendordp= By.xpath(".//*[@id='commentsFromVendor']");
	public static By systemInformation = By.xpath("//td[contains(text(),'System Information:')]");
	public static By cancelbtn = By.xpath("//a[contains(text(),'Cancel')]");
	public static By costSheetNameTemplate = By.xpath(".//*[@id='ViewButton']/img//following::td[contains(text(),'Name')]//following::a[1]");
	public static By costsheetAction =  By.xpath(".//*[@id='costSheetResults']//following::select[1]");
	public static By costsheetUpdate= By.xpath(".//*[@id='costSheetResults']//following::select[1]/option[contains(text(),'Update')]");
	public static By costsheetUpdatepage= By.xpath("//td[contains(text(),'Update Product Cost Sheet')]");
	public static By csWaveEditpage = By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By waveDetailPage= By.xpath(".//*[@id='hbWave']");
	
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcRFQ(String[] data) throws Exception{
		try{
			count++;
			log.info(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for RFQ Security");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[2]);
			log.info("col1 :" + data[3]);
			log.info("col4 :" + data[4]);
			username=data[0];
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					driver.quit();
				}
			}
			if(runmodes[count].equalsIgnoreCase("y")){
				if(y==0){
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					log.info("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}

			//Create RFQ
			if(data[3].equalsIgnoreCase("createRFQ"))
			{ createRFQ(data); }
			
			//Create Draft RFQ
			if(data[3].equalsIgnoreCase("createDraftRFQ"))
			{ createDraftRFQ(data); }
			
			//Create Request Sent RFQ
			if(data[3].equalsIgnoreCase("createRequestSentRFQ"))
			{ createRequestSentRFQ(data); }
									
			//Create Cancel RFQ
			if(data[3].equalsIgnoreCase("createCancelledRFQ"))
			{ createCancelledRFQ(data); }
			
			//Create Complete RFQ
			if(data[3].equalsIgnoreCase("createCompleteRFQ"))
			{ createCompleteRFQ(data); }
			
			//Read RFQ
			if(data[3].equalsIgnoreCase("readRFQ"))
			{ readRFQ(data); }
			
			//Update RFQ
			if(data[3].equalsIgnoreCase("updateRFQ"))
			{ updateRFQ(data); }
			
			//Delete RFQ
			if(data[3].equalsIgnoreCase("deleteRFQ"))
			{ deleteRFQ(data); }	
			
			//Read Draft RFQ
			if(data[3].equalsIgnoreCase("readDraftRFQ"))
			{ readDraftRFQ(data); }
			
			//Update Draft RFQ
			if(data[3].equalsIgnoreCase("updateDraftRFQ"))
			{ updateDraftRFQ(data); }
									
			//Read Request Sent RFQ
			if(data[3].equalsIgnoreCase("readRSRFQ"))
			{ readRSRFQ(data); }
			
			//Update Draft RFQ
			if(data[3].equalsIgnoreCase("updateRSRFQ"))
			{ updateRSRFQ(data); }
			
			//Read Request Sent RFQ
			if(data[3].equalsIgnoreCase("readCancelledRFQ"))
			{ readCancelledRFQ(data); }
			
			
			//Update Draft RFQ
			if(data[3].equalsIgnoreCase("updateCancelledRFQ"))
			{ updateCancelledRFQ(data); }
			
			
			//Read Request Sent RFQ
			if(data[3].equalsIgnoreCase("readCompleteRFQ"))
			{ readCompleteRFQ(data); }
						
			
			//Update Draft RFQ
			if(data[3].equalsIgnoreCase("updateCompleteRFQ"))
			{ updateCompleteRFQ(data); }
			
			//Read General Attribute
			if(data[3].equalsIgnoreCase("readGeneralAttribute"))
			{ readGeneralAttribute(data); }
			
			//update GeneralAttribute
			if(data[3].equalsIgnoreCase("updateGeneralAttribute"))
			{ updateGeneralAttribute(data); }
			
			//readVendors
			if(data[3].equalsIgnoreCase("readVendors"))
			{ readVendors(data); }
						
			//update Vendors
			if(data[3].equalsIgnoreCase("updateVendors"))
			{ updateVendors(data); }
						
			//read RFQLine Item Attributes
			if(data[3].equalsIgnoreCase("readRFQLineItemAttributes"))
			{ readRFQLineItemAttributes(data); }
			 
			//update RFQ Line Item Attributes
			if(data[3].equalsIgnoreCase("updateRFQLineItemAttributes"))
			{ updateRFQLineItemAttributes(data); }
									
			//read Products
			if(data[3].equalsIgnoreCase("readProducts"))
			{ readProducts(data); }
			
			//update Products
			if(data[3].equalsIgnoreCase("updateProducts"))
			{ updateProducts(data); }
						
			//read CostSheetTemplateList
			if(data[3].equalsIgnoreCase("readCostSheetTemplateList"))
			{ readCostSheetTemplateList(data); }
			
			//update CostSheet TemplateList
			if(data[3].equalsIgnoreCase("updateCostSheetTemplateList"))
			{ updateCostSheetTemplateList(data); }
			
			//read Quote
			if(data[3].equalsIgnoreCase("readQuote"))
			{ readQuote(data); }
			
			//Update Quote
			if(data[3].equalsIgnoreCase("updateQuote"))
			{ updateQuote(data); }
			
			//read Vendor Comments
			if(data[3].equalsIgnoreCase("readVendorComments"))
			{ readVendorComments(data); }
			
			//Update Vendor Comments
			if(data[3].equalsIgnoreCase("updateVendorComments"))
			{ updateVendorComments(data); }
						
			//read Associated Documents
			if(data[3].equalsIgnoreCase("readAssociatedDocument"))
			{ readAssociatedDocument(data); }
			
			//update Associated Documents
			if(data[3].equalsIgnoreCase("updateAssociatedDocuments"))
			{ updateAssociatedDocuments(data); }
			
			//read System Information
			if(data[3].equalsIgnoreCase("readSystemInformation"))
			{ readSystemInformation(data); }
			
			//update System Information
			if(data[3].equalsIgnoreCase("updateSystemInformation"))
			{ updateSystemInformation(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	public static String searchandClickforRequriedRFQ(String rfqtoSearch) throws Exception
	{
		try{
			List<WebElement> row1 = driver.findElements(rfqResult);
			System.out.println(row1.size());
			for(WebElement e: row1){
				rfqsearchName =e.getText().trim();
				log.info("The name got search is:" + rfqsearchName);
				String rfqsearchName1 =e.getText().trim().replaceAll("\\s+", " ");
				log.info("The name got search and after trim is:" + rfqsearchName);

				if (rfqsearchName1.equals(rfqtoSearch)) {
					Thread.sleep(2000);
					CommonFunctions.clickButtonOrLink(By.linkText(rfqsearchName),"link","Cost sheet name");
					return rfqsearchName;
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in searchandClickforRequriedRFQ()", e);
			return "";
		}
		return rfqsearchName;
	}

	
	public static Boolean navigateToRFQ(String[] data) throws Exception{
		try{
			if(driver.findElements(rfqTab).size()==0) {
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
				
				//Click on RFQ tab
				CommonFunctions.clickButtonOrLink(rfqTab, "Click on RFQ Link");	
				closeTheOpenRFQ();
			}
			
			else {
				
				//Click on RFQ tab
				CommonFunctions.clickButtonOrLink(rfqTab, "Click on RFQ Link");				
				//Close the open cost sheet
				closeTheOpenRFQ();
			}
			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in navigateToRFQ()", e);
			return false;
		}
	}
	
	
	private static void fillDocumentmandat(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			Date date = new Date();
			//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(createNewDocument));
			CommonFunctions.clickButtonOrLink(documentName, "document name");
			CommonFunctions.enterTextInTextbox(documentName,data[3]+date.getTime());
			//Search for Product from POPUP Page
			CommonFunctions.clickButtonOrLink(productNo, "link", "Product");
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.enterTextInTextbox(productNumber, data[5]);
			CommonFunctions.clickButtonOrLink(search, "Search For Product Number");
			CommonFunctions.clickButtonOrLink(choose, "Product selected");
			driver.switchTo().window(parent);
			driver.switchTo().frame("contentframe");
			
			CommonFunctions.clickButtonOrLink(season, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			CommonFunctions.enterTextInTextbox(seasonNumber, data[8]);
			CommonFunctions.clickButtonOrLink(search, "Search For Season");
			CommonFunctions.clickButtonOrLink(choose, "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			
			//Select Packaging Status
			CommonFunctions.selectFromDropDownByVisibleText(pckgStatus, "HDM – ESTIMATE"); 
			
			//Select Wave
			CommonFunctions.selectFromDropDownByVisibleText(wave, data[14]);
			//Select Secondary Package Style
			CommonFunctions.selectFromDropDownByVisibleText(SecPackageStyle, data[20]);
			//Select Shipping Unit Style
			CommonFunctions.selectFromDropDownByVisibleText(ShipUnitStyle, data[21]);
			//Select Secondary Package Sold As
			CommonFunctions.selectFromDropDownByVisibleText(SecPackageSoldAs, data[22]);
			//Select Shipping Unit Sold As
			CommonFunctions.selectFromDropDownByVisibleText(ShipUnitSoldAs, data[23]);
			//Select Secondary Package Pieces
			CommonFunctions.enterTextInTextbox(SecPckgPieces, data[24]);
			//Select Shipping Unit Package Pieces 
			CommonFunctions.enterTextInTextbox(ShipUnitPckgPics, data[25]);
			//Select Secondary Package Depth IN  
			CommonFunctions.enterTextInTextbox(SecPckgDepthIN, data[26]);
			//Select Shipping Unit Package Depth IN 
			CommonFunctions.enterTextInTextbox(ShipUnitPckgDepthIN, data[27]);
			//Select Secondary Package Width IN
			CommonFunctions.enterTextInTextbox(SecPckgWidthIN, data[28]);
			//Select Shipping Unit Package Width IN
			CommonFunctions.enterTextInTextbox(ShipUnitPckgWidthIN, data[29]);
			//Select Secondary Package Height IN
			CommonFunctions.enterTextInTextbox(SecPckgHeightIN, data[30]);
			//Select Shipping Unit Package Height IN
			CommonFunctions.enterTextInTextbox(ShipUnitPckgHeightIN, data[31]);
			

			
			} 
		   
			catch(Exception e){
				fail=true;
				log.error("Exception in fillDocumentmandat()", e);
			}
		}
	
	
	public static Boolean readGeneralAttribute(String[] data) throws Exception{
		try{
		
		if(data[4].equalsIgnoreCase("Yes")){		
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
				
			
			//Click on Request Sent RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			if(driver.findElements(Product.labelGeneralAttri).size() != 0){
				//Check that General attribute table name in application
				String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
				//Verify that General attribute table name is correct
				Assert.assertEquals(GALabel, " General Attributes:");
				log.info("General Attributes label is Present");
			}else if(driver.findElements(Product.labelGeneralAttri).size() == 0){
				
				log.error("General Attributes label is Absent");
				fail=true;
			}
									
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
						
				//Click on RFQ tab
				if(driver.findElements(rfqTab).size()==0){
				log.info("User is not able to see RFQ tab");				
				}
					
				else if(!(driver.findElements(rfqTab).size()==0) ){
				log.info("User is able to see RFQ tab");
				fail=true;
				
				}
				
			}			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readGeneralAttribute()", e);
			return false;
		}
	}
	
	
	public static Boolean readCostSheetTemplateList(String[] data) throws Exception{
		try{
		
		if(data[4].equalsIgnoreCase("Yes")){		
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
							
			//Click on Request Sent RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			if(driver.findElements(costSheetTemplateList).size() != 0){
				//Check that General attribute table name in application
				String costSheetTemplate= driver.findElement(costSheetTemplateList).getText();
				//Verify that General attribute table name is correct
				Assert.assertEquals(costSheetTemplate, "Cost Sheet Template List");
				log.info("Cost Sheet Template List table is Present");
			}else if(driver.findElements(costSheetTemplateList).size() == 0){
				
				log.error("Cost Sheet Template List table is Absent");
				fail=true;
			}
									
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
						
				//Click on RFQ tab
				if(driver.findElements(rfqTab).size()==0){
				log.info("User is not able to see RFQ tab");				
				}
					
				else if(!(driver.findElements(rfqTab).size()==0) ){
				log.info("User is able to see RFQ tab");
				fail=true;
				
				}
				
			}			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readCostSheetTemplateList()", e);
			return false;
		}
	}
	
	public static Boolean updateCostSheetTemplateList(String[] data) throws Exception{
		try{
			
		//Browse to RFQ tab	
		navigateToRFQ(data);
			
		//wait for visibility of RFQ result
		CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
							
		//Click on Request Sent RFQ
		searchandClickforRequriedRFQ(rfqRSName);
			
		//Wait for image close button to display
		CommonFunctions.waitForVisibilityOfElement(imgClose);
		
		if(data[4].equalsIgnoreCase("Yes")){		
						
			//Click on cost sheet template
			CommonFunctions.clickButtonOrLink(costSheetNameTemplate, "template cost sheet");
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
			//Click on Cost Sheet Action
			CommonFunctions.selectFromDropDownByVisibleText(costsheetAction,"Cost sheet Action");
			
			//Click on Cost Sheet Update
			CommonFunctions.clickButtonOrLink(costsheetUpdate, "Cost Sheet Update");
			
			//Wait for visibility of update page
			CommonFunctions.waitForVisibilityOfElement(costsheetUpdatepage);
			
			//Identify the Wave in edit page
			String waveeditpage = driver.findElement(csWaveEditpage).getText();
	
			//Select the wave value
			if(waveeditpage.equals(data[14])){
				
			CommonFunctions.selectFromDropDownByIndex(csWaveEditpage, 33);
			waveDate=true;
				
						}
			else{
				CommonFunctions.selectFromDropDownByIndex(csWaveEditpage, 14);

				waveDate=false;
				
						}			
			//Click on Done
			CommonFunctions.clickButtonOrLink(save, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the value of Wave in detail page
			String wavedetail =driver.findElement(waveDetailPage).getText();
			
			//Comment in detail page
			log.info("Wave in detail page:" + wavedetail);

					
			//Verify that the RFQ status changed at the edit page and in detail page are same
			if(varData){
				Assert.assertEquals(data[33], wavedetail);
			}
			else{
				Assert.assertEquals(data[14], wavedetail);
			}
			
			log.info("Wave in detail page is: " + wavedetail);
					
			}

			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Check that Update action is visible or not
				Select select = new Select(driver.findElement(rfqAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
				
				//Verify that Update option is visible or not
				Assert.assertFalse(bVal);
				log.info("Does user has update access on selected RFQ " + bVal);				
			
				
				}
						
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in updateCostSheetTemplateList()", e);
			return false;
		}
	}
	
	public static Boolean readRFQLineItemAttributes(String[] data) throws Exception{
		try{
		
		if(data[4].equalsIgnoreCase("Yes")){		
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
							
			//Click on Request Sent RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			System.out.println(driver.findElement(rfqLineItemAttribute).getText());
			
			if(driver.findElements(rfqLineItemAttribute).size() != 0){
				//Check that General attribute table name in application
				String rfqLineItem=driver.findElement(rfqLineItemAttribute).getText();
				//Verify that General attribute table name is correct
				Assert.assertEquals(rfqLineItem, " RFQ Line Item Attributes:");
				log.info("RFQ line Item attribute is Present");
			}else if(driver.findElements(rfqLineItemAttribute).size() == 0){			
				log.error("RFQ Line Item Attributes table is Absent");
				fail=true;
			}									
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
						
				//Click on RFQ tab
				if(driver.findElements(rfqTab).size()==0){
				log.info("User is not able to see RFQ tab");				
				}
					
				else if(!(driver.findElements(rfqTab).size()==0) ){
				log.info("User is able to see RFQ tab");
				fail=true;
				
				}
				
			}			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readRFQLineItemAttributes()", e);
			return false;
		}
	}
	
	public static Boolean readVendors(String[] data) throws Exception{
		try{
		
		if(data[4].equalsIgnoreCase("Yes")){		
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
						
			//Click on Request Sent RFQ Actions Menu
			CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='rfqResults']//following::a[contains(text(),'Name')]//following::a[contains(text(),'"+rfqRSName+"')]//preceding::td[1]/a[contains(text(),'Actions')]"), "Actions Menu");
			
			//Wait for visibility of View action
			CommonFunctions.waitForVisibilityOfElement(actionView);
			
			//Click on Actions>View
			CommonFunctions.clickButtonOrLink(actionView, "Action View");
			
			//Wait for RFQ Identification table			
			CommonFunctions.waitForVisibilityOfElement(rfqnamedetail);
			
			if(driver.findElements(vendors).size() != 0){
				//Check that General attribute table name in application
				String vendorLabel=driver.findElement(vendors).getText();
				//Verify that General attribute table name is correct
				Assert.assertEquals(vendorLabel, "Vendors");
				log.info("Vendors table is Present");
			}else if(driver.findElements(vendors).size() == 0){				
				log.error("Vendor table is Absent");
				fail=true;
			}
						
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
						
				//Click on RFQ tab
				if(driver.findElements(rfqTab).size()==0){
				log.info("User is not able to see RFQ tab");				
				}
					
				else if(!(driver.findElements(rfqTab).size()==0) ){
				log.info("User is able to see RFQ tab");
				fail=true;
				
				}
				
			}			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readVendors()", e);
			return false;
		}
	}
	public static Boolean readAssociatedDocument(String[] data) throws Exception{
		try{
		
		if(data[4].equalsIgnoreCase("Yes")){		
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
						
			//Click on Request Sent RFQ Actions Menu
			CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='rfqResults']//following::a[contains(text(),'Name')]//following::a[contains(text(),'"+rfqRSName+"')]//preceding::td[1]/a[contains(text(),'Actions')]"), "Actions Menu");
			
			//Wait for visibility of View action
			CommonFunctions.waitForVisibilityOfElement(actionView);
			
			//Click on Actions>View
			CommonFunctions.clickButtonOrLink(actionView, "Action View");
			
			//Wait for RFQ Identification table			
			CommonFunctions.waitForVisibilityOfElement(rfqnamedetail);
			
			if(driver.findElements(associatedDocument).size() != 0){
				//Check that General attribute table name in application
				String associatedDocumentbeforeName=driver.findElement(associatedDocument).getText();
				
				//trim the name
				String associatedDocumentName =associatedDocumentbeforeName.trim();
				
				//Verify that General attribute table name is correct
				Assert.assertEquals(associatedDocumentName, "Associated Documents:");
				log.info("Associated Documents table is Present");
			}else if(driver.findElements(associatedDocument).size() == 0){				
				log.error("Associated Documents table is Absent");
				fail=true;
			}
						
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
						
				//Click on RFQ tab
				if(driver.findElements(rfqTab).size()==0){
				log.info("User is not able to see RFQ tab");				
				}
					
				else if(!(driver.findElements(rfqTab).size()==0) ){
				log.info("User is able to see RFQ tab");
				fail=true;
				
				}
				
			}			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readAssociatedDocument()", e);
			return false;
		}
	}
	
	
	
	public static Boolean readSystemInformation(String[] data) throws Exception{
		try{
		
		if(data[4].equalsIgnoreCase("Yes")){		
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
						
			//Click on Request Sent RFQ Actions Menu
			CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='rfqResults']//following::a[contains(text(),'Name')]//following::a[contains(text(),'"+rfqRSName+"')]//preceding::td[1]/a[contains(text(),'Actions')]"), "Actions Menu");
			
			//Wait for visibility of View action
			CommonFunctions.waitForVisibilityOfElement(actionView);
			
			//Click on Actions>View
			CommonFunctions.clickButtonOrLink(actionView, "Action View");
			
			//Wait for RFQ Identification table			
			CommonFunctions.waitForVisibilityOfElement(rfqnamedetail);
			
			if(driver.findElements(systemInformation).size() != 0){
				//Check that General attribute table name in application
				String systemInformationtbefore=driver.findElement(systemInformation).getText();
				
				//trim the name
				String systemInformationtName =systemInformationtbefore.trim();
				
				//Verify that General attribute table name is correct
				Assert.assertEquals(systemInformationtName, "System Information:");
				log.info("System Information table is Present");

			}else if(driver.findElements(systemInformation).size() == 0){				
				log.error("System Information table is Absent");
				fail=true;
			}
						
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
						
				//Click on RFQ tab
				if(driver.findElements(rfqTab).size()==0){
				log.info("User is not able to see RFQ tab");				
				}
					
				else if(!(driver.findElements(rfqTab).size()==0) ){
				log.info("User is able to see RFQ tab");
				fail=true;
				
				}
				
			}			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readSystemInformation()", e);
			return false;
		}
	}
	
	
	
	public static Boolean readProducts(String[] data) throws Exception{
		try{
		
		if(data[4].equalsIgnoreCase("Yes")){		
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
						
			//Click on Request Sent RFQ Actions Menu
			CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='rfqResults']//following::a[contains(text(),'Name')]//following::a[contains(text(),'"+rfqRSName+"')]//preceding::td[1]/a[contains(text(),'Actions')]"), "Actions Menu");
			
			//Wait for visibility of View action
			CommonFunctions.waitForVisibilityOfElement(actionView);
			
			//Click on Actions>View
			CommonFunctions.clickButtonOrLink(actionView, "Action View");
			
			//Wait for RFQ Identification table			
			CommonFunctions.waitForVisibilityOfElement(rfqnamedetail);
			
			if(driver.findElements(products).size() != 0){
				//Check that Product table name in application
				String productLabel=driver.findElement(products).getText();
				//Verify that Products table name is correct
				Assert.assertEquals(productLabel, "Products");
				log.info("Products table is Present");
			}else if(driver.findElements(products).size() == 0){				
				log.error("Products table is Absent");
				fail=true;
			}
						
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
						
				//Click on RFQ tab
				if(driver.findElements(rfqTab).size()==0){
				log.info("User is not able to see RFQ tab");				
				}
					
				else if(!(driver.findElements(rfqTab).size()==0) ){
				log.info("User is able to see RFQ tab");
				fail=true;
				
				}
				
			}			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readProducts()", e);
			return false;
		}
	}
	
	public static Boolean readRFQ(String[] data) throws Exception{
		try{
		
		if(data[4].equalsIgnoreCase("Yes")){		
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
				
			
			//Click on Request Sent RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the name of RFQ status in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ status at detail page is: " + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Request Sent");
			
			log.info("User is able to see RFQ which is of status:" + rfqstatusdetailpage);

			//close the open RFQ
			closeTheOpenRFQ();
						
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[8]);
						
				//Click on RFQ tab
				if(driver.findElements(rfqTab).size()==0){
				log.info("User is not able to see RFQ tab");				
				}
					
				else if(!(driver.findElements(rfqTab).size()==0) ){
				log.info("User is able to see RFQ tab");
				fail=true;
				
				}
				
			}			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readRFQ()", e);
			return false;
		}
	}
	
	public static Boolean readDraftRFQ(String[] data) throws Exception{
		try{
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
		if(data[4].equalsIgnoreCase("Yes")){		
							
			//Click on Request Sent RFQ
			searchandClickforRequriedRFQ(rfqDraftName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the name of RFQ status in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ status at detail page is:" + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Draft");
			
			log.info("User is able to see Draft RFQ:" + rfqstatusdetailpage);

			//close the open RFQ
			closeTheOpenRFQ();			
			
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//search draft RFQ
			searchRFQ=searchRFQ(rfqDraftName);
				
			log.info("Does user has access to see Draft RFQ: " + searchRFQ);			
													
			}
			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readDraftRFQ()", e);
			return false;
		}
	}
	
	
	public static Boolean readRSRFQ(String[] data) throws Exception{
		try{
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
		if(data[4].equalsIgnoreCase("Yes")){		
							
			//Click on Request Sent RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the name of RFQ status in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ status at detail page is:" + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Request Sent");
			
			log.info("User is able to see RFQ which is of status:" + rfqstatusdetailpage);

			//close the open RFQ
			closeTheOpenRFQ();			
			
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//search draft RFQ
			searchRFQ=searchRFQ(rfqRSName);
				
			log.info("Does user has access to see Request Sent RFQ: " + searchRFQ);			
													
			}
			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readRSRFQ()", e);
			return false;
		}
	}
	
	public static Boolean readQuote(String[] data) throws Exception{
		try{
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
		if(data[4].equalsIgnoreCase("Yes")){		
							
			//Click on Request Sent RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the name of RFQ status in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ status at detail page is:" + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Request Sent");
			
			//Wait for Quote to display
			CommonFunctions.waitForVisibilityOfElement(quote);
			
			//Verify that Cost sheet list table exist in Quote
			String costsheetlisttable = driver.findElement(costsheetList).getText();
			Assert.assertEquals(costsheetlisttable, "Cost Sheet List");									
			
			log.info("User is able to see RFQ which  has Quote associated");		
			
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//search draft RFQ
			searchRFQ=searchRFQ(rfqRSName);
				
			log.info("Does user has access to see Request Sent RFQ: " + searchRFQ);			
													
			}
			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readRSRFQ()", e);
			return false;
		}
	}
	
	
	public static Boolean readVendorComments(String[] data) throws Exception{
		try{
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
		if(data[4].equalsIgnoreCase("Yes")){		
							
			//Click on Request Sent RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Wait for Quote to display
			CommonFunctions.waitForVisibilityOfElement(quote);
			
			//Get the name of RFQ status in detail page
			String vendorcommentsbefore =driver.findElement(vendorComments).getText();
			
			String vendorcommentsdetailpage = vendorcommentsbefore.trim();
			
			log.info("Vendor Comment table at detail page displays:" + vendorcommentsdetailpage);
			
			//Verify that the Vendor Comment displays at details page
			Assert.assertEquals(vendorcommentsdetailpage, "Vendor Comments:");							
			
			log.info("User is able to see Vendor Comment table");		
			
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//search draft RFQ
			searchRFQ=searchRFQ(rfqRSName);
				
			log.info("Does user has access to see Request Sent RFQ: " + searchRFQ);			
													
			}
			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readVendorComments()", e);
			return false;
		}
	}
	
	public static Boolean readCompleteRFQ(String[] data) throws Exception{
		try{
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
		if(data[4].equalsIgnoreCase("Yes")){		
							
			//Click on Cancelled RFQ
			searchandClickforRequriedRFQ(rfqCompleteName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the name of RFQ status in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ status at detail page is:" + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Complete");
			
			log.info("User is able to see RFQ which is of status:" + rfqstatusdetailpage);

			//close the open RFQ
			closeTheOpenRFQ();			
			
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//search draft RFQ
			searchRFQ=searchRFQ(rfqCompleteName);
				
			log.info("Does user has access to see Complete RFQ: " + searchRFQ);			
													
			}
			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readCompleteRFQ()", e);
			return false;
		}
	}
	
	public static Boolean readCancelledRFQ(String[] data) throws Exception{
		try{
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
		if(data[4].equalsIgnoreCase("Yes")){		
							
			//Click on Cancelled RFQ
			searchandClickforRequriedRFQ(rfqCancelledName);
			
			//Wait for image close button to display
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the name of RFQ status in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ status at detail page is:" + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Cancelled");
			
			log.info("User is able to see RFQ which is of status:" + rfqstatusdetailpage);

			//close the open RFQ
			closeTheOpenRFQ();			
			
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//search draft RFQ
			searchRFQ=searchRFQ(rfqCancelledName);
				
			log.info("Does user has access to see Request Sent RFQ: " + searchRFQ);			
													
			}
			
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in readCancelledRFQ()", e);
			return false;
		}
	}
		
	
	public static void createNewRFQ(String[] data){
		try{
		//Browse to RFQ tab
		navigateToRFQ(data);
		
		//Select Funskool Source
		Select selectsource= new Select(driver.findElement(selectSource));
		selectsource.selectByVisibleText(data[9]);
		CommonFunctions.handleAlertPopUp();
		
		//Click on Actions Menu
		CommonFunctions.clickButtonOrLink(productAction, "Click on Product Action Menu");
		
		//Click on Actions>Create RFQ
		CommonFunctions.clickButtonOrLink(createRFQ, "Click on add Sourcing Configuration Option");
		
		//Wait for Select Vendor Heading
		CommonFunctions.waitForVisibilityOfElement(selectVendorHeading);
		
		//Click on Select
		CommonFunctions.clickButtonOrLink(selectVendorSelect, "click on Select");
		
		//Wait for RFQ ID to display
		CommonFunctions.waitForVisibilityOfElement(rfqID);
		
	   if(data[3].equalsIgnoreCase("createRFQ")){
		
			//Select Colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayInput, 1);
			
			//Select Wave
			CommonFunctions.selectFromDropDownByIndex(waveInput, 10);
			}	   	   
	   
	   else if (data[3].equalsIgnoreCase("createDraftRFQ")){
		   
			//Select Colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayInput, 2);
			
			//Select Wave
			CommonFunctions.selectFromDropDownByIndex(waveInput, 11);
		   
	   }
	   	   
	   else if (data[3].equalsIgnoreCase("createRequestSentRFQ")){
		   
			//Select Colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayInput, 3);
			
			//Select Wave
			CommonFunctions.selectFromDropDownByIndex(waveInput, 12);
		   
	   }
	   
	   else if (data[3].equalsIgnoreCase("createCancelledRFQ")){
		   
			//Select Colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayInput, 4);
			
			//Select Wave
			CommonFunctions.selectFromDropDownByIndex(waveInput, 13);
		   
	   }
	   
	   else if (data[3].equalsIgnoreCase("createCompleteRFQ")){
		   
			//Select Colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayInput, 5);
			
			//Select Wave
			CommonFunctions.selectFromDropDownByIndex(waveInput, 14);
		   
	   }

	   else {
		//Select Colorway
		CommonFunctions.selectFromDropDownByIndex(colorwayInput, 1);
		
		//Select Wave
		CommonFunctions.selectFromDropDownByIndex(waveInput, 15);
	   }
	   
	   
		//Select Request Date
		CommonFunctions.enterTextInTextbox(requestDate, data[10]);
		
		//Select Request Response Cut off Date
		CommonFunctions.enterTextInTextbox(requestResponseCutoffDate, data[11]);
		
		//Enter comment
		CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[16]);
		
		//Click on Create button
		CommonFunctions.clickButtonOrLink(create, "Click on Create Button");
		
		//Wait till Update RFQ page is not coming
		CommonFunctions.waitForVisibilityOfElement(updateRFQ);
		
		//Click on expand button
		CommonFunctions.clickButtonOrLink(expandButton, "Click on Expand button");
		
		//Wait for Cost Sheet list heading to display
		CommonFunctions.waitForVisibilityOfElement(costsheetList);
		
		//Click on Action menu
		CommonFunctions.clickButtonOrLink(costingActions, "Click on Costing Actions Menu");
		
		//Click on Create Cost Sheet Template
		CommonFunctions.clickButtonOrLink(createCostSheetTemplate, "Click on Create Cost Sheet Template");
		
		//Get the Window Handler of parent Window
		String parentWindow= driver.getWindowHandle();
		System.out.println("The ID of parent Window is: " + parentWindow);
		
		//Wait for visibility of create Product CostSheet Heading
		CommonFunctions.waitForVisibilityOfElement(createProductCostSheetHeading);
		
		//Get the number of pop up Windows open
		Set<String> handles =driver.getWindowHandles();
		
		for (String handle1: driver.getWindowHandles()){
		
			System.out.println(handle1);
			driver.switchTo().window(handle1);				
		}
		
		//Switch to default content
		driver.switchTo().defaultContent();
		
		//Switch to Content Frame
		driver.switchTo().frame("contentframe");
		
		//Select the Type
		CommonFunctions.selectFromDropDownByVisibleText(cstype, data[12]);
		
		//Select colorway
		CommonFunctions.selectFromDropDownByIndex(csTempColorways, 1);
		
		// click lnkAdd
		CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");
		
		//Input Name
		CommonFunctions.enterTextInTextbox(csname, data[13]);
		
		//Select wave
		CommonFunctions.selectFromDropDownByVisibleText(csWave, data[14]);
		
		//Select Quote Currency
		CommonFunctions.selectFromDropDownByVisibleText(csQuoteCurrency, data[15]);
		//Click on Save
		CommonFunctions.clickButtonOrLink(csSave, "btn", "Save");
		
		//Wait
		Thread.sleep(1000);
		
		//Close window
		CommonFunctions.clickButtonOrLink(closeBtn, "btn", "Close");
		
		//Wait
		Thread.sleep(1000);
		
		//Switch to Parent Window
		driver.switchTo().window(parentWindow);
					
		//Switch to default content
		driver.switchTo().defaultContent();
		
		//Switch to Content Frame
		driver.switchTo().frame("contentframe");
		
		//Click on Approximate Volume
		CommonFunctions.clickButtonOrLink(approxVolume, "Approximat Volume");
		
		//Enter value in Approximate Volume
		CommonFunctions.enterTextInTextbox(approxVolumeInput, data[18]);
		
		}
		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in createNewRFQ()", e);
		}
	
	}
	
	public static String createRFQ(String[] data){
		try{
		
			if(data[4].equalsIgnoreCase("Yes")){
							
			createNewRFQ(data);
			
			//Get the name of RFQ
			rfqCreateName = driver.findElement(rfqName).getText();
			
			//Name of the RFQ:
			log.info("The name of the RFQ got created is:" + rfqCreateName);
			
			//Click on save
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Done");	
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//Browse to RFQ tab
			navigateToRFQ(data);
				
			//Select Funskool Source
			Select selectsource= new Select(driver.findElement(selectSource));
			selectsource.selectByVisibleText(data[9]);
			CommonFunctions.handleAlertPopUp();
		
			//Check that In Work BOM is visible or not
			Select select = new Select(driver.findElement(productAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that In Work BOM is visible or not
			Assert.assertFalse(bVal);
			log.info("Create RFQ action menu present: " + bVal);				
			}
			
		}
		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in createRFQ()", e);
		}
		return rfqCreateName;				
	}
	
	public static String createDraftRFQ(String[] data){
		try{
		
			if(data[4].equalsIgnoreCase("Yes")){
							
			createNewRFQ(data);
			
			//Get the name of RFQ
			rfqDraftName = driver.findElement(rfqName).getText();
			
			//Name of the RFQ:
			log.info("The name of the RFQ got created is:" + rfqDraftName);
			
			//Click on save
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Done");	
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//Browse to RFQ tab
			navigateToRFQ(data);
				
			//Select Funskool Source
			Select selectsource= new Select(driver.findElement(selectSource));
			selectsource.selectByVisibleText(data[9]);
			CommonFunctions.handleAlertPopUp();
		
			//Check that In Work BOM is visible or not
			Select select = new Select(driver.findElement(productAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that In Work BOM is visible or not
			Assert.assertFalse(bVal);
			log.info("Create RFQ action menu present: " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in createDraftRFQ()", e);
		}
		return rfqDraftName;				
	}

	public static String createRequestSentRFQ(String[] data){
		try{
		
			if(data[4].equalsIgnoreCase("Yes")){
							
			createNewRFQ(data);
			
			//Get the name of RFQ
			rfqRSName = driver.findElement(rfqName).getText();
			
			//Name of the RFQ:
			log.info("The name of the RFQ got created is:" + rfqRSName);
			
			//Click on save
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done");	
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
			
			//Close the open cost sheet
			RFQ.closeTheOpenRFQ();
			
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);						
			
			//Wait for close tab
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(submitReqToVendor, "Click on Submit Request to Vendor");
			
			//Wait
			Thread.sleep(2000);
			
			//Get the Window Handler of parent Window
			String parentWindow= driver.getWindowHandle();
			System.out.println("The ID of parent Window is: " + parentWindow);
					
			//Get the number of pop up Windows open
			Set<String> handles =driver.getWindowHandles();
			
			for (String handle1: driver.getWindowHandles()){
			
				System.out.println(handle1);
				driver.switchTo().window(handle1);				
			}
			
			
			//Switch to Parent Window
			driver.switchTo().window(parentWindow);
			
			//Switch to default content
			driver.switchTo().defaultContent();
			
			//Switch to Content Frame
			driver.switchTo().frame("contentframe");
		
			//Wait
			Thread.sleep(1000);
			
			//Wait for image close button to display
			CommonFunctions.waitForElementTobeClickable(imgClose);
			
			//Click on Refresh button
			CommonFunctions.clickButtonOrLink(rfqRefreshbtn, "Click on Refresh button");
			
			//Wait for image close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			if(driver.findElements(quoteCostSheetList).size()==0)							
			{
				//Click on rfqtab
				CommonFunctions.clickButtonOrLink(rfqTab, "Click on RFQ Tab");
				
				//wait for 1 sec
				Thread.sleep(1000);			
				
				//Wait
				CommonFunctions.waitForVisibilityOfElement(imgClose);

				
				}
			
			else if(CommonFunctions.isElementPresent(quoteCostSheetList) == true){
				log.info("RFQ Quote got created");				
			}
			
			//Wait for image close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the status of RFQ in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ status at detail page is: " + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Request Sent");
			
			log.info("RFQ state is updated to: " + rfqstatusdetailpage);
				
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//Browse to RFQ tab
			navigateToRFQ(data);
				
			//Select Funskool Source
			Select selectsource= new Select(driver.findElement(selectSource));
			selectsource.selectByVisibleText(data[9]);
			CommonFunctions.handleAlertPopUp();
		
			//Check that In Work BOM is visible or not
			Select select = new Select(driver.findElement(productAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that In Work BOM is visible or not
			Assert.assertFalse(bVal);
			log.info("Create RFQ action menu present: " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in createRequestSentRFQ()", e);
		}
		return rfqRSName;				
	}
	
	
	public static String createCancelledRFQ(String[] data){
		try{
		
			if(data[4].equalsIgnoreCase("Yes")){
							
			createNewRFQ(data);
			
			//Get the name of RFQ
			rfqCancelledName = driver.findElement(rfqName).getText();
			
			//Name of the RFQ:
			log.info("The name of the RFQ got created is:" + rfqCancelledName);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");			
					
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
			
			//wait
			Thread.sleep(1000);
			
			//Click on RFQ Tab
			CommonFunctions.clickButtonOrLink(rfqTab, "RFQ tab");		
			
			//Close the open cost sheet
			RFQ.closeTheOpenRFQ();
			
			if(driver.findElements(rfqResult).size()==0){
			
				//Close the open cost sheet
				RFQ.closeTheOpenRFQ();				
			}						
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqCancelledName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			String rfqNamecaptured = driver.findElement(rfqnamedetail).getText();

			
			if(rfqNamecaptured.equalsIgnoreCase(rfqCancelledName)){
			
				//Click on Action Menu
				CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
				
			}
			
			else {
				
				//Close the open cost sheet
				RFQ.closeTheOpenRFQ();					
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqCancelledName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);		
			
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");

			
			}

			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Select Cancelled status from drop down
			CommonFunctions.selectFromDropDownByVisibleText(rfqstatusedit, "Cancelled");
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the name of RFQ status in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ Status at detail page is: " + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Cancelled");
			
			log.info("RFQ state is updated to: " + rfqstatusdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//Browse to RFQ tab
			navigateToRFQ(data);
				
			//Select Funskool Source
			Select selectsource= new Select(driver.findElement(selectSource));
			selectsource.selectByVisibleText(data[9]);
			CommonFunctions.handleAlertPopUp();
		
			//Check that In Work BOM is visible or not
			Select select = new Select(driver.findElement(productAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that In Work BOM is visible or not
			Assert.assertFalse(bVal);
			log.info("Create RFQ action menu present: " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in createCancelledRFQ()", e);
		}
		return rfqCancelledName;				
	}
	
	
	public static String createCompleteRFQ(String[] data){
		try{
		
			if(data[4].equalsIgnoreCase("Yes")){
							
			createNewRFQ(data);
			
			//Get the name of RFQ
			rfqCompleteName = driver.findElement(rfqName).getText();
			
			//Name of the RFQ:
			log.info("The name of the RFQ got created is:" + rfqCompleteName);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
			
			//Close the open cost sheet
			RFQ.closeTheOpenRFQ();
			
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqCompleteName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Select Cancelled status from drop down
			CommonFunctions.selectFromDropDownByVisibleText(rfqstatusedit, "Complete");
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the name of RFQ status in detail page
			String rfqstatusdetailpage =driver.findElement(rfqstatusdetail).getText();
			
			log.info("RFQ Status at detail page is: " + rfqstatusdetailpage);
			
			//Verify that the RFQ status changed at the edit page and in detail page are same
			Assert.assertEquals(rfqstatusdetailpage, "Complete");
			
			log.info("RFQ state is updated to: " + rfqstatusdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//Browse to RFQ tab
			navigateToRFQ(data);
				
			//Select Funskool Source
			Select selectsource= new Select(driver.findElement(selectSource));
			selectsource.selectByVisibleText(data[9]);
			CommonFunctions.handleAlertPopUp();
		
			//Check that In Work BOM is visible or not
			Select select = new Select(driver.findElement(productAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that In Work BOM is visible or not
			Assert.assertFalse(bVal);
			log.info("Create RFQ action menu present: " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in createCompleteRFQ()", e);
		}
		return rfqCompleteName;				
	}
	
	public static void updateRFQ(String[] data){
		try{
			
			navigateToRFQ(data);
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
	
		
			if(data[4].equalsIgnoreCase("Yes")){
							
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Identify the RR Cut off date attribute value
			String rfqcomment = driver.findElement(rfqCommenttextarea).getText();
	
			//Select request response date
			if(rfqcomment.equals(data[16])){
				
				//click and clear the existing date
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Select Request Response Cut off Date
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[17]);
				varData=true;
				
						}
			else{
				
				//click and clear the existing date
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Select Request Response Cut off Date
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[16]);
				varData=false;
				
						}			
			//Identify the RR Cut off date attribute value
			String rfqcommenteditpage = driver.findElement(rfqCommenttextarea).getText();
			
			//Comment in detail page
			log.info("Comment in edit page:" + rfqcommenteditpage);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the RR cut of date in detail page
			String rfqcommentdetailpage =driver.findElement(rfqCommenttextareadp).getText();
			
			//Comment in detail page
			log.info("Comment in detail page:" + rfqcommentdetailpage);

					
			//Verify that the RFQ status changed at the edit page and in detail page are same
			if(varData){
				Assert.assertEquals(data[17], rfqcommentdetailpage);
			}
			else{
				Assert.assertEquals(data[16], rfqcommentdetailpage);
			}
			
			log.info("Comment in detail page is: " + rfqcommentdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
										
			//Check that In Work BOM is visible or not
			Select select = new Select(driver.findElement(rfqAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that In Work BOM is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected RFQ " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateRFQ()", e);
		}				
	}
	
	public static void updateVendors(String[] data){
		try{
			
			navigateToRFQ(data);
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
	
		
			if(data[4].equalsIgnoreCase("Yes")){
							
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Click on Actions Menu on Vendor table
			CommonFunctions.clickButtonOrLink(removeVendorAction, "Remove Vendor");
			
			//Click ok on pop up menu
			CommonFunctions.handleAlertPopUp();
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(cancelUpdatebtn, "btn", "Click on Cancel button");
			
			Thread.sleep(1000);
			
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
			//Wait for visibility of RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
				
			//User has access to update Vendors table
			log.info("User has access to update Vendor table");
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
										
			//Check that In Work BOM is visible or not
			Select select = new Select(driver.findElement(rfqAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that In Work BOM is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected RFQ " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateVendors()", e);
		}				
	}
	
	

	
	public static void updateGeneralAttribute(String[] data){
		try{
			
			navigateToRFQ(data);
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
	
		
			if(data[4].equalsIgnoreCase("Yes")){
							
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Identify the RR Cut off date attribute value
			String rfqcomment = driver.findElement(rfqCommenttextarea).getText();
	
			//Select request response date
			if(rfqcomment.equals(data[16])){
				
				//click and clear the existing date
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Select Request Response Cut off Date
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[17]);
				varData=true;
				
						}
			else{
				
				//click and clear the existing date
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Select Request Response Cut off Date
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[16]);
				varData=false;
				
						}			
			//Identify the RR Cut off date attribute value
			String rfqcommenteditpage = driver.findElement(rfqCommenttextarea).getText();
			
			//Comment in detail page
			log.info("Comment in edit page:" + rfqcommenteditpage);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the RR cut of date in detail page
			String rfqcommentdetailpage =driver.findElement(rfqCommenttextareadp).getText();
			
			//Comment in detail page
			log.info("Comment in detail page:" + rfqcommentdetailpage);

					
			//Verify that the RFQ status changed at the edit page and in detail page are same
			if(varData){
				Assert.assertEquals(data[17], rfqcommentdetailpage);
			}
			else{
				Assert.assertEquals(data[16], rfqcommentdetailpage);
			}
			
			log.info("General Attribute Comment in detail page updated to: " + rfqcommentdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
										
			//Check that Update action is visible or not
			Select select = new Select(driver.findElement(rfqAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that Update option is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected RFQ " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateGeneralAttribute()", e);
		}				
	}
	
	
	public static void updateRFQLineItemAttributes(String[] data){
		try{
			
			navigateToRFQ(data);
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
	
		
			if(data[4].equalsIgnoreCase("Yes")){
							
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Identify the Approximate Volume attribute value
			String rfqapproxVolume = driver.findElement(approxVolume).getText();
	
			//Enter Approximate Volume attribute value
			if(rfqapproxVolume.equals(data[18])){
				
				//click and clear the existing Approximate Volume
				driver.findElement(approxVolume).click();
				driver.findElement(approxVolumeInput).clear();
				
				//Enter Approximate Volume attribute value
				CommonFunctions.enterTextInTextbox(approxVolumeInput, data[19]);
				approxV=true;
				
						}
			else{
				
				//click and clear the existing date
				driver.findElement(approxVolume).click();
				driver.findElement(approxVolumeInput).clear();
				
				//Enter value in Approximate Volume
				CommonFunctions.enterTextInTextbox(approxVolumeInput, data[18]);
				approxV=false;
				
						}			
			//Identify the Approximate Volume attribute value
			String rfqapproxVolumeeditPage = driver.findElement(approxVolumeInput).getText();
			
			//Approximate Volume in detail page
			log.info("RFQLineItem in edit page:" + rfqapproxVolumeeditPage);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the approx Volume in detail page
			String rfqLineItemdetailpage =driver.findElement(approxVolumedetailpage).getText();
			
			//Approximate Volume in detail page is
			log.info("Approximate Volume in detail page:" + rfqLineItemdetailpage);

					
			//Verify that the Approximate Volume changed at the edit page and in detail page are same
			if(approxV){
				Assert.assertEquals(data[19], rfqLineItemdetailpage);
			}
			else{
				Assert.assertEquals(data[18], rfqLineItemdetailpage);
			}
			
			log.info("Approximate Volume in detail page updated to: " + rfqLineItemdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
										
			//Check that Update action is visible or not
			Select select = new Select(driver.findElement(rfqAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that Update option is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected RFQ " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateRFQLineItemAttributes()", e);
		}				
	}
		
	public static void updateProducts(String[] data){
		try{
			
			navigateToRFQ(data);
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
	
		
			if(data[4].equalsIgnoreCase("Yes")){
							
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Identify the Approximate Volume attribute value
			String rfqapproxVolume = driver.findElement(approxVolume).getText();
	
			//Enter Approximate Volume attribute value
			if(rfqapproxVolume.equals(data[18])){
				
				//click and clear the existing Approximate Volume
				driver.findElement(approxVolume).click();
				driver.findElement(approxVolumeInput).clear();
				
				//Enter Approximate Volume attribute value
				CommonFunctions.enterTextInTextbox(approxVolumeInput, data[19]);
				approxVProduct=true;
				
						}
			else{
				
				//click and clear the existing Approximate Volume
				driver.findElement(approxVolume).click();
				driver.findElement(approxVolumeInput).clear();
				
				//Enter Approximate Volume
				CommonFunctions.enterTextInTextbox(approxVolumeInput, data[18]);
				approxVProduct=false;
				
						}			
			//Identify the Approximate Volume attribute value
			String rfqapproxVolumeeditPage = driver.findElement(approxVolumeInput).getText();
			
			//Approximate Volume in detail page
			log.info("Approximate Volume in edit page:" + rfqapproxVolumeeditPage);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the approximate volume in detail page
			String rfqLineItemdetailpage =driver.findElement(approxVolumedetailpage).getText();
			
			//Approximate Volume in detail page
			log.info("Approximate volumen in detail page:" + rfqLineItemdetailpage);

					
			//Verify that the Approximate Volume changed at the edit page and in detail page are same
			if(approxVProduct){
				Assert.assertEquals(data[19], rfqLineItemdetailpage);
			}
			else{
				Assert.assertEquals(data[18], rfqLineItemdetailpage);
			}
			
			log.info("Approximate Volume in detail page updated to: " + rfqLineItemdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
										
			//Check that Update action is visible or not
			Select select = new Select(driver.findElement(rfqAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that Update option is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected RFQ " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateProducts()", e);
		}				
	}
	
	public static boolean updateSystemInformation(String[] data){
		try{
			
			navigateToRFQ(data);
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
		
			if(data[4].equalsIgnoreCase("Yes")){
									
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
				
			//Enter Approximate Volume attribute value
			if(driver.findElements(systemInformation).size()==0){
			log.info("User is not able to see System Information table");
			//Click on Cancel button
			CommonFunctions.clickButtonOrLink(cancelbtn, "Cancel button");
			
			//Wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);			
			
				return false ;
				}
			else if (!(driver.findElements(systemInformation).size()==0)){
				log.info("User is able to see System Information table");
				
				//Click on Cancel button
				CommonFunctions.clickButtonOrLink(cancelbtn, "Cancel button");
				
				//Wait for visibility of RFQ
				CommonFunctions.waitForVisibilityOfElement(imgClose);
				
				return true ;
				
				}							
			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
				//Verify that Update button is disable or not
				Select select = new Select(driver.findElement(rfqAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
				
				System.out.println("Does Update button visible:" + bVal);
				
			if(bVal==true){
				
				CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
				
				//Wait for Update page to display
				CommonFunctions.waitForVisibilityOfElement(updateRFQ);
					
				//Enter Approximate Volume attribute value
				if(driver.findElements(systemInformation).size()==0){
				log.info("User is able to see System Information table");
				//Click on Cancel button
				CommonFunctions.clickButtonOrLink(cancelbtn, "Cancel button");
				
				//Wait for visibility of RFQ
				CommonFunctions.waitForVisibilityOfElement(imgClose);
				return true ;
					
					}
				else if (!(driver.findElements(systemInformation).size()==0)){
					log.info("User does not able to see System Information table");
					
					//Click on Cancel button
					CommonFunctions.clickButtonOrLink(cancelbtn, "Cancel button");
					
					//Wait for visibility of RFQ
					CommonFunctions.waitForVisibilityOfElement(imgClose);
					
					return false ;
					
					}				
			}
			
			else if(bVal==false){													
			//Verify that Update option is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected RFQ " + bVal);				
			}
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateSystemInformation()", e);
			
		}
		return true;			
	}
	
	public static void updateVendorComments(String[] data){
		try{
			
			navigateToRFQ(data);
			
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
			
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);
			
			//Wait
			Thread.sleep(1000);
			
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
	
		
			if(data[4].equalsIgnoreCase("Yes")){
			
			//Wait for Quote to display
			CommonFunctions.waitForVisibilityOfElement(quote);
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(quoteActions, "Click on quote Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(quoteUpdate, "Click on Update button");
			
			//Wait for Comment from Vendor table to appear
			CommonFunctions.waitForVisibilityOfElement(commentFromVendorInput);
			
			//clear the Comment From Vendors table
			driver.findElement(commentFromVendorInput).clear();
			
			//Enter comment
			CommonFunctions.enterTextInTextbox(commentFromVendorInput, data[32]);
						
			//Click on Done
			CommonFunctions.clickButtonOrLink(save, "btn", "Click on Done button");
			
			//wait for visibility of RFQ close button
			CommonFunctions.waitForVisibilityOfElement(commentFromVendor);
			
			//Get the  value of Comment from Vendor in detail page
			String commentfromVendorsdetailpage =driver.findElement(commentFromVendordp).getText();
			
			//Comment from Vendors in detail page
			log.info("Comment from Vendors in detail page is:" + commentfromVendorsdetailpage);

					
			//Verify that the Comment from Vendor entered at the edit page and in detail page are same
			Assert.assertEquals(data[32], commentfromVendorsdetailpage);
			
			log.info("User is able to add comment in Comment from Vendor table:" + commentfromVendorsdetailpage);
						
			}
			
			else if(data[4].equalsIgnoreCase("No")){
										
			//Check that Update action is visible or not
			Select select = new Select(driver.findElement(quoteActions));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that Update option is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected Quote " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateVendorComments()", e);
		}				
	}
	
	public static void updateQuote(String[] data){
		try{
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
						
			//Click on Request Sent RFQ Actions Menu
			CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='rfqResults']//following::a[contains(text(),'Name')]//following::a[contains(text(),'"+rfqRSName+"')]//preceding::td[1]/a[contains(text(),'Actions')]"), "Actions Menu");
			
			//Wait for visibility of View action
			CommonFunctions.waitForVisibilityOfElement(actionView);
			
			//Click on Actions>View
			CommonFunctions.clickButtonOrLink(actionView, "Action View");
			
			//Wait for RFQ Identification table			
			CommonFunctions.waitForVisibilityOfElement(rfqnamedetail);
					
			if(data[4].equalsIgnoreCase("Yes")){
			
			//Wait for visibility of associate document
			CommonFunctions.waitForVisibilityOfElement(associatedDocument);
			
			//Click on Actions
			CommonFunctions.clickButtonOrLink(associatedDocumentActions, "Actions");
			
			//Click on Actions>Create New Reference document
			CommonFunctions.clickButtonOrLink(createNewRefDocument, "Create New Reference Document");
			
			//Wait for visibility of create New document
			CommonFunctions.waitForVisibilityOfElement(createNewDocument);
			
			//Click on Packaging>Assortment/Solid Document
			CommonFunctions.clickButtonOrLink(psAssortmentType , "Assortment/Solid");
			
			//Wait for create new document 
			CommonFunctions.waitForVisibilityOfElement(createNewDocument);
			
			//fill data
			fillDocumentmandat(data);
			
			//Create Document
			CommonFunctions.clickButtonOrLink(createDocument, "btn", "Create Assortment/Solid Documents");
			
			//Wait for Associated Document table
			CommonFunctions.waitForVisibilityOfElement(associatedDocument);
			
			log.info("New document got created");
			
			}
			
			else if(data[4].equalsIgnoreCase("No")){
										
			//Check that Update action is visible or not
			Select select = new Select(driver.findElement(rfqAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that Update option is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected RFQ " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updatequote()", e);
		}				
	}
	
	public static void updateAssociatedDocuments(String[] data){
		try{
			
			//Browse to RFQ tab	
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
						
			//Click on Request Sent RFQ Actions Menu
			CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='rfqResults']//following::a[contains(text(),'Name')]//following::a[contains(text(),'"+rfqRSName+"')]//preceding::td[1]/a[contains(text(),'Actions')]"), "Actions Menu");
			
			//Wait for visibility of View action
			CommonFunctions.waitForVisibilityOfElement(actionView);
			
			//Click on Actions>View
			CommonFunctions.clickButtonOrLink(actionView, "Action View");
			
			//Wait for RFQ Identification table			
			CommonFunctions.waitForVisibilityOfElement(rfqnamedetail);
					
			if(data[4].equalsIgnoreCase("Yes")){
			
			//Wait for visibility of associate document
			CommonFunctions.waitForVisibilityOfElement(associatedDocument);
			
			//Click on Actions
			CommonFunctions.clickButtonOrLink(associatedDocumentActions, "Actions");
			
			//Click on Actions>Create New Reference document
			CommonFunctions.clickButtonOrLink(createNewRefDocument, "Create New Reference Document");
			
			//Wait for visibility of create New document
			CommonFunctions.waitForVisibilityOfElement(createNewDocument);
			
			//Click on Packaging>Assortment/Solid Document
			CommonFunctions.clickButtonOrLink(psAssortmentType , "Assortment/Solid");
			
			//Wait for create new document 
			CommonFunctions.waitForVisibilityOfElement(createNewDocument);
			
			//fill data
			fillDocumentmandat(data);
			
			//Create Document
			CommonFunctions.clickButtonOrLink(createDocument, "btn", "Create Assortment/Solid Documents");
			
			//Wait for Associated Document table
			CommonFunctions.waitForVisibilityOfElement(associatedDocument);
			
			log.info("New document got created");
			
			}
			
			else if(data[4].equalsIgnoreCase("No")){
										
			//Check that Update action is visible or not
			Select select = new Select(driver.findElement(rfqAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that Update option is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has update access on selected RFQ " + bVal);				
			}
			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateAssociatedDocuments()", e);
		}				
	}
	
	public static void updateDraftRFQ(String[] data) throws Exception{		
		try{
			
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
	
		
			if(data[4].equalsIgnoreCase("Yes")){
				
				//search the required RFQ
				searchandClickforRequriedRFQ(rfqDraftName);
				
				//Wait
				Thread.sleep(1000);
				
				//Wait for close button
				CommonFunctions.waitForVisibilityOfElement(imgClose);
							
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Identify the comment attribute value
			String rfqcomment = driver.findElement(rfqCommenttextarea).getText();
	
			//Enter value in comment
			if(rfqcomment.equals(data[16])){
				
				//click and clear the existing comment
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[17]);
				varData=true;
				
						}
			else{
				
				//click and clear the existing comment
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[16]);
				varData=false;
				
						}			
			//Identify the comment attribute value in detail page
			String rfqcommenteditpage = driver.findElement(rfqCommenttextarea).getText();
			
			//Comment in detail page
			log.info("Comment in edit page:" + rfqcommenteditpage);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the RR cut of date in detail page
			String rfqcommentdetailpage =driver.findElement(rfqCommenttextareadp).getText();
			
			//Comment in detail page
			log.info("Comment in detail page:" + rfqcommentdetailpage);

					
			//Verify that the RFQ status changed at the edit page and in detail page are same
			if(varData){
				Assert.assertEquals(data[17], rfqcommentdetailpage);
			}
			else{
				Assert.assertEquals(data[16], rfqcommentdetailpage);
			}
			
			log.info("Comment in detail page is: " + rfqcommentdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
			//Search RFQ
			searchRFQ= searchRFQ(rfqDraftName);
			
			log.info("Does user able to search RFQ:" + searchRFQ);
			
			if(searchRFQ == true){
				
			
				//search the required RFQ
				searchandClickforRequriedRFQ(rfqDraftName);
				
				//Wait
				Thread.sleep(1000);
				
				//Wait for close button
				CommonFunctions.waitForVisibilityOfElement(imgClose);
				
				//Check that In Work BOM is visible or not
				Select select = new Select(driver.findElement(rfqAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
				
				//Verify that In Work BOM is visible or not
				Assert.assertFalse(bVal);
				log.info("Does user has update access on selected RFQ " + bVal);		
			}
			
			else if(searchRFQ == false){
			
			log.info("Does user is able to see Draft RFQ:" + searchRFQ);
				
			}

			}
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateDraftRFQ()", e);
		}				
	}
	
	
	
	public static void updateRSRFQ(String[] data) throws Exception{
			
		try{
			
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
	
		
			if(data[4].equalsIgnoreCase("Yes")){
				
				//search the required RFQ
				searchandClickforRequriedRFQ(rfqRSName);
				
				//Wait
				Thread.sleep(1000);
				
				//Wait for close button
				CommonFunctions.waitForVisibilityOfElement(imgClose);
							
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Identify the comment attribute value
			String rfqcomment = driver.findElement(rfqCommenttextarea).getText();
	
			//Enter value in comment
			if(rfqcomment.equals(data[16])){
				
				//click and clear the existing comment
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[17]);
				varData=true;
				
						}
			else{
				
				//click and clear the existing comment
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[16]);
				varData=false;
				
						}			
			//Identify the comment attribute value in detail page
			String rfqcommenteditpage = driver.findElement(rfqCommenttextarea).getText();
			
			//Comment in detail page
			log.info("Comment in edit page:" + rfqcommenteditpage);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the RR cut of date in detail page
			String rfqcommentdetailpage =driver.findElement(rfqCommenttextareadp).getText();
			
			//Comment in detail page
			log.info("Comment in detail page:" + rfqcommentdetailpage);

					
			//Verify that the RFQ status changed at the edit page and in detail page are same
			if(varData){
				Assert.assertEquals(data[17], rfqcommentdetailpage);
			}
			else{
				Assert.assertEquals(data[16], rfqcommentdetailpage);
			}
			
			log.info("Comment in detail page is: " + rfqcommentdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
			//Search RFQ
			searchRFQ= searchRFQ(rfqRSName);
			
			log.info("Does user able to search RFQ:" + searchRFQ);
			
			if(searchRFQ == true){
				
			
				//search the required RFQ
				searchandClickforRequriedRFQ(rfqRSName);
				
				//Wait
				Thread.sleep(1000);
				
				//Wait for close button
				CommonFunctions.waitForVisibilityOfElement(imgClose);
				
				//Check that In Work BOM is visible or not
				Select select = new Select(driver.findElement(rfqAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
				
				//Verify that In Work BOM is visible or not
				Assert.assertFalse(bVal);
				log.info("Does user has update access on selected RFQ " + bVal);		
			}
			
			else if(searchRFQ == false){
			
			log.info("Does user is able to see Request Sent RFQ:" + searchRFQ);
				
			}

			}
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateRSRFQ()", e);
		}				
	}
	

	public static void updateCancelledRFQ(String[] data) throws Exception{
		
		
		try{
			
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
				
			if(data[4].equalsIgnoreCase("Yes")){
				
				//search the required RFQ
				searchandClickforRequriedRFQ(rfqCancelledName);
				
				//Wait
				Thread.sleep(1000);
				
				//Wait for close button
				CommonFunctions.waitForVisibilityOfElement(imgClose);
							
		
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Identify the comment attribute value
			String rfqcomment = driver.findElement(rfqCommenttextarea).getText();
	
			//Enter value in comment
			if(rfqcomment.equals(data[16])){
				
				//click and clear the existing comment
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[17]);
				varData=true;
				
						}
			else{
				
				//click and clear the existing comment
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[16]);
				varData=false;
				
						}			
			//Identify the comment attribute value in detail page
			String rfqcommenteditpage = driver.findElement(rfqCommenttextarea).getText();
			
			//Comment in detail page
			log.info("Comment in edit page:" + rfqcommenteditpage);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the RR cut of date in detail page
			String rfqcommentdetailpage =driver.findElement(rfqCommenttextareadp).getText();
			
			//Comment in detail page
			log.info("Comment in detail page:" + rfqcommentdetailpage);

					
			//Verify that the RFQ status changed at the edit page and in detail page are same
			if(varData){
				Assert.assertEquals(data[17], rfqcommentdetailpage);
			}
			else{
				Assert.assertEquals(data[16], rfqcommentdetailpage);
			}
			
			log.info("Comment in detail page is: " + rfqcommentdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
			//Search RFQ
			searchRFQ= searchRFQ(rfqCancelledName);
			
			log.info("Does user able to search RFQ:" + searchRFQ);
			
			if(searchRFQ == true){
				
			
				//search the required RFQ
				searchandClickforRequriedRFQ(rfqCancelledName);
				
				//Wait
				Thread.sleep(1000);
				
				//Wait for close button
				CommonFunctions.waitForVisibilityOfElement(imgClose);
				
				//Check that In Work BOM is visible or not
				Select select = new Select(driver.findElement(rfqAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
				
				//Verify that In Work BOM is visible or not
				Assert.assertFalse(bVal);
				log.info("Does user has update access on selected RFQ " + bVal);		
			}
			
			else if(searchRFQ == false){
			
			log.info("Does user is able to see Cancelled RFQ:" + searchRFQ);
				
			}

			}
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateCancelledRFQ()", e);
		}				
	}
	
	public static void updateCompleteRFQ(String[] data) throws Exception{
				
		try{
			
			navigateToRFQ(data);
			
			//wait for visibility of RFQ result
			CommonFunctions.waitForVisibilityOfElement(rfqResultTab);
			
			//close the open RFQ
			RFQ.closeTheOpenRFQ();
		
			if(data[4].equalsIgnoreCase("Yes")){
				
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqCompleteName);
				
			//Wait
			Thread.sleep(1000);
				
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
															
			//Click on Action Menu
			CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(rfqUpdate, "Click on Update button");
			
			//Wait for Update page to display
			CommonFunctions.waitForVisibilityOfElement(updateRFQ);
			
			//Identify the comment attribute value
			String rfqcomment = driver.findElement(rfqCommenttextarea).getText();
	
			//Enter value in comment
			if(rfqcomment.equals(data[16])){
				
				//click and clear the existing comment
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[17]);
				varData=true;
				
						}
			else{
				
				//click and clear the existing comment
				driver.findElement(rfqCommenttextarea).click();
				driver.findElement(rfqCommenttextarea).clear();
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(rfqCommenttextarea, data[16]);
				varData=false;
				
						}			
			//Identify the comment attribute value in detail page
			String rfqcommenteditpage = driver.findElement(rfqCommenttextarea).getText();
			
			//Comment in detail page
			log.info("Comment in edit page:" + rfqcommenteditpage);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
			
			//wait for visibility of RFQ
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			
			//Get the RR cut of date in detail page
			String rfqcommentdetailpage =driver.findElement(rfqCommenttextareadp).getText();
			
			//Comment in detail page
			log.info("Comment in detail page:" + rfqcommentdetailpage);

					
			//Verify that the RFQ status changed at the edit page and in detail page are same
			if(varData){
				Assert.assertEquals(data[17], rfqcommentdetailpage);
			}
			else{
				Assert.assertEquals(data[16], rfqcommentdetailpage);
			}
			
			log.info("Comment in detail page is: " + rfqcommentdetailpage);
					
			}
			
			else if(data[4].equalsIgnoreCase("No")){
			
			//Search RFQ
			searchRFQ= searchRFQ(rfqCompleteName);
			
			log.info("Does user able to search RFQ:" + searchRFQ);
			
			if(searchRFQ == true){
				
			
				//search the required RFQ
				searchandClickforRequriedRFQ(rfqCompleteName);
				
				//Wait
				Thread.sleep(1000);
				
				//Wait for close button
				CommonFunctions.waitForVisibilityOfElement(imgClose);
				
				//Check that In Work BOM is visible or not
				Select select = new Select(driver.findElement(rfqAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
				
				//Verify that In Work BOM is visible or not
				Assert.assertFalse(bVal);
				log.info("Does user has update access on selected RFQ " + bVal);		
			}
			
			else if(searchRFQ == false){
			
			log.info("Does user is able to see complete RFQ:" + searchRFQ);
				
			}

			}
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in updateCompleteRFQ()", e);
		}				
	}
	
	
	public static void deleteRFQ(String[] data){
		try{
	
			if(data[4].equalsIgnoreCase("Yes")){
							
				createNewRFQ(data);
				
				//Get the name of RFQ
				rfqDeleteName = driver.findElement(rfqName).getText();
				
				//Name of the RFQ:
				log.info("The name of the RFQ got created is:" + rfqDeleteName);
				
				//Click on Done
				CommonFunctions.clickButtonOrLink(btnDone, "btn", "Click on Done button");
				
				//Wait for RFQ tab
				CommonFunctions.waitForVisibilityOfElement(rfqResult);
				
				//search the required RFQ
				searchandClickforRequriedRFQ(rfqDeleteName);
				
				//Wait
				Thread.sleep(1000);
				
				//Wait for close button
				CommonFunctions.waitForVisibilityOfElement(imgClose);
				
				//Click on Action Menu
				CommonFunctions.clickButtonOrLink(rfqAction, "Click on RFQ Action menu");
				
				//Click on Delete button
				CommonFunctions.clickButtonOrLink(rfqDelete, "Click on RFQ Action menu");
				
				//Wait for Delete page header
				CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
				
				//Click ok/yes on confirmation of Deletion of BOM
				driver.switchTo().alert().accept(); 
				CommonFunctions.handleAlertPopUp();
				
				//Wait for visibility of RFQ tab
				CommonFunctions.waitForVisibilityOfElement(rfqResult);
				
				//Search for deleted RFQ
				deletedRFQ = searchRFQ(rfqDeleteName);
				
				//Verify that RFQ got deleted
				Assert.assertFalse(deletedRFQ, "RFQ has been deleted");
				
				log.info("Is user is able to see deleted BOM:" + deletedRFQ);

			}
			
			else if(data[4].equalsIgnoreCase("No")){
				
			//Browse to RFQ tab
			navigateToRFQ(data);
				
			//Wait for RFQ tab
			CommonFunctions.waitForVisibilityOfElement(rfqResult);
				
			//search the required RFQ
			searchandClickforRequriedRFQ(rfqRSName);
				
			//Wait
			Thread.sleep(1000);
				
			//Wait for close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
											
			//Check that In Work BOM is visible or not
			Select select = new Select(driver.findElement(rfqAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[6],options);
			
			//Verify that In Work BOM is visible or not
			Assert.assertFalse(bVal);
			log.info("Does user has Delete access on selected RFQ " + bVal);
			
			}			
		}		
		catch(Exception e){ 
			fail=true;
			log.error("Exception in deleteRFQ()", e);
		}				
	}
		
	public static boolean searchRFQ(String rfqName) throws Exception
	{
		try{
			List<WebElement> col1 = SeleniumDriver.driver.findElements(rfqNameColumn);
			System.out.println(col1.size());
			for(WebElement e: col1){
				System.out.println("e.getText(): "+ e.getText());
				System.out.println("Segment: "+rfqName);
				if (e.getText().trim().equals(rfqName)) {
					return true;
				}
			}
			return false;
		}catch(Exception e){
			fail=true;
			log.error("Exception in SearchRFQ()", e);
			return false;
		}
	}
	
	
	public static boolean closeTheOpenRFQ() throws Exception
	{
		try{
			if(!(driver.findElements(imgClose).size()==0)){
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			// close the open cost sheet
			CommonFunctions.clickButtonOrLink(imgClose,"icon", "Close");
			log.info("RFQ closed");
			}
			else if(driver.findElements(imgClose).size()==0){
				log.info("No cost sheet is open");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in closeTheOpenRFQ", e);
			return false;
		}
	}
	
	

	public static boolean findString (String data1, String regex){
		log.info("Calling Find String");
		boolean flag = false;
		try {
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(data1);
			if (matcher.find())
			{
				flag = true;
				log.info("Start index: " + matcher.start());
				//System.out.print("Start index: " + matcher.start());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error in Handling Findstring  :"+e);
		}
		return flag;
	}


	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSecurityXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteSecurityXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteSecurityXls,"Testcases", Utility.getRowNum(suiteSecurityXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteSecurityXls,"Testcases", Utility.getRowNum(suiteSecurityXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteSecurityXls, this.getClass().getSimpleName());
	}

}
