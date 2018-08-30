package com.hasbro.workflow;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

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

//import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Color;
import com.hasbro.security.Colorway;
import com.hasbro.security.GlobalLinePlan;
import com.hasbro.security.PlaceHolderDevPlan;
import com.hasbro.security.Product;
import com.hasbro.security.Sample;
import com.hasbro.security.SourcingConfig;
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

public class SmokeFlow extends TestsuiteBase{
	private static final String vuser1 = null;
	private static final String plm = null;
	public static By totalRetailItemCount = By.id("r7_hbTotalRetailItemCount");

	public static By objClass = By.id("r2_dimExpander");
	public static By objDivision = By.id("r3_dimExpander");
	public static By objBrand = By.id("r4_dimExpander");

	public static By expImg2 = By.id("expanderImage2");
	public static By expImg3 = By.id("expanderImage3");
	public static By expImg4 = By.id("expanderImage4");
	public static By expImg6 = By.id("expanderImage6");

	//Placeholder

	public static By generatePlaceholders = By.xpath("//a[text()='Generate Placeholders']");
	public static By plSucessGen = By.xpath("//td[contains(text(),'Placeholders successfully generated')]");
	public static By devPlan = By.xpath("//select[@id='viewId']");  ////select[@id='viewId']/option[@selected='']
	public static By filterButton = By.id("filterButton");
	public static By creSearchFilter = By.id("displayName");
	public static By headingUpdateSearchPref = By.xpath("//td[contains(text(),' Update Search Preference')]");

	public static By selectBtn= By.xpath("//a[text()='Select']");
	public static By colorwayDropDown = By.id("contextSKUId");
	public static By ddFilterOption = By.name("attributeSelect");
	public static By lbFilterOption = By.xpath("//select[contains(@id,'PRODUCTOptions')]");
	public static By addLink = By.xpath("//a[text()='Add']");
	public static By addBtn = By.xpath("//div[@id='attDisplayDiv']/table/tbody/tr/td[@class='button']/a");
	public static By updateBtn = By.xpath("//a[text()='Update']");
	public static By createdByDD = By.xpath("//select[contains(@id,'PLACEHOLDER_ID')]");
	public static By returnBtn = By.xpath("//a[text()='Return']");
	public static By optionBtn = By.xpath("//a[text()='Options']");
	public static By placeHolderDD = By.id("placeholderMode");
	public static By runBtn = By.xpath("//a[text()='Run']");
	public static By filterDDonLS = By.id("filterId");
	//	public static By filterAction = By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[2]/a");
	public static By filterAction = By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[2]/td[2]/a");
	public static By placeholderOption = By.xpath("//div[@id='actionOptions']/div/a");
	public static By updatePlaceholder = By.xpath("//div[@id='secondaryActionOptions']/div[3]/a");
	public static By updatePlaceholderHeading = By.xpath("//td[contains(text(),'Update Placeholder')]");
	public static By globalForcasting = By.xpath("//td[contains(text(),'Global Forecasting')]");
	public static By retailItemCounts = By.xpath("//td[contains(text(),'Retail Item Counts')]");
	public static By retailItemCountTotalLabel = By.xpath("//td[contains(text(),'Retail Item Count Total (PLC)')]//following::td[1]");
	public static By AssSolid = By.xpath("//a[contains(text(),'Assortment/Solid')]");
	public static By myWork = By.xpath("//*[@id='myWorkContentIcon']");
	public static By phPlusIcon = By.xpath("//img[contains(@id,'com.lcs.wc.placeholder.PlaceholderContentIcon')]");
	public static By reviewCreateAstSolidTask = By.xpath("//a[contains(text(),'Solid Task')]");
	public static By plusSign = By.xpath("//img[contains(@src,'/Windchill/netmarkets/images/add')]");
	public static By completeBtn = By.xpath("//a[text()='Complete']");
	public static By comment = By.xpath(".//textarea[contains(@name,'COMMENTS')]");
	public static By selectCheckBox = By.xpath("//div[@id='allSpace']//tr[@class='GROUPHEADER']/td[1]/input");

	public static By relationShipTab = By.xpath("//a[text()='Relationships']");
	public static By copyLinkProd = By.xpath("//a[contains(text(),'Copy / Link Product')]");

	public static By seasonDD = By.id("seasondata");
	public static By productType = By.id("productTypedata");
	public static By relationShipType = By.id("copyModedata");
	public static By nextBtn = By.xpath("//a[text()='Next']");

	public static By softgoodsIncluded = By.id("hbSoftgoodsIncluded");
	public static By electronicsIncluded = By.id("hbElectronicsIncluded");
	public static By headingWaveRequirements = By.xpath("//div[@id='contentDiv']/table/tbody/tr[1]/td");
	//Assortment/Solid - E2759 : Wave Requirements - Dates 
	public static By waveWEntry1 = By.id("r1_hbWEntryOneStr");
	public static By waveWEntry1Textbox = By.xpath(".//div[@id='hbWEntryOneStrSourceDiv']/input");
	public static By NotOnShelfBeforeWEntry1 = By.id("r2_hbWEntryOneStr");
	public static By NotOnShelfBeforeWEntry1TB = By.xpath(".//div[@id='hbWEntryOneStrSourceDiv']/input");
	public static By DoNotSellAfterWEntry1 = By.id("r3_hbWEntryOneStr");
	public static By DoNotSellAfterWEntry1TB = By.xpath(".//div[@id='hbWEntryOneStrSourceDiv']/input");
	public static By waveWEntry2 = By.id("r1_hbWEntryTwoStr");
	public static By waveWEntry2TB = By.xpath("//div[@id='hbWEntryTwoStrSourceDiv']/input"); 
	public static By NotOnShelfBeforeWEntry2 = By.id("r2_hbWEntryTwoStr");
	public static By NotOnShelfBeforeWEntry2TB = By.xpath(".//div[@id='hbWEntryTwoStrSourceDiv']/input");
	public static By DoNotSellAfterWEntry2 = By.id("r3_hbWEntryTwoStr");
	public static By DoNotSellAfterWEntry2TB = By.xpath(".//div[@id='hbWEntryTwoStrSourceDiv']/input");

	//ssortment/Solid - E2759 : Wave Requirements - Quantity 
	public static By forecastQtyWEntry1 = By.id("r1_hbWEntryOneDouble");
	public static By forecastQtyWEntry1Textbox = By.xpath(".//div[@id='hbWEntryOneDoubleSourceDiv']/input");

	public static By MaxShipQtyWEntry1 = By.id("r2_hbWEntryOneDouble");
	public static By MaxShipQtyWEntry1TB = By.xpath(".//div[@id='hbWEntryOneDoubleSourceDiv']/input");

	public static By SetQtyWEntry1 = By.id("r3_hbWEntryOneDouble");
	public static By SetQtyWEntry1TB = By.xpath(".//div[@id='hbWEntryOneDoubleSourceDiv']/input");

	public static By DistributionQtyWEntry1 = By.id("r4_hbWEntryOneDouble");
	public static By DistributionQtyWEntry1TB = By.xpath(".//div[@id='hbWEntryOneDoubleSourceDiv']/input");

	public static By forecastQtyWEntry2 = By.id("r1_hbWEntryTwoDouble");
	public static By forecastQtyWEntry2Textbox = By.xpath(".//div[@id='hbWEntryTwoDoubleSourceDiv']/input");

	public static By MaxShipQtyWEntry2 = By.id("r2_hbWEntryTwoDouble");
	public static By MaxShipQtyWEntry2TB = By.xpath(".//div[@id='hbWEntryTwoDoubleSourceDiv']/input");

	public static By SetQtyWEntry2 = By.id("r3_hbWEntryTwoDouble");
	public static By SetQtyWEntry2TB = By.xpath(".//div[@id='hbWEntryTwoDoubleSourceDiv']/input");

	public static By DistributionQtyWEntry2 = By.id("r4_hbWEntryTwoDouble");
	public static By DistributionQtyWEntry2TB = By.xpath("//div[@id='hbWEntryTwoDoubleSourceDiv']/input");

	public static By done= By.xpath("//a[text()='Done']");
	public static By colorwayDD= By.id("contextSKUId");

	public static By labelWaveRequirements3StatusEdit = By.xpath("//td[contains(text(),'Wave Requirements 3 - Status')]//following::a[1]");
	public static By btnSearch = By.xpath("//a[text()='Search']");

