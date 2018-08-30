package com.hasbro.security;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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


public class CostsheetExternalRetail extends TestsuiteBase{


	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String strTestCaseName = null;
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

	int y=0;
	static String errMsg="Only the Primary Cost Sheet Attribute may be changed at this time. Please click on Cancel to exit.";
	static String overheadCosterrMsg="Access Denied. Costsheet state is Initial Quote Approved" ;

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
			log.info("Inside Test Case:-> " + strTestCaseName + " for External Costsheet  Security");				
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
			//Prerequisite

			if(data[3].equalsIgnoreCase("CreateInWork"))
			{ createRetailCS_inwork(data); }

			if(data[3].equalsIgnoreCase("CreateReadyforReview"))
			{ createVenRetCS_ReadyforReview(data,"Ready for Review"); }

			if(data[3].equalsIgnoreCase("CreateUnderReview"))
			{ createRetailCS_underReview(data,"Under Review"); }

			if(data[3].equalsIgnoreCase("CreateInitialQuoteApproved"))
			{ createVenRetCS_initialQuoteApproved(data,"Initial Quote Approved"); }

			if(data[3].equalsIgnoreCase("CreateSG4Approved"))
			{ createVenRetCS_SG4Approved(data,"SG4 Approved"); }

			if(data[3].equalsIgnoreCase("CreateFEPApproved"))
			{ createVenRetCS_FEPApproved(data,"FEP Approved"); }

			if(data[3].equalsIgnoreCase("CreateSeasonalReviewApproved"))
			{ createVenRetCS_seasonalReviewApproved(data,"Seasonal Review Approved"); }

			if(data[3].equalsIgnoreCase("CreateCancelled"))
			{ createRetailCS_Cancelled(data,"Cancelled"); }

			if(data[3].equalsIgnoreCase("CreateRejected"))
			{ createVenRetCS_Rejected(data,"Rejected"); }
			//Other costsheet
			if(data[3].equalsIgnoreCase("CreateforDelete"))
			{ createInternalRetailCS_forDelete(data); }

			if(data[3].equalsIgnoreCase("CreateCS"))
			{ 
				log.info("In side :-> " + data[3]);
				createExternalCS(data);			           			         
				log.info("Out side :-> " + data[3]);
			}

