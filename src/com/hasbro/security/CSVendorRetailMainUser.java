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


public class CSVendorRetailMainUser extends TestsuiteBase{


	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String strTestCaseName = null;
	public static Properties prop=null;
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

	public static By assSolidRef =By.xpath("//td[contains(text(),'Assortment / Solid Reference:')]");
	public static By assVendorSolidRef =By.xpath("//td[contains(text(),'Vendor Assortment / Solid Reference:')]");
	public static By assSolidColorway =By.xpath("//td[contains(text(),'Ast./Solid Colorway')]");
	public static By assSolidWave =By.xpath("//td[contains(text(),'Ast./Solid Wave')]");
	public static By btnDone = By.xpath("//a[text()='Done']");
	public static By vendorCostDetails =By.xpath("//td[contains(text(),'Vendor Cost Details:')]");
	public static By lblCSComments =By.xpath("//td[contains(text(),'Cost Sheet Comments:')]");
	public static By csCommentsView =By.xpath("//td[contains(text(),'Cost Sheet Comments:')]//following::a[2]");
	public static By csCommentsEdit =By.xpath("//td[contains(text(),'Cost Sheet Comments:')]//following::a[text()='Edit'][1]");

	public static By enterComment =By.xpath("//div[contains(@id,'div_plus')]//a[text()='Enter Comment']");
	public static By taComments =By.xpath("//textarea[contains(@id,'comments')]"); // ta=text area
	public static By btnPost =By.xpath("//a[text()='Post']"); 

	public static By prodType =By.id("hBProductType"); 
	public static By csSequenceNo =By.xpath("//td[contains(text(),'Cost Sheet Sequence Number')]//following::td[1]"); 

	public static By labelPrimaryCS = By.xpath("//td[contains(text(),'Vendor Primary Cost Sheet')]");
	public static By PrimaryCSYN = By.id("hbPrimaryCostSheet");
	public static By chkPriCS = By.xpath("//input[@type='checkbox']");
	public static By lblPrimaryCS  = By.id("hbPrimaryCostSheet");
	public static By lblOverheadCost =By.xpath("//td[contains(text(),'Overhead Cost:')]");
	public static By overheadCostView =By.xpath("//td[contains(text(),'Overhead Cost: ')]//following::a[2]");
	public static By overheadCostEdit =By.xpath("//td[contains(text(),'Overhead Cost:')]//following::a[text()='Edit'][1]");

	public static By overheadType =By.xpath("//td[contains(@id,'hbOverheadType')]");
	public static By ddOverheadType =By.xpath("//div[@id='hbOverheadTypeSourceDiv']/select");

	public static By ricQuoteCurr = By.xpath("//td[contains(text(),'Vendor Retail Item Cost - Quote Currency')]");
	public static By txtRetailItemCost  = By.xpath("//td[contains(text(),'Vendor Retail Item Cost - Quote Currency')]//following::td[2]");
	public static By txtPackMaterial  = By.xpath("//td[contains(text(),'Packaging Material')]//following::input[@type='text'][1]");
	public static By lblPackMaterial =By.id("hBPackagingMaterial");

	public static By ricUSDConversion = By.xpath("//td[contains(text(),'Vendor Retail Item Cost - USD Conversion:')]");
	public static By plasticMaterialUSD  = By.id("hbPlasticMaterialUSDConversion");

	public static By statusErrorMsg = By.xpath("//td[contains(text(),'Sorry, You do not have the necessary privileges to update the Status field.')]");
	public static By pCSErrorMsg = By.xpath("//td[contains(text(),'Only the Primary Cost Sheet Attribute may be changed at this time.')]");
	public static By ohCostErrorMsg = By.xpath("//td[contains(text(),'Access Denied. Costsheet state is Initial Quote Approved')]");
	public static By typeVendor = By.xpath("//a[text()='Vendor']");
	public static By selectedTab = By.xpath("//li[@class='tabselected']/a[@title!='Cost Sheet List']");
	int y=0;
	static String errMsg="Only the Primary Cost Sheet Attribute may be changed at this time. Please click on Cancel to exit.";
	static String overheadCosterrMsg="Access Denied. Costsheet state is Initial Quote Approved" ;
	static String errMsgStatus="Sorry, You do not have the necessary privileges to update the Status field." ;


