package com.hasbro.sanity;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.SeleniumDriver;

import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Product;

import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

public class SanitySuite5_WorkFlow extends TestsuiteBase{
	
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
	public static String UpdateReviewer; 
	public static String ResponsibleEngineer;
	public static String TaskCheckBox;
	public static String TaskComment;
	public static String TaskTextArea;
	public static String LeadReviewer;
	public static String TaskByReferingText;
	public static By ResponsibleEngineerDropDown               = By.xpath("//td[contains(text(),'Responsible Engineer')]//following::select[1]");
	public static By ResponsibleCostEngineerDropDown           = By.xpath("//td[contains(text(),'Responsible Engineer')]//following::select[2]");
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
	public static By UpdateReviewersTask                       = By.xpath("//a[contains(text(),'Update Reviewers')]");
	public static By SecondaryReviewerReviewCostSheet          = By.xpath("//a[contains(text(),'Secondary Reviewer: Review Cost Sheet')]");
	public static By CompleteButton                            = By.xpath("//a[text()='Complete']");
	public static By VendorReferenceNumber                     = By.xpath("//td[contains(text(),'Vendor Reference Number')]");
	public static By PopUpAfterSubmission                      = By.xpath("//div[@id='divContent']/center");
	public static By MyWorktab                                 = By.xpath("//li[@id='siteNavLink']/a");
	public static By ProcessHistoryTaskName_SecondRow          = By.xpath("//td[contains(text(),'Process History')]//following::td[12]");
	public static By ProcessHistotyAction_SecondRow            = By.xpath("//td[contains(text(),'Process History')]//following::td[15]");
	public static By ProcessHistoryTaskName_FourthRow          = By.xpath("//td[contains(text(),'Process History')]//following::td[22]");
	public static By ProcessHistotyAction_FourthRow            = By.xpath("//td[contains(text(),'Process History')]//following::td[15]");
	public static By ReviewCommentRowForSecondaryReviewer      = By.xpath("//td[contains(text(),'Reviewer Comments')]//following::div[2]/table/tbody/tr[3]");
	public static By CommentsSecondaryReviewer                 = By.xpath("//td[contains(text(),'Reviewer Comments')]//following::div[2]/table/tbody/tr[3]/td[5]");
	public static By ReviewCommentRowForResponsibleEngineer    = By.xpath("//td[contains(text(),'Reviewer Comments')]//following::div[2]/table/tbody/tr[5]");
	public static By CommentsResponsibleEngineer               = By.xpath("//td[contains(text(),'Reviewer Comments')]//following::div[2]/table/tbody/tr[5]/td[5]");
	public static By UpdateRFQButton                           = By.xpath("//select[@id='rfqRequestActionOptions']");
	
	
	
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
	public static By UpdateReviewerRadioButton;
	public static By TaskCheckBoxWebElement;
	public static By TaskCommentWebElement;
	public static By TaskTextAreaWebElement;
	public static By LeadReviewerWebElement;
	public static By TaskByReferingText_WebElement;
	public static By ResponsibleEngineer_WebElement;
	
	
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSCFunctional(String[] data) throws Exception{
		count++;
		System.out.println(runmodes[count]);
		if(!runmodes[count].equalsIgnoreCase("y")||(WorkFlowPreRequisite.fail||fail||CommonFunctions.error)){
			skip=true;
			if(WorkFlowPreRequisite.fail||fail||CommonFunctions.error){
			Utility.automatedDataSet(suiteSanityXls, this.getClass().getSimpleName(), count+2, "Previous Work Flow Test Case Failed.So Skipping This Test cases Deliberately Since Test Case Dependent on Previous Script");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			else{
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
		}
		try{
			log.debug("Inside testcase for Sanity suite");
			// User Name, Password and Action
			log.info("col0 :" + data[0]); 
			log.info("col1 :" + data[1]);
			log.info("col1 :" + data[2]);
			//log.info("Testcase is :" + data[137]);
			//log.info("Testcase no is :" + data[138]);
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
					//SendEmailToPeople.sendMail();
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}
			switch (data[2]) {
			case "Pre-Requisite":
			WorkFlowPreRequisite.workFlow_PreRequisite_ForTwoRetailItems(data);
			/*Pre-Req
			 * AdminUser(data[0],data[1]
			 * SourceName(data[27])
			 * SouceDropDownValue(data[28])
			 * Costing User(data [44] ,Password data[45])
			 * vendor User(data[66],data[67])
			 * Responsible Engineer(data[50])
			 * Responsible Cost Engineer(data[51])
			 */
			 break;
			
			 
			case "PC86 - 1.04 Activate a Vendor Product Cost Sheet":
			PC86_ActivateVendorProductCostSheet(data);
			/*Pre-Req
			* Costing User(data[0] and data[1])
			* Responsible Cost Engineer(data[102],data[103])
			* Responsible Engineer(data[data[105],data[106])
			*/
			break;
			
			
			case "PC101 - 2.06 Secondary Reviewer Lead Reviewer":
			PC101_SecondaryReviewerLeadReviewer(data);
			/*Pre-Req
				*Responsible Engineering User(data[0] and data[1])
				*Source(data[28])
				*/
				break;
				
			case "PC99 - 2.04 Lead Reviewer Update Reviewers":
			PC99_LeadReviewersUpdateReviewers(data);
			break;
			/*Pre-Req
			 * Responsible Cost Engineer(data[0],data[1])
			 * Responsible Engineer(data[50])
			 * Responsible Cost Engineer(data[51])
			 * Responsible Engineer2UserName(data[113)-as data[50], and password(data[114])
			 */
			
		    default:
			fail=true;
			log.info("Default is executed");
			
			}
		}
	   catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	
	public static boolean ClickUpdateReviewersTaskOption(String [] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(MyWorktab);
			CommonFunctions.clickButtonOrLink(MyWorktab, "tab", "MyWorktab");
			//Clicking my work Tab
			CommonFunctions.waitForElementTobeClickable(MyWorkPlusButton);
			CommonFunctions.clickButtonOrLink(MyWorkPlusButton, "Image", "MyWorkPlusButton");
			//Clicking on my work Plus Button
			CommonFunctions.waitForElementTobeClickable(LCSProductCostSheet);
			CommonFunctions.clickButtonOrLink(LCSProductCostSheet, "Hyper_link", "LCSProductCostSheet");
			//Clicking LCS ProductCost Sheet
			wait.until(ExpectedConditions.visibilityOfElementLocated(UpdateReviewersTask));
			CommonFunctions.clickButtonOrLink(UpdateReviewersTask, "HyperLink", "UpdateReviewersTask");
			//Clicking on  Lead reviewer Review Cost Sheet
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait.until(ExpectedConditions.titleIs(data[21]));
			CommonFunctions.waitForPageLoaded();
			}
		catch(Exception e){
			fail=true;
			log.error("Exception in ClickUpdateReviewersTaskOption"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Error in ClickUpdateReviewersTaskOption"+er);
			throw er;
		}
		return true;
	}
	
	
	public static boolean PC99_LeadReviewersUpdateReviewers(String [] data) throws Exception{
		try{
			OpenMyWork_LeadReviewerReviewCostSheet(data);
			//Open My Work
			Assertion_T1Verification(data);
			//T1 Verification of Assertion
			Select_UpdateReviewers(data);
			//Selecting the Update Reviewer option
			ClickUpdateReviewersTaskOption(data);
			//click update Reviewers option
			UpdateNewresponsibleCostEngineerAndResponsibleEngineer(data);
			//Update New Responsible Engineer and Responsible Cost Engineer
			SelectResponsibleEngineer(data);
			//Select a Responsible Engineer
			AssertionVerificationOfProcessHistorySectionAndReviewComments(data);
			//Assertion verification of Process History and Review Comments
			CommonProjectFunctions.logOut();
			//Logging out
			openBrowser();
			//Opening the Browser
			launchApp(data[113],data[114]);
			//Launching the Browser
			OpenMyWork_LeadReviewerReviewCostSheet(data);
			//Open My Work
			Assertion_T1Verification(data);
			//T1 Verification of Assertion
			UpdateTask_SecondaryReviewer(data);
			//Updating it as Secondary Reviewer
			}
		
		catch(Exception e){
			fail=true;
			log.error("Exception in PC99_LeadReviewersUpdateReviewers"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Erron in PC99_LeadReviewersUpdateReviewers"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean AssertionVerificationOfProcessHistorySectionAndReviewComments(String [] data) throws Exception{
		try{
			WorkFlowPreRequisite.SearchAssortmentProduct(data);
			//Search Assortment/Solid Product
			WorkFlowPreRequisite.SelectSourceAndSpec(data);
			//Selecting the SOurce and Specification
			SanitySuite1.NavigateToCostingTabThroughSidebar(data);
			//Navigate to Costing through Side Bar
			AssertionVerificationOfProcessHistoryAndReviewComment_FourthRow(data);
			//Assertion verification of process history and review comments
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in UpdateNewresponsibleCostEngineerAndResponsibleEngineer"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Error in UpdateNewresponsibleCostEngineerAndResponsibleEngineer"+er);
			throw er;
		}	
	return true;	
	}
	
	public static boolean UpdateNewresponsibleCostEngineerAndResponsibleEngineer(String [] data) throws Exception{
		try{
			WorkFlowPreRequisite.SearchAssortmentProduct(data);
			//Search Assortment/Solid Product
			SanitySuite1.NavigateToRFQThroughSideBar(data);
			//Clicking RFQ
			WorkFlowPreRequisite.SelectSourceAndSpec(data);
			//Selecting the SOurce and Specification
			WorkFlowPreRequisite.SearchTheRFQ(data);
			//Searching the RFQ
			UpdateRFQNewResponsibleCostAndResponsibleEngineer(data);
			//Update new Responsible Engineer and Responsible Cost Engineer
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in UpdateNewresponsibleCostEngineerAndResponsibleEngineer"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Error in UpdateNewresponsibleCostEngineerAndResponsibleEngineer"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean UpdateRFQNewResponsibleCostAndResponsibleEngineer(String [] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(UpdateRFQButton);
			CommonFunctions.selectFromDropDownByVisibleTextUpdated(UpdateRFQButton, data[60], "UpdateOption");
			//Selecting Update Option
			wait.until(ExpectedConditions.titleIs(data[57]));
			CommonFunctions.waitForPageLoaded();
			   CommonFunctions.waitForElementTobeClickable(WorkFlowPreRequisite.ResponsibleEngineerDropDown);
  		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(WorkFlowPreRequisite.ResponsibleEngineerDropDown, data[50], "ResponsibleEngineerDropDown");
  		     //Selecting the responsible  engineer
  		     CommonFunctions.waitForElementTobeClickable(WorkFlowPreRequisite.ResponsibleCostEngineerDropDown_Update);
  		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(WorkFlowPreRequisite.ResponsibleCostEngineerDropDown_Update, data[51], "ResponsibleCostEngineerDropDown");
  		     //Selecting the responsible Cost engineer
  		    wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CostSheetDoneButton));
			CommonFunctions.clickButtonOrLink(SanitySuite1.CostSheetDoneButton, "Button", "DoneButton");
			CommonFunctions.waitForPageLoaded();
			wait.until(ExpectedConditions.titleIs(data[112]));
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in UpdateRFQNewResponsibleCostAndResponsibleEngineer"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Error in UpdateRFQNewResponsibleCostAndResponsibleEngineer"+er);
			throw er;
		}
		return true;
	}
	public static boolean SelectResponsibleEngineer(String [] data) throws Exception{
		try{
			CommonFunctions.waitForElementTobeClickable(TaskByReferingText_WebElement);
			ResponsibleEngineer =TaskByReferingText+"//following::tr[6]/td[1]/input[1]";
			ResponsibleEngineer_WebElement=By.xpath(ResponsibleEngineer);
			CommonFunctions.waitForElementTobeClickable(ResponsibleEngineer_WebElement);
			CommonFunctions.clickButtonOrLink(ResponsibleEngineer_WebElement, "Radio Button", "ResponsibleEngineer_WebElement");
			//Clicking on Secondary Reviewer Radio Button
			TaskCheckBox=TaskByReferingText+"//preceding::input[1]";
			TaskCheckBoxWebElement=By.xpath(TaskCheckBox);
			CommonFunctions.waitForElementTobeClickable(TaskCheckBoxWebElement);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(TaskCheckBoxWebElement, "CheckBox", "TaskCheckBoxWebElement");
			//Clicking task Check Box
			TaskComment=TaskByReferingText+"//following::img[1]";
			TaskCommentWebElement=By.xpath(TaskComment);
			CommonFunctions.waitForElementTobeClickable(TaskCommentWebElement);
			CommonFunctions.clickButtonOrLink(TaskCommentWebElement, "Image", "TaskCommentWebElement");
			TaskTextArea=TaskByReferingText+"//following::textarea[1]";
			TaskTextAreaWebElement=By.xpath(TaskTextArea);
			CommonFunctions.waitForElementTobeClickable(TaskTextAreaWebElement);
			CommonFunctions.enterTextInTextboxUpdated(TaskTextAreaWebElement, data[111], "TaskTextAreaWebElement");
			//Entering the value in TextArea
			CommonFunctions.waitForElementTobeClickable(CompleteButton);
			CommonFunctions.clickButtonOrLink(CompleteButton, "Button", "CompleteButton");
			//Clicking on Complete Button
			CommonFunctions.waitForPageLoaded();
	        CommonFunctions.isElementNotPresent(TaskByReferingText_WebElement, "TaskByReferingText_WebElement");
			log.info(getTextOfTheTask+" Task get Disappears");
			//Waiting for Invisibility of That element
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in SelectResponsibleEngineer"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Erron in SelectResponsibleEngineer"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean Select_UpdateReviewers(String [] data) throws Exception{
		try{
			UpdateReviewer =TaskByReferingText+"//following::tr[11]/td[1]/input[1]";
			UpdateReviewerRadioButton=By.xpath(UpdateReviewer);
			CommonFunctions.waitForElementTobeClickable(UpdateReviewerRadioButton);
			CommonFunctions.clickButtonOrLink(UpdateReviewerRadioButton, "Radio Button", "UpdateReviewerRadioButton");
			//Clicking on Secondary Reviewer Radio Button
			TaskCheckBox=TaskByReferingText+"//preceding::input[1]";
			TaskCheckBoxWebElement=By.xpath(TaskCheckBox);
			CommonFunctions.waitForElementTobeClickable(TaskCheckBoxWebElement);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(TaskCheckBoxWebElement, "CheckBox", "TaskCheckBoxWebElement");
			//Clicking task Check Box
			TaskComment=TaskByReferingText+"//following::img[1]";
			TaskCommentWebElement=By.xpath(TaskComment);
			CommonFunctions.waitForElementTobeClickable(TaskCommentWebElement);
			CommonFunctions.clickButtonOrLink(TaskCommentWebElement, "Image", "TaskCommentWebElement");
			TaskTextArea=TaskByReferingText+"//following::textarea[1]";
			TaskTextAreaWebElement=By.xpath(TaskTextArea);
			CommonFunctions.waitForElementTobeClickable(TaskTextAreaWebElement);
			CommonFunctions.enterTextInTextboxUpdated(TaskTextAreaWebElement, data[115], "TaskTextAreaWebElement");
			//Entering the value in TextArea
			CommonFunctions.waitForElementTobeClickable(CompleteButton);
			CommonFunctions.clickButtonOrLink(CompleteButton, "Button", "CompleteButton");
			//Clicking on Complete Button
			CommonFunctions.waitForPageLoaded();
	        CommonFunctions.isElementNotPresent(TaskByReferingText_WebElement, "TaskByReferingText_WebElement");
			log.info(getTextOfTheTask+" Task get Disappears");
			//Waiting for Invisibility of That element
			
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in Select_UpdateReviewers"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Erron in Select_UpdateReviewers"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean Assertion_T1Verification(String [] data){
		try{
			CommonFunctions.waitForElementTobeClickable(TaskByReferingText_WebElement);
			//Waiting till the Element To Be Clickable
			for(int s=7,j=2,k=0;s<=12;s++,k++){
				T1DefinitionOptionPath=TaskByReferingText+"//following::tr["+s+"]/td["+j+"]";
				T1DefinitionOption=By.xpath(T1DefinitionOptionPath);
				CommonFunctions.waitForElementTobeClickable(T1DefinitionOption);
				SanitySuite1.GettingText(T1DefinitionOption);
				CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[96+k], "Actual and expected values if T1Definition values are Not matched.Assertion failed.Please check");
				//verification Of First Option	
			}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in Assertion_T1Verification"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Erron in Select_UpdateReviewers"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean PC101_SecondaryReviewerLeadReviewer(String [] data) throws Exception{
		try{
			OpenMyWork_SecondaryReviewerReviewCostSheet(data);
			//opening the Secondary Reviewer
			FindWorkFlowTaskSecondTimeAndAssertionVerificationOf_T10_Definition(data);
			//Find My Work Flow task and assertion Verification of Task 10 Definition
			UpdateTask_LeadReviewer(data);
			//Clicking lead reviewer and update
			WorkFlowPreRequisite.SearchAssortmentProduct(data);
			//Search Assortment/Solid Product
			WorkFlowPreRequisite.SelectSourceAndSpec(data);
			//Selecting the SOurce and Specification
			SanitySuite1.NavigateToCostingTabThroughSidebar(data);
			//Navigate to Costing through Side Bar
			AssertionVerificationOfProcessHistoryAndReviewComment_SecondRow(data);
			//Assertion verification of process history and review comments
			
			if(!(WorkFlowPreRequisite.fail==true||fail==true || CommonFunctions.error==true)){
			    log.info("PC101_SecondaryReviewerLeadReviewer TEST CASE HAS BEEN VERIFIED SUCCESSFULLY");
			    }
			    else{
			    log.error("PC101_SecondaryReviewerLeadReviewer TEST CASE FAILED.PLEASE CHECK");
			    }
		      }
		catch(Exception e){
			fail=true;
			log.error("Exception in PC101_SecondaryReviewerLeadReviewer"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in PC101_SecondaryReviewerLeadReviewer"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean AssertionVerificationOfProcessHistoryAndReviewComment_SecondRow(String [] data){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(ProcessHistoryTaskName_SecondRow));
			SanitySuite1.GettingText(ProcessHistoryTaskName_SecondRow);
			CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[110],"Actual And Expected Process History task name Is Not Matched.Assertion failed.Please Verify");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ProcessHistotyAction_SecondRow));
			SanitySuite1.GettingText(ProcessHistotyAction_SecondRow);
			CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[108],"Actual And Expected Process History Action name Is Not Matched.Assertion failed.Please Verify");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ReviewCommentRowForSecondaryReviewer));
			CommonFunctions.isElementDisplayed(ReviewCommentRowForSecondaryReviewer, "ReviewCommentRowForSecondaryReviewer");
			CommonFunctions.waitForElementTobeClickable(CommentsSecondaryReviewer);
			SanitySuite1.GettingText(CommentsSecondaryReviewer);
			CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[104], "Actual and Expected Commonts Are not matched.Assertion Failed.Please Verify");
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationOfProcessHistoryAndReviewComment_SecondRow"+e);
			throw e;
			}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in AssertionVerificationOfProcessHistoryAndReviewComment_SecondRow"+er);
			throw er;
		}
		return true;
	}
	public static boolean AssertionVerificationOfProcessHistoryAndReviewComment_FourthRow(String [] data){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(ProcessHistoryTaskName_FourthRow));
			SanitySuite1.GettingText(ProcessHistoryTaskName_FourthRow);
			CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[110],"Actual And Expected Process History task name Is Not Matched.Assertion failed.Please Verify");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ProcessHistotyAction_FourthRow));
			SanitySuite1.GettingText(ProcessHistotyAction_FourthRow);
			CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[108],"Actual And Expected Process History Action name Is Not Matched.Assertion failed.Please Verify");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ReviewCommentRowForResponsibleEngineer));
			CommonFunctions.isElementDisplayed(ReviewCommentRowForResponsibleEngineer, "ReviewCommentRowForResponsibleEngineer");
			CommonFunctions.waitForElementTobeClickable(CommentsResponsibleEngineer);
			SanitySuite1.GettingText(CommentsResponsibleEngineer);
			CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[111], "Actual and Expected Commonts Are not matched.Assertion Failed.Please Verify");
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in AssertionVerificationOfProcessHistoryAndReviewComment_SecondRow"+e);
			throw e;
			}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in AssertionVerificationOfProcessHistoryAndReviewComment_SecondRow"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean PC86_ActivateVendorProductCostSheet(String [] data) throws Exception{
		try{
			WorkFlowPreRequisite.SearchAssortmentProduct(data);
			//Search Assortment/Solid Product
			SanitySuite1.NavigateToRFQThroughSideBar(data);
			//Clicking RFQ
			WorkFlowPreRequisite.SelectSourceAndSpec(data);
			//Selecting the SOurce and Specification
			WorkFlowPreRequisite.SearchTheRFQ(data);
			//Searching the RFQ
			InitialQuoteForReview(data);
			//Initial Quote for Review
			CommonProjectFunctions.logOut();
			//Logging out
			openBrowser();
			//opening the Browser
			launchApp(data[102],data[103]);
			//Launching the URL with Responsible Costing Engineering user
			OpenMyWork_LeadReviewerReviewCostSheet(data);
			//Open My Work
			FindWorkFlowTaskAndAssertionVerificationOfTask_T1_Definition(data);
			//Find My Work Flow task and assertion Verification of Task Definition
			UpdateTask_SecondaryReviewer(data);
			//Updating it as Secondary Reviewer
            if(!(WorkFlowPreRequisite.fail==true||fail==true || CommonFunctions.error==true)){
			    log.info("PC86_ActivateVendorProductCostSheet TEST CASE HAS BEEN VERIFIED SUCCESSFULLY");
			    }
			    else{
			    log.error("PC86_ActivateVendorProductCostSheet TEST CASE FAILED.PLEASE CHECK");
			    }
		      }
		catch(Exception e){
			fail=true;
			log.error("Exception in PC86_ActivateVendorProductCostSheet"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in PC86_ActivateVendorProductCostSheet"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean UpdateTask_SecondaryReviewer(String [] data) throws Exception{
		try{
			
			SecondaryReviewer =TaskByReferingText+"//following::tr[9]/td[1]/input[1]";
			SecondaryReviewerRadioButton=By.xpath(SecondaryReviewer);
			CommonFunctions.waitForElementTobeClickable(SecondaryReviewerRadioButton);
			CommonFunctions.clickButtonOrLink(SecondaryReviewerRadioButton, "Radio Button", "SecondaryReviewerRadioButton");
			//Clicking on Secondary Reviewer Radio Button
			TaskCheckBox=TaskByReferingText+"//preceding::input[1]";
			TaskCheckBoxWebElement=By.xpath(TaskCheckBox);
			CommonFunctions.waitForElementTobeClickable(TaskCheckBoxWebElement);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(TaskCheckBoxWebElement, "CheckBox", "TaskCheckBoxWebElement");
			//Clicking task Check Box
			TaskComment=TaskByReferingText+"//following::img[1]";
			TaskCommentWebElement=By.xpath(TaskComment);
			CommonFunctions.waitForElementTobeClickable(TaskCommentWebElement);
			CommonFunctions.clickButtonOrLink(TaskCommentWebElement, "Image", "TaskCommentWebElement");
			TaskTextArea=TaskByReferingText+"//following::textarea[1]";
			TaskTextAreaWebElement=By.xpath(TaskTextArea);
			CommonFunctions.waitForElementTobeClickable(TaskTextAreaWebElement);
			CommonFunctions.enterTextInTextboxUpdated(TaskTextAreaWebElement, data[104], "TaskTextAreaWebElement");
			//Entering the value in TextArea
			CommonFunctions.waitForElementTobeClickable(CompleteButton);
			CommonFunctions.clickButtonOrLink(CompleteButton, "Button", "CompleteButton");
			//Clicking on Complete Button
			CommonFunctions.waitForPageLoaded();
	        CommonFunctions.isElementNotPresent(TaskByReferingText_WebElement, "TaskByReferingText_WebElement");
			log.info(getTextOfTheTask+" Task get Disappears");
			//Waiting for Invisibility of That element
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in UpdateTask_SecondaryReviewer"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in UpdateTask_SecondaryReviewer"+er);
			throw er;
		}
		return true;
	}
	public static boolean UpdateTask_LeadReviewer(String [] data) throws Exception{
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(TaskByReferingText_WebElement));
			LeadReviewer =TaskByReferingText+"//following::tr[7]/td[1]/input[1]";
			LeadReviewerWebElement=By.xpath(LeadReviewer);
			CommonFunctions.waitForElementTobeClickable(LeadReviewerWebElement);
			CommonFunctions.clickButtonOrLink(LeadReviewerWebElement, "Radio Button", "LeadReviewerWebElement");
			//Clicking on Lead Reviewer Radio Button
		    CommonFunctions.waitForElementTobeClickable(TaskCheckBoxWebElement);
			CommonFunctions.clickButtonOrLink(TaskCheckBoxWebElement, "CheckBox", "TaskCheckBoxWebElement");
			//Clicking task Check Box
	        CommonFunctions.waitForElementTobeClickable(TaskCommentWebElement);
			CommonFunctions.clickButtonOrLink(TaskCommentWebElement, "Image", "TaskCommentWebElement");
		    //Clicking Task Comment
			CommonFunctions.waitForElementTobeClickable(TaskTextAreaWebElement);
			CommonFunctions.enterTextInTextboxUpdated(TaskTextAreaWebElement, data[104], "TaskTextAreaWebElement");
			//Entering the value in TextArea
			CommonFunctions.waitForElementTobeClickable(CompleteButton);
			CommonFunctions.clickButtonOrLink(CompleteButton, "Button", "CompleteButton");
			//Clicking on Complete Button
			CommonFunctions.waitForPageLoaded();
		    CommonFunctions.isElementNotPresent(TaskByReferingText_WebElement, "TaskByReferingText_WebElement");
			log.info(getTextOfTheTask+" Task get Disappears");
			//Waiting for Invisibility of That element
	      
			 
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in UpdateTask_SecondaryReviewer"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in UpdateTask_LeadReviewer"+er);
			throw er;
		}
		return true;
	}
	public static boolean OpenMyWork_SecondaryReviewerReviewCostSheet(String [] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(MyWorktab);
			CommonFunctions.clickButtonOrLink(MyWorktab, "tab", "MyWorktab");
			//Clicking my work Tab
			CommonFunctions.waitForElementTobeClickable(MyWorkPlusButton);
			CommonFunctions.clickButtonOrLink(MyWorkPlusButton, "Image", "MyWorkPlusButton");
			//Clicking on my work Plus Button
			CommonFunctions.waitForElementTobeClickable(LCSProductCostSheet);
			CommonFunctions.clickButtonOrLink(LCSProductCostSheet, "Hyper_link", "LCSProductCostSheet");
			//Clicking LCS ProductCost Sheet
			wait.until(ExpectedConditions.visibilityOfElementLocated(SecondaryReviewerReviewCostSheet));
			CommonFunctions.clickButtonOrLink(SecondaryReviewerReviewCostSheet, "HyperLink", "SecondaryReviewerReviewCostSheet");
			//Clicking on  Lead reviewer Review Cost Sheet
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait.until(ExpectedConditions.titleIs(data[21]));
			CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in OpenMyWork_SecondaryReviewerReviewCostSheet"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in OpenMyWork_SecondaryReviewerReviewCostSheet"+er);
			throw er;
		}
		return true;
	}
	
	public static boolean OpenMyWork_LeadReviewerReviewCostSheet(String [] data) throws Exception{
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("sidebarframe");
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(MyWorktab);
			CommonFunctions.clickButtonOrLink(MyWorktab, "tab", "MyWorktab");
			//Clicking my work Tab
			CommonFunctions.waitForElementTobeClickable(MyWorkPlusButton);
			CommonFunctions.clickButtonOrLink(MyWorkPlusButton, "Image", "MyWorkPlusButton");
			//Clicking on my work Plus Button
			CommonFunctions.waitForElementTobeClickable(LCSProductCostSheet);
			CommonFunctions.clickButtonOrLink(LCSProductCostSheet, "Hyper_link", "LCSProductCostSheet");
			//Clicking LCS ProductCost Sheet
			wait.until(ExpectedConditions.visibilityOfElementLocated(LeadReviewerReviewCostSheet));
			CommonFunctions.clickButtonOrLink(LeadReviewerReviewCostSheet, "HyperLink", "LeadReviewerReviewCostSheet");
			//Clicking on  Lead reviewer Review Cost Sheet
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait.until(ExpectedConditions.titleIs(data[21]));
			CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in OpenMyWork_LeadReviewerReviewCostSheet"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in OpenMyWork_LeadReviewerReviewCostSheet"+er);
			throw er;
		}
		return true;
	}
	public static boolean FindWorkFlowTaskAndAssertionVerificationOfTask_T1_Definition(String [] data){
		try{
			WorkFlowTask =WorkFlowPreRequisite.AssortmentProductNumberForWorkFlow+data[94]+" "+data[28];
			for(int i=2;i<=1000000;i++){
			WorkFlowReference="//a[text()='Complete']//following::a["+i+"]";
			MyWorkFlowTask=By.xpath(WorkFlowReference);
			myWorkFlowTaskCommentBox="//a[text()='Complete']//following::a["+i+"]"+"//following::img[1]";
			MyWorkFlowTaskCommentBox= By.xpath(myWorkFlowTaskCommentBox);		
			myWorkFlowCheckBox="//a[text()='Complete']//following::a["+i+"]//preceding::input[1]";
			MyWorkFlowCheckBox=By.xpath(myWorkFlowCheckBox);
			CommonFunctions.waitForElementTobeClickable(MyWorkFlowTask);
			getTextOfTheTask= driver.findElement(MyWorkFlowTask).getText();
			if(getTextOfTheTask.contains(WorkFlowTask)){
				SanitySuite1.GettingText(MyWorkFlowTask);
				log.info("Task Name is "+SanitySuite1.ActualValue);
				TaskByReferingText="//a[text()='"+getTextOfTheTask+"']";
				TaskByReferingText_WebElement=By.xpath(TaskByReferingText);
				log.info("Work flow Task has been found");
				//if(data[95].equals("T1_Definition")){
				for(int s=7,j=2,k=0;s<=12;s++,k++){
				T1DefinitionOptionPath=TaskByReferingText+"//following::tr["+s+"]/td["+j+"]";
				T1DefinitionOption=By.xpath(T1DefinitionOptionPath);
				CommonFunctions.waitForElementTobeClickable(T1DefinitionOption);
				SanitySuite1.GettingText(T1DefinitionOption);
				CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[96+k], "Actual and expected values if T1Definition values are Not matched.Assertion failed.Please check");
				//verification Of First Option	
				//}
				}
				break;
				}}
	}
		catch(Exception e){
			fail=true;
			log.error("Exception in FindWorkFlowTask"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in FindWorkFlowTaskAndAssertionVerificationOfTask_T1_Definition"+er);
			throw er;
		}
	return true;
	}
	
	public static boolean FindWorkFlowTaskSecondTimeAndAssertionVerificationOf_T10_Definition(String [] data){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(TaskByReferingText_WebElement));
			for(int s=7,k=0;s<=8;s++,k++){
				T10DefinitionOptionPath=TaskByReferingText+"//following::tr["+s+"]/td[2]";
				T10DefinitionOption=By.xpath(T10DefinitionOptionPath);
				CommonFunctions.waitForElementTobeClickable(T10DefinitionOption);
				SanitySuite1.GettingText(T10DefinitionOption);
				CommonFunctions.AssertEqualsVerification(SanitySuite1.ActualValue, data[108+k], "Actual and expected values if T1Definition values are Not matched.Assertion failed.Please check");
				//verification Of First Option	
				}
				
				
		//}
			//}
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in FindWorkFlowTaskSecondTimeAndAssertionVerificationOf_T10_Definition"+e);
			throw e;
		}
		catch(AssertionError er){
			fail=true;
			log.error("Assertion Error in FindWorkFlowTaskSecondTimeAndAssertionVerificationOf_T10_Definition"+er);
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
	 
	 public static boolean UpdateBOM(String [] data) throws Exception{
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
				WorkFlowPreRequisite.fillCreateBOM(data);
				//Filling the BOM Details
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("Exception in UpdateBOM" +e);
			 throw e;
		 }
		 catch(AssertionError er){
				fail=true;
				log.error("Error in CreateProductFromLineSheet"+er);
				throw er;
			}
		 
		 return true;
	 }
	 
	
	
	
	 public static boolean selectTheCostSheet(String [] data) throws Exception{
		 try{
		
				 //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SortTable));
				 CommonFunctions.waitForElementTobeClickable(SanitySuite1.FirstSortDropDown);
				 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.FirstSortDropDown, data[58], "First Sort By DropDown");
				 //Clicking the Value On SortBy Drop down
				 CommonFunctions.waitForElementTobeClickable(SanitySuite1.SecondSortDropDown);
				 CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SecondSortDropDown, data[59], "Second Sort By DropDown");
				 //Clicking order on sort
				 CommonFunctions.waitForElementTobeClickable(SanitySuite1.SortButton);
				 CommonFunctions.clickButtonOrLink(SanitySuite1.SortButton, "Button", "Sort Button");
				 //Clicking Sort Button
				 CommonFunctions.waitForElementTobeClickable(SanitySuite1.SortOption);
				 wait.until(ExpectedConditions.titleIs(data[21]));
				 CommonFunctions.waitForPageLoaded();
				 SanitySuite1.CloseOpenedExistingCostSheet();
				 JavascriptExecutor jse = (JavascriptExecutor)driver;
			    jse.executeScript("window.scrollBy(0,200)", "");
			    wait.until(ExpectedConditions.visibilityOfElementLocated(CostSheetInAdminUser));
	           CommonFunctions.clickButtonOrLink(CostSheetInAdminUser, "HyperLink", "CostSheetInAdminUser");
				   //Clicking on CostSheet HyperLink
				    CommonFunctions.waitForPageLoaded();
		 }
		 catch(Exception e){
			 fail=true;
			 log.error("Exception in selectTheCostSheet"+e);
			 throw e;
		 }
		 return true;
	 }
	 
	 
	 
	 
	
	 
	
	 
	
	 
	
	
	 
	 
	
	
	
		
	 
	 
	
		
	
	
	 
	
	 
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	@BeforeMethod
	  public void Refresh(){
		  if(driver!=null && runmodes[count+1].equalsIgnoreCase("y")){
			  driver.navigate().refresh();
			  log.info("Driver is refreshed");
			}
		  else{
			  log.info("No Driver is Launched");
		  }
	  }


	@AfterMethod
	public void reporterdataSetResult(){
		if(skip){
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "SKIP");
			
		}
		else if(WorkFlowPreRequisite.fail||fail||CommonFunctions.error){
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "FAIL");
			  Utility.automatedDataSet(suiteSanityXls, this.getClass().getSimpleName(), count+2, "TEST CASE FAILED");
			
			/*isTestPass=false;
			skip=false;
			fail=false;
			CommonFunctions.error=false;
			WorkFlowPreRequisite.fail=false;*/
		
			
	}
		else{
			Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "PASS");
		    Utility.automatedDataSet(suiteSanityXls, this.getClass().getSimpleName(), count+2, "Created Assortment/Solid Product is "+WorkFlowPreRequisite.AssortmentProductNumberForWorkFlow+" and Created task is "+getTextOfTheTask);
	
		}
	}
	
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteSanityXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteSanityXls, this.getClass().getSimpleName());
	}
	
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"PASS");
			
		}else
			Utility.dataSetResult(suiteSanityXls,"Testcases", Utility.getRowNum(suiteSanityXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public  Object[] testDataTest(){
		return Utility.getData(suiteSanityXls, this.getClass().getSimpleName());
		
		
	}


}