	//Assortment/Solid - prod number : Suffix Requirements 
	public static By comments1 = By.id("r3_hbComments");
	public static By comments2 = By.id("r2_hbComments");
	public static By comments3 = By.id("r1_hbComments");
	public static By WEntry1Row1 = By.id("r3_hbWEntryOneStr");
	public static By WEntry1Row2 = By.id("r2_hbWEntryOneStr");
	public static By WEntry1Row3 = By.id("r1_hbWEntryOneStr");
	public static By WEntry2Row1 = By.id("r3_hbWEntryTwoStr");
	public static By WEntry2Row2 = By.id("r2_hbWEntryTwoStr");
	public static By WEntry2Row3 = By.id("r1_hbWEntryTwoStr");

	public static By comments1TB = By.xpath("//div[@id='hbCommentsSourceDiv']/input");  ////div[@id='ptc_verRef_1Display']/input
	public static By comments2TB = By.xpath("//div[@id='hbCommentsSourceDiv']/input");
	public static By comments3TB = By.xpath("//div[@id='hbCommentsSourceDiv']/input");
	public static By WEntry1Row1TB = By.xpath("//div[@id='hbWEntryOneStrSourceDiv']/input");
	public static By WEntry1Row2TB = By.xpath("//div[@id='hbWEntryOneStrSourceDiv']/input");
	public static By WEntry1Row3TB = By.xpath("//div[@id='hbWEntryOneStrSourceDiv']/input");
	public static By WEntry2Row1TB = By.xpath("//div[@id='hbWEntryTwoStrSourceDiv']/input");
	public static By WEntry2Row2TB = By.xpath("//div[@id='hbWEntryTwoStrSourceDiv']/input");
	public static By WEntry2Row3TB = By.xpath("//div[@id='hbWEntryTwoStrSourceDiv']/input");

	//Assortment/Solid - ProdNum : Wave Distribution 
	public static By retailItemRow1 = By.id("r4_hbRetailItemName");
	public static By retailItemRow2 = By.id("r3_hbRetailItemName");
	public static By retailItemRow3 = By.id("r1_hbRetailItemName");
	public static By WEntry1Row1Dis = By.id("r4_hbWEntryOneInt");
	public static By WEntry1Row2Dis = By.id("r3_hbWEntryOneInt");
	public static By WEntry1Row3Dis = By.id("r2_hbWEntryOneInt");
	public static By WEntry2Row1Dis = By.id("r4_hbWEntryTwoInt");
	public static By WEntry2Row2Dis = By.id("r3_hbWEntryTwoInt");
	public static By WEntry2Row3Dis = By.id("r1_hbWEntryTwoInt");

	public static By retailItemRow1Dis = By.xpath("//div[@id='ptc_verRef_1Display']/input");
	public static By retailItemRow2Dis = By.xpath("//div[@id='ptc_verRef_1Display']/input");
	public static By retailItemRow3Dis = By.xpath("//div[@id='ptc_verRef_1Display']/input");
	public static By WEntry1Row1TBDis = By.xpath("//div[@id='hbWEntryOneIntSourceDiv']/input");
	public static By WEntry1Row2TBDis = By.xpath("//div[@id='hbWEntryOneIntSourceDiv']/input");
	public static By WEntry1Row3TBDis = By.xpath("//div[@id='hbWEntryOneIntSourceDiv']/input");
	public static By WEntry2Row1TBDis = By.xpath("//div[@id='hbWEntryTwoIntSourceDiv']/input");
	public static By WEntry2Row2TBDis = By.xpath("//div[@id='hbWEntryTwoIntSourceDiv']/input");
	public static By WEntry2Row3TBDis = By.xpath("//div[@id='hbWEntryTwoIntSourceDiv']/input");

	public static By retailItemNumRow1Dis = By.xpath("//div[@id='ptc_verRef_1Display']/a");
	public static By retailItemNumTB = By.xpath("//div[@id='ptc_verRef_1Display']/input");

	public static By headingDocument = By.xpath("//a[text()='Documents']");

	public static By ADAction = By.xpath("//select[@class='FORMELEMENT' and contains(@onchange,'evalList(this)')]");

	public static By formalDoc = By.xpath("//a[text()='Formal Document']");
	public static By headingCreNewDoc = By.xpath("//td[contains(text(),'Create New Document')]");
	//Create document
	public static By docName = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By visibleToVendor  = By.xpath("//td[contains(text(),'Visible To Vendor')]//following::select[1]");
	public static By iPSensitive  = By.xpath("//td[contains(text(),'IP Sensitive')]//following::select[1]");
	public static By function  = By.xpath("//td[contains(text(),'Function')]//following::select[1]");
	public static By type  = By.xpath("//td[contains(text(),'*Type')]//following::select[1]");
	public static By browseBtn  = By.xpath("//td[contains(text(),'Primary Content')]//following::input[1]");

	public static By createBtn  = By.xpath("//a[text()='Create']");

	public static By docNameTable  = By.xpath("//a[contains(text(),'Document Name')]//following::a[4]");
	public static By createNewFilter  = By.xpath("//a[@id='FilterButton']/img");
	public static By PHNumber  = By.xpath("//*[@id='placeholderName']");   ////div[@id='placeholderIdentification']//td[contains(text(),'Name')]//following::td[1]
	public static By assSolidName  = By.xpath("//div[@id='hbAssortmentSolidNameSourceDiv']/input");
	public static By PlaceholderChk  = By.id("forceUpdatePHbox"); 

	public static By txtSRPUSDPLC  = By.xpath("//td[contains(text(),'SRP (USD) (PLC)')]//following::input[1]");
	public static By txtLCPriceUSDPLC = By.xpath("//td[contains(text(),'L/C Price (USD) (PLC)')]//following::input[1]");
	public static By txtUSDomesticPLC   = By.xpath("//td[contains(text(),'US Domestic (%) (PLC)')]//following::input[1]");
	public static By txtDOMPriceUSDPLC  = By.xpath("//td[contains(text(),'DOM Price (USD) (PLC)')]//following::input[1]");

	public static By lblAssortmentSolidName   = By.xpath("//td[contains(text(),'Assortment / Solid Name')]//following::td[1]");
	public static By lblAssortmentSolidNo  = By.xpath("//td[contains(text(),'Assortment / Solid # (PLC)')]//following::td[1]");
	public static By ddAstSolid = By.xpath("//td[contains(text(),'Ast. / Solid (PLC)')]//following::select[1]");
	public static By ddClassPLC= By.xpath("//td[contains(text(),'Class (PLC)')]//following::select[1]");
	public static By ddDivisionPLC= By.xpath("//td[contains(text(),'Division (PLC)')]//following::select[1]");
	public static By ddBrandPLC= By.xpath("//td[contains(text(),'*Brand (PLC)')]//following::select[1]");
	public static By ddSegmentPLC= By.xpath("//td[contains(text(),'Segment (PLC)')]//following::select[1]");
	public static By ddLicensorPLC= By.xpath("//td[contains(text(),'Licensor (PLC)')]//following::select[1]");
	public static By ddCoBrandPLC  = By.xpath("//td[contains(text(),'Co-Brand (PLC)')]//following::select[1]");
	public static By ddISOPLC = By.xpath("//td[contains(text(),'ISO (PLC)')]//following::select[1]");
	public static By ddDistributionChannelPLC = By.xpath("//td[contains(text(),'Distribution Channel (PLC)')]//following::select[1]");
	public static By txtRetailerDistribution= By.xpath("//td[contains(text(),'Retailer Distribution (PLC)')]//following::input[1]");
	public static By ddProjectTypePLC= By.xpath("//td[contains(text(),'Project Type (PLC)')]//following::select[1]");
	public static By ddInnovationTypePLC= By.xpath("//td[contains(text(),'Innovation Type (PLC)')]//following::select[1]");
	public static By ddTVAdPLC= By.xpath("//td[contains(text(),'TV Ad (PLC)')]//following::select[1]");
	public static By ddMoviePLC= By.xpath("//td[contains(text(),'Movie (PLC)')]//following::select[1]");
	public static By ddDigitalProductPLC= By.xpath("//td[contains(text(),'Digital Product (PLC)')]//following::select[1]");
	public static By taCommentsPLC= By.xpath("//td[contains(text(),'Comments (PLC)')]//following::textarea[1]");
	public static By ddIntroTimingPLC= By.xpath("//td[contains(text(),'Intro Timing (PLC)')]//following::select[1]");
	public static By txtOnShelfDate = By.xpath("//td[contains(text(),'On-Shelf Date (PLC)')]//following::input[1]");
	public static By ddPT_MHPLC= By.xpath("//td[contains(text(),'PT / MH (PLC)')]//following::select[1]");
	public static By lblPlanIdentifier= By.xpath("//td[contains(text(),'Plan Identifier (PLC)')]//following::td[1]");

