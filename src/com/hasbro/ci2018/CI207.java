package com.hasbro.ci2018;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.SeleniumDriver;

import com.hasbro.security.Product;

import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

public class CI207 extends TestsuiteBase{

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
	public static String productName;
	public static String prodNumber;
	public static String setQuantityCell;
	public static String waveOnShelfIdentification;
	public static String textBoxWaveOnShelfIdentification;
	private static final int CountOfColumns=33;
		
	//WebElements
	public static By ProductName = By.xpath("//td[contains(text(),'Product Name')]//following::input[1]");
	public static By Details =By.xpath("//a[text()='Details']");
	public static By labelWaveRequirements2Edit = By.xpath("//td[contains(text(),'Wave Requirements 2 - Quantity')]//following::a[1]");
	public static By labelWaveRequirements1Edit = By.xpath("//td[contains(text(),'Wave Requirements 1 - Dates')]//following::a[1]");
	public static By DoneButton=By.xpath(".//*[@id='contentDiv']/table/tbody/tr[7]/td/table/tbody/tr/td[2]");
    public static By labelWaveRequirements5Edit = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution')]//following::a[1]");
    public static By ItemQuantity=By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[2]/td[38]");
    public static By SourceDropDown=By.xpath("//td[contains(text(),'Source')]//following::select[1]");
    public static By SetQuantity=By.xpath("//td[contains(text(),'Set Qty')]//following::td[1]");
    public static By SetQuantity2=By.xpath("//td[contains(text(),'Set Qty')]//following::td[2]");
    public static By SetQuantityTextBox=By.xpath("//td[contains(text(),'Set Qty')]//following::td[1]/div/input[1]");
	public static By PercentageWaveRequirementColumn;
	public static By PercentageWaveForecast2Columns;
	public static By enterValuesInWaveRequirement;
	public static By enterValuesInWaveRequirement1;
	public static By enterValuesInWaveDistribution;
    public static By WaveDistributionColumns;
    public static By CasePack;
    public static By CasePacks;
    public static By ForeCastQuantityColumns;  
    public static By ForeCastQuantity;
    public static By SetQuantityCell;
    public static By WaveOnShelfIdetification;
    public static By TextBoxWaveOnShelfIdentification;
	
		
	//for edit multiple-object Collection page
	
  
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcCI(String[] productData) throws Exception{
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
				if(!loginVal.equalsIgnoreCase(productData[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
				}
			}
			if(runmodes[count].equalsIgnoreCase("y")){
				if(y==0){
					log.debug("Inside testcase for CI207");
					log.info("col0 :" + productData[0]);
					log.info("col2 :" + productData[2]);
					log.info("col3 :" + productData[3]);
					openBrowser();
					launchApp(productData[0],productData[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=productData[0];
					flaglogin=true;
				}
			
		switch(productData[3])
		{
		
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC1":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC1 is Column 1 of Forecase Quantity > (Total Global Forecast  Unit*1/12 AND Total Global Forecast  Unit*0.30)");
		//Common formulae was written in excel cells of AV(AV2) in CI207 sheet .so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity > Total Global Forecast  Unit*1/12  AND Column 1 of Forecase Quantity < Total Global Forecast  Unit*0.30)");
		//Common formulae was written in excel cells of AV(AV3) in CI207 sheet .so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC3":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity < Total Global Forecast  Unit*1/12");
		//Common formulae was written in excel cells of AV(AV4) in CI207 sheet.so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC4":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity < Total Global Forecast  Unit*1/12");
		//Common formulae was written in excel cells of AV(AV4) in CI207 sheet.so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC5":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity < Total Global Forecast  Unit*1/12");
		//Common formulae was written in excel cells of AV(AV4) in CI207 sheet.so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC6":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity < Total Global Forecast  Unit*1/12");
		//Common formulae was written in excel cells of AV(AV4) in CI207 sheet.so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC7":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC1 is Column 1 of Forecase Quantity > (Total Global Forecast  Unit*1/12 AND Total Global Forecast  Unit*0.30)");
		//Common formulae was written in excel cells of AV(AV2) in CI207 sheet .so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
			
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC8":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity > Total Global Forecast  Unit*1/12  AND Column 1 of Forecase Quantity < Total Global Forecast  Unit*0.30)");
		//Common formulae was written in excel cells of AV(AV3) in CI207 sheet .so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
			
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC9":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity < Total Global Forecast  Unit*1/12");
		//Common formulae was written in excel cells of AV(AV4) in CI207 sheet.so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
			
	    case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC10":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity < Total Global Forecast  Unit*1/12");
		//Common formulae was written in excel cells of AV(AV4) in CI207 sheet.so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC11":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity < Total Global Forecast  Unit*1/12");
		//Common formulae was written in excel cells of AV(AV4) in CI207 sheet.so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		case "AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC12":
		log.info("Condition for AutoUpdate Set Qty Based on Forecast Qty and Total Global ForeCast_TC2 is Column 1 of Forecase Quantity < Total Global Forecast  Unit*1/12");
		//Common formulae was written in excel cells of AV(AV4) in CI207 sheet.so just need a test data updation as per above condition.
		TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(productData);
		break;
		case "AutoUpdate Set Qty Based on Wave - On Shelf date Row_TC1":
		log.info("Condition for AutoUpdate Set Qty Based on Wave - On Shelf date Row_TC1 is on Wave - On Shelf date value After 09/01/YYYY");
		//Common formulae was written in excel cells of AV(AV5) in CI207 sheet.so just need a test data updation as per above condition.
		TC_13_14_15_AutoupdateSetQtyBasedOnWave_OnShelfDate(productData);
		break;
		
		case "AutoUpdate Set Qty Based on Wave - On Shelf date Row_TC2":
		log.info("Condition for AutoUpdate Set Qty Based on Wave - On Shelf date Row_TC1 is on Wave - On Shelf date value before 09/01/YYYY");
		//Common formulae was written in excel cells of AV(AV6) in CI207 sheet.so just need a test data updation as per above condition.
		TC_13_14_15_AutoupdateSetQtyBasedOnWave_OnShelfDate(productData);
		break;
		
		case "AutoUpdate Set Qty Based on Wave - On Shelf date Row_TC3":
		log.info("Condition for AutoUpdate Set Qty Based on Wave - On Shelf date Row_TC1 is on Wave - On Shelf date value is blank");	
		//Common formulae was written in excel cells of AV(AV7) in CI207 sheet.so just need a test data updation as per above condition.
		TC_13_14_15_AutoupdateSetQtyBasedOnWave_OnShelfDate(productData);
		break;
		
		case "AutoUpdate Set Qty Based on CF/Other/Empty Selection_TC1":
		//For CF value Selection 
		TC_16_17_18_AutoupdateSetQtyBasedOn_CF_Other_Empty_Selection(productData);
		break;
		
		case "AutoUpdate Set Qty Based on CF/Other/Empty Selection_TC2":
		//For Other value selection
		TC_16_17_18_AutoupdateSetQtyBasedOn_CF_Other_Empty_Selection(productData);
		break;
		
		case "AutoUpdate Set Qty Based on CF/Other/Empty Selection_TC3":
		//For Empty value Selection
		TC_16_17_18_AutoupdateSetQtyBasedOn_CF_Other_Empty_Selection(productData);
		break;
		case "Restriction of Manual Update in set Qty":
		TC_19_RestrictonForSetQtyManualUpdate(productData);
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
	
	
	
 public static boolean TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast(String [] productData) throws Exception{
	 //This Function describes 3 test cases with a common Formulae provided in excel.
	 //This Function is about Auto updation of Set Quantity Based on the Forecast Quantity and Total Global Forecast.
		try{
			
			if(productData[31].equalsIgnoreCase(productData[32])){
			    //Indicates to un check/check Manual input Wave Forecast check Box.if both are no then check box will be unchecked.if yes then check box will be Checked. 
		CreateProdFromLineSheetForCI207(productData[2],productData[5],productData[9],productData[8],productData[10],productData[11],productData[12],
						productData[13],productData[14],productData[16],productData[6],productData[7],productData[17],productData[18],productData[19],productData[20],productData[21],productData[25],
				        productData[26],productData[27],productData[28],productData[29],productData[30],productData[31],productData[32],productData[33],productData[34],productData[30]);
				//Code to create a Product Without checking the Manual input Wave Forecast check Box.
		CI260.EditWaveRequirement2(productData[36], productData[37]);
		// Code To navigate the page To edit the wave requirement 2 table
		CI260.enterPercentageWaveForecastValuesWR2(productData[39], productData[41]);
		//code enter the values in percentage waveForecast
		CI260.ForecastQuantityVerification(productData[42]);
		//Code to verify the Forecast quantity calculated from Percentage waveForecast. 
		SetQtyAssertionVerifcationForDifferentValues(productData);
		//Verifying the Different values of Set Qty value using Assertion
		
		}
			if(productData[31].equalsIgnoreCase(productData[33])){
				//Indicates to un check/check Manual input Wave Forecast check Box.if both are no then check box will be unchecked.if yes then check box will be Checked. 
				CreateProdFromLineSheetForCI207(productData[2],productData[5],productData[9],productData[8],productData[10],productData[11],productData[12],
						productData[13],productData[14],productData[16],productData[6],productData[7],productData[17],productData[18],productData[19],productData[20],productData[21],productData[25],
						productData[26],productData[27],productData[28],productData[29],productData[30],productData[31],productData[32],productData[33],productData[34],productData[30]);
				//Code to create a Product With checking the Manual input Wave Forecast check Box.
				CI260.EditWaveRequirement2(productData[36], productData[37]);
				// Code To navigate the page To edit the wave requirement 2 table
				CI260.enterWaveForecastQuantityValuesWR2(productData[39], productData[41]);
				//Entering the Value in Wave forecast Quantity Value tables
				SetQtyAssertionVerifcationForDifferentValues(productData);
				//Verifying the Different values of Set Qty value using Assertion
				}
			if(CommonFunctions.error==false){
			log.info("AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast TEST CASE VERIFIED SUCCESSFULLY");
			}
			else{
				log.error("Assertion error appears");
			}
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_Upto12_AutoupdateSetQtyBasedOnForecastQtyAndTotlaGlobalForecast"+e);
			throw e;
		}
		return true;
	    }
	
	public static boolean TC_13_14_15_AutoupdateSetQtyBasedOnWave_OnShelfDate(String [] productData) throws Exception{
		 //This Function describes 3 test cases with a common Formulae provided in excel.
		 //This Function is about Auto updation of Set Quantity Based OnWave_OnShelf Date.
		try{
			CreateProdFromLineSheetForCI207(productData[2],productData[5],productData[9],productData[8],productData[10],productData[11],productData[12],
					productData[13],productData[14],productData[16],productData[6],productData[7],productData[17],productData[18],productData[19],productData[20],productData[21],productData[25],
			        productData[26],productData[27],productData[28],productData[29],productData[30],productData[31],productData[32],productData[33],productData[34],productData[30]);
			//Creating a Product
			EditWaveRequirement1(productData[36], productData[37]);
			//navigating to Wave Requirement1 table Edit Button
			EnterWaveOnShelfDate(productData[54],productData[41]);
			//Entering the Date Value
			SetQtyAssertionVerifcationForDifferentValues(productData);
			//Verifying the Different values of Set Qty value using Assertion 
			if(CommonFunctions.error==false){
			log.info("AutoupdateSetQtyBasedOnWave_OnShelfDate TEST CASE PASSED SUCCESSFULLY");
			}
			else{
				log.error("Assertion error appears");
			}
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_13_14_15_AutoupdateSetQtyBasedOnWave_OnShelfDate"+e);
			throw e;
		}
		return true;
	}
	
	public static boolean TC_16_17_18_AutoupdateSetQtyBasedOn_CF_Other_Empty_Selection(String [] productData) throws Exception{
		//This Function is about Auto updation of set quantity based on the Internal Attribute selection for particular values(CF/Others/"")
		try{
			CreateProdFromLineSheetForCI207(productData[2],productData[5],productData[9],productData[8],productData[10],productData[11],productData[12],
					productData[13],productData[14],productData[16],productData[6],productData[7],productData[17],productData[18],productData[19],productData[20],productData[21],productData[25],
			        productData[26],productData[27],productData[28],productData[29],productData[30],productData[31],productData[32],productData[33],productData[34],productData[30]);
			//Creating a Product
			moveToDetailsPage();
			//Navigating to details Page
			SetQtyAssertionVerifcationForUniqueValue(productData);
			//Verifying the unique value of Set Qty Value using Assertion
			if(CommonFunctions.error==false){
			log.info("AutoupdateSetQtyBasedOnWave_OnShelfDate TEST CASE PASSED SUCCESSFULLY");
			}
			else{
				log.error("Assertion error appears");
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in TC_16_17_18_AutoupdateSetQtyBasedOn_CF_Other_Empty_Selection"+e);
			throw e;
		}
		return true;
	}
	
	public static boolean TC_19_RestrictonForSetQtyManualUpdate(String [] productData) throws Exception{
		//This Function says Restriction of set quantity manual Updation.
		try{
			CreateProdFromLineSheetForCI207(productData[2],productData[5],productData[9],productData[8],productData[10],productData[11],productData[12],
					productData[13],productData[14],productData[16],productData[6],productData[7],productData[17],productData[18],productData[19],productData[20],productData[21],productData[25],
			        productData[26],productData[27],productData[28],productData[29],productData[30],productData[31],productData[32],productData[33],productData[34],productData[30]);
			//Creating a Product
			CI260.EditWaveRequirement2(productData[36], productData[37]);
			//navigating to editWaveRequirement2 page
			enterValueInSetQty(productData);
			//Entering value into Set Qty text boxes 
			CommonFunctions.handleAlertPopUp1();
			//Alert handling
			CommonFunctions.AssertEqualsVerification(CommonFunctions.alertMsg, productData[56], "Actual and expected are not matched.please verify ");
			//verifying the error messages of actual and expected.
			if(CommonFunctions.error==false){
				log.info("RestrictonForSetQtyManualUpdate is verified Successfully");
			}
			else{
				log.error("Assertion error appears");
			}
			}
		catch(Exception e){
			fail=true;
			log.error("Exception while TC_19_RestrictonForSetQtyManualUpdate"+e);
			throw e;
		}
		return true;
	}
	
	public static String CreateProdFromLineSheetForCI207(String prodType,String year,String strlineSheetView,String strlineSheetAction,
			String strClass,String strDivision,String strBrand,String strInternalClassification,String AstSolid,String strIPSensitive,
			String strElectronicsIncluded,String strSoftgoodsIncluded,String strDistributionChannel,String strSRPPriceUSD,String strUSDomestic
			,String strLCPriceUSD,String strDOMPriceUSD,String strGlobalLAMUnit,String strGlobalNAUnit,String strGlobalEUUnit,String strGlobalASIAUnit,String strGlobalPACIFICUnit,
			String verifyProductPageTitle,String strManualInputWaveForecastValue,String strManualInputWaveForecastValueNo,String strManualInputWaveForecastValueYes,String strIntroTiming,String strPageTitle ) throws Exception{
		try{
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(CI260.mySeasonYear, year);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
		    CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetView, strlineSheetView);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetAction, strlineSheetAction);
			//Click on Assortment/Solid
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(By.xpath("//td[contains(text(),'Choose a Type')]"));
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+prodType+"')]"), "link", "Product Type");
			CommonFunctions.waitForPageLoaded();
	        productName=CommonFunctions.getRandomString(4);
			CommonFunctions.waitForElementTobeClickable(Product.ProductName);
			SeleniumDriver.driver.findElement(Product.ProductName).clear();
			CommonFunctions.enterTextInTextbox(Product.ProductName,productName);
			if(!prodType.equalsIgnoreCase("Trademark Display")){
				//Select Class
				CommonFunctions.waitForElementTobeClickable(Product.Class);
				CommonFunctions.enterTextInTextbox(Product.Class, strClass);
				CommonFunctions.waitForElementTobeClickable(Product.Division);
				CommonFunctions.enterTextInTextbox(Product.Division, strDivision);
				CommonFunctions.enterTextInTextbox(Product.Brand, strBrand);
				if(!prodType.equalsIgnoreCase("Trade Marketing Pallet")){
					CommonFunctions.waitForElementTobeClickable(Product.InternalClassification);
					CommonFunctions.enterTextInTextbox(Product.InternalClassification,strInternalClassification);
					//	CommonFunctions.enterTextInTextbox(AstSolid, productData[14]);
					CommonFunctions.waitForElementTobeClickable(Product.AstSolid);
					CommonFunctions.selectFromDropDownByVisibleText(Product.AstSolid, AstSolid);
					//	CommonFunctions.enterTextInTextbox(IPSensitive,productData[16]);
					CommonFunctions.selectFromDropDownByVisibleText(Product.IPSensitive,strIPSensitive);
				}

				if(prodType.equalsIgnoreCase("Retail")|| prodType.equalsIgnoreCase("Bundle Pack")){
					//Electronics Included
					CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,strElectronicsIncluded);
					//Softgoods Included
					CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,strSoftgoodsIncluded);
				}
			}
			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			wait.until(ExpectedConditions.titleIs(strPageTitle));
			CommonFunctions.waitForElementTobeClickable(Product.distributionChannel);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.lebelProdNum);
			prodNumber=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
		if(!prodType.equalsIgnoreCase("Trademark Display")){
			CommonFunctions.waitForElementTobeClickable(Product.distributionChannel);
				CommonFunctions.enterTextInTextbox(Product.distributionChannel,strDistributionChannel);
				if(prodType.equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,strSRPPriceUSD);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.IntroTiming, strIntroTiming, "Intro Timing Attribute");
					CommonFunctions.enterTextInTextbox(Product.USDomestic,strUSDomestic);
					CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,strLCPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,strDOMPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.GlobalLamForecast,strGlobalLAMUnit);
					CommonFunctions.enterTextInTextbox(Product.GlobalNAForeCast,strGlobalNAUnit);
					CommonFunctions.enterTextInTextbox(Product.GlobalEuForeCast,strGlobalEUUnit);
					CommonFunctions.enterTextInTextbox(Product.GlobalAsiaForeCast,strGlobalASIAUnit);
					CommonFunctions.enterTextInTextbox(Product.GlobalPacificForecast,strGlobalPACIFICUnit);
					if(strManualInputWaveForecastValue.equalsIgnoreCase(strManualInputWaveForecastValueYes)){
						CommonFunctions.waitForElementTobeClickable(CI260.ManualInputWaveForeCast);
						CommonFunctions.clickButtonOrLink(CI260.ManualInputWaveForeCast, "checkbox", "Manual Input Wave Forcast");
						log.info("User allowed to give value only in Forecast Qty columns because ManualInputWaveForeCastValue is"+" "+strManualInputWaveForecastValue);
						log.warn("User not allowed to give value in % waveForecast columns because ManualInputWaveForeCastValue is"+" "+strManualInputWaveForecastValue);
					}
					
					if(strManualInputWaveForecastValue.equalsIgnoreCase(strManualInputWaveForecastValueNo)){
						log.info("User allowed to give value only in % waveForecast columns because ManualInputWaveForeCastValue is"+" "+strManualInputWaveForecastValue);
						log.warn("User not allowed to give value in Forecast Qty columns because ManualInputWaveForeCastValue is"+" "+strManualInputWaveForecastValue);
					}
				}
				if(prodType.equalsIgnoreCase("Retail")|| prodType.equalsIgnoreCase("Bundle Pack")){
					CommonFunctions.enterTextInTextbox(Product.targetCostUSD, strDOMPriceUSD);
				}
			}
			//Click View Product Button
		    log.info("Product Number is "+prodNumber);
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.titleIs(verifyProductPageTitle));
			CI260.getPageTitle();
			CommonFunctions.AssertEqualsVerification(CI260.pageTitle, verifyProductPageTitle,"ERROR:Pagetile is not correct.please verify");
			log.info(CI260.pageTitle+" Page successfully appears");
			//	log.info(prodNum);
		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in CreateProductFromLineSheet()", e);
		    throw e;
		}
		return prodNumber;
	
	}

	
	public static boolean EditWaveRequirement1(String strSourceConfigValue,String strPageTitle) throws Exception {
		//Entering the Values in Edit Requirement table1
		 boolean value = false;
		 try{
		 CommonFunctions.waitForPageLoaded();	
		 CommonFunctions.waitForElementTobeClickable(Details);
		 CommonFunctions.clickButtonOrLink(Details, "link", "Details");
		 CommonFunctions.waitForPageLoaded();
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("window.scrollBy(0,1675)", "");
		 jse.executeScript("window.scrollBy(1000,0)", "");
		 CommonFunctions.waitForElementTobeClickable(labelWaveRequirements1Edit);
		 CommonFunctions.clickButtonOrLink(labelWaveRequirements1Edit, "link", "WaveRequirementWave1Edit");
		 CommonFunctions.waitForPageLoaded();
		 CI260.getPageTitle();
		 CommonFunctions.AssertEqualsVerification(CI260.pageTitle,strPageTitle,"ERROR:The Page Name is not correct.Please verify");
		 log.info( CI260.pageTitle +"Page Appears");
		 value=true;
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("Exception in CreateProductFromLineSheet()", e);
			 throw e;
		 }
		return value;
		
	}
	
	public static boolean moveToDetailsPage() throws Exception  {
		//Navigating into Details Page pf Product
		try{
		CommonFunctions.waitForPageLoaded();	
		 CommonFunctions.waitForElementTobeClickable(Details);
		 CommonFunctions.clickButtonOrLink(Details, "link", "Details");
		 CommonFunctions.waitForPageLoaded();
		}
		catch(Exception  e){
			fail=true;
			log.error("Exception while moving to details page");
		    throw e;
		}
		return true;
		
	}
	
	public static boolean SetQtyAssertionVerifcationForDifferentValues(String [] productData){
		//Verifying the Different values of Set Qty Value using Assertion
		try{
			for(int i=1;i<=CountOfColumns;i++){
				//This loop verify the all the 33 cell values of Set Qty  
				setQuantityCell="//td[contains(text(),'Set Qty')]//following::td["+i+"]";
				SetQuantityCell=By.xpath(setQuantityCell);
				CommonFunctions.waitForElementTobeClickable(SetQuantityCell);
				CommonFunctions.waitForPageLoaded();
				CI260.GettingText(SetQuantityCell);
				//Getting values of set Quantity cell
				if(i==1){
			     CommonFunctions.AssertEqualsVerification(CI260.ActualValue, productData[55],"Actual and Expected set quantity Value are not Matched.please Check");
			    //verifying the Actual and Expected using Assertion  for first column
				}
				else{
			      CommonFunctions.AssertEqualsVerification(CI260.ActualValue, productData[58],"Actual and Expected set quantity Value are not Matched.please Check");
					//Verifying value for remaining columns
				}
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception while Verifying the values" +e);
			throw e;
			}
		return true;
	}
		public static boolean SetQtyAssertionVerifcationForUniqueValue(String [] productData){
			//Verifying the unique value of Set Qty Value using Assertion
			try{
			    for(int i=1;i<=CountOfColumns;i++){
					//This loop verify the all  cell values of Set Qty  as per The CountOfColumns value
					setQuantityCell="//td[contains(text(),'Set Qty')]//following::td["+i+"]";
					SetQuantityCell=By.xpath(setQuantityCell);
					CommonFunctions.waitForElementTobeClickable(SetQuantityCell);
					CI260.GettingText(SetQuantityCell);
				    //Getting values of set Quantity cell 
				    CommonFunctions.AssertEqualsVerification(CI260.ActualValue, productData[55],"Actual and Expected set quantity Value are not Matched.please Check");
				    //verifying the Actual and Expected using Assertion
				}
			}
			catch(Exception e){
				fail=true;
				log.error("Exception while Verifying the values");
				throw e;
				}
			return true;
	}
	public static boolean enterValueInSetQty(String [] productData) throws Exception {
		try{
			//manually Entering the Value in Set Qty testx Box
			CommonFunctions.waitForElementTobeClickable(SetQuantity);
			CommonFunctions.clickButtonOrLink(SetQuantity, "Text box");
			CommonFunctions.waitForElementTobeClickable(SetQuantityTextBox);
			CommonFunctions.enterTextInTextboxUpdated(SetQuantityTextBox, productData[57], "internalTextBox");
			CommonFunctions.waitForElementTobeClickable(SetQuantity2);
			CommonFunctions.clickButtonOrLink(SetQuantity2, "textBox");
		  }
		catch(Exception e){
			fail=true;
			log.error("Exception in enterValueInSetQty"+e);
			throw e;
		}
		return true;
	}
		public static boolean EnterWaveOnShelfDate(String strDate,String strPageTitle) throws Exception{
			//Entering wave-on Shelf date in Wave Requirement Table 1 
			try{
				for(int i=1,j=1;i<=CountOfColumns;i++){
				waveOnShelfIdentification="//td[contains(text(),'Wave - On Shelf Date')]//following::td["+i+"]";
				//String of Xpath
				WaveOnShelfIdetification=By.xpath(waveOnShelfIdentification);
				//Assigning waveOnShelfIdentification String into WebeElement
				textBoxWaveOnShelfIdentification=waveOnShelfIdentification+"/div/input["+j+"]";
				//String of Xpath
				TextBoxWaveOnShelfIdentification=By.xpath(textBoxWaveOnShelfIdentification);
				//Assigning textBoxWaveOnShelfIdentification String into WebeElement
				CommonFunctions.waitForElementTobeClickable(WaveOnShelfIdetification);
				CommonFunctions.clickButtonOrLink(WaveOnShelfIdetification, "textBox", "WaveOnShelf text box");
				CommonFunctions.waitForElementTobeClickable(TextBoxWaveOnShelfIdentification);
				CommonFunctions.enterTextInTextboxUpdated(TextBoxWaveOnShelfIdentification, strDate, "WaveonSheldDate");
				//Entering the Vale in Wave OnShelf Text box 
				log.info("Date has been entered in text Box number:"+i);
				}
				 CommonFunctions.clickButtonOrLink(DoneButton, "btn", "Done");
				 wait.until(ExpectedConditions.titleIs(strPageTitle));
				CommonFunctions.waitForElementTobeClickable(SourceDropDown);
				 CommonFunctions.waitForPageLoaded();
			     CI260.getPageTitle();
				 CommonFunctions.AssertEqualsVerification(CI260.pageTitle, strPageTitle,"ERROR:The Page Name is not correct.Please verify");
				 log.info( CI260.pageTitle+ " "+"Page appears ");
				}
		   catch(Exception e){
			 fail=true;
			 log.error("Exception in EnterWaveOnShelfDate()", e);
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
