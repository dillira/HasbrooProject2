package com.hasbro.security;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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


public class Product extends TestsuiteBase{

	static Product prd = new Product();
	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String prodNum;
	public static By libraryLink = By.id("librariesContentIcon");

	public static By seasonLink = By.linkText("Season");
	public static By newLink = By.xpath("//a[text()='New']");
	public static By productType = By.xpath("//a[contains(text(),'Product Type')]");
	public static By retailItem = By.xpath("//a[text()='Retail Item']");
	public static By seasonYear = By.id("ptc_str_1");
	public static By createBtn = By.xpath("//a[text()='Create']");
	//My Season 
	public static By mySeasonLink = By.id("seasonsContentIcon");
	public static By mySeasonYear = By.id("seasonSelectList");
	public static By lineSheet = By.linkText("Line Sheet");
	public static By lineSheetView = By.id("viewId");
	public static By lineSheetAction = By.name("linePlanActions");

	public static By labelHierarchy = By.xpath("//td[contains(text(),'Hierarchy')]");
	public static By labelGeneralAttri = By.xpath("//td[contains(text(),'General Attributes')]");
	public static By labelRetailItemCount = By.xpath("//td[contains(text(),'Retail Item Counts')]");
	public static By labelGlobalFroecasting = By.xpath("//td[contains(text(),'Global Forecasting')]");
	public static By labelFinance = By.xpath("//td[contains(text(),'Finance')]");
	public static By labelContentTable = By.xpath("//td[contains(text(),'Trademark Pallet - Contents:')]");
	public static By labelBundleContent = By.xpath("//td[contains(text(),'Bundle Pack - Contents:')]");


	public static By labelWaveRequirements1 = By.xpath("//td[contains(text(),'Wave Requirements 1 - Dates')]");
	public static By labelWaveRequirements2 = By.xpath("//td[contains(text(),'Wave Requirements 2 - Quantity')]");
	public static By labelWaveRequirements4 = By.xpath("//td[contains(text(),'Wave Requirements 4 - Suffix')]");
	public static By labelWaveRequirements5 = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution')]");

	public static By labelWaveRequirements1Edit = By.xpath("//td[contains(text(),'Wave Requirements 1 - Dates')]//following::a[text()='Edit'][1]");
	public static By labelWaveRequirements2Edit = By.xpath("//td[contains(text(),'Wave Requirements 2 - Quantity')]//following::a[text()='Edit'][1]");
	public static By labelWaveRequirements4Edit = By.xpath("//td[contains(text(),'Wave Requirements 4 - Suffix')]//following::a[text()='Edit'][1]");
	public static By labelWaveRequirements5Edit = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution')]//following::a[text()='Edit'][1]");
	public static By labelTrademarkPalletContentsEdit = By.xpath("//td[contains(text(),'Trademark Pallet - Contents:')]//following::a[text()='Edit'][1]");
	public static By labelBundlePackContentsEdit = By.xpath("//td[contains(text(),'Bundle Pack - Contents:')]//following::a[text()='Edit'][1]");

	public static By AssortmentSolid = By.xpath("//a[contains(text(),'Assortment/Solid')]");
	public static By Class = By.xpath("//td[contains(text(),'Class')]//following::select[1]");
	public static By ROHierarchyClass = By.id("hbClassPr");

	public static By Editable_RIC_CFSpring = By.xpath("//td[contains(text(),'CF Spring')]//following::input[1]");
	public static By RO_RIC_CFSpring = By.id("hbCFSpringPr");

	public static By Editable_SRPPriceUSD= By.xpath("//td[contains(text(),'Target Cost (USD)')]//following::input[1]");
	public static By RO_SRPPriceUSD= By.id("hbSRPPriceUSDPr");

	public static By Editable_Finance_TargetCostUSD= By.xpath("//td[contains(text(),'Target Cost (USD)')]//following::input[1]");
	public static By RO_Finance_TargetCostUSD= By.id("hbTargetCostUSD");

	public static By Division = By.xpath("//td[contains(text(),'Division')]//following::select[1]");
	public static By Brand = By.xpath("//td[contains(text(),'Brand')]//following::select[1]");
	public static By segment = By.xpath("//td[contains(text(),'Segment')]//following::select[1]");
	public static By InternalClassification = By.xpath("//td[contains(text(),'Internal Classification')]//following::select[1]");
	public static By AstSolid = By.xpath("//td[contains(text(),'*Ast')]//following::select[1]");
	public static By ProductName = By.xpath("//td[contains(text(),'Product Name')]//following::input[1]");
	public static By ProductNameRO = By.xpath("//td[contains(text(),'Product Name')]//following::td[1]");

	public static By ROProductName = By.id("hbProductName");
	public static By IPSensitive = By.xpath("//td[contains(text(),'IP Sensitive')]//following::select[1]");

	public static By familyBrand = By.xpath("//td[contains(text(),'Family Brand')]//following::select[1]");
	public static By lowerAge = By.xpath("//td[contains(text(),'Lower Age')]//following::select[1]");
	public static By UpperAge = By.xpath("//td[contains(text(),'Upper Age')]//following::select[1]");


	public static By SaveBtn = By.xpath("//a[text()='Save']");
	//Create Product-Season Attributes Page
	public static By viewProductBtn = By.xpath("//a[text()='View Product.']");
	public static By distributionChannel  = By.xpath("//td[contains(text(),'Marketing Channel')]//following::select[1]");
	public static By SRPPriceUSD= By.xpath("//td[contains(text(),'SRP Price')]//following::input[1]");
	public static By USDomestic = By.xpath("//td[contains(text(),'US Domestic')]//following::input[1]");
	public static By LCPriceUSD = By.xpath("//td[contains(text(),'L/C Price')]//following::input[1]");
	public static By DOMPriceUSD= By.xpath("//td[contains(text(),'DOM Price ')]//following::input[1]");
	//For Retail
	public static By targetCostUSD= By.xpath("//td[contains(text(),'Target Cost')]//following::input[1]");

