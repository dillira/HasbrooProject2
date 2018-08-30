package com.hasbro.security;
/*
 * Prerequisite :
 * Need to change Product name and colorway name in excel sheet.
 * Create Product and colorway mamually and add them in excel sheet
 */


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


public class Colorway extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;

	//Variable required for Colorway
	public static By ProductLink = By.linkText("Product");
	public static By productNumber = By.xpath("//td[contains(text(),'Product Number')]//following::input[1]");
	public static By search = By.id("SearchButton2");
	public static By Details = By.xpath("//a[contains(text(),'Details')]");
	public static By seasonDD = By.id("splId");
	public static By ColorwayNameDD = By.xpath("//*[@id='contextSKUId']");
	public static By actionDD = By.id("prodseasonActions");
	public static By suffix = By.xpath("//td[contains(text(),'General Attribute')]/following::a[1]");
	public static By suffixSearch = By.id("LCSCOLOR_ptc_str_1");
	public static By btnSearch = By.id("SearchButton2");
	public static By languageCode = By.xpath("//td[contains(text(),'Language Code')]/following::select[1]");
	public static By choose = By.xpath("//a[contains(text(),'choose')]");
	public static By save = By.xpath("//a[contains(text(),'Save')]");
	public static By ViewProduct = By.xpath("//a[contains(text(),'View Product')]"); 
	public static By CreateColorway = By.xpath("//*[@id='prodseasonActions']");
	public static By CreateMultipleColorways = By.xpath("//*[@id='prodseasonActions']");
	public static By DeleteColorway = By.xpath("//*[@id='prodseasonActions']");
	//public static By DetailWaveHDM = By.xpath("//td[contains(text(),'Detailed Wave - HDM:')]");
	public static By DetailwaveHDMEdit = By.xpath("//a[contains(@href,'hbDetailedWaveHDM') and contains(text(),'Edit')]");
	public static By DetailwaveHDMView = By.xpath("//a[contains(@href,'hbDetailedWaveHDM') and contains(text(),'View')]");
	public static By WavetosecpackageEdit = By.xpath("//a[contains(@href,'hbWavetoSecondaryPackageDimensions') and contains(text(),'Edit')]");
	public static By WavetosecpackageView = By.xpath("//a[contains(@href,'hbWavetoSecondaryPackageDimensions') and contains(text(),'View')]");
	public static By Wavetoshipunitdimen = By.xpath("//td[contains(text(),'Wave to Shipping Unit Dimensions:')]");
	public static By WavetoshipunitdimenEdit = By.xpath("//a[contains(@href,'hbWavetoShippingUnitDimensions') and contains(text(),'Edit')]");
	public static By WavetoshipwtEdit = By.xpath("//a[contains(@href,'hbProductWeightsShippingMOA') and contains(text(),'Edit')]");
	public static By WavetoshipwtView = By.xpath("//a[contains(@href,'hbProductWeightsShippingMOA') and contains(text(),'View')]");
	public static By WavetosecwtEdit = By.xpath("//a[contains(@href,'hbProductWeightsSecondaryMOA') and contains(text(),'Edit')]");
	public static By WavetosecwtView = By.xpath("//a[contains(@href,'hbProductWeightsSecondaryMOA') and contains(text(),'View')]");
	public static By ContentsTableEdit = By.xpath("//a[contains(@href,'hbTrademarkPalletContents') and contains(text(),'Edit')]");
	public static By ContentsTableView = By.xpath("//a[contains(@href,'hbTrademarkPalletContents') and contains(text(),'View')]");
	public static By DisplayTableEdit = By.xpath("//a[contains(@href,'hbDisplayItemOutofPackageFullyAssembled') and contains(text(),'Edit')]");
	public static By DisplayTableView = By.xpath("//a[contains(@href,'hbDisplayItemOutofPackageFullyAssembled') and contains(text(),'View')]");

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
	//public static By btnSearch = By.id("SearchButton2");
	public static By colorIdentification = By.xpath("//td[contains(text(),'Color Identification')]");
	public static By colorAction = By.xpath("//select[contains(@onchange,'evalList(this)')]");
	public static By RO_ColorUpdateLifecycleState = By.xpath("//td[contains(text(),'Colorway Lifecycle State')]//following::td[1]");
	public static By deleteSucessful = By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td");

	//For assorment colorway
	public static By DetailWaveHDM = By.xpath("//td[contains(text(),'Detailed Wave - HDM:')]");
	public static By Packaging = By.xpath("//a[@name='Packaging']");   ////td[text()='Packaging']
	public static By Wavetosecpacdimen = By.xpath("//td[contains(text(),'Wave to Secondary Package Dimensions:')]"); 
	//	public static By Wavetoshipunitdimen = By.xpath("//td[contains(text(),'Wave to Shipping Unit Dimensions:')]"); 
	public static By Wavetoshipunitwt = By.xpath("//td[contains(text(),'Wave to Shipping Unit Weights:')]");
	public static By Wavetosecwt = By.xpath("//td[contains(text(),'Wave to Secondary Package Weights:')]");
	public static By Contentstable = By.xpath("//td[contains(text(),'Trademark Pallet - Contents:')]");
	public static By Displaytable = By.xpath("//td[contains(text(),'Display Item Out Of Package Fully Assembled:')]");

	//For Retail colorway
	public static By Engineeringview = By.xpath("//td[contains(text(),'Engineering')]");
	public static By Weight = By.xpath("//td[contains(text(),'Weight:')]");
	public static By searchColorways = By.id("searchDropDownSelect");
	public static By searchProduct= By.name("quickSearchCriteria");
	//	public static By searchColorways = By.id("searchDropDownSelect");
	public static By searchIcon= By.id("searchButton");
	public static By colorWay= By.xpath("//td[contains(text(),'Colorway')]//following::td[@class='DISPLAYTEXT']/a[1]");
	public static By selectBtn= By.xpath("//a[text()='Select']");
	public static By colorwayDropDown = By.id("contextSKUId");
	public static By lastColorWayChoose = By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[position()=last()]/td[1]/a");
	public static By lastColorWayName = By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[position()=last()]/td[3]/a");
	public static By colorWayNumber = By.xpath("//td[@id='color']/a");

	public static By waveReq3Status = By.xpath("//td[contains(text(),'Wave Requirements 3 - Status:')]");
	public static By customerGroup = By.xpath("//td[contains(text(),'Customer Group')]//following::select[1]");
	public static By netContentVersionQuantity = By.xpath("//td[contains(text(),'Net Content Version Quantity')]//following::input[1]");
	public static By packagingStatus = By.xpath("//td[contains(text(),'Packaging Status')]//following::select[1]");
	
	
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String colorWayName;
	public static String ProdName;
	public static String colorWayNum;
	public static String prodNumber;
	public static String textRO_UpdateLifecycleState;
	static boolean bStatus;
	static String strCW;

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
			log.debug("Inside testcase for Colorway");
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

			//Create colorway from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_Colorway(data); }
			if(data[3].equalsIgnoreCase("CreateMultiple"))
			{ CreateMultiple_Colorway(data); }
			//1.
			//Read view verification for general attributes
			if(data[3].equalsIgnoreCase("GARead_View"))
			{	verifyGAReadView(data);}
			//Update Verification for general attributes
			if(data[3].equalsIgnoreCase("GAUpdate"))
			{	verifyGAUpdate(data);}
			//2.
			//Read view verification for detail wave-HDM
			if(data[3].equalsIgnoreCase("DWRead_View"))
			{	verifyReadDetailWaveHDM(data);}
			//update verification for detail wave-HDM
			if(data[3].equalsIgnoreCase("DWUpdate"))
			{	verifyUpdateDetailWaveHDM(data);}
			//Read view verification for packaging
			if(data[3].equalsIgnoreCase("PRead_View"))
			{	verifyPackaging(data);}
			//update verification for packaging
			if(data[3].equalsIgnoreCase("PUpdate"))
			{	verifyUpdatePackaging(data);}
			/********************************************/
			//Read view verification for Wave to Secondary Package Dimensions: 
			if(data[3].equalsIgnoreCase("WSPDRead_View"))
			{	verifyWaveSecPackagech(data);}
			//update verification for Wave to Secondary Package Dimensions: 
			if(data[3].equalsIgnoreCase("WSPDUpdate"))
			{	verifySecDimenUpdate(data);}
			/********************************************/
			//Read view verification for Wave to Shipping Unit Dimensions
			if(data[3].equalsIgnoreCase("WSUDRead_View"))
			{	verifyWaveshippingunitdimen(data);}
			//update verification for Wave to Shipping Unit Dimensions
			if(data[3].equalsIgnoreCase("WSUDUpdate"))
			{	verifyShipUnitDimenUpdate(data);}
			/********************************************/
			//Read view verification for Wave to Shipping Unit Weights
			if(data[3].equalsIgnoreCase("WSUWRead_View"))
			{	verifyReadWaveShippingUnitWt(data);}
			//update verification forWave to Shipping Unit Weights
			if(data[3].equalsIgnoreCase("WSUWUpdate"))
			{	verifyShipUnitWtUpdate(data);}
			/********************************************/
			//Read view verification for Wave to Secondary Package Weights
			if(data[3].equalsIgnoreCase("WSPWRead_View")) 
			{	verifyWaveSecPackWt(data);}
			//update verification for Wave to Secondary Package Weights
			if(data[3].equalsIgnoreCase("WSPWUpdate"))
			{	verifySecPackWtUpdate(data);}

			/**************************Colorway Assortment completed****************************************************/

			//Read view verification for Engin
			if(data[3].equalsIgnoreCase("ERead_View"))
			{	verifyEngineeringReadView(data);}
			//update verification for Engin
			if(data[3].equalsIgnoreCase("EUpdate"))
			{	verifyEngineeringUpdate(data);}

			//Read view verification for wt
			if(data[3].equalsIgnoreCase("WRead_View"))
			{	verifyWeightsReadView(data);}
			//update verification for wt
			if(data[3].equalsIgnoreCase("WUpdate"))
			{	verifyWeightUpdate(data);}

			//Read view verification for CT
			if(data[3].equalsIgnoreCase("CTRead_View"))
			{	verifReadViewContentsTable(data);}
			//update verification for CT
			if(data[3].equalsIgnoreCase("CTUpdate"))
			{	verifyTPContentsTableUpdate(data);}

			//Read view verification for Display
			if(data[3].equalsIgnoreCase("DRead_View"))
			{	verifyReadViewDisplayTable(data);}
			//update verification for Display
			if(data[3].equalsIgnoreCase("DUpdate"))
			{	verifyDisplaytableUpdate(data);}

			/****************************************************************************************************/
			//Update
			if(data[3].equalsIgnoreCase("Update"))
			{ Update_Colorway(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_Colorway(data); }
			//Delete colorway from product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Colorway(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create colorway
	public static String Create_Colorway(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
				ProdName=CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[7], "Development Plan", "Create New: Product", "TOYS","BOYS", "NERF", "CHANNEL", "Assortment", "Not IP Sensitive (Open)", "No", "No", "Mainline", "4", "6", "8", "10");
				log.info("Product number is: "+ProdName);
				System.setProperty("KeyProdNumber", ProdName);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//Click on Details tab
				driver.findElement(Details).click();
				// Select season from DD
				CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
				// Select Action DD
				CommonFunctions.selectFromDropDownByVisibleText(actionDD, data[8]);
				//Click on Suffix
				CommonFunctions.clickButtonOrLink(suffix, "Link", "Suffix Clicked");
				//Switch window
				Set set = driver.getWindowHandles();
				Iterator iter = set.iterator();
				String parent = (java.lang.String) iter.next();
				String child = (java.lang.String) iter.next();
				driver.switchTo().window(child);
				driver.findElement(suffixSearch).sendKeys(data[5]);
				//CommonFunctions.enterTextInTextbox(suffixSearch, data[7]);
				CommonFunctions.clickButtonOrLink(search, "Link", "Search Clicked");
				CommonFunctions.waitForElementTobeClickable(choose);
				CommonFunctions.clickButtonOrLink(choose, "Link", "Suffix Selected");
				driver.switchTo().window(parent);
				driver.switchTo().frame("contentframe");
				CommonFunctions.enterTextInTextbox(languageCode, data[6]);
				CommonFunctions.clickButtonOrLink(save, "Link", "Colorway Created");
				Thread.sleep(1000);
				colorWayName = driver.findElement(colorWay).getText();
				System.setProperty("ColorwayKeyName", colorWayName);
				log.info("Colorway created.Colorway name is: "+colorWayName);
				String expectedColorway=ProdName+"-"+data[5];
				System.out.println(expectedColorway);
				System.out.println(colorWayName);
				//CommonFunctions.clickButtonOrLink(ViewProduct, "Link", "Back to product page");
				//check that colorway is created - verification point- use assert always for verification
				Assert.assertEquals(colorWayName,expectedColorway);
				Thread.sleep(1000);
				log.info(data[2]+"Product is: "+ProdName);
				log.info(data[2]+"Colorway name is: "+colorWayName);
			} 
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				//	prodNumber=System.getProperty("KeyProdNumber");
				CommonProjectFunctions.searchProduct(data[9]); 
				//	ProdName=CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[7], "Development Plan", "Create New: Product", "TOYS","GIRLS", "FURBY", "BOYS", "Assortment", "Not IP Sensitive (Open)", "No", "No", "Mainline", "4", "6", "8", "10");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//Click on Details tab
				driver.findElement(Details).click();
				// Select season from DD
				CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
				Select select = new Select(driver.findElement(colorAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[8],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}
			else{
				log.info("Create is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in CreateColorway()", e);
			return "";
		}
		return ProdName;
	}

	public static boolean CreateMultiple_Colorway(String[] data) throws Exception{
		try{
			//Search Product
			//CommonProjectFunctions.searchProduct(ProdName); 
			CommonProjectFunctions.searchProduct(data[9]); 
			//	ProdName=CommonProjectFunctions.CreateProdFromLineSheet("Assortment/Solid",data[7], "Development Plan", "Create New: Product", "TOYS","GIRLS", "FURBY", "BOYS", "Assortment", "Not IP Sensitive (Open)", "No", "No", "Mainline", "4", "6", "8", "10");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on Details tab
			driver.findElement(Details).click();
			// Select season from DD
			CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
			String str = driver.findElement(actionDD).getText();
			System.out.println(str);
			//find create colorway action 
			boolean val= findString1(str.trim(), data[8]);

			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
				// Select Action DD
				// Select season from DD
				//	CommonFunctions.selectFromDropDownByVisibleText(Colorway.seasonDD, data[6]);
				// Select Action DD
				CommonFunctions.selectFromDropDownByVisibleText(Colorway.actionDD, "Create Multiple Colorways");
				//Switch window
				Set set = driver.getWindowHandles();
				Iterator iter = set.iterator();
				String parent = (java.lang.String) iter.next();
				String child = (java.lang.String) iter.next();
				driver.switchTo().window(child);
				CommonFunctions.waitForElementTobeClickable(btnSearch);
				//Click on search
				CommonFunctions.clickButtonOrLink(btnSearch,"Btn","Search");
				for(int i=2;i<5;i++)
				{	
					System.out.println(i);
					System.out.println(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[" + i + "]/td/input"));
					CommonFunctions.selectCheckbox(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[" + i + "]/td/input"));	
				}
				//Click on select btn
				CommonFunctions.clickButtonOrLink(selectBtn, "btn", "Select");
				driver.switchTo().window(parent);
				driver.switchTo().frame("contentframe");
				Select select = new Select(driver.findElement(colorwayDropDown));
				List<WebElement> options = select.getOptions();
				if(options.size()>=3)
					log.info("Colorway Added in dropdown");
				else
					Assert.fail();
				//CommonFunctions.clickButtonOrLink(ViewProduct, "Link", "Back to product page");
				//check that colorway is created - verification point- use assert always for verification
				//	Assert.assertEquals(colorWayName,expectedColorway);
				Thread.sleep(1000);
			} 
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				Assert.assertFalse(val, data[8]);
				//Assert.assertFalse(bVal, "false");
			}
			else{
				log.info("Create Multiple Colorway is not applicable(NA)");
			}
		}catch(Exception e){ fail=true;
		log.error("Exception in CreateColorway()", e);
		return false;
		}
		return true;
	}


	//method written to find string
	public static boolean findString1 (String data, String regex){
		System.out.println("Calling Find String");
		boolean flag = false;
		try {
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(data);
			if (matcher.find())
			{
				flag = true;
				log.info("Start index: " + matcher.start());
				System.out.print("Start index: " + matcher.start());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error in Handling Findstring  :"+e);
		}

		return flag;
	}

	public static boolean SetState_Colorway(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			//Search Product
			//	CommonProjectFunctions.searchProduct(ProdName); 
			CommonProjectFunctions.searchProduct(data[9]); 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on Details tab
			driver.findElement(Details).click();
			// Select season from DD
			CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);

			//select colorway from DD
			CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1); 
			String valueBeforeChange = driver.findElement(RO_ColorUpdateLifecycleState).getText();
			//	if(!data[0].contains("vuser") && !data[0].contains("vendora")){
			//select change state:colorway from actions dropdown
			Boolean bStatus=CommonFunctions.selectFromDropDownByVisibleText(actionDD, data[8]);
			//	if(!data[0].contains("vuser") && !data[0].contains("vendora")){
			//verification
			String valULCSAfterChange=selectUpdateLifecycleState(data);
			//Click on Update
			CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
			//Verification
			textRO_UpdateLifecycleState = driver.findElement(RO_ColorUpdateLifecycleState).getText();

			//	}
			//	else{
			//		Assert.assertEquals(bStatus, "Fail");
		//}
		if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
			Assert.assertEquals(textRO_UpdateLifecycleState, textRO_UpdateLifecycleState);
		}
		else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				Assert.assertEquals(valueBeforeChange, textRO_UpdateLifecycleState);
			}
			else{
				Select select = new Select(driver.findElement(colorAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[8],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}
		}
		else
			log.info("SetState or chageState is not applicable(NA)");
	}catch(Exception e){ fail=true;
	log.error("Exception in SetState_Colorway()", e);
	return false;
	}
	return true;
}

