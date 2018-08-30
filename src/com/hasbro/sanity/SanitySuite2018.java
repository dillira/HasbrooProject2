package com.hasbro.sanity;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

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

//import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Color;
import com.hasbro.security.Colorway;
import com.hasbro.security.CostsheetInternal;
import com.hasbro.security.GlobalLinePlan;
import com.hasbro.security.PlaceHolderDevPlan;
import com.hasbro.security.Product;
import com.hasbro.security.Sample;
import com.hasbro.security.SourcingConfig;
import com.hasbro.security.Supplier;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

/**
 * @author anjali.gupta
 *
 */

public class SanitySuite2018 extends TestsuiteBase{
	static int count=-1;
	static boolean fail=false;
	String loginVal;
	Boolean flaglogin=false;
	int y=0;
	static String Prodname;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSCFunctional(String[] data) throws Exception{
		count++;
		System.out.println(CommonProjectFunctions.runmodes[count]);
		if(!CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
			CommonProjectFunctions.skip=true;
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
		}
		try{
			log.debug("Inside testcase for Sanity suit");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("Testcase is :" + data[2]);
			log.info("Testcase no is :" + data[3]);
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
			case "Create Product":
				log.info("In side :-> " + data[3]);	
				CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[5],data[9],data[8],data[10],
						data[11],data[12],data[13],data[14],data[16],data[6],data[7],
						data[17],data[18],data[19], data[20], data[21],
						"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
						"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
				log.info("Out side :-> " + data[3]);
				break;
			case "Create Multiple Colorway":
				log.info("In side :-> " + data[3]);	
				createMultipleCW(data);
				log.info("Out side :-> " + data[3]);
				break;	
			case "Create Colorway":
				log.info("In side :-> " + data[3]);	
				createCW(data);
				log.info("Out side :-> " + data[3]);
				break;	
			case "Create Development Plan":
				log.info("In side :-> " + data[3]);	
				createDevelopmentPlan(data);
				log.info("Out side :-> " + data[3]);
				break;	
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	
	
	public static String createDevelopmentPlan(String[] data) throws Exception{
		try{
			log.info(" Create Development Plan verification started....");
			//Click My Season Link
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[5]);
			//Click on Planning
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.planningLink, "link", "Planning");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on create new plan
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.createNewPlan, "btn", "Create New Plan");
			//Click Global Line Plan :
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.globalLinePlan, "btn", "Global Line Plan");
			//wait for heading 
			CommonFunctions.waitForVisibilityOfElement(GlobalLinePlan.headingCreateNewPlan);
			//Click Create New Plan
			SmokeFlow.glPlanName = createNewPlan(data);
			//glPlanName="AutoDevPlan"+glPlanName;
			SmokeFlow.assortmentSolidName= fillGlobalLinePlan(data);
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg2);
			//Expend
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg2, "Expand class");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg3);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg3, "Expand division");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg4);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg4, "Expand Brand");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.expImg6);
			CommonFunctions.clickButtonOrLink(SmokeFlow.expImg6, "Expand MainLine");
			//CommonFunctions.waitForElementTobeClickable(expImg6);
			//CommonFunctions.clickButtonOrLink(expImg4, "Expand MainLine");
			//Class
			SmokeFlow.strClass=driver.findElement(SmokeFlow.objClass).getText();
			Assert.assertEquals(SmokeFlow.strClass.trim(), data[7]);
			//Division
			SmokeFlow.strDivision=driver.findElement(SmokeFlow.objDivision).getText();
			Assert.assertEquals(SmokeFlow.strDivision.trim(), data[8]);//
			//Brand
			SmokeFlow.strBrand=driver.findElement(SmokeFlow.objBrand).getText();
			Assert.assertEquals(SmokeFlow.strBrand.trim(), data[5]);
			//Verification of totalRetailItemCount
			SmokeFlow.strTotalRetailItemCount=driver.findElement(SmokeFlow.totalRetailItemCount).getText();
			String val =data[35].replaceAll("\\.0*$", "");
			Assert.assertEquals(SmokeFlow.strTotalRetailItemCount, val);
			log.info("Plan name is: "+ SmokeFlow.glPlanName);
			log.info("Create Development Plan verification completed");
		}catch(Exception e){
			log.error("Exception in createDevelopmentPlan()", e);
			//	return false;
		}
		return SmokeFlow.glPlanName;
	}
	public static String createNewPlan(String[] data) {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			SmokeFlow.glPlanName = CommonFunctions.getRandomString(5);
			SmokeFlow.glPlanName ="AutoDevP"+SmokeFlow.glPlanName;
			//	glPlanName = data[6]+date.getTime();
			//	glPlanName=glPlanName.substring(0,5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.planName,SmokeFlow.glPlanName);
			//Enter Brand
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.Brand, data[12]);
			//Enter Season Year
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.seasonYear, data[5]);
			//Click on create button
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return SmokeFlow.glPlanName;
	}
	
	public static String fillGlobalLinePlan(String[] data) {
		try{
			//Add Assortment/Solid name
			SmokeFlow.assSoName= data[4]+CommonFunctions.getRandomString(3);
			CommonFunctions.enterTextInTextbox(SmokeFlow.assSolidName, SmokeFlow.assSoName);
			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink1, "Image", "Plus Sign");
			//Select Class
			//CommonFunctions.selectFromDropDownByValue(hbclass, data[5]);
			new Select(SeleniumDriver.driver.findElement(GlobalLinePlan.hbclass)).selectByVisibleText(data[10]);
			SeleniumDriver.log.info("Selected" + " " + data[7] + " " + "option from drop down.");
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink2, "Image", "Plus Sign");
			//Select DIVISION
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDivision, data[11]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink3, "Image", "Plus Sign");
			//Select Brand
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbBrand, data[12]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			//	CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink5, "Image", "Plus Sign");
			//Enter Assortment Number
			//	CommonFunctions.enterTextInTextbox(GlobalLinePlan.hbAssortmentSolidNumber, data[10]);
			//Click on Add
			//	CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink6, "Image", "Plus Sign");
			//Enter Assortment Number
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.hbAssortmentSolidNumber, SmokeFlow.assSoName);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbAssortmentSolidName, "cell", "Assortment / Solid Name");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPutAssortmentSolidName, SmokeFlow.assSoName);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDistributionChannel, "cell", "Distribution Channel");
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDistributionChannelForUpdate, "Mainline");



			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbLicensor, "cell", "Licensor");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbLicensor, data[13]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbCoBrand, "cell", "Co-Brand");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbCoBrand, data[14]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.r6_hbISO, "cell", "ISO");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbISO, data[8]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbMovie, "cell", "Movie");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbMovie, data[9]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbIntroTiming, "cell", "Intro Timing");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbIntroTiming, data[16]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbOnShelfDate, "cell", "On-Shelf Date");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbOnShelfDate, data[17]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbTVAd, "cell", "TV Ad");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbTVAd, data[18]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDigitalProduct, "cell", "Digital Product");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbDigitalProduct, data[19]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbProjectType, "cell", "Project Type");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbProjectType, data[20]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbInnovationType, "cell", "Innovation Type");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbInnovationType, data[21]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbPTMH, "cell", "PT / MH");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbPTMH, data[22]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbAstSolid, "cell", "Ast. / Solid");
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.selecthbAstSolid, data[23]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbRetailerDistribution, "cell", "Retailer Distribution");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbRetailerDistribution, data[24]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbCFItemCount, "cell", "CF Total");
			System.out.println(data[25]);
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbCFItemCount, data[25]); //

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbNewItemCount, "cell", "New Total");
			System.out.println(data[26]);
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbNewItemCount, data[26]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbProductRefreshItemCount, "cell", "Product Refresh Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbProductRefreshItemCount, data[27]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbPackageRefreshItemCount, "cell", "Package Refresh Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbPackageRefreshItemCount, data[28]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbSoldasSolid, "cell", "Sold as Solid Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbSoldasSolid, data[29]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbSRPPriceUSD, "cell", "SRP (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbSRPPriceUSD, data[30]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbLCPriceUSD, "cell", " L/C Price (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbLCPriceUSD, data[31]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDOMPriceUSD, "cell", "DOM Price (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbDOMPriceUSD, data[32]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDomesticPercentage, "cell", "Domestic (%)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbDomesticPercentage, data[33]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbComments, "cell", "Comments");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.textareahbComments, data[34]);

			//Click on Save
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
			CommonFunctions.waitForElementTobeClickable(GlobalLinePlan.btnDone);
			Thread.sleep(2000);
			//Click on Done
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");


		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return SmokeFlow.assSoName;
	}

	public static String createMultipleCW(String[] data) throws Exception{
		try{
			//Create Product
			Prodname=CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[5],data[9],data[8],data[10],
					data[11],data[12],data[13],data[14],data[16],data[6],data[7],
					data[17],data[18],data[19], data[20], data[21],
					"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Assortment Product: "+Prodname);
			//Create multiple colorway
			CommonProjectFunctions.CreateMultiple_Colorway();
		}catch(Exception e){
			fail=true;
			log.error("Exception in createMultipleCW()", e);
			throw e;
		}
		return Prodname;
	}

	public static String createCW(String[] data) throws Exception{
		try{
			//Create Product
			/*Prodname=CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[5],data[9],data[8],data[10],
					data[11],data[12],data[13],data[14],data[16],data[6],data[7],
					data[17],data[18],data[19], data[20], data[21],
					"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");*/
		//	log.info("Assortment Product: "+Prodname);
			//Create colorway
			CommonProjectFunctions.Create_Colorway(Prodname,data[5],data[4],data[23],data[24]);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createCW()", e);
			throw e;
		}
		return Prodname;
	}



	@AfterMethod
	public void reporterdataSetResult(){
		if(CommonProjectFunctions.skip)
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			CommonProjectFunctions.isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "PASS");
		CommonProjectFunctions.skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSanityXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		CommonProjectFunctions.runmodes=Utility.getDataSetRunmodeTest(suiteSanityXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(CommonProjectFunctions.isTestPass){
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[] testDataTest(){
		return Utility.getData(suiteSanityXls, this.getClass().getSimpleName());
	}

}
