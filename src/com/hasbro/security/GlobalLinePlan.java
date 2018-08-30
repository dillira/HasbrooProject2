package com.hasbro.security;
/**
 * @author Anjali Gupta
 *
 */
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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




public class GlobalLinePlan extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;

	public static By planningLink = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
	public static By createNewPlan = By.xpath("//a[contains(text(),'Create New Plan')]");
	public static By globalLinePlan = By.xpath("//a[text()='Global Line Plan']");
	public static By headingCreateNewPlan = By.xpath("//td[contains(text(),'Create New Plan')]");

	public static By planName = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By Brand = By.xpath("//td[contains(text(),'Brand')]//following::select[1]");
	public static By seasonYear = By.xpath("//td[contains(text(),'Season Year')]//following::select[1]");
	public static By menulink1 = By.id("menuLink1");
	public static By menulink2 = By.id("menuLink2");
	public static By menulink3 = By.id("menuLink3");
	public static By menulink4 = By.id("menuLink4");
	public static By menulink5 = By.id("menuLink5");
	public static By menulink6 = By.id("menuLink6");
	public static By hbclass = By.id("hbClass");
	public static By hbDivision = By.id("hbBusinessUnit");
	public static By hbBrand = By.id("hbBrand");
	public static By hbAssortmentSolidNumber = By.id("hbAssortmentSolidNumber");
	public static By lnkAdd = By.xpath("//div[@id='attPopDiv']//a[contains(text(),'Add')][1]");

	public static By hbAssortmentSolidName = By.id("r7_hbAssortmentSolidName");
	public static By hbAssortmentSolidNameForUpdate = By.xpath("//tr[@style='background-color: rgb(221, 228, 242);']//td[contains(@id,'hbAssortmentSolidName')]");
	
	public static By hbDistributionChannel = By.id("r7_hbDistributionChannel");
	public static By hbDistributionChannelForUpdate = By.xpath("//div[@id='hbDistributionChannelSourceDiv']/select");
	
	public static By hbLicensor = By.id("r7_hbLicensor");
	public static By hbCoBrand = By.id("r7_hbCoBrand");
	public static By r6_hbISO = By.id("r7_hbISO");
	public static By hbMovie = By.id("r7_hbMovie");
	public static By hbIntroTiming = By.id("r7_hbIntroTiming");
	public static By hbOnShelfDate = By.id("r7_hbOnShelfDate");
	public static By hbTVAd = By.id("r7_hbTVAd");
	public static By hbDigitalProduct = By.id("r7_hbDigitalProduct");
	public static By hbProjectType = By.id("r7_hbProjectType");
	public static By hbInnovationType = By.id("r7_hbInnovationType");
	public static By hbPTMH = By.id("r7_hbPTMH");
	public static By hbAstSolid = By.id("r7_hbAstSolid");
	public static By hbRetailerDistribution = By.id("r7_hbRetailerDistribution");
	public static By hbCFItemCount = By.id("r7_hbCFItemCount");
	public static By hbNewItemCount = By.id("r7_hbNewItemCount");
	public static By hbProductRefreshItemCount = By.id("r7_hbProductRefreshItemCount");
	public static By hbPackageRefreshItemCount = By.id("r7_hbPackageRefreshItemCount");
	public static By hbSoldasSolid = By.id("r7_hbSoldasSolid");
	public static By hbTotalRetailItemCount = By.id("r7_hbTotalRetailItemCount");
	public static By hbSRPPriceUSD = By.id("r7_hbSRPPriceUSD");
	public static By hbLCPriceUSD = By.id("r7_hbLCPriceUSD");
	public static By hbDOMPriceUSD = By.id("r7_hbDOMPriceUSD");
	public static By hbDomesticPercentage = By.id("r7_hbDomesticPercentage");
	public static By hbComments = By.id("r7_hbComments");
	public static By productCount = By.id("r7_productCount");
	public static By hbPlanIdentifier = By.id("r7_hbPlanIdentifier");
	public static By hbPlanSequence = By.id("r6_hbPlanSequence");

	public static By inPutAssortmentSolidName = By.xpath("//div[@id='hbAssortmentSolidNameSourceDiv']/input");
	public static By selecthbLicensor = By.xpath("//div[@id='hbLicensorSourceDiv']/select");
	public static By selecthbCoBrand = By.xpath("//div[@id='hbCoBrandSourceDiv']/select");
	public static By selecthbISO = By.xpath("//div[@id='hbISOSourceDiv']/select");
	public static By selecthbMovie = By.xpath("//div[@id='hbMovieSourceDiv']/select");
	public static By selecthbIntroTiming = By.xpath("//div[@id='hbIntroTimingSourceDiv']/select");
	public static By inPuthbOnShelfDate = By.xpath("//div[@id='hbOnShelfDateSourceDiv']/input");
	public static By selecthbTVAd = By.xpath("//div[@id='hbTVAdSourceDiv']/select");
	public static By selecthbDigitalProduct = By.xpath("//div[@id='hbDigitalProductSourceDiv']/select");
	public static By selecthbProjectType = By.xpath("//div[@id='hbProjectTypeSourceDiv']/select");
	public static By selecthbInnovationType = By.xpath("//div[@id='hbInnovationTypeSourceDiv']/select");
	public static By selecthbPTMH = By.xpath("//div[@id='hbPTMHSourceDiv']/select");
	public static By selecthbAstSolid = By.xpath("//div[@id='hbAstSolidSourceDiv']/select");
	public static By inPuthbRetailerDistribution = By.xpath("//div[@id='hbRetailerDistributionSourceDiv']/input");
	public static By inPuthbCFItemCount = By.xpath("//div[@id='hbCFItemCountSourceDiv']/input");
	public static By inPuthbNewItemCount = By.xpath("//div[@id='hbNewItemCountSourceDiv']/input");
	public static By inPuthbProductRefreshItemCount = By.xpath("//div[@id='hbProductRefreshItemCountSourceDiv']/input");
	public static By inPuthbPackageRefreshItemCount = By.xpath("//div[@id='hbPackageRefreshItemCountSourceDiv']/input");
	public static By inPuthbSoldasSolid = By.xpath("//div[@id='hbSoldasSolidSourceDiv']/input");
	public static By inPuthbSRPPriceUSD = By.xpath("//div[@id='hbSRPPriceUSDSourceDiv']/input");
	public static By inPuthbLCPriceUSD = By.xpath("//div[@id='hbLCPriceUSDSourceDiv']/input");
	public static By inPuthbDOMPriceUSD = By.xpath("//div[@id='hbDOMPriceUSDSourceDiv']/input");
	public static By inPuthbDomesticPercentage = By.xpath("//div[@id='hbDomesticPercentageSourceDiv']/input");
	public static By textareahbComments = By.xpath("//div[@id='hbCommentsSourceDiv']/textarea");

	public static By btnSave = By.xpath("//a[text()='Save']");
	public static By btnDone = By.xpath("//a[text()='Done']");
	public static By pName = By.xpath("//td[@class='FORMLABEL' and contains(text(),'Name')]//following::td[1]");

	public static By planAction = By.xpath("//select[contains(@onchange,'evalList(this)')]"); ////div[@id='contentDiv']/table/tbody/tr[1]//select[contains(@onchange,'evalList(this)')]
	public static By actionSetStateOption = By.xpath("//select[contains(@onchange,'evalList(this)')]/option[contains(text(),'Change State:  Plan')]");
	public static By actionDeleteOption = By.xpath("//option[contains(text(),'Remove Plan')]");
	public static By actionUpdateOption = By.xpath("//select[contains(@onchange,'evalList(this)')]/option[contains(text(),'Update Plan')]");
	public static By actionCheckInOption = By.xpath("//select[contains(@onchange,'evalList(this)')]/option[contains(text(),'Check In')]");
	public static By RO_UpdateLifecycleState = By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By msgDelete = By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td");
	public static By planIsSaved = By.xpath("//div[@class='BORDERED_BLOCK']/p/font");



	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String glPlanName;
	static String strPlanName;
	static String valULCS;
	static String valULCSAfterChange;
	static String strRO_UpdateLifecycleState;
	static String strDelete;
	

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcGlobalLinePlan(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Global line Plan Security");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			System.out.println("col4 :" + data[4]);
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
			//	wait = new WebDriverWait(driver, 10);
			//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchDropDownSelect")));

			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_GLPlan(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_GLPlan(data); }
			//Read view verification
			if(data[3].equalsIgnoreCase("GeneralAttributesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttributesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_GLPlan(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Specification should be created.previous specifications should not be available
	public static boolean Create_GLPlan(String[] data) throws Exception{
		try{
			//Click My Season Link
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[8]);
			//Click on Planning
			CommonFunctions.clickButtonOrLink(planningLink, "link", "Planning");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){

				CommonFunctions.clickButtonOrLink(createNewPlan, "btn", "Create New Plan");
				//Click Global Line Plan :
				CommonFunctions.clickButtonOrLink(globalLinePlan, "btn", "Global Line Plan");
				//wait for heading 
				CommonFunctions.waitForVisibilityOfElement(headingCreateNewPlan);
				//Click Create New Plan
				glPlanName = createNewPlan(data);
				fillGlobalLinePlan(data);
				//Verification
				strPlanName=driver.findElement(pName).getText();
				Assert.assertEquals(strPlanName, glPlanName);
			}
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				String btnCreateNewPlan= driver.findElement(createNewPlan).getAttribute("disabled");
				Assert.assertEquals("true", btnCreateNewPlan);
			}
			else{
				log.info("Create is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_GLPlan()", e);
			return false;
		}
		return true;
	}

	public static String createNewPlan(String[] data) {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			glPlanName = CommonFunctions.getRandomString(5);
		//	glPlanName = data[6]+date.getTime();
		//	glPlanName=glPlanName.substring(0,5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(planName,glPlanName);
			//Enter Brand
			CommonFunctions.enterTextInTextbox(Brand, data[7]);
			//Enter Season Year
			CommonFunctions.enterTextInTextbox(seasonYear, data[8]);
			//Click on create button
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return glPlanName;
	}
	public static void fillGlobalLinePlan(String[] data) {
		try{
			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(menulink1, "Image", "Plus Sign");
			//Select Class
			//CommonFunctions.selectFromDropDownByValue(hbclass, data[9]);
			new Select(SeleniumDriver.driver.findElement(hbclass)).selectByVisibleText(data[9]);
			SeleniumDriver.log.info("Selected" + " " + data[9] + " " + "option from drop down.");
			//Click on Add
			CommonFunctions.clickButtonOrLink(lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(menulink2, "Image", "Plus Sign");
			//Select DIVISION
			CommonFunctions.selectFromDropDownByVisibleText(hbDivision, data[10]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(menulink3, "Image", "Plus Sign");
			//Select Brand
			CommonFunctions.selectFromDropDownByVisibleText(hbBrand, data[11]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(menulink5, "Image", "Plus Sign");
			//Select Class
			CommonFunctions.enterTextInTextbox(hbAssortmentSolidNumber, data[12]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(lnkAdd, "link", "Add");

			CommonFunctions.clickButtonOrLink(hbAssortmentSolidName, "cell", "Assortment / Solid Name");
			CommonFunctions.enterTextInTextbox(inPutAssortmentSolidName, data[13]);

			CommonFunctions.clickButtonOrLink(hbLicensor, "cell", "Licensor");
			CommonFunctions.enterTextInTextbox(selecthbLicensor, data[14]);

			CommonFunctions.clickButtonOrLink(hbCoBrand, "cell", "Co-Brand");
			CommonFunctions.enterTextInTextbox(selecthbCoBrand, data[15]);

			CommonFunctions.clickButtonOrLink(r6_hbISO, "cell", "ISO");
			CommonFunctions.enterTextInTextbox(selecthbISO, data[16]);

			CommonFunctions.clickButtonOrLink(hbMovie, "cell", "Movie");
			CommonFunctions.enterTextInTextbox(selecthbMovie, data[17]);

			CommonFunctions.clickButtonOrLink(hbIntroTiming, "cell", "Intro Timing");
			CommonFunctions.enterTextInTextbox(selecthbIntroTiming, data[18]);

			CommonFunctions.clickButtonOrLink(hbOnShelfDate, "cell", "On-Shelf Date");
			CommonFunctions.enterTextInTextbox(inPuthbOnShelfDate, data[19]);

			CommonFunctions.clickButtonOrLink(hbTVAd, "cell", " TV Ad");
			CommonFunctions.enterTextInTextbox(selecthbTVAd, data[20]);

			CommonFunctions.clickButtonOrLink(hbDigitalProduct, "cell", "Digital Product");
			CommonFunctions.enterTextInTextbox(selecthbDigitalProduct, data[21]);

			CommonFunctions.clickButtonOrLink(hbProjectType, "cell", "Project Type");
			CommonFunctions.enterTextInTextbox(selecthbProjectType, data[22]);

			CommonFunctions.clickButtonOrLink(hbInnovationType, "cell", "Innovation Type");
			CommonFunctions.enterTextInTextbox(selecthbInnovationType, data[23]);

			CommonFunctions.clickButtonOrLink(hbPTMH, "cell", "PT / MH");
			CommonFunctions.enterTextInTextbox(selecthbPTMH, data[24]);

			CommonFunctions.clickButtonOrLink(hbAstSolid, "cell", "Ast. / Solid");
			CommonFunctions.enterTextInTextbox(selecthbAstSolid, data[25]);

			CommonFunctions.clickButtonOrLink(hbRetailerDistribution, "cell", "Retailer Distribution");
			CommonFunctions.enterTextInTextbox(inPuthbRetailerDistribution, data[26]);

			CommonFunctions.clickButtonOrLink(hbCFItemCount, "cell", "CF Total");
			System.out.println(data[27]);
			CommonFunctions.enterTextInTextbox(inPuthbCFItemCount, data[27]); //

			CommonFunctions.clickButtonOrLink(hbNewItemCount, "cell", "New Total");
			System.out.println(data[28]);
			CommonFunctions.enterTextInTextbox(inPuthbNewItemCount, data[28]);

			CommonFunctions.clickButtonOrLink(hbProductRefreshItemCount, "cell", "Product Refresh Total");
			CommonFunctions.enterTextInTextbox(inPuthbProductRefreshItemCount, data[29]);

			CommonFunctions.clickButtonOrLink(hbPackageRefreshItemCount, "cell", "Package Refresh Total");
			CommonFunctions.enterTextInTextbox(inPuthbPackageRefreshItemCount, data[30]);

			CommonFunctions.clickButtonOrLink(hbSoldasSolid, "cell", "Sold as Solid Total");
			CommonFunctions.enterTextInTextbox(inPuthbSoldasSolid, data[31]);

			CommonFunctions.clickButtonOrLink(hbSRPPriceUSD, "cell", "SRP (USD)");
			CommonFunctions.enterTextInTextbox(inPuthbSRPPriceUSD, data[32]);

			CommonFunctions.clickButtonOrLink(hbLCPriceUSD, "cell", " L/C Price (USD)");
			CommonFunctions.enterTextInTextbox(inPuthbLCPriceUSD, data[33]);

			CommonFunctions.clickButtonOrLink(hbDOMPriceUSD, "cell", "DOM Price (USD)");
			CommonFunctions.enterTextInTextbox(inPuthbDOMPriceUSD, data[34]);

			CommonFunctions.clickButtonOrLink(hbDomesticPercentage, "cell", "Domestic (%)");
			CommonFunctions.enterTextInTextbox(inPuthbDomesticPercentage, data[35]);

			CommonFunctions.clickButtonOrLink(hbComments, "cell", "Comments");
			CommonFunctions.enterTextInTextbox(textareahbComments, data[36]);

			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone,"btn", "Done");


		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
	}

	public static boolean SetState_GLPlan(String[] data) throws Exception{
		try{
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[8]);
			//Click on Planning
			CommonFunctions.clickButtonOrLink(planningLink, "link", "Planning");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//	Create_GLPlan(data);
			clickCheckIn();
			CommonFunctions.clickButtonOrLink(planAction, "btn", "Action dropdown");
			CommonFunctions.clickButtonOrLink(actionSetStateOption, "option", "Set State");
			String valULCSAfterChange=GLPlan_selectUpdateLifecycleState(data);
			Thread.sleep(1000);
			//Click on Update
			CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
			if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
				strRO_UpdateLifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				//verification	
				Assert.assertEquals(strRO_UpdateLifecycleState, valULCSAfterChange);
			}
			else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
				Assert.assertNotEquals(strRO_UpdateLifecycleState, valULCSAfterChange);
			}
			else
				log.info("SetState or chageState is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in SetState_GLPlan()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_GLPlan(String[] data) throws Exception{
		try{
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[8]);
			//Click on Planning
			CommonFunctions.clickButtonOrLink(planningLink, "link", "Planning");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			clickCheckIn();

			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.clickButtonOrLink(planAction, "btn", "Action dropdown");
				CommonFunctions.clickButtonOrLink(actionDeleteOption, "btn", "Delete Option");
			/*	CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
				driver.switchTo().alert().accept(); */
				CommonFunctions.handleAlertPopUp();
		//		strDelete= driver.findElement(msgDelete).getText();
				Assert.assertEquals(strDelete, "Deleted Sucessfully"); //Need to update 
			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(planAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}
			else
				log.info("Delete is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesReadView()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			//Click My Season Link
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[8]);
			//Click on Planning
			CommonFunctions.clickButtonOrLink(planningLink, "link", "Planning");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			if(data[3].contains("GeneralAttributesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
					System.out.println(GALabel);
					System.out.println(" General Attributes:");
					Assert.assertEquals(GALabel, " General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					log.error("General Attributes label is Absent");
					fail=true;
				}
			}
			else if(data[3].contains("GeneralAttributesRead_View")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					System.out.println("General Attirbutes label is Present");
					log.error("General Attirbutes label is Present");
					fail=true;
				}else{
					log.info("General Attirbutes label is Absent");
				}
			}
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesReadView()", e);
			return false;
		}
		return true;
	}
	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[8]);
			//Click on Planning
			CommonFunctions.clickButtonOrLink(planningLink, "link", "Planning");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			clickCheckIn();

			if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.clickButtonOrLink(planAction, "btn", "Action dropdown");
				CommonFunctions.clickButtonOrLink(actionUpdateOption, "btn", "Update Plan Option");
				if(driver.findElements(hbAssortmentSolidNameForUpdate).size()>0)
					CommonFunctions.clickButtonOrLink(hbAssortmentSolidNameForUpdate, "cell", "Assortment / Solid Name");
				CommonFunctions.enterTextInTextbox(inPutAssortmentSolidName, data[13]);
				//Click on Save
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				CommonFunctions.waitForVisibilityOfElement(planIsSaved);
				String strPlanisSaved = driver.findElement(planIsSaved).getText();
				Assert.assertEquals(strPlanisSaved,"Plan is saved.");
			}
			else if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(planAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}	
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}
	
	
	
	public static void clickCheckIn() throws Exception{
		try{
			WebElement mySelectElm = driver.findElement(planAction); 
			Select mySelect= new Select(mySelectElm);
			List<WebElement> options = mySelect.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerificationActions("Check In",options);
			if(bVal)
			{
				CommonFunctions.clickButtonOrLink(planAction, "btn", "Action dropdown");
				CommonFunctions.clickButtonOrLink(actionCheckInOption, "btn", "Check In Option");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in clickCheckIn()", e);
		}
	}

	//This funcion is to select Update Lifecycle State	
	public static String GLPlan_selectUpdateLifecycleState(String[] data) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState,"Dropped");
			}
			else if(valULCS.contains("Completed")){
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
			}
			else if(valULCS.contains("Dropped")){
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
			}

			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in selectUpdateLifecycleState()", e);
		}
		return valULCSAfterChange;
	}


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
