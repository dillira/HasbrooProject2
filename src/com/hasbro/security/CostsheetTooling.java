package com.hasbro.security;

import java.util.Iterator;
import java.util.List;
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


public class CostsheetTooling extends TestsuiteBase{
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
	public static By roWave = By.xpath("//td[contains(text(),'Wave')]//following::td[@id='hbWave']");
	
	public static By lstAstSolidWave = By.xpath("//td[contains(text(),'Ast./Solid Wave')]//following::select[1]");

	public static By lblWave = By.id("hbWave");
	public static By astSolidWaveNew = By.id("hbAstSolidWaveNew");

	public static By tabCostsheet =By.xpath("//a[text()='Cost Sheet List']");
	public static By imgClose = By.xpath("//img[contains(@src,'deleteXsmall.png')]");
	public static By newCostsheetTitle =By.xpath("//td[@class='PAGEHEADINGTITLE' and contains(text(),'Cost Sheet Details')]");
	public static By costAction =By.id("costingActions");
	//public static By lblquoteCurrency=By.id("hBCurrency");
	public static By lblComment=By.id("hBComments");
	public static By csHeading=By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]/td");
	public static By selectSource= By.id("sourcingConfigId");
	public static By sourcingLead   = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead   = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");

	public static By csCol =By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td");
	public static By csStatus =By.xpath("//td[contains(text(),'Tooling Cost Sheet Status')]//following::td[2]");

	public static By toolingCSStatus =By.xpath("//td[contains(text(),'Tooling Cost Sheet Status')]");

	public static By toolingCost =By.xpath("//td[contains(text(),'Tooling Cost:')]");
	public static By vendorToolingCost =By.xpath("//td[contains(text(),'Vendor Tooling Cost:')]");
	public static By toolingCostQC =By.id("hBToolingCost");
	public static By toolingCostUSD =By.id("hbToolingCostUSDConversion");
	public static By btnCancel =By.xpath("//a[text()='Cancel']");

	public static By assSolidRef =By.xpath("//td[contains(text(),'Assortment / Solid Reference:')]");
	public static By assVendorSolidRef =By.xpath("//td[contains(text(),'Vendor Assortment / Solid Reference:')]");
	public static By assSolidColorway =By.xpath("//td[contains(text(),'Ast./Solid Colorway')]");
	public static By assSolidWave =By.xpath("//td[contains(text(),'Ast./Solid Wave')]");

	public static By toolingTable =By.xpath("//td[contains(text(),'Tooling Table:')]");
	public static By toolingTableEdit =By.xpath("//td[contains(text(),'Tooling Table:')]//following::a[text()='Edit'][1]");
	public static By toolingTableView =By.xpath("//td[contains(text(),'Tooling Table:')]//following::a[2]");

	public static By vendortooling =By.xpath("//td[contains(text(),'Vendor Tooling:')]");
	public static By vendortoolingEdit =By.xpath("//td[contains(text(),'Vendor Tooling:')]//following::a[text()='Edit'][1]");
	public static By vendortoolingView =By.xpath("//td[contains(text(),'Vendor Tooling:')]//following::a[2]");

	public static By tTMoldNumberCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbMoldNumber')]");
	public static By tTMoldNumberInput =By.xpath("//div[@id='hbMoldNumberSourceDiv']/input");

	public static By tTPartDescCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbPartDescription')]");
	public static By tTPartDescInput =By.xpath("//div[@id='hbPartDescriptionSourceDiv']/input");

	public static By tTToolNatureCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbToolNatureList')]");
	public static By tTToolNatureSelect =By.xpath("//div[@id='hbToolNatureListSourceDiv']/select");

	public static By tTMoldingMatCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbMoldingMaterial')]");
	public static By tTMoldingMatSelect =By.xpath("//div[@id='hbMoldingMaterialSourceDiv']/Select");

	public static By tTTotalCostCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbCost')]");
	public static By tTTotalCostInput =By.xpath("//div[@id='hbCostSourceDiv']/input");

	public static By tTUpCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbUp')]");
	public static By tTUpInput =By.xpath("//div[@id='hbUpSourceDiv']/input");

	public static By tTCavCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbCav')]");
	public static By tTCavInput =By.xpath("//div[@id='hbCavSourceDiv']/input");

	public static By tTCycleTimeCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbCycleTime')]");
	public static By tTCycleTimeInput =By.xpath("//div[@id='hbCycleTimeSourceDiv']/input");

	public static By tTEffiPercCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbEfficiencyPercent')]");
	public static By tTEffiPercInput =By.xpath("//div[@id='hbEfficiencyPercentSourceDiv']/input");

	public static By toolTypeCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbToolType')]");
	public static By toolTypeSelect =By.xpath("//div[@id='hbToolTypeSourceDiv']/select");

	public static By toolBuildingReasonCell =By.xpath("//table[@id='TBLeditorTable']/tbody/tr[2]/td[contains(@id,'hbToolBuildingReason')]");
	public static By toolBuildingReasonSelect =By.xpath("//div[@id='hbToolBuildingReasonSourceDiv']/select");

	public static By lblMoldNumber =By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[2]/td[1]");
	public static By lblPartDescription =By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[2]/td[2]");
	public static By lblToolNature =By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[2]/td[5]");
	public static By lblMoldingMaterial =By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[2]/td[7]");

	public static By btnDone = By.xpath("//a[text()='Done']");
	public static By vendorCostDetails =By.xpath("//td[contains(text(),'Vendor Cost Details:')]");
	public static By toolingQuoLevDelCol =By.xpath("//td[contains(text(),'Tooling Quote Level of Detail Collection')]");

	public static By lblToolingBenchmark =By.xpath("//td[contains(text(),'Tooling Benchmark:')]");
	public static By toolingBenchmarkEdit =By.xpath("//td[contains(text(),'Tooling Benchmark:')]//following::a[text()='Edit'][1]");
	public static By toolingBenchmarkView =By.xpath("//td[contains(text(),'Tooling Benchmark:')]//following::a[2]");

	public static By lblCSComments =By.xpath("//td[contains(text(),'Cost Sheet Comments:')]");
	public static By csCommentsView =By.xpath("//td[contains(text(),'Cost Sheet Comments:')]//following::a[2]");
	public static By csCommentsEdit =By.xpath("//td[contains(text(),'Cost Sheet Comments:')]//following::a[text()='Edit'][1]");

	public static By enterComment =By.xpath("//div[contains(@id,'div_plus')]//a[text()='Enter Comment']");
	public static By taComments =By.xpath("//textarea[contains(@id,'comments')]"); // ta=text area
	public static By btnPost =By.xpath("//a[text()='Post']"); 

	public static By lblQC =By.xpath("//td[@id='hBCurrency']/preceding::td[1]"); 
	public static By lblCCR =By.xpath("//td[contains(text(),'Currency Conversion Rate')]"); 
	public static By lblVC =By.xpath("	//td[contains(text(),'Vendor Code')]");

	public static By attriErrorMsg = By.xpath("//td[contains(text(),'Sorry, You do not have the necessary privileges to update any Attribute at this time. Please click on Cancel to exit.')]");
	static String errMsgAttri="Sorry, You do not have the necessary privileges to update any Attribute at this time. Please click on Cancel to exit." ;

	static By statusErrorMsg = By.xpath("//td[contains(text(),'Sorry, You do not have the necessary privileges to update the Status field.')]");
	static String errMsgStatus="Sorry, You do not have the necessary privileges to update the Status field." ;

	static By adErrorMsg = By.xpath("//td[contains(text(),'Access Denied. Costsheet state is Initial Quote Approved')]");
	static String errMsgAD="Access Denied. Costsheet state is Initial Quote Approved" ;

	int y=0;
	static int count=-1;
	public static boolean fail=false;
	static int intloopBreak=0;
	static int intBOMPresent=0;
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
	static String toolingVendorCS;
	static String strVendorRetailTooling;
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
			System.out.println(CommonProjectFunctions.runmodes[count]);
			if(!CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
				CommonProjectFunctions.skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			CommonProjectFunctions.strTestCaseName = "User:"+data[0] + " for testcase:"+data[3];	
			log.info("Inside Test Case:-> " + CommonProjectFunctions.strTestCaseName + " for Costsheet tooling Security");				
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
			//Prerequisite
			//For Internal Tooling
			if(data[3].equalsIgnoreCase("CreateInWork"))
			{ createRetailToolingCS_inwork(data); }
			if(data[3].equalsIgnoreCase("CreateUnderReview"))
			{ createRetailToolingCS_underReview(data,"Under Review"); }
			if(data[3].equalsIgnoreCase("CreateApproved"))
			{ createRetailToolingCS_Approved(data,"Approved"); }
			if(data[3].equalsIgnoreCase("CreateCancelled"))
			{ createRetailToolingCS_Cancelled(data,"Cancelled"); }

			//For external Tooling
			if(data[3].equalsIgnoreCase("CreateReadyforReview"))
			{ createVenRetToolCS_ReadyforReview(data,"Ready for Review"); }
			if(data[3].equalsIgnoreCase("CreateInitialQuoteApproved"))
			{ createVenRetToolCS_initialQuoteApproved(data,"Initial Quote Approved"); }
			if(data[3].equalsIgnoreCase("CreateSG4Approved"))
			{ createVenRetToolCS_SG4Approved(data,"SG4 Approved"); }
			if(data[3].equalsIgnoreCase("CreateFEPApproved"))
			{ createVenRetToolCS_FEPApproved(data,"FEP Approved"); }
			if(data[3].equalsIgnoreCase("CreateSeasonalReviewApproved"))
			{ createVenRetToolCS_seasonalReviewApproved(data,"Seasonal Review Approved"); }
			if(data[3].equalsIgnoreCase("CreateRejected"))
			{ createVenRetToolCS_Rejected(data,"Rejected"); }

			//Other costsheet
			if(data[3].equalsIgnoreCase("CreateforDelete"))
			{ createInternalRetailCS_forDelete(data); }

			//Common code for Internal tooling and Vendor tooling
			if(data[3].equalsIgnoreCase("CreateToolingCS"))
			{ 
				log.info("In side :-> " + data[3]);
				CreateInternalRetailItemToolingCS(data);			           			         
				log.info("Out side :-> " + data[3]);
			}
			//******************************Internal tooling***************************************************
			if(data[3].equalsIgnoreCase("ReadViewCostsheet"))
			{ 
				log.info("In side :-> " + data[3]);	
				readViewCS(data);		         
				log.info("Out side ReadViewCS");
			}
			if(data[3].equalsIgnoreCase("UpdateCostsheet"))
			{ 
				log.info("In side :-> " + data[3]);
				updateCS(data);		         
				log.info("Out side UpdateCostsheet");

			}
			//Delete
			if(data[3].equalsIgnoreCase("DeleteCostsheet"))
			{ 
				log.info("In side :-> " + data[3]);
				DeleteCostsheet(data);		         
				log.info("Out side DeleteCostsheet");
			}
			/****************************************************Internal Tooling********************************************************************/
			//Inwork 
			//Common code for internal and external tooling
			if(data[3].equalsIgnoreCase("ReadViewCSInWork"))
			{ 
				log.info("In side :-> " + data[3]);	
				readViewCSInWork(data);		        
				log.info("Out side ReadViewCSInWork");

			}
			//Common code for internal and external tooling
			if(data[3].equalsIgnoreCase("UpdateCSInWork"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSInWork(data);		         
				log.info("Out side UpdateCSInWork");

			}	

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
			//Approved
			if(data[3].equalsIgnoreCase("ReadViewCSApproved"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSApproved(data);		         
				log.info("Out side :-> " + data[3]);

			}						
			if(data[3].equalsIgnoreCase("UpdateCSApproved"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSApproved(data);		         
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
			/***************************Internal Tooling*****************************************************/

			if(data[3].equalsIgnoreCase("ReadViewGeneralAttributesIn"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyGeneralAttributesReadViewInternal(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateGeneralAttributesIn"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyGeneralAttributesUpdateInternal(data);		         
				log.info("Out side " + data[3]);
			}

			/********************************************************************************/

			if(data[3].equalsIgnoreCase("ReadViewToolingCSStatus"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewToolingCSStatus(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateToolingCSStatus"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateToolingCSStatus(data);
				log.info("Out side " + data[3]);
			}

			/********************************************************************************/

			if(data[3].equalsIgnoreCase("ReadViewToolingCost"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewToolingCost(data);	         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateToolingCost"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateToolingCost(data);
				log.info("Out side " + data[3]);
			}
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewAssortSolidRef"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewAssSolidRef(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateAssortSolidRef"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateAssSolidRef(data);
				log.info("Out side " + data[3]);
			}	
			/********************************************************************************/

			if(data[3].equalsIgnoreCase("ReadVieToolingMOATable"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewToolingMOATable(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateToolingMOATable"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateToolingMOATable(data);		         
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

			/************************************************************************************************************************/
			/****************************External Tooling***************************************/
			if(data[3].equalsIgnoreCase("ReadViewGeneralAttributesVen"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyGeneralAttributesReadViewVendor(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateGeneralAttributesVen"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyGeneralAttributesUpdateVendor(data);		         
				log.info("Out side " + data[3]);
			}

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
			/********************************External Tooling*********************************************************/
			if(data[3].equalsIgnoreCase("ReadViewVendorToolingCSStatus"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewVendorToolingCSStatus(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVendorToolingCSStatus"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateVendorToolingCSStatus(data);
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
			if(data[3].equalsIgnoreCase("ReadViewVendorToolingCost"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewVendorToolingCost(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVendorToolingCost"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateVendorToolingCost(data);
				log.info("Out side " + data[3]);
			}
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewVenAssortSolidRef"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewVenAssSolidRef(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVenAssortSolidRef"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateVenAssSolidRef(data);
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewToolQuoLevDetailCollec"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewToolQuoLevDetailCollec(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateToolQuoLevDetailCollec"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateToolQuoLevDetailCollec(data);
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewVendorTooling"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewVendorTooling(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateVendorTooling"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateVendorTooling(data);
				log.info("Out side " + data[3]);
			}
			/*****************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewToolingBenchmark"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyReadViewToolingBenchmark(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateToolingBenchmark"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyUpdateToolingBenchmark(data);
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
			if(data[3].equalsIgnoreCase("ReadViewProcessHistoryVen"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyProcessHistoryReadViewVen(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateProcessHistoryVen"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyProcessHistoryUpdateVen(data);
				log.info("Out side " + data[3]);
			}
		}catch(Throwable t){
			fail=true;
			log.info("****Varification failed or Skipped for**** "+CommonProjectFunctions.strTestCaseName);
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//In Work
	public static String createRetailToolingCS_inwork(String[] data) throws Exception{
		try{
			createToolingCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			log.info("**********Inside********");
			strCSInWork = driver.findElement(csHeading).getText().substring(20);
			String []varCSInWork = strCSInWork.split("Actions:");
			csInWork = varCSInWork[0].trim();

			log.info("Retail Tooling costsheet in Inwork status is : "+csInWork);
			CostsheetInternal.closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCS()", e);
			return "";
		}
		return csInWork;
	}	
	//underReview
	public static String createRetailToolingCS_underReview(String[] data,String sStatus) throws Exception{
		try{
			createToolingCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSUnderReview = driver.findElement(csHeading).getText().substring(20);
			String []varCSUnderReview = strCSUnderReview.split("Actions:");
			csUnderReview = varCSUnderReview[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			log.info("Retail Tooling costsheet in underReview status is: "+csUnderReview);

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return "";
		}
		return csUnderReview;
	}
	public static String createRetailToolingCS_Approved(String[] data,String sStatus) throws Exception{
		try{
			createToolingCS(data);
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
	public static String createRetailToolingCS_Cancelled(String[] data,String sStatus) throws Exception{
		try{
			createToolingCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSCancelled= driver.findElement(csHeading).getText().substring(20);
			String []varCSCancelled = strCSCancelled.split("Actions:");
			csCancelled = varCSCancelled[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail Tooling costsheet in Cancelled status is: "+csCancelled);

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCS()", e);
			return "";
		}
		return csCancelled;
	}	
	//Common code for internal and external tooling
	public static String createInternalRetailCS_forDelete(String[] data) throws Exception{
		try{
			createToolingCS(data);
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

	public static boolean CreateInternalRetailItemToolingCS(String[] data) throws Exception{
		try{

			if(data[5].equalsIgnoreCase("Yes")){
				createToolingCS(data);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//read newly created cost sheet
				strCostSheetName = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
				//strCostSheetName = newcostsheetTitle.getText().substring(20);
				String []varCostSheetName = strCostSheetName.split("Actions:");
				cstNewCostSheetName = varCostSheetName[0].trim();
				log.info("Created costsheet name is :" + cstNewCostSheetName);

				//Update Status
				if(data[2].equalsIgnoreCase("Tooling Internal")) 
					updateCSStatus("Approved");
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					if(!data[4].equalsIgnoreCase("vendor")) {
						updateCSStatus("Initial Quote Approved");
					}
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No")){					
				CostsheetInternal.nevigationToCostsheet(data);
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
					Select select = new Select(driver.findElement(CostsheetTooling.costAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification("Create Cost Sheet",options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Create Cost Sheet option is not present");
					log.info("Create Cost Sheet option is not present in Action dropdown");
				}
				else {
					verifySourceForVendor(data);
					//Verify internal is not present
					// select Create cost sheet action					
					CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet"); 
					//if(data[17].contains("Retail")) {
					Assert.assertEquals(driver.findElements(CostsheetInternal.lnkInternal).size(),0);
					//}
					/*else if(data[17].contains("Product")) {
									Assert.assertEquals(driver.findElements(By.xpath("//td[contains(text(),'Sourcing Configuration')]//following::td[contains(text(),'"+vendorName+"')]")).size(),1);
								}*/
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Button");
				}
			}		
			else{
				log.info("User dont have access to CreateInternalRetailItemToolingCostSheet");
			}
			return true;

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateInternalRetailItemToolingCostSheet()", e);
			return false;
		}

	}	
	/******************************************External tooling*************************************************************************/
	//ReadyforReview
	public static String createVenRetToolCS_ReadyforReview(String[] data,String sStatus) throws Exception{
		try{
			createToolingCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//read newly created cost sheet
			strCSReadyforReview = driver.findElement(csHeading).getText().substring(20);
			String []varCSReadyforReview = strCSReadyforReview.split("Actions:");
			csReadyforReview = varCSReadyforReview[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail Tooling costsheet in ReadyforReview status is: "+csReadyforReview);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVendorRetailToolingCS_ReadyforReview()", e);
			return "";
		}
		return csReadyforReview;
	}	

	//initialQuoteApproved
	public static String createVenRetToolCS_initialQuoteApproved(String[] data,String sStatus) throws Exception{
		String csReadyforReview;
		try{

			createToolingCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSInitialQuoteApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSInitialQuoteApproved = strCSInitialQuoteApproved.split("Actions:");
			csInitialQuoteApproved = varCSInitialQuoteApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail Tooling costsheet in initialQuoteApproved status is: "+csInitialQuoteApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVendorRetailToolingCS_initialQuoteApproved()", e);
			return "";
		}
		return csInitialQuoteApproved;
	}

	//SG4 Approved
	public static String createVenRetToolCS_SG4Approved(String[] data,String sStatus) throws Exception{
		try{

			createToolingCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSSG4Approved = driver.findElement(csHeading).getText().substring(20);
			String []varCSSG4Approved = strCSSG4Approved.split("Actions:");
			csSG4Approved = varCSSG4Approved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail Tooling costsheet in SG4Approved status is: "+csSG4Approved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVendorRetailToolingCS_SG4Approved()", e);
			return "";
		}
		return csSG4Approved;
	}

	//FEP Approved
	public static String createVenRetToolCS_FEPApproved(String[] data,String sStatus) throws Exception{
		try{

			createToolingCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSFEPApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSFEPApproved = strCSFEPApproved.split("Actions:");
			csFEPApproved = varCSFEPApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail Tooling costsheet in FEPApproved status is: "+csFEPApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVendorRetailToolingCS_FEPApproved()", e);
			return "";
		}
		return csFEPApproved;
	}

	//SeasonalReviewApproved
	public static String createVenRetToolCS_seasonalReviewApproved(String[] data,String sStatus) throws Exception{
		try{

			createToolingCS(data);
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
			log.info("Vendor Retail Tooling costsheet in seasonalReviewApproved status is: "+csSeasonalReviewApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVenRetToolCS_seasonalReviewApproved()", e);
			return "";
		}
		return csSeasonalReviewApproved;
	}

	//Rejected
	public static String createVenRetToolCS_Rejected(String[] data,String sStatus) throws Exception{
		try{
			createToolingCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSRejected = driver.findElement(csHeading).getText().substring(20);
			String []varCSRejected = strCSRejected.split("Actions:");
			csRejected = varCSRejected[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			CostsheetInternal.closeTheOpenCostSheet();
			log.info("Vendor Retail Tooling costsheet in Rejected status is: "+csRejected);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVenRetToolCS_Rejected()", e);
			return "";
		}
		return csRejected;
	}

	public static Boolean createToolingCS(String[] data) throws Exception{
		try{
			if(driver.findElements(CostsheetTooling.tabCostsheet).size() == 0) {
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

			// select tooling type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"),"link","Cost sheet type");

			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,1);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
			if(!data[4].equalsIgnoreCase("vendor")) {
				// select wave
				CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[12]);
			}

			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			if(data[2].equalsIgnoreCase("Tooling Internal")) {
				// select QuoteCurrency
				CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
				CommonFunctions.enterTextInTextbox(CostsheetInternal.csName, data[14]);
			}
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return false;
		}
	}

	public static boolean readViewCS(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			sStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
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
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verified " +csApproved + "Cost sheet is not present in list ");
				}
				else {
					verifySourceForVendor(data);
				}
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


	public static boolean updateCS(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Change Status
				updateCSStatus("In Work");
				CostsheetInternal.verifyCSStatus("In Work",csApproved);
				//change back to Approved
				updateCSStatus("Approved");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (sStatus.equalsIgnoreCase("No")) {
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Cost sheet is not present");
					}
					else {
						verifySourceForVendor(data);
					}
				}
				else if (sStatus.equalsIgnoreCase("Yes")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verified"+ CommonProjectFunctions.strTestCaseName + " Update option is not present");
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
	public static boolean DeleteCostsheet(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				//Create Costsheet to delete
				searchandClickforRequriedCostsheet(csForD);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//strSource=InternalBOMSoftG.AddSource(data);
				//strCS = createVendorRetailToolingCS_inwork(data);
				//	CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetActionDelete,data[13]);	
				driver.findElement(CostsheetTooling.lstCostSheetAction).click();
				driver.findElement(CostsheetInternal.lstCostSheetActionDelete).click();
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.tabCostsheet);
				bCostSheet= SearchCostsheet(csForD);
				Assert.assertFalse(bCostSheet, "Cost sheet has been deleted");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has been deleted");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{								
				if(!data[4].contains("vendor")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(cstNewCostSheetName);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//verifyUserCostsheetAccess(data);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal," Delete option is not present");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Delete option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInWork);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "In Work");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : status as 'In Work' verified.");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csInWork);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
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
		return inWorkStatus;
	}


	public static boolean updateCSInWork(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					if(data[2].equalsIgnoreCase("Tooling Internal")) {
						//Change Status
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csInWork);
						//change back to inwork
						updateCSStatus("In Work");
						//Change to Cancelled
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csInWork);
						//change back to inwork
						updateCSStatus("In Work");
						//Change to Approved
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csInWork);
					}
					else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
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
					}
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csInWork);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csUnderReview);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Under Review");
				log.info("Verifified(" +CommonProjectFunctions.strTestCaseName+ ") : status as Under Review");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csUnderReview);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					if(data[2].equalsIgnoreCase("Tooling Internal")) {	
						//Change Status
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csUnderReview);
						//change back to inwork
						updateCSStatus("Under Review");
						//Change to Cancelled
						updateCSStatus("Cancelled");
						CostsheetInternal.verifyCSStatus("Cancelled",csUnderReview);
						//change back to inwork
						updateCSStatus("Under Review");
						//Change to Approved
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csUnderReview);
					}
					else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
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
					}
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csUnderReview);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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

	public static String readViewCSApproved(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			approvedStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Approved");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : status as Approved");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
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
	}

	public static boolean updateCSApproved(String[] data) throws Exception 
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
	}

	public static boolean readViewCSCancelled(String[] data) throws Exception
	{ 
		try
		{
			CostsheetInternal.nevigationToCostsheet(data);
			cancelledStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csCancelled);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Cancelled");
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : status as Cancelled");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csCancelled);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					if(data[2].equalsIgnoreCase("Tooling Internal")) {
						//Change Status
						updateCSStatus("In Work");
						CostsheetInternal.verifyCSStatus("In Work",csCancelled);
						//change back to inwork
						updateCSStatus("Cancelled");
						//Change to Cancelled
						updateCSStatus("Approved");
						CostsheetInternal.verifyCSStatus("Approved",csCancelled);
						//change back to inwork
						updateCSStatus("Cancelled");
						//Change to Approved
						updateCSStatus("Under Review");
						CostsheetInternal.verifyCSStatus("Under Review",csCancelled);
					}
					else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
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
					}
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csCancelled);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
	public static boolean verifyGeneralAttributesReadViewInternal(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				//	if(data[2].equalsIgnoreCase("Tooling Internal")) {
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				/*	}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				}*/
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
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					//if(data[2].equalsIgnoreCase("Tooling Internal")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
					/*}
					else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					}*/
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
				}
				else {
					verifySourceForVendor(data);
				}
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

	public static boolean verifyGeneralAttributesReadViewVendor(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
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
				//if(!data[4].contains("vendor")) {
				CostsheetTooling.clickOnCostSheetListTab();
				/*	if(data[2].equalsIgnoreCase("Tooling Internal")) {
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csApproved);
					}
					else if(data[2].equalsIgnoreCase("Tooling Vendor")) {*/
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				//	}
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

	public static boolean verifyGeneralAttributesUpdateInternal(String[] data) throws Exception{
		try{
			//CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				//	if(data[2].equalsIgnoreCase("Tooling Internal")) {
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				/*}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
				}*/
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Select Update from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				//if(data[2].equalsIgnoreCase("Tooling Internal")) {
				//updating comments
				driver.findElement(CostsheetInternal.updateComment).clear();
				CommonFunctions.enterTextInTextbox(CostsheetInternal.updateComment, data[14]);
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				//Click tab
				//CostsheetInternal.clickTab(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				String sComment=driver.findElement(lblComment).getText();
				Assert.assertEquals(sComment.trim(),data[14]);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : updated comment");
				/*}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[25]);
					//Click on Save
					CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
					if (data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")||data[4].equalsIgnoreCase("engwithCost")) {
						verifyErrorMessage(attriErrorMsg,errMsgAttri);
					}
					else {
					String sWave=driver.findElement(lblWave).getText();
					Assert.assertEquals(sWave.trim(),data[25]);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : updated wave value as: "+sWave);
					//revert wave value
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[12]);
					//Click on Save
					CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
					}
				}*/

				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//	if(!data[4].contains("vendor")&& data[2].equalsIgnoreCase("Tooling Internal")) {
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				//if(data[2].equalsIgnoreCase("Tooling Internal")) {
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				/*}
					else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csSG4Approved);
					}*/
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//verifyUserCostsheetAccess(data);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal,data[13]+ " option is not present");
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +"option is not present");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
				/*}
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

	public static boolean verifyGeneralAttributesUpdateVendor(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Select Update from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[25]);
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				if (data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")||data[4].equalsIgnoreCase("engwithCost")) {
					verifyErrorMessage(attriErrorMsg,errMsgAttri);
				}
				else {
					String sWave=driver.findElement(lblWave).getText();
					Assert.assertEquals(sWave.trim(),data[25]);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : updated wave value as: "+sWave);
					//revert wave value
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[12]);
					//Click on Save
					CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csSG4Approved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
			
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(bVal) {
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					Assert.assertEquals(driver.findElements(roWave).size(),1);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : General attrobute wave is not editable");
				}
				//	dropDownOptionVerification
				Assert.assertFalse(bVal,data[13]+ " option is not present");
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +"option is not present");
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

	public static boolean verifyErrorMessage(By by, String errMsg) throws Exception{
		try {
			Assert.assertEquals(driver.findElement(by).getText().trim(),errMsg);
			log.info("Verified error message as : " +errMsg +"for" + CommonProjectFunctions.strTestCaseName);
			CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateCSStatus()", e);
			return false;
		}
	}

	/*public static boolean verifyErrorMessageStatus() throws Exception{
		try {
			Assert.assertEquals(driver.findElement(statusErrorMsg).getText().trim(),errMsgStatus);
			log.info("Verified error message as : " +errMsgStatus +"for" + CommonProjectFunctions.strTestCaseName);
			CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateCSStatus()", e);
			return false;
		}
	}*/


	/************************************************************************************/
	//Function consist scenario : ToolingCSStatus:Read_View
	public static boolean verifyReadViewToolingCSStatus(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				if(data[2].equalsIgnoreCase("Tooling Internal")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
				}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
				}
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(toolingCSStatus).size() != 0){
					String toolingCSStatusLabel=driver.findElement(toolingCSStatus).getText();
					Assert.assertEquals(toolingCSStatusLabel.trim(), "Tooling Cost Sheet Status:");
					log.info("Tooling Cost Sheet Status label is Present");
				}else{
					log.error("Tooling Cost Sheet Status label is Absent");
					fail=true;
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					log.info("No condition is applicable only for Vendor user");
				}
				else {
					verifySourceForVendor(data);
				}
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateToolingCSStatus(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				if(data[2].equalsIgnoreCase("Tooling Internal")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
				}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csSG4Approved);
				}
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//updating status
				updateCSStatus("Under Review");
				//verification
				CostsheetInternal.verifyCSStatus("Under Review",csInitialQuoteApproved);
				//Reverted back Status
				updateCSStatus("Approved");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					if(data[2].equalsIgnoreCase("Tooling Internal")) {
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csApproved);
					}
					else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csSG4Approved);
					}
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//verifyUserCostsheetAccess(data);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +"option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
				else {
					verifySourceForVendor(data);
				}

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
	//Function consist scenario : ToolingCost:Read_View
	public static boolean verifyReadViewToolingCost(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(toolingCost).size() != 0){
					String toolingCostLabel=driver.findElement(toolingCost).getText();
					Assert.assertEquals(toolingCostLabel.trim(), "Tooling Cost:");
					log.info("Tooling Cost label is Present");
				}else{
					log.error("Tooling Cost label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(toolingCostQC).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
				Assert.assertEquals(driver.findElements(toolingCostUSD).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					log.info("No condition is applicable only for Vendor user");
				}
				else {
					verifySourceForVendor(data);
				}
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateToolingCost(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Select Update from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				CommonFunctions.waitForVisibilityOfElement(toolingCostQC);
				//Verification
				Assert.assertEquals(driver.findElements(toolingCostQC).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
				Assert.assertEquals(driver.findElements(toolingCostUSD).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");

				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Verifying that toolingCostQC and toolingCostUSD both are not editable
					//Verification
					Assert.assertEquals(driver.findElements(toolingCostQC).size(),1);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
					Assert.assertEquals(driver.findElements(toolingCostUSD).size(),1);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");
					//Verifying that 'Update' is not present in action dropdown
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13]+ " option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
				else {
					verifySourceForVendor(data);
				}
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
	//Function consist scenario : Assortment / Solid Reference::Read_View
	public static boolean verifyReadViewAssSolidRef(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(assSolidRef).size() != 0){
					String assSolidRefLabel=driver.findElement(assSolidRef).getText();
					Assert.assertEquals(assSolidRefLabel.trim(), "Assortment / Solid Reference:");
					log.info("Assortment / Solid Reference label is Present");
				}else{
					log.error("Assortment / Solid Reference label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(assSolidColorway).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :assSolidColorway");
				Assert.assertEquals(driver.findElements(assSolidWave).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :assSolidWave");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					log.info("No condition is applicable only for Vendor user");
				}
				else {
					verifySourceForVendor(data);
				}
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateAssSolidRef(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Select Update from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				CommonFunctions.waitForVisibilityOfElement(toolingCostQC);
				//Verification
				Assert.assertEquals(driver.findElements(toolingCostQC).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
				Assert.assertEquals(driver.findElements(toolingCostUSD).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");

				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Verifying that toolingCostQC and toolingCostUSD both are not editable
					Assert.assertEquals(driver.findElements(toolingCostQC).size(),1);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
					Assert.assertEquals(driver.findElements(toolingCostUSD).size(),1);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");

					//Verifying that 'Update' is not present in action dropdown
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13]+ " option is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
				else {
					verifySourceForVendor(data);
				}
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

	//Function consist scenario : ToolingMOATable::Read_View
	public static boolean verifyReadViewToolingMOATable(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				/*if(driver.findElements(assSolidRef).size() != 0){
						String assSolidRefLabel=driver.findElement(assSolidRef).getText();
						Assert.assertEquals(assSolidRefLabel.trim(), "Tooling Cost:");
						log.info("Assortment / Solid Reference label is Present");
					}else{
						log.error("Assortment / Solid Reference label is Absent");
						fail=true;
					}*/
				//Verification
				Assert.assertEquals(driver.findElements(toolingTable).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingTable");
				Assert.assertEquals(driver.findElements(toolingTableView).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingTableView");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					log.info("No condition is applicable only for Vendor user");
				}
				else {
					verifySourceForVendor(data);
				}
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}

	//Update
	public static boolean verifyUpdateToolingMOATable(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Edit table
				//click on edit
				CommonFunctions.clickButtonOrLink(toolingTableEdit, "lnk", "Edit");
				CommonFunctions.waitForVisibilityOfElement(tTMoldNumberCell);
				CommonFunctions.clickButtonOrLink(tTMoldNumberCell, "Cell", "MoldNumber");
				CommonFunctions.enterTextInTextbox(tTMoldNumberInput, data[16]);

				CommonFunctions.clickButtonOrLink(tTPartDescCell, "Cell", "PartDesc");
				CommonFunctions.enterTextInTextbox(tTPartDescInput, data[17]);

				CommonFunctions.clickButtonOrLink(tTToolNatureCell, "Cell", "ToolNature");
				CommonFunctions.selectFromDropDownByVisibleText(tTToolNatureSelect, data[18]);

				CommonFunctions.clickButtonOrLink(tTMoldingMatCell, "Cell", "MoldingMat");
				CommonFunctions.selectFromDropDownByVisibleText(tTMoldingMatSelect, data[19]);

				CommonFunctions.clickButtonOrLink(tTTotalCostCell, "Cell", "TotalCost");
				CommonFunctions.enterTextInTextbox(tTTotalCostInput, data[20]);	

				CommonFunctions.clickButtonOrLink(tTUpCell, "Cell", "Up");
				CommonFunctions.enterTextInTextbox(tTUpInput, data[21]);	

				CommonFunctions.clickButtonOrLink(tTCavCell, "Cell", "Cav");
				CommonFunctions.enterTextInTextbox(tTCavInput, data[22]);	

				CommonFunctions.clickButtonOrLink(tTCycleTimeCell, "Cell", "CycleTime");
				CommonFunctions.enterTextInTextbox(tTCycleTimeInput, data[23]);	

				CommonFunctions.clickButtonOrLink(tTEffiPercCell, "Cell", "EffiPerc");
				CommonFunctions.enterTextInTextbox(tTEffiPercInput, data[24]);	

				//Click on Done
				CommonFunctions.clickButtonOrLink(btnDone, "Btn", "Done");

				CommonFunctions.waitForVisibilityOfElement(lblMoldNumber);
				//Verification
				Assert.assertEquals(driver.findElement(lblMoldNumber).getText().trim(),data[16]);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :lblMoldNumber");
				Assert.assertEquals(driver.findElement(lblPartDescription).getText().trim(),data[17]);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :lblPartDescription");
				Assert.assertEquals(driver.findElement(lblToolNature).getText().trim(),data[18]);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :lblToolNature");
				Assert.assertEquals(driver.findElement(lblMoldingMaterial).getText().trim(),data[19]);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :lblMoldingMaterial");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					//Verifying that edit is not present
					Assert.assertEquals(driver.findElements(toolingTableEdit).size(),0);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingTable Edit is not present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
				if(data[2].equalsIgnoreCase("Tooling Internal")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
				}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				}
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
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :processHisDate Edit is  present under Process History section");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(CostsheetInternal.processHis).size() != 0){
						System.out.println("Process History label is Present");
						log.error("Process History label is Present");
						fail=true;
					}else{
						Assert.assertEquals(driver.findElements(CostsheetInternal.processHis).size(),0);
						log.info("Process History label is Absent");
					}
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
				else {
					verifySourceForVendor(data);
				}
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
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					if(data[2].equalsIgnoreCase("Tooling Internal")) {
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csApproved);
					}
					else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
						// search cost sheet name in web table
						searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					}
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Assert.assertEquals(driver.findElements(CostsheetInternal.lblProcessHistory).size(),1);
					log.info("Verified:ProcessHistory label is present");
					Assert.assertEquals(driver.findElements(CostsheetInternal.processHisTask).size(),1);
					log.info("Verified:ProcessHistory task is present");
					// close the open cost sheet
					CostsheetInternal.closeTheOpenCostSheet();
				}
				else {
					verifySourceForVendor(data);
				}
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

	/************************************************External Tooling*******************************************************************************/
	public static boolean readViewCSVendor(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			sStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
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
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verified " +csApproved + "Cost sheet is not present in list ");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Cost sheet is not present");
					}
					else {
						verifySourceForVendor(data);
					}
				}
				else if (sStatus.equalsIgnoreCase("Yes")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					log.info("Verified"+ CommonProjectFunctions.strTestCaseName + " Update option is not present");
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
				CostsheetTooling.clickOnCostSheetListTab();
				//Create Costsheet to delete
				searchandClickforRequriedCostsheet(csForD);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//strSource=InternalBOMSoftG.AddSource(data);
				//strCS = createVendorRetailToolingCS_inwork(data);
				//	CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetActionDelete,data[13]);	
				driver.findElement(CostsheetTooling.lstCostSheetAction).click();
				driver.findElement(CostsheetInternal.lstCostSheetActionDelete).click();
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.tabCostsheet);
				bCostSheet= SearchCostsheet(csForD);
				Assert.assertFalse(bCostSheet, "Cost sheet has been deleted.");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has been deleted");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{								
				//	if(!data[4].contains("vendor")) {
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);  //cstNewCostSheetName
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//verifyUserCostsheetAccess(data);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal," Delete option is not present");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Delete option is not present");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
				/*}
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


	public static boolean readViewCSReadyforReview(String[] data) throws Exception
	{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			readyforReviewStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csReadyforReview);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "Ready for Review");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csReadyforReview);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csReadyforReview);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "Initial Quote Approved");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csInitialQuoteApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csInitialQuoteApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csSG4Approved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "SG4 Approved");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csSG4Approved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csSG4Approved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csFEPApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "FEP Approved");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csFEPApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csFEPApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "Seasonal Review Approved");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csSeasonalReviewApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csSeasonalReviewApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csRejected);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				CostsheetInternal.verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				System.out.println(strStatus);
				Assert.assertEquals(strStatus.trim(), "Rejected");
				log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : status as "+strStatus+" verified");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csRejected);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
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
				CostsheetTooling.clickOnCostSheetListTab();
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
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csRejected);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
						log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
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
					log.info("Verification(" +CommonProjectFunctions.strTestCaseName+ ") : Update option is not present");
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
	/**************************************************************************************************************/
	//Function consist scenario : ToolingCSStatus:Read_View
	public static boolean verifyReadViewVendorToolingCSStatus(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(toolingCSStatus).size() != 0){
					String toolingCSStatusLabel=driver.findElement(toolingCSStatus).getText();
					Assert.assertEquals(toolingCSStatusLabel.trim(), "Vendor Tooling Cost Sheet Status:");
					log.info("Vendor Tooling Cost Sheet Status label is Present");
				}else{
					log.error("Vendor Tooling Cost Sheet Status label is Absent");
					fail=true;
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//	if(!data[4].contains("vendor")) {
				log.info("No condition is applicable only for Vendor user");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateVendorToolingCSStatus(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//updating status
				updateCSStatus("In Work");
				if(data[4].equalsIgnoreCase("engineer")||data[4].equalsIgnoreCase("engLead")||data[4].equalsIgnoreCase("engwithCost")||data[4].equalsIgnoreCase("vendor")){
					verifyErrorMessage(statusErrorMsg,errMsgStatus);
				}
				else {
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//verification
					CostsheetInternal.verifyCSStatus("In Work",csInitialQuoteApproved);
					//Reverted back Status
					updateCSStatus("Initial Quote Approved");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//if(!data[4].contains("vendor")) {
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//verifyUserCostsheetAccess(data);
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal,data[13]+ " option is not present");
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +"option is not present");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
				/*}
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
	/********************************** Vendor Cost Details************************************/
	public static boolean verifyReadViewVendorCostDetail(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
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
				Assert.assertEquals(driver.findElements(lblQC).size(),1);
				log.info("Label Quote qurency is present");
				Assert.assertEquals(driver.findElements(lblCCR).size(),1);
				log.info("Label Currency Conversion Rate  is present");
				Assert.assertEquals(driver.findElements(lblVC).size(),1);
				log.info("Label Vendor Code is present");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//	if(!data[4].contains("vendor")) {
				log.info("No condition is applicable only for Vendor user");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
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
				CostsheetTooling.clickOnCostSheetListTab();
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
				if(data[4].equalsIgnoreCase("vendor")){
					verifyErrorMessage(attriErrorMsg, errMsgAttri);
				}
				else {
				//Click tab
				//CostsheetInternal.clickTab(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				String sComment=driver.findElement(lblComment).getText();
				Assert.assertEquals(sComment.trim(),data[15]);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : Update of 'Vendor Cost Detail' section");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				/*	// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					log.info("**********Inside********");*/
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(bVal) {
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					Assert.assertEquals(driver.findElements(CostsheetInternal.updateComment).size(),0);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : comment is not editable");
					//Assert.assertEquals(driver.findElements(lstQuoteCurrency).size(),0);
					//log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") : Quote Currency  is not editable");
					//Click on Cancel
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");
				}
				else {
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +"option is not present");
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

	/************************************Vendor Assortment / Solid Reference:************************************************/
	//Function consist scenario : Assortment / Solid Reference::Read_View
	public static boolean verifyReadViewVenAssSolidRef(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(assVendorSolidRef).size() != 0){
					String assSolidRefLabel=driver.findElement(assVendorSolidRef).getText();
					Assert.assertEquals(assSolidRefLabel.trim(), "Vendor Assortment / Solid Reference:");
					log.info("Vendor Assortment / Solid Reference label is Present");
				}else{
					log.error("Vendor Assortment / Solid Reference label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(assSolidColorway).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :assSolidColorway");
				Assert.assertEquals(driver.findElements(assSolidWave).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :assSolidWave");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//if(!data[4].contains("vendor")) {
				log.info("No condition is applicable only for Vendor user");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateVenAssSolidRef(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csFEPApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Select Update from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				CommonFunctions.waitForVisibilityOfElement(lstAstSolidWave);
				CommonFunctions.selectFromDropDownByVisibleText(lstAstSolidWave,data[25]);
				CommonFunctions.clickButtonOrLink(btnSave,"btn", "Save");
				if(data[4].contains("engineer")||data[4].contains("engLead")||data[4].contains("engwithCost")) {
					verifyErrorMessage(attriErrorMsg,errMsgAttri);
				}
				else {
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					String sWave=driver.findElement(astSolidWaveNew).getText();
					Assert.assertEquals(sWave.trim(),data[25]);
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//if(!data[4].contains("vendor")) {
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csFEPApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verifying that toolingCostQC and toolingCostUSD both are not editable
				Assert.assertEquals(driver.findElements(toolingCostQC).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
				Assert.assertEquals(driver.findElements(toolingCostUSD).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");

				//Verifying that 'Update' is not present in action dropdown
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(bVal)
				{
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					Assert.assertEquals(driver.findElements(lstAstSolidWave).size(),0);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :lstAstSolidWave");
					CommonFunctions.clickButtonOrLink(btnCancel,"btn", "Cancel");
				}
				else {
				//	dropDownOptionVerification
				Assert.assertFalse(bVal,data[13]+ " option is not present");
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13]+ " option is not present");
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

	/*********************************Tooling Quote Level of Detail Collection***************************************/
	public static boolean verifyReadViewToolQuoLevDetailCollec(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("Yes condition is not applicable for any user ");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//if(!data[4].contains("vendor")) {
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				Assert.assertEquals(driver.findElements(toolingQuoLevDelCol).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :Tooling Quote Level of Detail Collection is not present.");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}

	//Update
	public static boolean verifyUpdateToolQuoLevDetailCollec(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("Yes condition is not applicable for any user ");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				Assert.assertEquals(driver.findElements(toolingQuoLevDelCol).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :Tooling Quote Level of Detail Collection is not present.");
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
	/************************************************************************/
	//Function consist scenario : ToolingMOATable::Read_View
	public static boolean verifyReadViewVendorTooling(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verification
				Assert.assertEquals(driver.findElements(vendortooling).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :Vendor tooling");
				Assert.assertEquals(driver.findElements(vendortoolingView).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :Vendor tooling View");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{ //Need to update no condition
				//if(!data[4].contains("vendor")) {
				log.info("No condition is applicable only for Vendor user");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}

	//Update
	public static boolean verifyUpdateVendorTooling(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Edit table
				//click on edit
				CommonFunctions.clickButtonOrLink(vendortoolingEdit, "lnk", "Edit");
				fillVendorToolingTable(data);
				if(data[4].contains("engineer")||data[4].contains("engLead")||data[4].contains("vendor")) {
					verifyErrorMessage(adErrorMsg, errMsgAD);
				}
				else {
					CommonFunctions.waitForVisibilityOfElement(lblMoldNumber);
					//Verification
					Assert.assertEquals(driver.findElement(lblMoldNumber).getText().trim(),data[16]);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :MoldNumber");
					Assert.assertEquals(driver.findElement(lblPartDescription).getText().trim(),data[17]);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :Part Description");
					Assert.assertEquals(driver.findElement(By.xpath("//td[text()='"+data[26]+"']")).getText().trim(),data[26]);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :Tool Type");
					Assert.assertEquals(driver.findElement(By.xpath("//td[text()='"+data[18]+"']")).getText().trim(),data[18]);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :Tool Nature");
				}
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//if(!data[4].contains("vendor")) {
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verifying that edit is not present
				Assert.assertEquals(driver.findElements(toolingTableEdit).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingTable Edit is not present");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
				/*}
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

	public static boolean fillVendorToolingTable(String[] data) throws Exception{
		try{
			CommonFunctions.waitForVisibilityOfElement(tTMoldNumberCell);
			CommonFunctions.clickButtonOrLink(tTMoldNumberCell, "Cell", "MoldNumber");
			CommonFunctions.enterTextInTextbox(tTMoldNumberInput, data[16]);

			CommonFunctions.clickButtonOrLink(tTPartDescCell, "Cell", "PartDesc");
			CommonFunctions.enterTextInTextbox(tTPartDescInput, data[17]);

			CommonFunctions.clickButtonOrLink(toolTypeCell, "Cell", "Tool Type");
			CommonFunctions.enterTextInTextbox(toolTypeSelect, data[26]);

			CommonFunctions.clickButtonOrLink(tTToolNatureCell, "Cell", "ToolNature");
			CommonFunctions.selectFromDropDownByVisibleText(tTToolNatureSelect, data[18]);

			CommonFunctions.clickButtonOrLink(toolBuildingReasonCell, "Cell", "Tool Building Reason");
			CommonFunctions.selectFromDropDownByVisibleText(toolBuildingReasonSelect, data[27]);

			CommonFunctions.clickButtonOrLink(tTMoldingMatCell, "Cell", "MoldingMat");
			CommonFunctions.selectFromDropDownByVisibleText(tTMoldingMatSelect, data[19]);

			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "Btn", "Done");
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillVendorToolingTable", e);
			return false;
		}
		return true;
	}
	/************************************************************************************/
	//Function consist scenario : Vendor Tooling Cost:Read_View
	public static boolean verifyReadViewVendorToolingCost(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(vendorToolingCost).size() != 0){
					String sVendortoolingCost=driver.findElement(vendorToolingCost).getText();
					Assert.assertEquals(sVendortoolingCost.trim(), "Vendor Tooling Cost:");
					log.info("Vendor Tooling Cost label is Present");
				}else{
					log.error("Vendor Tooling Cost label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(toolingCostQC).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
				Assert.assertEquals(driver.findElements(toolingCostUSD).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//if(!data[4].contains("vendor")) {
				log.info("No condition is applicable for Vendor user");
				/*}
				else {
					verifySourceForVendor(data);
				}*/
			}
			else
			{
				log.info("For this ToolingCSStatus is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			return false;
		}
		return true;
	}
	//Update
	public static boolean verifyUpdateVendorToolingCost(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Select Update from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				CommonFunctions.waitForVisibilityOfElement(toolingCostQC);
				//Verification
				Assert.assertEquals(driver.findElements(toolingCostQC).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
				Assert.assertEquals(driver.findElements(toolingCostUSD).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Cancel");

				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//if(!data[4].contains("vendor")) {
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verifying that toolingCostQC and toolingCostUSD both are not editable
				//Verification
				Assert.assertEquals(driver.findElements(toolingCostQC).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
				Assert.assertEquals(driver.findElements(toolingCostUSD).size(),1);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");
				//Verifying that 'Update' is not present in action dropdown
				Select select = new Select(driver.findElement(lstCostSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
				if(bVal)
				{
					CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					Assert.assertEquals(driver.findElements(toolingCostQC).size(),1);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostQC");
					Assert.assertEquals(driver.findElements(toolingCostUSD).size(),1);
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :toolingCostUSD");
					CommonFunctions.clickButtonOrLink(btnCancel, "Btn", "Cancel");
				}
				else {
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13]+ " option is not present");
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
				log.info("For this "+data[13]+" is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}	
	/*****************************Tooling Benchmark*******************************************************/
	//Function consist scenario : :Read_View
	public static boolean verifyReadViewToolingBenchmark(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(lblToolingBenchmark).size() != 0){
					String tbLabel=driver.findElement(lblToolingBenchmark).getText();
					Assert.assertEquals(tbLabel.trim(), "Tooling Benchmark:");
					log.info("Tooling Benchmark label is Present");
				}else{
					log.error("Tooling Benchmark label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(toolingBenchmarkView).size(),1);
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//if(!data[4].contains("vendor")) {
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				if(driver.findElements(lblToolingBenchmark).size() != 0){
					System.out.println("Tooling Benchmark label is Present");
					log.error("Tooling Benchmark label is Present");
					fail=true;
				}else{
					Assert.assertEquals(driver.findElements(toolingBenchmarkView).size(),0);
					log.info("Tooling Benchmark label is Absent");

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
				log.info("For this Tooling Benchmark is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}

	public static boolean verifyUpdateToolingBenchmark(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("Yes scenario is not applicable for UpdateToolingBenchmark");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verification
				Assert.assertEquals(driver.findElements(toolingBenchmarkEdit).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +"ToolingBenchmark Edit link is not present");
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
				CostsheetTooling.clickOnCostSheetListTab();
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
				//if(!data[4].contains("vendor")) {
				CostsheetTooling.clickOnCostSheetListTab();
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
				/*}
				else {
					verifySourceForVendor(data);
				}*/
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
				CostsheetTooling.clickOnCostSheetListTab();
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
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +" :updation of comment");
				// close the open cost sheet
				CostsheetInternal.closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				log.info("**********Inside********");
				//Verification
				Assert.assertEquals(driver.findElements(enterComment).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +"Enter Comment link is not present");
				Assert.assertEquals(driver.findElements(csCommentsEdit).size(),0);
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :"+data[13] +" Comment Edit link is not present");
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
	public static boolean verifyProcessHistoryReadViewVen(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				if(data[2].equalsIgnoreCase("Tooling Internal")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
				}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				}
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
				log.info("Verified(" +CommonProjectFunctions.strTestCaseName+ ") :processHisDate Edit is  present under Process History section");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				CostsheetTooling.clickOnCostSheetListTab();
				if(data[2].equalsIgnoreCase("Tooling Internal")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
				}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				}
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//	if(!data[4].contains("vendor")) {
				if(driver.findElements(CostsheetInternal.processHis).size() != 0){
					System.out.println("Process History label is Present");
					log.error("Process History label is Present");
					fail=true;
				}else{
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

	public static boolean verifyProcessHistoryUpdateVen(String[] data) throws Exception{
		try{
			CostsheetInternal.nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("In security sheet for ProcessHistory it is No only,hence no code required for Yes condition.");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				CostsheetTooling.clickOnCostSheetListTab();
				if(data[2].equalsIgnoreCase("Tooling Internal")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
				}
				else if(data[2].equalsIgnoreCase("Tooling Vendor")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInitialQuoteApproved);
				}
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				if(!data[4].contains("vendor")) {
				log.info("**********Inside********");
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
			//	CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
			CommonFunctions.waitForVisibilityOfElement(csStatus);
			CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
			Thread.sleep(1000);	
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
			log.error("Exception in CreateVendorRetailItemToolingCostSheet()", e);
			return false;
		}
	}
	//**************************************************************************************************
	public static Boolean verifySourceForVendor(String[] data) throws Exception{
		try{
			Select selectSou = new Select(driver.findElement(InternalBOMSoftG.selectSource));
			List<WebElement> options = selectSou.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
			//	dropDownOptionVerification
			Assert.assertFalse(bVal,"Internal Source is not present");
			log.info("Verification : Internal Source(Hasbro Internal) is not present for Vendor user");

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return false;
		}
		return true;
	}
	///Search is shifted to row wise as view is different for different users
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
					//Thread.sleep(1000);
					//String strCostSheetName = e.getText().trim();
					Thread.sleep(2000);
					System.out.println(By.xpath("//a[text()='"+csName+"']"));
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(tabCostsheet));
			CommonFunctions.waitForPageLoaded();
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

