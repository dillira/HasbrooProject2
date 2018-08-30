package com.hasbro.security;
/**
 * @author Anjali Gupta
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


public class Specifications extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String strPrimary ;
	static String inWorkspec;
	static String Underreviewspec;
	static String supplierreleasedspec;
	static String reworkspec;
	static String productionreleasedspec;
	static String cancelledspec;
	Date date = new Date();
	String imgPath= "C:\\SeleniumScreen\\INC0290848_"+date.getTime();
	boolean failure = false;
	public static String username;
	static boolean varData;

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
	public static By RO_specificationChangeHistory= By.id("hbSpecificationChangeHistory");
	public static By RO_Primary= By.xpath("//div[contains(@id,'plus')]/span");
	public static By deleteSpec= By.xpath("//table[@class='PAGEHEADINGTITLE_BG']//select/option[contains(text(),'Delete Spec')]");
	public static By textAfertDelete= By.xpath("//div[contains(@class,'HEADING1')]/text()[preceding-sibling::br and following-sibling::br]");
	public static By plusSign=By.xpath("//div[contains(@id,'specTitle')]/a"); //div[contains(@id,'plus')]/a/img
	public static By Specificationtitle=By.xpath("//table[@class='PAGEHEADINGTITLE_BG']/tbody/tr/td[1]/div[1]/a/following-sibling::text()[1]");
	public static By specificationC= By.xpath("//table[@class='PAGEHEADINGTITLE_BG']");
	public static By specificationdropdown= By.xpath(".//*[@id='contextSpecId']");
	public static By specificationstatusinput = By.xpath("//td[contains(text(),'Specification Status')]//following::select[1]");
	public static By updatespecsave = By.xpath("//a[text()='Save']");
	public static By specsummary = By.xpath("//a[text()='Summary']");
	public static By updatespecificationheader = By.xpath("//td[contains(text(),'Update Specification')]");
	public static By selectSource= By.id("sourcingConfigId");	


	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	static String Selectedspec;
	static int j;

	static Specifications sp = new Specifications();

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
			log.debug("Inside testcase for Security Product");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			System.out.println("col4 :" + data[4]);
			username=data[0];
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
	
			//Create Under Review Specification
			if(data[3].equalsIgnoreCase("Createunderreview"))
			{ Create_Specifications_Underreview(data); }

			//Create Super_Released Specification
			if(data[3].equalsIgnoreCase("Createsupplierreleased"))
			{ Create_Supplier_Realeased(data); }

			//Create Rework Specification

			if(data[3].equalsIgnoreCase("Createrework"))
			{ Create_Rework(data); }

			//Create Production Released Specification
			if(data[3].equalsIgnoreCase("Createproductionreleased"))
			{ Create_production_released(data); }

			//Create cancelled Specification
			if(data[3].equalsIgnoreCase("Createcancelled"))
			{ Create_cancelled(data); }
			
			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_Specifications(data); }
			
			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("CreateInWork"))
			{ create_InWork(data); }
			
			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Read"))
			{ read(data); }

			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("UpdateSpec"))
			{ update_Spec(data); }
			
			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("SpecificationUnderreviewUpdateSpec"))
			{ Specification_Underreview_UpdateSpec(data); }

			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("SpecificationsupplierreleasedUpdateSpec"))
			{ Specification_Supplier_Released_UpdateSpec(data); }			
			
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_Specifications(data); }
			//Read view verification
			if(data[3].equalsIgnoreCase("GeneralAttirbutesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttirbutesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Specifications(data); }

			//Add or remove Component
			if(data[3].equalsIgnoreCase("Component"))
			{ Component_Specifications(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	public static boolean read(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Read")&& data[4].equalsIgnoreCase("Yes")){
		
				
				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				
				//Verify that In Work Specification is visible
				CommonFunctions.selectFromDropDownByVisibleText(specificationdropdown, inWorkspec);
				
				log.info("User is able to see In Work Specification: " + inWorkspec);

			}
			else if(data[3].contains("Createsupplierreleased")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
				log.info("User is not able to see specification drop down");
				
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in read()", e);
			return false;
		}
		return true;

	}
	
	//Function consist scenario : General Attributes:Read_View
		public static boolean update_Spec(String[] data) throws Exception{
			try{
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("UpdateSpec")&& data[4].equalsIgnoreCase("Yes")){
				//Select the under review specification from the specification dropdown
				CommonFunctions.enterTextInTextbox(specificationdropdown, "E4925-Z-020");
				
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
				
				CommonFunctions.waitForVisibilityOfElement(updatespecificationheader);

				//Identify the Wave attribute value
				String remarkname= driver.findElement(remarks).getText();
				
				System.out.println("The remarks value before modifying in update page:" + remarkname );
				
				//Clear the remark value
				driver.findElement(remarks).clear();
				

				if (remarkname.equals(data[8])){
					CommonFunctions.enterTextInTextbox(remarks, data[13]);
					varData=true;
				}
				else{
					CommonFunctions.enterTextInTextbox(remarks, data[8]);
					varData=false;
				}			

				//Capture the Wave attribute value
				//String remarkineditpage= driver.findElement(remarks).getText();
				
				//Print the wave in edit page
			//	System.out.println("The value of the remarks updated in Edit page is: " + remarkineditpage);
				
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(updatespecsave, "Save button");
				
				CommonFunctions.waitForVisibilityOfElement(RO_Primary);			
				
				//Capture the wave attribute value in detail page
				String remarkdetailpage= driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']")).getText();
				
				//System.out.println("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]//following::td[1]//*[@id='hbRemarks']");
				
				//Print the wave in edit page
				System.out.println("The name of the wave in detail page is: " + remarkdetailpage);
				if(varData)
					Assert.assertEquals(data[13], remarkdetailpage);
				else
					Assert.assertEquals(data[8], remarkdetailpage);
				
				log.info("The remark updated in edit page and in detail page are same:" + remarkdetailpage);
			
				}
				
				else if(data[3].contains("UpdateSpec")&& data[4].equalsIgnoreCase("No")){
					//Select the under review specification from the specification dropdown
					CommonFunctions.enterTextInTextbox(specificationdropdown, inWorkspec);

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

	public static String Create_Specifications_Underreview(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Createunderreview")&& data[4].equalsIgnoreCase("Yes")){

				//Click create New Specification
				CommonFunctions.clickButtonOrLink(createNewSpecification, "btn", "create New Specification");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingcreateNewSpecification));
				//Enter wave
				CommonFunctions.enterTextInTextbox(wave, data[7]);
				//Remarks
				CommonFunctions.enterTextInTextbox(remarks, data[8]);
				//Source:first Checkbox
				//CommonFunctions.clickButtonOrLink(sourcesFirstCheckbox, "CheckBox", "Source");
				//String soureCheckbox =driver.findElement(sourcesFirstCheckbox).getText();
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				//	strPrimary =driver.findElement(By.xpath("//table[@class='PAGEHEADINGTITLE_BG']/tbody/tr/td[1]/div[1]/a/following-sibling::text()[1]")).getText();
				strPrimary=driver.findElement(RO_Primary).getText();
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
				String Underreviewspec= select3.getFirstSelectedOption().getText();

				System.out.println("The specification which got created is" + Underreviewspec );

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Under Review");

				//Click on Save button
				driver.findElement(updatespecsave).click();

			}
			else if(data[3].contains("Createunderreview")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
				//Assert.assertFalse(strcreateNewSpec);
			}
			else{
				log.info("Create New is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in Create_Specifications_Underreview()", e);
		//	return false;
		}
		return  Underreviewspec;
	}

	public static String Create_Supplier_Realeased(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Createsupplierreleased")&& data[4].equalsIgnoreCase("Yes")){

				//Boolean strcreateNewSpec =driver.findElement(createNewSpecification).isDisplayed();

				//Click create New Specification
				CommonFunctions.clickButtonOrLink(createNewSpecification, "btn", "create New Specification");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingcreateNewSpecification));
				//Fill page
				//Enter wave
				CommonFunctions.enterTextInTextbox(wave, data[7]);
				//Remarks
				CommonFunctions.enterTextInTextbox(remarks, data[8]);
				//Source:first Checkbox
				//CommonFunctions.clickButtonOrLink(sourcesFirstCheckbox, "CheckBox", "Source");
				//String soureCheckbox =driver.findElement(sourcesFirstCheckbox).getText();
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				//	strPrimary =driver.findElement(By.xpath("//table[@class='PAGEHEADINGTITLE_BG']/tbody/tr/td[1]/div[1]/a/following-sibling::text()[1]")).getText();
				strPrimary=driver.findElement(RO_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				//System.out.println(strPrimary);
				//Assert.assertEquals(strPrimary,"Primary");

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

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Supplier Released");

				//Click on Save button
				driver.findElement(updatespecsave).click();

			}
			else if(data[3].contains("Createsupplierreleased")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
				//Assert.assertFalse(strcreateNewSpec);
			}
			else{
				log.info("Create New is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in Create_Supplier_Realeased()", e);
		//	return false;
		}
		return supplierreleasedspec;

	}

	public static String Create_Rework(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Createrework")&& data[4].equalsIgnoreCase("Yes")){

				//Boolean strcreateNewSpec =driver.findElement(createNewSpecification).isDisplayed();

				//Click create New Specification
				CommonFunctions.clickButtonOrLink(createNewSpecification, "btn", "create New Specification");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingcreateNewSpecification));
				//Fill page
				//Enter wave
				CommonFunctions.enterTextInTextbox(wave, data[7]);
				//Remarks
				CommonFunctions.enterTextInTextbox(remarks, data[8]);
				//Source:first Checkbox
				//CommonFunctions.clickButtonOrLink(sourcesFirstCheckbox, "CheckBox", "Source");
				//String soureCheckbox =driver.findElement(sourcesFirstCheckbox).getText();
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				//	strPrimary =driver.findElement(By.xpath("//table[@class='PAGEHEADINGTITLE_BG']/tbody/tr/td[1]/div[1]/a/following-sibling::text()[1]")).getText();
				strPrimary=driver.findElement(RO_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				//System.out.println(strPrimary);
				//Assert.assertEquals(strPrimary,"Primary");

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
				driver.findElement(updatespecsave).click();

			}
			else if(data[3].contains("Createrework")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
				//Assert.assertFalse(strcreateNewSpec);
			}
			else{
				log.info("Create New is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in Create_Rework()", e);
		//	return false;
		}
		return reworkspec;
	}

	public static String Create_production_released(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Createproductionreleased")&& data[4].equalsIgnoreCase("Yes")){

				//Boolean strcreateNewSpec =driver.findElement(createNewSpecification).isDisplayed();

				//Click create New Specification
				CommonFunctions.clickButtonOrLink(createNewSpecification, "btn", "create New Specification");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingcreateNewSpecification));
				//Fill page
				//Enter wave
				CommonFunctions.enterTextInTextbox(wave, data[7]);
				//Remarks
				CommonFunctions.enterTextInTextbox(remarks, data[8]);
				//Source:first Checkbox
				//CommonFunctions.clickButtonOrLink(sourcesFirstCheckbox, "CheckBox", "Source");
				//String soureCheckbox =driver.findElement(sourcesFirstCheckbox).getText();
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				//	strPrimary =driver.findElement(By.xpath("//table[@class='PAGEHEADINGTITLE_BG']/tbody/tr/td[1]/div[1]/a/following-sibling::text()[1]")).getText();
				strPrimary=driver.findElement(RO_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				//System.out.println(strPrimary);
				//Assert.assertEquals(strPrimary,"Primary");

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

				System.out.println("total element in Specification dropdown" +totalelement );

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Production Released");

				//Click on Save button
				driver.findElement(updatespecsave).click();

			}
			else if(data[3].contains("Createproductionreleased")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
				//Assert.assertFalse(strcreateNewSpec);
			}
			else{
				log.info("Create New is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in Create_production_released()", e);
		//	return false;
		}
		return productionreleasedspec;

	}

	public static String Create_cancelled(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Createcancelled")&& data[4].equalsIgnoreCase("Yes")){

				//Boolean strcreateNewSpec =driver.findElement(createNewSpecification).isDisplayed();

				//Click create New Specification
				CommonFunctions.clickButtonOrLink(createNewSpecification, "btn", "create New Specification");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingcreateNewSpecification));
				//Fill page
				//Enter wave
				CommonFunctions.enterTextInTextbox(wave, data[7]);
				//Remarks
				CommonFunctions.enterTextInTextbox(remarks, data[8]);
				//Source:first Checkbox
				//CommonFunctions.clickButtonOrLink(sourcesFirstCheckbox, "CheckBox", "Source");
				//String soureCheckbox =driver.findElement(sourcesFirstCheckbox).getText();
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				//	strPrimary =driver.findElement(By.xpath("//table[@class='PAGEHEADINGTITLE_BG']/tbody/tr/td[1]/div[1]/a/following-sibling::text()[1]")).getText();
				strPrimary=driver.findElement(RO_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				//System.out.println(strPrimary);
				//Assert.assertEquals(strPrimary,"Primary");

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

				System.out.println("total element in Specification dropdown" +totalelement );

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				//select Update Specification
				select2.selectByVisibleText(data[9]);

				//select under review state from Update Spec UI
				CommonFunctions.selectFromDropDownByVisibleText(specificationstatusinput, "Canceled");

				//Click on Save button
				driver.findElement(updatespecsave).click();

			}
			else if(data[3].contains("Createcancelled")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
				//Assert.assertFalse(strcreateNewSpec);
			}
			else{
				log.info("Create New is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in Create_cancelled()", e);
		//	return false;
		}
		return cancelledspec;

	}

	public static boolean Specification_Underreview_readView(String[] data) throws Exception{
		try{
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

	//Function consist scenario : General Attributes:Read_View
	public static boolean Specification_Underreview_UpdateSpec(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");


			//Select the under review specification from the specification dropdown
			CommonFunctions.enterTextInTextbox(specificationdropdown, "E0856-Z-002"); // use variable and remove this

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
			System.out.println(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+j+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"));
			CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+j+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");


		}catch(Exception e){ 
			fail=true;
			log.error("Exception in Create_cancelled()", e);
		}
		return true;

	}

	public static boolean Specification_Supplier_Released_UpdateSpec(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			//Select the under review specification from the specification dropdown
			CommonFunctions.enterTextInTextbox(specificationdropdown, data[11]);

			Select selectspec01=new Select(driver.findElement(specificationdropdown));

			//Get the text for first selected specification
			String selectedspec= selectspec01.getFirstSelectedOption().getText();

			//Print the selected specification text
			System.out.println("The Selected Specfication is" + selectedspec);

			//Get the option from specification drop down
			List<WebElement> list = selectspec01.getOptions();


			for(int i=0;i<list.size();i++){
				if(list.get(i).getText().equals(selectspec01.getFirstSelectedOption().getText())){
					System.out.println("The index of the selected option is: "+i);
					break;
				}

				System.out.println("The index of the selected option is: "+i);

				//Select the required specification
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+i+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));					

				if(data[3].contains("SpecificationsupplierreleasedUpdateSpec")&& data[4].equalsIgnoreCase("Yes")){//Read_View

					//Check and verify that Update spec exist in Actions menu or not
					List<WebElement> options = select2.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
					//	dropDownOptionVerification
					Assert.assertTrue(bVal);

				}				
				else if(data[3].contains("SpecificationsupplierreleasedUpdateSpec")&& data[4].equalsIgnoreCase("No")){
					//Check and verify that Update spec exist in Actions menu or not
					List<WebElement> options = select2.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
					//	dropDownOptionVerification
					Assert.assertFalse(bVal);

				}

			}
		}
		catch(Exception e){ fail=true;
		log.error("Exception in Create_cancelled()", e);
		//	return false;
		}
		return true;			
			
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
	public static String Create_Specifications(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){

				//Create Specification
				createDefaultSpecification(data);
				strPrimary=driver.findElement(RO_Primary).getText();
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
				System.out.println(strPrimary);
				Assert.assertEquals(strPrimary,"Primary");
			}
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
				log.info("User does not have create New Specification button");
			}

		}catch(Exception e){ fail=true;
		log.error("Exception in Create_Specifications()", e);
		//	return false;
		}
		return strPrimary;
	}

	//Prerequisite: Create Specification
		public static String create_InWork(String[] data) throws Exception{
			try{
				searchProduct(data);
				//Click on Specification
				clickSpecificationTab(data);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				if(data[3].contains("CreateInWork")&& data[4].equalsIgnoreCase("Yes")){

					createDefaultSpecification(data);
					strPrimary=driver.findElement(RO_Primary).getText();
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(addNewSpecification));
					System.out.println(strPrimary);
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
					Assert.assertEquals(driver.findElements(createNewSpecification).size(), 0, "Create New Specification button does not exist");
					log.info("Create New Specification button does not exist");
				}

			}catch(Exception e){ fail=true;
			log.error("Exception in create_InWork()", e);
			//	return false;
			}
			return inWorkspec;
		}



	public static boolean SetState_Specifications(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(plusSign, "Plus sign");

			if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.enterTextInTextbox(specificationAction, data[9]);
				//driver.findElement(specificationAction).sendKeys(Keys.ENTER);

				String valULCSAfterChange=specification_selectUpdateLifecycleState(data);
				Thread.sleep(1000);
				//Click on Update
				CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
				Thread.sleep(1000);
				CommonFunctions.clickButtonOrLink(plusSign, "Plus sign");
				//verification
				String strRO_specificationChangeHistory = driver.findElement(RO_specificationChangeHistory).getText();
				System.out.println("strRO_specificationChangeHistory:" + strRO_specificationChangeHistory);
				String[] parts = strRO_specificationChangeHistory.split("to");
				System.out.println("parts[0]"+parts[0]);
				System.out.println("parts[1]"+parts[1]);
				System.out.println(parts[1].trim());
				//	System.out.println("strRO_specificationChangeHistory: "+strRO_specificationChangeHistory);
				System.out.println("valULCSAfterChange: "+valULCSAfterChange);

				Assert.assertEquals(parts[1].trim(), valULCSAfterChange);
			}
			else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
				//Check that Change state option is not present in Action Menu
				Select select = new Select(driver.findElement(specificationAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
				//Verification that change state does not exist in Action drop down
				Assert.assertFalse(bVal);
			}
			else
				log.info("SetState or chageState is not applicable(NA)");
		}catch(Exception e){ fail=true;
		log.error("Exception in SetState_Specifications()", e);
		return false;
		}
		return true;
	}

	public static boolean delete_Specifications(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				
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
				log.info("Does User is able to view Delete BOM: " + bVal);

			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				//Select In Work Specification
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
				System.out.println(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"));
				//Click on Action menu
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select"), "dropdown", "Action");
				//Click on Update Spec option
				CommonFunctions.clickButtonOrLink(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select[1]/option[contains(text(),'Update Spec')]"), "dropdown", "Update Spec");
				
				//Check that Deleted BOM is visible or not
				Select select = new Select(driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[3]/td/table["+k+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_Specifications()", e);
			return false;
		}
		return true;
	}


	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			//Search Product
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(plusSign, "Plus sign");
			if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
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
			else if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("No")){
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
		}catch(Exception e){ fail=true;
		log.error("Exception in verifyGeneralAttributesReadView()", e);
		return false;
		}
		return true;
	}
	//Function consist scenario : General Attributes://Update
	public static boolean Component_Specifications(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			//Create Specification
			Create_Specifications(data);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			if(data[3].contains("Component")&& data[4].equalsIgnoreCase("Yes")){//Update


				//Click create New Specification
				CommonFunctions.clickButtonOrLink(createNewSpecification, "btn", "create New Specification");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingcreateNewSpecification));
				//Fill page
				//Enter wave
				CommonFunctions.enterTextInTextbox(wave, data[7]);
				//Remarks
				CommonFunctions.enterTextInTextbox(remarks, data[8]);
				//Source:first Checkbox
				CommonFunctions.clickButtonOrLink(sourcesFirstCheckbox, "CheckBox", "Source");
				String soureCheckbox =driver.findElement(sourcesFirstCheckbox).getText();
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");


				//Select the last value from Specification value drop down
				Select select1 = new Select(driver.findElement(specificationdropdown));
				List<WebElement> options1 = select1.getOptions();
				int totalelement= options1.size();

				System.out.println("total element in Specification dropdown" +totalelement );

				//Select Add Existing Component and Remove Existing Component exist from actions drop down
				Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table["+totalelement+"]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select2.getOptions();

				System.out.println("Option available from Actions drop down" + options);
				//Verify that Add Existing Component and Remove Existing Component exist from actions drop down
				boolean bVal1=CommonFunctions.dropDownOptionVerification(data[9],options);
				boolean bVal2=CommonFunctions.dropDownOptionVerification(data[10],options);

				//Verify that Add Existing Component and Remove Existing Component is present
				Assert.assertTrue(bVal1);
				Assert.assertTrue(bVal2);

			}
			else if(data[3].contains("Component")&& data[4].equalsIgnoreCase("No")){

				//Select first specification from the drop down list
				Select firstspecification= new Select(driver.findElement(specificationdropdown));
				firstspecification.selectByIndex(2);

				//Select Add Existing Component and Remove Existing Component exist from actions drop down
				Select select = new Select(driver.findElement(By.xpath(".//*[@id='contentDiv']/table/tbody/tr[3]/td/table[2]/tbody/tr[1]/td/table/tbody/tr/td[3]/select")));
				List<WebElement> options = select.getOptions();
				boolean bVal3=CommonFunctions.dropDownOptionVerification(data[9],options);
				boolean bVal4=CommonFunctions.dropDownOptionVerification(data[10],options);

				//Verify that Add Existing Component and Remove Existing Component does not present
				Assert.assertFalse(bVal3);
				Assert.assertFalse(bVal4);
			}


			{
				log.info("For this Component_Specifications is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in Component_Specifications()", e);
		return false;
		}
		return true;
	}
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			searchProduct(data);
			//Click on Specification
			clickSpecificationTab(data);
			if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				Select select = new Select(driver.findElement(specificationAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
				//	dropDownOptionVerification
				Assert.assertTrue(bVal);
			}
			else if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(specificationAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[9],options);
				//	dropDownOptionVerification
				if(!bVal)
					Assert.assertFalse(bVal);
				else{
					CommonFunctions.enterTextInTextbox(specificationAction, data[9]);
					//	System.out.println(driver.findElements(ROProductName).size());
					//	Assert.assertEquals(driver.findElements(ROProductName).size(), 1, "General Attributes Not Editable"); 
				}
			}	
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in verifyGeneralAttributesUpdate()", e);
		return false;
		}
		return true;
	}
	//This funcion is to select Update Lifecycle State	
	public static String specification_selectUpdateLifecycleState(String[] data) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			String strSupplierReleased ="Supplier Released";
			if(valULCS.contains("In Work")){
				//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState,strSupplierReleased);
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, strSupplierReleased);
			}
			else if(valULCS.contains("Under Review")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "In Work");
			}
			else if(valULCS.contains("Canceled")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Rework");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Rework");
			}
			else if(valULCS.contains("Production Released")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Rework");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Rework");
			}
			else if(valULCS.contains("Supplier Released")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Rework");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Rework");
			}
			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in selectUpdateLifecycleState()", e);
		}
		return valULCSAfterChange;
	}
	public static boolean searchProduct(String[] data) throws Exception{
		try{
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("headerframe");
			CommonFunctions.waitForVisibilityOfElement(Product.searchProduct);
			driver.findElement(Product.searchProduct).clear();
			CommonFunctions.enterTextInTextbox(Product.searchProduct, data[5]);
			if(CommonFunctions.waitForElementTobeClickable(Product.searchIcon))
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
				CommonFunctions.waitForVisibilityOfElement(specificationsTablink);
				CommonFunctions.clickButtonOrLink(specificationsTablink, "link", "specifications tab");

					CommonFunctions.clickButtonOrLink(Product.detailPageSeasonDD, "Season dropdown");
					System.out.println(By.xpath("//*[@id='splId']/option[contains(text(),'"+data[6]+"')]"));
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
