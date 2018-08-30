package com.hasbro.ci2018;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.BOMExternalMainUser;
import com.hasbro.security.CSVendorProductMainUser;
import com.hasbro.security.Color;
import com.hasbro.security.Colorway;
import com.hasbro.security.CostsheetExternalRetail;
import com.hasbro.security.ExternalBOM;
import com.hasbro.security.GlobalLinePlan;
import com.hasbro.security.PlaceHolderDevPlan;
import com.hasbro.security.Product;
import com.hasbro.security.Sample;
import com.hasbro.security.SourcingConfig;
import com.hasbro.security.Specifications_old;
import com.hasbro.security.Supplier;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

/**
 * @author anjali.gupta
 *
 */

public class CI253 extends TestsuiteBase{
	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	public static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String Prodname;
	static String retailProdName;
	//static String prodNumRetail;
	static String SoftgoodsInc;
	static String electronicsInc; 
	
	static String strSpec;
	static String retProdCW;
	static String strRFQ;
	static Actions action;
	static String strCSProdVendorName;
	static String csName;
	static String rfqString = "You have the RFQ checked out.  Actions:";
	public static By specAction= By.xpath("//span[@class='PRIMARYINDICATOR']//following::select[1]");
	public static By cbSource= By.xpath("//div[@id='sourcesOptionsdiv_plus']/input");
	public static By cbColorWay= By.xpath("//div[@id='colorwayOptionsdiv_plus']/input");
	//RFQ
	public static By tabRFQ= By.xpath("//a[text()='RFQ']");
	public static By ddAction= By.id("prodseasonActions");
	public static By btnSelect= By.xpath("//a[text()='Select']");
	public static By ddColorway= By.xpath("//td[contains(text(),'Colorway')]//following::select[1]");
	public static By ddWave= By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By requestDate= By.xpath("//td[contains(text(),'Request Date')]//following::input[1]");
	public static By cuttOffDate= By.xpath("//td[contains(text(),'Requested Response Cut-Off Date')]//following::input[1]");
	public static By responsibleEng= By.xpath("//td[contains(text(),'Responsible Engineer')]//following::select[1]");
	public static By responsibleCostEng= By.xpath("//td[contains(text(),'Responsible Cost Engineer')]//following::select[1]");
	public static By btnCreate= By.xpath("//a[text()='Create']");
	public static By expandProd= By.xpath("//img[contains(@id,'expandItemImage')]");
	public static By costingAction= By.xpath("//select[contains(@id,'costingActions')]");
	public static By labelCreProdCS= By.xpath("//span[contains(text(),'Create Product Cost Sheet')]");
	public static By csTempType= By.xpath("//select[contains(@id,'FLEXTYPEID')]");
	public static By rfqRequestStatus= By.id("status");
	public static By quote= By.xpath("//div[@id='rfqResults']//td[contains(text(),'Quote: RFQ')]");
	
	public static By csTempColorways= By.xpath("//select[contains(@id,'colorwayGroupOptions')]");
	public static By csTempName= By.xpath("//input[contains(@id,'name') and not(contains(@id,'Searchable'))]");
	public static By csWave= By.xpath("//input[contains(@id,'hbWave')]//following::select[1]");
	public static By csQuoteCurrency= By.xpath("//input[contains(@id,'hBCurrency')]//following::select[1]");
	public static By btnSave= By.id("saveButton");
	public static By confirmMessage= By.xpath("//td[@class='INFO']");
	public static By closeBtn= By.xpath("//div[@id='divHeader']//img[contains(@src,'closebutton.png')]");
	public static By csList= By.xpath("//table[@id='TBLeditorTable']//table[contains(@id,'TBLT')]/tbody/tr[2]/td[2]/a");
	public static By contentTableEdit= By.xpath("//td[contains(text(),'Contents Table:')]//following::a[text()='Edit'][1]");

	//Edit content table
	public static By cellProductColorway= By.id("r1_hbProductColorway");
	public static By lineProductColorway= By.xpath("//div[@id='ptc_verRef_1Display']/a");
	public static By searchProductColorway= By.id("quickSearchInput");
	public static By cellWaveContentsTable= By.id("r1_hbWaveContentsTable");
	public static By ddWaveContentsTable= By.xpath("//div[@id='hbWaveContentsTableSourceDiv']/select");
	
	public static By cellUnitRatioContentsTable= By.id("r1_hbUnitRatioContentsTable");
	public static By txtUnitRatioContentsTable= By.xpath("//div[@id='hbUnitRatioContentsTableSourceDiv']/input");
	public static By btnSaveGenric= By.xpath("//a[text()='Save']");
	public static By cellPrdType= By.xpath("//td[contains(text(),'Retail Item')]");
	public static By btnDone= By.xpath("//a[text()='Done']");
	
