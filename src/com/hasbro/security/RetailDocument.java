package com.hasbro.security;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

/**
 * @author sourabh.singh
 *
 */
public class RetailDocument extends TestsuiteBase{

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
	// Variable for Choose a type:
	public static By genearlDocumentType = By.xpath("//a[contains(text(),'General Document')and @class='LABEL']");
	public static By formalDocumentType = By.xpath("//a[contains(text(),'Formal Document')and @class='LABEL']");
	public static By templateDocumentType = By.xpath("//a[contains(text(),'Template Document')and @class='LABEL']");
	
	//Variable for Genearl Type Document
	public static By documentName= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By ipSensitive= By.xpath("//td[contains(text(),'IP Sensitive')]//following::select[1]");
	public static By securedforBrand= By.xpath("//td[contains(text(),'Secured for Brand')]//following::select[1]");
	public static By function= By.xpath("//td[contains(text(),'*Function')]//following::select[1]");
	public static By type = By.xpath("//td[contains(text(),'*Type')]//following::select[1]");
	public static By description= By.xpath("//td[contains(text(),'Description')]//following::input[1]");
	public static By designedForBrand = By.xpath("//td[contains(text(),'Designed for Brand')]//following::select[1]");
	public static By URL= By.xpath("//td[contains(text(),'URL')]//following::input[1]");
	public static By primaryDocumentFile = By.name("primaryDocumentFile");
	public static By comments = By.name("primaryDocumentComment");
	public static By crateDocument= By.xpath("//a[text()='Create']");
	
	//Variables for Image Pages - Concept Page:
	public static By category = By.xpath("//td[contains(text(),'*Category')]//following::select[1]");
	public static By pageDescription = By.xpath("//td[contains(text(),'*Page Description')]//following::textarea[1]");
	//Variable for Image Page Common Method
	public static By textLayout = By.xpath("//td[contains(text(),'Text Layout')]//following::select[1]");
	public static By pageLayout = By.xpath("//td[contains(text(),'*Page Layout')]//following::select[1]");
	public static By product = By.xpath("//a[contains(text(),'Product:')]");
	public static By productNumber = By.xpath("//*[@id='LCSPRODUCT_ptc_str_1']");
	public static By search= By.id("SearchButton1");
	public static By choose = By.xpath("//a[contains(text(),'choose')]");
	
	//Variable Required for Image Page - Specification Images Page:
	public static By pageType = By.xpath("//td[contains(text(),'*Page Type')]//following::select[1]");
	public static By pageDescription1 = By.xpath("//td[contains(text(),'*Page Description')]//following::input[1]");
	// For Image page upload
	public static By sourceDD = By.xpath("//select[@id='setInputMode1']");
	public static By documentReference = By.xpath("//a[contains(text(),'Document Reference 1:')]");
	public static By searchName = By.xpath("//input[@id='LCSDOCUMENT_ptc_str_1']");
	public static By saveDocument= By.xpath("//a[text()='Save']");
	public static By imagePage = By.xpath("//td[contains(text(),'Image Page')]");
	
	//Document Set state
	public static By Editable_UpdateLifecycleState= By.id("lcstate");
	public static By documentDetails = By.xpath("//td[contains(text(),'Document Details:')]");
	public static By documentsAction = By.xpath("//select[contains(@onchange,'evalList(this)')]");
	public static By RO_DocumentsUpdateLifecycleState = By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By linkUpdate= By.xpath("//a[text()='Update']");
	
