package com.hasbro.security;
/*
 * Prerequisite :
 * 1. Create Product.Add colorway and extenal source.
 * 2. Make sure source are in approved state 
 */
import java.io.FileInputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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


public class BOMMaterialMainUser extends TestsuiteBase{

	static int count=-1;
	static boolean fail=false;
	static WebDriverWait wait=null;
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
	public static By lstCostSheetAction =  By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']");
	public static By tblCostSheetIdentification = By.xpath("//td[contains(text(),'Cost Sheet Identification')]");
	public static By lstWave = By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By lstAstSolidWave = By.xpath("//td[contains(text(),'Ast./Solid Wave')]//following::select[1]");

	public static By lblWave = By.id("hbWave");
	public static By astSolidWaveNew = By.id("hbAstSolidWaveNew");

	//	public static By tabCostsheet =By.xpath("//a[text()='Cost Sheet List']");
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
	public static By bomMstatus=By.id("hbMaterialBOMStatus");
	public static By lstSeason =  By.id("splId");
	public static By season = By.xpath("//a[contains(text(),'Season:')]");
	public static By txtName = By.xpath("//td[contains(text(),'*Name')]//following::input[1]");

	public static By btnSearch =  By.id("SearchButton1");
	public static By createBtn = By.xpath("//a[text()='Create']");
	public static By materialDet = By.xpath("//td[contains(text(),' Material Details')]");
	public static By txtDescription= By.xpath("//td[contains(text(),'Description')]//following::textarea[1]");
	public static By seasonLnk= By.xpath("//a[contains(text(),'Season:')]");
	public static By txtMName = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By cpADD = By.xpath("//a[contains(@id,'addComposite')]"); 
	
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
	static String materialName;
	static String matName;
	static String URBOM;
	static String strCW;
	static String materialInwork;
	static String materialUR;
	static String materialR;
	static String materialC;
	static String materialRj;

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
				CreateInWork_MBOM(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateUnderReview":
				log.info("In side :-> " + data[3]);	
				CreateUnderReview_MBOM(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateReleased":
				log.info("In side :-> " + data[3]);	
				CreateReleased_MBOM(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateCancelled":
				log.info("In side :-> " + data[3]);	
				CreateCancelled_MBOM(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateRejected":
				log.info("In side :-> " + data[3]);	
				CreateRejected_MBOM(data);
				log.info("Out side :-> " + data[3]);
				break;
				/*************Update********************/
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
			navigateMaterialBOM(data,materialInwork);
			//if(!data[4].contains("vendor")) {
			//Select BOM
			////CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
			//CommonFunctions.handleAlertPopUp();
			//	}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialInwork);
			//if(!data[4].contains("vendor")) {
			//Select BOM
		//	//CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
		//	CommonFunctions.handleAlertPopUp();
			//}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}

			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialInwork);
			//if(!data[4].contains("vendor")) {
			////CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
			//CommonFunctions.handleAlertPopUp();
			//	}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialInwork);
			//if(!data[4].contains("vendor")) {
			//CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
			//CommonFunctions.handleAlertPopUp();
			//}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMInWork);
				//Revert back status
				updateBOMStatus("In Work");
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialUR);
			//if(!data[4].contains("vendor")) {
			//CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMUR);
			//CommonFunctions.handleAlertPopUp();
			//}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialUR);
			//if(!data[4].contains("vendor")) {
			//CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMUR);
			//CommonFunctions.handleAlertPopUp();
			//	}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialUR);
			//if(!data[4].contains("vendor")) {
			//CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMUR);
			//CommonFunctions.handleAlertPopUp();
			//}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialUR);
			//if(!data[4].contains("vendor")) {
			//CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMUR);
			//CommonFunctions.handleAlertPopUp();
			//}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMUR);
				//Revert back status
				updateBOMStatus("Under Review");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialRj);
			//if(!data[4].contains("vendor")) {
			//CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMRj);
			//CommonFunctions.handleAlertPopUp();
			//}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialRj);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMRj);
		//	CommonFunctions.handleAlertPopUp();
			//}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}

			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialRj);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMRj);
		//	CommonFunctions.handleAlertPopUp();
			//}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialRj);
			//if(!data[4].contains("vendor")) {
			//CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMRj);
			//CommonFunctions.handleAlertPopUp();
			//	}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMRj);
				//Revert back status
				updateBOMStatus("Rejected");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialC);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMC);
		//	CommonFunctions.handleAlertPopUp();
			//}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}

			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialC);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMC);
		//	CommonFunctions.handleAlertPopUp();
			//}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialC);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMC);
		//	CommonFunctions.handleAlertPopUp();
			//	}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Released");
				verifyBOMStatus("Released",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialC);

			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.BOMId);
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMC);
		//	CommonFunctions.handleAlertPopUp();
			//	}

			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMC);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialR);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMR);
		//	CommonFunctions.handleAlertPopUp();
			//}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("In Work");
				verifyBOMStatus("In Work",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialR);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMR);
		//	CommonFunctions.handleAlertPopUp();
			//}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Under Review");
				verifyBOMStatus("Under Review",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialR);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMR);
		//	CommonFunctions.handleAlertPopUp();
			//}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Rejected");
				verifyBOMStatus("Rejected",strBOMR);
				//Revert back status
				updateBOMStatus("Released");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
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
			navigateMaterialBOM(data,materialR);
			//if(!data[4].contains("vendor")) {
		//	CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMR);
		//	CommonFunctions.handleAlertPopUp();
			//}
			if(data[4].equalsIgnoreCase("Admin")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMR);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("LibAdmin")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("MatAdm")) {
				updateBOMStatus("Canceled");
				verifyBOMStatus("Canceled",strBOMR);
				//Revert back status
				updateBOMStatus("Canceled");
			}
			if(data[4].equalsIgnoreCase("engineer")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engLead")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("Costing")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("engwithCost")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			if(data[4].equalsIgnoreCase("vendor")) {
				Assert.assertEquals(driver.findElements(InternalBOMSoftG.updateBtn).size(),0);
				log.info("Update button is not displayed");
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for user: " + data[0], e);
			return false;
		}
	}

	public static boolean navigateUptoCreateDiffrntTypesOfMaterial(String[] data) throws Exception
	{
		try{
			//Added refresh code below as to create another material BOM require this as we need to close library + sign
			if(driver.findElements(Material.materialLink).size()==0) {
			driver.navigate().refresh();
			}
			//	if(driver.findElements(materialDet).size()== 0) {	
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
			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialHeadning));
			//Click on new
			CommonFunctions.clickButtonOrLink(Material.newLink, "link", "New Link");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.chooseaType));
			//Click on color type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+ data[4]+ "')and @class='LABEL']"), "link", "Material Type");
			System.out.println(By.xpath("//a[contains(text(),'"+data[4]+"')and @class='LABEL']"));
			//	}
		}catch(Exception e){
			fail=true;
			log.error("Exception in navigateUptoCreateDiffrntTypesOfMaterial()", e);
		}
		return true;
	}

	public static boolean navigateMaterialBOM(String[] data,String mName) throws Exception
	{
		try{
			//Added refresh code below as to create another material BOM require this as we need to close library + sign
			//	driver.navigate().refresh();
		//	if(driver.findElements(materialDet).size()== 0) {	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			if(driver.findElements(Material.materialLink).size()==0) {
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
			}
			//Click on Color link
			driver.findElement(Material.materialLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//wait = new WebDriverWait(driver, 10);
			//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialHeadning));
			CommonFunctions.waitForElementTobeClickable(txtMName);
			//Enter material Name
			CommonFunctions.enterTextInTextbox(txtMName, mName);
			//Click on search
			CommonFunctions.clickButtonOrLink(btnSearch, "btn", "Search");
			//Click on material tab
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			//	}
		}catch(Exception e){
			fail=true;
			log.error("Exception in navigateUptoCreateDiffrntTypesOfMaterial()", e);
		}
		return true;
	}

	static String fillMaterailMandatory(String[] data) throws Exception {
		try{
			matName = data[8] + CommonFunctions.getRandomString(5);
			CommonFunctions.enterTextInTextbox(Material.materialName,matName);		
			CommonFunctions.enterTextInTextbox(Material.parentRefNumber, data[9]);
			driver.findElement(Material.cBlack).click();
			log.info("cBlack is Selected!!!");
			driver.findElement(Material.crADD).click();
			log.info("crADD is Selected!!!");
			// Send Material Description Details						
			CommonFunctions.enterTextInTextbox(Material.description, data[22]);
			// Send Comments for Creating Materials Details
			CommonFunctions.enterTextInTextbox(Material.comments, data[22]);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in fillMaterailMandatory()", e);
		}
		return matName;
	}

	private static void fillMatFabric(String[] data) throws Exception {
		try{
			//Select Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.construction, data[12],"construction");
			//Select Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.constructionType, data[10],"constructionType");
			//Select Secondary Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.secondaryConstruction, data[11],"secondaryConstruction");
			//Select Content From drop down			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.content, data[13],"content");
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(Material.percent, data[14]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(cpADD, "btn", "cpADD");
			//Select Content From drop down		
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.content, data[15],"content");
			// Send Content Percent values			
			CommonFunctions.enterTextInTextbox(Material.percent, data[16]);
			//Click On Add button	
			CommonFunctions.clickButtonOrLink(cpADD, "btn", "cpADD");
			// Send Weight
			CommonFunctions.enterTextInTextbox(Material.weightGYD, data[17]);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in fillMatFabric()", e);
		}
	}

	public static Boolean createMeterialBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			MaterialBOM.addedSupp = AddMeterialSource(data);
			MaterialBOM.addedColor= AddColor(data);
			//Click on meterial		
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(MaterialBOM.initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"In Work", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(MaterialBOM.createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");			
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(MaterialBOM.compOrLoca)).doubleClick().perform();			
			CommonFunctions.enterTextInTextbox(MaterialBOM.inputCompOrLoca,"Mat description");
			//Quanity
			action.moveToElement(driver.findElement(MaterialBOM.quantity)).doubleClick().perform();			
			CommonFunctions.enterTextInTextbox(MaterialBOM.inputquantity,"5");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");			

		}catch(Exception e){
			fail=true;
			log.error("Exception in createMeterialBOM()", e);
			return false;
		}
		return true;
	}

	public static String AddMeterialSource(String[] data) throws Exception{
		try{
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(MaterialBOM.meterialSource));
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource = elementCountSource.size();
			//log.info("Number of supplier: " + countSource);
			if(countSource>2)
			{
				CommonFunctions.selectFromDropDownByIndex(MaterialBOM.meterialSource, 1);
				strSource=new Select(driver.findElement(MaterialBOM.meterialSource)).getFirstSelectedOption().getText();
			}
			else
			{
				AddSupplier(data);
				CommonFunctions.selectFromDropDownByIndex(MaterialBOM.meterialSource, 1);
				strSource=new Select(driver.findElement(MaterialBOM.meterialSource)).getFirstSelectedOption().getText();
			}
			log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddMeterialSource()", e);
			return "";
		}
		return strSource;
	}
	
	public static Boolean AddSupplier(String[] data) throws Exception{
		try{			
			//Click on sourcing tab
			CommonFunctions.clickButtonOrLink(MaterialBOM.sourcingTab, "Tab", "Sourcing");
			///CommonFunctions.selectFromDropDownByVisibleText(meterialSupplierAction, "Add Suppliers");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(MaterialBOM.meterialSupplierAction, "Add Suppliers","meterialSupplierAction");
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			//Click on show All
			CommonFunctions.clickButtonOrLink(MaterialBOM.supplierShowAll, "link", "Show All");
			//Select check box
			CommonFunctions.selectCheckbox(By.xpath("//a[text()='"+data[26]+"']/preceding::input[1]"));
			//Click on Select
			CommonFunctions.clickButtonOrLink(MaterialBOM.selectBtn, "btn", "Select");
			driver.switchTo().window(parent);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in AddSupplier()", e);
			return false;
		}
		return true;
	}
	
	public static String AddColor(String[] data) throws Exception{
		try{
			//Click on Meterial tab
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			//	CommonFunctions.clickButtonOrLink(colorLink, "tab", "Color");
			//Add colorway
			Select dropDownCW = new Select(SeleniumDriver.driver.findElement(MaterialBOM.meterialColor));
			List<WebElement> elementCountCW = dropDownCW.getOptions();
			int countCW = elementCountCW.size();
			//	int count =SeleniumDriver.driver.findElements(selectSpecification).size();
			if(countCW>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(MaterialBOM.meterialColor, 1);
				strCW=new Select(driver.findElement(MaterialBOM.meterialColor)).getFirstSelectedOption().getText();
			}
			else
			{
				strCW=Add_Color(data);
			}
			log.info("Selected Color is: "+strCW);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddColor()", e);
			return "";
		}
		return strCW;
	}
	public static String Add_Color(String[] data) throws Exception{
		try{
			//Click on Color
			CommonFunctions.clickButtonOrLink(MaterialBOM.colorLink, "link", "Color");			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(MaterialBOM.selectMeterialColor, "Add Multiple Colors","selectMeterialColor");
			CommonFunctions.waitForElementTobeClickable(btnSearch);
			//Click on Search
			CommonFunctions.clickButtonOrLink(btnSearch, "Btn", "Search");
			for(int i=3;i<=4;i++)
			{
				System.out.println(By.xpath("//div[@id='chooserResultsDiv']/table/tbody/tr[5]/td/div[3]/table/tbody/tr["+i+"]/td/input"));
				CommonFunctions.waitForElementTobeClickable(By.xpath("//div[@id='chooserResultsDiv']/table/tbody/tr[5]/td/div[3]/table/tbody/tr["+i+"]/td/input"));
				CommonFunctions.selectCheckbox(By.xpath("//div[@id='chooserResultsDiv']/table/tbody/tr[5]/td/div[3]/table/tbody/tr["+i+"]/td/input"));
			}
			//Click on Select
			CommonFunctions.clickButtonOrLink(MaterialBOM.selectBtn, "btn", "Select");
			CommonFunctions.waitForElementTobeClickable(MaterialBOM.createtBtn);
			//Click on Create
			CommonFunctions.clickButtonOrLink(MaterialBOM.createtBtn, "Btn", "Create");
			CommonFunctions.waitForElementTobeClickable(MaterialBOM.colorCloseIcon);
			//Close frame
			CommonFunctions.clickButtonOrLink(MaterialBOM.colorCloseIcon, "icon", "Cross");
			//Click on Meterial tab
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			CommonFunctions.selectFromDropDownByIndex(MaterialBOM.meterialColor, 1);
			strCW=new Select(driver.findElement(MaterialBOM.meterialColor)).getFirstSelectedOption().getText();			
		}catch(Exception e){
			log.error("Exception in Add_Color()", e);
			//	return false;
		}
		return strCW;
	}

	private static String trimMandatoryData(String[] data) throws Exception {
		try{ 
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			matName = data[8] + CommonFunctions.getRandomString(4);
			//materialName = data[8] + date.getTime();
			CommonFunctions.enterTextInTextboxUpdated(txtName, matName,"txtlabourName");

			CommonFunctions.enterTextInTextboxUpdated(txtDescription, "SamplelaborDescription","txtlaborDescription");			

			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstTrimType, "Buckle","lstTrimType");

			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstcontentPercent ,"Plastic","lstcontentPercent");

			CommonFunctions.enterTextInTextbox(Material.txtPercentage,data[14]);

			CommonFunctions.clickButtonOrLink(Material.lnkAdd1, "lnkAdd");
			//Select Content From drop down		
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.content, data[15],"content");
			// Send Content Percent values			
			CommonFunctions.enterTextInTextbox(Material.percent, data[16]);
			CommonFunctions.waitForElementTobeClickable(cpADD);
			//Click On Add button
			//driver.findElement(cpADD);
			CommonFunctions.clickButtonOrLink(cpADD, "btn", "cpADD");
			CommonFunctions.clickButtonOrLink(Material.btnCreateTrim, "btnCreateTrim");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in trimMandatoryData()", e);
			return "";
		}
		return matName;
	}

	private static String resinmandatoryData(String[] data) throws Exception {
		try{ 
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			matName=data[8] + CommonFunctions.getRandomString(5);
			CommonFunctions.enterTextInTextbox(Material.materialName, matName);
			CommonFunctions.enterTextInTextbox(Material.resinDescription, "SampleMaterial");

			//Search for Season from POPUP Page
			CommonFunctions.clickButtonOrLink(seasonLnk, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			CommonFunctions.clickButtonOrLink(btnSearch, "Search For Season");
			//Select choose
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[7]+"')]/preceding::td[1]/a"), "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			// click on resin create button
			CommonFunctions.clickButtonOrLink(Material.btnCreateResin, "btnCreateResin");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in resinmandatoryData()", e);
			return "";
		}
		return matName;
	}

	private static String labormandatoryData(String[] data) throws Exception {
		try{ 
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			matName = data[8] + CommonFunctions.getRandomString(5);
			CommonFunctions.enterTextInTextboxUpdated(txtName, matName,"txtlabourName");
			CommonFunctions.enterTextInTextboxUpdated(Material.txtlaborDescription, "SamplelaborDescription","txtlaborDescription");			
			//Search for Season from POPUP Page
			CommonFunctions.clickButtonOrLink(season, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			CommonFunctions.clickButtonOrLink(btnSearch, "Search For Season");
			//Select choose
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[7]+"')]/preceding::td[1]/a"), "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			// click on resin create button
			CommonFunctions.selectFromDropDownByIndex(MaterialBOM.operationType,1);
			CommonFunctions.clickButtonOrLink(createBtn, "btnCreateLabour");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in labourmandatoryData()", e);
			return "";
		}
		return matName;
	}

	public static String CreateInWork_MBOM(String[] data) throws Exception{
		try{
			navigateUptoCreateDiffrntTypesOfMaterial(data);		
			switch (data[4]) 
			{
			case "Fabric":
				materialInwork= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createMeterialBOM(data);
				break;
			case "Trim":
				materialInwork=trimMandatoryData(data);					
				createMeterialBOM(data);
				break;
			case "Resin":
				materialInwork=resinmandatoryData(data);				
				createMeterialBOM(data);
				break;
			case "Labor":
				materialInwork=labormandatoryData(data);					
				createMeterialBOM(data);
				break;
			default:
				fail=true;
				log.info("Create_MaterialInWork is executed");
			}							
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateInWork_MBOM()", e);
			return "";
		}
		return materialInwork;
	}

	public static String CreateUnderReview_MBOM(String[] data) throws Exception{
		try{
			navigateUptoCreateDiffrntTypesOfMaterial(data);
			switch (data[4]) 
			{
			case "Fabric":
				materialUR= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createURMeterialBOM(data);
				break;
			case "Trim":
				materialUR=trimMandatoryData(data);				
				createURMeterialBOM(data);
				break;
			case "Resin":
				materialUR=resinmandatoryData(data);				
				createURMeterialBOM(data);
				break;
			case "Labor":
				materialUR=labormandatoryData(data);				
				createURMeterialBOM(data);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}						
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateUnderReview_MBOM()", e);
			return "";
		}
		return materialUR;
	}

	public static String CreateReleased_MBOM(String[] data) throws Exception{
		try{
			navigateUptoCreateDiffrntTypesOfMaterial(data);
			switch (data[4]) 
			{
			case "Fabric":
				materialR= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createReleasedMBOM(data);
				break;
			case "Trim":
				materialR=trimMandatoryData(data);			
				createReleasedMBOM(data);
				break;
			case "Resin":
				materialR=resinmandatoryData(data);				
				createReleasedMBOM(data);
				break;
			case "Labor":
				materialR=labormandatoryData(data);				
				createReleasedMBOM(data);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateReleased_MBOM()", e);
			return "";
		}
		return materialR;
	}

	public static String CreateCancelled_MBOM(String[] data) throws Exception{
		try{
			navigateUptoCreateDiffrntTypesOfMaterial(data);
			switch (data[4]) 
			{
			case "Fabric":
				materialC= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createCancelledMBOM(data);
				break;
			case "Trim":
				materialC=trimMandatoryData(data);				
				createCancelledMBOM(data);
				break;
			case "Resin":
				materialC=resinmandatoryData(data);				
				createCancelledMBOM(data);
				break;
			case "Labor":
				materialC=labormandatoryData(data);				
				createCancelledMBOM(data);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}			 
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateCancelled_MBOM()", e);
			return "";
		}
		return materialC;
	}

	public static String CreateRejected_MBOM(String[] data) throws Exception{
		try{
			navigateUptoCreateDiffrntTypesOfMaterial(data);
			switch (data[4]) 
			{
			case "Fabric":
				materialRj= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createRejectedMBOM(data);
				break;
			case "Trim":
				materialRj=trimMandatoryData(data);				
				createRejectedMBOM(data);
				break;
			case "Resin":
				materialRj=resinmandatoryData(data);				
				createRejectedMBOM(data);
				break;
			case "Labor":
				materialRj=labormandatoryData(data);				
				createRejectedMBOM(data);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateRejected_MBOM()", e);
			return "";
		}
		return materialRj;
	}
	
	public static boolean createCancelledMBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			MaterialBOM.addedSupp = AddMeterialSource(data);
			MaterialBOM.addedColor= AddColor(data);
			//Click on meterial
			//	CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(MaterialBOM.initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"Canceled", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(MaterialBOM.createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(MaterialBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.inputCompOrLoca,"Mat description","inputCompOrLoca");
			//Quanity
			action.moveToElement(driver.findElement(MaterialBOM.quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(MaterialBOM.billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");
			//Click on Update
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.updateBtn, "btn", "Update btn");
		//	WebDriverWait wait = new WebDriverWait(driver,10);
		//	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.headerAttributesBtn, "btn", "headerAttributesBtn");
			//MaterialBOM.cancelledBOM="Cancel"+matName;
			//update Name
			//CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.name, URBOM,"name");
			//Click 'Save and Check In'
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn, "btn", "Save and Check In");
		//	CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
		//	driver.switchTo().frame("contentframe");
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.headerAttributesPlus, "btn","Expand Collapse button");
		//	log.info("Cancelled status BOM is: "+MaterialBOM.cancelledBOM);
		}catch(Exception e){
			fail=true;
			log.error("Exception in createCancelledMBOM()", e);
			return false;
		}
		return true;
	}

	public static boolean createReleasedMBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			MaterialBOM.addedSupp = AddMeterialSource(data);
			MaterialBOM.addedColor= AddColor(data);
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(MaterialBOM.initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"Released", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(MaterialBOM.createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(MaterialBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.inputCompOrLoca,"Mat description","inputCompOrLoca" );
			//Quanity
			action.moveToElement(driver.findElement(MaterialBOM.quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(MaterialBOM.billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");
			//Click on Update
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.updateBtn, "btn", "Update btn");
		//	WebDriverWait wait = new WebDriverWait(driver,10);
		//	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.headerAttributesBtn, "btn", "headerAttributesBtn");
		//	String releasedBOM="Released"+matName;
			//update Name
		//	driver.findElement(MaterialBOM.name).clear();
		//	CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.name, URBOM,"name");
			//Click 'Save and Check In'
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn, "btn", "Save and Check In");
		//	CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
		//	driver.switchTo().frame("contentframe");
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.headerAttributesPlus, "btn","Expand Collapse button");

		//	log.info("Released status BOM is: "+releasedBOM);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createMeterialBOM()", e);
			return false;
		}
		return true;
	}

	public static boolean createURMeterialBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			MaterialBOM.addedSupp = AddMeterialSource(data);
			MaterialBOM.addedColor= AddColor(data);			
			//Click on meterial
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(MaterialBOM.initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"Under Review", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(MaterialBOM.createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(MaterialBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.inputCompOrLoca,"Mat description","inputCompOrLoca");
			//Quanity
			action.moveToElement(driver.findElement(MaterialBOM.quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(MaterialBOM.billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");
			//Click on Update
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.updateBtn, "btn", "Update btn");

		//	WebDriverWait wait = new WebDriverWait(driver,10);
		//	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.headerAttributesBtn, "btn", "headerAttributesBtn");
		//	URBOM=matName;
			//update Name
		//	CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.name, URBOM,"name");
			//Click 'Save and Check In'
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn, "btn", "Save and Check In");
		//	CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
		//	driver.switchTo().frame("contentframe");
		//	CommonFunctions.clickButtonOrLink(MaterialBOM.headerAttributesPlus, "btn","Expand Collapse button");
		//	log.info("UnderReview status BOM is: "+URBOM);
		}catch(Exception e){
			fail=true;
			log.error("Exception in createMeterialBOM()", e);
			return false;
		}
		return true;
	}

	public static boolean createRejectedMBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(MaterialBOM.meterialTab, "link", "Meterial");
			MaterialBOM.addedSupp = AddMeterialSource(data);
			MaterialBOM.addedColor= AddColor(data);
			//Click on meterial
			//	CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(MaterialBOM.initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"Rejected", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(MaterialBOM.createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(MaterialBOM.compOrLoca)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputCompOrLoca,"Mat description" ); //data[27]
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.inputCompOrLoca,"Mat description","inputCompOrLoca");
			//Quanity
			action.moveToElement(driver.findElement(MaterialBOM.quantity)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(MaterialBOM.inputquantity,"5" ); //
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(MaterialBOM.billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");
			//Click on Update
			/*CommonFunctions.clickButtonOrLink(MaterialBOM.updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(MaterialBOM.headerAttributesBtn, "btn", "headerAttributesBtn");
			MaterialBOM.rejectBOM="reject"+matName;
			//update Name
			///CommonFunctions.enterTextInTextbox(name, URBOM);
			CommonFunctions.enterTextInTextboxUpdated(MaterialBOM.name, URBOM,"name");
			
			if (data[3].contains("CreateUnderReview")){
				valULCSAfterChange=changeStatusRejected();
			}
			 
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(MaterialBOM.btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(MaterialBOM.headerAttributesPlus, "btn","Expand Collapse button");

			log.info("Rejected status BOM is: "+MaterialBOM.rejectBOM);*/

		}catch(Exception e){
			fail=true;
			log.error("Exception in createRejectedMBOM()", e);
			return false;
		}
		return true;
	}

	static boolean updateBOMStatus(String state) throws Exception{
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
			//System.out.println(driver.findElements(InternalBOMSoftG.headerAttributesBtn).size());
			driver.switchTo().frame("contentframe");
			
			/*if(driver.findElements(InternalBOMSoftG.headerAttributesBtn).size()==0)
			{
				driver.switchTo().frame("mainFrame");
			}
			else {
				//	do nothing
			}*/
			//	Assert.assertEquals(driver.findElements(InternalBOMSoftG.headerAttributesBtn).size(),1);
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateBOMStatus()", e);
			return false;
		}
		return true;
	}

	/*public static boolean verifyErrorMessageStatus() throws Exception{
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
	}*/
	public static boolean verifyBOMStatus(String sStatus,String bomName) throws Exception{
		try {
			Thread.sleep(3000);
			//CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.headerAttributesBtn);
			//Expand header attribute
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.headerAttributesPlus, "img", "Plus sign");

			String strStatus = driver.findElement(bomMstatus).getText();
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


	
	/*public static String createBOM(String[] data) throws Exception{
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
			log.error("Exception in navigateMaterialBOM()", e);
			return "";
		}
		return InternalBOMSoftG.BOMname;
	}*/
	
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
	/*public static boolean revertStatus(String statusBOM,String[] data,String bomName) throws Exception{
		try{
			reLogin();	
			navigateMaterialBOM(data);
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
	}*/

	/*public static boolean reLogin() throws Exception{
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
	}*/

	/************************************************************************************/

	/*public static String AddSource(String[] data) throws Exception{
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
	}*/
	/*public static Boolean AddSourcingConfiguration(String[] data) throws Exception{
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
	}*/

	/*public static Boolean verifySourceForVendor(String[] data) throws Exception{
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
*/
	/*public static String AddSpecification(String[] data) throws Exception{
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
*/
	/*public static String AddColorway(String[] data) throws Exception{
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
	}*/

	/*public static boolean clickSpecificationTab(String dataYear,String[] data) throws Exception{
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
	}*/
	
	/*
	 * Below function is for verdor user.We have to convert specificaion status to
	 *  'Supplier Released',if we want to see it from vendor user
	 */
	/*public static boolean changeSpecificationStatus(String [] data) throws Exception{
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

	}*/
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

