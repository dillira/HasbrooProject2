
package com.hasbro.cascading;
/**
 * @author Anshu Jha
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

import com.hasbro.security.GlobalLinePlan;
import com.hasbro.security.Product;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;


public class Cascadingplan extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	public static By libraryLink = By.id("librariesContentIcon");

	public static By expImg2 = By.id("expanderImage2");
	public static By expImg3 = By.id("expanderImage3");
	public static By expImg4 = By.id("expanderImage4");
	public static By expImg6 = By.id("expanderImage6");

	public static By objClass = By.id("r2_dimExpander");
	public static By objDivision = By.id("r3_dimExpander");
	public static By objBrand = By.id("r4_dimExpander");
	public static By objSegment = By.id("r5_dimExpander");

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
	public static By assSolidName  = By.xpath("//div[@id='hbAssortmentSolidNameSourceDiv']/input");
	public static By segment = By.id("r5_dimExpander");
	public static By planAction  = By.xpath("//select[(@onchange='evalList(this)') and not (@class='FORMELEMENT')]");
	public static By cancelBtn  = By.xpath("//a[text()='Cancel']");
	
	static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String Prodname;
	static String strDelete;
	static String glPlanName;
	static String assortmentSolidName;
	static String strClass;
	static String strDivision;
	static String strBrand;
	static String strSegment;
	static String  assSoName;
	public static Boolean flagVal=true;
	public static Boolean nevflag=true;
	static int i;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSCFunctional(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Plan cascading");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("Testcase is :" + data[2]);
			log.info("Testcase no is :" + data[3]);
			//	driver.manage().timeouts().pageLoadTimeout(myAutomationWait, TimeUnit.SECONDS);
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
			createPlan(data); 
			verifyPlanCascading(data); 
		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 1
	 */
	public static String createPlan(String[] data) throws Exception{
		try{
			if(nevflag){
			log.info(" Create Development Plan ....");
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
			glPlanName = createNewPlan(data);
			//glPlanName="AutoDevPlan"+glPlanName;
			assortmentSolidName= fillGlobalLinePlan(data);
			
			log.info("Plan name is: "+ glPlanName);
			log.info(" Development Plan created");
			nevflag=false;
			}
		}catch(Exception e){
			log.error("Exception in createDevelopmentPlan()", e);
		}
		return glPlanName;
	}
	//Create Plan page
	public static String verifyPlanCascading(String[] data) throws Exception{
		try{
			log.info(" Verify plan cascading....");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on plan
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+glPlanName+"']"),"lnk", "Plan");
			CommonFunctions.selectFromDropDownByVisibleText(planAction, "Update Plan");
			
			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink1, "Image", "Plus Sign");
			//Select Class
			flagVal= CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbclass, data[6]);
			bFlagVal(data);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink3, "Image", "Plus Sign");
			//Select DIVISION
			flagVal= CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDivision, data[7]);
			bFlagVal(data);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink4, "Image", "Plus Sign");
			//Select Brand
			flagVal= CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbBrand, data[8]);
			bFlagVal(data);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");
			//Segment 
			flagVal=getTableCell(data);
			bFlagVal(data);
			//Click on cancel
			CommonFunctions.clickButtonOrLink(cancelBtn, "btn", "Cancel");
		    }catch(Exception e){
			log.error("Exception in createDevelopmentPlan()", e);
			//	return false;
		}
		return glPlanName;
	}

	public static String fillGlobalLinePlan(String[] data) {
		try{
			//Add Assortment/Solid name
			assSoName= data[4]+CommonFunctions.getRandomString(3);
			CommonFunctions.enterTextInTextbox(assSolidName, assSoName);
			//Click on Done
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");


		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return assSoName;
	}

	public static Boolean getTableCell(String[] data) {
		try{
			int RowIndex=1;
			List<WebElement> row1 = driver.findElements(By.xpath("//table[@id='editorTable']/tbody/tr/td[3]"));
			 System.out.println(row1.size());
			for(WebElement e: row1){
				RowIndex=RowIndex+1;
				System.out.println("rowIndia: "+RowIndex);
		        System.out.println("e.getText(): "+ e.getText());
		        System.out.println("Segment: "+data[9]);
		        if(RowIndex>6){
		        if (e.getText().trim().equals(data[9])) {
		        	return true;
				}
		        }
			}
			return false;
		}catch(Exception e){
			fail=true;
			log.error("Exception in getTableCell()", e);
			return false;
		}
	}
	public static String createNewPlan(String[] data) {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			glPlanName = CommonFunctions.getRandomString(5);
			glPlanName ="AutoCasca"+glPlanName;
			//Enter Name
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.planName,glPlanName);
			//Enter Brand
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.Brand, data[8]);
			//Enter Season Year
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.seasonYear, data[5]);
			//Click on create button
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return glPlanName;
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