	// Read and Update Attribute
	public static By labelGeneralAttribute = By.xpath("//td[contains(text(),'General Attributes')]");
	public static By headerAttribute = By.xpath("//*[@id='genDetails_plus']/a[1]/img");
	public static By downloadFileName = By.xpath("//td[contains(text(),'Primary File')]/following::a[1]");
	
	
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;
	static String deletemsg = "This action will completely delete this object from the system.  Are you sure you want to do this?";

	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcMaterial(String[] data) throws Exception{
		try{
			count++;
			System.out.println(runmodes[count]);
			if(!runmodes[count].equalsIgnoreCase("y")){
				skip=true;
				log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
				throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
			}
			log.debug("Inside testcase for  Retail Documents Security");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			log.info("col0 :" + data[0]);
			log.info("col2 :" + data[2]);
			log.info("col3 :" + data[3]);
			log.info("col4 :" + data[4]);
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

			//Create Retail Document and Images Pages From Libraries
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_Documents(data); }
			//Read view verification For General Attributes
			if(data[3].equalsIgnoreCase("GeneralAttirbutesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification For General Attributes
			if(data[3].equalsIgnoreCase("GeneralAttirbutesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			//download
			if(data[3].equalsIgnoreCase("Download"))
			{ download_Document(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ setState_Document(data); }
			//Delete Document
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Document(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
			log.info("Error occured in Assret Statement or skip the line as set as No for Execution!!!!");
		}	
	}

	//Prerequisite: Create Retail Document
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
			
			CommonFunctions.clickButtonOrLink(newLink, "link", "New Link");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(chooseaType));
			By attType = By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']");
			boolean newType=false;
			Packaging.isPresent(attType, newType);
			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.clickButtonOrLink(attType, "link", "Retail Document Type");
				log.info(By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']"));
			
				switch (data[2]) {
			    case "Formal Document":
								    	fillDocumentmandat(data);
								    	fillDocumentSubs(data);
										fillDocumentDetails(data);
										createDocument(data);
										break;
			    case "General Document":
								    	fillDocumentmandat(data);
								    	fillDocumentSubs(data);
										fillDocumentDetails(data);
										createDocument(data);
										break;
			    case "Template Document":
								    	fillDocumentmandat(data);
								    	fillDocumentSubs(data);
										fillDocumentDetails(data);
										createDocument(data);
										break;
			    case "Generated Tech Pack":
								    	fillDocumentmandat(data);
								    	fillDocumentSubs(data);
								    	createDocument(data);
										break;
			    case "Concept Image":
			    						fillDocumentmandat(data);
			    						fillConcept(data);
			    						fillImageData(data);
			    						createDocument(data);
			    						clickOnSave(data);
			    						break;
			    case "Specification Images Page":
								    	fillDocumentmandat(data);
								    	fillSpecImg(data);
										fillImageData(data);
										createDocument(data);
										clickOnSave(data);
										break;
			    default:
								    	fail=true;
								    	log.info("Default is executed");
					}
				
			}
			else if(data[3].contains("Create")&& data[4].equalsIgnoreCase("No")){
				Assert.assertFalse(newType, "New Link not available");
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
			CommonFunctions.selectFromDropDownByVisibleText(ipSensitive, data[6]);
			//CommonFunctions.selectFromDropDownByVisibleText(securedforBrand, data[7]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillDocumentmandat()", e);
			}
		}
	
	private static void fillDocumentSubs(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			Date date = new Date();
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(documentName));
			CommonFunctions.enterTextInTextbox(documentName,data[5]+date.getTime());
			CommonFunctions.enterTextInTextbox(primaryDocumentFile, data[13]);
			CommonFunctions.enterTextInTextbox(comments, data[14]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillDocumentSubs()", e);
			}
		}
	
	private static void fillDocumentDetails(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			CommonFunctions.selectFromDropDownByVisibleText(function, data[8]);
			driver.findElement(type).sendKeys(data[9]);
			log.info("Type is selected   :"+data[9]);
			//CommonFunctions.enterTextInTextbox(type, data[9]);
			CommonFunctions.enterTextInTextbox(description, data[10]);
			//CommonFunctions.selectFromDropDownByVisibleText(designedForBrand, data[11]);
			CommonFunctions.enterTextInTextbox(URL, data[12]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in Fill fillDocumentDetails()", e);
			}
		}
	
	private static void fillImageData(String[] data) {
		// TODO Auto-generated method stub
		try{
			CommonFunctions.selectFromDropDownByVisibleText(textLayout, data[16]);
			//CommonFunctions.enterTextInTextbox(pageDescription, data[17]);
			CommonFunctions.selectFromDropDownByVisibleText(pageLayout, data[18]);
			//Product Selection
			CommonFunctions.clickButtonOrLink(product, "link", "Product");
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.enterTextInTextbox(productNumber, data[19]);
			CommonFunctions.clickButtonOrLink(search, "Search For Product Number");
			CommonFunctions.clickButtonOrLink(choose, "Product selected");
			driver.switchTo().window(parent);
			driver.switchTo().frame("contentframe");
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillImageData()", e);
			}
		}
	
	private static void fillConcept(String[] data) {
		// TODO Auto-generated method stub
		try{
			CommonFunctions.selectFromDropDownByVisibleText(category, data[15]);
			CommonFunctions.enterTextInTextbox(pageDescription, data[17]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillImageData()", e);
			}
		}
	private static void fillSpecImg(String[] data) {
		// TODO Auto-generated method stub
		try{
			CommonFunctions.selectFromDropDownByVisibleText(pageType, data[15]);
			CommonFunctions.enterTextInTextbox(pageDescription1, data[17]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillImageData()", e);
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
	private static void clickOnSave(String[] data) {
		// TODO Auto-generated method stub
		try{
			CommonFunctions.selectFromDropDownByVisibleText(sourceDD, data[14]);
			//Document Selection
			CommonFunctions.clickButtonOrLink(documentReference, "link", "Document Reference");
			Set set = driver.getWindowHandles();
			Iterator iter = set.iterator();
			String parent = (java.lang.String) iter.next();
			String child = (java.lang.String) iter.next();
			driver.switchTo().window(child);
			CommonFunctions.enterTextInTextbox(searchName, data[13]);
			CommonFunctions.clickButtonOrLink(search, "Search For Document");
			CommonFunctions.clickButtonOrLink(choose, "Document selected");
			driver.switchTo().window(parent);
			driver.switchTo().frame("contentframe");
			CommonFunctions.clickButtonOrLink(saveDocument, "btn", "Save Image Documents");
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(imagePage));
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
			documentDetails(data);
			//Get the old value of Lifecycle:
			String textRO_currentLifecycleState = driver.findElement(RO_DocumentsUpdateLifecycleState).getText();
			log.info("textRO_currentLifecycleState value is :"+textRO_currentLifecycleState);
			//Get the values fom Action Drop down
			String str = driver.findElement(documentsAction).getText();
			//log.info("STR is---------"+str);
			//Select the Action Dropdown
			CommonFunctions.selectFromDropDownByVisibleText(documentsAction, data[21]);
			//Select select = new Select(driver.findElement(documentsAction));
			//select.selectByVisibleText(data[21]);
			//verification
			String valULCSAfterChange=selectUpdateLifecycleState(data);
			//Click on Update
			CommonFunctions.clickButtonOrLink(linkUpdate, "link", "Update");
			// called only when type is either concept image or Specification Image Page
			documentDetails(data);
			//Get the Lifecycle State Value
			String textRO_changedLifecycleState = driver.findElement(RO_DocumentsUpdateLifecycleState).getText();
			log.info("textRO_LifecycleState value is :"+textRO_changedLifecycleState);
			log.info("textRO_UpdateLifecycleState: "+textRO_changedLifecycleState);
			log.info("valULCSAfterChange: "+valULCSAfterChange);
			// If User is having Set State Permission
			if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
				Assert.assertEquals(textRO_changedLifecycleState, valULCSAfterChange);
			}
			// If User is not having Set State Permission
			else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
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
			//td[contains(text(),'Primary File')]/following::a[1]
			//a[contains(text(),'"+data[13]+"')]
			//Download data details //*[@id='TBLT12525']/tbody/tr[2]/td[1]/a
			String downloadData = driver.findElement(downloadFileName).getText();
			//String downloadData = driver.findElement(By.id("//a[contains(text(),'"+data[13]+"')]")).getText();
			System.out.println("downloadData--------------- :"+downloadData);
			//If User is having Download Permission
			if(data[3].contains("Download")&& data[4].equalsIgnoreCase("Yes")){
			//	CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[13]+"')]"), "link", " Download Data");
				Assert.assertEquals(downloadData, data[13]);
			}
			else if(data[3].contains("Download")&& data[4].equalsIgnoreCase("No")){
				log.info("No Download access");
				Assert.assertEquals(downloadData, data[13]);
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
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.enterTextInTextbox(documentsAction, data[21]);
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
				
			/*	
				//CommonFunctions.handleAlertPopUp();
				String deleteMess =driver.findElement(deleteSucessful).getText();
				Assert.assertEquals(deleteMess,"Delete Successful");*/
				
				
			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(documentsAction).getText();
				//System.out.println(strPrimary.trim());
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[21]);
				Assert.assertFalse(val);
				/*Select select = new Select(driver.findElement(documentsAction));
				List<WebElement> options = select.getOptions();
				boolean bVal=CommonFunctions.dropDownOptionVerification(data[21],options);
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
			valULCS = new Select(driver.findElement(Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			//	System.out.println("valULCS: "+valULCS);
			//For Formal Document
			if (productData[2].equalsIgnoreCase("Formal Document")) {
						
						if(valULCS.contains("In Work")){
							//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Rework");
						}
						else if(valULCS.contains("Under Review")){
							//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Rework");
						}
						else if(valULCS.contains("Released")){
							//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work");
						}
						else if(valULCS.contains("Rework")){
							//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work");
						}
						else if(valULCS.contains("Canceled")){
							//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work");
						}
				}
			if (productData[2].equalsIgnoreCase("General Document") || productData[2].equalsIgnoreCase("Template Document")
					|| productData[2].equalsIgnoreCase("Specification Images Page") || productData[2].equalsIgnoreCase("Concept Image")) {
						
						if(valULCS.contains("In Work")){
							//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released");
						}
						else if(valULCS.contains("Vaulted")){
							//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Canceled");
						}
						else if(valULCS.contains("Released")){
							//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Vaulted");
						}
						else if(valULCS.contains("Canceled")){
							//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work");
						}
				}
			if(productData[2].equalsIgnoreCase("Generated Tech Pack")){
				
						if(valULCS.contains("In Work")){
							//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Under Review");
						}
						else if(valULCS.contains("Released")){
							//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work");
						}
						else if(valULCS.contains("Under Review")){
							//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
							CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released");
						}
				}
			
			valULCSAfterChange = new Select(driver.findElement(Product.Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			//System.out.println("valULCS: "+valULCSAfterChange);
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
			documentDetails(data);
			if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
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
			else if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					log.info("General Attirbutes label is supposed to be not Present, But its present!!!!");
					log.error("General Attirbutes label is Present");
				}else{
					log.error("General Attributes label is Absent");
					fail=true;
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
			if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				String strPrimary =driver.findElement(documentsAction).getText();
				//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailsTablink));
				//log.info("strPrimary  after trim     :"+strPrimary.trim());
				Assert.assertEquals(SourcingConfig.findString(strPrimary.trim(), data[21]), true);
			}
			else if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("No")){
				
				String strPrimary =driver.findElement(documentsAction).getText();
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[21]);
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
	
		//Function consist scenario : searchMaterial
		public static boolean searchDocument(String[] data) throws Exception{
			try{
				driver.navigate().refresh();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(libraryLink, "Link", "Library Link");
				//Click on Retail Document link
				driver.findElement(retailDocLink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(retailDocHeading));
				//Add name
				CommonFunctions.enterTextInTextbox(searchName, data[20]);
				//Click on Search
				CommonFunctions.clickButtonOrLink(search, "Btn", "Search");
			if (data[2].equalsIgnoreCase("Formal Document") || data[2].equalsIgnoreCase("General Document")
					|| data[2].equalsIgnoreCase("Template Document") || data[2].equalsIgnoreCase("Generated Tech Pack")) {
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(documentDetails));
				}
				
			}catch(Exception e){
				fail=true;
				log.error("Exception in searchDocuments()", e);
				return false;
			}
			return true;
		}
		
		//Function consist scenario : search Document
		public static boolean documentDetails(String[] data) throws Exception{
			try{
				if(data[2].equalsIgnoreCase("Concept Image") || data[2].equalsIgnoreCase("Specification Images Page")){
					wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(imagePage));
					driver.findElement(headerAttribute).click();
					log.info(data[2]+" is  Concept Image OR Specification Images Page !!!!");
					
					}
					else{log.info(data[2]+" is neithther Concept Image nor Specification Images Page !!!!");}
			}catch(Exception e){
				fail=true;
				log.error("Exception in documentDetails()", e);
				return false;
			}
			return true;
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
