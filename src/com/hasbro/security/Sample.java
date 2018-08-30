package com.hasbro.security;
/**
 * @author Anjali Gupta
 *
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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



public class Sample extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	public static By sourcingTabLink= By.xpath("//a[contains(text(),'Sourcing')]");
	public static By sampleTabLink= By.xpath("//a[contains(text(),'Samples')]");
	public static By btnNewSample= By.xpath("//a[contains(text(),'New Sample')]");
	public static By headingCreateNewSample= By.xpath("//td[contains(text(),'Create New Sample')]");

	public static By txtRequestName= By.xpath("//td[contains(text(),'Request Name')]//following::input[1]");
	public static By txtRequestQuantity = By.xpath("//td[contains(text(),'Request Quantity')]//following::input[1]");
	public static By ddIPSensitive= By.xpath("//td[contains(text(),'IP Sensitive')]//following::select[1]");
	public static By txtPurposeofRequest = By.xpath("//td[contains(text(),'Purpose of Request')]//following::input[1]");
	public static By txtDateRequestPlaced= By.xpath("//td[contains(text(),'Date Request Placed')]//following::input[1]");
	public static By txtRequestedDeliveryDate= By.xpath("//td[contains(text(),'Requested Delivery Date')]//following::input[1]");
	public static By txtDateAllSamplesReceived = By.xpath("//td[contains(text(),'Date All Samples Received')]//following::input[1]");
	public static By checkLicensorReviewRequired = By.id("LCSSAMPLEREQUEST_ptc_bln_1box");
	public static By RO_labelRequestName= By.xpath("//a[contains(text(),'Actions')]//following::a[1]"); //td[contains(text(),'Request Name')]//following::td[1]
	public static By tableRow=By.xpath("//table[@class='TABLE_OUTLINE']/tbody/tr/td[2]");
	public static By ddActions=By.id("sampleRequestActions");
	public static By labelVendorAttri = By.xpath("//td[contains(text(),'Vendor Attributes')]");
	public static By labelSampleApprovals = By.xpath("//td[contains(text(),'Sample Approvals')]");
	public static By taVendorComments= By.xpath("//td[contains(text(),'Vendor Comments')]//following::textarea[1]");
	public static By engineeringStatus= By.xpath("//td[contains(text(),'Engineering Status')]//following::select[1]");


	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	static String strRequestName;
	static String strBillOfMaterials;
	static Boolean bCheckedOut=false;
	static Actions action;
	static String strRO_UpdateLifecycleState;
	static String strUpdate;
	static String RO_requestName;
	static String strDate;
	static String todayDate;
	static String strDelete;

	static String lastMonthDate;

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSample(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for Sample Security");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			System.out.println("col0 :" + data[0]);
			System.out.println("col1 :" + data[1]);
			System.out.println("col4 :" + data[4]);
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

			//Create Product from LineSheet
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_Sample(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_Sample(data); }
			//Read view verification
			if(data[3].equalsIgnoreCase("GeneralAttributesRead_View")||data[3].equalsIgnoreCase("VendorAttributesRead_View")||data[3].equalsIgnoreCase("SampleApprovalsRead_View"))
			{  verifyAttributesReadView(data); }
			//Update Verification
			if(data[3].equalsIgnoreCase("GeneralAttributesUpdate")||data[3].equalsIgnoreCase("VendorAttributesUpdate")||data[3].equalsIgnoreCase("SampleApprovalsReadUpdate"))
			{  verifyAttributesUpdate(data); }
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Sample(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
			//	driver.quit();
		}	
	}

	//Prerequisite: Specification should be created.previous specifications should not be available
	public static boolean Create_Sample(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[7]);
			//CreateSpecification
			String strSpec= CommonProjectFunctions.Create_Specifications(data[8],data[9]);
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Sourcing
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			if(CommonFunctions.waitForVisibilityOfElement(sourcingTabLink))
				CommonFunctions.clickButtonOrLink(sourcingTabLink, "link", "sourcing tab");
			//Click on Sample tab link
			CommonFunctions.clickButtonOrLink(sampleTabLink, "link", "Sample tab");
			//Select season
			CommonFunctions.enterTextInTextbox(Product.detailPageSeasonDD, data[7]);
			//Select source
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSource, data[9]);
			//Select Specification
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSpecification, strSpec);

			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
				strRequestName= fillSample(data);
				List<WebElement> tablerow = driver.findElements(tableRow);
				System.out.println(tablerow);
				for (int i=0;i<tablerow.size();i++)
				{	String RO_labelRequestName =tablerow.get(i).getText();	
				System.out.println(strRequestName);
				System.out.println(RO_labelRequestName);
				if(strRequestName.contains(RO_labelRequestName.trim())){
					Assert.assertEquals(strRequestName,RO_labelRequestName);
					break;
				}	
				}
			}
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals((driver.findElements(btnNewSample).size()), 0);
			}
			else{
				log.info("Create is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in Create_Sample()", e);
			return false;
		}
		return true;
	}

	public static String fillSample(String[] data) {
		try{	
			//Click on 'New Sample' button
			CommonFunctions.clickButtonOrLink(btnNewSample, "btn", "New Sample");
			CommonFunctions.waitForVisibilityOfElement(headingCreateNewSample);
			//Click on type 
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[2]+"']"), "link", "Sample Type");
			strRequestName = CommonFunctions.getRandomString(5);
			todayDate= CommonFunctions.getTodayDate();
			lastMonthDate = CommonFunctions.lastDayofMonth();
			//Enter Request name
			CommonFunctions.enterTextInTextbox(txtRequestName, strRequestName);
			//Enter Request Quantity 
			CommonFunctions.enterTextInTextbox(txtRequestQuantity, data[11]);
			//Select  IP Sensitive 
			CommonFunctions.enterTextInTextbox(ddIPSensitive, data[12]);
			//Enter Purpose of Request
			CommonFunctions.enterTextInTextbox(txtPurposeofRequest, data[13]);
			//Enter Date Request Placed
			//	CommonFunctions.enterTextInTextbox(txtDateRequestPlaced, "todayDate");
			//Enter Requested Deliver		
			CommonFunctions.enterTextInTextbox(txtRequestedDeliveryDate, lastMonthDate);
			//Enter Date All Samples Received 
			CommonFunctions.enterTextInTextbox(txtDateAllSamplesReceived, lastMonthDate);
			//Check Licenser Review Required
			if(data[14].equalsIgnoreCase("Yes"))
				CommonFunctions.selectCheckbox(checkLicensorReviewRequired);
			//Click create
			CommonFunctions.clickButtonOrLink(Product.createBtn, "btn", "Create");
			//Click on Save Button
			CommonFunctions.clickButtonOrLink(Product.SaveBtn, "Btn", "Save");

		}catch(Exception e){ 
			fail=true;
			SeleniumDriver.log.error("Exception in fillSample()", e);

		}
		return strRequestName;
	}

	public static boolean SetState_Sample(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[7]);
			//CreateSpecification
			String strSpec= CommonProjectFunctions.Create_Specifications(data[8],data[9]);
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Sourcing
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			if(CommonFunctions.waitForVisibilityOfElement(sourcingTabLink))
				CommonFunctions.clickButtonOrLink(sourcingTabLink, "link", "sourcing tab");
			//Click on Sample tab link
			CommonFunctions.clickButtonOrLink(sampleTabLink, "link", "Sample tab");
			//Select season
			CommonFunctions.enterTextInTextbox(Product.detailPageSeasonDD, data[7]);
			//Select source
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSource, data[9]);
			//Select Specification
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSpecification, strSpec);
			List<WebElement> tablerow = driver.findElements(tableRow);
			if(tablerow.size()>0)
			{
				for(int i=1;i<tablerow.size();i++){
					Thread.sleep(1000);
					String strSample=tablerow.get(i).getText(); //Picking first sample
					System.out.println(strSample);
					driver.findElement(By.linkText(strSample)).click();
					break;
				}
			}
			else{
				log.error("Sample not created");
				fail=true;
			}
			CommonFunctions.selectFromDropDownByVisibleText(ddActions, data[5]);
			String valULCSAfterChange=BOM_selectUpdateLifecycleState(data);
			Thread.sleep(1000);
			//Click on Update
			CommonFunctions.clickButtonOrLink(Product.linkUpdate, "link", "Update");
			//verification
			strRO_UpdateLifecycleState = driver.findElement(Color.RO_UpdateLifecycleState).getText();
			//	Assert.assertEquals(strRO_UpdateLifecycleState, valULCSAfterChange);
			if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
				Assert.assertEquals(strRO_UpdateLifecycleState, valULCSAfterChange);
			}
			else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
				Assert.assertNotEquals(strRO_UpdateLifecycleState, valULCSAfterChange);
			}
			else
				log.info("SetState or chageState is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in SetState_Sample()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_Sample(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[7]);
			//CreateSpecification
			String strSpec= CommonProjectFunctions.Create_Specifications(data[8],data[9]);
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Sourcing
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			if(CommonFunctions.waitForVisibilityOfElement(sourcingTabLink))
				CommonFunctions.clickButtonOrLink(sourcingTabLink, "link", "sourcing tab");
			//Click on Sample tab link
			CommonFunctions.clickButtonOrLink(sampleTabLink, "link", "Sample tab");
			//Select season
			CommonFunctions.enterTextInTextbox(Product.detailPageSeasonDD, data[7]);
			//Select source
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSource, data[9]);
			//Select Specification
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSpecification, strSpec);
			List<WebElement> tablerow = driver.findElements(tableRow);
			if(tablerow.size()>0)
			{
				for(int i=1;i<tablerow.size();i++){
					Thread.sleep(1000);
					String strSample=tablerow.get(i).getText(); //Picking first sample
					System.out.println(strSample);
					driver.findElement(By.linkText(strSample)).click();
					break;
				}
			}
			else{
				log.error("Sample not created");
				fail=true;
			}
			CommonFunctions.selectFromDropDownByVisibleText(ddActions, data[5]);
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
				CommonFunctions.handleAlertPopUp();
				strDelete= driver.findElement(PlaceHolderDevPlan.msgDelete).getText();
				Assert.assertEquals(strDelete, "Delete Successful"); 
			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddActions));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
				//	dropDownOptionVerification
				Assert.assertFalse(bVal);
			}
			else
				log.info("Delete is not applicable(NA)");
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in delete_Sample()", e);
			return false;
		}
		return true;
	}

	//Function consist scenario : General Attributes:Read_View
	public static boolean verifyAttributesReadView(String[] data) throws Exception{
		try{
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[7]);
			//CreateSpecification
			String strSpec= CommonProjectFunctions.Create_Specifications(data[8],data[9]);
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Sourcing
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			if(CommonFunctions.waitForVisibilityOfElement(sourcingTabLink))
				CommonFunctions.clickButtonOrLink(sourcingTabLink, "link", "sourcing tab");
			//Click on Sample tab link
			CommonFunctions.clickButtonOrLink(sampleTabLink, "link", "Sample tab");
			//Select season
			CommonFunctions.enterTextInTextbox(Product.detailPageSeasonDD, data[7]);
			//Select source
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSource, data[9]);
			//Select Specification
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSpecification, strSpec);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");

			List<WebElement> sampleList = driver.findElements(tableRow);
			for(int i=1;i<sampleList.size();i++){
				Thread.sleep(1000);
				String strSample=sampleList.get(i).getText(); //Picking first sample
				System.out.println(strSample);
				driver.findElement(By.linkText(strSample)).click();
				break;
			} 

			if(data[3].contains("GeneralAttributesRead_View")&& data[4].equalsIgnoreCase("Yes")){//GeneralAttributes
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					String GALabel=driver.findElement(Product.labelGeneralAttri).getText();
					System.out.println(GALabel);
					System.out.println(" General Attributes:");
					Assert.assertEquals(GALabel, " General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					log.error("General Attributes label is Absent");
					fail=true;
				}
			}
			else if(data[3].contains("GeneralAttributesRead_View")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(Product.labelGeneralAttri).size() != 0){
					System.out.println("General Attirbutes label is Present");
					log.error("General Attirbutes label is Present");
					fail=true;
				}else{
					log.info("General Attirbutes label is Absent");
									}
			}
			if(data[3].contains("VendorAttributesRead_View")&& data[4].equalsIgnoreCase("Yes")){//VendorAttributes
				if(driver.findElements(labelVendorAttri).size() != 0){
					String VALabel=driver.findElement(labelVendorAttri).getText();
					Assert.assertEquals(VALabel, " Vendor Attributes:");
					log.info("Vendor Attributes label is Present");
				}else{
					log.error("Vendor Attributes label is Absent");
					fail=true;
				}
			}
			else if(data[3].contains("VendorAttributesRead_View")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelVendorAttri).size() != 0){
					log.error("VendorAttributes label is Present");
					fail=true;
				}else{
					log.info("VendorAttributes label is Absent");
				}
			}
			if(data[3].contains("SampleApprovalsRead_View")&& data[4].equalsIgnoreCase("Yes")){//SampleApprovals
				if(driver.findElements(labelSampleApprovals).size() != 0){
					String SALabel=driver.findElement(labelSampleApprovals).getText();
					Assert.assertEquals(SALabel, " Sample Approvals:");
					log.info("Sample Approvals label is Present");
				}else{
					log.error("Sample Approvals label is Absent");
					fail=true;
				}
			}
			else if(data[3].contains("SampleApprovalsRead_View")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelSampleApprovals).size() != 0){
					System.out.println("SampleApprovals label is Present");
					log.error("SampleApprovals label is Present");
					fail=true;
				}else{
					log.info("SampleApprovals label is Absent");
				}
			}
			else
			{
				log.info("For this General Attributes,VendorAttributes and SampleApprovals is not applicable(NA)");
			}
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in verifyAttributesReadView()", e);
			return false;
		}
		return true;
	}
	//Function consist scenario : General Attributes://Update
	public static boolean verifyAttributesUpdate(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Specification
			CommonProjectFunctions.clickSpecificationTab(data[7]);
			//CreateSpecification
			String strSpec= CommonProjectFunctions.Create_Specifications(data[8],data[9]);
			CommonProjectFunctions.searchProduct(data[6]);
			//Click on Sourcing
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			if(CommonFunctions.waitForVisibilityOfElement(sourcingTabLink))
				CommonFunctions.clickButtonOrLink(sourcingTabLink, "link", "sourcing tab");
			//Click on Sample tab link
			CommonFunctions.clickButtonOrLink(sampleTabLink, "link", "Sample tab");
			//Select season
			CommonFunctions.enterTextInTextbox(Product.detailPageSeasonDD, data[7]);
			//Select source
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSource, data[9]);
			//Select Specification
			CommonFunctions.enterTextInTextbox(InternalBOMSoftG.selectSpecification, strSpec);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			List<WebElement> sampleList = driver.findElements(tableRow);
			for(int i=1;i<sampleList.size();i++){
				Thread.sleep(1000);
				String strSample=sampleList.get(i).getText(); //Picking first sample
				System.out.println(strSample);
				driver.findElement(By.linkText(strSample)).click();
				break;
			} 

			if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.selectFromDropDownByVisibleText(ddActions, data[5]);
				Thread.sleep(2000);
				Assert.assertEquals(driver.findElements(txtRequestQuantity).size(), 1, "Field is Editable"); 
			}
			else if(data[3].contains("GeneralAttributesUpdate")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddActions));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
				//dropDownOptionVerification
				Assert.assertFalse(bVal);
			}
			if(data[3].contains("VendorAttributesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.selectFromDropDownByVisibleText(ddActions, data[5]);
				Thread.sleep(2000);
				Assert.assertEquals(driver.findElements(taVendorComments).size(), 1, "Field is Editable"); 
			}
			else if(data[3].contains("VendorAttributesUpdate")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddActions));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
				//dropDownOptionVerification
				Assert.assertFalse(bVal);
			}	
			if(data[3].contains("SampleApprovalsReadUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				CommonFunctions.selectFromDropDownByVisibleText(ddActions, data[5]);
				Thread.sleep(2000);
				Assert.assertEquals(driver.findElements(engineeringStatus).size(), 1, "Field is Editable"); 
			}
			else if(data[3].contains("SampleApprovalsReadUpdate")&& data[4].equalsIgnoreCase("No")){
				Select select = new Select(driver.findElement(ddActions));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[5],options);
				//dropDownOptionVerification
				Assert.assertFalse(bVal);
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

	//This funcion is to select Update Lifecycle State	
	public static String BOM_selectUpdateLifecycleState(String[] data) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			if(valULCS.contains("In Work")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState,"Approved");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "Approved");
			}
			else if(valULCS.contains("Rejected")){
				//CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "In Work");
			}
			else if(valULCS.contains("Canceled")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "In Work");
			}
			else if(valULCS.contains("Approved")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "In Work");
			}
			else if(valULCS.contains("Approved with changes")){
				//	CommonFunctions.enterTextInTextbox(Product.Editable_UpdateLifecycleState, "In Work");
				CommonFunctions.selectFromDropDownByVisibleText(Product.Editable_UpdateLifecycleState, "In Work");
			}

			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			System.out.println("valULCS: "+valULCSAfterChange);
		}catch(Exception e){ 
			fail=true;
			log.error("Exception in selectUpdateLifecycleState()", e);
		}
		return valULCSAfterChange;
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
			return false;
		}
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