	//static int intBOMPresent=0;
	static Boolean bPriCS=false;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String BOMname;
	static String strBillOfMaterials;
	static Boolean bCheckedOut=false;
	static Actions action;
	static String csName = null;

	static String strSpec;
	static String strSource;
	static String strCW;

	static String NewCostsheetname;

	static String cstInWork;
	static String cstCancelled;
	static String cstApproved;
	static String cstUnderReview;
	static String cstCommonCostSheet;
	static String cstNewCostSheetName;

	static String cstReadforReview;
	static String cstInitialQuoteApproved;
	static String cstSG4Approved;
	static String cstFEPApproved;
	static String cstSeasonalReviewApproved;
	static String cstRejected;
	static String cstDefaultCostSheet;
	static String cstDeleteCostSheet;
	static Boolean bCostSheet=false;
	static String strCostSheetName;

	static String csInWork;
	static String csReadyforReview;
	static String csUnderReview;
	static String csInitialQuoteApproved;
	static String csSG4Approved;
	static String csFEPApproved;
	static String csSeasonalReviewApproved;
	static String csRejected;
	static String csCancelled;
	static String csApproved;

	static String strCSInWork;
	static String strCSReadyforReview;
	static String strCSUnderReview;
	static String strCSInitialQuoteApproved;
	static String strCSSG4Approved;
	static String strCSFEPApproved;
	static String strCSSeasonalReviewApproved;
	static String strCSRejected;
	static String strCSCancelled;
	static String strCSApproved;


	static String csForD;
	static String strCSforD;


	static String inWorkStatus;
	static String underReviewStatus;
	static String approvedStatus;
	static String cancelledStatus;

	static String readyforReviewStatus;
	static String initialQuoteApprovedStatus;
	static String sg4ApprovedStatus;
	static String fepApprovedStatus;
	static String seasonalReviewApprovedStatus;
	static String RejectedStatus;

	static String sStatus;
	static String strStat;
	static String statusText;


	@Test(dataProvider="testDataTest")

