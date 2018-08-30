package com.hasbro.security;
/**
 * @author Anjali Gupta
 *
 *Column 2 is added to know which type of vendor BOM we are creating. 
 *Column 10 is added to identify that test is running with vendor user or non-vendor user.
 *Column 11 is added to know which wave we are selecting
 *Column 12 is added to know which assortment solid product we are using to create Product Vendor BOM.
 *Since Vendor user does not able to see Library>Country link. So we need to create new vendor BOM and browse to its detail page
 * and expand the Header attribute. Vendor user needs to select the Country value to browse to the existing country detail page and perform all the Country validation. 
 *
 *
 */

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;



public class Country extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	public static String bomname;
	static Actions action;
	static String strbomInWork;

	public static By newLink= By.xpath("//a[text()='New']");
	public static By headingCreateNewCountry= By.xpath("//td[contains(text(),'Create New Country')]");
	public static By RO_LCState= By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By search= By.id("SearchButton2");
	public static By searchPageHeading= By.xpath("//span[contains(text(),'Search Results for')]");
	public static By country=By.linkText("Country");
	public static By countrysearchname= By.xpath("//input[contains(@id,'LCSCOUNTRY_ptc_str')]");
	public static By countryname=By.xpath("//*[@id='null']/div/div[2]/table/tbody/tr/td[1]//following::input[1]");
	public static By SAPCountryAbbreviation=By.xpath("//*//*[contains(@id,'ptc_str')]//following::input[1]");
	public static By countryfirstsearch= By.xpath("//a[@id='COL0']/following::a[5]");
	public static By countrygeneralattribute=By.xpath("//td[contains(text(),'General Attributes')]");
	public static By countryidentification=By.xpath("//td[contains(text(),'Country Identification')]");
	public static By associateddoc=By.xpath("//td[contains(text(),'Associated Documents:')]");
	public static By systeminfo=By.xpath("//td[contains(text(),'System Information:')]");
	public static By countryactions=By.xpath("//td[contains(text(),'Actions:')]//select[1]");
	public static By countrystateupdate=By.xpath(".//*[@id='contentDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a[1]");
	public static By countrydelete= By.xpath(".//*[@id='contentDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a[1]");
	public static By countrynameupdate= By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By countryname2= By.xpath("//td[contains(text(),'Name')]//following::td[1]");
	public static By countryupdatepage= By.xpath("//td[contains(text(),'Update')]");
	public static By save=By.xpath(".//*[@id='contentDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a[1]");
	public static By SAPCountryAbbreviationread=By.xpath(".//*[@id='hbSAPCountryAbbr']");
	public static By SAPCountryAbbreviationupdate=By.xpath("//td[contains(text(),'SAP Country Abbreviation')]//following::input[1]");
	public static By countrynameread=By.xpath(".//*[@id='null']/div/div[2]/table/tbody/tr/td[2]");
	public static By selectSource= By.id("sourcingConfigId");
	public static By selectSpecification= By.id("contextSpecId");
	public static By addNewbomTab= By.xpath(".//*[@id='ADD_PAGETab']/a");
	public static By bomtypeid= By.id("bomTypeId");
	public static By initializebom= By.xpath("//a[contains(text(),'Initialize BOM')]");
	public static By headingCreatebom= By.xpath("//td[contains(text(),'Create BOM')]");
	public static By colorway= By.xpath("//td[contains(text(),'Colorway')]//following::select[1]");
	public static By wave= By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By currency= By.xpath("//td[contains(text(),'Currency')]//following::select[1]");
	public static By Factory = By.xpath("//a[contains(text(),'Factory')]");
	public static By factoryFindSearch = By.xpath(".//*[@id='SearchButton1']");
	public static By chooseFactory= By.xpath("//a[contains(text(),'(choose)')]");
	public static By compOrLoca= By.id("r1_partName");
	public static By inputCompOrLoca= By.xpath("//div[@id='partNameSourceDiv']/input");
	public static By btnSaveAndCheckIn= By.xpath("//a[text()='Save and Check In']");
	public static By headerAttributes= By.xpath("//div[@id='genDetails_plus']/a[2]");
	public static By bomId= By.xpath("//*[@id='bomId']");
	public static By headerAttributesPlus= By.xpath("//div[@id='genDetails_plus']/a[1]/img");
	public static By cooValue= By.xpath("//td[contains(text(),'COO')]//following::a[1]");


	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;


	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSupplier(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Country Security");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
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

			/**************Country***********************************/
			//Create Country
			if(data[3].equalsIgnoreCase("CreateCountry"))
			{ Create_Country(data); }

			//View Country
			if(data[3].equalsIgnoreCase("verifyCountryReadView"))
			{verifyCountryReadView(data);}

			//Set State Country
			if(data[3].equalsIgnoreCase("SetState_Country"))
			{SetState_Country(data);}

			//Delete Country
			if(data[3].equalsIgnoreCase("DeleteCountry"))
			{Delete_Country(data);}

			//Read Country Identification
			if(data[3].equalsIgnoreCase("Read_Country_Identification"))
			{Read_Country_Identification(data);}

			//Read Country Identification
			if(data[3].equalsIgnoreCase("Update_Country_Identification"))
			{Update_Country_Identification(data);}

			//Read_General_Attribute
			if(data[3].equalsIgnoreCase("Read_Country_General_Attribute"))
			{Read_Country_General_Attribute(data);}

			//Update_General_Attribute
			if(data[3].equalsIgnoreCase("Update_Country_General_Attribute"))
			{Update_Country_General_Attribute(data);}
			
			//Create Product Vendor BOM
			if(data[3].equalsIgnoreCase("CreateInWork"))
			{ createInWork_bom(data); }


		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	public static boolean navigateToMaterialTab(String [] data) throws Exception{
		try{
		//Search Product
		CommonProjectFunctions.searchProduct(data[12]);
		//Click on Specification
		CommonProjectFunctions.clickSpecificationTab(data[6]);
		//Click on Material tab
		CommonProjectFunctions.clickMaterialsTab();

		//Select Source
		Select selectsource= new Select(driver.findElement(selectSource));
		selectsource.selectByVisibleText(data[8]);
		CommonFunctions.handleAlertPopUp();
		
		//Select Specification
		CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
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
			//Select currency
			CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);
			//Select Currency
			CommonFunctions.selectFromDropDownByVisibleText(currency, data[7]);
			
			//Get the Window Handler of parent Window
			String parentWindow= driver.getWindowHandle();
			System.out.println("The ID of parent Window is: " + parentWindow);
			
			//Select Factory
			driver.findElement(Factory).click();
			
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
	public static String createInWork_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			//Click on Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			
			//Click on Save and Check in Button
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			
			//Click on Header Attribute button
			CommonFunctions.waitForVisibilityOfElement(headerAttributes);
			//Get the name of the selected BOM
			strbomInWork=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			//Remove the trailing and leading white space
			strbomInWork=strbomInWork.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:"+strbomInWork);

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateInWork_bom()", e);
	
		}
		return strbomInWork;
	}
	
	
	//Create Country
		public static boolean Create_Country(String[] data) throws Exception{
			try{

				if(data[3].contains("CreateCountry")&& data[4].equalsIgnoreCase("Yes")){								
					
					driver.navigate().refresh();
					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");					
					
					//Click on Country
					driver.findElement(country).click();
					
					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					//Wait till Create New Country Header is not displaying in the page
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
					
					//Get the unique name for Country
					String countryname01 = "Country_In_Work" + CommonFunctions.getRandomString(4);
					
					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, countryname01);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					
					//Get text for country name
					String countryname02=driver.findElement(countrynameread).getText();

					//Compare the country name value
					Assert.assertEquals(countryname01, countryname02);

					//Get text for SAP Abbreviation Country
					String sapcountryabb01=driver.findElement(SAPCountryAbbreviationread).getText();

					//Compare the SAP Abbreviation Country value
					Assert.assertEquals(sapcountryabb01, data[7]);
					
					//Country got created
					log.info("Country got created successully: " + countryname01);
					
				}
				else if(data[3].contains("CreateCountry")&& data[4].equalsIgnoreCase("No")){
					
					if(data[10].equalsIgnoreCase("Yes")){
						
					//Verify that Library>Country option is not present
						Assert.assertEquals(driver.findElements(country).size(), 0, "Country link is not visible");
						log.info("Library>Country link is not visible");			
					}
					
					else {
					
					//Refresh the page
					driver.navigate().refresh();
					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
											
					//Click on Country
					driver.findElement(country).click();
						
					//Verify that Country Creation New link is not present
					Assert.assertEquals(driver.findElements(newLink).size(), 0, "New Not Visible");
					
					//User does not have Country creation right
					log.info("User does not have country creation rights");
					}			
				}
			}

			catch(Exception e){ 
				fail=true;
				log.error("Create_Country()", e);
				return false;
			}
			return true;

		}

		public static boolean verifyCountryReadView(String[] data) throws Exception{
			try{

				if(data[3].contains("verifyCountryReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View
					
					if(data[10].equalsIgnoreCase("Yes")){
						
						//Browse to Material Tab
						navigateToMaterialTab(data);
						
						//Select In Work BOM
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						
						//Expand Header attribute
						CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
						
						//Click on COO
						CommonFunctions.clickButtonOrLink(cooValue, "COO Value");

						//General Attribute table
						String CountryGALabel=driver.findElement(countrygeneralattribute).getText();

						//Print screen
						System.out.println(CountryGALabel);

						//Print General Attribute
						System.out.println("General Attributes:");
						//Verify that General Attribute table exist
						Assert.assertEquals(CountryGALabel.trim(), "General Attributes:");
						log.info("General Attributes label is Present");

						//Country Identification
						String CI=driver.findElement(countryidentification).getText();
						System.out.println(CI);
						System.out.println("Country Identification");
						//Verify that Country Identification table exist
						Assert.assertEquals(CI, "Country Identification");
						log.info("Country Identification label is Present");

						//Associated document
						String associateddocument=driver.findElement(associateddoc).getText();
						System.out.println(associateddocument);
						System.out.println("Associated Documents:");
						//Verify that Associated document table exist
						Assert.assertEquals(associateddocument, "Associated Documents:");
						log.info("Associated Documents: label is Present");

						//System Information
						String systeminformation=driver.findElement(systeminfo).getText();
						System.out.println(systeminformation);
						System.out.println("System Information:");
						//Verify that System Information table exist
						Assert.assertEquals(systeminformation, "System Information:");
						log.info("System Information: label is Present");
			
					}
					
					else {
						
					//refresh the page
					driver.navigate().refresh();

					//switch to default frame
					driver.switchTo().defaultContent();

					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");
					
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
					
					//Click on Country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();
					//General Attribute table
					String CountryGALabel=driver.findElement(countrygeneralattribute).getText();

					//Print screen
					System.out.println(CountryGALabel);

					//Print General Attribute
					System.out.println("General Attributes:");
					//Verify that General Attribute table exist
					Assert.assertEquals(CountryGALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");

					//Country Identification
					String CI=driver.findElement(countryidentification).getText();
					System.out.println(CI);
					System.out.println("Country Identification");
					//Verify that Country Identification table exist
					Assert.assertEquals(CI, "Country Identification");
					log.info("Country Identification label is Present");

					//Associated document
					String associateddocument=driver.findElement(associateddoc).getText();
					System.out.println(associateddocument);
					System.out.println("Associated Documents:");
					//Verify that Associated document table exist
					Assert.assertEquals(associateddocument, "Associated Documents:");
					log.info("Associated Documents: label is Present");

					//System Information
					String systeminformation=driver.findElement(systeminfo).getText();
					System.out.println(systeminformation);
					System.out.println("System Information:");
					//Verify that System Information table exist
					Assert.assertEquals(systeminformation, "System Information:");
					log.info("System Information: label is Present");

					}
				}
				else if(data[3].contains("verifyCountryReadView")&& data[4].equalsIgnoreCase("No")){
					
					//refresh the page
					driver.navigate().refresh();

					//switch to default frame
					driver.switchTo().defaultContent();

					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");
					
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
	
					
					//Verify that Country link is present or not
					if(driver.findElements(country).size() !=0){
						System.out.println("Country label is Present");
						return false;
					}

				}else{
					log.info("Country label is Absent");
					return true;
				}
			}


			catch(Exception e){  fail=true;
			log.error("verifyCountryReadView()", e);
			return false;
			}
			return true;
		}

		public static boolean SetState_Country(String[] data) throws Exception{
			try{

				if(data[3].contains("SetState_Country")&& data[4].equalsIgnoreCase("Yes")){

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
					
					//Click country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");
										
					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					
					//Wait till Create New Country Header is not diplaying in the page
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
					
					//Get the Unique name for Country
					String countryname01 = "Country_In_Work" + CommonFunctions.getRandomString(4);
					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, countryname01);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					
					//Get text for country name
					String countryname02=driver.findElement(countrynameread).getText();

					//Compare the country name value
					Assert.assertEquals(countryname01, countryname02);
					
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");
									
					//enter name
					CommonFunctions.enterTextInTextbox(countrysearchname, countryname01);

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");

					//Click on Actions menu
					driver.findElement(countryactions).click();
					//Click on Change State
					driver.findElement(By.xpath("//td[contains(text(),'Actions:')]//select[1]//following::option[contains(text(),'"+data[5]+"')]")).click();
					//Select Released state from the Update Lifecycle dropdown
					CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, data[8]);

					//click Update in Change State wizard
					driver.findElement(countrystateupdate).click();

					//verification
					String country_LCStateafter = driver.findElement(RO_LCState).getText();
					Assert.assertEquals(country_LCStateafter, data[8]);
					
					//Country State changes
					log.info("Country State changes to: " + country_LCStateafter);
				
				}
				else if(data[3].contains("SetState_Country")&& data[4].equalsIgnoreCase("No")){	
					

					if(data[10].equalsIgnoreCase("Yes")){
						
						//Browse to Material Tab
						navigateToMaterialTab(data);
						
						//Select In Work BOM
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						
						//Expand Header attribute
						CommonFunctions.clickButtonOrLink(headerAttributesPlus,"btn","Expand Collapse button");
						
						//Click on COO
						CommonFunctions.clickButtonOrLink(cooValue, "COO Value");
						
						//Select Action menu and check the available list of actio
						Select select = new Select(driver.findElement(countryactions));
						List<WebElement> options = select.getOptions();
						boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);				
						
						if (bVal=true){
							//Click on Actions menu
							driver.findElement(countryactions).click();
							//Click on Change State
							driver.findElement(By.xpath("//td[contains(text(),'Actions:')]//select[1]//following::option[contains(text(),'"+data[5]+"')]")).click();
							//Select Released state from the Update Lifecycle dropdown
							CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, data[8]);

							//click Update in Change State wizard
							driver.findElement(countrystateupdate).click();

							//verification
							String country_LCStateafter = driver.findElement(RO_LCState).getText();
							Assert.assertNotEquals(country_LCStateafter, data[8]);
							
							//Country State changes
							log.info("Country State changes to: " + country_LCStateafter);
							log.info("State which is entered in Set state UI is: " + data[8]);
							log.info("Country name which is in detail page is: " + country_LCStateafter);
							
						}
						
						else {
							
							//Verify that User is not able to see Change State option
							Assert.assertFalse(bVal);
							log.info("Is Change State option present or not:" + bVal);
						}
						

					}
					
					else {
						

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
												
					//Click country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");
					
					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();
				
					//Select Action menu and check the available list of action
					Select select = new Select(driver.findElement(countryactions));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);				
					
					if (bVal=true){
						//Click on Actions menu
						driver.findElement(countryactions).click();
						//Click on Change State
						driver.findElement(By.xpath("//td[contains(text(),'Actions:')]//select[1]//following::option[contains(text(),'"+data[5]+"')]")).click();
						//Select Released state from the Update Lifecycle dropdown
						CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, data[8]);

						//click Update in Change State wizard
						driver.findElement(countrystateupdate).click();

						//verification
						String country_LCStateafter = driver.findElement(RO_LCState).getText();
						Assert.assertNotEquals(country_LCStateafter, data[8]);
						
						//Country State changes
						log.info("Country State changes to: " + country_LCStateafter);
						log.info("State which is entered in Set state UI is: " + data[8]);
						log.info("Country name which is in detail page is: " + country_LCStateafter);
						
					}
					
					else {
						
						//Verify that User is not able to see Change State option
						Assert.assertFalse(bVal);
						log.info("Is Change State option present or not:" + bVal);	
					}
					

					}
				}

			}catch(Exception e){  fail=true;
			log.error("SetState_Country()", e);
			return false;
			}
			return true;
		}

		public static boolean Delete_Country(String[] data) throws Exception{
			try{

				if(data[3].contains("DeleteCountry")&& data[4].equalsIgnoreCase("Yes")){
					
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");					
																			
					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					//Wait till Create New Country Header is not displaying
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
					
					//Get the Unique name for Country
					String countryname01 = "Country_In_Work" + CommonFunctions.getRandomString(4);
					
					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, countryname01);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					
					//Get text for country name
					String countryname02=driver.findElement(countrynameread).getText();

					//Compare the country name value
					Assert.assertEquals(countryname01, countryname02);
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");


					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//enter name
					CommonFunctions.enterTextInTextbox(countrysearchname, countryname01);

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");

					//Click on Actions menu
					driver.findElement(countryactions).click();
					//Click on Delete
					CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);					
					//Wait for visibility of Delete Object Header
					CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
					driver.findElement(countrydelete).click();
					Thread.sleep(2000);
					//Verify that Delete confirmation Alert pop up present or not
					Boolean bAlert = CommonFunctions.AlertPopUpPresent();			
					Assert.assertTrue(bAlert);

					//Click on delete and complete the action
					driver.findElement(countrydelete).click();
					wait.until(ExpectedConditions.alertIsPresent());
					//alt.accept();
					CommonFunctions.handleAlertPopUp();
					
					//Country get deleted
					log.info("Country got deleted");

				}
				else if(data[3].contains("DeleteCountry")&& data[4].equalsIgnoreCase("No")){
					

					if(data[10].equalsIgnoreCase("Yes")){
						
						//Browse to Material Tab
						navigateToMaterialTab(data);
						
						//Select In Work BOM
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						
						//Expand Header attribute
						CommonFunctions.clickButtonOrLink(headerAttributesPlus,"btn","Expand Collapse button");
						
						//Click on COO
						CommonFunctions.clickButtonOrLink(cooValue, "COO Value");
						
						//Select Action menu and check the available list of action available
						Select select = new Select(driver.findElement(countryactions));
						List<WebElement> options = select.getOptions();
						boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);				

						//Verify that User is not able to see Delete state
						Assert.assertFalse(bVal);
						log.info("Does User is able to see Delete option " + bVal);				
						
					}					
					
					else{
						
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
		
					//Click country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");					
													
					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();
					
					//Select Action menu and check the available list of action available
					Select select = new Select(driver.findElement(countryactions));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);				

					//Verify that User is not able to see Delete state
					Assert.assertFalse(bVal);
					log.info("Does User is able to see Delete option " + bVal);
					}
				}

			}catch(Exception e){  fail=true;
			log.error("Delete_Country()", e);
			return false;
			}
			return true;
		}


		public static boolean Read_Country_Identification(String[] data) throws Exception{
			try{


				if(data[3].contains("Read_Country_Identification")&& data[4].equalsIgnoreCase("Yes")){//Read_View
					

					if(data[10].equalsIgnoreCase("Yes")){
						

						//Browse to Material Tab
						navigateToMaterialTab(data);
						
						//Select In Work BOM
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						
						//Expand Header attribute
						CommonFunctions.clickButtonOrLink(headerAttributesPlus,"btn","Expand Collapse button");
						
						//Click on COO
						CommonFunctions.clickButtonOrLink(cooValue, "COO Value");
						
						
						//Country Identification
						String CI=driver.findElement(countryidentification).getText();
						System.out.println(CI);
						System.out.println("Country Identification");
						//Verify that Country Identification table exist
						Assert.assertEquals(CI, "Country Identification");
						log.info("Country Identification label is Present");	
					}	
					
					else {
						
						//Refresh the page
						driver.navigate().refresh();

						//switch to default content
						driver.switchTo().defaultContent();
						// Switch to Sidebar Frame
						driver.switchTo().frame("sidebarframe");
						// Click on Libraries
						CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");


					//Click on Country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//Country Identification
					String CI=driver.findElement(countryidentification).getText();
					System.out.println(CI);
					System.out.println("Country Identification");
					//Verify that Country Identification table exist
					Assert.assertEquals(CI, "Country Identification");
					log.info("Country Identification label is Present");			
					}
				}
				else if(data[3].contains("Read_Country_Identification")&& data[4].equalsIgnoreCase("No")){
					
					
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();
					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
					
					//Click on Country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();
					
					//Click on Country
					if(driver.findElements(countryidentification).size() !=0){
						System.out.println("country identification label is Present");						
						return false;
					}

					else if (driver.findElements(countryidentification).size() ==0){
						log.info("country identification label is Absent");						
						return true;
					}
				}

			}
			catch(Exception e){  fail=true;
			log.error("Read_Country_Identification()", e);
			return false;
			}
			return true;
		}


		public static boolean Update_Country_Identification(String[] data) throws Exception{
			try{

				if(data[3].contains("Update_Country_Identification")&& data[4].equalsIgnoreCase("Yes")){//Read_View


					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
					
					
					//Click on Country
					driver.findElement(country).click();


					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");					
														
					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					//Wait till Create New Country header is not displaying
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
					
					//Get the unique name for Country
					String countryname01 = "Country_In_Work" + CommonFunctions.getRandomString(4);
					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, countryname01);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					
					//Get text for country name
					String countryname02=driver.findElement(countrynameread).getText();

					//Compare the country name value
					Assert.assertEquals(countryname01, countryname02);
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");


					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//enter name
					CommonFunctions.enterTextInTextbox(countrysearchname, countryname01);

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");

					//Country Identification
					String CI=driver.findElement(countryidentification).getText();

					//Click on Actions
					driver.findElement(countryactions).click();
					//Click on Update
					CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);
					
					//Wait for visibility of Country update page.
					CommonFunctions.waitForVisibilityOfElement(countryupdatepage);

					//clear the Name value
					driver.findElement(countrynameupdate).clear();

					//Update the value of Country Identification
					CommonFunctions.enterTextInTextbox(countrynameupdate, data[9]);
					driver.findElement(save).click();

					//Get the Updated value for Country Identification
					String CIupdate=driver.findElement(countryname2).getText();
					
					//Print the country name 
					log.info("CIupdate: "+CIupdate);
					log.info("data: "+data[9]);
					
					//Compare the updated value
					Assert.assertEquals(CIupdate, data[9]);			

					//Verify that earlier and new updated value are same
					log.info("Entered in edit page and observed Name in detail page are same:" + CIupdate);

				}
				else if(data[3].contains("Update_Country_Identification")&& data[4].equalsIgnoreCase("No")){
					

					if(data[10].equalsIgnoreCase("Yes")){
						

						//Browse to Material Tab
						navigateToMaterialTab(data);
						
						//Select In Work BOM
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						
						//Expand Header attribute
						CommonFunctions.clickButtonOrLink(headerAttributesPlus,"btn","Expand Collapse button");
						
						//Click on COO
						CommonFunctions.clickButtonOrLink(cooValue, "COO Value");	
						
						//Verify that Update action is not enable
						Select select = new Select(driver.findElement(countryactions));
						List<WebElement> options = select.getOptions();
						boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);				

						//Verify that User is not able to see Update action
						Assert.assertFalse(bVal);
						log.info("User is able to see the Update action: " + bVal);
					}					
					
					else {					

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
						
					//Click on Country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");
																				
					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();
					
					//Verify that Update action is not enable
					Select select = new Select(driver.findElement(countryactions));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);				

					//Verify that User is not able to see Update action
					Assert.assertFalse(bVal);
					log.info("User is able to see the Update action: " + bVal);
				}
				}
			}
			catch(Exception e){  fail=true;
			log.error("Update_Country_Identification()", e);
			return false;
			}
			return true;
		}



		public static boolean Read_Country_General_Attribute(String[] data) throws Exception{
			try{

				if(data[3].contains("Read_Country_General_Attribute")&& data[4].equalsIgnoreCase("Yes")){

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					if(data[10].equalsIgnoreCase("Yes")){						

					//Browse to Material Tab
					navigateToMaterialTab(data);
						
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						
					//Expand Header attribute
					CommonFunctions.clickButtonOrLink(headerAttributesPlus,"btn","Expand Collapse button");
						
					//Click on COO
					CommonFunctions.clickButtonOrLink(cooValue, "COO Value");	
					
					//General Attribute table
					String CountryGALabel=driver.findElement(countrygeneralattribute).getText();
					System.out.println(CountryGALabel);
					System.out.println("General Attributes:");
					//Verify that General Attribute table exist
					Assert.assertEquals(CountryGALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");										
					
					}					
																			
					else {
					//Click on Country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//General Attribute table
					String CountryGALabel=driver.findElement(countrygeneralattribute).getText();
					System.out.println(CountryGALabel);
					System.out.println("General Attributes:");
					//Verify that General Attribute table exist
					Assert.assertEquals(CountryGALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");
					}
				}
				else if(data[3].contains("Read_Country_General_Attribute")&& data[4].equalsIgnoreCase("No")){
					
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					// Switch to Sidebar Frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click on Country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();
					
					//Click on Country
					if(driver.findElements(countrygeneralattribute).size() !=0){
						System.out.println("General Attribute table is Present");						
						return false;
					}

				}else if((driver.findElements(countrygeneralattribute).size()==0)){
					log.info("General Attribute table is Absent");
					
					return true;
				}
			}
			catch(Exception e){  fail=true;
			log.error("Read_General_Attribute()", e);
			return false;
			}
			return true;
		}

		public static boolean Update_Country_General_Attribute(String[] data) throws Exception{
			try{
			
				if(data[3].contains("Update_Country_General_Attribute")&& data[4].equalsIgnoreCase("Yes")){//Read_View
	
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					// Switch to Side bar Frame
					driver.switchTo().frame("sidebarframe");
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
							
					//Click on Country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");
					
					//Click on new Link
					CommonFunctions.clickButtonOrLink(newLink, "link", "New");
					//Wait for the visibility of Create New Country page
					wait = new WebDriverWait(driver, 10);
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewCountry));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
					
					//Get the unique name for the country
					String countryname01 = "Country_In_Work" + CommonFunctions.getRandomString(4);
					//Enter the value for name
					driver.findElement(countryname).click();
					CommonFunctions.enterTextInTextbox(countryname, countryname01);

					//Enter the value for SAPCountryAbbreviation
					driver.findElement(SAPCountryAbbreviation).click();
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviation, data[7]);

					//click on Create
					CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
					
					//Get text for country name
					String countryname02=driver.findElement(countrynameread).getText();

					//Compare the country name value
					Assert.assertEquals(countryname01, countryname02);
					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to side bar frame
					driver.switchTo().frame("sidebarframe");

					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");

					//Click country
					driver.findElement(country).click();

					//switch to default content
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					//enter name
					CommonFunctions.enterTextInTextbox(countrysearchname, countryname01);

					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");

					//Click on Actions
					driver.findElement(countryactions).click();

					//Click on Update
					CommonFunctions.selectFromDropDownByVisibleText(countryactions, data[5]);

					CommonFunctions.waitForVisibilityOfElement(countryupdatepage);

					//Clear the Sap Abbreviation attribute value
					driver.findElement(SAPCountryAbbreviationupdate).clear();

					//Update the value of Country Identification
					CommonFunctions.enterTextInTextbox(SAPCountryAbbreviationupdate, data[9]);
					driver.findElement(save).click();

					//Get the Updated value for Country Identification
					String SAPUpdate=driver.findElement(SAPCountryAbbreviationread).getText();

					//Compare the updated value
					Assert.assertEquals(SAPUpdate, data[9]);
					
					//General Attribute table get updated
					log.info("General Attribute table SAP Country Abbreviation attribute is updated:" + SAPUpdate);


				}
				else if(data[3].contains("Update_Country_General_Attribute")&& data[4].equalsIgnoreCase("No")){

					//Refresh the page
					driver.navigate().refresh();

					//switch to default content
					driver.switchTo().defaultContent();

					// Switch to Side bar Frame
					driver.switchTo().frame("sidebarframe");
					// Click on Libraries
					CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
						

					if(data[10].equalsIgnoreCase("Yes")){
						

						//Browse to Material Tab
						navigateToMaterialTab(data);
							
						//Select In Work BOM
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
							
						//Expand Header attribute
						CommonFunctions.clickButtonOrLink(headerAttributesPlus,"btn","Expand Collapse button");
							
						//Click on COO
						CommonFunctions.clickButtonOrLink(cooValue, "COO Value");	
						
						//Verify that Update action is not enable
						Select select = new Select(driver.findElement(countryactions));
						List<WebElement> options = select.getOptions();
						boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);				

						//Verify that User is not able Update action enabled
						Assert.assertFalse(bVal);
						log.info("User is able to see the Update action: " + bVal);								
						
						
					}
					
					else {
										
					//Click on Country
					driver.findElement(country).click();

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");
					
					//Click on search
					CommonFunctions.clickButtonOrLink(Country.search, "btn", "Search");
					//Wait for Search page
					CommonFunctions.waitForVisibilityOfElement(Country.searchPageHeading);
					//Click first search result
					driver.findElement(countryfirstsearch).click();

					//Verify that Update action is not enable
					Select select = new Select(driver.findElement(countryactions));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);				

					//Verify that User is not able Update action enabled
					Assert.assertFalse(bVal);
					log.info("User is able to see the Update action: " + bVal);		
				}
				}

			}
			catch(Exception e){  fail=true;
			log.error("Update_General_Attribute()", e);
			return false;
			}
			return true;
		}
		//This funcion is to select Update Lifecycle State	
		public static String Supplier_selectUpdateLifecycleState(String[] data) throws Exception{
			try{
				valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
				if(valULCS.contains("Unassigned")){
					//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState,"Approved");
					CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Approved");
				}
				else if(valULCS.contains("Approved")){
					//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
					CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Conditionally Approved");
				}
				else if(valULCS.contains("Conditionally Approved")){
					//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
					CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Approved");
				}
				else if(valULCS.contains("Inactive")){
					//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
					CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Active");
				}
				else if(valULCS.contains("Active")){
					//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Unassigned");
					CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Inactive");
				}
				valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
				System.out.println("valULCS: "+valULCSAfterChange);
			}catch(Exception e){  fail=true;
			log.error("Exception in Supplier_selectUpdateLifecycleState()", e);
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
