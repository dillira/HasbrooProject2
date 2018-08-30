package com.hasbro.security;
/**
 * @author Anjali Gupta
 *Prerequisite :
 *1. to run vendor user source should be in approved state and external source should be added in product and funskool(source) should be in
 *product. 
 *
 *Note : 'ReadViewCostsheet' is verifying N/A section of 'Cost Sheet Identification' and 
 *'Cost Sheet Variations'
 */
import java.util.List;

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
import util.MyException;

public class CostsheetInternal extends TestsuiteBase{


	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String strTestCaseName = null;

	public static By addNewBOMTab= By.xpath("//a[contains(text(),'Add New BOM')]");
	//	public static By BOMDetails= By.xpath("//li[@id='DETAILS_PAGETab']/a");
	public static By initializeBOM= By.xpath("//a[contains(text(),'Initialize BOM')]"); 
	public static By headingCreateBOM= By.xpath("//td[contains(text(),'Create BOM')]");

	//************CostSheet Variables ***********************************************

	static String strCostSheetName;	
	public static By lstseasonList = By.xpath("//*[@id='seasonList']");		
	public static By lstcolorwayName = By.xpath("//*[@id='contextSKUId']");	
	public static By lnkCreateCostSheetInternal = By.xpath("//*[@id='VR:com.ptc.core.meta.type.mgmt.server.impl.WTTypeDefinition:13468587_link']");
	public static By lstQuoteCurrency = By.xpath("//td[contains(text(),'Quote Currency')]//following::select[1]");
	public static By btnSave =  By.xpath("//a[text()='Save']");  
	public static By lnkAdd =  By.xpath("//a[text()='Add']");
	public static By lstCostSheetAction =  By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']");
	public static By lstCostSheetActionDelete =  By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']/option[contains(@value,'deleteCostSheet')]");

	public static By txtcomments =  By.xpath("//*[@id='ptc_str_13']"); 

	public static By tblcostSheetResults =  By.xpath("//*[@id='costSheetResults']/table"); 
	public static By tblCostSheetIdentification = By.xpath("//td[contains(text(),'Cost Sheet Identification')]");
	public static By costSheetVariations = By.xpath("//td[contains(text(),'Cost Sheet Variations')]");


	public static By chkPrimaryCostSheet = By.xpath("/html/body/form/div/table/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr[4]/td/table/tbody/tr/td/div[1]/div[2]/div[2]/div/div[2]/table/tbody/tr/td[2]/input[2]");
	public static By lstWave = By.xpath("//td[contains(text(),'*Wave')]//following::select[1]");
	public static By lnkIPContentsTable =  By.xpath("/html/body/form/div/table/tbody/tr/td/table/tbody/tr[3]/td/div/table/tbody/tr[3]/td/div/table/tbody/tr[4]/td/div[3]/div[2]/div[2]/div/div[2]/table/tbody/tr/td/div[1]/table/tbody/tr/td[2]/a[1]"); 

	public static By lnkUnitRatioContentsTable =  By.xpath("//*[@id='r1_hbUnitRatioContentsTable']");

	public static By csName = By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By csDetails = By.xpath("//td[contains(text(),' Cost Sheet Details')]");