	public void tcCostSheet(String[] data) throws Exception{

		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			strTestCaseName = "User:"+data[0] + " for testcase:"+data[3];			
			log.info("Inside Test Case:-> " + strTestCaseName + " for CSVendorRetailMainUser  Security");				
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
			switch (data[3]) {
			case "UpdateInWorktoRFR":
				log.info("In side :-> " + data[3]);	
				updateInWorktoRFR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoUR":	
				log.info("In side :-> " + data[3]);	
				updateInWorktoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoIQA":
				log.info("In side :-> " + data[3]);	
				updateInWorktoIQA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoSG4A":
				log.info("In side :-> " + data[3]);	
				updateInWorktoSG4A(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoFEPA":
				log.info("In side :-> " + data[3]);	
				updateInWorktoFEPA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoSRA":
				log.info("In side :-> " + data[3]);	
				updateInWorktoSRA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoC":
				log.info("In side :-> " + data[3]);	
				updateInWorktoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateInWorktoR":
				log.info("In side :-> " + data[3]);	
				updateInWorktoR(data);
				log.info("Out side :-> " + data[3]);
				break;
				/****************/
			case "UpdateRFRtoIW":
				log.info("In side :-> " + data[3]);	
				updateRFRtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRFRtoUR":
				log.info("In side :-> " + data[3]);	
				updateRFRtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRFRtoIQA":
				log.info("In side :-> " + data[3]);	
				updateRFRtoIQA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRFRtoSG4A":
				log.info("In side :-> " + data[3]);	
				updateRFRtoSG4A(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRFRtoFEPA":
				log.info("In side :-> " + data[3]);	
				updateRFRtoFEPA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRFRtoSRA":
				log.info("In side :-> " + data[3]);	
				updateRFRtoSRA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRFRtoC":
				log.info("In side :-> " + data[3]);	
				updateRFRtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRFRtoR":
				log.info("In side :-> " + data[3]);	
				updateRFRtoR(data);
				log.info("Out side :-> " + data[3]);
				break;
				/****************/
			case "UpdateURtoIW":
				log.info("In side :-> " + data[3]);	
				updateURtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoRFR":
				log.info("In side :-> " + data[3]);	
				UpdateURtoRFR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoIQA":
				log.info("In side :-> " + data[3]);	
				updateURtoIQA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoSG4A":
				log.info("In side :-> " + data[3]);	
				updateURtoSG4A(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoFEPA":
				log.info("In side :-> " + data[3]);	
				updateURtoFEPA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoSRA":
				log.info("In side :-> " + data[3]);	
				updateURtoSRA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoC":
				log.info("In side :-> " + data[3]);	
				updateURtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateURtoR":
				log.info("In side :-> " + data[3]);	
				updateURtoR(data);
				log.info("Out side :-> " + data[3]);
				break;
				/****************/
			case "UpdateIQAtoIW":
				log.info("In side :-> " + data[3]);	
				updateIQAtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateIQAtoRFR":
				log.info("In side :-> " + data[3]);	
				updateIQAtoRFR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateIQAtoUR":
				log.info("In side :-> " + data[3]);	
				updateIQAtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateIQAtoSG4A":
				log.info("In side :-> " + data[3]);	
				updateIQAtoSG4A(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateIQAtoFEPA":
				log.info("In side :-> " + data[3]);	
				updateIQAtoFEPA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateIQAtoSRA":
				log.info("In side :-> " + data[3]);	
				updateIQAtoSRA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateIQAtoC":
				log.info("In side :-> " + data[3]);	
				updateIQAtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateIQAtoR":
				log.info("In side :-> " + data[3]);	
				updateIQAtoR(data);
				log.info("Out side :-> " + data[3]);
				break;

			case "UpdateSG4AtoIW":
				log.info("In side :-> " + data[3]);	
				updateSG4AtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSG4AtoRFR":
				log.info("In side :-> " + data[3]);	
				updateSG4AtoRFR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSG4AtoUR":
				log.info("In side :-> " + data[3]);	
				updateSG4AtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSG4AtoIQA":
				log.info("In side :-> " + data[3]);	
				updateSG4AtoIQA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSG4AtoFEPA":
				log.info("In side :-> " + data[3]);	
				updateSG4AtoFEPA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSG4AtoSRA":
				log.info("In side :-> " + data[3]);	
				updateSG4AtoSRA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSG4AtoC":
				log.info("In side :-> " + data[3]);	
				updateSG4AtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSG4AtoR":
				log.info("In side :-> " + data[3]);	
				updateSG4AtoR(data);
				log.info("Out side :-> " + data[3]);
				break;
				/*************/
			case "UpdateFEPAtoIW":
				log.info("In side :-> " + data[3]);	
				updateFEPAtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateFEPAtoRFR":
				log.info("In side :-> " + data[3]);	
				updateFEPAtoRFR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateFEPAtoUR":
				log.info("In side :-> " + data[3]);	
				updateFEPAtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateFEPAtoIQA":
				log.info("In side :-> " + data[3]);	
				updateFEPAtoIQA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateFEPAtoSG4A":
				log.info("In side :-> " + data[3]);	
				updateFEPAtoSG4A(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateFEPAtoSRA":
				log.info("In side :-> " + data[3]);	
				updateFEPAtoSRA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateFEPAtoC":
				log.info("In side :-> " + data[3]);	
				updateFEPAtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateFEPAtoR":
				log.info("In side :-> " + data[3]);	
				updateFEPAtoR(data);
				log.info("Out side :-> " + data[3]);
				break;
				/***************/
			case "UpdateSRAtoIW":
				log.info("In side :-> " + data[3]);	
				updateSRAtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSRAtoRFR":
				log.info("In side :-> " + data[3]);	
				updateSRAtoRFR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSRAtoUR":
				log.info("In side :-> " + data[3]);	
				updateSRAtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSRAtoIQA":
				log.info("In side :-> " + data[3]);	
				updateSRAtoIQA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSRAtoSG4A":
				log.info("In side :-> " + data[3]);	
				updateSRAtoSG4A(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSRAtoFEPA":
				log.info("In side :-> " + data[3]);	
				updateSRAtoFEPA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSRAtoC":
				log.info("In side :-> " + data[3]);	
				updateSRAtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateSRAtoR":
				log.info("In side :-> " + data[3]);	
				updateSRAtoR(data);
				log.info("Out side :-> " + data[3]);
				break;
				/*********continue*******/
			case "UpdateCtoIW":
				log.info("In side :-> " + data[3]);	
				updateCtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoRFR":
				log.info("In side :-> " + data[3]);	
				updateCtoRFR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoUR":
				log.info("In side :-> " + data[3]);	
				updateCtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoIQA":
				log.info("In side :-> " + data[3]);	
				updateCtoIQA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoSG4A":
				log.info("In side :-> " + data[3]);	
				updateCtoSG4A(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoFEPA":
				log.info("In side :-> " + data[3]);	
				updateCtoFEPA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoSRA":
				log.info("In side :-> " + data[3]);	
				updateCtoSRA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateCtoR":
				log.info("In side :-> " + data[3]);	
				updateCtoR(data);
				log.info("Out side :-> " + data[3]);
				break;
				/*********continue*******/
			case "UpdateRtoIW":
				log.info("In side :-> " + data[3]);	
				updateRtoIW(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRtoRFR":
				log.info("In side :-> " + data[3]);	
				updateRtoRFR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRtoUR":
				log.info("In side :-> " + data[3]);	
				updateRtoUR(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRtoIQA":
				log.info("In side :-> " + data[3]);	
				updateRtoIQA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRtoSG4A":
				log.info("In side :-> " + data[3]);	
				updateRtoSG4A(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRtoFEPA":
				log.info("In side :-> " + data[3]);	
				updateRtoFEPA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRtoSRA":
				log.info("In side :-> " + data[3]);	
				updateRtoSRA(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "UpdateRtoC":
				log.info("In side :-> " + data[3]);	
				updateRtoC(data);
				log.info("Out side :-> " + data[3]);
				break;
				/******************/
			case "CreateInWork":
				log.info("In side :-> " + data[3]);	
				createRetailCS_inwork(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateReadyforReview":
				log.info("In side :-> " + data[3]);	
				createVenRetCS_ReadyforReview(data,"Ready for Review");
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateUnderReview":
				log.info("In side :-> " + data[3]);	
				createRetailCS_underReview(data,"Under Review");
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateInitialQuoteApproved":
				log.info("In side :-> " + data[3]);	
				createVenRetCS_initialQuoteApproved(data,"Initial Quote Approved");
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateSG4Approved":
				log.info("In side :-> " + data[3]);	
				createVenRetCS_SG4Approved(data,"SG4 Approved");
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateFEPApproved":
				log.info("In side :-> " + data[3]);	
				createVenRetCS_FEPApproved(data,"FEP Approved"); 
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateSeasonalReviewApproved":
				log.info("In side :-> " + data[3]);	
				createVenRetCS_seasonalReviewApproved(data,"Seasonal Review Approved");
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateCancelled":
				log.info("In side :-> " + data[3]);	
				createRetailCS_Cancelled(data,"Cancelled");
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateRejected":
				log.info("In side :-> " + data[3]);	
				createVenRetCS_Rejected(data,"Rejected");
				log.info("Out side :-> " + data[3]);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Throwable t){
			fail=true;
			log.info("****Varification failed or Skipped for**** "+strTestCaseName);
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	//InWork to Ready For Review
	public static Boolean updateInWorktoRFR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			////if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInWork,"In Work")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("In Work")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Ready for Review");
						CostsheetInternal.verifyCSStatus("Ready for Review",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
			//}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateInWorktoUR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			////if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInWork,"In Work")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("In Work")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				// close the open cost sheet
				//	CostsheetInternal.closeTheOpenCostSheet();
			//}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateInWorktoIQA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			////if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInWork,"In Work")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("In Work")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateInWorktoSG4A(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInWork,"In Work")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("In Work")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateInWorktoFEPA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInWork,"In Work")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("In Work")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	
	//InWork to Ready For Review
	public static boolean updateInWorktoSRA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInWork,"In Work")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("In Work")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
	//		}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateInWorktoC(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInWork,"In Work")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("In Work")){
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
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateInWorktoR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInWork,"In Work")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("In Work")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csInWork);
						//Revert back status
						updateCSStatus("In Work");
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					// close the open cost sheet
					//CostsheetInternal.closeTheOpenCostSheet();	
				}
				else {
					//Revert status to inwork 
					revertStatus("In Work",csInWork,data);
				}
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	/********2*********/
	//InWork to Ready For Review
	public static Boolean updateRFRtoIW(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csReadyforReview,"Ready for Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Ready for Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csReadyforReview);
						//Revert back status
						updateCSStatus("Ready for Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csReadyforReview,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRFRtoUR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csReadyforReview,"Ready for Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Ready for Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csReadyforReview);
						//Revert back status
						updateCSStatus("Ready for Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csReadyforReview,data);
				}
				// close the open cost sheet
				//	CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateRFRtoIQA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csReadyforReview,"Ready for Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Ready for Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csReadyforReview);
						//Revert back status
						updateCSStatus("Ready for Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csReadyforReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateRFRtoSG4A(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csReadyforReview,"Ready for Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Ready for Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csReadyforReview);
						//Revert back status
						updateCSStatus("Ready for Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csReadyforReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRFRtoFEPA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csReadyforReview,"Ready for Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Ready for Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csReadyforReview);
						//Revert back status
						updateCSStatus("Ready for Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csReadyforReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRFRtoSRA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csReadyforReview,"Ready for Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Ready for Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csReadyforReview);
						//Revert back status
						updateCSStatus("Ready for Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csReadyforReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRFRtoC(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csReadyforReview,"Ready for Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Ready for Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csReadyforReview);
						//Revert back status
						updateCSStatus("Ready for Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csReadyforReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRFRtoR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csReadyforReview,"Ready for Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Ready for Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csReadyforReview);
						//Revert back status
						updateCSStatus("Ready for Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Ready for Review",csReadyforReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
	//		}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	/********2*********/
	//InWork to Ready For Review
	public static Boolean updateURtoIW(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Under Review")){
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
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean UpdateURtoRFR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Under Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Ready for Review");
						CostsheetInternal.verifyCSStatus("Ready for Review",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				// close the open cost sheet
				//	CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateURtoIQA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Under Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
	//		}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateURtoSG4A(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Under Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
	//		}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateURtoFEPA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Under Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateURtoSRA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Under Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateURtoC(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Under Review")){
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
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateURtoR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csUnderReview,"Under Review")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Under Review")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csUnderReview);
						//Revert back status
						updateCSStatus("Under Review");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Under Review",csUnderReview,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	/********2*********/
	//InWork to Ready For Review
	public static Boolean updateIQAtoIW(String[] data) throws Exception{
		try{
			//Need updation
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInitialQuoteApproved,"Initial Quote Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Initial Quote Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csInitialQuoteApproved);
						//Revert back status
						revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateIQAtoRFR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInitialQuoteApproved,"Initial Quote Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Initial Quote Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Ready for Review");
						CostsheetInternal.verifyCSStatus("Ready for Review",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
				}
				// close the open cost sheet
				//	CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateIQAtoUR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInitialQuoteApproved,"Initial Quote Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Initial Quote Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateIQAtoSG4A(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInitialQuoteApproved,"Initial Quote Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Initial Quote Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateIQAtoFEPA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInitialQuoteApproved,"Initial Quote Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Initial Quote Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateIQAtoSRA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInitialQuoteApproved,"Initial Quote Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Initial Quote Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateIQAtoC(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInitialQuoteApproved,"Initial Quote Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Initial Quote Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateIQAtoR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csInitialQuoteApproved,"Initial Quote Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Initial Quote Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csInitialQuoteApproved);
						//Revert back status
						updateCSStatus("Initial Quote Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Initial Quote Approved",csInitialQuoteApproved,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	/********2*********/
	//InWork to Ready For Review
	public static Boolean updateSG4AtoIW(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSG4Approved,"SG4 Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("SG4 Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csSG4Approved);
						//Revert back status
						revertStatus("SG4 Approved",csSG4Approved,data);
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("SG4 Approved",csSG4Approved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSG4AtoRFR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSG4Approved,"SG4 Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("SG4 Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Ready for Review");
						CostsheetInternal.verifyCSStatus("Ready for Review",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("SG4 Approved",csSG4Approved,data);
				}
				// close the open cost sheet
				//	CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateSG4AtoUR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSG4Approved,"SG4 Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("SG4 Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("SG4 Approved",csSG4Approved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
	//		}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateSG4AtoIQA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSG4Approved,"SG4 Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("SG4 Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("SG4 Approved",csSG4Approved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSG4AtoFEPA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSG4Approved,"SG4 Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("SG4 Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("SG4 Approved",csSG4Approved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateSG4AtoSRA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSG4Approved,"SG4 Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("SG4 Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("SG4 Approved",csSG4Approved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSG4AtoC(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSG4Approved,"SG4 Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("SG4 Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("SG4 Approved",csSG4Approved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSG4AtoR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSG4Approved,"SG4 Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("SG4 Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csSG4Approved);
						//Revert back status
						updateCSStatus("SG4 Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("SG4 Approved",csSG4Approved,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	/********2*********/
	//InWork to Ready For Review
	public static Boolean updateFEPAtoIW(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csFEPApproved,"FEP Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("FEP Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csFEPApproved);
						//Revert back status
						revertStatus("FEP Approved",csFEPApproved,data);
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("FEP Approved",csFEPApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateFEPAtoRFR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csFEPApproved,"FEP Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("FEP Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Ready for Review");
						CostsheetInternal.verifyCSStatus("Ready for Review",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("FEP Approved",csFEPApproved,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateFEPAtoUR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csFEPApproved,"FEP Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("FEP Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("FEP Approved",csFEPApproved,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateFEPAtoIQA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csFEPApproved,"FEP Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("FEP Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("FEP Approved",csFEPApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateFEPAtoSG4A(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csFEPApproved,"FEP Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("FEP Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("FEP Approved",csFEPApproved,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateFEPAtoSRA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csFEPApproved,"FEP Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("FEP Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("FEP Approved",csFEPApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateFEPAtoC(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csFEPApproved,"FEP Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("FEP Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("FEP Approved",csFEPApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateFEPAtoR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csFEPApproved,"FEP Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("FEP Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csFEPApproved);
						//Revert back status
						updateCSStatus("FEP Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("FEP Approved",csFEPApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	/********2*********/
	//InWork to Ready For Review
	public static Boolean updateSRAtoIW(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSeasonalReviewApproved,"Seasonal Review Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Seasonal Review Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csSeasonalReviewApproved);
						//Revert back status
						revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSRAtoRFR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSeasonalReviewApproved,"Seasonal Review Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Seasonal Review Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Ready for Review");
						CostsheetInternal.verifyCSStatus("Ready for Review",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
				}
				// close the open cost sheet
				//	CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateSRAtoUR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSeasonalReviewApproved,"Seasonal Review Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Seasonal Review Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateSRAtoIQA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSeasonalReviewApproved,"Seasonal Review Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Seasonal Review Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSRAtoSG4A(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSeasonalReviewApproved,"Seasonal Review Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Seasonal Review Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSRAtoFEPA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSeasonalReviewApproved,"Seasonal Review Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Seasonal Review Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSRAtoC(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSeasonalReviewApproved,"Seasonal Review Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Seasonal Review Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateSRAtoR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csSeasonalReviewApproved,"Seasonal Review Approved")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Seasonal Review Approved")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csSeasonalReviewApproved);
						//Revert back status
						updateCSStatus("Seasonal Review Approved");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Seasonal Review Approved",csSeasonalReviewApproved,data);
				}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	/********2*********/
	//InWork to Ready For Review
	public static Boolean updateCtoIW(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Cancelled")){
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
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateCtoRFR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Cancelled")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Ready for Review");
						CostsheetInternal.verifyCSStatus("Ready for Review",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				// close the open cost sheet
				//	CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateCtoUR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Cancelled")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateCtoIQA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Cancelled")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateCtoSG4A(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Cancelled")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
//InWork to Ready For Review
	public static boolean updateCtoFEPA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Cancelled")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateCtoSRA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Cancelled")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateCtoR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csCancelled,"Cancelled")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Cancelled")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Rejected");
						CostsheetInternal.verifyCSStatus("Rejected",csCancelled);
						//Revert back status
						updateCSStatus("Cancelled");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Rejected");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Cancelled",csCancelled,data);
				}
				
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	/********2*********/
	//InWork to Ready For Review
	public static Boolean updateRtoIW(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csRejected,"Rejected")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Rejected")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("In Work");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Rejected",csRejected,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
			//}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRtoRFR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csRejected,"Rejected")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Rejected")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Ready for Review");
						CostsheetInternal.verifyCSStatus("Ready for Review",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Ready for Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Rejected",csRejected,data);
				}
				// close the open cost sheet
				//	CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Initial Quote Approved
	public static boolean updateRtoUR(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csRejected,"Rejected")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Rejected")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Under Review");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Rejected",csRejected,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}

	//InWork to Ready For Review
	public static boolean updateRtoIQA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csRejected,"Rejected")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Rejected")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Initial Quote Approved");
						CostsheetInternal.verifyCSStatus("Initial Quote Approved",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Rejected",csRejected,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRtoSG4A(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csRejected,"Rejected")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Rejected")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("SG4 Approved");
						CostsheetInternal.verifyCSStatus("SG4 Approved",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("SG4 Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Rejected",csRejected,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRtoFEPA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csRejected,"Rejected")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Rejected")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("FEP Approved");
						CostsheetInternal.verifyCSStatus("FEP Approved",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("FEP Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Rejected",csRejected,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRtoSRA(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csRejected,"Rejected")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Rejected")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Seasonal Review Approved");
						CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Seasonal Review Approved");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Rejected",csRejected,data);
				}
				// close the open cost sheet
				//CostsheetInternal.closeTheOpenCostSheet();
		//	}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}
	//InWork to Ready For Review
	public static boolean updateRtoC(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			//if(data[5].equalsIgnoreCase("Yes")){
				if(!identifyTab(csRejected,"Rejected")) {
					CSVendorRetailMainUser.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				}
				if(validateStatus("Rejected")){
					if(data[4].equalsIgnoreCase("Admin")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("MatAdm")) {
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csRejected);
						//Revert back status
						updateCSStatus("Rejected");
					}
					if(data[4].equalsIgnoreCase("Costing")||data[4].equalsIgnoreCase("engwithCost")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
					if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")) {
						CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
						CommonFunctions.waitForVisibilityOfElement(csStatus);
						//Verification
						Assert.assertEquals(driver.findElements(csStatus).size(),1);
						//Verify dropdown is not present
						Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
						log.info("Verified"+ strTestCaseName + " Cost Sheet Status is not editable");
						CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					if(data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Cancelled");
						verifyErrorMessageStatus();
					}
				}
				else {
					//Revert status to inwork 
					revertStatus("Rejected",csRejected,data);
				}
				
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}
	}


	/*****************************************************/
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
		}catch(Exception e){
			fail=true;
			log.error("Exception in identifyTab()", e);
			return false;
		}
	}

	public static boolean validateStatus(String statusCS) throws Exception{
		try{
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
			CSVendorRetailMainUser.clickOnCostSheetListTab();
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
			log.error("Exception in reLogin()", e);
			return false;
		}
	}

	/*************************************************************************/
	//In Work
	public static String createRetailCS_inwork(String[] data) throws Exception{
		try{
			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			log.info("**********Inside********");
			strCSInWork = driver.findElement(csHeading).getText().substring(20);
			String []varCSInWork = strCSInWork.split("Actions:");
			csInWork = varCSInWork[0].trim();

			log.info("Retail  costsheet in Inwork status is : "+csInWork);
			CostsheetInternal.closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCS()", e);
			return "";
		}
		return csInWork;
	}	
	//underReview
	public static String createRetailCS_underReview(String[] data,String sStatus) throws Exception{
		try{
			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSUnderReview = driver.findElement(csHeading).getText().substring(20);
			String []varCSUnderReview = strCSUnderReview.split("Actions:");
			csUnderReview = varCSUnderReview[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			log.info("Retail  costsheet in underReview status is: "+csUnderReview);

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return "";
		}
		return csUnderReview;
	}
	/*******************************************************************************************************************/
	//ReadyforReview
	public static String createVenRetCS_ReadyforReview(String[] data,String sStatus) throws Exception{
		try{
			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//read newly created cost sheet
			strCSReadyforReview = driver.findElement(csHeading).getText().substring(20);
			String []varCSReadyforReview = strCSReadyforReview.split("Actions:");
			csReadyforReview = varCSReadyforReview[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail  costsheet in ReadyforReview status is: "+csReadyforReview);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVenRetCS_ReadyforReview()", e);
			return "";
		}
		return csReadyforReview;
	}	

	//initialQuoteApproved
	public static String createVenRetCS_initialQuoteApproved(String[] data,String sStatus) throws Exception{
		String csReadyforReview;
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSInitialQuoteApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSInitialQuoteApproved = strCSInitialQuoteApproved.split("Actions:");
			csInitialQuoteApproved = varCSInitialQuoteApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail costsheet in initialQuoteApproved status is: "+csInitialQuoteApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVenRetCS_initialQuoteApproved()", e);
			return "";
		}
		return csInitialQuoteApproved;
	}

	//SG4 Approved
	public static String createVenRetCS_SG4Approved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSSG4Approved = driver.findElement(csHeading).getText().substring(20);
			String []varCSSG4Approved = strCSSG4Approved.split("Actions:");
			csSG4Approved = varCSSG4Approved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail costsheet in SG4Approved status is: "+csSG4Approved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVenRetCS_SG4Approved()", e);
			return "";
		}
		return csSG4Approved;
	}

	//FEP Approved
	public static String createVenRetCS_FEPApproved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSFEPApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSFEPApproved = strCSFEPApproved.split("Actions:");
			csFEPApproved = varCSFEPApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail costsheet in FEPApproved status is: "+csFEPApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVenRetCS_FEPApproved()", e);
			return "";
		}
		return csFEPApproved;
	}

	//SeasonalReviewApproved
	public static String createVenRetCS_seasonalReviewApproved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//read newly created cost sheet
			strCSSeasonalReviewApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSSeasonalReviewApproved = strCSSeasonalReviewApproved.split("Actions:");
			csSeasonalReviewApproved = varCSSeasonalReviewApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retailcostsheet in seasonalReviewApproved status is: "+csSeasonalReviewApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVenRetCS_seasonalReviewApproved()", e);
			return "";
		}
		return csSeasonalReviewApproved;
	}



	public static Boolean createCS(String[] data) throws Exception{
		try{
			if(driver.findElements(tabCostsheet).size() == 0) {
				CommonProjectFunctions.searchProduct(data[6]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[7]);
				CommonProjectFunctions.clickCostingTab();
				if(!data[4].equalsIgnoreCase("vendor")) {
					strSource=AddSource(data);
				//	strSpec=InternalBOMSoftG.AddSpecification(data);
				//	strCW=InternalBOMSoftG.AddColorway(data);
				}
			}
			clickOnCostSheetListTab();
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(lstcostingActions,"Create Cost Sheet"); //hard coding
			Thread.sleep(1000);	
			System.out.println(By.xpath("//a[text()='"+data[2]+"']"));
			// select  type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[2]+"']"),"link","Cost sheet type");
			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,1);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
			// select QuoteCurrency
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return false;
		}
	}

	//Cancelled
	public static String createRetailCS_Cancelled(String[] data,String sStatus) throws Exception{
		try{
			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSCancelled= driver.findElement(csHeading).getText().substring(20);
			String []varCSCancelled = strCSCancelled.split("Actions:");
			csCancelled = varCSCancelled[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail costsheet in Cancelled status is: "+csCancelled);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createRetailCS_Cancelled()", e);
			return "";
		}
		return csCancelled;
	}
	//Rejected
	public static String createVenRetCS_Rejected(String[] data,String sStatus) throws Exception{
		try{
			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSRejected = driver.findElement(csHeading).getText().substring(20);
			String []varCSRejected = strCSRejected.split("Actions:");
			csRejected = varCSRejected[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail costsheet in Rejected status is: "+csRejected);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVenRetCS_Rejected()", e);
			return "";
		}
		return csRejected;
	}

	/*public static String createInternalRetailCS_forDelete(String[] data) throws Exception{
		try{
			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//read newly created cost sheet
			strCSforD = driver.findElement(csHeading).getText().substring(20);
			String []varCSForD = strCSforD.split("Actions:");
			csForD = varCSForD[0].trim();
			log.info("Internal Retail Item Cost Sheet in Inwork status is : "+csForD);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in createInternalRetailCS_forDelete()", e);
			return "";
		}
		return csForD;
	}		
	 */
		
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
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
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
			List<WebElement> row1 = driver.findElements(By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]"));
			//Previous xpath : 
			// horizontal ://div[@id='costSheetResults']//div[3]/table/tbody/tr/td
			//vertical : //div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]
			System.out.println(row1.size());
			for(WebElement e: row1){
				colIndex=colIndex+1;
				csName =e.getText().trim(); //.replaceAll("\\s+", " ");
				String csName1 =e.getText().trim().replaceAll("\\s+", " ");
				//System.out.println("current row CS): "+ csName);
				//System.out.println("expected CS: "+costSheetName);
				if (csName1.equals(costSheetName)) {
					Thread.sleep(2000);
					//System.out.println(By.xpath("//a[text()='"+csName+"']"));
					CommonFunctions.clickButtonOrLink(By.linkText(csName),"link","Cost sheet name");
					//driver.findElement(By.xpath("//a[text()='"+csName+"']")).click();
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
			log.error("Exception in fillCreateBOM()", e);
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
			log.info("Verified error message as : " +errMsgStatus +"for" + strTestCaseName);
			CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateCSStatus()", e);
			return false;
		}
	}

	//*********************************************************************************************	


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

