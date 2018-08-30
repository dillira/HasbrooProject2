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



public class PlaceHolderDevPlan extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;

	public static By assortmentSolidName = By.xpath("//td[contains(text(),'Assortment / Solid Name')]//following::input[1]");
	public static By assortmentSolidnoPLC  = By.xpath("//td[contains(text(),'Assortment / Solid #')]//following::input[1]");
	public static By classPLC = By.xpath("//td[contains(text(),'Class (PLC)')]//following::select[1]");
	public static By astSolidPLC  = By.xpath("//td[contains(text(),'Ast. / Solid')]//following::select[1]");
	public static By divisionPLC  = By.xpath("//td[contains(text(),'Division')]//following::select[1]");
	public static By familyBrandPLC  = By.xpath("//td[contains(text(),'Family Brand')]//following::select[1]");
	public static By brandPLC  = By.xpath("//td[contains(text(),'*Brand (PLC)')]//following::select[1]");
	public static By superCategoryPLC  = By.xpath("//td[contains(text(),'Super Category (PLC)')]//following::select[1]");
	public static By segmentPLC  = By.xpath("//td[contains(text(),'Segment (PLC)')]//following::select[1]");
	public static By categoryPLC  = By.xpath("//td[contains(text(),'Category (PLC)')]//following::select[3]");
	public static By internalClassificationPLC  = By.xpath("//td[contains(text(),'Internal Classification (PLC)')]//following::select[1]");
	public static By licensorPLC  = By.xpath("//td[contains(text(),'Licensor (PLC)')]//following::select[1]");
	public static By coBrandPLC  = By.xpath("//td[contains(text(),'Co-Brand (PLC)')]//following::select[1]");
	public static By propertyPLC  = By.xpath("//td[contains(text(),'Property (PLC)')]//following::select[1]");
	public static By lowerAgePLC  = By.xpath("//td[contains(text(),'Lower Age (PLC)')]//following::select[1]");
	public static By upperAgePLC  = By.xpath("//td[contains(text(),'Upper Age (PLC)')]//following::select[1]");
	public static By iSOPLC  = By.xpath("//td[contains(text(),'ISO (PLC)')]//following::select[1]");
	public static By distributionChannelPLC  = By.xpath("//td[contains(text(),'Distribution Channel (PLC)')]//following::select[1]");
	public static By retailerDistributionPLC  = By.xpath("//td[contains(text(),'Retailer Distribution (PLC)')]//following::input[1]");
	public static By projectTypePLC  = By.xpath("//td[contains(text(),'Project Type (PLC)')]//following::select[1]");
	public static By innovationTypePLC  = By.xpath("//td[contains(text(),'Innovation Type (PLC)')]//following::select[1]");
	public static By tVAdPLC  = By.xpath("//td[contains(text(),'TV Ad (PLC)')]//following::select[1]");
	public static By introTimingPLC  = By.xpath("//td[contains(text(),'Intro Timing (PLC)')]//following::select[1]");
	public static By moviePLC  = By.xpath("//td[contains(text(),'Movie (PLC)')]//following::select[1]");
	public static By onShelfDatePLC  = By.xpath("//td[contains(text(),'On-Shelf Date (PLC)')]//following::input[1]");
	public static By digitalProductPLC  = By.xpath("//td[contains(text(),'Digital Product (PLC)')]//following::select[1]");
	public static By pT_MHPLC  = By.xpath("//td[contains(text(),'PT / MH (PLC)')]//following::select[1]");
	public static By commentsPLC  = By.xpath("//td[contains(text(),'Comments (PLC)')]//following::textarea[1]");
	public static By sRPUSDPLC  = By.xpath("//td[contains(text(),'SRP (USD) (PLC)')]//following::input[1]");
	public static By lCPriceUSDPLC  = By.xpath("//td[contains(text(),'L/C Price (USD) (PLC)')]//following::input[1]");
	public static By uSDomesticPLC  = By.xpath("//td[contains(text(),'US Domestic (%) (PLC)')]//following::input[1]");
	public static By dOMPriceUSDPLC  = By.xpath("//td[contains(text(),'DOM Price (USD) (PLC)')]//following::input[1]");
	public static By btnViewPlaceHolder  = By.xpath("//a[contains(text(),'View Placeholder')]");
	public static By assortmentName  = By.id("hbAssortmentSolidName");
	public static By placeHolderLink = By.xpath("//a[text()='Placeholder']");
	public static By inputPlaceHolderNumber = By.xpath("//td[contains(text(),'Placeholder Number')]//following::input[1]");
	public static By optionSetState = By.xpath("//select[contains(@onchange,'evalList(this)')]/option[contains(text(),'Change State:  Placeholder')]");
	public static By optionDelete = By.xpath("//select[contains(@onchange,'evalList(this)')]/option[contains(text(),'Delete')]");
	public static By msgDelete = By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td");
	
