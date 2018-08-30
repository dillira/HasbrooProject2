package com.hasbro.views;
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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hasbro.security.CostsheetExternalProduct;
import com.hasbro.security.CostsheetTooling;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;


public class LineSheetView extends TestsuiteBase{


	String runmodes[]=null;

	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;

	public static By libraryLink = By.id("librariesContentIcon");
	//	public static Boolean nevflag=true;
	static String strViewOwner;


	static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String Prodname;
	static String strDelete;
	public static Boolean flagVal=true;
	//	public static Boolean nevflag=true;
	static String strTestCaseName = null;

	//Line sheet view
	public static By mySeasonYear = By.id("seasonSelectList");
	public static By lineSheet = By.linkText("Line Sheet");
	public static By lineSheetView = By.id("viewId");
	public static By Filter = By.xpath("//a[contains(text(),'Filters')]");
	public static By Filterdropdown = By.id("filterId");
	//	public static By Filterdropdown = By.xpath("//td[contains(text(),'Filters:')]//following::select[1]/option[contains(text(),\"Assortment/Solid Product Type\")]");
	public static By Run = By.xpath("//a[text()='Run']");
	public static By options = By.xpath("//a[text()='Options']");
	//public static By level = By.id("//*[@id='linePlanLevel']");
	public static By level = By.xpath("//td[contains(text(),'Level')]//following::select[1]");
	//	public static By placeholders = By.id("//*[@id='placeholderMode']");
	public static By placeholders = By.id("placeholderMode");
	//	public static By systemview = By.id("//*[@id='ViewButton2']/img");
	public static By systemview = By.id("ViewButton2");
	public static By viewowner = By.xpath("//td[text()='System View']");
	public static By returnview = By.xpath("//a[text()='Return']");
	public static By ddRegion = By.xpath("//a[contains(text(),'Region')]//following::td[2]/select[1]");
	public static By ddBU = By.xpath("//a[contains(text(),'Business Unit')]//following::td[2]/select[1]");
	//public static By Lis<WebElement>col1 = By.xpath("//a[contains(@id,'COL')]");
	
	/*@FindBy(xpath="//a[contains(@id,'COL')]")
	public static List<WebElement> col1;*/
	//	public static By Region = By.xpath("//a[contains(text(),'Region')]//following::td[2]/select[1]/option[contains(text(),'"+data[8]+"')]");
	
	//sequence view verification



