package com.hasbro.workflow;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hasbro.security.CostsheetTooling;
import com.hasbro.security.InternalBOMSoftG;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

/**
 * @author Govind Kumar Pandey
 * 1. Ensure that Assortment/Solid Product exist which has Hasbro Internal Source, Specification, Colorway and added to one Season only.
 * 2. Column 2 in Excel sheet is meant to ensure that we are creating Internal Cost sheet so mention Internal over there. If not mention Vendor over there.
 * 3. Column 14 in Excel sheet is meant to ensure that we are creating cost sheet for Assortment/Solid product or Retail. Assortment/Solid option value is Product and Retail option value is Retail.
 * 4. Column 15 in Excel sheet is meant to ensure that the routing option[Approve/Reject/Rework] are capture there for method reviewCostSheet.
 * 5. Column 16 in Excel Sheet is meant to ensure that Enter comment attribute is getting handled for reviewCostSheet method .
 * 6. Column 17 in Excel sheet is meant to ensure that expected Status of cost sheet after approval is getting capture for verifyCSStatus method.
 * 7. Column 18 in Excel sheet is meant to ensure that which Costing approver we will be adding during changeTeam. 
 * 8. Before starting test make sure to create new user and add them to Costing group so that he should be start appearing in Costing approver list of cost sheet in Change Team UI.
 *
 */