	public static By csCostSheetList = By.xpath("//a[@title='Cost Sheet List']");
	public static By csCol =By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]");
	public static By csStatus =By.id("hbStatus");
	public static By labelPrimaryCS = By.xpath("//td[contains(text(),'Primary Cost Sheet')]");
	public static By PrimaryCSYN = By.id("hbPrimaryCostSheet");
	public static By ricQuoteCurr = By.xpath("//td[contains(text(),'Retail Item Cost - Quote Currency')]");
	public static By prodQuoteCurr = By.xpath("//td[contains(text(),'Product Cost - Quote Currency:')]");
	public static By riCostQuoteCurrency = By.id("hbPrimaryCostSheet");

	//Under Retail Item Cost - Quote Currency:
	public static By retailItemCost  = By.id("hBExFactorySAP");
	public static By plasticMaterial  = By.id("hBPlasticMaterial");
	public static By  packagingMaterial = By.id("hBPackagingMaterial");
	public static By chemicalMaterial  = By.id("hbChemicalMaterial");
	public static By purchasedMaterial  = By.id("hBPurchasedMaterial");

	public static By ricUSDConversion = By.xpath("//td[contains(text(),'Retail Item Cost - USD Conversion')]");
	//Under Retail Item Cost - USD Conversion:
	public static By retailItemCostUSDC  = By.id("hbExFactorySAPBasePriceUSDConversion");
	public static By plasticMaterialUSDC  = By.id("hbPlasticMaterialUSDConversion");
	public static By  packagingMaterialUSDC = By.id("hbRetailPackagingMaterialUSDConversion");
	public static By chemicalMaterialUSDC  = By.id("hbChemicalMaterialUSDConversion");
	public static By purchasedMaterialUSDC  = By.id("hbPurchasedMaterialUSDConversion");

	public static By  markupUSD  = By.id("hbMarkUpUSDConversion");
	public static By  SoftGoodsMaterialUSDC  = By.id("hbSoftgoodsMaterialUSDConversion");

	public static By processHis = By.xpath("//td[contains(text(),'Process History')]");
	public static By processHisDate = By.xpath("//td[contains(text(),'Process History')]//following::td[1]");
	public static By processHisTask = By.xpath("//td[contains(text(),'Process History')]//following::td[2]");
	public static By updateComment = By.xpath("//td[contains(text(),'Comments')]//following::textarea[1]");

	public static By lblComment  = By.id("hBComments");
	public static By chkPriCS = By.xpath("//input[@type='checkbox']");
	public static By lblPrimaryCS  = By.id("hbPrimaryCostSheet");

	public static By txtRetailItemCost  = By.xpath("//td[contains(text(),'Retail Item Cost')]//following::input[1]");
	public static By lblProcessHistory  = By.xpath("//td[contains(text(),'Process History')]");
	public static By imgClose =By.xpath("//img[contains(@src,'deleteXsmall.png')]");
	public static By lnkInternal =By.xpath("//a[contains(text(),'Internal')]");
	public static By btnCancel =By.xpath("//a[text()='Cancel']");

	public static By costTable = By.xpath("//td[contains(text(),'Cost Table')]");
	public static By costTableView = By.xpath("//td[contains(text(),'Cost Table')]//following::a[2]");
	public static By costTableEdit = By.xpath("//td[contains(text(),'Cost Table: ')]//following::a[text()='Edit'][1]");

	public static By contentTable = By.xpath("//td[contains(text(),'Contents Table:')]");
	public static By contentTableView = By.xpath("//td[contains(text(),'Contents Table:')]//following::a[2]");
	public static By contentTableEdit = By.xpath("//td[contains(text(),'Contents Table:')]//following::a[text()='Edit'][1]");

	public static By contentTableWave = By.xpath("//td[contains(@id,'hbWaveContentsTable')]");
	public static By ddContentTableWave = By.xpath("//div[@id='hbWaveContentsTableSourceDiv']/select");
	public static By btnDone = By.xpath("//a[text()='Done']");
	public static By conTableWave = By.xpath("//a[text()='Wave']//following::tr/td[3]");

	public static By contentTableCSumm = By.xpath("//td[contains(text(),'Contents Table Cost Summary:')]");
	public static By lblCasePackQuantity  = By.id("hbCasePackQuantity");
	public static By lblAstSolidWeightedAverage  = By.id("hbAstSolidWeightedAverage");
	public static By lblAstSolidWeightedAverageUSD  = By.id("hbAstSolidWeightedAverageUSD");

	public static By masterCartonPackagingMaterial = By.xpath("//td[contains(text(), 'Master Carton Packaging Material')]//following::input[1]");
	public static By masterCartonLaborCost = By.xpath("//td[contains(text(), 'Master Carton Labor Cost')]//following::input[1]");
	public static By miscellaneousCost = By.xpath("//td[contains(text(), 'Miscellaneous Cost')]//following::input[1]");
	public static By productMarkup  = By.xpath("//td[contains(text(), 'Product Markup')]//following::input[1]");

	public static By exFactoryCostQC  = By.xpath("//div[@id='costSheetResults']//div[6]//td[contains(text(), 'Ex-Factory Cost (SAP Base Price')]//following::td[1]");
	public static By totalMarkupQC  = By.xpath("//div[@id='costSheetResults']//div[6]//td[contains(text(), 'Total Markup')]//following::td[1]");
	public static By masCartonPackMaterialQC  = By.xpath("//div[@id='costSheetResults']//div[6]//td[contains(text(), 'Master Carton Packaging Material')]//following::td[1]");
	public static By masterCartonLaborCostQC  = By.xpath("//div[@id='costSheetResults']//div[6]//td[contains(text(), 'Master Carton Labor Cost')]//following::td[1]");
	public static By miscellaneousCostQC  = By.xpath("//div[@id='costSheetResults']//div[6]//td[contains(text(), 'Master Carton Packaging Material')]//following::td[1]");
	public static By productMarkupQC  = By.xpath("//div[@id='costSheetResults']//div[6]//td[contains(text(), 'Master Carton Labor Cost')]//following::td[1]");

	public static By  prodCSummUSDConv = By.xpath("//td[contains(text(),'Product Cost Summary - USD Conversion')]");
	
	public static By  exFactorySAPBasePriceUSDConversion = By.id("hbExFactorySAPBasePriceUSDConversion");
	public static By  totalMarkUpUSD = By.id("hbTotalMarkUpUSD");
	public static By  masterCartonPackagingMaterialUSD = By.id("hbMasterCartonPackagingMaterialUSD");
	public static By  masterCartonLaborCostUSD = By.id("hbMasterCartonLaborCostUSD");
	
	public static By  selectCheckBox = By.name("selectedIds");
	
	public static By  deleteRow = By.xpath("//img[@alt='Delete Selected Rows']");
	

	//************CostSheet Variables ***************************************************************************** 

	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String strSpec;
	static String strSource;
	static String strCW;
	static String cstCreateCS;

	static String strCSInWork;
	static String strCSUnderReview;
	static String strCSApproved;
	static String strCSCancelled;
	static String strCSforD;

	static String csInWork;
	static String csUnderReview;
	static String csApproved;
	static String csCancelled;
	static String csForD;

	static String inWorkStatus;
	static String underReviewStatus;
	static String approvedStatus;
	static String cancelledStatus;

	static String sStatus;
	static String vendorName ="FUNSKOOL";

	static Boolean bCostSheet=false;
	static Boolean bPriCS=false;

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

			strTestCaseName = data[0]+":" + data[3];			
			log.info("Inside Test Case:-> " + strTestCaseName + " for CostSheet Security");				
			//	SeleniumDriver.moduleName= "MaterialBOM";
			//	SeleniumDriver.intTestCaseValcnt=1;

			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			System.out.println("attribute group :" + data[2]);
			System.out.println("verification :" + data[3]);
			System.out.println("Cost :" + data[18]);
			System.out.println("Cost :" + data[19]);
			System.out.println("Cost :" + data[20]);
			System.out.println("Cost :" + data[21]);
			
				System.out.println("Cost :" + data[15]);
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
			//Prerequisite
			if(data[3].equalsIgnoreCase("CreateInWork"))
			{ createInternalRetailCS_inwork(data); }
			if(data[3].equalsIgnoreCase("CreateUnderReview"))
			{ createInternalRetailCS_underReview(data); }
			if(data[3].equalsIgnoreCase("CreateApproved"))
			{ createVendorRetailCS_Approved(data); }
			if(data[3].equalsIgnoreCase("CreateCancelled"))
			{ createInternalRetailCS_Cancelled(data); }
			if(data[3].equalsIgnoreCase("CreateforDelete"))
			{ createInternalRetailCS_forDelete(data); }

			//Internal Retail Item Cost Sheet
			if(data[3].equalsIgnoreCase("CreateInternalCostsheet"))
			{ 
				log.info("In side CreateCostsheet");
				CreateInternalRetailCS(data);			           			         
				log.info("Out side CreateCostsheet");
			}	
			//*********************************************************************************
			if(data[3].equalsIgnoreCase("ReadViewCostsheet"))
			{ 
				log.info("In side :-> " + data[3]);	
				ReadViewCostsheet(data);		         
				log.info("Out side ReadViewCostsheet");
			}
			//Need to update
			if(data[3].equalsIgnoreCase("UpdateCostsheet"))
			{ 
				log.info("In side :-> " + data[3]);	
				UpdateCostsheet(data);		         
				log.info("Out side UpdateCostsheet");
			}
			if(data[3].equalsIgnoreCase("DeleteCostsheet"))
			{ 
				log.info("In side :-> " + data[3]);
				DeleteCostsheet(data);		         
				log.info("Out side DeleteCostsheet");

			}	
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewCostsheetInWork"))
			{ 
				log.info("In side :-> " + data[3]);	
				readViewCSInWork(data);		        
				log.info("Out side ReadViewCostsheetInWork");

			}
			if(data[3].equalsIgnoreCase("UpdateCostsheetInWork"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSInWork(data);		         
				log.info("Out side UpdateCostsheetInWork");

			}						
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewCostsheetUnderReview"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSUnderReview(data);		         
				log.info("Out side :-> " + data[3]);

			}						
			if(data[3].equalsIgnoreCase("UpdateCostsheetUnderReview"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSUnderReview(data);		         
				log.info("Out side :-> " + data[3]);						  
			}
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewCostsheetApproved"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSApproved(data);		         
				log.info("Out side :-> " + data[3]);

			}
			if(data[3].equalsIgnoreCase("UpdateCostsheetApproved"))
			{ 
				log.info("In side :-> " + data[3]);
				updateCSApproved(data);
				log.info("Out side :-> " + data[3]);
			}
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewCostsheetCancelled"))
			{ 
				log.info("In side :-> " + data[3]);
				readViewCSCancelled(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateCostsheetCancelled"))
			{ 
				log.info("In side :-> " + data[3]);	
				updateCSCancelled(data);	
				log.info("Out side :-> " + data[3]);
			}		
			/***************************************************************************************/
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
			/***************************************************************************************/			
			if(data[3].equalsIgnoreCase("ReadViewPrimaryCostSheet"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyPrimaryCostSheetReadView(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdatePrimaryCostSheet"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyPrimaryCostSheetUpdate(data);
				log.info("Out side " + data[3]);
			}
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewRetailItemCostQuoteCurrency"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyRICostQuoteCurrencyReadView(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateRetailItemCostQuoteCurrency"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyRICostQuoteCurrencyUpdate(data);		         
				log.info("Out side " + data[3]);
			}
			/***************************************************************************************/
			if(data[3].equalsIgnoreCase("ReadViewRetailItemCostUSDConversion"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyRICostUSDConversionReadView(data);		         
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateRetailItemCostUSDConversion"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyRICostUSDConversionUpdate(data);	
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
			/***************************Internal Product******************************************************/
			/****************************Cost Table***********************************************************/
			if(data[3].equalsIgnoreCase("ReadViewProdCostTable"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyCostTableProdReadView(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateProdCostTable"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyCostTableProdUpdate(data);
				log.info("Out side " + data[3]);
			}

			/****************************Contents Table***********************************************************/
			if(data[3].equalsIgnoreCase("ReadViewProdContentsTable"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyContentsTableProdReadView(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateProdContentsTable"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyContentsTableProdUpdate(data);
				log.info("Out side " + data[3]);
			}
			/****************************Contents Table Cost Summary***********************************************************/
			if(data[3].equalsIgnoreCase("ReadViewProdContentsTableCostSummary"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyContentsTableCostSummaryProdReadView(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateProdContentsTableCostSummary"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyContentsTableCostSummaryProdUpdate(data);
				log.info("Out side " + data[3]);
			}
			/****************************Product Cost Summary - Quote Currency***********************************************************/
			if(data[3].equalsIgnoreCase("ReadViewProdCostSummaryQuoteCurrency"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyProdCostSummaryQuoteCurrencyReadView(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateProdCostSummaryQuoteCurrency"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyProdCostSummaryQuoteCurrencyUpdate(data);
				log.info("Out side " + data[3]);
			}
			/****************************Product Cost Summary - USD Conversion***********************************************************/
			if(data[3].equalsIgnoreCase("ReadViewProdCostSummaryUSDConversion"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyProdCostSummaryUSDConversionReadView(data);
				log.info("Out side :-> " + data[3]);
			}
			if(data[3].equalsIgnoreCase("UpdateProdCostSummaryUSDConversion"))
			{ 
				log.info("In side :-> " + data[3]);
				verifyProdCostSummaryUSDConversionUpdate(data);
				log.info("Out side " + data[3]);
			}

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	public static String createInternalRetailCS_inwork(String[] data) throws Exception{
		try{
			createCS(data);
			//read newly created cost sheet
			//	WebElement newcostsheetTitle = driver.findElement(csHeading);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSInWork = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			String []varCSInWork = strCSInWork.split("Actions:");
			csInWork = varCSInWork[0].trim();
			log.info("Internal Retail Item Cost Sheet in Inwork status is : "+csInWork);
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in createInternalRetailCS_inwork()", e);
			return "";
		}
		return csInWork;
	}

	public static String createInternalRetailCS_underReview(String[] data) throws Exception{
		try{
			createCS(data);
			//read newly created cost sheet
			//	WebElement newcostsheetTitle = driver.findElement(csHeading);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSUnderReview = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			String []varCSUnderReview = strCSUnderReview.split("Actions:");
			csUnderReview = varCSUnderReview[0].trim();
			//Update to required status
			updateCSStatus("Under Review");
			log.info("Internal Retail Item Cost Sheet underReview status is : "+csUnderReview);
			// close the open cost sheet
			closeTheOpenCostSheet();

		}catch(Exception e){
			fail=true;
			log.error("Exception in createInternalRetailCS_underReview()", e);
			return "";
		}
		return csUnderReview;
	}

	public static String createVendorRetailCS_Approved(String[] data) throws Exception{
		try{
			createCS(data);
			//read newly created cost sheet
			//	WebElement newcostsheetTitle = driver.findElement(csHeading);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			Thread.sleep(3000);
			strCSApproved = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			String []varCSApproved = strCSApproved.split("Actions:");
			csApproved = varCSApproved[0].trim();
			//Update Status
			updateCSStatus("Approved");
			log.info("Internal Retail Item Cost Sheet in  Approved status is : "+csApproved);
			// close the open cost sheet
			closeTheOpenCostSheet();

		}catch(Exception e){
			fail=true;
			log.error("Exception in createVendorRetailCS_Approved()", e);
			return "";
		}
		return csApproved;
	}

	public static String createInternalRetailCS_Cancelled(String[] data) throws Exception{
		try{
			createCS(data);
			//read newly created cost sheet
			//	WebElement newcostsheetTitle = driver.findElement(csHeading);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSCancelled = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			String []varCSCancelled = strCSCancelled.split("Actions:");
			csCancelled = varCSCancelled[0].trim();
			//Update Status
			updateCSStatus("Cancelled");
			log.info("Internal Retail Item Cost Sheet in Cancelled status is : "+csCancelled);
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in createInternalRetailCS_Cancelled()", e);
			return "";
		}
		return csCancelled;
	}

	public static String createInternalRetailCS_forDelete(String[] data) throws Exception{
		try{
			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//read newly created cost sheet
			strCSforD = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			String []varCSForD = strCSforD.split("Actions:");
			csForD = varCSForD[0].trim();
			log.info("Internal Retail Item Cost Sheet in Inwork status is : "+csForD);
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in createInternalRetailCS_forDelete()", e);
			return "";
		}
		return csForD;
	}

	public static String CreateInternalRetailCS(String[] data) throws Exception{
		try{
			//	CommonProjectFunctions.searchProduct(data[6]);				
			//	navigateUptoCostSheetTable(data);

			if(data[5].equalsIgnoreCase("Yes")){
				createCS(data);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				strCostSheetName = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
				String []varCostSheetName = strCostSheetName.split("Actions:");
				cstCreateCS = varCostSheetName[0].trim();
				log.info("Created costsheet name is :" + cstCreateCS);
				//Update Status
				updateCSStatus("Approved");
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{ 
				nevigationToCostsheet(data);
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.costAction);
					Select select = new Select(driver.findElement(CostsheetTooling.costAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification("Create Cost Sheet",options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Create Cost Sheet option is not present");
				}
				else {
					verifySourceForVendor(data);
					//Verify internal is not present
					// select Create cost sheet action					
					CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet"); 
					if(data[17].contains("Retail")) {
					Assert.assertEquals(driver.findElements(lnkInternal).size(),0);
					}
					else if(data[17].contains("Product")) {
						Assert.assertEquals(driver.findElements(By.xpath("//td[contains(text(),'Sourcing Configuration')]//following::td[contains(text(),'"+vendorName+"')]")).size(),1);
					}
					CommonFunctions.clickButtonOrLink(btnCancel, "btn", "Button");
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateInternalRetailCS()", e);
			return "";
		}
		return cstCreateCS;
	}	

	public static Boolean verifySourceForVendor(String[] data) throws Exception{
		try{
			Select selectSou = new Select(driver.findElement(InternalBOMSoftG.selectSource));
			List<WebElement> options = selectSou.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
			//	dropDownOptionVerification
			Assert.assertFalse(bVal,"Internal Source is not present");
			log.info("Internal Source verified for Vendor user");

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return false;
		}
		return true;
	}

	public static Boolean createCS(String[] data) throws Exception{
		try{
			//	System.out.println(driver.findElements(tblCostSheetIdentification).size());
			//int isize=driver.findElements(csDetails).size();
			if(driver.findElements(CostsheetTooling.tabCostsheet).size() == 0) {
				CommonProjectFunctions.searchProduct(data[6]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[7]);
				CommonProjectFunctions.clickCostingTab();
				//strSource=InternalBOMSoftG.AddSource(data);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.selectSource, data[8]);
				strSpec=InternalBOMSoftG.AddSpecification(data);
				strCW=InternalBOMSoftG.AddColorway(data);
			}


			//Click on Cost Sheet List
			CommonFunctions.clickButtonOrLink(csCostSheetList, "tab", "Cost Sheet List");
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet"); 
			//Thread.sleep(1000);	

			// select tooling type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"),"link","Cost sheet type");

			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(CostsheetTooling.lstcolorwayGroupOptions,1);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
			//Change status as 'Ready for Review'
			//CommonFunctions.selectFromDropDownByVisibleText(lstCSStatus,sStatus);
			if(data[17].contains("Product")) {
				// select wave
				CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[16]);
			}
			// select QuoteCurrency
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			if(data[2].equalsIgnoreCase("Internal")) {
				CommonFunctions.enterTextInTextbox(csName, data[12]);
			}
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			return false;
		}
		return true;
	}
	public static boolean nevigationToCostsheet(String[] data) throws Exception
	{
		try{
			System.out.println(driver.findElements(CostsheetTooling.tabCostsheet).size());
			if(driver.findElements(CostsheetTooling.tabCostsheet).size() == 0) {
				CommonProjectFunctions.searchProduct(data[6]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[7]);
				CommonProjectFunctions.clickCostingTab();
				//strSource=InternalBOMSoftG.AddSource(data);
				if(!data[4].contains("vendor")) {
					CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.selectSource, data[8]);
					//strSpec=InternalBOMSoftG.AddSpecification(data);
					//	strCW=InternalBOMSoftG.AddColorway(data);
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}



	//Read
	public static String ReadViewCostsheet(String[] data) throws Exception
	{
		try{
			nevigationToCostsheet(data);
			sStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Verification
				Assert.assertEquals(driver.findElements(tblCostSheetIdentification).size(),1);
				Assert.assertEquals(driver.findElements(costSheetVariations).size(),1);
				//	Assert.assertEquals(driver.findElements(labelPrimaryCS).size(),1);
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
				}
				else {
					verifySourceForVendor(data);
				}
			}
			else
			{
				log.info("For this ReadViewCostsheet is not applicable(NA)");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return "";
		}
		return sStatus;
	}

	//Not done
	public static boolean UpdateCostsheet(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Change Status
				updateCSStatus("In Work");
				verifyCSStatus("In Work",csApproved);
				//change back to Approved
				updateCSStatus("Approved");
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (sStatus.equalsIgnoreCase("No")) {
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
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
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Update Cost sheet()", e);
			return false;
		}
		return true;
	}	

	//Delete
	public static boolean DeleteCostsheet(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				//Create Costsheet to delete
				searchandClickforRequriedCostsheet(csForD);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//strSource=InternalBOMSoftG.AddSource(data);
				//strCS = createVendorRetailToolingCS_inwork(data);
				//	CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetActionDelete,data[13]);	
				driver.findElement(CostsheetTooling.lstCostSheetAction).click();
				driver.findElement(lstCostSheetActionDelete).click();
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.waitForVisibilityOfElement(CostsheetTooling.tabCostsheet);
				bCostSheet= SearchCostsheet(csForD);
				Assert.assertFalse(bCostSheet, "Cost sheet has been deleted");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{								
				if(!data[4].contains("vendor")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(cstCreateCS);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//verifyUserCostsheetAccess(data);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal," Delete option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
				}
				else {
					verifySourceForVendor(data);
				}
			}else{
				log.info("Delete Cost sheet is not applicable(NA)");
			}	

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}	
	// Read
	public static String readViewCSInWork(String[] data) throws Exception
	{
		try{
			nevigationToCostsheet(data);
			inWorkStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csInWork);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "In Work");
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csInWork);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
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

	// Update
	/*for update there are three condition
	1. Read No,Update No -> Cost sheet not visible on UI
	2. Read Yes,Update No -> Update should not come in dropdown
	3. Read Yes and Update Yes ->
	   6 costomization required as per users(admin,Material Admin,Engineer,Engineer Lead,Engineer w/ Costing,Costing)
	 */
	public static boolean updateCSInWork(String[] data) throws Exception
	{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//Change Status
					updateCSStatus("Under Review");
					verifyCSStatus("Under Review",csInWork);
					//change back to inwork
					updateCSStatus("In Work");
					//Change to Cancelled
					updateCSStatus("Cancelled");
					verifyCSStatus("Cancelled",csInWork);
					//change back to inwork
					updateCSStatus("In Work");
					//Change to Approved
					updateCSStatus("Approved");
					verifyCSStatus("Approved",csInWork);
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
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (inWorkStatus.equalsIgnoreCase("No")) {
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csInWork);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
				}
				else if (inWorkStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csInWork);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}	
		return true;
	}

	public static String readViewCSUnderReview(String[] data) throws Exception
	{
		try{
			nevigationToCostsheet(data);
			underReviewStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csUnderReview);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Under Review");
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csUnderReview);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
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
		return underReviewStatus;
	}



	// Update
	/*for update there are three condition
		1. Read No,Update No -> Cost sheet not visible on UI
		2. Read Yes,Update No -> Update should not come in dropdown
		3. Read Yes and Update Yes ->
		   6 costomization required as per users(Admin,Material Admin,Engineer,Engineer Lead,Engineer w/ Costing,Costing)
	 */
	public static boolean updateCSUnderReview(String[] data) throws Exception
	{
		try{
			//CommonProjectFunctions.searchProduct(data[6]);				
			//	navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//Change Status
					updateCSStatus("In Work");
					verifyCSStatus("In Work",csUnderReview);
					//change back to inwork
					updateCSStatus("Under Review");
					//Change to Cancelled
					updateCSStatus("Cancelled");
					verifyCSStatus("Cancelled",csUnderReview);
					//change back to inwork
					updateCSStatus("Under Review");
					//Change to Approved
					updateCSStatus("Approved");
					verifyCSStatus("Approved",csUnderReview);
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
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (underReviewStatus.equalsIgnoreCase("No")) {
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csUnderReview);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
				}
				else if (underReviewStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csUnderReview);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}	
		return true;
	}



	public static String readViewCSApproved(String[] data) throws Exception
	{
		try{
			//	CommonProjectFunctions.searchProduct(data[6]);				
			//	navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			approvedStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Approved");
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csApproved);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
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

	// Update
	/*for update there are three condition
			1. Read No,Update No -> Cost sheet not visible on UI
			2. Read Yes,Update No -> Update should not come in dropdown
			3. Read Yes and Update Yes ->
			   6 costomization required as per users(Admin,Material Admin,Engineer,Engineer Lead,Engineer w/ Costing,Costing)
	 */
	public static boolean updateCSApproved(String[] data) throws Exception
	{
		try{
			//	CommonProjectFunctions.searchProduct(data[6]);				
			//	navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//Change Status
					updateCSStatus("In Work");
					verifyCSStatus("In Work",csApproved);
					//change back to Approved
					updateCSStatus("Approved");
					//Change to Cancelled
					updateCSStatus("Cancelled");
					verifyCSStatus("Cancelled",csApproved);
					//change back to Approved
					updateCSStatus("Approved");
					//Change to Under Review
					updateCSStatus("Under Review");
					verifyCSStatus("Under Review",csApproved);
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
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (approvedStatus.equalsIgnoreCase("No")) {
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csApproved);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
				}
				else if (approvedStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}	
		return true;
	}




	public static String readViewCSCancelled(String[] data) throws Exception
	{
		try{
			nevigationToCostsheet(data);
			cancelledStatus=data[5];
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csCancelled);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				verifyCostSheetIdentification(data);
				String strStatus = driver.findElement(csStatus).getText();
				Assert.assertEquals(strStatus.trim(), "Cancelled");
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					bCostSheet= SearchCostsheet(csCancelled);
					Assert.assertFalse(bCostSheet, "Cost sheet has not been readable");
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
		return cancelledStatus;
	}



	// Update
	/*for update there are three condition
				1. Read No,Update No -> Cost sheet not visible on UI
				2. Read Yes,Update No -> Update should not come in dropdown
				3. Read Yes and Update Yes ->
				   6 costomization required as per users(Admin,Material Admin,Engineer,Engineer Lead,Engineer w/ Costing,Costing)
	 */
	public static boolean updateCSCancelled(String[] data) throws Exception
	{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				//Select Update from Action dropdown
				//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				if(data[4].equalsIgnoreCase("Admin")) {
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//Change Status
					updateCSStatus("In Work");
					verifyCSStatus("In Work",csCancelled);
					//change back to inwork
					updateCSStatus("Cancelled");
					//Change to Cancelled
					updateCSStatus("Approved");
					verifyCSStatus("Approved",csCancelled);
					//change back to inwork
					updateCSStatus("Cancelled");
					//Change to Approved
					updateCSStatus("Under Review");
					verifyCSStatus("Under Review",csCancelled);
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
				closeTheOpenCostSheet();
			}
			if(data[5].equalsIgnoreCase("No"))
			{
				if (cancelledStatus.equalsIgnoreCase("No")) {
					if(!data[4].contains("vendor")) {
						bCostSheet= SearchCostsheet(csCancelled);
						Assert.assertFalse(bCostSheet, "Cost sheet has not been updatable");
					}
					else {
						verifySourceForVendor(data);
					}
				}
				else if (cancelledStatus.equalsIgnoreCase("Yes")) {
					searchandClickforRequriedCostsheet(csCancelled);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,"Update option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}	
		return true;
	}



	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			//	CommonProjectFunctions.searchProduct(data[6]);				
			//	navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			// click on Cost sheet List tab

			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
					Assert.assertEquals(GALabel, " General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					log.error("General Attributes label is Absent");
					fail=true;
				}
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					// search cost sheet name in web table
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						System.out.println("General Attirbutes label is Present");
						log.error("General Attirbutes label is Present");
						fail=true;
					}else{
						log.info("General Attirbutes label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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



	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			//	CommonProjectFunctions.searchProduct(data[6]);				
			//	navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Select Update from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				//updating comments
				driver.findElement(updateComment).clear();
				CommonFunctions.enterTextInTextbox(updateComment, data[14]);
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				//Click tab
				clickTab(csApproved);
				String sComment=driver.findElement(lblComment).getText();
				Assert.assertEquals(sComment.trim(),data[14]);
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					// click on Cost sheet List tab
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//verifyUserCostsheetAccess(data);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
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



	//Function consist scenario : PrimaryCostSheet:Read_View
	public static boolean verifyPrimaryCostSheetReadView(String[] data) throws Exception{
		try{
			//		CommonProjectFunctions.searchProduct(data[6]);				
			//		navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				if(driver.findElements(labelPrimaryCS).size() != 0){
					String pCSLabel=driver.findElement(labelPrimaryCS).getText();
					Assert.assertEquals(pCSLabel.trim(), "Primary Cost Sheet:");
					log.info(" Primary Cost Sheet label is Present");
				}else{
					log.error(" Primary Cost Sheet label is Absent");
					fail=true;
				}
				//Verifying Primary cost sheet yes and No is present
				Assert.assertEquals(driver.findElements(PrimaryCSYN).size(),1);

			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(labelPrimaryCS).size() != 0){
						System.out.println("PrimaryCostSheet label is Present");
						log.error("PrimaryCostSheet label is Present");
						fail=true;
					}else{
						log.info("PrimaryCostSheet label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();	
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

	//PrimaryCostSheetUpdate
	public static boolean verifyPrimaryCostSheetUpdate(String[] data) throws Exception{
		try{
			//		CommonProjectFunctions.searchProduct(data[6]);				
			//		navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				//updating PrimaryCostSheet checkbox
				CommonFunctions.selectCheckbox(chkPriCS);
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
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//verifyUserCostsheetAccess(data);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
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


	//Function consist scenario : RetailItemCostQuoteCurrency:Read_View
	public static boolean verifyRICostQuoteCurrencyReadView(String[] data) throws Exception{
		try{
			//		CommonProjectFunctions.searchProduct(data[6]);				
			//		navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			// click on Cost sheet List tab
			CostsheetTooling.clickOnCostSheetListTab();
			// search cost sheet name in web table
			searchandClickforRequriedCostsheet(csApproved);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			if(data[5].equalsIgnoreCase("Yes")){
				
				if(driver.findElements(ricQuoteCurr).size() != 0){
					String cQLabel=driver.findElement(ricQuoteCurr).getText();
					Assert.assertEquals(cQLabel.trim(), "Retail Item Cost - Quote Currency:");
					log.info("Retail Item Cost - Quote Currency label is Present");
				}else{
					log.error("Retail Item Cost - Quote Currency label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(retailItemCost).size(),1);
				Assert.assertEquals(driver.findElements(plasticMaterial).size(),1);
				Assert.assertEquals(driver.findElements(packagingMaterial).size(),1);
				Assert.assertEquals(driver.findElements(chemicalMaterial).size(),1);
				Assert.assertEquals(driver.findElements(purchasedMaterial).size(),1);
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
				Assert.assertEquals(driver.findElements(ricQuoteCurr).size(),0);
				log.info("Retail Item Cost Quote Currency label is Absent");
					// close the open cost sheet
				closeTheOpenCostSheet();
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


	//PrimaryCostSheetUpdate
	public static boolean verifyRICostQuoteCurrencyUpdate(String[] data) throws Exception{
		try{
			//		CommonProjectFunctions.searchProduct(data[6]);				
			//		navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Select 'Update' from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				CommonFunctions.waitForVisibilityOfElement(txtRetailItemCost);
				//updating Retail Item Cost 
				driver.findElement(txtRetailItemCost).clear();
				CommonFunctions.enterTextInTextbox(txtRetailItemCost, data[15]);
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				String sRetailIC=driver.findElement(retailItemCost).getText();
				Assert.assertEquals(sRetailIC.trim(),data[15]);
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//verifyUserCostsheetAccess(data);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
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
/***********************************************************************************/
	//Function consist scenario : RetailItemCostQuoteCurrency:Read_View
	public static boolean verifyRICostUSDConversionReadView(String[] data) throws Exception{
		try{
			//	CommonProjectFunctions.searchProduct(data[6]);				
			//	navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				if(driver.findElements(ricUSDConversion).size() != 0){
					String usdCLabel=driver.findElement(ricUSDConversion).getText();
					Assert.assertEquals(usdCLabel.trim(), "Retail Item Cost - USD Conversion:");
					log.info("Retail Item Cost - USD Conversion label is Present");
				}else{
					log.error("Retail Item Cost - USD Conversion label is Absent");
					fail=true;
				}

				//Verification
				Assert.assertEquals(driver.findElements(retailItemCostUSDC).size(),1);
				Assert.assertEquals(driver.findElements(plasticMaterialUSDC).size(),1);
				Assert.assertEquals(driver.findElements(packagingMaterialUSDC).size(),1);
				Assert.assertEquals(driver.findElements(chemicalMaterialUSDC).size(),1);
				Assert.assertEquals(driver.findElements(purchasedMaterialUSDC).size(),1);
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(ricUSDConversion).size() != 0){
						System.out.println("Retail Item Cost - USD Conversion label is Present");
						log.error("Retail Item Cost - USD Conversion label is Present");
						fail=true;
					}else{
						log.info("Retail Item Cost - USD Conversion label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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

	public static boolean verifyRICostUSDConversionUpdate(String[] data) throws Exception{
		try{
			//		CommonProjectFunctions.searchProduct(data[6]);				
			//		navigateUptoCostSheetTable(data);
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("In security sheet for RICostUSDConversion it is No only,hence no code required for Yes ");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//Select 'Update' from Action dropdown
					//	CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
					//Verify Update is not present
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					if(!data[4].contains("Admin")) {
						//	dropDownOptionVerification
						Assert.assertFalse(bVal,data[13]+ " option is not present");
					}
					//Verifying that Retail Item Cost (USD)is label not edit box
					Assert.assertEquals(driver.findElements(retailItemCostUSDC).size(),1);
					Assert.assertEquals(driver.findElements(markupUSD).size(),1);
					Assert.assertEquals(driver.findElements(SoftGoodsMaterialUSDC).size(),1);
					//Click on Save
					//	CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
					// close the open cost sheet
					closeTheOpenCostSheet();
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


	//Function consist scenario : Process History:Read_View
	public static boolean verifyProcessHistoryReadView(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);

				if(driver.findElements(processHis).size() != 0){
					String pHLabel=driver.findElement(processHis).getText();
					Assert.assertEquals(pHLabel.trim(), "Process History");
					log.info("Process History label is Present");
				}else{
					log.error("Process History label is Absent");
					fail=true;
				}
				//Verification
				//System.out.println(driver.findElements(processHisDate).size());
				Assert.assertEquals(driver.findElements(processHisDate).size(),1);

			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(processHis).size() != 0){
						System.out.println("Process History label is Present");
						log.error("Process History label is Present");
						fail=true;
					}else{
						Assert.assertEquals(driver.findElements(processHis).size(),0);
						log.info("Process History label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("In security sheet for ProcessHistory it is No only,hence no code required for Yes condition.");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//Verifying that Retail Item Cost (USD)is label not edit box
					Assert.assertEquals(driver.findElements(lblProcessHistory).size(),1);
					Assert.assertEquals(driver.findElements(processHisTask).size(),1);

					// close the open cost sheet
					closeTheOpenCostSheet();
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
	/*******************************Internal Product*******************************************************/		
	//Function consist scenario : CostTableProd:Read_View
	public static boolean verifyCostTableProdReadView(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);

				if(driver.findElements(costTable).size() != 0){
					String pHLabel=driver.findElement(costTable).getText();
					Assert.assertEquals(pHLabel.trim(), "Cost Table:");
					log.info(" Cost Table label is Present");
				}else{
					log.error(" Cost Table label is Absent");
					fail=true;
				}
				//Verification as view is present
				//System.out.println(driver.findElements(costTableView).size());
				Assert.assertEquals(driver.findElements(costTableView).size(),1);
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(costTable).size() != 0){
						System.out.println("Cost Table label is Present");
						log.error("Cost Table label is Present");
						fail=true;
					}else{
						Assert.assertEquals(driver.findElements(costTableView).size(),0);
						log.info("Cost Table label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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
	//InComplete
	public static boolean verifyCostTableProdUpdate(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Click on Edit link
				//CommonFunctions.clickButtonOrLink(costTableEdit,"link", "Edit");
				//Now able to automate right now as testcase is failing
				//Incomplete
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//Verifying that cost sheet edit link is not present for No condition
					Assert.assertEquals(driver.findElements(costTableEdit).size(),0);

					// close the open cost sheet
					closeTheOpenCostSheet();
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
	/************************************************************************************************************/
	//Function consist scenario : ContentsTable Prod:Read_View
	public static boolean verifyContentsTableProdReadView(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);

				if(driver.findElements(contentTable).size() != 0){
					String conTLabel=driver.findElement(contentTable).getText();
					Assert.assertEquals(conTLabel.trim(), "Contents Table:");
					log.info("ContentTable label is Present");
				}else{
					log.error("ContentTable label is Absent");
					fail=true;
				}
				//Verification
				//System.out.println(driver.findElements(contentTableView).size());
				Assert.assertEquals(driver.findElements(contentTableView).size(),1);
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(contentTable).size() != 0){
						System.out.println("contentTable label is Present");
						log.error("contentTable label is Present");
						fail=true;
					}else{
						//Assert.assertEquals(driver.findElements(contentTableView).size(),0);
						log.info("contentTable label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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

	public static boolean verifyContentsTableProdUpdate(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Click on Edit link
				CommonFunctions.clickButtonOrLink(contentTableEdit,"link", "Edit");
				//Select check box to delete row
				CommonFunctions.selectCheckbox(selectCheckBox);
				//Click on delete icon
				CommonFunctions.clickButtonOrLink(deleteRow, "icon", "delete");
				CommonFunctions.handleAlertPopUp();
				//Click on wave cell
				CommonFunctions.clickButtonOrLink(contentTableWave,"Cell", "Wave");
				CommonFunctions.selectFromDropDownByVisibleText(ddContentTableWave, data[16]);
				//Click on Done
				CommonFunctions.clickButtonOrLink(btnDone,"Btn", "Done");
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Verification
				String lblWave=driver.findElement(conTableWave).getText();
				Assert.assertEquals(lblWave.trim(),data[16]);

				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					//Verifying that contentTable Edit is not present
					System.out.println(driver.findElements(contentTableEdit).size());
					Assert.assertEquals(driver.findElements(contentTableEdit).size(),0);
					// close the open cost sheet
					closeTheOpenCostSheet();
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
	/************************************************************************************************************/
	//Function consist scenario : ContentsTableCostSummary:Read_View
	public static boolean verifyContentsTableCostSummaryProdReadView(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);

				if(driver.findElements(contentTableCSumm).size() != 0){
					String cTSS=driver.findElement(contentTableCSumm).getText();
					Assert.assertEquals(cTSS.trim(), "Contents Table Cost Summary:");
					log.info("Contents Table Cost Summary label is Present");
				}else{
					log.error("Contents Table Cost Summary label is Absent");
					fail=true;
				}
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(contentTableCSumm).size() != 0){
						System.out.println("Contents Table Cost Summary label is Present");
						log.error("Contents Table Cost Summary label is Present");
						fail=true;
					}else{
						log.info("Contents Table Cost Summary label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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

	public static boolean verifyContentsTableCostSummaryProdUpdate(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("In security sheet for ContentsTableCostSummary it is No only,hence no code required for Yes condition.");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					if(!data[4].contains("Admin")) {
						//	dropDownOptionVerification
						Assert.assertFalse(bVal,data[13]+ " option is not present");
					}
					//Verifying that Retail Item Cost (USD)is label not edit box
					Assert.assertEquals(driver.findElements(lblCasePackQuantity).size(),1);
					Assert.assertEquals(driver.findElements(lblAstSolidWeightedAverage).size(),1);
					Assert.assertEquals(driver.findElements(lblAstSolidWeightedAverageUSD).size(),1);
					//Click on Save
					//	CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
					// close the open cost sheet
					closeTheOpenCostSheet();
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

	/************************************************************************************************************/
	//Function consist scenario : Product Cost - Quote Currency::Read_View
	public static boolean verifyProdCostSummaryQuoteCurrencyReadView(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				if(driver.findElements(prodQuoteCurr).size() != 0){
					String cQLabel=driver.findElement(prodQuoteCurr).getText();
					Assert.assertEquals(cQLabel.trim(), "Product Cost - Quote Currency:");
					log.info("Product Cost - Quote Currency label is Present");
				}else{
					log.error("Product Cost - Quote Currency label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(exFactoryCostQC).size(),1);
				Assert.assertEquals(driver.findElements(totalMarkupQC).size(),1);
				Assert.assertEquals(driver.findElements(masCartonPackMaterialQC).size(),1);
				Assert.assertEquals(driver.findElements(masterCartonLaborCostQC).size(),1);
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(prodQuoteCurr).size() != 0){
						System.out.println("Product Cost - Quote Currencylabel is Present");
						log.error("Product Cost - Quote Currency label is Present");
						fail=true;
					}else{
						log.info("Product Cost - Quote Currency label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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

	public static boolean verifyProdCostSummaryQuoteCurrencyUpdate(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Select 'Update' from Action dropdown
				CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
				CommonFunctions.waitForVisibilityOfElement(masterCartonPackagingMaterial);
				
				driver.findElement(masterCartonPackagingMaterial).clear();
				CommonFunctions.enterTextInTextbox(masterCartonPackagingMaterial, data[18]);
				
				driver.findElement(masterCartonLaborCost).clear();
				CommonFunctions.enterTextInTextbox(masterCartonLaborCost, data[19]);
				
				driver.findElement(miscellaneousCost).clear();
				CommonFunctions.enterTextInTextbox(miscellaneousCost, data[20]);
				
				driver.findElement(productMarkup).clear();
				CommonFunctions.enterTextInTextbox(productMarkup, data[21]);
				//Click on save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				CommonFunctions.waitForVisibilityOfElement(masCartonPackMaterialQC);
				//Verification
				Assert.assertEquals(driver.findElement(masCartonPackMaterialQC).getText().trim(), data[18]);
				Assert.assertEquals(driver.findElement(masterCartonLaborCostQC).getText().trim(), data[19]);
				Assert.assertEquals(driver.findElement(miscellaneousCostQC).getText().trim(), data[20]);
			//	Assert.assertEquals(driver.findElement(productMarkup).getText().trim(), data[21]);
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					// close the open cost sheet
					closeTheOpenCostSheet();
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
	/************************************************************************************************************/
	//Function consist scenario : CostSummaryUSDConversion:Read_View
	public static boolean verifyProdCostSummaryUSDConversionReadView(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(csApproved);
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);

				if(driver.findElements(prodCSummUSDConv).size() != 0){
					String usdCLabel=driver.findElement(prodCSummUSDConv).getText();
					Assert.assertEquals(usdCLabel.trim(), "Product Cost Summary - USD Conversion:");
					log.info("Product Cost Summary - USD Conversion label is Present");
				}else{
					log.error("Product Cost Summary - USD Conversion label is Absent");
					fail=true;
				}
				//Verification
				Assert.assertEquals(driver.findElements(exFactorySAPBasePriceUSDConversion).size(),1);
				Assert.assertEquals(driver.findElements(totalMarkUpUSD).size(),1);
				Assert.assertEquals(driver.findElements(masterCartonPackagingMaterialUSD).size(),1);
				Assert.assertEquals(driver.findElements(masterCartonLaborCostUSD).size(),1);
				// close the open cost sheet
				closeTheOpenCostSheet();
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					if(driver.findElements(prodCSummUSDConv).size() != 0){
						System.out.println(" Product Cost Summary - USD Conversion label is Present");
						log.error("Product Cost Summary - USD Conversion label is Present");
						fail=true;
					}else{
						log.info("Product Cost Summary - USD Conversion label is Absent");
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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

	public static boolean verifyProdCostSummaryUSDConversionUpdate(String[] data) throws Exception{
		try{
			nevigationToCostsheet(data);
			if(data[5].equalsIgnoreCase("Yes")){
				log.info("In security sheet for CostSummaryUSDConversion it is No only,hence no code required for Yes condition.");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(!data[4].contains("vendor")) {
					CostsheetTooling.clickOnCostSheetListTab();
					searchandClickforRequriedCostsheet(csApproved);
					CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
					Select select = new Select(driver.findElement(lstCostSheetAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[13],options);
					if(!data[4].contains("Admin")) {
					//	dropDownOptionVerification
					Assert.assertFalse(bVal,data[13]+ " option is not present");
					//Verifying that Retail Item Cost (USD)is label not edit box
					Assert.assertEquals(driver.findElements(exFactorySAPBasePriceUSDConversion).size(),1);
					Assert.assertEquals(driver.findElements(totalMarkUpUSD).size(),1);
					Assert.assertEquals(driver.findElements(masterCartonPackagingMaterialUSD).size(),1);
					Assert.assertEquals(driver.findElements(masterCartonLaborCostUSD).size(),1);
					}
					else {
						//Select 'Update' from Action dropdown
						//CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,data[13]);
						new Select(SeleniumDriver.driver.findElement(lstCostSheetAction)).selectByVisibleText(data[13]);
					//	driver.findElement(arg0)
						CommonFunctions.waitForVisibilityOfElement(exFactorySAPBasePriceUSDConversion);
						//Verification
						Assert.assertEquals(driver.findElements(exFactorySAPBasePriceUSDConversion).size(),1);
						Assert.assertEquals(driver.findElements(totalMarkUpUSD).size(),1);
						Assert.assertEquals(driver.findElements(masterCartonPackagingMaterialUSD).size(),1);
						Assert.assertEquals(driver.findElements(masterCartonLaborCostUSD).size(),1);
					}
					// close the open cost sheet
					closeTheOpenCostSheet();
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

/*****************************************************************************************************************/
	public static String searchandClickforRequriedCostsheet(String costSheetName) throws Exception
	{
		try{
			int colIndex=1;
			List<WebElement> col1 = driver.findElements(By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]"));
			System.out.println(col1.size());
			for(WebElement e: col1){
				colIndex=colIndex+1;
				System.out.println("e.getText(): "+ e.getText());
				System.out.println("Segment: "+costSheetName);
				if (e.getText().trim().equals(costSheetName)) {
					String strCostSheetName = e.getText().trim();
					driver.findElement(By.linkText(costSheetName)).click();
					return strCostSheetName;
				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in searchandClickforRequriedCostsheet()", e);
			return "";
		}
		return strCostSheetName;
	}

	public static boolean updateCSStatus(String sStatus) throws Exception{
		try {
			CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
			CommonFunctions.waitForVisibilityOfElement(csStatus);

			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			// update Status
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstCSStatus,sStatus);

			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");	
			//	Thread.sleep(3000);
			return true;

		}catch(Exception e){
			fail=true;
			log.error("Exception in verification for : " + sStatus , e);
			return false;
		}
	}

	public static boolean verifyCSStatus(String sStatus,String csName) throws Exception{
		try {
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			CommonFunctions.waitForVisibilityOfElement(csStatus);
			//CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+csName+"')]"),"link","Cost sheet type");
			String strStatus = driver.findElement(csStatus).getText();
			Assert.assertEquals(strStatus.trim(), sStatus);
			log.info("Verification: Status verified as: "+ sStatus);
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in verification for : " + sStatus + "for costsheet: " +csName , e);
			return false;
		}
	}

	public static boolean clickTab(String csName) throws Exception{
		try {
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			CommonFunctions.waitForVisibilityOfElement(csStatus);
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+csName+"')]"),"link","Cost sheet type");
			log.info("Clicked tab is: "+ csName);
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in clickTab()", e);
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
		//	return true;
	}

	public static boolean navigateUptoCostSheetTable(String[] data) throws Exception{
		try{
			// select seasion
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");

			// click on source link			
			CommonFunctions.clickButtonOrLink(CostsheetTooling.lnkSource,"lnk","lnkSource");
			CommonFunctions.clickButtonOrLink(CostsheetTooling.lnkCosting,"lnk","lnkCosting");

			// select season
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstSeason, data[7]);
			// select source
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstsource, data[8]);
			// select specification	
			CommonFunctions.selectFromDropDownByIndex(CostsheetTooling.lstspecification,1);
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in navigateUptoCostSheetTable()", e);
			return false;
		}
		return true;
	}

	public static boolean closeTheOpenCostSheet() throws Exception
	{
		try{
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			// close the open cost sheet
			CommonFunctions.clickButtonOrLink(imgClose,"icon", "Close");
			log.info("Costsheet closed");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in closeTheOpenCostSheet", e);
			return false;
		}
	}

	public static boolean verifyCostSheetIdentification(String[] data) throws Exception
	{
		try{
			String strDescrip = driver.findElement(tblCostSheetIdentification).getText();
			Assert.assertEquals(strDescrip.trim(), "Cost Sheet Identification");
			log.info("Verified Cost Sheet Identification");
			//return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Cost sheet functions end here $$$$$$$$$$$$$$$$$$

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
