package com.hasbro.security;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

/**
 * @author sourabh.singh
 *
 */
public class Packaging extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	
	//Variable required till choose a Documents Type
	public static By libraryLink = By.id("librariesContentIcon");
	public static By retailDocLink = By.linkText("Retail Document");
	public static By retailDocHeading = By.xpath("//span[contains(text(),'Find: Retail Document')]");
	public static By newLink = By.linkText("New");
	public static By chooseaType = By.xpath("//td[contains(text(),'Choose a Type')]");
	
	//Variable for Packaging Specification Document - AssortMent / Solid
	public static By documentName= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By productNo = By.xpath("//a[contains(text(),'Product Number:')]");
	public static By productNumber = By.xpath("//*[@id='LCSPRODUCT_ptc_str_1']");
	public static By search= By.id("SearchButton1");
	public static By choose = By.xpath("//a[contains(text(),'choose')]");
	public static By season = By.xpath("//a[contains(text(),'Season:')]");
	public static By seasonNumber = By.xpath("//*[@id='LCSSEASON_ptc_str_2']");
	public static By wave = By.xpath("//td[contains(text(),'*Wave')]//following::select[1]");
	public static By ipSensitive= By.xpath("//td[contains(text(),'IP Sensitive')]//following::select[1]");
	public static By pageDescription = By.xpath("//td[contains(text(),'Description / Comments')]//following::textarea[1]");
	public static By designedForBrand = By.xpath("//td[contains(text(),'Designed for Brand')]//following::select[1]");
	public static By securedforBrand= By.xpath("//td[contains(text(),'Secured for Brand')]//following::select[1]");
	public static By URL= By.xpath("//td[contains(text(),'URL')]//following::input[1]");
	public static By pckgStatus= By.xpath("//td[contains(text(),'*Packaging Status')]//following::select[1]");
	// Ast/Solid Details
	public static By SecPackageStyle= By.xpath("//td[contains(text(),'*Secondary Package Style')]//following::select[1]");
	public static By ShipUnitStyle= By.xpath("//td[contains(text(),'*Shipping Unit Style')]//following::select[1]");
	public static By SecPackageSoldAs= By.xpath("//td[contains(text(),'*Secondary Package Sold As')]//following::select[1]");
	public static By ShipUnitSoldAs= By.xpath("//td[contains(text(),'*Shipping Unit Sold As')]//following::select[1]");
	public static By SecPckgPieces= By.xpath("//td[contains(text(),'*Secondary Package Pieces')]//following::input[1]");
	public static By ShipUnitPckgPics= By.xpath("//td[contains(text(),'*Shipping Unit Package Pieces')]//following::input[1]");
	public static By SecPckgDepthIN= By.xpath("//td[contains(text(),'*Secondary Package Depth IN')]//following::input[1]");
	public static By ShipUnitPckgDepthIN= By.xpath("//td[contains(text(),'*Shipping Unit Package Depth IN')]//following::input[1]");
	public static By SecPckgWidthIN= By.xpath("//td[contains(text(),'*Secondary Package Width IN')]//following::input[1]");
	public static By ShipUnitPckgWidthIN= By.xpath("//td[contains(text(),'*Shipping Unit Package Width IN')]//following::input[1]");
	public static By SecPckgHeightIN= By.xpath("//td[contains(text(),'*Secondary Package Height IN')]//following::input[1]");
	public static By ShipUnitPckgHeightIN= By.xpath("//td[contains(text(),'*Shipping Unit Package Height IN')]//following::input[1]");
	// Attachment and Create
	public static By primaryDocumentFile = By.name("primaryDocumentFile");
	public static By comments = By.name("primaryDocumentComment");
	public static By crateDocument= By.xpath("//a[text()='Create']");
	public static By checkIN= By.xpath("//a[text()='Save & Check In']");
	//Variable for Packaging Specification Document - Retail Type
	public static By RetPckgDepthIN= By.xpath("//td[contains(text(),'*Retail Package Depth IN')]//following::input[1]");
	public static By RetPckgStyle= By.xpath("//td[contains(text(),'*Retail Package Style')]//following::select[1]");
	public static By RetPckgWidthIN= By.xpath("//td[contains(text(),'*Retail Package Width IN')]//following::input[1]");
	public static By RetPckgPackType= By.xpath("//td[contains(text(),'*Retail Package Pack Type')]//following::select[1]");
	public static By RetPackHeightIN= By.xpath("//td[contains(text(),'*Retail Package Height IN')]//following::input[1]");
	//Variable for Packaging Specification Document - Bundle Pack Type
	public static By bndlPckPckgStatus= By.xpath("//td[contains(text(),'*Bundle Pack Packaging Status')]//following::select[1]");
	public static By BndlPckgDepthIN= By.xpath("//td[contains(text(),'*Bundle Package Depth IN')]//following::input[1]");
	public static By BndlPckgStyle= By.xpath("//td[contains(text(),'*Bundle Package Style')]//following::select[1]");
	public static By BndlPckgWidthIN= By.xpath("//td[contains(text(),'*Bundle Package Width IN')]//following::input[1]");
	public static By BndlPckgPackType= By.xpath("//td[contains(text(),'*Bundle Package Pack Type')]//following::select[1]");
	public static By BndlPckgHeightIN= By.xpath("//td[contains(text(),'*Bundle Package Height IN')]//following::input[1]");
	// Document Weight
	public static By description = By.xpath("//td[contains(text(),'Description')]//following::textarea[1]");
	public static By brand = By.xpath("//td[contains(text(),'Brand')]//following::select[1]");
	public static By weightStatus= By.xpath("//td[contains(text(),'*Weight Status')]//following::select[1]");
	//Document Weight - Assortment
	public static By secPckNetWtKG= By.xpath("//td[contains(text(),'*Secondary Package Net Weight KG')]//following::input[1]");
	public static By secPckGrsWtKG= By.xpath("//td[contains(text(),'*Secondary Package Gross Weight KG')]//following::input[1]");
	public static By shpUntNtWtKG= By.xpath("//td[contains(text(),'*Shipping Unit Net Weight KG')]//following::input[1]");
	public static By shpUtGrsWtKG= By.xpath("//td[contains(text(),'*Shipping Unit Gross Weight KG')]//following::input[1]");
	//Document Weight - Retail
	public static By rtlPckNtWtKG= By.xpath("//td[contains(text(),'*Retail Package Net Weight KG')]//following::input[1]");
	public static By rtlPckGrsWtKG= By.xpath("//td[contains(text(),'*Retail Package Gross Weight KG')]//following::input[1]");
	//Document Weight - Retail
	public static By bndlPckNtWtKG= By.xpath("//td[contains(text(),'*Bundle Pack Net Weight KG')]//following::input[1]");
	public static By bndlPckGrsWtKG= By.xpath("//td[contains(text(),'*Bundle Pack Gross Weight KG')]//following::input[1]");
	//Search Document for Packaging and Weight Document
	public static By searchName = By.xpath("//input[@id='LCSDOCUMENT_ptc_str_1']");
	
	//Variable used for Set/Change State
	//Xpath for dropdown on Set life cycle state page
	public static By editable_UpdateLifecycleState= By.id("lcstate");
	public static By documentDetails = By.xpath("//td[contains(text(),'Document Details:')]");
	public static By documentsAction = By.xpath("//select[contains(@onchange,'evalList(this)')]");
	public static By RO_DocumentsUpdateLifecycleState = By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By linkUpdate= By.xpath("//a[text()='Update']");
	
	// Read and Update Attribute
	public static By labelGeneralAttribute = By.xpath("//td[contains(text(),'General Attributes')]");
	public static By packagingStatus = By.xpath("//td[contains(text(),'Packaging Status:')]");
	public static By astAttDef = By.xpath("//td[contains(text(),'Assortment Attribute Definition:')]");
	public static By varient = By.xpath("//td[contains(text(),'Variant:')]");
	public static By varientView = By.xpath("//a[contains(@href,'hbProductColorways') and contains(text(),'View')]");
	public static By varientEdit = By.xpath("//a[contains(@href,'hbProductColorways') and contains(text(),'Edit')]");
	//Xpath for Packaging Approval for all types of Packages.
	public static By packagingApproval = By.xpath("//td[contains(text(),'Packaging Approvals:')]");
	public static By aSPackages= By.xpath("//td[contains(text(),'Approve Secondary Package')]//following::select[1]");//assortment- Approved
	public static By linkCancel= By.xpath("//a[text()='Cancel']");
	public static By aRPackages= By.xpath("//td[contains(text(),'Approve Retail Package')]//following::select[1]");//Retail- Approved
	public static By aBPackages= By.xpath("//td[contains(text(),'Approve Bundle Package')]//following::select[1]");//Bundle- Approved
	// Change Reason Attribute Details:
	public static By CR = By.xpath("//td[contains(text(),'Change Reason:')]");
	public static By CRView = By.xpath("//a[contains(@href,'hbChangeReason') and contains(text(),'View')]");
	public static By CREdit = By.xpath("//a[contains(@href,'hbChangeReason') and contains(text(),'Edit')]");
	//Secondary Package Change History - in Assortment/Solid
	public static By secPckgChgHistory = By.xpath("//td[contains(text(),'Secondary Package Change History:')]");
	public static By secPckgChgHistoryView = By.xpath("//a[contains(@href,'hbAssortmentSecondaryPackage') and contains(text(),'View')]");
	public static By secPckgChgHistoryEdit = By.xpath("//a[contains(@href,'hbAssortmentSecondaryPackage') and contains(text(),'Edit')]");
	//Shipping Package Change History: - In Assortment / Solid
	public static By shpPckgChgHistory = By.xpath("//td[contains(text(),'Shipping Package Change History:')]");
	public static By shpPckgChgHistoryView = By.xpath("//a[contains(@href,'hbAssortmentShippingPackaging') and contains(text(),'View')]");
	public static By shpPckgChgHistoryEdit = By.xpath("//a[contains(@href,'hbAssortmentShippingPackaging') and contains(text(),'Edit')]");
	//Packaging Item Definition: label - Retail Packaging Document
	public static By pckgItemDeflabel = By.xpath("//td[contains(text(),'Packaging Item Definition:')]");
	//Retail Packeging - Retail Package Change History: 
	public static By rtlPckgChgHistory = By.xpath("//td[contains(text(),'Retail Package Change History:')]");
	public static By rtlPckgChgHistoryView = By.xpath("//a[contains(@href,'hbRetailItemDefinition') and contains(text(),'View')]");
	public static By rtlPckgChgHistoryEdit = By.xpath("//a[contains(@href,'hbRetailItemDefinition') and contains(text(),'Edit')]");
	//Bundle-Pack  Packaging - Bundle-Pack Attribute Definition:
	public static By bndlPckAttDef = By.xpath("//td[contains(text(),'Bundle-Pack Attribute Definition:')]");
	public static By bndlPckgChgHistory = By.xpath("//td[contains(text(),'Bundle Package Change History:')]");
	public static By bndlPckgChgHistoryView = By.xpath("//a[contains(@href,'hbBundlePackage') and contains(text(),'View')]");
	public static By bndlPckgChgHistoryEdit = By.xpath("//a[contains(@href,'hbBundlePackage') and contains(text(),'Edit')]");
	// Weight Document Variables:
	public static By docAttLabel = By.xpath("//td[contains(text(),'Document Attributes:')]");
	public static By wtStatusLabel = By.xpath("//td[contains(text(),'Weight Status:')]");
	public static By astSldWeightLabel = By.xpath("//td[contains(text(),'Assortment / Solid Weights:')]");
	public static By colorwaysLabel = By.xpath("//td[contains(text(),'Colorways:')]");
	public static By colorwaysView = By.xpath("//a[contains(@href,'hbAssociatedColorways') and contains(text(),'View')]");
	public static By colorwaysEdit = By.xpath("//a[contains(@href,'hbAssociatedColorways') and contains(text(),'Edit')]");
	public static By secPckWtChgHisLabel = By.xpath("//td[contains(text(),'Secondary Package Weight Change History:')]");
	public static By secPckWtChgHisView = By.xpath("//a[contains(@href,'hbSecondaryPackageWeightChangeHistory') and contains(text(),'View')]");
	public static By shpUtWtChgHisLabel = By.xpath("//td[contains(text(),'Shipping Unit Weight Change History:')]");
	public static By shpUtWtChgHisView = By.xpath("//a[contains(@href,'hbShippingUnitWeightChangeHistory') and contains(text(),'View')]");
	public static By rtlItemWtLabel = By.xpath("//td[contains(text(),'Retail Item Weight:')]");
	public static By rtlPckWtChgHisLabel = By.xpath("//td[contains(text(),'Retail Package Weight Change History:')]");
	public static By rtlPckWtChgHisView = By.xpath("//a[contains(@href,'hbRetailPackageWeightChangeHistory') and contains(text(),'View')]");
	public static By bndlPckWeightLabel = By.xpath("//td[contains(text(),'Bundle Pack Weight:')]");
	public static By bndlPckWtChgHisLabel = By.xpath("//td[contains(text(),'Bundle Pack Weight Change History:')]");
	public static By bndlPckWtChgHisView = By.xpath("//a[contains(@href,'hbBundlePackWeightChangeHistory') and contains(text(),'View')]");
	
	
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	static String deletemsg = "This action will completely delete this object from the system.  Are you sure you want to do this?";

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcPackaging(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Packaging Security");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			log.info("col0 :" + data[0]);
			log.info("col2 :" + data[4]);
			log.info("col3 :" + data[5]);
			log.info("col4 :" + data[6]);
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
			//Create Material From Libraries
			if(data[5].equalsIgnoreCase("Create"))
			{ Create_Documents(data); }
			//Read view verification For General Attributes
			if(data[5].equalsIgnoreCase("GeneralAttirbutesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification For General Attributes
			if(data[5].equalsIgnoreCase("GeneralAttirbutesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			//Read view verification For Packaging Status Attributes
			if(data[5].equalsIgnoreCase("PackagingStatusRead_View"))
			{  verify_Pckg_WT_StatusReadView(data); }
			//Update Verification For Packaging Status Attributes
			if(data[5].equalsIgnoreCase("PackagingStatusUpdate"))
			{  verify_Pckg_WT_StatusUpdate(data); }
			//Read view verification For Assortment Attribute Definition
			if(data[5].equalsIgnoreCase("AssortAttRead_View"))
			{  verifyAssortAttReadView(data); }
			//Update Verification For Assortment Attribute Definition
			if(data[5].equalsIgnoreCase("AssortAttUpdate"))
			{  verifyAssortAttUpdate(data); }
			//Read view verification For Varient
			if(data[5].equalsIgnoreCase("VarientRead_View"))
			{  verifyVarientReadView(data); }
			//Update verification For Varient
			if(data[5].equalsIgnoreCase("VarientUpdate"))
			{  verifyVarientUpdate(data); }
			//Read view verification For Packaging Approvals
			if(data[5].equalsIgnoreCase("PackagingApprovalRead_View"))
			{  verifyPckgApprovalsReadView(data); }
			//Update verification For Packaging Approvals
			if(data[5].equalsIgnoreCase("PackagingApprovalUpdate"))
			{  verifyPckgApprovalsUpdate(data); }
			//Read view verification For Change Reason
			if(data[5].equalsIgnoreCase("ChangeReasonRead_View"))
			{  verifyCRReadView(data); }
			//Update Verification For Change Reason
			if(data[5].equalsIgnoreCase("ChangeReasonUpdate"))
			{  verifyCRUpdate(data); }
			//Read view verification For Secondary Package Change History
			if(data[5].equalsIgnoreCase("SecPckgChgHistoryRead_View"))
			{  verifySecPckgChgHistoryReadView(data); }
			//Update verification For Secondary Package Change History
			if(data[5].equalsIgnoreCase("SecPckgChgHistoryUpdate"))
			{  verifySecPckgChgHistoryUpdate(data); }
			//Read view verification For Shipping Package Change History
			if(data[5].equalsIgnoreCase("ShpPckgChgHistoryRead_View"))
			{  verifyShpPckgChgHistoryReadView(data); }
			//Update verification For Shipping Package Change History
			if(data[5].equalsIgnoreCase("ShpPckgChgHistoryUpdate"))
			{  verifyShpPckgChgHistoryUpdate(data); }
			//Read view verification For Packaging Item Definition
			if(data[5].equalsIgnoreCase("PckgItemDefRead_View"))
			{  verifyRetailPckgDefReadView(data); }
			//Update verification For Packaging Item Definition
			if(data[5].equalsIgnoreCase("PckgItemDefUpdate"))
			{  verifyRetailPckgDefUpdate(data); }
			//Read view verification For Retail Package Change History
			if(data[5].equalsIgnoreCase("RtlPckgChgHistoryRead_View"))
			{  verifyRtlPckgChgHistoryReadView(data); }
			//Update verification For Retail Package Change History
			if(data[5].equalsIgnoreCase("RtlPckgChgHistoryUpdate"))
			{  verifyRtlPckgChgHistoryUpdate(data); }
			//Read view verification For Bundle-Pack Attribute Definition:
			if(data[5].equalsIgnoreCase("BndlPckAttDefRead_View"))
			{  verifyBndlPckAttDefReadView(data); }
			//Update verification For Bundle-Pack Attribute Definition:
			if(data[5].equalsIgnoreCase("BndlPckAttDefUpdate"))
			{  verifyBndlPckAttDefUpdate(data); }
			//Read view verification For Bundle Package Change History:
			if(data[5].equalsIgnoreCase("BndlPckgChgHistoryRead_View"))
			{  verifyBndlPckgChgHistoryReadView(data); }
			//Update verification For Bundle Package Change History:
			if(data[5].equalsIgnoreCase("BndlPckgChgHistoryUpdate"))
			{  verifyBndlPckgChgHistoryUpdate(data); }
	// For Weight Document Related
			//Read view verification For Document Attributes
			if(data[5].equalsIgnoreCase("DocumentAttirbutesRead_View"))
			{  verifyDocumentAttributeReadView(data); }
			//Update verification For Document Attributes
			if(data[5].equalsIgnoreCase("DocumentAttirbutesUpdate"))
			{  verifyDocumentAttributesUpdate(data); }
			//Read view verification For Weight Status:
			if(data[5].equalsIgnoreCase("WeightStatusRead_View"))
			{  verifyWeightStatusReadView(data); }
			//Update verification For Weight Status:
			if(data[5].equalsIgnoreCase("WeightStatusUpdate"))
			{  verifyWeightStatusUpdate(data); }
			//Read view verification For Assortment/Solid Weight:
			if(data[5].equalsIgnoreCase("AstSolidWeightRead_View"))
			{  verifyAstSldWeightAttReadView(data); }
			//Update verification For Assortment/Solid Weight:
			if(data[5].equalsIgnoreCase("AstSolidWeightUpdate"))
			{  verifyAstSldWeightAttUpdate(data); }
			//Read view verification For Colorways:
			if(data[5].equalsIgnoreCase("ColorwaysRead_View"))
			{  verifyColorwaysReadView(data); }
			//Update verification For Colorways:
			if(data[5].equalsIgnoreCase("ColorwaysUpdate"))
			{  verifyColorwaysUpdate(data); }
			//Read view verification For Secondary Package Weight Change History: 
			if(data[5].equalsIgnoreCase("SecPckWtChgHisRead_View"))
			{  verifySecPckWtChgHisReadView(data); }
			//Read View verification For Shipping Unit Weight Change History: 
			if(data[5].equalsIgnoreCase("ShpUtWtChgHisRead_View"))
			{  verifyShpUtWtChgHisReadView(data); }
			//Read view verification For Retail Item Weight:
			if(data[5].equalsIgnoreCase("RetailItemWeightRead_View"))
			{  verifyRetailItemWeightReadView(data); }
			//Update verification For Retail Item Weight:
			if(data[5].equalsIgnoreCase("RetailItemWeightUpdate"))
			{  verifyRetailItemWeightUpdate(data); }
			//Read view verification For Retail Package Weight Change History: 
			if(data[5].equalsIgnoreCase("RtlPckWtChgHistoryRead_View"))
			{  verifyRtlPckgWtChgHisReadView(data); }
			//Read view verification For Bundle Pack Weight:
			if(data[5].equalsIgnoreCase("BundlePackWeightRead_View"))
			{  verifyBundlePackWeightReadView(data); }
			//Update verification For Bundle Pack Weight:
			if(data[5].equalsIgnoreCase("BundlePackWeightUpdate"))
			{  verifyBundlePackWeightUpdate(data); }
			//Read view verification For Bundle Pack Weight Change History: 
			if(data[5].equalsIgnoreCase("BndlPckWtChgHistoryRead_View"))
			{  verifyBndlPckgWtChgHisReadView(data); }
			//download
			if(data[5].equalsIgnoreCase("Download"))
			{ download_Document(data); }
			//SetState
			if(data[5].equalsIgnoreCase("SetState"))
			{ setState_Document(data); }
			//Delete Document
			if(data[5].equalsIgnoreCase("Delete"))
			{ delete_Document(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create Document
	public static boolean Create_Documents(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(libraryLink, "Link", "Library Link");
			//Click on Color link
			driver.findElement(retailDocLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(retailDocHeading));
			//boolean newBool = driver.findElement(newLink).isDisplayed();
			//Click On New
			CommonFunctions.clickButtonOrLink(newLink, "link", "New Link");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(chooseaType));
			By attType = By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']/following::a["+data[3]+"]");
			boolean newType=false;
			isPresent(attType, newType);
			if(data[5].contains("Create")&& data[6].equalsIgnoreCase("Yes")){
				//Click on new
				/*CommonFunctions.clickButtonOrLink(newLink, "link", "New Link");
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(chooseaType));*/
				//Click on Retail Document Type
				//CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']/following::a["+data[3]+"]"), "link", "Retail Document Type");
				CommonFunctions.clickButtonOrLink(attType, "link", "Retail Document Type");
				log.info(By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']/following::a["+data[3]+"]"));
				//a[contains(text(),'Packaging Specification')and @class='LABEL']/following::a[1]
				switch (data[4]) {
			    case "AssortmentSolidPS":
								    	fillDocumentmandat(data);
								    	fillDocumentmandatPS(data);
								    	fillAssortSolidPS(data);
										createDocument(data);
										break;
			    case "RetailItemPS":
								    	fillDocumentmandat(data);
								    	fillDocumentmandatPS(data);
								    	fillRetailPS(data);
										createDocument(data);
										break;
			    case "BundlePackPS":
								    	fillDocumentmandat(data);
								    	fillDocumentmandatPS(data);
								    	fillBundlePackPS(data);
										createDocument(data);
										break;
			    case "AssortmentSolidWD":
								    	fillDocumentmandat(data);
								    	fillDocumentmandatWD(data);
								    	fillAssortSolidWD(data);
								    	createDocument(data);
										break;
			    case "RetailItemWD":
			    						fillDocumentmandat(data);
			    						fillDocumentmandatWD(data);
			    						fillRetailWD(data);
			    						createDocument(data);
			    						break;
			    case "BundlePackWD":
								    	fillDocumentmandat(data);
								    	fillDocumentmandatWD(data);
								    	fillBundlePackWD(data);
										createDocument(data);
										break;
			    default:
								    	fail=true;
								    	log.info("Default is executed");
					}
				
			}
			else if(data[5].contains("Create")&& data[6].equalsIgnoreCase("No")){
				//Assert.assertFalse(newBool, "New Link not available");
				Assert.assertFalse(newType, "New Link not available");
				System.out.println("User dont have to Create the Document-----   Sourabh Singh");
			}
			else{
				log.info("Create is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_Documents()", e);
			return false;
		}
		return true;
	}

//Methods are used for fill the Required data for Creating Retail Documents for Various types of Documents
	
	private static void fillDocumentmandat(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			Date date = new Date();
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(documentName));
			CommonFunctions.enterTextInTextbox(documentName,data[7]+date.getTime());
			//Search for Product from POPUP Page
			CommonFunctions.clickButtonOrLink(productNo, "link", "Product");
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.enterTextInTextbox(productNumber, data[8]);
			CommonFunctions.clickButtonOrLink(search, "Search For Product Number");
			CommonFunctions.clickButtonOrLink(choose, "Product selected");
			driver.switchTo().window(parent);
			driver.switchTo().frame("contentframe");
			//Select IP Sensitive
			CommonFunctions.selectFromDropDownByVisibleText(ipSensitive, data[11]);
			//Send URL
			CommonFunctions.enterTextInTextbox(URL, data[15]);
			//Select Primary Document
			CommonFunctions.enterTextInTextbox(primaryDocumentFile, data[39]);
			//Send Comments
			CommonFunctions.enterTextInTextbox(comments, data[40]);
			} 
		   
			catch(Exception e){
				fail=true;
				log.error("Exception in fillDocumentmandat()", e);
			}
		}
	
	
	private static void fillDocumentmandatPS(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			//Search for Season from POPUP Page
			CommonFunctions.clickButtonOrLink(season, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			CommonFunctions.enterTextInTextbox(seasonNumber, data[9]);
			CommonFunctions.clickButtonOrLink(search, "Search For Season");
			CommonFunctions.clickButtonOrLink(choose, "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			//Send Page Description
			CommonFunctions.enterTextInTextbox(pageDescription, data[12]);
			//Select Designed For Brand
			CommonFunctions.selectFromDropDownByVisibleText(designedForBrand, data[13]);
			//Select Secured For Brand
			CommonFunctions.selectFromDropDownByVisibleText(securedforBrand, data[14]);
			if(data[4].equalsIgnoreCase("AssortmentSolidPS") || data[4].equalsIgnoreCase("RetailItemPS")){
			//Select Packaging Status
				CommonFunctions.selectFromDropDownByVisibleText(pckgStatus, data[16]); }
			if(data[4].equalsIgnoreCase("BundlePackPS")){
			//Select Bundle Pack Status
			    CommonFunctions.selectFromDropDownByVisibleText(bndlPckPckgStatus, data[16]); }
			} 
		   
			catch(Exception e){
				fail=true;
				log.error("Exception in fillDocumentmandatPS()", e);
			}
		}
	
	private static void fillDocumentmandatWD(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			//Send Page Description
			CommonFunctions.enterTextInTextbox(description, data[12]);
			//Select Designed For Brand
			CommonFunctions.selectFromDropDownByVisibleText(brand, data[13]);
			//Select Packaging Status
			CommonFunctions.selectFromDropDownByVisibleText(weightStatus, data[16]); 
			} 
		   
			catch(Exception e){
				fail=true;
				log.error("Exception in fillDocumentmandatWD()", e);
			}
		}
	
	private static void fillAssortSolidPS(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
				//Select Wave
				CommonFunctions.selectFromDropDownByVisibleText(wave, data[10]);
				//Select Secondary Package Style
				CommonFunctions.selectFromDropDownByVisibleText(SecPackageStyle, data[17]);
				//Select Shipping Unit Style
				CommonFunctions.selectFromDropDownByVisibleText(ShipUnitStyle, data[18]);
				//Select Secondary Package Sold As
				CommonFunctions.selectFromDropDownByVisibleText(SecPackageSoldAs, data[19]);
				//Select Shipping Unit Sold As
				CommonFunctions.selectFromDropDownByVisibleText(ShipUnitSoldAs, data[20]);
				//Select Secondary Package Pieces
				CommonFunctions.enterTextInTextbox(SecPckgPieces, data[21]);
				//Select Shipping Unit Package Pieces 
				CommonFunctions.enterTextInTextbox(ShipUnitPckgPics, data[22]);
				//Select Secondary Package Depth IN  
				CommonFunctions.enterTextInTextbox(SecPckgDepthIN, data[23]);
				//Select Shipping Unit Package Depth IN 
				CommonFunctions.enterTextInTextbox(ShipUnitPckgDepthIN, data[24]);
				//Select Secondary Package Width IN
				CommonFunctions.enterTextInTextbox(SecPckgWidthIN, data[25]);
				//Select Shipping Unit Package Width IN
				CommonFunctions.enterTextInTextbox(ShipUnitPckgWidthIN, data[26]);
				//Select Secondary Package Height IN
				CommonFunctions.enterTextInTextbox(SecPckgHeightIN, data[27]);
				//Select Shipping Unit Package Height IN
				CommonFunctions.enterTextInTextbox(ShipUnitPckgHeightIN, data[28]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillAssortSolidPS()", e);
			}
		}
	
	private static void fillAssortSolidWD(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
				//Select Wave
				CommonFunctions.selectFromDropDownByVisibleText(wave, data[10]);
				//Send Secondary Package Net Weight KG
				CommonFunctions.enterTextInTextbox(secPckNetWtKG, data[21]);
				//Send Secondary Package Gross Weight KG 
				CommonFunctions.enterTextInTextbox(secPckGrsWtKG, data[22]);
				//Send Shipping Unit Net Weight KG 
				CommonFunctions.enterTextInTextbox(shpUntNtWtKG, data[23]);
				//Send Shipping Unit Gross Weight KG 
				CommonFunctions.enterTextInTextbox(shpUtGrsWtKG, data[24]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillAssortSolidWD()", e);
			}
		}
	
	private static void fillRetailPS(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
				//Send RetPckgDepthIN 
				CommonFunctions.enterTextInTextbox(RetPckgDepthIN, data[29]);
				//Select RetPckgStyle
				CommonFunctions.selectFromDropDownByVisibleText(RetPckgStyle, data[30]);
				//Send RetPckgWidthIN
				CommonFunctions.enterTextInTextbox(RetPckgWidthIN, data[31]);
				//Select RetPckgPackType
				CommonFunctions.selectFromDropDownByVisibleText(RetPckgPackType, data[32]);
				//Send RetPackHeightIN
				CommonFunctions.enterTextInTextbox(RetPackHeightIN, data[33]);	
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillRetailPS()", e);
			}
		}
	
	private static void fillRetailWD(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
				//Send Retail Package Net Weight KG 
				CommonFunctions.enterTextInTextbox(rtlPckNtWtKG, data[29]);
				//Send Retail Package Gross Weight KG
				CommonFunctions.enterTextInTextbox(rtlPckGrsWtKG, data[31]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillRetailWD()", e);
			}
		}
	
	private static void fillBundlePackPS(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
				//Send BndlPckgDepthIN 
				CommonFunctions.enterTextInTextbox(BndlPckgDepthIN, data[34]);
				//Select BndlPckgStyle
				CommonFunctions.selectFromDropDownByVisibleText(BndlPckgStyle, data[35]);
				//Send BndlPckgWidthIN
				CommonFunctions.enterTextInTextbox(BndlPckgWidthIN, data[36]);
				//Select BndlPckgPackType
				CommonFunctions.selectFromDropDownByVisibleText(BndlPckgPackType, data[37]);
				//Send BndlPckgHeightIN
				CommonFunctions.enterTextInTextbox(BndlPckgHeightIN, data[38]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillBundlePackPS()", e);
			}
		}
	
	private static void fillBundlePackWD(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
				//Send Bundle Pack Net Weight KG 
				CommonFunctions.enterTextInTextbox(bndlPckNtWtKG, data[34]);
				//Send Bundle Pack Gross Weight KG
				CommonFunctions.enterTextInTextbox(bndlPckGrsWtKG, data[36]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillBundlePackWD()", e);
			}
		}
	
	private static void createDocument(String[] data) {
		// TODO Auto-generated method stub
		try{
			CommonFunctions.clickButtonOrLink(crateDocument, "btn", "Create Retail Documents");
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillImageData()", e);
			}
		}
	
//End of Methods for Create Documents Type	


	public static boolean setState_Document(String[] data) throws Exception{
		try{
			//Search The Document type
			searchDocument(data);
			// called only when type is either concept image or Specification Image Page
			//Get the old value of Lifecycle:
			String textRO_currentLifecycleState = driver.findElement(RO_DocumentsUpdateLifecycleState).getText();
			log.info("textRO_currentLifecycleState value is :"+textRO_currentLifecycleState);
			//Get the values fom Action Drop down
			String str = driver.findElement(documentsAction).getText();
			//log.info("STR is---------"+str);
			//Select the Action Dropdown
			CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
			//verification
			String valULCSAfterChange=selectUpdateLifecycleState(data);
			//Click on Update
			CommonFunctions.clickButtonOrLink(linkUpdate, "link", "Update");
			// called only when type is either concept image or Specification Image Page
			//Get the Lifecycle State Value
			String textRO_changedLifecycleState = driver.findElement(RO_DocumentsUpdateLifecycleState).getText();
			log.info("textRO_LifecycleState value is :"+textRO_changedLifecycleState);
			log.info("textRO_UpdateLifecycleState: "+textRO_changedLifecycleState);
			log.info("valULCSAfterChange: "+valULCSAfterChange);
			// If User is having Set State Permission
			if(data[5].contains("SetState")&& data[6].equalsIgnoreCase("Yes")){
				Assert.assertEquals(textRO_changedLifecycleState, valULCSAfterChange);
			}
			// If User is not having Set State Permission
			else if(data[5].contains("SetState")&& data[6].equalsIgnoreCase("No")){
				Assert.assertEquals(textRO_currentLifecycleState, textRO_changedLifecycleState);
			}
			else
				log.info("SetState or chageState is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in setState_Document()", e);
			return false;
		}
		return true;
	}
	
	public static boolean download_Document(String[] data) {
		// TODO Auto-generated method stub

		try{
			searchDocument(data);
			Thread.sleep(1000);
			//Download data details //*[@id='TBLT12525']/tbody/tr[2]/td[1]/a
			String downloadData = driver.findElement(By.xpath("//a[contains(text(),'"+data[39]+"')]")).getText();
			//String downloadData = driver.findElement(By.id("//a[contains(text(),'"+data[13]+"')]")).getText();
			System.out.println("downloadData--------------- :"+downloadData);
			//If User is having Download Permission
			if(data[5].contains("Download")&& data[6].equalsIgnoreCase("Yes")){
				System.out.println("Code is in Side of Download Yes");
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[39]+"')]"), "link", " Download Data");
				System.out.println("Download Started!!!");
				Assert.assertEquals(downloadData, data[39]);
				Thread.sleep(1000);
				System.out.println("Download Completed!!!");
			}
			else if(data[5].contains("Download")&& data[6].equalsIgnoreCase("No")){
				log.info("No Download access");
				Assert.assertEquals(downloadData, data[39]);
				System.out.println("Download Not completed as no access!!!");
			}
			else
				log.info("Download is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in download_Document()", e);
			return false;
		}
		return true;
	
	}

	public static boolean delete_Document(String[] data) throws Exception{
		try{
			searchDocument(data);
			//If User is having Delete Permission
			if(data[5].contains("Delete")&& data[6].equalsIgnoreCase("Yes")){
				CommonFunctions.enterTextInTextbox(documentsAction, data[42]);
				CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
				//Accept AletPopup
				Thread.sleep(3000);
				//Accept AletPopup
				String msg= driver.switchTo().alert().getText(); 
				log.info("msg is -------"+msg);
				driver.switchTo().alert().dismiss();
				//driver.switchTo().alert().accept();
				Assert.assertEquals(msg, deletemsg);
			/*	//CommonFunctions.handleAlertPopUp();
				String deleteMess =driver.findElement(deleteSucessful).getText();
				Assert.assertEquals(deleteMess,"Delete Successful");*/
			}
			else if(data[5].contains("Delete")&& data[6].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(documentsAction).getText();
				System.out.println(strPrimary.trim());
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
				Assert.assertFalse(val);
				/*Select select = new Select(driver.findElement(documentsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[42],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);*/
			}
			else
				log.info("Delete is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_Document()", e);
			return false;
		}
		return true;
	}
	//This funcion is to select Update Lifecycle State	
	public static String selectUpdateLifecycleState(String[] productData) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
				System.out.println("valULCS in Select Update Life Cycle method: "+valULCS);
			
						if(valULCS.contains("In Work")){
							//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
							CommonFunctions.selectFromDropDownByVisibleText(editable_UpdateLifecycleState, "Released");
						}
						else if(valULCS.contains("Released")){
							//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
							CommonFunctions.selectFromDropDownByVisibleText(editable_UpdateLifecycleState, "In Work");
						}
						
						else if ((productData[4].equalsIgnoreCase("AssortmentSolidPS") || productData[4].equalsIgnoreCase("RetailItemPS")
								|| productData[4].equalsIgnoreCase("BundlePackPS")) && (valULCS.contains("Under Review")) ) { 
							
								//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
								CommonFunctions.selectFromDropDownByVisibleText(editable_UpdateLifecycleState, "In Work");
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

	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{

			searchDocument(data);
			// called only when type is either concept image or Specification Image Page
			if(data[5].contains("GeneralAttirbutesRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					String GALabel=driver.findElement(labelGeneralAttribute).getText();
					log.info("General Attribute value is    :"+GALabel);
					Assert.assertEquals(GALabel, " General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					log.error("General Attributes label is Absent");
					fail=true;
				}
			}
			else if(data[5].contains("GeneralAttirbutesRead_View")&& data[6].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
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
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			if(data[5].contains("GeneralAttirbutesUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				
				CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
				//Send URL
				driver.findElement(URL).clear();
				CommonFunctions.enterTextInTextbox(URL, data[15]);
				System.out.println("Size of URL is   :"+driver.findElements(URL).size());
				Assert.assertEquals(driver.findElements(URL).size(), 1, "General Attribute are Editable");
				System.out.println("General attribute is updatable   :");
				CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
			}
			else if(data[5].contains("GeneralAttirbutesUpdate")&& data[6].equalsIgnoreCase("No")){
				
				String strPrimary =driver.findElement(documentsAction).getText();
				//System.out.println(strPrimary);
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
				Assert.assertFalse(val);
			}	
			else
			{
				log.info("For this General Attributes is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Packaging Status:Read_View
		public static boolean verify_Pckg_WT_StatusReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("PackagingStatusRead_View")&& data[6].equalsIgnoreCase("Yes")){
					if(driver.findElements(packagingStatus).size() != 0){
						String pckgStats=driver.findElement(packagingStatus).getText();
						log.info("Packaging Status value is    :"+pckgStats);
						Assert.assertEquals(pckgStats, " Packaging Status:");
						log.info("Packaging Status Attributes label is Present");
					}else{
						log.error("Packaging Status Attributes label is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("PackagingStatusRead_View")&& data[6].equalsIgnoreCase("No")){
					if(driver.findElements(packagingStatus).size() != 0){
						log.error("Packaging Status label is Present");
						fail=true;
					}else{
						log.info("Packaging Status Attirbutes label is Absent");
					}
				}
				else
				{
					log.info("For this Packaging Status is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verify_Pckg_WT_StatusReadView()", e);
				return false;
			}
			return true;
		}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Packaging Status://Update
	public static boolean verify_Pckg_WT_StatusUpdate(String[] data) throws Exception{
		try{
			if(data[5].contains("PackagingStatusUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				
				CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
				if(data[4].equalsIgnoreCase("AssortmentSolidPS") || data[4].equalsIgnoreCase("RetailItemPS")){
					//Select Packaging Status
						CommonFunctions.selectFromDropDownByVisibleText(pckgStatus, data[16]); 
						log.info("Size of pckgStatus is   :"+driver.findElements(pckgStatus).size());
						Assert.assertEquals(driver.findElements(pckgStatus).size(), 1, "Packaging Status Attribute are Editable");
						}
					if(data[4].equalsIgnoreCase("BundlePackPS")){
					//Select Bundle Pack Status
					    CommonFunctions.selectFromDropDownByVisibleText(bndlPckPckgStatus, data[16]);
					    System.out.println("Size of bndlPckPckgStatus is   :"+driver.findElements(bndlPckPckgStatus).size());
					    Assert.assertEquals(driver.findElements(bndlPckPckgStatus).size(), 1, "Packaging Status Attribute are Editable");
					    }
				log.info("Packaging Status/ Weight Status is updatable   :");
				CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
			}
			else if(data[5].contains("PackagingStatusUpdate")&& data[6].equalsIgnoreCase("No")){
				
				String strPrimary =driver.findElement(documentsAction).getText();
				log.info(strPrimary);
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
				Assert.assertFalse(val);
			}	
			else
			{
				log.info("For this Packaging Status is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verify_Pckg_WT_StatusUpdate()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Assortment Attribute Defination:Read_View
	public static boolean verifyAssortAttReadView(String[] data) throws Exception{
		try{

			searchDocument(data);
			// called only when type is either concept image or Specification Image Page
			if(data[5].contains("AssortAttRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(astAttDef).size() != 0){
					String AstAttDef=driver.findElement(astAttDef).getText();
					log.info("Assortment Attrinute Defination value is    :"+AstAttDef);
					Assert.assertEquals(AstAttDef, " Assortment Attribute Definition:");
					log.info("Assortment Attribute Definition label is Present");
				}else{
					log.error("Assortment Attribute Definition label is Absent");
					fail=true;
				}
			}
			else if(data[5].contains("AssortAttRead_View")&& data[6].equalsIgnoreCase("No")){
				if(driver.findElements(astAttDef).size() != 0){
					log.error("Assortment Attribute Definition label is Present");
					fail=true;
				}else{
					log.info("Assortment Attribute Definition: label is Absent");
				}
			}
			else
			{
				log.info("For this Assortment Attribute Definition is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyAssortAttReadView()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Assortment Attribute Defination://Update
	public static boolean verifyAssortAttUpdate(String[] data) throws Exception{
		try{
			if(data[5].contains("AssortAttUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				
				CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
				//Send Secondary Package Pieces
				driver.findElement(SecPckgPieces).clear();
				CommonFunctions.enterTextInTextbox(SecPckgPieces, data[21]);
				log.info("Size of Secondary Package Pieces is   :"+driver.findElements(SecPckgPieces).size());
				Assert.assertEquals(driver.findElements(SecPckgPieces).size(), 1, "General Attribute are Editable");
				log.info("Assortment Attribute Defination is updatable   :");
				CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
			}
			else if(data[5].contains("AssortAttUpdate")&& data[6].equalsIgnoreCase("No")){
				
				String strPrimary =driver.findElement(documentsAction).getText();
				//System.out.println(strPrimary);
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
				Assert.assertFalse(val);
			}	
			else
			{
				log.info("For this Assortment Attribute Defination is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyAssortAttUpdate()", e);
			return false;
		}
		return true;
	}

	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Varient :Read_View
	public static boolean verifyVarientReadView(String[] data) throws Exception{
		try{

			searchDocument(data);
			String varView = driver.findElement(varientView).getText();
			log.info("Varient View Value is    :"+varView);
			String varientLbl = driver.findElement(varient).getText();
			log.info("Varient Label Value is    :"+varientLbl);
			// called only when type is either concept image or Specification Image Page
			if(data[5].contains("VarientRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
				if((driver.findElements(varient).size() != 0) && varView.contains("View")){
					log.info("Varient Label value is    :"+varientLbl);
					Assert.assertEquals(varientLbl, " Variant:");
					log.info("Varient label View is Present");
				}else{
					log.error("Varient label View is Absent");
					fail=true;
				}
			}
			else if(data[5].contains("VarientRead_View")&& data[6].equalsIgnoreCase("No")){
				if((driver.findElements(varient).size() != 0) && varView.contains("View")){
					log.error("Varient label View is Present");
					fail=true;
				}else{
					log.info("Varient label View is Absent");
				}
			}
			else
			{
				log.info("For this Varient label is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyVarientReadView()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return	
	 * @throws Exception
	 */
	//Function consist scenario : Assortment Attribute Defination://Update
	public static boolean verifyVarientUpdate(String[] data) throws Exception{
		try{
			
			boolean ve=false;
			isPresent(varientEdit, ve);
			
			/*try {
				ve = driver.findElement(varientEdit).isDisplayed();
				ve = true;
			} catch (NoSuchElementException e) {
				ve = false;
			}*/
			
			
			//Boolean ve = driver.findElement(varientEdit).isDisplayed();
			if(data[5].contains("VarientUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				Assert.assertTrue(ve, "Varient is Editable");
				/*String str = driver.findElement(varientEdit).getText();
				log.info("STR Valuse is    :"+str);
				if((driver.findElements(varient).size() != 0) && str.contains("Edit")){
					log.info("Varient value is    :"+varient);
					Assert.assertEquals(varient, " Varient:");
					log.info("Varient is Editable");
				}else{
					log.error("Varient is not Editable");
					fail=true;
				}*/
			
			}
			else if(data[5].contains("VarientUpdate")&& data[6].equalsIgnoreCase("No")){
				Assert.assertFalse(ve, "Varient is Not Editable");

				/*String strPrimary =driver.findElement(documentsAction).getText();
				System.out.println(strPrimary);
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
				Assert.assertFalse(val);*/
			}	
			else
			{
				log.info("For this Varient is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyVarientUpdate()", e);
			return false;
		}
		return true;
	}


	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Packaging Approvals :Read_View
		public static boolean verifyPckgApprovalsReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("PackagingApprovalRead_View")&& data[6].equalsIgnoreCase("Yes")){
					if(driver.findElements(packagingApproval).size() != 0){
						String pckgApproval=driver.findElement(packagingApproval).getText();
						log.info("Packaging Approval value is    :"+pckgApproval);
						Assert.assertEquals(pckgApproval, " Packaging Approvals:");
						log.info("Packaging Approvals Attributes label is Present");
					}else{
						log.error("Packaging Approvals Attributes label is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("PackagingApprovalRead_View")&& data[6].equalsIgnoreCase("No")){
					if(driver.findElements(packagingApproval).size() != 0){
						log.error("Packaging Approvals label is Present");
						fail=true;
					}else{
						log.info("Packaging Approvals Attirbutes label is Absent");
					}
				}
				else
				{
					log.info("For this Packaging Approvals is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyPckgApprovalsReadView()", e);
				return false;
			}
			return true;
		}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Packaging Approvals://Update   || data[4].equalsIgnoreCase("RetailItemPS")
	public static boolean verifyPckgApprovalsUpdate(String[] data) throws Exception{
		try{
			if(data[5].contains("PackagingApprovalUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				
				CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
				if(data[4].equalsIgnoreCase("AssortmentSolidPS")){
					//Select Approve Secondary Package
						CommonFunctions.selectFromDropDownByVisibleText(aSPackages, "Approved"); 
						log.info("Size of pckgStatus is   :"+driver.findElements(aSPackages).size());
						Assert.assertEquals(driver.findElements(aSPackages).size(), 1, "Packaging Approvals Attribute are Editable for Assortment Solid");
						}
				if(data[4].equalsIgnoreCase("RetailItemPS")){
					//Select Approve Retail Package
						CommonFunctions.selectFromDropDownByVisibleText(aRPackages, "Approved"); 
						log.info("Size of pckgStatus is   :"+driver.findElements(aRPackages).size());
						Assert.assertEquals(driver.findElements(aRPackages).size(), 1, "Packaging Approvals Attribute are Editable for Retail");
						}
				if(data[4].equalsIgnoreCase("BundlePackPS")){
					//Select Approve Bundle Package
					    CommonFunctions.selectFromDropDownByVisibleText(aBPackages, "Approved");
					    System.out.println("Size of bndlPckPckgStatus is   :"+driver.findElements(aBPackages).size());
					    Assert.assertEquals(driver.findElements(aBPackages).size(), 1, "Packaging Status Attribute are Editable for Bundle Pack");
					    }
				log.info("Packaging Approvals is updatable   :");
				CommonFunctions.clickButtonOrLink(linkCancel, "link", " Cancel");
			}
			else if(data[5].contains("PackagingApprovalUpdate")&& data[6].equalsIgnoreCase("No")){
				
				String strPrimary =driver.findElement(documentsAction).getText();
				//log.info(strPrimary);
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
				Assert.assertFalse(val);
			}	
			else
			{
				log.info("For this Packaging Approvals is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyPckgApprovalsUpdate()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Change Reason :Read_View
	public static boolean verifyCRReadView(String[] data) throws Exception{
		try{

			searchDocument(data);
			String crView = driver.findElement(CRView).getText();
			log.info("CR Valuse is    :"+crView);
			String crLabel = driver.findElement(CR).getText();
			log.info("CR Valuse is    :"+crLabel);
			if(data[5].contains("ChangeReasonRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
				if((driver.findElements(CR).size() != 0) && crView.contains("View")){
					log.info("Change Reason value is    :"+crLabel);
					Assert.assertEquals(crLabel, " Change Reason:");
					log.info("Change Reason: View is Present");
				}else{
					log.error("Change Reason: View is Absent");
					fail=true;
				}
			}
			else if(data[5].contains("ChangeReasonRead_View")&& data[6].equalsIgnoreCase("No")){
				if((driver.findElements(CR).size() != 0) && crView.contains("View")){
					log.error("Change Reason: View is Present");
					fail=true;
				}else{
					log.info("Change Reason: View is Absent");
					
				}
			}
			else
			{
				log.info("For this Change Reason: View is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyCRReadView()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return	
	 * @throws Exception
	 */
	//Function consist scenario : Change Reason Edit //Update
	public static boolean verifyCRUpdate(String[] data) throws Exception{
		try{
			boolean crEdit=false;
			isPresent(CREdit, crEdit);
			
			//Boolean crEdit = driver.findElement(CREdit).isDisplayed();
			if(data[5].contains("ChangeReasonUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				Assert.assertTrue(crEdit, "Change Reason is Editable");
			}
			else if(data[5].contains("ChangeReasonUpdate")&& data[6].equalsIgnoreCase("No")){
				Assert.assertFalse(crEdit, "Change Reason is Not Editable");
			}	
			else
			{
				log.info("For this Varient is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyVarientUpdate()", e);
			return false;
		}
		return true;
	}


	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Secondary Package Change History :Read_View
	public static boolean verifySecPckgChgHistoryReadView(String[] data) throws Exception{
		try{

			searchDocument(data);
			//Store View in Variable
			String sPCHView = driver.findElement(secPckgChgHistoryView).getText();
			log.info("secPckgChgHistoryView Valuse is    :"+sPCHView);
			
			//Store Secondary Package Change History: in Variable
			String sPCHLabel = driver.findElement(secPckgChgHistory).getText();
			log.info("secPckgChgHistoryView Valuse is    :"+sPCHLabel);
			//Condition Checking if Yes
			if(data[5].contains("SecPckgChgHistoryRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
				if((driver.findElements(secPckgChgHistory).size() != 0) && sPCHView.contains("View")){
					log.info("Secondary Package Change History value is    :"+sPCHLabel);
					Assert.assertEquals(sPCHLabel, " Secondary Package Change History:");
					log.info("Secondary Package Change History: View is Present");
				}else{
					log.error("Secondary Package Change History: View is Absent");
					fail=true;
				}
			}
			//Condition Checking if No
			else if(data[5].contains("SecPckgChgHistoryRead_View")&& data[6].equalsIgnoreCase("No")){
				if((driver.findElements(secPckgChgHistory).size() != 0) && sPCHView.contains("View")){
					log.error("Secondary Package Change History: View is Present");
					fail=true;
				}else{
					log.info("Secondary Package Change History: View is Absent");
				}
			}
			else
			{
				log.info("For this Secondary Package Change History: View is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySecPckgChgHistoryReadView()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return	
	 * @throws Exception
	 */
	//Function consist scenario : Secondary Package Change History Edit //Update
	public static boolean verifySecPckgChgHistoryUpdate(String[] data) throws Exception{
		try{
			boolean sPCHEdit=false;
			isPresent(secPckgChgHistoryEdit, sPCHEdit);
			
			//Boolean sPCHEdit = driver.findElement(secPckgChgHistoryEdit).isDisplayed();
			if(data[5].contains("SecPckgChgHistoryUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				Assert.assertTrue(sPCHEdit, "Secondary Package Change History is Editable");
			}
			else if(data[5].contains("SecPckgChgHistoryUpdate")&& data[6].equalsIgnoreCase("No")){
				Assert.assertFalse(sPCHEdit, "Secondary Package Change History is Not Editable");
			}	
			else
			{
				log.info("For this Secondary Package Change History is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySecPckgChgHistoryUpdate()", e);
			return false;
		}
		return true;
	}

	

	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Shipping Package Change History: Read_View
	public static boolean verifyShpPckgChgHistoryReadView(String[] data) throws Exception{
		try{

			searchDocument(data);
			//Store View in Variable
			String shpPCHView = driver.findElement(shpPckgChgHistoryView).getText();
			log.info("shpPckgChgHistoryView Valuse is    :"+shpPCHView);
			
			//Store Shipping Package Change History: in Variable
			String shpPCHLabel = driver.findElement(shpPckgChgHistory).getText();
			log.info("Shipping Package Change History Valuse is    :"+shpPCHLabel);
			
			//Condition Checking if Yes
			if(data[5].contains("ShpPckgChgHistoryRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
				if((driver.findElements(shpPckgChgHistory).size() != 0) && shpPCHView.contains("View")){
					log.info("Shipping Package Change History value is    :"+shpPCHLabel);
					Assert.assertEquals(shpPCHLabel, " Shipping Package Change History:");
					log.info("Shipping Package Change History: View is Present");
				}else{
					log.error("Shipping Package Change History: View is Absent");
					fail=true;
				}
			}
			//Condition Checking if No
			else if(data[5].contains("ShpPckgChgHistoryRead_View")&& data[6].equalsIgnoreCase("No")){
				if((driver.findElements(shpPckgChgHistory).size() != 0) && shpPCHView.contains("View")){
					log.error("Shipping Package Change History: View is Present");
					fail=true;
				}else{
					log.info("Shipping Package Change History: View is Absent");
				}
			}
			else
			{
				log.info("For this Shipping Package Change History: View is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyShpPckgChgHistoryReadView()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return	
	 * @throws Exception
	 */
	//Function consist scenario : Shipping Package Change History: Edit //Update
	public static boolean verifyShpPckgChgHistoryUpdate(String[] data) throws Exception{
		try{
			boolean shpPCHEdit=false;
			isPresent(shpPckgChgHistoryEdit, shpPCHEdit);
			//Boolean shpPCHEdit = driver.findElement(shpPckgChgHistoryEdit).isDisplayed();
			if(data[5].contains("ShpPckgChgHistoryUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				Assert.assertTrue(shpPCHEdit, "Shipping Package Change History is Editable");
			}
			else if(data[5].contains("ShpPckgChgHistoryUpdate")&& data[6].equalsIgnoreCase("No")){
				Assert.assertFalse(shpPCHEdit, "Shipping Package Change History is Not Editable");
			}	
			else
			{
				log.info("For this Shipping Package Change History is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyShpPckgChgHistoryUpdate()", e);
			return false;
		}
		return true;
	}
	
// For Retail Packaging Item Definition 
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Packaging Item Definition :Read_View
	public static boolean verifyRetailPckgDefReadView(String[] data) throws Exception{
		try{
			searchDocument(data);
			if(data[5].contains("PckgItemDefRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(pckgItemDeflabel).size() != 0){
					String pckgItemDef=driver.findElement(pckgItemDeflabel).getText();
					log.info("Retail Item Definition value is    :"+pckgItemDef);
					Assert.assertEquals(pckgItemDef, " Packaging Item Definition:");
					log.info("Packaging Item Definition label is Present");
				}else{
					log.error("Packaging Item Definition label is Absent");
					fail=true;
				}
			}
			else if(data[5].contains("PckgItemDefRead_View")&& data[6].equalsIgnoreCase("No")){
				if(driver.findElements(pckgItemDeflabel).size() != 0){
					log.error("Packaging Item Definition label is Present");
					fail=true;
				}else{
					log.info("Packaging Item Definition: label is Absent");
				}
			}
			else
			{
				log.info("For this Packaging Item Definition is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyRetailPckgDefReadView()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Retail/Packaging Item Definition://Update
	public static boolean verifyRetailPckgDefUpdate(String[] data) throws Exception{
		try{
			if(data[5].contains("PckgItemDefUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				//Click on Action Drop down For Update the Packaging Specification Document
				CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
				//Clear Retail Package Depth IN field
				driver.findElement(RetPckgDepthIN).clear();
				//Send Retail Package Depth IN 
				CommonFunctions.enterTextInTextbox(RetPckgDepthIN, data[29]);
				log.info("Size of Retail Package Depth IN is   :"+driver.findElements(RetPckgDepthIN).size());
				Assert.assertEquals(driver.findElements(RetPckgDepthIN).size(), 1, "Retail/Packaging Item Definition are Editable");
				log.info("Retail/Packaging Item Definition: is updatable   :");
				CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
			}
			else if(data[5].contains("PckgItemDefUpdate")&& data[6].equalsIgnoreCase("No")){
				
				String strPrimary =driver.findElement(documentsAction).getText();
				//System.out.println(strPrimary);
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
				Assert.assertFalse(val);
			}	
			else
			{
				log.info("For this Retail/Packaging Item Definition is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyRetailPckgDefUpdate()", e);
			return false;
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	//Function consist scenario : Retail Package Change History: Read_View
	public static boolean verifyRtlPckgChgHistoryReadView(String[] data) throws Exception{
		try{

			searchDocument(data);
			//Store View in Variable
			String rtlPCHView = driver.findElement(rtlPckgChgHistoryView).getText();
			log.info("Retail Package Change History: View Valuse is    :"+rtlPCHView);
			
			//Store Retail Package Change History: in Variable
			String rtlPCHLabel = driver.findElement(rtlPckgChgHistory).getText();
			log.info("Retail Package Change History Label Value is    :"+rtlPCHLabel);
			
			//Condition Checking if Yes
			if(data[5].contains("RtlPckgChgHistoryRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
				if((driver.findElements(rtlPckgChgHistory).size() != 0) && rtlPCHView.contains("View")){
					log.info("Retail Package Change History value is    :"+rtlPCHLabel);
					Assert.assertEquals(rtlPCHLabel, " Retail Package Change History:");
					log.info("Retail Package Change History: View is Present");
				}else{
					log.error("Retail Package Change History: View is Absent");
					fail=true;
				}
			}
			//Condition Checking if No
			else if(data[5].contains("RtlPckgChgHistoryRead_View")&& data[6].equalsIgnoreCase("No")){
				if((driver.findElements(rtlPckgChgHistory).size() != 0) && rtlPCHView.contains("View")){
					log.error("Retail Package Change History: View is Present");
					fail=true;
				}else{
					log.info("Retail Package Change History: View is Absent");
				}
			}
			else
			{
				log.info("For this Retail Package Change History: View is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyRtlPckgChgHistoryReadView()", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return	
	 * @throws Exception
	 */
	//Function consist scenario : Shipping Package Change History: Edit //Update
	public static boolean verifyRtlPckgChgHistoryUpdate(String[] data) throws Exception{
		try{
			boolean rtlPCHEdit=false;
			isPresent(rtlPckgChgHistoryEdit, rtlPCHEdit);
			//Boolean rtlPCHEdit = driver.findElement(rtlPckgChgHistoryEdit).isDisplayed();
			if(data[5].contains("RtlPckgChgHistoryUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
				Assert.assertTrue(rtlPCHEdit, "Retail Package Change History is Editable");
			}
			else if(data[5].contains("RtlPckgChgHistoryUpdate")&& data[6].equalsIgnoreCase("No")){
				Assert.assertFalse(rtlPCHEdit, "Retail Package Change History is Not Editable");
			}	
			else
			{
				log.info("For this Retail Package Change History is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyRtlPckgChgHistoryUpdate()", e);
			return false;
		}
		return true;
	}


		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Bundle-Pack Attribute Definition: Read_View
		public static boolean verifyBndlPckAttDefReadView(String[] data) throws Exception{
			try{
				searchDocument(data);
				if(data[5].contains("BndlPckAttDefRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(bndlPckAttDef).size() != 0){
						String bndlAttLabel=driver.findElement(bndlPckAttDef).getText();
						log.info("Bundle-Pack Attribute Definition: value is    :"+bndlAttLabel);
						Assert.assertEquals(bndlAttLabel, " Bundle-Pack Attribute Definition:");
						log.info("Bundle-Pack Attribute Definition: label is Present");
					}else{
						log.error("Bundle-Pack Attribute Definition: label is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("BndlPckAttDefRead_View")&& data[6].equalsIgnoreCase("No")){
					if(driver.findElements(bndlPckAttDef).size() != 0){
						log.error("Bundle-Pack Attribute Definition label is Present");
						fail=true;
					}else{
						log.info("Bundle-Pack Attribute Definition label is Absent");
					}
				}
				else
				{
					log.info("For this Bundle-Pack Attribute Definition is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyBndlPckAttDefReadView()", e);
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Bundle-Pack Attribute Definition: //Update
		public static boolean verifyBndlPckAttDefUpdate(String[] data) throws Exception{
			try{
				if(data[5].contains("BndlPckAttDefUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
					//Click on Action Drop down For Update the Packaging Specification Document
					CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
					//Clear Bundle Package Depth IN field
					driver.findElement(BndlPckgDepthIN).clear();
					//Send BndlPckgDepthIN 
					CommonFunctions.enterTextInTextbox(BndlPckgDepthIN, data[34]);
					log.info("Size of Bundle Package Depth IN is   :"+driver.findElements(BndlPckgDepthIN).size());
					Assert.assertEquals(driver.findElements(BndlPckgDepthIN).size(), 1, "Bundle-Pack Attribute Definition are Editable");
					log.info("Bundle-Pack Attribute Definition is updatable   :");
					CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
				}
				else if(data[5].contains("BndlPckAttDefUpdate")&& data[6].equalsIgnoreCase("No")){
					
					String strPrimary =driver.findElement(documentsAction).getText();
					//System.out.println(strPrimary);
					boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
					Assert.assertFalse(val);
				}	
				else
				{
					log.info("For this Retail/Packaging Item Definition is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyRetailPckgDefUpdate()", e);
				return false;
			}
			return true;
		}
		
	
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Bundle Package Change History: Read_View
		public static boolean verifyBndlPckgChgHistoryReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				//Store View in Variable
				String bndlPCHView = driver.findElement(bndlPckgChgHistoryView).getText();
				log.info("Bundle Package Change History: View Value is    :"+bndlPCHView);
				
				//Store Bundle Package Change History: in Variable
				String bndlPCHLabel = driver.findElement(bndlPckgChgHistory).getText();
				log.info("Bundle Package Change History Label Value is    :"+bndlPCHLabel);
				
				//Condition Checking if Yes
				if(data[5].contains("BndlPckgChgHistoryRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if((driver.findElements(bndlPckgChgHistory).size() != 0) && bndlPCHView.contains("View")){
						log.info("Retail Package Change History value is    :"+bndlPCHLabel);
						Assert.assertEquals(bndlPCHLabel, " Bundle Package Change History:");
						log.info("Bundle Package Change History: View is Present");
					}else{
						log.error("Bundle Package Change History: View is Absent");
						fail=true;
					}
				}
				//Condition Checking if No
				else if(data[5].contains("BndlPckgChgHistoryRead_View")&& data[6].equalsIgnoreCase("No")){
					if((driver.findElements(bndlPckgChgHistory).size() != 0) && bndlPCHView.contains("View")){
						log.error("Bundle Package Change History: View is Present");
						fail=true;
					}else{
						log.info("Bundle Package Change History: View is Absent");
					}
				}
				else
				{
					log.info("For this Bundle Package Change History: View is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyBndlPckgChgHistoryReadView()", e);
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @param data
		 * @return	
		 * @throws Exception
		 */
		//Function consist scenario : Bundle Package Change History: Edit //Update
		public static boolean verifyBndlPckgChgHistoryUpdate(String[] data) throws Exception{
			try{
				boolean bndlPCHEdit=false;
				isPresent(bndlPckgChgHistoryEdit, bndlPCHEdit);
				//Boolean bndlPCHEdit = driver.findElement(bndlPckgChgHistoryEdit).isDisplayed();
				if(data[5].contains("BndlPckgChgHistoryUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
					Assert.assertTrue(bndlPCHEdit, "Bundle Package Change History is Editable");
				}
				else if(data[5].contains("RtlPckgChgHistoryUpdate")&& data[6].equalsIgnoreCase("No")){
					Assert.assertFalse(bndlPCHEdit, "Bundle Package Change History is Not Editable");
				}	
				else
				{
					log.info("For this Bundle Package Change History is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyBndlPckgChgHistoryUpdate()", e);
				return false;
			}
			return true;
		}

//Weight Document Related Methods
		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Document Attributes:Read_View
		public static boolean verifyDocumentAttributeReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("DocumentAttirbutesRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(docAttLabel).size() != 0){
						String DALabel=driver.findElement(docAttLabel).getText();
						log.info("Document Attribute value is    :"+DALabel);
						Assert.assertEquals(DALabel, " Document Attributes:");
						log.info("Document Attributes label is Present");
					}else{
						log.error("Document Attributes label is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("DocumentAttirbutesRead_View")&& data[6].equalsIgnoreCase("No")){
					if(driver.findElements(docAttLabel).size() != 0){
						log.error("Document Attirbutes label is Present");
						fail=true;
					}else{
						log.info("Document Attirbutes label is Absent");
					}
				}
				else
				{
					log.info("For this Document Attributes is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyDocumentAttributesReadView()", e);
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Document Attributes://Update
		public static boolean verifyDocumentAttributesUpdate(String[] data) throws Exception{
			try{
				if(data[5].contains("DocumentAttirbutesUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
					
					CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
					//Send URL
					driver.findElement(URL).clear();
					CommonFunctions.enterTextInTextbox(URL, data[15]);
					System.out.println("Size of URL is   :"+driver.findElements(URL).size());
					Assert.assertEquals(driver.findElements(URL).size(), 1, "Document Attribute are Editable");
					System.out.println("Document attribute is updatable   :");
					CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
				}
				else if(data[5].contains("DocumentAttirbutesUpdate")&& data[6].equalsIgnoreCase("No")){
					
					String strPrimary =driver.findElement(documentsAction).getText();
					//System.out.println(strPrimary);
					boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
					Assert.assertFalse(val);
				}	
				else
				{
					log.info("For this Document Attributes is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyDocumentAttributesUpdate()", e);
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Weight Status:Read_View
			public static boolean verifyWeightStatusReadView(String[] data) throws Exception{
				try{

					searchDocument(data);
					if(data[5].contains("WeightStatusRead_View")&& data[6].equalsIgnoreCase("Yes")){
						if(driver.findElements(wtStatusLabel).size() != 0){
							String wtStats=driver.findElement(wtStatusLabel).getText();
							log.info("Weight Status value is    :"+wtStats);
							Assert.assertEquals(wtStats, " Weight Status:");
							log.info("Weight Status Attributes label is Present");
						}else{
							log.error("Weight Status Attributes label is Absent");
							fail=true;
						}
					}
					else if(data[5].contains("WeightStatusRead_View")&& data[6].equalsIgnoreCase("No")){
						if(driver.findElements(wtStatusLabel).size() != 0){
							log.error("Weight Status label is Present");
							fail=true;
						}else{
							log.info("Weight Status Attirbutes label is Absent");
						}
					}
					else
					{
						log.info("For this Weight Status is not applicable(NA)");
					}
				}catch(Exception e){
					fail=true;
					log.error("Exception in verifyWeightStatusReadView()", e);
					return false;
				}
				return true;
			}
		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Packaging Status://Update
		public static boolean verifyWeightStatusUpdate(String[] data) throws Exception{
			try{
				if(data[5].contains("WeightStatusUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
					
					CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
					//Select Weight Status 
					CommonFunctions.selectFromDropDownByVisibleText(weightStatus, data[16]); 
				    System.out.println("Size of Weight Status is   :"+driver.findElements(weightStatus).size());
				    Assert.assertEquals(driver.findElements(weightStatus).size(), 1, "Weight Status Attribute are Editable");
					log.info("Weight Status is updatable   :");
					CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
				}
				else if(data[5].contains("WeightStatusUpdate")&& data[6].equalsIgnoreCase("No")){
					
					String strPrimary =driver.findElement(documentsAction).getText();
					log.info(strPrimary);
					boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
					Assert.assertFalse(val);
				}	
				else
				{
					log.info("For this Packaging Status is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verify_Pckg_WT_StatusUpdate()", e);
				return false;
			}
			return true;
		}

		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Assortment/Solid Weight:Read_View
		public static boolean verifyAstSldWeightAttReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("AstSolidWeightRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(astSldWeightLabel).size() != 0){
						String AstSldWtDef=driver.findElement(astSldWeightLabel).getText();
						log.info("Assortment / Solid Weight  value is    :"+AstSldWtDef);
						Assert.assertEquals(AstSldWtDef, " Assortment / Solid Weights:");
						log.info("Assortment / Solid Weights: label is Present");
					}else{
						log.error("Assortment / Solid Weights: label is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("AstSolidWeightRead_View")&& data[6].equalsIgnoreCase("No")){
					if(driver.findElements(astSldWeightLabel).size() != 0){
						log.error("Assortment / Solid Weights: label is Present");
						fail=true;
					}else{
						log.info("Assortment / Solid Weights: label is Absent");
					}
				}
				else
				{
					log.info("For this Assortment / Solid Weights: is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyAstSldWeightAttReadView()", e);
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Assortment Attribute Defination://Update
		public static boolean verifyAstSldWeightAttUpdate(String[] data) throws Exception{
			try{
				if(data[5].contains("AstSolidWeightUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
					CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
					//Send Secondary Package Net Weight KG
					driver.findElement(secPckNetWtKG).clear();
					CommonFunctions.enterTextInTextbox(secPckNetWtKG, data[21]);
					//Send Secondary Package Gross Weight KG 
					driver.findElement(secPckGrsWtKG).clear();
					CommonFunctions.enterTextInTextbox(secPckGrsWtKG, data[22]);
					//Send Shipping Unit Net Weight KG 
					driver.findElement(shpUntNtWtKG).clear();
					CommonFunctions.enterTextInTextbox(shpUntNtWtKG, data[23]);
					//Send Shipping Unit Gross Weight KG 
					driver.findElement(shpUtGrsWtKG).clear();
					CommonFunctions.enterTextInTextbox(shpUtGrsWtKG, data[24]);
					log.info("Size of Secondary Package Net Weight  is   :"+driver.findElements(secPckNetWtKG).size());
					Assert.assertEquals(driver.findElements(secPckNetWtKG).size(), 1, "Assortment / Solid Weights: Group  is Editable");
					log.info("Assortment / Solid Weights: is updatable   :");
					CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
				}
				else if(data[5].contains("AstSolidWeightUpdate")&& data[6].equalsIgnoreCase("No")){
					
					String strPrimary =driver.findElement(documentsAction).getText();
					//System.out.println(strPrimary);
					boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
					Assert.assertFalse(val);
				}	
				else
				{
					log.info("For this Assortment / Solid Weights: is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyAstSldWeightAttUpdate()", e);
				return false;
			}
			return true;
		}

		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Colorways:  Read_View
		public static boolean verifyColorwaysReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				String colView = driver.findElement(colorwaysView).getText();
				log.info("Colorways View Value is    :"+colView);
				String colorwayLbl = driver.findElement(colorwaysLabel).getText();
				log.info("Colorways Label Value is    :"+colorwayLbl);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("ColorwaysRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if((driver.findElements(colorwaysLabel).size() != 0) && colView.contains("View")){
						log.info("Colorways Label value is    :"+colorwayLbl);
						Assert.assertEquals(colorwayLbl, " Colorways:");
						log.info("Colorways: label View is Present");
					}else{
						log.error("Colorways: label View is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("ColorwaysRead_View")&& data[6].equalsIgnoreCase("No")){
					if((driver.findElements(colorwaysLabel).size() != 0) && colView.contains("View")){
						log.error("Colorways: label View is Present");
						fail=true;
					}else{
						log.info("Colorways: label View is Absent");
					}
				}
				else
				{
					log.info("For this Colorways: label is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyColorwaysReadView()", e);
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @param data
		 * @return	
		 * @throws Exception
		 */
		//Function consist scenario : Colorways: //Update
		public static boolean verifyColorwaysUpdate(String[] data) throws Exception{
			try{
				boolean colorwayEdit=false;
				isPresent(colorwaysEdit, colorwayEdit);
				//Boolean colorwayEdit = driver.findElement(colorwaysEdit).isDisplayed();
				if(data[5].contains("ColorwaysUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
					Assert.assertTrue(colorwayEdit, "Colorways is Editable");
				
				}
				else if(data[5].contains("ColorwaysUpdate")&& data[6].equalsIgnoreCase("No")){
					Assert.assertFalse(colorwayEdit, "Colorways is Not Editable");

				}	
				else
				{
					log.info("For this Colorways is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyColorwaysUpdate()", e);
				return false;
			}
			return true;
		}


		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Secondary Package Weight Change History:  Read_View
		public static boolean verifySecPckWtChgHisReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				String secWTView = driver.findElement(secPckWtChgHisView).getText();
				log.info("secPckWtChgHis View Value is    :"+secWTView);
				String secWtLbl = driver.findElement(secPckWtChgHisLabel).getText();
				log.info("secPckWtChgHisLabel Label Value is    :"+secWtLbl);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("SecPckWtChgHisRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if((driver.findElements(secPckWtChgHisLabel).size() != 0) && secWTView.contains("View")){
						log.info("Secondary Package Weight Change History: Label value is    :"+secWtLbl);
						Assert.assertEquals(secWtLbl, " Secondary Package Weight Change History:");
						log.info("Secondary Package Weight Change History: label View is Present");
					}else{
						log.error("Secondary Package Weight Change History: label View is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("SecPckWtChgHisRead_View")&& data[6].equalsIgnoreCase("No")){
					if((driver.findElements(secPckWtChgHisLabel).size() != 0) && secWTView.contains("View")){
						log.error("Secondary Package Weight Change History: label View is Present");
						fail=true;
					}else{
						log.info("Secondary Package Weight Change History: label View is Absent");
					}
				}
				else
				{
					log.info("For this Secondary Package Weight Change History: label is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifySecPckWtChgHisReadView()", e);
				return false;
			}
			return true;
		}

		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Shipping Unit Weight Change History: Read_View
		public static boolean verifyShpUtWtChgHisReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				String shpWtView = driver.findElement(shpUtWtChgHisView).getText();
				log.info("Colorways View Value is    :"+shpWtView);
				String shpWtLbl = driver.findElement(shpUtWtChgHisLabel).getText();
				log.info("Colorways Label Value is    :"+shpWtLbl);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("ShpUtWtChgHisRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if((driver.findElements(shpUtWtChgHisLabel).size() != 0) && shpWtView.contains("View")){
						log.info("Colorways Label value is    :"+shpWtLbl);
						Assert.assertEquals(shpWtLbl, " Shipping Unit Weight Change History:");
						log.info("Shipping Unit Weight Change History: label View is Present");
					}else{
						log.error("Shipping Unit Weight Change History: label View is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("ShpUtWtChgHisRead_View")&& data[6].equalsIgnoreCase("No")){
					if((driver.findElements(shpUtWtChgHisLabel).size() != 0) && shpWtView.contains("View")){
						log.error("Shipping Unit Weight Change History: label View is Present");
						fail=true;
					}else{
						log.info("Shipping Unit Weight Change History: label View is Absent");
					}
				}
				else
				{
					log.info("For this Shipping Unit Weight Change History: label is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyShpUtWtChgHisReadView()", e);
				return false;
			}
			return true;
		}

		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario :  Retail Item Weight: Read_View
		public static boolean verifyRetailItemWeightReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("RetailItemWeightRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(rtlItemWtLabel).size() != 0){
						String RtlItemWTLbl=driver.findElement(rtlItemWtLabel).getText();
						log.info("Retail Item Weight:  value is    :"+RtlItemWTLbl);
						Assert.assertEquals(RtlItemWTLbl, " Retail Item Weight:");
						log.info("Retail Item Weight: label is Present");
					}else{
						log.error("Retail Item Weight: label is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("AstSolidWeightRead_View")&& data[6].equalsIgnoreCase("No")){
					if(driver.findElements(rtlItemWtLabel).size() != 0){
						log.error("Retail Item Weight: label is Present");
						fail=true;
					}else{
						log.info("Retail Item Weight: label is Absent");
					}
				}
				else
				{
					log.info("For this Retail Item Weight: is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyRetailItemWeightReadView()", e);
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario :  Retail Item Weight://Update
		public static boolean verifyRetailItemWeightUpdate(String[] data) throws Exception{
			try{
				if(data[5].contains("RetailItemWeightUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
					CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
					//Send Retail Package Net Weight KG 
					driver.findElement(rtlPckNtWtKG).clear();
					CommonFunctions.enterTextInTextbox(rtlPckNtWtKG, data[29]);
					//Send Retail Package Gross Weight KG
					driver.findElement(rtlPckGrsWtKG).clear();
					CommonFunctions.enterTextInTextbox(rtlPckGrsWtKG, data[31]);
				
					log.info("Size of Secondary Package Net Weight  is   :"+driver.findElements(rtlPckNtWtKG).size());
					Assert.assertEquals(driver.findElements(rtlPckNtWtKG).size(), 1, "Retail Item Weight: Group  is Editable");
					log.info("Retail Item Weight: is updatable   :");
					CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
				}
				else if(data[5].contains("RetailItemWeightUpdate")&& data[6].equalsIgnoreCase("No")){
					
					String strPrimary =driver.findElement(documentsAction).getText();
					//System.out.println(strPrimary);
					boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
					Assert.assertFalse(val);
				}	
				else
				{
					log.info("For this Retail Item Weight: is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyRetailItemWeightUpdate()", e);
				return false;
			}
			return true;
		}

	
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Retail Package Weight Change History:  Read_View
		public static boolean verifyRtlPckgWtChgHisReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				String rtlWtView = driver.findElement(rtlPckWtChgHisView).getText();
				log.info("Colorways View Value is    :"+rtlWtView);
				String rtlWtLbl = driver.findElement(rtlPckWtChgHisLabel).getText();
				log.info("Colorways Label Value is    :"+rtlWtLbl);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("RtlPckWtChgHistoryRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if((driver.findElements(rtlPckWtChgHisLabel).size() != 0) && rtlWtView.contains("View")){
						log.info("Retail Package Weight Change History: Label value is    :"+rtlWtLbl);
						Assert.assertEquals(rtlWtLbl, " Retail Package Weight Change History:");
						log.info("Retail Package Weight Change History: label View is Present");
					}else{
						log.error("Retail Package Weight Change History: label View is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("RtlPckWtChgHistoryRead_View")&& data[6].equalsIgnoreCase("No")){
					if((driver.findElements(rtlPckWtChgHisLabel).size() != 0) && rtlWtView.contains("View")){
						log.error("Retail Package Weight Change History: label View is Present");
						fail=true;
					}else{
						log.info("Retail Package Weight Change History: label View is Absent");
					}
				}
				else
				{
					log.info("For this Retail Package Weight Change History: label is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyRtlPckgWtChgHisReadView()", e);
				return false;
			}
			return true;
		}

		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Bundle Pack Weight:Read_View
		public static boolean verifyBundlePackWeightReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("BundlePackWeightRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if(driver.findElements(bndlPckWeightLabel).size() != 0){
						String BndlPckWTLbl=driver.findElement(bndlPckWeightLabel).getText();
						log.info("Bundle Pack Weight:  value is    :"+BndlPckWTLbl);
						Assert.assertEquals(BndlPckWTLbl, " Bundle Pack Weight:");
						log.info("Bundle Pack Weight: label is Present");
					}else{
						log.error("Bundle Pack Weight: label is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("BundlePackWeightRead_View")&& data[6].equalsIgnoreCase("No")){
					if(driver.findElements(bndlPckWeightLabel).size() != 0){
						log.error("Bundle Pack Weight: label is Present");
						fail=true;
					}else{
						log.info("Bundle Pack Weight: label is Absent");
					}
				}
				else
				{
					log.info("For this Bundle Pack Weight: is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyBundlePackWeightReadView()", e);
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Bundle Pack Weight://Update
		public static boolean verifyBundlePackWeightUpdate(String[] data) throws Exception{
			try{
				if(data[5].contains("BundlePackWeightUpdate")&& data[6].equalsIgnoreCase("Yes")){//Update
					CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[42]);
					//Send Bundle Pack Net Weight KG
					driver.findElement(bndlPckNtWtKG).clear();
					CommonFunctions.enterTextInTextbox(bndlPckNtWtKG, data[34]);
					//Send Bundle Pack Gross Weight KG
					driver.findElement(bndlPckGrsWtKG).clear();
					CommonFunctions.enterTextInTextbox(bndlPckGrsWtKG, data[36]);
				
					log.info("Size of Bundle Pack Net Weight KG  is   :"+driver.findElements(bndlPckNtWtKG).size());
					Assert.assertEquals(driver.findElements(bndlPckNtWtKG).size(), 1, "Bundle Pack Weight: Group  is Editable");
					log.info("Bundle Pack Weight: is updatable   :");
					CommonFunctions.clickButtonOrLink(checkIN, "link", " Save And Check In");
				}
				else if(data[5].contains("BundlePackWeightUpdate")&& data[6].equalsIgnoreCase("No")){
					
					String strPrimary =driver.findElement(documentsAction).getText();
					//System.out.println(strPrimary);
					boolean val= SourcingConfig.findString(strPrimary.trim(), data[42]);
					Assert.assertFalse(val);
				}	
				else
				{
					log.info("For this Bundle Pack Weight: is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyBundlePackWeightUpdate()", e);
				return false;
			}
			return true;
		}

		/**
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		//Function consist scenario : Bundle Pack Weight Change History:  Read_View
		public static boolean verifyBndlPckgWtChgHisReadView(String[] data) throws Exception{
			try{

				searchDocument(data);
				String bndlWtView = driver.findElement(bndlPckWtChgHisView).getText();
				log.info("Bundle Pack Weight Change History: View Value is    :"+bndlWtView);
				String bndlWtLbl = driver.findElement(bndlPckWtChgHisLabel).getText();
				log.info("Bundle Pack Weight Change History: Label Value is    :"+bndlWtLbl);
				// called only when type is either concept image or Specification Image Page
				if(data[5].contains("BndlPckWtChgHistoryRead_View")&& data[6].equalsIgnoreCase("Yes")){//Read_View
					if((driver.findElements(bndlPckWtChgHisLabel).size() != 0) && bndlWtView.contains("View")){
						log.info("Bundle Pack Weight Change History: Label value is    :"+bndlWtLbl);
						Assert.assertEquals(bndlWtLbl, " Bundle Pack Weight Change History:");
						log.info("Bundle Pack Weight Change History: label View is Present");
					}else{
						log.error("Bundle Pack Weight Change History: label View is Absent");
						fail=true;
					}
				}
				else if(data[5].contains("RtlPckWtChgHistoryRead_View")&& data[6].equalsIgnoreCase("No")){
					if((driver.findElements(bndlPckWtChgHisLabel).size() != 0) && bndlWtView.contains("View")){
						log.error("Bundle Pack Weight Change History: label View is Present");
						fail=true;
					}else{
						log.info("Bundle Pack Weight Change History: label View is Absent");
					}
				}
				else
				{
					log.info("For this Bundle Pack Weight Change History: label is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyRtlPckgWtChgHisReadView()", e);
				return false;
			}
			return true;
		}


		
		
	
		//Function consist scenario : search Document
		public static boolean searchDocument(String[] data) throws Exception{
			try{
				driver.navigate().refresh();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(libraryLink, "Link", "Library Link");
				//Click on Color link
				driver.findElement(retailDocLink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(retailDocHeading));
				//Add name
				CommonFunctions.enterTextInTextbox(searchName, data[41]);
				//Click on Search
				CommonFunctions.clickButtonOrLink(search, "Btn", "Search");
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(documentDetails));
				
			}catch(Exception e){
				fail=true;
				log.error("Exception in searchDocuments()", e);
				return false;
			}
			return true;
		}
		
		// method is for checking the variable.
		public static boolean isPresent(By by, boolean p) throws Exception{
		try {
			p = driver.findElement(by).isDisplayed();
			p = true;
		} catch (NoSuchElementException e) {
			p = false;
		}
		return p;
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