	public static By detailsTablink= By.xpath("//a[text()='Details']");
	public static By searchProduct= By.name("quickSearchCriteria");
	public static By searchIcon= By.id("searchButton");
	public static By ddDetailsAction= By.id("prodseasonActions");
	public static By ddOptionchangeState= By.xpath("//select[@id='prodseasonActions']/option[contains(@value,'changeState')]");
	public static By ddOptionDeleteProduct= By.xpath("//select[@id='prodseasonActions']/option[contains(@value,'deleteObject')]");
	public static By detailPageSeasonDD= By.id("splId");
	public static By ROInternalClassification= By.id("hbInternalClassification");
	//for Set State
	public static By setLifecycleState= By.xpath("//td[contains(text(),'Set Lifecycle State')]");
	public static By linkUpdate= By.xpath("//a[text()='Update']");

	public static By Editable_UpdateLifecycleState= By.id("lcstate");
	public static By RO_UpdateLifecycleState= By.xpath("//td[contains(text(),'Product Lifecycle State')]//following::td[1]");

	public static By season2018= By.xpath("/td[contains(text(),'Season')]//following::select[1]");
	public static By headerDeleteObject= By.xpath("//td[contains(text(),'Delete Object')]");
	public static By deleteButton= By.xpath("//a[text()='Delete']");
	//For Retail
	public static By electronicsIncluded= By.xpath("//td[contains(text(),'Electronics Included')]//following::select[1]");
	public static By softgoodsIncluded= By.xpath("//td[contains(text(),'Softgoods Included')]//following::select[1]");
	public static By optionUpdateProdSeason= By.xpath("//select[@id='prodseasonActions']/option[@value='editProductSeasonAttributes()']");

	public static By inductryShortDesc= By.xpath("//td[contains(text(),'Industry Short Description')]//following::input[1]");
	public static By inductryShortDescRO= By.xpath("//td[contains(text(),'Industry Short Description')]//following::td[1]");
	public static By  Licensor = By.xpath("//td[contains(text(),'Licensor')]//following::select[1]");
	public static By  LicensorRO = By.xpath("//td[contains(text(),'Licensor')]//following::td[1]");
	public static By  editLink = By.xpath("//a[text()='Edit']");
	//By Dilli
	public static By GlobalLamForecast=By.xpath("//td[contains(text(),'Global LAM Forecast Units')]//following::input[1]");
	public static By GlobalNAForeCast =By.xpath("//td[contains(text(),'Global NA Forecast Units')]//following::input[1]");
	public static By GlobalEuForeCast =By.xpath("//td[contains(text(),'Global EU Forecast Units')]//following::input[1]");
	public static By GlobalAsiaForeCast =By.xpath("//td[contains(text(),'Global Asia Forecast Units')]//following::input[1]");
	public static By GlobalPacificForecast =By.xpath("//td[contains(text(),'Global Pacific Forecast Units')]//following::input[1]");
	public static By ManualnputWaveForeCastChecckBox =By.xpath("//td[contains(text(),'Manually Input Wave Forecasts')]//following::input[2]"); 
	//New xpath
	public static By coBrand =By.xpath("//td[contains(text(),'Co-Brand')]//following::select[1]");
	public static By gender =By.xpath("//td[contains(text(),'Gender')]//following::select[1]");
	public static By iso =By.xpath("//td[contains(text(),'ISO')]//following::select[1]");

	public static By superCategory =By.xpath("//td[contains(text(),'Super Category')]//following::select[1]");
	public static By category =By.xpath("//td[contains(text(),'Category') and not (contains(text(),'Super Category'))]//following::select[1]");
	public static By  licensor =By.xpath("//td[contains(text(),'Licensor')]//following::select[1]");
	public static By property  =By.xpath("//td[contains(text(),'Property')]//following::select[1]");
	public static By IntroTiming=By.xpath("//td[contains(text(),'Intro Timing')]//following::select[1]"); 

