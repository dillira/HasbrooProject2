package com.hasbro.sanity;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
































































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






























































import com.hasbro.ci2018.CI296;
//import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Color;
import com.hasbro.security.Colorway;
import com.hasbro.security.CostsheetExternalRetail;
import com.hasbro.security.CostsheetInternal;
import com.hasbro.security.CostsheetTooling;
import com.hasbro.security.GlobalLinePlan;
import com.hasbro.security.InternalBOMSoftG;
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
 * @author Govind Pandey
 * 
 * **************PC76 - Cancel Vendor Retail Item Cost Sheet*******************************
 * 
 * Create a Retail Item product which is added in season 2019.
 * Create 2 colorway, Funskool source and specification for the above product.
 * Enter the above created product, season in the automation excel sheet
 * 
 * **************PC77 - Cancel Vendor Product Cost Sheet***********************************
 * 
 * Create an Assortment/Solid Product which is added in season 2019.
 * Create 5 colorway, Funskool source and specification for the above product.
 * Enter the value of above created product, season in the automation excel sheet

 * 
 * ***************M03 - Create a Carry Forward Product*************************************
 * 
 * Create an Assortment/Solid Product which is added in season 2019. Ensure that Manually Input Wave Forecasts should be marked as Yes.
 * Do a copy/link and create 3 Retail Item Product which is associated to above product.
 * Enter the value of above created product, season in the automation excel sheet
 * Ensure that we are entering the existing season of product in 7 column and targeted season in column 13
 * 
 ******************PC51 - Remove BOM from Internal Product Cost Sheet**********************
 *
 * Create an Assortment/Solid Product which is added in season 2019.
 * Create 2 colorway, specification in Hasbro Internal Source for the above product.
 * Make sure there is only one source Hasbro Internal
 * Enter the value of above created product, season, specification in the automation excel sheet
 *
 *
 * ******************PC52 - Remove BOM from Internal Retail Item Cost sheet**********************
 *
 * Create a Retail Product which is added in season 2019.
 * Create 2 colorway, specification in Hasbro Internal Source for the above product.
 * Make sure there is only one source Hasbro Internal
 * Enter the value of above created product, season, specification in the automation excel sheet
 *
 *
 * ******************PC53 - Remove BOM from Vendor Product Cost Sheet**********************
 *
 * Create an Assortment/Solid Product which is added in season 2019. Create external source Funskool.
 * Create 2 colorway, specification in Funskool Source for the above product.
 * Enter the value of above created product, season, specification in the automation excel sheet.
 * Enter column 4 of excel value as Vendor as we are going to perform these scenario with vendor user. 
 *
 *
 * * ******************PD17 - Visibility of HDM Table to Member Group**********************
 *	
 * Enter the season in excel sheet column 5 where you want to create your product
 * Enter the Member and Vendor group name against method pd17ViewHDMTable in excel sheet column 4	
 *
 *
 * * * ******************PD17 - Visibility of HDM Table to Member Group**********************
 *
 * Enter Product Type in column 2, season in column 5Enter Wave, Not on Shelf Before date,
 *
 *
 *
 *
 *
 */



public class SanitySuite3 extends TestsuiteBase{
	static int count=-1;
	static boolean fail=false;
	String loginVal;
	Boolean flaglogin=false;
	int y=0;
	static String Prodname;
	static String csName = null;
	static String csApproved;
	static String strCSInWork;
	static String csInWork1;
	static String csInWork;


	
	static String csSG4Approved;
	static String csVendorSelectionQuote;
	static String sStatus;
	static String strSpec;
	static String strSource;
	static String strCW;
	static String strcsVendorSelectionQuote;
	static String strCSSG4Approved;
	static String strCSFEPApproved;
	static String strCSSeasonalReviewApproved;
	static String csFEPApproved;
	static String csSeasonalReviewApproved;
	static String strCSInitialQuoteApproved;
	static String csProductVendorSelectionQuote;
	static String strCSProductSG4Approved;
	static String csProductSG4Approved;
	static String strCSProductFEPApproved;
	static String csProductFEPApproved;
	static String strCSProductSeasonalReviewApproved;
	static String csProductSeasonalReviewApproved;
	public static String bomName;
	public static String bomnNameInWork1;
	public static Actions action;
	static String strBOMInWork;
	static String csProdInternal;
	static String csProdInternalInWork;
	static String bomNameRetail;
	static String bomNameInWorkRetail1;
	static String strBOMInWorkRetail;
	static String strCSInWorkRetail;
	static String csRetailInternalInWork;
	public static String bomname;
	static String strbomInWork;
	public static Boolean flagVal=true;
	static String prodNum;
	static String prodNumber;
	static By waveWaveReq1;
	static String IncreasewaveForecastWaveReq2;
	static String IncreasewaveForecastWaveReq2A;
	static By waveForecastWaveReq2;
	static By enterValuesInWaveForecast;
	static String IncreasewaveForecastWaveReq;
	static String IncreasewaveWaveReq1;
	static String IncreasecolWaveReq5;
	static String IncreasecolWaveReq5A;
	static String IncreasewaveWaveReq1A;
	static String IncreasecolWaveReq4;
	static String IncreasecolWaveReq4A;
	static String IncreaseNotOnShelfBeforeWaveReq1;
	static String IncreaseNotOnShelfBeforeWaveReq1A;
	static String IncreaseSetQtyWaveReq2;
	static String IncreaseSetQtyWaveReq2A;
	static String IncreaseWaveOnShelfDateWaveReq1;
	static String IncreaseWaveOnShelfDateWaveReq1A;
	static String IncreaseDistributionQtyWaveReq2;
	static String IncreaseDistributionQtyWaveReq2A;
	static String wavedpWaveReq1;
	static By wavedpWaveReq1Value;
	static String waveForecastValueWaveReq2;
	static By waveForecastValueWaveReq2Value;
	static String IncreaseDoNotShellBeforeWaveReq1;
	static By enterValuesInWave;
	static By colWaveReq4;
	static By enterValuesInCol;
	static By colWaveReq5;
	static By enterValuesInColl;
	static By notOnShelfBeforeWaveReq1;
	static String IncreaseMaxShipQtyWaveReq2;
	static By maxShipQtyWaveReq2;
	static String IncreaseMaxShipQtyWaveReq2A;
	static By enterValuesInMaxShipQty;
	static By enterValuesInNotOnShelfBefore;
	static By setQtyWaveReq2;
	static By enterValuesInSetQty;
	static By wavOnShelfDateWaveReq1;
	static By enterValuesInWaveOnShelfDate;
	static By distributionQtyWaveReq2;
	static By enterValuesInDistributionQty;
	static By enterValueInwavedpWaveReq1;
	static By doNotShellBeforeWaveReq1;
	static String IncreasewaveWaveReq;
	static String noteonShelfBeforedpWaveReq1;
	static By noteonShelfBeforedpWaveReq1Value;
	static String maxShipQtydpWaveReq2;
	static By maxShipdpWaveReq2Value;
	static String doNotSellAfterdpWaveReq1;
	static By doNotSellAfterdpWaveReq1Value;
	static String setQtydpWaveReq2;
	static By setQtydpWaveReq2Value;
	static String waveOnShelfDatedpWaveReq1;
	static By waveOnShelfDatedpWaveReq1Value;
	static String distributionQtydpWaveReq1;
	static By distributionQtydpWaveReq1Value;
	static String suffixdpWaveReq4;
	static By suffixdpWaveReq4Value;
	static String coldpWaveReq5;
	static By coldpWaveReq5Value;
	private static final int standardNoOfColumns=33;
	
	
	
