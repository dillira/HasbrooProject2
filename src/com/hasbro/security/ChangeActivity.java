package com.hasbro.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

/**
 * @author sourabh.singh
 *
 */
public class ChangeActivity extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;

	//Variable required before Create Page
	public static By libraryLink = By.id("librariesContentIcon");
	public static By rCALink = By.linkText("Retail Change Activity");
	public static By rCAHeadning = By.xpath("//span[contains(text(),'Find: Retail Change Activity')]");
	public static By newLink = By.linkText("New");
	public static By newChangeActHeading = By.xpath("//td[contains(text(),'Create New Change Activity')]");
	//Variable Required for Create RCA
	public static By rCAName= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By seasonCreated= By.xpath("//td[contains(text(),'*Season (Year) Created')]//following::select[1]");
	public static By season17= By.xpath("//*[@id='ptc_str_2Options']/option[1]");
	public static By season18= By.xpath("//*[@id='ptc_str_2Options']/option[2]");
	public static By seasonADD= By.xpath("//*[@class='LABEL']/a[text()='Add']");
	public static By crateRCA= By.xpath("//a[text()='Create']");
	public static By changeActDetails = By.xpath("//td[contains(text(),'Change Activity Details:')]");
	//Search RCA
	public static By rCAAction = By.xpath("//select[contains(@onchange,'evalList(this)')]");
	public static By inputBoxName = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By btnSearch = By.id("SearchButton2");
	public static By rCAIdentification = By.xpath("//td[contains(text(),'Change Activity Details:')]");

	// Read and Update Attribute
	public static By labelGeneralAttribute = By.xpath("//td[contains(text(),'General Attributes')]");
	public static By labelChangeActivity = By.xpath("//td[contains(text(),'Change Activity Identification')]");

	// Set LifeCycle Variables
	public static By RO_RCAUpdateLifecycleState = By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");

	public static By deleteObject= By.xpath("//td[contains(text(),'Delete Object')]");
	public static By deleteButton= By.xpath("//a[text()='Delete']");

	public static By linkUpdate= By.xpath("//a[text()='Update']");


	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcRCA(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Security Retail Change Activity");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			log.info("User Name     :" + data[0]);
			log.info("Att  Type     :" + data[2]);
			log.info("Verification  :" + data[3]);
			log.info("Action        :" + data[4]);
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

			//Create Material From Libraries
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_RCA(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_RCA(data); }
			//Read view verification For General Attributes
			if(data[3].equalsIgnoreCase("GeneralAttirbutesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification For General Attributes
			if(data[3].equalsIgnoreCase("GeneralAttirbutesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_RCA(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create Product
	public static boolean Create_RCA(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			//Thread.sleep(1000);
			if(!data[0].contains("vuser")){
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(libraryLink, "Link", "Library Link");
				//Click on Retail Change Activity Link
				driver.findElement(rCALink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//wait = new WebDriverWait(driver, 10);
				//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(rCAHeadning));
				//Checking and Validating New Button on Material Search Page
				boolean newType=false;
				Packaging.isPresent(newLink, newType);
				if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
					//Click on new
					CommonFunctions.clickButtonOrLink(newLink, "link", "New Link");
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(newChangeActHeading));
					Date date = new Date();
					//Send Name in *Name Text Box
					CommonFunctions.enterTextInTextbox(rCAName,data[5]+date.getTime());
					//Select 2018 for  *Season (Year) Created
					CommonFunctions.selectFromDropDownByVisibleText(seasonCreated, data[6]);
					//Select 2017 for  *Seasons (Years) Impacted
					CommonFunctions.clickButtonOrLink(season17, "link", "season17 is Selected!!!");
					//Click on Add button to Add for MultiSelect
					CommonFunctions.clickButtonOrLink(seasonADD, "link", "Add is Click");
					//Select 2018 for  *Seasons (Years) Impacted
					CommonFunctions.clickButtonOrLink(season18, "link", "season18 is Selected!!!");
					//Click on Add button to Add for MultiSelect
					CommonFunctions.clickButtonOrLink(seasonADD, "link", "Add is Click");
					//click on Create Button
					CommonFunctions.clickButtonOrLink(crateRCA, "btn", "Create Retail Change Activity");
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(changeActDetails));
					log.info("User : "+data[0]+ "  have Access to Create Retail Change Activity, and Created Sucessfully ..");
				}
				else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
					Assert.assertFalse(newType, "New Link not available");
					log.info("User : "+data[0]+ "  don't have Access to Create Retail Change Activity !!");
				}
				else{
					log.info("Create is not applicable(NA)");
				}}
			else{
				Assert.assertEquals(driver.findElements(rCALink).size(), 0);
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_RCA()", e);
			return false;
		}
		return true;
	}


	public static boolean SetState_RCA(String[] data) throws Exception{
		try{
			if(!data[0].contains("vuser")){
				searchRCA(data);
				//Current LifeCycle State 
				String initial_LifecycleState = driver.findElement(RO_RCAUpdateLifecycleState).getText();
				log.info("Initial LIfeCycle State of Material   :"+initial_LifecycleState);
				// Select Change State:  Sourcing Configuration from Action dropdown
				String str = driver.findElement(rCAAction).getText();
				//log.info("STR is---------"+str);
				CommonFunctions.selectFromDropDownByVisibleText(rCAAction, data[8]);
				String valULCSAfterChange=selectUpdateLifecycleState(data);
				Thread.sleep(1000);
				//Click on Update
				CommonFunctions.clickButtonOrLink(linkUpdate, "link", "Update");
				//Verification
				String updated_LifecysleState = driver.findElement(RO_RCAUpdateLifecycleState).getText();
				log.info("Initial LIfeCycle State of Source   :"+initial_LifecycleState);
				log.info("Updated LIfeCycle State of Source   :"+updated_LifecysleState);
				log.info("Lifecycle State return from method  :"+valULCSAfterChange);

				if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
					Assert.assertEquals(updated_LifecysleState, valULCSAfterChange);
					log.info("User : "+data[0]+ "  has the permission and able to change the Lifecycle State..");
				}
				else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
					Assert.assertEquals(updated_LifecysleState, initial_LifecycleState);
					log.info("User : "+data[0]+ "  don't have the permission hence not able to change the Lifecycle State !!!");
				}
				else
					log.info("SetState or chageState is not applicable(NA)");
			}
			else
				Assert.assertEquals(driver.findElements(rCALink).size(), 0);
		}catch(Exception e){
			fail=true;
			log.error("Exception in SetState_RCA()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_RCA(String[] data) throws Exception{
		try{
			if(!data[0].contains("vuser")){
				searchRCA(data);
				if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
					CommonFunctions.selectFromDropDownByVisibleText(rCAAction, data[8]);
					//Wait for Delete Object Heading
					CommonFunctions.waitForVisibilityOfElement(deleteObject);
					//Click on delete button
					CommonFunctions.clickButtonOrLink(deleteButton,"btn", "Delete");
					//Accept AletPopup
					log.info("about to delete the Retail CHange Activity-----");
					Thread.sleep(1000);
					//Accept AletPopup
					String msg= driver.switchTo().alert().getText(); 
					System.out.println("msg is -------"+msg);
					driver.switchTo().alert().dismiss();
					//driver.switchTo().alert().accept();
					Assert.assertEquals(msg, "This action will completely delete this object from the system.  Are you sure you want to do this?");
					log.info("User : "+data[0]+ " has the permission to delete Retail Change Activity ...");
					/*	
				//CommonFunctions.handleAlertPopUp();
				String deleteMess =driver.findElement(deleteSucessful).getText();
				Assert.assertEquals(deleteMess,"Delete Successful");*/


				}
				else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
					String strPrimary =driver.findElement(rCAAction).getText();
					boolean val= SourcingConfig.findString(strPrimary.trim(), data[8]);
					Assert.assertFalse(val);
					log.info("User : "+data[0]+ "  don't have the permission to Delete the Retail Change Activity !!!");
				}
				else
					log.info("Delete is not applicable(NA)");
			}
			else
				Assert.assertEquals(driver.findElements(rCALink).size(), 0);	
		}catch(Exception e){
			log.error("Exception in delete()", e);
			return false;
		}
		return true;
	}
	//This funcion is to select Update Lifecycle State	
	public static String selectUpdateLifecycleState(String[] productData) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			//	System.out.println("valULCS: "+valULCS);
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Under Review");
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Under Review");
			}
			else if(valULCS.contains("Rework")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Resolved");
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Resolved");
			}
			else if(valULCS.contains("Resolved")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Under Review");
				//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Under Review");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Resolved");
				//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Resolved");
			}
			else if(valULCS.contains("Implementation")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Under Review");
				//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Under Review");
			}
			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			log.error("Exception in selectUpdateLifecycleState()", e);
			//	return false;
		}
		return valULCSAfterChange;
	}


	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			if(!data[0].contains("vuser")){
				searchRCA(data);
				if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
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
				else if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("No")){
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
				}}
			else
				Assert.assertEquals(driver.findElements(rCALink).size(), 0);
		}catch(Exception e){
			log.error("Exception in verifyGeneralAttributesReadView()", e);
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
	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			if(!data[0].contains("vuser")){
				if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
					log.info("Code is in side Update General Attribute !!");
					String strPrimary =driver.findElement(rCAAction).getText();
					//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailsTablink));
					Assert.assertEquals(SourcingConfig.findString(strPrimary.trim(), data[8]), true);
					log.info("General Attribute is Updatable .....");

				}
				else if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("No")){
					String strPrimary =driver.findElement(rCAAction).getText();
					boolean val= SourcingConfig.findString(strPrimary.trim(), data[8]);
					Assert.assertFalse(val);
					log.info("General Attribute is Not Updatable !!!");
				}	
				else
				{
					log.info("For this General Attributes is not applicable(NA)");
				}}
			else
				Assert.assertEquals(driver.findElements(rCALink).size(), 0);
		}catch(Exception e){
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}


	//Function consist scenario : searchMaterial
	public static boolean searchRCA(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(libraryLink, "Link", "Library Link");
			//Click on Color link
			driver.findElement(rCALink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(rCAHeadning));
			//Add name
			CommonFunctions.enterTextInTextbox(inputBoxName, data[7]);
			//Click on Search
			CommonFunctions.clickButtonOrLink(btnSearch, "Btn", "Search");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(rCAIdentification));

		}catch(Exception e){
			log.error("Exception in searchRCA()", e);
			return false;
		}
		return true;
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