	public static By  genderRO = By.id("hbGender");
	public static By  onDetailsTab = By.xpath("//span[@class='breadcrumbdivider']//following::a[@class='contextBarText' and text()='Details']");


	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String Prodname;
	static String strDelete;
	public static Boolean flagVal=true;
	public static String username;
	public static String strInductryShortDesc;
	public static String strLicensor;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcProduct(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Product Security");
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
					//	driver.quit();
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
			case "Create":
				log.info("In side :-> " + data[3]);	
				CreateProductFromLineSheet(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Update":
				log.info("In side :-> " + data[3]);	
				UpdateProduct(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Delete":
				log.info("In side :-> " + data[3]);	
				delete(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "SetState":
				log.info("In side :-> " + data[3]);	
				SetState(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "HierarchyRead":
				log.info("In side :-> " + data[3]);	
				verifyHierarchyReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "HierarchyUpdate":
				log.info("In side :-> " + data[3]);	
				verifyHierarchyUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GeneralAttirbutesRead":
				log.info("In side :-> " + data[3]);	
				verifyGeneralAttributesReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GeneralAttirbutesUpdate":
				log.info("In side :-> " + data[3]);	
				verifyGeneralAttributesUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "RetailItemCountsRead":
				log.info("In side :-> " + data[3]);	
				verifyRetailItemCountsReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "RetailItemCountsUpdate":
				log.info("In side :-> " + data[3]);	
				verifyRetailItemCountsUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GlobalFroecastingRead":
				log.info("In side :-> " + data[3]);	
				verifyGlobalFroecastingReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "GlobalFroecastingUpdate":
				log.info("In side :-> " + data[3]);	
				verifyGlobalFroecastingUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Wave Requirements 1 - Dates_Read":
				log.info("In side :-> " + data[3]);	
				verifyWaveRequirements1DatesReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "WaveRequirements 1 - DatesUpdate":
				log.info("In side :-> " + data[3]);	
				verifyWaveRequirements1DatesUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Wave Requirements 2 - Quantity _Read":
				log.info("In side :-> " + data[3]);	
				verifyWaveRequirements2QuantityReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "WaveRequirements 2 - QuantityUpdate":
				log.info("In side :-> " + data[3]);	
				verifyWaveRequirements2QuantityUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Wave Requirements 4 - Suffix_Read":
				log.info("In side :-> " + data[3]);	
				verifyWaveRequirements4SuffixReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "WaveRequirements 4 - SuffixUpdate":
				log.info("In side :-> " + data[3]);	
				verifyWaveRequirements4SuffixUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Wave Requirements 5 - Distribution_Read":
				log.info("In side :-> " + data[3]);	
				verifyWaveRequirements5DistributionReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "WaveRequirements 5 - DistributionUpdate":
				log.info("In side :-> " + data[3]);	
				verifyWaveRequirements5DistributionUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "FinanceRead":
				log.info("In side :-> " + data[3]);	
				verifyFinanceReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "FinanceUpdate":
				log.info("In side :-> " + data[3]);	
				verifyFinanceUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "BundlePackContentsRead":
				log.info("In side :-> " + data[3]);	
				verifyBundlePackContentsRead(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "BundlePackContentsUpdate":
				log.info("In side :-> " + data[3]);	
				verifyBundlePackContentsUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "ContentsTableRead":
				log.info("In side :-> " + data[3]);	
				verifyContentsTableReadView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "ContentsTableUpdate":
				log.info("In side :-> " + data[3]);	
				verifyContentsTableUpdate(data);
				log.info("Out side :-> " + data[3]);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}
			/*//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ CreateProductFromLineSheet(data); }
			//Update
			if(data[3].equalsIgnoreCase("Update"))
			{ UpdateProduct(data); }

			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState(data); }

			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete(data); }

			//Read view verification
			if(data[3].equalsIgnoreCase("HierarchyRead"))
			{	verifyHierarchyReadView(data);}
			//Update Verification
			if(data[3].equalsIgnoreCase("HierarchyUpdate"))
			{	verifyHierarchyUpdate(data);}

			if(data[3].equalsIgnoreCase("General Attirbutes"))
			{  verifyGeneralAttributesReadView(data); }
			if(data[3].equalsIgnoreCase("GeneralAttirbutesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }

			if(data[3].equalsIgnoreCase("Retail Item Counts"))
			{  verifyRetailItemCountsReadView(data); }
			if(data[3].equalsIgnoreCase("RetailItemCountsUpdate"))
			{  verifyRetailItemCountsUpdate(data); }

			if(data[3].equalsIgnoreCase("Global Froecasting"))
			{  verifyGlobalFroecastingReadView(data); }
			if(data[3].equalsIgnoreCase("GlobalFroecastingUpdate"))
			{  verifyGlobalFroecastingUpdate(data); }

			if(data[3].equalsIgnoreCase("Wave Requirements 1 - Dates"))
			{  verifyWaveRequirements1DatesReadView(data); }
			//Update
			if(data[3].equalsIgnoreCase("WaveRequirements 1 - DatesUpdate"))
			{  verifyWaveRequirements1DatesUpdate(data); }

			if(data[3].equalsIgnoreCase("Wave Requirements 2 - Quantity "))
			{  verifyWaveRequirements2QuantityReadView(data); }
			if(data[3].equalsIgnoreCase("WaveRequirements 2 - QuantityUpdate"))
			{  verifyWaveRequirements2QuantityUpdate(data); }

			if(data[3].equalsIgnoreCase("Wave Requirements 4 - Suffix"))
			{  verifyWaveRequirements4SuffixReadView(data); }
			if(data[3].equalsIgnoreCase("WaveRequirements 4 - SuffixUpdate"))
			{  verifyWaveRequirements4SuffixUpdate(data); }

			if(data[3].equalsIgnoreCase("Wave Requirements 5 - Distribution"))
			{  verifyWaveRequirements5DistributionReadView(data); }
			if(data[3].equalsIgnoreCase("WaveRequirements 5 - DistributionUpdate"))
			{  verifyWaveRequirements5DistributionUpdate(data); }

			if(data[3].equalsIgnoreCase("Finance"))
			{  verifyFinanceReadView(data); }
			if(data[3].equalsIgnoreCase("FinanceUpdate"))
			{  verifyFinanceUpdate(data); }

			if(data[3].equalsIgnoreCase("Contents Table"))
			{ verifyContentsTableReadView(data);
			}
			if(data[3].equalsIgnoreCase("ContentsTableUpdate"))
			{ verifyContentsTableUpdate(data); 
			}

			if(data[3].equalsIgnoreCase("BundlePackContentsRead"))
			{ verifyBundlePackContentsRead(data);
			}
			if(data[3].equalsIgnoreCase("BundlePackContentsUpdate"))
			{ verifyBundlePackContentsUpdate(data);
			}*/

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create Product
	public static boolean CreateProductFromLineSheet(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				CommonProjectFunctions.clickMySeasonLink();
				//Select Season Year
				CommonFunctions.selectFromDropDownByVisibleText(mySeasonYear, data[5]);
				//Click on Line Sheet link
				CommonFunctions.clickButtonOrLink(lineSheet, "link", "Line Sheet");
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				Date date = new Date();
				//Select Line sheet view
				CommonFunctions.waitForVisibilityOfElement(lineSheetView);
				if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes"))
				{
					CommonFunctions.selectFromDropDownByVisibleText(lineSheetView, data[9]);
					CommonFunctions.selectFromDropDownByVisibleText(lineSheetAction, data[8]);
					//Click on Assortment/Solid
					CommonFunctions.waitForVisibilityOfElement(By.xpath("//td[contains(text(),'Choose a Type')]"));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"), "link", "Product Type");
					System.out.println("Prod Name: "+data[15]+date.getTime());
					Prodname=data[15]+date.getTime();
					Prodname=Prodname.substring(0,8);
					CommonFunctions.enterTextInTextbox(ProductName,Prodname);
					if(!data[2].equalsIgnoreCase("Trade Marketing Display")){
						//Select Class
						//					CommonFunctions.enterTextInTextbox(Class, data[10]);
						//					CommonFunctions.enterTextInTextbox(Division, data[11]);
						//					CommonFunctions.enterTextInTextbox(Brand, data[12]);
						CommonFunctions.clickButtonOrLink(Class, "Class");
						flagVal= CommonFunctions.selectFromDropDownByVisibleText(Class, data[10]);
						bFlagVal();
						//	CommonFunctions.waitForElementTobeClickable(Division);
						CommonFunctions.clickButtonOrLink(Division, "Division");
						flagVal= CommonFunctions.selectFromDropDownByVisibleText(Division, data[11]);
						bFlagVal();
						//	CommonFunctions.waitForElementTobeClickable(Brand);
						CommonFunctions.clickButtonOrLink(Brand, "Brand");
						flagVal= CommonFunctions.selectFromDropDownByVisibleText(Brand, data[12]);
						bFlagVal();
						//	CommonFunctions.waitForElementTobeClickable(segment);
						//	CommonFunctions.clickButtonOrLink(segment, "Segment");
						//	flagVal= CommonFunctions.selectFromDropDownByVisibleText(segment, data[24]);
						//	bFlagVal();
						if(!data[2].equalsIgnoreCase("Trade Marketing Pallet")){
							CommonFunctions.enterTextInTextbox(InternalClassification, data[13]);
							//	CommonFunctions.enterTextInTextbox(AstSolid, data[14]);
							CommonFunctions.selectFromDropDownByVisibleText(AstSolid, data[14]);
							//	CommonFunctions.enterTextInTextbox(IPSensitive,data[16]);
							CommonFunctions.selectFromDropDownByVisibleText(IPSensitive, data[16]);
						}

						if(data[2].equalsIgnoreCase("Retail")|| data[2].equalsIgnoreCase("Bundle Pack")){
							//Electronics Included
							CommonFunctions.enterTextInTextbox(electronicsIncluded,data[6]);
							//Softgoods Included
							CommonFunctions.enterTextInTextbox(softgoodsIncluded,data[7]);
						}
					}
					//Click on Save Button
					CommonFunctions.clickButtonOrLink(SaveBtn, "Btn", "Save");
					prodNum=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
					SeleniumDriver.log.info("Prod name: "+ prodNum);

					if(!data[2].equalsIgnoreCase("Trademark Display")){
						//wait = new WebDriverWait(driver, 10);
						//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(viewProductBtn));
						CommonFunctions.enterTextInTextbox(distributionChannel, data[17]);
						if(data[2].equalsIgnoreCase("Assortment/Solid")){
							CommonFunctions.enterTextInTextbox(SRPPriceUSD, data[18]);
							CommonFunctions.enterTextInTextbox(USDomestic, data[19]);
							CommonFunctions.enterTextInTextbox(LCPriceUSD, data[20]);
							CommonFunctions.enterTextInTextbox(DOMPriceUSD, data[21]);
						}
						if(data[2].equalsIgnoreCase("Retail")|| data[2].equalsIgnoreCase("Bundle Pack")){
							CommonFunctions.enterTextInTextbox(targetCostUSD, data[21]);
						}
					}
					//Click View Product Button
					CommonFunctions.clickButtonOrLink(viewProductBtn, "Btn", "View Product");
				}
				else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
					WebElement mySelectElm = driver.findElement(lineSheetAction); 
					Select mySelect= new Select(mySelectElm);
					List<WebElement> options = mySelect.getOptions();
					boolean bVal=dropDownOptionVerificationActions(data[8],options);
					//	dropDownOptionVerification
					//	if(!bVal)
					Assert.assertFalse(bVal);
					/*	else{
						CommonFunctions.enterTextInTextbox(lineSheetAction, data[23]);
						System.out.println(driver.findElements(RO_RIC_CFSpring).size());
						Assert.assertEquals(driver.findElements(RO_RIC_CFSpring).size(), 1, "Retail Item Counts Not Editable"); 
					}
					 */}
				else{
					log.info("Create New: Product is not available");
				}
			}
			else //For vendor : else condition
			{
				SeleniumDriver.driver.switchTo().frame("sidebarframe");
				Assert.assertEquals(driver.findElements(mySeasonLink).size(), 0, "For vendor My Season link is not present"); 
			}
			//	}	
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in CreateProductFromLineSheet()", e);
			throw e;
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
			throw e;
		}
		//return true;
	}
	//	UpdateProduct

	public static boolean UpdateProduct(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Update
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				Assert.assertTrue(bVal);
				CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
				//	String StrProd = data[15]+"1";
				//	driver.findElement(ProductName).clear();
				//	CommonFunctions.enterTextInTextbox(ProductName, StrProd);

				if(data[2].equalsIgnoreCase("Assortment/Solid") || data[2].equalsIgnoreCase("Retail") || data[2].equalsIgnoreCase("Bundle Pack"))
				{
					Assert.assertEquals(driver.findElements(IPSensitive).size(), 1, "General Attributes are Editable"); 
				}
				if(data[2].equalsIgnoreCase("Trade Marketing Pallet") || data[2].equalsIgnoreCase("Trade Marketing Display")){
					Assert.assertEquals(driver.findElements(ProductName).size(), 1, "General Attributes are Editable"); 
				}
				//Click on save
				CommonFunctions.clickButtonOrLink(SaveBtn, "Btn", "Save");
				/*//Click on save
				CommonFunctions.clickButtonOrLink(SaveBtn, "Btn", "Save");*/
				//				String strPName=driver.findElement(ProductNameRO).getText();
				//				Assert.assertEquals(strPName, StrProd);
			}
			else if(data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				if(!bVal)
					Assert.assertFalse(bVal);
				else{
					CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
					System.out.println(driver.findElements(ROProductName).size());
					Assert.assertEquals(driver.findElements(ROProductName).size(), 1, "General Attributes Not Editable"); 
				}
			}	
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in UpdateProduct()", e);
			throw e;
		}
		return true;
	}