//	public static By globalNAForecastUnitsPLC = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By globalLAMForecastUnitsPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By globalEUForecastUnitsPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By globalAsiaForecastUnitsPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By globalPacificForecastUnitsPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By calculatePLCTotal  	 = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By cFSpringPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By cFFallPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By newSpringPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By cewFallPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By productRefreshSpringPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By productRefreshFallPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By packageRefreshSpringPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By packageRefreshFallPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By soldasSolidSpringPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");
//	public static By soldasSolidFallPLC  = By.xpath("//div[@id='seasonDevelopmentContent']/a[contains(text(),'Planning')]");

	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String strAssortmentSolidName;
	static String strAssortmentName;
	static String strPlanName;
	static String valULCS;
	static String valULCSAfterChange;
	static String strRO_UpdateLifecycleState;
	static String strDelete;
	

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcPlaceHolderDevPlan(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Placeholder Dev plan");
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
			{ Create_PHPlan(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_PHPlan(data); }
			//Read view verification
			if(data[3].equalsIgnoreCase("GeneralAttributesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttributesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			
			if(data[3].equalsIgnoreCase("IdentificationRead_View"))
			{  verifyIdentificationRead_View(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("IdentificationAttributesUpdate"))
			{  verifyIdentificationAttributesUpdate(data); }
			
			if(data[3].equalsIgnoreCase("GeneralAttributesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttributesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			
			if(data[3].equalsIgnoreCase("GeneralAttributesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttributesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_PHPlan(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Specification should be created.previous specifications should not be available
	public static boolean Create_PHPlan(String[] data) throws Exception{
		try{
			//Click My Season Link
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[7]);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetAction, data[5]);
				CommonFunctions.waitForVisibilityOfElement(By.xpath("//td[contains(text(),'Choose a Type')]"));
				//Click Product type
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]"), "link", "Product Type");
				strAssortmentSolidName= createNewPlaceholder(data);
				strAssortmentName =driver.findElement(assortmentName).getText();
				Assert.assertEquals(strAssortmentSolidName, strAssortmentName);
			}
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(Product.lineSheetAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}
			else{
				log.info("Create is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_PHPlan()", e);
			return false;
		}
		return true;
	}

	public static String createNewPlaceholder(String[] data) {
		try{
		//	driver.switchTo().defaultContent();
		//	driver.switchTo().frame("contentframe");
			strAssortmentSolidName = CommonFunctions.getRandomString(5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(assortmentSolidName,strAssortmentSolidName);
			//Enter Assortment / Solid # (PLC) 
			CommonFunctions.enterTextInTextbox(assortmentSolidnoPLC, data[8]);
			//Enter *Ast. / Solid (PLC) 
		//	CommonFunctions.enterTextInTextbox(astSolidPLC, data[9]);
			CommonFunctions.selectFromDropDownByVisibleText(astSolidPLC, data[9]);
			//Enter Class (PLC) 
			CommonFunctions.enterTextInTextbox(classPLC, data[10]);
			//Click on Division (PLC)
			CommonFunctions.enterTextInTextbox(divisionPLC, data[11]);
			//Click on   Family Brand (PLC) 
			CommonFunctions.enterTextInTextbox(familyBrandPLC, data[12]);
			//Click on Brand (PLC) 
			CommonFunctions.enterTextInTextbox(brandPLC, data[13]);
			//Click on  Super Category (PLC) 
			CommonFunctions.enterTextInTextbox(superCategoryPLC, data[14]);
			//Click on *Segment (PLC) 
			CommonFunctions.enterTextInTextbox(segmentPLC, data[15]);
			//Click on  Category (PLC) 
			CommonFunctions.enterTextInTextbox(categoryPLC, data[16]);
			//Click on   Internal Classification (PLC) 
			CommonFunctions.enterTextInTextbox(internalClassificationPLC, data[17]);
			//Click on   Licensor (PLC) 
			CommonFunctions.enterTextInTextbox(licensorPLC, data[18]);
			//Click on Co-Brand (PLC) 
			CommonFunctions.enterTextInTextbox(coBrandPLC, data[19]);
			//Click on  Property (PLC) 
			CommonFunctions.enterTextInTextbox(propertyPLC, data[20]);
			//Click on  Lower Age (PLC) 
			CommonFunctions.enterTextInTextbox(lowerAgePLC, data[21]);
			//Click on  Upper Age (PLC) 
			CommonFunctions.enterTextInTextbox(upperAgePLC, data[22]);
			//Click on   *ISO (PLC) 
			CommonFunctions.enterTextInTextbox(iSOPLC, data[23]);
			//Click on Distribution Channel (PLC) 
			CommonFunctions.enterTextInTextbox(distributionChannelPLC, data[24]);
			//Click on  Retailer Distribution (PLC) 
			CommonFunctions.enterTextInTextbox(retailerDistributionPLC, data[25]);
			//Click on Project Type (PLC) 
			CommonFunctions.enterTextInTextbox(projectTypePLC, data[26]);
			//Click on Innovation Type (PLC) 
			CommonFunctions.enterTextInTextbox(innovationTypePLC, data[27]);
			//Click on   TV Ad (PLC)
			CommonFunctions.enterTextInTextbox(tVAdPLC, data[28]);
			//Click on *Intro Timing (PLC) 
			CommonFunctions.enterTextInTextbox(introTimingPLC, data[29]);
			//Click on Movie (PLC) 
			CommonFunctions.enterTextInTextbox(moviePLC, data[30]);
			//Click on On-Shelf Date (PLC) 
			CommonFunctions.enterTextInTextbox(onShelfDatePLC, data[31]);
			//Click on  Digital Product (PLC) 
			CommonFunctions.enterTextInTextbox(digitalProductPLC, data[32]);
			//Click on    PT / MH (PLC) 
			CommonFunctions.enterTextInTextbox(pT_MHPLC, data[33]);
			//Click on  Comments (PLC) 
			CommonFunctions.enterTextInTextbox(commentsPLC, data[34]);
			//Click on  SRP (USD) (PLC)  
			CommonFunctions.enterTextInTextbox(sRPUSDPLC, data[35]);
			//Click on   *L/C Price (USD) (PLC) 
			CommonFunctions.enterTextInTextbox(lCPriceUSDPLC, data[36]);
			//Click on  US Domestic (%) (PLC) 
			CommonFunctions.enterTextInTextbox(uSDomesticPLC, data[37]);
			//Click on  DOM Price (USD) (PLC) 
			CommonFunctions.enterTextInTextbox(dOMPriceUSDPLC, data[38]);
			//Click view Place holder
			CommonFunctions.clickButtonOrLink(btnViewPlaceHolder, "btn", "View PlaceHolder");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return strAssortmentSolidName;
	}
	

	public static boolean SetState_PHPlan(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//click on placeholder
			CommonFunctions.clickButtonOrLink(placeHolderLink, "Link", "PlaceHolder Link");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click Product type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]"), "link", "Product Type");
			CommonFunctions.enterTextInTextbox(inputPlaceHolderNumber, data[39]);
			//Click on Search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
			
			CommonFunctions.clickButtonOrLink(Color.colorAction, "btn", "Action dropdown");
			CommonFunctions.clickButtonOrLink(optionSetState, "option", "Set State");
			String valULCSAfterChange=PHPlan_selectUpdateLifecycleState(data);
			Thread.sleep(1000);
			//Click on Update
			CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
			strRO_UpdateLifecycleState = driver.findElement(GlobalLinePlan.RO_UpdateLifecycleState).getText();
			//verification	
			if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
				Assert.assertEquals(strRO_UpdateLifecycleState, valULCSAfterChange);
			}
			else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
				Assert.assertNotEquals(strRO_UpdateLifecycleState, valULCSAfterChange);
			}
			else
				log.info("SetState or chageState is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in SetState_PHPlan()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_PHPlan(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//click on placeholder
			CommonFunctions.clickButtonOrLink(placeHolderLink, "Link", "PlaceHolder Link");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click Product type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]"), "link", "Product Type");
			CommonFunctions.enterTextInTextbox(inputPlaceHolderNumber, data[39]);
			//Click on Search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");

			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.clickButtonOrLink(Color.colorAction, "btn", "Action dropdown");
				CommonFunctions.clickButtonOrLink(optionDelete, "btn", "Delete Option");
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
				driver.switchTo().alert().accept(); 
			//	CommonFunctions.handleAlertPopUp();
				strDelete= driver.findElement(msgDelete).getText();
				Assert.assertEquals(strDelete, "Delete Successful");  
			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(Color.colorAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}
			else
				log.info("Delete is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_PHPlan()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//click on placeholder
			CommonFunctions.clickButtonOrLink(placeHolderLink, "Link", "PlaceHolder Link");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click Product type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]"), "link", "Product Type");
			CommonFunctions.enterTextInTextbox(inputPlaceHolderNumber, data[39]);
			//Click on Search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
			
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
			driver.navigate().refresh();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//click on placeholder
			CommonFunctions.clickButtonOrLink(placeHolderLink, "Link", "PlaceHolder Link");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click Product type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]"), "link", "Product Type");
			CommonFunctions.enterTextInTextbox(inputPlaceHolderNumber, data[39]);
			//Click on Search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
			if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.enterTextInTextbox(Color.colorAction, data[5]);
				Thread.sleep(2000);
				Assert.assertEquals(driver.findElements(astSolidPLC).size(), 1, "Field is Editable"); 
			}
			else if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(Color.colorAction));
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
	
	//Function consist scenario : General Attributes:Read_View
		public static boolean verifyIdentificationRead_View(String[] data) throws Exception{
			try{
				/*driver.navigate().refresh();
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//click on placeholder
				CommonFunctions.clickButtonOrLink(placeHolderLink, "Link", "PlaceHolder Link");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//Click Product type
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]"), "link", "Product Type");
				CommonFunctions.enterTextInTextbox(inputPlaceHolderNumber, data[39]);
				//Click on Search
				CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
				
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
				}*/
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyIdentificationRead_View()", e);
				return false;
			}
			return true;
		}
		//Function consist scenario : General Attributes://Update
		public static boolean verifyIdentificationAttributesUpdate(String[] data) throws Exception{
			try{
				/*driver.navigate().refresh();
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
				//click on placeholder
				CommonFunctions.clickButtonOrLink(placeHolderLink, "Link", "PlaceHolder Link");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//Click Product type
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]"), "link", "Product Type");
				CommonFunctions.enterTextInTextbox(inputPlaceHolderNumber, data[39]);
				//Click on Search
				CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
				if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
					CommonFunctions.enterTextInTextbox(Color.colorAction, data[5]);
					Thread.sleep(2000);
					Assert.assertEquals(driver.findElements(astSolidPLC).size(), 1, "Field is Editable"); 
				}
				else if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("No")){
					Select select = new Select(driver.findElement(Color.colorAction));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal);
				}	
				else
				{
					log.info("For this General Attributes is not applicable(NA)");
				}*/
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyIdentificationAttributesUpdate()", e);
				return false;
			}
			return true;
		}
		
	

	//This funcion is to select Update Lifecycle State	
	public static String PHPlan_selectUpdateLifecycleState(String[] data) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState,"Under Review");
			}
			else if(valULCS.contains("Released")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Under Review");
				
			}
			else if(valULCS.contains("Inactive")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "In Work");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "In Work");
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
