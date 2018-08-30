package com.hasbro.security;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import util.Xls_Reader;


public class CSInternalProdMainUser extends TestsuiteBase{

	//String runmodes[]=null;
	static int count=-1;
	//static boolean skip=false;
	static boolean fail=false;
	//static boolean isTestPass=true;
	//static WebDriverWait wait=null;
	//	static String strTestCaseName = null;
	//public static Properties prop=null;
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
	public static By sourcingLead   = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead   = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");

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
	int y=0;
	static String errMsg="Only the Primary Cost Sheet Attribute may be changed at this time. Please click on Cancel to exit.";
	static String overheadCosterrMsg="Access Denied. Costsheet state is Initial Quote Approved" ;
	static String errMsgStatus="Sorry, You do not have the necessary privileges to update the Status field." ;

	static Boolean bPriCS=false;
	String loginVal;
	Boolean flaglogin=false;
	static String csName = null;

	//static String strSpec;
	static String strSource;
	//	static String strCW;
	static String cstInWork;
	static String cstCancelled;
	static String cstApproved;
	static String cstUnderReview;
	static String csInWork;
	static String csUnderReview;
	static String csCancelled;
	static String csApproved;

	static String strCSInWork;
	static String strCSUnderReview;
	static String strCSCancelled;
	static String strCSApproved;
	static String inWorkStatus;
	static String underReviewStatus;
	static String approvedStatus;
	static String cancelledStatus;
	static String statusText;


	@Test(dataProvider="testDataTest")

