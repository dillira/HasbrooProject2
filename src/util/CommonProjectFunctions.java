package util;
/**
 * @author Anjali Gupta
 *
 */
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.SeleniumDriver;

import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Colorway;
import com.hasbro.security.CostsheetTooling;
import com.hasbro.security.Product;
import com.hasbro.security.SourcingConfig;
import com.hasbro.security.Specifications;
//import org.apache.log4j.Logger;
import com.hasbro.security.Supplier;


public class CommonProjectFunctions extends SeleniumDriver{

	public static String screenshotFilename="";
  

	public static String runmodes[]=null;
	//public static int count=-1;
	public static boolean skip=false;
	public static boolean fail=false;
	public static boolean isTestPass=true;
	public static String strTestCaseName = null;

	String url= "hasbroplm-dev.ptcmanaged.com/Windchill/rfa/jsp/main/Main.jsp";
    public static By quickLinks= By.id("quickLinkSelectionId");
	public static By searchProduct= By.name("quickSearchCriteria");
	public static By searchIcon= By.id("searchButton");
	public static By MaterialsTab= By.xpath("//a[@class='subtabTxtNsel' and contains(text(),'Materials')]");
	//public static By MaterialsTab=By.xpath("//a[text()='Materials']");
	public static By mySeasonLink = By.id("seasonsContentIcon");
	public static By mySeasonYear = By.id("seasonSelectList");
	public static By fieldName = By.id("searchDropDownSelect"); 
	public static By lebelProdNum = By.xpath("//*[contains(text(),'Product Number')]//following::a[1]"); 
	public static By sourcingLead  = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead  = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");
	public static By selectSource= By.id("sourcingConfigId");
	public static String strSpecification;
	public static String strSpec;
	public static String prodName;
	public static String prodNum;
	public static String strSupplier;
	public static String strSource;
	/*
	 * This function is to log out from application
	 */
	public static void logOut() throws Exception{
		try{
			//Click on Quick Links
			if(SeleniumDriver.driver!=null){
            SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("headerframe");
			CommonFunctions.selectFromDropDownByValue(quickLinks, "logout");
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.handleAlertPopUp1();
		    CommonFunctions.WindowsCount();
			if(CommonFunctions.windowCount>1){
				driver.quit();
				log.info("Drivers are closed");
			}
			else{
			    driver.close();
			    log.info("Driver closed");
				}
			}
		}
	     catch(Exception e){
	     fail=true;
		 SeleniumDriver.log.error("Exception in logOut()", e);
		 throw e;
	    	 
		 }
		
	     }
	public static String AddSource(String sourcename,String source) throws Exception{
		try{
			CommonFunctions.waitForPageLoaded();
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(selectSource));
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource =elementCountSource.size();
			if(countSource>2)
			{
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, source);
				strSource=new Select(SeleniumDriver.driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			else
			{
				AddSourcingConfiguration(sourcename);
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(selectSource);
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, source);
				CommonFunctions.waitForPageLoaded();
				strSource=new Select(SeleniumDriver.driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			SeleniumDriver.log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddSource()", e);
			throw e;
		}
		return strSource;
	}
	
	public static Boolean AddSourcingConfiguration(String sourcename) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Sourcing Configuration");
			SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(SourcingConfig.supplier));
			//Supplier Selection
			CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
			CommonFunctions.waitForPageLoaded();
			//CommonFunctions.switchToChildWindow();
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+sourcename+"')]/preceding::td[1]/a"), "Supplier selected");
			SeleniumDriver.driver.switchTo().window(parent);

			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");

			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(sourcingLead, 14);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(sourcingHead, 6);
			CommonFunctions.waitForPageLoaded();
			//click on Create
			CommonFunctions.clickButtonOrLink(SourcingConfig.CreateSourcebtn, "btn", "Create Source");
			CommonFunctions.waitForPageLoaded();
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in AddSourcingConfiguration()", e);
			throw e;
		}
	}
	/*
	 * This function is to log out from application
	 */
	public static void goToWindChill() throws Exception{
		try{
			//Click on Quick Links
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("headerframe");
			//CommonFunctions.selectFromDropDownByValue(quickLinks, "Go to Windchill");
			CommonFunctions.enterTextInTextbox(quickLinks,"Go to Windchill");
		}
		catch(Exception e){
			SeleniumDriver.log.error("Exception in goToWindChill()", e);

		}
	}



	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Click on detail tab
	public static boolean clickDetailsTab(String year) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("contentframe"));
			SeleniumDriver.driver.switchTo().frame("contentframe");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailsTablink));
			if(CommonFunctions.waitForVisibilityOfElement(SourcingConfig.detailsTablink))
				CommonFunctions.clickButtonOrLink(SourcingConfig.detailsTablink, "link", "Details tab");
			CommonFunctions.waitForVisibilityOfElement(SourcingConfig.detailPageSeasonDD);
			log.info("In Sourcing config Click Details Method");
			if(CommonFunctions.waitForVisibilityOfElement(SourcingConfig.detailPageSeasonDD)){
				//	CommonFunctions.clickButtonOrLink(SourcingConfig.detailPageSeasonDD, "Season dropdown");
				//	CommonFunctions.clickButtonOrLink(SourcingConfig.season2018,year);
				CommonFunctions.selectFromDropDownByVisibleText(SourcingConfig.detailPageSeasonDD, year);
			}
		}catch(Exception e){
			log.error("Exception in Sourcing Config Tab()", e);
			return false;
		}
		return true;
	}

	/*
	 * 
	 */
	public static void clickLibrariesOption(By libOption) throws Exception{
		//	String alertMsg="";
		try{
			SeleniumDriver.driver.findElement(libOption).click();
		}
		catch(Exception e){
			SeleniumDriver.log.error("Exception in clickLibrariesOption()", e);

		}
		//	return libOption;
	}
	/*
	 * This function is to click My season link in left pane
	 */
	public static void clickMySeasonLink() throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("sidebarframe"));
			//SeleniumDriver.driver.switchTo().frame("sidebarframe");
			CommonFunctions.waitForElementTobeClickable(mySeasonLink);
			CommonFunctions.clickButtonOrLink(mySeasonLink, "link", "My Season link");
			CommonFunctions.waitForVisibilityOfElement(mySeasonYear);

		}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in clickLibrariesOption()", e);
			throw e;
		}
	}

	//Function consist scenario : Click on detail tab
	public static boolean clickSupplierLink() throws Exception{
		try{
			//Click on vendor Supplier link
			SeleniumDriver.driver.findElement(Supplier.supplierLink).click();
			//	CommonProjectFunctions.clickLibrariesOption(seasonLink);
			//Switch frame
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(Supplier.supplierPageheading);
		}catch(Exception e){ 
			//	fail=true;
			log.error("Exception in clickSupplierLink()", e);
			return false;
		}
		return true;
	}

	//This funcion is to Search Supplier	
	public static void Search_Supplier() throws Exception{
		try{
			//Click on search
			CommonFunctions.clickButtonOrLink(Supplier.search, "btn", "Search");
			CommonFunctions.waitForVisibilityOfElement(Supplier.searchPageHeading);
			//Click on first name in list
			CommonFunctions.clickButtonOrLink(Supplier.firstSupplier, "link", "Supplier Name");
		}catch(Exception e){  
			//	fail=true;
			log.error("Exception in Supplier_selectUpdateLifecycleState()", e);
		}
		//	return valULCSAfterChange;
	}

	//Create Colorway
	public static String Create_Colorway(String serchProd,String seasonYear,String action,String suffix,String langCode) throws Exception{
		try{
			//Search Product
			CommonProjectFunctions.searchProduct(serchProd); 
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			//Click on Details tab
			SeleniumDriver.driver.findElement(Colorway.Details).click();
			// Select season from DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.seasonDD, seasonYear);
			String str = SeleniumDriver.driver.findElement(Colorway.actionDD).getText();
			System.out.println(str);
			//find create colorway action 
			boolean val= Colorway.findString1(str.trim(), action);

			// Select Action DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.actionDD, action);
			//Click on Suffix
			CommonFunctions.clickButtonOrLink(Colorway.suffix, "Link", "Suffix Clicked");
			//Switch window
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			SeleniumDriver.driver.findElement(Colorway.suffixSearch).sendKeys(suffix);
			CommonFunctions.clickButtonOrLink(Colorway.search, "Link", "Search Clicked");
			CommonFunctions.clickButtonOrLink(Colorway.choose, "Link", "Suffix Selected");
			SeleniumDriver.driver.switchTo().window(parent);
			SeleniumDriver.driver.switchTo().frame("contentframe");
			CommonFunctions.enterTextInTextbox(Colorway.languageCode,langCode);
			CommonFunctions.clickButtonOrLink(Colorway.save, "Link", "Colorway Created");
			Thread.sleep(1000);
			Colorway.colorWayName = SeleniumDriver.driver.findElement(Colorway.colorWay).getText();
			//CommonFunctions.clickButtonOrLink(ViewProduct, "Link", "Back to product page");
			//check that colorway is created - verification point- use assert always for verification

		}catch(Exception e){
			log.error("Exception in CreateColorway()", e);
		}
		return Colorway.colorWayName;
	}

	//Create Product
	public static String CreateProdFromLineSheet(String prodType,String year,String strlineSheetView,String strlineSheetAction,
			String strClass,String strDivision,String strBrand,String strInternalClassification,String AstSolid,String strIPSensitive,
			String strElectronicsIncluded,String strSoftgoodsIncluded,String strDistributionChannel,String strSRPPriceUSD,String strUSDomestic
			,String strLCPriceUSD,String strDOMPriceUSD) throws Exception{
		try{
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(mySeasonYear, year);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			Thread.sleep(1000);
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");

			//	Date date = new Date();
			//Select Line sheet view
			CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetView, strlineSheetView);
			CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetAction, strlineSheetAction);
			//Click on Assortment/Solid
			CommonFunctions.waitForVisibilityOfElement(By.xpath("//td[contains(text(),'Choose a Type')]"));
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+prodType+"')]"), "link", "Product Type");
			//Prodname=prodName+date.getTime();
			//	Prodname=Prodname.substring(0,8);
			prodName=CommonFunctions.getRandomString(4);
			
			SeleniumDriver.driver.findElement(Product.ProductName).clear();
			CommonFunctions.enterTextInTextbox(Product.ProductName,prodName);
			if(!prodType.equalsIgnoreCase("Trademark Display")){
				//Select Class
				CommonFunctions.enterTextInTextbox(Product.Class, strClass);
				CommonFunctions.enterTextInTextbox(Product.Division, strDivision);
				CommonFunctions.enterTextInTextbox(Product.Brand, strBrand);
				if(!prodType.equalsIgnoreCase("Trade Marketing Pallet")){
					CommonFunctions.enterTextInTextbox(Product.InternalClassification,strInternalClassification);
					//	CommonFunctions.enterTextInTextbox(AstSolid, productData[14]);
					CommonFunctions.selectFromDropDownByVisibleText(Product.AstSolid, AstSolid);
					//	CommonFunctions.enterTextInTextbox(IPSensitive,productData[16]);
					CommonFunctions.selectFromDropDownByVisibleText(Product.IPSensitive,strIPSensitive);
				}

				if(prodType.equalsIgnoreCase("Retail")|| prodType.equalsIgnoreCase("Bundle Pack")){
					//Electronics Included
					CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,strElectronicsIncluded);
					//Softgoods Included
					CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,strSoftgoodsIncluded);
				}
			}
			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			prodNum=SeleniumDriver.driver.findElement(lebelProdNum).getText();
			if(!prodType.equalsIgnoreCase("Trademark Display")){
				//wait = new WebDriverWait(driver, 10);
				//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(viewProductBtn));
				CommonFunctions.enterTextInTextbox(Product.distributionChannel,strDistributionChannel);
				if(prodType.equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,strSRPPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.USDomestic,strUSDomestic);
					CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,strLCPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,strDOMPriceUSD);
					
				}
				if(prodType.equalsIgnoreCase("Retail")|| prodType.equalsIgnoreCase("Bundle Pack")){
					CommonFunctions.enterTextInTextbox(Product.targetCostUSD, strDOMPriceUSD);
				}
			}
			//Click View Product Button
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			//	log.info(prodNum);
		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in CreateProductFromLineSheet()", e);
			throw e;
		}
		return prodNum;
	}

	//Function consist scenario : Click on Specifications tab
	public static boolean clickSpecificationTab(String dataYear) throws Exception{
		try{
		
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(Specifications.specificationsTablink);
			CommonFunctions.waitForElementTobeClickable(Specifications.specificationsTablink);
				CommonFunctions.clickButtonOrLink(Specifications.specificationsTablink, "link", "specifications tab");
                CommonFunctions.waitForPageLoaded();
                CommonFunctions.waitForElementTobeClickable(Product.detailPageSeasonDD);
                CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, dataYear,"Season year");
                CommonFunctions.handleAlertPopUp1();
                CommonFunctions.waitForPageLoaded();
           //   CommonFunctions.clickButtonOrLink(Product.detailPageSeasonDD, "Season dropdown");
			//System.out.println(By.xpath("//*[@id='splId']/option[contains(text(),'"+dataYear+"')]"));
			//SeleniumDriver.driver.findElement(By.xpath("//*[@id='splId']/option[contains(text(),'"+dataYear+"')]")).click();
		}catch(Exception e){
			fail=true;
			log.error("Exception in clickSpecificationTab()", e);
			throw e;
	}
		return true;
	}

	//Function consist scenario : Click on Specifications tab
	public static boolean clickSourcingTab(String dataYear) throws Exception{
			try{
		   wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.lnkSource));
			CommonFunctions.clickButtonOrLink(CostsheetTooling.lnkSource, "link", "Sourcing tab");
			wait.until(ExpectedConditions.titleIs("View Season Product Information"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(Product.detailPageSeasonDD));
			CommonFunctions.waitForPageLoaded();
		CommonFunctions.clickButtonOrLink(Product.detailPageSeasonDD, "Season dropdown");
		System.out.println(By.xpath("//*[@id='splId']/option[contains(text(),'"+dataYear+"')]"));
		SeleniumDriver.driver.findElement(By.xpath("//*[@id='splId']/option[contains(text(),'"+dataYear+"')]")).click();
		}
			catch(Exception e){
				fail=true;
				log.error("Exception in clickSourcingTab()", e);
				throw e;
			}
		return true;
	}
	
	

	public static String Create_Specifications(String wave,String remarks) throws Exception{
		try{

			//Click create New Specification
			CommonFunctions.clickButtonOrLink(Specifications.createNewSpecification, "btn", "create New Specification");
			wait = new WebDriverWait(SeleniumDriver.driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Specifications.headingcreateNewSpecification));
			//Fill page
			//Enter wave
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Specifications.wave, wave,"dropDownSelection");
			//Remarks
			CommonFunctions.enterTextInTextbox(Specifications.remarks, remarks);
			//Source:first Check box
			//	CommonFunctions.clickButtonOrLink(Specifications.sourcesFirstCheckbox, "CheckBox", "Source");
			CommonFunctions.selectCheckbox(Specifications.sourcesFirstCheckbox);
			//String soureCheckbox =SeleniumDriver.driver.findElement(Specifications.sourcesFirstCheckbox).getText();
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			System.out.println(SeleniumDriver.driver.findElement(By.xpath("//div[contains(@id,'specTitle')]")).getText());
			strSpecification =SeleniumDriver.driver.findElement(By.xpath("//div[contains(@id,'specTitle')]")).getText();
			System.out.println(strSpecification);
			strSpec = strSpecification.substring(2, 13);
			System.out.println(strSpec);


		}catch(Exception e){
			log.error("Exception in Create_Specifications()", e);
			//	return false;
		}
		return strSpec;
	}

	public static String Create_Supplier(String[] data) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.actionDD, "btn", "Action dropdown");
			SeleniumDriver.driver.findElement(By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table/tbody//a[contains(text(),'Add Sourcing Configuration')]")).click();
			//	CommonFunctions.enterTextInTextbox(BOM.actionDD, "Add Sourcing Configuration ");
			//	CommonFunctions.selectFromDropDownByVisibleText(BOM.actionDD, "Add Sourcing Configuration");
			//	Boolean strNew =driver.findElement(newLink).isDisplayed();
			//Click on new Link
			CommonFunctions.clickButtonOrLink(Supplier.newLink, "link", "New");
			//	wait = new WebDriverWait(driver, 10);
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingCreateNewSupplier));
			System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'Supplier') and @class='LABEL']"), "link", "Product Type");
			//Fill page
			//Nothing on page to fill 
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSource, 1);
			strSupplier = new Select(SeleniumDriver.driver.findElement(InternalBOMSoftG.selectSource)).getFirstSelectedOption().getText();

		}catch(Exception e){ 
			//fail=true;
			log.error("Exception in Create_Supplier()", e);
			return "";
		}
		return strSupplier;
	}
	public static String CreateProdFromLineSheet(String prodType,String year,String strlineSheetView,String strlineSheetAction,
			String strClass,String strDivision,String strBrand,String strInternalClassification,String AstSolid,String strIPSensitive,
			String strElectronicsIncluded,String strSoftgoodsIncluded,String strDistributionChannel,String strSRPPriceUSD,String strUSDomestic
			,String strLCPriceUSD,String strDOMPriceUSD,String strSegment,String strIntClassifi,String strCoBrand
			,String strLowerAge,String strGender,String strISO,String strSuperCategory,String strCategory
			,String strLicensor,String strProperty,String strFamilyBrand,String strUpperAge) throws Exception{
		try{
			SeleniumDriver.driver.navigate().refresh();
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(mySeasonYear, year);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			Thread.sleep(1000);
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");

			//	Date date = new Date();
			//Select Line sheet view
			CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetView, strlineSheetView);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetAction, strlineSheetAction);
			//Click on Assortment/Solid
			CommonFunctions.waitForVisibilityOfElement(By.xpath("//td[contains(text(),'Choose a Type')]"));
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+prodType+"')]"), "lnk", prodType);
			//Prodname=prodName+date.getTime();
			//	Prodname=Prodname.substring(0,8);
			prodName=CommonFunctions.getRandomString(4);
			SeleniumDriver.driver.findElement(Product.ProductName).clear();
			CommonFunctions.enterTextInTextbox(Product.ProductName,prodName);
			if(!prodType.equalsIgnoreCase("Trade Marketing Display")){
				CommonFunctions.enterTextInTextbox(Product.Class, strClass);
				//Select Class
				CommonFunctions.enterTextInTextbox(Product.inductryShortDesc, prodName);
				CommonFunctions.enterTextInTextbox(Product.Division, strDivision);
				CommonFunctions.enterTextInTextbox(Product.Brand, strBrand);
				CommonFunctions.enterTextInTextbox(Product.segment, strSegment); //Updated
				CommonFunctions.enterTextInTextbox(Product.InternalClassification, strIntClassifi); //Updated

				CommonFunctions.enterTextInTextbox(Product.lowerAge, strLowerAge); //Updated
				CommonFunctions.enterTextInTextbox(Product.gender, strGender); //Updated

				CommonFunctions.enterTextInTextbox(Product.superCategory, strSuperCategory); //Updated
				CommonFunctions.enterTextInTextbox(Product.category, strCategory); //Updated
				CommonFunctions.enterTextInTextbox(Product.licensor, strLicensor); //Updated
				CommonFunctions.enterTextInTextbox(Product.property, strProperty); //Updated
				CommonFunctions.enterTextInTextbox(Product.familyBrand, strFamilyBrand); //Updated
				CommonFunctions.enterTextInTextbox(Product.UpperAge, strUpperAge); //Updated
				if(!prodType.equalsIgnoreCase("Trade Marketing Pallet")){
					CommonFunctions.enterTextInTextbox(Product.InternalClassification,strInternalClassification);
					CommonFunctions.enterTextInTextbox(Product.coBrand, strCoBrand); //Updated
					CommonFunctions.selectFromDropDownByVisibleText(Product.IPSensitive,strIPSensitive);
					CommonFunctions.enterTextInTextbox(Product.iso, strISO);
				}
				if(prodType.equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.selectFromDropDownByVisibleText(Product.AstSolid, AstSolid);
				}
				if(prodType.equalsIgnoreCase("Retail Item")|| prodType.equalsIgnoreCase("Bundle Pack")){
					//Electronics Included
					CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,strElectronicsIncluded);
					//Softgoods Included
					CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,strSoftgoodsIncluded);
				}
			}
			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			prodNum=SeleniumDriver.driver.findElement(lebelProdNum).getText();
			SeleniumDriver.log.info("Prod name: "+ prodNum);
			if(!prodType.equalsIgnoreCase("Trade Marketing Display")){
				//wait = new WebDriverWait(driver, 10);
				//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(viewProductBtn));
				CommonFunctions.enterTextInTextbox(Product.distributionChannel,strDistributionChannel);
				if(prodType.equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,strSRPPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.USDomestic,strUSDomestic);
					CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,strLCPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,strDOMPriceUSD);
				}
				if(prodType.equalsIgnoreCase("Bundle Pack")){
					CommonFunctions.enterTextInTextbox(Product.targetCostUSD, strDOMPriceUSD);
				}
			}
			//Click View Product Button
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			SeleniumDriver.log.info("created "+prodType+" Product is: " + prodNum);
		}
		catch(Exception e){ 
			log.error("Exception in CreateProductFromLineSheet()", e);
			//return "";
			throw e;
		}
		return prodNum;
	}

	//Function consist scenario : Click on Materials tab
	public static boolean clickMaterialsTab() throws Exception{
		try{
            CommonFunctions.waitForPageLoaded();
		    CommonFunctions.waitForElementTobeClickable(MaterialsTab);
			CommonFunctions.clickButtonOrLink(MaterialsTab, "link", "Materials Tab");
			CommonFunctions.handleAlertPopUp1();
			CommonFunctions.waitForPageLoaded();
			//CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.BOMDetails);
			}
		    catch(Exception e)
			{
		    fail=true;
			log.error("Exception in clickMaterialsTab()", e);
			throw e;
		}
		return true;
	}

	public static boolean clickCostingTab() throws Exception{
		try{
			
            wait.until(ExpectedConditions.visibilityOfElementLocated(CostsheetTooling.lnkCosting));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(CostsheetTooling.lnkCosting,"link","lnkCosting");
	        wait.until(ExpectedConditions.titleIs("View Season Product Information"));
			CommonFunctions.waitForPageLoaded();

		}catch(Exception e){
			fail=true;
			log.error("Exception in clickMaterialsTab()", e);
		    throw e;
		}
		return true;
	}

	public static boolean CreateMultiple_Colorway() throws Exception{
		try{
			//Click on Details tab
			SeleniumDriver.driver.findElement(Colorway.Details).click();
			// Select season from DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.actionDD,"Create Multiple Colorways");
			//Switch window
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
			CommonFunctions.waitForElementTobeClickable(Colorway.btnSearch);
			//Click on search
			CommonFunctions.clickButtonOrLink(Colorway.btnSearch,"Btn","Search");
			for(int i=2;i<7;i++)
			{	
				System.out.println(i);
				System.out.println(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[" + i + "]/td/input"));
				CommonFunctions.selectCheckbox(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[" + i + "]/td/input"));	
			}
			//Click on select btn
			CommonFunctions.clickButtonOrLink(Colorway.selectBtn, "btn", "Select");
			SeleniumDriver.driver.switchTo().window(parent);
			SeleniumDriver.driver.switchTo().frame("contentframe");
			Select select = new Select(SeleniumDriver.driver.findElement(Colorway.colorwayDropDown));
			List<WebElement> options = select.getOptions();
			if(options.size()>=3)
				SeleniumDriver.log.info("***Verification : Colorway created and Added in dropdown***");
			else
				Assert.fail();
		}catch(Exception e){ 
			//fail=true;
			log.error("Exception in CreateColorway()", e);
			throw e;
		}
		return true;
	}

	public static boolean searchProduct(String productData) throws Exception{
		try{
			//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(searchProduct));
			
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("headerframe");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(searchProduct);
			SeleniumDriver.driver.findElement(searchProduct).clear();
			CommonFunctions.enterTextInTextbox(searchProduct, productData);
			if(CommonFunctions.waitForElementTobeClickable(searchIcon))
				CommonFunctions.clickButtonOrLink(searchIcon, "Btn", "SearchButton");
		}catch(Exception e){
			fail=true;
			log.error("Exception in searchProduct()", e);
			throw e;
		}
		return true;
	}

	//Added By Sourabh Singh
	public static boolean searchAttributeType(String attType, String productData) throws Exception{
		try{
			//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(searchProduct));
			Thread.sleep(1000);
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("headerframe");
			CommonFunctions.waitForVisibilityOfElement(searchProduct);
			CommonFunctions.selectFromDropDownByVisibleText(fieldName, attType);
			SeleniumDriver.driver.findElement(searchProduct).clear();
			CommonFunctions.enterTextInTextbox(searchProduct, productData);
			if(CommonFunctions.waitForElementTobeClickable(searchIcon))
				CommonFunctions.clickButtonOrLink(searchIcon, "Btn", "SearchButton");
		}catch(Exception e){
			log.error("Exception in search Attribute()", e);
			return false;
		}
		return true;
	}

	public static boolean switchToContentFrame() throws Exception{
		boolean result=false;

		Thread.sleep(100);

		try{
			//Switch frame
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			result=true;
		}
		catch(Exception e){
			SeleniumDriver.log.error("Exception in switchToContentFrame()", e);
			return result;
		}
		return result;
	}

	public static boolean ProductActionDropDown(By by, String inputValue) throws Exception{
		boolean result=false;
		Thread.sleep(100);
		try{
			//check the presence of web element if available then select the value from drop down
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				new Select(SeleniumDriver.driver.findElement(by)).selectByVisibleText(inputValue);
				SeleniumDriver.log.info("Selected" + " " + inputValue + " " + "Product Action Dropdown.");
				result=true;
			}
			return result;
		}
		catch(Exception e){
			SeleniumDriver.log.error("Exception in selectFromDropDown()", e);
			SeleniumDriver.log.info(inputValue + " " + "is not able to select.");
			return false;
		}
	}

	public static boolean switchToSidebarFrame() throws Exception{
		boolean result=false;

		Thread.sleep(100);

		try{
			//Switch frame
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("sidebarframe");
			result=true;
		}
		catch(Exception e){
			SeleniumDriver.log.error("Exception in switchToSidebarFrame()", e);
			return result;
		}
		return result;
	}
}