			/************************************************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewCSVendor"))
			{ 
				log.info("In side :-> " + data[3]);	
				readViewCSVendor(data);		         
				log.info("Out side ReadViewCS");
			}
			if(data[3].equalsIgnoreCase("UpdateCSVendor"))
			{ 
				log.info("In side :-> " + data[3]);
				updateCSVendor(data);		         
				log.info("Out side UpdateCostsheet");
			}
			//Delete
			if(data[3].equalsIgnoreCase("DeleteCSVendor"))
			{ 
				log.info("In side :-> " + data[3]);
				DeleteCSVendor(data);		         
				log.info("Out side DeleteCostsheet");
			}

			/************************************************************************************************************************/
			//Inwork 
			if(data[3].equalsIgnoreCase("ReadViewCSInWork"))
			{ 
				log.info("In side :-> " + data[3]);	
				readViewCSInWork(data);		        
				log.info("Out side ReadViewCSInWork");

			}
			if(data[3].equalsIgnoreCase("UpdateCSInWork"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSInWork(data);		         
				log.info("Out side UpdateCSInWork");

			}	
			/*************************************************************************/
			//ReadyforReview
			if(data[3].equalsIgnoreCase("ReadViewCSReadyforReview"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSReadyforReview(data);		        
				log.info("Out side ReadViewCSReadyforReview");
			}
			if(data[3].equalsIgnoreCase("UpdateCSReadyforReview"))
			{ 
				log.info("In side :-> " + data[3]);
				updateCSReadyforReview(data);	
				log.info("Out side UpdateCSReadyforReview");
			}	
			/*************************************************************************/
			//UnderReview
			if(data[3].equalsIgnoreCase("ReadViewCSUnderReview"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSUnderReview(data);
				log.info("Out side :-> " + data[3]);

			}						
			if(data[3].equalsIgnoreCase("UpdateCSUnderReview"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSUnderReview(data);		         
				log.info("Out side :-> " + data[3]);

			}
			/*************************************************************************/
			//InitialQuoteApproved
			if(data[3].equalsIgnoreCase("ReadViewCSInitialQuoteApproved"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSInitialQuoteApproved(data);		        
				log.info("Out side ReadViewCSInitialQuoteApproved");
			}						
			if(data[3].equalsIgnoreCase("UpdateCSInitialQuoteApproved"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSInitialQuoteApproved(data);		         
				log.info("Out side :-> " + data[3]);
			}
			/*************************************************************************/
			//SG4Approved
			if(data[3].equalsIgnoreCase("ReadViewCSSG4Approved"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSSG4Approved(data); 
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateCSSG4Approved"))
			{ 
				log.info("In side :-> " + data[3]);
				updateCSSG4Approved(data);  
				log.info("Out side :-> " + data[3]); 
			}
			/*************************************************************************/
			//FEPApproved
			if(data[3].equalsIgnoreCase("ReadViewCSFEPApproved"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSFEPApproved(data); 
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateCSFEPApproved"))
			{ 
				log.info("In side :-> " + data[3]);
				updateCSFEPApproved(data);  
				log.info("Out side :-> " + data[3]);
			}
			/*************************************************************************/
			//SeasonalReviewApproved
			if(data[3].equalsIgnoreCase("ReadViewCSSeasonalReviewApproved"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSSeasonalReviewApproved(data); 
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateCSSeasonalReviewApproved"))
			{ 
				log.info("In side :-> " + data[3]);
				updateCSSeasonalReviewApproved(data);  
				log.info("Out side :-> " + data[3]); 
			}
			/*************************************************************************/
			//Cancelled
			if(data[3].equalsIgnoreCase("ReadViewCSCancelled"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSCancelled(data);	
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateCSCancelled"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSCancelled(data);	
				log.info("Out side :-> " + data[3]);
			}
			/*************************************************************************/
			//Rejected
			if(data[3].equalsIgnoreCase("ReadViewCSRejected"))
			{ 
				log.info("In side :-> " + data[3]);	
				readViewCSRejected(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateCSRejected"))
			{ 
				log.info("In side :-> " + data[3]);
				updateCSRejected(data);		         
				log.info("Out side :-> " + data[3]);
			}
			/********************************************************************************/

			if(data[3].equalsIgnoreCase("ReadViewGeneralAttributes"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyGeneralAttributesReadView(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateGeneralAttributes"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyGeneralAttributesUpdate(data);		         
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewVendorCSStatus"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewVendorCSStatus(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVendorCSStatus"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateVendorCSStatus(data);
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewVendorPrimaryCS"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyPrimaryCostSheetReadView(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVendorPrimaryCS"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyPrimaryCostSheetUpdate(data);
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewVendorCostDetails"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewVendorCostDetail(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVendorCostDetails"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateVendorCostDetail(data);
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewOverheadCost"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewOverheadCost(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateOverheadCost"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateOverheadCost(data);
				log.info("Out side " + data[3]);
			}
			/***************************Vendor Retail Item Cost - Quote Currency**************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewVRICQC"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyRICostQuoteCurrencyReadView(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVRICQC"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyRICostQuoteCurrencyUpdate(data);
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewVRICUC"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyRICostUSDConversionReadView(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVRICUC"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyRICostUSDConversionUpdate(data);
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewCSComments"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewCSComments(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateCSComments"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateCSComments(data);
				log.info("Out side " + data[3]);
			}
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewProcessHistory"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyProcessHistoryReadView(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateProcessHistory"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyProcessHistoryUpdate(data);
				log.info("Out side " + data[3]);
			}
		}catch(Throwable t){
			fail=true;
			log.info("****Varification failed or Skipped for**** "+strTestCaseName);
			ErrorUtil.addVerificationFailure(t);
		}	
	}

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
			//	WebElement newcostsheetTitle = driver.findElement());

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

	public static Boolean createCS(String[] data) throws Exception{
		try{
			if(driver.findElements(tabCostsheet).size() == 0) {
				CommonProjectFunctions.searchProduct(data[6]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[7]);
				CommonProjectFunctions.clickCostingTab();
				if(!data[4].equalsIgnoreCase("vendor")) {
					strSource=AddSource(data);
					strSpec=InternalBOMSoftG.AddSpecification(data);
					strCW=InternalBOMSoftG.AddColorway(data);
				}
			}
			clickOnCostSheetListTab();
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(lstcostingActions,"Create Cost Sheet"); //hard coding
			Thread.sleep(1000);	
			System.out.println(By.xpath("//a[text()='"+data[2]+"']"));
			// select  type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[2]+"']"),"link","Cost sheet type");

			//a[contains(text(),'"+data[2]+"')]
			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,1);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
			/*if(!data[4].equalsIgnoreCase("vendor")) {
				// select wave
				CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[12]);
			}*/
			// select QuoteCurrency
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			/*if(data[2].equalsIgnoreCase("Tooling Internal")) {
				CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[14]);
			}*/
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


	public static String createInternalRetailCS_forDelete(String[] data) throws Exception{
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

	public static boolean createExternalCS(String[] data) throws Exception{
		try{
			if(data[5].equalsIgnoreCase("Yes")){
				createCS(data);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//read newly created cost sheet
				strCostSheetName = driver.findElement(CostsheetExternalRetail.csHeading).getText().substring(20);
				//strCostSheetName = newcostsheetTitle.getText().substring(20);
				String []varCostSheetName = strCostSheetName.split("Actions:");
				cstNewCostSheetName = varCostSheetName[0].trim();
				log.info("Created costsheet name is :" + cstNewCostSheetName);

				//Vendor can not change 'inwork' to 'Initial Quote Approved' hence not updating for vendor
				if(data[4].equalsIgnoreCase("Admin")) {
					//Update Status
					updateCSStatus("Initial Quote Approved");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No")){					
				CostsheetInternal.nevigationToCostsheet(data);
				//		if(!data[4].contains("vendor")) {
				CostsheetExternalRetail.clickOnCostSheetListTab();
				CommonFunctions.waitForVisibilityOfElement(CostsheetExternalRetail.costAction);
				Select select = new Select(driver.findElement(CostsheetExternalRetail.costAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification("Create Cost Sheet",options);
				if(bVal)
				{
					// select Create cost sheet action					
					CommonFunctions.selectFromDropDownByVisibleText(lstcostingActions,"Create Cost Sheet");
					Thread.sleep(2000);
					Assert.assertEquals(driver.findElements(typeVendor).size(),0);
					log.info("Verified Vendor is not present");
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Button");
				}
				else {
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Create Cost Sheet option is not present");
					log.info("Create Cost Sheet option is not present in Action dropdown");
				}
			}	
			else{
				log.info("User dont have access to createExternalCS");
			}
			return true;

		}catch(Exception e){
			fail=true;
			log.error("Exception in createExternalCS()", e);
			return false;
		}
	}	

	/*public static boolean createExternalCS(String[] data) throws Exception{
		try{
			if(data[5].equalsIgnoreCase("Yes")){
				createCS(data);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//read newly created cost sheet
				strCostSheetName = driver.findElement(CostsheetExternal.csHeading).getText().substring(20);
				//strCostSheetName = newcostsheetTitle.getText().substring(20);
				String []varCostSheetName = strCostSheetName.split("Actions:");
				cstNewCostSheetName = varCostSheetName[0].trim();
				log.info("Created costsheet name is :" + cstNewCostSheetName);

				//Vendor can not change 'inwork' to 'Initial Quote Approved' hence not updating for vendor
				if(data[4].equalsIgnoreCase("Admin")) {
				//Update Status
				updateCSStatus("Initial Quote Approved");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No")){					
				CostsheetInternal.nevigationToCostsheet(data);
				//		if(!data[4].contains("vendor")) {
				CostsheetExternal.clickOnCostSheetListTab();
				CommonFunctions.waitForVisibilityOfElement(CostsheetExternal.costAction);
				Select select = new Select(driver.findElement(CostsheetExternal.costAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification("Create Cost Sheet",options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal,"Create Cost Sheet option is not present");
				log.info("Create Cost Sheet option is not present in Action dropdown");
				CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Button");
			}	
			else{
				log.info("User dont have access to createExternalCS");
			}
			return true;

		}catch(Exception e){
			fail=true;
			log.error("Exception in createExternalCS()", e);
			return false;
		}
	}*/	

	/*******************************************************************************************************************************/
	public static boolean readViewCSVendor(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			sStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verification
				Assert.assertEquals(driver.findElements(tblCostSheetIdentification).size(),1);
				log.info("Verified CostSheetIdentification");
				Assert.assertEquals(driver.findElements(CostsheetInternal.costSheetVariations).size(),1);
				log.info("Verified costSheetVariations");
				//	Assert.assertEquals(driver.findElements(labelPrimaryCS).size(),1);
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csApproved);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verified " +csApproved + "Cost sheet is not present in list ");
				//	}
				/*else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this ReadViewCostsheet is not applicable(NA)");
			}

			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return false;
		}
	}


	public static boolean updateCSVendor(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Change Status
				updateCSStatus("In Work");
				CostsheetInternal.verifyCSStatus("In Work",csApproved);
				//change back to Approved
				updateCSStatus("Initial Quote Approved");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (sStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Cost sheet is not present");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (sStatus.equalsIgnoreCase("Yes")) {
					// click on Cost sheet List tab
					CostsheetExternalRetail.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verified"+ strTestCaseName + " Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in Update Cost sheet()", e);
			return false;
		}

	}	
	public static boolean DeleteCSVendor(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Create Costsheet to delete
				searchandClickforRequriedCostsheet(csForD);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//strSource=InternalBOMSoftG.AddSource(data);
				//strCS = createVendorRetailToolingCS_inwork(data);
				//	CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetActionDelete,data[13]);	
				driver.findElement(CostsheetExternalRetail.lstCostSheetAction).click();
				driver.findElement(CostsheetInternal.lstCostSheetActionDelete).click();
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.waitForVisibilityOfElement(CostsheetExternalRetail.tabCostsheet);
				bCostSheet= SearchCostsheet(csForD);
				Assert.assertFalse(bCostSheet, "Cost sheet has been deleted.");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has been deleted");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{								

				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);  //cstNewCostSheetName
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//verifyUserCostsheetAccess(data);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal," Delete option is not present");
				log.info("Verification(" +strTestCaseName+ ") : Delete option is not present");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
				/*	}
				else {
					verifySourceForVendor(data);
				}*/
			}else{
				log.info("Delete Cost sheet is not applicable(NA)");
			}							
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}	

	public static String readViewCSInWork(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			inWorkStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInWork);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "In Work");
				log.info("Verification(" +strTestCaseName+ ") : status as 'In Work' verified.");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csInWork);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return "";
		}	
		return inWorkStatus;
	}


	public static boolean updateCSInWork(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");

					//Change Status
					updateCSStatus("Ready for Review");
					CostsheetInternal.verifyCSStatus("Ready for Review",csInWork);
					/**************************************/
					//change back to inwork
					updateCSStatus("In Work");
					//Change to Cancelled
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csInWork);
					/**************************************/
					//change back to inwork
					updateCSStatus("In Work");
					//Change to Approved
					updateCSStatus("Initial Quote Approved");
					CostsheetInternal.verifyCSStatus("Initial Quote Approved",csInWork);
					/**************************************/
					updateCSStatus("In Work");
					//Change
					updateCSStatus("SG4 Approved");
					CostsheetInternal.verifyCSStatus("SG4 Approved",csInWork);
					/**************************************/
					updateCSStatus("In Work");
					//Change
					updateCSStatus("FEP Approved");
					CostsheetInternal.verifyCSStatus("FEP Approved",csInWork);
					/**************************************/
					updateCSStatus("In Work");
					//Change
					updateCSStatus("Seasonal Review Approved");
					CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csInWork);
					/**************************************/
					updateCSStatus("In Work");
					//Change
					updateCSStatus("Rejected");
					CostsheetInternal.verifyCSStatus("Rejected",csInWork);
					/**************************************/
					updateCSStatus("In Work");
					//Change
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csInWork);
					//	}
					//change back to inwork
					updateCSStatus("In Work");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (inWorkStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csInWork);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (inWorkStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetInWork()", e);
			return false;
		}

	}	
	public static boolean readViewCSUnderReview(String[] data) throws Exception
	{	
		try{	
			CostsheetInternal.nevigationToCostsheet(data);
			underReviewStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csUnderReview);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Under Review");
				log.info("Verifified(" +strTestCaseName+ ") : status as Under Review");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csUnderReview);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}

		return true;
	}


	public static boolean updateCSUnderReview(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");

					//Change Status
					updateCSStatus("Ready for Review");
					CostsheetInternal.verifyCSStatus("Ready for Review",csUnderReview);
					/**************************************/
					//change back to inwork
					updateCSStatus("Under Review");
					//Change to Cancelled
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csUnderReview);
					/**************************************/
					//change back 
					updateCSStatus("Under Review");
					//Change to Approved
					updateCSStatus("Initial Quote Approved");
					CostsheetInternal.verifyCSStatus("Initial Quote Approved",csUnderReview);
					/**************************************/
					updateCSStatus("Under Review");
					//Change
					updateCSStatus("SG4 Approved");
					CostsheetInternal.verifyCSStatus("SG4 Approved",csUnderReview);
					/**************************************/
					updateCSStatus("Under Review");
					//Change
					updateCSStatus("FEP Approved");
					CostsheetInternal.verifyCSStatus("FEP Approved",csUnderReview);
					/**************************************/
					updateCSStatus("Under Review");
					//Change
					updateCSStatus("Seasonal Review Approved");
					CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csUnderReview);
					/**************************************/
					updateCSStatus("Under Review");
					//Change
					updateCSStatus("Rejected");
					CostsheetInternal.verifyCSStatus("Rejected",csUnderReview);
					/**************************************/
					updateCSStatus("Under Review");
					//Change
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csUnderReview);
					//	}
					//change back to inwork
					updateCSStatus("Under Review");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (underReviewStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csUnderReview);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (underReviewStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetUnderReview()", e);
			return false;
		}

	}
	public static boolean readViewCSReadyforReview(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			readyforReviewStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csReadyforReview);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "Ready for Review");
				log.info("Verification(" +strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csReadyforReview);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}	
		return true;
	}

	public static boolean updateCSReadyforReview(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					//Change Status
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csReadyforReview);
					/**************************************/
					//change back 
					updateCSStatus("Ready for Review");
					//Change 
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csReadyforReview);
					/**************************************/
					//change back 
					updateCSStatus("Ready for Review");
					//Change 
					updateCSStatus("Initial Quote Approved");
					CostsheetInternal.verifyCSStatus("Initial Quote Approved",csReadyforReview);
					/**************************************/
					updateCSStatus("Ready for Review");
					//Change
					updateCSStatus("SG4 Approved");
					CostsheetInternal.verifyCSStatus("SG4 Approved",csReadyforReview);
					/**************************************/
					updateCSStatus("Ready for Review");
					//Change
					updateCSStatus("FEP Approved");
					CostsheetInternal.verifyCSStatus("FEP Approved",csReadyforReview);
					/**************************************/
					updateCSStatus("Ready for Review");
					//Change
					updateCSStatus("Seasonal Review Approved");
					CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csReadyforReview);
					/**************************************/
					updateCSStatus("Ready for Review");
					//Change
					updateCSStatus("Rejected");
					CostsheetInternal.verifyCSStatus("Rejected",csReadyforReview);
					/**************************************/
					updateCSStatus("Ready for Review");
					//Change
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csReadyforReview);
					/**************************************/
					//Changed back
					updateCSStatus("Ready for Review");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (inWorkStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csReadyforReview);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*	}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (inWorkStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csReadyforReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;

			log.error("Exception in UpdateCostsheetInitialQuoteApproved()", e);
			return false;
		}

	}

	public static boolean readViewCSInitialQuoteApproved(String[] data) throws Exception
	{
		try{

			CostsheetInternal.nevigationToCostsheet(data);
			initialQuoteApprovedStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "Initial Quote Approved");
				log.info("Verification(" +strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csInitialQuoteApproved);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}

		return true;
	}

	public static boolean updateCSInitialQuoteApproved(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Change Status
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csInitialQuoteApproved);
					/**************************************/
					//change back 
					updateCSStatus("Initial Quote Approved");
					//Change 
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csInitialQuoteApproved);
					/**************************************/
					//change back 
					updateCSStatus("Initial Quote Approved");
					//Change 
					updateCSStatus("Ready for Review");
					CostsheetInternal.verifyCSStatus("Ready for Review",csInitialQuoteApproved);
					/**************************************/
					updateCSStatus("Initial Quote Approved");
					//Change
					updateCSStatus("SG4 Approved");
					CostsheetInternal.verifyCSStatus("SG4 Approved",csInitialQuoteApproved);
					/**************************************/
					updateCSStatus("Initial Quote Approved");
					//Change
					updateCSStatus("FEP Approved");
					CostsheetInternal.verifyCSStatus("FEP Approved",csInitialQuoteApproved);
					/**************************************/
					updateCSStatus("Initial Quote Approved");
					//Change
					updateCSStatus("Seasonal Review Approved");
					CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csInitialQuoteApproved);
					/**************************************/
					updateCSStatus("Initial Quote Approved");
					//Change
					updateCSStatus("Rejected");
					CostsheetInternal.verifyCSStatus("Rejected",csInitialQuoteApproved);
					/**************************************/
					updateCSStatus("Initial Quote Approved");
					//Change
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csInitialQuoteApproved);
					/**************************************/
					//Changed back
					updateCSStatus("Initial Quote Approved");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (initialQuoteApprovedStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csInitialQuoteApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (initialQuoteApprovedStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;

			log.error("Exception in " + data[3], e);
			return false;
		}

	}	

	public static boolean readViewCSSG4Approved(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			sg4ApprovedStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csSG4Approved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "SG4 Approved");
				log.info("Verification(" +strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csSG4Approved);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	public static boolean updateCSSG4Approved(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Change Status
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csSG4Approved);
					/**************************************/
					//change back 
					updateCSStatus("SG4 Approved");
					//Change 
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csSG4Approved);
					/**************************************/
					//change back 
					updateCSStatus("SG4 Approved");
					//Change 
					updateCSStatus("Ready for Review");
					CostsheetInternal.verifyCSStatus("Ready for Review",csSG4Approved);
					/**************************************/
					updateCSStatus("SG4 Approved");
					//Change
					updateCSStatus("Initial Quote Approved ");
					CostsheetInternal.verifyCSStatus("Initial Quote Approved ",csSG4Approved);
					/**************************************/
					updateCSStatus("SG4 Approved");
					//Change
					updateCSStatus("FEP Approved");
					CostsheetInternal.verifyCSStatus("FEP Approved",csSG4Approved);
					/**************************************/
					updateCSStatus("SG4 Approved");
					//Change
					updateCSStatus("Seasonal Review Approved");
					CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csSG4Approved);
					/**************************************/
					updateCSStatus("SG4 Approved");
					//Change
					updateCSStatus("Rejected");
					CostsheetInternal.verifyCSStatus("Rejected",csSG4Approved);
					/**************************************/
					updateCSStatus("SG4 Approved");
					//Change
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csSG4Approved);
					/**************************************/
					//Changed back
					updateCSStatus("SG4 Approved");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (sg4ApprovedStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csSG4Approved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (sg4ApprovedStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csSG4Approved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}

	}


	public static boolean readViewCSFEPApproved(String[] data) throws Exception
	{
		try
		{
			CostsheetInternal.nevigationToCostsheet(data);
			fepApprovedStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csFEPApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "FEP Approved");
				log.info("Verification(" +strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csFEPApproved);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}

	public static boolean updateCSFEPApproved(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Change Status
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csFEPApproved);
					/**************************************/
					//change back 
					updateCSStatus("FEP Approved");
					//Change 
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csFEPApproved);
					/**************************************/
					//change back 
					updateCSStatus("FEP Approved");
					//Change 
					updateCSStatus("Ready for Review");
					CostsheetInternal.verifyCSStatus("Ready for Review",csFEPApproved);
					/**************************************/
					updateCSStatus("FEP Approved");
					//Change
					updateCSStatus("Initial Quote Approved ");
					CostsheetInternal.verifyCSStatus("Initial Quote Approved ",csFEPApproved);
					/**************************************/
					updateCSStatus("FEP Approved");
					//Change
					updateCSStatus("SG4 Approved");
					CostsheetInternal.verifyCSStatus("SG4 Approved",csFEPApproved);
					/**************************************/
					updateCSStatus("FEP Approved");
					//Change
					updateCSStatus("Seasonal Review Approved");
					CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csFEPApproved);
					/**************************************/
					updateCSStatus("FEP Approved");
					//Change
					updateCSStatus("Rejected");
					CostsheetInternal.verifyCSStatus("Rejected",csFEPApproved);
					/**************************************/
					updateCSStatus("FEP Approved");
					//Change
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csFEPApproved);
					/**************************************/
					//Changed back
					updateCSStatus("FEP Approved");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (fepApprovedStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csFEPApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (fepApprovedStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csFEPApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}

	}	

	public static boolean readViewCSSeasonalReviewApproved(String[] data) throws Exception
	{
		try
		{
			CostsheetInternal.nevigationToCostsheet(data);
			seasonalReviewApprovedStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "Seasonal Review Approved");
				log.info("Verification(" +strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csSeasonalReviewApproved);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}

		return true;
	}
	public static boolean updateCSSeasonalReviewApproved(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Change Status
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csSeasonalReviewApproved);
					/**************************************/
					//change back 
					updateCSStatus("Seasonal Review Approved");
					//Change 
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csSeasonalReviewApproved);
					/**************************************/
					//change back 
					updateCSStatus("Seasonal Review Approved");
					//Change 
					updateCSStatus("Ready for Review");
					CostsheetInternal.verifyCSStatus("Ready for Review",csSeasonalReviewApproved);
					/**************************************/
					updateCSStatus("Seasonal Review Approved");
					//Change
					updateCSStatus("Initial Quote Approved ");
					CostsheetInternal.verifyCSStatus("Initial Quote Approved ",csSeasonalReviewApproved);
					/**************************************/
					updateCSStatus("Seasonal Review Approved");
					//Change
					updateCSStatus("SG4 Approved");
					CostsheetInternal.verifyCSStatus("SG4 Approved",csSeasonalReviewApproved);
					/**************************************/
					updateCSStatus("Seasonal Review Approved");
					//Change
					updateCSStatus("FEP Approved");
					CostsheetInternal.verifyCSStatus("FEP Approved",csSeasonalReviewApproved);
					/**************************************/
					updateCSStatus("Seasonal Review Approved");
					//Change
					updateCSStatus("Rejected");
					CostsheetInternal.verifyCSStatus("Rejected",csSeasonalReviewApproved);
					/**************************************/
					updateCSStatus("Seasonal Review Approved");
					//Change
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csSeasonalReviewApproved);
					/**************************************/
					//Changed back
					updateCSStatus("Seasonal Review Approved");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (seasonalReviewApprovedStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csSeasonalReviewApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (seasonalReviewApprovedStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}

	}	

	public static boolean readViewCSRejected(String[] data) throws Exception
	{
		try
		{
			CostsheetInternal.nevigationToCostsheet(data);
			RejectedStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csRejected);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "Rejected");
				log.info("Verification(" +strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csRejected);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}

		return true;
	}
	public static boolean updateCSRejected(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Change Status
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csRejected);
					/**************************************/
					//change back 
					updateCSStatus("Rejected");
					//Change 
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csRejected);
					/**************************************/
					//change back 
					updateCSStatus("Rejected");
					//Change 
					updateCSStatus("Ready for Review");
					CostsheetInternal.verifyCSStatus("Ready for Review",csRejected);
					/**************************************/
					updateCSStatus("Rejected");
					//Change
					updateCSStatus("Initial Quote Approved ");
					CostsheetInternal.verifyCSStatus("Initial Quote Approved ",csRejected);
					/**************************************/
					updateCSStatus("Rejected");
					//Change
					updateCSStatus("SG4 Approved");
					CostsheetInternal.verifyCSStatus("SG4 Approved",csRejected);
					/**************************************/
					updateCSStatus("Rejected");
					//Change
					updateCSStatus("FEP Approved");
					CostsheetInternal.verifyCSStatus("FEP Approved",csRejected);
					/**************************************/
					updateCSStatus("Rejected");
					//Change
					updateCSStatus("Seasonal Review Approved");
					CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csRejected);
					/**************************************/
					updateCSStatus("Rejected");
					//Change
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csRejected);
					/**************************************/
					//Changed back
					updateCSStatus("Seasonal Review Approved");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (RejectedStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csRejected);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (RejectedStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csRejected);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in UpdateCostsheetRejected", e);
			return false;
		}

	}
	/*public static String readViewCSApproved(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			approvedStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternal.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Approved");
				log.info("Verification(" +strTestCaseName+ ") : status as Approved");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

					bCostSheet= SearchCostsheet(csApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return "";
		}	
		return approvedStatus;
	}*/

	/*public static boolean updateCSApproved(String[] data) throws Exception 
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternal.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Change Status
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csApproved);
					//change back to Approved
					updateCSStatus("Approved");
					//Change to Cancelled
					updateCSStatus("Cancelled");
					CostsheetInternal.verifyCSStatus("Cancelled",csApproved);
					//change back to Approved
					updateCSStatus("Approved");
					//Change to Under Review
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csApproved);
					//change back to inwork
					updateCSStatus("Approved");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (approvedStatus.equalsIgnoreCase("No")) {

						bCostSheet= SearchCostsheet(csApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
				}
				else if (approvedStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verification(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}	
		return true;
	}*/

	public static boolean readViewCSCancelled(String[] data) throws Exception
	{ 
		try
		{
			CostsheetInternal.nevigationToCostsheet(data);
			cancelledStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csCancelled);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Cancelled");
				log.info("Verified(" +strTestCaseName+ ") : status as Cancelled");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{

				bCostSheet= SearchCostsheet(csCancelled);
				Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				log.info("Verified(" +strTestCaseName+ ") : Cost sheet has not been readable");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}}catch(Exception e){
				fail=true;
				log.error("Exception in " + data[3], e);
				return false;
			}

		return true;
	}

	public static boolean updateCSCancelled(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");

					//Change Status
					updateCSStatus("Ready for Review");
					CostsheetInternal.verifyCSStatus("Ready for Review",csCancelled);
					/**************************************/
					//change back to inwork
					updateCSStatus("Cancelled");
					//Change to Cancelled
					updateCSStatus("In Work");
					CostsheetInternal.verifyCSStatus("In Work",csCancelled);
					/**************************************/
					//change back 
					updateCSStatus("Cancelled");
					//Change to Approved
					updateCSStatus("Initial Quote Approved");
					CostsheetInternal.verifyCSStatus("Initial Quote Approved",csCancelled);
					/**************************************/
					updateCSStatus("Cancelled");
					//Change
					updateCSStatus("SG4 Approved");
					CostsheetInternal.verifyCSStatus("SG4 Approved",csCancelled);
					/**************************************/
					updateCSStatus("Cancelled");
					//Change
					updateCSStatus("FEP Approved");
					CostsheetInternal.verifyCSStatus("FEP Approved",csCancelled);
					/**************************************/
					updateCSStatus("Cancelled");
					//Change
					updateCSStatus("Seasonal Review Approved");
					CostsheetInternal.verifyCSStatus("Seasonal Review Approved",csCancelled);
					/**************************************/
					updateCSStatus("Cancelled");
					//Change
					updateCSStatus("Rejected");
					CostsheetInternal.verifyCSStatus("Rejected",csCancelled);
					/**************************************/
					updateCSStatus("Cancelled");
					//Change
					updateCSStatus("Under Review");
					CostsheetInternal.verifyCSStatus("Under Review",csCancelled);

					//change back to inwork
					updateCSStatus("Cancelled");
				}
				else if(data[4].equalsIgnoreCase("MatAdm")) {

				}
				else if(data[4].equalsIgnoreCase("engineer")) {

				}
				else if(data[4].equalsIgnoreCase("engLead")) {

				}
				else if(data[4].equalsIgnoreCase("engwithCost")) {

				}
				else if(data[4].equalsIgnoreCase("Costing")) {

				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (cancelledStatus.equalsIgnoreCase("No")) {

					bCostSheet= SearchCostsheet(csCancelled);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					log.info("Verified(" +strTestCaseName+ ") : Cost sheet has not been updatable");
					/*}
					else {
						verifySourceForVendor(data);
					}*/
				}
				else if (cancelledStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verified(" +strTestCaseName+ ") : Update option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_Costsheet()", e);
			return false;
		}

	}
	/************************************************************************************/
	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
					Assert.assertEquals(GALabel, " General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					log.error("General Attributes label is Absent");
					fail=true;
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{

				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					System.out.println("General Attirbutes label is Present");
					log.error("General Attirbutes label is Present");
					fail=true;
				}else{
					log.info("General Attirbutes label is Absent");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}

	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();

				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);

				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Select Update from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				//CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[25]);
				Assert.assertEquals(driver.findElements(prodType).size(),1);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +"prodType");
				Assert.assertEquals(driver.findElements(csSequenceNo).size(),1);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" Cost Sheet Sequence Number");
				Assert.assertEquals(driver.findElements(prodType).size(),1);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" prod Type");
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{

				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();

				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				//	}
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
				log.info("**********Inside********");
				//verifyUserCostsheetAccess(data);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(bVal) {
					//Select Update from Action dropdown
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					CommonFunctions.waitForVisibilityOfElement(csSequenceNo);
					Assert.assertEquals(driver.findElements(csSequenceNo).size(),1);
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +"Cost Sheet Sequence Number");
					Assert.assertEquals(driver.findElements(prodType).size(),1);
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +"prod Type");
					//Click on Save
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else {
					//dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +"option is not present");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
				/*	}
				else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}
	/**************************************************************************************************************/
	//Function consist scenario : ToolingCSStatus:Read_View
	public static boolean verifyReadViewVendorCSStatus(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(vendorCSStatus).size() != 0){
					String CSStatusLabel=driver.findElement(vendorCSStatus).getText();
					Assert.assertEquals(CSStatusLabel.trim(), "Vendor Cost Sheet Status:");
					log.info("Vendor Cost Sheet Status label is Present");
				}else{
					log.error("Vendor Cost Sheet Status label is Absent");
					fail=true;
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{

				log.info("No condition is applicable only for Vendor user");
				/*	}
				else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this verifyReadViewVendorCSStatus is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateVendorCSStatus(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(!data[4].contains("vendor")) {
					//updating status
					updateCSStatus("In Work");
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//verification
					CostsheetInternal.verifyCSStatus("In Work",csInitialQuoteApproved);
					//Reverted back Status
					updateCSStatus("Initial Quote Approved");
				}
				else {
					updateCSStatus("In Work");
					Assert.assertEquals(driver.findElement(statusErrorMsg).getText().trim(), "Sorry, You do not have the necessary privileges to update the Status field.");
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//verifyUserCostsheetAccess(data);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(bVal) {
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					//Verifying that Retail Item Cost (USD)is label not edit box
					Assert.assertEquals(driver.findElements(lstCSStatus).size(),0);
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" 'Vendor Cost Sheet Status' is not updatable");
					//Click on Cancel
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else {
					//dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : Update option not present");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}
	/*********************************Vendor Primary Cost Sheet***************************************/
	//Function consist scenario : PrimaryCostSheet:Read_View
	public static boolean verifyPrimaryCostSheetReadView(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				if(driver.findElements(labelPrimaryCS).size() != 0){
					String pCSLabel=driver.findElement(labelPrimaryCS).getText();
					Assert.assertEquals(pCSLabel.trim(), "Vendor Primary Cost Sheet:");
					log.info(" Primary Cost Sheet label is Present");
				}else{
					log.error(" Primary Cost Sheet label is Absent");
					fail=true;
				}
				//Verifying Primary cost sheet yes and No is present
				Assert.assertEquals(driver.findElements(PrimaryCSYN).size(),1);
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();	
			}
			else if(data[5].equalsIgnoreCase("No"))
			{

				if(driver.findElements(labelPrimaryCS).size() != 0){
					System.out.println("PrimaryCostSheet label is Present");
					log.error("PrimaryCostSheet label is Present");
					fail=true;
				}else{
					log.info("PrimaryCostSheet label is Absent");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();	
			}
			else
			{
				log.info("For this" + data[3]+ " is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}

	//PrimaryCostSheetUpdate
	public static boolean verifyPrimaryCostSheetUpdate(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				//updating PrimaryCostSheet check box
				//CommonFunctions.selectCheckbox(chkPriCS);
				Boolean bSelect = driver.findElement(chkPriCS).isSelected();
				if (bSelect) {
					driver.findElement(chkPriCS).click();
					bPriCS=driver.findElement(chkPriCS).isSelected();
				}
				else
				{
					driver.findElement(chkPriCS).click();
					bPriCS=driver.findElement(chkPriCS).isSelected();
				}
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				String sPrimaryCS=driver.findElement(lblPrimaryCS).getText();
				if (bPriCS) {
					Assert.assertEquals(sPrimaryCS.trim(),"Yes");
				}
				else {
					Assert.assertEquals(sPrimaryCS.trim(),"No");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(bVal) {
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					//Verifying 
					Assert.assertEquals(driver.findElements(chkPriCS).size(),0);
					log.info("Verified(" +strTestCaseName+ " :"+data[13] +" 'PrimaryCostSheet' is not updatable");
					//Click on Cancel
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else {
					//dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : Update option not present");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}
	/********************************** Vendor Cost Details************************************/
	public static boolean verifyReadViewVendorCostDetail(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(vendorCostDetails).size() != 0){
					String sVendorCostDetails=driver.findElement(vendorCostDetails).getText();
					Assert.assertEquals(sVendorCostDetails.trim(), "Vendor Cost Details:");
					log.info(" Vendor Cost Details label is Present");
				}else{
					log.error(" Vendor Cost Details label is Absent");
					fail=true;
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				log.info("No condition is applicable only for Vendor user");
			}
			else
			{
				log.info("For this Vendor Cost Details is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateVendorCostDetail(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				//updating comments
				driver.findElement(CostsheetInternal.updateComment).clear();
				CommonFunctions.enterTextInTextbox(CostsheetInternal.updateComment, data[15]);
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				if(!data[4].contains("vendor")) {
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					String sComment=driver.findElement(lblComment).getText();
					Assert.assertEquals(sComment.trim(),data[15]);
					log.info("Verified(" +strTestCaseName+ ") : Update of 'Vendor Cost Detail' section");
				}
				else {
					Assert.assertEquals(driver.findElement(pCSErrorMsg).getText().trim(),errMsg);
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//verifyUserCostsheetAccess(data);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				//	dropDownOptionVerification
				//Assert.assertFalse(bVal,data[13]+ " option is not present");
				//log.info("Verified(" +strTestCaseName+ ") :"+data[13] +"option is not present");
				if(bVal) {
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					//Verifying 
					Assert.assertEquals(driver.findElements(lstQuoteCurrency).size(),0);
					log.info("Verified(" +strTestCaseName+ " :"+data[13] +" ' Vendor Cost Details:' is not updatable");
					//Click on Cancel
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else {
					//dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : Update option not present");
				}

				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}
	/********************************** Overhead Cost************************************/
	public static boolean verifyReadViewOverheadCost(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(lblOverheadCost).size() != 0){
					String sOverHeadCost=driver.findElement(lblOverheadCost).getText();
					Assert.assertEquals(sOverHeadCost.trim(), "Overhead Cost:");
					log.info("Overhead Cost label is Present");
				}else{
					log.error("Overhead Cost label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(overheadCostView).size(),1);
				log.info("Verified(" +strTestCaseName+ ") : OverheadCost view is present");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verification
				Assert.assertEquals(driver.findElements(lblOverheadCost).size(),0);
				log.info("Verified(" +strTestCaseName+ ") : OverheadCost is not present");
			}
			else
			{
				log.info("For this verifyReadViewOverheadCost is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateOverheadCost(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				//click on edit
				CommonFunctions.clickButtonOrLink(overheadCostEdit, "btn", "Edit");
				CommonFunctions.waitForElementTobeClickable(overheadType);
				//Click on cell
				CommonFunctions.clickButtonOrLink(overheadType, "Cell", "overheadType");
				//select from dropdown
				CommonFunctions.selectFromDropDownByVisibleText(ddOverheadType, data[16]);
				CommonFunctions.clickButtonOrLink(btnDone, "Btn", "Done");

				if(!data[4].contains("vendor")) {
					CommonFunctions.waitForVisibilityOfElement(lblOverheadCost);
					//Verification
					Assert.assertEquals(driver.findElement(By.xpath("//td[text()='"+data[16]+"']")).getText().trim(),data[16]);
					log.info("Verified(" +strTestCaseName+ ") :Overhead Type");
				}
				else {
					Thread.sleep(2000);
					Assert.assertEquals(driver.findElement(ohCostErrorMsg).getText().trim(),overheadCosterrMsg);
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(bVal) {
					if(data[4].contains("engineer")||data[4].contains("engLead")) {
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					//Verifying 
					Assert.assertEquals(driver.findElements(lblOverheadCost).size(),0);
					log.info("Verified(" +strTestCaseName+ ") : OverheadCost is not present");
					//Click on Cancel
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
					}
					else {
						Assert.assertEquals(driver.findElements(lblOverheadCost).size(),1);
						Assert.assertEquals(driver.findElements(overheadCostEdit).size(),0);  
						log.info("Verified(" +strTestCaseName+ ") : OverheadCost label is present but edit is not present");
					}
				}
				else {
					//dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : Update option not present");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}
	/*****************************Vendor Retail Item Cost - Quote Currency***************************************/
	//Function consist scenario : RetailItemCostQuoteCurrency:Read_View
	public static boolean verifyRICostQuoteCurrencyReadView(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			// click on Cost sheet List tab
			CostsheetTooling.clickOnCostSheetListTab();
			// search cost sheet name in web table
			searchandClickforRequriedCostsheet(csInitialQuoteApproved);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			if(data[5].equalsIgnoreCase("Yes")){
				if(driver.findElements(ricQuoteCurr).size() != 0){
					String cQLabel=driver.findElement(ricQuoteCurr).getText();
					Assert.assertEquals(cQLabel.trim(), "Vendor Retail Item Cost - Quote Currency:");
					log.info("Retail Item Cost - Quote Currency label is Present");
				}else{
					log.error("Retail Item Cost - Quote Currency label is Absent");
					fail=true;
				}
				//Verification
				//Assert.assertEquals(driver.findElements(CostsheetInternal.retailItemCost).size(),1);
				Assert.assertEquals(driver.findElements(CostsheetInternal.plasticMaterial).size(),1);
				Assert.assertEquals(driver.findElements(CostsheetInternal.packagingMaterial).size(),1);
				Assert.assertEquals(driver.findElements(CostsheetInternal.chemicalMaterial).size(),1);
				Assert.assertEquals(driver.findElements(CostsheetInternal.purchasedMaterial).size(),1);
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				Assert.assertEquals(driver.findElements(ricQuoteCurr).size(),0);
				log.info("Retail Item Cost Quote Currency label is Absent");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();

			}
			else
			{
				log.info("For this" + data[3]+ " is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}


	//PrimaryCostSheetUpdate
	public static boolean verifyRICostQuoteCurrencyUpdate(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Select 'Update' from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				CommonFunctions.waitForVisibilityOfElement(txtRetailItemCost);
				//Verify 'Retail Item Cost' is not editable
				Assert.assertEquals(driver.findElements(txtRetailItemCost).size(),1);
				//updating Retail Item Cost 
				driver.findElement(txtPackMaterial).clear();
				CommonFunctions.enterTextInTextbox(txtPackMaterial, data[18]);
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				if(!data[4].contains("vendor")) {
					CommonFunctions.waitForVisibilityOfElement(lblPackMaterial);
					System.out.println(driver.findElement(lblPackMaterial).getText().trim());
					Assert.assertEquals(driver.findElement(lblPackMaterial).getText().trim(),data[18]);
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" :updation of Vendor Retail Item Cost - Quote Currency:");
				}
				else {
					Assert.assertEquals(driver.findElement(pCSErrorMsg).getText().trim(),errMsg);
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//verifyUserCostsheetAccess(data);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				//	dropDownOptionVerification
				//Assert.assertFalse(bVal,data[13]+ " option is not present");
				if(bVal) {
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					if(data[4].contains("engineer")||data[4].contains("engLead")) {
						//Verifying 
						Assert.assertEquals(driver.findElements(ricQuoteCurr).size(),0);
						log.info("Verified(" +strTestCaseName+ ") : Retail Item Cost Quote Currency label is Absent");
						}
						else {
							Assert.assertEquals(driver.findElements(txtPackMaterial).size(),0);
							log.info("Verified(" +strTestCaseName+ ") :  Vendor Retail Item Cost - Quote Currency is not editable");
						}
					//Click on Cancel
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else {
					//dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : Update option not present");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}

	/********************************** Vendor Retail Item Cost - USD Conversion:*************************************************/
	//Function consist scenario : Vendor Retail Item Cost - USD Conversion::Read_View
	public static boolean verifyRICostUSDConversionReadView(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			// click on Cost sheet List tab
			CostsheetTooling.clickOnCostSheetListTab();
			// search cost sheet name in web table
			searchandClickforRequriedCostsheet(csInitialQuoteApproved);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			if(data[5].equalsIgnoreCase("Yes")){

				if(driver.findElements(ricUSDConversion).size() != 0){
					String usdCLabel=driver.findElement(ricUSDConversion).getText();
					Assert.assertEquals(usdCLabel.trim(), "Vendor Retail Item Cost - USD Conversion:");
					log.info("Retail Item Cost - USD Conversion label is Present");
				}else{
					log.error("Retail Item Cost - USD Conversion label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(CostsheetInternal.retailItemCostUSDC).size(),1);
				Assert.assertEquals(driver.findElements(CostsheetInternal.plasticMaterialUSDC).size(),1);
				Assert.assertEquals(driver.findElements(CostsheetInternal.packagingMaterialUSDC).size(),1);
				Assert.assertEquals(driver.findElements(CostsheetInternal.chemicalMaterialUSDC).size(),1);
				Assert.assertEquals(driver.findElements(CostsheetInternal.purchasedMaterialUSDC).size(),1);
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				Assert.assertEquals(driver.findElements(ricUSDConversion).size(),0);
				log.info("Retail Item Cost - USD Conversion label is Absent");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this" + data[3]+ " is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}

	public static boolean verifyRICostUSDConversionUpdate(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("In security sheet for RICostUSDConversion it is No only,hence no code required for Yes ");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Verify Update is not present
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(data[4].contains("Admin")||data[4].contains("vendor")||data[4].contains("MatAdm")||data[4].contains("engwithCost")||data[4].contains("Costing")) {
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					//Verifying that Retail Item Cost (USD)is label not edit box
					Assert.assertEquals(driver.findElements(CostsheetInternal.retailItemCostUSDC).size(),1);
					Assert.assertEquals(driver.findElements(plasticMaterialUSD).size(),1);
					Assert.assertEquals(driver.findElements(CostsheetInternal.SoftGoodsMaterialUSDC).size(),1);
					//Click on Cancel
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else if(data[4].contains("engineer")||data[4].contains("engLead"))
						{
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					//Verifying that Retail Item Cost (USD)is label not edit box
					Assert.assertEquals(driver.findElements(ricUSDConversion).size(),0);
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : RICostUSDConversion not present");
					Assert.assertEquals(driver.findElements(CostsheetInternal.retailItemCostUSDC).size(),0);
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : retailItemCostUSDC not present");
					Assert.assertEquals(driver.findElements(plasticMaterialUSD).size(),0);
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : plasticMaterialUSD not present");
					Assert.assertEquals(driver.findElements(CostsheetInternal.SoftGoodsMaterialUSDC).size(),0);
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : SoftGoodsMaterialUSDC not present");
					//Click on Cancel
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
						}
				else {
					//dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" : Update option not present");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}
	/************************************************************************************/
	//Function consist scenario : :Read_View
	public static boolean verifyReadViewCSComments(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(lblCSComments).size() != 0){
					String csCommentsLabel=driver.findElement(lblCSComments).getText();
					Assert.assertEquals(csCommentsLabel.trim(), "Cost Sheet Comments:");
					log.info("Cost Sheet Comments label is Present");
				}else{
					log.error("Cost Sheet Comments label is Absent");
					fail=true;
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{

				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(lblCSComments).size() != 0){
					System.out.println("Cost Sheet Comments label is Present");
					log.error("Cost Sheet Comments label is Present");
					fail=true;
				}else{
					log.info("Cost Sheet Comments label is Absent");
				}
				//Verification
				Assert.assertEquals(driver.findElements(csCommentsView).size(),1);
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this CSComments is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}

	public static boolean verifyUpdateCSComments(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Click on 'Enter Comment'
				CommonFunctions.clickButtonOrLink(enterComment, "link", "Enter Comment");
				CommonFunctions.enterTextInTextbox(taComments, data[15]);
				//Click on Post
				CommonFunctions.clickButtonOrLink(btnPost, "btn", "Post");
				Thread.sleep(2000);
				//Verification
				Assert.assertEquals(driver.findElements(By.xpath("//table[contains(@id,'TBLT')]/tbody//td[text()='"+data[15]+"']")).size(),1);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" :updation of comment");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verification
				Assert.assertEquals(driver.findElements(enterComment).size(),0);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +"Enter Comment link is not present");
				Assert.assertEquals(driver.findElements(csCommentsEdit).size(),0);
				log.info("Verified(" +strTestCaseName+ ") :"+data[13] +" Comment Edit link is not present");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}	

	/******************************************************************************************************/
	//Function consist scenario : Process History:Read_View
	public static boolean verifyProcessHistoryReadView(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(CostsheetInternal.processHis).size() != 0){
					String pHLabel=driver.findElement(CostsheetInternal.processHis).getText();
					Assert.assertEquals(pHLabel.trim(), "Process History");
					log.info("Process History label is Present");
				}else{
					log.error("Process History label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(CostsheetInternal.processHisDate).size(),1);
				log.info("Verified(" +strTestCaseName+ ") :processHisDate Edit is  present under Process History section");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				if(driver.findElements(CostsheetInternal.processHis).size() != 0){
					System.out.println("Process History label is Present");
					log.error("Process History label is Present");
					fail=true;
				}
				else{
					Assert.assertEquals(driver.findElements(CostsheetInternal.processHis).size(),0);
					log.info("Process History label is Absent");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this" + data[3]+ " is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}

	public static boolean verifyProcessHistoryUpdate(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("In security sheet for ProcessHistory it is No only,hence no code required for Yes condition.");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				CostsheetExternalRetail.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);

				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(!data[4].contains("vendor")) {
					Assert.assertEquals(driver.findElements(CostsheetInternal.lblProcessHistory).size(),1);
					log.info("Verified:ProcessHistory label is present");
					Assert.assertEquals(driver.findElements(CostsheetInternal.processHisTask).size(),1);
					log.info("Verified:ProcessHistory task is present");
				}
				else {
					Assert.assertEquals(driver.findElements(CostsheetInternal.lblProcessHistory).size(),0);
					log.info("Verified:ProcessHistory label is not present");
					Assert.assertEquals(driver.findElements(CostsheetInternal.processHisTask).size(),0);
					log.info("Verified:ProcessHistory task is not present");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
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
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return false;
		}
	}

	//Update cost Sheet Status
	public static boolean updateCSStatus(String sStatus) throws Exception{
		try {
			CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
			Thread.sleep(3000);	
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