	public static By internalClassification= By.xpath("//td[contains(text(),'Internal Classification (PLC)')]//following::select[1]");


	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String glPlanName;
	static String strTotalRetailItemCount;
	static String strClass;
	static String strDivision;
	static String strBrand;
	static int cnt = 0;
	static String strPHSucessGen;
	static String strDevPlan;
	static String searFilter;
	static String phName;
	static String prodNum;
	static String placeholderNumber;
	static String prodName;
	public static String prodNumRetail1;
	static String prodNumRetail2;
	static String prodNumRetail3;
	static String  SoftgoodsInc;
	static String electronicsInc; 
	static Set set ;
	static Iterator iter ;
	static String parent ;
	static String child;
	static Select select;
	static String  assSoName;
	static String assortmentSolidName;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSCFunctional(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Sanity or Regression suit");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("Testcase is :" + data[2]);
			log.info("Testcase no is :" + data[3]);
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

			if(data[3].equalsIgnoreCase("SmokeTest1"))
				createDevelopmentPlan(data); 
			if(data[3].equalsIgnoreCase("SmokeTest2"))
				generateAndVerifyAsstSolidPlaceholders(data); 
			if(data[3].equalsIgnoreCase("SmokeTest3"))
				updateForecastAndAttributesAgainstPlaceholders(data); 
			if(data[3].equalsIgnoreCase("SmokeTest4"))
				updatePlaceholderStatus(data); 
			if(data[3].equalsIgnoreCase("SmokeTest5"))
				reviewPlaceholderAndApproveTask(data); 
			if(data[3].equalsIgnoreCase("SmokeTest6"))
				createProductlinkedtoPlaceholder(data); 
			if(data[3].equalsIgnoreCase("SmokeTest7A"))
				createNewRetailItemLinktoSolid1(data); 
			if(data[3].equalsIgnoreCase("SmokeTest7B"))
				createNewRetailItemLinktoSolid2(data); 
			if(data[3].equalsIgnoreCase("SmokeTest7C"))
				createNewRetailItemLinktoSolid3(data); 
			if(data[3].equalsIgnoreCase("SmokeTest8"))
				createColorwaysForaProduct(data); 
			if(data[3].equalsIgnoreCase("SmokeTest9"))
				wavePlanagAinstanAssortment(data); 
			if(data[3].equalsIgnoreCase("SmokeTest10"))
				createAndUploadaProjectBrief(data); 


		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}


	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 1
	 */
	public static String createDevelopmentPlan(String[] data) throws Exception{
		try{
			log.info(" Create Development Plan verification started....");
			//Click My Season Link
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[6]);
			//Click on Planning
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.planningLink, "link", "Planning");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on create new plan
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.createNewPlan, "btn", "Create New Plan");
			//Click Global Line Plan :
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.globalLinePlan, "btn", "Global Line Plan");
			//wait for heading 
			CommonFunctions.waitForVisibilityOfElement(GlobalLinePlan.headingCreateNewPlan);
			//Click Create New Plan
			glPlanName = createNewPlan(data);
			//glPlanName="AutoDevPlan"+glPlanName;
			assortmentSolidName= fillGlobalLinePlan(data);
			CommonFunctions.waitForElementTobeClickable(expImg2);
			//Expend
			CommonFunctions.clickButtonOrLink(expImg2, "Expand class");
			CommonFunctions.waitForElementTobeClickable(expImg3);
			CommonFunctions.clickButtonOrLink(expImg3, "Expand division");
			CommonFunctions.waitForElementTobeClickable(expImg4);
			CommonFunctions.clickButtonOrLink(expImg4, "Expand Brand");
			CommonFunctions.waitForElementTobeClickable(expImg6);
			CommonFunctions.clickButtonOrLink(expImg6, "Expand MainLine");
			//CommonFunctions.waitForElementTobeClickable(expImg6);
			//CommonFunctions.clickButtonOrLink(expImg4, "Expand MainLine");
			//Class
			strClass=driver.findElement(objClass).getText();
			Assert.assertEquals(strClass.trim(), data[7]);
			//Division
			strDivision=driver.findElement(objDivision).getText();
			Assert.assertEquals(strDivision.trim(), data[8]);//
			//Brand
			strBrand=driver.findElement(objBrand).getText();
			Assert.assertEquals(strBrand.trim(), data[5]);
			//Verification of totalRetailItemCount
			strTotalRetailItemCount=driver.findElement(totalRetailItemCount).getText();
			String val =data[35].replaceAll("\\.0*$", "");
			Assert.assertEquals(strTotalRetailItemCount, val);
			log.info("Plan name is: "+ glPlanName);
			log.info("Create Development Plan verification completed");
		}catch(Exception e){
			log.error("Exception in createDevelopmentPlan()", e);
			//	return false;
		}
		return glPlanName;
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 2
	 */
	//Step 2
	public static String generateAndVerifyAsstSolidPlaceholders(String[] data) throws Exception{
		try{
			log.info("Generate & Verify Asst./Solid Placeholders verified");
			CommonFunctions.selectCheckbox(PlaceholderChk); 
			//Click on 'Generate Place holder'
			CommonFunctions.clickButtonOrLink(generatePlaceholders,"btn", "Generate PlaceHolder");
			//Verification
			strPHSucessGen=driver.findElement(plSucessGen).getText();
			Assert.assertEquals(strPHSucessGen, "Placeholders successfully generated.");
			//Step 7:To validate if theplaceholder wascreated, go to the left pane menu: My Seasons menu on the left, select "Line Sheet" under the "Development" section
			//Click My Season Link
			//CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(Product.mySeasonYear, data[7]);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Verification
			//strDevPlan=driver.findElement(devPlan).getText();
			//Assert.assertEquals(strDevPlan, "Development Plan");
			String view= CommonFunctions.getDefaultSelectedValueFromDropdown(devPlan);
			Assert.assertEquals(view.trim(), "Development Plan");
			//Click on filter button
			CommonFunctions.clickButtonOrLink(filterButton, "btn", "Filter Button");
			//Click 'Create new filter' icon
			phName = createSearchFilter(data);
			log.info("Search name is:"+phName);
			String filterName= filterOptions(data);
			//Click on Options button
			CommonFunctions.clickButtonOrLink(optionBtn, "btn", "Option");
			//Select from placeholder drop down
			CommonFunctions.selectFromDropDownByVisibleText(placeHolderDD, "Placeholders Only");
			//Click on run
			CommonFunctions.clickButtonOrLink(runBtn, "btn", "Run");
			//need to verify class\division
			log.info(" Generate & Verify Asst./Solid Placeholders verification completed");

		}catch(Exception e){
			log.error("Exception in generateAndVerifyAsstSolidPlaceholders()", e);
			//	return false;
		}
		return phName;
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 3
	 */
	//Step 3
	public static String updateForecastAndAttributesAgainstPlaceholders(String[] data) throws Exception{
		try{
			//String phName="";
			log.info("Update Forecast and attributes against Placeholders verification.....");
			//Click on filter Action
			CommonFunctions.clickButtonOrLink(By.xpath("//table[@class='TABLE_OUTLINE']/tbody//td[contains(text(),'"+assSoName+"')]/preceding-sibling::td[8]"), "btn", "filterAction");

			CommonFunctions.waitForElementTobeClickable(updatePlaceholder);
			//Click update placeholder
			CommonFunctions.clickButtonOrLink(updatePlaceholder, "btn", "updatePlaceholder");
			//verify heading
			CommonFunctions.waitForVisibilityOfElement(updatePlaceholderHeading);
			placeholderNumber=driver.findElement(PHNumber).getText();
			log.info("Placeholder Number is: "+placeholderNumber);
			//Update Internal Classification
			CommonFunctions.selectFromDropDownByVisibleText(internalClassification, data[69]);
			//Verify  General Attributes
//			String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
//			Assert.assertEquals(GALabel, " General Attributes:");
//			//Verify Assortment / Solid Name (PLC) 
//			String slblAssortmentSolidName = driver.findElement(lblAssortmentSolidName).getText();
//			Assert.assertEquals(slblAssortmentSolidName, assortmentSolidName);
//			// Assortment / Solid # (PLC) 
//			String slblAssortmentSolidNo = driver.findElement(lblAssortmentSolidNo).getText();
//			Assert.assertEquals(slblAssortmentSolidNo, assortmentSolidName);
//			//Verify Class
//			String sddClassPLC= new Select(driver.findElement(ddClassPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddClassPLC, data[7]);
//			//Verify Division
//			String sddDivisionPLC= new Select(driver.findElement(ddDivisionPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddDivisionPLC, data[8]);
//			//Verify Brand
//			String sddBrandPLC= new Select(driver.findElement(ddBrandPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddBrandPLC, data[5]);
//			//Verify Segment
//			String sddSegmentPLC= new Select(driver.findElement(ddSegmentPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddSegmentPLC, data[9]);
//			//Co-Brand (PLC) 
//			String sddCoBrandPLC= new Select(driver.findElement(ddCoBrandPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddCoBrandPLC, data[13]);
//			//Licensor (PLC) 
//			String sddLicensorPLC= new Select(driver.findElement(ddLicensorPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddLicensorPLC, data[12]);
//			//*ISO (PLC) 
//			String sddISOPLC= new Select(driver.findElement(ddISOPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddISOPLC, data[14]);
//			// *Project Type (PLC)
//			String sddProjectTypePLC= new Select(driver.findElement(ddProjectTypePLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddProjectTypePLC, data[20]);
//			//  TV Ad (PLC) 
//			String sddTVAdPLC= new Select(driver.findElement(ddTVAdPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddTVAdPLC, data[18]);
//			//*Movie (PLC) 
//			String sddMoviePLC= new Select(driver.findElement(ddMoviePLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddMoviePLC, data[15]);
//			// Digital Product (PLC) 
//			String sddDigitalProductPLC= new Select(driver.findElement(ddDigitalProductPLC)).getFirstSelectedOption().getText();
//			Assert.assertEquals(sddDigitalProductPLC, data[19]);
//			// Comments (PLC) 
//			String staCommentsPLC = driver.findElement(taCommentsPLC).getText();
//			Assert.assertEquals(staCommentsPLC, data[34]);
//			// Retailer Distribution (PLC) 
//			String stxtRetailerDistribution = driver.findElement(txtRetailerDistribution).getAttribute("value");
//			Assert.assertEquals(stxtRetailerDistribution, data[24]);
//			//  *Innovation Type (PLC) 
//			String sddInnovationTypePLC= new Select(driver.findElement(ddInnovationTypePLC)).getFirstSelectedOption().getText();
//			//	String sddInnovationTypePLC = driver.findElement(ddInnovationTypePLC).getText();
//			Assert.assertEquals(sddInnovationTypePLC, data[21]);
//			//  *Intro Timing (PLC) 
//			String sddIntroTimingPLC= new Select(driver.findElement(ddIntroTimingPLC)).getFirstSelectedOption().getText();
//			//	String sddIntroTimingPLC = driver.findElement(ddIntroTimingPLC).getText();
//			Assert.assertEquals(sddIntroTimingPLC, data[16]);
//			//  *On-Shelf Date (PLC) 
//			String stxtOnShelfDate = driver.findElement(txtOnShelfDate).getAttribute("value");
//			Assert.assertEquals(stxtOnShelfDate, data[17]);
//			//  PT / MH (PLC) 
//			String sddPT_MHPLC= new Select(driver.findElement(ddPT_MHPLC)).getFirstSelectedOption().getText();
//			//		String sddPT_MHPLC = driver.findElement(ddPT_MHPLC).getText();
//			Assert.assertEquals(sddPT_MHPLC, data[22]);
//			//  Plan Identifier (PLC) 
//			//	String slblPlanIdentifier= new Select(driver.findElement(lblPlanIdentifier)).getFirstSelectedOption().getText();
//			String slblPlanIdentifier = driver.findElement(lblPlanIdentifier).getText();
//			Assert.assertEquals(slblPlanIdentifier, glPlanName);
//
//
//			//Verify Global Forecasting
//			String GFLabel=driver.findElement(globalForcasting).getText();
//			Assert.assertEquals(GFLabel, " Global Forecasting:");
//			// *SRP (USD) (PLC) 
//			String sRPUSDPLC = driver.findElement(txtSRPUSDPLC).getAttribute("value");
//			Assert.assertEquals(sRPUSDPLC, data[30]);
//			//  *L/C Price (USD) (PLC) 
//			String lCPriceUSDPLC = driver.findElement(txtLCPriceUSDPLC).getAttribute("value");
//			Assert.assertEquals(lCPriceUSDPLC, data[31]);
//			//   *US Domestic (%) (PLC) 
//			String uSDomesticPLC = driver.findElement(txtUSDomesticPLC).getAttribute("value");
//			Assert.assertEquals(uSDomesticPLC, data[33]);
//			//    *DOM Price (USD) (PLC) 
//			String dOMPriceUSDPLC = driver.findElement(txtDOMPriceUSDPLC).getAttribute("value");
//			Assert.assertEquals(dOMPriceUSDPLC, data[32]);
//
//
//			//Verify Retail Item Counts
//			String RICLabel=driver.findElement(retailItemCounts).getText();
//			Assert.assertEquals(RICLabel.trim(), "Retail Item Counts:");
//			//Retail Item Count Total (PLC) 
//			String RICTLabel=driver.findElement(retailItemCountTotalLabel).getText();
//			Assert.assertEquals(RICTLabel, data[35]);
			//Click on save
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "btn", "Save");
			log.info("Update Forecast and attributes against Placeholders verification completed.....");
		}catch(Exception e){
			log.error("Exception in updateForecastAndAttributesAgainstPlaceholders()", e);
			return "";
		}
		return placeholderNumber;
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 4
	 */
	public static boolean updatePlaceholderStatus(String[] data) throws Exception{
		try{
			log.info("Update Placeholder Status verification.....");
			//			driver.navigate().refresh();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//click on placeholder
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.placeHolderLink, "Link", "PlaceHolder Link");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//	CommonFunctions.clickButtonOrLink(AssSolid, "link", "Product Type");
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.inputPlaceHolderNumber, placeholderNumber);//
			//Click on Search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
			CommonFunctions.clickButtonOrLink(Color.colorAction, "btn", "Action dropdown");
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.optionSetState, "option", "Set State");
			String valULCSAfterChange=PlaceHolderDevPlan.PHPlan_selectUpdateLifecycleState(data);
			//	Thread.sleep(1000);
			//Click on Update
			CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
			String strRO_UpdateLifecycleState = driver.findElement(GlobalLinePlan.RO_UpdateLifecycleState).getText();
			//Verification
			Assert.assertEquals(strRO_UpdateLifecycleState, valULCSAfterChange);
			log.info("Update Placeholder Status verification completed.....");
		}catch(Exception e){
			log.error("Exception in updatePlaceholderStatus()", e);
			return false;
		}
		return true;
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 5
	 */
	public static boolean reviewPlaceholderAndApproveTask(String[] data) throws Exception{
		try{
			log.info("Review Placeholder and Approve Task verification Started.....");
			//	driver.navigate().refresh();
			//Click on mywork
			driver.switchTo().frame("sidebarframe");
			CommonFunctions.clickButtonOrLink(myWork, "plus icon", "My work");
			//Click on placeholder icon
			CommonFunctions.waitForElementTobeClickable(phPlusIcon);
			CommonFunctions.clickButtonOrLink(phPlusIcon, "plus icon", "Placeholder");
			//Click on  Review / Create Ast/Solid Task
			CommonFunctions.waitForElementTobeClickable(reviewCreateAstSolidTask);
			CommonFunctions.clickButtonOrLink(reviewCreateAstSolidTask, "link", "Review / Create Ast/Solid Task");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.waitForElementTobeClickable(selectCheckBox);
			//Select check box
			CommonFunctions.selectCheckbox(By.xpath("//div[@id='allSpace']//tr[@class='GROUPHEADER']/td[1]/input"));  //selectCheckBox
			//Click on + sign
			CommonFunctions.clickButtonOrLink(plusSign, "Img", "Plus sign");
			//Enter comment
			CommonFunctions.enterTextInTextbox(comment, data[34]);
			//Click on Complete
			CommonFunctions.clickButtonOrLink(completeBtn, "btn", "Completed");
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//click on placeholder
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.placeHolderLink, "Link", "PlaceHolder Link");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//	CommonFunctions.clickButtonOrLink(AssSolid, "link", "Product Type");
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.inputPlaceHolderNumber, placeholderNumber); // placeholderNumber : Need to input placeholder name //
			//Click on Search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
			String strRO_UpdateLifecycleState = driver.findElement(GlobalLinePlan.RO_UpdateLifecycleState).getText();
			Assert.assertEquals(strRO_UpdateLifecycleState, "Released");
			log.info("Review Placeholder and Approve Task verification completed.....");
		}catch(Exception e){
			log.error("Exception in reviewPlaceholderAndApproveTask()", e);
			return false;
		}
		return true;
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 6
	 */
	public static String createProductlinkedtoPlaceholder(String[] data) throws Exception{
		try{
			log.info("Create product linked to Placeholder verification Started.....");
			driver.navigate().refresh();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			//	 Click on Libraries
			CommonFunctions.clickButtonOrLink(Product.libraryLink, "Link", "Library Link");
			//	click on placeholder
			CommonFunctions.clickButtonOrLink(PlaceHolderDevPlan.placeHolderLink, "Link", "PlaceHolder Link");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.enterTextInTextbox(PlaceHolderDevPlan.inputPlaceHolderNumber, placeholderNumber); // placeholderNumber :Need to input placeholder name//
			//	Click on Search
			CommonFunctions.clickButtonOrLink(Color.btnSearch, "Btn", "Search");
			//	Click on Action
			CommonFunctions.selectFromDropDownByVisibleText(Color.colorAction, "Create New Product For Placeholder");
			CommonFunctions.clickButtonOrLink(AssSolid, "link", "Product Type");
			//Just for some time commented
			prodName= CreateProd("Assortment/Solid",data[6], "Development Plan", "Create New: Product", data[7], data[8], data[5],data[9], "BOYS", "Assortment", "Not IP Sensitive (Open)", "No", "No", "Mainline", "4", "6", "8", "10",data[70],data[71],data[72]);
			log.info("Product Name is: "+prodName);
			Product.clickDetailsTab(data);
			//Verification
			if (driver.findElement(By.linkText(prodName)).isEnabled())
			{
				log.info("Product name is available");
			}
			else
			{
				log.error("Product name is not available");
				Assert.fail();
			}
			if (driver.findElement(By.linkText(placeholderNumber)).isEnabled()) 
			{
				log.info("placeholderNumber is available");
			}
			else
			{
				log.error("placeholderNumber is not available");
				Assert.fail();
			}
			//Hierarchy verification
			String hirarchyLabel=driver.findElement(Product.labelHierarchy).getText();
			Assert.assertEquals(hirarchyLabel, " Hierarchy:");
			//General Attributes: Verification
			String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
			Assert.assertEquals(GALabel, " General Attributes:");
			log.info("Product Number: "+ prodName);
			log.info("PlaceHolder Number: "+ placeholderNumber);
			log.info("Create product linked to Placeholder verification completed.....");
		}catch(Exception e){
			log.error("Exception in createProductlinkedtoPlaceholder()", e);
			return "";
		}
		return prodName;
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 7
	 */

	public static String createNewRetailItemLinktoSolid1(String[] data) throws Exception{
		try{
			//log.info("Create new Retail Item & Link to Solid verification Started.....");
			CommonProjectFunctions.searchProduct(prodName);//prodName
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on relationShip
			CommonFunctions.clickButtonOrLink(relationShipTab, "tab", "RelationShip");
			//Click "Copy/ Link Product"
			CommonFunctions.clickButtonOrLink(copyLinkProd, "btn", "Copy/ Link Product");
			prodNumRetail1 = copyLinkProduct(data);
			log.info("First retail product:" +prodNumRetail1);
			//Select Distribution channel
			CommonFunctions.selectFromDropDownByVisibleText(Product.distributionChannel,"Mainline");
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View product");
			//	viewProductBtn
			//Click on details
			Product.clickDetailsTab(data);
			//Verification for Softgoods Included 
			SoftgoodsInc=SeleniumDriver.driver.findElement(softgoodsIncluded).getText();
			Assert.assertEquals(SoftgoodsInc, data[39]);
			//Verification for  Softgoods Included 
			electronicsInc=SeleniumDriver.driver.findElement(electronicsIncluded).getText();
			Assert.assertEquals(electronicsInc, data[38]);
			log.info("Create new Retail Item & Link to Solid verification completed for retail item 1.....");
		}catch(Exception e){
			log.error("Exception in createNewRetailItemLinktoSolid()", e);
			//	return false;
		}
		return prodNumRetail1;
	}
	public static String createNewRetailItemLinktoSolid2(String[] data) throws Exception{
		try{		
			//Second retail item
			CommonProjectFunctions.searchProduct(prodName);//prodName
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on relationShip
			CommonFunctions.clickButtonOrLink(relationShipTab, "tab", "RelationShip");
			//Click "Copy/ Link Product"
			CommonFunctions.clickButtonOrLink(copyLinkProd, "btn", "Copy/ Link Product");
			prodNumRetail2 = copyLinkProduct(data);
			log.info("Second retail product:" +prodNumRetail2);
			//Select Distribution channel
			CommonFunctions.selectFromDropDownByVisibleText(Product.distributionChannel,"Mainline");
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View product");
			//Click on details
			Product.clickDetailsTab(data);
			//Verification for Softgoods Included 
			SoftgoodsInc=SeleniumDriver.driver.findElement(softgoodsIncluded).getText();
			Assert.assertEquals(SoftgoodsInc, data[39]);
			//Verification for  Softgoods Included 
			electronicsInc=SeleniumDriver.driver.findElement(electronicsIncluded).getText();
			Assert.assertEquals(electronicsInc, data[38]);
			log.info("Create new Retail Item & Link to Solid verification completed for retail item 2.....");
		}catch(Exception e){
			log.error("Exception in createNewRetailItemLinktoSolid()", e);
			//	return false;
		}
		return prodNumRetail2;
	}

	public static String createNewRetailItemLinktoSolid3(String[] data) throws Exception{
		try{		
			//Third retail item
			CommonProjectFunctions.searchProduct(prodName);//prodName
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on relationShip
			CommonFunctions.clickButtonOrLink(relationShipTab, "tab", "RelationShip");
			//Click "Copy/ Link Product"
			CommonFunctions.clickButtonOrLink(copyLinkProd, "btn", "Copy/ Link Product");
			prodNumRetail3 = copyLinkProduct(data);
			log.info("Third retail product:" +prodNumRetail3);
			//Select Distribution channel
			CommonFunctions.selectFromDropDownByVisibleText(Product.distributionChannel,"Mainline");
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View product");
			//Click on details
			Product.clickDetailsTab(data);
			//Verification for Softgoods Included 
			SoftgoodsInc=SeleniumDriver.driver.findElement(softgoodsIncluded).getText();
			Assert.assertEquals(SoftgoodsInc, data[39]);
			//Verification for  Softgoods Included 
			electronicsInc=SeleniumDriver.driver.findElement(electronicsIncluded).getText();
			Assert.assertEquals(electronicsInc, data[38]);

			log.info("Create new Retail Item & Link to Solid verification completed for retail item 3.....");
		}catch(Exception e){
			log.error("Exception in createNewRetailItemLinktoSolid()", e);
			//	return false;
		}
		return prodNumRetail3;
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 8
	 */
	public static boolean createColorwaysForaProduct(String[] data) throws Exception{
		try{
			log.info("Create Colorways for a product Started.....");
			//Search Product
			CommonProjectFunctions.searchProduct(prodName); 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on Details tab
			driver.findElement(Colorway.Details).click();
			// Select season from DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.seasonDD, data[6]);
			// Select Action DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.actionDD, "Create Multiple Colorways");
			//Switch window
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			//	driver.switchTo().defaultContent();
			//Click on Suffix
			//	CommonFunctions.clickButtonOrLink(Colorway.suffix, "Link", "Suffix Clicked");
			//	driver.findElement(Colorway.suffixSearch).sendKeys(data[5]);
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

			//For retial product 
			//Retial product 1
			addColorWayToRetailProduct(data,prodNumRetail1);
			//Retial product 2
			addColorWayToRetailProduct(data,prodNumRetail2);
			//Retial product 3
			addColorWayToRetailProduct(data,prodNumRetail3);

			log.info("Create Colorways for a product finished.....");

		}catch(Exception e){
			log.error("Exception in createColorwaysForaProduct()", e);
			return false;
		}
		return true;
	}

	public static boolean addColorWayToRetailProduct(String[] data,String prodNumRetail) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(prodNumRetail); 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on Details tab
			driver.findElement(Colorway.Details).click();
			// Select season from DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.seasonDD, data[6]);
			// Select Action DD
			CommonFunctions.selectFromDropDownByVisibleText(Colorway.actionDD, "Create Multiple Colorways");
			//Switch window
			set = driver.getWindowHandles();
			iter = set.iterator();
			parent = (java.lang.String) iter.next();
			child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			//	driver.switchTo().defaultContent();
			//Click on Suffix
			//	CommonFunctions.clickButtonOrLink(Colorway.suffix, "Link", "Suffix Clicked");
			//	driver.findElement(Colorway.suffixSearch).sendKeys(data[5]);
			//Click on search
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='Search']"), "btn", "Search");
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
			select = new Select(driver.findElement(colorwayDropDown));
			List<WebElement> options1 = select.getOptions();
			if(options1.size()>=3)
				log.info("Colorway Added in dropdown");
			else
				Assert.fail();
		}catch(Exception e){
			log.error("Exception in addColorWayToRetailProduct()", e);
			return false;
		}
		return true;
	}

	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 9
	 */
	public static boolean wavePlanagAinstanAssortment(String[] data) throws Exception{
		try{
			log.info("Wave Plan against an Assortment verification Started.....");
			CommonProjectFunctions.searchProduct(prodName); 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on Details tab
			if(CommonFunctions.waitForVisibilityOfElement(SourcingConfig.detailsTablink))
				CommonFunctions.clickButtonOrLink(SourcingConfig.detailsTablink, "link", "Details tab");
			CommonFunctions.selectFromDropDownByVisibleText(SourcingConfig.detailPageSeasonDD, data[6]);
			//Step 6 :  Enter value in table
			fillWaveRequirements1Dates(data); 
			//Step 7 and Stpe 8 :  Enter value in table
			fillWaveRequirements2Quantity(data); 
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayDD, 1);
			//Wave Requirements 3 - Status
			//	fillWaveRequirements3Status(data); //Discarded
			//Wave Requirements 4 - Suffix 
			fillwaveRequirements4Suffix(data); 
			//Wave Requirements 5 - Distribution
			fillwaveRequirements5Distribution(data); 

			log.info("Wave Plan against an Assortment verification completed.....");
		}catch(Exception e){
			log.error("Exception in wavePlanagAinstanAssortment()", e);
			return false;
		}
		return true;
	}
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 * Tab 10
	 */
	public static boolean createAndUploadaProjectBrief(String[] data) throws Exception{
		try{
			log.info("Create and upload a Project Brief verification Started.....");
			CommonProjectFunctions.searchProduct(prodName); 
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on Details tab
			//	CommonProjectFunctions.clickDetailsTab(data[6]);
			if(CommonFunctions.waitForVisibilityOfElement(SourcingConfig.detailsTablink))
				CommonFunctions.clickButtonOrLink(SourcingConfig.detailsTablink, "link", "Details tab");
			CommonFunctions.selectFromDropDownByVisibleText(SourcingConfig.detailPageSeasonDD, data[6]);
			//Click on Documents
			CommonFunctions.clickButtonOrLink(headingDocument,"link", "Document");
			CommonFunctions.selectFromDropDownByVisibleText(ADAction,"Create New Reference Document");
			//Click on 'Formal Document'
			CommonFunctions.clickButtonOrLink(formalDoc,"link", "Formal Document");
			CommonFunctions.waitForVisibilityOfElement(headingCreNewDoc);
			createNewDocument(data);
			log.info("Create and upload a Project Brief verification completed.....");
		}catch(Exception e){
			log.error("Exception in createAndUploadaProjectBrief()", e);
			return false;
		}
		return true;
	}

	public static String createNewPlan(String[] data) {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			glPlanName = CommonFunctions.getRandomString(5);
			glPlanName ="AutoDevP"+glPlanName;
			//	glPlanName = data[6]+date.getTime();
			//	glPlanName=glPlanName.substring(0,5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.planName,glPlanName);
			//Enter Brand
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.Brand, data[5]);
			//Enter Season Year
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.seasonYear, data[6]);
			//Click on create button
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return glPlanName;
	}
	public static String fillGlobalLinePlan(String[] data) {
		try{
			//Add Assortment/Solid name
			assSoName= data[4]+CommonFunctions.getRandomString(3);
			CommonFunctions.enterTextInTextbox(assSolidName, assSoName);
			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink1, "Image", "Plus Sign");
			//Select Class
			//CommonFunctions.selectFromDropDownByValue(hbclass, data[5]);
			new Select(SeleniumDriver.driver.findElement(GlobalLinePlan.hbclass)).selectByVisibleText(data[7]);
			SeleniumDriver.log.info("Selected" + " " + data[7] + " " + "option from drop down.");
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink2, "Image", "Plus Sign");
			//Select DIVISION
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDivision, data[8]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink3, "Image", "Plus Sign");
			//Select Brand
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbBrand, data[5]);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			//	CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink5, "Image", "Plus Sign");
			//Enter Assortment Number
			//	CommonFunctions.enterTextInTextbox(GlobalLinePlan.hbAssortmentSolidNumber, data[10]);
			//Click on Add
			//	CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			//Click Plas Sign Image
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.menulink6, "Image", "Plus Sign");
			//Enter Assortment Number
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.hbAssortmentSolidNumber, assSoName);
			//Click on Add
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.lnkAdd, "link", "Add");

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbAssortmentSolidName, "cell", "Assortment / Solid Name");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPutAssortmentSolidName, assSoName);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDistributionChannel, "cell", "Distribution Channel");
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.hbDistributionChannelForUpdate, "Mainline");



			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbLicensor, "cell", "Licensor");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbLicensor, data[12]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbCoBrand, "cell", "Co-Brand");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbCoBrand, data[13]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.r6_hbISO, "cell", "ISO");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbISO, data[14]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbMovie, "cell", "Movie");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbMovie, data[15]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbIntroTiming, "cell", "Intro Timing");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbIntroTiming, data[16]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbOnShelfDate, "cell", "On-Shelf Date");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbOnShelfDate, data[17]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbTVAd, "cell", "TV Ad");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbTVAd, data[18]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDigitalProduct, "cell", "Digital Product");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbDigitalProduct, data[19]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbProjectType, "cell", "Project Type");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbProjectType, data[20]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbInnovationType, "cell", "Innovation Type");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbInnovationType, data[21]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbPTMH, "cell", "PT / MH");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.selecthbPTMH, data[22]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbAstSolid, "cell", "Ast. / Solid");
			CommonFunctions.selectFromDropDownByVisibleText(GlobalLinePlan.selecthbAstSolid, data[23]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbRetailerDistribution, "cell", "Retailer Distribution");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbRetailerDistribution, data[24]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbCFItemCount, "cell", "CF Total");
			System.out.println(data[25]);
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbCFItemCount, data[25]); //

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbNewItemCount, "cell", "New Total");
			System.out.println(data[26]);
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbNewItemCount, data[26]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbProductRefreshItemCount, "cell", "Product Refresh Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbProductRefreshItemCount, data[27]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbPackageRefreshItemCount, "cell", "Package Refresh Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbPackageRefreshItemCount, data[28]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbSoldasSolid, "cell", "Sold as Solid Total");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbSoldasSolid, data[29]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbSRPPriceUSD, "cell", "SRP (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbSRPPriceUSD, data[30]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbLCPriceUSD, "cell", " L/C Price (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbLCPriceUSD, data[31]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDOMPriceUSD, "cell", "DOM Price (USD)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbDOMPriceUSD, data[32]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbDomesticPercentage, "cell", "Domestic (%)");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.inPuthbDomesticPercentage, data[33]);

			CommonFunctions.clickButtonOrLink(GlobalLinePlan.hbComments, "cell", "Comments");
			CommonFunctions.enterTextInTextbox(GlobalLinePlan.textareahbComments, data[34]);

			//Click on Save
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnSave, "btn", "Save");
			CommonFunctions.waitForElementTobeClickable(GlobalLinePlan.btnDone);
			Thread.sleep(2000);
			//Click on Done
			CommonFunctions.clickButtonOrLink(GlobalLinePlan.btnDone,"btn", "Done");


		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return assSoName;
	}

	public static String createSearchFilter(String[] data) {
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Click on 'Create New Filter'
			CommonFunctions.clickButtonOrLink(createNewFilter, "btn", "Create New Filter");
			searFilter = "Auto" +CommonFunctions.getRandomString(5);
			//	glPlanName = data[6]+date.getTime();
			//	glPlanName=glPlanName.substring(0,5);
			//Enter Name
			CommonFunctions.enterTextInTextbox(creSearchFilter,searFilter);
			//Click on create button
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			//Verify heading
			CommonFunctions.waitForVisibilityOfElement(headingUpdateSearchPref);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return searFilter;
	}

	public static String filterOptions(String[] data) {
		try{
			//Select 'Product\Brand (PLC)'
			CommonFunctions.selectFromDropDownByVisibleText(ddFilterOption, "Product"+"\\"+"Brand (PLC)");
			//Select 'Furby' from list
			CommonFunctions.selectFromDropDownByVisibleText(lbFilterOption,data[5]);
			//Click on Add link
			CommonFunctions.clickButtonOrLink(addLink, "link", "Add");
			//Click on Add button
			CommonFunctions.clickButtonOrLink(addBtn, "btn", "Add");
			//Click on update button
			//	CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update");
			//Select 'Placeholder\placeholder Creator'
			CommonFunctions.selectFromDropDownByVisibleText(ddFilterOption, "Placeholder"+"\\"+"Placeholder Creator");
			//Select 'Created by '
			CommonFunctions.selectFromDropDownByVisibleText(createdByDD, data[0]);
			//Click on Add button
			CommonFunctions.clickButtonOrLink(addBtn, "btn", "Add");
			//Click on update button
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update");
			//Click on return button
			CommonFunctions.clickButtonOrLink(returnBtn, "btn", "Return");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return glPlanName;
	}

	public static String copyLinkProduct(String[] data) {
		try{
			//Select 'Season'
			CommonFunctions.selectFromDropDownByVisibleText(seasonDD, data[6]);
			//Select ' Product Type'
			CommonFunctions.selectFromDropDownByVisibleText(productType,data[36]);
			//Select 'Relationship Type'
			CommonFunctions.selectFromDropDownByVisibleText(relationShipType, data[37]);



			//			CommonFunctions.selectFromDropDownByVisibleText(Product.familyBrand, data[70]);
			//			CommonFunctions.selectFromDropDownByVisibleText(Product.lowerAge,data[71]);
			//			CommonFunctions.selectFromDropDownByVisibleText(Product.UpperAge,data[72]);
			//Click on Next button
			CommonFunctions.clickButtonOrLink(nextBtn, "btn", "Next");
			//Softgoods Included
			CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,data[39]);
			//Electronics Included
			CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,data[38]);
			//Family Brand
		//	CommonFunctions.enterTextInTextbox(Product.familyBrand,data[70] );
			//Lower age
		//	CommonFunctions.enterTextInTextbox(Product.lowerAge,data[71]);
			//Upper age
		//	CommonFunctions.selectFromDropDownByIndex(Product.UpperAge,10);
			//Click on Next button
			CommonFunctions.clickButtonOrLink(nextBtn, "btn", "Next");
			prodNum=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
		//	log.info("retail product:" +prodNum);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewPlan()", e);
		}
		return prodNum;
	}
	//Wave Requirements 1 - Dates
	public static String fillWaveRequirements1Dates(String[] data) {
		try{
			//Click edit of Wave Requirements 1 - Dates: 
			CommonFunctions.clickButtonOrLink(Product.labelWaveRequirements1Edit, "link", "Wave Requirements 1 - Dates: ");
			CommonFunctions.waitForVisibilityOfElement(headingWaveRequirements);

			CommonFunctions.clickButtonOrLink(waveWEntry1, "table cell", "W-Entry 1 for Wave");
			CommonFunctions.enterTextInTextbox(waveWEntry1Textbox, data[40]);

			CommonFunctions.clickButtonOrLink(NotOnShelfBeforeWEntry1, "table cell", "W-Entry 1 for Not On-Shelf Before");
			CommonFunctions.enterTextInTextbox(NotOnShelfBeforeWEntry1TB, data[41]);

			CommonFunctions.clickButtonOrLink(DoNotSellAfterWEntry1, "table cell", "W-Entry 1 for Do Not Sell After ");
			CommonFunctions.enterTextInTextbox(DoNotSellAfterWEntry1TB, data[42]);

			CommonFunctions.clickButtonOrLink(waveWEntry2, "table cell", "W-Entry 2 for Wave");
			CommonFunctions.enterTextInTextbox(waveWEntry2TB, data[43]);

			CommonFunctions.clickButtonOrLink(NotOnShelfBeforeWEntry2, "table cell", "W-Entry 2 for Not On-Shelf Before");
			CommonFunctions.enterTextInTextbox(NotOnShelfBeforeWEntry2TB, data[44]);

			CommonFunctions.clickButtonOrLink(DoNotSellAfterWEntry2, "table cell", "W-Entry 2 for Do Not Sell After ");
			CommonFunctions.enterTextInTextbox(DoNotSellAfterWEntry2TB, data[45]);

			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			//Click on Done Button
			CommonFunctions.clickButtonOrLink(done, "Btn", "Done");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in fillWaveRequirements1Dates()", e);
		}
		return prodNum;
	}
	//Wave Requirements 2 - Quantity
	public static String fillWaveRequirements2Quantity(String[] data) {
		try{
			//Click edit of Wave Requirements 2 - Quantity
			CommonFunctions.clickButtonOrLink(Product.labelWaveRequirements2Edit, "link", "Wave Requirements 2 - Quantity");
			CommonFunctions.waitForVisibilityOfElement(headingWaveRequirements);

			CommonFunctions.clickButtonOrLink(forecastQtyWEntry1, "table cell", "W-Entry 1 for Forecast Qty");
			CommonFunctions.enterTextInTextbox(forecastQtyWEntry1Textbox, data[46]);

			CommonFunctions.clickButtonOrLink(MaxShipQtyWEntry1, "table cell", "W-Entry 1 for Max Ship Qty ");
			CommonFunctions.enterTextInTextbox(MaxShipQtyWEntry1TB, data[47]);

			CommonFunctions.clickButtonOrLink(SetQtyWEntry1, "table cell", "W-Entry 1 for Set Qty  ");
			CommonFunctions.enterTextInTextbox(SetQtyWEntry1TB, data[48]);

			CommonFunctions.clickButtonOrLink(DistributionQtyWEntry1, "table cell", "W-Entry 1 for Distribution Qty ");
			CommonFunctions.enterTextInTextbox(DistributionQtyWEntry1TB, data[49]);

			CommonFunctions.clickButtonOrLink(forecastQtyWEntry2, "table cell", "W-Entry 2 for Forecast Qty");
			CommonFunctions.enterTextInTextbox(forecastQtyWEntry2Textbox, data[50]);

			CommonFunctions.clickButtonOrLink(MaxShipQtyWEntry2, "table cell", "W-Entry 2 for Max Ship Qty ");
			CommonFunctions.enterTextInTextbox(MaxShipQtyWEntry2TB, data[51]);

			CommonFunctions.clickButtonOrLink(SetQtyWEntry2, "table cell", "W-Entry 2 for Set Qty  ");
			CommonFunctions.enterTextInTextbox(SetQtyWEntry2TB, data[52]);

			CommonFunctions.clickButtonOrLink(DistributionQtyWEntry2, "table cell", "W-Entry 2 for Distribution Qty   ");
			CommonFunctions.enterTextInTextbox(DistributionQtyWEntry2TB, data[53]);

			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			//Click on Done Button
			CommonFunctions.clickButtonOrLink(done, "Btn", "Done");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in fillWaveRequirements2Quantity()", e);
		}
		return prodNum;
	}

	// Wave Requirements 4 - Suffix: 
	public static String fillwaveRequirements4Suffix(String[] data) {
		try{
			CommonFunctions.clickButtonOrLink(Product.labelWaveRequirements4Edit, "link", "Wave Requirements 4 - Suffix");
			CommonFunctions.waitForVisibilityOfElement(headingWaveRequirements);

//			CommonFunctions.clickButtonOrLink(comments1, "table cell", "comments1");
//			CommonFunctions.enterTextInTextbox(comments1TB, data[54]);
//
//			CommonFunctions.clickButtonOrLink(comments2, "table cell", "comments2");
//			CommonFunctions.enterTextInTextbox(comments2TB, data[55]);

			CommonFunctions.clickButtonOrLink(comments3, "table cell", "comments3");
			CommonFunctions.enterTextInTextbox(comments3TB, data[56]);

//			CommonFunctions.clickButtonOrLink(WEntry1Row1, "table cell", "WEntry1Row1");
//			CommonFunctions.enterTextInTextbox(WEntry1Row1TB, data[57]);
//
//			CommonFunctions.clickButtonOrLink(WEntry1Row2, "table cell", "WEntry1Row2");
//			CommonFunctions.enterTextInTextbox(WEntry1Row2TB, data[58]);

			CommonFunctions.clickButtonOrLink(WEntry1Row3, "table cell", "WEntry1Row3");
			CommonFunctions.enterTextInTextbox(WEntry1Row3TB, data[59]);

//			CommonFunctions.clickButtonOrLink(WEntry2Row1, "table cell", "WEntry2Row1");
//			CommonFunctions.enterTextInTextbox(WEntry2Row1TB, data[60]);
//
//			CommonFunctions.clickButtonOrLink(WEntry2Row2, "table cell", "WEntry2Row2");
//			CommonFunctions.enterTextInTextbox(WEntry2Row2TB, data[61]);

			CommonFunctions.clickButtonOrLink(WEntry2Row3, "table cell", "WEntry2Row3");
			CommonFunctions.enterTextInTextbox(WEntry2Row3TB, data[62]);

			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			//Click on Done Button
			CommonFunctions.clickButtonOrLink(done, "Btn", "Done");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in fillwaveRequirements4Suffix()", e);
		}
		return prodNum;
	}

	// Wave Requirements 5 - Distribution: 
	public static String fillwaveRequirements5Distribution(String[] data) {
		try{
			CommonFunctions.clickButtonOrLink(Product.labelWaveRequirements5Edit, "link", "Wave Requirements 5 - Distribution");
			CommonFunctions.waitForVisibilityOfElement(headingWaveRequirements);

//			CommonFunctions.clickButtonOrLink(retailItemRow1, "table cell", "Retail Item # row 1");
//			CommonFunctions.clickButtonOrLink(retailItemNumRow1Dis, "table cell line", "Retail Item # row 1");
//			CommonFunctions.enterTextInTextbox(retailItemNumTB, prodNumRetail1);//
//			//retailItemNumRow1Dis
//			CommonFunctions.clickButtonOrLink(retailItemRow2, "table cell", "Retail Item # row 2");
//			CommonFunctions.clickButtonOrLink(retailItemNumRow1Dis, "table cell line", "Retail Item # row 2");
//			CommonFunctions.enterTextInTextbox(retailItemNumTB, prodNumRetail2);//

			CommonFunctions.clickButtonOrLink(retailItemRow3, "table cell", "Retail Item # row 3");
			CommonFunctions.clickButtonOrLink(retailItemNumRow1Dis, "table cell line", "Retail Item # row 3");
			CommonFunctions.enterTextInTextbox(retailItemNumTB, prodNumRetail3);//

//			CommonFunctions.clickButtonOrLink(WEntry1Row1Dis, "table cell", "W-Entry 1 row 1");
//			CommonFunctions.enterTextInTextbox(WEntry1Row1TBDis, data[63]);
//
//			CommonFunctions.clickButtonOrLink(WEntry1Row2Dis, "table cell", "W-Entry 1 row 2");
//			CommonFunctions.enterTextInTextbox(WEntry1Row2TBDis, data[64]);

			CommonFunctions.clickButtonOrLink(WEntry1Row3Dis, "table cell", "W-Entry 1 row 3");
			CommonFunctions.enterTextInTextbox(WEntry1Row3TBDis, data[65]);

//			CommonFunctions.clickButtonOrLink(WEntry2Row1Dis, "table cell", "W-Entry 1 row");
//			CommonFunctions.enterTextInTextbox(WEntry2Row1TBDis, data[66]);
//
//			CommonFunctions.clickButtonOrLink(WEntry2Row2Dis, "table cell", "W-Entry 2 row");
//			CommonFunctions.enterTextInTextbox(WEntry2Row2TBDis, data[67]);

			CommonFunctions.clickButtonOrLink(WEntry2Row3Dis, "table cell", "W-Entry 3 row");
			CommonFunctions.enterTextInTextbox(WEntry2Row3TBDis, data[68]);

			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			//Click on Done Button
			CommonFunctions.clickButtonOrLink(done, "Btn", "Done");
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in fillwaveRequirements5Distribution()", e);
		}
		return prodNum;
	}

	// Create New Document
	public static String createNewDocument(String[] data) {
		try{
			//Enter name
			CommonFunctions.enterTextInTextbox(docName, data[4]);
			//Visible to vendor
			CommonFunctions.selectFromDropDownByVisibleText(visibleToVendor, data[14]);
			//IP Sensitive
			CommonFunctions.selectFromDropDownByVisibleText(iPSensitive, data[15]);
			//  *Function 
			CommonFunctions.selectFromDropDownByVisibleText(function, data[16]);
			//Type
			CommonFunctions.waitForElementTobeClickable(type);
			CommonFunctions.clickButtonOrLink(type, "drop down", "Type");
			CommonFunctions.selectFromDropDownByVisibleText(type, data[18]);
			//CommonFunctions.enterTextInTextbox(type, data[18]);
			//Document Upload
			driver.findElement(browseBtn).sendKeys("C:\\Hasbro\\myfile.txt");
			//Click on create
			CommonFunctions.clickButtonOrLink(createBtn, "btn", "Create");
			String docName = driver.findElement(By.xpath("//a[contains(text(),'Document Name')]//following::a[5]")).getText();
			Assert.assertEquals(docName, data[4],"Doc name verified");


		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in createNewDocument()", e);
		}
		return prodNum;
	}
	public static String CreateProd(String prodType,String year,String strlineSheetView,String strlineSheetAction,
			String strClass,String strDivision,String strBrand,String strSegment,String strInternalClassification,String AstSolid,String strIPSensitive,
			String strElectronicsIncluded,String strSoftgoodsIncluded,String strDistributionChannel,String strSRPPriceUSD,String strUSDomestic
			,String strLCPriceUSD,String strDOMPriceUSD,String strFamilyBrand,String strLowerAge,String strUpperAge) throws Exception{
		try{
			prodName=CommonFunctions.getRandomString(4);
			prodName="E"+prodName;
			SeleniumDriver.driver.findElement(Product.ProductName).clear();
			CommonFunctions.enterTextInTextbox(Product.ProductName,prodName);
			if(!prodType.equalsIgnoreCase("Trademark Display")){
				//Select Class
				CommonFunctions.enterTextInTextbox(Product.Class, strClass);
				CommonFunctions.enterTextInTextbox(Product.Division, strDivision);
				CommonFunctions.enterTextInTextbox(Product.Brand, strBrand);
				CommonFunctions.enterTextInTextbox(Product.segment,strSegment);
				//Family Brand
				CommonFunctions.enterTextInTextbox(Product.familyBrand,strFamilyBrand );
				//Lower age
				CommonFunctions.enterTextInTextbox(Product.lowerAge,strLowerAge);
				//Upper age
				CommonFunctions.selectFromDropDownByIndex(Product.UpperAge,10);

				//	if(!prodType.equalsIgnoreCase("Trade Marketing Pallet")){
				CommonFunctions.enterTextInTextbox(Product.InternalClassification,strInternalClassification);
				//	CommonFunctions.enterTextInTextbox(AstSolid, productData[14]);
				CommonFunctions.selectFromDropDownByVisibleText(Product.AstSolid, AstSolid);
				//	CommonFunctions.enterTextInTextbox(IPSensitive,productData[16]);
				CommonFunctions.selectFromDropDownByVisibleText(Product.IPSensitive,strIPSensitive);
				//	}

				//				if(prodType.equalsIgnoreCase("Retail")|| prodType.equalsIgnoreCase("Bundle Pack")){
				//					//Electronics Included
				//					CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,strElectronicsIncluded);
				//					//Softgoods Included
				//					CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,strSoftgoodsIncluded);
				//				}
			}
			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
			prodNum=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
			log.info("Product Number is : "+prodNum);
			if(!prodType.equalsIgnoreCase("Trademark Display")){
				//wait = new WebDriverWait(driver, 10);
				//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(viewProductBtn));
				CommonFunctions.enterTextInTextbox(Product.distributionChannel,strDistributionChannel);
				/*	if(prodType.equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,strSRPPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.USDomestic,strUSDomestic);
					CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,strLCPriceUSD);
					CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,strDOMPriceUSD);
				}*/
				if(prodType.equalsIgnoreCase("Retail")|| prodType.equalsIgnoreCase("Bundle Pack")){
					CommonFunctions.enterTextInTextbox(Product.targetCostUSD, strDOMPriceUSD);
				}
			}
			//Click View Product Button
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			updateProduct(year,strFamilyBrand,strLowerAge,strUpperAge);

		}catch(Exception e){ 
			log.error("Exception in CreateProductFromLineSheet()", e);
			//	return prodName;
		}
		return prodNum;
	}

	public static boolean updateProduct(String year,String strFamilyBrand,String strLowerAge,String strUpperAge) throws Exception{
		try{
			//Product.clickDetailsTab(year);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(Product.detailsTablink, "link", "Details tab");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailPageSeasonDD));
			CommonFunctions.waitForVisibilityOfElement(Product.detailPageSeasonDD);
			//Select Season
			CommonFunctions.clickButtonOrLink(Product.detailPageSeasonDD, "Season dropdown");
			CommonFunctions.clickButtonOrLink(Product.season2018, year);
			CommonFunctions.enterTextInTextbox(Product.ddDetailsAction, "Update Product");
			CommonFunctions.enterTextInTextbox(Product.familyBrand,strFamilyBrand );
			//Lower age
			CommonFunctions.enterTextInTextbox(Product.lowerAge,strLowerAge);
			//Upper age
			CommonFunctions.selectFromDropDownByIndex(Product.UpperAge,10);
			//Click on save
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
		}catch(Exception e){  
			fail=true;
			log.error("Exception in updateProduct()", e);
			return false;
		}
		return true;
	}

	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteSmokeTestXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSmokeTestXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteSmokeTestXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSmokeTestXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteSmokeTestXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteSmokeTestXls,"Testcases", Utility.getRowNum(suiteSmokeTestXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteSmokeTestXls,"Testcases", Utility.getRowNum(suiteSmokeTestXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteSmokeTestXls, this.getClass().getSimpleName());
	}

}