	public static By updateTableLayoutHidden = By.xpath("//div[@id='updateViewDiv' and @style='display: none;']//img[contains(@src,'customize_tablebutton.gif')]");
	public static By viewOwner = By.xpath("//td[contains(text(),'View Owner')]//following::td[1]");
	public static By lsTable =  By.xpath("//a[contains(text(),'Hide/Show columns')]//following::table[5]/tbody/tr[1]/td/a[2]");
	
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tclsView(String[] data) throws Exception{
		
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			try{
			log.debug("Inside testcase for Line Sheet View");
			log.info("***********************");
			log.info("user :" + data[0]); 
			log.info("Password :" + data[1]);
			log.info("Testcase is :" + data[2]);
			log.info("***********************");
			//	driver.manage().timeouts().pageLoadTimeout(myAutomationWait, TimeUnit.SECONDS);
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
			//writing cases to read data from excel sheet for each row
			switch (data[2]) {
			case "Business Unit Forecast - ASIA":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Business Unit Forecast - EU":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Business Unit Forecast - LAM":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Business Unit Forecast - NA":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Business Unit Forecast - PACIFIC":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Cost Comparison":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Development Plan":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Global Line Plan":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Product Carryover View":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Regional Forecast - ASIA":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Regional Forecast - EU":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Regional Forecast - LAM":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Regional Forecast - NA":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Regional Forecast - PACIFIC":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Retail Item Definition":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Visual Line Plan":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "What-If Analysis - Placeholder":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "What-If Analysis - Product":
				log.info("In side :-> " + data[2]);	
				NavigatingtoLineSheet(data);
				log.info("Out side :-> " + data[2]);
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

	//Navigating to Line Sheet View
	public static boolean NavigatingtoLineSheet(String[] data) throws Exception{
		try{
			if(driver.findElements(lineSheetView).size() == 0) {
				driver.switchTo().defaultContent();
				CommonProjectFunctions.clickMySeasonLink();
				//Select Season Year
				CommonFunctions.selectFromDropDownByVisibleText(mySeasonYear, data[5]);
				//Click on Line Sheet link
				CommonFunctions.clickButtonOrLink(lineSheet, "link", "Line Sheet");
				wait.until(ExpectedConditions.titleIs("Season: Line Sheet"));
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				//Select Line sheet view
				CommonFunctions.waitForVisibilityOfElement(lineSheetView);
			}

			CommonFunctions.selectFromDropDownByVisibleText(lineSheetView, data[2]);
	//		if(driver.findElements(lsTable).size() == 0) {
				//click on Filter
				driver.findElement(Filter).click();
				//Select line sheet view filters
				CommonFunctions.selectFromDropDownByVisibleText(Filterdropdown, data[6]);
				//Again click on Filter
				CommonFunctions.clickButtonOrLink(Filter, "link", "Filter");

				//Put if condition , where it should select colorway only for 5 BU Views
				if(data[2].contains("Business Unit Forecast - ASIA")||(data[2].contains("Business Unit Forecast - EU"))||(data[2].contains("Business Unit Forecast - LAM"))||(data[2].contains("Business Unit Forecast - NA"))||(data[2].contains("Business Unit Forecast - PACIFIC")))
					//click on options link to select colorway option
				{
					CommonFunctions.clickButtonOrLink(options, "link", "options");
					//select colorway option from level dropdown
					CommonFunctions.selectFromDropDownByVisibleText(level, data[7]);
					//again click on options 
					CommonFunctions.clickButtonOrLink(options, "link", "options");
				}
				//select region 
				CommonFunctions.selectFromDropDownByVisibleText(ddRegion , data[8]);
				//select business unit
				CommonFunctions.selectFromDropDownByVisibleText(ddBU , data[9]);
	//		}
			//for Development plan view
			if(data[2].contains("Development Plan"))
			{
				CommonFunctions.clickButtonOrLink(options, "link", "options");
				//select product and placeholders from placeholders dropdown
				CommonFunctions.selectFromDropDownByVisibleText(placeholders, data[10]);
				//again click on options 
				CommonFunctions.clickButtonOrLink(options, "link", "options");
			}
			
			//for what if analyasis placeholder view
			if(data[2].contains("What-If Analysis - Placeholder"))
			{
				CommonFunctions.clickButtonOrLink(options, "link", "options");
				//select product and placeholders from placeholders dropdown
				CommonFunctions.selectFromDropDownByVisibleText(placeholders, data[11]);
				//again click on options 
				CommonFunctions.clickButtonOrLink(options, "link", "options");
			}
			//
			//for what if analysis product view
			if(data[2].contains("What-If Analysis - Product"))
			{
				CommonFunctions.clickButtonOrLink(options, "link", "options");
				//select product and placeholders from placeholders dropdown
				CommonFunctions.selectFromDropDownByVisibleText(placeholders, data[12]);
				//again click on options 
				CommonFunctions.clickButtonOrLink(options, "link", "options");
			}
			
			//click on Run
			CommonFunctions.clickButtonOrLink(Run, "link", "Run");
		//}
		//call verify sequence method
		verifySequence(data);

		//call verify user group method
		verifyUserGroup(data);

	}catch(Exception e){
		fail=true;
		log.error("Exception in Linesheetview()", e);
		throw e;
	}
	return true ;
}


/*
 * Below function verify Sequencing for View Type
 */
public static boolean verifySequence(String[] data) throws Exception{
	try {
		int colIndex=1;
		int i=13;
		int j=1;
		CommonFunctions.waitForPageLoaded();
	    List<WebElement> col1=driver.findElements(By.xpath("//a[contains(@id,'COL')]"));
		
		//a[contains(text(),'Hide/Show columns')]//following::table[5]/tbody/tr[1]/td/a[2]
		
		System.out.println(col1.size());
		for(WebElement e: col1){
			colIndex=colIndex+1;
			System.out.println("UI data: "+ e.getText());
			System.out.println("excel data: "+data[i]);
			if(data[i].equalsIgnoreCase("NA")){
				break;
			}
			Assert.assertEquals(e.getText().trim(), data[i]);
			log.info("Col"+j+" :matched for "+ e.getText());
			i++;
			j++;
		}
		log.info("*****Sequence verified for: "+ data[2]+"*****"); 
	}catch(Exception e){
		fail=true;
		log.error("Exception in verifySequence()", e);
		return false;
	}
	return true;
}



// This function verifies view owner which should be system view by default
public static boolean verifyUserGroup(String[] data) throws Exception{
	try {
		if(!data[3].equalsIgnoreCase("(-- None --)")) {
			//Click on update table layout icon
			//CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
			wait.until(ExpectedConditions.titleIs("Update Report View"));
			CommonFunctions.waitForPageLoaded();
			strViewOwner = driver.findElement(viewOwner).getText();
			Assert.assertEquals(strViewOwner, data[3]);
			log.info("Owner Group verified for : "+strViewOwner);
			//Click on Cancel
			CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
		}
		else {
			Assert.assertEquals(driver.findElements(updateTableLayoutHidden).size(),1);
			log.info("For None Update icon is not present");
		}
	}catch(Exception e){
		fail=true;
		log.error("Exception in verifyUserGroup()", e);
		throw e;
	}
	return true;
}



@AfterMethod
public void reporterdataSetResult(){
	if(skip)
		Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "SKIP");
	else if(fail){
		Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "FAIL");
		isTestPass=false;
	}
	else
		Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "PASS");
	skip=false;
	fail=false;
}
@BeforeTest
public void checkTestcaseSkip() throws Exception{

	if(!Utility.isCaseRunnable(suiteViewsXls, this.getClass().getSimpleName())){
		log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
	}
	runmodes=Utility.getDataSetRunmodeTest(suiteViewsXls, this.getClass().getSimpleName());
}
@AfterTest
public void reportTestcaseResult(){
	if(isTestPass){
		Utility.dataSetResult(suiteViewsXls,"Testcases", Utility.getRowNum(suiteViewsXls, this.getClass().getSimpleName()),"PASS");
	}else
		Utility.dataSetResult(suiteViewsXls,"Testcases", Utility.getRowNum(suiteViewsXls, this.getClass().getSimpleName()),"FAIL");

}

@DataProvider
public Object[][] testDataTest(){
	return Utility.getData(suiteViewsXls, this.getClass().getSimpleName());
}

}