public static boolean Update_Colorway(String[] data) throws Exception{
	try{
		//Search Product
		driver.navigate().refresh();

		//	CommonProjectFunctions.searchProduct(ProdName); 
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");

		//Click on Details tab
		//you can use common function for details tab
		driver.findElement(Details).click();

		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);

		//select colorway from DD
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1); 

		if(data[3].contains("Update")&& data[4].equalsIgnoreCase("Yes")){
			//select change state:colorway from actions dropdown
			CommonFunctions.selectFromDropDownByVisibleText(actionDD, data[8]);
			//Click on Suffix
			CommonFunctions.clickButtonOrLink(suffix, "Link", "Suffix Clicked");
			//Switch window
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.waitForElementTobeClickable(btnSearch);
			//Click on search
			CommonFunctions.clickButtonOrLink(btnSearch,"Btn","Search");
			colorWayName = driver.findElement(lastColorWayName).getText();
			//Select last colorway in list
			CommonFunctions.clickButtonOrLink(lastColorWayChoose, "lnk", "Choose last colorway");
			//Click on select btn
			//	CommonFunctions.clickButtonOrLink(selectBtn, "btn", "Select");
			driver.switchTo().window(parent);
			driver.switchTo().frame("contentframe");
			CommonFunctions.enterTextInTextbox(languageCode, data[6]);
			CommonFunctions.clickButtonOrLink(save, "Btn", "Save");
			Thread.sleep(1000);
			colorWayNum = driver.findElement(colorWayNumber).getText();
			//check that suffix got changed
			Assert.assertEquals(colorWayNum,colorWayName);
		}
		else if(data[3].contains("Update")&& data[4].equalsIgnoreCase("No")){
			Assert.assertNotEquals(RO_ColorUpdateLifecycleState, valULCSAfterChange);
		}
		else
			log.info("SetState or chageState is not applicable(NA)");
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in Update_Colorway()", e);
		return false;
	}
	return true;
}