	public static By lnkRFQ= By.linkText("RFQ");
	public static By rfqName= By.xpath("//a[contains(text(),'Name')]//following::tr[1]/td[2]/a[1]");
	public static By rfqCheckedOut= By.xpath("//a[@id='refreshRFQButton']//following::text()[1]");
	public static By ddRFQAction= By.xpath("//a[@id='refreshRFQButton']//following::select[1]");
	
	//Supplier
	public static By linkSupplier= By.xpath("//td[@id='hbSupplier']/a");
	public static By supplierAction= By.xpath("//td[contains(text(),'Supplier Details')]//following::select[1]");
	public static By rfqReceiptContact = By.xpath("//td[contains(text(),'RFQ Receipt Contact')]//following::input[1]");
	public static By costApprovalContact = By.xpath("//td[contains(text(),'Cost Approval Contact')]//following::input[1]");
	
	//Costsheet list
	public static By csListName = By.xpath("//td[contains(text(),'Cost Sheet List')]//following::a[3]");
	public static By productCW = By.xpath("//a[text()='Product Colorway']//following::td[@class='TBLD']/a[1]");
	//BOM
	public static By lnkMaterial= By.linkText("Materials");
	public static By lnkCosting= By.linkText("Costing");
	public static By csSpecification= By.id("specReference");
	public static By csBOM= By.xpath("//td[contains(text(),'BOM')]//following::select[1]");
	
	public static By  plasticMat = By.xpath("//td[contains(text(),'Plastic Material')]//following::input[1]");
	public static By chemicalMat= By.xpath("//td[contains(text(),'Chemical Material')]//following::input[1]");
	public static By purchasedMat = By.xpath("//td[contains(text(),'Purchased Material')]//following::input[1]");
	public static By electronicMat = By.xpath("//td[contains(text(),'Electronic Material')]//following::input[1]");
	public static By softGoodsMat = By.xpath("//td[contains(text(),'Soft Goods Material')]//following::input[1]");
	public static By  packagingMat = By.xpath("//td[contains(text(),'Packaging Material')]//following::input[1]");
	public static By  generalDecoLaborCost = By.xpath("//td[contains(text(),'General / Deco Labor Cost')]//following::input[1]");
	public static By  moldingLaborCost = By.xpath("//td[contains(text(),'Molding Labor Cost')]//following::input[1]");
	public static By  overheadMarkup = By.xpath("//td[contains(text(),'Overhead & Markup')]//following::input[1]");
	
	public static By cellProductSource = By.xpath("//td[contains(@id,'r1_hbProductSourceCostingMOA')]");
	public static By ddProductSource = By.xpath("//div[@id='hbProductSourceCostingMOASourceDiv']/select");
	public static By ctCSName = By.xpath("//a[@name='Contents Table']//following::table/tbody/tr[2]/td[7]");
	
	public static By cellUnitPrice = By.xpath("//td[@id='r1_hbUnitPrice']");
	public static By inputUnitPrice = By.xpath("//div[@id='hbUnitPriceSourceDiv']/input");
	public static By AddRowsButton = By.xpath("//img[@id='menuImage1']");
	
	public static By cellUsagePerK = By.xpath("//td[@id='r1_hbUsagePerK']");
	public static By inputUsagePerK = By.xpath("//div[@id='hbUsagePerKSourceDiv']/input");
	public static By csProdVendorName = By.xpath("//div[@id='costSheetResults']//td[contains(text(),'Cost Sheet Identification')]//following::td[3]");
	public static By rfqResponseAction = By.id("rfqResponseActionOptions");
	public static By labelResponseStatus = By.id("responseStatus");
	//public static By csListName = By.xpath("//td[contains(text(),'Cost Sheet List')]//following :: a[3]");
	
