package com.hasbro.security;
/**
 * @author Govind Pandey
 *Prerequisite :
 *Product should be created and added in column 5. Ensure that only one Product exist with that Product Number.
 *Make sure Product does have external source created.
 *External source say Funskool must have a specification who's state is Supplier Released 
 *Ensure there is column in automation security sheet namely "Is vendor user" [column 21] which will notify that user is a vendor user or non-vendor user.  
 *Ensure there is column in automation security sheet namely "In Work Read" [column 19] which will notify that does user able to Read In work BOM or not. 
 *  Not means user is not able to see In work BOM but able to see Released BOM.
 *In Column 15 of automation sheet user can mention that what comment user needs to add in any BOM detail page comment section.
 *
 *
 */

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

import base.SeleniumDriver;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;



public class ExternalBOM extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	public static By addNewbomTab= By.xpath(".//*[@id='ADD_PAGETab']/a");
	public static By initializebom= By.xpath("//a[contains(text(),'Initialize BOM')]");
	public static By headingCreatebom= By.xpath("//td[contains(text(),'Create BOM')]");
	public static By colorwayName= By.xpath("//td[contains(text(),'Colorway Name')]//following::select[1]");
	public static By colorway= By.xpath("//td[contains(text(),'Colorway')]//following::select[1]");
	public static By currencyvalue=	By.xpath("//td[contains(text(),'Currency')]//following::select[1]");
	public static By currency= By.xpath("//td[contains(text(),'Currency')]//following::select[1]");
	public static By wave= By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By Factory = By.xpath("//a[contains(text(),'Factory')]");
	public static By findFactory = By.xpath("//span[contains(text(),'Find: Factory')]");
	public static By factoryFindSearch = By.xpath(".//*[@id='SearchButton1']");
	public static By factorySearchResult = By.xpath("//span[contains(text(),'Search Results for Vendor (Supplier)')]");
	public static By chooseFactory= By.xpath("//a[contains(text(),'(choose)')]");	
	public static By vendorbomstatustable= By.xpath("//td[contains(text(),'Vendor BOM Status')]");
	public static By bomdetailpage = By.xpath(".//*[@id='hbBOMStatus']");
	public static By noneBtn= By.xpath("//a[contains(text(),'None')]");
	public static By selectSource= By.id("sourcingConfigId");
	public static By selectSpecification= By.id("contextSpecId");
	public static By compOrLoca= By.id("r1_partName");
	public static By checmicalDesc= By.id("r2_partName");
	public static By meterial= By.id("r1_materialDescription");
	public static By meterialRow2= By.id("r2_materialDescription");
	public static By plasticDescription= By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[3]/td[1]");
	public static By plasticMeterialDesc= By.xpath(".//table[contains(@id,'TBLT')]/tbody/tr[3]/td[3]");
	public static By chemicalsDescription= By.xpath("//table[contains(@id,'TBLT')]/tbody/tr[5]/td[1]");
	public static By chemicalsMeterialDesc= By.xpath(".//table[contains(@id,'TBLT')]/tbody/tr[5]/td[3]");
	public static By inputCompOrLoca= By.xpath("//div[@id='partNameSourceDiv']/input");
	public static By inputMaterial= By.xpath("//div[@id='materialDescriptionSourceDiv']/textarea");
	public static By btnSaveAndCheckIn= By.xpath("//a[text()='Save and Check In']");
	public static By headerAttributes= By.xpath("//div[@id='genDetails_plus']/a[2]");
	public static By headerAttributesBtn= By.xpath("//a[contains(text(),'Header Attributes')]");
	public static By bomId= By.xpath("//*[@id='bomId']");
	public static By bomtypeid= By.id("bomTypeId");
	public static By headerAttributesPlus= By.xpath("//div[@id='genDetails_plus']/a[1]/img");
	public static By bomAction= By.xpath("//div[@id='UPDATE_BTNS']//a[contains(text(),'Actions')]");
	public static By checkedOutByYou= By.xpath("//td[contains(text(),'Checked Out by: You')]");
	public static By billOfMaterials= By.xpath("//select[@id='bomId']/option[@selected='']");
	public static By ro_UpdateLifecycleState = By.xpath("//div[contains(@id,'systemInformationOR')]//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By updateBtn= By.xpath("//a[contains(text(),'Update')]");
	public static By updateBtn1= By.xpath("//div[@id='UPDATE_BTNS']/table/tbody//a[@disabled='disabled']");
	public static By actionDD= By.id("prodseasonActions");
	public static By sourcingLead   = By.xpath("//td[contains(text(),'Sourcing Lead')]//following::select[1]");
	public static By sourcingHead   = By.xpath("//td[contains(text(),'Sourcing Head')]//following::select[1]");
	public static By bomDetails  = By.xpath("//a[text()='BOM Details']");
	public static By bomStatusUpdate = By.xpath("//td[contains(text(),'BOM Status')]//following::select[1]");	
	public static By bomCostSummary  = By.xpath("//td[contains(text(),'Vendor BOM Cost Summary')]");	
	public static By bomComments  = By.xpath("//td[contains(text(),'Comments')]");
	public static By enterComments  = By.xpath("//a[text()='Enter Comment']");
	public static By postNewComment  = By.xpath("//td[contains(text(),'Post New Comment')]//following::textarea[1]");
	public static By post  = By.xpath("//a[text()='Post']");
	public static By descHeading  = By.xpath("//td[@class='TABLESUBHEADER' and text()='Description']");
	public static By bomStatus  = By.xpath("//td[contains(text(),'Vendor BOM Status')]//following::select[1]");
	public static By bommmatchcostsummary  = By.xpath("//td[contains(text(),'Vendor BOM Match Cost Summary')]");
	public static By componentPlus  = By.xpath("//a[text()='Components']/preceding-sibling::a");
	public static By removeLink  = By.xpath("//a[text()='Remove']");
	public static By detailpagecurrencyvalue= By.xpath("//td[contains(text(),'Currency')]//following::td[1]");
	public static By plasticClear  = By.xpath("//div[@id='hbPlasticsTabEditorTableDiv']//a[contains(text(),'Clear')]");
	public static By chemicalClear  = By.xpath("//div[@id='hbChemicalsTabEditorTableDiv']//a[contains(text(),'Clear')]");
	public static By viewBtn= By.xpath("//img[@src='/Windchill/netmarkets/images/view.png']");
	public static By  bcTotalbomCost =By.id("hbTotalBOMCost");
	public static By  bcTotalPlasticCost =By.id("hbTotalPlasticCost");
	public static By  bcTotalChemicalsCost =By.id("hbTotalChemicalsCost");
	public static By  bcTotalPurchasedPartsCost =By.id("hbTotalPurchasedPartsCost");
	public static By  bcTotalElectronicsCost =By.id("hbTotalElectronicsCost");
	public static By  bcTotalSoftGoodsCost =By.id("hbTotalSoftGoodsCost");
	public static By  bcTotalPackagingCost =By.id("hbTotalPackagingCost");
	public static By  bcTotalGenDecoLaborCost =By.id("hbTotalGenDecoLaborCost");
	public static By  bcTotalMoldingLaborCost =By.id("hbTotalMoldingLaborCost");
	public static By  bcTotalMarkUpCost =By.id("hbTotalMarkUpCost");
	public static By  bcTotalPlasticUsageK =By.id("hbTotalPlasticUsageK");
	public static By totalbomMatchCost =By.id("hbTotalBOMMatchCost");
	public static By totalPlasticMatchCost  =By.id("hbTotalPlasticMatchCost");
	public static By totalChemicalsMatctCost =By.id("hbTotalChemicalsMatchCost");
	public static By totalPurchasedPartsMatchCost =By.id("hbTotalPurchasedPartsMatchCost");
	public static By totalElectronicsMatchCost =By.id("hbTotalElectronicsMatchCost");
	public static By totalSoftGoodsMatchCost =By.id("hbTotalSoftGoodsMatchCost");
	public static By totalPackagingMatchCost =By.id("hbTotalPackagingMatchCost");
	public static By totalGeneralDecoLaborMatchCost =By.id("hbTotalGeneralDecoLaborMatchCost");
	public static By totalMoldingLaborMatchCost =By.id("hbTotalMoldingLaborMatchCost");
	public static By totalMarkUpMatchCost =By.id("hbTotalMarkUpMatchCost");
	public static By totalMasterCartonPackagingCost =By.id("hbTotalMasterCartonPkgCost");
	public static By totalMiscellaneousCost =By.id("hbTotalMiscellaneousCost");
	public static By totalMarkUpCost =By.id("hbTotalMarkUpCost");
	public static By totalLaborCost =By.id("hbTotalLaborCost");
	public static By statusErrorMsg = By.xpath("//td[contains(text(),'Sorry, You do not have the necessary privileges to update the Status field.')]");
	public static By closeEditor = By.xpath("//a[text()='Close Editor']");
	public static By mastercartonDescription= By.xpath("//a[contains(text(),'Master Carton Packaging')]//following::td[1]");
	public static By mastercartonpackagingClear  = By.xpath("//*[@id='hbMasterCartonPackagingTabEditorTableDiv']//a[contains(text(),'Clear')]");	

	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	public static String bomname;
	static String strBillOfMaterials;
	static Actions action;
	static String strUpdate;
	static String strSpec;
	static String strSource;
	static String strCW;
	static String strbomInWork;
	static String strbomIW;
	static String strbomUR;
	static String strbomR;
	static String strbomC;
	static String strbomRj;
	static String strbomRFR;
	static String strbomRelDelete;
	static String strbomInWorkDelete;
	static String strTestCaseName = null;
	static String errMsgStatus="Sorry, You do not have the necessary privileges to update the Status field." ;
	
	@Test(dataProvider="testDataTest")
	public void tcbom(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
					
			strTestCaseName = data[0]+":" + data[3];			
			log.info("Inside Test Case:-> " + strTestCaseName + " for CostSheet Security");				
			
			
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

			if(data[3].equalsIgnoreCase("Create"))
			{ create_bom(data); }
			if(data[3].equalsIgnoreCase("CreateInWork"))
			{ createInWork_bom(data); }
			if(data[3].equalsIgnoreCase("CreateUnderReview"))
			{ createUnderReview_bom(data); }
			if(data[3].equalsIgnoreCase("CreateReleased"))
			{ createReleased_bom(data); }
			
			if(data[3].equalsIgnoreCase("CreateReleasedDelete"))
			{ create_Released_Delete(data); }
			
			if(data[3].equalsIgnoreCase("CreateCancelled"))
			{ createCancelled_bom(data); }
			if(data[3].equalsIgnoreCase("CreateRejected"))
			{ createRejected_bom(data); }
			if(data[3].equalsIgnoreCase("CreateReadyforReview"))
			{ createReadyforReview_bom(data); }

			if(data[3].equalsIgnoreCase("readView"))
			{ readView_bom(data); }
			if(data[3].equalsIgnoreCase("update"))
			{ update_bom(data); }

			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_bom(data); }

			if(data[3].equalsIgnoreCase("inWorkReadView"))
			{ inWorkreadView_bom(data); }
			if(data[3].equalsIgnoreCase("inWorkUpdate"))
			{ inWorkUpdate_bom(data); }

			if(data[3].equalsIgnoreCase("UnderReviewReadView"))
			{ underReviewReadView(data); }
			if(data[3].equalsIgnoreCase("UnderReviewUpdate"))
			{ underReviewUpdate(data); }
			

			if(data[3].equalsIgnoreCase("ReleasedReadView"))
			{ releasedReadView(data); }
			if(data[3].equalsIgnoreCase("ReleasedUpdate"))
			{ releasedUpdate(data); }

			if(data[3].equalsIgnoreCase("CancelledReadView"))
			{ cancelledReadView(data); }
			if(data[3].equalsIgnoreCase("CancelledUpdate"))
			{ cancelledUpdate(data); }

			if(data[3].equalsIgnoreCase("RejectedReadView"))
			{ rejectedReadView(data); }
			if(data[3].equalsIgnoreCase("RejectedUpdate"))
			{ rejectedUpdate(data);   }

			if(data[3].equalsIgnoreCase("ReadyforReviewReadView"))
			{ readyForReviewReadView(data); }
			if(data[3].equalsIgnoreCase("ReadyforReviewUpdate"))
			{ readyForReviewUpdate(data);   }

			//Read view verification
			if(data[3].equalsIgnoreCase("GeneralAttributesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttributesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }

			if(data[3].equalsIgnoreCase("bomCostingSummaryReadView"))
			{ verifybomCostingSummaryReadView(data); }
			if(data[3].equalsIgnoreCase("bomCostingSummaryUpdate"))
			{ verifybomCostingSummaryUpdate(data); }
			
			if(data[3].equalsIgnoreCase("bomMatchCostingSummaryReadView"))
			{ verifybomMatchCostingSummaryReadView(data); }
			
			if(data[3].equalsIgnoreCase("bomMatchCostingSummaryUpdate"))
			{ verifybomMatchCostingSummaryUpdate(data); }

			if(data[3].equalsIgnoreCase("bomCommentsRead"))
			{ verifybomCommentsRead(data); }
			if(data[3].equalsIgnoreCase("bomCommentsUpdate"))
			{ verifybomCommentsUpdate(data); }

			if(data[3].equalsIgnoreCase("bomSectionsRead"))
			{ verifybomSectionsRead(data); }
			if(data[3].equalsIgnoreCase("bomSectionsUpdate"))
			{ verifybomSectionsUpdate(data); }
			
			if(data[3].equalsIgnoreCase("bomStatusRead"))
			{ log.info("Out side verifybomStatusRead");
			         verifybomStatusReadView(data); 
			  log.info("In side verifybomStatusRead");}
			
			if(data[3].equalsIgnoreCase("bomStatusUpdate"))
			{ log.info("Out side verifybomStatusRead");
			               verifybomStatusUpdate(data); 
			  log.info("In side verifybomStatusUpdate");}		

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

			public static boolean verifybomStatusReadView(String[] data) throws Exception{
				try{
					//Browse to Material Tab
					navigateToMaterialTab(data);
											
					if(data[3].contains("bomStatusRead")&& data[4].equalsIgnoreCase("Yes")){//Read_View
						
					if(data[19].equalsIgnoreCase("No")){
						
						//Select Released bom
							CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
							CommonFunctions.handleAlertPopUp();
							
							//Click on Expand button
							CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
						
					    //Check the BOM Status table
						String strbomStatusInReadView =driver.findElement(vendorbomstatustable).getText();
						
						//Verify that Vendor BOM Status table is visible and showing correct name
						Assert.assertEquals(strbomStatusInReadView.trim(),"Vendor BOM Status:");						    
						log.info("bom Status attribute value verified:->" + strbomStatusInReadView);
						
						}
						
						else {
							
						//Select In Work bom
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						///CommonFunctions.selectFromDropDownByVisibleTextUpdated
						CommonFunctions.handleAlertPopUp();
						
						//Click on Expand Collapse button
						CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");				
						
						//Check the BOM Status table
						String strbomStatusInReadView =driver.findElement(vendorbomstatustable).getText();
						 
						//Verify that BOM Status table name is correct
						Assert.assertEquals(strbomStatusInReadView.trim(),"Vendor BOM Status:");					    
						log.info("bom Status attribute value verified:->" + strbomStatusInReadView);
						}
						
					}
					else if(data[3].contains("bomStatusRead")&& data[4].equalsIgnoreCase("No"))
					{
						if (data[19].equalsIgnoreCase("No")){
							
							//Select Released BOM from drop down
							Select select = new Select(driver.findElement(bomId));
							List<WebElement> options = select.getOptions();
							boolean bVal=CommonFunctions.dropDownOptionVerification(strbomR,options);
							
							//Verify that Released BOM is not displaying
							Assert.assertFalse(bVal);	
							log.info("Is In Work bom is visible: " + bVal);

							
						}
						
						else {
						
						//Select In Work BOM
						Select select = new Select(driver.findElement(bomId));
						List<WebElement> options = select.getOptions();
						boolean bVal=CommonFunctions.dropDownOptionVerification(strbomInWork,options);
						
						//Verify that In Work BOM is not displaying
						Assert.assertFalse(bVal);	
						log.info("Is In Work bom is visible: " + bVal);

						}
						
					}
				}catch(Exception e){
					fail=true;
					log.error("Exception in verifybomStatusReadView()", e);
					return false;
				}
				return true;
			}
		

			public static boolean verifybomStatusUpdate(String[] data) throws Exception{
				try{
					
					//Browse to Material Tab
					navigateToMaterialTab(data);
					
					if(data[4].equalsIgnoreCase("Yes")){//Update

						//Select In Work bom
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						///CommonFunctions.selectFromDropDownByVisibleTextUpdated
						CommonFunctions.handleAlertPopUp();
						
						//Click on Update button
						CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
						//Switch to mainframe
						WebDriverWait wait = new WebDriverWait(driver,10);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
						
						//Click on Header Attribute button
						CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
						
						//Select another BOM Status apart from In Work
						CommonFunctions.selectFromDropDownByIndex(bomStatusUpdate, 2);
		
					String bomstatuseditpage=new Select(driver.findElement(bomStatusUpdate)).getFirstSelectedOption().getText();
						
						//Click button btnSaveAndCheckIn
						CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
						CommonFunctions.handleAlertPopUp();
						
						//Switch to default frame
						driver.switchTo().defaultContent();
						
						//Switch to contentFrame
						driver.switchTo().frame("contentframe");
						
						//Expand Header attribute
						CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
						
						//Get the value of BOM status text 
						String bomstatusvalue= driver.findElement(bomdetailpage).getText();
						
						//Verify that BOM Status in detail page and edit page is same
						Assert.assertEquals(bomstatuseditpage , bomstatusvalue);
						log.info("BOM Status in Detail page is: " + bomstatusvalue);
						
						//Revert the bom status back to In Work. Click on Update button						
						CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
						
						//Switch to mainframe
						WebDriverWait wait1 = new WebDriverWait(driver,10);
						wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
						
						//Click on Header Attribute button
						CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
						
						//Select Rejected bom status from drop down
						CommonFunctions.selectFromDropDownByVisibleText(bomStatusUpdate, "In Work");
						
						//Click button btnSaveAndCheckIn
						CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
						CommonFunctions.handleAlertPopUp();
						
						//switch to default frame
						driver.switchTo().defaultContent();
						
						//Switch to contentFrame
						driver.switchTo().frame("contentframe");
						
						//Expand Header attribute
						CommonFunctions.handleAlertPopUp();
						CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
						
						//Check the BOM status value in detail page
						String bomstatusvalue1= driver.findElement(bomdetailpage).getText();
						
						//Verify that BOM Status value is updated to In Work
						Assert.assertEquals("In Work" , bomstatusvalue1);					
						log.info("bom gets updated back to " +bomstatusvalue1 );
						
					}
					else if(data[3].contains("bomStatusUpdate")&& data[4].equalsIgnoreCase("No")){
						
						if(data[19].equalsIgnoreCase("No")){
							
						//Select Released bom
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
						CommonFunctions.handleAlertPopUp();

							//Check that Update button disable or not
							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							
							//Verify that Update button is disable
							log.info("Is bom update button is disable:" + strUpdate);
							Assert.assertEquals(strUpdate, "true");
											
						}
						
						else {
						
						//Select In Work BOM
						CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
						CommonFunctions.handleAlertPopUp();
						//Get the size of disable Update button
						int strUpd = driver.findElements(updateBtn1).size();
						if(strUpd!=0){
							//Check that Update button disable or not
							strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
							//Verify that Update button is disable
							log.info("Is bom update button is disable:" + strUpdate);
							Assert.assertEquals(strUpdate, "true");
						}						
						else {							
							//Click on Update button
							CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
							//Switch to mainframe
							WebDriverWait wait = new WebDriverWait(driver,10);
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));

							//Click on Header Attribute button
							CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

							//Select another BOM Status apart from In Work
							CommonFunctions.selectFromDropDownByIndex(bomStatusUpdate, 2);

							//Click button btnSaveAndCheckIn
							CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
							CommonFunctions.handleAlertPopUp();
							Thread.sleep(3000);
							
							//Switch to content frame
							driver.switchTo().frame("contentframe");
							if(driver.findElements(InternalBOMSoftG.headerAttributesBtn).size()==0)
							{
								driver.switchTo().frame("mainFrame");
							}
							
							verifyErrorMessageStatus();
						}	
					}
				}
				}
				catch(Exception e){ 
					fail=true;
					log.error("Exception in verifybomStatusUpdateUpdate()", e);
					return false;
				}
				return true;
			}		
	
			
			public static boolean verifyErrorMessageStatus() throws Exception{
				try {
					//Verify that actual and expected error message is correct
					Assert.assertEquals(driver.findElement(statusErrorMsg).getText().trim(),errMsgStatus);
					log.info("Verified error message as : " +errMsgStatus +"for" + CommonProjectFunctions.strTestCaseName);
					//Click on Close Editor button
					CommonFunctions.clickButtonOrLink(closeEditor, "btn", "Close editor");
					CommonFunctions.handleAlertPopUp();
					//Wait for 2 sec
					Thread.sleep(2000);
					return true;
				}catch(Exception e){
					fail=true;
					log.error("Exception in verifyErrorMessageStatus()", e);
					return false;
				}
			}
			
		public static String create_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){				
				
				//Click Add New bom tab
				CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
				//Enter bom Type
				CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);

				//Click Initialize bom
				CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
				//Create bom page
				fillCreatebom(data);
				//Switch to mainframe
				driver.switchTo().frame("mainFrame");
				//Enter in Component or Location attribute
				action = new Actions(driver);
				action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
				
				//Click on Save and Check in button
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				
				//Switch to Default frame
				driver.switchTo().defaultContent();
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Click on Header Attribute button
				CommonFunctions.waitForVisibilityOfElement(headerAttributes);
				
				//Check the name of the first selected BOM
				strbomIW=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
				System.out.println("The name of the bom is:" + strbomIW);
				//Remove leading and trailing white spaces
				strbomIW=strbomIW.trim().replaceAll("\\s+", " ");
				System.out.println("The name of the bom after trim is:" + strbomIW);
				
				}
	
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				//Get the size of Add new BOM tab
				System.out.println(driver.findElements(addNewbomTab).size());
				//Verify that Add New BOM tab is not present
				Assert.assertEquals(driver.findElements(addNewbomTab).size(), 0, "bom can not create"); 
				System.out.println("Use does not have access to create bom");
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_bom()", e);
		}
		return strbomIW;
	}

	public static String createInWork_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
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
			strbomInWork=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			//Remove the trailing and leading white space
			strbomInWork=strbomInWork.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:"+strbomInWork);

		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateInWork_bom()", e);
	
		}
		return strbomInWork;
	}

	public static String createUnderReview_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			//Switch to default content
			driver.switchTo().defaultContent();
			
			//Switch to content frame
			driver.switchTo().frame("contentframe");

			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			
			//Check the name of the BOM First Selected
			strbomUR=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom is:" + strbomUR);
			//Remove the leading and trailing whitespace
			strbomUR=strbomUR.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom aftre trim:" + strbomUR);
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			//Wait for main frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			if (data[3].contains("CreateUnderReview")){
				valULCSAfterChange=changeStatusUnderReview();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default Frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			
			log.info("UnderReview status bom is: "+strbomUR);
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateUnderReview_bom()", e);
					}
		return strbomUR;
	}

	public static String createReleased_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			
			//Get the name of first Selected BOM
			strbomR=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom before trim is:"+strbomR);
			//Remove the Leading and trailing white spaces
			strbomR=strbomR.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:"+strbomR);
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			//Wait for main frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			
			//Change the status of BOM to Released
			if(data[3].contains("CreateReleased")){
				valULCSAfterChange=changeStatusReleased();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Released status bom is:"+strbomR);
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateReleased_bom()", e);
					}
		return strbomR;
	}
	
	public static String create_Released_Delete(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			
			//Get the name of first selected BOM
			strbomRelDelete = new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom is: " + strbomRelDelete);
			strbomRelDelete=strbomRelDelete.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim:" + strbomRelDelete);
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			//Wait for Main frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			//Click on Header attribute button
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			
			//Change the status of BOM to Released
			if(data[3].contains("CreateReleasedDelete")){
				valULCSAfterChange=changeStatusReleased();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Released status bom is: "+strbomRelDelete);
			
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);
			//Click on '+' sign of component
			CommonFunctions.clickButtonOrLink(componentPlus, "btn", "Component plus sign");
			//Click on Action
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strbomRelDelete+"')]/preceding::td[2]/a"), "dropdown", "Action");
			CommonFunctions.waitForVisibilityOfElement(removeLink);
			//Click on Remove
			CommonFunctions.clickButtonOrLink(removeLink, "link", "Remove");
			CommonFunctions.handleAlertPopUp();

			
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_Released_Delete()", e);
		}
		return strbomRelDelete;
	}

	public static String createCancelled_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material tab
			navigateToMaterialTab(data);
			
			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);
			
			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			
			//Get the name of the first selected BOM
			strbomC=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom is: " + strbomC );
			//Remove the leading and Trailing space
			strbomC=strbomC.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:" + strbomC );
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			//Wait for Main frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			
			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			
			//Change the status of BOM to Cancelled
			if(data[3].contains("CreateCancelled")){
				valULCSAfterChange=changeStatusCancelled();
			}

			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			
			log.info("Cancelled status bom is: "+strbomC);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateCancelled_bom()", e);
		}
			return strbomC;
	}

	public static String createRejected_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);
			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			
			//Get the name of first selected BOM
			strbomRj=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom is: " + strbomRj);
			//Trim the name of the BOM
			strbomRj=strbomRj.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is:" + strbomRj);
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			//Wait for the main Frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			
			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			
			//Change the status of BOM to Rejected
			if(data[3].contains("CreateRejected")){
				valULCSAfterChange=changeStatusRejected();
			}
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Rejected status bom is: "+strbomRj);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateRejected_bom()", e);
			//	return "";
		}
		return strbomRj;
	}

	public static String createReadyforReview_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material tab
			navigateToMaterialTab(data);
			
			//Click Add New bom tab
			CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
			//Enter bom Type
			CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);

			//Click Initialize bom
			CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
			//Create bom page
			fillCreatebom(data);
			//Switch to mainframe
			driver.switchTo().frame("mainFrame");
			//Component or Location
			action = new Actions(driver);
			action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
			CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);

			//Click button btnSaveAndCheckIn
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			//Get the name of the first Selected BOM
			strbomRFR=new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
			System.out.println("The name of the bom is: " + strbomRFR);
			//Trim the name of the BOM
			strbomRFR=strbomRFR.trim().replaceAll("\\s+", " ");
			System.out.println("The name of the bom after trim is: " + strbomRFR);
			
			//Click on Update button
			CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
			//Wait for main frame
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
			
			//Click on Header Attribute button
			CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
			
			//Change the state
			valULCSAfterChange=changeStatusReadyForReview();
			//Click 'Save and Check In'
			CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
			CommonFunctions.handleAlertPopUp();
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");
			log.info("Rejected status bom is: "+strbomRFR);
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateReadyforReview_bom()", e);
		}
		return strbomRFR;
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
			return "";
		}
		return bomname;
	}
	

	
	public static String addSource(String[] data) throws Exception{
		try{
			//Add Source
			Select dropDownSource = new Select(SeleniumDriver.driver.findElement(selectSource));
			//Get the Source count
			List<WebElement> elementCountSource = dropDownSource.getOptions();
			int countSource =elementCountSource.size();
			if(countSource>2)
			{
				//Select Source
				CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
				//Get the name of the source selected
				strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			else
			{
				//Create source
				addSourcingConfiguration(data);
				//Select first source
				CommonFunctions.selectFromDropDownByIndex(selectSource, 1);
				//Get the name of the source selected
				strSource=new Select(driver.findElement(selectSource)).getFirstSelectedOption().getText();
			}
			log.info("Source is: "+strSource);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreatebom()", e);
			return "";
		}
		return strSource;
	}

	public static String addSpecification(String[] data) throws Exception{
		try{
			//Add Specification
			Select dropDownSpec = new Select(SeleniumDriver.driver.findElement(selectSpecification));
			List<WebElement> elementCount = dropDownSpec.getOptions();
			int count =elementCount.size();
			if(count>=2)
			{
				//Select first Specification
				CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
				strSpec=new Select(driver.findElement(selectSpecification)).getFirstSelectedOption().getText();
			}
			else
			{
				//Get the name of specification created
				strSpec= CommonProjectFunctions.Create_Specifications(data[11],data[12]);
				//Select specification first
				CommonFunctions.selectFromDropDownByIndex(selectSpecification, 1);
				//Get the name of the specification created
				strSpec=new Select(driver.findElement(selectSpecification)).getFirstSelectedOption().getText();
			}
			log.info("Specification is: "+strSpec);
		}catch(Exception e){
			fail=true;
			log.error("Exception in fillCreatebom()", e);
			return "";
		}
		return strSpec;
	}

	public static String addColorway(String[] data) throws Exception{
		try{
			//Add colorway
			Select dropDownCW = new Select(SeleniumDriver.driver.findElement(colorwayName));			
			List<WebElement> elementCountCW = dropDownCW.getOptions();
			int countCW =elementCountCW.size();
			if(countCW>=2)
			{
				//Select Colorway first
				CommonFunctions.selectFromDropDownByIndex(colorwayName, 1);
				//Get the name of the first Selected Colorway
				strCW=new Select(driver.findElement(colorwayName)).getFirstSelectedOption().getText();
			}
			else
			{
				//Create Colorway
				strCW= Create_Colorway(data);
			}
			log.info("Colorway is: "+strCW);
		}catch(Exception e){
			fail=true;
			log.error("Exception in AddColorway()", e);
			return "";
		}
		return strCW;
	}

	public static boolean readView_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){
				
				if(data[19].equalsIgnoreCase("No")){

					//Select Released bom					
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					log.info("Released bom is Visible");
									
				}
				else{
				
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				log.info("In Work bom is Visible");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				
				//BOM Details tab is not present
				Assert.assertEquals(driver.findElements(bomDetails).size(), 0, "bom detail tab is not present"); 
				System.out.println("bom is not visible");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in readView_bom()", e);
			return false;
		}
		return true;
	}

	public static boolean update_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[4].equalsIgnoreCase("Yes")){//Update
				
				//Select In Work BOM from drop down
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Wait for Main Frame
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				//Click on Header Attribute button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");			
				
				//Identify the currency attribute value
				Select selectcurrency= new Select(driver.findElement(currencyvalue));
				String currencyname = selectcurrency.getFirstSelectedOption().getText();
				
				//Select Currency value
				if (currencyname.equals(data[11])){
				//Select currency from drop down
				CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[20]);					
				}
				else{
					//Select currency from drop down
					CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[7]);					
				}
				
				//Capture the currency attribute value
				String currencyvalueineditmode= selectcurrency.getFirstSelectedOption().getText();
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Capture the currency attribute value in detail page
				String currencyvaluedetailpage= driver.findElement(detailpagecurrencyvalue).getText();
				
				//Verify that currency in detail page and updated in Edit page are same
				Assert.assertEquals(currencyvalueineditmode, currencyvaluedetailpage);
				log.info("bom gets updated properly");

			}
			else if(data[4].equalsIgnoreCase("No")){
				
				if(data[19].equalsIgnoreCase("No")){
					
					//Select In Released bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					//Click on Update button
					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					
					//Verify that Update button is disable
					Assert.assertEquals(strUpdate, "true");
					log.info("Is Update button is disabled for the user:" + strUpdate);
					
				}
				
				else {
					
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				//Check that Update button is disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				
				//Verify that Update button is disable
				Assert.assertEquals(strUpdate, "true");
				System.out.println("Is Update button disable: " + strUpdate);

				}

			}	

		}catch(Exception e){
			fail=true;
			log.error("Exception in update_bom()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_bom(String[] data) throws Exception{
		try{
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				
				//Click on Add New Button
				CommonFunctions.clickButtonOrLink(addNewbomTab, "btn", "Add New bom tab");
				//Enter Bom Type
				CommonFunctions.enterTextInTextbox(bomtypeid, data[2]);

				//Click Initialize bom
				CommonFunctions.clickButtonOrLink(initializebom,"btn", "Initialize bom");
				
				//Create BOM
				fillCreatebom(data);
				//Switch to mainframe
				driver.switchTo().frame("mainFrame");
				//Component or Location
				action = new Actions(driver);
				action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
				CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);
				
				//Click on Save and Check in
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				
				//Switch to Default frame
				driver.switchTo().defaultContent();
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Wait for Header Attribute
				CommonFunctions.waitForVisibilityOfElement(headerAttributes);
				//Get the name of first selected BOM
				strbomInWorkDelete=  new Select(driver.findElement(bomId)).getFirstSelectedOption().getText();
				//Trim the name of the BOM
				strbomInWorkDelete=strbomInWorkDelete.trim();
				System.out.println("The name of the bom after trim is:"+ strbomInWorkDelete);
				
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[6]);
			//Click on '+' sign of component
			CommonFunctions.clickButtonOrLink(componentPlus, "btn", "Component plus sign");
			//Click on Action
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+strbomInWorkDelete+"')]/preceding::td[2]/a"), "dropdown", "Action");
			CommonFunctions.waitForVisibilityOfElement(removeLink);
			//Click on Remove
			CommonFunctions.clickButtonOrLink(removeLink, "link", "Remove");
			CommonFunctions.handleAlertPopUp();
			
			//Click on Material Tab
			CommonProjectFunctions.clickMaterialsTab();
			CommonFunctions.handleAlertPopUp();
			
			//Select Source
			CommonFunctions.selectFromDropDownByIndex(selectSource, 0);
			CommonFunctions.handleAlertPopUp();
			//Select In Work BOM
			CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWorkDelete);
				//Click Check in
				if(driver.findElements(checkedOutByYou).size()>0){
					//Click on Action button
					CommonFunctions.clickButtonOrLink(bomAction, "btn", "Action dropdown");
					//Check In
					driver.findElement(By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table/tbody//a[contains(text(),'Check In')]")).click();
				}
				//Get the Bill of Material text
				strBillOfMaterials = driver.findElement(billOfMaterials).getText();
				//Click on Actions drop down
				CommonFunctions.clickButtonOrLink(bomAction, "btn", "Action dropdown");
				
				//Click on Delete Button
				driver.findElement(By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table/tbody//a[contains(text(),'Delete')]")).click();
				//Wait for Delete page header
				CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
				
				//Click ok/yes on confirmation of Deletion of BOM
				driver.switchTo().alert().accept(); 
				CommonFunctions.handleAlertPopUp();
				//Check that Delete BOM exist or not from BOM List drop down
				Select select = new Select(driver.findElement(bomId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strBillOfMaterials,options);
				log.info("bom value after delete is: "+bVal);
				//Verify that delete BOM does not exist in BOM List drop down
				Assert.assertFalse(bVal);
				log.info("bom has been deleted.Deleted bom is: "+strBillOfMaterials);
			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				
				//Unselect Source
				CommonFunctions.selectFromDropDownByIndex(selectSource, 0);
				
				if(data[21].equalsIgnoreCase("Yes")){
					
					//Check that In Work BOM which is removed from specification exist or not
					Select select = new Select(driver.findElement(bomId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strbomRelDelete,options);
					
					//Verify that In Work BOM which is removed from specification is not visible
					Assert.assertFalse(bVal);									
					log.info("Is In Work bom which is removed from the specification is visible: " + bVal);

				}
				
				else {
				//Select BOM which is removed from specification
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomRelDelete);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Action Drop down
				CommonFunctions.clickButtonOrLink(bomAction, "btn", "Action dropdown");				
				System.out.println("Action menu clicked");
				
				//Check that Delete button is disable or not
				String bDelete= driver.findElement(By.xpath("//div[@id='overDiv']/table/tbody/tr/td/table[2]/tbody/tr/td/font/table/tbody//a[contains(text(),'Delete')]")).getAttribute("disabled");
				
				//Verify that Delete button is disable				
				Assert.assertEquals("true", bDelete);
				System.out.println("Does Delete button disable: " + bDelete);

				}
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in delete_bom()", e);
			return false;
		}
		return true;
	}

	public static boolean inWorkreadView_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){
				
				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				log.info("User is able to see In Work bom");				
			}
			else if(data[4].equalsIgnoreCase("No")){
				
				//Check that In Work BOM is present or not
				Select select = new Select(driver.findElement(bomId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strbomInWork,options);
				
				//Verify that In Work BOM is not visible
				Assert.assertFalse(bVal);
				log.info("Is In Work bom is visible: " + bVal);
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in InWorkreadView_bom()", e);
			return false;
		}
		return true;
	}

	public static boolean inWorkUpdate_bom(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[4].equalsIgnoreCase("Yes")){//Update
				
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				
				//Switch to mainframe
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				
				//Click on Header Attribute button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
								
				//Identify the currency attribute value
				Select selectcurrency= new Select(driver.findElement(currencyvalue));
				String currencyname = selectcurrency.getFirstSelectedOption().getText();
				
				//Select the currency
				if (currencyname.equals(data[11])){
				CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[20]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[7]);					
				}
				
				//Capture the currency attribute value
				String currencyvalueineditmode= selectcurrency.getFirstSelectedOption().getText();
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Capture the currency attribute value in detail page
				String currencyvaluedetailpage= driver.findElement(detailpagecurrencyvalue).getText();
				
				//Verify that Currency in detail page and updated in Edit page are same
				Assert.assertEquals(currencyvalueineditmode, currencyvaluedetailpage);
				log.info("currency in detail page and edit page are same: "+ currencyvaluedetailpage);
				
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(data[19].equalsIgnoreCase("No")){
				
				//Check that In Work BOM is visible or not
				Select select = new Select(driver.findElement(bomId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strbomInWork,options);
				
				//Verify that In Work BOM is visible or not
				Assert.assertFalse(bVal);
				log.info("Is In Work bom is visible: " + bVal);
				}
				else {
				
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				
				//Check that Update button is disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				
				System.out.println("Is Update button disable or not: " + strUpdate);
				
				//Verify that Update button is disable
				Assert.assertEquals(strUpdate, "true");
				log.info("Does user dont have update action disabled(true) or enabled(null): " + strUpdate);

			}	
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in InWorkUpdate_bom()", e);
			return false;
		}
		return true;
	}

	public static boolean underReviewReadView(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[4].equalsIgnoreCase("Yes")){
				
				//Select Under review bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomUR);	
				CommonFunctions.handleAlertPopUp();
				log.info("User is able to view the Under Review bom: " + strbomUR);
				}
				
			else if(data[4].equalsIgnoreCase("No")){
				
				//Check that Under Review BOM is present in the BOM List drop down or not
				Select select = new Select(driver.findElement(bomId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strbomUR,options);
				//Verify that Under Review BOM is not visible in BOM Drop down list
				Assert.assertFalse(bVal);
				log.info("Does User is able to view the Under Review bom: " + bVal);

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
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){//Update
				
				//Select Under review bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomUR);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				//Click on Header Attribute button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				
				//Identify the currency attribute value
				Select selectcurrency= new Select(driver.findElement(currencyvalue));
				String currencyname = selectcurrency.getFirstSelectedOption().getText();
				
				//Select the value of currency from drop down
				if (currencyname.equals(data[11])){
				CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[20]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[7]);					
				}
				
				//Capture the currency attribute value
				String currencyvalueineditmode= selectcurrency.getFirstSelectedOption().getText();
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Capture the currency attribute value in detail page
				String currencyvaluedetailpage= driver.findElement(detailpagecurrencyvalue).getText();
				
				//Verify that Currency value in detail page and Updated in Edit page are same
				Assert.assertEquals(currencyvalueineditmode, currencyvaluedetailpage);
				log.info("Currency value in detail page and Updated in Edit page are same" +currencyvalueineditmode);
				
				log.info("bom get Updated properly");
				
			}
			else if(data[4].equalsIgnoreCase("No")){
				if(data[19].equalsIgnoreCase("No")){
					
					//Check that Under Review BOM exist in the BOM List drop down or not
					Select select = new Select(driver.findElement(bomId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strbomUR,options);
					
					//Verify that Under Review BOM does not exit in BOM drop down list
					Assert.assertFalse(bVal);
					log.info("Does User is able to view the Under Review bom: " + bVal);

					
				}
				else {
					
					//Select Under Review bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomUR);
					CommonFunctions.handleAlertPopUp();		
					//Check that Update button is diable or not
					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					//Verify that Update button is disable for Under Review BOM
					Assert.assertEquals(strUpdate, "true");
					log.info("Does user have update action disabled: " + strUpdate);

				}
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
			
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[4].equalsIgnoreCase("Yes")){
				
				//Select Released bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
				CommonFunctions.handleAlertPopUp();				
				log.info("User is able to view Released bom : " + strbomR);

			}
			else if(data[4].equalsIgnoreCase("No")){
				
				//Check that Released BOM available in BOM drop down list or not
				Select select = new Select(driver.findElement(bomId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strbomR,options);
				//Verify that Released BOM is not visible 
				Assert.assertFalse(bVal);
				log.info("Is User is able to view Released bom:" + bVal);

			}
		}
			catch(Exception e){
			fail=true;
			log.error("Exception in releasedReadView()", e);
			return false;
		}
		return true;
	}

	public static boolean releasedUpdate(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[4].equalsIgnoreCase("Yes")){//Update
				
				//Select Released bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				
				//Click on Header Attribute Button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				
				//Identify the currency attribute value
				Select selectcurrency= new Select(driver.findElement(currencyvalue));
				String currencyname = selectcurrency.getFirstSelectedOption().getText();
				
				//Select the currency
				if (currencyname.equals(data[11])){
				CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[20]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[7]);					
				}
				
				//Capture the currency attribute value
				String currencyvalueineditmode= selectcurrency.getFirstSelectedOption().getText();
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Capture the currency attribute value in detail page
				String currencyvaluedetailpage= driver.findElement(detailpagecurrencyvalue).getText();
				//Verify the value of currency in detail page and edited in Update page are same 
				Assert.assertEquals(currencyvalueineditmode, currencyvaluedetailpage);
				log.info("Value of currency in detail page and edited in Update page are same" + currencyvaluedetailpage);
			}
			else if(data[4].equalsIgnoreCase("No")){
				
				//Select Released bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
				CommonFunctions.handleAlertPopUp();	
				//Check that Update button is disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				//Verify that Update button is disable
				Assert.assertEquals(strUpdate, "true");
				log.info("Does user have update action disabled: " + strUpdate);

			}	

		}catch(Exception e){
			fail=true;
			log.error("Exception in releasedUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean cancelledReadView(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[4].equalsIgnoreCase("Yes")){
				
				//Select Cancelled bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomC);
				CommonFunctions.handleAlertPopUp();
				
				log.info("User is able to see Cancelled bom: " + strbomC);
				
			}
			else if(data[4].equalsIgnoreCase("No")){
				//Check that Cancelled BOM is present in BOM List drop down or not
				Select select = new Select(driver.findElement(bomId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strbomC,options);
				//Verify that Cancelled BOM Is not present in BOM List drop down
				Assert.assertFalse(bVal);
				log.info("Is User is able to see Cancelled bom: " + bVal);

			}
		}
			catch(Exception e){
			fail=true;
			log.error("Exception in cancelledReadView()", e);
			return false;
		}
		return true;
	}

	public static boolean cancelledUpdate(String[] data) throws Exception{
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

			if(data[4].equalsIgnoreCase("Yes")){//Update
				
				//Select Released BOM
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				
				//Click on Header Attribute button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				
				//Identify the currency attribute value
				Select selectcurrency= new Select(driver.findElement(currencyvalue));
				String currencyname = selectcurrency.getFirstSelectedOption().getText();
				
				//Select currency
				if (currencyname.equals(data[11])){
				CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[20]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[7]);					
				}
				
				//Capture the currency attribute value
				String currencyvalueineditmode= selectcurrency.getFirstSelectedOption().getText();
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Capture the currency attribute value in detail page
				String currencyvaluedetailpage= driver.findElement(detailpagecurrencyvalue).getText();
				
				//Verify that currency value in detail page and updated in edit page are same
				Assert.assertEquals(currencyvalueineditmode, currencyvaluedetailpage);
				log.info("Check that currency value in detail page and updated in edit page are same:" + currencyvaluedetailpage);
				
			}
			else if(data[4].equalsIgnoreCase("No")){
				
				if(data[19].equalsIgnoreCase("No")){
					//Check that Cancelled BOM is present in BOM List or not
					Select select = new Select(driver.findElement(bomId));
					List<WebElement> options = select.getOptions();
					boolean bVal=CommonFunctions.dropDownOptionVerification(strbomC,options);
					
					//Verify that cancelled BOM is visible or not
					Assert.assertFalse(bVal);
					log.info("Does User is able to view the Cancelled bom: " + bVal);

					
				}
				else {
				
				//Select Cancelled bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomC);
				CommonFunctions.handleAlertPopUp();	
				//Check that update button is disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				
				//Verify that Update button is disable
				Assert.assertEquals(strUpdate, "true");
				log.info("Does User is able to see update button disabled(true) or enabled(null) for the Cancelled bom: " + strUpdate);

			}	
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
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			//Switch to Default frame
			driver.switchTo().defaultContent();
			
			//Switch to contentFrame
			driver.switchTo().frame("contentframe");

			if(data[4].equalsIgnoreCase("Yes")){
				
				//Select Rejected bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomRj);
				CommonFunctions.handleAlertPopUp();		
				
				log.info("User is able to view Rejected bom: "+ strbomRj);
				
			}
			else if(data[4].equalsIgnoreCase("No")){
				//Check Rejected BOM present BOM List drop down or not
				Select select = new Select(driver.findElement(bomId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strbomRj,options);
				
				//Verify that User is not able to view Rejected BOM
				log.info("Is User is able to see the Rejected bom: " + bVal);
				Assert.assertFalse(bVal);
			}
		}
			catch(Exception e){
			fail=true;
			log.error("Exception in rejectedReadView()", e);
			return false;
		}
		return true;
	}

	public static boolean rejectedUpdate(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){//Update
				
				//Select Rejected bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomRj);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				
				//Click on Header Attribute button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				
				//Identify the currency attribute value
				Select selectcurrency= new Select(driver.findElement(currencyvalue));
				
				//Get the name of the first selected Currency
				String currencyname = selectcurrency.getFirstSelectedOption().getText();
				
				//Change the currency value
				if (currencyname.equals(data[11])){
				CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[20]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[7]);					
				}
				
				//Capture the currency attribute value
				String currencyvalueineditmode= selectcurrency.getFirstSelectedOption().getText();
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				//Handle pop up menu
				CommonFunctions.handleAlertPopUp();
				//Expand Header attribute
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Capture the currency attribute value in detail page
				String currencyvaluedetailpage= driver.findElement(detailpagecurrencyvalue).getText();
				
				//Verify that currency in edit page and detail page are same
				Assert.assertEquals(currencyvalueineditmode, currencyvaluedetailpage);
				log.info("currency name in edit page and detail page are same as: " + currencyvalueineditmode);
			}
			else if(data[4].equalsIgnoreCase("No")){
				
			if(data[19].equalsIgnoreCase("No")){
			
			//Select Rejected bom from drop down
			Select select = new Select(driver.findElement(bomId));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(strbomRj,options);
			
			//Verify that User is not able to see Rejected bom
			log.info("Does User is able to view the Rejected bom: " + bVal);
			Assert.assertFalse(bVal);
			
		}
			else {
				//Select Rejected bom
				Select selectbom=new Select(driver.findElement(bomId));
				selectbom.selectByVisibleText(strbomRj);
				CommonFunctions.handleAlertPopUp();	
				
				//Check that Update button disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				//Verify that Update button is diable for Rejected BOM
				Assert.assertEquals(strUpdate, "true");
				log.info("Is Update button is disabled for the Rejected bom: " + strUpdate);

			}	
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
			
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[4].equalsIgnoreCase("Yes")){
				
				//Select Ready for Review bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomRFR);
				CommonFunctions.handleAlertPopUp();		
				
				log.info("User is able to see the Ready for Review bom: " + strbomRFR);
			}
			else if(data[4].equalsIgnoreCase("No")){
				
				//Select Ready for Review bom
				Select select = new Select(driver.findElement(bomId));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(strbomRFR,options);				

				//Verify that User is not able to see Ready for Review bom
				Assert.assertFalse(bVal);
				log.info("User is able to see the Ready for Review bom: " + bVal);

			}
		}
			catch(Exception e){
			fail=true;
			log.error("Exception in readyForReviewReadView()", e);
			return false;
		}
		return true;
	}

	public static boolean readyForReviewUpdate(String[] data) throws Exception{
		try{
			
			//Browse to Material tab
			navigateToMaterialTab(data);

			if(data[4].equalsIgnoreCase("Yes")){//Update
				
				//Select Ready for Review bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomRFR);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				//Switch to mainframe
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				
				//Click on Header Attribute Button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				
				//Identify the currency attribute value
				Select selectcurrency= new Select(driver.findElement(currencyvalue));
				String currencyname = selectcurrency.getFirstSelectedOption().getText();
				
				//Select currency value
				if (currencyname.equals(data[11])){
				CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[20]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[7]);					
				}
				
				//Capture the currency attribute value
				String currencyvalueineditmode= selectcurrency.getFirstSelectedOption().getText();
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Capture the currency attribute value in detail page
				String currencyvaluedetailpage= driver.findElement(detailpagecurrencyvalue).getText();
				
				//Verify that currency value in detail page and updated in edit mode are same
				Assert.assertEquals(currencyvalueineditmode, currencyvaluedetailpage);
				log.info("currency value in detail page and updated in edit mode are same:" + currencyvaluedetailpage);
				
			}
			else if(data[4].equalsIgnoreCase("No")){
				
			if(data[19].equalsIgnoreCase("No")){
			//Check that Ready for Review BOM exist in drop down or not
			Select select = new Select(driver.findElement(bomId));
			List<WebElement> options = select.getOptions();
			boolean bVal=CommonFunctions.dropDownOptionVerification(strbomRFR,options);
			
			//Verify that User is not able to view Ready for Review BOM
			Assert.assertFalse(bVal);
			log.info("Does User is able to view the Ready For Review bom: " + bVal);

			}
			
			else {
				
				//Select Ready for Review bom
				Select selectbom=new Select(driver.findElement(bomId));
				selectbom.selectByVisibleText(strbomRFR);
				CommonFunctions.handleAlertPopUp();	
				
				//Check that Update button is disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				
				//Verify that Update button is disable for user
				Assert.assertEquals(strUpdate, "true");
				log.info("Is update button is disabled(true) or enabled(null) for the user: " + strUpdate);

			}	
			}
			
		}catch(Exception e){
			fail=true;
			log.error("Exception in readyForReviewUpdate()", e);
			return false;
		}
		return true;
	}


	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyGeneralAttributesReadView(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[3].contains("GeneralAttributesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				
				if (data[19].equalsIgnoreCase("No")){
				
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();	
					
					//Click on Expand Button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						//Check that General attribute table name in application
						String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
						//Verify that General attribute table name is correct
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");
					}else if(driver.findElements(Product.labelGeneralAttri).size() == 0){
						
						log.error("General Attributes label is Absent");
						fail=true;
					}				
				}
				
				else {
					
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
					CommonFunctions.handleAlertPopUp();
					
					//Click on Expand Collapse button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						//Check that General attribute table name in application
						String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
						//Verify that General attribute table name is correct
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");
					}else if(driver.findElements(Product.labelGeneralAttri).size() == 0){						
						log.error("General Attributes label is Absent");
						fail=true;
					}
				}
			}
			else if(data[3].contains("GeneralAttributesRead_View")&& data[4].equalsIgnoreCase("No")){
				
				if (data[19].equalsIgnoreCase("No")){
					
					//Select Released BOM
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();	
					
					//Click on Expand Button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						//Check that General attribute table name in application
						String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
						//Verify that General attribute table name is correct
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");
						fail=true;
					}else if(driver.findElements(Product.labelGeneralAttri).size() == 0){
						
						log.error("General Attributes label is Absent");
					}				
				}			
				else {
					
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
					CommonFunctions.handleAlertPopUp();	
					
					//Click on Expand Button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					if(driver.findElements(Product.labelGeneralAttri).size() != 0){
						//Check that General attribute table name in application
						String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
						//Verify that General attribute table name is correct
						Assert.assertEquals(GALabel, " General Attributes:");
						log.info("General Attributes label is Present");
						fail=true;
					}else if(driver.findElements(Product.labelGeneralAttri).size() == 0){
						
						log.error("General Attributes label is Absent");
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
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){//Update
			
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Update button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				
				//Switch to mainframe
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				
				//Click on Header attribute button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");

				//Identify the currency attribute value
				Select selectcurrency= new Select(driver.findElement(currencyvalue));
				String currencyname = selectcurrency.getFirstSelectedOption().getText();
				
				//Select Currency
				if (currencyname.equals(data[11])){
				CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[20]);					
				}
				else{
					CommonFunctions.selectFromDropDownByVisibleText(currencyvalue, data[7]);					
				}
				
				//Capture the currency attribute value
				String currencyvalueineditmode= selectcurrency.getFirstSelectedOption().getText();
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");
				
				//Expand Header attribute
				CommonFunctions.handleAlertPopUp();
				
				//Click on Expand Collapse button
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Capture the currency attribute value in detail page
				String currencyvaluedetailpage= driver.findElement(detailpagecurrencyvalue).getText();
				
				//Check that currency value in detail page and updated in edit page are same
				Assert.assertEquals(currencyvalueineditmode, currencyvaluedetailpage);
				log.info("currency value in detail page and updated in edit page are same:" + currencyvaluedetailpage);
				
			}
			else if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("No")){
				
				if (data[19].equalsIgnoreCase("No")){
					
					//Select In Released bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();		
					
					//Check that Update button is diable or not 
					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					
					//Verify that Update button is disable
					Assert.assertEquals(strUpdate, "true");
					log.info("Is Update button disable:" + strUpdate);
					
					}
				else {
					
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
					CommonFunctions.handleAlertPopUp();		
					
					//Check that Update button is disable or not
					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					//Verify that update button is disable
					Assert.assertEquals(strUpdate, "true");
					log.info("Is Update button disable:" + strUpdate);
					
				}
				}			

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyGeneralAttributesUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean verifybomMatchCostingSummaryReadView(String[] data) throws Exception{
		try{
			
			//Browse to Material tab
			navigateToMaterialTab(data);
			
			if(data[3].contains("bomMatchCostingSummaryReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				
				if(data[19].equalsIgnoreCase("No")){
					
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					
					//Click on Expand Button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
										
					if(driver.findElements(bommmatchcostsummary).size() != 0){
						//Check the name of BOM Match cost summary table name
						String BCSLabel=driver.findElement(bommmatchcostsummary).getText();						
						System.out.println("Table name is: " + BCSLabel);	
						//Verify that BOM Match Cost Summary name is visible
						Assert.assertEquals(BCSLabel.trim(), "Vendor bom Match Cost Summary:");
						log.info("bom Match CostingSummary table is visible");
					}else if(driver.findElements(bommmatchcostsummary).size() == 0){
						log.error("bom Match CostingSummary table is  not visible");
						fail=true;
					}					
				}				
				else {
							
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();		
				
				//Click on Expand Button
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				if(driver.findElements(bommmatchcostsummary).size() != 0){
					//Check the name of BOM Match cost summary table name
					String BCSLabel=driver.findElement(bommmatchcostsummary).getText();						
					System.out.println("Table name is: " + BCSLabel);	
					//Verify that BOM Match Cost Summary name is visible
					Assert.assertEquals(BCSLabel.trim(), "Vendor bom Match Cost Summary:");
					log.info("bom Match CostingSummary table is visible");
				}else if(driver.findElements(bommmatchcostsummary).size() == 0){
					log.error("bom Match CostingSummary table is  not visible");
					fail=true;
				}	
			}
			}
			else if(data[3].contains("bomMatchCostingSummaryReadView")&& data[4].equalsIgnoreCase("No")){
				
				if(data[19].equalsIgnoreCase("No")){
					
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					//Click on Expand Button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					//Verify that BOM Match Cost Summary table is present or not
					if(driver.findElements(bommmatchcostsummary).size() != 0){
						System.out.println("bom Match CostingSummary label is Present");
						fail=true;
					}else if (driver.findElements(bommmatchcostsummary).size() == 0){
						log.info("bom Match CostingSummary label is Absent");
					}
					
					
				}
				
				else {
					
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
					CommonFunctions.handleAlertPopUp();		
					
					//Click on Expand Button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
				//Verify that BOM Match Cost Summary table is present or not
				if(driver.findElements(bommmatchcostsummary).size() != 0){
					System.out.println("bom Match CostingSummary label is Present");
					fail=true;
				}else if (driver.findElements(bommmatchcostsummary).size() == 0){
					log.info("bom Match CostingSummary label is Absent");
				}
			}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifybomMatchCostingSummaryReadView()", e);
			return false;
		}
		return true;
	}

	public static boolean verifybomCostingSummaryReadView(String[] data) throws Exception{
		try{
			//Browse to Material Tab
			navigateToMaterialTab(data);

			if(data[3].contains("bomCostingSummaryReadView")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				
				if(data[19].equals("No")){
				
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					
					//Click on Expand Button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
									
					if(driver.findElements(bomCostSummary).size() != 0){
						//Check the name of BOM Cost Summary table
						String BCSLabel=driver.findElement(bomCostSummary).getText();
						//Verify that BOM Match Cost Summary table name in the application is correct
						Assert.assertEquals(BCSLabel.trim(), "Vendor BOM Cost Summary:");
						System.out.println("Name of the bomCostingSummary table: " + BCSLabel);
						log.info("bomCostingSummaryReadView label is Present");
					}else if(driver.findElements(bomCostSummary).size() == 0) {
						log.error("bomCostingSummaryReadView label is not present");
						fail=true;
					}
					
				}
				
				else {

				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Expand Button
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
							
				if(driver.findElements(bomCostSummary).size() != 0){
					//Check the name of BOM Cost Summary table
					String BCSLabel=driver.findElement(bomCostSummary).getText();
					//Verify that BOM Match Cost Summary table name in the application is correct
					Assert.assertEquals(BCSLabel.trim(), "Vendor BOM Cost Summary:");
					System.out.println("Name of the bomCostingSummary table: " + BCSLabel);
					log.info("bomCostingSummaryReadView label is Present");
				}else if(driver.findElements(bomCostSummary).size() == 0) {
					log.error("bomCostingSummaryReadView label is not present");
					fail=true;
				}
			}
			}
			else if(data[3].contains("bomCostingSummaryReadView")&& data[4].equalsIgnoreCase("No")){
				
				if(data[19].equals("No")){
					
					//Select Released BOM
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					
					//Click on Expand Button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					//Check the BOM Cost Summary table is present or not
					if(driver.findElements(bomCostSummary).size() != 0){
						System.out.println("bomCostingSummaryReadView label is Present");
						fail=true;
					}else if(driver.findElements(bomCostSummary).size() == 0){
						log.info("bomCostingSummaryReadView label is Absent");
					}
				}
				
				else {
				
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Expand Button
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");				
				
				//Check the BOM Cost Summary table is present or not
				if(driver.findElements(bomCostSummary).size() != 0){
					System.out.println("bomCostingSummaryReadView label is Present");
					fail=true;
				}else if(driver.findElements(bomCostSummary).size() == 0){
					log.info("bomCostingSummaryReadView label is Absent");
				}
			}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifybomCostingSummaryReadView()", e);
			return false;
		}
		return true;
	}

	public static boolean verifybomMatchCostingSummaryUpdate(String[] data) throws Exception{
		try{
			
			//Browse to Material tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){//Update
			
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Update Button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				CommonFunctions.handleAlertPopUp();
				
				//Wait for Main Frame
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				
				//Click on Header attribute button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				
				//Verify that all the below attribute are not editable
				Assert.assertEquals(driver.findElements(totalbomMatchCost).size(), 1, "TotalbomMatchCost is  not editable"); 
				Assert.assertEquals(driver.findElements(totalChemicalsMatctCost).size(), 1, "TotalChemicalsMatctCost is  not editable");
				Assert.assertEquals(driver.findElements(totalElectronicsMatchCost).size(), 1, "TotalElectronicsMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(totalGeneralDecoLaborMatchCost).size(), 1, "TotalGeneralDecoLaborMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(totalMarkUpMatchCost).size(), 1, "TotalMarkUpMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(totalMoldingLaborMatchCost).size(), 1, "TotalMoldingLaborMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(totalPackagingMatchCost).size(), 1, "TotalPackagingMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(totalPlasticMatchCost).size(), 1, "TotalPlasticMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(totalPurchasedPartsMatchCost).size(), 1, "TotalPurchasedPartsMatchCost is  not editable");
				Assert.assertEquals(driver.findElements(totalSoftGoodsMatchCost).size(), 1, "TotalPlasticMatchCost is  not editable");
				
				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");				
				
				log.info("User is able to see bomMatchCostingSummary");
			}
			else if(data[4].equalsIgnoreCase("No")){
				
				if(data[19].equalsIgnoreCase("No")){
				
					//Select Released BOM
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					
					//Check that Update button is disable or not
					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					//Verify that Update button is disable or not
					Assert.assertEquals(strUpdate, "true");			
					log.info("Does user has update button disable: " + strUpdate);

				}
				
				else {
					
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				//Check that Update button disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				//Verify that Update button is disable or not
				Assert.assertEquals(strUpdate, null);
				log.info("Does user has update button disable: " + strUpdate);

				}
			}	
			
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifybomMatchCostingSummaryUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean verifybomCostingSummaryUpdate(String[] data) throws Exception{
		try{
			
			//Browse to Material tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){//Update
								
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Update Button
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				CommonFunctions.handleAlertPopUp();
			
				//Wait for main frame
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				
				//Click on Header Attribute button
				CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
				
				if(data[2].contains("BOM\\Materials\\Product\\Product\\Vendor")){
					//Verify that following attribute are enabled or disable
					Assert.assertEquals(driver.findElements(bcTotalbomCost).size(), 1, "TotalbomCost is editable");
					Assert.assertEquals(driver.findElements(totalMasterCartonPackagingCost).size(), 1, "total MasterCarton Packaging Cost is editable");
					Assert.assertEquals(driver.findElements(totalLaborCost).size(), 1, "total Labor Cost is editable"); 
					Assert.assertEquals(driver.findElements(totalMiscellaneousCost).size(), 1, "TotalbomCost is editable"); 
					Assert.assertEquals(driver.findElements(totalMarkUpCost).size(), 1, "total MarkUp Cost   is editable"); 				
					
					//Click button btnSaveAndCheckIn
					CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
					CommonFunctions.handleAlertPopUp();
					
					//Switch to contentFrame
					driver.switchTo().frame("contentframe");								
					log.info("User is able to see bomCostingSummary table");
					
				}
				
				else if (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")){
				
				//Verify that following attribute are enabled or disable
				Assert.assertEquals(driver.findElements(bcTotalbomCost).size(), 1, "TotalbomCost is editable"); 
				Assert.assertEquals(driver.findElements(bcTotalChemicalsCost).size(), 1, "TotalChemicalsCost is editable");
				Assert.assertEquals(driver.findElements(bcTotalElectronicsCost).size(), 1, "TotalElectronicsCost is editable");
				Assert.assertEquals(driver.findElements(bcTotalGenDecoLaborCost).size(), 1, "TotalGenDecoLaborCost is editable");
				Assert.assertEquals(driver.findElements(bcTotalMarkUpCost).size(),1, "TotalMarkUpCost is editable");
				Assert.assertEquals(driver.findElements(bcTotalMoldingLaborCost).size(), 1, "TotalMoldingLaborCost is editable");
				Assert.assertEquals(driver.findElements(bcTotalPackagingCost).size(), 1, "TotalPackagingCost is editable");
				Assert.assertEquals(driver.findElements(bcTotalPlasticCost).size(), 1, "TotalPlasticCost is  not editable");
				Assert.assertEquals(driver.findElements(bcTotalPlasticUsageK).size(), 1, "TotalPlasticUsageK is editable");
				Assert.assertEquals(driver.findElements(bcTotalSoftGoodsCost).size(), 1, "TotalSoftGoodsCost is editable");
				Assert.assertEquals(driver.findElements(bcTotalPurchasedPartsCost).size(), 1, "TotalPurchasedPartsCost is editable");

				//Click button btnSaveAndCheckIn
				CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				CommonFunctions.handleAlertPopUp();
				
				//Switch to contentFrame
				driver.switchTo().frame("contentframe");								
				log.info("User is able to see bomCostingSummary table");
				}
			}
			else if(data[4].equalsIgnoreCase("No")){
				
				
				if(data[19].equalsIgnoreCase("No")){
				
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					
					//Check that Update button is disable or not
					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					//Verify that Update button is disable or not
					
					Assert.assertEquals(strUpdate, "true");
					log.info("Does user has update button disabled: " + strUpdate);

									
				}
				
				else {					
					
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();			
				
				//Check that Update button is disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				
				System.out.println("Is user able to see Update button disable:" + strUpdate);
				
				if(driver.findElement(updateBtn).getAttribute("disabled")==null){
					
					//Click on Update Button
					CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
					CommonFunctions.handleAlertPopUp();
				
					//Wait for main frame
					WebDriverWait wait1 = new WebDriverWait(driver,10);
					wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
					
					//Click on Header Attribute button
					CommonFunctions.clickButtonOrLink(headerAttributesBtn, "btn", "headerAttributesBtn");
					
					if(data[2].contains("BOM\\Materials\\Product\\Product\\Vendor")){
						//Verify that following attribute are enabled or disable
						Assert.assertEquals(driver.findElements(bcTotalbomCost).size(), 1, "TotalbomCost is editable");
						Assert.assertEquals(driver.findElements(totalMasterCartonPackagingCost).size(), 1, "total MasterCarton Packaging Cost is editable");
						Assert.assertEquals(driver.findElements(totalLaborCost).size(), 1, "total Labor Cost is editable"); 
						Assert.assertEquals(driver.findElements(totalMiscellaneousCost).size(), 1, "TotalbomCost is editable"); 
						Assert.assertEquals(driver.findElements(totalMarkUpCost).size(), 1, "total MarkUp Cost   is editable"); 				
						
						//Click button btnSaveAndCheckIn
						CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
						CommonFunctions.handleAlertPopUp();
						
						//Switch to contentFrame
						driver.switchTo().frame("contentframe");								
						log.info("User is able to see bomCostingSummary table");
						
					}
					
					else if (data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")){
					
					//Verify that following attribute are enabled or disable
					Assert.assertEquals(driver.findElements(bcTotalbomCost).size(), 1, "TotalbomCost is editable"); 
					Assert.assertEquals(driver.findElements(bcTotalChemicalsCost).size(), 1, "TotalChemicalsCost is editable");
					Assert.assertEquals(driver.findElements(bcTotalElectronicsCost).size(), 1, "TotalElectronicsCost is editable");
					Assert.assertEquals(driver.findElements(bcTotalGenDecoLaborCost).size(), 1, "TotalGenDecoLaborCost is editable");
					Assert.assertEquals(driver.findElements(bcTotalMarkUpCost).size(),1, "TotalMarkUpCost is editable");
					Assert.assertEquals(driver.findElements(bcTotalMoldingLaborCost).size(), 1, "TotalMoldingLaborCost is editable");
					Assert.assertEquals(driver.findElements(bcTotalPackagingCost).size(), 1, "TotalPackagingCost is editable");
					Assert.assertEquals(driver.findElements(bcTotalPlasticCost).size(), 1, "TotalPlasticCost is  not editable");
					Assert.assertEquals(driver.findElements(bcTotalPlasticUsageK).size(), 1, "TotalPlasticUsageK is editable");
					Assert.assertEquals(driver.findElements(bcTotalSoftGoodsCost).size(), 1, "TotalSoftGoodsCost is editable");
					Assert.assertEquals(driver.findElements(bcTotalPurchasedPartsCost).size(), 1, "TotalPurchasedPartsCost is editable");

					//Click button btnSaveAndCheckIn
					CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
					CommonFunctions.handleAlertPopUp();
					
					//Switch to contentFrame
					driver.switchTo().frame("contentframe");								
					log.info("User is able to see bomCostingSummary table but not able to update it");				
					
				}				
				
				}
				
				else {
					
				//Verify that Update button is not disable
				log.info("Does user has update button disabled: " + strUpdate);
				Assert.assertEquals(strUpdate, "true");				
				}
			}	
			}
			
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifybomCostingSummaryUpdate()", e);
			return false;
		}
		return true;
	}
	
	//Function consist scenario : bomCommentsRead:Read_View
	public static boolean verifybomCommentsRead(String[] data) throws Exception{
		try{
			
			//Browse to Material tab
			navigateToMaterialTab(data);

			if(data[3].contains("bomCommentsRead")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				
				if(data[19].equalsIgnoreCase("No")){
				
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					
					//Click on Expand Collapse button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					if(driver.findElements(bomComments).size() != 0){
						//Check the name of BOM Comments table
						String bomCommLabel=driver.findElement(bomComments).getText();
						//Verify that BOM Comment table name in application is correct
						Assert.assertEquals(bomCommLabel.trim(), "Comments:");
						log.info("bomComments Read label is Present");
					}else if (driver.findElements(bomComments).size() == 0){
						log.error("bomCommentsRead label is Absent");
						fail=true;
					}														
				}				
				else {
					
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				//Click on Header Attribute plus button
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				if(driver.findElements(bomComments).size() != 0){
					//Check the name of BOM Comments table
					String bomCommLabel=driver.findElement(bomComments).getText();
					//Verify that BOM Comment table name in application is correct
					Assert.assertEquals(bomCommLabel.trim(), "Comments:");
					log.info("bomComments Read label is Present");
				}else if (driver.findElements(bomComments).size() == 0){
					log.error("bomCommentsRead label is Absent");
					fail=true;
				}
			}
			}
			else if(data[3].contains("bomCommentsRead")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(bomComments).size() != 0){
					//Verify that BOM Comment table present or not
					System.out.println("bomCommentsRead label is Present");
					log.error("bomCommentsRead label is Present");
					fail=true;
				}
				//Verify that BOM Comment table present or not
				else if(driver.findElements(bomComments).size() == 0) {
					log.error("bomCommentsRead label is not Present");
					
				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in verifybomCommentsRead()", e);
			return false;
		}
		return true;
	}
	//Function consist scenario : bomComments://Update
	public static boolean verifybomCommentsUpdate(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){//Update

				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				
				//Click on Header attribute plus button
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
				
				//Click on Enter Comment
				CommonFunctions.clickButtonOrLink(enterComments, "link", "Enter Comment");
				
				//Enter text in Post New button
				CommonFunctions.enterTextInTextbox(postNewComment, data[15]);
				CommonFunctions.clickButtonOrLink(post, "btn", "Post Button");
				//Sleep
				Thread.sleep(3000);
				
				//Find comment
				String enteredComment = driver.findElement(By.xpath("//table[contains(@id,'TBLT')]/tbody/tr//td[text()='"+data[15]+"']")).getText();
				
				//Verify that comment entered and comment displaying detail page are same
				Assert.assertEquals(enteredComment, data[15]);
				System.out.println("The value of entered comment" + enteredComment);


			}
			else if(data[4].equalsIgnoreCase("No")){
				
				if(data[19].equalsIgnoreCase("No")){
					
					//Select Released bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					//Click on Header Attribute plus button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					//Check that Enter comment table present or not
					Assert.assertEquals(driver.findElements(enterComments).size(),0);

					System.out.println("Enter comments option is not present in Comment table");

				}
				
				else {
					
					//Select In Work bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
					CommonFunctions.handleAlertPopUp();
					
					//Click on Header Attribute expand collapse button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");
					
					//Check that Enter Comment table present or not
					boolean entercommentpresent =CommonFunctions.isElementPresent(enterComments);
					
					//Verify that Enter comment table is not present
					Assert.assertFalse(entercommentpresent);		
					System.out.println("Is Enter comments option is present in Comment table" + entercommentpresent);

				}                
			}	

		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifybomCommentsUpdate()", e);
			return false;
		}
		return true;
	}

	public static boolean verifybomSectionsRead(String[] data) throws Exception{
		try{			
			//Browse to Material tab
			navigateToMaterialTab(data);

			if(data[3].contains("bomSectionsRead")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				
				if(data[19].equalsIgnoreCase("No")){
					
					//Select Released bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					//Click on Header Attribute Plus button
					CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");							
					
					//Check that BOM section table Description Heading present or not
					if(driver.findElements(descHeading).size() != 0){					
						String descHeadLabel=driver.findElement(descHeading).getText();
						Assert.assertEquals(descHeadLabel.trim(), "Description");
						log.info("bomSectionsRead label is Present");
					}
					//Check that BOM section table Description Heading present or not
					else if (driver.findElements(descHeading).size() == 0) {
						log.error("bomSectionsRead label is Absent");
						fail=true;
					}
					
				}
				
				else {
				
				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				//Click on Header Attribute plus button
				CommonFunctions.clickButtonOrLink(headerAttributesPlus, "btn","Expand Collapse button");							
				
				//Check that BOM section table Description Heading present or not
				if(driver.findElements(descHeading).size() != 0){					
					String descHeadLabel=driver.findElement(descHeading).getText();
					Assert.assertEquals(descHeadLabel.trim(), "Description");
					log.info("bomSectionsRead label is Present");
				}
				//Check that BOM section table Description Heading present or not
				else if (driver.findElements(descHeading).size() == 0) {
					log.error("bomSectionsRead label is Absent");
					fail=true;
				}
			}
			}
			else if(data[3].contains("bomSectionsRead")&& data[4].equalsIgnoreCase("No")){
				
				if(data[19].equalsIgnoreCase("No")){
					//Select Released bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					
					//Check that BOM Section table is present or not
					if(driver.findElements(descHeading).size() != 0){					
						System.out.println("bomSections label is Present");
						log.error("bomSectionsRead label is Present");
						fail=true;
					} else if (driver.findElements(descHeading).size() == 0){
						log.info("bomSectionsRead label is Absent");
					}
					
				}
				
				else {
					
				//Select Released bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				
				//Check that BOM Section table is present or not
				if(driver.findElements(descHeading).size() != 0){					
					System.out.println("bomSections label is Present");
					log.error("bomSectionsRead label is Present");
					fail=true;
				} else if (driver.findElements(descHeading).size() == 0){
					log.info("bomSectionsRead label is Absent");
				}
			}
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifybomSectionsRead()", e);
			return false;
		}
		return true;
	}

	public static boolean verifybomSectionsUpdate(String[] data) throws Exception{
		try{
			
			//Browse to Material Tab
			navigateToMaterialTab(data);
			
			if(data[4].equalsIgnoreCase("Yes")){//Update

				//Select In Work bom
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				//	if(data[4].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.clickButtonOrLink(updateBtn, "btn", "Update btn");
				
				//Switch to mainframe			
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				if(data[2].contains("BOM\\Materials\\Product\\Retail Item\\Vendor")){

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
					
					//Select BOM
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
					CommonFunctions.handleAlertPopUp();

					//Click on 'Click to select a view'
					CommonFunctions.clickButtonOrLink(viewBtn, "img", "View");
					//Click on none
					CommonFunctions.clickButtonOrLink(noneBtn, "lnk", "--None--");
					//Click on Plastic Description
					CommonFunctions.waitForVisibilityOfElement(plasticDescription);
					String strplasticDescription=driver.findElement(plasticDescription).getText();
					Assert.assertEquals(strplasticDescription, data[9]);
					//Verify Plastic Material Description in edit mode and detail page are same
					String strplasticMeterialDesc=driver.findElement(plasticMeterialDesc).getText();
					Assert.assertEquals(strplasticMeterialDesc, data[16]);
					//Verify that Chemical Description in edit mode and detail page are same
					String strchemicalsDescription=driver.findElement(chemicalsDescription).getText();
					Assert.assertEquals(strchemicalsDescription, data[17]);
					
					//Verify that Chemical Material Description in edit mode and detail page are same
					String strchemicalsMeterialDesc=driver.findElement(chemicalsMeterialDesc).getText();
					Assert.assertEquals(strchemicalsMeterialDesc, data[18]);
				}
				
				else if(data[2].contains("BOM\\Materials\\Product\\Product\\Vendor")){

					//Click on Plastic Clear
					CommonFunctions.clickButtonOrLink(mastercartonpackagingClear,"link", "Clear for Master Carton Packaging");
					//Component or Location
					action = new Actions(driver);
					action.moveToElement(driver.findElement(compOrLoca)).doubleClick().perform();
					CommonFunctions.enterTextInTextbox(inputCompOrLoca, data[9]);

					//Click 'Save and Check In'
					CommonFunctions.clickButtonOrLink(btnSaveAndCheckIn, "btn", "Save and Check In");
					CommonFunctions.handleAlertPopUp();
					// Wait for content frame
					WebDriverWait wait1 = new WebDriverWait(driver,10);
					wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("contentframe"));

					//Select In Work BOM
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
					CommonFunctions.handleAlertPopUp();

					//Click on 'Click to select a view'
					CommonFunctions.clickButtonOrLink(viewBtn, "img", "View");
					//Click on none
					CommonFunctions.clickButtonOrLink(noneBtn, "lnk", "--None--");
					//Verify that Master carton Description entered and in detail page are same
					CommonFunctions.waitForVisibilityOfElement(mastercartonDescription);
					String strplasticDescription=driver.findElement(mastercartonDescription).getText();
					Assert.assertEquals(strplasticDescription, data[9]);
				
			}
			}
			else if(data[4].equalsIgnoreCase("No")){
				
				if(data[19].equalsIgnoreCase("No")){
				
					//Select Released bom
					CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomR);
					CommonFunctions.handleAlertPopUp();
					//Check that Update button disable or not
					strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
					
					//Verify that Update button is disable or not
					Assert.assertEquals(strUpdate, "true");
					log.info("Is Update button is disable: "+ strUpdate);
					
				}
				
				else {
				//Select In Work BOM
				CommonFunctions.selectFromDropDownByVisibleText(bomId, strbomInWork);
				CommonFunctions.handleAlertPopUp();
				//Check that Update button is disable or not
				strUpdate = driver.findElement(updateBtn).getAttribute("disabled");
				
				//Verify that Update button is enabled
				Assert.assertEquals(strUpdate, "true");
				log.info("Is Is Update button is disable: "+ strUpdate);
				
				}
			}	
			
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifybomSectionsUpdate()", e);
			return false;
		}
		return true;
	}


	public static Boolean addSourcingConfiguration(String[] data) throws Exception{
		try{
			//Switch to default content
			SeleniumDriver.driver.switchTo().defaultContent();
			//Switch to content frame
			SeleniumDriver.driver.switchTo().frame("contentframe");	
			CommonFunctions.selectFromDropDownByVisibleText(ExternalBOM.actionDD, "Add Sourcing Configuration");

			//Supplier Selection
			CommonFunctions.clickButtonOrLink(SourcingConfig.supplier, "link", "supplier");
			//Get the number of Windows
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			//Click Supplier
			CommonFunctions.clickButtonOrLink(SourcingConfig.search, "Search For Supplier");
			//Click on Selected supplier
			CommonFunctions.clickButtonOrLink(SourcingConfig.choose, "Supplier selected");
			driver.switchTo().window(parent);
			
			//Switch to default frame
			driver.switchTo().defaultContent();
			//Switch to content frame
			driver.switchTo().frame("contentframe");

			//Sourcing Lead 
			CommonFunctions.selectFromDropDownByIndex(sourcingLead, 1);
			//*Sourcing Head 
			CommonFunctions.selectFromDropDownByIndex(sourcingHead, 1);
			//click on Create Source
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
			// Select Action Drop down
			CommonFunctions.selectFromDropDownByVisibleText(ExternalBOM.actionDD, "Create Colorway");
			//Click on Suffix
			CommonFunctions.clickButtonOrLink(Colorway.suffix, "Link", "Suffix Clicked");
			//Switch window
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			//Switch to child window
			SeleniumDriver.driver.switchTo().window(child);
			//Enter suffix
			SeleniumDriver.driver.findElement(Colorway.suffixSearch).sendKeys(data[14]);
			//Click on Search
			CommonFunctions.clickButtonOrLink(Colorway.search, "Link", "Search Clicked");
			//Click on choose
			CommonFunctions.clickButtonOrLink(Colorway.choose, "Link", "Suffix Selected");
			//Switch to parent Window
			SeleniumDriver.driver.switchTo().window(parent);
			//Switch to content frame
			SeleniumDriver.driver.switchTo().frame("contentframe");
			//Click on Save
			CommonFunctions.clickButtonOrLink(Colorway.save, "Link", "Colorway Created");
			Thread.sleep(1000);
			//Get the name of Colorway
			Colorway.colorWayName = SeleniumDriver.driver.findElement(Colorway.colorWay).getText();
			//Click on view Product
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			
		}catch(Exception e){
			log.error("Exception in CreateColorway()", e);
		}
		return Colorway.colorWayName;
	}

	public static String changebomStatus() throws Exception{
		try{
			//Get the name of BOM status selected
			valULCS = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			//Change the BOM status to Released
			if(valULCS.contains("In Work")){
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus,"Released");
			}
			//Change the BOM status to In Work
			else if(valULCS.contains("Under Review")){
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "In Work");
			}
			//Change the BOM status to In Work
			else if(valULCS.contains("Released")){
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "In Work");
			}
			//Change the BOM status to In Work
			else if(valULCS.contains("Canceled")){
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "In Work");
			}
			//Change the BOM status to In Work
			else if(valULCS.contains("Rejected")){
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "In Work");
			}
			else if(valULCS.contains("Ready For Review")){
				//Change the BOM status to In Work
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "In Work");
			}
			//Get the name of BOM selected 
			valULCSAfterChange = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changebomStatus()", e);
		}
		return valULCSAfterChange;
	}
	
	public static String changeStatusUnderReview() throws Exception{
		try{
			//Get the name of the BOM selected
			valULCS = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				//Select Under Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus,"Under Review");
			}

			else if(valULCS.contains("Released")){
				//Select Under Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Under Review");
			}
			else if(valULCS.contains("Canceled")){
				//Select Under Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Under Review");
			}
			else if(valULCS.contains("Rejected")){
				//Select Under Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Under Review");
			}
			else if(valULCS.contains("Ready For Review")){
				//Select Under Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Under Review");
			}
			
			//Get the name of the BOM status selected
			valULCSAfterChange = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changebomStatus()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatusReleased() throws Exception{
		try{
			//Get the name fo BOM status selected 
			valULCS = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				//Select Released BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus,"Released");
			}
			else if(valULCS.contains("Under Review")){
				//Select In Work BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "In Work");
			}

			else if(valULCS.contains("Canceled")){
				//Select Released BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Released");
			}
			else if(valULCS.contains("Rejected")){
				//Select Released BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Released");
			}
			else if(valULCS.contains("Ready For Review")){
				//Select Under Released BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Released");
			}
			//Get the name of BOM first selected
			valULCSAfterChange = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeStatusReleased()", e);
		}
		return valULCSAfterChange;
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
			return false;
		}
		
	}

	public static String changeStatusCancelled() throws Exception{
		try{
			//Get the name of BOM selected
			valULCS = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				//Select Canceled BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus,"Canceled");
			}
			else if(valULCS.contains("Under Review")){
				//Select Canceled BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Canceled");
			}
			else if(valULCS.contains("Released")){
				//Select Canceled BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Canceled");
			}

			else if(valULCS.contains("Rejected")){
				//Select Canceled BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Canceled");
			}
			else if(valULCS.contains("Ready For Review")){
				//Select Canceled BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Canceled");
			}
			//Get the name of BOM selected
			valULCSAfterChange = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeStatusCancelled()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatusRejected() throws Exception{
		try{
			//Get the name of BOM selected
			valULCS = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				//Select Rejected BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus,"Rejected");
			}
			else if(valULCS.contains("Under Review")){
				//Select Rejected BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Rejected");
			}
			else if(valULCS.contains("Released")){
				//Select Rejected BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Rejected");
			}
			else if(valULCS.contains("Canceled")){
				//Select Rejected BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Rejected");
			}

			else if(valULCS.contains("Ready For Review")){
				//Select Rejected BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Rejected");
			}
			//Get the name of the BOM selected
			valULCSAfterChange = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeStatusRejected()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatusReadyForReview() throws Exception{
		try{
			//Get the name of the BOM selected
			valULCS = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				//Select Ready for Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus,"Ready For Review");
			}
			else if(valULCS.contains("Under Review")){
				//Select Ready for Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Ready For Review");
			}
			else if(valULCS.contains("Released")){
				//Select Ready for Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Ready For Review");
			}
			else if(valULCS.contains("Canceled")){
				//Select Ready for Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Ready For Review");
			}
			else if(valULCS.contains("Rejected")){
				//Select Ready for Review BOM status
				CommonFunctions.selectFromDropDownByVisibleText(bomStatus, "Ready For Review");
			}
			//Get the name of BOM Status selected
			valULCSAfterChange = new Select(driver.findElement(bomStatus)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){
			fail=true;
			log.error("Exception in changeStatusReadyForReview()", e);
		}
		return valULCSAfterChange;
	}

	public static String changeStatus(String[] data) throws Exception{
		try{
			//Get the name of Lifecycle selected
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				//Select Complete Lifecycle State
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState,"Complete");
			}
			else if(valULCS.contains("Complete")){
				//Select In Work Lifecycle State
				CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
			}
			//Get the name of Lifecycle selected
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
