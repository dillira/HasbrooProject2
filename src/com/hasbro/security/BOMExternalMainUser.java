package com.hasbro.security;
/*
 * Prerequisite :
 * 1. Create Product.Add colorway and extenal source.
 * 2. Make sure source are in approved state 
 */
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


public class BOMExternalMainUser extends TestsuiteBase{

	static int count=-1;
	static boolean fail=false;
	//************CostSheet Variables ***********************************************

	public static By lstsource = By.xpath("//*[@id='sourcingConfigId']");
	public static By lstspecification =  By.xpath("//*[@id='contextSpecId']");	
	public static By lstcostingActions = By.id("costingActions");	
	public static By lstcolorwayGroupOptions = By.id("colorwayGroupOptions");	
	public static By lstQuoteCurrency = By.xpath("//td[contains(text(),'Quote Currency')]//following::select[1]");	 //*[@id='ptc_str_2']
	public static By lstCSStatus = By.xpath("//td[contains(text(),'Cost Sheet Status')]//following::select[1]");	 //*[@id='ptc_str_2']
	public static By btnSave =  By.xpath("//a[text()='Save']");  
	public static By lnkSource =  By.xpath("//a[text()='Sourcing']"); 
	public static By lnkCosting =  By.xpath("//a[text()='Costing']");
	public static By txtCreateCostSheetName =  By.xpath("//*[@id='ptc_str_1']");
	public static By lnkAdd =  By.xpath("//a[text()='Add']");
	public static By lstSeason =  By.id("splId");
	public static By lstCostSheetAction =  By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']");
	public static By tblCostSheetIdentification = By.xpath("//td[contains(text(),'Cost Sheet Identification')]");
	public static By lstWave = By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By lstAstSolidWave = By.xpath("//td[contains(text(),'Ast./Solid Wave')]//following::select[1]");

	public static By lblWave = By.id("hbWave");
	public static By astSolidWaveNew = By.id("hbAstSolidWaveNew");

