package com.hasbro.security;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

/**
 * @author anjali Gupta
 *
 */

public class SourcingConfig extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;

	public static By selectSource= By.id("sourcingConfigId");
	public static By ActionDropdown= By.id("prodseasonActions");
	public static By detailsTablink= By.xpath("//a[contains(text(),'Details')]");
	public static By detailPageSeasonDD= By.id("splId");
	public static By season= By.id("splId");
	public static By productLifecycleState= By.xpath("//td[contains(text(),'Product Lifecycle State')]//following::td[1]");
	public static By name= By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By headingcreateNewSourcingConfig= By.xpath("//td[contains(text(),'Create New Sourcing Configuration')]");
	public static By supplier = By.xpath("//a[contains(text(),'Supplier:')]");
	public static By factory = By.xpath("//a[contains(text(),'Factory:')]"); 
	public static By search= By.id("SearchButton1");
	public static By choose = By.xpath("//a[contains(text(),'choose')]");
	public static By CreateSourcebtn = By.xpath("//a[contains(text(),'Create')]");

	//Read Attributes:	
	public static By sourcingTablink= By.xpath("//a[contains(text(),'Sourcing')]");
	public static By labelGeneralAttribute = By.xpath("//td[contains(text(),'General Attributes')]");
	public static By labelGlobalSourcing = By.xpath("//td[contains(text(),'Global Sourcing')]");
	public static By sourcingAction= By.id("sourcingActions");
	public static By sourcingDetails= By.xpath("//td[contains(text(),'Sourcing Details:')]");
	public static By actionDD= By.id("prodseasonActions");
	public static By btnSave= By.xpath("//a[text()='Save']");

	//Sourcing Lifecycle State:
	// Xpath for Source lifecycle Stste on Product Details Page
	public static By RO_UpdateLifecycleState= By.xpath("//td[contains(text(),'Source Lifecycle State')]//following::td[1]");
	//Xpath for Set Lifecycle State Page Header
	public static By setLifecycleState= By.xpath("//td[contains(text(),'Set Lifecycle State')]");
	//Xpath for Update button on Set life cycle state page
	public static By linkUpdate= By.xpath("//a[text()='Update']");
	//Xpath for dropdown on Set life cycle state page
	public static By editable_UpdateLifecycleState= By.id("lcstate");
	public static By editable_Status= By.xpath("//td[contains(text(),'Sourcing Status')]//following::select[1]");
	public static By ddStrategicSC= By.xpath("//td[contains(text(),'Strategic Sourcing Category')]//following::select[1]");
	public static By ddStrategicSSubCat= By.xpath("//td[contains(text(),'Strategic Sourcing Sub Category')]//following::select[1]");
	public static By roStrategicSourcingCategory= By.id("hbStrategicSourcingCategory");
	public static By roStrategicSourcingSubCategory= By.id("hbStrategicSourcingSubCategory");
	
	public static By scApprovers= By.name("Sourcing Config Approvers");
	public static By lblManager= By.xpath("//td[contains(text(),'Manager')]");
	public static By lblSourcingLead = By.xpath("//td[contains(text(),'Sourcing Lead')]");
	public static By lblSourcingHead = By.xpath("//td[contains(text(),'Sourcing Head')]");
	public static By lblManagement = By.xpath("//td[contains(text(),'Management')]");
	public static By ddManager = By.xpath("//td[contains(text(),'Manager')]//following::select[1]");
	public static By roManager = By.xpath("//td[contains(text(),'Manager')]//following::a[1]");
	
	//td[contains(text(),'Manager')]
	
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	//static String valULCSAfterChange;
	static String inwork ="In Work";
	static String strSource;
	static String initial_LifecycleState;
	static String strIRSource;
	static String strApprSource;
	static String  strRejSource;
	static String strCreate;
	static int i;


	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSourcingConfig(String[] data) throws Exception{
		try{
			count++;
			log.info(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for  Sourcing Configuration Security");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[2]);
			log.info("col1 :" + data[3]);
			log.info("col4 :" + data[4]);
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
					log.info("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}

			switch (data[3]) {
			/******************/
			case "CreateInReview":
				log.info("In side :-> " + data[3]);	
				createSCInReview(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateApproved":
				log.info("In side :-> " + data[3]);	
				createSCApproved(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateRejected":
				log.info("In side :-> " + data[3]);	
				createSCRejeted(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Create":
				log.info("In side :-> " + data[3]);	
				create_SourcingConfig(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SCReadView":
				log.info("In side :-> " + data[3]);	
				verifySCReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SCUpdate":
				log.info("In side :-> " + data[3]);	
				verifySCUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Delete":
				log.info("In side :-> " + data[3]);	
				delete_SourcingConfig(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "InReviewRead_View":
				log.info("In side :-> " + data[3]);	
				verifyInReviewRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "InReviewUpdate":
				log.info("In side :-> " + data[3]);	
				verifyInReviewUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "ApprovedRead_View":
				log.info("In side :-> " + data[3]);	
				verifyApprovedRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "ApprovedUpdate":
				log.info("In side :-> " + data[3]);	
				verifyApprovedUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "RejectedRead_View":
				log.info("In side :-> " + data[3]);	
				verifyRejectedRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "RejectedUpdate":
				log.info("In side :-> " + data[3]);	
				verifyRejectedUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GeneralAttirbutesRead_View":
				log.info("In side :-> " + data[3]);	
				verifyGeneralAttributesReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GeneralAttirbutesUpdate":
				log.info("In side :-> " + data[3]);	
				verifyGeneralAttributesUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GlobalSourcingRead_View":
				log.info("In side :-> " + data[3]);	
				verifyGlobalSourcingReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GlobalSourcingUpdate":
				log.info("In side :-> " + data[3]);	
				verifyGlobalSourcingUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SCARead_View":
				log.info("In side :-> " + data[3]);	
				verifySCARead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SCAUpdate":
				log.info("In side :-> " + data[3]);	
				verifySCAUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PHisRead_View":
				log.info("In side :-> " + data[3]);	
				verifyPHisRead_View(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "PHisUpdate":
				log.info("In side :-> " + data[3]);	
				verifyPHisUpdate(data);
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


	public static String create_SourcingConfig(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);

			if(data[4].equalsIgnoreCase("Yes")) { //&& plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){
				AddSourcingConfiguration(data);
				i=countOptionValue();
				CommonFunctions.selectFromDropDownByIndex(selectSource, i);
				strCreate=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}else if(data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(ActionDropdown).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
			}
			else{
				log.info("Create Sourcing config is not applicale");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			return "";
		}
		return strCreate;
	}

	public static String createSCInReview(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);
			AddSourcingConfiguration(data);
			i=countOptionValue();
			CommonFunctions.selectFromDropDownByIndex(selectSource, i);
			strIRSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			//Apply Action
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source");
			CommonFunctions.waitForElementTobeClickable(editable_Status);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "In-Review");
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			return "";
		}
		return strIRSource;
	}

	public static int countOptionValue() throws Exception{
		try{
			Select se = new Select(driver.findElement(selectSource));
			List<WebElement> ec = se.getOptions();
			System.out.println("Number of items: " + ec.size());
			i=ec.size()-2;
			System.out.println(i);
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			return 0;
		}
		return i;
	}

	public static String createSCApproved(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);
			AddSourcingConfiguration(data);
			i=countOptionValue();
			CommonFunctions.selectFromDropDownByIndex(selectSource, i);
			strApprSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			//Apply Action
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source");
			CommonFunctions.waitForElementTobeClickable(editable_Status);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Approved");
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			return "";
		}
		return strApprSource;
	}

	public static String createSCRejeted(String[] data) throws Exception{
		try{
			log.info("Code entered for Creating Sourcing Configuration.......");
			nevigateSourcingTab(data);
			AddSourcingConfiguration(data);
			i=countOptionValue();
			CommonFunctions.selectFromDropDownByIndex(selectSource, i);
			strRejSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			//Apply Action
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,"Update Source");
			CommonFunctions.waitForElementTobeClickable(editable_Status);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Rejected");
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SourcingConfig()", e);
			return "";
		}
		return strRejSource;
	}
	public static boolean verifySCReadView(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource, strCreate);

			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				String GALabel=driver.findElement(labelGeneralAttribute).getText();
				log.info(" General Attributes Value is    :"+GALabel);
				Assert.assertEquals(GALabel.trim(), "General Attributes:");
				log.info("General Attributes label is Present");
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
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
	public static boolean verifySCUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update

				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				//Apply Action
				CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
				String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
				Thread.sleep(1000);
				//Click on Update
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
				log.info("State return from method  :"+valULCSAfterChange);
				Assert.assertNotEquals(updated_LifecysleState, initial_LifecycleState);
				log.info("General Attribute is Updatable .....");
			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable !!!");
			}	
			else
			{
				//log.info("For this Either Product Lifecycle State is :"+plcState+" or General Attribute Update is Not Applicable");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}




	public static String sourcing_selectUpdateLifecycleState(String[] data) {
		try{
			/*if(data[3].equalsIgnoreCase("SetState")) {
				valULCS = new Select(driver.findElement(editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
				if(valULCS.contains("Open")){
					CommonFunctions.selectFromDropDownByVisibleText(editable_UpdateLifecycleState,"Confirmed");
				}
				else if(valULCS.contains("Confirmed")){
					CommonFunctions.selectFromDropDownByVisibleText(editable_UpdateLifecycleState, "In-Review");
				}
				else if(valULCS.contains("In-Review")){
					CommonFunctions.selectFromDropDownByVisibleText(editable_UpdateLifecycleState, "Rejected");
				}
				else if(valULCS.contains("Rejected")){
					CommonFunctions.selectFromDropDownByVisibleText(editable_UpdateLifecycleState, "Approved");
				}
				else if(valULCS.contains("Approved")){
					CommonFunctions.selectFromDropDownByVisibleText(editable_UpdateLifecycleState, "In-Review");
				}
				valULCSAfterChange = new Select(driver.findElement(editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
				log.info("valULCS: "+valULCSAfterChange);
			}
			else {*/
			valULCS = new Select(driver.findElement(editable_Status)).getFirstSelectedOption().getText();
			if(valULCS.contains("Open")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status,"Confirmed");
			}
			else if(valULCS.contains("Confirmed")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "In-Review");
			}
			else if(valULCS.contains("In-Review")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Rejected");
			}
			else if(valULCS.contains("Rejected")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "Open");
			}
			else if(valULCS.contains("Approved")){
				CommonFunctions.selectFromDropDownByVisibleText(editable_Status, "In-Review");
			}
			//valULCSAfterChange = new Select(driver.findElement(editable_Status)).getFirstSelectedOption().getText();
			//log.info("valULCS: "+valULCSAfterChange);
			//	}
		}catch(Exception e){
			fail=true;
			log.error("Exception in selectUpdateLifecycleState()", e);
		}
		//return valULCSAfterChange;
		return "";
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static boolean delete_SourcingConfig(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource, strCreate);
			log.info("Code is in side---------Delete");
			if(data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.selectFromDropDownByVisibleText(sourcingAction,data[8]);
				log.info("about to delete the sourcing Config-----");
				Thread.sleep(1000);
				//Accept AletPopup
				String msg= driver.switchTo().alert().getText(); 
				log.info("msg is -------"+msg);
				driver.switchTo().alert().dismiss();
				Assert.assertEquals(msg, "Are you sure you want to delete the Sourcing Configuration?");
				log.info("User is having Delete Access ..");
			}

			else if(data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(actionDD).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("User is not having Delete Access !!");
			}
			else
				log.info("Delete is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_SourcingConfig()", e);
			return false;
		}
		return true;
	}

	public static boolean verifyInReviewRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strIRSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					log.info("Code is in side---------");
					String GALabel=driver.findElement(labelGeneralAttribute).getText();
					log.info(" General Attributes Value is    :"+GALabel);
					Assert.assertEquals(GALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					fail=true;
					log.error("General Attributes label is Absent");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
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
	public static boolean verifyInReviewUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strIRSource);
			// Checking the value for Product Lifecycle State
			//	String plcState = driver.findElement(productLifecycleState).getText();
			//	log.info("Product Life Cycle State for :"+data[5]+ "is :"+plcState);
			log.info("We are in process to Update Sourcing Configuration General Attribute if Product Lifecycle State is In Work");
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update

				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				//Select Source from drop down as "002 : Source1"
				
				//String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
				updateStatus("Rejected",data);
				
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
				//log.info("State return from method  :"+valULCSAfterChange);
				Assert.assertEquals(updated_LifecysleState, "Rejected");
				log.info("General Attribute is Updatable .....");
				//Reverting back
				updateStatus("In-Review",data);
			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable !!!");
			}	
			else
			{
				//log.info("For this Either Product Lifecycle State is :"+plcState+" or General Attribute Update is Not Applicable");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean updateStatus(String state,String[] data) throws Exception{
		try{
			CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
			CommonFunctions.selectFromDropDownByVisibleText(editable_Status, state);
			Thread.sleep(1000);
			//Click on Update
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		//	valULCSAfterChange = new Select(driver.findElement(editable_Status)).getFirstSelectedOption().getText();
		//	log.info("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateBOMStatus()", e);
			return false;
		}
		return true;
	}


	public static boolean verifyApprovedRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					log.info("Code is in side---------");
					String GALabel=driver.findElement(labelGeneralAttribute).getText();
					log.info(" General Attributes Value is    :"+GALabel);
					Assert.assertEquals(GALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					fail=true;
					log.error("General Attributes label is Absent");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
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
	public static boolean verifyApprovedUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strApprSource);
			// Checking the value for Product Lifecycle State
			//	String plcState = driver.findElement(productLifecycleState).getText();
			//	log.info("Product Life Cycle State for :"+data[5]+ "is :"+plcState);
			//log.info("We are in process to Update Sourcing Configuration General Attribute if Product Lifecycle State is In Work");
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update

				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				//Select Source from drop down as "002 : Source1"
				//CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
				/*String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
				Thread.sleep(1000);
				//Click on Update
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");*/
				updateStatus("Rejected",data);
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
				//log.info("State return from method  :"+valULCSAfterChange);
				Assert.assertEquals(updated_LifecysleState, "Rejected");
				log.info("General Attribute is Updatable .....");
				updateStatus("Approved",data);
			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable !!!");
			}	
			else
			{
				//log.info("For this Either Product Lifecycle State is :"+plcState+" or General Attribute Update is Not Applicable");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean verifyRejectedRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strRejSource);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					log.info("Code is in side---------");
					String GALabel=driver.findElement(labelGeneralAttribute).getText();
					log.info(" General Attributes Value is    :"+GALabel);
					Assert.assertEquals(GALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					fail=true;
					log.error("General Attributes label is Absent");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
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
	public static boolean verifyRejectedUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strRejSource);
			// Checking the value for Product Lifecycle State
			//	String plcState = driver.findElement(productLifecycleState).getText();
			//	log.info("Product Life Cycle State for :"+data[5]+ "is :"+plcState);
			log.info("We are in process to Update Sourcing Configuration General Attribute if Product Lifecycle State is In Work");
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update

				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				//Select Source from drop down as "002 : Source1"
				//CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
				/*String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
				Thread.sleep(1000);
				//Click on Update
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");*/
				updateStatus("In-Review",data);
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
			//	log.info("State return from method  :"+valULCSAfterChange);
				Assert.assertEquals(updated_LifecysleState,"In-Review");
				log.info("General Attribute is Updatable .....");
				updateStatus("Rejected",data);
			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable !!!");
			}	
			else
			{
				//log.info("For this Either Product Lifecycle State is :"+plcState+" or General Attribute Update is Not Applicable");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}
	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					log.info("Code is in side---------");
					String GALabel=driver.findElement(labelGeneralAttribute).getText();
					log.info(" General Attributes Value is    :"+GALabel);
					Assert.assertEquals(GALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					fail=true;
					log.error("General Attributes label is Absent");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
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
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			// Checking the value for Product Lifecycle State
			//	String plcState = driver.findElement(productLifecycleState).getText();
			//	log.info("Product Life Cycle State for :"+data[5]+ "is :"+plcState);
			//log.info("We are in process to Update Sourcing Configuration General Attribute if Product Lifecycle State is In Work");
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update

				log.info("Code is in side Update General Attribute !!");
				initial_LifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				updateStatus("Rejected",data);
				//Select Source from drop down as "002 : Source1"
				/*CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
				String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
				Thread.sleep(1000);
				//Click on Update
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");*/
				//Verification
				String updated_LifecysleState = driver.findElement(RO_UpdateLifecycleState).getText();
				log.info("Initial State of Source   :"+initial_LifecycleState);
				log.info("Updated State of Source   :"+updated_LifecysleState);
				//log.info("State return from method  :"+valULCSAfterChange);
				Assert.assertEquals(updated_LifecysleState,"Rejected");
				log.info("General Attribute is Updatable .....");
			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable !!!");
			}	
			else
			{
				//log.info("For this Either Product Lifecycle State is :"+plcState+" or General Attribute Update is Not Applicable");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Global Sourcing:Read_View
	public static boolean verifyGlobalSourcingReadView(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGlobalSourcing).size() != 0){
					log.info("Code inside ---- GlobalSourcingRead_View---");
					//new Select(driver.findElement(selectSource)).selectByVisibleText("002 : Source1");
					//driver.findElement(sourcingTablink).click();
					String GSLabel=driver.findElement(labelGlobalSourcing).getText();
					log.info(" Global Sourcing: Attribute label Value is  :"+GSLabel);
					Assert.assertEquals(GSLabel.trim(), "Global Sourcing:");
					log.info("Global Sourcing: label is Present");
				}else{
					fail=true;
					log.error("Global Sourcing: label is Absent");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGlobalSourcing).size() != 0){
					//new Select(driver.findElement(selectSource)).selectByVisibleText("002 : Source1");
					//driver.findElement(sourcingTablink).click();
					fail=true;
					log.error("Global Sourcing: label is Present");
				}else{
					log.info("Global Sourcing: label is Absent");
				}
			}
			else
			{
				log.info("For this Global Sourcing: is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGlobalSourcingReadView()", e);
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Global Sourcings://Update
	public static boolean verifyGlobalSourcingUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			// Checking the value for Product Lifecycle State
			//String plcState = driver.findElement(productLifecycleState).getText();
			//log.info("Product Life Cycle State for :"+data[5]+ "is :"+plcState);
			//log.info("We are in process to Update Sourcing Configuration General Attribute if Product Lifecycle State is In Work");
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update

				log.info("Code is in side---------verifyGlobalSourcingUpdate");
				//Click on Action as 'Update Source To Season'
				CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
				//Update Strategic Sourcing Category
				CommonFunctions.selectFromDropDownByVisibleText(ddStrategicSC, data[6]);
				// Update Strategic Sourcing Sub Category 	
				CommonFunctions.selectFromDropDownByVisibleText(ddStrategicSSubCat, data[11]);
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
				CommonFunctions.waitForVisibilityOfElement(roStrategicSourcingCategory);
				Assert.assertEquals(driver.findElement(roStrategicSourcingCategory).getText().trim(), data[6]);
				log.info("Value matched for Strategic Sourcing Category: "+data[6]);
				Assert.assertEquals(driver.findElement(roStrategicSourcingSubCategory).getText().trim(), data[11]);
				log.info("Value matched for Strategic Sourcing Sub Category: "+data[11]);
				log.info("Global Sourcing is Updatable .....");

			}
			else if(data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(sourcingAction).getText();
				//log.info(strPrimary);
				boolean val= findString(strPrimary.trim(), data[8]);
				Assert.assertFalse(val);
				log.info("Global Sourcing is Not Updatable !!!!");
			}	
			else
			{
				log.info("Global Sourcing Update is Not Applicable");
			}
		}catch(Exception e){
			log.error("Exception in verifyGlobalSourcingUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean verifySCARead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				Assert.assertEquals(driver.findElements(scApprovers).size(), 1);
				log.info("Sourcing Config Approvers label is present");
				Assert.assertEquals(driver.findElements(lblManager).size(), 1);
				log.info("Manager label is present under Sourcing Config Approvers heading ");
				Assert.assertEquals(driver.findElements(lblSourcingLead).size(), 1);
				log.info("SourcingLead label is present under Sourcing Config Approvers heading ");
				Assert.assertEquals(driver.findElements(lblSourcingHead).size(), 1);
				log.info("SourcingHead label is present under Sourcing Config Approvers heading ");
				Assert.assertEquals(driver.findElements(lblManagement).size(), 1);
				log.info("Management label is present under Sourcing Config Approvers heading ");
			}
			else if(data[4].equalsIgnoreCase("No")){
				//Only for vendor user
			}
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySCARead_View()", e);
			return false;
		}
		return true;
	}


	//Function consist scenario :  Sourcing Config Approvers://Update
	public static boolean verifySCAUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			if(data[4].equalsIgnoreCase("Yes")) {
				//Click on Action as 'Update Source'
				CommonFunctions.selectFromDropDownByVisibleText(sourcingAction, data[8]);
				CommonFunctions.selectFromDropDownByIndex(ddManager, 1);
				String strManager =new Select(driver.findElement(ddManager)).getFirstSelectedOption().getText();
				CommonFunctions.clickButtonOrLink(btnSave, "btn", "Button");
				Assert.assertEquals(driver.findElement(roManager).getText().trim(), strManager);
				log.info(" Sourcing Config Approvers is updatable");
			}
			else if(data[4].equalsIgnoreCase("No")){

				
			}	
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySCAUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean verifyPHisRead_View(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				
			}
			else if(data[4].equalsIgnoreCase("No")){
				
			}
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesReadView()", e);
			return false;
		}
		return true;
	}


	//Function consist scenario : General Attributes://Update
	public static boolean verifyPHisUpdate(String[] data) throws Exception{
		try{
			nevigateSourcingTab(data);
			CommonFunctions.selectFromDropDownByVisibleText(selectSource,strCreate);
			
			if(data[4].equalsIgnoreCase("Yes")) {// && plcState.equalsIgnoreCase("In Work") || plcState.equalsIgnoreCase("Under Review")){//Update

				
			}
			else if(data[4].equalsIgnoreCase("No")){

				
			}	
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}

	public static Boolean nevigateSourcingTab(String[] data) throws Exception{
		try{
			if(driver.findElements(sourcingDetails).size()==0) {
				//Search for Assortment / Solid Product Type
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[9]);
				//CommonFunctions.selectFromDropDownByVisibleText(selectSource, data[10]);
			//	strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in nevigateSourcingTab()", e);
			return false;
		}
	}

	public static boolean AddSourcingConfiguration(String[] data) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.selectFromDropDownByVisibleText(actionDD, "Add Sourcing Configuration");

			//Supplier Selection
			CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
			//CommonFunctions.switchToChildWindow();
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[7]+"')]/preceding::td[1]/a"), "Supplier selected");
			driver.switchTo().window(parent);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			//	CommonFunctions.selectFromDropDownByVisibleText(manager, data[23]);
			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(CostsheetExternalProduct.sourcingLead, 1);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(CostsheetExternalProduct.sourcingHead, 1);
			// Management 
			//	CommonFunctions.selectFromDropDownByVisibleText(management, data[26]);
			//click on Create
			CommonFunctions.clickButtonOrLink(SourcingConfig.CreateSourcebtn, "btn", "Create Source");
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in AddSourcingConfiguration()", e);
			return false;
		}
	}

	public static boolean findString (String data1, String regex){
		log.info("Calling Find String");
		boolean flag = false;
		try {
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(data1);
			if (matcher.find())
			{
				flag = true;
				log.info("Start index: " + matcher.start());
				//System.out.print("Start index: " + matcher.start());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error in Handling Findstring  :"+e);
		}
		return flag;
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