	public static By softgoodsIncluded = By.id("hbSoftgoodsIncluded");
	public static By electronicsIncluded = By.id("hbElectronicsIncluded");
	public static By seasonDD = By.id("seasondata");
	public static By productType = By.id("productTypedata");
	public static By relationShipType = By.id("copyModedata");
	public static By nextBtn = By.xpath("//a[text()='Next']");
	
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcCI(String[] data) throws Exception{
		
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Cost sheet approval workflow");
			// User Name, Password and Action
			System.out.println("col0 :" + data[0]); 
			System.out.println("col1 :" + data[1]);
			System.out.println("col4 :" + data[4]);
			try{
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					//driver.quit();
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

			switch (data[3]) {
			/******************/
			case "WFPrerequisite":
				log.info("In side Prerequisite");	
				wfPrerequisite(data);
				log.info("Out side Prerequisite");
				break;
			case "Create new Retail Item & Link to Assorment":
				log.info("In side :-> " + data[2]);	
				createNewRetailItemLinktoAssort(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Create RFQ":
				log.info("In side :-> " + data[2]);	
				createRFQ(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Verify CS list":
				log.info("In side :-> " + data[2]);	
				verifyCSList(data);
				log.info("Out side :-> " + data[2]);
				break;	
			case "Create BOM for Retail":
				log.info("In side :-> " + data[2]);	
				createBOM(data,retailProdName);
				log.info("Out side :-> " + data[2]);
				break;	
			case "Create CostSheet for Retail":
				log.info("In side :-> " + data[2]);	
				createCS(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Link retail CS with Assortmemt Prod":
				log.info("In side :-> " + data[2]);	
				linkRetailCSwithAssortProd(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Create BOM for AssSolid":
				log.info("In side :-> " + data[2]);	
				createBOM(data,Prodname);
				log.info("Out side :-> " + data[2]);
				break;
			case "Create CostSheet for AssSolid":
				log.info("In side :-> " + data[2]);	
				createProductVendorCS(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Submit Quote for Review":
				log.info("In side :-> " + data[2]);	
				submitQuoteforReview(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Change Team":
				log.info("In side :-> " + data[2]);	
				changeTeam(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Add Approver and Review CostSheet for cost user":
				log.info("In side :-> " + data[2]);	
				addApproverandReviewCS(data);
				log.info("Out side :-> " + data[2]);
				break;
			case "Add Approver and Review CostSheet for engineer user":
				log.info("In side :-> " + data[2]);	
				addApproverandReviewCS(data);
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

	public static String wfPrerequisite(String[] data) throws Exception{
		try{
			//Create Product
			Prodname=CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[5],data[9],data[8],data[10],
					data[11],data[12],data[13],data[14],data[16],data[6],data[7],
					data[17],data[18],data[19], data[20], data[21],
					"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
					"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
			log.info("Assortment Product: "+Prodname);
			//Create multiple colorway
			CommonProjectFunctions.CreateMultiple_Colorway();
			//CommonProjectFunctions.searchProduct("E4944");
			CommonProjectFunctions.clickSourcingTab(data[5]);
			CommonProjectFunctions.AddSource(data[22],data[23]);
			updateSource(data,"Approved");
			//Update supplier's email id
			//updateSupplier(data); //need to uncomment
			// Add Specification
			addSpecification(data);
			//Update specification status to 'Supplier Released'
			updateSpecStatus(data);
		}catch(Exception e){
			fail=true;
			log.error("Exception in createDevelopmentPlan()", e);
			//	return false;
		}
		return Prodname;
	}

	public static String createNewRetailItemLinktoAssort(String[] data) throws Exception{
		try{
			log.info("Create new Retail Item & Link to Solid verification Started.....");
			//Select "Copy/ Link Product"
			CommonFunctions.selectFromDropDownByVisibleText(SourcingConfig.ActionDropdown,"Copy / Link Product");
			retailProdName = copyLinkProduct(data);
			log.info("Retail product:" +retailProdName);
			//Select Distribution channel
			//CommonFunctions.selectFromDropDownByVisibleText(Product.distributionChannel,"Mainline");
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View product");
			//	viewProductBtn
			//Click on details
			Product.clickDetailsTab(data);
			//Verification for Softgoods Included 
			SoftgoodsInc=SeleniumDriver.driver.findElement(softgoodsIncluded).getText();
			Assert.assertEquals(SoftgoodsInc, data[6]);
			//Verification for  Softgoods Included 
			electronicsInc=SeleniumDriver.driver.findElement(electronicsIncluded).getText();
			Assert.assertEquals(electronicsInc, data[7]);
			updateSource(data,"Approved");
			log.info("Create new Retail Item & Link to Solid verification completed for retail item .....");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createNewRetailItemLinktoAssort()", e);
			return "";
		}
		return retailProdName;
	}
	
	public static String createRFQ(String[] data) throws Exception{
		try{
			log.info("Create RFQ.....");
			//Select assortment RFQ
			log.warn("Make sure we are creating RFQ for Assortment product");
			nevigateToRFQ(data[5],Prodname);
			//Select 'Create RFQ' from Action dropdown
			CommonFunctions.selectFromDropDownByVisibleText(ddAction,"Create RFQ");
			//Click on Select
			CommonFunctions.clickButtonOrLink(btnSelect,"btn", "Select");
			fillCreateRFQ(data);
			//Create Cost Sheet templates
			createCSTemplate(data);
			//Update content table of Costsheet
			updateContentTableCS(data);
			//Submit Request to Vendor
			verifySubmitRequestToVendor(data);
			log.info("Create RFQ completed  .....");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createRFQ()", e);
			return "";
		}
		return "";//strRFQ;
	}
	
	public static boolean verifyCSList(String[] data) throws Exception{
		try{
			nevigateToRFQ(data[5],Prodname);
			//Verify RFQ is visible to verdor user
			Assert.assertEquals(driver.findElement(rfqName).getText().trim(), strRFQ);
			log.info("RFQ is visible for verdor user");
			//Click on 'Create costsheet from templates' button
			//CommonFunctions.clickButtonOrLink(by, "Button", "Create costsheet from templates");
			//Click on name of 'Cost Sheet List'
			CommonFunctions.clickButtonOrLink(csListName, "lnk", "Cost Sheet List name");
			//Verify Product colorway name
			Assert.assertEquals(driver.findElement(productCW).getText().trim(), retProdCW);
		}catch(Exception e){
			fail=true;
			log.error("Exception in submitQuoteForReview()", e);
			return false;
		}
		return true;
	}
	
	public static String createBOM(String[] data,String pName) throws Exception{
		try{
			//Nevigate to Materials
			nevigateToMaterials(pName);
			//Select BOM type
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.BOMTypeId, data[2]);
			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.initializeBOM,"btn", "Initialize BOM");
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Enter in Component or Location attribute
			action = new Actions(driver);
			action.moveToElement(driver.findElement(ExternalBOM.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(ExternalBOM.inputCompOrLoca, data[9]);
			//Enter unit Price
			action.moveToElement(driver.findElement(cellUnitPrice)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputUnitPrice, data[6]);
			//Enter usage per K
			action.moveToElement(driver.findElement(cellUsagePerK)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputUsagePerK, data[7]);
			//Click on Save and Check in button
			CommonFunctions.clickButtonOrLink(ExternalBOM.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to default frame
			driver.switchTo().defaultContent();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			//Expand Header attribute
			CommonFunctions.clickButtonOrLink(ExternalBOM.headerAttributesPlus, "btn","Expand Collapse button");
			//Get the value of BOM status text 
			//String bomstatusvalue= driver.findElement(ExternalBOM.bomdetailpage).getText();
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in createBOM()", e);
			return "";
		}
		return "";
	}
	
	public static String createCS(String[] data) throws Exception{
		try{
			//Click on Costing link
			SeleniumDriver.driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("sidebarframe"));
			CommonFunctions.clickButtonOrLink(lnkCosting, "lnk", "Costing");
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetExternalRetail.lstcostingActions,"Create Cost Sheet");
			Thread.sleep(1000);	
			System.out.println(By.xpath("//a[text()='"+data[2]+"']"));
			// select  type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[2]+"']"),"link","Cost sheet type");
			//Select specification
			CommonFunctions.selectFromDropDownByIndex(csSpecification,1);
			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(CostsheetExternalRetail.lstcolorwayGroupOptions,1);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(CostsheetExternalRetail.lnkAdd,"lnk","lnkAdd");	
			//Select BOM
			CommonFunctions.selectFromDropDownByIndex(csBOM,1);
			// select QuoteCurrency
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetExternalRetail.lstQuoteCurrency,data[11]);
			//  Plastic Material 
			CommonFunctions.enterTextInTextbox(plasticMat, data[6]);
			//Chemical Material 
			CommonFunctions.enterTextInTextbox(chemicalMat, data[6]);
			//Purchased Material 
			CommonFunctions.enterTextInTextbox(purchasedMat, data[6]);
			// Electronic Material 
			CommonFunctions.enterTextInTextbox(electronicMat, data[6]);
			//Soft Goods Material 
			CommonFunctions.enterTextInTextbox(softGoodsMat, data[6]);
			// Packaging Material 
			CommonFunctions.enterTextInTextbox(packagingMat, data[6]);
			//General / Deco Labor Cost 
			CommonFunctions.enterTextInTextbox(generalDecoLaborCost, data[6]);
			//Molding Labor Cost 
			CommonFunctions.enterTextInTextbox(moldingLaborCost, data[6]);
			//Overhead & Markup 
			CommonFunctions.enterTextInTextbox(overheadMarkup, data[6]);
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			
			//Make CostSheet as Primary
			//Select Update from Action
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetExternalRetail.lstCostSheetAction,"Update");
			//Check checkbox
			driver.findElement(CostsheetExternalRetail.chkPriCS).click();
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
			Assert.assertEquals(driver.findElement(CostsheetExternalRetail.lblPrimaryCS).getText().trim(),"Yes");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createCS()", e);
			return "";
		}
		return "";
	}
	
	public static String createProductVendorCS(String[] data) throws Exception{
		try{
			nevigateToRFQ(data[5],Prodname);
			//Click on RFQ
			CommonFunctions.clickButtonOrLink(rfqName, "lnk", "RFQ Name");
			//Click on costsheet name link
			CommonFunctions.clickButtonOrLink(csList, "lnk", "CostSheet");
			//Select Action from Update dropdown
			CommonFunctions.selectFromDropDownByVisibleText(CSVendorProductMainUser.lstCostSheetAction,"Update");
			CommonFunctions.waitForVisibilityOfElement(CSVendorProductMainUser.lstCSStatus);
			//Select specification
			CommonFunctions.selectFromDropDownByIndex(csSpecification,1);
			//Select BOM
			CommonFunctions.selectFromDropDownByIndex(csBOM,1);
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
			strCSProdVendorName = driver.findElement(csProdVendorName).getText();
		}catch(Exception e){
			fail=true;
			log.error("Exception in createCS()", e);
			return "";
		}
		return strCSProdVendorName;
	}
	
	
	public static String linkRetailCSwithAssortProd(String[] data) throws Exception{
		try{
			//Nevigate to RFQ
			nevigateToRFQ(data[5],Prodname);
			//Click on RFQ
			CommonFunctions.clickButtonOrLink(rfqName, "lnk", "RFQ Name");
			//Click on costsheet name link
			CommonFunctions.clickButtonOrLink(csList, "lnk", "CostSheet");
			//Click on content table edit link
			CommonFunctions.clickButtonOrLink(contentTableEdit, "lnk", "Edit of Content Table");
			//Click on Product source cell
			CommonFunctions.clickButtonOrLink(cellProductSource, "Cell", "Product Source");
			CommonFunctions.selectFromDropDownByIndex(ddProductSource, 1);
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSaveGenric, "btn", "Save");
			//Click on Done
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Done");
			//Verify costsheet name
			//Assert.assertEquals(driver.findElement(ctCSName).getText().trim(), expected);
		}catch(Exception e){
			fail=true;
			log.error("Exception in createBOM()", e);
			return "";
		}
		return "";
	}
	
	public static String submitQuoteforReview(String[] data) throws Exception{
		try{
			//Nevigate to RFQ
			SeleniumDriver.driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("sidebarframe"));
			CommonFunctions.clickButtonOrLink(lnkRFQ, "RFQ link in sidebar");
			//Click on RFQ Name
			CommonFunctions.clickButtonOrLink(rfqName, "link", "RFQ name");
			//Select 'Submit Quote for Review' from Quote Action Drop down
			CommonFunctions.selectFromDropDownByVisibleText(rfqResponseAction, "Submit Quote for Review");
			//Verify Quote Status
			Assert.assertEquals(driver.findElement(labelResponseStatus).getText().trim(), "Quote Submitted For Review");
			log.info("User will receive mail. Check manually");
			log.info("Next step will be from gcuser");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createBOM()", e);
			return "";
		}
		return "";
	}
	
	public static String changeTeam(String[] data) throws Exception{
		try{
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeTeam()", e);
			return "";
		}
		return "";
	}
	
	public static String addApproverandReviewCS(String[] data) throws Exception{
		try{
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in addApproverandReviewCS()", e);
			return "";
		}
		return "";
	}
	
	public static String initialQuoteReview(String[] data) throws Exception{
		try{
			nevigateToRFQ(data[5],Prodname);
			//Nevigate to RFQ
			SeleniumDriver.driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("sidebarframe"));
			CommonFunctions.clickButtonOrLink(lnkRFQ, "RFQ link in sidebar");
			//Click on RFQ Name
			CommonFunctions.clickButtonOrLink(rfqName, "link", "RFQ name");
			//Select 'Submit Quote for Review' from Quote Action Drop down
			CommonFunctions.selectFromDropDownByVisibleText(rfqResponseAction, "Initial Quote Review");
			//Verify Quote Status
			Assert.assertEquals(driver.findElement(labelResponseStatus).getText().trim(), "Quote Activate");
			log.info("Quote status changed to Quote activate");
			
			csName = driver.findElement(csListName).getText();
			log.info("Cost Sheet list name is: " + csName);
			//Verify CS status as 'Ready for Review' for Assortment product
			nevigateToCS(Prodname,data);
			Assert.assertEquals(driver.findElement(CostsheetExternalRetail.csStatus), "Ready for Review");
			log.info("Cost sheet status changed to  'Ready for Review' for Assortment Product ");
			////Verify BOM status as 'Ready for Review' for Assortment product
			//Nevigate to Materials
			nevigateToMaterials(Prodname);
			//Click on header attributes
			CommonFunctions.clickButtonOrLink(ExternalBOM.headerAttributes, "link", "Hearder Attributes");
			Assert.assertEquals(driver.findElement(BOMExternalMainUser.Bomstatusdetailpage), "Ready for Review");
			log.info("BOM status changed to:  ' Ready for Review' for Assortment Product ");
			/**************************/
			//Verify CS status as 'Ready for Review' for Retail product
			nevigateToCS(retailProdName,data);
			Assert.assertEquals(driver.findElement(CostsheetExternalRetail.csStatus), "Ready for Review");
			log.info("Cost sheet status changed to  'Ready for Review ' for Retail product ");
			////Verify BOM status as 'Ready for Review' for Retail product
			//Nevigate to Materials
			nevigateToMaterials(retailProdName);
			//Click on header attributes
			CommonFunctions.clickButtonOrLink(ExternalBOM.headerAttributes, "link", "Hearder Attributes");
			Assert.assertEquals(driver.findElement(BOMExternalMainUser.Bomstatusdetailpage), "Ready for Review");
			log.info("BOM status changed to:  ' Ready for Review ' for Retail product");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createBOM()", e);
			return "";
		}
		return csName;
	}
	
	public static String fillCreatebom(String[] data) throws Exception{
		try{
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(ExternalBOM.colorway, 1);
			//Select currency
			CommonFunctions.selectFromDropDownByVisibleText(ExternalBOM.wave, data[11]);
			//Select Currency
			CommonFunctions.selectFromDropDownByVisibleText(ExternalBOM.currency, data[7]);
			//Get the Window Handler of parent Window
			String parentWindow= driver.getWindowHandle();
			System.out.println("The ID of parent Window is: " + parentWindow);

			//Select Factory
			driver.findElement(ExternalBOM.Factory).click();

			//Get the number of pop up Windows open
			Set<String> handles =driver.getWindowHandles();

			for (String handle1: driver.getWindowHandles()){

				System.out.println(handle1);
				driver.switchTo().window(handle1);				
			}

			//Click on Find Factory Search
			driver.findElement(ExternalBOM.factoryFindSearch).click();
			//Click on Choose Factory
			driver.findElement(ExternalBOM.chooseFactory).click();

			//Switch to Parent Window
			driver.switchTo().window(parentWindow);

			//Switch to default content
			driver.switchTo().defaultContent();

			//Switch to Content Frame
			driver.switchTo().frame("contentframe");
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreatebom()", e);
			return "";
		}
		return "";
	}

	public static boolean nevigateToMaterials(String pName) {
		try{
			CommonProjectFunctions.searchProduct(pName); 
			//Click on details
			CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
			//select Source
			CommonFunctions.selectFromDropDownByIndex(CommonProjectFunctions.selectSource, 1);
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
			//Click on Material link in left pane
			SeleniumDriver.driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("sidebarframe"));
			//Click on material
			CommonFunctions.clickButtonOrLink(lnkMaterial, "lnk", "Materials");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in nevigateToMaterials()", e);
		}
		return true;
	}
	
	public static boolean nevigateToRFQ(String Year,String Productname) {
		try{
			CommonProjectFunctions.searchProduct(Productname); 
			CommonProjectFunctions.clickSourcingTab(Year);
			//click on RFQ tab
			CommonFunctions.clickButtonOrLink(tabRFQ, "btn", "RFQ");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in nevigateToRFQ()", e);
		}
		return true;
	}
	
	public static boolean nevigateToCS(String pName,String[] data) {
		try{
			CommonProjectFunctions.searchProduct(pName); 
			CommonProjectFunctions.clickSourcingTab(data[5]);
			CommonProjectFunctions.clickCostingTab();
			//select Source
			CommonFunctions.selectFromDropDownByIndex(CommonProjectFunctions.selectSource, 1);
			//Select Specification
		//	CommonFunctions.selectFromDropDownByIndex(Specifications.specificationdropdown, 1);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in nevigateToCS()", e);
		}
		return true;
	}

	
	public static boolean fillCreateRFQ(String[] data) {
		try{
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(ddColorway, 1);
			//select wave
			CommonFunctions.selectFromDropDownByVisibleText(ddWave, data[24]);
			//Enter Request Date 
			CommonFunctions.todayDate=CommonFunctions.getTodayDate();
			CommonFunctions.enterTextInTextbox(requestDate,CommonFunctions.todayDate);
			//Enter Requested Response Cut-Off Date
			CommonFunctions.tomorrowDate=CommonFunctions.getTomorrowDate();
			CommonFunctions.enterTextInTextbox(cuttOffDate,CommonFunctions.tomorrowDate);
			//Select Responsible Engineer
			CommonFunctions.selectFromDropDownByVisibleText(responsibleEng, data[8]);
			//Select Responsible Cost Engineer
			CommonFunctions.selectFromDropDownByVisibleText(responsibleCostEng, data[9]);
			//Click on Create
			CommonFunctions.clickButtonOrLink(btnCreate, "Create Btn");
			
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in fillCreateRFQ()", e);
		}
		return true;
	}

	public static String copyLinkProduct(String[] data) {
		try{
			//Select 'Season'
			CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[5]);
			//Select ' Product Type'
			CommonFunctions.selectFromDropDownByVisibleText(productType,data[11]);
			//Select 'Relationship Type'
			CommonFunctions.selectFromDropDownByVisibleText(relationShipType, data[12]);
			//Select checkbox for sources
			CommonFunctions.selectCheckbox(cbSource);
			//Select checkbox for Colorway
			CommonFunctions.selectCheckbox(cbColorWay);
			//Click on Next button
			CommonFunctions.clickButtonOrLink(nextBtn, "btn", "Next");
			//Softgoods Included
			CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,data[6]);
			//Electronics Included
			CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,data[7]);
			//Family Brand
			//	CommonFunctions.enterTextInTextbox(Product.familyBrand,data[70] );
			//Lower age
			//	CommonFunctions.enterTextInTextbox(Product.lowerAge,data[71]);
			//Upper age
			//	CommonFunctions.selectFromDropDownByIndex(Product.UpperAge,10);
			//Click on Next button
			CommonFunctions.clickButtonOrLink(nextBtn, "btn", "Next");
			retailProdName=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
			log.info("retail product:" +retailProdName);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return retailProdName;
	}

	public static boolean createCSTemplate(String[] data) {
		try{
			//Expand product
			CommonFunctions.clickButtonOrLink(expandProd, "Img", "Product plus sign");
			//select Create Cost Sheet Templates from costsheet Action dropdown
			CommonFunctions.selectFromDropDownByVisibleText(costingAction, "Create Cost Sheet Templates");
			CommonFunctions.waitForVisibilityOfElement(labelCreProdCS);
			//Select Type
			CommonFunctions.selectFromDropDownByVisibleText(csTempType, data[2]);
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(csTempColorways, 1);
			//Enter name
			CommonFunctions.enterTextInTextbox(csTempName, data[10]);
			//Select wave
			CommonFunctions.selectFromDropDownByVisibleText(csWave, data[24]);
			//Select Quote Currency
			CommonFunctions.selectFromDropDownByVisibleText(csQuoteCurrency, data[4]);
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
			//Close window
			CommonFunctions.clickButtonOrLink(closeBtn, "btn", "Close");
			//Click on save
			CommonFunctions.clickButtonOrLink(btnSave, "btn", "Save");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createCSTemplate()", e);
		}
		return true;
	}

	public static boolean updateContentTableCS(String[] data) {
		try{
			//Click on costsheet name link
			CommonFunctions.clickButtonOrLink(csList, "lnk", "CostSheet");
			//Click on content table edit link
			CommonFunctions.clickButtonOrLink(contentTableEdit, "lnk", "Edit of Content Table");
			//Click on cell ProductColorway
			CommonFunctions.clickButtonOrLink(cellProductColorway, "table cell", "Product Colorway");
			retProdCW=retailProdName+"-000";
			System.out.println("retail product colorway: "+retProdCW);
			//Click on ProductColorway line
			CommonFunctions.clickButtonOrLink(lineProductColorway, "cell line", "Product Colorway");
			CommonFunctions.enterTextInTextbox(searchProductColorway, retProdCW);
			//Enter wave
			CommonFunctions.clickButtonOrLink(cellWaveContentsTable, "cell", "wave");
			CommonFunctions.selectFromDropDownByVisibleText(ddWaveContentsTable, data[24]);
			//Enter unit ratio
			CommonFunctions.clickButtonOrLink(cellUnitRatioContentsTable, "cell", "Unit Ratio");
			CommonFunctions.enterTextInTextbox(txtUnitRatioContentsTable, data[18]);
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSaveGenric, "btn", "Save");
			//Verification : Product type displayed in cell
			Assert.assertEquals(SeleniumDriver.driver.findElement(cellPrdType).getText(), "Retail Item");
			log.info("Product type displayed in cell");
			//Verification : Product Name displayed in cell
			Assert.assertEquals(SeleniumDriver.driver.findElement(cellPrdType).getText(),Prodname);
			log.info("Product name displayed in cell");
			//Click on Done button
			CommonFunctions.clickButtonOrLink(btnDone, "btn", "Done");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createCSTemplate()", e);
		}
		return true;
	}
	
