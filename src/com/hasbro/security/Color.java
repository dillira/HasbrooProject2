package com.hasbro.security;
import java.util.List;
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


public class Color extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	//	By libraryLink = By.id("librariesContentIcon");
	public static By colorHeadning = By.xpath("//span[contains(text(),'Find: Color')]");
	public static By colorLink = By.linkText("Color");
	public static By chooseaType = By.xpath("//td[contains(text(),'Choose a Type')]");
	//Create page
	public static By suffixORname = By.id("ptc_str_1");   //td[contains(text(),'*Suffix')]//following::input[1]
	public static By red = By.name("redValue");
	public static By green = By.name("grnValue");
	public static By blue = By.name("bluValue");
	public static By definition = By.xpath("//td[contains(text(),'Definition')]//following::input[1]");
	public static By numberofLanguagesonPackage = By.xpath("//td[contains(text(),'Number of Languages on Package')]//following::select[1]");
	public static By languagesonPack  = By.xpath("//td[contains(text(),'*Languages on Pack')]//following::select[1]");
	public static By region = By.xpath("//td[contains(text(),'Region')]//following::select[1]");
	public static By shippingMethod = By.xpath("//td[contains(text(),'Shipping Method')]//following::select[1]");
	public static By suitableFor = By.xpath("//td[contains(text(),'Suitable For')]//following::select[1]");
	public static By deactivated = By.xpath("//td[contains(text(),'Deactivated')]//following::select[1]");
	public static By casepackChange= By.xpath("//td[contains(text(),'Casepack Change')]//following::select[1]");
	public static By majorPackageChange = By.xpath("//td[contains(text(),'Major Package Change')]//following::select[1]");
	public static By masterCartonChange = By.xpath("//td[contains(text(),'Master Carton Change')]//following::select[1]");
	public static By exclusiveCustomer  = By.xpath("//td[contains(text(),'Exclusive Customer')]//following::select[1]");
	public static By specialOnlinePackaging  = By.xpath("//td[contains(text(),'Special Online Packaging')]//following::select[1]");
	public static By Overlabeled = By.xpath("//td[contains(text(),'Overlabeled')]//following::select[1]");
	public static By overlabelRemoval= By.xpath("//td[contains(text(),'Overlabel Removal')]//following::select[1]");
	public static By speech_Content= By.xpath("//td[contains(text(),'Speech/Content')]//following::select[1]");
	public static By componenttoSolid  = By.xpath("//td[contains(text(),'Component to Solid')]//following::select[1]");
	public static By pallet_Display_PDQ  = By.xpath("//td[contains(text(),'Pallet/Display/PDQ')]//following::select[1]");
	public static By specialAction = By.xpath("//td[contains(text(),'Special Action')]//following::select[1]");
	public static By inputBoxName = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By btnSearch = By.id("SearchButton2");
	public static By colorIdentification = By.xpath("//td[contains(text(),'Color Identification')]");
	public static By colorAction = By.xpath("//select[contains(@onchange,'evalList(this)')]");
	public static By RO_UpdateLifecycleState = By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By deleteSucessful = By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td");
	public static By expandPlusLib = By.xpath("//img[@id='librariesContentIcon' and contains(@src,'expand_tree.png')]");
	public static By collapsePlusLib = By.xpath("//img[@id='librariesContentIcon' and contains(@src,'collapse_tree.png')]");

	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	static String strColorName;
	static int libExp;
	static int libCollapse;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcColor(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Color Security");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			System.out.println("col0 :" + data[0]);
			System.out.println("col2 :" + data[2]);
			System.out.println("col3 :" + data[3]);
			System.out.println("col4 :" + data[4]);
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
			//	wait = new WebDriverWait(driver, 10);
			//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchDropDownSelect")));

			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_Color(data); }
			//	Read view verification
			if(data[3].equalsIgnoreCase("Read_View"))
			{	verifyReadView(data);}
			//	Update Verification
			if(data[3].equalsIgnoreCase("Update"))
			{	verifyUpdate(data);}
			//	SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_Color(data); }
			//	Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Color(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create Product
	public static boolean Create_Color(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			driver.switchTo().frame("sidebarframe");
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

				//Click on Color link
				driver.findElement(colorLink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(colorHeadning));
				//	boolean newBool = driver.findElement(Product.newLink).isDisplayed();
				if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
					//Click on new
					CommonFunctions.clickButtonOrLink(Product.newLink, "link", "New Link");
					//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(chooseaType));
					//Click on color type
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']"), "link", "Color Type");
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']"));
					//	Date date = new Date();
					strColorName = CommonFunctions.getRandomString(5);
					CommonFunctions.enterTextInTextbox(suffixORname,strColorName);
					CommonFunctions.enterTextInTextbox(red,data[7]);
					CommonFunctions.enterTextInTextbox(green,data[8]);
					CommonFunctions.enterTextInTextbox(blue,data[9]);
					if(data[2].contains("Suffix")){
						CommonFunctions.enterTextInTextbox(definition,data[10]);
						CommonFunctions.enterTextInTextbox(numberofLanguagesonPackage,data[11]);
						CommonFunctions.enterTextInTextbox(languagesonPack,data[12]);
						CommonFunctions.enterTextInTextbox(region,data[13]);
						CommonFunctions.enterTextInTextbox(shippingMethod,data[14]);
						CommonFunctions.enterTextInTextbox(suitableFor,data[15]);
						CommonFunctions.enterTextInTextbox(deactivated,data[16]);
						CommonFunctions.enterTextInTextbox(casepackChange,data[17]);
						CommonFunctions.enterTextInTextbox(majorPackageChange,data[18]);
						CommonFunctions.enterTextInTextbox(masterCartonChange,data[19]);
						CommonFunctions.enterTextInTextbox(exclusiveCustomer,data[20]);
						CommonFunctions.enterTextInTextbox(specialOnlinePackaging,data[21]);
						CommonFunctions.enterTextInTextbox(Overlabeled,data[22]);
						CommonFunctions.enterTextInTextbox(overlabelRemoval,data[23]);
						CommonFunctions.enterTextInTextbox(speech_Content,data[24]);
						CommonFunctions.enterTextInTextbox(componenttoSolid,data[25]);
						CommonFunctions.enterTextInTextbox(pallet_Display_PDQ,data[26]);
						CommonFunctions.enterTextInTextbox(specialAction,data[27]);
					}
					//	String suffix = driver.findElement(suffixORname).getText();
					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				}
				else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
					//Assert.assertFalse(newBool, "New Link not available");
					Assert.assertEquals(driver.findElements(Product.newLink).size(), 0, "Color is not Editable"); 
				}
				else{
					log.info("Create is not applicable(NA)");
				}
			}	
			else{
				Assert.assertEquals(driver.findElements(colorLink).size(), 0);
			}
			//}	
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in Create_Color()", e);
			return false;
		}
		return true;
	}

	public static boolean SetState_Color(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			searchColor(data);
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				String textRO_UpdateLifecycleStateBefore = driver.findElement(RO_UpdateLifecycleState).getText();
				Select select = new Select(driver.findElement(colorAction));
				List<WebElement> options = select.getOptions();
				System.out.println(options);
				boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[29],options);
				//	dropDownOptionVerification

				if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
					CommonFunctions.selectFromDropDownByVisibleText(colorAction, data[29]);
					Thread.sleep(1000);
					//verification
					String valULCSAfterChange=selectUpdateLifecycleState(data);
					//Click on Update
					CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
					//Verification
					String textRO_UpdateLifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
					Assert.assertEquals(textRO_UpdateLifecycleState, valULCSAfterChange);
				}
				else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
					if(!bVal)
						Assert.assertFalse(bVal);
					else{
						CommonFunctions.selectFromDropDownByVisibleText(colorAction, data[29]);
						Thread.sleep(1000);
						//verification
						String valULCSAfterChange=selectUpdateLifecycleState(data);
						//Click on Update
						CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
						//Verification
						String textRO_UpdateLifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
						Assert.assertNotEquals(textRO_UpdateLifecycleStateBefore, valULCSAfterChange);
					}
				}
				else
					log.info("SetState or chageState is not applicable(NA)");
			}	

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in SetState_Color()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_Color(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			searchColor(data);
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
					//	CommonFunctions.clickButtonOrLink(ddDetailsAction, "btn", "Action dropdown");
					//	driver.findElement(ddOptionDeleteProduct).click();
					//	CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
					//	CommonFunctions.enterTextInTextbox(colorAction, data[29]);
					CommonFunctions.selectFromDropDownByVisibleText(colorAction,data[29]);
					//Click on delete button
					CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
					Thread.sleep(1000);
					Boolean bAlert = CommonFunctions.AlertPopUpPresent();
					Assert.assertTrue(bAlert);
					//Accept AletPopup
					CommonFunctions.handleAlertPopUp();
				}
				else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(colorAction));
					List<WebElement> options = select.getOptions();
					System.out.println(options);
					boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[29],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal);
				}
				else
					log.info("Delete is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
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
				//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Released");
			}
			else if(valULCS.contains("Under Review")){
				//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Released");
			}
			else if(valULCS.contains("Released")){
				//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
			}
			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){ fail=true;
		log.error("Exception in selectUpdateLifecycleState()", e);
		//	return false;
		}
		return valULCSAfterChange;
	}




	//Function consist scenario : General Attributes verification:Read_View
	public static boolean verifyReadView(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			searchColor(data);
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				if(data[3].contains("Read_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						String generalAttLabel=driver.findElement(Product.labelGeneralAttri).getText();
						System.out.println(generalAttLabel);
						Assert.assertEquals(generalAttLabel, " General Attributes:");
						log.info("General Attributes label is Present");
					}else{
						log.error("General Attributes label is Absent");
						fail=true;
					}
				}
				else if(data[3].contains("Read_View")&& data[4].equalsIgnoreCase("No")){
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						System.out.println("General Attributes label is Present");
						log.error("General Attributes label is Present");
						fail=true;
					}else{
						log.info("General Attributes label is Absent");
					}
				}
				else
				{
					log.info("For this General Attributes is not applicable(NA)");
				}
			}

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyReadView()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : General Attributes verification:Update
	public static boolean verifyUpdate(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			//	driver.switchTo().defaultContent();
			//	driver.switchTo().frame("sidebarframe");

			// Click on Libraries
			//	CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//	}*/
			searchColor(data);
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				if(data[3].contains("Update")&& data[4].equalsIgnoreCase("Yes")){//Update
					Select select = new Select(driver.findElement(colorAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[29],options);
					//	dropDownOptionVerification
					Assert.assertTrue(bVal);
				}
				else if(data[3].contains("Update")&& data[4].equalsIgnoreCase("No")){
					log.info("Verifying update with NO scenario");
					Select select = new Select(driver.findElement(colorAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[29],options);
					//	dropDownOptionVerification
					if(!bVal)
						Assert.assertFalse(bVal);
					else{
						//Assert.fail("Update displayed for No scenarios");
						//fail=true;
							CommonFunctions.enterTextInTextbox(colorAction, data[29]);
							System.out.println(driver.findElements(red).size());
							Assert.assertEquals(driver.findElements(red).size(), 0, "Red input box is not Editable"); 
					}
				}
				else
				{
					log.info("For this General Attributes is not applicable(NA)");
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyUpdate()", e);
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

	//Function consist scenario : searchColor
	public static boolean searchColor(String[] data) throws Exception{
		try{
			//	driver.switchTo().frame("sidebarframe");
			//		CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			libExpCollapse();
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				//Click on Color link
				driver.findElement(colorLink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(colorHeadning));
				//Add name
				CommonFunctions.enterTextInTextbox(inputBoxName, data[28]);
				//Click on Search
				CommonFunctions.clickButtonOrLink(btnSearch, "Btn", "Search");
			}
			else
			{
				log.info("Vendor user do not have Color access");
			}
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(colorIdentification));

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in searchColor()", e);
			return false;
		}
		return true;
	}

	public static boolean libExpCollapse() throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");

			libExp=driver.findElements(expandPlusLib).size();
			libCollapse=driver.findElements(collapsePlusLib).size();
			if (libExp==1)
			{
				//Do nothing
			}
			else if(libCollapse==1){
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			}


		}catch(Exception e){ 
			fail=true;
			log.error("Exception in searchColor()", e);
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
