package com.hasbro.security;
/**
 * @author Anjali Gupta
 *Prerequisite :
 *1. Update is using Create_Material Material name
 *2. matNameIW is inWork Material BOM,used only for delete purpose
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;


public class MaterialBOM extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	public static By addNewBOMTab= By.xpath("//a[contains(text(),'Add New BOM')]");
	//	public static By BOMDetails= By.xpath("//li[@id='DETAILS_PAGETab']/a");
	public static By initializeBOM= By.xpath("//a[contains(text(),'Initialize BOM')]"); 
	public static By headingCreateBOM= By.xpath("//td[contains(text(),'Create BOM')]");
	
	//public static By name= By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	
	public static By name= By.xpath("//*[@id='ptc_str_3']");
	
	//	public static By colorwayName= By.xpath("//td[contains(text(),'Colorway Name')]//following::select[1]");
	public static By colorway= By.xpath("//td[contains(text(),'Colorway')]//following::select[1]");
	public static By wave= By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By currency= By.xpath("//td[contains(text(),'Currency')]//following::select[1]");

	public static By selectSource= By.id("sourcingConfigId");
	public static By selectExistingSpec= By.id("existingSetList");

	public static By selectSpecification= By.id("contextSpecId");
	public static By compOrLoca= By.id("r1_partName");
	public static By checmicalDesc= By.id("r2_partName");

	public static By quantity= By.id("r1_quantity");
	public static By meterial= By.id("r1_materialDescription");
	public static By meterialRow2= By.id("r2_materialDescription");

	public static By plasticDescription= By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[3]/td[1]");
	public static By plasticMeterialDesc= By.xpath(".//table[contains(@id,'TBLT')]/tbody/tr[3]/td[3]");

	public static By chemicalsDescription= By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[5]/td[1]");
	public static By chemicalsMeterialDesc= By.xpath(".//table[contains(@id,'TBLT')]/tbody/tr[5]/td[3]");


	public static By headingEditBOM= By.xpath("//td[contains(text(),'Edit BOM')]");
	public static By inputCompOrLoca= By.xpath("//div[@id='partNameSourceDiv']/input");
	//public static By inputquantity= By.xpath("//div[@id='quantitySourceDiv']/input");
	//public static By inputquantity=By.xpath("//*[@id='FLEXBOMLINK_ptc_dbl_3Input']");
	public static By inputquantity=By.id("FLEXBOMLINK_ptc_dbl_3Input");
	public static By inputMaterial= By.xpath("//div[@id='materialDescriptionSourceDiv']/textarea");

	public static By btnSaveAndCheckIn= By.xpath("//a[text()='Save and Check In']");
	
	public static By headerAttributes= By.xpath("//div[@id='genDetails_plus']/a[2]");
	
	//public static By headerAttributesBtn = By.xpath("//a[contains(text(),'Header Attributes')]");
	
	// public static By headerAttributesBtn = By.xpath("//div[@id='bomEditorSpace']/table/tbody/tr[7]/td/table/tbody/tr/td[2]/a");
	
	  public static By headerAttributesBtn = By.xpath("/html/body/form[1]/div[2]/table/tbody/tr[7]/td/table/tbody/tr/td[2]/a");
	
	//public static By headerAttributesBtn = By.xpath("//*[@id='bomEditorSpace']/table/tbody/tr[7]/td/table/tbody/tr/td[2]");

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
	public static By RO_compLoc= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[3]/td[1]");
	public static By RO_quantity= By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr[3]/td[2]");
	public static By setState= By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table/tbody//a[contains(text(),'Set State')]");
	public static By actionDD= By.id("prodseasonActions");
	public static By manager   = By.xpath("//td[contains(text(),'Manager')]//following::select[1]");
	public static By sourcingLead   = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead   = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");
	public static By management   = By.xpath("//td[contains(text(),'Management')]//following::select[1]");
	public static By BOMDetails  = By.xpath("//a[text()='BOM Details']");

	public static By BOMIdentification  = By.xpath("//td[contains(text(),'BOM Identification')]");
	
	public static By BOMCostSummary  = By.xpath("//td[contains(text(),'BOM Cost Summary')]");
	
	public static By BOMComments  = By.xpath("//td[contains(text(),'Comments')]");
	public static By EnterComments  = By.xpath("//a[text()='Enter Comment']");
	public static By PostNewComment  = By.xpath("//td[contains(text(),'Post New Comment')]//following::textarea[1]");
	public static By post  = By.xpath("//a[text()='Post']");
	public static By descHeading  = By.xpath("//td[@class='TABLESUBHEADER' and text()='Description']");
	public static By plastics  = By.xpath("//a[contains(text(),'Plastics')]");

	public static By BOMStatus  = By.xpath("//td[contains(text(),'BOM Status')]//following::select[1]");
	public static By ROComment  = By.xpath("//a[contains(text(),'User')]//following::td[1]");
	//Meterial
	public static By meterialTab  = By.xpath("//a[text()='Materials']");
	public static By meterialSource= By.id("supplierLinkId");
	public static By sourcingTab  = By.xpath("//a[text()='Sourcing']");
	public static By meterialSupplierAction= By.id("supplierSelect");
	public static By supplierNameColumn  = By.xpath("//div[@id='chooserResultsDiv']/table/tbody/tr[5]/td/div[3]/table/tbody/tr/td[2]");
	public static By supplierNextBtn  = By.xpath("//a[text()='Next']");
	public static By supplierShowAll  = By.xpath("//td[@class='SEARCH_RESULTS_BAR']/span/a[contains(text(),'Show All')]");
	public static By selectBtn  = By.xpath("//a[text()='Select']");
	public static By meterialColor  = By.id("materialColorId");
	public static By colorLink  = By.xpath("//a[text()='Colors']");
	public static By selectMeterialColor = By.xpath("//div[@id='colorDevelopmentSpace']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select");
	public static By btnSearch = By.id("SearchButton2");
	public static By createtBtn = By.xpath("//a[text()='Create']");
	public static By colorCloseIcon = By.xpath("//div[@id='divHeader']//img[contains(@src,'closebutton.png')]");
	public static By billMeterial = By.xpath("//td[contains(text(),'Bill of Materials')]");
	public static By meterialBOMAction = By.xpath("//div[@id='UPDATE_BTNS']/table/tbody/tr/td[3]/a");
	public static By mBOMActionDelete = By.xpath("//div[@id='overDiv']//a[text()='Delete']");

	public static By description = By.xpath("//td[text()='Description']");
	public static By resultsZero = By.xpath("//td[contains(text(),'Results 0-0')]");
	public static By nameBOMread= By.xpath("//div[@id='null']//td[contains(text(),'Name')]//following::td[1]");

	public static By operationType = By.xpath("//*[@id='ptc_str_10']");
	
	public static By updateMBOMStatus = By.xpath("//*[@id='ptc_str_18']");
	
	
	
	//************CostSheet Variables ***********************************************
	
	static String strCostSheetName;	
	public static By lstseasonList = By.xpath("//*[@id='seasonList']");		
	//public static By lnkCosting = By.xpath("//*[@id='quickLinkContent']/div/div[2]/div/div[2]/a[1]");
	public static By lstsource = By.xpath("//*[@id='sourcingConfigId']");
	public static By lstspecification =  By.xpath("//*[@id='contextSpecId']");	
	public static By lstcolorwayName = By.xpath("//*[@id='contextSKUId']");	
	public static By lstcostingActions = By.xpath("//*[@id='costingActions']");	
	public static By lnkCreateCostSheetInternal = By.xpath("//*[@id='VR:com.ptc.core.meta.type.mgmt.server.impl.WTTypeDefinition:13468587_link']");
	public static By lstcolorwayGroupOptions = By.xpath("//*[@id='colorwayGroupOptions']");	
	public static By lstQuoteCurrency = By.xpath("//*[@id='ptc_str_2']");	
	public static By    txtretailItemCost=   By.xpath("//*[@id='ptc_dbl_22Input']");
	public static By	txtplasticMaterial =  By.xpath("//*[@id='ptc_dbl_19Input']");
	public static By	txtpurchasedMaterial= By.xpath("//*[@id='ptc_dbl_14Input']");
	public static By	txtsoftGoodsMaterial= By.xpath("//*[@id='ptc_dbl_106Input']");  
	public static By	txtChemicalMaterial =   By.xpath("//*[@id='ptc_dbl_8Input']"); 
	public static By	txtGeneralDecoLaborCost= By.xpath("//*[@id='ptc_dbl_83Input']");
	public static By	txtMoldingLaborCost = By.xpath("//*[@id='ptc_dbl_63Input']");
	public static By	txtElectronicMaterial =  By.xpath("//*[@id='ptc_dbl_9Input']"); 
	public static By	txtPackagingMaterial =  By.xpath("//*[@id='ptc_dbl_34Input']"); 
	public static By	txtMarkup =   By.xpath("//*[@id='ptc_dbl_80Input']"); 	
	public static By	btnSave =  By.xpath("//*[@id='contentDiv']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/a[1]");  

	public static By lnkSource =  By.xpath("//*[@id='allSpace']/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[17]/a"); 
	
	public static By lnkCosting =  By.xpath("//*[@id='allSpace']/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[7]/a");
	
	public static By txtCreateCostSheetName =  By.xpath("//*[@id='ptc_str_1']");
	
	public static By lnkAdd =  By.xpath("//*[@id='null']/div/div[2]/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/table/tbody/tr/td[1]/a");
	
	public static By lstSeason =  By.xpath("//*[@id='splId']");
	
	public static By btnClose =  By.xpath("//*[@id='CoSheet_VR:com.lcs.wc.sourcing.LCSProductCostSheet:17596923']/img");
	
	public static By tabInternal =  By.xpath("//*[@id='CoSheet_VR:com.lcs.wc.sourcing.LCSProductCostSheet:17597393']/a");
	
	// public static By lstCostSheetAction =  By.xpath("//*[@id='costSheetResults']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select");
	public static By lstCostSheetAction =  By.xpath("/html/body/form/div/table/tbody/tr/td/table/tbody/tr[3]/td/div/table/tbody/tr[3]/td/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select");

	public static By txtcomments =  By.xpath("//*[@id='ptc_str_13']"); 
	
	public static By tblcostSheetResults =  By.xpath("//*[@id='costSheetResults']/table"); 
	
	public static By lstActiondropdown = By.xpath("/html/body/form/div/table/tbody/tr/td/table/tbody/tr[3]/td/div/table/tbody/tr[3]/td/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select");
	
	public static By tblCostSheetIdentification = By.xpath("//*[@id='costSheetResults']/table/tbody/tr[2]/td/div/div[2]/div[1]/table/tbody/tr/td[2]");
	
	 public static By chkPrimaryCostSheet = By.xpath("/html/body/form/div/table/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr[4]/td/table/tbody/tr/td/div[1]/div[2]/div[2]/div/div[2]/table/tbody/tr/td[2]/input[2]");
	
	 
	 public static By lstWave = By.xpath("//*[@id='ptc_str_12']");
	 
	
	 
	 public static By lstDomesticDI = By.xpath("//*[@id='ptc_str_26']");
	 
	 public static By lnkInternalProduct = By.xpath("//*[@id='VR:com.ptc.core.meta.type.mgmt.server.impl.WTTypeDefinition:13466768_link']");
	 
	 public static By lnkContentsTable =  By.xpath("/html/body/form/div/table/tbody/tr/td/table/tbody/tr[3]/td/div/table/tbody/tr[3]/td/div/table/tbody/tr[4]/td/div[3]/div[2]/div[2]/div/div[2]/table/tbody/tr/td/div[1]/table/tbody/tr/td[2]/a[1]");
	
	 public static By lnkUnitRatioContentsTable =  By.xpath("//*[@id='r1_hbUnitRatioContentsTable']");
	 
	 
	 public static By btnDone =  By.xpath("//*[@id='contentDiv']/table/tbody/tr[2]/td/table/tbody/tr/td[2]");
	 
	 public static By edtUnitRatioContentsTable =  By.xpath("//*[@id='ptc_str_1']");
	 
	 public static By lnkVendor =  By.xpath("//*[@id='VR:com.ptc.core.meta.type.mgmt.server.impl.WTTypeDefinition:13469509_link']");
	 
	 public static By costSheetName =  By.xpath("//*[@id='costSheetResults']/table/tbody/tr[1]/td/table/tbody/tr/td[1]");
	 
	 public static By costingActions= By.xpath("//*[@id='costingActions']");
	 
	 //************CostSheet Variables *********************************************** 
	
	
	
	int y=0;
	static int intloopBreak=0;
	static int intBOMPresent=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String BOMname;
	static String strBillOfMaterials;
	static Boolean bCheckedOut=false;
	static Actions action;
	public static String strRO_UpdateLifecycleState;
	static String strUpdate;
	static String strRO_quantity;
	static String strSpec;
	static String  strSource;
	static String strCW;
	static String strBOMDetail;
	static String BOMnameInWork;
	static String BOMnameUnderReview;
	static String BOMnameReleased;
	static String BOMnameCancelled;
	static String BOMnameRejected;
	static String BOMnameReadyforReview;
	static String matName;
	static String addedColor;
	static String  addedSupp;
	static String URBOM;
	static String releasedBOM;
	static String cancelledBOM;
	static String rejectBOM;
	static String materialName;
	static String materialNameUpdate;
	static String matNameIW;
	
	static String NewCostsheetname;
	static String strBOMIW;
	static String strBOMInWork;
	
	static String strInWork;
	static String strRejected;
	static String strCancelled;
	static String strReleased; 
	static String strUnderReview;
	
	static String cstInWork;
	static String cstCancelled;
	static String cstApproved;
	static String cstUnderReview;
	static String cstCommonCostSheet;
	static String cstNewCostSheetName;
	
	static String cstReadforReview;
	static String cstInitialQuoteApproved;
	static String cstSG4Approved;
	static String cstFEPApproved;
	static String cstSeasonalReviewApproved;
	static String cstRejected;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcBOM(String[] data) throws Exception{
		
		try{
			
			//*************************************************
			if(data[4].equalsIgnoreCase("Fabric"))
			{
		//	updateFabBOMNames(data);
			}
			if(data[4].equalsIgnoreCase("Resin"))
			{
		//	updateResinBOMNames(data);
			}
			if(data[4].equalsIgnoreCase("Trim"))
			{
		//		updateTrimBOMNames(data);
			}if(data[4].equalsIgnoreCase("Labor"))
			{
		//		updateLaborBOMNames(data);
			}
			
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			
			
		//	strTestCaseName = data[0] + data[3];			
		//	log.info("Inside Test Case:-> " + SeleniumDriver.strTestCaseName + " for Material BOM Security");				
		//	SeleniumDriver.moduleName= "MaterialBOM";
		//	SeleniumDriver.intTestCaseValcnt=1;
			
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

						
	//************************************************************************************			
			//Create Product from LineSheet			
			if(data[3].equalsIgnoreCase("CreateMBOMUnderReview"))
			{ log.info("In side CreateUnderReview_MBOM");
			  CreateUnderReview_MBOM(data); 
			  log.info("Out side CreateUnderReview_MBOM");}
			if(data[3].equalsIgnoreCase("CreateMBOMReleased"))
			{ log.info("In side CreateReleased_MBOM");
			  CreateReleased_MBOM(data); 
			  log.info("Out side CreateReleased_MBOM");}
			if(data[3].equalsIgnoreCase("CreateMBOMCancelled"))
			{ log.info("In side CreateCancelled_MBOM");
			  CreateCancelled_MBOM(data); 
			  log.info("Out side CreateCancelled_MBOM");}
			if(data[3].equalsIgnoreCase("CreateMBOMRejected"))
			{ log.info("In side CreateRejected_MBOM");
			  CreateRejected_MBOM(data);
			  log.info("Out side CreateRejected_MBOM");}
			if(data[3].equalsIgnoreCase("CreateMaterialInWork"))
			{ log.info("In side CreateMaterialInWork");
			   Create_MaterialInWork(data);
			  log.info("Out side CreateMaterialInWork");}
			
			//	if(data[3].equalsIgnoreCase("Create"))
			//	{ Create_BOM(data); }

			if(data[3].equalsIgnoreCase("CreateMeterialBOM"))
			{ 
				log.info("In side Create_Material"); 
			  Create_Material(data); 
			  log.info("Out side Create_Material");
			  }
			if(data[3].equalsIgnoreCase("readView"))
			{ 
				log.info("In side readView_BOM"); 
				matName =strInWork;
			  readView_BOM(data); 
			  log.info("Out side readView_BOM");
			  }
			if(data[3].equalsIgnoreCase("update"))
			{ 
				log.info("In side update_BOM");
			    matName =strInWork;
			    update_BOM(data); 
			  log.info("Out side update_BOM");
			  }

			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ 
				log.info("In side delete_MBOM");
			  delete_MBOM(data); 
			  log.info("Out side delete_MBOM");
			  }

		/******************************************************/
			
		if(data[3].equalsIgnoreCase("inWorkReadView"))
			{ 
				log.info("In side InWorkreadView_BOM"); 
			   matName =strInWork;
			  InWorkreadView_BOM(data); 
			  log.info("Out side InWorkreadView_BOM");
			  }
			if(data[3].equalsIgnoreCase("inWorkUpdate"))
			{ 
				log.info("In side InWorkUpdate_BOM");
			  matName =strInWork;
			  InWorkUpdate_BOM(data); 
			  log.info("Out side InWorkUpdate_BOM");
			  }
			  
			if(data[3].equalsIgnoreCase("UnderReviewReadView"))
			{ 
				 log.info("In side underReviewReadView"); 
			     matName = strUnderReview;
			     underReviewReadView(data);
			     log.info("Out side underReviewReadView"); 
			  }
			if(data[3].equalsIgnoreCase("UnderReviewReadUpdate"))
			{ 
				log.info("In side underReviewReadUpdate");
			    matName = strUnderReview;
			    underReviewUpdate(data);
			    log.info("Out side underReviewReadUpdate"); 
			  
			}

			if(data[3].equalsIgnoreCase("ReleasedReadView"))
			{ 
				log.info("In side releasedReadView");
			    matName = strReleased; 
			    releasedReadView(data);
			    log.info("Out side releasedReadView"); 
			  }
			if(data[3].equalsIgnoreCase("ReleasedUpdate"))
			{ 
				log.info("In side releasedUpdate");
			    matName = strReleased;  
			    releasedUpdate(data); 
			    log.info("Out side releasedUpdate");
			  }

			if(data[3].equalsIgnoreCase("CancelledReadView"))
			{ 
				log.info("In side cancelledReadView");
			    matName =strCancelled;
			    cancelledReadView(data); 
			    log.info("Out side cancelledReadView");
			  }
			if(data[3].equalsIgnoreCase("CancelledReadUpdate"))
			{ 
				log.info("In side cancelledReadUpdate");
			    matName =strCancelled;
			    cancelledUpdate(data); 
			    log.info("Out side cancelledReadUpdate"); 
			  }

			if(data[3].equalsIgnoreCase("RejectedReadView"))
			{ 
				log.info("In side rejectedReadView");
			    matName = strRejected;
			    rejectedReadView(data); 
			    log.info("Out side rejectedReadView");
			  }
			if(data[3].equalsIgnoreCase("RejectedReadUpdate"))
			{ 
				log.info("In side rejectedReadUpdate");
			    matName = strRejected;
			    rejectedUpdate(data);   
			    log.info("Out side rejectedReadUpdate");
			  }

			if(data[3].equalsIgnoreCase("ReadyforReviewReadView"))
			{ 
				log.info("In side readyForReviewReadView");
			  readyForReviewReadView(data); 
			  log.info("Out side readyForReviewReadView");
			  }
			if(data[3].equalsIgnoreCase("ReadyforReviewUpdate"))
			{ 
				log.info("In side readyForReviewUpdate");
			  readyForReviewUpdate(data);  
			  log.info("Out side readyForReviewUpdate"); 
			  }

			/******************************************************/
			
			//Read view verification
			if(data[3].equalsIgnoreCase("GeneralAttributesRead_View"))
			{  log.info("In side verifyGeneralAttributesReadView");
			   matName = strReleased;
			   verifyGeneralAttributesReadView(data); 
			   log.info("Out side verifyGeneralAttributesReadView");}
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttributesUpdate"))
			{  log.info("In side verifyGeneralAttributesUpdate");
			   matName = strReleased;
			   verifyGeneralAttributesUpdate(data); 
			   log.info("Out side verifyGeneralAttributesUpdate");}
			   
			
             
			   
			   

			if(data[3].equalsIgnoreCase("BOMSectionsRead"))
			{ log.info("In side verifyBOMSectionsRead");
			   matName = strReleased;
			  verifyBOMSectionsRead(data); 
			  log.info("Out side verifyBOMSectionsRead");}
			if(data[3].equalsIgnoreCase("BOMSectionsUpdate"))
			{ log.info("In side verifyBOMSectionsUpdate");
			  matName = strReleased;
			  verifyBOMSectionsUpdate(data); 
			  log.info("Out side verifyBOMSectionsUpdate");}
			  
			  
			
			if(data[3].equalsIgnoreCase("BOMStatusRead"))
			{ log.info("Out side verifyBOMStatusRead");
			   matName = strReleased;
			         verifyBOMStatusReadView(data); 
			  log.info("In side verifyBOMStatusRead");}
			
			if(data[3].equalsIgnoreCase("BOMStatusUpdate"))
			{ log.info("Out side verifyBOMStatusRead");
			  matName = strReleased;
			  verifyBOMStatusUpdate(data); 
			  log.info("In side verifyBOMStatusUpdate");}
			  

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	/*public static boolean updateFabBOMNames(String[] data) throws Exception
	{
	
		strInWork="Fab-InWork";
		strRejected="Fab-Rejected";
		strCancelled="Fab-Cancelled";
		strReleased="Fab-Released"; 
		strUnderReview="Fab-UnderReview";
	return true;
	
	}
	
	public static boolean updateTrimBOMNames(String[] data) throws Exception
	{
	
		strInWork= "Trim-InWork";
		strRejected= "Trim-Rejected";
		strCancelled= "Trim-Cancelled";
		strReleased= "Trim-Released"; 
		strUnderReview= "Trim-UnderReview";
	    return true;
	
	}*/
	
	/*public static boolean updateLaborBOMNames(String[] data) throws Exception
	{
	
		strInWork = "Labor-InWork";
		strRejected = "Labor-Rejected";
		strCancelled = "Labor-Cancelled";
		strReleased = "Labor-Released"; 
		strUnderReview ="Labor-UnderReview";
	return true;
	
	}*/
	
	/*public static boolean updateResinBOMNames(String[] data) throws Exception
	{
	
		strInWork="Resin-InWork";		
		strRejected = "Resin-Rejected";		
		strCancelled = "Resin-Cancelled";		
		strReleased = "Resin-Released"; 		
		strUnderReview="Resin-UnderReview";
		
	return true;
	
	}
*/
	public static boolean navigateUptoMaterialPage(String[] data) throws Exception
	{
	
	    driver.navigate().refresh();
	    Thread.sleep(1000);
	    CommonProjectFunctions.searchAttributeType("Material", matName);
	    //Click on Meterial
	    SeleniumDriver.driver.switchTo().defaultContent();
	    SeleniumDriver.driver.switchTo().frame("contentframe");	
	    CommonFunctions.clickButtonOrLink(meterialTab, "tab", "Meterial");
	    Thread.sleep(1000);
	//    CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstsupplier, "FUNSKOOL (INDIA) LTD", "lstsupplier");
	    Thread.sleep(2000);
	return true;
	
	}
	
	public static boolean assertcheckUpdateButtonStatus(String[] data) throws Exception
	{
	   
	    strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
		Assert.assertEquals(strUpdate, "true");
	   return true;

	}
	
	public static boolean assertAttributesStatus(String[] data) throws Exception
	{
	   String BILabel=driver.findElement(BOMIdentification).getText();
	   Assert.assertEquals(BILabel.trim(), "BOM Identification");

	   String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
	   Assert.assertEquals(GALabel, " General Attributes:");
	
	   String strDescrip = driver.findElement(description).getText();
	   Assert.assertEquals(strDescrip.trim(), "Description");
	
	return true;
	}
	
    public static boolean navigateUptoCreateDiffrntTypesOfMaterial(String[] data) throws Exception
	
	{
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.switchTo().frame("sidebarframe");
		// Click on Libraries
		CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
		//Click on Color link
		driver.findElement(Material.materialLink).click();
		//Switch frame
		driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
		wait = new WebDriverWait(driver, 10);
		wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialHeadning));
		
		//Checking and Validating New Button on Material Search Page
		boolean newType=false;
		Packaging.isPresent(Material.newLink, newType);			
		//Click on new
		CommonFunctions.clickButtonOrLink(Material.newLink, "link", "New Link");
		wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.chooseaType));
		//Click on color type
		CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+ data[4]+ "')and @class='LABEL']"), "link", "Material Type");
		System.out.println(By.xpath("//a[contains(text(),'"+data[4]+"')and @class='LABEL']"));
		
	    return true;
	}
	
	
	//Function consist scenario : verifyBOMStatusReadView:Read_View
	public static boolean verifyBOMStatusReadView(String[] data) throws Exception{
		try{
			
			navigateUptoMaterialPage(data);
			
			CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");					
			if(data[3].contains("BOMStatusRead")&& data[5].equalsIgnoreCase("Yes")){//Read_View
				
				
				String BOMStatusInReadView= "/html/body/form/div/table/tbody/tr/td/table/tbody/tr[3]/td/div/table/tbody/tr[4]/td/div/table[2]/tbody/tr[3]/td/div/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td/div[2]/div[2]/div[1]/table/tbody/tr/td[2]";
			    
				String strBOMStatusInReadView =driver.findElement(By.xpath(BOMStatusInReadView)).getText();
				    
				Assert.assertEquals(strBOMStatusInReadView.trim(),"Material BOM Status:");
				    
				log.info("BOM Status attribute value verified:->" + BOMStatusInReadView);
				
				
			}
			else if(data[3].contains("BOMStatusRead")&& data[5].equalsIgnoreCase("No"))
			{
				
					log.info("BOM Status attribute label is Absent");
					return true;
				}
			else
			{
				log.info("For this BOMStatus Read View is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyBOMStatusReadView()", e);
			return false;
		}
		return true;
	}
		
	//Function consist scenario : General Attributes://Update
    public static boolean verifyBOMStatusUpdate(String[] data) throws Exception{
		try{
			if(data[5].equalsIgnoreCase("Yes")){//Update
				
				navigateUptoMaterialPage(data);
				
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				//	driver.switchTo().frame("mainFrame");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				
				updateBOMStatus(data);
			
			}
			else if(data[3].contains("BOMStatusUpdate")&& data[5].equalsIgnoreCase("No"))
			{
				
				navigateUptoMaterialPage(data);
				
				//assertcheckUpdateButtonStatus(data);
				
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present");


			}	
			else
			{
				log.info("For this BOMStatus Update is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyBOMStatusUpdateUpdate()", e);
			return false;
		}
		return true;
	}		
	
	
    public static boolean updateBOMStatus(String[] data) throws Exception
	  {	
    	boolean isEnabled=false;
		String tblupdateBOMStatus ="/html/body/form[1]/div[2]/table/tbody/tr[5]/td/div/table/tbody/tr[2]/td/table/tbody/tr/td/div[2]/div[2]/div[1]/table/tbody/tr/td[2]";			    
		String tblAttributeValue =driver.findElement(By.xpath(tblupdateBOMStatus)).getText();    
		if(data[2].contains("BOM\\Materials\\Material"))	    
	    { 
	      Assert.assertEquals(tblAttributeValue.trim(),"Material BOM Status:");	    
	      log.info("BOM Status attribute value verified:->" + tblAttributeValue);
	      isEnabled=  driver.findElement(By.xpath("//*[@id='ptc_str_18']")).isEnabled();
	      Assert.assertTrue(isEnabled,"Material BOM Status: filed is editable user can update the value");
	      if(isEnabled=true)
	      {   //change the status
	    	  CommonFunctions.selectFromDropDownByIndex(updateMBOMStatus, 1);
	    	  //re-set value to original status
	    	  CommonFunctions.selectFromDropDownByIndex(updateMBOMStatus, 3);
	    	  
	      }
	    }else
	    {
	      Assert.assertEquals(tblAttributeValue.trim(),"Material BOM Status:");	    
		  log.info("BOM Status attribute value verified:->" + tblAttributeValue);	
	    }	
		
	      return true;
	
	}
	

 
	//Create Material BOM
	
	public static String Create_Material(String[] data) throws Exception{
		try{
			
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
			//Click on Color link
			driver.findElement(Material.materialLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialHeadning));
			
			//Checking and Validating New Button on Material Search Page
			boolean newType=false;
			Packaging.isPresent(Material.newLink, newType);
			
		
			if(data[5].equalsIgnoreCase("Yes")){
				
				//Click on new
				CommonFunctions.clickButtonOrLink(Material.newLink, "link", "New Link");
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.chooseaType));
				//Click on color type
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[4]+"')and @class='LABEL']"), "link", "Material Type");
				System.out.println(By.xpath("//a[contains(text(),'"+data[4]+"')and @class='LABEL']"));

				
				switch (data[4]) 
				{
				case "Fabric":
					materialName= fillMaterailMandatory(data);
					fillMatFabric(data);
					Material.createMaterial(data);
					createMeterialBOM(data);
					break;
				case "Trim":
					trimMandatoryData(data);					
					createMeterialBOM(data);
					break;
				case "Resin":
					resinmandatoryData(data);					
					createMeterialBOM(data);
					break;
				case "Labor":
					labormandatoryData(data);					
					createMeterialBOM(data);
					break;
				default:
					fail=true;
					log.info("Default is executed");
				}
			}
			else if(data[3].contains("Create")&& data[5].equalsIgnoreCase("No"))
			{
				// condition for vuser1
				if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("No"))
				{
						String strDescrip = driver.findElement(resultsZero).getText();
						strDescrip=strDescrip.substring(0, 11);
						Assert.assertEquals(strDescrip, "Results 0-0");

				}else 					
				  {	
					Assert.assertFalse(newType, "New Link not available"); 					
				  }			
			}
			else{
				log.info("Create is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_Material()", e);
			return "";
		}
		return materialName;
	}

	public static String Create_MaterialInWork(String[] data) throws Exception{
		try{
							
	navigateUptoCreateDiffrntTypesOfMaterial(data);		
		switch (data[4]) 
				{
				case "Fabric":
					materialName= fillMaterailMandatory(data);
					fillMatFabric(data);
					Material.createMaterial(data);
					createMeterialBOM(data);
					break;
				case "Trim":
					trimMandatoryData(data);					
					createMeterialBOM(data);
					break;
				case "Resin":
					resinmandatoryData(data);				
					createMeterialBOM(data);
					break;
				case "Labor":
					labormandatoryData(data);					
					createMeterialBOM(data);
					break;
				default:
					fail=true;
					log.info("Create_MaterialInWork is executed");
				}							
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_Material()", e);
			return "";
		}
		return matNameIW;
	}
	
	
	/*
	public static boolean readView_UnderReviewMBOM(String[] data) throws Exception{
		try{
			
			navigateUptoMaterialPage(data);
			
			if(data[3].contains("ReadView")&& data[5].equalsIgnoreCase("Yes")){
				//Click on Meterial
				
				String strDescrip = driver.findElement(description).getText();
				Assert.assertEquals(strDescrip.trim(), "Description");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//String strDescrip = driver.findElement(resultsZero).getText();
				//strDescrip=strDescrip.substring(0, 11);
				//Assert.assertEquals(strDescrip, "Results 0-0");
				return true;

			}
			else{
				log.info("Not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in readView_UnderReviewMBOM", e);
			return false;
		}
		return true;
	}
	*/
	
	//Create Material BOM
	public static boolean CreateUnderReview_MBOM(String[] data) throws Exception{
		try{
			
			navigateUptoCreateDiffrntTypesOfMaterial(data);
			
			switch (data[4]) 
			{
			case "Fabric":
				materialName= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createURMeterialBOM(data);
				break;
			case "Trim":
				trimMandatoryData(data);				
				createURMeterialBOM(data);
				break;
			case "Resin":
				resinmandatoryData(data);				
				createURMeterialBOM(data);
				break;
			case "Labor":
				labormandatoryData(data);				
				createURMeterialBOM(data);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}						
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateUnderReview_MBOM()", e);
			return false;
		}
		return true;
	}

	public static boolean CreateReleased_MBOM(String[] data) throws Exception{
		try{
			
			navigateUptoCreateDiffrntTypesOfMaterial(data);

	switch (data[4]) 
			{
			case "Fabric":
				materialName= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createReleasedMBOM(data);
				break;
			case "Trim":
				trimMandatoryData(data);			
				createReleasedMBOM(data);
				break;
			case "Resin":
				resinmandatoryData(data);				
				createReleasedMBOM(data);
				break;
			case "Labor":
				labormandatoryData(data);				
				createReleasedMBOM(data);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateReleased_MBOM()", e);
			return false;
		}
		return true;
	}

	public static boolean CreateCancelled_MBOM(String[] data) throws Exception{
		try{
			
			navigateUptoCreateDiffrntTypesOfMaterial(data);
			
		switch (data[4]) 
			{
			case "Fabric":
				materialName= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createCancelledMBOM(data);
				break;
			case "Trim":
				trimMandatoryData(data);				
				createCancelledMBOM(data);
				break;
			case "Resin":
				resinmandatoryData(data);				
				createCancelledMBOM(data);
				break;
			case "Labor":
				labormandatoryData(data);				
				createCancelledMBOM(data);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}			 
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateCancelled_MBOM()", e);
			return false;
		}
		return true;
	}

	public static boolean CreateRejected_MBOM(String[] data) throws Exception{
		try{
			/*
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(Material.libraryLink, "Link", "Library Link");
			//Click on Color link
			driver.findElement(Material.materialLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialHeadning));
			//Checking and Validating New Button on Material Search Page
			boolean newType=false;
			Packaging.isPresent(Material.newLink, newType);
			
			//Click on new
			CommonFunctions.clickButtonOrLink(Material.newLink, "link", "New Link");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.chooseaType));
			//Click on color type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[4]+"')and @class='LABEL']"), "link", "Material Type");
			System.out.println(By.xpath("//a[contains(text(),'"+data[4]+"')and @class='LABEL']"));
			*/
			navigateUptoCreateDiffrntTypesOfMaterial(data);

			switch (data[4]) 
			{
			case "Fabric":
				materialName= fillMaterailMandatory(data);
				fillMatFabric(data);
				Material.createMaterial(data);
				createRejectedMBOM(data);
				break;
			case "Trim":
				trimMandatoryData(data);				
				createRejectedMBOM(data);
				break;
			case "Resin":
				resinmandatoryData(data);				
				createRejectedMBOM(data);
				break;
			case "Labor":
				labormandatoryData(data);				
				createRejectedMBOM(data);
				break;
			default:
				fail=true;
				log.info("Default is executed");
			}
					
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateRejected_MBOM()", e);
			return false;
		}
		return true;
	}

	static String fillMaterailMandatoryInWork(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			matNameIW=data[4]+CommonFunctions.getRandomString(5);
			///CommonFunctions.enterTextInTextbox(Material.materialName,matNameIW);
			CommonFunctions.enterTextInTextboxUpdated(Material.materialName,matNameIW,"materialName");
			CommonFunctions.enterTextInTextboxUpdated(Material.parentRefNumber, data[9],"parentRefNumber");
			driver.findElement(Material.cBlack).click();
			log.info("cBlack is Selected!!!");
			driver.findElement(Material.crADD).click();
			log.info("crADD is Selected!!!");
			// Send Material Description Details
			CommonFunctions.enterTextInTextboxUpdated(Material.description, data[22],"description");
			// Send Comments for Creating Materials Details			
			CommonFunctions.enterTextInTextboxUpdated(Material.comments, data[23],"comments");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in fillMaterailMandatory()", e);
		}
		return matNameIW;
	}	
	
	static String fillMaterailMandatory(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			
			matName = data[4] + CommonFunctions.getRandomString(5);
			
			CommonFunctions.enterTextInTextboxUpdated(Material.materialName,matName,"materialName");		
			CommonFunctions.enterTextInTextboxUpdated(Material.parentRefNumber, data[9],"parentRefNumber");
			driver.findElement(Material.cBlack).click();
			log.info("cBlack is Selected!!!");
			driver.findElement(Material.crADD).click();
			log.info("crADD is Selected!!!");
			// Send Material Description Details						
			CommonFunctions.enterTextInTextboxUpdated(Material.description, data[22],"description");
			// Send Comments for Creating Materials Details
			CommonFunctions.enterTextInTextboxUpdated(Material.comments, data[23],"comments");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in fillMaterailMandatory()", e);
		}
		return matName;
	}

	private static void fillMatFabric(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			//Select Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.construction, data[12],"construction");
			//Select Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.constructionType, data[10],"constructionType");
			//Select Secondary Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.secondaryConstruction, data[11],"secondaryConstruction");
			//Select Content From drop down			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.content, data[13],"content");
			// Send Content Percent values
			CommonFunctions.enterTextInTextboxUpdated(Material.percent, data[14],"percent");
			//Click On Add button
			CommonFunctions.clickButtonOrLink(Material.cpADD, "btn", "cpADD");
			//Select Content From drop down		
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.content, data[15],"content");
			// Send Content Percent values			
			CommonFunctions.enterTextInTextboxUpdated(Material.percent, data[16],"percent");
			//Click On Add button			
			// Send Weight
			CommonFunctions.enterTextInTextboxUpdated(Material.weightGYD, data[17],"weightGYD");
			/*
			//Send Pile Height
			///CommonFunctions.enterTextInTextbox(Material.pileHeightmm, data[18]);
			CommonFunctions.enterTextInTextboxUpdated(Material.pileHeightmm, data[18],"pileHeightmm");
			//Caoting PU
			driver.findElement(Material.coatingPU).click();
			log.info("coatingPU is Selected!!!");
			CommonFunctions.clickButtonOrLink(Material.coatingADD, "btn", "coatingADD");
			//Finishes Anti Pilling
			driver.findElement(Material.finishesAntiPilling).click();
			log.info("finishesAntiPilling is Selected!!!");
			CommonFunctions.clickButtonOrLink(Material.finishesADD, "btn", "finishesADD");
			//Print Type Back Side Print
			driver.findElement(Material.printTypeBacksidePrint).click();
			log.info("printTypeBacksidePrint is Selected!!!");
			CommonFunctions.clickButtonOrLink(Material.printTypeADD, "btn", "printTypeADD");
			*/
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
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.trimType, data[16],"trimType");
			//Send Size		
			CommonFunctions.enterTextInTextboxUpdated(Material.size, data[15],"size");
			//Select Coating			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.coatings, data[17],"coatings");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.sizeUOM, data[18],"sizeUOM");
			//Construction Type
			driver.findElement(Material.construction1Type).click();
			log.info("construction Type is Selected!!!");
			CommonFunctions.clickButtonOrLink(Material.constructionADD, "btn", "constructionADD");
			//Select Content From drop down			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.content1, data[10],"content1");
			// Send Content Percent values			
			CommonFunctions.enterTextInTextboxUpdated(Material.percent1, data[11],"percent1");
			//Click On Add button
			CommonFunctions.clickButtonOrLink(Material.cpADD1, "btn", "cpADD1");
			//Select Content From drop down			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.content1, data[12],"content1");
			// Send Content Percent values		
			CommonFunctions.enterTextInTextboxUpdated(Material.percent1, data[13],"percent1");
			//Click On Add button
			CommonFunctions.clickButtonOrLink(Material.cpADD1, "btn", "cpADD1");
			//Print Type
			driver.findElement(Material.printType).click();
			log.info("printType is Selected!!!");
			CommonFunctions.clickButtonOrLink(Material.printADD, "btn", "printADD");
			//Finishes
			driver.findElement(Material.finishes).click();
			log.info("finishes is Selected!!!");
			CommonFunctions.clickButtonOrLink(Material.finishes1ADD, "btn", "finishes1ADD");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in fillMatTrim()", e);
		}
	}

	private static boolean resinmandatoryData(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			Date date = new Date();
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			///CommonFunctions.enterTextInTextbox(Material.materialName,data[5]+date.getTime());
			matName=data[4] + date.getTime();
			CommonFunctions.enterTextInTextboxUpdated(Material.materialName, matName,"materialName");
			//CommonFunctions.enterTextInTextboxUpdated(Material.resindescription, "SampleMaterial","resindescription");
			
			//Search for Season from POPUP Page
		//	CommonFunctions.clickButtonOrLink(PartMgmtFunctional.season, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			///CommonFunctions.enterTextInTextbox(PartMgmtFunctional.seasonNumber, data[9]);
		//	CommonFunctions.enterTextInTextboxUpdated(PartMgmtFunctional.seasonNumber, data[7],"seasonNumber");
		//	CommonFunctions.clickButtonOrLink(PartMgmtFunctional.search, "Search For Season");
		//	CommonFunctions.clickButtonOrLink(PartMgmtFunctional.choose, "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			// click on resin create button
			CommonFunctions.clickButtonOrLink(Material.btnCreateResin, "btnCreateResin");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in resinmandatoryData()", e);
			return false;
		}
		return true;
	}
	
	private static boolean labormandatoryData(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			Date date = new Date();
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			///CommonFunctions.enterTextInTextbox(Material.materialName,data[5]+date.getTime());
			matName = data[4] + date.getTime();
			materialName = data[4] + date.getTime();
		//	CommonFunctions.enterTextInTextboxUpdated(Material.txtlabourName, matName,"txtlabourName");
			CommonFunctions.enterTextInTextboxUpdated(Material.txtlaborDescription, "SamplelaborDescription","txtlaborDescription");			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstlaborOperationType, "Assembly Labor","lstlaborOperationType");
			
			//Search for Season from POPUP Page
	//		CommonFunctions.clickButtonOrLink(PartMgmtFunctional.season, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			//bhalchandra
			///CommonFunctions.enterTextInTextbox(PartMgmtFunctional.seasonNumber, data[9]);
		//	CommonFunctions.enterTextInTextboxUpdated(PartMgmtFunctional.seasonNumber, data[7],"seasonNumber");
		//	CommonFunctions.clickButtonOrLink(PartMgmtFunctional.search, "Search For Season");
		//	CommonFunctions.clickButtonOrLink(PartMgmtFunctional.choose, "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			// click on resin create button
			CommonFunctions.selectFromDropDownByIndex(operationType,1);
		//	CommonFunctions.clickButtonOrLink(Material.btnCreateLabour, "btnCreateLabour");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in labourmandatoryData()", e);
			return false;
		}
		return true;
	}
	
	private static boolean trimMandatoryData(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			Date date = new Date();
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			///CommonFunctions.enterTextInTextbox(Material.materialName,data[5]+date.getTime());
			matName = data[4] + date.getTime();
			materialName = data[4] + date.getTime();
		//	CommonFunctions.enterTextInTextboxUpdated(Material.txtTrimName, matName,"txtlabourName");
			
			//CommonFunctions.enterTextInTextboxUpdated(Material.txtlaborDescription, "SamplelaborDescription","txtlaborDescription");			
			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstTrimType, "Buckle","lstTrimType");
			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstcontentPercent ,"Plastic","lstcontentPercent");
			
			CommonFunctions.enterTextInTextboxUpdated(Material.txtPercentage, "100","txtPercentage");
			
		//	CommonFunctions.clickButtonOrLink(Material.lnkAdd, "lnkAdd");
			
			CommonFunctions.clickButtonOrLink(Material.btnCreateTrim, "btnCreateTrim");
			
			
			
			/*
			//Search for Season from POPUP Page
			CommonFunctions.clickButtonOrLink(PartMgmtFunctional.season, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			///CommonFunctions.enterTextInTextbox(PartMgmtFunctional.seasonNumber, data[9]);
			CommonFunctions.enterTextInTextboxUpdated(PartMgmtFunctional.seasonNumber, data[7],"seasonNumber");
			CommonFunctions.clickButtonOrLink(PartMgmtFunctional.search, "Search For Season");
			CommonFunctions.clickButtonOrLink(PartMgmtFunctional.choose, "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			// click on resin create button
			CommonFunctions.clickButtonOrLink(Material.btnCreateLabour, "btnCreateLabour");
			*/
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in labourmandatoryData()", e);
			return false;
		}
		return true;
	}
	
	private static boolean mandatoryData(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			Date date = new Date();
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(Material.materialName));
			///CommonFunctions.enterTextInTextbox(Material.materialName,data[5]+date.getTime());
			matName = data[4] + date.getTime();
			materialName = data[4] + date.getTime();
		//	CommonFunctions.enterTextInTextboxUpdated(Material.txtTrimName, matName,"txtlabourName");
			
			//CommonFunctions.enterTextInTextboxUpdated(Material.txtlaborDescription, "SamplelaborDescription","txtlaborDescription");			
			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstTrimType, "Buckle","lstTrimType");
			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstcontentPercent ,"Plastic","lstcontentPercent");
			
			CommonFunctions.enterTextInTextboxUpdated(Material.txtPercentage, "100","txtPercentage");
			
		//	CommonFunctions.clickButtonOrLink(Material.lnkAdd, "lnkAdd");
			
			CommonFunctions.clickButtonOrLink(Material.btnCreateTrim, "btnCreateTrim");
			
			
			
			/*
			//Search for Season from POPUP Page
			CommonFunctions.clickButtonOrLink(PartMgmtFunctional.season, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
			///CommonFunctions.enterTextInTextbox(PartMgmtFunctional.seasonNumber, data[9]);
			CommonFunctions.enterTextInTextboxUpdated(PartMgmtFunctional.seasonNumber, data[7],"seasonNumber");
			CommonFunctions.clickButtonOrLink(PartMgmtFunctional.search, "Search For Season");
			CommonFunctions.clickButtonOrLink(PartMgmtFunctional.choose, "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			// click on resin create button
			CommonFunctions.clickButtonOrLink(Material.btnCreateLabour, "btnCreateLabour");
			*/
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in labourmandatoryData()", e);
			return false;
		}
		return true;
	}

	private static boolean createResin(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			//Select processing Method Type From drop down			
		//	CommonFunctions.selectFromDropDownByVisibleTextUpdated(PartMgmtFunctional.processingMethod, data[6],"processingMethod");
			// Send Resin Description Description Details			
		//	CommonFunctions.enterTextInTextboxUpdated(PartMgmtFunctional.resinDescription, data[7],"resinDescription");
			// Send Density (gr/gm2) Description Details		
		//	CommonFunctions.enterTextInTextboxUpdated(PartMgmtFunctional.density, data[8],"density");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in createResin()", e);
			return false;
		}
		return true;
	}

	private static boolean createLabor(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			//Select Operation Type From drop down			
		//	CommonFunctions.selectFromDropDownByVisibleTextUpdated(PartMgmtFunctional.operationType, data[6],"operationType");
			// Send Labor Description  Details			
		//	CommonFunctions.enterTextInTextboxUpdated(PartMgmtFunctional.laborDescription, data[7],"laborDescription");
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in createLabor()", e);
			return false;
		}
		return true;
	}	

	public static Boolean createMeterialBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			addedSupp = AddMeterialSource(data);
			addedColor= AddColor(data);
			//Click on meterial			
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"In Work", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");			
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();			
			CommonFunctions.enterTextInTextboxUpdated(inputCompOrLoca,"Mat description","inputCompOrLoca");
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();			
			CommonFunctions.enterTextInTextboxUpdated(inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");			

		}catch(Exception e){
			fail=true;
			log.error("Exception in createMeterialBOM()", e);
			return false;
		}
		return true;
	}

	public static String createURMeterialBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			addedSupp = AddMeterialSource(data);
			addedColor= AddColor(data);			
			//Click on meterial
			//	CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"Under Review", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputCompOrLoca,"Mat description" ); //data[27]
			CommonFunctions.enterTextInTextboxUpdated(inputCompOrLoca,"Mat description","inputCompOrLoca");
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputquantity,"5" ); //
			CommonFunctions.enterTextInTextboxUpdated(inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");
			//Click on Update
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			URBOM="UnderReview"+matName;
			
			//update Name
			///CommonFunctions.enterTextInTextbox(name, URBOM);
			CommonFunctions.enterTextInTextboxUpdated(name, URBOM,"name");
			/*if (data[3].contains("CreateUnderReview")){
				valULCSAfterChange=changeStatusUnderReview();
			}*/
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

			log.info("UnderReview status BOM is: "+URBOM);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createMeterialBOM()", e);
			return "";
		}
		return URBOM;
	}

	public static String createReleasedMBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			addedSupp = AddMeterialSource(data);
			addedColor= AddColor(data);
			//Click on meterial
			//	CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"Released", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputCompOrLoca,"Mat description" ); //data[27]
			CommonFunctions.enterTextInTextboxUpdated(inputCompOrLoca,"Mat description","inputCompOrLoca" );
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputquantity,"5" ); //
			CommonFunctions.enterTextInTextboxUpdated(inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");
			//Click on Update
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			String releasedBOM="Released"+matName;
			//update Name
			///CommonFunctions.enterTextInTextbox(name, URBOM);
			CommonFunctions.enterTextInTextboxUpdated(name, URBOM,"name");
            /*
			if (data[3].contains("CreateUnderReview")){
				valULCSAfterChange=changeStatusReleased();
			}*/
			
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

			log.info("Released status BOM is: "+releasedBOM);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createMeterialBOM()", e);
			return "";
		}
		return releasedBOM;
	}

	public static String createCancelledMBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			addedSupp = AddMeterialSource(data);
			addedColor= AddColor(data);
			//Click on meterial
			//	CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"Canceled", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputCompOrLoca,"Mat description" ); //data[27]
			CommonFunctions.enterTextInTextboxUpdated(inputCompOrLoca,"Mat description","inputCompOrLoca");
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputquantity,"5" ); //
			CommonFunctions.enterTextInTextboxUpdated(inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");
			//Click on Update
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			cancelledBOM="Cancel"+matName;
			//update Name
			///CommonFunctions.enterTextInTextbox(name, URBOM);
			CommonFunctions.enterTextInTextboxUpdated(name, URBOM,"name");
            /*
			if (data[3].contains("CreateUnderReview")){
				valULCSAfterChange=changeStatusCancelled();
			}
			*/
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

			log.info("Cancelled status BOM is: "+cancelledBOM);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createCancelledMBOM()", e);
			return "";
		}
		return cancelledBOM;
	}

	public static String createRejectedMBOM(String[] data) throws Exception{
		try{
			//Click on meterial
			CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			addedSupp = AddMeterialSource(data);
			addedColor= AddColor(data);
			//Click on meterial
			//	CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			//Click on initialize BOM
			CommonFunctions.clickButtonOrLink(initializeBOM,"btn", "Initialize BOM");
			// select Under Review
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstbomstatus,"Rejected", "lstbomstatus");
			//Click on Create
			CommonFunctions.clickButtonOrLink(createtBtn,"btn", "Create");
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//	wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(headingEditBOM));
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputCompOrLoca,"Mat description" ); //data[27]
			CommonFunctions.enterTextInTextboxUpdated(inputCompOrLoca,"Mat description","inputCompOrLoca");
			//Quanity
			action.moveToElement(driver.findElement(quantity)).doubleClick().perform();
			///CommonFunctions.enterTextInTextbox(inputquantity,"5" ); //
			CommonFunctions.enterTextInTextboxUpdated(inputquantity,"5","inputquantity");
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			String strHeader=driver.findElement(billMeterial).getText();
			Assert.assertEquals(strHeader.trim(), "Bill of Materials:");
			//Click on Update
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//	driver.switchTo().frame("mainFrame");
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			rejectBOM="reject"+matName;
			//update Name
			///CommonFunctions.enterTextInTextbox(name, URBOM);
			CommonFunctions.enterTextInTextboxUpdated(name, URBOM,"name");
            /*
			if (data[3].contains("CreateUnderReview")){
				valULCSAfterChange=changeStatusRejected();
			}
			*/
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

			log.info("Rejected status BOM is: "+rejectBOM);

		}catch(Exception e){
			fail=true;
			log.error("Exception in createRejectedMBOM()", e);
			return "";
		}
		return rejectBOM;
	}

	public static Boolean ClickMeterial(String[] data) throws Exception{
		try{
			CommonFunctions.clickButtonOrLink(meterialTab, "Tab", "Material");

		}catch(Exception e){
			fail=true;
			log.error("Exception in AddColorway()", e);
			return false;
		}
		return true;
	}

	public static String AddColor(String[] data) throws Exception{
		try{
			//Click on Meterial tab
			CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			//	CommonFunctions.clickButtonOrLink(colorLink, "tab", "Color");
			//Add colorway
			Select dropDownCW = new Select(SeleniumDriver.driver.findElement(meterialColor));
			List<WebElement> elementCountCW = dropDownCW.getOptions();
			int countCW = elementCountCW.size();
			//	int count =SeleniumDriver.driver.findElements(selectSpecification).size();
			if(countCW>=2)
			{
				CommonFunctions.selectFromDropDownByIndex(meterialColor, 1);
				strCW=new Select(driver.findElement(meterialColor)).getFirstSelectedOption().getText();
			}
			else
			{
				strCW=Add_Color(data);
			}
			log.info("Selected Color is: "+strCW);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddColorway()", e);
			return "";
		}
		return strCW;
	}

	public static String AddMeterialSource(String[] data) throws Exception{
		try{
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(meterialSource));
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource = elementCountSource.size();
			//log.info("Number of supplier: " + countSource);
			if(countSource>2)
			{
				CommonFunctions.selectFromDropDownByIndex(meterialSource, 1);
				strSource=new Select(driver.findElement(meterialSource)).getFirstSelectedOption().getText();
			}
			else
			{
				AddSupplier(data);
				CommonFunctions.selectFromDropDownByIndex(meterialSource, 1);
				strSource=new Select(driver.findElement(meterialSource)).getFirstSelectedOption().getText();
			}
			log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddMeterialSource()", e);
			return "";
		}
		return strSource;
	}

	public static boolean readView_BOM(String[] data) throws Exception{
		try{
			
		    navigateUptoMaterialPage(data);
			
			if(data[3].contains("ReadView")&& data[5].equalsIgnoreCase("Yes")){
				//Click on Meterial
				
				String strDescrip = driver.findElement(description).getText();
				Assert.assertEquals(strDescrip.trim(), "Description");
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				if(data[0].equalsIgnoreCase("vuser1"))
				{
				   String strDescrip = driver.findElement(resultsZero).getText();
				   strDescrip=strDescrip.substring(0, 11);
				   Assert.assertEquals(strDescrip, "Results 0-0");
				}else
				{
				  Assert.assertEquals(driver.findElement(description).getSize(), 0, "Description is not present"); 	
				}
				return true;

			}
			else{
				log.info("Not applicable(NA)");
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
			
			navigateUptoMaterialPage(data);
			
			if(data[5].equalsIgnoreCase("Yes")){//Update
				//Click on Update button
				Date date = new Date();
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				if (materialName == null)
				{
				 materialName = "UpdateSample";
				 materialNameUpdate= materialName + date.getTime();
				 
				}				
				driver.findElement(name).clear();
				///CommonFunctions.enterTextInTextbox(name, materialNameUpdate);
				CommonFunctions.enterTextInTextboxUpdated(name, materialNameUpdate,"name");				
				//Click 'Save and Check In'
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");	
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				String strBOMName=driver.findElement(nameBOMread).getText();
				//	strBOM=strBOM.substring(6, 14);
				Assert.assertEquals(strBOMName.trim(),materialNameUpdate);
			}
			else if(data[5].equalsIgnoreCase("No")){
				
				if(data[0].equalsIgnoreCase("vuser1"))
				{
				   String strDescrip = driver.findElement(resultsZero).getText();
				   strDescrip=strDescrip.substring(0, 11);
				   Assert.assertEquals(strDescrip, "Results 0-0");
				}else
				{
					Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 
				}
				
				
			}	
			else
			{
				log.info("For this update_BOM is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in update_BOM()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_MBOM(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			Thread.sleep(1000);
			if(data[3].contains("Delete")&& data[5].equalsIgnoreCase("Yes")){
				
				//Create_MaterialInWork(data);
				Create_Material(data); 				
				//Click on BOM Action
				CommonFunctions.clickButtonOrLink(meterialBOMAction, "dropdown", "Material BOM");
				CommonFunctions.clickButtonOrLink(mBOMActionDelete, "link", "Material BOM Delete");
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
				CommonFunctions.handleAlertPopUp();
				String iniBOM=driver.findElement(initializeBOM).getText();
				Assert.assertEquals(iniBOM.trim(),"Initialize BOM");
			}
			else if(data[3].contains("Delete")&& data[5].equalsIgnoreCase("No")){
				CommonProjectFunctions.searchAttributeType("Material", matName);
				//Click on Meterial
				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");	
				CommonFunctions.clickButtonOrLink(meterialTab, "tab", "Meterial");
				
			}
			else
				log.info("Delete is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_BOM()", e);
			return false;
		}
		return true;
	}

	/**************************************************************************/

	//Function consist scenario : BOMIdentificationReadView:Read_View
	public static boolean verifyBOMIdentificationReadView(String[] data) throws Exception{
		try{
					
			navigateUptoMaterialPage(data);
			
			CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

			if(data[5].equalsIgnoreCase("Yes")){//Read_View

				if(driver.findElements(BOMIdentification).size() != 0){
					String BILabel=driver.findElement(BOMIdentification).getText();
					Assert.assertEquals(BILabel.trim(), "BOM Identification");
					log.info("BOM IdentificationReadView label is Pass");
				}else{
					log.error("BOMIdentification ReadView label is Fail");
					fail=true;
				}
			}
			else if(data[5].equalsIgnoreCase("No")){
				if(driver.findElements(BOMIdentification).size() != 0){
					//	log.info("BOMIdentificationReadView label is Present");
					log.error("BOMIdentificationReadView label is Present");
					fail=true;
				}else{
					log.info("BOMIdentificationReadView label is Absent");
				}
			}
			else
			{
				log.info("For this BOMIdentificationReadView is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyBOMIdentificationReadView()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : BOMIdentificationReadView://Update
	public static boolean verifyBOMIdentificationUpdate(String[] data) throws Exception{
		try{
						
			navigateUptoMaterialPage(data);
			
			if(data[5].equalsIgnoreCase("Yes")){//Update
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));		
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");								
				BOMname=matName+"Updated";
				///CommonFunctions.enterTextInTextbox(name, BOMname);
				CommonFunctions.enterTextInTextboxUpdated(name, BOMname,"name");											
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				log.info("BOMIdentification is Updated" + BOMname);
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//assertcheckUpdateButtonStatus(data);
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 

			}	
			else
			{
				log.info("For this BOMIdentification is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyBOMIdentificationUpdate()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			navigateUptoMaterialPage(data);
			
			CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

			if(data[5].equalsIgnoreCase("Yes")){//Read_View
				//	CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
					Assert.assertEquals(GALabel, " General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					log.error("General Attributes label is Absent");
					fail=true;
				}
			}
			else if(data[5].equalsIgnoreCase("No")){
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
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
			return false;
		}
		return true;
	}
	
	
	//Function consist scenario : General Attributes://Update
	public static boolean verifyGeneralAttributesUpdate(String[] data) throws Exception{
		try{
			

			
			navigateUptoMaterialPage(data);
			
			if(data[3].contains("GeneralAttributesUpdate")&& data[5].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));		
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");	
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Material.lstSubassemblyInsertionMode, "Always Insert Copy", "lstSubassemblyInsertionMode");								
				//Click 'Save and Check In'
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				Thread.sleep(6000);
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				log.info("General Attributes is updated with Value :->" + "Always Insert Copy");								
			}
			else if(data[3].contains("GeneralAttributesUpdate")&& data[5].equalsIgnoreCase("No"))
			{
				CommonProjectFunctions.clickMaterialsTab();
				
				//assertcheckUpdateButtonStatus(data);
				
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 

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
	
	
	
	//Function consist scenario : BOMSectionsRead:Read_View
	public static boolean verifyBOMSectionsRead(String[] data) throws Exception{
		try{
			
			
			navigateUptoMaterialPage(data);
			
			CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");

			if(data[5].equalsIgnoreCase("Yes")){//Read_View
				//	CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				if(driver.findElements(descHeading).size() != 0){
					String descHeadLabel=driver.findElement(descHeading).getText();
					Assert.assertEquals(descHeadLabel.trim(), "Description");
					log.info("MeterialBOMSectionsRead label is Present");
				}else{
					log.error("MeterialBOMSectionsRead label is Absent");
					fail=true;
				}
			}
			else if(data[5].equalsIgnoreCase("No")){
				if(driver.findElements(descHeading).size() != 0){
					System.out.println("BOMSections label is Present");
					log.error("BOMSectionsRead label is Present");
					fail=true;
				}else{
					log.info("BOMSectionsRead label is Absent");
				}
			}
			else
			{
				log.info("For this BOMSectionsRead is not applicable(NA)");
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
						
			navigateUptoMaterialPage(data);
			
			if(data[5].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				driver.switchTo().frame("mainFrame");				
				//Component or Location
				action = new Actions(driver);
				action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();				
				CommonFunctions.enterTextInTextboxUpdated(inputCompOrLoca,"Mat description","inputCompOrLoca");
				//Quanity
				action.moveToElement(driver.findElement(quantity)).doubleClick().perform();			
				CommonFunctions.enterTextInTextboxUpdated(inputquantity,"5","inputquantity");
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();								
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");		
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//assertcheckUpdateButtonStatus(data);
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 
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

	/**************************************************************************/	
	public static boolean InWorkreadView_BOM(String[] data) throws Exception{
	     try{
	    	 
	    	   navigateUptoMaterialPage(data);				
				if(data[3].contains("inWorkReadView")&& data[5].equalsIgnoreCase("Yes"))
	            {											
					//Click on + sign
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");					
					assertAttributesStatus(data);					
				}
				else if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("No"))
				{										
					String strDescrip = driver.findElement(resultsZero).getText();
					strDescrip=strDescrip.substring(0, 11);
					Assert.assertEquals(strDescrip, "Results 0-0");
				}else if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("Yes"))					
					{	
						//if root attribute value is Yes then user is able to read view 
						//Click on + sign
						CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
						assertAttributesStatus(data); 						
					}
				else{
					log.info("Not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in rejectedReadView()", e);
				return false;
			}
			return true;
		}
	
	
	public static boolean InWorkUpdate_BOM(String[] data) throws Exception{
		try{
		
			navigateUptoMaterialPage(data);
			
		if(data[5].equalsIgnoreCase("Yes")){//Update
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");				
				materialName = "UpdateInWorkUpdate";
				materialNameUpdate= materialName + "1";							
				driver.findElement(name).clear();				
				CommonFunctions.enterTextInTextboxUpdated(name, materialNameUpdate,"name");
                //Verification
				Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable"); 		  
				
				//Click 'Save and Check In'
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");	
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				String strBOMName=driver.findElement(nameBOMread).getText();				
				Assert.assertEquals(strBOMName.trim(),materialNameUpdate);
			}
			else if(data[5].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 
			}	
			else
			{
				log.info("For this InWorkUpdate_BOM is not applicable(NA)");
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
    	 
    	     navigateUptoMaterialPage(data);
    	 
			if(data[3].contains("UnderReviewReadView")&& data[5].equalsIgnoreCase("Yes"))
            {			
								
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
							
				assertAttributesStatus(data);
			}
			else if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("No"))
			{
				String strDescrip = driver.findElement(resultsZero).getText();
				strDescrip=strDescrip.substring(0, 11);
				Assert.assertEquals(strDescrip, "Results 0-0");

			}else if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("Yes"))					
             {	
	          //if root attribute value is Yes then user is able to read view 
	          //Click on + sign
	           CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
	          assertAttributesStatus(data); 					
             }
			else{
				log.info("Not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in underReviewReadView()", e);
			return false;
		}
		return true;
	}	
	public static boolean underReviewUpdate(String[] data) throws Exception{
		try{			
			navigateUptoMaterialPage(data);
			
			if(data[5].equalsIgnoreCase("Yes")){//Update
				//Click on Update button
				Date date = new Date();
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");				
				materialName = "UpdateSample";
				materialNameUpdate= materialName + date.getTime();					
				driver.findElement(name).clear();				
				CommonFunctions.enterTextInTextboxUpdated(name, materialNameUpdate,"name");
                //Verification
				Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable"); 		  
				
				//Click 'Save and Check In'
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");	
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				String strBOMName=driver.findElement(nameBOMread).getText();
				//	strBOM=strBOM.substring(6, 14);
				Assert.assertEquals(strBOMName.trim(),materialNameUpdate);
			}
			else if(data[5].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 
			}	
			else
			{
				log.info("For this underReviewUpdate is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in underReviewUpdate()", e);
			return false;
		}
		return true;
	}
	public static boolean releasedReadView(String[] data) throws Exception{
	     try{
	    	 
	    	 
	    	     navigateUptoMaterialPage(data);
				
				if(data[3].contains("ReleasedReadView")&& data[5].equalsIgnoreCase("Yes"))
	            {					
										
					//Click on + sign
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					assertAttributesStatus(data);
				}
				else if(data[5].equalsIgnoreCase("No"))
				{
					String strDescrip = driver.findElement(resultsZero).getText();
					strDescrip=strDescrip.substring(0, 11);
					Assert.assertEquals(strDescrip, "Results 0-0");

				}
				else{
					log.info("Not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in releasedReadView()", e);
				return false;
			}
			return true;
		}
		
	public static boolean releasedUpdate(String[] data) throws Exception{
		try{
			
			navigateUptoMaterialPage(data);
		    
		    if(data[5].equalsIgnoreCase("Yes")){//Update
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");				
				materialName = "UpdateReleasedUpdateName";
				materialNameUpdate= materialName + "1";							
				driver.findElement(name).clear();				
				CommonFunctions.enterTextInTextboxUpdated(name, materialNameUpdate,"name");
                //Verification
				Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable"); 		  
				
				//Click 'Save and Check In'
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");	
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				String strBOMName=driver.findElement(nameBOMread).getText();				
				Assert.assertEquals(strBOMName.trim(),materialNameUpdate);
			}
			else if(data[5].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 
			}	
			else
			{
				log.info("For this underReviewUpdate is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in underReviewUpdate()", e);
			return false;
		}
		return true;
	}
	
	public static boolean cancelledReadView(String[] data) throws Exception{
	     try{
				
	    	    navigateUptoMaterialPage(data);
	    	 
				if(data[3].contains("CancelledReadView")&& data[5].equalsIgnoreCase("Yes"))
	            {
									
					//Click on + sign
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					assertAttributesStatus(data);
					
				}
				else if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("No"))
				{
					String strDescrip = driver.findElement(resultsZero).getText();
					strDescrip=strDescrip.substring(0, 11);
					Assert.assertEquals(strDescrip, "Results 0-0");

				}else if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("Yes"))					
                 {	
		          //if root attribute value is Yes then user is able to read view 
		          //Click on + sign
		           CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
		          assertAttributesStatus(data); 					
                 }
				else{
					log.info("Not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in releasedReadView()", e);
				return false;
			}
			return true;
		}

	
 public static boolean cancelledUpdate(String[] data) throws Exception{
		try{	
			
			navigateUptoMaterialPage(data);
			
		    if(data[5].equalsIgnoreCase("Yes")){//Update
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");				
				materialName = "UpdateCancelled";
				Date date = new Date();
				materialNameUpdate= materialName + date.getTime();							
				driver.findElement(name).clear();				
				CommonFunctions.enterTextInTextboxUpdated(name, materialNameUpdate,"name");
                //Verification
				Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable"); 		  
				
				//Click 'Save and Check In'
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");	
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				String strBOMName=driver.findElement(nameBOMread).getText();				
				Assert.assertEquals(strBOMName.trim(),materialNameUpdate);
			}
			else if(data[5].equalsIgnoreCase("No")){
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 
			}	
			else
			{
				log.info("For this cancelledUpdate is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in cancelledUpdate()", e);
			return false;
		}
		return true;
	}
 
public static boolean rejectedReadView(String[] data) throws Exception{
	     try{
				
	    	 
	    	   navigateUptoMaterialPage(data);
	    	 
				if(data[3].contains("RejectedReadView")&& data[5].equalsIgnoreCase("Yes"))
	            {		
					
					//Click on + sign
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
				}
				else if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("No"))
				{
					String strDescrip = driver.findElement(resultsZero).getText();
					strDescrip=strDescrip.substring(0, 11);
					Assert.assertEquals(strDescrip, "Results 0-0");

				}else if(data[5].equalsIgnoreCase("No") && data[27].equalsIgnoreCase("Yes"))					
                 {	
		          //if root attribute value is Yes then user is able to read view 
		          //Click on + sign
		           CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
		          assertAttributesStatus(data); 					
                 }
				else{
					log.info("Not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in rejectedReadView()", e);
				return false;
			}
			return true;
		}
	public static boolean rejectedUpdate(String[] data) throws Exception{
		try{
			
			navigateUptoMaterialPage(data);
			
		    if(data[5].equalsIgnoreCase("Yes")){//Update
				//Click on Update button
		    	Date date = new Date();
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");				
				materialName = "UpdateRejected";
				materialNameUpdate= materialName + date.getTime();							
				driver.findElement(name).clear();				
				CommonFunctions.enterTextInTextboxUpdated(name, materialNameUpdate,"name");
                //Verification
				Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable"); 		  
				
				//Click 'Save and Check In'
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
				CommonFunctions.handleAlertPopUp();
				SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");	
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				String strBOMName=driver.findElement(nameBOMread).getText();
				//	strBOM=strBOM.substring(6, 14);
				Assert.assertEquals(strBOMName.trim(),materialNameUpdate);
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 
			}	
			else
			{
				log.info("For this rejectedUpdate is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in rejectedUpdate()", e);
			return false;
		}
		return true;
	}
	public static boolean readyForReviewReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[7]);
			CommonProjectFunctions.clickMaterialsTab();
			//Select Source
			CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
			CommonFunctions.handleAlertPopUp();
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
			CommonFunctions.handleAlertPopUp();
			strBOMDetail = driver.findElement(BOMDetails).getText();

			if(data[5].equalsIgnoreCase("Yes")){
				//Select Under review BOM				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMId, BOMnameReadyforReview,"BOMId");
				//Click on + sign
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
				Assert.assertEquals(GALabel, " General Attributes:");

			}
			else if(data[5].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(BOMId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(BOMnameReadyforReview,options);
				Assert.assertFalse(bVal);
			}
			else
				log.info("Not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in readyForReviewReadView()", e);
			return false;
		}
		return true;
	}

	public static boolean readyForReviewUpdate(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[7]);
			CommonProjectFunctions.clickMaterialsTab();
			//Select Source
			CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
			CommonFunctions.handleAlertPopUp();
			//Select Specification
			CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
			CommonFunctions.handleAlertPopUp();
			//Select In Work BOM
			///CommonFunctions.selectFromDropDownByVisibleText(BOMId, BOMnameReadyforReview);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMId, BOMnameReadyforReview,"BOMId");
			if(data[5].equalsIgnoreCase("Yes")){//Update
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				//Verification
				Assert.assertEquals(driver.findElements(name).size(), 1, "BOM Identification Name is Editable"); 
				Assert.assertEquals(driver.findElements(colorway).size(), 1, "General Attributes Colorway is Editable"); 
			}
			else if(data[5].equalsIgnoreCase("No"))
			{
				//assertcheckUpdateButtonStatus(data);
				Assert.assertEquals(driver.findElements(updateBtn ).size(), 0, " Update button is not present"); 
			}	
			else
			{
				log.info("For this readyForReviewUpdate is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in readyForReviewUpdate()", e);
			return false;
		}
		return true;
	}

	public static Boolean AddSupplier(String[] data) throws Exception{
		try{			
			//Click on sourcing tab
			CommonFunctions.clickButtonOrLink(sourcingTab, "Tab", "Sourcing");
			///CommonFunctions.selectFromDropDownByVisibleText(meterialSupplierAction, "Add Suppliers");
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(meterialSupplierAction, "Add Suppliers","meterialSupplierAction");
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			//Click on show All
			CommonFunctions.clickButtonOrLink(supplierShowAll, "link", "Show All");
			//Select check box
			CommonFunctions.selectCheckbox(By.xpath("//a[text()='"+data[26]+"']/preceding::input[1]"));
			//Click on Select
			CommonFunctions.clickButtonOrLink(selectBtn, "btn", "Select");
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

	public static Boolean selectSupplier(String a,List<WebElement> b,String[] data) throws Exception{
		try{
			for (WebElement option : b) {
				System.out.println("b: "+option.getText());
				System.out.println("a: "+a);
				if (option.getText().trim().contains(a.trim())) {
					CommonFunctions.selectCheckbox(By.xpath("//a[text()='"+data[26]+"']/preceding::input[1]"));
					return true;
				}
			}

		}catch(Exception e){
			log.error("Exception in selectSupplier()", e);
			return false;
		}
		return true;
	}

	public static String Add_Color(String[] data) throws Exception{
		try{
			//Click on Color
			CommonFunctions.clickButtonOrLink(colorLink, "link", "Color");			
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(selectMeterialColor, "Add Multiple Colors","selectMeterialColor");
			CommonFunctions.waitForElementTobeClickable(btnSearch);
			//Click on Search
			CommonFunctions.clickButtonOrLink(btnSearch, "Btn", "Search");
			for(int i=3;i<=4;i++)
			{
				System.out.println(By.xpath("//div[@id='chooserResultsDiv']/table/tbody/tr[5]/td/div[3]/table/tbody/tr["+i+"]/td/input"));
				CommonFunctions.waitForElementTobeClickable(By.xpath("//div[@id='chooserResultsDiv']/table/tbody/tr[5]/td/div[3]/table/tbody/tr["+i+"]/td/input"));
				CommonFunctions.selectCheckbox(By.xpath("//div[@id='chooserResultsDiv']/table/tbody/tr[5]/td/div[3]/table/tbody/tr["+i+"]/td/input"));
			}
			//Click on Select
			CommonFunctions.clickButtonOrLink(selectBtn, "btn", "Select");
			CommonFunctions.waitForElementTobeClickable(createtBtn);
			//Click on Create
			CommonFunctions.clickButtonOrLink(createtBtn, "Btn", "Create");
			//Close frame
			CommonFunctions.clickButtonOrLink(colorCloseIcon, "icon", "Cross");
			//Click on Meterial tab
			CommonFunctions.clickButtonOrLink(meterialTab, "link", "Meterial");
			CommonFunctions.selectFromDropDownByIndex(meterialColor, 1);
			strCW=new Select(driver.findElement(meterialColor)).getFirstSelectedOption().getText();			
		}catch(Exception e){
			log.error("Exception in Add_Color()", e);
			//	return false;
		}
		return strCW;
	}

	//	//This funcion is to select Update Lifecycle State	
	public static String changeBOMStatus() throws Exception{
		try{
			valULCS = new Select(driver.findElement(BOMStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus,"Released","BOMStatus");
			}
			else if(valULCS.contains("Under Review")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "In Work","BOMStatus");
			}
			else if(valULCS.contains("Released")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "In Work","BOMStatus");
			}
			else if(valULCS.contains("Canceled")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "In Work","BOMStatus");
			}
			else if(valULCS.contains("Rejected")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "In Work","BOMStatus");
			}
			else if(valULCS.contains("Ready For Review")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "In Work","BOMStatus");
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
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus,"Under Review","BOMStatus");
			}			
			else if(valULCS.contains("Released")){
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Under Review","BOMStatus");
			}
			else if(valULCS.contains("Canceled")){
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Under Review","BOMStatus");
			}
			else if(valULCS.contains("Rejected")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Under Review","BOMStatus");
			}
			else if(valULCS.contains("Ready For Review")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Under Review","BOMStatus");
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
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus,"Released","BOMStatus");
			}
			else if(valULCS.contains("Under Review")){
			
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "In Work","BOMStatus");
			}			
			else if(valULCS.contains("Canceled")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Released","BOMStatus");
			}
			else if(valULCS.contains("Rejected")){			
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Released","BOMStatus");
			}
			else if(valULCS.contains("Ready For Review")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Released","BOMStatus");
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
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus,"Canceled","BOMStatus");
			}
			else if(valULCS.contains("Under Review")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Canceled","BOMStatus");
			}
			else if(valULCS.contains("Released")){			
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Canceled","BOMStatus");
			}			
			else if(valULCS.contains("Rejected")){
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Canceled","BOMStatus");
			}
			else if(valULCS.contains("Ready For Review")){
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Canceled","BOMStatus");
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
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus,"Rejected","BOMStatus");
			}
			else if(valULCS.contains("Under Review")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Rejected","BOMStatus");
			}
			else if(valULCS.contains("Released")){			
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Rejected","BOMStatus");
			}
			else if(valULCS.contains("Canceled")){				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Rejected","BOMStatus");
			}			
			else if(valULCS.contains("Ready For Review")){			
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Rejected","BOMStatus");
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
			
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus,"Ready For Review","BOMStatus");
			}
			else if(valULCS.contains("Under Review")){
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Ready For Review","BOMStatus");
			}
			else if(valULCS.contains("Released")){
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Ready For Review","BOMStatus");
			}
			else if(valULCS.contains("Canceled")){
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Ready For Review","BOMStatus");
			}
			else if(valULCS.contains("Rejected")){
				
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatus, "Ready For Review","BOMStatus");
			}			
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
				CommonFunctions.enterTextInTextboxUpdated(Product.Editable_UpdateLifecycleState,"Complete","Editable_UpdateLifecycleState");
			}
			else if(valULCS.contains("Complete")){			
				CommonFunctions.enterTextInTextboxUpdated(Product.Editable_UpdateLifecycleState, "In Work","Editable_UpdateLifecycleState");
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
