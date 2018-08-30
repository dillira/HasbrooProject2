package com.hasbro.security;
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
import org.openqa.selenium.Keys;
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
public class Season extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;

	public static By seasonLink = By.linkText("Season");
	public static By newLink = By.xpath("//a[text()='New']");
	public static By productType = By.xpath("//a[contains(text(),'Product Type')]");
	public static By retailItem = By.xpath("//a[text()='Retail Item']");
	public static By seasonYear = By.id("ptc_str_1");
	public static By createBtn = By.xpath("//a[text()='Create']");
	public static By findSeasonPage = By.xpath("//span[contains(text(),'Find: Season')]");
	public static By headingSeason = By.xpath("//td[contains(text(),'Details')]");
	public static By seasonAction = By.xpath("//td[contains(text(),'Details')]//following::select[1]");
	public static By headingUpdateSeason = By.xpath("//td[contains(text(),'Update Season')]");
	public static By RO_year = By.id("year");
	public static By duplicateYearErrorMasg = By.xpath("//td[contains(text(),'Value for Name must be unique.')]");

	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSeason(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Season Security");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			//System.out.println("col4 :" + data[4]);
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
			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_Season(data); }
			if(data[3].equalsIgnoreCase("Update"))
			{ verifyUpdate(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_Season(data); }
			//Read view verification
			if(data[3].equalsIgnoreCase("GlobalAttributesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GlobalAttributesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Season(data); }
			/***************************************************/
			//Season - Group - Region
			
			if(data[3].equalsIgnoreCase("SGRCreate"))
			{ SGRCreate_Season(data); }
			//Read view verification
			if(data[3].equalsIgnoreCase("SGRGlobalAttributesRead_View"))
			{  //verifyGeneralAttributesReadViewSGR(data); 
			}
			//Update Verification
			if(data[3].equalsIgnoreCase("SGRGlobalAttributesUpdate"))
			{  //verifyGeneralAttributesUpdateSGR(data); 
			}
			//Delete Product
			if(data[3].equalsIgnoreCase("SGRDelete"))
			{ delete_Season(data); }
			/***************************************************/
			//Season - Group - Business Unit
			
			if(data[3].equalsIgnoreCase("SGBCreate"))
		//	{ Create_Season(data); }
			//Read view verification
			if(data[3].equalsIgnoreCase("SGBGlobalAttributesRead_View"))
		//	{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("SGBGlobalAttributesUpdate"))
		//	{  verifyGeneralAttributesUpdate(data); }
			//Delete Product
			if(data[3].equalsIgnoreCase("SGBDelete"))
			{ delete_Season(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create Season
	/*
	 * Create_Season is out of scope as already required seasons are created
	 */
	public static boolean Create_Season(String[] data) throws Exception{
		try{
			if(!data[0].contains("vuser")){
				// Switch to Sidebar Frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Season
				clickSeasonLink(data);			

				//	Boolean strNew =driver.findElement(newLink).isDisplayed();
				if(data[4].equalsIgnoreCase("Yes")){
					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(20, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(productType));
					CommonFunctions.clickButtonOrLink(productType, "link", "Product Type");
					driver.switchTo().defaultContent();
					//Switch window
					Set set = driver.getWindowHandles();
					Iterator iter = set.iterator();
					String parent = (java.lang.String) iter.next();
					String child = (java.lang.String) iter.next();
					driver.switchTo().window(child);
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[5]+"') and @class='LABEL']"), "link", "Product Type");
					driver.switchTo().window(parent);
					driver.switchTo().frame("contentframe");
					System.out.println(data[6]);
					//Select Year
					CommonFunctions.enterTextInTextbox(seasonYear, data[6]);
					//	CommonFunctions.selectFromDropDownByValue(seasonYear, data[6]);
					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					if(driver.findElements(duplicateYearErrorMasg).size()==0){
						String strYear = driver.findElement(RO_year).getText();
						Assert.assertEquals(strYear,data[6]);
					}
					else{
						log.info(data[6]+" Year already created.");
					}			
				}
				else if(data[4].equalsIgnoreCase("No")){
					Assert.assertEquals(driver.findElements(newLink).size(), 0);
				}
				else{
					log.info("Create New is not applicable(NA)");
				}
			}
			else{
				Assert.assertEquals(driver.findElements(seasonLink).size(), 0);
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in Create_Season()", e);
			return false;
		}
		return true;
	}

	public static boolean verifyUpdate(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Season
				clickSeasonLink(data);
				search_Season(data);
				if(data[4].equalsIgnoreCase("Yes")){//Update
					/*Select select = new Select(driver.findElement(seasonAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
				//	dropDownOptionVerification
				Assert.assertTrue(bVal);*/
					CommonFunctions.enterTextInTextbox(seasonAction, data[9]);
					CommonFunctions.waitForVisibilityOfElement(headingUpdateSeason);
					Boolean byear= driver.findElement(seasonYear).isEnabled();
					Assert.assertTrue(byear);
				}
				else if(data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(seasonAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
					//	dropDownOptionVerification
					if(!bVal)
						Assert.assertFalse(bVal);
					else{
						CommonFunctions.enterTextInTextbox(seasonAction, data[9]);
						//	System.out.println(driver.findElements(ROProductName).size());
						//	Assert.assertEquals(driver.findElements(ROProductName).size(), 1, "General Attributes Not Editable"); 
					}
				}	
				else
				{
					log.info("For this General Attributes is not applicable(NA)");
				}}
			else{
				Assert.assertEquals(driver.findElements(seasonLink).size(), 0);
			}	
		}catch(Exception e){ fail=true;
		log.error("Exception in verifyGeneralAttributesUpdate()", e);
		return false;
		}
		return true;
	}

	
	//Prerequisite: Create Season
		/*
		 * Create_Season is out of scope as already required seasons are created
		 */
		public static boolean SGRCreate_Season(String[] data) throws Exception{
			try{
				if(!data[0].contains("vuser")){
					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
					//Click on Season
					clickSeasonLink(data);			

					//	Boolean strNew =driver.findElement(newLink).isDisplayed();
					if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
						//Click on new Link
						CommonFunctions.clickButtonOrLink(newLink, "link", "New");
						wait = new WebDriverWait(driver, 10);
						wait.withTimeout(20, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(productType));
						CommonFunctions.clickButtonOrLink(productType, "link", "Product Type");
						driver.switchTo().defaultContent();
						//Switch window
						Set set = driver.getWindowHandles();
						Iterator iter = set.iterator();
						String parent = (java.lang.String) iter.next();
						String child = (java.lang.String) iter.next();
						driver.switchTo().window(child);
						CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[5]+"') and @class='LABEL']"), "link", "Product Type");
						driver.switchTo().window(parent);
						driver.switchTo().frame("contentframe");
						System.out.println(data[6]);
						//Select Year
						CommonFunctions.enterTextInTextbox(seasonYear, data[6]);
						//	CommonFunctions.selectFromDropDownByValue(seasonYear, data[6]);
						//click on Create
						CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
						if(driver.findElements(duplicateYearErrorMasg).size()==0){
							String strYear = driver.findElement(RO_year).getText();
							Assert.assertEquals(strYear,data[6]);
						}
						else{
							log.info(data[6]+" Year already created.");
						}			
					}
					else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
						Assert.assertEquals(driver.findElements(newLink).size(), 0);
					}
					else{
						log.info("Create New is not applicable(NA)");
					}
				}
				else{
					Assert.assertEquals(driver.findElements(seasonLink).size(), 0);
				}
			}catch(Exception e){ 
				fail=true;
				log.error("Exception in Create_Season()", e);
				return false;
			}
			return true;
		}
	//Function consist scenario : Click on season link
	public static boolean clickSeasonLink(String[] data) throws Exception{
		try{
			//Click on Season link
			CommonFunctions.clickButtonOrLink(seasonLink, "link", "Season");
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(findSeasonPage);
		}catch(Exception e){ fail=true;
		log.error("Exception in clickSeasonLink()", e);
		return false;
		}
		return true;
	}

	public static boolean SetState_Season(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Season
				clickSeasonLink(data);
				search_Season(data);
				Thread.sleep(2000);
				String strRO_LCStateBeforeChange = driver.findElement(Supplier.RO_LCState).getText();
				Select select = new Select(driver.findElement(seasonAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[3],options);
				//	dropDownOptionVerification
				;

				if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
					CommonFunctions.clickButtonOrLink(seasonAction, "drop down", "Action");
					CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Details')]//following::select[1]/option[contains(text(),'"+data[9] +"')]"), "drop down", "Action");
					//driver.findElement(specificationAction).sendKeys(Keys.ENTER);
					String valULCSAfterChange=Season_selectUpdateLifecycleState(data);
					Thread.sleep(1000);
					//Click on Update
					CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
					//verification
					String strRO_LCState = driver.findElement(Supplier.RO_LCState).getText();
					System.out.println("valULCSAfterChange: "+valULCSAfterChange);
					Assert.assertEquals(strRO_LCState, "abd");
				}
				else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
					if(!bVal)
						Assert.assertFalse(bVal);
					else{
						CommonFunctions.clickButtonOrLink(seasonAction, "drop down", "Action");
						CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Details')]//following::select[1]/option[contains(text(),'"+data[9] +"')]"), "drop down", "Action");
						//driver.findElement(specificationAction).sendKeys(Keys.ENTER);
						String valULCSAfterChange=Season_selectUpdateLifecycleState(data);
						Thread.sleep(1000);
						//Click on Update
						CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
						//verification
						String strRO_LCState = driver.findElement(Supplier.RO_LCState).getText();
						System.out.println("valULCSAfterChange: "+valULCSAfterChange);
						Assert.assertNotEquals(strRO_LCStateBeforeChange, valULCSAfterChange);
					}
				}
				else
					log.info("SetState or chageState is not applicable(NA)");
			}
			else{
				Assert.assertEquals(driver.findElements(seasonLink).size(), 0);
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in SetState_Season()", e);
			return false;
		}
		return true;
	}


	public static boolean delete_Season(String[] data) throws Exception{
		try{
			//	Create_Season(data);
			driver.navigate().refresh();
			if(!data[0].contains("vuser")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Season
				clickSeasonLink(data);
				search_Season(data);
				if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
					CommonFunctions.clickButtonOrLink(seasonAction, "btn", "Action dropdown");
					CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'Details')]//following::select[1]/option[contains(text(),'"+data[9] +"')]"), "drop down", "Action");
					CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
					driver.findElement(Product.deleteButton).click();
					Thread.sleep(2000);
					Boolean bAlert = CommonFunctions.AlertPopUpPresent();
					Assert.assertTrue(bAlert);
				}
				else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(seasonAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[3],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal);
				}
				else
					log.info("Delete is not applicable(NA)");
			}
			else{
				Assert.assertEquals(driver.findElements(seasonLink).size(), 0);
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in delete_Season()", e);
		return false;
		}
		return true;
	}


	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Season
				clickSeasonLink(data);
				search_Season(data);

				if(data[3].contains("GlobalAttributesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
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
				else if(data[3].contains("GlobalAttributesRead_View")&& data[4].equalsIgnoreCase("No")){
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
			}
			else{
				Assert.assertEquals(driver.findElements(seasonLink).size(), 0);
			}	
		}catch(Exception e){ fail=true;
		log.error("Exception in verifyGeneralAttributesReadView()", e);
		return false;
		}
		return true;
	}
	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser")){
				// Switch to Sidebar Frame
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//Click on Season
				clickSeasonLink(data);
				search_Season(data);
				if(data[3].contains("GlobalAttributesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
					/*Select select = new Select(driver.findElement(seasonAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
				//	dropDownOptionVerification
				Assert.assertTrue(bVal);*/
					CommonFunctions.enterTextInTextbox(seasonAction, data[9]);
					CommonFunctions.waitForVisibilityOfElement(headingUpdateSeason);
					Boolean byear= driver.findElement(seasonYear).isEnabled();
					Assert.assertTrue(byear);
				}
				else if(data[3].contains("GlobalAttributesUpdate")&& data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(seasonAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
					//	dropDownOptionVerification
					if(!bVal)
						Assert.assertFalse(bVal);
					else{
						CommonFunctions.enterTextInTextbox(seasonAction, data[9]);
						//	System.out.println(driver.findElements(ROProductName).size());
						//	Assert.assertEquals(driver.findElements(ROProductName).size(), 1, "General Attributes Not Editable"); 
					}
				}	
				else
				{
					log.info("For this General Attributes is not applicable(NA)");
				}}
			else{
				Assert.assertEquals(driver.findElements(seasonLink).size(), 0);
			}	
		}catch(Exception e){ fail=true;
		log.error("Exception in verifyGeneralAttributesUpdate()", e);
		return false;
		}
		return true;
	}

	//Function consist scenario : searchColor
	public static boolean search_Season(String[] data) throws Exception{
		try{

			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(findSeasonPage));
			//Add name
			CommonFunctions.enterTextInTextbox(Color.inputBoxName, data[6]);
			//Click on Search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingSeason));

		}catch(Exception e){ fail=true;
		log.error("Exception in search_Season()", e);
		return false;
		}
		return true;
	}

	//This funcion is to select Update Lifecycle State	
	public static String Season_selectUpdateLifecycleState(String[] data) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState,"Complete");
			}
			else if(valULCS.contains("Complete")){
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
			}
			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in Season_selectUpdateLifecycleState()", e);
		}
		return valULCSAfterChange;
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
		}catch(Exception e){ fail=true;
		SeleniumDriver.log.error("Exception in dropDownOptionVerificationActions()", e);
		return false;
		}
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