	public static boolean SetState(String[] data) throws Exception{
		try{
			//	driver.navigate().refresh();
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				String valULCSBeforeChange = driver.findElement(RO_UpdateLifecycleState).getText();
				CommonFunctions.clickButtonOrLink(ddDetailsAction, "btn", "Action dropdown");
				driver.findElement(ddOptionchangeState).click();
				String valULCSAfterChange=selectUpdateLifecycleState(data);
				//	Thread.sleep(1000);
				//Click on Update
				CommonFunctions.clickButtonOrLink(linkUpdate, "link", "Update");
				//verification
				String textRO_UpdateLifecycleState = driver.findElement(RO_UpdateLifecycleState).getText();
				if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
					Assert.assertEquals(textRO_UpdateLifecycleState, valULCSAfterChange);
				}
				else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
					Assert.assertNotEquals(valULCSBeforeChange, valULCSAfterChange);
				}
				else
					log.info("SetState or chageState is not applicable(NA)");
			}
			else //For vendor
			{
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				Assert.assertFalse(bVal);
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in SetState()", e);
			throw e;
		}
		return true;
	}

	public static boolean delete(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				Prodname=CommonProjectFunctions.CreateProdFromLineSheet(data[2],data[5],data[9],data[8],data[10],data[11],data[12],
						data[13],data[14],data[16],data[6],data[7],
						data[17],data[18],data[19], data[20], data[21]
								,"CHANNEL","BOYS","1D","1 MONTH","FEMALE","Yes","ACTION FIGURES & ACCESSORIES",
								"ACTION FIGURE ROLE PLAY","1D MEDIA LTD","1D","AVALON HILL","10 MONTHS");
				CommonProjectFunctions.searchProduct(Prodname);
				clickDetailsTab(data);

				CommonFunctions.clickButtonOrLink(ddDetailsAction, "btn", "Action dropdown");
				driver.findElement(ddOptionDeleteProduct).click();
				CommonFunctions.waitForVisibilityOfElement(headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(deleteButton,"btn", "Delete");
				//Accept AletPopup
				CommonFunctions.handleAlertPopUp();
				strDelete= driver.findElement(PlaceHolderDevPlan.msgDelete).getText();
				Assert.assertEquals(strDelete, "Delete Successful"); 
			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				CommonProjectFunctions.searchProduct(data[22]);
				clickDetailsTab(data);
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerificationActions(data[23],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}
			else
				log.info("Delete is not applicable(NA)");
		}catch(Exception e){  
			fail=true;
			log.error("Exception in delete()", e);
			throw e;
		}
		return true;
	}
	//This funcion is to select Update Lifecycle State	
	public static String selectUpdateLifecycleState(String[] data) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			//	System.out.println("valULCS: "+valULCS);
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released");
				//	CommonFunctions.enterTextInTextbox(Editable_UpdateLifecycleState, "Released");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released");
				//	CommonFunctions.enterTextInTextbox(Editable_UpdateLifecycleState, "Released");
			}
			else if(valULCS.contains("Released")){
				CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Under Review");
				//CommonFunctions.enterTextInTextbox(Editable_UpdateLifecycleState, "Under Review");
			}
			valULCSAfterChange = new Select(driver.findElement(Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in selectUpdateLifecycleState()", e);
			throw e;
		}
		return valULCSAfterChange;
	}

	//Function consist scenario : Click on detail tab
	public static boolean clickDetailsTab(String[] data) throws Exception{
		try{

			if(SeleniumDriver.driver.findElements(onDetailsTab).size()== 0) {
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailsTablink));
				//	if(CommonFunctions.waitForVisibilityOfElement(detailsTablink))
				CommonFunctions.clickButtonOrLink(detailsTablink, "link", "Details tab");
				//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailPageSeasonDD));
				CommonFunctions.waitForVisibilityOfElement(detailPageSeasonDD);
				//Select Season
				if(!data[3].contains("Delete")){
					if(CommonFunctions.waitForVisibilityOfElement(detailPageSeasonDD)){
						//	CommonFunctions.clickButtonOrLink(detailPageSeasonDD, "Season dropdown");
						//CommonFunctions.clickButtonOrLink(season2018, data[5]);
						CommonFunctions.selectFromDropDownByVisibleText(detailPageSeasonDD, data[5]);
					}
				}
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in clickDetailsTab()", e);
			throw e;
		}
		return true;
	}


	//Function consist scenario : Hierarchy verification:Read_View
	public static boolean verifyHierarchyReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[3].contains("HierarchyRead")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelHierarchy).size() != 0){
					String hirarchyLabel=driver.findElement(labelHierarchy).getText();
					System.out.println(hirarchyLabel);
					System.out.println(" Hierarchy:");
					Assert.assertEquals(hirarchyLabel, " Hierarchy:");
					log.info("Hirarchy label is Present");
				}else{
					log.error("Hirarchy label is Absent");
					fail=true;
				}
			}
			else if(data[3].contains("HierarchyRead")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelHierarchy).size() != 0){
					System.out.println("Hirarchy label is Present");
					log.error("Hirarchy label is Present");
					fail=true;
				}else{
					log.info("Hirarchy label is Absent");
				}
			}
			else
			{
				log.info("For this Hieranchy is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyHierarchy()", e);
			throw e;
		}
		return true;
	}

	//Function consist scenario : Hierarchy verification:Update
	public static boolean verifyHierarchyUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Update
				Select select = new Select(driver.findElement(ddDetailsAction));
				//	List<WebElement> options = select.getOptions();
				//	boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				//	Assert.assertTrue(bVal);
				CommonFunctions.selectFromDropDownByVisibleText(ddDetailsAction, data[23]);
				CommonFunctions.selectFromDropDownByVisibleText(Product.gender, "FEMALE");
				//Click on Save
				CommonFunctions.clickButtonOrLink(SaveBtn, "btn", "Save");
				Assert.assertEquals(driver.findElement(genderRO).getText().trim(), "FEMALE");
				log.info(" Hierarchy is updatable ");
			}
			else if(data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				if(!bVal)
					Assert.assertFalse(bVal);
				else{
					CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
					System.out.println(driver.findElements(ProductName).size());
					Assert.assertEquals(driver.findElements(ProductName).size(), 0, "Hirarchy Not Editable"); 
				}
			}
			else
			{
				log.info("For this Hieranchy is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyHierarchy()", e);
			throw e;
		}
		return true;
	}

	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttri).size() != 0){
					String GALabel=driver.findElement(labelGeneralAttri).getText();
					System.out.println(GALabel);
					System.out.println(" General Attributes:");
					Assert.assertEquals(GALabel, " General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					log.error("General Attributes label is Absent");
					fail=true;
				}
			}
			else if(data[3].contains("General Attirbutes")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttri).size() != 0){
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
			throw e;
		}
		return true;
	}
	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Update
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				Assert.assertTrue(bVal);
				CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
				String StrProd = data[15]+"1";
				driver.findElement(ProductName).clear();
				CommonFunctions.enterTextInTextbox(ProductName, StrProd);

				if(data[2].equalsIgnoreCase("Assortment/Solid") && data[2].equalsIgnoreCase("Retail") && data[2].equalsIgnoreCase("Bundle Pack"))
				{
					Assert.assertEquals(driver.findElements(IPSensitive).size(), 1, "General Attributes are Editable"); 
					log.info("****Verification :General Attributes are Editable****");
				}
				if(data[2].equalsIgnoreCase("Trade Marketing Pallet") && data[2].equalsIgnoreCase("Trade Marketing Display")){
					Assert.assertEquals(driver.findElements(ProductName).size(), 1, "General Attributes are Editable"); 
					log.info("****Verification :General Attributes are Editable****");
				}
				//Click on save
				CommonFunctions.clickButtonOrLink(SaveBtn, "Btn", "Save");
				//	String strPName=driver.findElement(ProductNameRO).getText();
				//	Assert.assertEquals(strPName, StrProd);
			}
			else if(data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				if(!bVal) {
					Assert.assertFalse(bVal);
					log.info("****Verification :General Attributes are not Editable****");
				}
				else{
					CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
					System.out.println(driver.findElements(IPSensitive).size());
					Assert.assertEquals(driver.findElements(IPSensitive).size(), 0, "General Attributes Not Editable");
					log.info("****Verification :General Attributes are not Editable****");
				}
			}	
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			throw e;
		}
		return true;
	}
	//Function consist scenario : Retail Item Counts:Read_View
	public static boolean verifyRetailItemCountsReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelRetailItemCount).size() != 0){
					String label=driver.findElement(labelRetailItemCount).getText();
					System.out.println(label);
					System.out.println(" Retail Item Counts:");
					Assert.assertEquals(label, " Retail Item Counts:");
					log.info("Retail Item Counts label is Present");
				}else{
					log.error("Retail Item Counts label is Absent");
					fail=true;
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelRetailItemCount).size() != 0){
					System.out.println("Retail Item Counts label is Present");
					log.error("Retail Item Counts label is Present");
					fail=true;
				}else{
					Assert.assertEquals(driver.findElements(labelRetailItemCount).size(), 0, "Retail Item Counts label is Absent"); 
					log.info("Retail Item Counts label is Absent");
				}
			}
			else
			{
				log.info("For this Retail Item Counts is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyRetailItemCountsReadView()", e);
		throw e;
		}
		return true;
	}
	//Function consist scenario : Retail Item Counts:Update
	public static boolean verifyRetailItemCountsUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Update
				//	dropDownOptionVerification
				//	CommonFunctions.enterTextInTextbox(ddDetailsAction,data[23]);
				//	CommonFunctions.selectFromDropDownByVisibleText(ddDetailsAction, data[23]);
				//optionUpdateProdSeason
				CommonFunctions.clickButtonOrLink(ddDetailsAction, "Select", "Action");
				CommonFunctions.clickButtonOrLink(optionUpdateProdSeason, "Select Option", "Update Product Season");
				System.out.println(driver.findElements(Editable_RIC_CFSpring).size());
				Assert.assertEquals(driver.findElements(Editable_RIC_CFSpring).size(), 1, "Retail Item Counts are Editable"); 
				log.info("*****Verification : Retail Item Counts are Editable*****");
			}
			else if(data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				if(!bVal)
					Assert.assertFalse(bVal);
				else{
					//	Assert.fail();
					CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
					if(!data[0].contains("vuser") && !data[0].contains("vendora")){ 
						System.out.println(driver.findElements(RO_RIC_CFSpring).size());
						Assert.assertEquals(driver.findElements(RO_RIC_CFSpring).size(), 1, "Retail Item Counts Not Editable"); 
						log.info("*****Verification : Retail Item Counts Not Editable*****");
					}
					else
					{
						Assert.assertEquals(driver.findElements(RO_RIC_CFSpring).size(), 0, "Retail Item Counts Not Editable"); 
						log.info("*****Verification : Retail Item Counts Not Editable*****");
					}
				}
			}
			else
			{
				log.info("For this Retail Item Counts is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyRetailItemCountsUpdate()", e);
		throw e;
		}
		return true;
	}

	//Function consist scenario :GlobalFroecasting:Read_View
	public static boolean verifyGlobalFroecastingReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGlobalFroecasting).size() != 0){
					String label=driver.findElement(labelGlobalFroecasting).getText();
					System.out.println(label);
					Assert.assertEquals(label," Global Forecasting:");
					log.info("****verification: Global Froecasting label is Present****");
				}else{
					log.error("****verification: Global Froecasting label is Absent****");
					fail=true;
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGlobalFroecasting).size() != 0){
					System.out.println("Global Froecasting label is Present");
					log.error("****verification:Global Froecasting label is Present****");
					fail=true;
				}else{
					Assert.assertEquals(driver.findElements(labelGlobalFroecasting).size(), 0, "Global Froecasting label is Absent"); 
					log.info("****verification:Global Froecasting label is Absent****");
				}
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in verifyGlobalFroecastingReadView()", e);
			throw e;
		}
		return true;
	}
	//Function consist scenario : GlobalFroecasting:Update
	public static boolean verifyGlobalFroecastingUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Update
				//	dropDownOptionVerification
				//CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
				CommonFunctions.clickButtonOrLink(ddDetailsAction, "Select", "Action");
				CommonFunctions.clickButtonOrLink(optionUpdateProdSeason, "Select Option", "Update Product Season");
				System.out.println(driver.findElements(Editable_SRPPriceUSD).size());
				Assert.assertEquals(driver.findElements(Editable_SRPPriceUSD).size(), 1, "Global Froecasting are Editable"); 

			}
			else if(data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				if(!bVal)
					Assert.assertFalse(bVal);
				else{
					//Assert.fail();
					CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
					//System.out.println(driver.findElements(RO_SRPPriceUSD).size());
					Assert.assertEquals(driver.findElements(RO_SRPPriceUSD).size(), 1, "Global Froecasting Not Editable"); 
				}
			}
			else
			{
				log.info("For this Global Froecasting is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyGlobalFroecastingUpdate()", e);
		throw e;
		}
		return true;
	}

	//Function consist scenario :Wave Requirements 1 - Dates:Read_View
	public static boolean verifyWaveRequirements1DatesReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			driver.findElement(ROInternalClassification).click();
			if(data[3].contains("Wave Requirements 1 - Dates")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelWaveRequirements1).size() != 0){
					String label=driver.findElement(labelWaveRequirements1).getText();
					System.out.println(label);
					System.out.println(" Wave Requirements 1 - Dates");
					Assert.assertEquals(label, " Wave Requirements 1 - Dates:");
					log.info("Wave Requirements 1 - Dates label is Present");
				}else{
					log.error("Wave Requirements 1 - Dates label is Absent");
					Assert.fail("Wave Requirements 1 - Dates label is Absent");
				}
			}
			else if(data[3].contains("Wave Requirements 1 - Dates")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelWaveRequirements1).size() != 0){
					System.out.println("Wave Requirements 1 - Dates label is Present");
					log.error("Wave Requirements 1 - Dates label is Present");
					fail=true;
				}else{
					log.info("Wave Requirements 1 - Dates label is Absent");
				}
			}
			else
			{
				log.info("For this Wave Requirements 1 - Dates is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyWaveRequirements1DatesReadView()", e);
		throw e;
		}
		return true;
	}
	//Function consist scenario : Wave Requirements 1 - Dates:Update
	public static boolean verifyWaveRequirements1DatesUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			//	Boolean wr1 = driver.findElement(labelWaveRequirements1Edit).isDisplayed();
			if(data[4].equalsIgnoreCase("Yes")){//Update
				//Assert.assertTrue(wr1, "Edit is displayed");
				Assert.assertEquals(driver.findElements(labelWaveRequirements1Edit).size(), 1, "Edit is present");

			}
			else if(data[4].equalsIgnoreCase("No")){
				//	Assert.assertFalse(wr1, "Edit is not displayed");
				Assert.assertEquals(driver.findElements(labelWaveRequirements1Edit).size(), 0, "Edit is not present");
			}
			else
			{
				log.info("For this Wave Requirements 1 - Dates is not applicable(NA)");
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in verifyWaveRequirements1DatesUpdate()", e);
			throw e;
		}
		return true;
	}

	//Function consist scenario :Wave Requirements 2 - Quantity:Read_View
	public static boolean verifyWaveRequirements2QuantityReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			driver.findElement(ROInternalClassification).click();
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelWaveRequirements2).size() != 0){
					String label=driver.findElement(labelWaveRequirements2).getText();
					System.out.println(label);
					System.out.println(" Wave Requirements 2 - Quantity");
					Assert.assertEquals(label, " Wave Requirements 2 - Quantity:");
					log.info("Wave Requirements 2 - Quantity label is Present");
				}else{
					log.error("Wave Requirements 2 - Quantity label is Absent");
					fail=true;
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelWaveRequirements2).size() != 0){
					System.out.println("Wave Requirements 2 - Quantity label is Present");
					log.error("Wave Requirements 2 - Quantity label is Present");
					fail=true;
				}else{
					log.info("Wave Requirements 2 - Quantity label is Absent");
				}
			}
			else
			{
				log.info("For this Wave Requirements 2 - Quantity is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyWaveRequirements2QuantityReadView()", e);
		throw e;
		}
		return true;
	}
	//Function consist scenario : Wave Requirements 2 - Quantity:Update
	public static boolean verifyWaveRequirements2QuantityUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			//	Boolean wr1 = driver.findElement(labelWaveRequirements2Edit).isDisplayed();
			if(data[4].equalsIgnoreCase("Yes")){//Update
				Assert.assertEquals(driver.findElements(labelWaveRequirements2Edit).size(), 1, "Edit is present");

			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(labelWaveRequirements2Edit).size(), 0, "Edit is not present");
			}
			else
			{
				log.info("For this Wave Requirements 2 - Quantity is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyWaveRequirements2QuantityUpdate()", e);
		throw e;
		}
		return true;
	}	

	//Function consist scenario :Wave Requirements 4 - Suffix:Read_View
	public static boolean verifyWaveRequirements4SuffixReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			driver.findElement(ROInternalClassification).click();
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelWaveRequirements4).size() != 0){
					String label=driver.findElement(labelWaveRequirements4).getText();
					System.out.println(label);
					System.out.println(" Wave Requirements 4 - Suffix");
					Assert.assertEquals(label, " Wave Requirements 4 - Suffix:");
					log.info("Wave Requirements 4 - Suffix label is Present");
				}else{
					log.error("Wave Requirements 4 - Suffix label is Absent");
					fail=true;
				}
			}
			else if(data[3].contains("Wave Requirements 4 - Suffix")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelWaveRequirements4).size() != 0){
					System.out.println("Wave Requirements 4 - Suffix label is Present");
					log.error("Wave Requirements 4 - Suffix label is Present");
					fail=true;
				}else{
					log.info("Wave Requirements 4 - Suffix label is Absent");
				}
			}
			else
			{
				log.info("For this Wave Requirements 4 - Suffix is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyWaveRequirements4SuffixReadView()", e);
		throw e;
		}
		return true;
	}
	//Function consist scenario : Wave Requirements 4 - Suffix:Update
	public static boolean verifyWaveRequirements4SuffixUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			//	Boolean wr1 = driver.findElement(labelWaveRequirements4Edit).isDisplayed();
			if(data[4].equalsIgnoreCase("Yes")){//Update
				Assert.assertEquals(driver.findElements(labelWaveRequirements4Edit).size(), 1, "Edit is present");

			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(labelWaveRequirements4Edit).size(), 0, "Edit is not present");
			}
			else
			{
				log.info("For this Wave Requirements 4 - Suffix is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyWaveRequirements4SuffixUpdate()", e);
		throw e;
		}
		return true;
	}
	//Function consist scenario :Wave Requirements 5 - Distribution:Read_View
	public static boolean verifyWaveRequirements5DistributionReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			driver.findElement(ROInternalClassification).click();
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelWaveRequirements5).size() != 0){
					String label=driver.findElement(labelWaveRequirements5).getText();
					System.out.println(label);
					System.out.println(" Wave Requirements 5 - Distribution");
					Assert.assertEquals(label, " Wave Requirements 5 - Distribution:");
					log.info("Wave Requirements 5 - Distribution label is Present");
				}else{
					log.error("Wave Requirements 5 - Distribution label is Absent");
					fail=true;
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelWaveRequirements5).size() != 0){
					System.out.println("Wave Requirements 5 - Distribution label is Present");
					log.error("Wave Requirements 5 - Distribution label is Present");
					fail=true;
				}else{
					log.info("Wave Requirements 5 - Distribution label is Absent");
				}
			}
			else
			{
				log.info("For this Wave Requirements 5 - Distribution is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyWaveRequirements5DistributionReadView()", e);
		throw e;
		}
		return true;
	}
	//Function consist scenario : Wave Requirements 5 - Distribution:Update
	public static boolean verifyWaveRequirements5DistributionUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			//	Boolean wr1 = driver.findElement(labelWaveRequirements5Edit).isDisplayed();
			if(data[4].equalsIgnoreCase("Yes")){//Update
				Assert.assertEquals(driver.findElements(labelWaveRequirements5Edit).size(), 1, "Edit is present");

			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(labelWaveRequirements5Edit).size(), 0, "Edit is not present");
			}
			else
			{
				log.info("For this Wave Requirements 5 - Distribution is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyWaveRequirements5DistributionUpdate()", e);
		throw e;
		}
		return true;
	}	

	//Function consist scenario :Finance:Read_View
	public static boolean verifyFinanceReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				System.out.println(driver.findElements(labelFinance).size());
				if(driver.findElements(labelFinance).size() != 0){
					String label=driver.findElement(labelFinance).getText();
					System.out.println(label);
					System.out.println(" Finance");
					Assert.assertEquals(label, " Finance:");
					log.info("Finance label is Present");
				}else{
					log.error("Finance label is Absent");
					fail=true;
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				System.out.println(driver.findElements(labelFinance).size());
				int finSize=driver.findElements(labelFinance).size();
				if(finSize != 0){
					System.out.println("Finance label is Present");
					log.info("Finance label is Present");
					Assert.assertEquals(finSize, 1);
				}else{
					log.error("Finance label is Absent");
					fail=true;
				}
			}
			else
			{
				log.info("For this Finance is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyFinanceReadView()", e);
		throw e;
		}
		return true;
	}

	//Function consist scenario : Finance:Update
	public static boolean verifyFinanceUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Update
				//	dropDownOptionVerification
				//	CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
				CommonFunctions.clickButtonOrLink(ddDetailsAction, "Select", "Action");
				CommonFunctions.clickButtonOrLink(optionUpdateProdSeason, "Select Option", "Update Product Season");
				System.out.println(driver.findElements(Editable_Finance_TargetCostUSD).size());
				Assert.assertEquals(driver.findElements(Editable_Finance_TargetCostUSD).size(), 1, "Finance are Editable"); 

			}
			else if(data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddDetailsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[23],options);
				//	dropDownOptionVerification
				if(!bVal)
					Assert.assertFalse(bVal);
				else{
					CommonFunctions.enterTextInTextbox(ddDetailsAction, data[23]);
					System.out.println(driver.findElements(RO_Finance_TargetCostUSD).size());
					Assert.assertEquals(driver.findElements(RO_Finance_TargetCostUSD).size(), 1, "Finance Not Editable"); 
				}
			}
			else
			{
				log.info("For this Finance is not applicable(NA)");
			}
		}catch(Exception e){  fail=true;
		log.error("Exception in verifyFinanceUpdate()", e);
		throw e;
		}
		return true;
	}	
	public static boolean bFlagVal() throws Exception{
		try{
			if (flagVal)
			{ return true;}
			else{
				fail=true;
				Assert.fail("Cascading not working");
				return false;
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in bFlagVal()", e);
			throw e;
		}
	}

	//Function consist scenario : Trademark Pallet - Contents: Read_View
	public static boolean verifyContentsTableReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelContentTable).size() != 0){
					String CTLabel=driver.findElement(labelContentTable).getText();
					System.out.println(CTLabel);
					Assert.assertEquals(CTLabel.trim(), "Trademark Pallet - Contents:");
					log.info("Trademark Pallet - Contents: label is Present");
				}else{
					log.error("Trademark Pallet - Contents: label is Absent");
					fail=true;
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelContentTable).size() != 0){
					log.info("Trademark Pallet - Contents: label is Present");
					//log.error("Trademark Pallet - Contents: label is Present");
					fail=true;
				}else{
					log.info("Trademark Pallet - Contents: label is Absent");
				}
			}
			else
			{
				log.info("For this Trademark Pallet - Contents: is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyContentsTableReadView()", e);
			throw e;
		}
		return true;
	}
	//Function consist scenario : Trademark Pallet - Contents: //Update
	public static boolean verifyContentsTableUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);

			if(data[4].equalsIgnoreCase("Yes")){//Update
				System.out.println(driver.findElements(labelTrademarkPalletContentsEdit).size());
				Assert.assertEquals(driver.findElements(labelTrademarkPalletContentsEdit).size(), 1, "Edit Not Editable"); 
			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(labelTrademarkPalletContentsEdit ).size(), 0, " Edit is not available"); 
			}
			else
			{
				log.info("For this Trademark Pallet - Contents:  is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyContentsTableUpdate()", e);
			throw e;
		}
		return true;
	}

	//Function consist scenario :  Bundle Pack - Contents:  Read_View
	public static boolean verifyBundlePackContentsRead(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			if(data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelBundleContent).size() != 0){
					String CTLabel=driver.findElement(labelBundleContent).getText();
					System.out.println(CTLabel);
					Assert.assertEquals(CTLabel.trim(), "Bundle Pack - Contents:");
					log.info("Bundle Pack - Contents:  label is Present");
				}else{
					log.error(" Bundle Pack - Contents:  label is Absent");
					fail=true;
				}
			}
			else if(data[3].contains("BundlePackContentsRead")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelBundleContent).size() != 0){
					log.info(" Bundle Pack - Contents:  label is Present");
					log.error(" Bundle Pack - Contents:  label is Present");
					fail=true;
				}else{
					log.info(" Bundle Pack - Contents:  label is Absent");
				}
			}
			else
			{
				log.info("For this Trademark Pallet - Contents: is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyContentsTableReadView()", e);
			throw e;
		}
		return true;
	}
	//Function consist scenario :  Bundle Pack - Contents:  //Update
	public static boolean verifyBundlePackContentsUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[22]);
			clickDetailsTab(data);
			//	Boolean wr1 = driver.findElement(labelBundlePackContentsEdit).isDisplayed();
			if(data[4].equalsIgnoreCase("Yes")){//Update
				Assert.assertEquals(driver.findElements(labelBundlePackContentsEdit ).size(), 1, "Edit is present"); 

			}
			else if(data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(labelBundlePackContentsEdit ).size(), 0, " Edit is not present"); 
			}
			else
			{
				log.info("For this Bundle Pack - Contents: is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyBundlePackContentsUpdate()", e);
			throw e;
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
