package com.hasbro.ci2018;
/**
 * @author Anjali Gupta
 *
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.hasbro.security.Material;
import com.hasbro.security.Product;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;


public class CI260 extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	
	public static By libraryLink = By.id("librariesContentIcon");
	public static String productName;
	public static String prodNumber;
	public static String foreCastQuantityString;
	public static String itemQuantityString;

	public static By seasonLink = By.linkText("Season");
	public static By newLink = By.xpath("//a[text()='New']");
	public static By productType = By.xpath("//a[contains(text(),'Product Type')]");
	public static By retailItem = By.xpath("//a[text()='Retail Item']");
	public static By seasonYear = By.id("ptc_str_1");
	public static By createBtn = By.xpath("//a[text()='Create']");
	//My Season 
	public static By mySeasonLink = By.id("seasonsContentIcon");
	public static By mySeasonYear = By.id("seasonSelectList");
	public static By lineSheet = By.linkText("Line Sheet");
	public static By lineSheetView = By.id("viewId");
	public static By lineSheetAction = By.name("linePlanActions");
	public static By DetailsTab =By.linkText("Details");
	public static By SeasonDropDown = By.xpath("//select[@id='splId']");
    public static By ActionsDropDown=By.xpath(".//select[@id='prodseasonActions']");



	public static By labelWaveRequirements2 = By.xpath("//td[contains(text(),'Wave Requirements 2 - Quantity')]");
    public static By labelWaveRequirements5 = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution')]");
    public static By labelWaveRequirements2Edit = By.xpath("//td[contains(text(),'Wave Requirements 2 - Quantity')]//following::a[1]");
    public static By labelWaveRequirements5Edit = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution')]//following::a[1]");

	public static By AssortmentSolid = By.xpath("//a[contains(text(),'Assortment/Solid')]");
	

	public static By Division = By.xpath("//td[contains(text(),'Division (PLC)')]//following::select[1]");
	public static By Brand = By.xpath("//td[contains(text(),'*Brand (PLC)')]//following::select[1]");
	public static By segment = By.xpath("//td[contains(text(),'Segment (PLC)')]//following::select[1]");
	public static By InternalClassification = By.xpath("//td[contains(text(),'Internal Classification')]//following::select[1]");
	public static By AstSolid = By.xpath("//td[contains(text(),'*Ast')]//following::select[1]");
	public static By ProductName = By.xpath("//td[contains(text(),'Product Name')]//following::input[1]");
	public static By ROProductName = By.id("hbProductName");
	public static By IPSensitive = By.xpath("//td[contains(text(),'IP Sensitive')]//following::select[1]");
	public static By TotalGlobalForecastUnit=By.xpath("//td[contains(text(),'Total Global Forecast Units')]//following::td[1]");
	public static By SaveBtn = By.xpath("//a[text()='Save']");
	//Create Product-Season Attributes Page
	public static By viewProductBtn = By.xpath("//a[text()='View Product.']");
	public static By distributionChannel  = By.xpath("//td[contains(text(),'Distribution Channel')]//following::select[1]");
	public static By SRPPriceUSD= By.xpath("//td[contains(text(),'SRP Price')]//following::input[1]");
	public static By USDomestic = By.xpath("//td[contains(text(),'US Domestic')]//following::input[1]");
	public static By LCPriceUSD = By.xpath("//td[contains(text(),'L/C Price')]//following::input[1]");
	public static By DOMPriceUSD= By.xpath("//td[contains(text(),'DOM Price ')]//following::input[1]");

	
	public static By Details =By.xpath("//a[text()='Details']");
    public static By ManualInputWaveForeCast=By.xpath("//td[contains(text(),'Manually Input Wave Forecasts')]//following::input[2]");
    public static String IncreaseColumnPercentageWaveForecast; 
	public static String IncreaseColumnPercentageWaveForecast1; 
	public static String IncreaseColumnWaveQuantityForecast;
	public static String IncreaseColumnWaveQuantityForecast1;
	public static String  ActualValue;
	public static String WaveDistributionTable;
	public static String WaveDistributionTable1;
	public static String CasePackColumn;
	public static String CasePackColumns;
	//for edit multiple-object Collection page
	public static By PercentageWaveRequirementColumn;
	public static By PercentageWaveForecast2Columns;
	
	public static By DoneButton=By.xpath(".//*[@id='contentDiv']/table/tbody/tr[7]/td/table/tbody/tr/td[2]");
	//public static By PercentageWaveForecastColumns=By.xpath(".//*[contains(@id,'r1_hbWEntry')]");
	public static By TotalPercentageWaveForecast=By.xpath("//td[contains(text(),'% Wave Forecast')]//following::td[34]");
	public static By ForeCastQuantity;
	public static By ItemQuantity=By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[2]/td[38]");
	
		
	public static By WaveDistributionColumn=By.xpath("//input[@name='selectedIds']");
	
	public static SoftAssert S_ASSERT = new SoftAssert();
    public static By enterValuesInWaveRequirement;
    public static By enterValuesInWaveRequirement1;
    public static By enterValuesInWaveDistribution;
    public static By WaveDistributionColumns;
    public static By casePack;
    public static By casePacks;
    public static By ForeCastQuantityColumns;
    private static final int standardNoOfColumns=33; //This the standard to run loops.it should have a same value as 'NumberOfColumns' in excel.
	
	static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String Prodname;
	static String strDelete;
	public static Boolean flagVal=true;
	public static Boolean nevflag=true;
	public static String pageTitle;
	public static String ManualInputWaveForeCastValue;
    public static String ValueForComparison;
  
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
			log.debug("Inside testcase for CI260");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			log.info("col0 :" + productData[0]);
			log.info("col2 :" + productData[2]);
			log.info("col3 :" + productData[3]);
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
					openBrowser();
					launchApp(productData[0],productData[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=productData[0];
					flaglogin=true;
				}
				switch(productData[3]){
				case "Item Qunatity verification Based on the PercentageWaveForeCast":
				TC12_VerifyItemQunatity(productData);
				break;
				
				case "Global Forecast Unit Value Change Verification when Manual wave Foecast is No":
				GlobalForecastUnitValueChangeVerification(productData);
				break;
					
				case "Item Qunatity verification Based on the WaveForeCastQty":
				TC12_VerifyItemQunatity(productData);
				break;
				
				
				}
			}
          }
		catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	
	
	  public static boolean TC12_VerifyItemQunatity(String [] productData) throws Exception{
		  try{
			  if(productData[31].equalsIgnoreCase(productData[32]))
				{
					CreateProdFromLineSheetForCI260(productData[2],productData[5],productData[9],productData[8],productData[10],productData[11],productData[12],
							productData[13],productData[14],productData[16],productData[6],productData[7],productData[17],productData[18],productData[19],productData[20],productData[21],productData[25],
							productData[26],productData[27],productData[28],productData[29],productData[30],productData[31],productData[32],productData[33]);
				    EditWaveRequirement2(productData[34],productData[35]);
				    enterPercentageWaveForecastValuesWR2(productData[37],productData[39]);
					ForecastQuantityVerification(productData[41]);
					EditWaveRequirement5(productData[42]);
					enterColumnValuesWR5(productData[37],productData[43],productData[45]);
					ItemQuantityVerification(productData[47]);
					
					}
				if(productData[31].equalsIgnoreCase(productData[33])){
					CreateProdFromLineSheetForCI260(productData[2],productData[5],productData[9],productData[8],productData[10],productData[11],productData[12],
							productData[13],productData[14],productData[16],productData[6],productData[7],productData[17],productData[18],productData[19],productData[20],productData[21],productData[25],
							productData[26],productData[27],productData[28],productData[29],productData[30],productData[31],productData[32],productData[33]);
					EditWaveRequirement2(productData[34],productData[35]);
					enterWaveForecastQuantityValuesWR2(productData[37],productData[39]);
					EditWaveRequirement5(productData[42]);
					enterColumnValuesWR5(productData[37],productData[43],productData[45]);
					ItemQuantityVerification(productData[47]);
			}
			}
		  
		  catch(Exception e){
			  fail=true;
			  log.error("Exception in TC12_VerifyItemQunatity"+e);
			  throw e;
		  }
		  return true;
	  }
	  
	  public static boolean GlobalForecastUnitValueChangeVerification(String [] productData) throws Exception{
		  try{
			  navigateToProduct(productData);
			   //Navigating to product search Page
			  SearchExistingProduct(productData);
			  //Searching Existing product
			  NavigateToDetailsPage(productData);
			  //Navigate To details Page
			  UpdateGlobalForecast(productData);
			  //Updating Global Forecast Unit
			  ItemQuantityVerificationAfterGlobalWaveForecastUpdation(productData);
			  //Verification of item Quantity after the Excel Value Updation
			  
			 }
		  catch(Exception e ){
			  fail=true;
			  log.error("GlobalForecastUnitValueChangeVerification");
			  throw e;
		  }
		  return true;
	  }
	  
	  public static boolean navigateToProduct(String[] productData) throws Exception
		{
			try{
				//Added refresh code below as to create another material BOM require this as we need to close library + sign
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				if(driver.findElements(CI296.ProductLink).size()==0) 
				{
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
				}
				//Click on Color link
				driver.findElement(CI296.ProductLink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.titleIs(productData[6]));
				wait.until(ExpectedConditions.visibilityOfElementLocated(CI299.ProductNumberSearch));
			  }
			catch(Exception e){
				fail=true;
				log.error("Exception in navigateToProduct()", e);
				throw e;
			}
			return true;
		}
	  
	  public static boolean SearchExistingProduct(String [] productData) throws Exception{
			try{
				CI299.ProductType=By.linkText(productData[2]);
				CommonFunctions.waitForElementTobeClickable(CI299.ProductType);
				CommonFunctions.clickButtonOrLink(CI299.ProductType, "HyperLink", "Assortment/Solid");
				//Choosing the product type as Retail Item
				wait.until(ExpectedConditions.visibilityOfElementLocated(CI299.ProductNumberSearch));
				CommonFunctions.waitForPageLoaded();
			    CommonFunctions.enterTextInTextboxUpdated(CI299.ProductNumberSearch, prodNumber, "product number");
				//Entering the Product Number
				CommonFunctions.clickButtonOrLink(CI299.SearchProductButton, "Button", "Search");
				wait.until(ExpectedConditions.titleIs(productData[7]));
				//waiting for the expected Pagetitle to appear
				CommonFunctions.waitForPageLoaded();
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in SearchExistingProduct"+e);
				throw e;
			}
			 return true;
		}

	  public static boolean NavigateToDetailsPage(String [] productData) throws Exception{
			try{
				CommonFunctions.waitForElementTobeClickable(DetailsTab);
				CommonFunctions.clickButtonOrLink(DetailsTab, "HyperLink","Details");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SeasonDropDown);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SeasonDropDown, productData[5], "SaesonYear");
				CommonFunctions.waitForPageLoaded();
				
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in NavigateToDetailsPage"+e);
				throw e;
			}
			 return true;
			 }
	  
	  public static boolean UpdateGlobalForecast(String [] productData) throws Exception{
			try{
			CommonFunctions.waitForElementTobeClickable(ItemQuantity);
			wait.until(ExpectedConditions.elementToBeClickable(ItemQuantity));
			ValueForComparison=SeleniumDriver.driver.findElement(ItemQuantity).getText();
			log.info("The Item quantity is "+ValueForComparison);
			CommonFunctions.waitForElementTobeClickable(ActionsDropDown);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(ActionsDropDown, productData[48], "ActionDropDownValue");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.titleIs(productData[30]));
			CommonFunctions.clearTextBox(Product.GlobalLamForecast, "GlobalLamForecast");
			CommonFunctions.enterTextInTextbox(Product.GlobalLamForecast,productData[25]);
			CommonFunctions.clearTextBox(Product.GlobalNAForeCast, "GlobalNAForeCast");
			CommonFunctions.enterTextInTextbox(Product.GlobalNAForeCast,productData[26]);
			CommonFunctions.clearTextBox(Product.GlobalEuForeCast, "GlobalEuForeCast");
			CommonFunctions.enterTextInTextbox(Product.GlobalEuForeCast,productData[27]);
			CommonFunctions.clearTextBox(Product.GlobalAsiaForeCast, "GlobalAsiaForeCast");
			CommonFunctions.enterTextInTextbox(Product.GlobalAsiaForeCast,productData[28]);
			CommonFunctions.clearTextBox(Product.GlobalPacificForecast, "GlobalPacificForecast");
			CommonFunctions.enterTextInTextbox(Product.GlobalPacificForecast,productData[29]);
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.titleIs(productData[30]));
			
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in UpdateGobalForecast"+e);
				throw e;
			}
			 return true;
			 }
	public static String CreateProdFromLineSheetForCI260(String prodType,String year,String strlineSheetView,String strlineSheetAction,
			String strClass,String strDivision,String strBrand,String strInternalClassification,String AstSolid,String strIPSensitive,
			String strElectronicsIncluded,String strSoftgoodsIncluded,String strDistributionChannel,String strSRPPriceUSD,String strUSDomestic
			,String strLCPriceUSD,String strDOMPriceUSD,String strGlobalLAMUnit,String strGlobalNAUnit,String strGlobalEUUnit,String strGlobalASIAUnit,String strGlobalPACIFICUnit,
			String verifyProductPageTitle,String strManualInputWaveForecastValue,String strManualInputWaveForecastValueNo,String strManualInputWaveForecastValueYes ) throws Exception{
		try{
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(mySeasonYear, year);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();
		    SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
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
			Thread.sleep(2000);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.lebelProdNum);
			prodNumber=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
			log.info("Product Number is "+prodNumber);
		if(!prodType.equalsIgnoreCase("Trademark Display")){
			CommonFunctions.waitForElementTobeClickable(Product.distributionChannel);
				CommonFunctions.enterTextInTextbox(Product.distributionChannel,strDistributionChannel);
				if(prodType.equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,strSRPPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.USDomestic,strUSDomestic);
					CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,strLCPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,strDOMPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.GlobalLamForecast,strGlobalLAMUnit);
					CommonFunctions.enterTextInTextbox(Product.GlobalNAForeCast,strGlobalNAUnit);
					CommonFunctions.enterTextInTextbox(Product.GlobalEuForeCast,strGlobalEUUnit);
					CommonFunctions.enterTextInTextbox(Product.GlobalAsiaForeCast,strGlobalASIAUnit);
					CommonFunctions.enterTextInTextbox(Product.GlobalPacificForecast,strGlobalPACIFICUnit);
					if(strManualInputWaveForecastValue.equalsIgnoreCase(strManualInputWaveForecastValueYes)){
						CommonFunctions.waitForElementTobeClickable(ManualInputWaveForeCast);
						CommonFunctions.clickButtonOrLink(ManualInputWaveForeCast, "checkbox", "Manual Input Wave Forcast");
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
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			CommonFunctions.waitForPageLoaded();
			getPageTitle();
			S_ASSERT.assertEquals(pageTitle, verifyProductPageTitle,"ERROR:Pagetile is not correct.please verify");
			log.info("View Season Product Information apge successfully appears");
			//	log.info(prodNum);
		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in CreateProductFromLineSheet()", e);
			throw e;
		}
		return prodNumber;
	}

 public static  String getPageTitle(){
	 //Common function getting the page title when navigating into another page
	 try{
	 pageTitle=driver.getTitle();
	 log.info(pageTitle);
	 }
	 catch(Exception e){
		 fail=true;
		 log.error("Exception in getPageTitle");
		 throw e;
	 }
	 return pageTitle;
 }
 
 public static boolean EditWaveRequirement2(String strSourceConfigValue,String strPageTitle) throws Exception{
	 boolean value = false;
	 try{
	 CommonFunctions.waitForPageLoaded();	
	 CommonFunctions.waitForElementTobeClickable(Details);
	 CommonFunctions.clickButtonOrLink(Details, "link", "Details");
	 CommonFunctions.waitForPageLoaded();
	 JavascriptExecutor jse = (JavascriptExecutor)driver;
	 jse.executeScript("window.scrollBy(0,2000)", "");
	 jse.executeScript("window.scrollBy(1000,0)", "");
	 CommonFunctions.waitForElementTobeClickable(labelWaveRequirements2Edit);
	 CommonFunctions.clickButtonOrLink(labelWaveRequirements2Edit, "link", "Details");
	 CommonFunctions.waitForPageLoaded();
	 getPageTitle();
	 S_ASSERT.assertEquals(pageTitle,strPageTitle,"ERROR:The Page Name is not correct.Please verify");
	 log.info(pageTitle +"Page Appears");
	 }
	 catch(Exception e){
		 fail=true;
		 log.info("Exception in CreateProductFromLineSheet()", e);
		 throw e;
	 }
	return true;
	}
 
 
 public static void enterPercentageWaveForecastValuesWR2(String ColumnValue,String strPageTitle) throws Exception {
	try{
	 for(int i=1,j=1;i<=standardNoOfColumns;i++){
		 //Assigning the Dynamic xpath into the String variable
        IncreaseColumnPercentageWaveForecast="//td[contains(text(),'% Wave Forecast')]//following::td"+"["+i+"]"; 
        //Assigning above string a xpath into a Webelement
		 PercentageWaveForecast2Columns=By.xpath(IncreaseColumnPercentageWaveForecast);
		 //Assigning the Dynamic xpath into the String variable
		IncreaseColumnPercentageWaveForecast1=IncreaseColumnPercentageWaveForecast+"/div/input["+j+"]";
		 //Assigning above string a xpath into a Webelement
		enterValuesInWaveRequirement=By.xpath(IncreaseColumnPercentageWaveForecast1);
		CommonFunctions.waitForElementTobeClickable(PercentageWaveForecast2Columns);
		//Thread.sleep(2000);
		CommonFunctions.clickButtonOrLink(PercentageWaveForecast2Columns, "table");
		CommonFunctions.waitForElementTobeClickable(enterValuesInWaveRequirement);
		//CommonFunctions.clickButtonOrLink(enterValuesInWaveRequirement, "table");
	    CommonFunctions.enterTextInTextbox(enterValuesInWaveRequirement, ColumnValue);
		
		 log.info("value entered for column"+" "+i);
		 
	 }
	        CommonFunctions.clickButtonOrLink(DoneButton, "btn", "Done");
	        wait.until(ExpectedConditions.titleIs(strPageTitle));
			CommonFunctions.waitForPageLoaded();
			 getPageTitle();
			 S_ASSERT.assertEquals(pageTitle, strPageTitle,"ERROR:The Page Name is not correct");
			 log.info(pageTitle+ " "+"Page appears ");
			
	}
	catch(Exception e){
		fail=true;
		log.error("error while entering the value" +e);
		throw e;
	}
	 }
public static void ForecastQuantityVerification(String strExpectedForeCastQuauntity){
	try{
	 CommonFunctions.waitForPageLoaded();
	 JavascriptExecutor jse = (JavascriptExecutor)driver;
	// jse.executeScript("window.scrollBy(0,2000)", "");
	 for(int i=1;i<=standardNoOfColumns;i++){
		 // //Assigning the Dynamic xpath into the String variable
	 foreCastQuantityString="//td[contains(text(),'Forecast Qty')]//following::td"+"["+i+"]";
	//Assigning above string a xpath into a Webelement
	 ForeCastQuantity=By.xpath(foreCastQuantityString);
	 CommonFunctions.waitForElementTobeClickable(ForeCastQuantity);
	 //getting forecast value to Comapre with excel calculated value
     GettingText(ForeCastQuantity);
     CommonFunctions.waitForPageLoaded();
     S_ASSERT.assertEquals(ActualValue, strExpectedForeCastQuauntity, "ERROR:ForeCastQuantityValueisWrong.Please Verify");
     log.info("value verified for column"+" "+i);
     }
	}
	 catch(Exception e){
		 fail=true;
		 log.error("Exception in ForecastQuantityVerification");
		 throw e;
	 }
	}
public static void enterWaveForecastQuantityValuesWR2(String ColumnValue,String strPageTitle) throws Exception {
	try{
	 for(int i=1,j=1;i<=standardNoOfColumns;i++){
		 //Assigning the Dynamic xpath into the String variable
		  IncreaseColumnWaveQuantityForecast="//td[contains(text(),'Forecast Qty')]//following::td"+"["+i+"]";
		  //Assigning above string a xpath into a Webelement
		  ForeCastQuantityColumns=By.xpath(IncreaseColumnWaveQuantityForecast);
			 //Assigning the Dynamic xpath into the String variable
		  IncreaseColumnWaveQuantityForecast1=IncreaseColumnWaveQuantityForecast+"/div/input["+j+"]";
		//Assigning above string a xpath into a Webelement
		  enterValuesInWaveRequirement1=By.xpath(IncreaseColumnWaveQuantityForecast1);
		  CommonFunctions.waitForElementTobeClickable(ForeCastQuantityColumns);
		 // Thread.sleep(2000);
		  CommonFunctions.clickButtonOrLink(ForeCastQuantityColumns, "table");
		  CommonFunctions.waitForElementTobeClickable(enterValuesInWaveRequirement1);
		 // CommonFunctions.clickButtonOrLink(enterValuesInWaveRequirement1, "table");
	      CommonFunctions.enterTextInTextbox(enterValuesInWaveRequirement1, ColumnValue);
		 log.info("value entered for column"+" "+i);
		 
	 }
	        CommonFunctions.clickButtonOrLink(DoneButton, "btn", "Done");
	        wait.until(ExpectedConditions.titleIs(strPageTitle));
			CommonFunctions.waitForPageLoaded();
			 getPageTitle();
			 S_ASSERT.assertEquals(pageTitle, strPageTitle,"ERROR:The Page Name is not correct");
			 log.info(pageTitle+ " "+"Page appears ");
			
	}
	catch(Exception e){
		fail=true;
		log.error("error while entering the value" +e);
		throw e;
	}
	 }
 public static boolean EditWaveRequirement5(String strPageTitle) throws Exception{
	 boolean value = false;
	 try{
	 CommonFunctions.waitForPageLoaded();
     JavascriptExecutor jse = (JavascriptExecutor)driver;
     //Scrolling down
	 jse.executeScript("window.scrollBy(0,2250)", "");
	 //Scrolling right
	 jse.executeScript("window.scrollBy(1000,0)", "");
	 CommonFunctions.waitForElementTobeClickable(labelWaveRequirements5Edit);
	 CommonFunctions.clickButtonOrLink(labelWaveRequirements5Edit, "link", "labelWaveRequirementLabel5");
	 CommonFunctions.waitForPageLoaded();
	 getPageTitle();
	 S_ASSERT.assertEquals(pageTitle,strPageTitle,"ERROR:The Page Name is not correct.Please verify");
	 log.info(pageTitle +"Page Appears");
	 }
	 catch(Exception e){
		 fail=true;
		 log.info("Exception in CreateProductFromLineSheet()", e);
		 throw e;
	 }
	return true;
 }
 
 public static void enterColumnValuesWR5(String NoOfColumns,String ColumnValue,String strPageTitle) throws Exception {
		try{
			
		     for(int i=1,j=1;i<=standardNoOfColumns;i++){
		    	//Assigning the Dynamic xpath into the String variable
			 WaveDistributionTable="//td[@id='r1_hbProductType']//following::td["+i+"]"; 
			 //Assigning above string a xpath into a Webelement
			 WaveDistributionColumns=By.xpath(WaveDistributionTable);
			 //Assigning the Dynamic xpath into the String variable
			WaveDistributionTable1=WaveDistributionTable+"/div/input["+j+"]";
			//Assigning above string a xpath into a Webelement
			enterValuesInWaveDistribution=By.xpath(WaveDistributionTable1);
			 CommonFunctions.waitForElementTobeClickable(WaveDistributionColumns);
			 CommonFunctions.clickButtonOrLink(WaveDistributionColumns, "table");
			 CommonFunctions.waitForVisibilityOfElement(enterValuesInWaveDistribution);
			 CommonFunctions.clickButtonOrLink(enterValuesInWaveDistribution, "table");
			 enterTextInTextbox1(enterValuesInWaveDistribution, ColumnValue);
			 log.info("value entered for column"+" "+i);
			
			 
		 }
		        CommonFunctions.clickButtonOrLink(DoneButton, "btn", "Done");
				CommonFunctions.waitForPageLoaded();
				getPageTitle();
				S_ASSERT.assertEquals(pageTitle, strPageTitle,"ERROR:The Page Name is not correct");
					 log.info(pageTitle+ " "+"Page appears ");
				 
				
		}
		catch(Exception e){
		fail=true;
		log.error("Exception while entering the value" +e);
		throw e;
		}
		 }
 public static void ItemQuantityVerification(String strExpectedItemQuauntity){
	 try{
     JavascriptExecutor jse = (JavascriptExecutor)driver;
	 jse.executeScript("window.scrollBy(1000,2250)", "");
	 //Getting item quantity to compare with the calculated excel value 
	 CommonFunctions.waitForVisibilityOfElement(ItemQuantity);
	 CommonFunctions.waitForPageLoaded();
	 CommonFunctions.waitForPageLoaded();
	 GettingText(ItemQuantity);
	 CommonFunctions.AssertEqualsVerification(ActualValue, strExpectedItemQuauntity.substring(0,strExpectedItemQuauntity.indexOf(".")), "ERROR:ForeCast Quantity Value is Wrong.Please Verify");
     //S_ASSERT.assertEquals(ActualValue, strExpectedItemQuauntity, "ERROR:ForeCast Quantity Value is Wrong.Please Verify");
     log.info("The value of the item quantity is "+ActualValue);
     log.info("TEST CASE HAS BEEN VERIFIED SUCCESSFULLY");
	 }
	 catch(Exception e){
		 fail=true;
		 log.error("Exception While Verifing the Item Quanity" +e);
		 throw e;
	 }
	}
 public static void ItemQuantityVerificationAfterGlobalWaveForecastUpdation(String [] productData){
	 try{
     JavascriptExecutor jse = (JavascriptExecutor)driver;
	 jse.executeScript("window.scrollBy(1000,2250)", "");
	 //Getting item quantity to compare with the calculated excel value 
	 CommonFunctions.waitForVisibilityOfElement(ItemQuantity);
	 CommonFunctions.waitForPageLoaded();
	 GettingText(ItemQuantity);
	 if(!ActualValue.equals(ValueForComparison)){
		 log.info("User updated the test data in before the Execution ");
	 }
	 else{
		 log.info("User Should Update the test data in excel Before excecuting this Script");
	 }
	 CommonFunctions.AssertEqualsVerification(ActualValue, productData[47].substring(0,3), "ERROR:ForeCast Quantity Value is Wrong.Please Verify");
	//S_ASSERT.assertEquals(ActualValue, productData[47], "ERROR:ForeCast Quantity Value is Wrong.Please Verify");
     log.info("The value of the item quantity is "+ActualValue);
     log.info("TEST CASE HAS BEEN VERIFIED SUCCESSFULLY");
	 }
	 catch(Exception e){
		 fail=true;
		 log.error("Error While Verifing the Item Quanity" +e);
		 throw e;
	 }
	}
	public static boolean enterTextInTextbox1(By by, String inputValue) throws Exception{
		//This function is to enter the the value in waveRequirement table 5 Since the value are passing with decimal value .to avoid that this function made. 
		boolean result=false;
		Thread.sleep(100);
	
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				SeleniumDriver.driver.findElement(by).clear();
				SeleniumDriver.driver.findElement(by).sendKeys((inputValue.substring(0,2)));
				SeleniumDriver.log.info("Entered" + " " + inputValue + " " + "in the text field.");
				result=true;
			}
			return result;
			}
	
	public static String GettingText(By by){
	
		try{
			wait.until(ExpectedConditions.elementToBeClickable(by));
			ActualValue=SeleniumDriver.driver.findElement(by).getText();
			}
		catch(TimeoutException t){
			fail=true;
			log.error("Exception in waitForElementTobeClicable()", t);
			throw t;
			
		}
		return ActualValue;
		
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
 
	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	
	
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

