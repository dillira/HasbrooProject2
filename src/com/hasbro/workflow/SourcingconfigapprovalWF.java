package com.hasbro.workflow;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hasbro.security.CostsheetExternalProduct;
import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.SourcingConfig;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

/**
 * @author anjali.gupta
 *
 */

public class SourcingconfigapprovalWF extends TestsuiteBase{
	public static By objClass = By.id("r2_dimExpander");
	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static int num;
	static Random rand;
	int y=0;
	static int i;
	String loginVal;
	Boolean flaglogin=false;
	static Set set;
	static Iterator iter;
	static String parent;
	static String child;
	static String leadName;
	static String headName;
	static String strSource;
	static String mainwindow;
	static String prodName;
	public static By participantName = By.xpath("//label[contains(text(),'Participant Name:')]//following::input[1]");
	public static By searchBtn = By.xpath("//button[text()='Search']");
	public static By okBtn = By.xpath("//button[contains(text(),'K')]");
	public static By adminLink = By.xpath("//a[contains(text(),'Administrative')]");
	public static By manageUsersIcon = By.id("manageUsersIcon");
	public static By organizationUsers = By.xpath("//a[contains(text(),'Organization Users')]");
	public static By infoIcon = By.xpath("//img[contains(@src,'details.gif')]");
	public static By plusSign = By.xpath("//button[contains(@style,'add')]");
	public static By iconUserCreate = By.xpath("//button[contains(@style,'usercreate.gif')]");

	public static By userName = By.xpath("//td[contains(text(),'User Name:')]//following::input[1]");
	public static By fullName = By.xpath("//input[@id='fullName']");
	public static By lastName = By.xpath("//input[@id='last']");
	public static By email = By.id("eMail");
	public static By password = By.id("password");
	public static By passwordConfirm = By.id("passwordConfirmation");
	public static By btnNext = By.xpath("//button[contains(text(),'ext')]");
	public static By btnUGPlus = By.xpath("//div[@id='principaladmin.group.listPAGroups.toolBar']/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/em/button");
	public static By grpName = By.xpath("//label[contains(text(),'Group Name:')]//following::input[1]");
	public static By sourcingLead = By.id("Sourcing Lead");
	public static By sourcingHead = By.id("Sourcing Head");
	public static By searchresult = By.id("PP_CD_searchResults");
	public static By ugAddBtn = By.id("PP_CD_movePrincipalToPrincipalList");
	public static By finishBtn = By.xpath("//button[contains(text(),'inish')]");
	public static By backBtn = By.xpath("//button[contains(text(),'ack')]");
	public static By ddSourcingLead   = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By ddSourcingHead   = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");