public class InternalcostingWF extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String strSpec;
	static String strCW;
	static String strCSInWork;
	static String costSheetName;
	static String strCostSheetName;
	static String costsheetName;
	
	public static By tblCostSheetIdentification = By.xpath("//td[contains(text(),'Cost Sheet Identification')]");
	public static By imgClose =By.xpath("//img[contains(@src,'deleteXsmall.png')]");
	public static By csCostSheetList = By.xpath("//a[@title='Cost Sheet List']");
	public static By lnkAdd =  By.xpath("//a[text()='Add']");
	public static By lstWave = By.xpath("//td[contains(text(),'*Wave')]//following::select[1]");
	public static By lstQuoteCurrency = By.xpath("//td[contains(text(),'Quote Currency')]//following::select[1]");
	public static By csName = By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By btnSave =  By.xpath("//a[text()='Save']");  
	public static By lstCostSheetAction =  By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']");
	public static By setteam = By.xpath("//td[contains(text(),'Set Team')]");
	public static By costapproveradd = By.xpath("//td[contains(text(),'Cost Approver')]//following::a[1]");
	public static By saveandClose = By.xpath("//a[contains(text(),'Save & Close')]");
	public static By changeteamoption = By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']/option[contains(text(),'Change Team:  CostSheet')]");
	public static By costapprover = By.xpath("//td[contains(text(),'Cost Approver')]");
	public static By costapprovervalue = By.xpath("//*[@id='COSTAPPROVER_UsersOptions']");
	public static By csStatus =By.id("hbStatus");
	public static By myWork =By.id("myWorkContentIcon");
	public static By mycheckoutitem =By.xpath("//a[contains(text(),'My Checked-Out Items')]");
	public static By lcsProductCostSheet =By.xpath("//a[contains(text(),'LCSProductCostSheet')]");
	public static By reviewCostsheet =By.xpath("//a[contains(text(),'Review Costsheet')]");
	public static By yourWorkList =By.xpath("//td[@class='contextBarText']/img[1]//following::text()[1]");
	public static By reviewCostsheetcheck =By.xpath(".//td[contains(text(),'Review Costsheet')]//preceding::input[1]");
	public static By expand =By.xpath("//td[contains(text(),'Review Costsheet')]//following::img[1]");
	public static By entercommentbox =By.xpath("//td[contains(text(),'Review Costsheet')]//following::textarea[1]");
	public static By selectapprove =By.xpath("//td[contains(text(),'Review Costsheet')]//following::input[@value='Approve']");
	public static By clickComplete =By.xpath("//td[contains(text(),'Review Costsheet')]//preceding::a[contains(text(),'Complete')]");	
	public static By reviewTaskCSName =By.xpath("//td[contains(text(),'Review Costsheet')]//following::a[1]");

	
	
	@Test(dataProvider="testDataTest")
	public void tcSCFunctional(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Internal costing workflow\n" + 
					"");
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

			if(data[3].equalsIgnoreCase("createCostSheet"))
				create_Cost_Sheet(data); 
 
			if(data[3].equalsIgnoreCase("changeTeam"))
				change_Team(data); 
			if(data[3].equalsIgnoreCase("updateCSUnderReview"))
				updateCS_UnderReview(data);
			
			if(data[3].equalsIgnoreCase("reviewCostSheet"))
				review_CostSheet(data);
			
			if(data[3].equalsIgnoreCase("verifyCSStatus"))
				verify_CS_Status(data);
						
		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	
	public static boolean review_CostSheet(String[] data) throws Exception
	{
		try{
			//Refresh the page
			driver.navigate().refresh();
			//wait for 1000ms
			Thread.sleep(1000);
			
			//Browse to sidebar frame
			driver.switchTo().frame("sidebarframe");
			
			//Click on Site>My Work
			driver.findElement(myWork).click();
			
			//Wait for lcsProductCostSheet attribute to visible
			WebDriverWait wait= new WebDriverWait(driver, 10);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lcsProductCostSheet));
						
			//Click on lcsProductCostSheet
			CommonFunctions.clickButtonOrLink(lcsProductCostSheet, "lcsProductCostSheet");
			
			//Click on Review Cost Sheet
			CommonFunctions.clickButtonOrLink(reviewCostsheet, "review Costsheet");
		
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
			//Wait for Review task to be visible
			WebDriverWait wait1= new WebDriverWait(driver, 1);
			WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(reviewTaskCSName));
			
			//Get the name of cost sheet for Review Task
			String csname =driver.findElement(reviewTaskCSName).getText();
			
			//Verify that Cost Sheet name in Review Cost Sheet task is same as cost sheet name which is created
			Assert.assertEquals(csname, costSheetName);
			log.info("Cost Sheet name in Review Cost Sheet task is same as cost sheet name which is created: " + costSheetName);
			
			//Click on Review Cost Sheet Check mark
			CommonFunctions.clickButtonOrLink(reviewCostsheetcheck, "check mark");
			
			//Click on Expand button
			CommonFunctions.clickButtonOrLink(expand, "expand enter comment");
			
			//Enter text 
			CommonFunctions.enterTextInTextbox(entercommentbox, data[16]);
			
			//Click on Approve, Reject or Rework Radio option
			CommonFunctions.clickButtonOrLink((By.xpath("//td[contains(text(),'Review Costsheet')]//following::input[@value='"+data[15]+"']")), "Click on Routing Option");
			
			//Click on Complete Button
			CommonFunctions.clickButtonOrLink(clickComplete, "click on Complete button");
			
			//Verify that Review Cost Sheet task is completed successfully and no more exist
			Assert.assertEquals(driver.findElements(reviewTaskCSName).size() ,0);			
			log.info("Review Cost Sheet Task for csInWork is completed");
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in review_CostSheet" , e);
			return false;
		}
		return true;
	}

	public static String create_Cost_Sheet(String[] data) throws Exception{
		try{
			//Create Cost Sheet
			createCS(data);
			
			//Wait for visibility of Cost Sheet tab
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//Get the name of cost sheet
			strCSInWork = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			//Remove the Actions: keyword name from cost sheet name
			String []varCSInWork = strCSInWork.split("Actions:");
			//Trim the cost sheet name so as to remove the leading and trailling space
			costSheetName = varCSInWork[0].trim();
			log.info("Internal Product Cost Sheet in Inwork status is : "+costSheetName);
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in create_Cost_Sheet()", e);
			return "";
		}
		return costSheetName;
	}
	
	public static String change_Team(String[] data) throws Exception{
		try{
			
			//Browse to Cost Sheet tab
			nevigationToCostsheet(data);
			
			//Click on Cost Sheet List
			CommonFunctions.clickButtonOrLink(csCostSheetList, "tab", "Cost Sheet List");
			
			// search cost sheet name in web table
			searchandClickforRequriedCostsheet(costSheetName);
			//Wait for the visibility of Cost Sheet tab
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//Wait for the visibility of Cost Sheet action
			CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
			
			//Click on Actions>Change Team
			CommonFunctions.clickButtonOrLink(lstCostSheetAction, "Action Button");
			CommonFunctions.clickButtonOrLink(changeteamoption, "Change Team:  CostSheet");
			
			//Wait for the visibility of Set team UI
			CommonFunctions.waitForVisibilityOfElement(setteam);
			//Wait for the visibility of Cost Approver panel
			CommonFunctions.waitForVisibilityOfElement(costapprover);
			
			//Select Cost Approver
			CommonFunctions.selectFromDropDownByVisibleText(costapprovervalue, data[18]);
			//Click on Add button
			CommonFunctions.clickButtonOrLink(costapproveradd, "btn", "costapproveradd");
			//Click on Save and Close button
			CommonFunctions.clickButtonOrLink(saveandClose, "btn", "saveandclose");
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in change_Team()", e);
			return "";
		}
		return costSheetName;
	}
	
	
	public static boolean updateCS_UnderReview(String[] data) throws Exception
	{
		try{	
				//Browse to Cost Sheet tab
				nevigationToCostsheet(data);
				
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(costSheetName);
				
				//Verify that the cost sheet tab is open
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				
				//Change Status
				updateCSStatus("Under Review");
				//Verify that the Cost Sheet status in detail page of cost sheet is Under Review
				verifyCSStatus("Under Review",costSheetName);
				//close the open cost sheet
				closeTheOpenCostSheet();
		
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateCS_UnderReview" + data[3], e);
			return false;
		}	
		return true;
	}
	
	public static boolean verify_CS_Status(String[] data) throws Exception
	{
		try{	
				//Browse to Cost Sheet tab
				nevigationToCostsheet(data);
				
				// click on Cost sheet List tab
				CostsheetTooling.clickOnCostSheetListTab();
				// search cost sheet name in web table
				searchandClickforRequriedCostsheet(costSheetName);
				//Verify that the cost sheet tab is open
				CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
				//Verify Status
				verifyCSStatus(data[17],costSheetName);
				//close the open cost sheet
				closeTheOpenCostSheet();
		
		}catch(Exception e){
			fail=true;
			log.error("Exception in verify_CS_Status" + data[3], e);
			return false;
		}	
		return true;
	}
	
	
	public static boolean verifyCSStatus(String sStatus,String csName) throws Exception{
		try {
			//Wait for the visibility of select cost sheet tab to appear
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//Wait for Cost Sheet status attribute to appear
			CommonFunctions.waitForVisibilityOfElement(csStatus);
			//Get the name of Cost Sheet status 
			String strStatus = driver.findElement(csStatus).getText();
			//Verify that the cost sheet status expected and actual in UI are matching
			Assert.assertEquals(strStatus.trim(), sStatus);
			log.info("Verification: Status verified as: "+ sStatus);
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in verification for : " + sStatus + "for costsheet: " +csName , e);
			return false;
		}
	}
	
	public static boolean updateCSStatus(String sStatus) throws Exception{
		try {
			//Wait for the visibility of Cost Sheet action menu bar to appear
			CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
			//Wait for Cost Sheet status to be visible
			CommonFunctions.waitForVisibilityOfElement(csStatus);
			//Click on Actions>Update button
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

	public static boolean nevigationToCostsheet(String[] data) throws Exception
	{
		try{
			//Search product
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Sourcing Tab
			CommonProjectFunctions.clickSourcingTab(data[7]);
			//Click on Costing Tab
			CommonProjectFunctions.clickCostingTab();
			//Select Source
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.selectSource, data[8]);
			//Select Specification
			strSpec=InternalBOMSoftG.AddSpecification(data);
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in nevigationToCostsheet" , e);
			return false;
		}
		return true;
	}
	
	
	public static String searchandClickforRequriedCostsheet(String costSheetName) throws Exception
	{
		try{
			int colIndex=1;
			//Get the list of Cost Sheets in Costing tab
			List<WebElement> col1 = driver.findElements(By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td[3]"));
			System.out.println(col1.size());
			//Apply the for loop to get the expected cost sheet and click it
			for(WebElement e: col1){
				colIndex=colIndex+1;
				System.out.println("e.getText(): "+ e.getText());
				System.out.println("Segment: "+costSheetName);
				//Once expected and search cost sheet are match click on it
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
	
	public static Boolean createCS(String[] data) throws Exception{
		try{
			//If cost sheet tab is not visible
			if(driver.findElements(CostsheetTooling.tabCostsheet).size() == 0) {
				//Browse to Cost Sheet tab
				nevigationToCostsheet(data);
			}


			//Click on Cost Sheet List
			CommonFunctions.clickButtonOrLink(csCostSheetList, "tab", "Cost Sheet List");
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet"); 

			// select tooling type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"),"link","Cost sheet type");

			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(CostsheetTooling.lstcolorwayGroupOptions,0);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
			if(data[14].contains("Product")) {
				// select wave
				CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[13]);
			}
			// select QuoteCurrency
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			if(data[2].equalsIgnoreCase("Internal")) {
				costsheetName ="InWork"+CommonFunctions.getRandomString(4);
				CommonFunctions.enterTextInTextbox(csName, costsheetName);
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
	
	
	public static boolean closeTheOpenCostSheet() throws Exception
	{
		try{
			//Wait for visibility of image close buttong
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
	

	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteSmokeTestXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSmokeTestXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteSmokeTestXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSmokeTestXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteSmokeTestXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteSmokeTestXls,"Testcases", Utility.getRowNum(suiteSmokeTestXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteSmokeTestXls,"Testcases", Utility.getRowNum(suiteSmokeTestXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteSmokeTestXls, this.getClass().getSimpleName());
	}

}