	public static By inputquantity= By.xpath("//div[@id='quantitySourceDiv']/input");
	public static By quantity= By.id("r1_quantity");
	public static By inputCompOrLoca= By.xpath("//div[@id='partNameSourceDiv']/input");
	public static By compOrLoca= By.id("r1_partName");
	public static By lstcostingActions = By.id("costingActions");	
	public static By tabCostsheet =By.xpath("//a[text()='Cost Sheet List']");
	public static By selectSource= By.id("sourcingConfigId");
	public static By sourcingLead   = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead   = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");
	public static By lstcolorwayGroupOptions = By.id("colorwayGroupOptions");
	public static By lnkAdd =  By.xpath("//a[text()='Add']");
	public static By lstQuoteCurrency = By.xpath("//td[contains(text(),'Quote Currency')]//following::select[1]");	 //*[@id='ptc_str_2']
	public static By tblCostSheetIdentification = By.xpath("//td[contains(text(),'Cost Sheet Identification')]");
	public static By btnSave =  By.xpath("//a[text()='Save']"); 
	public static By csHeading=By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]/td");
	public static By lstCostSheetAction =  By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]//select[@onchange='evalList(this)']");
	public static By lstCSStatus = By.xpath("//td[contains(text(),'Cost Sheet Status')]//following::select[1]");	 //*[@id='ptc_str_2']
	public static By lstWave = By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By productDetailstab = By.xpath("//a[contains(text(),'Specifications')]//preceding::a[contains(text(),'Details')]");
	public static By colorwayName = By.xpath(".//*[@id='contextSKUId']");
	public static By forecastQuantity = By.xpath("//td[contains(text(),'Forecast Qty')]");
    public static By labelWaveRequirements2Edit = By.xpath("//td[contains(text(),'Wave Requirements 2 - Quantity')]//following::a[1]");
    public static By wave2hidebutton = By.xpath("//td[contains(text(),'% Wave Forecast')]//preceding::a[@id='hideColButton']");
    public static By waveForecastcol1= By.xpath("//td[contains(text(),'% Wave Forecast')]//following::td[1]");
    public static By setQtycol1= By.xpath("//td[contains(text(),'Set Qty')]//following::td[1]");    
    public static By forecastQtycol1= By.xpath("//td[contains(text(),'Forecast Qty')]//following::td[1]");
    public static By forecastQtycol1input= By.xpath("//td[contains(text(),'Forecast Qty')]//following::input[1]");
    public static By maxShipQtycol1 = By.xpath("//td[contains(text(),'Max Ship Qty')]//following::td[1]");
    public static By maxShipQtycol1input = By.xpath("//td[contains(text(),'Max Ship Qty')]//following::input[1]");
    public static By distributionQtycol1 = By.xpath("//td[contains(text(),'Distribution Qty')]//following::td[1]");
    public static By distributionQtycol1input = By.xpath("//td[contains(text(),'Distribution Qty')]//following::input[1]");
    public static By doneWave2table = By.xpath("//td[contains(text(),'Distribution Qty')]//following::a[contains(text(),'Done')]");
    public static By seasonValue = By.xpath(".//*[@id='splId']");
	public static By Site = By.xpath(".//*[@id='siteNavLink']/a");
	public static By mySeasonLink = By.id("seasonsContentIcon");
	public static By mySeasonYear = By.id("seasonSelectList");
	public static By lineSheet = By.linkText("Line Sheet");
	public static By lineSheetView = By.id("viewId");
	public static By Class = By.xpath("//td[contains(text(),'Class')]//following::select[1]");
	public static By Division = By.xpath("//td[contains(text(),'Division')]//following::select[1]");
	public static By Brand = By.xpath("//td[contains(text(),'Brand')]//following::select[1]");
	public static By segment = By.xpath("//td[contains(text(),'Segment')]//following::select[1]");
	public static By InternalClassification = By.xpath("//td[contains(text(),'Internal Classification')]//following::select[1]");
	public static By AstSolid = By.xpath("//td[contains(text(),'*Ast')]//following::select[1]");
	public static By electronicsIncluded= By.xpath("//td[contains(text(),'Electronics Included')]//following::select[1]");
	public static By IPSensitive = By.xpath("//td[contains(text(),'IP Sensitive')]//following::select[1]");
	public static By softgoodsIncluded= By.xpath("//td[contains(text(),'Softgoods Included')]//following::select[1]");
	public static By SaveBtn = By.xpath("//a[text()='Save']");
	public static By distributionChannel  = By.xpath("//td[contains(text(),'Marketing Channel')]//following::select[1]");
	public static By SRPPriceUSD= By.xpath("//td[contains(text(),'SRP Price')]//following::input[1]");
	public static By USDomestic = By.xpath("//td[contains(text(),'US Domestic')]//following::input[1]");
	public static By LCPriceUSD = By.xpath("//td[contains(text(),'L/C Price')]//following::input[1]");
	public static By DOMPriceUSD= By.xpath("//td[contains(text(),'DOM Price ')]//following::input[1]");
	public static By viewProductBtn = By.xpath("//a[text()='View Product.']");
	public static By targetCostUSD= By.xpath("//td[contains(text(),'Target Cost')]//following::input[1]");
	public static By ProductName = By.xpath("//td[contains(text(),'Product Name')]//following::input[1]");
	public static By lineSheetAction = By.name("linePlanActions");
	public static By carryOverseason = By.xpath(".//*[@id='selectedSeason']");
	public static By carryOverSearch = By.xpath("//a[contains(text(),'Run')]//preceding::*[@class='SEARCH_RESULTS_BAR']");
	public static By carryOverSelectbtn = By.xpath("//a[contains(text(),'Sort Options')]//preceding::a[contains(text(),'Select')]");
	public static By carryOverFilter = By.xpath(".//*[@id='filterButton']");
	public static By carryOverProductinput= By.xpath("//td[contains(text(),'Product Number')]//following::input[1]");
	public static By carryOverRunbtn = By.xpath("//a[contains(text(),'Run')]");
	public static By carryOverFiltervalue = By.xpath(".//*[@id='filterId']");
	public static By carryOverView = By.xpath(".//*[@id='viewId']");
	public static By manuallyInputWaveForecast = By.xpath("//td[contains(text(),'Global Pacific Forecast Sales ( Net USD)')]//following::td[contains(text(),'Manually Input Wave Forecasts')]");
	public static By manuallyInputWaveForecastValue= By.xpath(".//*[@id='hbManuallyInputWaveForecasts']");
	public static By addNewBOMTab= By.xpath("//a[contains(text(),'Add New BOM')]");
	public static By initializeBOM= By.xpath("//a[contains(text(),'Initialize BOM')]");
	public static By BOMTypeId= By.id("bomTypeId");
	public static By headingCreateBOM= By.xpath("//td[contains(text(),'Create BOM')]");
	public static By name= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By colorway= By.xpath("//td[contains(text(),'Colorway')]//following::select[1]");
	public static By wave= By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By currency= By.xpath("//td[contains(text(),'Currency')]//following::select[1]");
	public static By btnSaveAndCheckIn= By.xpath("//a[text()='Save and Check In']");
	public static By headerAttributes= By.xpath("//div[@id='genDetails_plus']/a[2]");
	public static By BOMId= By.id("bomId");
	public static By csStatus =By.xpath("//td[contains(text(),'Cost Sheet Status')]//following::td[1]");
	public static By csAddBOM = By.xpath("//td[contains(text(),'BOM')]//following::select[1]");
	public static By csMasterCartonPackagingInput = By.xpath("//td[contains(text(),'Master Carton Packaging Material')]//following::input[1]");
	public static By csMasterCartonLaborCostInput = By.xpath("//td[contains(text(),'Master Carton Labor Cost')]//following::input[1]");
	public static By csMiscellaneousCostInput = By.xpath("//td[contains(text(),'Miscellaneous Cost')]//following::input[1]");
	public static By csProductMarkupInput = By.xpath("//td[contains(text(),'Product Markup')]//following::input[1]");
	public static By csQuoteCurrencydp = By.xpath(".//*[@id='hBCurrency']");
	public static By csMasterCartonPackagingdp = By.xpath(".//*[@id='hbMasterCartonPackagingMaterial']");
	public static By csMasterCartonLaborCostdp = By.xpath(".//*[@id='hbMasterCartonPackagingLaborCost']");
	public static By csMiscellaneousCostdp = By.xpath(".//*[@id='hbMiscellaneousMaterial']");
	public static By csProductMarkupdp = By.xpath(".//*[@id='hbProductMarkup']");
	public static By selectSpecification= By.id("contextSpecId");
	public static By csCostSheetList = By.xpath("//a[@title='Cost Sheet List']");
	public static By csNameInternal= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By csRetailItemCostInput= By.xpath("//td[contains(text(),'Retail Item Cost')]//following::input[1]");
	public static By csRetailPlasticMaterialInput= By.xpath("//td[contains(text(),'Plastic Material')]//following::input[1]");
	public static By csRetailChemicalMaterialInput = By.xpath("//td[contains(text(),'Chemical Material')]//following::input[1]");
	public static By csRetailPurchasedMaterialInput= By.xpath("//td[contains(text(),'Purchased Material')]//following::input[1]");
	public static By csRetailElectronicMaterialInput= By.xpath("//td[contains(text(),'Electronic Material')]//following::input[1]");	
	public static By csRetailSoftGoodsMaterialInput= By.xpath("//td[contains(text(),'Soft Goods Material')]//following::input[1]");
	public static By csRetailPackagingMaterialInput= By.xpath("//td[contains(text(),'Packaging Material')]//following::input[1]");
	public static By csRetailGeneralDecoLaborCostInput= By.xpath("//td[contains(text(),'General / Deco Labor Cost')]//following::input[1]");
	public static By csRetailMoldingLaborCostInput= By.xpath("//td[contains(text(),'Molding Labor Cost')]//following::input[1]");
	public static By csRetailMarkupInput= By.xpath("//td[contains(text(),'Markup')]//following::input[1]");	
	public static By csRetailItemCostdp= By.xpath(".//*[@id='hBExFactorySAP']");
	public static By csRetailPlasticMaterialdp= By.xpath(".//*[@id='hBPlasticMaterial']");
	public static By csRetailChemicalMaterialdp = By.xpath("//*[@id='hbChemicalMaterial']");
	public static By csRetailPurchasedMaterialdp= By.xpath(".//*[@id='hBPurchasedMaterial']");
	public static By csRetailElectronicMaterialdp= By.xpath(".//*[@id='hBElectronicMaterial']");	
	public static By csRetailSoftGoodsMaterialdp= By.xpath(".//*[@id='hBSoftgoodsMaterial']");
	public static By csRetailPackagingMaterialdp= By.xpath(".//*[@id='hBPackagingMaterial']");
	public static By csRetailGeneralDecoLaborCostdp= By.xpath(".//*[@id='hbGeneralDecoLaborTotalCost']");
	public static By csRetailMoldingLaborCostdp= By.xpath(".//*[@id='hbMoldingLaborTotalCost']");
	public static By csRetailMarkupdp= By.xpath(".//*[@id='hbMarkUp']");
	public static By addNewbomTab= By.xpath(".//*[@id='ADD_PAGETab']/a");
	public static By bomtypeid= By.id("bomTypeId");
	public static By initializebom= By.xpath("//a[contains(text(),'Initialize BOM')]");
	public static By headingCreatebom= By.xpath("//td[contains(text(),'Create BOM')]");
	public static By Factory = By.xpath("//a[contains(text(),'Factory')]");
	public static By factoryFindSearch = By.xpath(".//*[@id='SearchButton1']");
	public static By chooseFactory= By.xpath("//a[contains(text(),'(choose)')]");
	public static By bomId= By.xpath("//*[@id='bomId']");
	public static By imgClose =By.xpath("//img[contains(@src,'deleteXsmall.png')]");
	public static By productNumber = By.xpath("//td[contains(text(),'Product Number')]//following::td[1]");
	public static By hdmEdit= By.xpath("//td[contains(text(),'Finance')]//preceding::a[contains(text(),'Edit')]");
	public static By hdmEditPencil= By.xpath(".//*[@id='menuImage1']");
	public static By hdmColorway9thDigit= By.xpath(".//*[@id='r1_hbRINinthDigit']");
	public static By hdmColorway9thDigitInput= By.xpath(".//*[@id='hbRINinthDigitSourceDiv']//following::input[1]");
	public static By hdmWave = By.xpath(".//*[@id='r1_hbWave']");
	public static By hdmWaveInput = By.xpath(".//*[@id='hbWaveSourceDiv']/select[1]");
	public static By hdmColorwayRatio= By.xpath(".//*[@id='r1_hbRIRatio']");
	public static By hdmColorwayRatioInput= By.xpath(".//*[@id='hbRIRatioSourceDiv']/input[1]");
	public static By hdmColorwayStatus= By.xpath(".//*[@id='r1_hbWaveStatus']");
	public static By hdmColorwayStatusInput= By.xpath(".//*[@id='hbWaveStatusSourceDiv']/select[1]");
	public static By hdmDone=By.xpath(".//*[@id='r1_hbWaveStatus']//following::a[contains(text(),'Done')]");
	public static By hdmtable= By.xpath("//td[contains(text(),'Detailed Wave - HDM')]");
	public static By sourceActions = By.xpath(".//*[@id='sourcingActions']");
	public static By sourceFunskool = By.xpath(".//*[@id='sourcingConfigId']/option[contains(text(),'AEQUS')]");
	public static By updateSourceHeading = By.xpath("//td[contains(text(),'Update Sourcing Configuration')]");
	public static By sourcingStatus =By.xpath("//td[contains(text(),'Sourcing Status')]//following::select[1]");
	public static By selectApprovedStatus = By.xpath("//td[contains(text(),'Sourcing Status')]//following::select[1]/option[contains(text(),'Approved')]");
	public static By waveReq1 = By.xpath("//td[contains(text(),'Wave Requirements 1 - Dates: ')]//following::a[1]");
    public static By labelWaveRequirements1Edit = By.xpath("//td[contains(text(),'Wave Requirements 1 - Dates')]//following::a[1]");
    public static By waveRequiremnt1editPencil = By.xpath(".//*[@id='menuImage1']");
    public static By waveReq1Donebtn = By.xpath("//td[contains(text(),'Wave - On Shelf Date')]//following::a[contains(text(),'Done')]");
    public static By waveReq2Donebtn = By.xpath("//td[contains(text(),'Distribution Qty')]//following::a[contains(text(),'Done')]");
    public static By labelWaveRequirements3Edit = By.xpath("//td[contains(text(),'Wave Requirements 3 - Status')]//following::a[1]");
    public static By statusWaveRequirement3 = By.xpath(".//*[@id='r1_hbWaveStatus']");
    public static By statusWaveRequirement3Input = By.xpath(".//*[@id='r1_hbWaveStatus']/div/select");
    public static By waveWaveRequirement3 = By.xpath(".//*[@id='r1_hbWave']");
    public static By waveWaveRequirement3Input = By.xpath(".//*[@id='r1_hbWave']/div/select");
    public static By donebtnWaveRequirement3Input = By.xpath(".//*[@id='r1_hbWaveStatus']//following::a[contains(text(),'Done')]");
    public static By statusdpwaveRequirement3 = By.xpath("//td[contains(text(),'Wave Requirements 3 - Status:')]//following::a[contains(text(),'Status')]//following::tr[1]/td[1]");
    public static By wavedpwaveRequirement3= By.xpath("//td[contains(text(),'Wave Requirements 3 - Status:')]//following::a[contains(text(),'Status')]//following::tr[1]/td[2]");
    public static By lablewaveRequirement4edit = By.xpath("//td[contains(text(),'Wave Requirements 4 - Suffix')]//following::a[1]");
    public static By suffixwaveRequirement4 = By.xpath(".//*[@id='r1_hbSuffix']");
    public static By suffixInputwaveRequirement4 = By.xpath(".//*[@id='hbSuffixSourceDiv']/input[1]");
    public static By commentwaveRequirement4 = By.xpath(".//*[@id='r1_hbComments']");
    public static By commentInputwaveRequirement4 = By.xpath(".//*[@id='hbCommentsSourceDiv']/input[1]");
    public static By donebtnWaverequirement4 = By.xpath(".//*[@id='menuImage1']//following::a[contains(text(),'Done')]");
    public static By suffixwavedpRequirement4 = By.xpath("//td[contains(text(),'Wave Requirements 4 - Suffix:')]//following::a[contains(text(),'Suffix')]//following::tr[1]/td[1]");
    public static By commentwavedpRequirement4 = By.xpath("//td[contains(text(),'Wave Requirements 4 - Suffix:')]//following::a[contains(text(),'Suffix')]//following::tr[1]/td[2]");
    public static By labelwaveRequirement5edit = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution')]//following::a[1]");
    public static By productwaveRequirement5 = By.xpath(".//*[@id='r1_hbRetailItemName']");
    public static By productInputwaveRequirement5 = By.xpath(".//*[@id='ptc_verRef_1Link']");
    public static By findProduct = By.xpath("//span[contains(text(),'Find: Product')]");
    public static By productNumberValue = By.xpath("//td[contains(text(),'Brand')]//preceding::input[1]");
    public static By findProductSearch = By.xpath("//td[contains(text(),'Brand')]//preceding::input[1]//following::a[contains(text(),'Search')]");
    public static By searchResultProduct = By.xpath("//span[contains(text(),'Search Results for Product')]");
    public static By doneBtnwaveRequirement5 = By.xpath(".//*[@id='menuImage1']//following::a[contains(text(),'Done')]");
    public static By productNameWaveRequirement5 = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution:')]//following::a[contains(text(),'Product Type')]//following::tr[1]/td[3]");
    public static By productTypeWaveRequirement5 = By.xpath("//td[contains(text(),'Wave Requirements 5 - Distribution:')]//following::a[contains(text(),'Product Type')]//following::tr[1]/td[4]");
    public static By itemQtyWaveRequirement5 = By.xpath("//a[contains(text(),'Item Qty')]//following::td[38]");
    
	  
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSCFunctional(String[] data) throws Exception{
		count++;
		System.out.println(CommonProjectFunctions.runmodes[count]);
		if(!CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
			CommonProjectFunctions.skip=true;
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
		}
		try{
			log.debug("Inside testcase for Sanity suit");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("Testcase is :" + data[2]);
			log.info("Testcase no is :" + data[3]);
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					CommonProjectFunctions.logOut();
				 
				}
			}
			if(CommonProjectFunctions.runmodes[count].equalsIgnoreCase("y")){
				if(y==0){
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}
			
// * **************PC76 - Cancel Vendor Retail Item Cost Sheet*******************************

			if(data[3].equalsIgnoreCase("pc76createRetailVendorSelectionQuote"))
			{ pc76createRetailVendorSelectionQuote(data,"Vendor Selection Quote"); }

			if(data[3].equalsIgnoreCase("pc76createRetailSG4Approved"))
			{ pc76createRetailSG4Approved(data,"SG4 Approved"); }

			if(data[3].equalsIgnoreCase("pc76createRetailFEPApproved"))
			{ pc76createRetailFEPApproved(data,"FEP Approved"); }

			if(data[3].equalsIgnoreCase("pc76createRetailSeasonalReviewApproved"))
			{ pc76createRetailSeasonalReviewApproved(data,"Seasonal Review Approved"); }
			
			if(data[3].equalsIgnoreCase("pc76cancelRetailCostSheet"))
			{ pc76cancelRetailCostSheet(data); }
			
// * **************PC76 - Cancel Vendor Retail Item Cost Sheet*******************************

//*****************PC77 - Cancel Vendor Product Cost Sheet***********************************			
			if(data[3].equalsIgnoreCase("pc77createProductVendorSelectionQuote"))
			{ pc77createProductVendorSelectionQuote(data,"Vendor Selection Quote"); }
			
			if(data[3].equalsIgnoreCase("pc77createProductSG4Approved"))
			{ pc77createProductSG4Approved(data,"SG4 Approved"); }
			
			if(data[3].equalsIgnoreCase("pc77createProductFEPApproved"))
			{ pc77createProductFEPApproved(data,"FEP Approved"); }
			
			if(data[3].equalsIgnoreCase("pc77createProductSeasonalReviewApproved"))
			{ pc77createProductSeasonalReviewApproved(data,"Seasonal Review Approved"); }
			
			if(data[3].equalsIgnoreCase("pc77cancelProductCostSheet"))
			{ pc77cancelProductCostSheet(data); }

//*****************PC77 - Cancel Vendor Product Cost Sheet***********************************			
			

//*****************M03 - Create a Carry Forward Product*************************************
			if(data[3].equalsIgnoreCase("m03EnterWaveReq2Table"))
			{ m03EnterWaveReq2Table(data); }
            //enterWaveReq2Table
			
			if(data[3].equalsIgnoreCase("m03CarryForwardProduct"))
			{ m03CarryForwardProduct(data); }
			
//*****************M03 - Create a Carry Forward Product*************************************
			
//******************PC51- Remove BOM from Internal Product Cost Sheet************************
			
			//pc51createProductIntBOM
			if(data[3].equalsIgnoreCase("pc51createProductIntBOM"))
			{ pc51createProductIntBOM(data); }
			
			//pc51createProductIntCostSheet
			if(data[3].equalsIgnoreCase("pc51createProductIntCostSheet"))
			{ pc51createProductIntCostSheet(data); }
			
			//pc51updateProductIntCostSheet
			if(data[3].equalsIgnoreCase("pc51updateProductIntCostSheet"))
			{ pc51updateProductIntCostSheet(data); }
		
//******************PC51- Remove BOM from Internal Product Cost Sheet************************

//******************PC52 - Remove BOM from Internal Retail Item Cost sheet************************
			
			//pc52createRetailIntBOM
			if(data[3].equalsIgnoreCase("pc52createRetailIntBOM"))
			{ pc52createRetailIntBOM(data); }
			
			//pc52createRetailIntCostSheet
			if(data[3].equalsIgnoreCase("pc52createRetailIntCostSheet"))
			{ pc52createRetailIntCostSheet(data); }
			
			//pc52updateRetailIntCostSheet
			if(data[3].equalsIgnoreCase("pc52updateRetailIntCostSheet"))
			{ pc52updateRetailIntCostSheet(data); }
			
//******************PC53 - Remove BOM from Vendor Product Cost Sheet************************
			
			//pc53createProductVendorBOM
			if(data[3].equalsIgnoreCase("pc53createProductVendorBOM"))
			{ pc53createProductVendorBOM(data); }
			
			//pc53createProductVendorCostSheet
			if(data[3].equalsIgnoreCase("pc53createProductVendorCostSheet"))
			{ pc53createProductVendorCostSheet(data); }
			
			//pc53updateProductVendorCostSheet
			if(data[3].equalsIgnoreCase("pc53updateProductVendorCostSheet"))
			{ pc53updateProductVendorCostSheet(data); }
			
//******************PC53 - Remove BOM from Vendor Product Cost Sheet************************

//******************PD17 - Visibility of HDM Table to Member Group************************
			
			//pd17CreateProductFromLineSheet
			if(data[3].equalsIgnoreCase("pd17CreateProductFromLineSheet"))
			{ pd17CreateProductFromLineSheet(data); }
						
			//pd17UpdateWavetable
			if(data[3].equalsIgnoreCase("pd17UpdateHDMtable"))
			{ pd17UpdateHDMtable(data); }
			
			//pd17UpdateWavetable
			if(data[3].equalsIgnoreCase("pd17ViewHDMTable"))
			{ pd17ViewHDMTable(data); }
			
			if(data[3].equalsIgnoreCase("pd17ViewHDMTable1"))
			{ pd17ViewHDMTable(data); }
			
//******************PD17 - Visibility of HDM Table to Member Group***************************
			
//******************PD18 - Wave Plan against Solid*******************************************
			
			//pd17UpdateWavetable
			if(data[3].equalsIgnoreCase("pd18createProductWaveReq1Table"))
			{ pd18createProductWaveReq1Table(data); }
			
			
			
			
			
		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}
	}
	
	public static void pd17ViewHDMTable(String []data)throws Exception{
	try{
		
		//Search for Product	
		CommonProjectFunctions.searchProduct(prodNumber);
			
		//Switch to default frame
		driver.switchTo().defaultContent();
			
		//Switch to content frame
		driver.switchTo().frame("contentframe");
		CommonFunctions.waitForPageLoaded();
		//Click on details tab
		wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailstab));
		CommonFunctions.clickButtonOrLink(productDetailstab, "Details tab");
		
		//Select Season
		wait.until(ExpectedConditions.visibilityOfElementLocated(Product.detailPageSeasonDD));
		CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);

		//Wait for visibility of source value
		CommonFunctions.waitForVisibilityOfElement(selectSource);
				
		//Select first Colorway
		CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
		
		
		if(data[4].contains("Vendor")){
		
		if(driver.findElements(hdmtable).size()==0){
			log.info("HDM table is not visible");
			
		}	
		else if(!(driver.findElements(hdmtable).size()==0)){
			log.info("HDM table is visible");
			fail=true;
			
		}
		}
		else if (!data[4].contains("Vendor")){
			
			if(driver.findElements(hdmtable).size()==0){
				log.info("HDM table is not visible");
				fail=true;
			}	
			else if(!(driver.findElements(hdmtable).size()==0)){
				log.info("HDM table is visible");
				
				
			}
				
		}
		
		
	}
	catch(Exception e){
		fail=true;
		log.error("Exception in pd17ViewHDMTable()", e);
		throw e;
	}
}
	
	
	public static void pd17UpdateHDMtable(String [] data) throws Exception{
		try{
		//Search for Product	
		CommonProjectFunctions.searchProduct(prodNumber);
			
		//Switch to default frame
		driver.switchTo().defaultContent();
			
		//Switch to content frame
		driver.switchTo().frame("contentframe");
		CommonFunctions.waitForPageLoaded();
		//Click on Sourcing tab
		CommonProjectFunctions.clickSourcingTab(data[5]);
			
		//Wait for specification tab
		CommonFunctions.waitForVisibilityOfElement(selectSpecification);
		
		//Click on Sourcing
		AddSourcing(data);
		
		//Wait for visibility of SOURCE
		CommonFunctions.waitForVisibilityOfElement(selectSource);
		
		//Select source
		CommonFunctions.clickButtonOrLink(selectSource, "Source value");
		
		//Select source Funskool
		CommonFunctions.clickButtonOrLink(sourceFunskool, "select source");
		
		//Wait for visibility of source action
		CommonFunctions.waitForVisibilityOfElement(sourceActions);
		
		//Click on Source Actions
		CommonFunctions.clickButtonOrLink(sourceActions, "Source Action");
		
		//Select Update source
		CommonFunctions.selectFromDropDownByIndex(sourceActions, 1);
		
		//Wait for Update Source page
		CommonFunctions.waitForVisibilityOfElement(updateSourceHeading);
		
		//Click on Sourcing status
		CommonFunctions.clickButtonOrLink(sourcingStatus, "Sourcing Status");
		
		//Click on Sourcing status as Approved
		CommonFunctions.clickButtonOrLink(selectApprovedStatus, "approved source");
		
		//Click on Save button
		CommonFunctions.clickButtonOrLink(btnSave, "Save button");
		CommonFunctions.waitForPageLoaded();
		//Wait for source actions menu bar to come
		CommonFunctions.waitForVisibilityOfElement(sourceActions);
		
		//Search for Product	
		CommonProjectFunctions.searchProduct(prodNumber);
			
		//Switch to default frame
		driver.switchTo().defaultContent();
			
		//Switch to content frame
		driver.switchTo().frame("contentframe");
		CommonFunctions.waitForPageLoaded();
		//Click on Product Details tab
		wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailstab));
		CommonFunctions.clickButtonOrLink(productDetailstab, "Details tab");
		
		//Wait for specification value
		wait.until(ExpectedConditions.visibilityOfElementLocated(Product.detailPageSeasonDD));
		
		//Select Season
		CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);

		
		//Create Colorway
		InternalBOMSoftG.Create_Colorway(data);
		
		//Select first Colorway
		wait.until(ExpectedConditions.visibilityOfElementLocated(colorwayName));
		CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
		
		//Wait for visiblity of HDM edit
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmEdit));

		
		//Click on HDM table edit option
		CommonFunctions.clickButtonOrLink(hdmEdit, "hdm edit");
		
		//wait for HDM edit pencil
		CommonFunctions.waitForVisibilityOfElement(hdmEditPencil);
		
		//Click on hdm Colorway9thDigit and enter value
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmColorway9thDigit));
		CommonFunctions.clickButtonOrLink(hdmColorway9thDigit, "hdm Colorway 9thDigit");
		CommonFunctions.clearTextBox(hdmColorway9thDigitInput, "Clear Colorway 9thDigit");
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmColorway9thDigitInput));
		CommonFunctions.enterTextInTextbox(hdmColorway9thDigitInput, data[18]);


		//Click on hdm ColorwayRatio and Enter  Value
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmColorwayRatio));
		CommonFunctions.clickButtonOrLink(hdmColorwayRatio, "hdm Colorway Ratio");
		CommonFunctions.clearTextBox(hdmColorwayRatioInput, "Colorway Ratio Input");
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmColorwayRatioInput));
		CommonFunctions.enterTextInTextbox(hdmColorwayRatioInput,data[19]);
		
		//Click on hdm Colorway Status and Enter Value
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmColorwayStatus));
		CommonFunctions.clickButtonOrLink(hdmColorwayStatus, "hdm Colorway Ratio");
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmColorwayStatusInput));
		CommonFunctions.selectFromDropDownByVisibleText(hdmColorwayStatusInput, "Active");
		
		//Click on Wave and Enter value
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmWave));
		CommonFunctions.clickButtonOrLink(hdmWave, "hdm Wave");
		CommonFunctions.selectFromDropDownByVisibleText(hdmWaveInput, data[12]);
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmWaveInput));
		CommonFunctions.clickButtonOrLink(hdmWaveInput,data[12]);
		
		//Click on Done button
		wait.until(ExpectedConditions.visibilityOfElementLocated(hdmDone));
		CommonFunctions.clickButtonOrLink(hdmDone, "Done button");
        CommonFunctions.waitForPageLoaded();
		//Wait for visibility of HDM edit
		CommonFunctions.waitForVisibilityOfElement(selectSpecification);
		
		}
		catch(Exception e){  
			fail=true;
			log.error("Exception in pd17UpdateHDMtable()", e);
			throw e;
		}
	}
	
	public static boolean closeTheOpenCostSheet() throws Exception
	{
		try{
			if((!(driver.findElements(imgClose).size()==0))){
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			// close the open cost sheet
			CommonFunctions.clickButtonOrLink(imgClose,"icon", "Close");
			log.info("Costsheet closed");
			}
			else if(driver.findElements(imgClose).size()==0){
			
			log.info("Cost sheet is not open");	
			}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in closeTheOpenCostSheet", e);
		   throw e;
		}
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
	
	//Prerequisite: Create Product
	public static void pd18createProductWaveReq1Table(String[] data) throws Exception{
		try{
			pd17CreateProductFromLineSheet(data);
			
			//Switch to default frame
			driver.switchTo().defaultContent();
				
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
			//Click on Product Details tab
			CommonFunctions.clickButtonOrLink(productDetailstab, "Details tab");
			
			//Wait for visibility of specification value dropdown
			CommonFunctions.waitForVisibilityOfElement(selectSpecification);
			
			//Select Season
			CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);

			
			//Create Colorway
			InternalBOMSoftG.Create_Colorway(data);
			
			//Select first Colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
			
			//Wait for visibility of specification value dropdown
			CommonFunctions.waitForVisibilityOfElement(selectSpecification);
			
			 //Enter wave requirement table 1
			 enterWaveRequirement1table(data);
			 
			//Verify Wave table value
			verifyWaveRequirement1table(data);
				

			//Enter wave requirement table 2
			enterWaveRequirement2table(data);
				 
					
			//Verify wave requirement 2 table
			verifyWaveRequirement2table(data);
					
			//enter Wave Requirement 3
			enterWaveRequirement3(data);					
				
			//Select Season
			CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);
					
			//Unselect season
			CommonFunctions.selectFromDropDownByIndex(colorwayName, 0);
					
			//Select first Colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
					
					
			//wait for visibility of Wave requirement 3 table edit
			CommonFunctions.waitForVisibilityOfElement(labelWaveRequirements3Edit);
			
					
			//Get wave requirement 3 status attribue value
			String statusWaveReq3 = driver.findElement(statusdpwaveRequirement3).getText();
					
			//Verify status attribute value is displaying as Active
			Assert.assertEquals(statusWaveReq3, "Active");
					
			//Get the wave requirement 3 wave attribute value
			String waveWaveReq1 = driver.findElement(wavedpwaveRequirement3).getText();
					
			//Verify the Wave Requirement 3 wave value
			Assert.assertEquals(waveWaveReq1, data[22]);
					
			//Enter value in wave Requirement 4 table
			enterWaveRequirement4(data);					
					
			//Verify that wave column
			verifyWaveRequirement4table(data);
					
			//Enter wave requirement 5 table
			enterWaveRequirement5(data);
					
			//Verify wave Requirement 5 table
			verifyWaveRequirement5table(data);
														
			
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in pd18createProductWaveReq1Table()", e);
			throw e;
		}
		
	}
	
	public static void enterWaveRequirement4(String[]data) throws Exception{
		try{
			
			//Click edit on wave requirement 4 table
			CommonFunctions.clickButtonOrLink(lablewaveRequirement4edit, "Wave Requirement 4 table");
			
			//Wait for edit option to display in wave requirement 4 table
			CommonFunctions.waitForVisibilityOfElement(hdmEditPencil);
			
			//Click on Suffix
			CommonFunctions.clickButtonOrLink(suffixwaveRequirement4, "Suffix");
			
			//Wait for visibility of Suffix input to appear
			CommonFunctions.waitForElementTobeClickable(suffixInputwaveRequirement4);
			
			//Enter value suffix with value
			CommonFunctions.enterTextInTextbox(suffixInputwaveRequirement4, data[23]);
			
			//Click on Comment
			CommonFunctions.clickButtonOrLink(commentwaveRequirement4, "Comment");
			
			//Wait for visibility of comments input to appear
			CommonFunctions.waitForElementTobeClickable(commentInputwaveRequirement4);
			
			//Enter value in comment
			CommonFunctions.enterTextInTextbox(commentInputwaveRequirement4, data[28]);
			
			 for(int i=4;i<=36;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreasecolWaveReq4=".//*[@id='menuImage1']//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 colWaveReq4=By.xpath(IncreasecolWaveReq4);
				 //Assigning the Dynamic xpath into the String variable
				 IncreasecolWaveReq4A=".//*[@id='menuImage1']//following::input[2]";
				//Assigning above string a xpath into a Webelement
				enterValuesInCol=By.xpath(IncreasecolWaveReq4A);
				CommonFunctions.waitForElementTobeClickable(colWaveReq4);
				CommonFunctions.clickButtonOrLink(colWaveReq4, "col");
				CommonFunctions.waitForElementTobeClickable(enterValuesInCol);
			    CommonFunctions.enterTextInTextbox(enterValuesInCol, data[18]);
				
				 log.info("value entered for column"+" "+i);
				 
				
			 }
			 //Click on Done
			 CommonFunctions.clickButtonOrLink(donebtnWaverequirement4, "Done");
			

		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in enterWaveRequirement4()", e);
			throw e;
		}
		
	}
	
	public static void enterWaveRequirement5(String[]data) throws Exception{
		try{
			
			//Click edit on wave requirement 5 table
			CommonFunctions.clickButtonOrLink(labelwaveRequirement5edit, "Wave Requirement 5 table edit button");
			
			//Wait for edit option to display in wave requirement 5 table
			CommonFunctions.waitForVisibilityOfElement(hdmEditPencil);
			
			//Click on Suffix
			CommonFunctions.clickButtonOrLink(productwaveRequirement5, "product value");
			
			//Wait for visibility of Suffix input to appear
			CommonFunctions.waitForElementTobeClickable(productInputwaveRequirement5);
			
			
			//Get the Window Handler of parent Window
			String parentWindow= driver.getWindowHandle();
			System.out.println("The ID of parent Window is: " + parentWindow);
			
			//Enter value suffix with value
			CommonFunctions.clickButtonOrLink(productInputwaveRequirement5, "Product cell");
			
			//Get the number of pop up Windows open
			Set<String> handles =driver.getWindowHandles();
			
			for (String handle1: driver.getWindowHandles()){
			
				System.out.println(handle1);
				driver.switchTo().window(handle1);				
			}
			
			//Wait for visibility of Find Product
			CommonFunctions.waitForVisibilityOfElement(findProduct);
			
			//Enter Product Number
			CommonFunctions.enterTextInTextbox(productNumberValue, prodNumber);
			
			//Click on Search
			CommonFunctions.clickButtonOrLink(findProductSearch, "Search");
			
			//Wait for search result
			CommonFunctions.waitForVisibilityOfElement(searchResultProduct);
			
			//Choose the searched the product
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+prodNumber+"')]//preceding::a[1]"), "Choose");
			
			//Switch to Parent Window
			driver.switchTo().window(parentWindow);
			
			//Switch to default content
			driver.switchTo().defaultContent();
			
			//Switch to Content Frame
			driver.switchTo().frame("contentframe");
			
			//Wait for visiblity of Done button
			CommonFunctions.waitForVisibilityOfElement(doneBtnwaveRequirement5);
			
			
			 for(int i=6;i<=38;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreasecolWaveReq5=".//*[@id='menuImage1']//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 colWaveReq5=By.xpath(IncreasecolWaveReq5);
				 //Assigning the Dynamic xpath into the String variable
				 IncreasecolWaveReq5A=".//*[@id='menuImage1']//following::input[2]";
				 //Assigning above string a xpath into a Webelement
				enterValuesInColl=By.xpath(IncreasecolWaveReq5A);
				CommonFunctions.waitForElementTobeClickable(colWaveReq5);
				CommonFunctions.clickButtonOrLink(colWaveReq5, "col");
				CommonFunctions.waitForElementTobeClickable(enterValuesInColl);
				//Clear the cell value 
				CommonFunctions.clearTextBox(enterValuesInColl, "clear");
			    CommonFunctions.enterTextInTextbox(enterValuesInColl, data[22]);
				
				 log.info("value entered for column"+" "+i);
				 

			 }
			 
			 //Click on Done
			 CommonFunctions.clickButtonOrLink(doneBtnwaveRequirement5, "Done");
			

		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in enterWaveRequirement5()", e);
			throw e;
		}
		
	}
	
	public static void enterWaveRequirement3(String[]data) throws Exception{
		try{
			
			//Select Season
			CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);
			
			//Unselect colorways
			CommonFunctions.selectFromDropDownByIndex(colorwayName, 0);
						
			
			//Select first Colorway
			CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
			
			//wait for visibility of Wave requirement 3 table edit
			CommonFunctions.waitForVisibilityOfElement(labelWaveRequirements3Edit);
			
			//Click edit on Wave requirement 3 table
			CommonFunctions.clickButtonOrLink(labelWaveRequirements3Edit, "Wave requirement 3 table edit button");
			
			//Wait for visibility of Edit pencil button
			CommonFunctions.waitForVisibilityOfElement(hdmEditPencil);
			
			
			//Click on Status attribute
			CommonFunctions.clickButtonOrLink(statusWaveRequirement3, "Status attribute");
			
			//Wait for status input attribute to display
			CommonFunctions.waitForElementTobeClickable(statusWaveRequirement3Input);
								
			//Click on Status input and select Active
			CommonFunctions.clickButtonOrLink(statusWaveRequirement3Input, "Status Input");
			CommonFunctions.selectFromDropDownByVisibleText(statusWaveRequirement3Input, "Active");
			
			//Click on wave attribute
			CommonFunctions.clickButtonOrLink(waveWaveRequirement3, "Status attribute");
			
			//Wait for Wave attribute to display
			CommonFunctions.waitForElementTobeClickable(waveWaveRequirement3Input);
								
			//Click on Status input and select Active
			CommonFunctions.clickButtonOrLink(waveWaveRequirement3Input, "Status Input");
			CommonFunctions.selectFromDropDownByVisibleText(waveWaveRequirement3Input, data[22]);
			
			//Click on Done
			CommonFunctions.clickButtonOrLink(donebtnWaveRequirement3Input, "Done");

		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in enterWaveRequirement3()", e);
			throw e;
		}
		
	}
	
	 public static void enterWaveRequirement1table(String []data) throws Exception {
			try{
				
				//Wait for visibility of Wave Requirement table 1
				 CommonFunctions.waitForElementTobeClickable(labelWaveRequirements1Edit);
				 CommonFunctions.clickButtonOrLink(labelWaveRequirements1Edit, "link", "Wave Requirement 1");

				//Wait for visibility of Wave requirement 1 edit pencil image to appear
				 CommonFunctions.waitForVisibilityOfElement(waveRequiremnt1editPencil);
		
				 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreasewaveWaveReq1=".//*[@id='r1_hbWaveDescription']//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
		        waveWaveReq1=By.xpath(IncreasewaveWaveReq1);
				 //Assigning the Dynamic xpath into the String variable
		        IncreasewaveWaveReq1A=IncreasewaveWaveReq1+"/div/input["+j+"]";
				 //Assigning above string a xpath into a Webelement
				enterValuesInWave=By.xpath(IncreasewaveWaveReq1A);
				CommonFunctions.waitForElementTobeClickable(waveWaveReq1);
				//CommonFunctions.waitForPageLoaded();
				CommonFunctions.clickButtonOrLink(waveWaveReq1, "wave value");
				CommonFunctions.waitForElementTobeClickable(enterValuesInWave);
				CommonFunctions.clearTextBox(enterValuesInWave, "clear wave cell");
			    CommonFunctions.enterTextInTextbox(enterValuesInWave, data[22]);
				
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseNotOnShelfBeforeWaveReq1="//td[contains(text(),'Not On-Shelf Before')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 notOnShelfBeforeWaveReq1=By.xpath(IncreaseNotOnShelfBeforeWaveReq1);
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseNotOnShelfBeforeWaveReq1A=IncreaseNotOnShelfBeforeWaveReq1+"/div/input["+j+"]";
				 //Assigning above string a xpath into a Webelement
				enterValuesInNotOnShelfBefore=By.xpath(IncreaseNotOnShelfBeforeWaveReq1A);
				//Wait for Not OnShelf Before cell value to appear
				CommonFunctions.waitForElementTobeClickable(notOnShelfBeforeWaveReq1);
				//Click on Not OnShelf Before cell value to appear
				CommonFunctions.clickButtonOrLink(notOnShelfBeforeWaveReq1, "Not on Shelf Before");
				//Wait for Not OnShelf Before cell value to appear
				CommonFunctions.waitForElementTobeClickable(enterValuesInNotOnShelfBefore);
				//Clear text on Not OnShelf Before cell value to appear
				CommonFunctions.clearTextBox(enterValuesInNotOnShelfBefore, "clear Not on Shelf Before");
				//Enter in Not OnShelf Before cell value to appear
			    CommonFunctions.enterTextInTextbox(enterValuesInNotOnShelfBefore, data[25]);
				
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseDoNotShellBeforeWaveReq1="//td[contains(text(),'Do Not Sell After')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 doNotShellBeforeWaveReq1=By.xpath(IncreaseDoNotShellBeforeWaveReq1);
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseNotOnShelfBeforeWaveReq1A=IncreaseDoNotShellBeforeWaveReq1+"/div/input["+j+"]";
				 //Assigning above string a xpath into a Webelement
				enterValuesInNotOnShelfBefore=By.xpath(IncreaseNotOnShelfBeforeWaveReq1A);
				//Wait for Do Not Shell Before cell value to appear
				CommonFunctions.waitForElementTobeClickable(doNotShellBeforeWaveReq1);
				//Click on Do Not Shell Before value to appear
				CommonFunctions.clickButtonOrLink(doNotShellBeforeWaveReq1, "Not On Shelf Before");
				//Wait for Do Not Shell Before cell value to appear
				CommonFunctions.waitForElementTobeClickable(enterValuesInNotOnShelfBefore);
				//Clear text on Do Not Shell Before cell value to appear
				CommonFunctions.clearTextBox(enterValuesInNotOnShelfBefore, "clear Not On Shelf Before cell");
				//Enter in Do Not Shell Before value to appear
			    CommonFunctions.enterTextInTextbox(enterValuesInNotOnShelfBefore, data[26]);
				
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseWaveOnShelfDateWaveReq1="//td[contains(text(),'Wave - On Shelf Date')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 wavOnShelfDateWaveReq1=By.xpath(IncreaseWaveOnShelfDateWaveReq1);
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseWaveOnShelfDateWaveReq1A=IncreaseWaveOnShelfDateWaveReq1+"/div/input["+j+"]";
				 //Assigning above string a xpath into a Webelement
				enterValuesInWaveOnShelfDate=By.xpath(IncreaseWaveOnShelfDateWaveReq1A);
				//Wait for Do Not Shell Before cell value to appear
				CommonFunctions.waitForElementTobeClickable(wavOnShelfDateWaveReq1);
				//Click on Do Not Shell Before value to appear
				CommonFunctions.clickButtonOrLink(wavOnShelfDateWaveReq1, "Wave on Shelf Date");
				//Wait for Do Not Shell Before cell value to appear
				CommonFunctions.waitForElementTobeClickable(enterValuesInWaveOnShelfDate);
				//Clear text on Do Not Shell Before cell value to appear
				CommonFunctions.clearTextBox(enterValuesInWaveOnShelfDate, "clear Wave on Shelf Date cell");
				//Enter in Do Not Shell Before value to appear
			    CommonFunctions.enterTextInTextbox(enterValuesInWaveOnShelfDate, data[27]);
				
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 //Click on Done button
			 CommonFunctions.clickButtonOrLink(waveReq1Donebtn, "btn", "Done");
			    
			 //Wait for select specification to appear
			 CommonFunctions.waitForVisibilityOfElement(selectSpecification);
			 
					
			}
			catch(Exception e){
				fail=true;
				log.error("error while entering the value enterWaveRequirement1table()" +e);
				throw e;
			}
			 }
	 
	 
	 public static void enterWaveRequirement2table(String []data) throws Exception {
			try{
				
				//Wait for visibility of Wave Requirement table 1
				 CommonFunctions.waitForElementTobeClickable(labelWaveRequirements2Edit);
				 CommonFunctions.clickButtonOrLink(labelWaveRequirements2Edit, "link", "Wave Requirement 2");

				//Wait for visibility of Hide button
				 CommonFunctions.waitForVisibilityOfElement(wave2hidebutton);

			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreasewaveForecastWaveReq2=".//td[contains(text(),'Wave Forecast')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 waveForecastWaveReq2=By.xpath(IncreasewaveForecastWaveReq2);
				 //Assigning the Dynamic xpath into the String variable
				 IncreasewaveForecastWaveReq2A=IncreasewaveForecastWaveReq2+"/div/input["+j+"]";
				 //Assigning above string a xpath into a Webelement
				enterValuesInWaveForecast=By.xpath(IncreasewaveForecastWaveReq2A);
				CommonFunctions.waitForElementTobeClickable(waveForecastWaveReq2);
				//Click on Wave Forecast value cell
				CommonFunctions.clickButtonOrLink(waveForecastWaveReq2, "Wave Forecast");
				//Wait for Wave Forecast 
				CommonFunctions.waitForElementTobeClickable(enterValuesInWaveForecast);
				//Clear Wave forcast
				CommonFunctions.clearTextBox(enterValuesInWaveForecast, "clear Wave Forecast cell");
			    CommonFunctions.enterTextInTextbox(enterValuesInWaveForecast, data[18]);
				
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseMaxShipQtyWaveReq2="//td[contains(text(),'Max Ship Qty')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 maxShipQtyWaveReq2=By.xpath(IncreaseMaxShipQtyWaveReq2);
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseMaxShipQtyWaveReq2A=IncreaseMaxShipQtyWaveReq2+"/div/input["+j+"]";
				 //Assigning above string a xpath into a Webelement
				enterValuesInMaxShipQty=By.xpath(IncreaseMaxShipQtyWaveReq2A);
				//Wait for Max Ship Qty cell value to appear
				CommonFunctions.waitForElementTobeClickable(maxShipQtyWaveReq2);
				//Click on Max Ship Qty cell value to appear
				CommonFunctions.clickButtonOrLink(maxShipQtyWaveReq2, "Not on Shelf Before");
				//Wait for Max Ship Qty cell value to appear
				CommonFunctions.waitForElementTobeClickable(enterValuesInMaxShipQty);
				//Clear text on Max Ship Qty cell value to appear
				CommonFunctions.clearTextBox(enterValuesInMaxShipQty, "clear Max Ship Qty");
				//Enter in Max Ship Qty cell value to appear
			    CommonFunctions.enterTextInTextbox(enterValuesInMaxShipQty, data[19]);
				
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
//			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
//				 //Assigning the Dynamic xpath into the String variable
//				 IncreaseSetQtyWaveReq2="//td[contains(text(),'Set Qty')]//following::td"+"["+i+"]"; 
//		        //Assigning above string a xpath into a Webelement
//				 setQtyWaveReq2=By.xpath(IncreaseSetQtyWaveReq2);
//				 //Assigning the Dynamic xpath into the String variable
//				 IncreaseSetQtyWaveReq2A=IncreaseSetQtyWaveReq2+"/div/input["+j+"]";
//				 //Assigning above string a xpath into a Webelement
//				enterValuesInSetQty=By.xpath(IncreaseSetQtyWaveReq2A);
//				//Wait for Set Qty cell value to appear
//				CommonFunctions.waitForElementTobeClickable(setQtyWaveReq2);
//				//Click on Set Qty value to appear
//				CommonFunctions.clickButtonOrLink(setQtyWaveReq2, "Set Qty");
//				//Wait for Set Qty cell value to appear
//				CommonFunctions.waitForElementTobeClickable(enterValuesInSetQty);
//				//Clear text on Set Qty cell value to appear
//				CommonFunctions.clearTextBox(enterValuesInSetQty, "clear Not On Shelf Before cell");
//				//Enter in Set QTy value to appear
//			    CommonFunctions.enterTextInTextbox(enterValuesInSetQty, data[20]);
//				
//				 log.info("value entered for column"+" "+i);
//				 
//			 }
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseDistributionQtyWaveReq2="//td[contains(text(),'Distribution Qty')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 distributionQtyWaveReq2=By.xpath(IncreaseDistributionQtyWaveReq2);
				 //Assigning the Dynamic xpath into the String variable
				 IncreaseDistributionQtyWaveReq2A=IncreaseDistributionQtyWaveReq2+"/div/input["+j+"]";
				 //Assigning above string a xpath into a Webelement
				enterValuesInDistributionQty=By.xpath(IncreaseDistributionQtyWaveReq2A);
				//Wait for Distribution Qty cell value to appear
				CommonFunctions.waitForElementTobeClickable(distributionQtyWaveReq2);
				//Click on Distribution Qty value to appear
				CommonFunctions.clickButtonOrLink(distributionQtyWaveReq2, "Dsitribution Qty");
				//Wait for Distribution Qty cell value to appear
				CommonFunctions.waitForElementTobeClickable(enterValuesInDistributionQty);
				//Clear text on Distribution Qty cell value to appear
				CommonFunctions.clearTextBox(enterValuesInDistributionQty, "clear Wave on Shelf Date cell");
				//Enter in Distribution Qty value to appear
			    CommonFunctions.enterTextInTextbox(enterValuesInDistributionQty, data[21]);
				
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 //Click on Done button
			 CommonFunctions.clickButtonOrLink(waveReq2Donebtn, "btn", "Done");
			    
			 //Wait for select specification to appear
			 CommonFunctions.waitForVisibilityOfElement(selectSpecification);
			 
					
			}
			catch(Exception e){
				fail=true;
				log.error("error while entering the value enterWaveRequirement2table()" +e);
				throw e;
			}
			 }
	
	 
	 public static void verifyWaveRequirement1table(String []data) throws Exception {
			try{
				
				//Select Season
				CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);
				
				//Select first Colorway
				CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
				
				//wait for visibility of specification dropdown
				CommonFunctions.waitForVisibilityOfElement(selectSpecification);
				
	
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 wavedpWaveReq1="//td[contains(text(),'Wave Requirements 1 - Dates: ')]//following::td[1]/div[2]/table/tbody/tr[2]/td[1]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 wavedpWaveReq1Value=By.xpath(wavedpWaveReq1);
				 
				 //Get the text for wave cell
				 String waveValue =driver.findElement(wavedpWaveReq1Value).getText();
				 
				 //Verify that wave value is displaying what is entered
				 Assert.assertEquals(waveValue, data[22]);
				 log.info("The value of wave displaying in detail page is matching what is entered:" + waveValue);
				 
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 noteonShelfBeforedpWaveReq1="//td[contains(text(),'Not On-Shelf Before')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 noteonShelfBeforedpWaveReq1Value=By.xpath(noteonShelfBeforedpWaveReq1);
				 
				 //Get the text for Not on Shelf Before cell
				 String NoteonShelfBeforeValue =driver.findElement(noteonShelfBeforedpWaveReq1Value).getText();
				 
				 //Verify that Not on Shelf Before value is displaying what is entered
				 Assert.assertEquals(NoteonShelfBeforeValue, data[25]);
				 log.info("Not on Shelf Before attribute value matching what is entered" +NoteonShelfBeforeValue );
				 
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 doNotSellAfterdpWaveReq1="//td[contains(text(),'Do Not Sell After')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 doNotSellAfterdpWaveReq1Value=By.xpath(doNotSellAfterdpWaveReq1);
				 
				 //Get the text for Do Not Sell After cell
				 String DoNotSellAfterWaveReqValue =driver.findElement(doNotSellAfterdpWaveReq1Value).getText();
				 
				 //Verify that Do Not Sell After value is displaying what is entered
				 Assert.assertEquals(DoNotSellAfterWaveReqValue, data[26]);
				 log.info("Do Not Sell After attribute value matching what is entered" +DoNotSellAfterWaveReqValue );
				 
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 waveOnShelfDatedpWaveReq1="//td[contains(text(),'Wave - On Shelf Date')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 waveOnShelfDatedpWaveReq1Value=By.xpath(waveOnShelfDatedpWaveReq1);
				 
				 //Get the text for Wave on Shelf Date cell
				 String waveOnShelfDateWaveReq1Value =driver.findElement(waveOnShelfDatedpWaveReq1Value).getText();
				 
				 //Verify that Wave on Shelf Date value is displaying what is entered
				 Assert.assertEquals(waveOnShelfDateWaveReq1Value, data[27]);
				 log.info("Do Not Sell After attribute value matching what is entered" +waveOnShelfDateWaveReq1Value );
				 
				 log.info("value entered for column"+" "+i);
				 
			 }

					
			}
			catch(Exception e){
				fail=true;
				log.error("error while entering the value verifyWaveRequirement1table()" +e);
				throw e;
			}
			 }
	 
	 public static void verifyWaveRequirement2table(String []data) throws Exception {
			try{
				
				//Select Season
				CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);
				
				//Select first Colorway
				CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
				
				//wait for visibility of specification dropdown
				CommonFunctions.waitForVisibilityOfElement(labelWaveRequirements2Edit);

			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 waveForecastValueWaveReq2="//td[contains(text(),'% Wave Forecast')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 waveForecastValueWaveReq2Value=By.xpath(waveForecastValueWaveReq2);
				 
				 //Get the text for wave Forecast cell
				 String waveForcastValue =driver.findElement(waveForecastValueWaveReq2Value).getText();
				 
				 //Verify that wave Forecast value is displaying what is entered
				 Assert.assertEquals(waveForcastValue, data[18]);
				 log.info("The value of wave Forecast displaying in detail page is matching what is entered:" + waveForcastValue);
				 
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 for(int i=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 maxShipQtydpWaveReq2="//td[contains(text(),'Max Ship Qty')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 maxShipdpWaveReq2Value=By.xpath(maxShipQtydpWaveReq2);
				 
				 //Get the text for Max Ship Qty cell
				 String maxshiptQtyValue =driver.findElement(maxShipdpWaveReq2Value).getText();
				 
				 //Verify that Max Ship Qty value is displaying what is entered
				 Assert.assertEquals(maxshiptQtyValue, data[19]);
				 log.info("Max Ship Qty attribute value matching what is entered" +maxshiptQtyValue );
				 
				 log.info("value entered for column"+" "+i);
				 
			 }
			 
			 
//			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
//				 //Assigning the Dynamic xpath into the String variable
//				 setQtydpWaveReq2="//td[contains(text(),'Set Qty')]//following::td"+"["+i+"]"; 
//		        //Assigning above string a xpath into a Webelement
//				 setQtydpWaveReq2Value=By.xpath(setQtydpWaveReq2);
//				 
//				 //Get the text for Set Qty cell
//				 String setQtyWaveReq2Value =driver.findElement(setQtydpWaveReq2Value).getText();
//				 
//				 //Verify that Set Qty value is displaying what is entered
//				 Assert.assertEquals(setQtyWaveReq2Value, data[20]);
//				 log.info("Set Qty attribute value matching what is entered" +setQtyWaveReq2Value );
//				 
//				 log.info("value entered for column"+" "+i);
//				 
//			 }
			 
			 for(int i=1,j=1;i<=standardNoOfColumns;i++){
				 //Assigning the Dynamic xpath into the String variable
				 distributionQtydpWaveReq1="//td[contains(text(),'Distribution Qty')]//following::td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 distributionQtydpWaveReq1Value=By.xpath(distributionQtydpWaveReq1);
				 
				 //Get the text for Distribution value cell
				 String distributionQtyWaveReq1Value =driver.findElement(distributionQtydpWaveReq1Value).getText();
				 
				 //Verify that Distribution value is displaying what is entered
				 Assert.assertEquals(distributionQtyWaveReq1Value, data[21]);
				 log.info("Distribution value attribute value matching what is entered" +distributionQtyWaveReq1Value );
				 
				 log.info("value entered for column"+" "+i);
				 
			 }

					
			}
			catch(Exception e){
				fail=true;
				log.error("error while entering the value verifyWaveRequirement2table()" +e);
				throw e;
			}
			 }
	 
	 public static void verifyWaveRequirement4table(String []data) throws Exception {
			try{
				
				//Select Season
				CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);
				
				//Select first Colorway
				CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
				
				//wait for visibility of specification dropdown
				CommonFunctions.waitForVisibilityOfElement(suffixwavedpRequirement4);				
				
				//Get the text for Suffix
				String suffixWave = driver.findElement(suffixwavedpRequirement4).getText();
				
				log.info("The value of Suffix in wave requirement 4 table is:" + suffixWave);
				
				//Verify that suffix value is displaying what is entered
				Assert.assertEquals(suffixWave, data[23]);
				
				//Get the text for comment
				String comment= driver.findElement(commentwavedpRequirement4).getText();
				
				log.info("The value of comment in wave requirement 4 table is:" + suffixWave);
				
				//Verify that comments are displaying what is entered
				Assert.assertEquals(comment, data[28]);
				 
			 for(int i=3;i<=35;i++){
				 //Assigning the Dynamic xpath into the String variable
				 suffixdpWaveReq4="//td[contains(text(),'Wave Requirements 4 - Suffix:')]//following::a[contains(text(),'Suffix')]//following::tr[1]/td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 suffixdpWaveReq4Value=By.xpath(suffixdpWaveReq4);
				 
				 //Get the text for wave cell
				 String suffixValue =driver.findElement(suffixdpWaveReq4Value).getText();
				 
				 //Verify that wave value is displaying what is entered
				 Assert.assertEquals(suffixValue, data[18]);
				 log.info("The value of wave displaying in detail page is matching what is entered:" + suffixValue);
				 
				 log.info("value entered for column"+" "+i);
				 
			 }							
			}
			catch(Exception e){
				fail=true;
				log.error("error while entering the value verifyWaveRequirement4table()" +e);
				throw e;
			}
			 }
	 
	 
	 public static void verifyWaveRequirement5table(String []data) throws Exception {
			try{
				
				
				//Select Season
				CommonFunctions.selectFromDropDownByVisibleText(Product.detailPageSeasonDD, data[5]);
				
				//Select first Colorway
				CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
				
				//wait for visibility of specification dropdown
				CommonFunctions.waitForVisibilityOfElement(labelwaveRequirement5edit);
				
				//Get the text of Product Name
				String productName =driver.findElement(productNameWaveRequirement5).getText();
				
				log.info("The name of the product is:" + productName);
				
				//Verify that product Name is not empty
				Assert.assertNotEquals(productName, null);
				
				log.info("Product Name value is puled from the product Number entered:" + productName);
				
				//Get the Product Type
				String productType= driver.findElement(productTypeWaveRequirement5).getText();
				
				//Verify that product Type is assortment/Solid
				Assert.assertEquals(productType, data[2]);
				
				log.info("The Product Type is pulled from the product which is entered:"+ productType);
				
				//Get the value of Item Qty
				String itemQty =driver.findElement(itemQtyWaveRequirement5).getText();
				
				//Verify itemQty value
				Assert.assertEquals(itemQty, data[4]);
				
				log.info("The value of Set Qty attribute is:" + itemQty);
				 
			 for(int i=5;i<=37;i++){
				 //Assigning the Dynamic xpath into the String variable
				 coldpWaveReq5="//td[contains(text(),'Wave Requirements 5 - Distribution:')]//following::a[contains(text(),'Product Type')]//following::tr[1]/td"+"["+i+"]"; 
		        //Assigning above string a xpath into a Webelement
				 coldpWaveReq5Value=By.xpath(coldpWaveReq5);
				 
				 //Get the text for wave cell
				 String colValue =driver.findElement(coldpWaveReq5Value).getText();
				 
				 //Verify that wave value is displaying what is entered
				 Assert.assertEquals(colValue, data[22]);
				 log.info("The value of wave displaying in detail page is matching what is entered:" + colValue);
				 
				 log.info("value entered for column"+" "+i);
				 				 
				 
			 }							
			}
			catch(Exception e){
				fail=true;
				log.error("error while entering the value verifyWaveRequirement5table()" +e);
				throw e;
			}
			 }
	 
	//Prerequisite: Create Product
	public static String pd17CreateProductFromLineSheet(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			if(!data[0].contains("vuser") && !data[0].contains("vendora")){
				CommonProjectFunctions.clickMySeasonLink();
				CommonFunctions.waitForPageLoaded();
				//Select Season Year
				CommonFunctions.selectFromDropDownByVisibleText(mySeasonYear, data[5]);
				//Click on Line Sheet link
				CommonFunctions.clickButtonOrLink(lineSheet, "link", "Line Sheet");
				CommonFunctions.waitForPageLoaded();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");

				Date date = new Date();
				//Select Line sheet view
				CommonFunctions.waitForVisibilityOfElement(lineSheetView);
					CommonFunctions.selectFromDropDownByVisibleText(lineSheetView, data[9]);
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.selectFromDropDownByVisibleText(lineSheetAction, data[8]);
					CommonFunctions.waitForPageLoaded();
					//Click on Assortment/Solid
					CommonFunctions.waitForVisibilityOfElement(By.xpath("//td[contains(text(),'Choose a Type')]"));
					System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')]"));
					CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"), "link", "Product Type");
					System.out.println("Prod Name: "+data[15]+date.getTime());
					Prodname=data[15]+date.getTime();
					Prodname=Prodname.substring(0,8);
					CommonFunctions.enterTextInTextbox(ProductName,Prodname);
					if(!data[2].equalsIgnoreCase("Trade Marketing Display")){
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
					CommonFunctions.waitForPageLoaded();
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
					CommonFunctions.waitForPageLoaded();
					
					//Wait for Details tab
					CommonFunctions.waitForVisibilityOfElement(productDetailstab);
					
					//Click on details tab
					CommonFunctions.clickButtonOrLink(productDetailstab, "product details tab");
					
					//Wait for specification dropdown
					CommonFunctions.waitForVisibilityOfElement(selectSpecification);
					
					prodNumber =driver.findElement(productNumber).getText();
										
				}
			
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in pd17CreateProductFromLineSheet()", e);
			throw e;
		}
		return prodNumber;
	}
	
	public static Boolean AddSourcing(String[] data) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Sourcing Configuration");

			//Supplier Selection
			CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
			//CommonFunctions.switchToChildWindow();
			CommonFunctions.waitForPageLoaded();
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
		
			   CommonFunctions.waitForElementTobeClickable(SanitySuite1.NameTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.NameTextBox,"AEQUS ENGINEERED PLASTICS PRIVATE L IMITED", "ProductNumber");
				CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
		
			
			  
			    CommonFunctions.waitForPageLoaded();
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ChooseColorWay);
			    CommonFunctions.clickButtonOrLink(SanitySuite1.ChooseColorWay, "HyperLink", "ChooseButton");
			    //Clicking on Choose Button
			//Switch to parent frame
			driver.switchTo().window(parent);
			//Switch to default frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(sourcingLead, 1);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(sourcingHead, 1);
			//click on Create
			CommonFunctions.clickButtonOrLink(SourcingConfig.CreateSourcebtn, "btn", "Create Source");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.titleIs("ViewProduct/Season: Information"));
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in AddSourcing()", e);
		 throw e;
			}
		}
	
	public static String pc53updateProductVendorCostSheet(String[] data) throws Exception{
		try{
			//Navigate to Cost Sheet tab
			nevigationToCostsheet(data);
			
			// click on Cost sheet List tab
			CostsheetTooling.clickOnCostSheetListTab();
			
			//Select source
			CommonFunctions.selectFromDropDownByVisibleText(selectSource, data[8]);
			
			//SelectSpecification and colorways
			CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
			strCW=InternalBOMSoftG.AddColorway(data);
			
			// search cost sheet name in web table
			searchandClickforRequriedCostsheet(csInWork);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on Actions Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			
			//Wait for visibility of cost sheet identification
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on BOM value
			CommonFunctions.clickButtonOrLink(csAddBOM, "BOM");

			//Remove the BOM
			CommonFunctions.selectFromDropDownByIndex(csAddBOM, 0);
			
			//Click on Save button
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			//Wait for cost sheet identification to display
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on Actions Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			
			//Update Quote currency value
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[13]);
			
			//Enter value in Master Carton Packaging Cost
			CommonFunctions.clearTextBox(csMasterCartonPackagingInput, "Master Carton Packaging Input");
			CommonFunctions.enterTextInTextbox(csMasterCartonPackagingInput, data[18]);
			
			//Enter value in Master Carton Labor Cost
			CommonFunctions.clearTextBox(csMasterCartonLaborCostInput, "Master Carton Labor Cost Input");
			CommonFunctions.enterTextInTextbox(csMasterCartonLaborCostInput, data[19]);
			
			//Enter value in Miscellaneous Cost
			CommonFunctions.clearTextBox(csMiscellaneousCostInput, "Miscellaneous Cost Input");
			CommonFunctions.enterTextInTextbox(csMiscellaneousCostInput, data[20]);
			
			//Enter value in Product Markup
			CommonFunctions.clearTextBox(csProductMarkupInput, "Product Markup Input");
			CommonFunctions.enterTextInTextbox(csProductMarkupInput, data[21]);
			
			//Click on Save button
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			
			//Wait for cost sheet identification to display
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Verify that Quote currency is updated
			String updatedquote =driver.findElement(csQuoteCurrencydp).getText();
			Assert.assertEquals(updatedquote, data[13]);
			
			log.info("Quote currency is successfully updated to:" + updatedquote);
			
			//Verify that all the Product Cost - Quote Currency: table attribute value is matching as entered
			String MasterCartonPackagingdp =driver.findElement(csMasterCartonPackagingdp).getText();
			Assert.assertEquals(MasterCartonPackagingdp, data[18]);
			log.info("Master Carton Packaging Material is updated to" + MasterCartonPackagingdp);
			
			//Master Carton Labor Cost attribute verification
			String MasterCartonLaborCostdp =driver.findElement(csMasterCartonLaborCostdp).getText();
			Assert.assertEquals(MasterCartonLaborCostdp, data[19]);
			log.info("Master Carton Labor Cost is updated to" + MasterCartonLaborCostdp);
				
			//Miscellaneous Cost  attribute verification
			String MiscellaneousCostdp =driver.findElement(csMiscellaneousCostdp).getText();
			Assert.assertEquals(MiscellaneousCostdp, data[20]);
			log.info("Miscellaneous Cost is updated to" + MiscellaneousCostdp);

			//Product Markup  attribute verification
			String ProductMarkupdp =driver.findElement(csProductMarkupdp).getText();
			Assert.assertEquals(ProductMarkupdp, data[21]);
			log.info("Product Markup is updated to" + ProductMarkupdp);
		
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in pc53updateProductVendorCostSheet()", e);
			throw e;
		}
		return csInWork1;
	}
	
	public static String pc53createProductVendorCostSheet(String[] data) throws Exception{
		try{
			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			log.info("**********Inside********");
			strCSInWork = driver.findElement(csHeading).getText().substring(20);
			String []varCSInWork = strCSInWork.split("Actions:");
			csInWork = varCSInWork[0].trim();

			log.info("Retail  costsheet in Inwork status is : "+csInWork);
			
			//Wait for cost sheet Identification tab
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on Actions>Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			
			//Wait for cost sheet Identification tab
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);

			System.out.println("User needs to select BOM:" + strbomInWork);
			//Select the BOM
			CommonFunctions.clickButtonOrLink(csAddBOM, "BOM");
			CommonFunctions.clickButtonOrLink(By.xpath("//td[contains(text(),'BOM')]//following::select[1]/option[contains(text(),'"+strbomInWork+"')]"), "BOM");
			//CommonFunctions.selectFromDropDownByVisibleText(csAddBOM, strbomInWork);
		
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in pc53createProductVendorCostSheet()", e);
			throw e;
		}
		return csInWork;
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
			throw e;
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
			throw e;
		}
		return bomname;
	}
	
	
	public static String pc53createProductVendorBOM(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
						
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
			String strbomInWork1=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			//Remove the trailing and leading white space
			strbomInWork=strbomInWork1.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:"+strbomInWork);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc53createProductVendorBOM()", e);
			throw e;
	
		}
		return strbomInWork;
	}
	
	public static String pc51updateProductIntCostSheet(String[] data) throws Exception{
		try{
			//Navigate to Cost Sheet tab
			nevigationToCostsheet(data);
			
			// click on Cost sheet List tab
			CostsheetTooling.clickOnCostSheetListTab();
			
			//Select source
			CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
			
			//SelectSpecification and colorways
			CommonFunctions.selectFromDropDownByVisibleText(selectSpecification, data[9]);
			strCW=InternalBOMSoftG.AddColorway(data);
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(selectSpecification));
			// search cost sheet name in web table
			searchandClickforRequriedCostsheet(csProdInternalInWork);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on Actions Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			
			//Wait for visibility of cost sheet identification
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on BOM value
			CommonFunctions.clickButtonOrLink(csAddBOM, "BOM");

			//Remove the BOM
			CommonFunctions.selectFromDropDownByIndex(csAddBOM, 0);
			
			//Click on Save button
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			//Wait for cost sheet identification to display
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on Actions Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			
			//Update Quote currency value
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[13]);
			
			//Enter value in Master Carton Packaging Cost
			CommonFunctions.clearTextBox(csMasterCartonPackagingInput, "Master Carton Packaging Input");
			CommonFunctions.enterTextInTextbox(csMasterCartonPackagingInput, data[18]);
			
			//Enter value in Master Carton Labor Cost
			CommonFunctions.clearTextBox(csMasterCartonLaborCostInput, "Master Carton Labor Cost Input");
			CommonFunctions.enterTextInTextbox(csMasterCartonLaborCostInput, data[19]);
			
			//Enter value in Miscellaneous Cost
			CommonFunctions.clearTextBox(csMiscellaneousCostInput, "Miscellaneous Cost Input");
			CommonFunctions.enterTextInTextbox(csMiscellaneousCostInput, data[20]);
			
			//Enter value in Product Markup
			CommonFunctions.clearTextBox(csProductMarkupInput, "Product Markup Input");
			CommonFunctions.enterTextInTextbox(csProductMarkupInput, data[21]);
			
			//Click on Save button
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			
			//Wait for cost sheet identification to display
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Verify that Quote currency is updated
			String updatedquote =driver.findElement(csQuoteCurrencydp).getText();
			Assert.assertEquals(updatedquote, data[13]);
			
			log.info("Quote currency is successfully updated to:" + updatedquote);
			
			//Verify that all the Product Cost - Quote Currency: table attribute value is matching as entered
			String MasterCartonPackagingdp =driver.findElement(csMasterCartonPackagingdp).getText();
			Assert.assertEquals(MasterCartonPackagingdp, data[18]);
			log.info("Master Carton Packaging Material is updated to" + MasterCartonPackagingdp);
			
			//Master Carton Labor Cost attribute verification
			String MasterCartonLaborCostdp =driver.findElement(csMasterCartonLaborCostdp).getText();
			Assert.assertEquals(MasterCartonLaborCostdp, data[19]);
			log.info("Master Carton Labor Cost is updated to" + MasterCartonLaborCostdp);
				
			//Miscellaneous Cost  attribute verification
			String MiscellaneousCostdp =driver.findElement(csMiscellaneousCostdp).getText();
			Assert.assertEquals(MiscellaneousCostdp, data[20]);
			log.info("Miscellaneous Cost is updated to" + MiscellaneousCostdp);

			//Product Markup  attribute verification
			String ProductMarkupdp =driver.findElement(csProductMarkupdp).getText();
			Assert.assertEquals(ProductMarkupdp, data[21]);
			log.info("Product Markup is updated to" + ProductMarkupdp);
		
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in pc51updateProductIntCostSheet()", e);
			throw e;
		}
		return csInWork1;
	}
	
	public static String pc52updateRetailIntCostSheet(String[] data) throws Exception{
		try{
			//Navigate to Cost Sheet tab
			nevigationToCostsheet(data);
			
			// click on Cost sheet List tab
			CostsheetTooling.clickOnCostSheetListTab();
			
			//Select source
			CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
			
			//SelectSpecification and colorways
			CommonFunctions.selectFromDropDownByVisibleText(selectSpecification, data[9]);
			strCW=InternalBOMSoftG.AddColorway(data);
			
			// search cost sheet name in web table
			searchandClickforRequriedCostsheet(csRetailInternalInWork);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on Actions Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			
			//Wait
			CommonFunctions.waitForPageLoaded();
			
			//Wait for visibility of cost sheet identification
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Remove the BOM
			CommonFunctions.selectFromDropDownByIndex(csAddBOM, 0);
			
			//Click on Save button
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			
			//Wait
			CommonFunctions.waitForPageLoaded();
			
			//Wait for cost sheet identification to display
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Click on Actions Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			
			//Update Quote currency value
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[13]);
			
			//Enter value in Retail Item Cost
			CommonFunctions.clearTextBox(csRetailItemCostInput, "Retail Item Cost");
			CommonFunctions.enterTextInTextbox(csRetailItemCostInput, data[18]);
			
			//Enter value in Plastic Material Labor
			CommonFunctions.clearTextBox(csRetailPlasticMaterialInput, "Plastic Material");
			CommonFunctions.enterTextInTextbox(csRetailPlasticMaterialInput, data[19]);
			
			//Enter value in Chemical Material
			CommonFunctions.clearTextBox(csRetailChemicalMaterialInput, "Chemical Material");
			CommonFunctions.enterTextInTextbox(csRetailChemicalMaterialInput, data[20]);
			
			//Enter value in Purchased Material
			CommonFunctions.clearTextBox(csRetailPurchasedMaterialInput, "Purchased Material");
			CommonFunctions.enterTextInTextbox(csRetailPurchasedMaterialInput, data[21]);
			
			//Enter value in Electronic Material
			CommonFunctions.clearTextBox(csRetailElectronicMaterialInput, "Electronic Material");
			CommonFunctions.enterTextInTextbox(csRetailElectronicMaterialInput, data[22]);
			
			//Enter value in SoftGoods Material
			CommonFunctions.clearTextBox(csRetailSoftGoodsMaterialInput, "SoftGoods Material");
			CommonFunctions.enterTextInTextbox(csRetailSoftGoodsMaterialInput, data[23]);
			
			//Enter value in Packaging Material
			CommonFunctions.clearTextBox(csRetailPackagingMaterialInput, "Packaging Material");
			CommonFunctions.enterTextInTextbox(csRetailPackagingMaterialInput, data[24]);
			
			//Enter value in General Deco Labor Cost
			CommonFunctions.clearTextBox(csRetailGeneralDecoLaborCostInput, "General Deco Labor Cost");
			CommonFunctions.enterTextInTextbox(csRetailGeneralDecoLaborCostInput, data[25]);
			
			//Enter value in Molding Labor Cost
			CommonFunctions.clearTextBox(csRetailMoldingLaborCostInput, "Molding Labor Cost");
			CommonFunctions.enterTextInTextbox(csRetailMoldingLaborCostInput, data[26]);
			
			//Enter value in Markup
			CommonFunctions.clearTextBox(csRetailMarkupInput, "Markup");
			CommonFunctions.enterTextInTextbox(csRetailMarkupInput, data[27]);
			
			//Click on Save button
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			
			//Wait for cost sheet identification to display
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			//Verify that Quote currency is updated
			String updatedquote =driver.findElement(csQuoteCurrencydp).getText();
			Assert.assertEquals(updatedquote, data[13]);
			
			log.info("Quote currency is successfully updated to:" + updatedquote);
			
			//Verify that all the Retail Item Cost - Quote Currency: table attribute value is matching as entered
			String RetailItemCostdp =driver.findElement(csRetailItemCostdp).getText();
			Assert.assertEquals(RetailItemCostdp, data[18]);
			log.info("Retail Item Cost is updated to" + RetailItemCostdp);
			
			//Plastic Material attribute verification
			String RetailPlasticMaterialdp =driver.findElement(csRetailPlasticMaterialdp).getText();
			Assert.assertEquals(RetailPlasticMaterialdp, data[19]);
			log.info("Plastic Material is updated to" + RetailPlasticMaterialdp);
				
			//Chemical Material attribute verification
			String RetailChemicalMaterialdp =driver.findElement(csRetailChemicalMaterialdp).getText();
			Assert.assertEquals(RetailChemicalMaterialdp, data[20]);
			log.info("Chemical Material is updated to" + RetailChemicalMaterialdp);

			//Purchased Material attribute verification
			String RetailPurchasedMaterialdp =driver.findElement(csRetailPurchasedMaterialdp).getText();
			Assert.assertEquals(RetailPurchasedMaterialdp, data[21]);
			log.info("Purchased Material is updated to" + RetailPurchasedMaterialdp);
			
			//Electronic Material attribute verification
			String RetailElectronicMaterialdp =driver.findElement(csRetailElectronicMaterialdp).getText();
			Assert.assertEquals(RetailElectronicMaterialdp, data[22]);
			log.info("Electronic Material is updated to" + RetailElectronicMaterialdp);
	
			
			//Soft Goods Material attribute verification
			String RetailSoftGoodsMaterialdp =driver.findElement(csRetailSoftGoodsMaterialdp).getText();
			Assert.assertEquals(RetailSoftGoodsMaterialdp, data[23]);
			log.info("Soft Goods Material is updated to" + RetailSoftGoodsMaterialdp);
	
			
			//Packaging Material attribute verification
			String RetailPackagingMaterialdp =driver.findElement(csRetailPackagingMaterialdp).getText();
			Assert.assertEquals(RetailPackagingMaterialdp, data[24]);
			log.info("Packaging Material is updated to" + RetailPackagingMaterialdp);
	
			
			//General Deco Labor Cost attribute verification
			String RetailGeneralDecoLaborCostdp =driver.findElement(csRetailGeneralDecoLaborCostdp).getText();
			Assert.assertEquals(RetailGeneralDecoLaborCostdp, data[25]);
			log.info("General Deco Labor Cost is updated to" + RetailGeneralDecoLaborCostdp);
	
			
			//Molding Labor Cost attribute verification
			String RetailMoldingLaborCostdp =driver.findElement(csRetailMoldingLaborCostdp).getText();
			Assert.assertEquals(RetailMoldingLaborCostdp, data[26]);
			log.info("Molding Labor Cost is updated to" + RetailMoldingLaborCostdp);
	
						
			//Markup attribute verification
			String RetailMarkupdp =driver.findElement(csRetailMarkupdp).getText();
			Assert.assertEquals(RetailMarkupdp, data[27]);
			log.info("Markup is updated to" + RetailMarkupdp);
	
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in pc52updateRetailIntCostSheet()", e);
			throw e;
		}
		return "";
	}
	
	public static boolean nevigationToCostsheet(String[] data) throws Exception
	{
		try{
			System.out.println(driver.findElements(CostsheetTooling.tabCostsheet).size());
				CommonProjectFunctions.searchProduct(data[6]);
				
				//Switch to default frame
				driver.switchTo().defaultContent();
				
				//Switch to content frame
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.titleIs("ViewProduct/Season: Information"));
				CommonFunctions.waitForPageLoaded();
			//Wait for details tab
			CommonFunctions.waitForVisibilityOfElement(productDetailstab);
			
			//Click on Sourcing
			CommonProjectFunctions.clickSourcingTab(data[7]);
			CommonProjectFunctions.clickCostingTab();
			
			CommonFunctions.selectFromDropDownByVisibleText(selectSource, data[8]);
			CommonFunctions.handleAlertPopUp();
			
			//SelectSpecification and colorways
			CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);

		}catch(Exception e){
			fail=true;
			log.error("Exception in " + data[3], e);
			throw e;
		}
		return true;
	}
	
	public static String pc51createProductIntCostSheet(String[] data) throws Exception{
		try{
			//Create Cost Sheet
			createCSInternal(data);
			//Wait for cost sheet Identification tab
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSInWork = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			String []varCSInWork = strCSInWork.split("Actions:");
			csProdInternalInWork = varCSInWork[0].trim();
			log.info("Product Internal Item Cost Sheet in Inwork status is : "+csProdInternalInWork);
			//Wait for cost sheet status
			CommonFunctions.waitForVisibilityOfElement(csStatus);
			
			//Click on Actions>Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			wait.until(ExpectedConditions.titleIs("Update Cost Sheet"));
			CommonFunctions.waitForPageLoaded();
			//Wait for cost sheet Identification tab
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);

			System.out.println("User needs to select BOM:" + strBOMInWork);
			//Select the BOM
			wait.until(ExpectedConditions.visibilityOfElementLocated(csAddBOM));
			CommonFunctions.clickButtonOrLink(csAddBOM, "BOM");
			CommonFunctions.selectFromDropDownByVisibleText(csAddBOM, strBOMInWork);
		
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			wait.until(ExpectedConditions.titleIs("View Season Product Information"));
			CommonFunctions.waitForPageLoaded();
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in pc51createProductIntCostSheet()", e);
			throw e;
		}
		return csProdInternalInWork;
	}
	
	public static String pc52createRetailIntCostSheet(String[] data) throws Exception{
		try{
			//Create Cost Sheet
			createCSInternal(data);
			//Wait for cost sheet Identification tab
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSInWorkRetail = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			String []varCSInWork = strCSInWorkRetail.split("Actions:");
			csRetailInternalInWork = varCSInWork[0].trim();
			log.info("Product Internal Item Cost Sheet in Inwork status is : "+csProdInternalInWork);
			//Wait for cost sheet status
			CommonFunctions.waitForVisibilityOfElement(csStatus);
			
			//Click on Actions>Update
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			
			//Wait
			CommonFunctions.waitForPageLoaded();
			
			//Wait for cost sheet Identification tab
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		
			//Select the BOM
			CommonFunctions.selectFromDropDownByVisibleText(csAddBOM, strBOMInWorkRetail);
		
			//Click on Save
			CommonFunctions.clickButtonOrLink(btnSave, "Save button");
			
			//Wait for cost sheet tab to appear
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			
			
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in pc52createRetailIntCostSheet()", e);
			throw e;
		}
		return csRetailInternalInWork;
	}
	
	public static String fillCreateBOM(String[] data) throws Exception{
		try{
			InternalBOMSoftG.BOMname="InWork"+CommonFunctions.getRandomString(4);
			System.out.println("BOM Name entered is: " + InternalBOMSoftG.BOMname);
			CommonFunctions.waitForVisibilityOfElement(headingCreateBOM);
			if(data[2].contains("Retail"))
			{
				CommonFunctions.enterTextInTextbox(name,bomNameRetail);
			}
			else if(data[2].contains("Product"))
			{
				CommonFunctions.enterTextInTextbox(name,bomName);
			}
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(colorway, 1);
			//Select Wave
			CommonFunctions.selectFromDropDownByVisibleText(wave, data[12]);
			//Select Currency
			CommonFunctions.selectFromDropDownByVisibleText(currency, data[11]);
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			
			if((data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				CommonFunctions.enterTextInTextbox(name,bomName);
				//Select colorway
				CommonFunctions.selectFromDropDownByIndex(colorway, 1);
				//Select Wave
				CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);

				//click on Create
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");			
			}
		}

		catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			throw e;
		}
		return bomName;
	}

	
	public static String[] pc51createProductIntBOM(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			wait.until(ExpectedConditions.titleIs("ViewProduct/Season: Information"));
			
		//Wait for details tab
			CommonFunctions.waitForPageLoaded();
		CommonFunctions.waitForVisibilityOfElement(productDetailstab);
		
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selectSource));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
			CommonFunctions.handleAlertPopUp();
			
			//SelectSpecification and colorways
			CommonFunctions.selectFromDropDownByVisibleText(selectSpecification, data[9]);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selectSpecification));
			CommonFunctions.waitForPageLoaded();
			strCW=InternalBOMSoftG.AddColorway(data);
			CommonFunctions.waitForPageLoaded();		
			CommonProjectFunctions.clickMaterialsTab();
             wait.until(ExpectedConditions.visibilityOfElementLocated(addNewBOMTab));
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			//CommonFunctions.enterTextInTextbox(By.xpath("//select[@id='bomTypeId']/option[contains(text(),'"+data[2]+"')]"), data[2]);
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			wait.until(ExpectedConditions.titleIs("Edit BOM:"));
			CommonFunctions.waitForPageLoaded();
			bomName="InWork"+CommonFunctions.getRandomString(4);

			//Create BOM page
			bomnNameInWork1 = fillCreateBOM(data);
			System.out.println("BOM Name returned in create page:  " + bomnNameInWork1);

			//Switch to mainframe
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(CI296.AddRowsButton));
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[17]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
            wait.until(ExpectedConditions.titleIs("ViewProduct/Season: Information"));
            CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(headerAttributes));
			//	String strBOM=driver.findElement(BOMId).getText();
			String strBOMInWork1=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			strBOMInWork= strBOMInWork1.trim();
			System.out.println("BOM name in detail page after check in: " + strBOMInWork);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc51createProductIntBOM()", e);
			throw e;
				}
		return new String[] {strBOMInWork, bomnNameInWork1};
	}
	
	public static String[] pc52createRetailIntBOM(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
		//Wait for details tab
		CommonFunctions.waitForVisibilityOfElement(productDetailstab);
		
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);
			
			CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
			CommonFunctions.handleAlertPopUp();
			
			//SelectSpecification and colorways
			CommonFunctions.selectFromDropDownByVisibleText(selectSpecification, data[9]);
			strCW=InternalBOMSoftG.AddColorway(data);
					
			CommonProjectFunctions.clickMaterialsTab();

			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			//CommonFunctions.enterTextInTextbox(By.xpath("//select[@id='bomTypeId']/option[contains(text(),'"+data[2]+"')]"), data[2]);
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			bomNameRetail="InWork"+CommonFunctions.getRandomString(4);

			//Create BOM page
			bomNameInWorkRetail1 = fillCreateBOM(data);
			System.out.println("BOM Name returned in create page:  " + bomNameInWorkRetail1);

			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[17]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			CommonFunctions.waitForVisibilityOfElement(headerAttributes);
			//	String strBOM=driver.findElement(BOMId).getText();
			String strBOMInWork1=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			System.out.println("The name of the BOM before trim:" + strBOMInWork1);
			strBOMInWorkRetail= strBOMInWork1.trim();
			System.out.println("BOM name in detail page after check in: " + strBOMInWorkRetail);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc52createRetailIntBOM()", e);
			throw e;
				}
		return new String[] {strBOMInWorkRetail, bomNameInWorkRetail1};
	}
	
	
	public static String pc77createProductVendorSelectionQuote(String[] data,String sStatus) throws Exception{
		//	String csReadyforReview;
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSInitialQuoteApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSInitialQuoteApproved = strCSInitialQuoteApproved.split("Actions:");
			csProductVendorSelectionQuote = varCSInitialQuoteApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			closeTheOpenCostSheet();
			log.info("Vendor Product costsheet in initialQuoteApproved status is: "+csProductVendorSelectionQuote);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc77createProductVendorSelectionQuote()", e);
			throw e;
		}
		return csProductVendorSelectionQuote;
	}

	
	public static String searchandClickforRequriedCostsheet(String costSheetName) throws Exception
	{
		try{
			//Wait for Cost Sheet tab
			CommonFunctions.waitForVisibilityOfElement(tabCostsheet);
			
			//Click on cost sheet tab
			CommonFunctions.clickButtonOrLink(tabCostsheet, "Cost Sheet tab");
			
			int colIndex=1;
			List<WebElement> row1 = driver.findElements(By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td"));

			System.out.println(row1.size());
			for(WebElement e: row1){
				colIndex=colIndex+1;
				csName =e.getText().trim(); //.replaceAll("\\s+", " ");
				String csName1 =e.getText().trim().replaceAll("\\s+", " ");
				if (csName1.equals(costSheetName)) {
					CommonFunctions.waitForPageLoaded();
					//System.out.println(By.xpath("//a[text()='"+csName+"']"));
					CommonFunctions.clickButtonOrLink(By.linkText(csName),"link","Cost sheet name");
					//driver.findElement(By.xpath("//a[text()='"+csName+"']")).click();
					return csName;
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in searchandClickforRequriedCostsheet()", e);
			throw e;
		}
		return csName;
	}
	
	public static void pc76cancelRetailCostSheet(String[] data) throws Exception{		
		try{
		if(driver.findElements(tabCostsheet).size() == 0) {
			CommonProjectFunctions.searchProduct(data[6]);
			
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
		//Wait for details tab
		CommonFunctions.waitForVisibilityOfElement(productDetailstab);
		
			//Click on Sourcing
			CommonProjectFunctions.clickSourcingTab(data[7]);
			CommonProjectFunctions.clickCostingTab();
				strSource=AddSource(data);
				strSpec=InternalBOMSoftG.AddSpecification(data);
				strCW=InternalBOMSoftG.AddColorway(data);
		}
		
		//Close the open cost sheet
		closeTheOpenCostSheet();
		
		log.info("*************Cancel the Vendor Selection Quote Cost Sheet**********************************");
		//Click on Cost Sheet List tab
		clickOnCostSheetListTab();
	
		// search Vendor Selection Quote cost sheet name in web table
		searchandClickforRequriedCostsheet(csVendorSelectionQuote);
		CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		log.info("**********Inside********");
		//Change Status
		updateCSStatus("Cancelled");
		CostsheetInternal.verifyCSStatus("Cancelled",csVendorSelectionQuote);

		// close the open cost sheet
		closeTheOpenCostSheet();
		
		log.info("*************Cancel the FEP Approved Cost Sheet**********************************");

		//Click on Cost Sheet List tab
		clickOnCostSheetListTab();
	
		// search FEP Approved cost sheet name in web table
		searchandClickforRequriedCostsheet(csFEPApproved);
		CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		log.info("**********Inside********");
		//Change Status
		updateCSStatus("Cancelled");
		CostsheetInternal.verifyCSStatus("Cancelled",csFEPApproved);

		// close the open cost sheet
		closeTheOpenCostSheet();
		
		
		log.info("*************Cancel the SG4 Approved Cost Sheet**********************************");

		//Click on Cost Sheet List tab
		clickOnCostSheetListTab();
	
		// search SG4 Approved cost sheet name in web table
		searchandClickforRequriedCostsheet(csSG4Approved);
		CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		log.info("**********Inside********");
		//Change Status
		updateCSStatus("Cancelled");
		CostsheetInternal.verifyCSStatus("Cancelled",csSG4Approved);

		// close the open cost sheet
		closeTheOpenCostSheet();
		
		log.info("*************Cancel the Seasonal Review Approved Cost Sheet**********************************");

		//Click on Cost Sheet List tab
		clickOnCostSheetListTab();
	
		// search SG4 Approved cost sheet name in web table
		searchandClickforRequriedCostsheet(csSeasonalReviewApproved);
		CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		log.info("**********Inside********");
		//Change Status
		updateCSStatus("Cancelled");
		CostsheetInternal.verifyCSStatus("Cancelled",csSeasonalReviewApproved);

		// close the open cost sheet
		closeTheOpenCostSheet();

		}
		catch(Exception e){
			fail=true;
			log.error("Exception in pc76cancelRetailCostSheet()", e);
			throw e;
		}
	}
	
	
	public static void pc77cancelProductCostSheet(String[] data) throws Exception{		
		try{
		if(driver.findElements(tabCostsheet).size() == 0) {
			CommonProjectFunctions.searchProduct(data[6]);
			
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
		//Wait for details tab
		CommonFunctions.waitForVisibilityOfElement(productDetailstab);
		
			//Click on Sourcing
			CommonProjectFunctions.clickSourcingTab(data[7]);
			CommonProjectFunctions.clickCostingTab();
				strSource=AddSource(data);
				strSpec=InternalBOMSoftG.AddSpecification(data);
				strCW=InternalBOMSoftG.AddColorway(data);
		}
		
		//Close the open Cost Sheet
	     closeTheOpenCostSheet();
		
		log.info("*************Cancel the Vendor Selection Quote Cost Sheet**********************************");
		//Click on Cost Sheet List tab
		clickOnCostSheetListTab();
	
		// search Vendor Selection Quote cost sheet name in web table
		searchandClickforRequriedCostsheet(csProductVendorSelectionQuote);
		CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		log.info("**********Inside********");
		//Change Status
		updateCSStatus("Cancelled");
		CostsheetInternal.verifyCSStatus("Cancelled",csProductVendorSelectionQuote);

		// close the open cost sheet
		closeTheOpenCostSheet();
		
		log.info("*************Cancel the FEP Approved Cost Sheet**********************************");

		//Click on Cost Sheet List tab
		clickOnCostSheetListTab();
	
		// search FEP Approved cost sheet name in web table
		searchandClickforRequriedCostsheet(csProductFEPApproved);
		CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		log.info("**********Inside********");
		//Change Status
		updateCSStatus("Cancelled");
		CostsheetInternal.verifyCSStatus("Cancelled",csProductFEPApproved);

		// close the open cost sheet
		closeTheOpenCostSheet();
		
		
		log.info("*************Cancel the SG4 Approved Cost Sheet**********************************");

		//Click on Cost Sheet List tab
		clickOnCostSheetListTab();
		
		// search SG4 Approved cost sheet name in web table
		searchandClickforRequriedCostsheet(csProductSG4Approved);
		CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		log.info("**********Inside********");
		//Change Status
		updateCSStatus("Cancelled");
		CostsheetInternal.verifyCSStatus("Cancelled",csProductSG4Approved);

		// close the open cost sheet
		closeTheOpenCostSheet();
		
		log.info("*************Cancel the Seasonal Review Approved Cost Sheet**********************************");

		//Click on Cost Sheet List tab
		clickOnCostSheetListTab();
		
		// search SG4 Approved cost sheet name in web table
		searchandClickforRequriedCostsheet(csProductSeasonalReviewApproved);
		CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
		log.info("**********Inside********");
		//Change Status
		updateCSStatus("Cancelled");
		CostsheetInternal.verifyCSStatus("Cancelled",csProductSeasonalReviewApproved);

		// close the open cost sheet
		closeTheOpenCostSheet();

		}
		catch(Exception e){
			fail=true;
			log.error("Exception in pc77cancelProductCostSheet()", e);
			throw e;
		}
	}
	public static String pc76createRetailSeasonalReviewApproved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
            strCSSeasonalReviewApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSSeasonalReviewApproved = strCSSeasonalReviewApproved.split("Actions:");
			csSeasonalReviewApproved = varCSSeasonalReviewApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			closeTheOpenCostSheet();
			log.info("Vendor Retailcostsheet in seasonalReviewApproved status is: "+csSeasonalReviewApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc76createRetailSeasonalReviewApproved()", e);
			throw e;
		}
		return csSeasonalReviewApproved;
	}
	
	public static String pc77createProductSeasonalReviewApproved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);

			strCSProductSeasonalReviewApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSSeasonalReviewApproved = strCSProductSeasonalReviewApproved.split("Actions:");
			csProductSeasonalReviewApproved = varCSSeasonalReviewApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			closeTheOpenCostSheet();
			log.info("Vendor Product costsheet in seasonalReviewApproved status is: "+csProductSeasonalReviewApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc77createProductSeasonalReviewApproved()", e);
		    throw e;
		}
		return csProductSeasonalReviewApproved;
	}
	
	public static String pc76createRetailFEPApproved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSFEPApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSFEPApproved = strCSFEPApproved.split("Actions:");
			csFEPApproved = varCSFEPApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			closeTheOpenCostSheet();
			log.info("Vendor Retail costsheet in FEPApproved status is: "+csFEPApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc76createRetailFEPApproved()", e);
			return "";
		}
		return csFEPApproved;
	}
	
	public static String pc77createProductFEPApproved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSProductFEPApproved = driver.findElement(csHeading).getText().substring(20);
			String []varCSFEPApproved = strCSProductFEPApproved.split("Actions:");
			csProductFEPApproved = varCSFEPApproved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			closeTheOpenCostSheet();
			log.info("Vendor Product costsheet in FEPApproved status is: "+csProductFEPApproved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc77createProductFEPApproved()", e);
			throw e;
		}
		return csProductFEPApproved;
	}
	
	public static boolean carryOver(String [] data) throws Exception{
		
		try{
			
			//Get the Window Handler of parent Window
			String parentWindow= driver.getWindowHandle();
			System.out.println("The ID of parent Window is: " + parentWindow);
			
			//Click on Actions>Carry Over Product
			CommonFunctions.selectFromDropDownByVisibleText(lineSheetAction, data[5]);		
			
			//Get the number of pop up Windows open
			Set<String> handles =driver.getWindowHandles();
			
			for (String handle1: driver.getWindowHandles()){
			
				System.out.println(handle1);
				driver.switchTo().window(handle1);				
			}
			
			//Select season
			CommonFunctions.selectFromDropDownByVisibleText(carryOverseason, data[7]);
			
			//Wait for visibility of search result
			CommonFunctions.waitForVisibilityOfElement(carryOverSearch);
			
			//Change the view to Global Line Plan
			CommonFunctions.selectFromDropDownByVisibleText(carryOverView, data[18]);
			
			
			//Click on Filter
			CommonFunctions.clickButtonOrLink(carryOverFilter, "Filter");			
			
			//Wait for Product number input to display
			CommonFunctions.waitForVisibilityOfElement(carryOverFiltervalue);
						
			CommonFunctions.selectFromDropDownByIndex(carryOverFiltervalue, 0);
			
			//Clear the product number input filed and enter the product number
			CommonFunctions.clearTextBox(carryOverProductinput, "Product Number Input");
			CommonFunctions.enterTextInTextbox(carryOverProductinput, data[6]);
			
			//Click on Run
			CommonFunctions.clickButtonOrLink(carryOverRunbtn, "Run button");
			
			//Wait for visibility of check box
			CommonFunctions.waitForVisibilityOfElement(carryOverSelectbtn);
			
			
			//Select the check box against required product
			CommonFunctions.clickButtonOrLink(By.xpath("//*[contains(text(),'"+data[6]+"')]//preceding::input[1]"), "check box");
			
			//Click on Select button
			CommonFunctions.clickButtonOrLink(carryOverSelectbtn, "select");		
			
			//Switch to Parent Window
			driver.switchTo().window(parentWindow);
			
			//Switch to default content
			driver.switchTo().defaultContent();
			
			//Switch to Content Frame
			driver.switchTo().frame("contentframe");
			
			//Wait for visibility of Line Sheet view
			CommonFunctions.waitForVisibilityOfElement(lineSheetView);
			
			return true;
		}
		
		catch(Exception e){			
		fail=true;
		log.error("Exception in createProductFEPApproved()", e);
		throw e;
			
		}
		
	}
	
	public static void m03CarryForwardProduct(String[] data) throws Exception{
		try{
		
			//switch to default frame
			driver.switchTo().defaultContent();

			// Switch to Sidebar Frame
			driver.switchTo().frame("sidebarframe");

			//Click on Site
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(Site, "Click on Site");
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			CommonFunctions.selectFromDropDownByVisibleText(mySeasonYear, data[13]);
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(lineSheet, "link", "Line Sheet");
			CommonFunctions.waitForPageLoaded();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			//Wait for Line sheet view to appear
			CommonFunctions.waitForVisibilityOfElement(lineSheetView);
			
			//Perform Carry Over product
			carryOver(data);
			
			//Search Product
			CommonProjectFunctions.searchProduct(data[6]);
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
		//Wait for details tab
		CommonFunctions.waitForVisibilityOfElement(productDetailstab);
						
		//Click on Details tab
		CommonFunctions.clickButtonOrLink(productDetailstab, "Product Details tab");
			
		//Wait for visibility of Season
		CommonFunctions.waitForVisibilityOfElement(seasonValue);
		
		//select season
		CommonFunctions.selectFromDropDownByVisibleText(seasonValue ,data[7]);
		
		//Wait for visibility of wave Forecast column 1
		CommonFunctions.waitForVisibilityOfElement(waveForecastcol1);
			
		//Verify that manual input wave forcast attribute and Wave forecast Requirement2 column1 attribute are correct 	
		waveRequirement2ColVal(data);
			
		//select season
		CommonFunctions.selectFromDropDownByVisibleText(seasonValue ,data[13]);
		
		//Wait for visibility of wave Forecast column 1
		CommonFunctions.waitForVisibilityOfElement(waveForecastcol1);	

		//Verify that manual input wave forcast attribute and Wave forecast Requirement2 column1 attribute are correct 	
		waveRequirement2ColVal1(data);

		}catch(Exception e){
			fail=true;
			log.error("Exception in m03CarryForwardProduct()", e);
			throw e;
		}
		}
	
	public static boolean waveRequirement2ColVal(String [] data){
		try{
			//Check that manually input wave forecast is present below pacific attribute
			String manuallInputWavewithouttrim= driver.findElement(manuallyInputWaveForecast).getText();
			
			//Trim the attribute
			String manuallInputWavewithtrim =manuallInputWavewithouttrim.trim();
			
			//Verify that Manually Input Wave Forecasts attribute is matching
			Assert.assertEquals(manuallInputWavewithtrim, "Manually Input Wave Forecasts");
			
			log.info("Manual input wave forecast attribute is displaying below pacific attribute");
			
			//Capture the value of manually Input Wave Forecast attribute Value
			String manuallInput = driver.findElement(manuallyInputWaveForecastValue).getText();
			
			//Verify that manually Input Wave Forecast attribute Value is Yes
			Assert.assertEquals(manuallInput, "Yes");
			log.info("Manual input wave forecast attribute is value is displaying as:" + manuallInput);
			
			//Get the value of wave forecast 2 table column 1 attributes
			String waveForecastcol1value = driver.findElement(waveForecastcol1).getText();
			String forecastQtycol1value = driver.findElement(forecastQtycol1).getText();
			String maxShipQtycol1value = driver.findElement(maxShipQtycol1).getText();
			String setQtycol1value = driver.findElement(setQtycol1).getText();
			String distributionQtycol1value = driver.findElement(distributionQtycol1).getText();

			//Verify that wave forecast attribute value for column1 are same as entered
			Assert.assertEquals(waveForecastcol1value, data[17]);
			Assert.assertEquals(forecastQtycol1value, data[14]);
			Assert.assertEquals(maxShipQtycol1value, data[15]);
			Assert.assertEquals(setQtycol1value, data[17]);
			Assert.assertEquals(distributionQtycol1value, data[16]);
			
			log.info("All the attribute value in Wave Requirement 2 table column 1 is matching as entered");
			
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in waveRequirement2ColVal()", e);	
		    throw e;
		}
		
	}
	
	public static boolean waveRequirement2ColVal1(String [] data){
		try{
			//Check that manually input wave forecast is present below pacific attribute
			String manuallInputWavewithouttrim= driver.findElement(manuallyInputWaveForecast).getText();
			
			//Trim the attribute
			String manuallInputWavewithtrim =manuallInputWavewithouttrim.trim();
			
			//Verify that Manually Input Wave Forecasts attribute is matching
			Assert.assertEquals(manuallInputWavewithtrim, "Manually Input Wave Forecasts");
			
			log.info("Manual input wave forecast attribute is displaying below pacific attribute");
			
			//Capture the value of manually Input Wave Forecast attribute Value
			String manuallInput = driver.findElement(manuallyInputWaveForecastValue).getText();
			
			//Verify that manually Input Wave Forecast attribute Value is Yes
			Assert.assertEquals(manuallInput, "Yes");
			log.info("Manual input wave forecast attribute is value is displaying as:" + manuallInput);
			
			//Get the value of wave forecast 2 table column 1 attributes
			String waveForecastcol1value = driver.findElement(waveForecastcol1).getText();
			String forecastQtycol1value = driver.findElement(forecastQtycol1).getText();
			String maxShipQtycol1value = driver.findElement(maxShipQtycol1).getText();
			String setQtycol1value = driver.findElement(setQtycol1).getText();
			String distributionQtycol1value = driver.findElement(distributionQtycol1).getText();

			//Verify that wave forecast attribute value for column1 are same as entered
			Assert.assertEquals(waveForecastcol1value, data[17]);
			Assert.assertEquals(forecastQtycol1value, data[17]);
			Assert.assertEquals(maxShipQtycol1value, data[17]);
			Assert.assertEquals(setQtycol1value, data[17]);
			Assert.assertEquals(distributionQtycol1value, data[17]);
			
			log.info("All the attribute value in Wave Requirement 2 table column 1 is matching as entered");
			
			return true;
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in waveRequirement2ColVal()", e);	
		    throw e;
		}
		
	}
	public static void m03EnterWaveReq2Table(String[] data) throws Exception{
		try{
			
			//Search Product
			CommonProjectFunctions.searchProduct(data[6]);
			//Switch to default frame
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");
			
			//Wait for details tab
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.titleIs("ViewProduct/Season: Information"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailstab));
						
			//Click on Details tab
			CommonFunctions.clickButtonOrLink(productDetailstab, "Product Details tab");
			wait.until(ExpectedConditions.titleIs("View Season Product Information"));
			CommonFunctions.waitForPageLoaded();
			//Wait for visibility of Season
			wait.until(ExpectedConditions.visibilityOfElementLocated(seasonValue));
			//Select season
			CommonFunctions.selectFromDropDownByVisibleText(seasonValue ,data[7]);
			CommonFunctions.waitForPageLoaded();
			//Edit the Wave Forecast Quantity table
			wait.until(ExpectedConditions.visibilityOfElementLocated(labelWaveRequirements2Edit));
			 CommonFunctions.clickButtonOrLink(labelWaveRequirements2Edit, "link", "Details");
			 wait.until(ExpectedConditions.titleIs("Edit Multi-Object Collection"));CommonFunctions.waitForPageLoaded();
			 //Wait for visibility of Hide/Show button
				wait.until(ExpectedConditions.visibilityOfElementLocated(wave2hidebutton));
		
			 
			 //Click on forecast quantity column1
				wait.until(ExpectedConditions.visibilityOfElementLocated(forecastQtycol1));
			 CommonFunctions.clickButtonOrLink(forecastQtycol1, "forecast Qty col1");
			 
			 //Enter value in forecast quantity column1
				wait.until(ExpectedConditions.visibilityOfElementLocated(forecastQtycol1input));
			 CommonFunctions.enterTextInTextbox(forecastQtycol1input, data[14]);			 
			 
			 //Click on max Ship Qty column1
				wait.until(ExpectedConditions.visibilityOfElementLocated(maxShipQtycol1));
			 CommonFunctions.clickButtonOrLink(maxShipQtycol1, "max Ship Qty");
			 
			 //Enter value in max Ship Qty column1
				wait.until(ExpectedConditions.visibilityOfElementLocated(maxShipQtycol1input));
			 CommonFunctions.enterTextInTextbox(maxShipQtycol1input, data[15]);
			 
			 //Click on distribution Qty column1
				wait.until(ExpectedConditions.visibilityOfElementLocated(distributionQtycol1));
			 CommonFunctions.clickButtonOrLink(distributionQtycol1, "distribution Qty");
			 
			 //Enter value in distribution Qty column1
				wait.until(ExpectedConditions.visibilityOfElementLocated(distributionQtycol1input));
			 CommonFunctions.enterTextInTextbox(distributionQtycol1input, data[16]);
			 
			 //Click on Done button
				wait.until(ExpectedConditions.visibilityOfElementLocated(doneWave2table));
			 CommonFunctions.clickButtonOrLink(doneWave2table, "Done");
			 
			//Wait for details tab
				wait.until(ExpectedConditions.titleIs("View Season Product Information"));
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailstab));
			

		}catch(Exception e){
			fail=true;
			log.error("Exception in m03EnterWaveReq2Table()", e);
			throw e;
		}
		}

	
	public static String pc76createRetailSG4Approved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSSG4Approved = driver.findElement(csHeading).getText().substring(20);
			String []varCSSG4Approved = strCSSG4Approved.split("Actions:");
			csSG4Approved = varCSSG4Approved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			closeTheOpenCostSheet();
			log.info("Vendor Retail costsheet in SG4Approved status is: "+csSG4Approved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc76createRetailSG4Approved()", e);
			throw e;
		}
		return csSG4Approved;
	}
	
	public static String pc77createProductSG4Approved(String[] data,String sStatus) throws Exception{
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strCSProductSG4Approved = driver.findElement(csHeading).getText().substring(20);
			String []varCSSG4Approved = strCSProductSG4Approved.split("Actions:");
			csProductSG4Approved = varCSSG4Approved[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			closeTheOpenCostSheet();
			log.info("Vendor Product costsheet in SG4Approved status is: "+csProductSG4Approved);

		}catch(Exception e){
			fail=true;
			log.error("Exception in pc77createProductSG4Approved()", e);
			throw e;
		}
		return csProductSG4Approved;
	}
	
	public static Boolean AddSourcingConfiguration(String[] data) throws Exception{
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Add Sourcing Configuration");

			//Supplier Selection
			CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
			//CommonFunctions.switchToChildWindow();
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			CommonFunctions.clickButtonOrLink(SourcingConfig.choose, "Supplier selected");
			driver.switchTo().window(parent);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			//	CommonFunctions.selectFromDropDownByVisibleText(manager, data[23]);
			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(sourcingLead, 1);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(sourcingHead, 1);
			// Management 
			//	CommonFunctions.selectFromDropDownByVisibleText(management, data[26]);
			//click on Create
			CommonFunctions.clickButtonOrLink(SourcingConfig.CreateSourcebtn, "btn", "Create Source");
			return true;
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in AddSourcingConfiguration()", e);
			throw e;
		}
	}
	
	public static String AddSource(String[] data) throws Exception{
		try{
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(selectSource));
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource =elementCountSource.size();
			//log.info("Number of supplier: " + countSource);
			if(countSource>2)
			{
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, data[8]);
				strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			else
			{
				AddSourcingConfiguration(data);
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, data[8]);
				strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			throw e;
		}
		return strSource;
	}
	
	public static boolean clickOnCostSheetListTab() throws Exception
	{
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(tabCostsheet));
			CommonFunctions.waitForPageLoaded();
			driver.findElement(tabCostsheet).click();   
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in clickOnCostSheetListTab()", e);
		    throw e;
		}
	}
	
	public static Boolean createCSInternal(String[] data) throws Exception{
		try{	
				//Search Product
				CommonProjectFunctions.searchProduct(data[6]);
				
				//Switch to default frame
				driver.switchTo().defaultContent();
				
				//Swtich to content frame
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.titleIs("ViewProduct/Season: Information"));
				CommonFunctions.waitForPageLoaded();
				//Wait for visibility of details tab
				CommonFunctions.waitForVisibilityOfElement(productDetailstab);
                //Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[7]);
			    CommonProjectFunctions.clickCostingTab();
				//Select source
			    wait.until(ExpectedConditions.visibilityOfElementLocated(selectSource));
				CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(selectSpecification));
				//SelectSpecification and colorways
				CommonFunctions.selectFromDropDownByVisibleText(selectSpecification, data[9]);
				strCW=InternalBOMSoftG.AddColorway(data);
	
			//Click on Cost Sheet List
			wait.until(ExpectedConditions.visibilityOfElementLocated(csCostSheetList));
		    CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(csCostSheetList, "tab", "Cost Sheet List");
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet"); 
            wait.until(ExpectedConditions.titleIs("Create Cost Sheet"));
            CommonFunctions.waitForPageLoaded();
			
			
			
			// select Product type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'Internal')]"),"link","Cost sheet type");
            CommonFunctions.waitForPageLoaded();
            wait.until(ExpectedConditions.visibilityOfElementLocated(btnSave));
			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(CostsheetTooling.lstcolorwayGroupOptions,0);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
			
			if(data[2].contains("Product")) {
				
				// select wave
				CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[12]);
			}
			// select QuoteCurrency
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			
			csProdInternal="InWork"+CommonFunctions.getRandomString(4);
			
			System.out.println("Cost Sheet Name is:" + csProdInternal);
			
			if(data[2].equalsIgnoreCase("ProductInternal") || (data[2].equalsIgnoreCase("RetailInternal"))) {
				CommonFunctions.enterTextInTextbox(csNameInternal, csProdInternal);
			}
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			wait.until(ExpectedConditions.titleIs("View Season Product Information"));
			CommonFunctions.waitForPageLoaded();

		}catch(Exception e){
			fail=true;
			log.error("Exception in createCSInternal()", e);
			throw e;
		}
		return true;
	}
	
	public static Boolean createCS(String[] data) throws Exception{
		try{
				CommonProjectFunctions.searchProduct(data[6]);
				//Switch to default frame
				driver.switchTo().defaultContent();
				
				//Switch to content frame
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.titleIs("ViewProduct/Season: Information"));
				CommonFunctions.waitForPageLoaded();
				//Click on Sourcing
				CommonProjectFunctions.clickSourcingTab(data[7]);
				CommonProjectFunctions.clickCostingTab();
				wait.until(ExpectedConditions.visibilityOfElementLocated(selectSource));
				CommonFunctions.selectFromDropDownByVisibleText(selectSource, data[8]);
				strSpec=InternalBOMSoftG.AddSpecification(data);
				strCW=InternalBOMSoftG.AddColorway(data);
				CommonFunctions.waitForPageLoaded();
				
			//Close the open cost sheet
			closeTheOpenCostSheet();
			
			//Wait for visibility of tab cost sheet
			CommonFunctions.waitForVisibilityOfElement(tabCostsheet);
				
			//Click onCost Sheet list		
			clickOnCostSheetListTab();
			
			CommonFunctions.waitForPageLoaded();	
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(lstcostingActions,"Create Cost Sheet");
		    wait.until(ExpectedConditions.titleIs("Create Cost Sheet"));
		    CommonFunctions.waitForPageLoaded();
			
			if(data[2].equalsIgnoreCase("RetailVendor")){
			
			//Select Vendor
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='Vendor']"),"link","Cost sheet type");
			wait.until(ExpectedConditions.titleIs("Create Cost Sheet"));
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnSave));
	}
			
			
			if(data[2].equalsIgnoreCase("RetailVendor")){
				
				// select colorwayGroupOptions
				CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,1);
				// click lnkAdd
				CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
				
				// select QuoteCurrency
				CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
				// click on Save button
				CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
				wait.until(ExpectedConditions.titleIs("View Season Product Information"));
				CommonFunctions.waitForPageLoaded();
			}
			else if((data[2].equalsIgnoreCase("ProductVendor"))){
			
			if (data[3].equalsIgnoreCase("pc77createProductVendorSelectionQuote")){
				// select colorwayGroupOptions
				CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,1);				
			}
			
			else if (data[3].equalsIgnoreCase("pc77createProductSG4Approved")){
				// select colorwayGroupOptions
				CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,2);				
			}
			
			else if (data[3].equalsIgnoreCase("pc77createProductFEPApproved")){
				// select colorwayGroupOptions
				CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,3);				
			}
			
			else if (data[3].equalsIgnoreCase("pc77createProductSeasonalReviewApproved")){
				// select colorwayGroupOptions
				CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,4);				
			}
			
			else {
				// select colorwayGroupOptions
				CommonFunctions.selectFromDropDownByIndex(lstcolorwayGroupOptions,0);					
			}
			
			if(!data[4].equalsIgnoreCase("Vendor")){
				// click lnkAdd
				CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
				CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[12]);
		
				
			}

		    wait.until(ExpectedConditions.visibilityOfElementLocated(lstQuoteCurrency));
			// select QuoteCurrency
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			wait.until(ExpectedConditions.titleIs("View Season Product Information"));
			CommonFunctions.waitForPageLoaded();
		}
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateVendorRetailItemCostSheet()", e);
			throw e;
		}
	}
	

	public static String pc76createRetailVendorSelectionQuote(String[] data,String sStatus)throws Exception {
		try{

			createCS(data);
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			strcsVendorSelectionQuote = driver.findElement(csHeading).getText().substring(20);
			String []varcsVendorSelectionQuote = strcsVendorSelectionQuote.split("Actions:");
			csVendorSelectionQuote = varcsVendorSelectionQuote[0].trim();
			//Update Status
			updateCSStatus(sStatus);
			// close the open cost sheet
			closeTheOpenCostSheet();
			log.info("Vendor Retail costsheet in Vendor Selection Quote status is: "+csVendorSelectionQuote);
	}
		catch(Exception e){
			fail=true;
			log.error("Exception in pc76createRetailVendorSelectionQuote()", e);
			throw e;
		}
		return csVendorSelectionQuote;
	}
	
	public static boolean updateCSStatus(String sStatus) throws Exception{
		try {
			CommonFunctions.waitForVisibilityOfElement(lstCostSheetAction);
			CommonFunctions.waitForPageLoaded();	
			CommonFunctions.selectFromDropDownByVisibleText(lstCostSheetAction,"Update");
			// update Status
			wait.until(ExpectedConditions.titleIs("Update Cost Sheet"));
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnSave));
			CommonFunctions.selectFromDropDownByVisibleText(lstCSStatus,sStatus);
			log.info("Updated status to: "+sStatus);
			CommonFunctions.waitForVisibilityOfElement(btnSave);
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
			wait.until(ExpectedConditions.titleIs("View Season Product Information"));
			CommonFunctions.waitForPageLoaded();
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in updateCSStatus()", e);
			throw e;
		}
	}

	@AfterMethod
	public void reporterdataSetResult(){
		if(CommonProjectFunctions.skip)
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			CommonProjectFunctions.isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "PASS");
		CommonProjectFunctions.skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSanityXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		CommonProjectFunctions.runmodes=Utility.getDataSetRunmodeTest(suiteSanityXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(CommonProjectFunctions.isTestPass){
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[] testDataTest(){
		return Utility.getData(suiteSanityXls, this.getClass().getSimpleName());
	}

}