	public void tcCostSheet(String[] data) throws Exception{

		try{
			count++;
			System.out.println(CommonProjectFunctions.runmodes[count]);
			if(!CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
				CommonProjectFunctions.skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			CommonProjectFunctions.strTestCaseName = "User:"+data[0] + " for testcase:"+data[3];			
			log.info("Inside Test Case:-> " + CommonProjectFunctions.strTestCaseName + " for CSInternalProdMainUser  Security");				
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
			case "UpdateInWorktoUR":
				log.info("In side :-> " + data[3]);	
				UpdateInWorktoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoA":	
				log.info("In side :-> " + data[3]);	
				UpdateInWorktoA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoC":
				log.info("In side :-> " + data[3]);	
				UpdateInWorktoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoIW":
				log.info("In side :-> " + data[3]);	
				UpdateURtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoA":
				log.info("In side :-> " + data[3]);	
				UpdateURtoA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoC":
				log.info("In side :-> " + data[3]);	
				UpdateURtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateAtoIW":
				log.info("In side :-> " + data[3]);	
				UpdateAtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateAtoUR":
				log.info("In side :-> " + data[3]);	
				UpdateAtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
				/****************/
			case "UpdateAtoC":
				log.info("In side :-> " + data[3]);	
				UpdateAtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoIW":
				log.info("In side :-> " + data[3]);	
				UpdateCtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoUR":
				log.info("In side :-> " + data[3]);	
				UpdateCtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoA":
				log.info("In side :-> " + data[3]);	
				UpdateCtoA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateInWork":
				log.info("In side :-> " + data[3]);	
				createInternalCS_inwork(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateUnderReview":
				log.info("In side :-> " + data[3]);	
				createInternalCS_underReview(data,"Under Review");
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateApproved":
				log.info("In side :-> " + data[3]);	
				createInternalCS_Approved(data,"Approved");
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateCancelled":
				log.info("In side :-> " + data[3]);	
				createInternalCS_Cancelled(data,"Cancelled");
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
		public static Boolean UpdateInWorktoUR(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				if(!identifyTab(csInWork,"In Work")) {
					if(!data[4].contains("vendor")) {
						CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csInWork);
						CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("In Work",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csInWork);
						//Revert back status
						revertStatus("In Work",csInWork,data);
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csInWork);
						//Revert back status
						revertStatus("In Work",csInWork,data);
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csInWork);
						//Revert back status
						revertStatus("In Work",csInWork,data);
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in UpdateInWorktoUR()", e);
				return false;
			}
		}
		//InWork to Ready For Review
		public static boolean UpdateInWorktoA(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				if(!identifyTab(csInWork,"In Work")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("In Work",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}

				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}

		//InWork to Initial Quote Approved
		public static boolean UpdateInWorktoC(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				if(!identifyTab(csInWork,"In Work")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("In Work",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}

					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}

		//InWork to Ready For Review
		public static boolean UpdateURtoIW(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				if(!identifyTab(csUnderReview,"Under Review")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Under Review",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}

					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}
		//InWork to Ready For Review
		public static boolean UpdateURtoA(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) 
					if(!data[4].contains("vendor")) {{
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Under Review",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Approved");
						verifyErrorMessageStatus();
					}

					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}

		//InWork to Ready For Review
		public static boolean UpdateURtoC(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				if(!identifyTab(csUnderReview,"Under Review")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Under Review",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}


		public static boolean UpdateAtoIW(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				if(!identifyTab(csApproved,"Approved")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Approved",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csApproved);
						//Revert back status
						updateCSStatus("Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csApproved);
						//Revert back status
						updateCSStatus("Approved");
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csApproved);
						//Revert back status
						updateCSStatus("Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csApproved);
						//Revert back status
						updateCSStatus("Approved");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Approved",csApproved,data);
				}

				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}
		//InWork to Ready For Review
		public static boolean UpdateAtoUR(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				if(!identifyTab(csApproved,"Approved")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Approved",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csApproved);
						//Revert back status
						updateCSStatus("Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Approved",csApproved,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}

		public static Boolean UpdateAtoC(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				if(!identifyTab(csApproved,"Approved")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Approved",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csApproved);
						//Revert back status
						updateCSStatus("Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Approved",csApproved,data);
				}

				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}
		//InWork to Ready For Review
		public static boolean UpdateCtoIW(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Cancelled",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csCancelled,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}


		public static boolean UpdateCtoUR(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					if(!data[4].contains("vendor")) {
					CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Cancelled",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csCancelled);
						//Revert back status
						revertStatus("Cancelled",csCancelled,data);
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csCancelled);
						//Revert back status
						revertStatus("Cancelled",csCancelled,data);
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csCancelled);
						//Revert back status
						revertStatus("Cancelled",csCancelled,data);
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}


		public static boolean UpdateCtoA(String[] data) throws Exception{
			try{
				CostsheetInternal.nevigationToCostsheet(data);
				//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					if(!data[4].contains("vendor")) {
						CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csCancelled);
						CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					}
				}
				if(validateStatus("Cancelled",data)){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						updateCSStatus("Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						verifySourceForVendor(data);
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				return true;
			}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3]+ "for user: " + data[0], e);
				return false;
			}
		}

	/*****************************************************/
	/*
	 * This function identify required tab is opened or not. If it is present,
	 * test case follow next step otherwise close opened tab
	 */
	public static boolean identifyTab(String costSheetName,String ssStatus) throws Exception{
		try{

			if(driver.findElements(selectedTab).size()>0 ) {
				statusText = driver.findElement(csStatus).getText().trim();
				if(ssStatus.equalsIgnoreCase(statusText))
				{
					log.info("**On required cs tab**: "+costSheetName);
					return true;
				}
				else
				{
					CostsheetInternal.closeTheOpenCostSheet();
					return false;
				}
			}
			else {
				log.info("Not On required cs tab: "+costSheetName);
				return false;
			}
			//}
		}catch(Exception e){
			fail=true;
			log.error("Exception in identifyTab()", e);
			return false;
		}
	}

	public static boolean validateStatus(String statusCS,String[] data) throws Exception{
		try{
			if(!data[4].contains("vendor")) {
				String strStat = driver.findElement(csStatus).getText().trim();	
				if(strStat.equalsIgnoreCase(statusCS))
				{
					log.info("status is as expected: "+statusCS);
					return true;
				}
				else {
					fail=true;
					log.info("status is not as expected: "+strStat);
					return false;
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in validateStatus()", e);
			return false;
		}
	}

	public static boolean revertStatus(String statusCS,String costSheet,String[] data) throws Exception{
		try{
			reLogin();	
			CostsheetInternal.nevigationToCostsheet(data);
			// click on Cost sheet List tab
			CSInternalRetailToolingMainUser.clickOnCostSheetListTab();
			// search cost sheet name in web table
			searchandClickforRequriedCostsheet(costSheet);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			// Update Status
			updateCSStatus(statusCS); //statusCS
			//Log Out
			CommonProjectFunctions.logOut();
			//Launch App with required login\Pwd
			openBrowser();
			Thread.sleep(2000);
			launchApp(data[0],data[1]);	
			return true;	
		}catch(Exception e){
			fail=true;
			log.error("Exception in revertStatus()", e);
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
			Thread.sleep(2000);
			launchApp(prop.getProperty("adminuser"),prop.getProperty("adminpwd"));	
			return true;	
		}catch(Exception e){
			fail=true;
			log.error("Exception in reLoin()", e);
			return false;
		}
	}

	/*************************************************************************/
	//In Work
	public static String createInternalCS_inwork(String[] data) throws Exception{
		try{
			createInternalCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			log.info("**********Inside********");
			strCSInWork = driver.findElement(csHeading).getText().substring(20);
			String []varCSInWork = strCSInWork.split("Actions:");
			csInWork = varCSInWork[0].trim();

			log.info("Internal Retail Tooling costsheet in Inwork status is : "+csInWork);
			CostsheetInternal.closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in createRetailToolingCS_inwork()", e);
			return "";
		}
		return csInWork;
	}	
	//underReview
	public static String createInternalCS_underReview(String[] data,String sStatus) throws Exception{
		try{
			createInternalCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSUnderReview = driver.findElement(csHeading).getText().substring(20);
			String []varCSUnderReview = strCSUnderReview.split("Actions:");
			csUnderReview = varCSUnderReview[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			log.info("Internal Retail Tooling costsheet in underReview status is: "+csUnderReview);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createRetailToolingCS_underReview()", e);
			return "";
		}
		return csUnderReview;
	}
	public static String createInternalCS_Approved(String[] data,String sStatus) throws Exception{
		try{
			createInternalCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSApproved = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			String []varCSApproved = strCSApproved.split("Actions:");
			csApproved = varCSApproved[0].trim();

			//Update Status
			updateCSStatus(sStatus);
			log.info("Vendor Retail Tooling costsheet in Approved status is: "+csApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createRetailToolingCS_Approved()", e);
			return "";
		}
		return csApproved;
	}
	//Cancelled
	public static String createInternalCS_Cancelled(String[] data,String sStatus) throws Exception{
		try{
			createInternalCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSCancelled= driver.findElement(csHeading).getText().substring(20);
			String []varCSCancelled = strCSCancelled.split("Actions:");
			csCancelled = varCSCancelled[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Internal Vendor Retail Tooling costsheet in Cancelled status is: "+csCancelled);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createRetailToolingCS_Cancelled()", e);
			return "";
		}
		return csCancelled;
	}
	public static Boolean createInternalCS(String[] data) throws Exception{
		try{
			if(driver.findElements(CostsheetTooling.tabCostsheet).size() == 0) {
				CommonProjectFunctions.searchProduct(data[6]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[7]);
				CommonProjectFunctions.clickCostingTab();
				if(!data[4].equalsIgnoreCase("vendor")) {
					strSource=AddSource(data);
					//strSpec=InternalBOMSoftG.AddSpecification(data);
					//strCW=InternalBOMSoftG.AddColorway(data);
				}
			}
			clickOnCostSheetListTab();
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(lstcostingActions,"Create Cost Sheet"); //hard coding
			Thread.sleep(1000);	

			// select tooling type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"),"link","Cost sheet type");

			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,1);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
			/*if(!data[4].equalsIgnoreCase("vendor")) {*/
			// select wave
			CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[12]);
		//	}

			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			//	if(data[2].equalsIgnoreCase("Tooling Internal")) {
			// select QuoteCurrency
			//	CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[14]);
			//	}
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in createToolingCS()", e);
			return false;
		}
	}

	/************************************************************************************/
	public static boolean navigateUptoCostSheetTable(String[] data) throws Exception{
		try{
			// select seasion
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			// click on source link	and select year		
			CommonProjectFunctions.clickSourcingTab(data[7]);
			CommonFunctions.clickButtonOrLink(lnkCosting,"link","lnkCosting");
			// select season
			CommonFunctions.selectFromDropDownByVisibleText(lstSeason,data[7]);
			// select source
			CommonFunctions.selectFromDropDownByVisibleText(lstsource, data[8]);
			CommonFunctions.selectFromDropDownByIndex(lstspecification,1);
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in navigateUptoCostSheetTable()", e);
			return false;
		}
	}

	//Update cost Sheet Status
	public static boolean updateCSStatus(String sStatus) throws Exception{
		try {
			CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
			//Thread.sleep(3000);	
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			CommonFunctions.waitForVisibilityOfElement(lstCSStatus);
			// update Status
			CommonFunctions.selectFromDropDownByVisibleText(lstCSStatus,sStatus);
			log.info("Updated status to: "+sStatus);
			CommonFunctions.waitForVisibilityOfElement(btnSave);
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");	
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateCSStatus()", e);
			return false;
		}
	}


	//Search is shifted to row wise as view is different for different users
	public static String searchandClickforRequriedCostsheet(String costSheetName) throws Exception
	{
		try{
			int colIndex=1;
			List<WebElement> row1 = driver.findElements(By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td"));
			//Previous xpath : 
			// horizontal ://div[@id='costSheetResults']//div[3]/table/tbody/tr/td
			//vertical : //div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]
			System.out.println(row1.size());
			for(WebElement e: row1){
				colIndex=colIndex+1;
				csName =e.getText().trim(); //.replaceAll("\\s+", " ");
				String csName1 =e.getText().trim().replaceAll("\\s+", " ");
				System.out.println("current row CS): "+ csName);
				System.out.println("expected CS: "+costSheetName);
				if (csName1.equals(costSheetName)) {
					Thread.sleep(2000);
					CommonFunctions.clickButtonOrLink(By.linkText(csName),"link","Cost sheet name");
					return csName;
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in searchandClickforRequriedCostsheet()", e);
			return "";
		}
		return csName;
	}

	public static boolean clickOnCostSheetListTab() throws Exception
	{
		try{
			driver.findElement(tabCostsheet).click();   
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in clickOnCostSheetListTab()", e);
			return false;
		}
	}
	public static String AddSource(String[] data) throws Exception{
		try{
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(selectSource));
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource =elementCountSource.size();
			//log.info("Number of supplier: " + countSource);
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

			//	CommonFunctions.selectFromDropDownByVisibleText(manager, data[23]);
			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(sourcingLead, 1);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(sourcingHead, 1);
			// Management 
			//	CommonFunctions.selectFromDropDownByVisibleText(management, data[26]);
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
	public static boolean SearchCostsheet(String costSheetname) throws Exception
	{
		try{
			int colIndex=1;
			List<WebElement> col1 = SeleniumDriver.driver.findElements(csCol);
			System.out.println(col1.size());
			for(WebElement e: col1){
				colIndex=colIndex+1;
				System.out.println("e.getText(): "+ e.getText());
				System.out.println("Segment: "+costSheetname);
				if (e.getText().trim().equals(costSheetname)) {
					return true;
				}
			}
			return false;
		}catch(Exception e){
			fail=true;
			log.error("Exception in SearchCostsheet()", e);
			return false;
		}
	}

	public static boolean verifyErrorMessageStatus() throws Exception{
		try {
			Assert.assertEquals(driver.findElement(statusErrorMsg).getText().trim(),errMsgStatus);
			log.info("Verified error message as : " +errMsgStatus +"for" + CommonProjectFunctions.strTestCaseName);
			CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyErrorMessageStatus()", e);
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

