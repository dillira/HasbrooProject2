package com.hasbro.views;

import java.util.Iterator;
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

import com.hasbro.security.BOMInternalMainUser;
import com.hasbro.security.CostsheetExternalProduct;
import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.MaterialOld;
import com.hasbro.security.MaterialBOMAnshu;
import com.hasbro.security.Product;
import com.hasbro.security.SourcingConfig;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;


public class BOMView extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	public static By libraryLink = By.id("librariesContentIcon");
	static String strBOMInWork;
	static String  BOMnameInWork1;
	static String strViewOwner;
	static String prodName;
	static String BOMname;
	static String  materialName;
	static String matName;
	static String strCW;
	static String addedColor;
	static String addedSupp;
	static Set set;
	static Iterator iter;
	static Set set1;
	static Iterator iter1;

	public static By viewIcon = By.xpath("//img[contains(@src,'view.png')]");
	public static By hasbroProdBOMView = By.xpath("//a[text()='Hasbro Product BOM View']");
	public static By updateTableLayout = By.xpath("//img[contains(@src,'customize_tablebutton.gif')]");
	public static By updateTableLayoutHidden = By.xpath("//div[@id='updateViewDiv' and @style='display: none;']//img[contains(@src,'customize_tablebutton.gif')]");
	public static By lblFactory = By.xpath("//a[text()='Factory:']");
	public static By search = By.id("SearchButton2");
	public static By lblFabric = By.xpath("//a[contains(text(),'Fabric')and @class='LABEL']");
	
	public static By viewOwner = By.xpath("//td[contains(text(),'View Owner')]//following::td[1]");
	
	public static By processingMethod= By.xpath("//td[contains(text(),'Processing Method')]//following::select[1]");
	public static By resinDescription = By.xpath("//td[contains(text(),'*Resin Description')]//following::input[1]");
	public static By density = By.xpath("//td[contains(text(),'Density (gr/gm2)')]//following::input[1]"); 
	public static By resinType  = By.xpath("//td[contains(text(),'Resin Type')]//following::select[1]"); 
	
	
	public static By operationType = By.xpath("//*[@id='ptc_str_10']");
	public static By laborDescription = By.xpath("//td[contains(text(),'*Labor Description')]//following::input[1]"); 
	public static By description = By.xpath("//td[contains(text(),'Description')]//following::textarea[1]");
	public static By comments = By.xpath("//td[contains(text(),'Comments')]//following::textarea[1]");
	static By printType= By.xpath("//td[contains(text(),'Print Type')]//following::select[1]/option[1]"); 
	public static By finishes= By.xpath("//td[contains(text(),'Finishes')]//following::select[1]/option[1]");  
	public static By season = By.xpath("//a[contains(text(),'Season:')]");
	public static By bomTable = By.xpath("//div[@id='DETAILS_PAGEDisplay']/table/tbody/tr[3]//table[contains(@id,'TBLT')]/tbody/tr/td");
		
	static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String child;
	static String strTestCaseName = null;
	static String strSource;


	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcView(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			strTestCaseName = "User:"+data[0] + " for testcase:"+data[3]+" for BOM Type: "+data[2] ;			
			log.info("Inside Test Case:-> " + strTestCaseName);

			log.info("col0 :" + data[0]);
			log.info("col2 :" + data[2]);
			log.info("col3 :" + data[3]);
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

			switch (data[3]) {
			case "CreateIntRetItemBOM":
				log.info("In side :-> " + data[3]);	
				createBOMInWork(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateIntProdBOM":
				log.info("In side :-> " + data[3]);	
				createBOMInWork(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateSoftGoodsBOM":
				log.info("In side :-> " + data[3]);	
				createBOMInWork(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateVendorRetItemBOM":
				log.info("In side :-> " + data[3]);	
				createBOMInWork(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateVendorProductBOM":
				log.info("In side :-> " + data[3]);	
				createBOMInWork(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "CreateMaterialBOM":
				log.info("In side :-> " + data[3]);	
				CreateMaterialBOM(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "(-- None --)":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Hasbro Product BOM View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Internal: Full":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Internal: Full General/Deco Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Internal: Full Molding Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Internal: Full Plastic":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Internal: Simple":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Internal: Full Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Soft Goods BOM View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Costing: Full":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Costing: Full General / Deco Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Costing: Full Molding Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Costing: Full Plastic":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Non Costing: Full":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Non Costing: Full General / Deco Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Non Costing: Full Molding Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Non Costing: Full Plastic":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Vendor: Full":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Vendor: Full General / Deco Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Vendor: Full Molding Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Vendor: Full Plastic":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Costing: Full Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Non Costing: Full Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Vendor: Full Labor":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Hasbro Material BOM View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
				/*case "LinePlanGroup":
				log.info("In side :-> " + data[3]);	
				verifyLinePlanGroup("LinePlanGroup");
				log.info("Out side :-> " + data[3]);
				break;
			case "System View":
				log.info("In side :-> " + data[3]);	
				verifySystemView(data);
				log.info("Out side :-> " + data[3]);
				break;*/
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Throwable t){
			fail=true;
			log.info("Testcase failed for: "+strTestCaseName);
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	public static String CreateMaterialBOM(String[] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(MaterialOld.libraryLink, "Link", "Library Link");
			//Click on Color link
			driver.findElement(MaterialOld.materialLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(MaterialOld.materialHeadning));
			
				//Click on new
				CommonFunctions.clickButtonOrLink(MaterialOld.newLink, "link", "New Link");
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(MaterialOld.chooseaType));
				//Click on color type
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[5]+"')and @class='LABEL']"), "link", "Material Type");
				switch (data[5]) {
				case "Fabric":
					materialName= fillMaterailMandatory(data);
					fillMatFabric(data);
					MaterialOld.createMaterial(data);
					fillMeterialBOM(data);
					break;
				case "Trim":
					fillMaterailMandatory(data);
					fillMatTrim(data);
					MaterialOld.createMaterial(data);
					fillMeterialBOM(data);
					break;
				case "Resin":
					mandatoryData(data);
					createResin(data);
					MaterialOld.createMaterial(data);
					fillMeterialBOM(data);
					break;
				case "Labor":
					mandatoryData(data);
					createLabor(data);
					MaterialOld.createMaterial(data);
					fillMeterialBOM(data);
					break;
				default:
					fail=true;
					log.info("Default is executed");
				}
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateMaterialBOM()", e);
			return "";
		}
		return materialName;
	}
	
	static String fillMaterailMandatory(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(MaterialOld.materialName));
			matName=data[5]+CommonFunctions.getRandomString(5);
			CommonFunctions.enterTextInTextbox(MaterialOld.materialName,matName);

			CommonFunctions.enterTextInTextbox(MaterialOld.parentRefNumber, data[14]);
			driver.findElement(MaterialOld.cBlack).click();
			log.info("cBlack is Selected!!!");
			driver.findElement(MaterialOld.crADD).click();
			log.info("crADD is Selected!!!");
			// Send Material Description Details
			CommonFunctions.enterTextInTextbox(description, data[15]);
			// Send Comments for Creating Materials Details
			CommonFunctions.enterTextInTextbox(comments, data[15]);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in fillMaterailMandatory()", e);
		}
		return matName;
	}
	private static boolean mandatoryData(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			matName=data[5]+CommonFunctions.getRandomString(5);
			CommonFunctions.enterTextInTextbox(MaterialOld.materialName,matName);
			//Search for Season from POPUP Page
			CommonFunctions.clickButtonOrLink(season, "link", "Season");
			set1 = driver.getWindowHandles();
			iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			//CommonFunctions.enterTextInTextbox(PartMgmtFunctional.seasonNumber, data[9]);
			CommonFunctions.clickButtonOrLink(search, "Search For Season");
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]/preceding::td[1]"), "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in mandatoryData()", e);
			return false;
		}
		return true;
	}
	private static void fillMatFabric(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			//Select Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.construction, data[16]);
			//Select Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.constructionType, data[17]);
			//Select Secondary Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.secondaryConstruction, data[18]);

			//Select Content From drop down
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.content, data[19]);
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(MaterialOld.percent, data[20]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(MaterialOld.cpADD, "btn", "cpADD");
			//Select Content From drop down
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.content, data[21]);
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(MaterialOld.percent, data[22]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(MaterialOld.cpADD, "btn", "cpADD");
			// Send Weight
			CommonFunctions.enterTextInTextbox(MaterialOld.weightGYD, data[23]);
			//Send Pile Height
			CommonFunctions.enterTextInTextbox(MaterialOld.pileHeightmm, data[24]);
			//Caoting PU
			driver.findElement(MaterialOld.coatingPU).click();
			log.info("coatingPU is Selected!!!");
			CommonFunctions.clickButtonOrLink(MaterialOld.coatingADD, "btn", "coatingADD");
			//Finishes Anti Pilling
			driver.findElement(MaterialOld.finishesAntiPilling).click();
			log.info("finishesAntiPilling is Selected!!!");
			CommonFunctions.clickButtonOrLink(MaterialOld.finishesADD, "btn", "finishesADD");
			//Print Type Back Side Print
			driver.findElement(MaterialOld.printTypeBacksidePrint).click();
			log.info("printTypeBacksidePrint is Selected!!!");
			CommonFunctions.clickButtonOrLink(MaterialOld.printTypeADD, "btn", "printTypeADD");

		}
		catch(Exception e){
			fail=true;
			log.error("Exception in fillMatFabric()", e);
		}
	}
	
	private static void fillMatTrim(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			//Select Trim Type from Drop down
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.trimType, data[16]);
			//Send Size
			CommonFunctions.enterTextInTextbox(MaterialOld.size, data[14]);
			//Select Coating
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.coatings, data[17]);
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.sizeUOM, data[18]);
			//Construction Type
			driver.findElement(MaterialOld.construction1Type).click();
			log.info("construction Type is Selected!!!");
			CommonFunctions.clickButtonOrLink(MaterialOld.constructionADD, "btn", "constructionADD");
			//Select Content From drop down
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.content1, data[19]);
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(MaterialOld.percent1, data[20]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(MaterialOld.cpADD1, "btn", "cpADD1");
			//Select Content From drop down
			CommonFunctions.selectFromDropDownByVisibleText(MaterialOld.content1, data[21]);
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(MaterialOld.percent1, data[22]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(MaterialOld.cpADD1, "btn", "cpADD1");
			//Print Type
			driver.findElement(printType).click();
			log.info("printType is Selected!!!");
			CommonFunctions.clickButtonOrLink(MaterialOld.printADD, "btn", "printADD");
			//Finishes
			driver.findElement(finishes).click();
			log.info("finishes is Selected!!!");
			CommonFunctions.clickButtonOrLink(MaterialOld.finishes1ADD, "btn", "finishes1ADD");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in fillMatTrim()", e);
		}
	}
	
	private static boolean createResin(String[] data) throws Exception {
		
		try{ 
			// Send Resin Description Description Details
			CommonFunctions.enterTextInTextbox(resinDescription, data[15]);
			//Select processing Method Type From drop down
			CommonFunctions.selectFromDropDownByVisibleText(processingMethod, data[16]);
			// Send Density (gr/gm2) Description Details
			CommonFunctions.enterTextInTextbox(density, data[17]);
			// Send   Resin Type 
			CommonFunctions.enterTextInTextbox(resinType, data[18]);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in createResin()", e);
			return false;
		}
		return true;
	}


	private static boolean createLabor(String[] data) throws Exception {
		try{ 
			//Select Operation Type From drop down
			CommonFunctions.selectFromDropDownByVisibleText(operationType, data[16]);

			// Send Labor Description  Details
			CommonFunctions.enterTextInTextbox(laborDescription, data[15]);
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in createLabor()", e);
			return false;
		}
		return true;
	}	

	public static Boolean fillMeterialBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.meterialTab, "link", "Meterial");
			addedSupp = AddMeterialSource(data);
			addedColor= AddColor(data);
			//Click on meterial
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.initializeBOM,"btn", "Initialize BOM");
			//Click on Create
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(MaterialBOMAnshu.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(MaterialBOMAnshu.inputCompOrLoca,"Mat description" ); //data[27]
			//Quanity
			action.moveToElement(driver.findElement(MaterialBOMAnshu.quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(MaterialBOMAnshu.inputquantity,"5" ); //
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(MaterialBOMAnshu.billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");

		}catch(Exception e){
			fail=true;
			log.error("Exception in fillMeterialBOM()", e);
			return false;
		}
		return true;
	}
	public static String AddColor(String[] data) throws Exception{
		try{
			//Click on Meterial tab
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.meterialTab, "link", "Meterial");
			//	CommonFunctions.clickButtonOrLink(colorLink, "tab", "Color");
			//Add colorway
			Select dropDownCW = new Select(SeleniumDriver.driver.findElement(MaterialBOMAnshu.meterialColor));
			List<WebElement> elementCountCW = dropDownCW.getOptions();
			int countCW =elementCountCW.size();
			//	int count =SeleniumDriver.driver.findElements(selectSpecification).size();
			if(countCW>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(MaterialBOMAnshu.meterialColor, 1);
				strCW=new Select(driver.findElement(MaterialBOMAnshu.meterialColor)).getFirstSelectedOption().getText();
			}
			else
			{
				strCW=MaterialBOMAnshu.Add_Color(data);
			}
			log.info("Selected Color is: "+strCW);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddColor()", e);
			return "";
		}
		return strCW;
	}
	public static String AddMeterialSource(String[] data) throws Exception{
		try{
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(MaterialBOMAnshu.meterialSource));
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource =elementCountSource.size();
			//log.info("Number of supplier: " + countSource);
			if(countSource>2)
			{
				CommonFunctions.selectFromDropDownByIndex(MaterialBOMAnshu.meterialSource, 1);
				strSource = new Select(driver.findElement(MaterialBOMAnshu.meterialSource)).getFirstSelectedOption().getText();
			}
			else
			{
				AddSupplier(data);
				CommonFunctions.selectFromDropDownByIndex(MaterialBOMAnshu.meterialSource, 1);
				strSource=new Select(driver.findElement(MaterialBOMAnshu.meterialSource)).getFirstSelectedOption().getText();
			}
			log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddMeterialSource()", e);
			return "";
		}
		return strSource;
	}
	
	public static Boolean AddSupplier(String[] data) throws Exception{
		try{
			//	SeleniumDriver.driver.switchTo().defaultContent();
			//	SeleniumDriver.driver.switchTo().frame("contentframe");	
			//Click on sourcing tab
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.sourcingTab, "Tab", "Sourcing");
			CommonFunctions.selectFromDropDownByVisibleText(MaterialBOMAnshu.meterialSupplierAction, "Add Suppliers");
			set = driver.getWindowHandles();
			iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			//Click on show All
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.supplierShowAll, "link", "Show All");
			//Select check box
			CommonFunctions.selectCheckbox(By.xpath("//a[text()='"+data[42]+"']/preceding::input[1]"));
			//Click on Select
			CommonFunctions.clickButtonOrLink(MaterialBOMAnshu.selectBtn, "btn", "Select");
			driver.switchTo().window(parent);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in AddSupplier()", e);
			return false;
		}
		return true;
	}
	
	public static String[] createBOMInWork(String[] data) throws Exception{

		try{
			//prodName =CommonProjectFunctions.CreateProdFromLineSheet(data[22], data[6], "Development Plan", "Create New: Product", data[23], data[24], data[25], data[25], data[25], data[25], data[25], data[25], data[25], data[25], data[25], data[25], data[25]);
			nevigationBOM(data);
			BOMname=createBOM(data);
			//Create BOM page
			BOMnameInWork1 = fillCreateBOM(data);
			System.out.println("BOM Name returned in create page:  " + BOMnameInWork1);
			fillEditBOM(data);
			strBOMInWork=new Select(driver.findElement(InternalBOMSoftG.BOMId)).getFirstSelectedOption().getText();
			System.out.println("BOM name is: " + strBOMInWork);

		}catch(Exception e){
			fail=true;
			log.error("Exception in "+data[3], e);
		}
		return new String[] {strBOMInWork, BOMnameInWork1};
	}

	public static void verifyBOMView(String[] data) throws Exception{
		try{
			if(!data[2].equalsIgnoreCase("BOM\\Materials\\Material")) {
			//Select BOM
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.BOMId, strBOMInWork);
		    CommonFunctions.handleAlertPopUp();
		}
			//Select view type
			CommonFunctions.waitForElementTobeClickable(viewIcon);
			CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
			CommonFunctions.waitForElementTobeClickable(By.xpath("//a[text()='"+data[3]+"']"));
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[3]+"']"), "option", data[3]);
			Thread.sleep(2000);
			verifySequence(data);
			verifyUserGroup(data);
		}catch(Exception e){
			fail=true;
			log.error("Exception in "+data[3], e);
		}
	}
	
	
	/*
	 * Below function verify Sequencing for View Type
	 */
	public static boolean verifySequence(String[] data) throws Exception{
		try {
			int i=14;
			int j=1;
			List<WebElement> col1 = driver.findElements(bomTable);
			System.out.println(col1.size());
			for(WebElement e: col1){
				System.out.println("UI data: "+ e.getText());
				System.out.println("excel data: "+data[i]);
				if(data[i].equalsIgnoreCase("NA")){
					break;
				}
				Assert.assertEquals(e.getText().trim(), data[i]);
				log.info("Col"+j+" :matched for "+ e.getText());
				i++;
				j++;
			}
			log.info("Sequence verified for: "+ data[3]+" for BOM Type: "+data[2]);
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySequence()", e);
			return false;
		}
		return true;
	}
	public static boolean verifyUserGroup(String[] data) throws Exception{
		try {
			if(!data[3].equalsIgnoreCase("(-- None --)")) {
			//Click on update table layout icon
			CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
			strViewOwner = driver.findElement(viewOwner).getText();
			Assert.assertEquals(strViewOwner, data[4]);
			log.info("Owner Group verified for : "+strTestCaseName);
			//Click on Cancel
			CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
			}
			else {
				Assert.assertEquals(driver.findElements(updateTableLayoutHidden).size(),1);
				log.info("For None Update icon is not present");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyUserGroup()", e);
			return false;
		}
		return true;
	}

	public static boolean fillEditBOM(String[] data) throws Exception{
		try {
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			InternalBOMSoftG.action = new Actions(driver);
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.inputCompOrLoca, data[9]);
			if((!data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor"))){
			//Quanity
			InternalBOMSoftG.action.moveToElement(driver.findElement(InternalBOMSoftG.quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.inputquantity, data[10]);
			}
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.headerAttributes);

		}catch(Exception e){
			fail=true;
			log.error("Exception in fillEditBOM()", e);
			return false;
		}
		return true;
	}

	public static String createBOM(String[] data) throws Exception{
		try {
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			//	CommonFunctions.enterTextInTextbox(InternalBOMSoftG.BOMTypeId, data[2]);
			CommonFunctions.enterTextInTextbox(BOMInternalMainUser.BOMTypeId, data[2]);
			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(InternalBOMSoftG.initializeBOM,"btn", "Initialize BOM");
			BOMname=data[13]+CommonFunctions.getRandomString(4);
			System.out.println("BOM Name entered is: " + BOMname);

		}catch(Exception e){
			fail=true;
			log.error("Exception in nevigationBOM()", e);
			return "";
		}
		return BOMname;
	}

	public static String fillCreateBOM(String[] data) throws Exception{
		try{
			CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.headingCreateBOM);
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal")))
			{	CommonFunctions.enterTextInTextbox(InternalBOMSoftG.name,BOMname);
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
			//Select Wave
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.wave, data[11]);
			//Select Currency
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.currency, data[7]);
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			}
			else if((data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				CommonFunctions.enterTextInTextbox(InternalBOMSoftG.name,BOMname);
				//Select colorway
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				//Select Wave
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.wave, data[11]);

				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");			
			}
			else if((data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor"))||(data[2].contains("BOM\\Materials\\Product\\Product\\Vendor"))){
				//Select colorway
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				//Select Wave
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.wave, data[11]);
				//Select Currency
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.currency, data[7]);
				//Click on factory
				CommonFunctions.clickButtonOrLink(lblFactory, "link", "Factory");	
				//Switch window
				 set = driver.getWindowHandles();
				 iter = set.iterator();
				String parent = (java.lang.String) iter.next();
				String child = (java.lang.String) iter.next();
				driver.switchTo().window(child);
				CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
				//driver.switchTo().defaultContent();
				//System.out.println(By.xpath("//a[contains(text(),'"+data[34]+"')]/preceding::td[1]/a"));
				//driver.findElement(SourcingConfig.choose).click();
				//CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[34]+"')]/preceding::td[1]/a"), "Supplier selected");
				CommonFunctions.clickButtonOrLink(SourcingConfig.choose, "Supplier selected");
				driver.switchTo().window(parent);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");			
			}
		}

		catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			return "";
		}
		return BOMname;
	}
	public static boolean nevigationBOM(String[] data)throws Exception{
		try {
			System.out.println(driver.findElements(InternalBOMSoftG.BOMDetails).size());
			if(driver.findElements(InternalBOMSoftG.BOMDetails).size()== 0) {
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				BOMInternalMainUser.clickSpecificationTab(data[6],data);
				//Select Source
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.selectSource, data[8]);
				CommonFunctions.handleAlertPopUp();

				InternalBOMSoftG.strSpec=AddSpecification(data);
				CommonFunctions.handleAlertPopUp();
				InternalBOMSoftG.strCW=AddColorway(data);
				CommonProjectFunctions.clickMaterialsTab();
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in nevigationBOM()", e);
			//Some times not able to put URL in broswe due to synchronization,hence TC are failing,to handle it put below statement
			launchApp(prop.getProperty("adminuser"),prop.getProperty("adminpwd"));	
		}
		return true;
	}

	public static String AddSpecification(String[] data) throws Exception{
		try{
			//Add Specification
			Select dropDownSpec = new Select(SeleniumDriver.driver.findElement(InternalBOMSoftG.selectSpecification));
			List<WebElement> elementCount = dropDownSpec.getOptions();
			int count =elementCount.size();
			if(count>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			}
			else
			{
				InternalBOMSoftG.strSpec= CommonProjectFunctions.Create_Specifications(data[11],data[12]);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
			}
			log.info("Specification is: "+InternalBOMSoftG.strSpec);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			return "";
		}
		return InternalBOMSoftG.strSpec;
	}

	public static String AddColorway(String[] data) throws Exception{
		try{
			//Add colorway
			Select dropDownCW = new Select(SeleniumDriver.driver.findElement(InternalBOMSoftG.colorwayName));
			List<WebElement> elementCountCW = dropDownCW.getOptions();
			int countCW =elementCountCW.size();
			if(countCW>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorwayName, 1);
				InternalBOMSoftG.strCW=new Select(driver.findElement(InternalBOMSoftG.colorwayName)).getFirstSelectedOption().getText();
			}
			else
			{
				InternalBOMSoftG.strCW= InternalBOMSoftG.Create_Colorway(data);
			}
			log.info("Colorway is: "+InternalBOMSoftG.strCW);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddColorway()", e);
			return "";
		}
		return InternalBOMSoftG.strCW;
	}
	public static String verifyLinePlanGroup(String grpName) throws Exception{
		try{
			/*nevigateUserGroup();
			//Enter value in Participent Name
			CommonFunctions.enterTextInTextbox(participantName, grpName);
			//Click on Search
			CommonFunctions.clickButtonOrLink(searchBtn, "btn", "Search");
			//Click on OK button
			CommonFunctions.clickButtonOrLink(okBtn, "btn", "OK");
			driver.switchTo().window(child);
			// Click in info icon
			CommonFunctions.clickButtonOrLink(infoIcon, "Icon", "Info");
			//	validateMembers(data);
*/
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCS()", e);
			return "";
		}
		return "";
	}	

	public static String validateMembers(String[] data) throws Exception{
		try{

		}catch(Exception e){
			fail=true;
			log.error("Exception in validateMembers()", e);
			return "";
		}
		return "";
	}	

	public static String verifySystemView(String[] data) throws Exception{
		try{

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCS()", e);
			return "";
		}
		return "";
	}	
	public static boolean nevigateUserGroup() throws Exception
	{
		try{
			/*driver.switchTo().frame("sidebarframe");
			//Click on Administrative in left pane
			CommonFunctions.clickButtonOrLink(adminLink, "lnk", "Administrative");
			//Click on Manage user Icon
			CommonFunctions.clickButtonOrLink(manageUsersIcon, "lnk", "Manage user");
			//Click on Organization user link
			CommonFunctions.clickButtonOrLink(organizationUsers, "lnk", "Organization user");
			//Switch to new window
			 set = driver.getWindowHandles();
			 iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			//Click on plus sign
			CommonFunctions.clickButtonOrLink(plusSign, "icon", "plus");
			// Switch to new window opened
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}*/

		}catch(Exception e){
			fail=true;
			//	log.error("Exception in " + data[3]+ "for" + data[17], e);
			return false;
		}
		return true;
	}


	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteViewsXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteViewsXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteViewsXls,"Testcases", Utility.getRowNum(suiteViewsXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteViewsXls,"Testcases", Utility.getRowNum(suiteViewsXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteViewsXls, this.getClass().getSimpleName());
	}

}