	public static By clearUGCache = By.xpath("//a[text()='Clear User/Group Cache']");
	public static By clearFTCache = By.xpath("//a[text()='Clear FlexType Cache']");
	public static By lnkSourConfig = By.xpath("//div[@id='workListDiv']/div[2]/a[contains(text(),'Sourcing Configuration')]");
	public static By lnkConfirmSourConfig = By.xpath("//a[contains(text(),'Confirm Sourcing Configuration')]");
	public static By commentPlus = By.xpath("//img[contains(@src,'add')]");
	public static By sourcStatus = By.id("hbSourcingConfigStatus");
	public static By confirmation = By.xpath("//b[text()='CONFIRMATION: Create successful']");
	//	public static By infoIcon = By.xpath("//a[@class='linkfont icon']/img");


	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSCWF(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Sourcing config Approval Workflow");
			log.info("**************************"); 
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("Testcase is :" + data[2]);
			log.info("**************************"); 
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

			switch (data[2]) {
			case "Create Sourcing Lead user":
				log.info("In side :-> " + data[2]);	
				createSourcingLeaduser(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Create Sourcing Head user":
				log.info("In side :-> " + data[2]);	
				createSourcingHeaduser(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Approved Flow":
				log.info("In side :-> " + data[2]);	
				workFlow(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Rejected Flow1":
				log.info("In side :-> " + data[2]);	
				workFlow(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Rejected Flow2":
				log.info("In side :-> " + data[2]);	
				workFlow(data);
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

	public static String createSourcingLeaduser(String[] data) throws Exception{
		try{
			log.info("Creating Sourcing lead");
			nevigateUserGroup(data);
			rand = new Random(System.currentTimeMillis());
			num = rand.nextInt(200);
			leadName=data[3]+num;
			System.out.println(leadName);
			CommonFunctions.waitForElementTobeClickable(userName);
			//Enter value in User Name
			CommonFunctions.enterTextInTextbox(userName,leadName);
			log.info("Lead user name is: "+leadName);
			//Enter value in Full Name
			CommonFunctions.enterTextInTextbox(fullName,leadName);
			//Enter value in Last Name
			CommonFunctions.enterTextInTextbox(lastName,leadName);
			//Enter value in email
			CommonFunctions.enterTextInTextbox(email,data[5]);
			//Enter value in password textbox
			CommonFunctions.enterTextInTextbox(password,data[4]);
			//Enter value in Password Confirmation textbox
			CommonFunctions.enterTextInTextbox(passwordConfirm,data[4]);
			//Click on Next
			CommonFunctions.clickButtonOrLink(btnNext, "btn", "next");
			CommonFunctions.waitForElementTobeClickable(btnUGPlus);
			//Click on plus button
			CommonFunctions.clickButtonOrLink(btnUGPlus, "btn", "Plus");
			String orignalWindow = driver.getWindowHandle();
			// Switch to new window opened
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			CommonFunctions.waitForElementTobeClickable(grpName);
			//Enter Group Name
			CommonFunctions.enterTextInTextbox(grpName,data[6]);
			// Click on search
			CommonFunctions.clickButtonOrLink(searchBtn, "btn", "Search");
			//Select Sourcing Lead
			CommonFunctions.selectFromDropDownByVisibleText(searchresult, data[7]);
			//Click on Add>> button
			CommonFunctions.clickButtonOrLink(ugAddBtn, "btn", "Add");
			//Click on OK button
			CommonFunctions.clickButtonOrLink(okBtn, "btn", "OK");
			//Switch back to previous window
			driver.switchTo().window(orignalWindow);
			//Click on Finish button
			CommonFunctions.clickButtonOrLink(finishBtn, "btn", "Finish");
			//	Boolean alt= driver.switchTo().alert(); 

			if(CommonFunctions.isAlertPresent())
			{
			driver.switchTo().alert().accept();
			 //click on back button
			CommonFunctions.clickButtonOrLink(backBtn, "btn", "Back");
			leadName=leadName+"1";
			CommonFunctions.waitForElementTobeClickable(userName);
			//Enter value in User Name
			driver.findElement(userName).clear();
			CommonFunctions.enterTextInTextbox(userName,leadName);
			//Enter value in Full Name
			driver.findElement(fullName).clear();
			CommonFunctions.enterTextInTextbox(fullName,leadName);
			//Enter value in Last Name
			driver.findElement(lastName).clear();
			CommonFunctions.enterTextInTextbox(lastName,leadName);
			//Click on finish
			CommonFunctions.clickButtonOrLink(finishBtn, "btn", "Finish");
			}
			//CommonFunctions.waitForVisibilityOfElement(confirmation);
			//CommonFunctions.waitForVisibilityOfElement(infoIcon);
		}catch(Exception e){
			fail=true;
			log.error("Exception in createSourcingLeaduser()", e);
			return "";
		}
		return leadName;
	}	

	public static String createSourcingHeaduser(String[] data) throws Exception{
		try{
			nevigateUserGroup(data);
			headName=data[3]+num;;
			CommonFunctions.waitForElementTobeClickable(userName);
			//Enter value in User Name
			CommonFunctions.enterTextInTextbox(userName,headName);
			log.info("Head user name is: "+headName);
			//Enter value in Full Name
			CommonFunctions.enterTextInTextbox(fullName,headName);
			//Enter value in Last Name
			CommonFunctions.enterTextInTextbox(lastName,headName);
			//Enter value in email
			CommonFunctions.enterTextInTextbox(email,data[5]);
			//Enter value in password textbox
			CommonFunctions.enterTextInTextbox(password,data[4]);
			//Enter value in Password Confirmation textbox
			CommonFunctions.enterTextInTextbox(passwordConfirm,data[4]);
			//Click on Next
			CommonFunctions.clickButtonOrLink(btnNext, "btn", "next");
			CommonFunctions.waitForElementTobeClickable(btnUGPlus);
			//Click on plus button
			CommonFunctions.clickButtonOrLink(btnUGPlus, "btn", "Plus");
			String orignalWindow = driver.getWindowHandle();
			// Switch to new window opened
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			CommonFunctions.waitForElementTobeClickable(grpName);
			//Enter Group Name
			CommonFunctions.enterTextInTextbox(grpName,data[6]);
			// Click on search
			CommonFunctions.clickButtonOrLink(searchBtn, "btn", "Search");
			CommonFunctions.waitForElementTobeClickable(searchresult);
			//Select Sourcing Head
			CommonFunctions.selectFromDropDownByVisibleText(searchresult, data[7]);
			//Click on Add>> button
			CommonFunctions.clickButtonOrLink(ugAddBtn, "btn", "Add");
			//Click on OK button
			CommonFunctions.clickButtonOrLink(okBtn, "btn", "OK");
			//Switch back to previous window
			driver.switchTo().window(orignalWindow);
			//Click on Finish button
			CommonFunctions.clickButtonOrLink(finishBtn, "btn", "Finish");
			driver.switchTo().window(child);
			driver.quit();
			//	driver.switchTo().frame(mainwindow);
			clearflexCache(data);
		}catch(Exception e){
			fail=true;
			log.error("Exception in createSourcingHeaduser()", e);
			return "";
		}
		return headName;
	}

	public static boolean clearflexCache(String[] data) throws Exception
	{
		try{
			openBrowser();
			Thread.sleep(2000);
			launchApp(data[0],data[1]);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			//Click on Administrative in left pane
			CommonFunctions.clickButtonOrLink(adminLink, "lnk", "Administrative");
			//Click on Clear User/Group Cache
			CommonFunctions.clickButtonOrLink(clearUGCache, "link", "User/Group Cache");
			//Click on Clear FlexType Cache
			CommonFunctions.clickButtonOrLink(clearFTCache, "link", "FlexType Cache");
			//driver.close();
		}catch(Exception e){
			fail=true;
			log.error("Exception in clearflexCache" , e);
			return false;
		}
		return true;
	}
	public static String workFlow(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			prodName = CommonProjectFunctions.CreateProdFromLineSheet("Assortment/Solid", data[9], "Development Plan", "Create New: Product", "TOYS", "BOYS", "NERF", "BOYS", "Assortment", "Not IP Sensitive (Open)", "", "", "", "", "",  "",  "");
			log.info("Created product is: "+prodName);
			//Click on Sourcing
			CommonProjectFunctions.clickSourcingTab(data[9]);			
			AddSourcingConfiguration(data);
			CommonProjectFunctions.logOut();
			//Launch App with required login\Pwd
			openBrowser();
			Thread.sleep(2000);
			launchApp(data[3],data[4]);
			log.info("****Login with: "+data[3]);
			i=0;
			approveTask(data,data[14]);
			openBrowser();
			Thread.sleep(2000);
			launchApp(data[8],data[4]);
			log.info("****Login with: "+data[8]);
			approveTask(data,data[15]);
			verifyStatus(data);

		}catch(Exception e){
			fail=true;
			log.error("Exception in workFlow()", e);
			return "";
		}
		return strSource;
	}

	public static boolean approveTask(String[] data,String status) throws Exception{
		try{
			
			log.info("Approve Task verification Started.....");
			log.info("Task for: "+ data[14]);
			//driver.navigate().refresh();
			//Click on mywork
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			CommonFunctions.waitForElementTobeClickable(SmokeFlow.myWork);
			CommonFunctions.clickButtonOrLink(SmokeFlow.myWork, "plus icon", "My work");
			if(data[2].equalsIgnoreCase("Rejected Flow2")&&(i!=0)) 
			{
				//CommonFunctions.waitForInvisibilityOfElement(lnkSourConfig);
				Assert.assertEquals(driver.findElements(lnkSourConfig).size(),0);
				log.info("Sourcing config link is not present as Lead rejected request");
			}
			else {
				//Click on Sourcing Config
				CommonFunctions.waitForElementTobeClickable(lnkSourConfig);
				CommonFunctions.clickButtonOrLink(lnkSourConfig, "lnk", "Sourcing Config");
				CommonFunctions.waitForElementTobeClickable(lnkConfirmSourConfig);
				//Click Confirm sourcing config
				CommonFunctions.clickButtonOrLink(lnkConfirmSourConfig, "lnk", "Confirm Sourcing Configuration");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				CommonFunctions.waitForElementTobeClickable(SmokeFlow.selectCheckBox);
				//Select check box
				CommonFunctions.selectCheckbox(By.xpath("//a[text()='002 : FUNSKOOL ("+prodName+")']/preceding::input[1]"));  //selectCheckBox
				//a[text()='002 : FUNSKOOL (E4783)']/preceding::input[1]
				//Select option button Approve
				//CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", data[14]);
				
				System.out.println(By.xpath("//a[text()='002 : FUNSKOOL ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"));
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : FUNSKOOL ("+prodName+")']//following::td[contains(text(),'"+status+"')]/preceding::td[1]/input"), "radio", status);
				
				//Click on + sign
			//	CommonFunctions.clickButtonOrLink(By.xpath("//img[contains(@src,'add')]"), "Img", "Plus sign");
				System.out.println(By.xpath("//a[text()='002 : FUNSKOOL ("+prodName+")']//following::img[1]"));
				CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='002 : FUNSKOOL ("+prodName+")']//following::img[1]"), "Img", "Plus sign");
				
				//Enter comment
				CommonFunctions.enterTextInTextbox(By.xpath("//a[text()='002 : FUNSKOOL ("+prodName+")']//following::textarea[1]"), data[12]);
				
				//Click on Complete
				CommonFunctions.clickButtonOrLink(SmokeFlow.completeBtn, "btn", "Completed");
			}	
				Thread.sleep(3000);
				CommonProjectFunctions.logOut();
				log.info("Task verification completed.....");
				i++;
				System.out.println("end: "+i); 
			
			}catch(Exception e){
				fail=true;
				log.error("Exception in approveTask()", e);
				return false;
			}
			return true;
		}

		public static boolean verifyStatus(String[] data) throws Exception{
			try{
				log.info("Verify Status.....");
				//Launch App with required login\Pwd
				openBrowser();
				Thread.sleep(3000);
				launchApp(data[0],data[1]);
				//Search Product
				CommonProjectFunctions.searchProduct(prodName);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[9]);		
				//Select source
				CommonFunctions.selectFromDropDownByVisibleText(CostsheetExternalProduct.selectSource, data[10]);
				//strSource=new Select(driver.findElement(CostsheetExternalProduct.selectSource)).getFirstSelectedOption().getText();
				Thread.sleep(3000);
				CommonFunctions.waitForElementTobeClickable(sourcStatus);
				log.info("Waited for object : " + sourcStatus);
				//Verify Status
				Assert.assertEquals(driver.findElement(sourcStatus).getText().trim(),data[13]);
				log.info("***************************************");
				log.info("Sourcing Status verified as: "+data[13]);
				log.info("***************************************");
				log.info("Verify Status completed.....");
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyStatus()", e);
				return false;
			}
			return true;
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
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[11]+"')]/preceding::td[1]/a"), "Supplier selected");
				//CommonFunctions.clickButtonOrLink(SourcingConfig.choose, "Supplier selected");
				driver.switchTo().window(parent);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//Sourcing Lead 
				CommonFunctions.selectFromDropDownByVisibleText(ddSourcingLead,data[3]);
				CommonFunctions.waitForElementTobeClickable(ddSourcingHead);
				//*Sourcing Head 
				CommonFunctions.selectFromDropDownByVisibleText(ddSourcingHead,data[8]);
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
		public static String nevigateUserGroup(String[] data) throws Exception
		{
			try{
				if(data[2].equalsIgnoreCase("Create Sourcing Lead user")) {
					driver.switchTo().defaultContent();
					driver.switchTo().frame("sidebarframe");
					//Click on Administrative in left pane
					CommonFunctions.clickButtonOrLink(adminLink, "lnk", "Administrative");
					//Click on Manage user Icon
					CommonFunctions.clickButtonOrLink(manageUsersIcon, "lnk", "Manage user");
					//Click on Organization user link
					CommonFunctions.clickButtonOrLink(organizationUsers, "lnk", "Organization user");
				}
				//Switch to new window
				mainwindow  = driver.getWindowHandle();
				set = driver.getWindowHandles();
				iter = set.iterator();
				parent = (java.lang.String) iter.next();
				child = (java.lang.String) iter.next();
				driver.switchTo().window(child);

				CommonFunctions.waitForElementTobeClickable(iconUserCreate);
				//	CommonFunctions.waitForElementTobeClickable(infoIcon);
				//Click on plus sign
				CommonFunctions.clickButtonOrLink(iconUserCreate, "icon", "Create New User");
				// Switch to new window opened
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in nevigateUserGroup" , e);
				return "";
			}
			return mainwindow;
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