//Delete colorway from product
//Logic of delete colorway : The right of deleting a Colorway is to first remove it from the Season and then Delete it from the Product.
public static boolean delete_Colorway(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		
		//As colorway delete logic to delete colorway no need to select season
	//	CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);

		//select colorway from DD
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1); 
		strCW=new Select(driver.findElement(ColorwayNameDD)).getFirstSelectedOption().getText();
		if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){

			// Select Action DD
			CommonFunctions.selectFromDropDownByVisibleText(actionDD, data[8]);
			//click on Delete button
			//	CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
			//Click on delete button
			CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
			//Accept AlertPopup
			CommonFunctions.handleAlertPopUp();
			Select select = new Select(driver.findElement(ColorwayNameDD));
			List<WebElement> options = select.getOptions();
			boolean bValCW=CommonFunctions.dropDownOptionVerificationActions(strCW,options);
			Assert.assertFalse(bValCW);
	
		//	String deleteMess =driver.findElement(deleteSucessful).getText();
		//	Assert.assertEquals(deleteMess,"Delete Successful");
		}
		else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
			//dont do anything
			Select select = new Select(driver.findElement(colorAction));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[8],options);
			//	dropDownOptionVerification
			Assert.assertFalse(bVal);
		}
		else
			log.info("Delete is not applicable(NA)");
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in delete()", e);
		return false;
	}
	return true;
}

