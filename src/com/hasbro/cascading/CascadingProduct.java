package com.hasbro.cascading;
/**
 * @author Anjali Gupta
 *
 */
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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


public class CascadingProduct extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	public static By libraryLink = By.id("librariesContentIcon");

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

	public static By labelHierarchy = By.xpath("//td[contains(text(),'Hierarchy')]");
	public static By labelGeneralAttri = By.xpath("//td[contains(text(),'General Attributes')]");
	public static By labelRetailItemCount = By.xpath("//td[contains(text(),'Retail Item Counts')]");
	public static By labelGlobalFroecasting = By.xpath("//td[contains(text(),'Global Forecasting')]");
	public static By labelFinance = By.xpath("//td[contains(text(),'Finance')]");

	public static By labelWaveRequirements1 = By.xpath("//td[contains(text(),'Wave Requirements 1 - Dates')]");
	public static By labelWaveRequirements2 = By.xpath("//td[contains(text(),'Wave Requirements 2 - Quantity')]");
	public static By labelWaveRequirements4 = By.xpath("//td[contains(text(),'Wave Requirements 4 - Suffix')]");
	public static By labelWaveRequirements5 = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution')]");

	public static By labelWaveRequirements1Edit = By.xpath("//td[contains(text(),'Wave Requirements 1 - Dates')]//following::a[1]");
	public static By labelWaveRequirements2Edit = By.xpath("//td[contains(text(),'Wave Requirements 2 - Quantity')]//following::a[1]");
	public static By labelWaveRequirements4Edit = By.xpath("//td[contains(text(),'Wave Requirements 4 - Suffix')]//following::a[1]");
	public static By labelWaveRequirements5Edit = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution')]//following::a[1]");

	public static By AssortmentSolid = By.xpath("//a[contains(text(),'Assortment/Solid')]");
	public static By Class = By.xpath("//td[contains(text(),'Class')]//following::select[1]");
	public static By ROHierarchyClass = By.id("hbClassPr");

	public static By Editable_RIC_CFSpring = By.xpath("//td[contains(text(),'CF Spring')]//following::input[1]");
	public static By RO_RIC_CFSpring = By.id("hbCFSpringPr");

	public static By Editable_SRPPriceUSD= By.xpath("//td[contains(text(),'Target Cost (USD)')]//following::input[1]");
	public static By RO_SRPPriceUSD= By.id("hbSRPPriceUSDPr");

	public static By Editable_Finance_TargetCostUSD= By.xpath("//td[contains(text(),'Target Cost (USD)')]//following::input[1]");
	public static By RO_Finance_TargetCostUSD= By.id("hbTargetCostUSD");

	public static By Division = By.xpath("//td[contains(text(),'Division')]//following::select[1]");
	public static By Brand = By.xpath("//td[contains(text(),'Brand')]//following::select[1]");
	public static By segment = By.xpath("//td[contains(text(),'Segment')]//following::select[1]");
	public static By InternalClassification = By.xpath("//td[contains(text(),'Internal Classification')]//following::select[1]");
	public static By AstSolid = By.xpath("//td[contains(text(),'*Ast')]//following::select[1]");
	public static By ProductName = By.xpath("//td[contains(text(),'Product Name')]//following::input[1]");
	public static By ROProductName = By.id("hbProductName");
	public static By IPSensitive = By.xpath("//td[contains(text(),'IP Sensitive')]//following::select[1]");
	public static By SaveBtn = By.xpath("//a[text()='Save']");
	//Create Product-Season Attributes Page
	public static By viewProductBtn = By.xpath("//a[text()='View Product.']");
	public static By distributionChannel  = By.xpath("//td[contains(text(),'Distribution Channel')]//following::select[1]");
	public static By SRPPriceUSD= By.xpath("//td[contains(text(),'SRP Price')]//following::input[1]");
	public static By USDomestic = By.xpath("//td[contains(text(),'US Domestic')]//following::input[1]");
	public static By LCPriceUSD = By.xpath("//td[contains(text(),'L/C Price')]//following::input[1]");
	public static By DOMPriceUSD= By.xpath("//td[contains(text(),'DOM Price ')]//following::input[1]");
	//For Retail
	public static By targetCostUSD= By.xpath("//td[contains(text(),'Target Cost')]//following::input[1]");

	public static By detailsTablink= By.xpath("//a[text()='Details']");
	public static By searchProduct= By.name("quickSearchCriteria");
	public static By searchIcon= By.id("searchButton");
	public static By ddDetailsAction= By.id("prodseasonActions");
	public static By ddOptionchangeState= By.xpath("//select[@id='prodseasonActions']/option[contains(@value,'changeState')]");
	public static By ddOptionDeleteProduct= By.xpath("//select[@id='prodseasonActions']/option[contains(@value,'deleteObject')]");
	public static By detailPageSeasonDD= By.id("splId");
	public static By ROInternalClassification= By.id("hbInternalClassification");
	//for Set State
	public static By setLifecycleState= By.xpath("//td[contains(text(),'Set Lifecycle State')]");
	public static By linkUpdate= By.xpath("//a[text()='Update']");

	public static By Editable_UpdateLifecycleState= By.id("lcstate");
	public static By RO_UpdateLifecycleState= By.xpath("//td[contains(text(),'Product Lifecycle State')]//following::td[1]");

	public static By season2018= By.xpath("//*[@id='splId']/option[contains(text(),'2018')]");
	public static By headerDeleteObject= By.xpath("//td[contains(text(),'Delete Object')]");
	public static By deleteButton= By.xpath("//a[text()='Delete']");
	//For Retail
	public static By electronicsIncluded= By.xpath("//td[contains(text(),'Electronics Included')]//following::select[1]");
	public static By softgoodsIncluded= By.xpath("//td[contains(text(),'Softgoods Included')]//following::select[1]");
	public static By optionUpdateProdSeason= By.xpath("//select[@id='prodseasonActions']/option[@value='editProductSeasonAttributes()']");


	static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String Prodname;
	static String strDelete;
	public static Boolean flagVal=true;
	public static Boolean nevflag=true;


	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcProduct(String[] productData) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Product Cascading");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			log.info("col0 :" + productData[0]);
			log.info("col2 :" + productData[2]);
			log.info("col3 :" + productData[3]);
			log.info("class :" + productData[10]);
			log.info("Division :" + productData[11]);
			log.info("col6 :" + productData[12]);
			log.info("col7 :" + productData[13]);
			//	driver.manage().timeouts().pageLoadTimeout(myAutomationWait, TimeUnit.SECONDS);
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(productData[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					driver.quit();
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
			}
			//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchDropDownSelect")));

			//Create Product from LineSheet
			if(productData[3].equalsIgnoreCase("Create"))
			{ CreateProductFromLineSheet(productData); }
			//SetState


		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create Product
	public static boolean CreateProductFromLineSheet(String[] productData) throws Exception{
		try{
		//	driver.navigate().refresh();
			//driver.getTitle();
			if(nevflag){
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(mySeasonYear, productData[5]);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(lineSheet, "link", "Line Sheet");
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

		//	Date date = new Date();
			//Select Line sheet view
			CommonFunctions.waitForVisibilityOfElement(lineSheetView);

			CommonFunctions.selectFromDropDownByVisibleText(lineSheetView, productData[9]);
			CommonFunctions.selectFromDropDownByVisibleText(lineSheetAction, productData[8]);
			//Click on Assortment/Solid
			CommonFunctions.waitForVisibilityOfElement(By.xpath("//td[contains(text(),'Choose a Type')]"));
			System.out.println(By.xpath("//a[contains(text(),'"+productData[2]+"')]"));
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+productData[2]+"')]"), "link", "Product Type");
//		/	System.out.println("Prod Name: "+productData[15]+date.getTime());
			Prodname="P"+CommonFunctions.getRandomString(5);
			//Prodname=Prodname.substring(0,8);
			CommonFunctions.enterTextInTextbox(ProductName,Prodname);
			nevflag=false;
			}
				//Select Class
				//	CommonFunctions.waitForElementTobeClickable(Class);
				CommonFunctions.clickButtonOrLink(Class, "Class");
				flagVal= CommonFunctions.selectFromDropDownByVisibleText(Class, productData[10]);
				bFlagVal(productData);
				//	CommonFunctions.waitForElementTobeClickable(Division);
				CommonFunctions.clickButtonOrLink(Division, "Division");
				flagVal= CommonFunctions.selectFromDropDownByVisibleText(Division, productData[11]);
				bFlagVal(productData);
				//	CommonFunctions.waitForElementTobeClickable(Brand);
				CommonFunctions.clickButtonOrLink(Brand, "Brand");
				flagVal= CommonFunctions.selectFromDropDownByVisibleText(Brand, productData[12]);
				bFlagVal(productData);
				//	CommonFunctions.waitForElementTobeClickable(segment);
				CommonFunctions.clickButtonOrLink(segment, "Segment");
				flagVal= CommonFunctions.selectFromDropDownByVisibleText(segment, productData[13]);
				bFlagVal(productData);
				log.info("Cascading verified for Class:" +productData[10] + " Division : "+productData[11]+" Brand: "+productData[12]+" Segment: " +productData[13]);
	
		}catch(Exception e){ 
			fail=true;
			log.info("Cascading fail for Class:" +productData[10] + " Division : "+productData[11]+" Brand: "+productData[12]+" Segment: " +productData[13]);
			log.error("Exception in CreateProductFromLineSheet()", e);
			return false;
		}
		return true;
	}

	public static boolean dropDownOptionVerificationActions(String a,List<WebElement> b) {
		try{	
			for (WebElement option : b) {
				System.out.println(option.getText());
				System.out.println("a: "+a);
				if (option.getText().equalsIgnoreCase(a)) {
					return true;
				}
			}
			return false;
		}catch(Exception e){ 
			fail=true;
			SeleniumDriver.log.error("Exception in dropDownOptionVerificationActions()", e);
			return false;
		}
	}


	//Function consist scenario : Click on detail tab
	public static boolean clickDetailsTab(String[] productData) throws Exception{
		try{

			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailsTablink));
			//	if(CommonFunctions.waitForVisibilityOfElement(detailsTablink))
			CommonFunctions.clickButtonOrLink(detailsTablink, "link", "Details tab");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailPageSeasonDD));
			CommonFunctions.waitForVisibilityOfElement(detailPageSeasonDD);
			//Select Season
			if(!productData[3].contains("Delete")){
				if(CommonFunctions.waitForVisibilityOfElement(detailPageSeasonDD)){
					CommonFunctions.clickButtonOrLink(detailPageSeasonDD, "Season dropdown");
					CommonFunctions.clickButtonOrLink(season2018, "2018");
				}
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in clickDetailsTab()", e);
			return false;
		}
		return true;
	}

	public static boolean bFlagVal(String[] productData) throws Exception{
		try{
			if (flagVal)
			{ return true;}
			else{
				fail=true;
				log.info("Cascading fail for Class:" +productData[10] + " Division : "+productData[11]+" Brand: "+productData[12]+" Segment: " +productData[13]);
				Assert.fail("Cascading not working");
				return false;
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in bFlagVal()", e);
			return false;
		}
	}



	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteCascadingXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteCascadingXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteCascadingXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteCascadingXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteCascadingXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteCascadingXls,"Testcases", Utility.getRowNum(suiteCascadingXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteCascadingXls,"Testcases", Utility.getRowNum(suiteCascadingXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteCascadingXls, this.getClass().getSimpleName());
	}

}
