package com.hasbro.sanity;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import base.SeleniumDriver;

import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Product;

import util.CommonFunctions;
import util.CommonProjectFunctions;

public class WorkFlowPreRequisite extends TestsuiteBase{

	
	String runmodes[]=null;
	static int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	String loginVal;
	Boolean flaglogin=false;
	int y=0;
	
	
	public static String AssortmentProductNumberForWorkFlow;
	public static String productName;
	public static String CopyLinkRetailItem1_WorkFlow;
	public static String CopyLinkRetailItem2_WorkFlow;
	public static String WorkFlowTask;
	public static String getTextOfTheTask;
	public static String WorkFlowReference;
	public static String myWorkFlowTaskCommentBox;
	public static String myWorkFlowCheckBox;
	public static String T1DefinitionOptionPath;
	public static String T10DefinitionOptionPath;
	public static String T1DefinitionOptionPath3;
	public static String T1DefinitionOptionPath4;
	public static String T1DefinitionOptionPath5;
	public static String T1DefinitionOptionPath6;
	public static String SecondaryReviewer;
	public static String TaskCheckBox;
	public static String TaskComment;
	public static String TaskTextArea;
	public static String LeadReviewer;
	public static String TaskByReferingText;
	public static By ResponsibleEngineerDropDown               = By.xpath("//td[contains(text(),'Responsible Engineer')]//following::select[1]");
	public static By ResponsibleCostEngineerDropDown           = By.xpath("//td[contains(text(),'Responsible Engineer')]//following::select[2]");
	public static By ResponsibleCostEngineerDropDown_Update    = By.xpath("//td[contains(text(),'Responsible Engineer')]//following::select[3]");
	public static By SourcesCheckBox                           = By.xpath("//div[@id='sourcesOptionsdiv_plus']/input");
	public static By UpdateButton                              = By.xpath("//a[text()='Update']");
	public static By HeaderAttributesButton                    = By.xpath("//a[text()='Header Attributes']");
	public static By BOMStatusDropDown                         = By.xpath("//td[contains(text(),'*BOM Status')]//following::select[1]");
	public static By BOMDropDown                               = By.xpath("//td[contains(text(),'BOM')]//following::select[1]");
	public static By CostSheetInAdminUser                      = By.xpath("//a[text()='Sourcing Configuration']//following::a[10]");
	public static By CostSheetStatusDropDown                   = By.xpath("//td[contains(text(),'*Cost Sheet Status')]//following::select[1]");
	public static By BOMDropDown_Assortment                    = By.xpath("//td[contains(text(),'BOM')]//following::select[1]");
	public static By QuoteStatusValue                          = By.xpath("//td[contains(text(),'Quote Status')]//following::td[1]");
	public static By SpecificationDropDown                     = By.xpath("//td[contains(text(),'Specification')]//following::select[1]");
	public static By MyWorkPlusButton                          = By.xpath("//img[@id='myWorkContentIcon']");
	public static By LCSProductCostSheet                       = By.xpath("//div[@id='workListDiv']/div[2]/a");
	public static By LeadReviewerReviewCostSheet               = By.xpath("//a[contains(text(),'Lead Reviewer: Review Cost Sheet')]");
	public static By SecondaryReviewerReviewCostSheet          = By.xpath("//a[contains(text(),'Secondary Reviewer: Review Cost Sheet')]");
	public static By CompleteButton                            = By.xpath("//a[text()='Complete']");
	public static By VendorReferenceNumber                     = By.xpath("//td[contains(text(),'Vendor Reference Number')]");
	public static By PopUpAfterSubmission                      = By.xpath("//div[@id='divContent']/center");
	public static By MyWorktab                                 = By.xpath("//li[@id='siteNavLink']/a");
	public static By ProcessHistoryTaskName                    = By.xpath("//td[contains(text(),'Process History')]//following::td[12]");
	public static By ProcessHistotyAction                      = By.xpath("//td[contains(text(),'Process History')]//following::td[15]");
	public static By ReviewCommentRowForSecondaryReviewer      = By.xpath("//td[contains(text(),'Reviewer Comments')]//following::div[2]/table/tbody/tr[3]");
	public static By CommentsSecondaryReviewer                 = By.xpath("//td[contains(text(),'Reviewer Comments')]//following::div[2]/table/tbody/tr[3]/td[5]");
	
	
	public static By MyWorkFlowTask;
	public static By MyWorkFlowTaskCommentBox;
	public static By MyWorkFlowCheckBox;
	public static By T1DefinitionOption;
	public static By T10DefinitionOption;
	public static By T1DefinitionOption3;
	public static By T1DefinitionOption4;
	public static By T1DefinitionOption5;
	public static By T1DefinitionOption6;
	public static By SecondaryReviewerRadioButton;
	public static By TaskCheckBoxWebElement;
	public static By TaskCommentWebElement;
	public static By TaskTextAreaWebElement;
	public static By LeadReviewerWebElement;
	public static By TaskByReferingText_WebElement;
	
	
	
	
	
	
	
	
	 public static boolean workFlow_PreRequisite_ForTwoRetailItems(String [] data) throws Exception{
			try{
				//SendEmailToPeople.sendMail();
				CreateProdFromLineSheetForWorkFlow(data);
				//Creating a Product
				clickSourcingTab(data);
				//Navigating to a Sourcing Tab
			    AddVendorSource(data);
				//Adding a Vendor Source
				ApproveVendorSource(data);
				//Approving the Vendor Source
				SanitySuite1.navigateSpecificationThroughSideBar(data);
				//Navigating Specification Through Side Bar
				CreateSpecification(data);
				//Creating a Specification
				CreateColorway(data);
				//Create a Colorway
				CopyLinkProductRetailItem1(data);
				//Copy Link First Retail Item
				SearchAssortmentProduct(data);
				//Search Assortment/Solid Product
				CopyLinkProductRetailItem2(data);
				//Copy Link of Second Retail Item
				CommonProjectFunctions.logOut();
				//Logging out
				openBrowser();
				//Open  Browser
				launchApp(data[44],data[45]);
				//Launch  Application
				SearchAssortmentProduct(data);
				//Search Assortment/Solid Product
				CreateWhatIFCostSheetForAssortment_SolidthroughRFQ(data);
				//Create What If Cost Sheet Template
				SearchTheRFQ(data);
				//Searching the RFQ
				AddContentTableValues(data);
				//Adding the Contents Table values
			    SubmitRequestThroughCostSheet(data);
				//Submitting the Request to the Vendors
				SanitySuite1.NavigateToRFQThroughSideBar(data);
				//Clicking RFQ
			    SearchTheRFQ(data);
				//Searching the RFQ
				SubmitRequestStatusCheck(data);
				//Submitting the Request
				CommonProjectFunctions.logOut();
				//Logging out
				openBrowser();
				//opening the Browser
				launchApp(data[66],data[67]);
				//Launching the URL
				SearchRetailItemProduct1(data);
				//Searching the RetailItem 1
				CreateVendorBOM_RetailItem(data);
				//Creating a Retail item1 Vendor BOM
				CreateVendorCostSheet_RetailItem(data);
				//Creating a vendor Cost Sheet
				SearchRetailItemProduct2(data);
				//Searching the Retail Item 2
				CreateVendorBOM_RetailItem(data);
				//Creating a Retail item12 Vendor BOM
				CreateVendorCostSheet_RetailItem(data);
				//Creating a vendor Cost Sheet
				CommonProjectFunctions.logOut();
				//Logging out
				openBrowser();
				//opening the Browser
				launchApp(data[0],data[1]);
				//Launching the URL with Admin User
				SearchRetailItemProduct2(data);
				//Searching the RetailItem 1
				SanitySuite1.navigateToMaterialTabThroughSideBar(data);
				//Navigating to Material Side Bar
				SelectSourceAndSpec(data);
				//Selecting the SOurce and Specification
				ChangeBOMAndCostSheetStatusOfRetailItem2(data);
				//Change BOM and The Cost Sheet STatus
				CommonProjectFunctions.logOut();
				//Logging out
				openBrowser();
				//opening the Browser
				launchApp(data[66],data[67]);
				//Launching the URL with Admin User
				SearchAssortmentProduct(data);
				//Search Assortment/Solid Product
				SanitySuite1.navigateToMaterialTabThroughSideBar(data);
				//Navigating to Material Side Bar
				CreateBOM_Assortment_Solid(data);
				//Creating a BOM for Assortment/Solid Product
				SanitySuite1.NavigateToRFQThroughSideBar(data);
				//Clicking RFQ 
			    SearchTheRFQ(data);
				//Searching the RFQ
			    UpdateContentTable_VendorUser(data);
			    //Update Contents table in vendor User
			   //BlankUpdate(data);
			    //Blank Update
			    AddBOMInCostSheet(data);
			    //Adding a BOM In the Cost Sheet
				SanitySuite1.NavigateToRFQThroughSideBar(data);
				//Clicking RFQ 
				 SearchTheRFQ(data);
				//Searching the RFQ
				SubmitQuoteForReview(data);
				//Submitting the Quote For Review
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in workFlow_Pre_Requisite"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error In workFlow_PreRequisite_ForTwoRetailItems"+er);
				throw er;
			}
			return true;
		 }
	 public static boolean InitialQuoteForReview(String [] data) throws Exception{
		 try{
			 CommonFunctions.waitForElementTobeClickable(SanitySuite1.SubmitQuoteForReviewDropDown);
				Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Quote Status')]//preceding::select[1]")));
			    List<WebElement> allOptions=dropdDown.getOptions();
	            for(int i=0;i<allOptions.size();i++){
	 		    String RequiredValue=allOptions.get(i).getText();
	 		    // System.out.println(RequiredValue);
	 		     if(RequiredValue.contains(data[92]))
	 		     {
	 		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SubmitQuoteForReviewDropDown, RequiredValue, "Action DropDown Selection");
	 		  //  wait.until(ExpectedConditions.visibilityOfElementLocated(VendorReferenceNumber));
	 		    wait.until(ExpectedConditions.invisibilityOfElementLocated(PopUpAfterSubmission));
	 		    //Thread.sleep(2000);
	 		     CommonFunctions.waitForPageLoaded();
	 		     break;
	 		     }
	 		     }
	 		    wait.until(ExpectedConditions.visibilityOfElementLocated(QuoteStatusValue));
	             SanitySuite1.GettingText(QuoteStatusValue);
	             if(SanitySuite1.ActualValue.equals(data[93])){
	             CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[93], "Actual and Expected Quote Status Are not matched.Assertion failed.Please Verify");
	            //Assertion verification of Quote STatus
	            log.info("The Status of the Quote is "+SanitySuite1.ActualValue+" Work Flows pre-requisite is Done");
	             }
	            else{
	            	//Thread.sleep(2000);
	            	CommonFunctions.waitForPageLoaded();
	            	  SanitySuite1.GettingText(QuoteStatusValue);
	            	  CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[93], "Actual and Expected Quote Status Are not matched.Assertion failed.Please Verify");
	  	              //Assertion verification of Quote STatus
	  	            log.info("The Status of the Quote is "+SanitySuite1.ActualValue+" Work Flows pre-requisite is Done");
	            
	             
	            }
	            
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("Exception in InitialQuoteForReview"+e);
			 throw e;
		 }
		 catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in InitialQuoteForReview"+er);
				throw er;
			}
		 return true;
	 }
	 
	
	 public static boolean BlankUpdate(String [] data) throws Exception{
			try{
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostSheetUpdateButton);
				Select dropdDown = new Select(driver.findElement(By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select")));
			     List<WebElement> allOptions=dropdDown.getOptions();
	            for(int i=0;i<allOptions.size();i++){
	  		     String RequiredValue=allOptions.get(i).getText();
	  		    // System.out.println(RequiredValue);
	  		     if(RequiredValue.contains(data[60]))
	  		     {
	  		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CostSheetUpdateButton, RequiredValue, "Source DropDown Selection");
	  		     CommonFunctions.waitForPageLoaded();
	  		     break;
	  		     }
	  		     }
	            wait.until(ExpectedConditions.titleIs(data[83]));
	            CommonFunctions.waitForElementTobeClickable(Product.SaveBtn);
	            CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Button", "Save");
	            wait.until(ExpectedConditions.titleIs(data[21]));
	            CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in BlankUpdate"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in BlankUpdate"+er);
				throw er;
			}
			return true;
		}
	 public static boolean AddBOMInCostSheet(String [] data) throws Exception{
		 try{
		 
         wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
			 CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
			 CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostSheetUpdateButton);
			 CommonFunctions.waitForPageLoaded();
			  CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetUpdateButton, "DropDown", "SanitySuite1.CostSheetUpdateButton");
			  CommonFunctions.selectFromDropDownByIndex(SanitySuite1.CostSheetUpdateButton, 1);
				//CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CostSheetUpdateButton, data[60],"Cost Sheet Update Drop down");
				wait.until(ExpectedConditions.titleIs(data[83]));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SpecificationDropDown);
				CommonFunctions.selectFromDropDownByIndex(SpecificationDropDown, 1);
				//Selecting the Specification
				CommonFunctions.waitForPageLoaded();
				Thread.sleep(1000);
				CommonFunctions.waitForElementTobeClickable(BOMDropDown_Assortment);
				CommonFunctions.selectFromDropDownByIndex(BOMDropDown_Assortment, 1);
				//Selecting the First BOM
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.UpdateCostSheetSaveButton);
				CommonFunctions.clickButtonOrLink(SanitySuite1.UpdateCostSheetSaveButton, "UpdateCostSheetSaveButton", "UpdateCostSheetSaveButton");
				wait.until(ExpectedConditions.titleIs(data[21]));
				CommonFunctions.waitForPageLoaded();
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("Exception in AddBOMInCostSheet" +e);
			 throw e;
		 }
		 catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in AddBOMInCostSheet"+er);
				throw er;
			}
	 return true;
	 }
	 public static boolean SubmitQuoteForReview(String [] data) throws Exception{
		 try{
			 CommonFunctions.waitForElementTobeClickable(SanitySuite1.SubmitQuoteForReviewDropDown);
				Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Quote Status')]//preceding::select[1]")));
			    List<WebElement> allOptions=dropdDown.getOptions();
	            for(int i=0;i<allOptions.size();i++){
	 		    String RequiredValue=allOptions.get(i).getText();
	 		    System.out.println(RequiredValue);
	 		     if(RequiredValue.contains(data[90]))
	 		     {
	 		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SubmitQuoteForReviewDropDown, RequiredValue, "Action DropDown Selection");
	 		    wait.until(ExpectedConditions.visibilityOfElementLocated(VendorReferenceNumber));
                 CommonFunctions.waitForPageLoaded();
	 		     break;
	 		     }
	 		     }
	 		    wait.until(ExpectedConditions.visibilityOfElementLocated(QuoteStatusValue));
	            SanitySuite1.GettingText(QuoteStatusValue);
	            if(SanitySuite1.ActualValue.equals(data[91])){
	            CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[91], "Actual and Expected Quote Status Are not matched.Assertion failed.Please Verify");
	            //Assertion verification of Quote STatus
		 }
	            else{
	            	CommonFunctions.waitForPageLoaded();
	            	//Thread.sleep(2000);
	            	 SanitySuite1.GettingText(QuoteStatusValue);
	            	 CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[91], "Actual and Expected Quote Status Are not matched.Assertion failed.Please Verify");
	            }
	            
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("Exception in SubmitQuoteForReview"+e);
			 throw e;
		 }
		 catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in SubmitQuoteForReview"+er);
				throw er;
			}
		 return true;
	 }
	
	
	 public static String CreateProdFromLineSheetForWorkFlow(String [] data) throws Exception{
			try{
				CommonProjectFunctions.clickMySeasonLink();
				//Select Season Year
				CommonFunctions.selectFromDropDownByVisibleText(SanitySuite1.mySeasonYear, data[3]);
				//Click on Line Sheet link
				CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
			    SeleniumDriver.driver.switchTo().defaultContent();
				SeleniumDriver.driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.titleIs(data[24]));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
				CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetView, data[4]);
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
				CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetAction, data[5]);
				//Click on Assortment/Solid
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(By.xpath("//td[contains(text(),'Choose a Type')]"));
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[6]+"')]"), "link", "Product Type");
				CommonFunctions.waitForPageLoaded();
		        productName=CommonFunctions.getRandomString(4);
				CommonFunctions.waitForElementTobeClickable(Product.ProductName);
				SeleniumDriver.driver.findElement(Product.ProductName).clear();
				CommonFunctions.enterTextInTextbox(Product.ProductName,productName);
				if(!data[6].equalsIgnoreCase("Trademark Display")){
					//Select Class
					CommonFunctions.waitForElementTobeClickable(Product.Class);
					CommonFunctions.enterTextInTextbox(Product.Class, data[7]);
					CommonFunctions.waitForElementTobeClickable(Product.Division);
					CommonFunctions.enterTextInTextbox(Product.Division, data[8]);
					CommonFunctions.enterTextInTextbox(Product.Brand, data[9]);
					if(!data[6].equalsIgnoreCase("Trade Marketing Pallet")){
						CommonFunctions.waitForElementTobeClickable(Product.InternalClassification);
						CommonFunctions.enterTextInTextbox(Product.InternalClassification,data[10]);
						//	CommonFunctions.enterTextInTextbox(AstSolid, productData[14]);
						CommonFunctions.waitForElementTobeClickable(Product.AstSolid);
						CommonFunctions.selectFromDropDownByVisibleText(Product.AstSolid, data[11]);
						//	CommonFunctions.enterTextInTextbox(IPSensitive,productData[16]);
						CommonFunctions.selectFromDropDownByVisibleText(Product.IPSensitive,data[12]);
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.SegmentDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SegmentDropDown, data[9], "Segment Drop Down");
						//Selecting the Segment Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.SuperCategoryDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SuperCategoryDropDown, data[13], "Super category Drop Down");
						//Selecting the Super category Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.CategoryDropDown);
						CommonFunctions.clickButtonOrLink(SanitySuite1.CategoryDropDown, "DropDown", "CategoryDropDown");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CategoryDropDown, data[14], "Category Drop Down");
						//Selecting the  category Drop down value
			            CommonFunctions.waitForElementTobeClickable(SanitySuite1.LicensorDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.LicensorDropDown, data[15], "Licensor Drop Down");
						//Selecting the Licensor Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.PropertyDropDown);
						CommonFunctions.clickButtonOrLink(SanitySuite1.PropertyDropDown, "Drop-Down", "PropertyDropDown");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.PropertyDropDown, data[16], "Property Drop Down");
						//Selecting the Property Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.FamilyBrandDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.FamilyBrandDropDown, data[9], "Family Drop Down");
						//Selecting the Family Brand Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.CoBrandDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CoBrandDropDown, data[16], "Family Drop Down");
						//Selecting the Co-Brand Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.LowerAgeDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.LowerAgeDropDown, data[17], "Lower Age Drop Down");
						//Selecting the Lower age Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.UpperAgeDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.UpperAgeDropDown, data[17], "Upper Age Drop Down");
						//Selecting the Upper age Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.GenderDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.GenderDropDown, data[19], " Gender  Drop Down");
						//Selecting the gender Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.ThirdPartyIndicatorDropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ThirdPartyIndicatorDropDown, data[18], "Third party Indicator  Drop Down");
						//Selecting the Third Party Indicator Drop down value
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.ISODropDown);
						CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ISODropDown, data[20], "ISO  Drop Down");
						//Selecting the Third Party Indicator Drop down value
						
						
					}

					/*if(data[6].equalsIgnoreCase("Retail")|| data[6].equalsIgnoreCase("Bundle Pack")){
						//Electronics Included
						CommonFunctions.enterTextInTextbox(Product.electronicsIncluded,strElectronicsIncluded);
						//Softgoods Included
						CommonFunctions.enterTextInTextbox(Product.softgoodsIncluded,strSoftgoodsIncluded);
					}*/
				}
				//Click on Save Button
				CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");
				wait.until(ExpectedConditions.titleIs(data[21]));
				CommonFunctions.waitForPageLoaded();
				
			if(!data[6].equalsIgnoreCase("Trademark Display")){
				CommonFunctions.waitForElementTobeClickable(Product.distributionChannel);
					CommonFunctions.enterTextInTextbox(Product.distributionChannel,data[22]);
					CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.lebelProdNum);
					AssortmentProductNumberForWorkFlow=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
					log.info("Product Number is "+AssortmentProductNumberForWorkFlow);
					if(data[6].equalsIgnoreCase("Assortment/Solid")){
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.TotalCost);
						CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.TotalCost, data[23], "Total Cost");
					}
					/*if(data[6].equalsIgnoreCase("Retail")|| data[6].equalsIgnoreCase("Bundle Pack")){
						CommonFunctions.enterTextInTextbox(Product.targetCostUSD, strDOMPriceUSD);
					}*/
				}
				//Click View Product Button
				CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
				wait.until(ExpectedConditions.titleIs(data[21]));
				CommonFunctions.waitForPageLoaded();
				log.info("View Season Product Information apge successfully appears");
				//	log.info(prodNum);
			}
			catch(Exception e){ 
				fail=true;
				log.error("Exception in CreateProductFromLineSheet()", e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Error in CreateProductFromLineSheet"+er);
				throw er;
			}
			return AssortmentProductNumberForWorkFlow;
		}
	
	 public static boolean clickSourcingTab(String [] data) throws Exception{
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingHyperLink));
				CommonFunctions.clickButtonOrLink(SanitySuite1.SourcingHyperLink, "SourcingHyperLink","Sourcing");
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
				CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in Clicking Sourcing Tab");
				throw e;
					}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in clickSourcingTab"+er);
				throw er;
			}
			return true;
		}
	
	 public static boolean AddVendorSource(String [] data) throws Exception{
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ActionsDropDown));
				Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
				List<WebElement> allOptions1=dropdDown.getOptions();
	            for(int i=0;i<allOptions1.size();i++){
	 		     String RequiredValue=allOptions1.get(i).getText();
	 		     System.out.println(RequiredValue);
	 		     if(RequiredValue.contains(data[25]))
	 		     {
	 		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ActionsDropDown, RequiredValue, "Add Sourcing Configuration");
	 		     break;
	 		     }
	           }
	            wait.until(ExpectedConditions.titleIs(data[26]));
	            CommonFunctions.waitForPageLoaded();
	            CommonFunctions.waitForElementTobeClickable(SanitySuite1.SourcingLeadDropDown);
	            CommonFunctions.selectFromDropDownByIndex(SanitySuite1.SourcingLeadDropDown, 1);
	            //Selecting the Sourcing Lead
	            CommonFunctions.waitForElementTobeClickable(SanitySuite1.SourcingHeadDropDown);
	            CommonFunctions.selectFromDropDownByIndex(SanitySuite1.SourcingHeadDropDown, 1);
	            //Selecting Sourcing Head
	            CommonFunctions.gettingParentWindow();
	            By Supplier =By.linkText("Supplier:");
	            CommonFunctions.waitForElementTobeClickable(Supplier);
	            CommonFunctions.clickButtonOrLink(Supplier, "Hyperlink", "Supplier");
	            //Clicking on Supplier Link
	            CommonFunctions.switchingChildWindow();
	            CommonFunctions.waitForElementTobeClickable(SanitySuite1.NameTextBox);
	            CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.NameTextBox, data[27], "Suplier Name");
	            //Entering the Vale In Name Text Box
	            CommonFunctions.waitForElementTobeClickable(SanitySuite1.SearchProductButton);
	            CommonFunctions.clickButtonOrLink(SanitySuite1.SearchProductButton, "Button", "Search Button");
	            //Clicking Search Button
	            wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ChooseColorWay));
	            CommonFunctions.clickButtonOrLink(SanitySuite1.ChooseColorWay, "HyperLink", "ChooseColorWay");
	            //Choosing the Source
	            CommonFunctions.switchParentWindow();
	            driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
			    //Switching Frame
			    CommonFunctions.waitForPageLoaded();
	            wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CreateButtonOfSourcing));
	            CommonFunctions.clickButtonOrLink(SanitySuite1.CreateButtonOfSourcing, "Button", "Create Button");
	            //Clicking Create Button
	            wait.until(ExpectedConditions.titleIs(data[21]));
	            CommonFunctions.waitForPageLoaded();
	            wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingHyperLink));
				CommonFunctions.clickButtonOrLink(SanitySuite1.SourcingHyperLink, "SourcingHyperLink","Sourcing");
				 CommonFunctions.waitForPageLoaded();
	            
	      }
			catch(Exception e){
				fail=true;
				log.error("Exception in AddVendorSource"+e);
				throw e;
					}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in AddVendorSource"+er);
				throw er;
			}
			return true;
		}
	 public static boolean ApproveVendorSource(String [] data) throws Exception{
			try{
				  wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
		         CommonFunctions.waitForPageLoaded();         
		             //CommonFunctions.waitForElementTobeClickable(SourcingDropDown);
		             Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Source')]//following::select[1]")));
				     List<WebElement> allOptions=dropdDown.getOptions();
		             for(int i=0;i<allOptions.size();i++){
		    		     String RequiredValue=allOptions.get(i).getText();
		    		    // System.out.println(RequiredValue);
		    		     if(RequiredValue.contains(data[28]))
		    		     {
		    		    	 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SourcingDropDown, RequiredValue, "Source DropDown Selection");
		    		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
		    		    	 CommonFunctions.waitForPageLoaded();
		    		    	 break;
		    		     }
		             }
		    		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.VendorActionDropDown);
		    		     Select dropdDown1 = new Select(driver.findElement(By.xpath("//select[@id='sourcingActions']")));
					     List<WebElement> allOptions1=dropdDown1.getOptions();
			             for(int j=0;j<allOptions1.size();j++){
			    		     String RequiredValue1=allOptions1.get(j).getText();
			    		     System.out.println(RequiredValue1);
			    		     if(RequiredValue1.contains(data[29]))
			    		     {
			    		    	 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.VendorActionDropDown, RequiredValue1, "VendorActionDropDown");
			    		    	 wait.until(ExpectedConditions.titleIs(data[30]));
			    		    	 CommonFunctions.waitForPageLoaded();
			    		    	 break;
			    		     }
			             }
			    		 CommonFunctions.waitForElementTobeClickable(SanitySuite1.ApproveDropDownSource);
			             CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ApproveDropDownSource, data[31], "SourceStatusDropDown");
			             //Selecting as Approved Status
			             CommonFunctions.waitForElementTobeClickable(SanitySuite1.SaveButtonOfCostSheet);
			             CommonFunctions.clickButtonOrLink(SanitySuite1.SaveButtonOfCostSheet, "SaveButtonOfCostSheet");
			             //Clicking on Save Button
			             wait.until(ExpectedConditions.titleIs(data[21]));
			             CommonFunctions.waitForPageLoaded();
			             
		             }
			catch(Exception e){
				fail=true;
				log.error("Exception in ApproveVendorSource");
				throw e;
					}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in ApproveVendorSource"+er);
				throw er;
			}
		return true;
		}
	 
	 public static String CreateSpecification(String [] data) throws Exception{
			try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CreateNewSpecification));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(SanitySuite1.CreateNewSpecification, "Button", "Create New Specification");
			wait.until(ExpectedConditions.titleIs(data[32]));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.WaveDropDownInSpecificaton);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.WaveDropDownInSpecificaton, data[33], "wave value");
			//Selecting wave value
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.SpecificationStatus);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SpecificationStatus, data[34], "Specification Status");
			//Selecting the Specification Status
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQCreateButton);
			CommonFunctions.clickButtonOrLink(SanitySuite1.RFQCreateButton, "Button", "Create");
			 //Clicking Create Button
			wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
			wait.until(ExpectedConditions.titleIs(data[21]));
			CommonFunctions.waitForPageLoaded();
		   CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
		  //CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.selectSpecification);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
		   CommonFunctions.waitForPageLoaded();
		  InternalBOMSoftG.strSpec=new Select(driver.findElement(InternalBOMSoftG.selectSpecification)).getFirstSelectedOption().getText();
		}
			catch(Exception e){
				fail=true;
				log.error("Exception in CreateSpecification"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in CreateSpecification"+er);
				throw er;
			}
			return InternalBOMSoftG.strSpec;
		}
	
	
	 public static String CreateColorway(String [] data) throws Exception{
			try{
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.ActionDropDownOfSpecicationTab);
			Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
		     List<WebElement> allOptions=dropdDown.getOptions();
		    
	      for(int i=0;i<allOptions.size();i++){
		     String RequiredValue=allOptions.get(i).getText();
		    // System.out.println(RequiredValue);
		     if(RequiredValue.contains(data[35]))
		     {
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ActionDropDownOfSpecicationTab, RequiredValue, "Source DropDown Selection");
		     wait.until(ExpectedConditions.titleIs(data[36]));
		     CommonFunctions.waitForPageLoaded();
		     break;
		     }
	      }
	      CommonFunctions.gettingParentWindow();
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.SuffixColorwayHyperLink);
			CommonFunctions.clickButtonOrLink(SanitySuite1.SuffixColorwayHyperLink, "HyperLink", "Suffix");
			//Clicking on Suffix Hyperlink
			CommonFunctions.switchingChildWindow();
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.SearchButton);
		    CommonFunctions.clickButtonOrLink(SanitySuite1.SearchButton, "Button", "Serach Button");
		    //Clicking Search Button
		    CommonFunctions.waitForPageLoaded();
		    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ChooseColorWay);
		    CommonFunctions.clickButtonOrLink(SanitySuite1.ChooseColorWay, "HyperLink", "ChooseButton");
		    //Clicking on Choose Button
		    CommonFunctions.switchParentWindow();
		    driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
		    //Switching Frame
		    CommonFunctions.waitForPageLoaded();	
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.SaveButtonOfCostSheet);
			CommonFunctions.clickButtonOrLink(SanitySuite1.SaveButtonOfCostSheet, "Button", "Save");
			//Clicking Save Button
			wait.until(ExpectedConditions.titleIs(data[21]));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.ViewProductButton);
			CommonFunctions.clickButtonOrLink(SanitySuite1.ViewProductButton, "Button", "View Product");
			//Clicking view Product Button
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ColorwayDropDown));
			SanitySuite1.ColorwayDropdownValue = new Select(driver.findElement(SanitySuite1.ColorwayDropDown)).getFirstSelectedOption().getText();
			log.info("The Seected ColorwayValue is "+SanitySuite1.ColorwayDropdownValue);
			
		}
			catch(Exception e){
				fail=true;
				log.error("Exception in CreateSpecification"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in CreateColorway"+er);
				throw er;
			}
			return SanitySuite1.ColorwayDropdownValue;
		}
	 public static String CopyLinkProductRetailItem1(String [] data) throws Exception{
			try{
				UnSelectSource(data);
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQPageActionDropDown);
				Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
			     List<WebElement> allOptions=dropdDown.getOptions();
		      for(int i=0;i<allOptions.size();i++){
			     String RequiredValue=allOptions.get(i).getText();
			    // System.out.println(RequiredValue);
			     if(RequiredValue.contains(data[37]))
			     {
			     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQPageActionDropDown, RequiredValue, "Source DropDown Selection");
			     wait.until(ExpectedConditions.titleIs(data[38]));
			    CommonFunctions.waitForPageLoaded();
			     break;
			     }
		      }
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductTypeInCopyLink);
		     Select dropdDown1 = new Select(driver.findElement(By.xpath("//select[@id='productTypedata']")));
		     List<WebElement> allOptions1=dropdDown1.getOptions();
	         for(int i=0;i<allOptions1.size();i++){
		     String RequiredValue=allOptions1.get(i).getText();
		     System.out.println(RequiredValue);
		     if(RequiredValue.contains(data[39]))
		     {
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ProductTypeInCopyLink, data[39], "product Type");
		     break;
		     }
	         }
		     //Selecting the Product Type
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.RelationShipType);
		     Select dropdDown2 = new Select(driver.findElement(By.xpath("//select[@id='copyModedata']")));
		     List<WebElement> allOptions2=dropdDown2.getOptions();
	         for(int i=0;i<allOptions2.size();i++){
		     String RequiredValue=allOptions2.get(i).getText();
		     System.out.println(RequiredValue);
		     if(RequiredValue.contains(data[40]))
		     {
		      CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RelationShipType, data[40], "RelationShipType");
			   //Selecting a RelationShip Type
		     break;
		     }
	         }
		     CommonFunctions.waitForElementTobeClickable(SourcesCheckBox);
		     CommonFunctions.clickButtonOrLink(SourcesCheckBox, "CheckBox", "SourcesCheckBox");
		     //clicking the SOurces Check Box
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorwayCheckBox);
		     CommonFunctions.clickButtonOrLink(SanitySuite1.ColorwayCheckBox, "Check Box", "ColorWay Check Box");
		     //Selecting the Colorway Check box
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
		     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
		     //Clicking Next Button
		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SoftGoodsIncluded));
		     CommonFunctions.waitForPageLoaded();
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SoftGoodsIncluded, data[41], "SoftGoodIncluded");
		     //Selecting the SoftGoods Value
		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ElectronicsIncluded));
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ElectronicsIncluded, data[42], "ElectronicsIncluded");
		     //Selecting the Electronics Included Value
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
		     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
		     //Clicking Next Button
		     wait.until(ExpectedConditions.titleIs(data[21]));
		     CommonFunctions.waitForPageLoaded();
		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ViewProductButton));
		     CommonFunctions.clickButtonOrLink(SanitySuite1.ViewProductButton, "Button", "view Product");
		    CommonFunctions.waitForPageLoaded();
		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.RetailItemIdentification));
		     CopyLinkRetailItem1_WorkFlow=driver.findElement(SanitySuite1.RetailItemIdentification).getText();
		     log.info("Created Copy/Link Product RetailItem Value is "+CopyLinkRetailItem1_WorkFlow);
	           
		}
			catch(Exception e){
				fail=true;
				log.error("Exception in CopyLinkProductRetailItem1"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in CopyLinkProductRetailItem1"+er);
				throw er;
			}
		 return CopyLinkRetailItem1_WorkFlow;
		}
	 
	 public static boolean UnSelectSource(String [] data) throws Exception{
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
	            //CommonFunctions.waitForElementTobeClickable(SourcingDropDown);
	            Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Source')]//following::select[1]")));
			     List<WebElement> allOptions=dropdDown.getOptions();
	            for(int i=0;i<allOptions.size();i++){
	   		     String RequiredValue=allOptions.get(i).getText();
	   		    System.out.println(RequiredValue);
	   		     if(RequiredValue.contains(data[43]))
	   		     {
	   		    	 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SourcingDropDown, RequiredValue, "Source DropDown Selection");
	   		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
	   		    	 CommonFunctions.waitForPageLoaded();
	   		    	 break;
	   		     }
	   		    
	            }
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in UnSelectSource"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in UnSelectSource"+er);
				throw er;
			}
			return true;
		}
	
	 
	 
	 public static boolean SearchAssortmentProduct(String [] data) throws Exception{
			try{
				  driver.switchTo().defaultContent();
			      driver.switchTo().frame("headerframe");
			      //Switching into Header Frame
			      CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchProduct);
				  SeleniumDriver.driver.findElement(CommonProjectFunctions.searchProduct).clear();
				  CommonFunctions.enterTextInTextbox(CommonProjectFunctions.searchProduct,AssortmentProductNumberForWorkFlow);
				  CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchIcon);
				  CommonFunctions.clickButtonOrLink(CommonProjectFunctions.searchIcon, "Btn", "SearchButton");
				  //Clicking Search Button
				  driver.switchTo().defaultContent();
			      driver.switchTo().frame("contentframe");
			      CommonFunctions.waitForPageLoaded();
				  SanitySuite1.NavigateToDetailsTab(data);
					//Navigating to details Tab
				  CommonFunctions.waitForElementTobeClickable(Product.detailPageSeasonDD);
		          CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[3],"Season year");
		          //CommonFunctions.handleAlertPopUp1();
		          CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in SearchAssortmentProduct"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in SearchAssortmentProduct"+er);
				throw er;
			}
			
			return true;
		}
	 
	 
	 public static String CopyLinkProductRetailItem2(String [] data) throws Exception{
			try{
				UnSelectSource(data);
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQPageActionDropDown);
				Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
			     List<WebElement> allOptions=dropdDown.getOptions();
		      for(int i=0;i<allOptions.size();i++){
			     String RequiredValue=allOptions.get(i).getText();
			    // System.out.println(RequiredValue);
			     if(RequiredValue.contains(data[37]))
			     {
			     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQPageActionDropDown, RequiredValue, "Source DropDown Selection");
			     wait.until(ExpectedConditions.titleIs(data[38]));
			    CommonFunctions.waitForPageLoaded();
			     break;
			     }
		      }
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductTypeInCopyLink);
		     Select dropdDown1 = new Select(driver.findElement(By.xpath("//select[@id='productTypedata']")));
		     List<WebElement> allOptions1=dropdDown1.getOptions();
	         for(int i=0;i<allOptions1.size();i++){
		     String RequiredValue=allOptions1.get(i).getText();
		     System.out.println(RequiredValue);
		     if(RequiredValue.contains(data[39]))
		     {
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ProductTypeInCopyLink, data[39], "product Type");
		     break;
		     }
	         }
		     //Selecting the Product Type
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.RelationShipType);
		     Select dropdDown2 = new Select(driver.findElement(By.xpath("//select[@id='copyModedata']")));
		     List<WebElement> allOptions2=dropdDown2.getOptions();
	         for(int i=0;i<allOptions2.size();i++){
		     String RequiredValue=allOptions2.get(i).getText();
		     System.out.println(RequiredValue);
		     if(RequiredValue.contains(data[40]))
		     {
		      CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RelationShipType, data[40], "RelationShipType");
			   //Selecting a RelationShip Type
		     break;
		     }
	         }
	         CommonFunctions.waitForElementTobeClickable(SourcesCheckBox);
		     CommonFunctions.clickButtonOrLink(SourcesCheckBox, "CheckBox", "SourcesCheckBox");
		     //clicking the SOurces Check Box
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorwayCheckBox);
		     CommonFunctions.clickButtonOrLink(SanitySuite1.ColorwayCheckBox, "Check Box", "ColorWay Check Box");
		     //Selecting the Colorway Check box
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
		     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
		     //Clicking Next Button
		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SoftGoodsIncluded));
		     CommonFunctions.waitForPageLoaded();
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SoftGoodsIncluded, data[41], "SoftGoodIncluded");
		     //Selecting the SoftGoods Value
		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ElectronicsIncluded));
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ElectronicsIncluded, data[42], "ElectronicsIncluded");
		     //Selecting the Electronics Included Value
		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
		     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
		     //Clicking Next Button
		     wait.until(ExpectedConditions.titleIs(data[21]));
		     CommonFunctions.waitForPageLoaded();
		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ViewProductButton));
		     CommonFunctions.clickButtonOrLink(SanitySuite1.ViewProductButton, "Button", "view Product");
		    CommonFunctions.waitForPageLoaded();
		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.RetailItemIdentification));
		     CopyLinkRetailItem2_WorkFlow=driver.findElement(SanitySuite1.RetailItemIdentification).getText();
		     log.info("Created Copy/Link Product RetailItem Value is "+CopyLinkRetailItem2_WorkFlow);
	           
		}
			catch(Exception e){
				fail=true;
				log.error("Exception in CopyLinkProductRetailItem1"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in CopyLinkProductRetailItem2"+er);
				throw er;
			}
		 return CopyLinkRetailItem2_WorkFlow;
		}
	 
	 public static boolean CreateWhatIFCostSheetForAssortment_SolidthroughRFQ(String [] data) throws Exception{
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingHyperLink));
				CommonFunctions.clickButtonOrLink(SanitySuite1.SourcingHyperLink, "SourcingHyperLink","Sourcing");
				//Clicking on Sourcing Tab
			    CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostingHyperLink));
				//CommonFunctions.waitForPageLoaded();
				CommonFunctions.clickButtonOrLink(SanitySuite1.CostingHyperLink, "HyperLink", "Costing");
				//Clicking on Costing Link
				CommonFunctions.waitForPageLoaded();
			    wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SortOption));
				//Waiting for a Sort Button
			    Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Source')]//following::select[1]")));
			     List<WebElement> allOptions=dropdDown.getOptions();
	            for(int i=0;i<allOptions.size();i++){
	   		     String RequiredValue=allOptions.get(i).getText();
	   		    // System.out.println(RequiredValue);
	   		     if(RequiredValue.contains(data[28]))
	   		     {
	   		    	 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SourcingDropDown, RequiredValue, "Source DropDown Selection");
	   		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
	   		    	 CommonFunctions.waitForPageLoaded();
	   		    	 break;
	   		     }
	            }
	                CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQPageActionDropDown);
	    		    Select dropdDown1 = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
	    		     List<WebElement> allOptions1=dropdDown1.getOptions();
	                 for(int i=0;i<allOptions1.size();i++){
	        		     String RequiredValue1=allOptions1.get(i).getText();
	        		    // System.out.println(RequiredValue);
	        		     if(RequiredValue1.contains(data[46]))
	        		     {
	        		    	 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQPageActionDropDown, RequiredValue1, "Source DropDown Selection");
	        		    	 CommonFunctions.waitForPageLoaded();
	        		    	 wait.until(ExpectedConditions.titleIs(data[46]));
	        		    	 break;
	        		     }
	                 }
	        		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.AllCheckBox);
	        		     SanitySuite1.vendorCheckBox="//a[contains(text(),'"+data[27]+"')]//preceding::input[1]";
	        		     SanitySuite1.VendorCheckBox=By.xpath(SanitySuite1.vendorCheckBox);
	        		     try{
	        		    	driver.findElement(By.xpath("//input[@id='source_selectAllCheckBox']")).isSelected();
	        		    	CommonFunctions.clickButtonOrLink(SanitySuite1.AllCheckBox, "CheckBox", "AllCheckBox");
	        		    	//Unchecking the All cehckBox
	        		    	try
	        		    	{
	        		    	CommonFunctions.waitForElementTobeClickable(SanitySuite1.VendorCheckBox);
	        		    	CommonFunctions.clickButtonOrLink(SanitySuite1.VendorCheckBox, "CheckBox", "VendorCheckBox");
	        		    	log.info(data[27]+" is only checked");
	        		    	}
	        		    	catch(Exception e){
	        		    		fail=true;
	        		    		log.error("User Should have Been added the "+data[27]+" as Suuplier");
	        		    		throw e; 
	        		    	}
	        		    	}
	        		     catch(Exception e){
	        		    	 CommonFunctions.waitForElementTobeClickable(SanitySuite1.VendorCheckBox);
	         		    	 CommonFunctions.clickButtonOrLink(SanitySuite1.VendorCheckBox, "CheckBox", "VendorCheckBox");
	         		    	 log.info(data[158]+" is only checked");
	        		     }
	            
	        		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.SelectButton);
	        		     CommonFunctions.clickButtonOrLink(SanitySuite1.SelectButton, "Button", "SelectButton");
	        		     //Clicking Select Button
	        		     CommonFunctions.waitForPageLoaded();
	        		     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.RFQColorway));
	        		     wait.until(ExpectedConditions.titleIs(data[46]));
	        		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQColorway,SanitySuite1.ColorwayDropdownValue, "ColrwayDropDown");
	        		     //Selecting colorway
	        		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQWave);
	        		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQWave, data[47], "WaveDropDown");
	        		     //Selecting Wave
	        		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQRequestDate);
	        		     CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.RFQRequestDate, data[48], "RFQRequestDate");
	        		     //Entering the RFQ Request date
	        		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQCutOffDate);
	        		     CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.RFQCutOffDate, data[49], "RFQ Cut Off date");
	        		     //Entering RFQ Cutoff date
	        		     CommonFunctions.waitForElementTobeClickable(ResponsibleEngineerDropDown);
	        		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ResponsibleEngineerDropDown, data[50], "ResponsibleEngineerDropDown");
	        		     //Selecting the responsible  engineer
	        		     CommonFunctions.waitForElementTobeClickable(ResponsibleCostEngineerDropDown);
	        		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(ResponsibleCostEngineerDropDown, data[51], "ResponsibleCostEngineerDropDown");
	        		     //Selecting the responsible Cost engineer
	        		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQCreateButton);
	        		     CommonFunctions.clickButtonOrLink(SanitySuite1.RFQCreateButton, "Button", "Create Button");
	        		     //clicking Create Button
	        		     wait.until(ExpectedConditions.titleIs(data[57]));
	        		     CommonFunctions.waitForPageLoaded();
	        		     CreateCostSheetTemplate(data);
	        			//Create Cost Sheet Template
	            
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in CreateWhatIFCostSheetForAssortment_SolidthroughRFQ"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in CreateWhatIFCostSheetForAssortment_SolidthroughRFQ"+er);
				throw er;
			}
			return true;
			}
	 
	 public static boolean CreateCostSheetTemplate(String [] data) throws Exception{
			try{
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductExpandImage);
				CommonFunctions.clickButtonOrLink(SanitySuite1.ProductExpandImage, "Image", "Expansion product Image");
				//Clicking + button
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.ApproximateVolume);
				CommonFunctions.clickButtonOrLink(SanitySuite1.ApproximateVolume, "table", "Approximate Volume");
				//Clicking out side of the table
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.ApproximateVolumeInputTextBox);
				CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.ApproximateVolumeInputTextBox, data[52], "Approximate Value");
				//Entering the Approximate value
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.UpdateRFQActionDropDown);
			   CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.UpdateRFQActionDropDown, data[53], "Create Cost Sheet Template");
				//Clicking Cost Sheet Template Option
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CreateCostSheetTemplateWindow));
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostSheetTypeDropDown);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CostSheetTypeDropDown, data[54], "CostSheetType");
				//Selection CostSheet Type
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorWayFirstValue);
				CommonFunctions.clickButtonOrLink(SanitySuite1.ColorWayFirstValue, "value", "ColorWay First Value");
				//clicking first value Of ColorValue
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.AddButton);
				CommonFunctions.clickButtonOrLink(SanitySuite1.AddButton, "Hyperlink", "Add HyperLink");
				//Adding first value Of ColorValue
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostSheetName);
				CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.CostSheetName, data[55], "Cost Sheet Name text Box");
				//Entering the value of the Cost Sheet Text Box
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.WaveDropDown);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.WaveDropDown, data[47], "WaveDropDown");
				//Selecting Wave Drop down value
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.Currency);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.Currency, data[56], "Currency");
				//Selecting Currency 
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.SaveButton);
				CommonFunctions.clickButtonOrLink(SanitySuite1.SaveButton, "Button","Save");
				//Clicking on Save Button
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostSheetCreateConfirmationTable));
			    wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CloseButtonOfCreateCostSheetWindow));
				CommonFunctions.clickButtonOrLink(SanitySuite1.CloseButtonOfCreateCostSheetWindow, "Button", "CloseButton");
				//Clicking on Close Button
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostSheetDoneButton));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetDoneButton, "Button", "DoneButton");
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.titleIs(data[21]));
				
			}
			catch(Exception e)
			{
				fail=true;
				log.error("Exception in UpdateRFQ"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Asssertion Error in CreateCostSheetTemplate"+er);
				throw er;
			}
			return true;
		}
	
	 public static boolean SearchTheRFQ(String [] data) throws Exception{
			try{
				 By RFQLinkText= By.linkText("RFQ");
				 CommonFunctions.waitForElementTobeClickable(RFQLinkText);
				 CommonFunctions.clickButtonOrLink(RFQLinkText, "Hyper-Link", "RFQLinkText");
				 //SanitySuite1.CloseOpenedExistingCostSheet();
			     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SortOption));
			     CommonFunctions.waitForPageLoaded();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.RFQinAdminServer));
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQinAdminServer);
				    CommonFunctions.clickButtonOrLink(SanitySuite1.RFQinAdminServer, "HyperLink", "RFQinAdminServer");
				    //Clicking on CostSheet HyperLink
				    CommonFunctions.waitForPageLoaded();
			   }
			catch(Exception e){
				fail=true;
				log.error("Exception in SearchTheRFQForTC_30"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in SearchTheRFQ"+er);
				throw er;
			}
			return true;
		}
	 
	 public static boolean AddContentTableValues(String [] data) throws Exception{
			try{
				 CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQUpdateDropDown);
				  Select dropdDown = new Select(driver.findElement(By.xpath("//div[@id='rfqResults']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/select")));
				     List<WebElement> allOptions=dropdDown.getOptions();
		             for(int i=0;i<allOptions.size();i++){
		 		     String RequiredValue=allOptions.get(i).getText();
		 		  //   System.out.println(RequiredValue);
		 		     if(RequiredValue.contains(data[60]))
		 		     {
		 		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQUpdateDropDown, RequiredValue, "RFQ Drop down");
		 		     wait.until(ExpectedConditions.titleIs(data[57]));    
		 		     CommonFunctions.waitForPageLoaded();
		 		     break;
		 		     }
		 		     }
		             CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductExpandImage);
		             CommonFunctions.clickButtonOrLink(SanitySuite1.ProductExpandImage, "Image", "ProductExpandImage");
		             //Clicking on + Button
		             CommonFunctions.waitForPageLoaded();
		             wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostSheetTemplateHyperLink));
		             CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetTemplateHyperLink, "HyperLink", "CostSheetTemplateHyperLink");
		             //Clicking on Cost SHeet Hyper link
		             wait.until(ExpectedConditions.titleIs(data[21]));
		             CommonFunctions.waitForPageLoaded();
		             wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ContentstableEditButton));
					    CommonFunctions.clickButtonOrLink(SanitySuite1.ContentstableEditButton, "HyperLink", "ContentsTableEditButton");
					    //Clicking on contents table Edit Button
					    CommonFunctions.waitForPageLoaded();
					    wait.until(ExpectedConditions.titleIs(data[61]));
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductColorWay);
					    CommonFunctions.clickButtonOrLink(SanitySuite1.ProductColorWay, "textBox", "Product ColrWayTextBox");
					    //Clicking on Product colorway Text Box
					    CommonFunctions.gettingParentWindow();
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.productColorWayHyperLink);
					    CommonFunctions.clickButtonOrLink(SanitySuite1.productColorWayHyperLink, "HyperLink", "productColorWayHyperLink");
					    //Clicking productColorWayHyperLink 
					    CommonFunctions.switchingChildWindow();
					    //Switching Child window
					    SanitySuite1.ColorWayType=By.linkText(data[62]);
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorWayType);
					    CommonFunctions.clickButtonOrLink(SanitySuite1.ColorWayType, "HyperLink", "ColorWayType");
					    //Clicking on Retail Item
					    CommonFunctions.waitForPageLoaded();
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductNumberTextBox);
					    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.ProductNumberTextBox, CopyLinkRetailItem1_WorkFlow, "ProductNumber");
					    //Entering a Product Number
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.SearchButton);
					    CommonFunctions.clickButtonOrLink(SanitySuite1.SearchButton, "Button", "Serach Button");
					    //Clicking Search Button
					    CommonFunctions.waitForPageLoaded();
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ChooseColorWay);
					    CommonFunctions.clickButtonOrLink(SanitySuite1.ChooseColorWay, "HyperLink", "ChooseButton");
					    //Clicking on Choose Button
					    CommonFunctions.switchParentWindow();
					    driver.switchTo().defaultContent();
						driver.switchTo().frame("contentframe");
					    //Switching Frame
					    CommonFunctions.waitForPageLoaded();
					    FillingContentTableForRFQ(data);
					    wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ContentstableEditButton));
					    CommonFunctions.clickButtonOrLink(SanitySuite1.ContentstableEditButton, "HyperLink", "ContentsTableEditButton");
					    //Clicking on contents table Edit Button
					    CommonFunctions.waitForPageLoaded();
					    wait.until(ExpectedConditions.titleIs(data[61]));
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.AddRowsButton);
					    CommonFunctions.clickButtonOrLink(SanitySuite1.AddRowsButton, "Image", "AddRowsButton");
					    //Clicking Add Row Icon
					    wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.AddRowTable));
					    //Waiting for add row Table
					    SanitySuite1.InsertAfter=By.linkText(data[63]);
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.InsertAfter);
					    CommonFunctions.clickButtonOrLink(SanitySuite1.InsertAfter, "hyperLink", "InsertAfter");
					    //Clicking on Insert After
					    CommonFunctions.waitForElementTobeClickable(SanitySuite1.UnitRatioTable);
						CommonFunctions.clickButtonOrLink(SanitySuite1.UnitRatioTable, "table", "UnitRatiotable");
						//Clicking on Unit Ratio
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductColorWay2);
						CommonFunctions.clickButtonOrLink(SanitySuite1.ProductColorWay2, "Text Box", "ProductColorWay2");
						//clicking on the Product Colorway
						 CommonFunctions.gettingParentWindow();
						    CommonFunctions.waitForElementTobeClickable(SanitySuite1.productColorWayHyperLink);
						    CommonFunctions.clickButtonOrLink(SanitySuite1.productColorWayHyperLink, "HyperLink", "productColorWayHyperLink");
						    //Clicking productColorWayHyperLink 
						    CommonFunctions.switchingChildWindow();
						    //Switching Child window
						    SanitySuite1.ColorWayType=By.linkText(data[62]);
						    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorWayType);
						    CommonFunctions.clickButtonOrLink(SanitySuite1.ColorWayType, "HyperLink", "ColorWayType");
						    //Clicking on Retail Item
						    CommonFunctions.waitForPageLoaded();
						    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductTextBox);
						    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.ProductTextBox,CopyLinkRetailItem2_WorkFlow, "ProductNumber");
						    log.info("The Selected colorway is: "+CopyLinkRetailItem2_WorkFlow);
						    //Entering a Product Number
						    CommonFunctions.waitForElementTobeClickable(SanitySuite1.SearchButton);
						    CommonFunctions.clickButtonOrLink(SanitySuite1.SearchButton, "Button", "Serach Button");
						    //Clicking Search Button
						    CommonFunctions.waitForPageLoaded();
						    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ChooseColorWay);
						    CommonFunctions.clickButtonOrLink(SanitySuite1.ChooseColorWay, "HyperLink", "ChooseButton");
						    //Clicking on Choose Button
						    CommonFunctions.switchParentWindow();
						    driver.switchTo().defaultContent();
							driver.switchTo().frame("contentframe");
						    //Switching Frame
						    CommonFunctions.waitForPageLoaded();
						    CommonFunctions.waitForElementTobeClickable(SanitySuite1.WaveTable1);
							CommonFunctions.clickButtonOrLink(SanitySuite1.WaveTable1, "Table", "wavetable");
							CommonFunctions.waitForElementTobeClickable(SanitySuite1.DropDownOfWaveTable1);
							CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.DropDownOfWaveTable1, data[47], "waveDropDown");
							//Clicking Wave Table value
							CommonFunctions.waitForElementTobeClickable(SanitySuite1.UnitRatioTable1);
							CommonFunctions.clickButtonOrLink(SanitySuite1.UnitRatioTable1, "table", "UnitRatiotable");
							CommonFunctions.waitForElementTobeClickable(SanitySuite1.UnitRatioTableTextBox1);
							CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.UnitRatioTableTextBox1, data[64], "UnitRatioTextBox");
							 CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostSheetDoneButton);
				   		     CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetDoneButton, "Button", "DoneButton");
				   		     //Clicking Done Button
				   		     CommonFunctions.waitForPageLoaded();
				   		     wait.until(ExpectedConditions.titleIs(data[21]));
						    
						    
						   
				}
				
			catch(Exception e){
				fail=true;
				log.error("Exception in AddContentTableValues"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in AddContentTableValues"+er);
				throw er;
			}
			return true;
		}
	 public static boolean SubmitRequestStatusCheck(String [] data) throws Exception{
			try{
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.RequestStatusInRFQSCreen);
				SanitySuite1.GettingText(SanitySuite1.RequestStatusInRFQSCreen);
			
				if(SanitySuite1.ActualValue.equals(data[65])){
				CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[65], "Request status is not updated as 'RequestSent'.Assertion failed.Please check");
		
				    }
				else{
					Thread.sleep(10000);
					By RefreshButton = By.xpath("//a[text()='Refresh']");
					CommonFunctions.waitForElementTobeClickable(RefreshButton);
					CommonFunctions.clickButtonOrLink(RefreshButton, "Button", "RefreshButton");
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SanitySuite1.RequestStatusInRFQSCreen));
					SanitySuite1.GettingText(SanitySuite1.RequestStatusInRFQSCreen);
					//SearchTheRFQ(data);
					CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[65], "Request status is not updated as 'RequestSent'.Assertion failed.Please check");
					//Checking the status of the Cost Sheet
					wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostSheetSelection));
					CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetSelection, "HyperLink", "Cost Sheet HyperLink");
					//Clicking on CostSheet HyperLink
					CommonFunctions.waitForPageLoaded();
				}
				
			}
			
			catch(Exception e){
				fail=true;
				log.error("Exception in SubmitRequestStatusCheck"+e);
				throw e;
			}
		catch(AssertionError er){
				fail=true;
				log.error("Assertion error in SubmitRequestStatusCheck"+er);
				throw er;
			}
			return true;
		}
	 public static boolean SearchRetailItemProduct1(String [] data) throws Exception{
			try{
				  driver.switchTo().defaultContent();
			      driver.switchTo().frame("headerframe");
			      //Switching into Header Frame
			      CommonFunctions.waitForPageLoaded();
			      CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchProduct);
				  SeleniumDriver.driver.findElement(CommonProjectFunctions.searchProduct).clear();
				  CommonFunctions.enterTextInTextbox(CommonProjectFunctions.searchProduct,CopyLinkRetailItem1_WorkFlow);
				  CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchIcon);
				  CommonFunctions.clickButtonOrLink(CommonProjectFunctions.searchIcon, "Btn", "SearchButton");
				  //Clicking Search Button
				  driver.switchTo().defaultContent();
			      driver.switchTo().frame("contentframe");
			      CommonFunctions.waitForPageLoaded();
				  SanitySuite1.NavigateToDetailsTab(data);
					//Navigating to details Tab
				  CommonFunctions.waitForElementTobeClickable(Product.detailPageSeasonDD);
		          CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[3],"Season year");
		          //CommonFunctions.handleAlertPopUp1();
		          CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in SearchAssortmentProduct"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in SearchRetailItemProduct1"+er);
				throw er;
			}
			return true;
		}
	 public static boolean CreateVendorBOM_RetailItem(String [] data) throws Exception{
		 try{
			 SanitySuite1.navigateToMaterialTabThroughSideBar(data);
			 wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
			 CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);

			 //Selecting the Specifiation
			 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SanitySuite1.addNewbomTab));
			 CommonFunctions.clickButtonOrLink(SanitySuite1.addNewbomTab, "btn", "Add New bom tab");
				//Enter bom Type
			 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SanitySuite1.bomtypeid));
				CommonFunctions.enterTextInTextbox(SanitySuite1.bomtypeid, data[84]);
				//Click Initialize bom
				 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SanitySuite1.initializebom));
				CommonFunctions.clickButtonOrLink(SanitySuite1.initializebom,"btn", "Initialize bom");
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.titleIs(data[85]));
				fillCreateBOM(data);
			 
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("CreateVendorBOM_RetailItem1"+e);
			 throw e;
		 }
		 catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in CreateVendorBOM_RetailItem"+er);
				throw er;
			}
		return true;
	 }
	 public static String fillCreateBOM(String[] data) throws Exception{
			try{
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(InternalBOMSoftG.headingCreateBOM));
				CommonFunctions.waitForVisibilityOfElement(InternalBOMSoftG.headingCreateBOM);
			
				//Select colorway
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.colorway);
				CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.colorway, 1);
				//Select Wave
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.wave);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.wave, data[33]);
				//Select Currency
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.currency);
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.currency, data[56]);
				//click on Create
				CommonFunctions.gettingParentWindow();
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.BOMFactoryValue);
				CommonFunctions.clickButtonOrLink(SanitySuite1.BOMFactoryValue, "Factory");
				//Clicking on Factory Link
				CommonFunctions.switchingChildWindow();
				 CommonFunctions.waitForElementTobeClickable(SanitySuite1.SearchButton);
				    CommonFunctions.clickButtonOrLink(SanitySuite1.SearchButton, "Button", "Search Button");
				    //Clicking Search Button
				    CommonFunctions.waitForPageLoaded();
				    /*CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductVendorName);
				    SanitySuite1.GettingText(SanitySuite1.ProductVendorName);
				    CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[159], "Actual and Expected Product vendors names Are Matched.Assetion failed.Please Verify");
				    log.info("***Verify that the search results include  factory selected in the Product - validated***");*/
				    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ChooseColorWay);
				    CommonFunctions.clickButtonOrLink(SanitySuite1.ChooseColorWay, "HyperLink", "ChooseButton");
				    //Clicking on Choose Button
				    CommonFunctions.switchParentWindow();
				    driver.switchTo().defaultContent();
					driver.switchTo().frame("contentframe");
				    //Switching Frame
				    CommonFunctions.waitForPageLoaded();
				CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.AddRowsButton));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.btnSaveAndCheckIn);
				CommonFunctions.clickButtonOrLink(InternalBOMSoftG.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				//Clicking on Save And Check In Button
				CommonFunctions.handleAlertPopUp1();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.titleIs(data[86]));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.BOMid);
				Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='bomId']")));
				SanitySuite1.BOMId=dropdDown.getFirstSelectedOption().getText();
			    log.info("Recently Created BOMID is " +SanitySuite1.BOMId);
				
		        }
			  catch(Exception e)
			  {
				fail=true;
				log.error("Exception in fillCreateBOM()", e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in fillCreateBOM"+er);
				throw er;
			}
		  return SanitySuite1.BOMId;
		}
	 
	 public static boolean CreateVendorCostSheet_RetailItem(String [] data) throws Exception{
			try{
				SanitySuite1.NavigateToCostingTabThroughSidebar(data);
				//Navigate to Costing through Side Bar
				CreateWhatIfCostSheetForRetailItemVendor(data);
				//Creating WhatIf Cost Sheet
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostSheetUpdateButton));
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CostSheetUpdateButton, data[60],"Cost Sheet Update Drop down");
				wait.until(ExpectedConditions.titleIs(data[83]));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.PrimaryCostSheetCheckBox);
			    CommonFunctions.clickButtonOrLink(SanitySuite1.PrimaryCostSheetCheckBox, "Check Box", "Primary Cost Sheet Check Box");
				//Clicking on Primary Cost Sheet Check Box
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.UpdateCostSheetSaveButton);
				CommonFunctions.clickButtonOrLink(SanitySuite1.UpdateCostSheetSaveButton, "UpdateCostSheetSaveButton", "UpdateCostSheetSaveButton");
				wait.until(ExpectedConditions.titleIs(data[21]));
				CommonFunctions.waitForPageLoaded();
				}
			catch(Exception e){
				fail=true;
				log.error("Exception in CreateVendorCostSheet_RetailItem1"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in CreateVendorCostSheet_RetailItem"+er);
				throw er;
			}
			return true;
		}
	 
	 
	 public static boolean CreateWhatIfCostSheetForRetailItemVendor(String [] data) throws Exception{
			try{
				  wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
			         CommonFunctions.waitForPageLoaded();         
			             //CommonFunctions.waitForElementTobeClickable(SourcingDropDown);
			             Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Source')]//following::select[1]")));
					     List<WebElement> allOptions=dropdDown.getOptions();
			             for(int i=0;i<allOptions.size();i++){
			    		     String RequiredValue=allOptions.get(i).getText();
			    		    // System.out.println(RequiredValue);
			    		     if(RequiredValue.contains(data[28]))
			    		     {
			    		    	 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SourcingDropDown, RequiredValue, "Source DropDown Selection");
			    		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
			    		    	 CommonFunctions.waitForPageLoaded();
			    		    	 break;
			    		     }
			             }
			    		     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostingActionDropDown);
			    			 Select dropdDown1 = new Select(driver.findElement(By.xpath("//select[@id='costingActions']")));
			    		     List<WebElement> allOptions1=dropdDown1.getOptions();
			                 for(int j=0;j<allOptions1.size();j++){
			     		     String RequiredValue1=allOptions1.get(j).getText();
			     		    // System.out.println(RequiredValue);
			     		     if(RequiredValue1.contains(data[71]))
			     		     {
			     		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CostingActionDropDown, RequiredValue1, "Costing Drop down");
			     		     CommonFunctions.waitForPageLoaded();
			     		     break;
			     		     }
			     		     }
			    			wait.until(ExpectedConditions.titleIs(data[72]));
			    			CommonFunctions.waitForPageLoaded();
			    			SanitySuite1.CostingType=By.linkText(data[73]);
			    			CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostingType);
			    			CommonFunctions.clickButtonOrLink(SanitySuite1.CostingType, "HyperLink", "CostingType");
			    			//Clicking on Costing Type
			    			wait.until(ExpectedConditions.titleIs(data[72]));
			    			CommonFunctions.waitForPageLoaded();
			    			CreateWhatIfCostSheetForRetailItem(data);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in CreateWhatIfCostSheet"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Error in CreateProductFromLineSheet"+er);
				throw er;
			}
			return true;
		}
	 
	 public static boolean CreateWhatIfCostSheetForRetailItem(String [] data) throws Exception{
			try{
		        CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostSheetCurrencyDropDown);
			    CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CostSheetCurrencyDropDown, data[56], "CyrrencyDropDown");
			    //Selecting the Currency Value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorWayFirstOption);
			    CommonFunctions.clickButtonOrLink(SanitySuite1.ColorWayFirstOption, "Option", "ColorwatFirstOption");
			    //Clicking ColorWay First Option
			    CommonFunctions.waitForElementTobeClickable(BOMDropDown);
			    CommonFunctions.selectFromDropDownByIndex(BOMDropDown, 1);
			    //Selection the BOM value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorwayAddButton);
			    CommonFunctions.clickButtonOrLink(SanitySuite1.ColorwayAddButton, "hyper Link", "Add");
			    //Clicking add  button
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.PlasticMaterialTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.PlasticMaterialTextBox, data[74], "PlasticMaterialTextBox");
			    //Entering ProductDevelopmentCost value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.PackagingMaterialTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.PackagingMaterialTextBox, data[75], "PackagingMaterialTextBox");
			    //Entering MasterCartonPackagingMaterial value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ChemicalMaterialTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.ChemicalMaterialTextBox, data[76], "ChemicalMaterialTextBox");
			    //Entering MasterCortonLaborCost value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.PurchasedMaterialTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.PurchasedMaterialTextBox, data[77], "PurchasedMaterialTextBox");
			    //Entering MisCellaneousCost value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ElectronicMaterialTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.ElectronicMaterialTextBox, data[78], "ElectronicMaterialTextBox");
			   //Entering ProductMarkUp value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.SoftGoodMaterialTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.SoftGoodMaterialTextBox, data[79], "SoftGoodMaterialTextBox");
			   //Entering Discount value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.GeneralDecoLaborTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.GeneralDecoLaborTextBox, data[80], "GeneralDecoLaborTextBox");
			   //Entering Length value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.MoldinglaborCostTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.MoldinglaborCostTextBox, data[81], "MoldinglaborCostTextBox");
			   //Entering Width value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.OverheadAndMarkUpTextBox);
			    CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.OverheadAndMarkUpTextBox, data[82], "OverheadAndMarkUpTextBox");
			   //Entering Height value
			    CommonFunctions.waitForElementTobeClickable(SanitySuite1.WhatIfCostSheetSaveButton);
			    CommonFunctions.clickButtonOrLink(SanitySuite1.WhatIfCostSheetSaveButton, "Button", "WhatIfCostSheetSaveButton");
			    //Clicking WhatIfCostSheetSaveButton 
			    CommonFunctions.waitForPageLoaded();
			    wait.until(ExpectedConditions.titleIs(data[21]));
			    
			}
					catch(Exception e){
						fail=true;
						log.error("Exception in CreateWhatIfCostSheet"+e);
						throw e;
					}
			return true;
				}
		
	 
	 
	 public static boolean SubmitRequestThroughCostSheet(String [] data) throws Exception{
			try{
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQHyperLinkOnCostSheet);
				CommonFunctions.clickButtonOrLink(SanitySuite1.RFQHyperLinkOnCostSheet, "RFQHyperLink");
				//Clicking RFQ Link
				wait.until(ExpectedConditions.titleIs(data[68]));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQActionDropDown);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQActionDropDown, data[70], "Action drop down");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQActionDropDown);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQActionDropDown, data[69], "Action Drop down value");
				//Clicking on Submit Request To vendors
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.RFQSelectButton));
				CommonFunctions.clickButtonOrLink(SanitySuite1.RFQSelectButton, "Button", "Select");
				//clicking on Select Button
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CloseButton));
				//Clicking on Close Button
				CommonFunctions.clickButtonOrLink(SanitySuite1.CloseButton, "HyperLink", "Close");
			}
				

			catch(Exception e){
				fail=true;
				log.error("Exception in SubmitRequestThroughCostSheet"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in SubmitRequestThroughCostSheet"+er);
				throw er;
			}
			return true;
		}
	 public static boolean SearchRetailItemProduct2(String [] data) throws Exception{
			try{
				  driver.switchTo().defaultContent();
			      driver.switchTo().frame("headerframe");
			      //Switching into Header Frame
			      CommonFunctions.waitForPageLoaded();
			      CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchProduct);
				  SeleniumDriver.driver.findElement(CommonProjectFunctions.searchProduct).clear();
				  CommonFunctions.enterTextInTextbox(CommonProjectFunctions.searchProduct,CopyLinkRetailItem2_WorkFlow);
				  CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchIcon);
				  CommonFunctions.clickButtonOrLink(CommonProjectFunctions.searchIcon, "Btn", "SearchButton");
				  //Clicking Search Button
				  driver.switchTo().defaultContent();
			      driver.switchTo().frame("contentframe");
			      CommonFunctions.waitForPageLoaded();
				  SanitySuite1.NavigateToDetailsTab(data);
					//Navigating to details Tab
				  CommonFunctions.waitForElementTobeClickable(Product.detailPageSeasonDD);
		          CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[3],"Season year");
		          //CommonFunctions.handleAlertPopUp1();
		          CommonFunctions.waitForPageLoaded();
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in SearchRetailItemProduct2"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in SearchRetailItemProduct2"+er);
				throw er;
			}
			return true;
		}
	 public static boolean CreateBOM_Assortment_Solid(String [] data) throws Exception{
		 try{
			 wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
			 CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
			 CommonFunctions.waitForPageLoaded();
			 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SanitySuite1.addNewbomTab));
			 CommonFunctions.clickButtonOrLink(SanitySuite1.addNewbomTab, "btn", "Add New bom tab");
				//Enter bom Type
			 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SanitySuite1.bomtypeid));
				CommonFunctions.enterTextInTextbox(SanitySuite1.bomtypeid, data[89]);
				//Click Initialize bom
				 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SanitySuite1.initializebom));
				CommonFunctions.clickButtonOrLink(SanitySuite1.initializebom,"btn", "Initialize bom");
				CommonFunctions.waitForPageLoaded();
				fillCreateBOM(data);
				//Filling the BOM Details
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("Exception in CreateBOM_Assortment_Solid" +e);
			 throw e;
		 }
		 catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in CreateBOM_Assortment_Solid"+er);
				throw er;
			}
		 return true;
	 }
	 public static boolean SelectSourceAndSpec(String [] data) throws Exception{
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
	            CommonFunctions.waitForPageLoaded();
	            //CommonFunctions.waitForElementTobeClickable(SourcingDropDown);
	            Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Source')]//following::select[1]")));
			     List<WebElement> allOptions=dropdDown.getOptions();
	            for(int i=0;i<allOptions.size();i++){
	   		     String RequiredValue=allOptions.get(i).getText();
	   		    // System.out.println(RequiredValue);
	   		     if(RequiredValue.contains(data[28]))
	   		     {
	   		    	 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SourcingDropDown, RequiredValue, "Source DropDown Selection");
	   		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
	   		    	 CommonFunctions.waitForPageLoaded();
	   		    	 break;
	   		     }
	            }
	            wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
				 CommonFunctions.selectFromDropDownByIndex(InternalBOMSoftG.selectSpecification, 1);
				 CommonFunctions.waitForPageLoaded();
				 
				 
				 
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in SelectSourceAndSpec"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in SelectSourceAndSpec"+er);
				throw er;
			}
			return true;
		}
	 public static boolean ChangeBOMAndCostSheetStatusOfRetailItem2(String [] data) throws Exception{
		 try{
			 wait.until(ExpectedConditions.visibilityOfElementLocated(UpdateButton));
			 CommonFunctions.clickButtonOrLink(UpdateButton, "Button", "UpdateButton");
			 //clicking on Update Button
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mainFrame"));
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.AddRowsButton));
				CommonFunctions.waitForPageLoaded();
			 wait.until(ExpectedConditions.visibilityOfElementLocated(HeaderAttributesButton));
			 CommonFunctions.clickButtonOrLink(HeaderAttributesButton, "Header ATrrribute Button", "HeaderAttributesButton");
			 //Clicking on Header Attribute
			 wait.until(ExpectedConditions.visibilityOfElementLocated(BOMStatusDropDown));
			 CommonFunctions.selectFromDropDownByVisibleTextUpdated(BOMStatusDropDown, data[87], "BOM Status DropDown");
			 CommonFunctions.waitForElementTobeClickable(InternalBOMSoftG.btnSaveAndCheckIn);
				CommonFunctions.clickButtonOrLink(InternalBOMSoftG.btnSaveAndCheckIn,"btn", "btnSaveAndCheckIn");
				//Clicking on Save And Check In Button
				CommonFunctions.handleAlertPopUp1();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait.until(ExpectedConditions.titleIs(data[86]));
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.BOMid);
				SanitySuite1.NavigateToCostingTabThroughSidebar(data);
				//Navigate to Costing through Side Bar
				//selectTheCostSheet(data);
				//Searching the RFQ
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostSheetUpdateButton));
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CostSheetUpdateButton, data[60],"Cost Sheet Update Drop down");
				wait.until(ExpectedConditions.titleIs(data[83]));
				wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetStatusDropDown));
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(CostSheetStatusDropDown, data[88], "CostSheetStatusDropDown");
				//Selecting the Cost SHeet STatus Drop Down value
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.UpdateCostSheetSaveButton);
				CommonFunctions.clickButtonOrLink(SanitySuite1.UpdateCostSheetSaveButton, "UpdateCostSheetSaveButton", "UpdateCostSheetSaveButton");
				wait.until(ExpectedConditions.titleIs(data[21]));
				CommonFunctions.waitForPageLoaded();
				
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("exception in ChangeBOMAndCostSheetStatusOfRetailItem2"+e);
			 throw e;
		 }
		 catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in ChangeBOMAndCostSheetStatusOfRetailItem2"+er);
				throw er;
			}
		 return true;
	 }
	 
	 public static boolean UpdateContentTable_VendorUser(String [] data) throws Exception{
			try{
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.CreateCostSheetsFromTemplate);
				CommonFunctions.clickButtonOrLink(SanitySuite1.CreateCostSheetsFromTemplate, "Button", "CreateCostSheetFromTemplate");
				//Clicking Create Cost SheetS from Template
				CommonFunctions.waitForPageLoaded();
				wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostSheetCreatedFromTemplate));
				CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetCreatedFromTemplate, "HyperLink", "Cost Sheet");
				//Clicking the Created Cost Sheet
				CommonFunctions.waitForPageLoaded();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ContentstableEditButton));
				    CommonFunctions.clickButtonOrLink(SanitySuite1.ContentstableEditButton, "HyperLink", "ContentsTableEditButton");
				    //Clicking on contents table Edit Button
				    CommonFunctions.waitForPageLoaded();
				    wait.until(ExpectedConditions.titleIs(data[61]));
				    CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductSource);
					CommonFunctions.clickButtonOrLink(SanitySuite1.ProductSource, "table", "Product Source");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductSourceDropDown);
					CommonFunctions.clickButtonOrLink(SanitySuite1.ProductSourceDropDown, "Drop-Down", "ProductSourceDropDown");
					Thread.sleep(2000);
					Select dropdDown = new Select(driver.findElement(By.xpath("//td[@id='r1_hbProductSourceCostingMOA']/div/select[1]")));
				     List<WebElement> allOptions=dropdDown.getOptions();
		             for(int i=0;i<allOptions.size();i++){
		   		     String RequiredValue=allOptions.get(i).getText();
		   		     //System.out.println(RequiredValue);
		   		     if(RequiredValue.contains(data[28]))
		   		     {
		   		     CommonFunctions.waitForPageLoaded();
		   		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ProductSourceDropDown, RequiredValue, "Source DropDown Selection");
		   		     break;
		   		     }
		             }
		             CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductSource1);
						CommonFunctions.clickButtonOrLink(SanitySuite1.ProductSource1, "table", "Product Source1");
						CommonFunctions.waitForPageLoaded();
						CommonFunctions.waitForElementTobeClickable(SanitySuite1.ProductSourceDropDown1);
						CommonFunctions.clickButtonOrLink(SanitySuite1.ProductSourceDropDown1, "Drop-Down", "ProductSourceDropDown1");
						Thread.sleep(2000);
						Select dropdDown1 = new Select(driver.findElement(By.xpath("//td[@id='r2_hbProductSourceCostingMOA']/div/select[1]")));
					     List<WebElement> allOptions1=dropdDown1.getOptions();
		             
		                 for(int i=0;i<allOptions1.size();i++){
			   		     String RequiredValue=allOptions1.get(i).getText();
			   		     //System.out.println(RequiredValue);
			   		     if(RequiredValue.contains(data[28]))
			   		     {
			   		     CommonFunctions.waitForPageLoaded();
			   		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ProductSourceDropDown1, RequiredValue, "Source DropDown Selection1");
			   		     break;
			   		     }
			             }
		             CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostSheetDoneButton);
		   		     CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetDoneButton, "Button", "DoneButton");
		   		     //Clicking Done Button
		   		     CommonFunctions.waitForPageLoaded();
		   		     wait.until(ExpectedConditions.titleIs(data[21]));
				
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in UpdateContentTable_VendorUser"+e);
				throw e;
			}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in UpdateContentTable_VendorUser"+er);
				throw er;
			}
			return true;
		}
	 public static  boolean FillingContentTableForRFQ(String [] data) throws Exception{
			try{
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.WaveTable);
				CommonFunctions.clickButtonOrLink(SanitySuite1.WaveTable, "Table", "wavetable");
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.DropDownOfWaveTable);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.DropDownOfWaveTable, data[47], "waveDropDown");
				//Clicking Wave Table value
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.UnitRatioTable);
				CommonFunctions.clickButtonOrLink(SanitySuite1.UnitRatioTable, "table", "UnitRatiotable");
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.UnitRatioTableTextBox);
				CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.UnitRatioTableTextBox, data[64], "UnitRatioTextBox");
				//Entering a Unit Price value
				 CommonFunctions.waitForElementTobeClickable(SanitySuite1.CostSheetDoneButton);
	   		     CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetDoneButton, "Button", "DoneButton");
	   		     //Clicking Done Button
	   		     CommonFunctions.waitForPageLoaded();
	   		     wait.until(ExpectedConditions.titleIs(data[21]));
	   		     }
				
				catch(Exception e){
					fail=true;
					log.error("Exception in FillingContentTable"+e);
					throw e;
				}
			catch(AssertionError er){
				fail=true;
				log.error("Assertion Error in FillingContentTableForRFQ"+er);
				throw er;
			}
				return true;
		}
	 
	 
}
