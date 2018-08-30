package com.hasbro.security;
/**
 * @author Govind Pandey
 * 
 * Create an Assortment/Solid Product with a external source Funskool and colorway
 * Enter the Product Name, Season Year, Source in Security sheet
 * All the specification we need to create in External Source 
 * Security sheet has column 14 where in we can define that user is Vendor user or non-Vendor User
 * For validating component we need to ensure that user is able to create BOM if the have access as Yes
 * 
 *
 */
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

import org.openqa.selenium.TakesScreenshot;


public class Specifications_old extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String strPrimary ;
	static String inWorkspec;
	static String underreviewspec;
	static String supplierreleasedspec;
	static String reworkspec;
	static String productionreleasedspec;
	static String cancelledspec;
	public static String bomname;
	Date date = new Date();
	String imgPath= "C:\\SeleniumScreen\\INC0290848_"+date.getTime();
	boolean failure = false;
	public static String username;
	static boolean varData;
	static Actions action;
	static String strbomIW;


	public static By specificationsTablink= By.xpath("//a[contains(text(),'Specifications')]");
	public static By addNewSpecification= By.xpath("//td[contains(text(),'Add New Specification')]");
	public static By createNewSpecification= By.xpath("//a[text()='Create New Specification']");
	public static By headingcreateNewSpecification= By.xpath("//td[contains(text(),'Create New Specification')]");
	public static By wave= By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By remarks= By.xpath("//td[contains(text(),'Remarks')]//following::td[1]/textarea");
	public static By sourcesCheckAll= By.xpath("//div[@id='souces']//a[text()='Check All']");
	public static By sourcesFirstCheckbox= By.xpath("//input[@name='sourceIds'][1]");
	public static By specificationDetailsLebel= By.xpath("//div[contains(@id,'minus')]/a[text()='Details']");
	public static By specificationAction= By.xpath("//table[@class='PAGEHEADINGTITLE_BG']//select");
	public static By noSpecificationHeadig= By.xpath("//td[@class='HEADING1']");
	public static By ro_specificationChangeHistory= By.id("hbSpecificationChangeHistory");
	public static By ro_Primary= By.xpath("//div[contains(@id,'plus')]/span");
	public static By deleteSpec= By.xpath("//table[@class='PAGEHEADINGTITLE_BG']//select/option[contains(text(),'Delete Spec')]");
	public static By textAfertDelete= By.xpath("//div[contains(@class,'HEADING1')]/text()[preceding-sibling::br and following-sibling::br]");
	public static By plusSign=By.xpath("//div[contains(@id,'specTitle')]/a");
	public static By specificationtitle=By.xpath("//table[@class='PAGEHEADINGTITLE_BG']/tbody/tr/td[1]/div[1]/a/following-sibling::text()[1]");
	public static By specificationC= By.xpath("//table[@class='PAGEHEADINGTITLE_BG']");
	public static By specificationdropdown= By.xpath(".//*[@id='contextSpecId']");
	public static By specificationstatusinput = By.xpath("//td[contains(text(),'Specification Status')]//following::select[1]");
	public static By updatespecSave = By.xpath("//a[text()='Save']");
	public static By updatespecCancel = By.xpath("//a[text()='Cancel']");
	public static By specsummary = By.xpath("//a[text()='Summary']");
	public static By updatespecificationheader = By.xpath("//td[contains(text(),'Update Specification')]");
	public static By selectSource= By.id("sourcingConfigId");	
	public static By vendorUpdateWave= By.id("hbWave");
	public static By vendorUpdateRemarks= By.id("hbRemarks");	
	public static By vendorUpdateStatus= By.id("hbSpecStatus");	
	public static By selectSpecification= By.id("contextSpecId");
	public static By addNewbomTab= By.xpath(".//*[@id='ADD_PAGETab']/a");
	public static By initializebom= By.xpath("//a[contains(text(),'Initialize BOM')]");
	public static By bomtypeid= By.id("bomTypeId");
	public static By headingCreatebom= By.xpath("//td[contains(text(),'Create BOM')]");
	public static By colorway= By.xpath("//td[contains(text(),'Colorway')]//following::select[1]");
	public static By factory = By.xpath("//a[contains(text(),'Factory')]");
	public static By currency= By.xpath("//td[contains(text(),'Currency')]//following::select[1]");
	public static By findFactory = By.xpath("//span[contains(text(),'Find: Factory')]");
	public static By factoryFindSearch = By.xpath(".//*[@id='SearchButton1']");
	public static By factorySearchResult = By.xpath("//span[contains(text(),'Search Results for Vendor (Supplier)')]");
	public static By chooseFactory= By.xpath("//a[contains(text(),'(choose)')]");
	public static By compOrLoca= By.id("r1_partName");
	public static By inputCompOrLoca= By.xpath("//div[@id='partNameSourceDiv']/input");
	public static By headerAttributes= By.xpath("//div[@id='genDetails_plus']/a[2]");
	public static By btnSaveAndCheckIn= By.xpath("//a[text()='Save and Check In']");
	public static By bomId= By.xpath("//*[@id='bomId']");
	

	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	static String Selectedspec;
	static int j;

	static Specifications_old sp = new Specifications_old();

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSpecifications(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Security Specification");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			System.out.println("col2 :" + data[3]);
			System.out.println("col4 :" + data[4]);
			username=data[0];
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
	
			//Create Under Review Specification
			if(data[3].equalsIgnoreCase("createunderreview"))
			{ create_Specifications_Underreview(data); }

			//Create Super_Released Specification
			if(data[3].equalsIgnoreCase("createsupplierreleased"))
			{ create_Supplier_Realeased(data); }

			//Create Rework Specification
			if(data[3].equalsIgnoreCase("createrework"))
			{ create_Rework(data); }

			//Create Production Released Specification
			if(data[3].equalsIgnoreCase("createproductionreleased"))
			{ create_Production_Released(data); }

			//Create cancelled Specification
			if(data[3].equalsIgnoreCase("Createcancelled"))
			{ create_Cancelled(data); }
			
			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ create_Specifications(data); }
			
			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("CreateInWork"))
			{ create_InWork(data); }
			
			//Read specification
			if(data[3].equalsIgnoreCase("Read"))
			{ read(data); }
			
			//Update Specification
			if(data[3].equalsIgnoreCase("updateSpec"))
			{ update_Spec(data); }
			
			//Read In Work Specification
			if(data[3].equalsIgnoreCase("inWorkRead"))
			{ inWork_Read(data); }
			//Update In Work Specification
			if(data[3].equalsIgnoreCase("inWorkUpdate"))
			{ inWork_Update(data); }
			
			//Read Under Review Specification
			if(data[3].equalsIgnoreCase("underReviewRead"))
			{ underReview_Read(data); }
			
			//Update Under Review Specification
			if(data[3].equalsIgnoreCase("underReviewUpdate"))
			{ underReview_Update(data); }
			
			//Read Supplier Released Specification
			if(data[3].equalsIgnoreCase("supplierReleasedRead"))
			{ supplierReleased_Read(data); }
			
			//Update Supplier Released Specification
			if(data[3].equalsIgnoreCase("supplierReleasedUpdate"))
			{ supplierReleased_Update(data); }
			
			//Read Rework Specification
			if(data[3].equalsIgnoreCase("reworkRead"))
			{ rework_Read(data); }
			
			//Update Rework Specification
			if(data[3].equalsIgnoreCase("reworkUpdate"))
			{ rework_Update(data); }
			
			//Read Production Released Specification
			if(data[3].equalsIgnoreCase("productionReleasedRead"))
			{ productionReleased_Read(data); }
			
			//Update Production Released Specification
			if(data[3].equalsIgnoreCase("productionReleasedUpdate"))
			{ productionReleased_Update(data); }
			
			//Read Cancelled Specification
			if(data[3].equalsIgnoreCase("cancelledRead"))
			{ cancelled_Read(data); }
			//Update Cancelled Specification
			if(data[3].equalsIgnoreCase("cancelledUpdate"))
			{ cancelled_Update(data); }

			//Read General Attribute table
			if(data[3].equalsIgnoreCase("generalAttributesRead"))
			{  generalAttributes_Read(data); }
			//Update General Attribute table
			if(data[3].equalsIgnoreCase("generalAttirbutesUpdate"))
			{  generalAttirbutes_Update(data); }
			
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Specifications(data); }

			//Create BOM component in accessible specification
			if(data[3].equalsIgnoreCase("componentSpecifications"))
			{ component_Specifications(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	public static boolean navigateToMaterialTab(String [] data) throws Exception{
		try{
		//Search Product
		CommonProjectFunctions.searchProduct(data[5]);
		//Click on Specification
		CommonProjectFunctions.clickSpecificationTab(data[6]);
		//Click on Material tab
		CommonProjectFunctions.clickMaterialsTab();

		//Select Source
		Select selectsource= new Select(driver.findElement(selectSource));
		selectsource.selectByVisibleText(data[12]);
		CommonFunctions.handleAlertPopUp();
		
		//Select the Supplier Released specification from the specification drop down
		CommonFunctions.enterTextInTextbox(specificationdropdown, supplierreleasedspec);		
		CommonFunctions.handleAlertPopUp();
		
		return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in navigateToMaterialTab()", e);
			return false;
		}
		
	}
	
	public static String fillCreatebom(String[] data) throws Exception{
		try{
			//Wait for heading Create BOM
			CommonFunctions.waitForVisibilityOfElement(headingCreatebom);
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Vendor")))
			{
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(colorway, 1);
			//Select Wave
			CommonFunctions.selectFromDropDownByVisibleText(wave, data[7]);
			//Select Currency
			CommonFunctions.selectFromDropDownByVisibleText(currency, data[10]);
			
			//Get the Window Handler of parent Window
			String parentWindow= driver.getWindowHandle();
			System.out.println("The ID of parent Window is: " + parentWindow);
			
			//Select Factory
			driver.findElement(factory).click();
			
			//Get the number of pop up Windows open
			Set<String> handles =driver.getWindowHandles();
			
			for (String handle1: driver.getWindowHandles()){
			
				System.out.println(handle1);
				driver.switchTo().window(handle1);				
			}
			
			//Click on Find Factory Search
			driver.findElement(factoryFindSearch).click();
			//Click on Choose Factory
			driver.findElement(chooseFactory).click();
			
			//Switch to Parent Window
			driver.switchTo().window(parentWindow);
			
			//Switch to default content
			driver.switchTo().defaultContent();
			
			//Switch to Content Frame
			driver.switchTo().frame("contentframe");
					
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreatebom()", e);
			return "";
		}
		return bomname;
	}
	
	
	public static String component_Specifications(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[3].contains("componentSpecifications")&& data[4].equalsIgnoreCase("Yes")){				
				
				//Click Add New bom tab
				CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
				//Enter bom Type
				CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);

				//Click Initialize bom
				CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
				//Create bom page
				fillCreatebom(data);
				//Switch to mainframe
				driver.switchTo().frame("mainFrame");
				//Enter in Component or Location attribute
				action = new Actions(driver);
				action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
				
				//Click on Save and Check in button
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				
				//Switch to Default frame
				driver.switchTo().defaultContent();
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Click on Header Attribute button
				CommonFunctions.waitForVisibilityOfElement(headerAttributes);
				
				//Check the name of the first selected BOM
				strbomIW=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
				System.out.println("The name of the bom is:" + strbomIW);
				//Remove leading and trailing white spaces
				strbomIW=strbomIW.trim().replaceAll("\\s+", " ");
				System.out.println("User has access to create component and the name of component is" + strbomIW);
				
				}
	
			else if(data[3].contains("componentSpecifications")&& data[4].equalsIgnoreCase("No")){
				//Get the size of Add new BOM tab
				System.out.println(driver.findElements(addNewbomTab).size());
				//Verify that Add New BOM tab is not present
				Assert.assertEquals(driver.findElements(addNewbomTab).size(), 0, "bom can not create"); 
				System.out.println("Use does not have access to create bom");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in component_Specifications()", e);
		}
		return strbomIW;
	}
		
	
	public static boolean read(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Read")&& data[4].equalsIgnoreCase("Yes")){
				
				if(data[14].equalsIgnoreCase("Yes")){
					
					//Select the last value from Specification value drop down
					Select select1 = new Select(driver.findElement(specificationdropdown));
					List<WebElement> options1 = select1.getOptions();
					
					//Verify that Supplier Released Specification is visible
					boolean bVal = CommonFunctions.selectFromDropDownByVisibleText(specificationdropdown, supplierreleasedspec);
					
					log.info("User is able to see In Work Specification: " + bVal);
					
				}
				else {
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that In Work Specification is visible
				boolean bVal = CommonFunctions.selectFromDropDownByVisibleText(specificationdropdown, inWorkspec);
				
				log.info("User is able to see In Work Specification: " + bVal);
				}
			}
			else if(data[3].contains("Read")&& data[4].equalsIgnoreCase("No")){
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that In Work Specification is visible
				 boolean bVal =CommonFunctions.dropDownOptionVerification(inWorkspec, options1);
				
				log.info("Does user is able to see Supplier Released Specification: " + bVal);		
			
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in read()", e);
			return false;
		}
		return true;

	}
	
	public static boolean inWork_Read(String[] data) throws Exception{
		try{
			//search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("inWorkRead")&& data[4].equalsIgnoreCase("Yes")){
			
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that In Work Specification is visible
				boolean bVal = CommonFunctions.selectFromDropDownByVisibleText(specificationdropdown, inWorkspec);
				
				log.info("User is able to see In Work Specification: " + bVal);

			}
			else if(data[3].contains("inWorkRead")&& data[4].equalsIgnoreCase("No")){
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that In Work Specification is visible or not
				 boolean bVal =CommonFunctions.dropDownOptionVerification(inWorkspec, options1);
				
				log.info("Does user is able to see In Work Specification: " + bVal);		
			
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in inWork_Read()", e);
			return false;
		}
		return true;

	}

	public static boolean underReview_Read(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("underReviewRead")&& data[4].equalsIgnoreCase("Yes")){
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();				
				
				//Verify that Under Review Specification is visible
				boolean bVal= CommonFunctions.selectFromDropDownByVisibleText(specificationdropdown, underreviewspec);

				log.info("User is able to see Under Review Specification: " + bVal);

			}
			else if(data[3].contains("underReviewRead")&& data[4].equalsIgnoreCase("No")){
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that Under Review Specification is visible
				 boolean bVal =CommonFunctions.dropDownOptionVerification(underreviewspec, options1);
				
				log.info("Does user is able to see Under Review Specification: " + bVal);		

			}

		}catch(Exception e){ fail=true;
		log.error("Exception in underReview_Read()", e);
			return false;
		}
		return true;

	}
	
	public static boolean supplierReleased_Read(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("supplierReleasedRead")&& data[4].equalsIgnoreCase("Yes")){
						
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that Supplier Released Specification is visible
				boolean bVal = CommonFunctions.selectFromDropDownByVisibleText(specificationdropdown, supplierreleasedspec);
				
				log.info("User is able to see Supplier Released Specification: " + bVal);

			}
			else if(data[3].contains("supplierReleasedRead")&& data[4].equalsIgnoreCase("No")){
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that Supplier Released Specification is visible
				 boolean bVal =CommonFunctions.dropDownOptionVerification(supplierreleasedspec, options1);
				
				log.info("Does user is able to see Supplier Released Specification: " + bVal);		
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in supplierReleased_Read()", e);
			return false;
		}
		return true;

	}
	
	
	public static boolean rework_Read(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("reworkRead")&& data[4].equalsIgnoreCase("Yes")){
						
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that Rework Specification is visible
				boolean bVal = CommonFunctions.selectFromDropDownByVisibleText(specificationdropdown, reworkspec);
				
				log.info("User is able to see Rework Specification: " + bVal);

			}
			else if(data[3].contains("reworkRead")&& data[4].equalsIgnoreCase("No")){
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that Rework Specification is visible
				 boolean bVal =CommonFunctions.dropDownOptionVerification(reworkspec, options1);
				
				log.info("Does user is able to see rework Specification: " + bVal);		
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in rework_Read()", e);
			return false;
		}
		return true;

	}
	
	
	public static boolean productionReleased_Read(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("productionReleasedRead")&& data[4].equalsIgnoreCase("Yes")){
						
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that In Work Specification is visible
				boolean bVal =CommonFunctions.enterTextInTextbox(specificationdropdown, productionreleasedspec);
				
				log.info("User is able to see Production Released Specification: " + bVal);

			}
			else if(data[3].contains("productionReleasedRead")&& data[4].equalsIgnoreCase("No")){
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that In Work Specification is visible
				 boolean bVal =CommonFunctions.dropDownOptionVerification(productionreleasedspec, options1);
				
				log.info("Does user is able to see Production Released Specification: " + bVal);		
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in productionReleased_Read()", e);
			return false;
		}
		return true;

	}
	
	
	public static boolean cancelled_Read(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("cancelledRead")&& data[4].equalsIgnoreCase("Yes")){
						
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that In Work Specification is visible
				boolean bVal = CommonFunctions.selectFromDropDownByVisibleText(specificationdropdown, cancelledspec);
				
				log.info("User is able to see Cancelled Specification: " + bVal);

			}
			else if(data[3].contains("cancelledRead")&& data[4].equalsIgnoreCase("No")){
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that Cancelled Specification is visible
				 boolean bVal =CommonFunctions.dropDownOptionVerification(cancelledspec, options1);
				
				log.info("Does user is able to see Cancelled Specification: " + bVal);		
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in cancelled_Read()", e);
			return false;
		}
		return true;

	}
	
		public static boolean update_Spec(String[] data) throws Exception{
			try{
				//Search Product
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("updateSpec")&& data[4].equalsIgnoreCase("Yes")){
				
				if(data[14].equalsIgnoreCase("Yes")){
					//Select the supplier released specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, supplierreleasedspec);
					
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
				//	int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k =j+1;
					System.out.println(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"));
					//Click on Action menu
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
					//Click on Update Spec option
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
					
					//Wait for Update specification header to appear
					CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);
					
					//Verify that the attributes in General attribute are not editable
					Assert.assertEquals(driver.findElements(vendorUpdateRemarks).size(), 1, "Remark is not editable");
					Assert.assertEquals(driver.findElements(vendorUpdateWave).size(), 1, "Wave is not editable");
					Assert.assertEquals(driver.findElements(vendorUpdateStatus).size(), 1, "Status is not editable");

		
					
					//Click on Save Button
					CommonFunctions.clickButtonOrLink(updatespecCancel, "Cancel button");			
					
					log.info("Vendor User has Update spec action available");									
				}
					
				else {	
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);
				
				//Select Specification drop down
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				System.out.println(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"));
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				//Waito for Update Specification header
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				
				//Change the value of remarks
				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecSave, "Save button");
				
				//Wait for visibility of Primary specifiction to appear
				CommonFunctions.waitForVisibilityOfElement(ro_Primary);			
				
				//Capture the remark attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
								
				//Print the remark in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				//Verify that remark attribute in detail page and edit page are same
				if(varData){
					Assert.assertEquals(data[13], remarkdetailpage);
				}
				else{
					Assert.assertEquals(data[8], remarkdetailpage);
				}
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);
			
				}
				}
				
				else if(data[3].contains("updateSpec")&& data[4].equalsIgnoreCase("No")){
					//Select the under review specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);
					//Select Specification drop down
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k= j+1;
					//Get the option available in action Menu
					Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
					
					//Verify that Update Spec button present or not
					boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
					
					//Verify that In Work BOM is not visible
					Assert.assertFalse(bVal);
					log.info("Is In Update button is visible for selected specification: " + bVal);
					
				}

			}catch(Exception e){ 
				fail=true;
				log.error("Exception in update_Spec()", e);
			}
			return true;

		}
		
		public static boolean inWork_Update(String[] data) throws Exception{
			try{
				//Search product
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("inWorkUpdate")&& data[4].equalsIgnoreCase("Yes")){
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);
				//Select Specification drop down
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				System.out.println(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"));
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				//Wait for Update specification header to appear
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				
				//Change the remark attribute
				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecSave, "Save button");
				//wait for primary specification to appear
				CommonFunctions.waitForVisibilityOfElement(ro_Primary);			
				
				//Capture the remark attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
								
				//Print the remark in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				//Verify that remark updated in edit page and in detail page are same
				if(varData){
					Assert.assertEquals(data[13], remarkdetailpage);
				}
				else{
					Assert.assertEquals(data[8], remarkdetailpage);
				}
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);
			
				}
				
				else if(data[3].contains("inWorkUpdate")&& data[4].equalsIgnoreCase("No")){
					
					if(data[14].equalsIgnoreCase("Yes"))
						
					{
						//Select the last value from Specification value drop down
						Select select1 = new Select(driver.findElement(specificationdropdown));
						List<WebElement> options1 = select1.getOptions();
						
						//Verify that In Work Specification is visible
						 boolean bVal =CommonFunctions.dropDownOptionVerification(inWorkspec, options1);
						
						log.info("Does user is able to see In Work Specification: " + bVal);		
											
					}
					
					else {
					//Select the In Work specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);
					//Select Specification drop down
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					//
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k= j+1;
					//Get the option available in action Menu
					Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
					
					//Verify that Update Spec button present or not
					boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
					
					//Verify that In Update button is visible or not
					Assert.assertFalse(bVal);
					log.info("Is In Update button is visible for selected specification: " + bVal);
					}
				}

			}catch(Exception e){ 
				fail=true;
				log.error("Exception in inWork_Update()", e);
			}
			return true;

		}
		
		
		
		public static boolean underReview_Update(String[] data) throws Exception{
			try{
				//Search product
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("underReviewUpdate")&& data[4].equalsIgnoreCase("Yes")){
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, underreviewspec);
				//Select Specification drop down
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;

				System.out.println(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"));
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				//Wait for update specification header
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				
				//change the remark attribute
				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecSave, "Save button");
				//Wait for primary specification to appear
				CommonFunctions.waitForVisibilityOfElement(ro_Primary);			
				
				//Capture the wave attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
								
				//Print the wave in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				
				//Verify that remark updated in edit page and in detail page are same
				if(varData){
					Assert.assertEquals(data[13], remarkdetailpage);
				}
				else{
					Assert.assertEquals(data[8], remarkdetailpage);
				}
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);
			
				}
				
				else if(data[3].contains("underReviewUpdate")&& data[4].equalsIgnoreCase("No")){
					
					if(data[14].equalsIgnoreCase("Yes")){
					
						//Select the last value from Specification value drop down
						Select select1 = new Select(driver.findElement(specificationdropdown));
						List<WebElement> options1 = select1.getOptions();
						
						//Verify that In Work Specification is visible
						 boolean bVal =CommonFunctions.dropDownOptionVerification(underreviewspec, options1);
						
						log.info("Does user is able to see Under Review Specification: " + bVal);		
												
					}
					
					else {
					
					
					//Select the under review specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, underreviewspec);
					
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					//
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k= j+1;
					//Get the option available in action Menu
					Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
					
					//Verify that Update Spec button present or not
					boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
					Assert.assertFalse(bVal);
					log.info("Is In Update button is visible for selected specification: " + bVal);
					}
				}

			}catch(Exception e){ 
				fail=true;
				log.error("Exception in underReview_Update()", e);
			}
			return true;

		}
		
		
		public static boolean supplierReleased_Update(String[] data) throws Exception{
			try{
				//Search Product
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("supplierReleasedUpdate")&& data[4].equalsIgnoreCase("Yes")){
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, supplierreleasedspec);
				
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				
				if(data[14].equalsIgnoreCase("Yes")){
					System.out.println(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"));
					//Click on Action menu
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
					//Click on Update Spec option
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
					
					//Wait for Update page
					CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);
					
					//Verify that the attributes in General attribute are not editable
					Assert.assertEquals(driver.findElements(vendorUpdateRemarks).size(), 1, "Remark is not editable");
					Assert.assertEquals(driver.findElements(vendorUpdateWave).size(), 1, "Wave is not editable");
					Assert.assertEquals(driver.findElements(vendorUpdateStatus).size(), 1, "Status is not editable");				
					//Click on Save Button
					CommonFunctions.clickButtonOrLink(updatespecCancel, "Cancel button");			
					
					log.info("Vendor User has Update spec action available");	
										
				}
				
				else {
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				
				//Wait for Update page
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				
				//Change the remark attribute value
				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecSave, "Save button");
				
				CommonFunctions.waitForVisibilityOfElement(ro_Primary);			
				
				//Capture the wave attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
								
				//Print the wave in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				//Verify that remark updated in edit page and in detail page are same
				if(varData){
					Assert.assertEquals(data[13], remarkdetailpage);
				}
				else{
					Assert.assertEquals(data[8], remarkdetailpage);
				}
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);
			
				}
				}
				
				else if(data[3].contains("supplierReleasedUpdate")&& data[4].equalsIgnoreCase("No")){
					//Select the supplier released specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, supplierreleasedspec);

					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					//
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k= j+1;
					//Get the option available in action Menu
					Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
					
					//Verify that Update Spec button present or not
					boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
					Assert.assertFalse(bVal);
					log.info("Is In Update button is visible for selected specification: " + bVal);
					
				}

			}catch(Exception e){ 
				fail=true;
				log.error("Exception in supplierReleased_Update()", e);
			}
			return true;

		}

		
		public static boolean rework_Update(String[] data) throws Exception{
			try{
				//Search product
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("reworkUpdate")&& data[4].equalsIgnoreCase("Yes")){
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, reworkspec);
				
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				
				if(data[14].equalsIgnoreCase("Yes")){
					//Click on Action menu
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
					//Click on Update Spec option
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
					//wait for Update specification header page to appear
					CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);
					
					//Verify that the attributes in General attribute are not editable
					Assert.assertEquals(driver.findElements(vendorUpdateRemarks).size(), 1, "Remark is not editable");
					Assert.assertEquals(driver.findElements(vendorUpdateWave).size(), 1, "Wave is not editable");
					Assert.assertEquals(driver.findElements(vendorUpdateStatus).size(), 1, "Status is not editable");
						
					//Click on Save Button
					CommonFunctions.clickButtonOrLink(updatespecCancel, "Cancel button");			
					
					log.info("Vendor User has Update spec action available");						
				}
				
				else {
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				
				//Change the remark attribute
				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecSave, "Save button");
				//wait for primary specificaiton tpo appear
				CommonFunctions.waitForVisibilityOfElement(ro_Primary);			
				
				//Capture the remark attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
								
				//Print the remark in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				
				//Verify that remark updated in edit page and in detail page are same
				if(varData){
					Assert.assertEquals(data[13], remarkdetailpage);
				}
				else{
					Assert.assertEquals(data[8], remarkdetailpage);
				}
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);
			
				}
				}
				
				else if(data[3].contains("reworkUpdate")&& data[4].equalsIgnoreCase("No")){
					//Select the under review specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, reworkspec);

					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					//
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k= j+1;
					//Get the option available in action Menu
					Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
					
					//Verify that Update Spec button present or not
					boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
					
					//Verify that Update Spec button present or not
					Assert.assertFalse(bVal);
					log.info("Is In Update button is visible for selected specification: " + bVal);					
				}

			}catch(Exception e){ 
				fail=true;
				log.error("Exception in rework_Update()", e);
			}
			return true;
		}
				
		public static boolean productionReleased_Update(String[] data) throws Exception{
			try{
				//Search Product
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("productionReleasedUpdate")&& data[4].equalsIgnoreCase("Yes")){
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, productionreleasedspec);
				//Select Specification drop down
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				
				//Wait for Update header to display
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				
				//Update the value of remarks
				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecSave, "Save button");
				
				//Wait for Primary Specification
				CommonFunctions.waitForVisibilityOfElement(ro_Primary);			
				
				//Capture the wave attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
								
				//Print the wave in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				//Verify that remark updated in edit page and in detail page are same
				if(varData){
					Assert.assertEquals(data[13], remarkdetailpage);
				}
				else{
					Assert.assertEquals(data[8], remarkdetailpage);
				}
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);
			
				}
				
				else if(data[3].contains("productionReleasedUpdate")&& data[4].equalsIgnoreCase("No")){
					//Select the under review specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, productionreleasedspec);
					//Select Specification from Specification drop down
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					//
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k= j+1;
					
					if(data[14].equalsIgnoreCase("Yes"))						
					{
						//Get the option available in action Menu
						Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
						List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
						
						//Verify that Update Spec button present or not
						boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
						Assert.assertFalse(bVal);
						log.info("Is In Update button is visible for selected specification: " + bVal);					
						
					}
					
					else {
					//Get the option available in action Menu
					Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
					
					//Verify that Update Spec button present or not
					boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
					Assert.assertFalse(bVal);
					log.info("Is In Update button is visible for selected specification: " + bVal);					
				}
				}

			}catch(Exception e){ 
				fail=true;
				log.error("Exception in productionReleased_Update()", e);
			}
			return true;
		}

		
		public static boolean cancelled_Update(String[] data) throws Exception{
			try{
				//Search Product
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("cancelledUpdate")&& data[4].equalsIgnoreCase("Yes")){
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, cancelledspec);
				//Select Specification drop down
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				//Wait for Update header page to display
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				
				//Update the remark attribute value
				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecSave, "Save button");
				//Wait for Primary specification to display
				CommonFunctions.waitForVisibilityOfElement(ro_Primary);			
				
				//Capture the wave attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
								
				//Print the wave in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				//Verify that remark updated in edit page and in detail page are same
				if(varData){
					Assert.assertEquals(data[13], remarkdetailpage);
				}
				else{
					Assert.assertEquals(data[8], remarkdetailpage);
				}
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);
			
				}
				
				else if(data[3].contains("cancelledUpdate")&& data[4].equalsIgnoreCase("No")){
					//Select the under review specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, cancelledspec);
					//Select specification drop down
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k= j+1;
					
					if(data[14].equalsIgnoreCase("Yes")){
					//Get the option available in action Menu
					Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
						
					//Verify that Update Spec button present or not
					boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
						
					//Verify that In Work BOM is not visible
					Assert.assertFalse(bVal);
					log.info("Is In Update button is visible for selected specification: " + bVal);										
						
					}
					
					else {
					//Get the option available in action Menu
					Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
					
					//Verify that Update Spec button present or not
					boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
					Assert.assertFalse(bVal);
					log.info("Is In Update button is visible for selected specification: " + bVal);					
				}
				}

			}catch(Exception e){ 
				fail=true;
				log.error("Exception in cancelled_Update()", e);
			}
			return true;
		}
		

	public static String create_Specifications_Underreview(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("createunderreview")&& data[4].equalsIgnoreCase("Yes")){
				
				//Create Specification
				createDefaultSpecification(data);
				
				//find and wait for Primary specificaiton to display 
				strPrimary=driver.findElement(ro_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));

				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				int totalelement= options1.size();

				//Click on Summary to refresh the page
				driver.findElement(specsummary);

				System.out.println("total element in Specification dropdown" + totalelement );

				int speccount = totalelement -1;				

				//Select the specification from the specification drop down
				select1.selectByIndex(speccount);			
				
				//Get the text for Specification
				Select select3= new Select(driver.findElement(specificationdropdown));
				underreviewspec= select3.getFirstSelectedOption().getText();

				System.out.println("The specification which got created is: " + underreviewspec );

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Under Review");

				//Click on Save button
				CommonFunctions.clickButtonOrLink(updatespecSave, "update button");
				
				//Under Review specificaion got created
				log.info("Under Review Specificaiton got created: " + underreviewspec);

			}
			else if(data[3].contains("createunderreview")&& data[4].equalsIgnoreCase("No")){
				//Verify that create New Specification does not exist
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in create_Specifications_Underreview()", e);
		}       			
		return underreviewspec;
	}

	public static String create_Supplier_Realeased(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("createsupplierreleased")&& data[4].equalsIgnoreCase("Yes")){

				//create specification
				createDefaultSpecification(data);
				//Wait for primary specification to display
				strPrimary=driver.findElement(ro_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));

				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				int totalelement= options1.size();

				System.out.println("total element in Specification dropdown" + totalelement );

				int speccount = totalelement -1;

				//Select the specification from the specification drop down
				select1.selectByIndex(speccount);			

				//Get the text for Specification
				Select select3= new Select(driver.findElement(specificationdropdown));

				//Get the text for specification selected
				supplierreleasedspec= select3.getFirstSelectedOption().getText();
				
				System.out.println("The specification which got created is" + supplierreleasedspec );
				

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Supplier Released");

				//Click on Save button
				driver.findElement(updatespecSave).click();

			}
			else if(data[3].contains("createsupplierreleased")&& data[4].equalsIgnoreCase("No")){
				//Verify that create New Specification does not exist
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in create_Supplier_Realeased()", e);
		}
		return supplierreleasedspec;

	}

	public static String create_Rework(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("createrework")&& data[4].equalsIgnoreCase("Yes")){
				
				//Create specification
				createDefaultSpecification(data);
				
				//Wait primary specification to display
				strPrimary=driver.findElement(ro_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				int totalelement= options1.size();
				
				System.out.println("total element in Specification dropdown" + totalelement );

				int speccount = totalelement -1;

				//Select the specification from the specification drop down
				select1.selectByIndex(speccount);			

				//Get the text for Specification
				Select select3= new Select(driver.findElement(specificationdropdown));				
				reworkspec= select3.getFirstSelectedOption().getText();
				
				System.out.println("The specification which got created is: " + reworkspec);
				
				//print out the total element
				System.out.println("total element in Specification dropdown" +totalelement );

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Rework");

				//Click on Save button
				driver.findElement(updatespecSave).click();

			}
			else if(data[3].contains("createrework")&& data[4].equalsIgnoreCase("No")){
				//Verify that Create New Specification option does not exist
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in create_Rework()", e);
		//	return false;
		}
		return reworkspec;
	}

	public static String create_Production_Released(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("createproductionreleased")&& data[4].equalsIgnoreCase("Yes")){
				
				//Create Specification
				createDefaultSpecification(data);
				
				//wait for the primary specification to display
				strPrimary=driver.findElement(ro_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				int totalelement= options1.size();

				System.out.println("total element in Specification dropdown" + totalelement );

				int speccount = totalelement -1;

				//Select the specification from the specification drop down
				select1.selectByIndex(speccount);			

				//Get the text for Specification
				Select select3= new Select(driver.findElement(specificationdropdown));

				//Get the text for selected specification
				productionreleasedspec= select3.getFirstSelectedOption().getText();
				
				System.out.println("The specification which got created is : " + productionreleasedspec);

				System.out.println("total element in Specification dropdown" +totalelement );

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Production Released");

				//Click on Save button
				driver.findElement(updatespecSave).click();

			}
			else if(data[3].contains("createproductionreleased")&& data[4].equalsIgnoreCase("No")){
				//Verify that Create New specification option is not displaying
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in create_Production_Released()", e);
		}
		return productionreleasedspec;

	}

	public static String create_Cancelled(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Createcancelled")&& data[4].equalsIgnoreCase("Yes")){
				
				//Create New Specification
				createDefaultSpecification(data);
				
				//Wait till primary specification is not displaying
				strPrimary=driver.findElement(ro_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				int totalelement= options1.size();
				

				System.out.println("total element in Specification dropdown" + totalelement );

				int speccount = totalelement -1;

				//Select the specification from the specification drop down
				select1.selectByIndex(speccount);			

				//Get the text for Specification
				Select select3= new Select(driver.findElement(specificationdropdown));

				//Get the text for created specification
				cancelledspec= select3.getFirstSelectedOption().getText();
				
				System.out.println("The specification which got created is:" + cancelledspec);


				System.out.println("total element in Specification dropdown" +totalelement );

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Canceled");

				//Click on Save button
				driver.findElement(updatespecSave).click();

			}
			else if(data[3].contains("Createcancelled")&& data[4].equalsIgnoreCase("No")){
				//Verify that Create New Specification option is not displaying
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in Create_cancelled()", e);
		}
		return cancelledspec;

	}

	public static boolean Specification_Underreview_readView(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);

			//Switch to content frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("SpecificationUnderrevieweadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View

				//Select the under review specification from the specification dropdown
				Select selectspec=new Select(driver.findElement(specificationdropdown));
				selectspec.selectByVisibleText(data[11]);

				//Get the text for first selected specification
				String specname=selectspec.getFirstSelectedOption().getText();

			}

			else if(data[3].contains("SpecificationUnderrevieweadView")&& data[4].equalsIgnoreCase("No")){

			}
		}catch(Exception e){ fail=true;
		log.error("Exception in Specification_Underreview_readView()", e);
		}
		return false;
	}

	
	public static void createDefaultSpecification(String[] data){
		
		try{
				//Click create New Specification
				CommonFunctions.clickButtonOrLink(createNewSpecification, "btn", "create New Specification");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingcreateNewSpecification));
				//Fill page
				//Enter wave
				CommonFunctions.enterTextInTextbox(wave, data[7]);
				//Remarks
				CommonFunctions.enterTextInTextbox(remarks, data[8]);

				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");

		}catch(Exception e){ fail=true;
		log.error("Exception in createDefaultSpecification()", e);
		}		
	}


	//Prerequisite: Create Specification
	public static String create_Specifications(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){

				//Create Specification
				createDefaultSpecification(data);
				//Wait for Primary specification to display
				strPrimary=driver.findElement(ro_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				//Verify that created specificaiton has primary tag
				System.out.println(strPrimary);
				Assert.assertEquals(strPrimary,"Primary");
			}
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				//Verify that User does not have create New Specification button
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
				log.info("User does not have create New Specification button");
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in create_Specifications()", e);
		}
		return strPrimary;
	}

	//Prerequisite: Create Specification
		public static String create_InWork(String[] data) throws Exception{
			try{
				//Search Product
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("CreateInWork")&& data[4].equalsIgnoreCase("Yes")){
					//Create Specification
					createDefaultSpecification(data);
					//wait for primary specification
					strPrimary=driver.findElement(ro_Primary).getText();
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
					System.out.println(strPrimary);
					//Verify that Primary specification tag present on created specificaiton
					Assert.assertEquals(strPrimary,"Primary");
					log.info("New primary specification got created");
					
					//Select the last value from Specification value drop down
					Select select1 = new Select(driver.findElement(specificationdropdown));
					List<WebElement> options1 = select1.getOptions();
					int totalelement= options1.size();

					//Click on Summary to refresh the page
					driver.findElement(specsummary);

					System.out.println("total element in Specification dropdown" + totalelement );

					int speccount = totalelement -1;

					//Select the specification from the specification drop down
					select1.selectByIndex(speccount);			

					//Get the text for Specification
					Select select3= new Select(driver.findElement(specificationdropdown));
					inWorkspec= select3.getFirstSelectedOption().getText();

					System.out.println("The specification which got created is" + inWorkspec );
					
				}
				else if(data[3].contains("CreateInWork")&& data[4].equalsIgnoreCase("No")){
					//Verify that create New Specification button does not exist
					Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
					log.info("Create New Specification button does not exist");
				}

			}catch(Exception e){ 
				fail=true;
				log.error("Exception in create_InWork()", e);
			}
			return inWorkspec;
		}


	public static boolean delete_Specifications(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
			  //Create Specification	
			  createDefaultSpecification(data);	
			  
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				int totalelement= options1.size();

				//Click on Summary to refresh the page
				driver.findElement(specsummary);

				System.out.println("total element in Specification dropdown" + totalelement );

				int speccount = totalelement -1;

				//Select the specification from the specification drop down
				select1.selectByIndex(speccount);			

				//Get the text for Specification
				Select selectspec= new Select(driver.findElement(specificationdropdown));
				String specDelete= selectspec.getFirstSelectedOption().getText();
				
				//Created Specification name is
				System.out.println("The specification which got created is" + specDelete );
				
				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Delete Specification option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Delete Spec')]"), "dropdown", "Delete Spec");
				//Wait for 1 second
				Thread.sleep(1000);
				//Click ok on Delete Confirmation pop up
				driver.switchTo().alert().accept();
				
				//Check that Delete Specification present or not present in Specification drop down
				Select select = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(specDelete,options);
				//Verify that Under Review BOM is not visible in BOM Drop down list
				Assert.assertFalse(bVal);
				log.info("Does User is able to view Deleted BOM: " + bVal);

			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				
				if(data[14].equalsIgnoreCase("Yes")){
					
					//Select In Work Specification
					CommonFunctions.enterTextInTextbox(specificationdropdown, supplierreleasedspec);
					//Select specificaiton drop down
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k =j+1;
					//Click on Action menu
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
					
					//Check that Deleted BOM is visible or not
					Select select = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal);
					log.info("Does User is able to see Delete Spec option: " + bVal);
					
					
				}
				
				
				else {
				//Select In Work Specification
				CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);
				//Select Specification drop down
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				System.out.println(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"));
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				
				//Check that Deleted BOM is visible or not
				Select select = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
				log.info("Does User is able to see Delete Spec option: " + bVal);
			}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_Specifications()", e);
			return false;
		}
		return true;
	}


	//Function consist scenario : General Attributes:Read_View
	public static boolean generalAttributes_Read(String[] data) throws Exception{
		try{		
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
			//CommonFunctions.clickButtonOrLink(plusSign, "Plus sign");
			if(data[3].contains("generalAttributesRead")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				if(data[14].equalsIgnoreCase("Yes")){
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, supplierreleasedspec);
				
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k =j+1;
					//Verify if General Attribute tab size is not 0
					if(driver.findElements(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select//following::td[7][contains(text(),'General Attributes')]")).size() != 0){
						String GALabel=driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select//following::td[7][contains(text(),'General Attributes')]")).getText();
						System.out.println(GALabel);
						System.out.println(" General Attributes:");
						//Verify that General attribute table name is displaying correctly
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");					
					}
					//Verify that General Attribute tab size is 0
					else if (driver.findElements(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select//following::td[7][contains(text(),'General Attributes')]")).size() == 0){
						log.error("General Attributes label is Absent");
						fail=true;
					}
					
				}
				}
				
				else {
				//Select the In Work specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);			
				//Select Specification drop down
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				//Verify that General Attribute tab size is not zero
				if(driver.findElements(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select//following::td[7][contains(text(),'General Attributes')]")).size() != 0){
					String GALabel=driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select//following::td[7][contains(text(),'General Attributes')]")).getText();
					System.out.println(GALabel);
					System.out.println(" General Attributes:");
					//Verify that General attribute tab name is showing correctly
					Assert.assertEquals(GALabel, " General Attributes:");
					log.info("General Attributes label is Present");
				}
				//General Attribute tab size is sero
				else if (driver.findElements(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select//following::td[7][contains(text(),'General Attributes')]")).size() == 0){
					log.error("General Attributes label is Absent");
					fail=true;
				}
				}
			}
			else if(data[3].contains("generalAttributesRead")&& data[4].equalsIgnoreCase("No")){
				
				//Select the In Work specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);
				//General attribute table size is not zero
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					System.out.println("General Attirbutes label is Present");
					log.error("General Attirbutes label is Present");
					fail=true;
				}
				//General attribute table size is zero
				else if (driver.findElements(Product.labelGeneralAttri).size() == 0){
					log.info("General Attirbutes label is Absent");
				}
			}
		
		}catch(Exception e){ fail=true;
		log.error("Exception in generalAttributes_Read()", e);
		return false;
		}
		return true;
	}
	
	public static boolean generalAttirbutes_Update(String[] data) throws Exception{
		try{
			//Search product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
			if(data[3].contains("generalAttirbutesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);
				//Select Specification drop down
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				
				//Wait for update specification header page
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				
				//Update remark
				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecSave, "Save button");
				
				CommonFunctions.waitForVisibilityOfElement(ro_Primary);			
				
				//Capture the remark attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
								
				//Print the reamrk in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				//Verify that remark updated in edit page and in detail page are same
				if(varData){
					Assert.assertEquals(data[13], remarkdetailpage);
				}
				else{
					Assert.assertEquals(data[8], remarkdetailpage);
				}
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);			
				}
			
			else if(data[3].contains("generalAttirbutesUpdate")&& data[4].equalsIgnoreCase("No")){
				
				if(data[14].equalsIgnoreCase("Yes")){
					
					//Select the under review specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, "E4925-Z-089");
					
					Select selectspec=new Select(driver.findElement(specificationdropdown));			
					//Get the text for first selected Specification
					String selectedspec= selectspec.getFirstSelectedOption().getText();
					
					//Print the selected Specification text
					System.out.println("The Selected Specfication is: " + selectedspec);

					//Get the list of specification value drop down
					List<WebElement> list = selectspec.getOptions();
					log.info(list.size());
					int indexSpec= (list.size())-1;
					for(int i=0;i<list.size();i++){
						System.out.println(list.get(i).getText());
						System.out.println(selectspec.getFirstSelectedOption().getText());
						if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
							System.out.println("The index of the selected option is: "+i);
							j=i;
							break;
						}
					}
					int k =j+1;
					//Click on Action Menu
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
					//Click on Update Spec option
					CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[4]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
					//Wait for Update Specification header
					CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);
					
					//Verify that the attributes in General attribute are not editable
					Assert.assertEquals(driver.findElements(vendorUpdateRemarks).size(), 1, "Remark is not editable");
					Assert.assertEquals(driver.findElements(vendorUpdateWave).size(), 1, "Wave is not editable");
					Assert.assertEquals(driver.findElements(vendorUpdateStatus).size(), 1, "Status is not editable");
						
					//Click on Save Button
					CommonFunctions.clickButtonOrLink(updatespecCancel, "Cancel button");			
					
					log.info("Vendor User has Update spec action available");										
				}
				else {
				
				//Select the In Work specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);
				
				Select selectspec=new Select(driver.findElement(specificationdropdown));			
				//Get the text for first selected Specification
				String selectedspec= selectspec.getFirstSelectedOption().getText();
				
				//Print the selected Specification text
				System.out.println("The Selected Specfication is: " + selectedspec);

				//Get the list of specification value drop down
				List<WebElement> list = selectspec.getOptions();
				log.info(list.size());
				int indexSpec= (list.size())-1;
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getText());
					System.out.println(selectspec.getFirstSelectedOption().getText());
					if(list.get(i).getText().equals(selectspec.getFirstSelectedOption().getText())){
						System.out.println("The index of the selected option is: "+i);
						j=i;
						break;
					}
				}
				int k =j+1;
				//Get the option available in action Menu
				Select selectactionmenu = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> actionmenuoption =  selectactionmenu.getOptions();
				
				//Verify that Update Spec button present or not
				boolean bVal= CommonFunctions.dropDownOptionVerification(data[9], actionmenuoption);
				
				//Verify that In Work BOM is not visible
				Assert.assertFalse(bVal);
				log.info("Is In Update button is visible for selected specification: " + bVal);
				}
			}			
		}
		catch(Exception e){ fail=true;
		log.error("Exception in generalAttirbutes_Update()", e);
		return false;
		}
		return true;
	}
	
	public static boolean searchProduct(String[] data) throws Exception{
		try{
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("headerframe");
			//Wait for visibility of search Product
			CommonFunctions.waitForVisibilityOfElement(Product.searchProduct);
			//Clear any existing value in search product
			driver.findElement(Product.searchProduct).clear();
			//Enter value in search Product
			CommonFunctions.enterTextInTextbox(Product.searchProduct, data[5]);
			if(CommonFunctions.waitForElementTobeClickable(Product.searchIcon))
				//Click on Search Button
				CommonFunctions.clickButtonOrLink(Product.searchIcon, "Btn", "SearchButton");
		}catch(Exception e){ fail=true;
		log.error("Exception in searchProduct()", e);
		return false;
		}
		return true;
	}
	//Function consist scenario : Click on detail tab
	public static boolean clickSpecificationTab(String[] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click Specification tab
				CommonFunctions.waitForVisibilityOfElement(specificationsTablink);
				CommonFunctions.clickButtonOrLink(specificationsTablink, "link", "specifications tab");
					//Click on Season drop down
					CommonFunctions.clickButtonOrLink(Product.detailPageSeasonDD, "Season dropdown");
					System.out.println(By.xpath("//*[@id='splId']/option[contains(text(),'"+data[6]+"')]"));
					//Select the season
					driver.findElement(By.xpath("//*[@id='splId']/option[contains(text(),'"+data[6]+"')]")).click();
					
					Select selectsourcevendor= new Select(driver.findElement(selectSource));
					selectsourcevendor.selectByVisibleText(data[12]);
					CommonFunctions.handleAlertPopUp();
	
		return true;
		}
		catch(Exception e){ fail=true;
		log.error("Exception in clickSpecificationTab()", e);
		return false;
	}
		
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



	/**
	 * Saves the screenshot to the specified Location
	 *
	 * @param filePathToSave
	 */
	public static void getScreenShot(String filePathToSave,String imageName){
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screen = new Rectangle(0, 0, screenSize.width, screenSize.height);
		Robot robot;
		try {
			robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screen);
			File capturedScreenshotFile = new File (filePathToSave);
			try {
				ImageIO.write ( image, "png", capturedScreenshotFile );
				System.out.println("Screenshot saved at ("+filePathToSave+")");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch(AWTException e) {
			e.printStackTrace();
		}
	}

	public String getClassName(){
		String className = this.getClass().getSimpleName(); 
		System.out.println("Name:"+className);
		return className;
	}

	@AfterMethod
	public void reporterdataSetResult() throws Exception{
		if(skip)
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
			takeScreenshot();
		}
		else
			Utility.dataSetResult(suiteSecurityXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}

	public static void takeScreenshot() {
		try {
			File scrFile = ((TakesScreenshot)SeleniumDriver.driver).getScreenshotAs(OutputType.FILE);
			java.util.Date date= new java.util.Date();
			String fileName = new Timestamp(date.getTime()) + ".png";
			System.out.println(fileName);
			fileName = fileName.replace(':', ' ');
			System.out.println(fileName);
			fileName = fileName.replace('-', ' ');
			System.out.println(fileName);
			String page = SeleniumDriver.driver.getTitle();
			String testname=sp.getClassName();
			fileName = testname+username+fileName.trim();
			System.out.println(fileName);
			FileUtils.copyFile(scrFile, new File("C:\\screenshots\\" + fileName));
			SeleniumDriver.log.info("Taking screenshot: "+ "<a href='screenshots\\" + fileName + "'><img src='screenshots\\" + fileName + "' /></a>");
		}
		catch (IOException e) {
			SeleniumDriver.log.error("IOException, cannot take screenshot", e);
		}
		catch (Exception e) {
			SeleniumDriver.log.error("Error taking screenshot", e);
		}
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