//method written to find string
public static boolean findString (String data1, String regex){

	System.out.println("Calling Find String");

	boolean flag = false;
	try {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(data1);
		if (matcher.find())
		{
			flag = true;
			log.info("Start index: " + matcher.start());
			System.out.print("Start index: " + matcher.start());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		log.error("Error in Handling Findstring  :"+e);
	}

	return flag;
}

//This function is to select Update Lifecycle State	
public static String selectUpdateLifecycleState(String[] Data) throws Exception{
	try{
		valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
		//	System.out.println("valULCS: "+valULCS);
		if(valULCS.contains("In Work")){
			CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Released");
			//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Released");
		}
		else if(valULCS.contains("Under Review")){
			CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Released");
			//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Released");
		}
		else if(valULCS.contains("Released")){
			CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Under Review");
			//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "Under Review");
		}
		//put for Inactive status also
		else if(valULCS.contains("Inactive")){
			CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "In Work");
			//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");

		}
		valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
		System.out.println("valULCS: "+valULCSAfterChange);
	}catch(Exception e){
		fail=true;
		log.error("Exception in selectUpdateLifecycleState()", e);
		//	return false;
	}
	return valULCSAfterChange;
}




//Function consist scenario : General Attributes verification:Read_View
public static boolean verifyGAReadView(String[] data) throws Exception{
	try{
		//Search colorways
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");

		//Click on Details tab
		//you can use common function for details tab
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1); 

		if(data[3].contains("GARead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
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
		else if(data[3].contains("GARead_View")&& data[4].equalsIgnoreCase("No")){
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
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyReadView()", e);
		return false;
	}
	return true;
}

//Function consist scenario : General Attributes verification:Update
public static boolean verifyGAUpdate(String[] data) throws Exception{
	try{

		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");

		//Click on Details tab
		//you can use common function for details tab
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1); 
		if(data[3].contains("GAUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			/*Select select = new Select(driver.findElement(actionDD));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);*/
			CommonFunctions.selectFromDropDownByVisibleText(actionDD, data[8]);
			Assert.assertEquals(driver.findElements(languageCode).size(), 1, "General Attributs are Editable"); 
		}
		else if(data[3].contains("GAUpdate")&& data[4].equalsIgnoreCase("No")){
			Select select = new Select(driver.findElement(actionDD));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[8],options);
			if(!bVal)
				Assert.assertFalse(bVal, "false");
			else{
				CommonFunctions.enterTextInTextbox(actionDD, data[8]);
				//	CommonFunctions.waitForVisibilityOfElement(languageCode);
					System.out.println(driver.findElements(languageCode).size());
				Assert.assertEquals(driver.findElements(languageCode).size(), 0, "General Attributes is Editable"); 
			}
		}
		else
		{
			log.info("For this General Attributes is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyUpdate()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Detail Wave HDM verification:Read_View
public static boolean verifyReadDetailWaveHDM(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();

		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);


		if(data[3].contains("DWRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			String bvalView = driver.findElement(DetailwaveHDMView).getText();
			if(driver.findElements(DetailWaveHDM).size() != 0 && bvalView.contains("View")){
				String DetailWave =driver.findElement(DetailWaveHDM).getText();
				System.out.println(DetailWave);
				Assert.assertEquals(DetailWave.trim(), "Detailed Wave - HDM:");
				log.info("Detail wave HDM table is Present");
			}else{
				log.error("Detail wave HDM table is Absent");
				fail=true;
			}
		}
		else if(data[3].contains("DWRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(DetailWaveHDM).size() != 0 ){
				System.out.println("Detail wave HDM table is Present");
				log.error("Detail wave HDM table is Present");
				fail=true;
			}else{
				log.info("Detail wave HDM table is Absent");
			}
		}
		else
		{
			log.info("For this Detail wave HDM table is not applicable(NA)");
		}
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyReadView()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Detail Wave HDM verification:Update
public static boolean verifyUpdateDetailWaveHDM(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();

		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);
		int iVal = driver.findElements(DetailwaveHDMEdit).size();
		if(data[3].contains("DWUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			Assert.assertEquals(iVal, 1, "Detail Wave HDM is updatable");
		}
		else if(data[3].contains("DWUpdate")&& data[4].equalsIgnoreCase("No")){
			Assert.assertEquals(iVal, 0, "Detail Wave HDM is not updatable");
		}
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyUpdate()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Wave Requirements 3 - Status verification:Read_View
public static boolean verifyReadWaveRequirements3Status(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);
		String bvalView = driver.findElement(DetailwaveHDMView).getText();

		if(data[3].contains("DR3SRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			if(driver.findElements(waveReq3Status).size() != 0 && bvalView.contains("View")){
				String strWaveReq3Status =driver.findElement(waveReq3Status).getText();
				System.out.println(strWaveReq3Status);
				Assert.assertEquals(strWaveReq3Status.trim(), "Wave Requirements 3 - Status:");
				log.info("Wave Requirements 3 - Status table is Present");
			}else{
				log.error("Wave Requirements 3 - Status table is Absent");
			}
		}
		else if(data[3].contains("DR3SRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(waveReq3Status).size() != 0  && bvalView.contains("View")){
				System.out.println("Detail wave HDM table is Present");
				log.error("Wave Requirements 3 - Status table is Present");
				fail=true;
			}else{
				log.info("Wave Requirements 3 - Status table is Absent");
			}
		}
		else
		{
			log.info("For this Detail Wave Requirements 3 - Status table is not applicable(NA)");
		}
	}catch(Exception e){
		fail=true;
		log.error("Exception in verifyReadWaveRequirements3Status()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Wave Requirements 3 - Status verification:Update
public static boolean verifyUpdateWaveRequirements3Status(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);
		int iVal = driver.findElements(DetailwaveHDMEdit).size();
		if(data[3].contains("DR3SUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			Assert.assertEquals(iVal, 1, " Wave Requirements 3 - Status is updatable");
		}
		else if(data[3].contains("DR3SUpdate")&& data[4].equalsIgnoreCase("No")){
			Assert.assertEquals(iVal, 0, " Wave Requirements 3 - Status is not updatable");
		}

	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyUpdateWaveRequirements3Status()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Packaging :Read_View
public static boolean verifyPackaging(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();

		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//	String bvalView = driver.findElement(DetailwaveHDMView).getText();
		//select colorway from DD
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);

		if(data[3].contains("PRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			if(driver.findElements(Packaging).size() != 0){
				String Packaging1 =driver.findElement(Packaging).getText();
				System.out.println(Packaging1);
				Assert.assertEquals(Packaging1.trim(), "Packaging:");
				log.info("Packaging is Present");
			}else{
				log.error("Packaging is Absent");
				fail=true;
			}
		}
		else if(data[3].contains("PRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Packaging).size() != 0){
				System.out.println("Packaging is Present");
				log.error("Packaging is Present");
				fail=true;
			}else{
				log.info("Packaging is Absent");
			}
		}
		else
		{
			log.info("For this Packaging is not applicable(NA)");
		}
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyReadView()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Update Packaging verification:Update
public static boolean verifyUpdatePackaging(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");

		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);

		//select colorway from DD
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1); 

		//put all actions dropdown methods in get text and print it
		String str = driver.findElement(actionDD).getText();
		System.out.println(str);

		//find create colorway action 
	//	boolean bval= findString1(str.trim(), data[8]);

		if(data[3].contains("PUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			Select select = new Select(driver.findElement(actionDD));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);

			//	dropDownOptionVerification
			Assert.assertTrue(bVal);
		}
		else if(data[3].contains("PUpdate")&& data[4].equalsIgnoreCase("No")){
			Select select = new Select(driver.findElement(actionDD));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
			//	CommonFunctions.clickButtonOrLink(save, "Link", "Colorway Updated");
			//	dropDownOptionVerification
			//write the code that system gives error and not allowing it to update
			if(!bVal)
				//	Assert.assertFalse(bVal, data[8]);
				Assert.assertFalse(bVal, "false");
			else{
				CommonFunctions.enterTextInTextbox(actionDD, data[8]);
				System.out.println(driver.findElements(packagingStatus).size());
				Assert.assertEquals(driver.findElements(packagingStatus).size(), 0, "Update Packaging Not Editable"); 
			}
		}
		else
		{
			log.info("For this Packaging is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyUpdate()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Wave to secondary package dimensions : verification:Read_View
public static boolean verifyWaveSecPackagech(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(data[10]); 
		//			//searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();

		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);
		//	String bvalView = driver.findElement(WavetosecpackageView).getText();

		if(data[3].contains("WSPDRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			String bvalView = driver.findElement(WavetosecpackageView).getText();
			if(driver.findElements(Wavetosecpacdimen).size() != 0 && bvalView.contains("View")){
				String Wavesecpackagedimensions =driver.findElement(Wavetosecpacdimen).getText();
				System.out.println(Wavesecpackagedimensions);
				Assert.assertEquals(Wavesecpackagedimensions.trim(), "Wave to Secondary Package Dimensions:");
				log.info("Wave to Secondary Package Dimensions is Present");
			}else{
				log.error("Wave to Secondary Package Dimensions is Absent");
				fail=true;
			}
		}
		else if(data[3].contains("WSPDRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Wavetosecpacdimen).size() != 0 ){
				System.out.println("Detail wave HDM table is Present");
				log.error("Wave to Secondary Package Dimensions is Present");
				fail=true;
			}else{
				log.info("Wave to Secondary Package Dimensions is Absent");
			}
		}
		else
		{
			log.info("For this Wave to Secondary Package Dimensions is not applicable(NA)");
		}
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyReadView()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Wave to secondary package dimensions:Update
public static boolean verifySecDimenUpdate(String[] data) throws Exception{
	try{
		int bval = driver.findElements(WavetosecpackageEdit).size();
		if(data[3].contains("WSPDUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			Assert.assertEquals(bval, 1, "Edit is present");
		}
		else if(data[3].contains("WSPDUpdate")&& data[4].equalsIgnoreCase("No")){
			Assert.assertEquals(bval, 0, "Edit is not present");
		}
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyUpdate()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Wave to shipping unit dimensions : verification:Read_View
public static boolean verifyWaveshippingunitdimen(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);


		if(data[3].contains("WSUDRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			String bvalView = driver.findElement(WavetoshipwtView).getText();
			if(driver.findElements(Wavetoshipunitdimen).size() != 0 && bvalView.contains("View")){
				String Waveshipunitdimensions =driver.findElement(Wavetoshipunitdimen).getText();
				System.out.println(Waveshipunitdimensions);
				Assert.assertEquals(Waveshipunitdimensions.trim(), "Wave to Shipping Unit Dimensions:");
				log.info("Wave to Shipping Unit Dimensions is Present");
			}else{
				log.error("Wave to Shipping Unit Dimensions is Absent");
				fail=true;
			}
		}
		else if(data[3].contains("WSUDRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Wavetosecpacdimen).size() != 0){
				System.out.println("Wave to Shipping Unit Dimensions is Present");
				log.error("Wave to Shipping Unit Dimensions is Present");
			}else{
				log.info("Wave to Shipping Unit Dimensions is Absent");
			}
		}
		else
		{
			log.info("For this Wave to Shipping Unit Dimensions is not applicable(NA)");
		}
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyReadView()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Wave to shipping unit dimensions:Update
public static boolean verifyShipUnitDimenUpdate(String[] data) throws Exception{
	try{
		//	boolean bval = driver.findElement(WavetoshipunitdimenEdit).isDisplayed();
		int bval = driver.findElements(WavetoshipunitdimenEdit).size();
		if(data[3].contains("WSUDUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			//	Assert.assertTrue(bval);
			Assert.assertEquals(bval, 1, "Edit is present");
		}
		else if(data[3].contains("WSUDUpdate")&& data[4].equalsIgnoreCase("No")){
			//	Assert.assertFalse(bval);
			Assert.assertEquals(bval, 0, "Edit is not present");
		}
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyUpdate()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Wave to shipping unit weights : verification:Read_View
public static boolean verifyReadWaveShippingUnitWt(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();

		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);
		//	String bvalView = driver.findElement(WavetoshipwtView).getText();

		if(data[3].contains("WSUWRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			String bvalView = driver.findElement(WavetoshipwtView).getText();
			if(driver.findElements(Wavetoshipunitwt).size() != 0 && bvalView.contains("View")){
				String Waveshipunitwt =driver.findElement(Wavetoshipunitwt).getText();
				System.out.println(Waveshipunitwt);
				Assert.assertEquals(Waveshipunitwt.trim(), "Wave to Shipping Unit Weights:");
				log.info("Wave to Shipping Unit Weights is Present");
			}else{
				log.error("Wave to Shipping Unit Weights is Absent");
			}
		}
		else if(data[3].contains("WSUWRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Wavetoshipunitwt).size() != 0){
				System.out.println("Wave to Shipping Unit Weights is Present");
				log.error("Wave to Shipping Unit Weights is Present");
			}else{
				log.info("Wave to Shipping Unit Weights is Absent");
			}
		}
		else
		{
			log.info("For this Wave to Shipping Unit Weights is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyReadView()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Wave to shipping unit wt :Update
public static boolean verifyShipUnitWtUpdate(String[] data) throws Exception{
	try{
		//	boolean bval = driver.findElement(WavetoshipwtEdit).isDisplayed();
		int bval = driver.findElements(WavetoshipwtEdit).size();
		if(data[3].contains("WSUWUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			//	Assert.assertTrue(bval);
			Assert.assertEquals(bval, 1, "Edit is present");
		}
		else if(data[3].contains("WSUWUpdate")&& data[4].equalsIgnoreCase("No")){
			//	Assert.assertFalse(bval);
			Assert.assertEquals(bval, 0, "Edit is not present");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyUpdate()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Wave to secondary package weights : verification:Read_View
public static boolean verifyWaveSecPackWt(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();

		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);


		if(data[3].contains("WSPWRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			String bvalView = driver.findElement(WavetosecwtView).getText();
			if(driver.findElements(Wavetosecwt).size() != 0 && bvalView.contains("View")){
				String Wavesecwt =driver.findElement(Wavetosecwt).getText();
				System.out.println(Wavesecwt);
				Assert.assertEquals(Wavesecwt.trim(), "Wave to Secondary Package Weights:");
				log.info("Wave to Secondary Package Weights is Present");
			}else{
				log.error("Wave to Secondary Package Weights is Absent");
			}
		}
		else if(data[3].contains("WSPWRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Wavetoshipunitwt).size() != 0){
				System.out.println("Wave to Secondary Package Weights is Present");
				log.error("Wave to Secondary Package Weights is Present");
			}else{
				log.info("Wave to Secondary Package Weights is Absent");
			}
		}
		else
		{
			log.info("For this Wave to Secondary Package Weights is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyReadView()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Wave to Secondary Package Weights :Update
public static boolean verifySecPackWtUpdate(String[] data) throws Exception{
	try{
		//boolean bval = driver.findElement(WavetosecwtEdit).isDisplayed();
		int bval = driver.findElements(WavetosecwtEdit).size();
		if(data[3].contains("WSPWUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			//	Assert.assertTrue(bval);
			Assert.assertEquals(bval, 1, "Edit is present");
		}
		else if(data[3].contains("WSPWUpdate")&& data[4].equalsIgnoreCase("No")){
			//	Assert.assertFalse(bval);
			Assert.assertEquals(bval, 0, "Edit is not present");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyUpdate()", e);
	return false;
	}
	return true;
}
/******************************Colorway Assortment Completed*******************************************/

//Function consist scenario : Engineering verification:Read_View
public static boolean verifyEngineeringReadView(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);

		if(data[3].contains("ERead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			if(driver.findElements(Engineeringview).size() != 0){
				String Engineering =driver.findElement(Engineeringview).getText();
				System.out.println(Engineering);
				Assert.assertEquals(Engineering.trim(), "Engineering:");
				log.info("Engineering is Present");
			}else{
				log.error("Engineering is Absent");
				fail=true;
			}
		}
		else if(data[3].contains("ERead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Engineeringview).size() != 0){
				System.out.println("General Attributes label is Present");
				log.error("Engineering is Present");
			}else{
				log.info("Engineering is Absent");
			}
		}
		else
		{
			log.info("For this Engineering is not applicable(NA)");
		}
	}catch(Exception e){ 
		fail=true;
		log.error("Exception in verifyReadView()", e);
		return false;
	}
	return true;
}

//Function consist scenario : Engineering verification:Update
public static boolean verifyEngineeringUpdate(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]); 
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);
		//put all actions dropdown methods in get text and print it
		String str = driver.findElement(actionDD).getText();
		System.out.println(str);
		//find update colorway season action 
		boolean bval= findString1(str.trim(), data[8]);

		if(data[3].contains("EUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			Select select = new Select(driver.findElement(actionDD));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
			//	dropDownOptionVerification
			Assert.assertTrue(bVal);
		}
		else if(data[3].contains("EUpdate")&& data[4].equalsIgnoreCase("No")){
			Select select = new Select(driver.findElement(actionDD));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
			if(!bVal)
				//Assert.assertFalse(bVal, data[8]);
				Assert.assertFalse(bVal, "false");
			else{
				CommonFunctions.enterTextInTextbox(actionDD, data[8]);
				//	System.out.println(driver.findElements(ROHierarchyClass).size());
				Assert.assertEquals(driver.findElements(customerGroup).size(), 0, "Update Engineering Not Editable"); 
			}
		}
		else
		{
			log.info("For this Engineering is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyUpdate()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Weights verification:Read_View
public static boolean verifyWeightsReadView(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);

		if(data[3].contains("WRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			if(driver.findElements(Weight).size() != 0){
				String Weight1 =driver.findElement(Weight).getText();
				System.out.println(Weight1);
				Assert.assertEquals(Weight1.trim(), "Weight:");
				log.info("Weight is Present");
			}else{
				log.error("Weight is Absent");
			}
		}
		else if(data[3].contains("WRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Weight).size() != 0){
				System.out.println("Weight is Present");
				log.error("Weight is Present");
			}else{
				log.info("Weight is Absent");
			}
		}
		else
		{
			log.info("For this Weight is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyReadView()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Weight verification:Update
public static boolean verifyWeightUpdate(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]); 
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);
		//put all actions dropdown methods in get text and print it
		String str = driver.findElement(actionDD).getText();
		System.out.println(str);
		//find update colorway season action 
		boolean bval= findString1(str.trim(), data[8]);

		if(data[3].contains("Wupdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			Select select = new Select(driver.findElement(actionDD));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
			//	dropDownOptionVerification
			Assert.assertTrue(bVal);
		}
		else if(data[3].contains("Wupdate")&& data[4].equalsIgnoreCase("No")){
			Select select = new Select(driver.findElement(actionDD));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(data[8],options);
			//	CommonFunctions.clickButtonOrLink(save, "Link", "Colorway Updated");
			//	dropDownOptionVerification

			//write the code that system gives error and not allowing it to update

			if(!bVal)
				//Assert.assertFalse(bVal, data[8]);
				Assert.assertFalse(bVal, "false");
			else{
				CommonFunctions.enterTextInTextbox(actionDD, data[8]);
				//	System.out.println(driver.findElements(ROHierarchyClass).size());
				Assert.assertEquals(driver.findElements(languageCode).size(), 0, "Weight is Editable"); 
			}
		}
		else
		{
			log.info("For this Weight is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyUpdate()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Contents table verification:Read_View
public static boolean verifReadViewContentsTable(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//	searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		//navigate to details page
		driver.findElement(Details).click();
		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);
		String bvalView = driver.findElement(ContentsTableView).getText();

		if(data[3].contains("CTRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			if(driver.findElements(Contentstable).size() != 0 && bvalView.contains("View")){
				String Contenttable1 =driver.findElement(Contentstable).getText();
				System.out.println(Contenttable1);
				Assert.assertEquals(Contenttable1.trim(), "Trademark Pallet - Contents:");
				log.info("Trademark pallet contents table is Present");
			}else{
				log.error("Trademark pallet contents table is Absent");
			}
		}
		else if(data[3].contains("CTRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Contentstable).size() != 0  && bvalView.contains("View")){
				System.out.println("Trademark pallet contents table is Present");
				log.error("Trademark pallet contents table is Present");
			}else{
				log.info("Trademark pallet contents table is Absent");
			}
		}
		else
		{
			log.info("For this Trademark pallet contents table is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyReadView()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Trademark Pallet - contents :Update
public static boolean verifyTPContentsTableUpdate(String[] data) throws Exception{
	try{
		//	boolean bval = driver.findElement(ContentsTableEdit).isDisplayed();
		int bval = driver.findElements(ContentsTableEdit).size();
		if(data[3].contains("Cupdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			//Assert.assertTrue(bval);
			Assert.assertEquals(bval, 1, "Edit is present");
		}
		else if(data[3].contains("Cupdate")&& data[4].equalsIgnoreCase("No")){
			//Assert.assertFalse(bval);
			Assert.assertEquals(bval, 0, "Edit is not present");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyUpdate()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Display table verification:Read_View
public static boolean verifyReadViewDisplayTable(String[] data) throws Exception{
	try{
		//			colorWayName=System.getProperty("ColorwayKeyName");
		//			//Search colorways
		//			//searchColorways(colorWayName); 
		//			searchColorways(data[10]); 
		driver.navigate().refresh();
		CommonProjectFunctions.searchProduct(data[9]); 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");

		//navigate to details page

		driver.findElement(Details).click();

		// Select season from DD
		CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[7]);
		//select colorway from DD
		//	CommonFunctions.selectFromDropDownByVisibleText(ColorwayNameDD, data[10]);
		CommonFunctions.selectFromDropDownByIndex(ColorwayNameDD, 1);

		String bvalView = driver.findElement(DisplayTableView).getText();

		if(data[3].contains("DIRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
			if(driver.findElements(Displaytable).size() != 0 && bvalView.contains("View")){
				String Contenttable1 =driver.findElement(Displaytable).getText();
				System.out.println(Contenttable1);
				Assert.assertEquals(Contenttable1.trim(), "Display Item Out Of Package Fully Assembled:");
				log.info("Display Item Out Of Package Fully Assembled is Present");
			}else{
				log.error("Display Item Out Of Package Fully Assembled is Absent");
			}
		}
		else if(data[3].contains("DIRead_View")&& data[4].equalsIgnoreCase("No")){
			if(driver.findElements(Displaytable).size() != 0  && bvalView.contains("View")){
				System.out.println("Display Item Out Of Package Fully Assembled is Present");
				log.error("Display Item Out Of Package Fully Assembled is Present");
			}else{
				log.info("Display Item Out Of Package Fully Assembled is Absent");
			}
		}
		else
		{
			log.info("For this Display Item Out Of Package Fully Assembled is not applicable(NA)");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyReadView()", e);
	return false;
	}
	return true;
}

//Function consist scenario : Display Item Out Of Package Fully Assembled :Update
public static boolean verifyDisplaytableUpdate(String[] data) throws Exception{
	try{
		//	boolean bval = driver.findElement(DisplayTableEdit).isDisplayed();
		int bval = driver.findElements(DisplayTableEdit).size();
		if(data[3].contains("DIupdate")&& data[4].equalsIgnoreCase("Yes")){//Update
			//	Assert.assertTrue(bval);
			Assert.assertEquals(bval, 1, "Edit is present");
		}
		else if(data[3].contains("DIupdate")&& data[4].equalsIgnoreCase("No")){
			//	Assert.assertFalse(bval);
			Assert.assertEquals(bval, 0, "Edit is not present");
		}
	}catch(Exception e){ fail=true;
	log.error("Exception in verifyUpdate()", e);
	return false;
	}
	return true;
}

public static boolean searchColorways(String colorwayName) throws Exception{
	try{
		//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(searchProduct));
		Thread.sleep(2000);
		SeleniumDriver.driver.switchTo().defaultContent();
		SeleniumDriver.driver.switchTo().frame("headerframe");
		//selecting colorways from dropdown
		CommonFunctions.selectFromDropDownByVisibleText(searchColorways, "Colorways");
		// Sending Colorway data in text box and search
		SeleniumDriver.driver.findElement(searchProduct).clear();
		CommonFunctions.enterTextInTextbox(searchProduct, colorwayName);
		if(CommonFunctions.waitForElementTobeClickable(searchIcon))
			CommonFunctions.clickButtonOrLink(searchIcon, "Btn", "SearchButton");
	}catch(Exception e){
		log.error("Exception in searchColorways()", e);
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