	public static boolean verifySubmitRequestToVendor(String[] data) {
		try{
			//Go back to RFQ
			SeleniumDriver.driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("sidebarframe"));
			CommonFunctions.clickButtonOrLink(lnkRFQ, "RFQ link in sidebar");
			//Click on RFQ Name
			CommonFunctions.clickButtonOrLink(rfqName, "link", "RFQ name");
			String rfqMsg = driver.findElement(rfqCheckedOut).getText();
			if(rfqMsg.trim().equals("rfqString"))
			{
				//Select check in option
				CommonFunctions.selectFromDropDownByVisibleText(ddRFQAction,"Check In");
			}
			//Select 'Submit request to vendor' from Action dropdown
			CommonFunctions.selectFromDropDownByVisibleText(ddRFQAction,"Submit Request to Vendors");
			//Verify Status
			Assert.assertEquals(driver.findElement(rfqRequestStatus).getText().trim(), "Request Sent");
			log.info("Status chneged to 'Request Sent' ");
			//Verify Quote has been created
			Assert.assertEquals(driver.findElements(quote).size(),1);
			log.info("Quote has been created");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createCSTemplate()", e);
		}
		return true;
	}
	
	
	
	public static String addSpecification(String[] data) throws Exception{
		try{

			//Click back to product
			CommonProjectFunctions.searchProduct(Prodname);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[5]);
			//Select source
			CommonFunctions.selectFromDropDownByVisibleText(CommonProjectFunctions.selectSource, data[23]);
			//Add Specification
			Select dropDownSpec = new Select(SeleniumDriver.driver.findElement(Specifications_old.specificationdropdown));
			List<WebElement> elementCount = dropDownSpec.getOptions();
			int count =elementCount.size();
			if(count>=2)
			{
				//Select first Specification
				CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
				strSpec=new Select(driver.findElement(Specifications_old.specificationdropdown)).getFirstSelectedOption().getText();
			}
			else
			{
				//Get the name of specification created
				strSpec= CommonProjectFunctions.Create_Specifications(data[24],data[25]);
				//Select specification first
				CommonFunctions.selectFromDropDownByIndex(Specifications_old.specificationdropdown, 1);
				//Get the name of the specification created
				strSpec=new Select(driver.findElement(Specifications_old.specificationdropdown)).getFirstSelectedOption().getText();
			}
			log.info("Specification is: "+strSpec);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreatebom()", e);
			return "";
		}
		return strSpec;
	}
	
	public static boolean updateSource(String[] data,String status) throws Exception{
		try{
			if(driver.findElements(SourcingConfig.sourcingDetails).size()== 0) {
				CommonProjectFunctions.clickSourcingTab(data[5]);
				//Select Source
				CommonFunctions.selectFromDropDownByVisibleText(CommonProjectFunctions.selectSource, data[23]);
			}
			//Apply Action
			CommonFunctions.selectFromDropDownByVisibleText(SourcingConfig.sourcingAction,"Update Source");
			//String valULCSAfterChange=sourcing_selectUpdateLifecycleState(data);
			Thread.sleep(1000);
			updateSourceStatus(status);
			//Click on Update
			CommonFunctions.clickButtonOrLink(SourcingConfig.btnSave, "btn", "Save");
			log.info("Source updated to" + status);
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateSource()", e);
			return false;
		}
		return true;
	}
	
	public static boolean updateSupplier(String[] data) throws Exception{
		try{
			//Click on Supplier
			CommonFunctions.clickButtonOrLink(linkSupplier, "lnk", "Supplier");
			//Select update from Action dropdown
			CommonFunctions.selectFromDropDownByVisibleText(supplierAction, "Update");
			//Update email id in   RFQ Receipt Contact
			driver.findElement(rfqReceiptContact).clear();
			CommonFunctions.enterTextInTextbox(rfqReceiptContact, data[26]);
			//Update email id in  Cost Approval Contact 
			driver.findElement(costApprovalContact).clear();
			CommonFunctions.enterTextInTextbox(costApprovalContact, data[27]);
			//Click on Save button
			CommonFunctions.clickButtonOrLink(btnSaveGenric, "Btn", "Save");
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateSupplier()", e);
			return false;
		}
		return true;
	}
	
	
	public static boolean updateSourceStatus(String state) throws Exception{
		try{
			CommonFunctions.selectFromDropDownByVisibleText(SourcingConfig.editable_Status,state);
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateBOMStatus()", e);
			return false;
		}
		return true;
	}
//Update specification to 'Supplier released' state
	public static boolean updateSpecStatus(String[] data) throws Exception{
		try{
			//Select 'Update Spec' from Action dropdown
			CommonFunctions.selectFromDropDownByVisibleText(specAction,"Update Spec");
			//Update Status
			CommonFunctions.selectFromDropDownByVisibleText(Specifications_old.specificationstatusinput,"Supplier Released");
			//Click on Save
			CommonFunctions.clickButtonOrLink(Specifications_old.updatespecSave, "Save button");
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateBOMStatus()", e);
			return false;
		}
		return true;
	}


	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteCIExcelXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteCIExcelXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteCIExcelXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteCIExcelXls,"Testcases", Utility.getRowNum(suiteCIExcelXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteCIExcelXls,"Testcases", Utility.getRowNum(suiteCIExcelXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteCIExcelXls, this.getClass().getSimpleName());
	}

}
