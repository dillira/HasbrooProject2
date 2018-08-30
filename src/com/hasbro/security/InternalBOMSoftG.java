package com.hasbro.security;
/**
 * @author Anjali Gupta
 *Prerequisite :
 *1. Product should be created and added in column 5. Ensure that only one Product exist with that Product Number.
 *2. Make sure Product does have external source created
 *3. External source say Funskool must have a specification who's state is Supplier Released 
 *3. Ensure there is columns in automation security sheet for "vendor user" and "In Work Read"
 *4. Make sure mark BOM match Cost Summary update and BOM Cost Summary Update as No for Admin user as they no one has update access for that table attributes
 *5. For Soft Good BOM we have "create" methods in Hasbro Internal and Funskool source. Soft Good BOM should be in Retail Item Product
 *6. Ensure that Soft Good BOM vendor user scenario in security sheet should have source as Funskool
 *
 */
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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


import com.sun.jna.platform.win32.WinUser.SIZE;

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;



public class InternalBOMSoftG extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	
	public static By addNewBOMTab= By.xpath("//a[contains(text(),'Add New BOM')]");
	//	public static By BOMDetails= By.xpath("//li[@id='DETAILS_PAGETab']/a");
	public static By initializeBOM= By.xpath("//a[contains(text(),'Initialize BOM')]");
	public static By headingCreateBOM= By.xpath("//td[contains(text(),'Create BOM')]");
	public static By name= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By ROname= By.xpath("//td[contains(text(),'BOM Identification')]//following::td[2]");
	public static By colorwayName= By.xpath("//td[contains(text(),'Colorway Name')]//following::select[1]");
	public static By colorway= By.xpath("//td[contains(text(),'Colorway')]//following::select[1]");
	public static By wave= By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By currency= By.xpath("//td[contains(text(),'Currency')]//following::select[1]");
	public static By Factory=By.linkText("Factory:");

	public static By selectSource= By.id("sourcingConfigId");
	public static By selectExistingSpec= By.id("existingSetList");

	public static By selectSpecification= By.id("contextSpecId");
	public static By compOrLoca= By.id("r1_partName");
	public static By checmicalDesc= By.id("r2_partName");

	public static By quantity= By.id("r1_quantity");
	public static By meterial= By.id("r1_materialDescription");
	public static By meterialRow2= By.id("r2_materialDescription");

	public static By plasticDescription= By.xpath("//a[contains(text(),'Plastics')]//following::td[1]");
	public static By mastercartonDescription= By.xpath("//a[contains(text(),'Master Carton Packaging')]//following::td[1]");
	public static By FabricDescription= By.xpath("//a[contains(text(),'Fabrics')]//following::td[1]");

	public static By plasticMeterialDesc= By.xpath("//a[contains(text(),'Plastics')]//following::td[3]");

	public static By chemicalsDescription= By.xpath("//a[contains(text(),'Chemicals')]//following::td[1]");
	public static By chemicalsMeterialDesc= By.xpath("//a[contains(text(),'Chemicals')]//following::td[3]");

	public static By headingEditBOM= By.xpath("//td[contains(text(),'Edit BOM')]");
	public static By inputCompOrLoca= By.xpath("//div[@id='partNameSourceDiv']/input");
	public static By inputChemical=By.xpath(".//td[@id='r2_partName']/div[1]/input[1]");
	public static By inputquantity= By.xpath("//div[@id='quantitySourceDiv']/input");
	public static By inputMaterial= By.xpath("//div[@id='materialDescriptionSourceDiv']/textarea");

	public static By btnSaveAndCheckIn= By.xpath("//a[text()='Save and Check In']");
	public static By headerAttributes= By.xpath("//*[@id='genDetails_plus']/a[2]");
	public static By headerAttributesBtn= By.xpath("//a[contains(text(),'Header Attributes')]");

	public static By BOMId= By.id("bomId");
	public static By BOMTypeId= By.id("bomTypeId");
	public static By headerAttributesPlus= By.xpath("//div[@id='genDetails_plus']/a[1]/img");
	public static By headerAttributeExpand= By.xpath("//div[@id='genDetails_plus']/a[1]/img[@src='/Windchill/netmarkets/images/expand_tree.png']");
	public static By headerAttributeCollapse= By.xpath("//div[@id='genDetails_plus']/a[1]/img[@src='/Windchill/netmarkets/images/collapse_tree.png']");
	public static By BOMAction= By.xpath("//div[@id='UPDATE_BTNS']//a[contains(text(),'Actions')]");
	public static By checkedOutByYou= By.xpath("//td[contains(text(),'Checked Out by: You')]");
	public static By billOfMaterials= By.xpath("//select[@id='bomId']/option[@selected='']");
	public static By RO_UpdateLifecycleState = By.xpath("//div[contains(@id,'systemInformationOR')]//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By updateBtn= By.xpath("//a[contains(text(),'Update')]");
	public static By actionDD= By.id("prodseasonActions");
	public static By manager   = By.xpath("//td[contains(text(),'Manager')]//following::select[1]");
	public static By sourcingLead   = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead   = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");
	public static By management   = By.xpath("//td[contains(text(),'Management')]//following::select[1]");
	public static By BOMDetails  = By.xpath("//a[text()='BOM Details']");

	public static By BOMIdentification  = By.xpath("//td[contains(text(),'BOM Identification')]");
	public static By BOMCostSummary  = By.xpath("//td[contains(text(),'BOM Cost Summary')]");
	public static By BOMMatchCostSummary  = By.xpath("//td[contains(text(),'BOM Match Cost Summary')]");									
	public static By BOMComments  = By.xpath("//td[contains(text(),'Comments')]");
	public static By EnterComments  = By.xpath("//a[text()='Enter Comment']");
	public static By PostNewComment  = By.xpath("//td[contains(text(),'Post New Comment')]//following::textarea[1]");
	public static By post  = By.xpath("//a[text()='Post']");
	public static By descHeading  = By.xpath("//td[@class='TABLESUBHEADER' and text()='Description']");
	public static By plastics  = By.xpath("//a[contains(text(),'Plastics')]");

	public static By BOMStatus  = By.xpath("//td[contains(text(),'BOM Status')]//following::select[1]");

	public static By ROComment  = By.xpath("//a[contains(text(),'User')]//following::td[1]");

	public static By componentPlus  = By.xpath("//a[text()='Components']/preceding-sibling::a");
	public static By removeLink  = By.xpath("//a[text()='Remove']");
	public static By ROWave  = By.xpath("//td[contains(text(),'Wave')]//following::td[1]");
	public static By plasticClear  = By.xpath("//div[@id='hbPlasticsTabEditorTableDiv']//a[contains(text(),'Clear')]");
	public static By mastercartonpackagingClear  = By.xpath("//*[@id='hbMasterCartonPackagingTabEditorTableDiv']//a[contains(text(),'Clear')]");
	public static By softgoddFabricClear = By.xpath(".//*[@id='hbFabricsTabEditorTableDiv']//a[contains(text(),'Clear')]");	
	public static By chemicalClear  = By.xpath("//div[@id='hbChemicalsTabEditorTableDiv']//a[contains(text(),'Clear')]");
	public static By viewBtn= By.xpath("//img[@src='/Windchill/netmarkets/images/view.png']");
	public static By NoneBtn= By.xpath("//a[contains(text(),'None')]");
	public static By BOMStatusInReadView = By.xpath("//td[contains(text(),'BOM Status:')]");
	public static By BOMStatusupdate = By.xpath("//td[contains(text(),'Internal BOM Status')]//following::select[1]");
	public static By Bomstatusdetailpage=By.id("hbInternalBOMStatus");
	public static By TotalBOMMatchCost =By.id("hbTotalBOMMatchCost");
	public static By TotalPlasticMatchCost  =By.id("hbTotalPlasticMatchCost");
	public static By  TotalChemicalsMatctCost =By.id("hbTotalChemicalsMatchCost");
	public static By  TotalPurchasedPartsMatchCost =By.id("hbTotalPurchasedPartsMatchCost");
	public static By  TotalElectronicsMatchCost =By.id("hbTotalElectronicsMatchCost");
	public static By  TotalSoftGoodsMatchCost =By.id("hbTotalSoftGoodsMatchCost");
	public static By  TotalPackagingMatchCost =By.id("hbTotalPackagingMatchCost");
	public static By  TotalGeneralDecoLaborMatchCost =By.id("hbTotalGeneralDecoLaborMatchCost");
	public static By  TotalMoldingLaborMatchCost =By.id("hbTotalMoldingLaborMatchCost");
	public static By  TotalMarkUpMatchCost =By.id("hbTotalMarkUpMatchCost");
	public static By  TTotalBOMCost =By.id("hbTotalBOMCost");
	public static By  TTotalPlasticCost =By.id("hbTotalPlasticCost");
	public static By  TTotalChemicalsCost =By.id("hbTotalChemicalsCost");
	public static By  TTotalPurchasedPartsCost =By.id("hbTotalPurchasedPartsCost");
	public static By  TTotalElectronicsCost =By.id("hbTotalElectronicsCost");
	public static By  TTotalSoftGoodsCost =By.id("hbTotalSoftGoodsCost");
	public static By  TTotalPackagingCost =By.id("hbTotalPackagingCost");
	public static By  TTotalGenDecoLaborCost =By.id("hbTotalGenDecoLaborCost");
	public static By  TTotalMoldingLaborCost =By.id("hbTotalMoldingLaborCost");
	public static By  TTotalMarkUpCost =By.id("hbTotalMarkUpCost");
	public static By  TTotalPlasticUsageK =By.id("hbTotalPlasticUsageK");
	public static By productdetailstab = By.xpath("//a[contains(text(),'Details')]");	
	public static By specificationtab = By.xpath("//a[contains(text(),'Specifications')]");
	public static By TotalMasterCartonPackagingCost =By.id("hbTotalMasterCartonPkgCost");
	public static By TotalMiscellaneousCost = By.id("hbTotalMasterCartonPkgCost");
	public static By TotalLaborCost = By.id("hbTotalLaborCost");
	public static By TotalMarkUpCost = By.id("hbTotalMarkUpCost");
	public static By MaterialTextBox1=By.xpath(".//td[@id='r1_materialDescription']");
	public static By MaterialTextBox2=By.xpath(".//td[@id='r2_materialDescription']");
	public static By MaterialOptionAlertBox=By.xpath(".//div[@id='100' and @class='BORDERED_BLOCK']");
	public static By FirstValueOfMaterial=By.xpath(".//table[contains(@id,'TBLT')]/tbody/tr[2]/td[1]/img[1]");
	//public static By SupplierTextBox1=By.xpath(".//*[@id='r1_supplierName']");
	//public static By SupplierTextBox2=By.xpath(".//*[@id='r2_supplierName']");


	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String BOMname;
	static String strBillOfMaterials;
	static Boolean bCheckedOut=false;
	public static Actions action;
	public static String strRO_UpdateLifecycleState;
	static String strUpdate;
	static String strRO_quantity;
	public static String strSpec;
	static String  strSource;
	public static String strCW;
	static String strBOMDetail;
	static String BOMnameInWork;
	static String BOMnameSGExternal;
	static String BOMnameUnderReview;
	static String BOMnameReleased;
	static String BOMnameCancelled;
	static String BOMnameRejected;
	static String BOMnameReadyforReview;
	static String strBOMInWork;
	static String strBOM;
	static String strBOMIW;
	static String strBOMUR;
	static String strBOMR;
	static String strBOMC;
	static String strBOMRj;
	static String strBOMRFR;
	public static String BOMnameInWork1;
	static String strBOMRDelete;
	static String SGExternalBOMInWork;
	static String SGBOMnameReleased;
	static String SGstrBOMR;
	static String SGstrBOMUR;
	static String SGBOMnameUnderReview;
	static String SGstrBOMC;
	static String SGBOMnameCancelled;
	static String SGBOMnameRejected;
	static String SGstrBOMRj;
	static String SGstrBOMInWork;
	static String SGBOMnameInWork1;
	static String SGBOMnameReleasedDelete;
	static String SGstrBOMRDelete;


	@Test(dataProvider="testDataTest")
	public void tcBOM(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Security Internal and Soft good BOM");
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			System.out.println("attribute group :" + data[2]);
			System.out.println("verification :" + data[3]);
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

			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateInWork"))
			{ CreateInWork_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateSoftGoodInWorkExternal"))
			{ Create_SoftGood_InWork_External(data); }

			if(data[3].equalsIgnoreCase("CreateUnderReview"))
			{ CreateUnderReview_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateSoftGoodUWExternal"))
			{ Create_Soft_Good_UW_External(data); }

			if(data[3].equalsIgnoreCase("CreateReleased"))
			{ CreateReleased_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateSoftGoodRelExternal"))
			{ Create_SoftGood_Rel_External(data); }

			if(data[3].equalsIgnoreCase("CreateCancelled"))
			{ CreateCancelled_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateSoftGoodCanExternal"))
			{ Create_Soft_Good_Can_External(data); }


			if(data[3].equalsIgnoreCase("CreateRejected"))
			{ CreateRejected_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateSoftGoodRejExternal"))
			{ Create_SoftGood_Rej_External(data); }


			if(data[3].equalsIgnoreCase("CreateReadyforReview"))
			{ CreateReadyforReview_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateReleasedDelete"))
			{ CreateReleasedDelete_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateSoftGoodRelDeleteExternal"))
			{ Create_SoftGood_Rel_Delete_External(data); }

			if(data[3].equalsIgnoreCase("readView"))
			{ readView_BOM(data); }
			if(data[3].equalsIgnoreCase("update"))
			{ update_BOM(data); }

			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_BOM(data); }

			if(data[3].equalsIgnoreCase("inWorkReadView"))
			{ InWorkreadView_BOM(data); }
			if(data[3].equalsIgnoreCase("inWorkUpdate"))
			{ InWorkUpdate_BOM(data); }

			if(data[3].equalsIgnoreCase("UnderReviewReadView"))
			{ underReviewReadView(data); }
			if(data[3].equalsIgnoreCase("UnderReviewReadUpdate"))
			{ underReviewUpdate(data); }

			if(data[3].equalsIgnoreCase("ReleasedReadView"))
			{ releasedReadView(data); }
			if(data[3].equalsIgnoreCase("ReleasedUpdate"))
			{ releasedUpdate(data); }

			if(data[3].equalsIgnoreCase("CancelledReadView"))
			{ cancelledReadView(data); }
			if(data[3].equalsIgnoreCase("CancelledReadUpdate"))
			{ cancelledUpdate(data); }

			if(data[3].equalsIgnoreCase("RejectedReadView"))
			{ rejectedReadView(data); }

			if(data[3].equalsIgnoreCase("RejectedReadUpdate"))
			{ rejectedUpdate(data);   }

			//Read view verification
			if(data[3].equalsIgnoreCase("GeneralAttributesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttributesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }

			if(data[3].equalsIgnoreCase("BOMStatusRead"))
			{ verifyBOMStatusReadView(data); }

			if(data[3].equalsIgnoreCase("BOMStatusUpdate")){
				verifyBOMStatusUpdate(data); 
			}

			if(data[3].equalsIgnoreCase("BOMCostingSummaryReadView"))
			{ verifyBOMCostingSummaryReadView(data); }
			if(data[3].equalsIgnoreCase("BOMCostingSummaryUpdate"))
			{ verifyBOMCostingSummaryUpdate(data); }


			if(data[3].equalsIgnoreCase("BOMMatchCostSummaryReadView"))
			{ 
				verifyBOMMatchCostSummaryReadView(data); 

			}
			if(data[3].equalsIgnoreCase("BOMMatchCostSummaryUpdate"))
			{
				verifyBOMMatchCostSummaryUpdate(data); 
			}

			if(data[3].equalsIgnoreCase("BOMCommentsRead"))
			{ verifyBOMCommentsRead(data); }
			if(data[3].equalsIgnoreCase("BOMCommentsUpdate"))
			{ verifyBOMCommentsUpdate(data); }

			if(data[3].equalsIgnoreCase("BOMSectionsRead"))
			{ verifyBOMSectionsRead(data); }
			if(data[3].equalsIgnoreCase("BOMSectionsUpdate"))
			{ verifyBOMSectionsUpdate(data); }



		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Specification should be created.previous specifications should not be available
	public static String[] Create_BOM(String[] data) throws Exception{
		try{
			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){

				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				CommonProjectFunctions.clickSpecificationTab(data[6]);

				//Code for selecting the Hasbro Internal Source
				//Get the size of source dropdown
				Select selectsource= new Select(driver.findElement(selectSource));
				List<WebElement> sourcelist= selectsource.getOptions();

				int sourcelistcount =sourcelist.size();
				log.info("The size of source drop down list is: " + sourcelistcount);

				int internalsourcecount= sourcelistcount -1;

				//Select source dropdown
				selectsource.selectByIndex(internalsourcecount);


				CommonFunctions.handleAlertPopUp();
				strSpec=AddSpecification(data);
				strCW=AddColorway(data);

				CommonProjectFunctions.clickMaterialsTab();
				CommonFunctions.handleAlertPopUp();

				//Click Add New BOM tab
				CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
				//Enter BOM Type
				//CommonFunctions.enterTextInTextbox(By.xpath("//select[@id='bomTypeId']/option[contains(text(),'"+data[2]+"')]"), data[2]);
				CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

				//Click Initialize BOM
				CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
				BOMname="InWork"+CommonFunctions.getRandomString(4);
				//Create BOM page
				BOMnameInWork = fillCreateBOM(data);
				//Switch to mainframe
				driver.switchTo().frame("mainFrame");
				//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
				//Component or Location
				action = new Actions(driver);
				action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
				//Quanity
				action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				CommonFunctions.waitForVisibilityOfElement(headerAttributes);
				//	String strBOM=driver.findElement(BOMId).getText();
				strBOMIW=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
				if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal") || (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods")))
				{
					strBOM=strBOMIW.substring(6, 16);
					String strHeader=driver.findElement(headerAttributes).getText();
					System.out.println(strBOM.trim());
					System.out.println(BOMnameInWork);
					Assert.assertEquals(strHeader,"Header Attributes");
					Assert.assertEquals(strBOM.trim(),BOMnameInWork);
				}
			}
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){

					CommonProjectFunctions.searchProduct(data[5]);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else{
					CommonProjectFunctions.searchProduct(data[5]);
					//Click on Specification
					CommonProjectFunctions.clickSpecificationTab(data[6]);

					//Get the size of source dropdown
					Select selectsource= new Select(driver.findElement(selectSource));
					List<WebElement> sourcelist= selectsource.getOptions();


					int sourcelistcount =sourcelist.size();
					log.info("The size of source drop down list is: " + sourcelistcount);

					int internalsourcecount= sourcelistcount -1;

					//Select source dropdown
					selectsource.selectByIndex(internalsourcecount);

					CommonFunctions.handleAlertPopUp();
					strSpec=AddSpecification(data);
					strCW=AddColorway(data);

					CommonProjectFunctions.clickMaterialsTab();

					boolean addnewBomtabpresent= CommonFunctions.isElementPresent(addNewBOMTab);
					log.info("Is Add new BOM tab present: " + addnewBomtabpresent);

					//	Assert.assertFalse(strAddNewBOMTab);
					Assert.assertEquals(driver.findElements(addNewBOMTab).size(), 0, "BOM can not create");

				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_BOM()", e);
			//		return "";
		}
		return new String[] {strBOMIW, BOMnameInWork};
		//return BOMnameInWork;
	}

	public static String[] Create_SoftGood_InWork_External(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//Select the Funskool source
			Select selectsource= new Select(driver.findElement(selectSource));
			//selectsource.selectByValue(data[8]);
			selectsource.selectByVisibleText(data[8]);

			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();

			//	Boolean strAddNewBOMTab =driver.findElement(addNewBOMTab).isDisplayed();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			//CommonFunctions.enterTextInTextbox(By.xpath("//select[@id='bomTypeId']/option[contains(text(),'"+data[2]+"')]"), data[2]);
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="InWork"+CommonFunctions.getRandomString(4);
			System.out.println("BOM Name entered is: " + BOMname);

			//Create BOM page
			SGBOMnameInWork1 = fillCreateBOM(data);
			System.out.println("BOM Name returned in create page:  " + SGBOMnameInWork1);

			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			CommonFunctions.waitForVisibilityOfElement(headerAttributes);
			//	String strBOM=driver.findElement(BOMId).getText();
			SGstrBOMInWork=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			System.out.println("BOM name in detail page after check in: " + SGstrBOMInWork);

		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SoftGood_InWork_External()", e);

		}
		return new String[] {SGstrBOMInWork, SGBOMnameInWork1};
		//return BOMnameUnderReview;
	}


	public static String[] CreateInWork_BOM(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//strSource=AddSource(data);
			//Select Specificaiton
			Select selectsource= new Select(driver.findElement(selectSource));
			List<WebElement> sourcelist= selectsource.getOptions();

			int sourcelistcount =sourcelist.size();
			log.info("The size of source drop down list is: " + sourcelistcount);			
			int internalsourcecount= sourcelistcount -1;

			//Select source dropdown
			selectsource.selectByIndex(internalsourcecount);
			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();

			//	Boolean strAddNewBOMTab =driver.findElement(addNewBOMTab).isDisplayed();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			//CommonFunctions.enterTextInTextbox(By.xpath("//select[@id='bomTypeId']/option[contains(text(),'"+data[2]+"')]"), data[2]);
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="InWork"+CommonFunctions.getRandomString(4);
			System.out.println("BOM Name entered is: " + BOMname);

			//Create BOM page
			BOMnameInWork1 = fillCreateBOM(data);
			System.out.println("BOM Name returned in create page:  " + BOMnameInWork1);

			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			CommonFunctions.waitForVisibilityOfElement(headerAttributes);
			//	String strBOM=driver.findElement(BOMId).getText();
			strBOMInWork=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			System.out.println("BOM name in detail page after check in: " + strBOMInWork);

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateInWork_BOM()", e);
			//	return "";
		}
		return new String[] {strBOMInWork, BOMnameInWork1};
		//return BOMnameUnderReview;
	}

	//Prerequsite : Product must be created and added in excel sheet
	public static String[] Create_Soft_Good_UW_External(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//Select the Funskool source
			Select selectsource= new Select(driver.findElement(selectSource));
			//selectsource.selectByValue(data[8]);
			selectsource.selectByVisibleText(data[8]);

			CommonFunctions.handleAlertPopUp();
			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			//Create BOM page
			BOMname="UnderReview"+CommonFunctions.getRandomString(4);
			SGBOMnameUnderReview = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			SGstrBOMUR=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=SGstrBOMUR.substring(6, 21);
				System.out.println(strBOM.trim());
				System.out.println(SGBOMnameUnderReview);
				Assert.assertEquals(strBOM.trim(),SGBOMnameUnderReview);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			if (data[3].contains("CreateSoftGoodUWExternal")){
				valULCSAfterChange=changeStatusUnderReview();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("UnderReview status BOM is: "+SGstrBOMUR);
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_Soft_Good_UW_External()", e);
			//	return "";
		}
		return new String[] {SGstrBOMUR, SGBOMnameUnderReview};
		//return BOMnameUnderReview;
	}

	//Prerequsite : Product must be created and added in excel sheet
	public static String[] CreateUnderReview_BOM(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//strSource=AddSource(data);
			//select Specification
			Select selectsource= new Select(driver.findElement(selectSource));
			List<WebElement> sourcelist= selectsource.getOptions();

			int sourcelistcount =sourcelist.size();
			log.info("The size of source drop down list is: " + sourcelistcount);			
			int internalsourcecount= sourcelistcount -1;

			//Select source dropdown
			selectsource.selectByIndex(internalsourcecount);

			CommonFunctions.handleAlertPopUp();
			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			//Create BOM page
			BOMname="UnderReview"+CommonFunctions.getRandomString(4);
			BOMnameUnderReview = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			strBOMUR=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=strBOMUR.substring(6, 21);
				System.out.println(strBOM.trim());
				System.out.println(BOMnameUnderReview);
				Assert.assertEquals(strBOM.trim(),BOMnameUnderReview);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			if (data[3].contains("CreateUnderReview")){
				valULCSAfterChange=changeStatusUnderReview();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("UnderReview status BOM is: "+strBOMUR);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateUnderReview_BOM()", e);
			//	return "";
		}
		return new String[] {strBOMUR, BOMnameUnderReview};
		//return BOMnameUnderReview;
	}

	public static String[] Create_SoftGood_Rel_External(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//Select the Funskool source
			Select selectsource= new Select(driver.findElement(selectSource));
			//selectsource.selectByValue(data[8]);
			selectsource.selectByVisibleText(data[8]);

			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="Released"+CommonFunctions.getRandomString(4);
			//Create BOM page
			SGBOMnameReleased = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			SGstrBOMR=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=SGstrBOMR.substring(6, 18);
				System.out.println(strBOM.trim());
				System.out.println(SGBOMnameReleased);
				Assert.assertEquals(strBOM.trim(),SGBOMnameReleased);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateSoftGoodRelExternal")){
				valULCSAfterChange=changeStatusReleased();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Released status BOM is: "+SGstrBOMR);

		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SoftGood_Rel_External()", e);
			//	return [];
		}
		return new String[] {SGstrBOMR, SGBOMnameReleased};
		//return BOMnameReleased;
	}

	public static String[] CreateReleased_BOM(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//strSource=AddSource(data);
			//Select Specification
			Select selectsource= new Select(driver.findElement(selectSource));
			List<WebElement> sourcelist= selectsource.getOptions();

			int sourcelistcount =sourcelist.size();
			log.info("The size of source drop down list is: " + sourcelistcount);


			int internalsourcecount= sourcelistcount -1;

			//Select source dropdown
			selectsource.selectByIndex(internalsourcecount);
			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="Released"+CommonFunctions.getRandomString(4);
			//Create BOM page
			BOMnameReleased = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			strBOMR=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=strBOMR.substring(6, 18);
				System.out.println(strBOM.trim());
				System.out.println(BOMnameReleased);
				Assert.assertEquals(strBOM.trim(),BOMnameReleased);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateReleased")){
				valULCSAfterChange=changeStatusReleased();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Released status BOM is: "+strBOMR);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateReleased_BOM()", e);
			//	return [];
		}
		return new String[] {strBOMR, BOMnameReleased};
		//return BOMnameReleased;
	}

	public static String[] Create_Soft_Good_Can_External(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//Select the Funskool source
			Select selectsource= new Select(driver.findElement(selectSource));
			//selectsource.selectByValue(data[8]);
			selectsource.selectByVisibleText(data[8]);

			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="Canceled"+CommonFunctions.getRandomString(4);
			//Create BOM page
			SGBOMnameCancelled = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			SGstrBOMC=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();

			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=SGstrBOMC.substring(6, 18);
				System.out.println(strBOM.trim());
				System.out.println(SGBOMnameCancelled);
				Assert.assertEquals(strBOM.trim(),SGBOMnameCancelled);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");

			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateSoftGoodCanExternal")){
				valULCSAfterChange=changeStatusCancelled();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Cancelled status BOM is: "+strBOMC);
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_Soft_Good_Can_External()", e);
			//		return "";
		}
		return new String[] {SGstrBOMC, SGBOMnameCancelled};
		//	return BOMnameCancelled;
	}

	public static String[] CreateCancelled_BOM(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//strSource=AddSource(data);
			//Select Specification
			Select selectsource= new Select(driver.findElement(selectSource));
			List<WebElement> sourcelist= selectsource.getOptions();

			int sourcelistcount =sourcelist.size();
			log.info("The size of source drop down list is: " + sourcelistcount);

			int internalsourcecount= sourcelistcount -1;

			//Select source dropdown
			selectsource.selectByIndex(internalsourcecount);

			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="Canceled"+CommonFunctions.getRandomString(4);
			//Create BOM page
			BOMnameCancelled = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			strBOMC=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();

			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=strBOMC.substring(6, 18);
				System.out.println(strBOM.trim());
				System.out.println(BOMnameCancelled);
				Assert.assertEquals(strBOM.trim(),BOMnameCancelled);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");

			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateCancelled")){
				valULCSAfterChange=changeStatusCancelled();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Cancelled status BOM is: "+strBOMC);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateCancelled_BOM()", e);
			//		return "";
		}
		return new String[] {strBOMC, BOMnameCancelled};
		//	return BOMnameCancelled;
	}



	public static String[] Create_SoftGood_Rej_External(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//Select the Funskool source
			Select selectsource= new Select(driver.findElement(selectSource));
			//selectsource.selectByValue(data[8]);
			selectsource.selectByVisibleText(data[8]);			

			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="Rejected"+CommonFunctions.getRandomString(4);
			//Create BOM page
			SGBOMnameRejected = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			SGstrBOMRj=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();

			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=SGstrBOMRj.substring(6, 18);
				System.out.println(strBOM.trim());
				System.out.println(SGBOMnameRejected);
				Assert.assertEquals(strBOM.trim(),SGBOMnameRejected);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateSoftGoodRejExternal")){
				valULCSAfterChange=changeStatusRejected();
			}
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Rejected status BOM is: "+strBOMRj);
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_SoftGood_Rej_External()", e);
			//	return "";
		}
		return new String[] {SGstrBOMRj, SGBOMnameRejected};
		//return BOMnameRejected;
	}

	public static String[] CreateRejected_BOM(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//strSource=AddSource(data);
			//Select Specification
			Select selectsource= new Select(driver.findElement(selectSource));
			List<WebElement> sourcelist= selectsource.getOptions();

			int sourcelistcount =sourcelist.size();
			log.info("The size of source drop down list is: " + sourcelistcount);

			int internalsourcecount= sourcelistcount -1;

			//Select source dropdown
			selectsource.selectByIndex(internalsourcecount);

			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="Rejected"+CommonFunctions.getRandomString(4);
			//Create BOM page
			BOMnameRejected = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			strBOMRj=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();

			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=strBOMRj.substring(6, 18);
				System.out.println(strBOM.trim());
				System.out.println(BOMnameRejected);
				Assert.assertEquals(strBOM.trim(),BOMnameRejected);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

			if(data[3].contains("CreateRejected")){
				valULCSAfterChange=changeStatusRejected();
			}
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Rejected status BOM is: "+strBOMRj);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateRejected_BOM()", e);
			//	return "";
		}
		return new String[] {strBOMRj, BOMnameRejected};
		//return BOMnameRejected;
	}


	public static String[] CreateReadyforReview_BOM(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			//strSource=AddSource(data);
			//Select Specification
			Select selectsource= new Select(driver.findElement(selectSource));
			List<WebElement> sourcelist= selectsource.getOptions();

			int sourcelistcount =sourcelist.size();
			log.info("The size of source drop down list is: " + sourcelistcount);


			int internalsourcecount= sourcelistcount -1;

			//Select source dropdown
			selectsource.selectByIndex(internalsourcecount);

			CommonFunctions.handleAlertPopUp();

			strSpec=AddSpecification(data);
			strCW=AddColorway(data);

			CommonProjectFunctions.clickMaterialsTab();
			//Click Add New BOM tab
			CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
			//Enter BOM Type
			CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

			//Click Initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			BOMname="ReadyforReview"+CommonFunctions.getRandomString(4);
			//Create BOM page
			BOMnameReadyforReview = fillCreateBOM(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			strBOMRFR=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText();
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				strBOM=strBOMRFR.substring(6, 13);
				System.out.println(strBOM.trim());
				System.out.println(BOMnameReadyforReview);
				Assert.assertEquals(strBOM.trim(),BOMnameReadyforReview);
			}
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//		driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

			//	if(data[3].contains("CreateRejected")){
			valULCSAfterChange=changeStatusReadyForReview();
			//	}
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Rejected status BOM is: "+strBOMRFR);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateRejected_BOM()", e);
			//		return "";
		}
		return new String[] {strBOMRFR, BOMnameReadyforReview};
		//	return BOMnameReadyforReview;
	}

	public static String[] Create_SoftGood_Rel_Delete_External(String[] data) throws Exception{
		try{
			if(data[3].contains("CreateSoftGoodRelDeleteExternal")){
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				CommonProjectFunctions.clickSpecificationTab(data[6]);

				//Select the Funskool source
				Select selectsource= new Select(driver.findElement(selectSource));
				//selectsource.selectByValue(data[8]);
				selectsource.selectByVisibleText(data[8]);

				CommonFunctions.handleAlertPopUp();

				strSpec=AddSpecification(data);
				strCW=AddColorway(data);

				CommonProjectFunctions.clickMaterialsTab();
				//Click Add New BOM tab
				CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
				//Enter BOM Type
				CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

				//Click Initialize BOM
				CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
				BOMname="Released"+CommonFunctions.getRandomString(4);
				//Create BOM page
				SGBOMnameReleasedDelete = fillCreateBOM(data);
				//Switch to mainframe
				driver.switchTo().frame("mainFrame");
				//Component or Location
				action = new Actions(driver);
				action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
				//Quanity
				action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				SGstrBOMRDelete =new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText(); 

				System.out.println("BOM name before trim: " + SGstrBOMRDelete);


				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");

				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				//	driver.switchTo().frame("mainFrame");
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				if(data[3].contains("CreateSoftGoodRelDeleteExternal")){
					valULCSAfterChange=changeStatusReleased();
				}

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//	CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				CommonProjectFunctions.clickSpecificationTab(data[6]);
				//Click on '+' sign of component
				CommonFunctions.clickButtonOrLink(componentPlus, "btn", "Component plus sign");
				//Click on Action
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+SGstrBOMRDelete+"')]/preceding::td[2]/a"), "dropdown", "Action");
				CommonFunctions.waitForVisibilityOfElement(removeLink);
				//Click on Remove
				CommonFunctions.clickButtonOrLink(removeLink, "link", "Remove");
				CommonFunctions.handleAlertPopUp();

			}	

		} catch(Exception e){
			fail=true;
			log.error("Exception in Create_SoftGood_Rel_Delete_External()", e);

		}
		return new String[] {SGstrBOMRDelete, SGBOMnameReleasedDelete};
	}


	public static String[] CreateReleasedDelete_BOM(String[] data) throws Exception{
		try{
			if(data[3].contains("CreateReleasedDelete")){
				CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				CommonProjectFunctions.clickSpecificationTab(data[6]);

				//strSource=AddSource(data);
				//Select the Hasbro Internal Source
				Select selectsource= new Select(driver.findElement(selectSource));
				List<WebElement> sourcelist= selectsource.getOptions();

				int sourcelistcount =sourcelist.size();
				log.info("The size of source drop down list is: " + sourcelistcount);


				int internalsourcecount= sourcelistcount -1;

				//Select source dropdown
				selectsource.selectByIndex(internalsourcecount);
				CommonFunctions.handleAlertPopUp();

				strSpec=AddSpecification(data);
				strCW=AddColorway(data);

				CommonProjectFunctions.clickMaterialsTab();
				//Click Add New BOM tab
				CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");
				//Enter BOM Type
				CommonFunctions.enterTextInTextbox(BOMTypeId, data[2]);

				//Click Initialize BOM
				CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
				BOMname="Released"+CommonFunctions.getRandomString(4);
				//Create BOM page
				BOMnameReleased = fillCreateBOM(data);
				//Switch to mainframe
				driver.switchTo().frame("mainFrame");
				//Component or Location
				action = new Actions(driver);
				action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
				//Quanity
				action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputquantity, data[10]);
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				strBOMRDelete=new Select(driver.findElement(BOMId)).getFirstSelectedOption().getText(); 

				System.out.println("BOM name before trim: " + strBOMRDelete);


				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");

				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				//	driver.switchTo().frame("mainFrame");
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				if(data[3].contains("CreateReleasedDelete")){
					valULCSAfterChange=changeStatusReleased();
				}

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//	CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				CommonProjectFunctions.clickSpecificationTab(data[6]);
				//Click on '+' sign of component
				CommonFunctions.clickButtonOrLink(componentPlus, "btn", "Component plus sign");
				//Click on Action
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strBOMRDelete+"')]/preceding::td[2]/a"), "dropdown", "Action");
				CommonFunctions.waitForVisibilityOfElement(removeLink);
				//Click on Remove
				CommonFunctions.clickButtonOrLink(removeLink, "link", "Remove");
				CommonFunctions.handleAlertPopUp();

			}	

		} catch(Exception e){
			fail=true;
			log.error("Exception in CreateReleasedDelete_BOM()", e);

		}
		return new String[] {strBOMRDelete, BOMnameReleased};
	}

	public static String fillCreateBOM(String[] data) throws Exception{
		try{
			InternalBOMSoftG.BOMname="InWork"+CommonFunctions.getRandomString(4);
			System.out.println("BOM Name entered is: " + InternalBOMSoftG.BOMname);
			CommonFunctions.waitForVisibilityOfElement(headingCreateBOM);
			if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal")))
			{	CommonFunctions.enterTextInTextbox(name,BOMname);
			//Select colorway
			CommonFunctions.selectFromDropDownByIndex(colorway, 1);
			//Select Wave
			CommonFunctions.selectFromDropDownByVisibleText(wave, data[12]);
			//Select Currency
			CommonFunctions.selectFromDropDownByVisibleText(currency, data[11]);
			//click on Create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			}
			else if((data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods"))){
				CommonFunctions.enterTextInTextbox(name,BOMname);
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
			return "";
		}
		return BOMname;
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
				CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
				strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			else
			{
				AddSourcingConfiguration(data);
				CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
				strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
			return "";
		}
		return strSource;
	}

	public static String AddSpecification(String[] data) throws Exception{
		try{
			//Add Specification
			wait.until(ExpectedConditions.visibilityOfElementLocated(selectSpecification));
			CommonFunctions.waitForPageLoaded();
			Select dropDownSpec = new Select(SeleniumDriver.driver.findElement(selectSpecification));
			List<WebElement> elementCount = dropDownSpec.getOptions();
			int count =elementCount.size();
			//	int count =SeleniumDriver.driver.findElements(selectSpecification).size();
			if(count>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
				strSpec=new Select(driver.findElement(selectSpecification)).getFirstSelectedOption().getText();
			}
			else
			{
				strSpec= CommonProjectFunctions.Create_Specifications(data[11],data[12]);
				CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
				strSpec=new Select(driver.findElement(selectSpecification)).getFirstSelectedOption().getText();
			}
			log.info("Specification is: "+strSpec);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreateBOM()", e);
		    throw e;
		}
		return strSpec;
	}

	public static String AddColorway(String[] data) throws Exception{
		try{
			//Add colorway
			wait.until(ExpectedConditions.visibilityOfElementLocated(colorwayName));
			CommonFunctions.waitForPageLoaded();
			Select dropDownCW = new Select(SeleniumDriver.driver.findElement(colorwayName));
			List<WebElement> elementCountCW = dropDownCW.getOptions();
			int countCW =elementCountCW.size();
			//	int count =SeleniumDriver.driver.findElements(selectSpecification).size();
			if(countCW>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
				strCW=new Select(driver.findElement(colorwayName)).getFirstSelectedOption().getText();
			}
			else
			{
				strCW= Create_Colorway(data);
			}
			log.info("Colorway is: "+strCW);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddColorway()", e);
		    throw e;
		}
		return strCW;
	}

	public static boolean readView_BOM(String[] data) throws Exception{
		try{
			if(data[3].contains("ReadView")&& data[4].equalsIgnoreCase("Yes")){

				if (data[21].contains("No")){

					navigateToMaterialTab(data);

					strBOMDetail = driver.findElement(BOMDetails).getText();
					//Select Released BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					Assert.assertEquals(strBOMDetail, "BOM Details");					
				}

				else if (data[20].contains("Yes")){

					navigateToMaterialTab(data);

					strBOMDetail = driver.findElement(BOMDetails).getText();
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
					CommonFunctions.handleAlertPopUp();

					log.info("In Work BOM is visible to User");

					Assert.assertEquals(strBOMDetail, "BOM Details");				

				}

				else{
					navigateToMaterialTab(data);

					strBOMDetail = driver.findElement(BOMDetails).getText();
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					log.info("In Work BOM is visible to User");

					Assert.assertEquals(strBOMDetail, "BOM Details");

				}

			}
			else if(data[3].contains("ReadView")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else {
					navigateToMaterialTab(data);				
					strBOMDetail = driver.findElement(BOMDetails).getText();
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					Assert.assertEquals(driver.findElements(BOMDetails).size(), 0, "BOM detail tab is not present"); 
				}

			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in readView_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean update_BOM(String[] data) throws Exception{
		try{

			if(data[3].contains("Update") && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);

				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
				CommonFunctions.handleAlertPopUp();

				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				//CommonFunctions.waitForVisibilityOfElement(headingEditBOM);
				//	driver.switchTo().frame("mainFrame");
				String strEditPage = driver.findElement(headingEditBOM).getText();
				strEditPage=strEditPage.substring(0, 8);
				Assert.assertEquals(strEditPage, "Edit BOM");
				log.info("Edit BOM page is displayed");

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}

				//Capture the Wave attribute value
				String wavevalueineditmode= selectwave.getFirstSelectedOption().getText();

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				//Capture the wave attribute value in detail page
				String wavevaluedetailpage= driver.findElement(ROWave).getText();

				Assert.assertEquals(wavevalueineditmode, wavevaluedetailpage);

			}
			else if(data[3].contains("Update") && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (driver.findElements(addNewBOMTab).size()>0){

							CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
							CommonFunctions.handleAlertPopUp();

							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							log.info("Is Update Button Disable: " + strUpdate );

							Assert.assertEquals(strUpdate, "true");							

						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){

					navigateToMaterialTab(data);
					//Select Released BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					log.info("Is Update Button Disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");						

				}

				else {

					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					log.info("Is Update Button Disable" + strUpdate );

					Assert.assertEquals(strUpdate, "true");		

				}

			}	

		}catch(Exception e){
			fail=true;
			log.error("Exception in update_BOM()", e);
			return false;
		}
		return true;
	}


	public static boolean delete_BOM(String[] data) throws Exception{
		try{
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				CreateInWork_BOM(data); 
				//	CommonProjectFunctions.searchProduct(data[5]);
				//Click on Specification
				CommonProjectFunctions.clickSpecificationTab(data[6]);
				//Click on '+' sign of component
				CommonFunctions.clickButtonOrLink(componentPlus, "btn", "Component plus sign");
				//Click on Action
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strBOMInWork+"')]/preceding::td[2]/a"), "dropdown", "Action");
				CommonFunctions.waitForVisibilityOfElement(removeLink);
				//Click on Remove
				CommonFunctions.clickButtonOrLink(removeLink, "link", "Remove");
				CommonFunctions.handleAlertPopUp();

				CommonProjectFunctions.clickMaterialsTab();
				CommonFunctions.handleAlertPopUp();
				//Code for selecting the Hasbro Internal Source
				//Get the size of source dropdown
				Select selectsource= new Select(driver.findElement(selectSource));
				List<WebElement> sourcelist= selectsource.getOptions();

				int sourcelistcount =sourcelist.size();
				log.info("The size of source drop down list is: " + sourcelistcount);


				int internalsourcecount= sourcelistcount -1;

				//Select source dropdown
				selectsource.selectByIndex(0);
				CommonFunctions.handleAlertPopUp();

				//Switch to default frame
				driver.switchTo().defaultContent();

				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMInWork);

				if(driver.findElements(checkedOutByYou).size()>0){
					CommonFunctions.clickButtonOrLink(BOMAction, "btn", "Action dropdown");
					//Check In
					driver.findElement(By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table/tbody//a[contains(text(),'Check In')]")).click();
				}
				strBillOfMaterials = driver.findElement(billOfMaterials).getText();
				CommonFunctions.clickButtonOrLink(BOMAction, "btn", "Action dropdown");
				//CommonFunctions.clickButtonOrLink(deleteSpec, "option", "Delete Spec");
				driver.findElement(By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table/tbody//a[contains(text(),'Delete')]")).click();
				//	Thread.sleep(3000);
				CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete"); 
				CommonFunctions.handleAlertPopUp();
				Select select = new Select(driver.findElement(BOMId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strBillOfMaterials,options);

				log.info("BOM value after delete is: "+bVal);
				//	dropDownOptionVerificationverifying deleted one is not present in dropdown
				Assert.assertFalse(bVal);
				log.info("BOM has been deleted is:  "+strBillOfMaterials);
			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){

							Select selectbom = new Select(driver.findElement(BOMId));
							List<WebElement> deletedsoftgoodBOM=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(SGstrBOMRDelete, deletedsoftgoodBOM);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);			

						}

						else{

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);

						}
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}
				}


				else{
					CommonProjectFunctions.searchProduct(data[5]);
					//Click on Specification
					CommonProjectFunctions.clickSpecificationTab(data[6]);
					CommonProjectFunctions.clickMaterialsTab();

					//Unselect Source
					CommonFunctions.selectFromDropDownByIndex(selectSource, 0);

					//Switch to default frame
					driver.switchTo().defaultContent();

					//Switch to contentFrame
					driver.switchTo().frame("contentframe");

					//Select Release BOM whcih is removed from Specification
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMRDelete);

					CommonFunctions.clickButtonOrLink(BOMAction, "btn", "Action dropdown");

					System.out.println("Action menu clicked");

					String bDelete= driver.findElement(By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table/tbody//a[contains(text(),'Delete')]")).getAttribute("disabled");

					//	dropDownOptionVerification : verifying deleted one is not present in drop down 
					System.out.println("Does Delete button disable: " + bDelete);

					Assert.assertEquals("true", bDelete);
				}

			}


		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_BOM()", e);
			return false;
		}
		return true;
	}


	public static boolean InWorkreadView_BOM(String[] data) throws Exception{
		try{

			if(data[3].contains("inWorkReadView")&& data[4].equalsIgnoreCase("Yes")){

				if (data[20].equalsIgnoreCase("Yes")){				
					navigateToMaterialTab(data);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
					CommonFunctions.handleAlertPopUp();

					log.info("In Work BOM visible" + SGstrBOMInWork);

				}

				else {
					//Select In Work BOM
					navigateToMaterialTab(data);

					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					log.info("Is In Work BOM visible" + strBOMIW);

					//				//Click on + sign
					//				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					//				String BILabel=driver.findElement(BOMIdentification).getText();
					//				Assert.assertEquals(BILabel.trim(), "BOM Identification");
					//
					//				String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
					//				Assert.assertEquals(GALabel, " General Attributes:");
					//
					//				if(!data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){
					//					String BCSLabel=driver.findElement(BOMCostSummary).getText();
					//					Assert.assertEquals(BCSLabel.trim(), "BOM Cost Summary:");
					//				}
					//				String BOMCommLabel=driver.findElement(BOMComments).getText();
					//				Assert.assertEquals(BOMCommLabel.trim(), "Comments:");
				}
			}
			else if(data[3].contains("inWorkReadView") && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else{

					//Select In Work BOM
					navigateToMaterialTab(data);	

					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMIW,options);
					log.info("Is In Work BOM visible: " + bVal);

					Assert.assertFalse(bVal);

				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in readView_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean InWorkUpdate_BOM(String[] data) throws Exception{
		try{

			if(data[3].contains("inWorkUpdate") && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);			
				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
				CommonFunctions.handleAlertPopUp();

				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				//	driver.switchTo().frame("mainFrame");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				//	System.out.println(driver.findElements(name).size());
				//Verification
				if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods")))
				{	Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable");
				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				}
				else if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")||data[2].contains("BOM\\Materials\\Product\\Product\\Vendor"))
				{	System.out.println(driver.findElements(ROname).size());
				Assert.assertEquals(driver.findElements(ROname).size(), 1, "BOM Identification Name is derived"); 

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}

				//Capture the Wave attribute value
				String wavevalueineditmode= selectwave.getFirstSelectedOption().getText();

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				//Capture the wave attribute value in detail page
				String wavevaluedetailpage= driver.findElement(ROWave).getText();

				Assert.assertEquals(wavevalueineditmode, wavevaluedetailpage);

				}

			}

			else if(data[3].contains("inWorkUpdate") && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){

							CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
							CommonFunctions.handleAlertPopUp();

							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							log.info("Is In Work BOM update disable: " + strUpdate);

							Assert.assertEquals(strUpdate, "true");		

						}
						else {
							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){

					navigateToMaterialTab(data);
					CommonFunctions.handleAlertPopUp();	

					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMIW,options);
					log.info("Is In Work BOM visible: " + bVal);

					Assert.assertFalse(bVal);

				}

				else {
					navigateToMaterialTab(data);			
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					log.info("Is In Work BOM update disable: " + strUpdate);

					Assert.assertEquals(strUpdate, "true");
				}	
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in InWorkUpdate_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean underReviewReadView(String[] data) throws Exception{
		try{

			if(data[3].contains("UnderReviewReadView")  && data[4].equalsIgnoreCase("Yes")){

				if (data[20].equalsIgnoreCase("Yes")){				
					navigateToMaterialTab(data);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMUR);
					CommonFunctions.handleAlertPopUp();

					log.info("Under Review BOM visible" + SGstrBOMUR);

				}

				else {
					navigateToMaterialTab(data);

					//Select Under review BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMUR);
					CommonFunctions.handleAlertPopUp();

					log.info("Under Review BOM visible");

					//				//Click on + sign
					//				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					//				String BILabel=driver.findElement(BOMIdentification).getText();
					//				Assert.assertEquals(BILabel.trim(), "BOM Identification");
					//
					//				String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
					//				Assert.assertEquals(GALabel, " General Attributes:");
					//
					//				if(!data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){
					//					String BCSLabel=driver.findElement(BOMCostSummary).getText();
					//					Assert.assertEquals(BCSLabel.trim(), "BOM Cost Summary:");
					//				}
					//
					//				String BOMCommLabel=driver.findElement(BOMComments).getText();
					//				Assert.assertEquals(BOMCommLabel.trim(), "Comments:");
				}

			}
			else if(data[3].contains("UnderReviewReadView") &&  data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}


				else{

					//Select In Work BOM
					navigateToMaterialTab(data);	

					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMIW,options);
					log.info("Is In Under Review BOM visible" + bVal);

					Assert.assertFalse(bVal);

				}

			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in readView_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean underReviewUpdate(String[] data) throws Exception{
		try{

			if(data[3].contains("UnderReviewReadUpdate") && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);

				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMUR);
				CommonFunctions.handleAlertPopUp();

				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				//	driver.switchTo().frame("mainFrame");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				//Verification
				if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods")))
				{	Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable");

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				}
				else if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")||data[2].contains("BOM\\Materials\\Product\\Product\\Vendor"))
				{	System.out.println(driver.findElements(ROname).size());
				Assert.assertEquals(driver.findElements(ROname).size(), 1, "BOM Identification Name is derived"); 

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}

				//Capture the Wave attribute value
				String wavevalueineditmode= selectwave.getFirstSelectedOption().getText();

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				//Capture the wave attribute value in detail page
				String wavevaluedetailpage= driver.findElement(ROWave).getText();

				Assert.assertEquals(wavevalueineditmode, wavevaluedetailpage);

				}	

			}
			else if(data[3].contains("UnderReviewReadUpdate")  && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){

							CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMUR);
							CommonFunctions.handleAlertPopUp();

							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							log.info("Is In Work BOM update disable: " + strUpdate);

							Assert.assertEquals(strUpdate, "true");		

						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}					

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){
					navigateToMaterialTab(data);

					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMUR,options);
					log.info("Is Under Review BOM visible: " + bVal );

					Assert.assertFalse(bVal);

				}

				else {
					navigateToMaterialTab(data);

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMUR);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					log.info("Is Under Review BOM Update is disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");
				}	

			}	
		}catch(Exception e){
			fail=true;
			log.error("Exception in update_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean releasedReadView(String[] data) throws Exception{
		try{

			if(data[3].contains("ReleasedReadView") && data[4].equalsIgnoreCase("Yes")){

				if (data[20].equalsIgnoreCase("Yes")){				
					navigateToMaterialTab(data);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMR);
					CommonFunctions.handleAlertPopUp();

					log.info("Released BOM visible" + SGstrBOMR);

				}

				else {

					navigateToMaterialTab(data);
					//Select Under review BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					log.info("Released BOM visible");

					//				//Click on + sign
					//				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					//
					//				String BILabel=driver.findElement(BOMIdentification).getText();
					//				Assert.assertEquals(BILabel.trim(), "BOM Identification");

					//				String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
					//				Assert.assertEquals(GALabel, " General Attributes:");
					//
					//				if(!data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){
					//					String BCSLabel=driver.findElement(BOMCostSummary).getText();
					//					Assert.assertEquals(BCSLabel.trim(), "BOM Cost Summary:");
					//				}
					//
					//				String BOMCommLabel=driver.findElement(BOMComments).getText();
					//				Assert.assertEquals(BOMCommLabel.trim(), "Comments:");
				}
			}
			else if(data[3].contains("ReleasedReadView") && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else {

					navigateToMaterialTab(data);

					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMR,options);

					Assert.assertFalse(bVal);

					log.info("Is Released BOM Visible: " + bVal);

				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in ReleasedReadView()", e);
			return false;
		}
		return true;
	}

	public static boolean releasedUpdate(String[] data) throws Exception{
		try{

			if(data[3].contains("ReleasedUpdate")  && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);

				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
				CommonFunctions.handleAlertPopUp();

				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				//driver.switchTo().frame("mainFrame");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				System.out.println(driver.findElements(name).size());
				//Verification
				//Verification
				if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods")))
				{	Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable");

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				}
				else if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")||data[2].contains("BOM\\Materials\\Product\\Product\\Vendor"))
				{	System.out.println(driver.findElements(ROname).size());
				Assert.assertEquals(driver.findElements(ROname).size(), 1, "BOM Identification Name is derived"); 

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}

				//Capture the Wave attribute value
				String wavevalueineditmode= selectwave.getFirstSelectedOption().getText();

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				//Capture the wave attribute value in detail page
				String wavevaluedetailpage= driver.findElement(ROWave).getText();

				Assert.assertEquals(wavevalueineditmode, wavevaluedetailpage);

				}

			}
			else if(data[3].contains("ReleasedUpdate")  && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){


							CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMR);
							CommonFunctions.handleAlertPopUp();

							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							log.info("Is In Work BOM update disable: " + strUpdate);

							Assert.assertEquals(strUpdate, "true");		

						}

						else {
							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}
					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else {
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");

					log.info("Is Released BOM Update is disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");
				}	

			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in update_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean cancelledReadView(String[] data) throws Exception{
		try{

			if(data[3].contains("CancelledReadView")  && data[4].equalsIgnoreCase("Yes")){

				navigateToMaterialTab(data);

				//Select Under review BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMC);
				CommonFunctions.handleAlertPopUp();

				log.info("Cancelled BOM visible");

				//				//Click on + sign
				//				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				//				String BILabel=driver.findElement(BOMIdentification).getText();
				//				Assert.assertEquals(BILabel.trim(), "BOM Identification");
				//
				//				String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
				//				Assert.assertEquals(GALabel, " General Attributes:");
				//
				//				if(!data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){
				//					String BCSLabel=driver.findElement(BOMCostSummary).getText();
				//					Assert.assertEquals(BCSLabel.trim(), "BOM Cost Summary:");
				//				}
				//
				//				String BOMCommLabel=driver.findElement(BOMComments).getText();
				//				Assert.assertEquals(BOMCommLabel.trim(), "Comments:");

			}
			else if(data[3].contains("CancelledReadView")  && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){


							Select selectbom = new Select(driver.findElement(BOMId));
							List<WebElement> deletedsoftgoodBOM=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(SGstrBOMC, deletedsoftgoodBOM);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);			

						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else {

					navigateToMaterialTab(data);
					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMC,options);
					log.info("Is Cancelled BOM visible: " + bVal );

					Assert.assertFalse(bVal);

				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in readView_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean cancelledUpdate(String[] data) throws Exception{
		try{

			if(data[3].contains("CancelledReadUpdate") && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);
				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMC);
				CommonFunctions.handleAlertPopUp();

				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				//	driver.switchTo().frame("mainFrame");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				//Verification
				//Verification
				if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods")))
				{	Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable");

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");		

				}
				else if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")||data[2].contains("BOM\\Materials\\Product\\Product\\Vendor"))
				{	System.out.println(driver.findElements(ROname).size());
				Assert.assertEquals(driver.findElements(ROname).size(), 1, "BOM Identification Name is derived"); 

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}

				//Capture the Wave attribute value
				String wavevalueineditmode= selectwave.getFirstSelectedOption().getText();

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				//Capture the wave attribute value in detail page
				String wavevaluedetailpage= driver.findElement(ROWave).getText();

				Assert.assertEquals(wavevalueineditmode, wavevaluedetailpage);

				}

			}
			else if(data[3].contains("CancelledReadUpdate") && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){

							Select selectbom = new Select(driver.findElement(BOMId));
							List<WebElement> softgoodBOM=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(SGstrBOMC, softgoodBOM);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);	

						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}

					}
					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){

					navigateToMaterialTab(data);
					CommonFunctions.handleAlertPopUp();

					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMC,options);
					log.info("Is Cancelled BOM is visible: " + bVal );

					Assert.assertFalse(bVal);

				}

				else {
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMC);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");

					log.info("Is Cancelled BOM Update is disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");

				}
			}	

		}catch(Exception e){
			fail=true;
			log.error("Exception in update_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean rejectedReadView(String[] data) throws Exception{
		try{			

			if(data[3].contains("RejectedReadView") && data[4].equalsIgnoreCase("Yes")){

				navigateToMaterialTab(data);
				strBOMDetail = driver.findElement(BOMDetails).getText();

				//Select Under review BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMRj);
				CommonFunctions.handleAlertPopUp();

				log.info("Rejected BOM visible");

				//				//Click on + sign
				//				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				//
				//				String BILabel=driver.findElement(BOMIdentification).getText();
				//				Assert.assertEquals(BILabel.trim(), "BOM Identification");
				//
				//				String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
				//				Assert.assertEquals(GALabel, " General Attributes:");
				//
				//				if(!data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){
				//					String BCSLabel=driver.findElement(BOMCostSummary).getText();
				//					Assert.assertEquals(BCSLabel.trim(), "BOM Cost Summary:");
				//				}
				//
				//				String BOMCommLabel=driver.findElement(BOMComments).getText();
				//				Assert.assertEquals(BOMCommLabel.trim(), "Comments:");

			}
			else if(data[3].contains("RejectedReadView") && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){


							Select selectbom = new Select(driver.findElement(BOMId));
							List<WebElement> deletedsoftgoodBOM=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(SGstrBOMRj, deletedsoftgoodBOM);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);			

						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else {

					//Browse to Material tab by selecting season, source, spec
					navigateToMaterialTab(data);
					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMRj,options);
					log.info("Is Rejected BOM visible: " + bVal);

					Assert.assertFalse(bVal);
				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in readView_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean rejectedUpdate(String[] data) throws Exception{
		try{

			if(data[3].contains("RejectedReadUpdate") && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);
				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMRj);
				CommonFunctions.handleAlertPopUp();

				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				//	driver.switchTo().frame("mainFrame");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				//Verification
				if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")|| (data[2].contains("BOM\\Materials\\Product\\Product\\Internal"))|| (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Soft Goods")))
				{	Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable");

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				}
				else if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")||data[2].contains("BOM\\Materials\\Product\\Product\\Vendor"))
				{	System.out.println(driver.findElements(ROname).size());
				Assert.assertEquals(driver.findElements(ROname).size(), 1, "BOM Identification Name is derived"); 

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}

				//Capture the Wave attribute value
				String wavevalueineditmode= selectwave.getFirstSelectedOption().getText();

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				//Capture the wave attribute value in detail page
				String wavevaluedetailpage= driver.findElement(ROWave).getText();

				Assert.assertEquals(wavevalueineditmode, wavevaluedetailpage);

				}

			}
			else if(data[3].contains("RejectedReadUpdate") && data[4].equalsIgnoreCase("No")){


				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){

							Select selectbom = new Select(driver.findElement(BOMId));
							List<WebElement> softgoodBOM=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(SGstrBOMRj, softgoodBOM);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);


						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){

					navigateToMaterialTab(data);
					Select select = new Select(driver.findElement(BOMId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strBOMRj,options);
					log.info("Is Rejected BOM visible: " + bVal );

					Assert.assertFalse(bVal);

				}

				else {					
					//Select Rejected BOM	
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMRj);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					log.info("Is RejectedBOM Update is disable: " + strUpdate );
					Assert.assertEquals(strUpdate, "true");
				}	

			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in rejectedUpdate()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{

			if(data[3].contains("GeneralAttributesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				//	CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				if (data[21].contains("No")){
					navigateToMaterialTab(data);				
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();				
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");
					}else{
						log.error("General Attributes label is Absent");
						fail=true;
					}

				}

				else if (data[20].contains("Yes")){

					navigateToMaterialTab(data);				
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");
					}else{
						log.error("General Attributes label is Absent");
						fail=true;
					}


				}


				else{

					navigateToMaterialTab(data);				
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();				
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");
					}else{
						log.error("General Attributes label is Absent");
						fail=true;
					}
				}
			}
			else if(data[3].contains("GeneralAttributesRead_View")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else{
					navigateToMaterialTab(data);				
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						System.out.println("General Attirbutes label is Present");
						log.error("General Attirbutes label is Present");
						fail=true;
					}else{
						log.info("General Attirbutes label is Absent");
					}
				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyGeneralAttributesReadView()", e);
			return false;
		}
		return true;
	}
	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			if(data[3].contains("GeneralAttributesUpdate") && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);

				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
				CommonFunctions.handleAlertPopUp();

				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				//	driver.switchTo().frame("mainFrame");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				//Identify the Wave attribute value
				Select selectwave= new Select(driver.findElement(wave));
				String wavename = selectwave.getFirstSelectedOption().getText();

				if (wavename.equals(data[11])){
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[19]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(wave, data[11]);					
				}

				//Capture the Wave attribute value
				String wavevalueineditmode= selectwave.getFirstSelectedOption().getText();

				log.info("The wave attribtue value in update UI is: " + wavevalueineditmode);

				//Click 'Save and Check In'
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				driver.switchTo().frame("contentframe");
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				//Capture the wave attribute value in detail page
				String wavevaluedetailpage= driver.findElement(ROWave).getText();

				log.info("The wave attribtue value in detail page is: " + wavevaluedetailpage);


				Assert.assertEquals(wavevalueineditmode, wavevaluedetailpage);			

			}
			else if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){


							CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
							CommonFunctions.handleAlertPopUp();

							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							log.info("Is In Work BOM update disable: " + strUpdate);

							Assert.assertEquals(strUpdate, "true");		

						}

						else {
							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if(data[21].contains("No")){

					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");

					log.info("Is Update button is disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");			

				}

				else{

					navigateToMaterialTab(data);

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");


					log.info("Is Update button is disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");

				}
			}	

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : BOMCostSummary:Read_View
	public static boolean verifyBOMCostingSummaryReadView(String[] data) throws Exception{
		try{


			if(data[3].contains("BOMCostingSummaryReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View

				if (data[21].contains("No")) {

					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					//	CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					if(driver.findElements(BOMCostSummary).size() != 0){
						String BCSLabel=driver.findElement(BOMCostSummary).getText();
						Assert.assertEquals(BCSLabel.trim(), "BOM Cost Summary:");
						log.info("BOMCostingSummaryReadView label is Pass");
					}else{
						log.error("BOMCostingSummaryReadView label is Fail");
						fail=true;
					}			
				}

				else {

					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					//	CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					if(driver.findElements(BOMCostSummary).size() != 0){
						String BCSLabel=driver.findElement(BOMCostSummary).getText();
						Assert.assertEquals(BCSLabel.trim(), "BOM Cost Summary:");
						log.info("BOMCostingSummaryReadView label is Pass");
					}

				}
			}
			else if(data[3].contains("BOMCostingSummaryReadView")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}


				else if (data[21].contains("No")){

					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");	

					if(driver.findElements(BOMCostSummary).size() != 0){
						System.out.println("BOMCostingSummaryReadView label is Present");
						log.error("BOMCostingSummaryReadView label is Present");
						fail=true;
					}else{
						log.info("BOMCostingSummaryReadView label is Absent");
					}


				}

				else {
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");	

					if(driver.findElements(BOMCostSummary).size() != 0){
						System.out.println("BOMCostingSummaryReadView label is Present");
						log.error("BOMCostingSummaryReadView label is Present");
						fail=true;
					}else{
						log.info("BOMCostingSummaryReadView label is Absent");
					}
				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyBOMCostingSummaryReadView()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : CostingSummary://Update
	public static boolean verifyBOMCostingSummaryUpdate(String[] data) throws Exception{
		try{

			if(data[3].contains("BOMCostingSummaryUpdate") && data[4].equalsIgnoreCase("Yes")){//Update

				//Browse to the Material Tab
				navigateToMaterialTab(data);

				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);


				//Click on Update Button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				CommonFunctions.handleAlertPopUp();

				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");


				Assert.assertEquals(driver.findElements(TTotalBOMCost).size(), 0, "TotalBOMCost is editable"); 
				Assert.assertEquals(driver.findElements(TTotalChemicalsCost).size(), 0, "TotalChemicalsCost is editable");
				Assert.assertEquals(driver.findElements(TTotalElectronicsCost).size(), 0, "TotalElectronicsCost is editable");
				Assert.assertEquals(driver.findElements(TTotalGenDecoLaborCost).size(), 0, "TotalGenDecoLaborCost is editable");
				Assert.assertEquals(driver.findElements(TTotalMarkUpCost).size(),0, "TotalMarkUpCost is editable");
				Assert.assertEquals(driver.findElements(TTotalMoldingLaborCost).size(), 0, "TotalMoldingLaborCost is editable");
				Assert.assertEquals(driver.findElements(TTotalPackagingCost).size(), 0, "TotalPackagingCost is editable");
				Assert.assertEquals(driver.findElements(TTotalPlasticCost).size(), 0, "TotalPlasticCost is  not editable");
				Assert.assertEquals(driver.findElements(TTotalPlasticUsageK).size(), 0, "TTotalPlasticUsageK is editable");
				Assert.assertEquals(driver.findElements(TTotalSoftGoodsCost).size(), 0, "TTotalSoftGoodsCost is editable");
				Assert.assertEquals(driver.findElements(TTotalPurchasedPartsCost).size(), 0, "TTotalSoftGoodsCost is editable");

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

			}

			if(data[3].contains("BOMCostingSummaryUpdate") && data[4].equalsIgnoreCase("No")){//Update 

				if (data[21].contains("No")){

					navigateToMaterialTab(data);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to content frame
					driver.switchTo().frame("contentframe");


					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();


					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");

					log.info("Is Update button is disable: " + strUpdate );
					Assert.assertEquals(strUpdate, "true");

				}

				else if(data[20].equalsIgnoreCase("Yes")){

					CommonProjectFunctions.searchProduct(data[5]);

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");

					if(driver.findElements(specificationtab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{

						boolean productexist= CommonFunctions.isElementPresent(specificationtab);

						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else{

					navigateToMaterialTab(data);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to content frame
					driver.switchTo().frame("contentframe");

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);

					//boolean updatebutton= driver.findElement(updateBtn).isEnabled();

					String updatebutton = driver.findElement(updateBtn).getAttribute("disabled");

					System.out.println("Is update button disable or not diable(null): " +updatebutton);


					if(updatebutton != null){

						String updatebttnactive = driver.findElement(updateBtn).getAttribute("disabled");	

						log.info("Update Button is disable " + updatebttnactive);

					}

					else {

						//Click on Update Button
						CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");


						WebDriverWait wait = new WebDriverWait(driver,10);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
						CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

						if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")){

							Assert.assertEquals(driver.findElements(TTotalBOMCost).size(), 1, "TotalBOMCost is  not editable"); 
							Assert.assertEquals(driver.findElements(TTotalChemicalsCost).size(), 1, "TotalChemicalsCost is  not editable");
							Assert.assertEquals(driver.findElements(TTotalElectronicsCost).size(), 1, "TotalElectronicsCost is  not editable");
							Assert.assertEquals(driver.findElements(TTotalGenDecoLaborCost).size(), 1, "TotalGenDecoLaborCost is  not editable");
							Assert.assertEquals(driver.findElements(TTotalMarkUpCost).size(), 1, "TotalMarkUpCost is  not editable");
							Assert.assertEquals(driver.findElements(TTotalMoldingLaborCost).size(), 1, "TotalMoldingLaborCost is  not editable");
							Assert.assertEquals(driver.findElements(TTotalPackagingCost).size(), 1, "TotalPackagingCost is  not editable");
							Assert.assertEquals(driver.findElements(TTotalPlasticCost).size(), 1, "TotalPlasticCost is  not editable");
							Assert.assertEquals(driver.findElements(TTotalPlasticUsageK).size(), 1, "TTotalPlasticUsageK is  not editable");
							Assert.assertEquals(driver.findElements(TTotalSoftGoodsCost).size(), 1, "TTotalSoftGoodsCost is  not editable");
							Assert.assertEquals(driver.findElements(TTotalPurchasedPartsCost).size(), 1, "TTotalSoftGoodsCost is  not editable");

							log.info("User does not have access to update BOM Cost Summary table");

							//Click button btnSaveAndCheckIn
							CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
							CommonFunctions.handleAlertPopUp();
							//Switch to contentFrame
							driver.switchTo().frame("contentframe");

						}

						else if(data[2].contains("BOM\\Materials\\Product\\Product\\Internal")){

							Assert.assertEquals(driver.findElements(TTotalBOMCost).size(), 1, "TotalBOMCost is  not editable"); 
							Assert.assertEquals(driver.findElements(TotalMasterCartonPackagingCost).size(), 1, "TotalMasterCartonPackagingCost is  not editable");
							Assert.assertEquals(driver.findElements(TotalLaborCost).size(), 1, "TotalLaborCost is  not editable");
							Assert.assertEquals(driver.findElements(TotalMiscellaneousCost).size(), 1, "TotalMiscellaneousCost is  not editable");
							Assert.assertEquals(driver.findElements(TotalMarkUpCost).size(), 1, "TotalMarkUpCost is  not editable");

							log.info("User does not have access to update BOM Cost Summary table");

							//Click button btnSaveAndCheckIn
							CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
							CommonFunctions.handleAlertPopUp();
							//Switch to contentFrame
							driver.switchTo().frame("contentframe");
						}

					}

				}
			}	

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyBOMCostingSummaryUpdate()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : BOMCommentsRead:Read_View
	public static boolean verifyBOMCommentsRead(String[] data) throws Exception{
		try{

			if(data[3].contains("BOMCommentsRead")&& data[4].equalsIgnoreCase("Yes")){//Read_View

				if (data[21].contains("No")){

					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(BOMComments).size() != 0){
						String BOMCommLabel=driver.findElement(BOMComments).getText();
						Assert.assertEquals(BOMCommLabel.trim(), "Comments:");
						log.info("BOMComments Read label is Present");
					}else{
						log.error("BOMCommentsRead label is Absent");
						fail=true;
					}

				}

				else if (data[20].contains("Yes")){

					navigateToMaterialTab(data);				
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(BOMComments).size() != 0){
						String BOMCommLabel=driver.findElement(BOMComments).getText();
						Assert.assertEquals(BOMCommLabel.trim(), "Comments:");
						log.info("BOMComments Read label is Present");
					}else{
						log.error("BOMCommentsRead label is Absent");
						fail=true;
					}


				}

				else {

					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(BOMComments).size() != 0){
						String BOMCommLabel=driver.findElement(BOMComments).getText();
						Assert.assertEquals(BOMCommLabel.trim(), "Comments:");
						log.info("BOMComments Read label is Present");
					}else{
						log.error("BOMCommentsRead label is Absent");
						fail=true;
					}
				}
			}

			else if(data[3].contains("BOMCommentsRead")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if(data[21].contains("No")){
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(BOMComments).size() != 0){
						System.out.println("BOMCommentsRead label is Present");
						log.error("BOMCommentsRead label is Present");
						fail=true;
					}else{
						log.info("BOMCommentsRead label is Absent");
					}

				}

				else {
					navigateToMaterialTab(data);
					//Select In Work BOM
					//CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);

					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(BOMComments).size() != 0){
						System.out.println("BOMCommentsRead label is Present");
						log.error("BOMCommentsRead label is Present");
						fail=true;
					}else{
						log.info("BOMCommentsRead label is Absent");
					}
				}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyBOMCommentsRead()", e);
			return false;
		}
		return true;
	}
	//Function consist scenario : BOMComments://Update
	public static boolean verifyBOMCommentsUpdate(String[] data) throws Exception{
		try{
			if(data[3].contains("BOMCommentsUpdate") && data[4].equalsIgnoreCase("Yes")){//Update

				if (data[20].contains("Yes")){

					navigateToMaterialTab(data);				
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					//Click on Enter Comment
					CommonFunctions.clickButtonOrLink(EnterComments, "link", "Enter Comment");
					CommonFunctions.enterTextInTextbox(PostNewComment, data[15]);
					CommonFunctions.clickButtonOrLink(post, "btn", "Post Button");

					Thread.sleep(2000);

					//Find comment
					String enteredComment = driver.findElement(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr//td[text()='"+data[15]+"']")).getText();

					System.out.println("The value of entered comment" + enteredComment);
					Assert.assertEquals(enteredComment, data[15]);
				}


				else {

					navigateToMaterialTab(data);

					//Select In Work BOM
					//CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);

					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					//Click on Enter Comment
					CommonFunctions.clickButtonOrLink(EnterComments, "link", "Enter Comment");
					CommonFunctions.enterTextInTextbox(PostNewComment, data[15]);
					CommonFunctions.clickButtonOrLink(post, "btn", "Post Button");

					Thread.sleep(2000);

					//Find comment
					String enteredComment = driver.findElement(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr//td[text()='"+data[15]+"']")).getText();

					System.out.println("The value of entered comment" + enteredComment);
					Assert.assertEquals(enteredComment, data[15]);
				}

			}

			else if(data[3].contains("BOMCommentsUpdate") && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){


							CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMR);
							CommonFunctions.handleAlertPopUp();

							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							log.info("Is In Work BOM update disable: " + strUpdate);

							Assert.assertEquals(strUpdate, "true");		

						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}

					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){

					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					boolean entercommentpresent =CommonFunctions.isElementPresent(EnterComments);
					System.out.println("Is Enter comments option is present in Comment table" + entercommentpresent);

					Assert.assertFalse(entercommentpresent);
				}

				else {
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					Assert.assertEquals(strUpdate, "true");

				}
			}	

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : BOMSectionsRead:Read_View
	public static boolean verifyBOMSectionsRead(String[] data) throws Exception{
		try{
			//Select In Work BOM
			if(data[3].contains("BOMSectionsRead")&& data[4].equalsIgnoreCase("Yes")){//Read_View

				if (data[21].contains("No")){

					navigateToMaterialTab(data);
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(descHeading).size() != 0){
						String descHeadLabel=driver.findElement(descHeading).getText();
						Assert.assertEquals(descHeadLabel.trim(), "Description");
						log.info("BOMSectionsRead label is Present");
					}else{
						log.error("BOMSectionsRead label is Absent");
						fail=true;
					}

				}

				else if (data[20].contains("Yes")){

					navigateToMaterialTab(data);				
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(descHeading).size() != 0){
						String descHeadLabel=driver.findElement(descHeading).getText();
						Assert.assertEquals(descHeadLabel.trim(), "Description");
						log.info("BOMSectionsRead label is Present");
					}else{
						log.error("BOMSectionsRead label is Absent");
						fail=true;
					}


				}

				else {
					navigateToMaterialTab(data);
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					if(driver.findElements(descHeading).size() != 0){
						String descHeadLabel=driver.findElement(descHeading).getText();
						Assert.assertEquals(descHeadLabel.trim(), "Description");
						log.info("BOMSectionsRead label is Present");
					}else{
						log.error("BOMSectionsRead label is Absent");
						fail=true;
					}
				}
			}
			else if(data[3].contains("BOMSectionsRead")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){

					navigateToMaterialTab(data);
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");


					if(driver.findElements(descHeading).size() != 0){
						System.out.println("BOMSections label is Present");
						log.error("BOMSectionsRead label is Present");
						fail=true;
					}else{
						log.info("BOMSectionsRead label is Absent");
					}

				}

				else {

					navigateToMaterialTab(data);
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");


					if(driver.findElements(descHeading).size() != 0){
						System.out.println("BOMSections label is Present");
						log.error("BOMSectionsRead label is Present");
						fail=true;
					}else{
						log.info("BOMSectionsRead label is Absent");
					}

				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyBOMSectionsRead()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : BOMSections://Update
	public static boolean verifyBOMSectionsUpdate(String[] data) throws Exception{
		try{
			if(data[3].contains("BOMSectionsUpdate")  && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);

				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
				CommonFunctions.handleAlertPopUp();
				//	if(data[4].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");

				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Internal")){

					//Click on Plastic Clear
					CommonFunctions.clickButtonOrLink(plasticClear,"link", "Clear for Plastic");
					//Component or Location
					action = new Actions(driver);
					action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
					CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
					//Meterial Desc
					action.moveToElement(driver.findElement(meterial)).doubleClick().perform();
					CommonFunctions.enterTextInTextbox(inputMaterial, data[16]);

					//Click on Chemicals Clear
					CommonFunctions.clickButtonOrLink(chemicalClear,"link", "Clear for Chemicals");
					//Fill Chemical section
					action.moveToElement(driver.findElement(checmicalDesc)).doubleClick().perform();
					CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[17]);
					//Meterial Desc
					action.moveToElement(driver.findElement(meterialRow2)).doubleClick().perform();
					CommonFunctions.enterTextInTextbox(inputMaterial, data[18]);

					//Click 'Save and Check In'
					CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
					CommonFunctions.handleAlertPopUp();
					WebDriverWait wait1 = new WebDriverWait(driver,10);
					wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("contentframe"));
					//Switch to contentFrame
					//	driver.switchTo().frame("contentframe");
					//Select BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					//Click on 'Click to select a view'
					CommonFunctions.clickButtonOrLink(viewBtn, "img", "View");
					//Click on none
					CommonFunctions.clickButtonOrLink(NoneBtn, "lnk", "--None--");

					CommonFunctions.waitForVisibilityOfElement(plasticDescription);
					String strplasticDescription=driver.findElement(plasticDescription).getText();
					Assert.assertEquals(strplasticDescription, data[9]);

					String strplasticMeterialDesc=driver.findElement(plasticMeterialDesc).getText();
					Assert.assertEquals(strplasticMeterialDesc, data[16]);

					String strchemicalsDescription=driver.findElement(chemicalsDescription).getText();
					Assert.assertEquals(strchemicalsDescription, data[17]);

					String strchemicalsMeterialDesc=driver.findElement(chemicalsMeterialDesc).getText();
					Assert.assertEquals(strchemicalsMeterialDesc, data[18]);
				}

				else if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){

					//Click on Fabric Clear
					CommonFunctions.clickButtonOrLink(softgoddFabricClear,"link", "Clear for Fabric table");
					//Component or Location
					action = new Actions(driver);
					action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
					CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);

					//Click 'Save and Check In'
					CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
					CommonFunctions.handleAlertPopUp();
					WebDriverWait wait1 = new WebDriverWait(driver,10);
					wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("contentframe"));

					//Select BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					//Click on 'Click to select a view'
					CommonFunctions.clickButtonOrLink(viewBtn, "img", "View");
					//Click on none
					CommonFunctions.clickButtonOrLink(NoneBtn, "lnk", "--None--");

					CommonFunctions.waitForVisibilityOfElement(FabricDescription);
					String strplasticDescription=driver.findElement(FabricDescription).getText();
					Assert.assertEquals(strplasticDescription, data[9]);	

				}

				else if(data[2].contains("BOM\\Materials\\Product\\Product\\Internal")){

					//Click on Plastic Clear
					CommonFunctions.clickButtonOrLink(mastercartonpackagingClear,"link", "Clear for Master Carton Packaging");
					//Component or Location
					action = new Actions(driver);
					action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
					CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);

					//Click 'Save and Check In'
					CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
					CommonFunctions.handleAlertPopUp();
					WebDriverWait wait1 = new WebDriverWait(driver,10);
					wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("contentframe"));
					//Switch to contentFrame
					//	driver.switchTo().frame("contentframe");
					//Select BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					//Click on 'Click to select a view'
					CommonFunctions.clickButtonOrLink(viewBtn, "img", "View");
					//Click on none
					CommonFunctions.clickButtonOrLink(NoneBtn, "lnk", "--None--");

					CommonFunctions.waitForVisibilityOfElement(mastercartonDescription);
					String strplasticDescription=driver.findElement(mastercartonDescription).getText();
					Assert.assertEquals(strplasticDescription, data[9]);

				}	

			}

			else if(data[3].contains("BOMSectionsUpdate") && data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){


							CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMR);
							CommonFunctions.handleAlertPopUp();

							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							log.info("Is In Work BOM update disable: " + strUpdate);

							Assert.assertEquals(strUpdate, "true");		

						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}

					}
					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					log.info("Is Update button is disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");

				}

				else {
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					log.info("Is Update button is disable: " + strUpdate );
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					Assert.assertEquals(strUpdate, "true");

				}
			}	

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyBOMSectionsUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean verifyBOMStatusReadView(String[] data) throws Exception{
		try{					

			if(data[3].contains("BOMStatusRead")&& data[4].equalsIgnoreCase("Yes")){//Read_View

				if (data[21].contains("No")){

					navigateToMaterialTab(data);

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);

					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					//Get the text for BOM Status table
					String strBOMStatusInReadView =driver.findElement(BOMStatusInReadView).getText();

					log.info("The table name is : " + strBOMStatusInReadView);

					Assert.assertEquals(strBOMStatusInReadView.trim(),"BOM Status:");

					log.info("BOM Status attribute value verified:" + BOMStatusInReadView);				

				}

				else if (data[20].contains("Yes")){

					navigateToMaterialTab(data);				
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMInWork);
					CommonFunctions.handleAlertPopUp();

					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					//Get the text for BOM Status table
					String strBOMStatusInReadView =driver.findElement(BOMStatusInReadView).getText();

					log.info("The table name is : " + strBOMStatusInReadView);

					Assert.assertEquals(strBOMStatusInReadView.trim(),"BOM Status:");

					log.info("BOM Status attribute value verified:" + BOMStatusInReadView);


				}

				else{
					navigateToMaterialTab(data);

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);

					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					//Get the text for BOM Status table
					String strBOMStatusInReadView =driver.findElement(BOMStatusInReadView).getText();

					log.info("The table name is : " + strBOMStatusInReadView);

					Assert.assertEquals(strBOMStatusInReadView.trim(),"BOM Status:");

					log.info("BOM Status attribute value verified:" + BOMStatusInReadView);

				}
			}
			else if(data[3].contains("BOMStatusRead")&& data[4].equalsIgnoreCase("No"))

			{
				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){

					navigateToMaterialTab(data);

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);

					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					//Verify that BOM status table is not present
					boolean Bomstatustable= CommonFunctions.isElementPresent(BOMStatusInReadView);
					Assert.assertFalse(Bomstatustable);

				}

				else{

					navigateToMaterialTab(data);

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);

					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					//Verify that BOM status table is not present
					boolean Bomstatustable= CommonFunctions.isElementPresent(BOMStatusInReadView);
					Assert.assertFalse(Bomstatustable);

				}

			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyBOMStatusReadView()", e);
			return false;
		}
		return true;
	}


	public static boolean verifyBOMStatusUpdate(String[] data) throws Exception{
		try{
			if(data[3].contains("BOMStatusUpdate") && data[4].equalsIgnoreCase("Yes")){//Update

				navigateToMaterialTab(data);

				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
				///CommonFunctions.selectFromDropDownByVisibleTextUpdated
				CommonFunctions.handleAlertPopUp();

				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe

				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				//Select Rejected BOM status from drop down
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatusupdate, "Rejected");

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				String bomstatusvalue= driver.findElement(Bomstatusdetailpage).getText();

				Assert.assertEquals("Rejected" , bomstatusvalue);

				//Revert the BOM status back to In Work

				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				//	driver.switchTo().frame("mainFrame");
				WebDriverWait wait1 = new WebDriverWait(driver,10);
				wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				//Select Rejected BOM status from drop down
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatusupdate, "In Work");

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

				String bomstatusvalue1= driver.findElement(Bomstatusdetailpage).getText();

				Assert.assertEquals("In Work" , bomstatusvalue1);

			}
			else if(data[3].contains("BOMStatusUpdate")&& data[4].equalsIgnoreCase("No"))
			{

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						if (data[2].equalsIgnoreCase("BOM\\Materials\\Product\\Retail Item\\Soft Goods")){

							driver.switchTo().defaultContent();

							//Switch to Content frame:					
							driver.switchTo().frame("contentframe");

							CommonFunctions.selectFromDropDownByVisibleText(BOMId, SGstrBOMR);
							CommonFunctions.handleAlertPopUp();

							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							log.info("Is In Work BOM update disable: " + strUpdate);

							Assert.assertEquals(strUpdate, "true");		

						}

						else {

							//Click Add New BOM tab
							CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

							Select selectbom = new Select(driver.findElement(BOMTypeId));
							List<WebElement> bomtype=  selectbom.getOptions();
							boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

							log.info("Is Internal BOM Type exist: " + bomtypepresent);

							Assert.assertFalse(bomtypepresent);
						}
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if (data[21].contains("No")){

					navigateToMaterialTab(data);

					//Select Rejected BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);

					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");

					log.info("Is Update button is disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");					
				}

				else{
					navigateToMaterialTab(data);

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					log.info("Is Update button is disable: " + strUpdate );

					Assert.assertEquals(strUpdate, "true");				
				}

			}	

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyBOMStatusUpdate()", e);
			return false;
		}
		return true;
	}		

	public static boolean verifyBOMMatchCostSummaryReadView(String[] data) throws Exception
	{
		try{
			if(data[3].contains("BOMMatchCostSummaryReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View

				if(data[21].contains("No")){

					navigateToMaterialTab(data);

					//Select Released BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");			

					//Verify BOM Match Cost Summary table 
					String BCSLabel=driver.findElement(BOMMatchCostSummary).getText();

					log.info("BOM Match Cost Summary Table is present");
					Assert.assertEquals(BCSLabel.trim(), "BOM Match Cost Summary:");

				}

				else{
					navigateToMaterialTab(data);

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");			

					//Verify BOM Match Cost Summary table 
					String BCSLabel=driver.findElement(BOMMatchCostSummary).getText();
					Assert.assertEquals(BCSLabel.trim(), "BOM Match Cost Summary:");
				}
			}
			else if(data[3].contains("BOMCostingSummaryReadView")&& data[4].equalsIgnoreCase("No")){

				if(data[20].equalsIgnoreCase("Yes")){
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to defail frame
					driver.switchTo().defaultContent();

					//Switch to Content frame:					
					driver.switchTo().frame("contentframe");

					boolean productexist= CommonFunctions.isElementPresent(productdetailstab);

					if(driver.findElements(productdetailstab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{
						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else if(data[21].contains("No")){

					navigateToMaterialTab(data);
					//Select Released BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					boolean bommatchcostsummarypresent =CommonFunctions.isElementPresent(BOMMatchCostSummary);				
					Assert.assertFalse(bommatchcostsummarypresent);

				}

				else{
					navigateToMaterialTab(data);
					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
					CommonFunctions.handleAlertPopUp();
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

					boolean bommatchcostsummarypresent =CommonFunctions.isElementPresent(BOMMatchCostSummary);				
					Assert.assertFalse(bommatchcostsummarypresent);				
				}

			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyBOMCostingSummaryReadView()", e);
			return false;
		}

		return true;
	}

	public static boolean verifyBOMMatchCostSummaryUpdate(String[] data) throws Exception{
		try{
			if(data[3].contains("BOMMatchCostSummaryUpdate") && data[4].equalsIgnoreCase("Yes")){//Update


				navigateToMaterialTab(data);

				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);
				CommonFunctions.handleAlertPopUp();

				//Click on Update Button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");

				//Switch to mainframe
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");


				Assert.assertEquals(driver.findElements(TotalBOMMatchCost).size(), 1, "TotalBOMMatchCost is  not editable"); 
				Assert.assertEquals(driver.findElements(TotalChemicalsMatctCost).size(), 1, "TotalChemicalsMatctCost is  not editable");
				Assert.assertEquals(driver.findElements(TotalElectronicsMatchCost).size(), 1, "TotalElectronicsMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(TotalGeneralDecoLaborMatchCost).size(), 1, "TotalGeneralDecoLaborMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(TotalMarkUpMatchCost).size(), 1, "TotalMarkUpMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(TotalMoldingLaborMatchCost).size(), 1, "TotalMoldingLaborMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(TotalPackagingMatchCost).size(), 1, "TotalPackagingMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(TotalPlasticMatchCost).size(), 1, "TotalPlasticMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(TotalPurchasedPartsMatchCost).size(), 1, "TotalPurchasedPartsMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(TotalSoftGoodsMatchCost).size(), 1, "TotalPlasticMatchCost is  not editable");

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");

			}
			else if(data[3].contains("BOMMatchCostSummaryUpdate") && data[4].equalsIgnoreCase("No"))
			{

				if (data[21].contains("No")){

					navigateToMaterialTab(data);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to content frame
					driver.switchTo().frame("contentframe");


					//Select In Released BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMR);
					CommonFunctions.handleAlertPopUp();

					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");

					log.info("Is Update button is disable: " + strUpdate );
					Assert.assertEquals(strUpdate, "true");

				}

				else if(data[20].equalsIgnoreCase("Yes")){

					//Search the Product
					CommonProjectFunctions.searchProduct(data[5]);

					//switch to default frame
					driver.switchTo().defaultContent();

					//switch to content frame
					driver.switchTo().frame("contentframe");


					if(driver.findElements(specificationtab).size()>0){

						//Click on Specification
						CommonProjectFunctions.clickSpecificationTab(data[6]);

						//Select Specification
						CommonFunctions.handleAlertPopUp();
						strSpec=AddSpecification(data);
						strCW=AddColorway(data);

						CommonProjectFunctions.clickMaterialsTab();

						//Click Add New BOM tab
						CommonFunctions.clickButtonOrLink(addNewBOMTab, "btn", "Add New BOM tab");

						Select selectbom = new Select(driver.findElement(BOMTypeId));
						List<WebElement> bomtype=  selectbom.getOptions();
						boolean bomtypepresent= CommonFunctions.dropDownOptionVerification(data[2], bomtype);

						log.info("Is Internal BOM Type exist: " + bomtypepresent);

						Assert.assertFalse(bomtypepresent);
					}

					else{

						boolean productexist= CommonFunctions.isElementPresent(specificationtab);

						System.out.println("Is vendor User able to see product which has only internal source: " + productexist);

						Assert.assertFalse(productexist);
					}

				}

				else{

					navigateToMaterialTab(data);

					//switch to default frame
					driver.switchTo().defaultContent();

					//Switch to content frame
					driver.switchTo().frame("contentframe");

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(BOMId, strBOMIW);

					String updatebutton = driver.findElement(updateBtn).getAttribute("disabled");


					System.out.println("Is Update button is disable or enable(null)" + updatebutton);


					if(updatebutton != null){

						String updatebttnactive = driver.findElement(updateBtn).getAttribute("disabled");	

						log.info("Update Button is disable " + updatebttnactive);

					}

					else {

						//Click on Update Button
						CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");					

						WebDriverWait wait = new WebDriverWait(driver,10);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
						CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");


						Assert.assertEquals(driver.findElements(TotalBOMMatchCost).size(), 1, "TotalBOMMatchCost is  not editable"); 
						Assert.assertEquals(driver.findElements(TotalChemicalsMatctCost).size(), 1, "TotalChemicalsMatctCost is  not editable");
						Assert.assertEquals(driver.findElements(TotalElectronicsMatchCost).size(), 1, "TotalElectronicsMatchCost is  not editable");
						Assert.assertEquals(driver.findElements(TotalGeneralDecoLaborMatchCost).size(), 1, "TotalGeneralDecoLaborMatchCost is  not editable");
						Assert.assertEquals(driver.findElements(TotalMarkUpMatchCost).size(), 1, "TotalMarkUpMatchCost is  not editable");
						Assert.assertEquals(driver.findElements(TotalMoldingLaborMatchCost).size(), 1, "TotalMoldingLaborMatchCost is  not editable");
						Assert.assertEquals(driver.findElements(TotalPackagingMatchCost).size(), 1, "TotalPackagingMatchCost is  not editable");
						Assert.assertEquals(driver.findElements(TotalPlasticMatchCost).size(), 1, "TotalPlasticMatchCost is  not editable");
						Assert.assertEquals(driver.findElements(TotalPurchasedPartsMatchCost).size(), 1, "TotalPurchasedPartsMatchCost is  not editable");
						Assert.assertEquals(driver.findElements(TotalSoftGoodsMatchCost).size(), 1, "TotalPlasticMatchCost is  not editable");

						log.info("User does not have access to update BOM Match Cost Summary table");

						//Click button btnSaveAndCheckIn
						CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
						CommonFunctions.handleAlertPopUp();
						//Switch to contentFrame
						driver.switchTo().frame("contentframe");
					}
				}
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyBOMMatchCostSummaryUpdate()", e);
			return false;
		}
		return true;
	}

	public static Boolean navigateToMaterialTab(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[5]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);

			CommonProjectFunctions.clickMaterialsTab();
			CommonFunctions.handleAlertPopUp();

			//Select Source
			Select selectsource= new Select(driver.findElement(selectSource));
			List<WebElement> sourcelist= selectsource.getOptions();

			int sourcelistcount =sourcelist.size();
			log.info("The size of source drop down list is: " + sourcelistcount);


			int internalsourcecount= sourcelistcount -1;

			//Select source dropdown
			selectsource.selectByIndex(internalsourcecount);
			CommonFunctions.handleAlertPopUp();
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
			CommonFunctions.handleAlertPopUp();

		}catch(Exception e){ 
			//fail=true;
			log.error("Exception in navigateToMaterialTab()", e);
			return false;
		}
		return true;
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
		}catch(Exception e){ 
			//fail=true;
			log.error("Exception in AddSourcingConfiguration()", e);
			return false;
		}
		return true;
	}

	public static String Create_Colorway(String[] data) throws Exception{
		try{
			// Select Action DD
			CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.actionDD, "Create Colorway");
			//Click on Suffix
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(Colorway.suffix, "Link", "Suffix Clicked");
			//Switch window
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			SeleniumDriver.driver.switchTo().window(child);
		//	SeleniumDriver.driver.findElement(Colorway.suffixSearch).sendKeys(data[14]);
			CommonFunctions.clickButtonOrLink(Colorway.search, "Link", "Search Clicked");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(Colorway.choose, "Link", "Suffix Selected");
			
			SeleniumDriver.driver.switchTo().window(parent);
			SeleniumDriver.driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			//	CommonFunctions.enterTextInTextbox(Colorway.languageCode,langCode);
			CommonFunctions.clickButtonOrLink(Colorway.save, "Link", "Colorway Created");
			CommonFunctions.waitForPageLoaded();
			Thread.sleep(1000);
			Colorway.colorWayName = SeleniumDriver.driver.findElement(Colorway.colorWay).getText();
			//Click on view Product
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			CommonFunctions.waitForPageLoaded();
			//CommonFunctions.clickButtonOrLink(ViewProduct, "Link", "Back to product page");
			//check that colorway is created - verification point- use assert always for verification

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateColorway()", e);
			throw e;
		}
		return Colorway.colorWayName;
	}

	
	//	//This funcion is to select Update Lifecycle State	
	public static String changeBOMStatus() throws Exception{
		try{
			valULCS = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus,"Released");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "In Work");
			}
			else if(valULCS.contains("Released")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "In Work");
			}
			else if(valULCS.contains("Canceled")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "In Work");
			}
			else if(valULCS.contains("Rejected")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "In Work");
			}
			else if(valULCS.contains("Ready For Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "In Work");
			}

			valULCSAfterChange = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeBOMStatus()", e);
		}
		return valULCSAfterChange;
	}

	
	
	public static String changeStatusUnderReview() throws Exception{
		try{
			valULCS = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus,"Under Review");
			}
			//			else if(valULCS.contains("Under Review")){
			//				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "In Work");
			//			}
			else if(valULCS.contains("Released")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Under Review");
			}
			else if(valULCS.contains("Canceled")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Under Review");
			}
			else if(valULCS.contains("Rejected")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Under Review");
			}
			else if(valULCS.contains("Ready For Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Under Review");
			}

			valULCSAfterChange = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeBOMStatus()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatusReleased() throws Exception{
		try{
			valULCS = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus,"Released");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "In Work");
			}
			//			else if(valULCS.contains("Released")){
			//				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Released");
			//			}
			else if(valULCS.contains("Canceled")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Released");
			}
			else if(valULCS.contains("Rejected")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Released");
			}
			else if(valULCS.contains("Ready For Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Released");
			}

			valULCSAfterChange = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeStatusReleased()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatusCancelled() throws Exception{
		try{
			valULCS = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus,"Canceled");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Canceled");
			}
			else if(valULCS.contains("Released")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Canceled");
			}
			//			else if(valULCS.contains("Canceled")){
			//				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Canceled");
			//			}
			else if(valULCS.contains("Rejected")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Canceled");
			}
			else if(valULCS.contains("Ready For Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Canceled");
			}

			valULCSAfterChange = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeStatusCancelled()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatusRejected() throws Exception{
		try{
			valULCS = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus,"Rejected");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Rejected");
			}
			else if(valULCS.contains("Released")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Rejected");
			}
			else if(valULCS.contains("Canceled")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Rejected");
			}
			//			else if(valULCS.contains("Rejected")){
			//				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Rejected");
			//			}
			else if(valULCS.contains("Ready For Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Rejected");
			}

			valULCSAfterChange = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeStatusRejected()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatusReadyForReview() throws Exception{
		try{
			valULCS = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus,"Ready For Review");
			}
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Ready For Review");
			}
			else if(valULCS.contains("Released")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Ready For Review");
			}
			else if(valULCS.contains("Canceled")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Ready For Review");
			}
			else if(valULCS.contains("Rejected")){
				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Ready For Review");
			}
			//			else if(valULCS.contains("Ready For Review")){
			//				CommonFunctions.selectFromDropDownByVisibleText(BOMStatus, "Ready For Review");
			//			}

			valULCSAfterChange = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeStatusReadyForReview()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatus(String[] data) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState,"Complete");
			}
			else if(valULCS.contains("Complete")){
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
			}
			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in Season_selectUpdateLifecycleState()", e);
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
