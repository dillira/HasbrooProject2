
package com.hasbro.views;
/**
 * @author Govind Pandey
 * 
 * Create Retail Item product and Assortment Product
 * Both the above product must have colorway, Funskool Source, specification created
 * In Excel Column 2 is created to mention that cost sheet type is Internal or Vendor
 * Ensure that in excel sheet Column 8 has "Hasbro Internal" source for Internal Cost Sheet and "002 : FUNSKOOL" source for vendor cost sheet
 * Since Product and Retail cost sheet differs with mandatory Wave attribute, so in excel column 14 is created to handle it.
 *
 */

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.hasbro.security.CostsheetExternalProduct;
import com.hasbro.security.CostsheetInternal;
import com.hasbro.security.CostsheetTooling;
import com.hasbro.security.InternalBOMSoftG;
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;


public class CostSheetView extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	static String strCSInWork;
	static int cwi;
	static String strTestCaseName = null;
	public static By tblCostSheetIdentification = By.xpath("//td[contains(text(),'Cost Sheet Identification')]");

	
	static int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String csInWork;
	static String strViewOwner;
	static String strSpec;
	static String strCW;
	static String strCostSheetName;
	public static Boolean flagVal=true;
	static int i;
	
	public static By csHeading=By.xpath("//div[@id='costSheetResults']/table/tbody/tr[1]/td");
	public static By tabCostsheet =By.xpath("//a[text()='Cost Sheet List']");
	public static By selectSource= By.id("sourcingConfigId");
	public static By lstcostingActions = By.id("costingActions");	
	public static By lstcolorwayGroupOptions = By.id("colorwayGroupOptions");	
	public static By lnkAdd =  By.xpath("//a[text()='Add']");
	public static By lstWave = By.xpath("//td[contains(text(),'Wave')]//following::select[1]");
	public static By lstQuoteCurrency = By.xpath("//td[contains(text(),'Quote Currency')]//following::select[1]");
	public static By btnSave =  By.xpath("//a[text()='Save']");  
	public static By viewIcon = By.xpath("//img[contains(@src,'view.png')]");
	public static By updateTableLayout = By.xpath("//img[contains(@src,'customize_tablebutton.gif')]");
	public static By viewOwner = By.xpath("//td[contains(text(),'View Owner')]//following::td[1]");
	public static By updateTableLayoutHidden = By.xpath("//div[@id='updateViewDiv' and @style='display: none;']//img[contains(@src,'customize_tablebutton.gif')]");
	public static By csCostSheetList = By.xpath("//a[@title='Cost Sheet List']");
	public static By csName = By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By imgClose =By.xpath("//img[contains(@src,'deleteXsmall.png')]");
	public static By viewupdatepage = By.xpath("//td[contains(text(),'Update Search Preference')]");

	@Test(dataProvider="testDataTest")
	public void tcView(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			strTestCaseName = "User:"+data[0] + " for testcase:"+data[3]+" for Cost Type: "+data[2] ;			
			log.info("Inside Test Case:-> " + strTestCaseName);

			log.info("col0 :" + data[0]);
			log.info("col2 :" + data[2]);
			log.info("col3 :" + data[3]);
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

			switch (data[3]) {
			case "CreateIntCostSheet":
				log.info("In side :-> " + data[3]);	
				CreateIntCostSheet_inwork(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Eng Retail Item Cost View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Vendor Retail Item Cost View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Retail Item - Cost View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "(-- None --)":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Eng Asst Cost Sheet View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Ast Cost Sheet View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;
			case "Vendor Asst Cost Sheet View":
				log.info("In side :-> " + data[3]);	
				verifyBOMView(data);
				log.info("Out side :-> " + data[3]);
				break;

			default:
				fail=true;
				log.info("Default is executed");
			}
		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}
	/**
	 * Create cost sheet
	 */
	public static String CreateIntCostSheet_inwork(String[] data) throws Exception{
		try{
			//Create cost sheet
			createCS(data);
			//read newly created cost sheet
			CommonFunctions.waitForVisibilityOfElement(tblCostSheetIdentification);
			//Get the Name of cost sheet created
			strCSInWork = driver.findElement(CostsheetTooling.csHeading).getText().substring(20);
			//Remove the Action text from cost sheet name
			String []varCSInWork = strCSInWork.split("Actions:");
			//trim the name of the cost Sheet
			csInWork = varCSInWork[0].trim();
			log.info("Internal Retail Item/Product Cost Sheet in Inwork status is : "+csInWork);
			// close the open cost sheet
			closeTheOpenCostSheet();
		}catch(Exception e){
			fail=true;
			log.error("Exception in CreateIntCostSheet_inwork()", e);
			return "";
		}
		return csInWork;
	}
	
	public static boolean closeTheOpenCostSheet() throws Exception
	{
		try{
			//Wait for the visibility of cost sheet close button
			CommonFunctions.waitForVisibilityOfElement(imgClose);
			// close the open cost sheet
			CommonFunctions.clickButtonOrLink(imgClose,"icon", "Close");
			log.info("Costsheet closed");
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in closeTheOpenCostSheet", e);
			return false;
		}
	}

	
	public static Boolean createCS(String[] data) throws Exception{
		try{
				//Search product
				CommonProjectFunctions.searchProduct(data[6]);
				//Click on Sourcing Tab
				CommonProjectFunctions.clickSourcingTab(data[7]);
				//Click on Costing Tab
				CommonProjectFunctions.clickCostingTab();
				//Select Source
				CommonFunctions.selectFromDropDownByVisibleText(InternalBOMSoftG.selectSource, data[8]);
				//Select Specification
				strSpec=InternalBOMSoftG.AddSpecification(data);
				//Select Colorway
				strCW=InternalBOMSoftG.AddColorway(data);

			//Click on Cost Sheet List
			CommonFunctions.clickButtonOrLink(csCostSheetList, "tab", "Cost Sheet List");
			// select Create cost sheet action					
			CommonFunctions.selectFromDropDownByVisibleText(CostsheetTooling.lstcostingActions,"Create Cost Sheet"); 

			// select tooling type
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"),"link","Cost sheet type");

			// select colorwayGroupOptions
			CommonFunctions.selectFromDropDownByIndex(CostsheetTooling.lstcolorwayGroupOptions,0);
			// click lnkAdd
			CommonFunctions.clickButtonOrLink(lnkAdd,"lnk","lnkAdd");	
			if(data[14].contains("Product") || data[2].contains("Vendor")) {
				// select wave
				CommonFunctions.selectFromDropDownByVisibleText(lstWave,data[13]);
			}
			// select QuoteCurrency
			CommonFunctions.selectFromDropDownByVisibleText(lstQuoteCurrency,data[11]);
			if(data[2].equalsIgnoreCase("Internal")) {
				CommonFunctions.enterTextInTextbox(csName, data[12]);
			}
			// click on Save button
			CommonFunctions.clickButtonOrLink(btnSave,"btn","btnSave");
		}catch(Exception e){
			fail=true;
			log.error("Exception in createCS()", e);
			return false;
		}
		return true;
	}
	
	
	
	public static boolean clickOnCostSheetListTab() throws Exception
	{
		try{
			//Click on Cost Sheet Tab
			driver.findElement(tabCostsheet).click();   
			return true;
		}catch(Exception e){
			fail=true;
			log.error("Exception in clickOnCostSheetListTab()", e);
			return false;
		}
	}
	
	public static String searchandClickforRequriedCostsheet(String costSheetName) throws Exception
	{
		try{
			int colIndex=1;
			//Get the list of cost sheets
			List<WebElement> col1 = driver.findElements(By.xpath("//div[@id='costSheetResults']//div[3]/table/tbody/tr/td[2]"));
			System.out.println(col1.size());
			//Get the name of the cost sheet from cost sheet list
			for(WebElement e: col1){
				colIndex=colIndex+1;
				System.out.println("e.getText(): "+ e.getText());
				System.out.println("Segment: "+costSheetName);
				//trim the name of the cost sheet and get th correct cost sheet which is matching
				if (e.getText().trim().equals(costSheetName)) {
					String strCostSheetName = e.getText().trim();
					driver.findElement(By.linkText(costSheetName)).click();
					return strCostSheetName;
				}
			}

		}catch(Exception e){
			fail=true;
			log.error("Exception in searchandClickforRequriedCostsheet()", e);
			return "";
		}
		return strCostSheetName;
	}
	
	public static void verifyBOMView(String[] data) throws Exception{
		try{
			
			//Browse to Costing
			CostsheetInternal.nevigationToCostsheet(data);
			
			if(data[3].equalsIgnoreCase("(-- None --)")){
				
				//Verify BOM VIew
				verifySequence(data);
				//Verify User group
				verifyUserGroup(data);				
			}
			
			else{
			
			//Select view type
			CommonFunctions.clickButtonOrLink(viewIcon, "icon", "View");
			CommonFunctions.clickButtonOrLink(By.xpath("//a[text()='"+data[3]+"']"), "option", data[3]);
			
			//Click on update table layout icon
			CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
			
			//switch to default frame
			driver.switchTo().defaultContent();
			
			//switch to content frame
			driver.switchTo().frame("contentframe");
			
			//Wait for View Update page
			CommonFunctions.waitForVisibilityOfElement(viewupdatepage);
			
			//Click on Cancel
			CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
			
			
			verifySequence(data);
			verifyUserGroup(data);
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in "+data[3], e);
		}
	}
	
	/*
	 * Below function verify Sequencing for View Type
	 */
	public static boolean verifySequence(String[] data) throws Exception{
		try {
			int i=15;
			int j=1;
			//Get the list of column header for selected view
			List<WebElement> col1 = driver.findElements(By.xpath("//a[contains(text(),'Hide/Show columns')]//following::table[4]/tbody/tr[1]/td/a[2]"));
			System.out.println(col1.size());
			//Match the view header in UI with the actual data from sheet
			for(WebElement e: col1){
				System.out.println("UI data: "+ e.getText());
				System.out.println("excel data: "+data[i]);
				if(data[i].equalsIgnoreCase("NA")){
					break;
				}
				//Verify that view in UI is matching with the actual data from sheet
				Assert.assertEquals(e.getText().trim(), data[i]);
				log.info("Col"+j+" :matched for "+ e.getText());
				i++;
				j++;
			}
			log.info("Sequence verified for: "+ data[3]+" for Cost Type: "+data[2]);
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifySequence()", e);
			return false;
		}
		return true;
	}
	public static boolean verifyUserGroup(String[] data) throws Exception{
		try {
			if(!data[3].equalsIgnoreCase("(-- None --)")) {
			//Click on update table layout icon
			CommonFunctions.clickButtonOrLink(updateTableLayout, "Icon", "update table layout");
			//Get the name of the View Owner group
			strViewOwner = driver.findElement(viewOwner).getText();
			//Verify that the View Owner group in UI is matching with the actual
			Assert.assertEquals(strViewOwner, data[4]);
			log.info("Owner Group verified for : "+strTestCaseName);
			//Click on Cancel
			CommonFunctions.clickButtonOrLink(CostsheetExternalProduct.btnCancel, "btn", "Cancel");
			}
			else {
				//Verify that the Update Table Layout button is hidden 
				Assert.assertEquals(driver.findElements(updateTableLayoutHidden).size(),1);
				log.info("For None Update icon is not present");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyUserGroup()", e);
			return false;
		}
		return true;
	}

	
	public static boolean bFlagVal(String[] productData) throws Exception{
		try{
			if (flagVal)
			{ return true;}
			else{
				fail=true;
				log.info("Cascading fail for Class:" +productData[10] + " Division : "+productData[11]+" Brand: "+productData[12]+" Segment: " +productData[13]);
				Assert.fail("Cascading not working");
				return false;
			}
		}catch(Exception e){  
			fail=true;
			log.error("Exception in bFlagVal()", e);
			return false;
		}
	}

	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteViewsXls, this.getClass().getSimpleName(), count+2, "PASS");
		skip=false;
		fail=false;
	}
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{

		if(!Utility.isCaseRunnable(suiteViewsXls, this.getClass().getSimpleName())){
			log.debug("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+" as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteViewsXls, this.getClass().getSimpleName());
	}
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteViewsXls,"Testcases", Utility.getRowNum(suiteViewsXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteViewsXls,"Testcases", Utility.getRowNum(suiteViewsXls, this.getClass().getSimpleName()),"FAIL");

	}

	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteViewsXls, this.getClass().getSimpleName());
	}

}