	public static By tabCostsheet =By.xpath("//a[text()='Cost Sheet List']");
	public static By imgClose = By.xpath("//img[contains(@src,'deleteXsmall.png')]");
	public static By newCostsheetTitle =By.xpath("//td[@class='PAGEHEADINGTITLE' and contains(text(),'Cost Sheet Details')]");
	public static By costAction =By.id("costingActions");
	public static By lblquoteCurrency=By.id("hBCurrency");
	public static By lblComment=By.id("hBComments");
	public static By csHeading=By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]/td");
	public static By selectSource= By.id("sourcingConfigId");
	public static By sourcingLead  = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead  = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");

	public static By csCol =By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]");
	public static By csStatus =By.id("hbStatus");

	public static By vendorCSStatus =By.xpath("//td[contains(text(),'Vendor Cost Sheet Status')]");
	public static By btnCancel =By.xpath("//a[text()='Cancel']");
	public static By btnDone = By.xpath("//a[text()='Done']");
	public static By statusErrorMsg = By.xpath("//td[contains(text(),'Sorry, You do not have the necessary privileges to update the Status field.')]");
	public static By pCSErrorMsg = By.xpath("//td[contains(text(),'Only the Primary Cost Sheet Attribute may be changed at this time.')]");
	public static By ohCostErrorMsg = By.xpath("//td[contains(text(),'Access Denied. Costsheet state is Initial Quote Approved')]");
	public static By typeVendor = By.xpath("//a[text()='Vendor']");
	public static By selectedTab = By.xpath("//li[@class='tabselected']/a[@title!='Cost Sheet List']");
	public static By headBOM = By.xpath("//td[contains(text(),'Bill of Materials:')]");
	public static By closeEditor = By.xpath("//a[text()='Close Editor']");
	public static By errMsgSpace = By.xpath("//div[@id='bomEditorSpace']/table/tbody/tr[2]/td");
	public static By BOMTypeId= By.id("bomTypeId");
	public static By updateDisBtn = By.xpath("//a[text()='Update' and text()='Update']");
	public static By Bomstatusdetailpage=By.id("hbBOMStatus");


	int y=0;
	static String errMsg="Only the Primary Cost Sheet Attribute may be changed at this time. Please click on Cancel to exit.";
	static String overheadCosterrMsg="Access Denied. Costsheet state is Initial Quote Approved" ;
	static String errMsgStatus="Sorry, You do not have the necessary privileges to update the Status field." ;


	static Boolean bPriCS=false;
	String loginVal;
	Boolean flaglogin=false;
	static String csName = null;


	static String strSource;
	static String inWorkStatus;
	static String underReviewStatus;
	static String approvedStatus;
	static String canceledStatus;
	static String statusText;

	static String strBOMInWork;
	static String strBOMUR;
	static String strBOMR;
	static String strBOMRj;
	static String strBOMC;
	static String bomname;

	static Actions action;

	static String valULCSAfterChange;

	@Test(dataProvider="testDataTest")

	public void tcBOM(String[] data) throws Exception{

		try{
			count++;
			System.out.println(CommonProjectFunctions.runmodes[count]);
			if(!CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
				CommonProjectFunctions.skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			CommonProjectFunctions.strTestCaseName = "User:"+data[0] + " for testcase:"+data[3];			
			log.info("Inside Test Case:-> " + CommonProjectFunctions.strTestCaseName + " for Internal retail BOM");				
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			System.out.println("attribute group :" + data[2]);
			System.out.println("verification :" + data[3]);
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					driver.quit();
				}
			}
			if(CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
				if(y==0){
					openBrowser();
					launchApp(data[0],data[1]);	

					y++;
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}
			switch (data[3]) {
			/******************/
			case "CreateInWork":
				log.info("In side :-> " + data[3]);	
				createInWork_bom(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateUnderReview":
				log.info("In side :-> " + data[3]);	
				createUnderReview_bom(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateReleased":
				log.info("In side :-> " + data[3]);	
				createReleased_bom(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateCancelled":
				log.info("In side :-> " + data[3]);	
				createCancelled_bom(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateRejected":
				log.info("In side :-> " + data[3]);	
				createRejected_bom(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateInWorktoUR":
				log.info("In side :-> " + data[3]);	
				updateInWorktoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateInWorktoR":	
				log.info("In side :-> " + data[3]);	
				updateInWorktoR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateInWorktoRj":	
				log.info("In side :-> " + data[3]);	
				updateInWorktoRj(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateInWorktoC":
				log.info("In side :-> " + data[3]);	
				updateInWorktoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateURtoIW":
				log.info("In side :-> " + data[3]);	
				updateURtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateURtoR":
				log.info("In side :-> " + data[3]);	
				updateURtoR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateURtoRj":
				log.info("In side :-> " + data[3]);	
				updateURtoRj(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateURtoC":
				log.info("In side :-> " + data[3]);	
				updateURtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateRjtoIW":
				log.info("In side :-> " + data[3]);	
				updateRjtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateRjtoUR":
				log.info("In side :-> " + data[3]);	
				updateRjtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateRjtoR":
				log.info("In side :-> " + data[3]);	
				updateRjtoR(data);
				log.info("Out side :-> " + data[3]);
				break;	
			case "updateRjtoC":
				log.info("In side :-> " + data[3]);	
				updateRjtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateCtoIW":
				log.info("In side :-> " + data[3]);	
				updateCtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateCtoUR":
				log.info("In side :-> " + data[3]);	
				updateCtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateCtoR":
				log.info("In side :-> " + data[3]);	
				updateCtoR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateCtoRj":
				log.info("In side :-> " + data[3]);	
				updateCtoRj(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateRtoIW":
				log.info("In side :-> " + data[3]);	
				updateRtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateRtoUR":
				log.info("In side :-> " + data[3]);	
				updateRtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateRtoRj":
				log.info("In side :-> " + data[3]);	
				updateRtoRj(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "updateRtoC":
				log.info("In side :-> " + data[3]);	
				updateRtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Throwable t){
			fail=true;
			log.info("****Varification failed or Skipped for**** "+CommonProjectFunctions.strTestCaseName);
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	//InWork to Under Review
	public static Boolean updateInWorktoUR(String[] data) throws Exception{
		try{
			nevigationBOM(data);

			//Select BOM
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
			CommonFunctions.handleAlertPopUp();
			//	}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				verifyStatusDropDown("Under Review",strBOMInWork);
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				if(data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Product\\Vendor"))
				{
					Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
					log.info("Update button is disabled");
				}
				else {
					updateBOMStatus("Under Review");
					verifyErrorMessageStatus();
				}
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				verifyStatusDropDown("Under Review",strBOMInWork);
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				verifyStatusDropDown("Under Review",strBOMInWork);
			}

			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateInWorktoUR()", e);
			return false;
		}
	}
	public static Boolean updateInWorktoR(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			//Select BOM
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
			CommonFunctions.handleAlertPopUp();

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				verifyStatusDropDown("Released",strBOMInWork);
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				if(data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Product\\Vendor"))
				{
					Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
					log.info("Update button is disabled");
				}
				else {
					updateBOMStatus("Released");
					verifyErrorMessageStatus();
				}
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				verifyStatusDropDown("Released",strBOMInWork);

			}
			if(data[4].equalsIgnoreCase("vendor")) {
				verifyStatusDropDown("Released",strBOMInWork);
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateInWorktoUR()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateInWorktoRj(String[] data) throws Exception{
		try{
			nevigationBOM(data);

			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				//verifyStatusDropDown("Rejected",strBOMInWork);
				updateBOMStatus("Rejected");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				if(data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Product\\Vendor"))
				{
					Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
					log.info("Update button is disabled");
				}
				else {
					updateBOMStatus("Rejected");
					verifyErrorMessageStatus();
				}
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				if(data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Product\\Vendor"))
				{
					Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
					log.info("Update button is disabled");
				}
				else {
					updateBOMStatus("Rejected");
					verifyErrorMessageStatus();
				}
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				verifyStatusDropDown("Rejected",strBOMInWork);
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateInWorktoC(String[] data) throws Exception{
		try{
			nevigationBOM(data);

			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
			CommonFunctions.handleAlertPopUp();


			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				verifyStatusDropDown("Canceled",strBOMInWork);
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				if(data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Product\\Vendor"))
				{
					Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
					log.info("Update button is disabled");
				}
				else {
					updateBOMStatus("Canceled");
					verifyErrorMessageStatus();
				}
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				verifyStatusDropDown("Canceled",strBOMInWork);
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}

			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateURtoIW(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMUR);
			CommonFunctions.handleAlertPopUp();

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("In Work");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static boolean updateURtoR(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMUR);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Released");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateURtoRj(String[] data) throws Exception{
		try{
			nevigationBOM(data);

			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMUR);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Rejected");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateURtoC(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMUR);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Canceled");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}


	public static boolean updateRjtoIW(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMRj);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMRj);
				revertStatus("Rejected",data,strBOMRj);
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMRj);
				revertStatus("Rejected",data,strBOMRj);
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMRj);
				revertStatus("Rejected",data,strBOMRj);
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRjtoUR(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMRj);
			CommonFunctions.handleAlertPopUp();

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				verifyStatusDropDown("Under Review",strBOMRj);
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				if(data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Product\\Vendor"))
				{
					Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
					log.info("Update button is disabled");
				}
				else {
					updateBOMStatus("Under Review");
					verifyErrorMessageStatus();
				}
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				verifyStatusDropDown("Under Review",strBOMRj);
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static boolean updateRjtoR(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMRj);
			CommonFunctions.handleAlertPopUp();

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				verifyStatusDropDown("Released",strBOMRj);
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				if(data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Product\\Vendor"))
				{
					Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
					log.info("Update button is disabled");
				}
				else {
					updateBOMStatus("Released");
					verifyErrorMessageStatus();
				}
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				verifyStatusDropDown("Released",strBOMRj);
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static Boolean updateRjtoC(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMRj);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				verifyStatusDropDown("Canceled",strBOMRj);
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				if(data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Product\\Vendor"))
				{
					Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
					log.info("Update button is disabled");
				}
				else {
					updateBOMStatus("Canceled");
					verifyErrorMessageStatus();
				}
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				verifyStatusDropDown("Canceled",strBOMRj);
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateCtoIW(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMC);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("In Work");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}


	public static boolean updateCtoUR(String[] data) throws Exception{
		try{ 
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMC);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Under Review");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				verifyStatusDropDown("Under Review",strBOMC);

			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static boolean updateCtoR(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMC);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Released");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				verifyStatusDropDown("Released",strBOMC);
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static boolean updateCtoRj(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.BOMId);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMC);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Rejected");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				verifyStatusDropDown("Rejected",strBOMC);
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static boolean updateRtoIW(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMR);
			CommonFunctions.handleAlertPopUp();
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("In Work");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}


	public static boolean updateRtoUR(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMR);
			CommonFunctions.handleAlertPopUp();

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Under Review");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}


	public static boolean updateRtoRj(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMR);
			CommonFunctions.handleAlertPopUp();

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Rejected");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static boolean updateRtoC(String[] data) throws Exception{
		try{
			nevigationBOM(data);
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMR);
			CommonFunctions.handleAlertPopUp();

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMR);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				updateBOMStatus("Canceled");
				verifyErrorMessageStatus();
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElement(updateDisBtn).getAttribute("disabled"), "true");
				log.info("Update button is disabled");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static String createInWork_bom(String[] data) throws Exception{
		try{
			//Browse to Material Tab
			navigateToMaterialTab(data);
			//Click on Add New BOM tab
			CommonFunctions.clickButtonOrLink(ExternalBOM.addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(ExternalBOM.bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(ExternalBOM.initializebom,"btn", "Initialize bom");
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(ExternalBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(ExternalBOM.inputCompOrLoca, data[9]);

			//Click on Save and Check in Button
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			//Click on Header Attribute button
			CommonFunctions.waitForVisibilityOfElement(ExternalBOM.headerAttributes);
			//Get the name of the selected BOM
			strBOMInWork=new Select(driver.findElement(ExternalBOM.bomId)).getFirstSelectedOption().getText();
			//Remove the trailing and leading white space
			strBOMInWork=strBOMInWork.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:"+strBOMInWork);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateInWork_bom()", e);

		}
		return strBOMInWork;
	}

	public static String createUnderReview_bom(String[] data) throws Exception{
		try{
			//Browse to Material Tab
			navigateToMaterialTab(data);
			//Switch to default content
			driver.switchTo().defaultContent();

			//Switch to content frame
			driver.switchTo().frame("contentframe");

			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(ExternalBOM.addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(ExternalBOM.bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(ExternalBOM.initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(ExternalBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(ExternalBOM.inputCompOrLoca, data[9]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			//Check the name of the BOM First Selected
			strBOMUR=new Select(driver.findElement(ExternalBOM.bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom is:" + strBOMUR);
			//Remove the leading and trailing whitespace
			strBOMUR=strBOMUR.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom aftre trim:" + strBOMUR);

			//Click on Update button
			CommonFunctions.clickButtonOrLink(ExternalBOM.updateBtn, "btn", "Update btn");
			//Wait for main frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(ExternalBOM.headerAttributesBtn, "btn", "headerAttributesBtn");
			if (data[3].contains("CreateUnderReview")){
				valULCSAfterChange=ExternalBOM.changeStatusUnderReview();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default Frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			log.info("UnderReview status bom is: "+strBOMUR);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateUnderReview_bom()", e);
		}
		return strBOMUR;
	}

	public static String createReleased_bom(String[] data) throws Exception{
		try{
			//Browse to Material Tab
			navigateToMaterialTab(data);

			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(ExternalBOM.addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(ExternalBOM.bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(ExternalBOM.initializebom,"btn", "Initialize bom");
			//Create bom page
			//bomnameReleased = fillCreatebom(data);
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(ExternalBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(ExternalBOM.inputCompOrLoca, data[9]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			//Get the name of first Selected BOM
			strBOMR=new Select(driver.findElement(ExternalBOM.bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom before trim is:"+strBOMR);
			//Remove the Leading and trailing white spaces
			strBOMR=strBOMR.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:"+strBOMR);

			//Click on Update button
			CommonFunctions.clickButtonOrLink(ExternalBOM.updateBtn, "btn", "Update btn");
			//Wait for main frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(ExternalBOM.headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateReleased")){
				valULCSAfterChange=ExternalBOM.changeStatusReleased();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Released status bom is:"+strBOMR);

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateReleased_bom()", e);
		}
		return strBOMR;
	}

	public static String createCancelled_bom(String[] data) throws Exception{
		try{
			//Browse to Material tab
			navigateToMaterialTab(data);

			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(ExternalBOM.addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(ExternalBOM.bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(ExternalBOM.initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(ExternalBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(ExternalBOM.inputCompOrLoca, data[9]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			//Get the name of the first selected BOM
			strBOMC=new Select(driver.findElement(ExternalBOM.bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom is: " + strBOMC );
			//Remove the leading and Trailing space
			strBOMC=strBOMC.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:" + strBOMC );

			//Click on Update button
			CommonFunctions.clickButtonOrLink(ExternalBOM.updateBtn, "btn", "Update btn");
			//Wait for Main frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));

			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(ExternalBOM.headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateCancelled")){
				valULCSAfterChange=ExternalBOM.changeStatusCancelled();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			log.info("Cancelled status bom is: "+strBOMC);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateCancelled_bom()", e);
		}
		return strBOMC;
	}

	public static String createRejected_bom(String[] data) throws Exception{
		try{
			//Browse to Material Tab
			navigateToMaterialTab(data);

			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(ExternalBOM.addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(ExternalBOM.bomtypeid, data[2]);
			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(ExternalBOM.initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(ExternalBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(ExternalBOM.inputCompOrLoca, data[9]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			//Get the name of first selected BOM
			strBOMRj=new Select(driver.findElement(ExternalBOM.bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom is: " + strBOMRj);
			//Trim the name of the BOM
			strBOMRj=strBOMRj.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:" + strBOMRj);

			//Click on Update button
			CommonFunctions.clickButtonOrLink(ExternalBOM.updateBtn, "btn", "Update btn");
			//Wait for the main Frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));

			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(ExternalBOM.headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateRejected")){
				valULCSAfterChange=ExternalBOM.changeStatusRejected();
			}
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();

			//Switch to Default frame
			driver.switchTo().defaultContent();

			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Rejected status bom is: "+strBOMRj);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateRejected_bom()", e);
			//	return "";
		}
		return strBOMRj;
	}

	public static String fillCreatebom(String[] data) throws Exception{
		try{
			//Wait for heading Create BOM
			CommonFunctions.waitForVisibilityOfElement(ExternalBOM.headingCreatebom);
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Vendor")))
			{
				//Select colorway
				CommonFunctions.selectFromDropDownByIndex(ExternalBOM.colorway, 1);
				//Select currency
				CommonFunctions.selectFromDropDownByVisibleText(ExternalBOM.wave, data[11]);
				//Select Currency
				CommonFunctions.selectFromDropDownByVisibleText(ExternalBOM.currency, data[7]);

				//Get the Window Handler of parent Window
				String parentWindow= driver.getWindowHandle();
				System.out.println("The ID of parent Window is: " + parentWindow);

				//Select Factory
				driver.findElement(ExternalBOM.Factory).click();

				//Get the number of pop up Windows open
				Set<String> handles =driver.getWindowHandles();

				for (String handle1: driver.getWindowHandles()){

					System.out.println(handle1);
					driver.switchTo().window(handle1);				
				}

				//Click on Find Factory Search
				driver.findElement(ExternalBOM.factoryFindSearch).click();
				//Click on Choose Factory
				driver.findElement(ExternalBOM.chooseFactory).click();

				//Switch to Parent Window
				driver.switchTo().window(parentWindow);

				//Switch to default content
				driver.switchTo().defaultContent();

				//Switch to Content Frame
				driver.switchTo().frame("contentframe");

				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreatebom()", e);
			return "";
		}
		return bomname;
	}
	public static boolean updateBOMStatus(String state) throws Exception{
		try{
			//Update BOM
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.headerAttributesBtn, "btn", "headerAttributesBtn");

			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMStatus,state);
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			Thread.sleep(3000);
			System.out.println(driver.findElements(InternalBOMSoftG.headerAttributesBtn).size());
			driver.switchTo().frame("contentframe");
			if(driver.findElements(InternalBOMSoftG.headerAttributesBtn).size()==0)
			{
				driver.switchTo().frame("mainFrame");
			}
			else {
				//	do nothing
			}
			//	Assert.assertEquals(driver.findElements(InternalBOMSoftG.headerAttributesBtn).size(),1);
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateBOMStatus()", e);
			return false;
		}
		return true;
	}

	public static boolean verifyErrorMessageStatus() throws Exception{
		try {
			Assert.assertEquals(driver.findElement(statusErrorMsg).getText().trim(),errMsgStatus);
			log.info("****Verified error message as : " +errMsgStatus +"for" + CommonProjectFunctions.strTestCaseName);
			CommonFunctions.clickButtonOrLink(closeEditor, "btn", "Close editor");
			CommonFunctions.handleAlertPopUp();
			Thread.sleep(2000);
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyErrorMessageStatus()", e);
			return false;
		}
	}
	public static boolean verifyBOMStatus(String sStatus,String bomName) throws Exception{
		try {
			CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.headerAttributesBtn);
			//Expand header attribute
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.headerAttributesPlus, "img", "Plus sign");

			String strStatus = driver.findElement(Bomstatusdetailpage).getText();
			Assert.assertEquals(strStatus.trim(), sStatus);
			log.info("Verification: Status verified as: "+ sStatus);
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in verification for : " + sStatus + "for BOM: " +bomName , e);
			return false;
		}
	}

	public static boolean verifyStatusDropDown(String sStatus,String bomName) throws Exception{
		try {
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.headerAttributesBtn, "btn", "headerAttributesBtn");
			Select select = new Select(driver.findElement(InternalBOMSoftG.BOMStatus));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerificationActions(sStatus,options);
			//	dropDownOptionVerification
			Assert.assertFalse(bVal);
			log.info("***Verified: "+sStatus+" is not present in dropdown");
			CommonFunctions.clickButtonOrLink(closeEditor, "btn", "Close Editor");
			CommonFunctions.handleAlertPopUp();
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in verification for : " + sStatus + "for BOM: " +bomName , e);
			return false;
		}
	}


	public static boolean nevigationBOM(String[] data)throws Exception{
		try {
			System.out.println(driver.findElements(InternalBOMSoftG.BOMDetails).size());
			if(driver.findElements(InternalBOMSoftG.BOMDetails).size()== 0) {
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				clickSpecificationTab(data[6],data);
				//Select Source
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.selectSource, data[8]);
				CommonFunctions.handleAlertPopUp();

				InternalBOMSoftG.strSpec=AddSpecification(data);
				CommonFunctions.handleAlertPopUp();
				InternalBOMSoftG.strCW=AddColorway(data);
				CommonProjectFunctions.clickMaterialsTab();
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in nevigationBOM()", e);
			//Some times not able to put URL in broswe due to synchronization,hence TC are failing,to handle it put below statement
			launchApp(prop.getProperty("adminuser"),prop.getProperty("adminpwd"));	
		}
		return true;
	}

	public static String createBOM(String[] data) throws Exception{
		try {
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			//	CommonFunctions.enterTextInTextbox(InternalBOMSoftG.BOMTypeId, data[2]);
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);
			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.initializeBOM,"btn", "Initialize BOM");
			InternalBOMSoftG.BOMname=data[14]+CommonFunctions.getRandomString(4);
			System.out.println("BOM Name entered is: " + InternalBOMSoftG.BOMname);

		}catch(Exception e){
			fail=true;
			log.error("Exception in nevigationBOM()", e);
			return "";
		}
		return InternalBOMSoftG.BOMname;
	}

	public static boolean fillEditBOM(String[] data) throws Exception{
		try {
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			InternalBOMSoftG.action = new Actions(driver);
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.inputCompOrLoca, data[9]);
			//Quanity
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.headerAttributes);

		}catch(Exception e){
			fail=true;
			log.error("Exception in fillEditBOM()", e);
			return false;
		}
		return true;
	}

	/*****************************************************/
	public static boolean revertStatus(String statusBOM,String[] data,String bomName) throws Exception{
		try{
			reLogin();	
			nevigationBOM(data);
			//Select BOM
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, bomName);
			// Update Status
			updateBOMStatus(statusBOM); 
			//Log Out
			CommonProjectFunctions.logOut();
			//Launch App with required login\Pwd
			openBrowser();
			Thread.sleep(3000);
			launchApp(data[0],data[1]);	
			return true;	
		}catch(Exception e){
			fail=true;
			log.error("Exception in revertStatus()", e);
			CommonProjectFunctions.logOut();
			return false;
		}
	}

	public static boolean reLogin() throws Exception{
		try{
			prop=new Properties();
			FileInputStream ip1=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\config.properties");
			prop.load(ip1);
			//Logout
			CommonProjectFunctions.logOut();
			//Login with Admin user : taking admin username\password from property file
			openBrowser();
			Thread.sleep(3000);
			launchApp(prop.getProperty("adminuser"),prop.getProperty("adminpwd"));	
			return true;	
		}catch(Exception e){
			fail=true;
			log.error("Exception in reLoin()", e);
			return false;
		}
	}

	/************************************************************************************/

	public static String AddSource(String[] data) throws Exception{
		try{
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(selectSource));
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource =elementCountSource.size();
			if(countSource>2)
			{
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, data[8]);
				strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			else
			{
				AddSourcingConfiguration(data);
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, data[8]);
				strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddSource()", e);
			return "";
		}
		return strSource;
	}
	public static Boolean AddSourcingConfiguration(String[] data) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Sourcing Configuration");

			//Supplier Selection
			CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
			//CommonFunctions.switchToChildWindow();
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			CommonFunctions.clickButtonOrLink(SourcingConfig.choose, "Supplier selected");
			driver.switchTo().window(parent);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(sourcingLead, 1);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(sourcingHead, 1);
			//click on Create
			CommonFunctions.clickButtonOrLink(SourcingConfig.CreateSourcebtn, "btn", "Create Source");
			return true;
		}catch(Exception e){ 
			//fail=true;
			log.error("Exception in AddSourcingConfiguration()", e);
			return false;
		}
	}

	public static Boolean verifySourceForVendor(String[] data) throws Exception{
		try{
			Select selectSou = new Select(driver.findElement(InternalBOMSoftG.selectSource));
			List<WebElement> options = selectSou.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
			//	dropDownOptionVerification
			Assert.assertFalse(bVal,"Internal Source is not present");
			log.info("Internal Source verified for Vendor user,internal source 'Hasbro Internal' not present.");
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySourceForVendor()", e);
			return false;
		}
		return true;
	}

	public static String AddSpecification(String[] data) throws Exception{
		try{
			//Add Specification
			Select dropDownSpec = new Select(SeleniumDriver.driver.findElement(InternalBOMSoftG.selectSpecification));
			List<WebElement> elementCount = dropDownSpec.getOptions();
			int count =elementCount.size();
			if(count>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			}
			else
			{
				InternalBOMSoftG.strSpec= CommonProjectFunctions.Create_Specifications(data[12],data[13]);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			}
			log.info("Specification is: "+InternalBOMSoftG.strSpec);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			return "";
		}
		return InternalBOMSoftG.strSpec;
	}

	public static String AddColorway(String[] data) throws Exception{
		try{
			//Add colorway
			Select dropDownCW = new Select(SeleniumDriver.driver.findElement(InternalBOMSoftG.colorwayName));
			List<WebElement> elementCountCW = dropDownCW.getOptions();
			int countCW =elementCountCW.size();
			if(countCW>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorwayName, 1);
				InternalBOMSoftG.strCW=new Select(driver.findElement(InternalBOMSoftG.colorwayName)).getFirstSelectedOption().getText();
			}
			else
			{
				InternalBOMSoftG.strCW= InternalBOMSoftG.Create_Colorway(data);
			}
			log.info("Colorway is: "+InternalBOMSoftG.strCW);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddColorway()", e);
			return "";
		}
		return InternalBOMSoftG.strCW;
	}

	public static boolean clickSpecificationTab(String dataYear,String[] data) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			if(CommonFunctions.waitForVisibilityOfElement(Specifications.specificationsTablink))
				CommonFunctions.clickButtonOrLink(Specifications.specificationsTablink, "link", "specifications tab");
			//	if(!data[4].equalsIgnoreCase("vendor")){
			CommonFunctions.clickButtonOrLink(Product.detailPageSeasonDD, "Season dropdown");
			System.out.println(By.xpath("//*[@id='splId']/option[contains(text(),'"+dataYear+"')]"));
			SeleniumDriver.driver.findElement(By.xpath("//*[@id='splId']/option[contains(text(),'"+dataYear+"')]")).click();
			//	}
		}catch(Exception e){
			log.error("Exception in clickSpecificationTab()", e);
			return false;
		}
		return true;
	}
	public static boolean navigateToMaterialTab(String [] data) throws Exception{
		try{
			if(driver.findElements(InternalBOMSoftG.BOMDetails).size()== 0) {
				//Search Product
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				CommonProjectFunctions.clickSpecificationTab(data[6]);
				//Select Source
				Select selectsource= new Select(driver.findElement(selectSource));
				selectsource.selectByVisibleText(data[8]);
				CommonFunctions.handleAlertPopUp();

				//Select Specification
				CommonFunctions.selectFromDropDownByIndex(ExternalBOM.selectSpecification, 1);
				CommonFunctions.handleAlertPopUp();
				changeSpecificationStatus(data);
				//Click on Material tab
				CommonProjectFunctions.clickMaterialsTab();
			}
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in navigateToMaterialTab()", e);
			return false;
		}

	}
	/*
	 * Below function is for verdor user.We have to convert specificaion status to
	 *  'Supplier Released',if we want to see it from vendor user
	 */
	public static boolean changeSpecificationStatus(String [] data) throws Exception{
		try{
			//Select from specification Action
			CommonFunctions.enterTextInTextbox(Specifications.specificationAction,"Update Spec");
			//Update status to supplier released
			CommonFunctions.selectFromDropDownByVisibleText(Specifications.specificationstatusinput, "Supplier Released");
			//Click on Save button
			driver.findElement(Specifications.updatespecsave).click();
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in changeSpecificationStatus()", e);
			return false;
		}

	}
	//*********************************************************************************************	

	@AfterMethod
	public void reporterdataSetResult(){
		if(CommonProjectFunctions.skip)
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			CommonProjectFunctions.isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "PASS");
		CommonProjectFunctions.skip=false;
		fail=false;
	}

	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSecurityXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		CommonProjectFunctions.runmodes=Utility.getDataSetRunmodeTest(suiteSecurityXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(CommonProjectFunctions.isTestPass){
			Utility.dataSetResult(suiteSecurityXls,"Testcases", Utility.getRowNum(suiteSecurityXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteSecurityXls,"Testcases", Utility.getRowNum(suiteSecurityXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteSecurityXls, this.getClass().getSimpleName());
	}

}

