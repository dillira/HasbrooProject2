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

public class MaterialOld extends TestsuiteBase{

	String runmodes[]=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	static WebDriverWait wait=null;
	
	//Variable required before Create Page
	public static By libraryLink = By.id("librariesContentIcon");
	public static By materialLink = By.linkText("Material");
	public static By materialHeadning = By.xpath("//span[contains(text(),'Find: Material')]");
	public static By newLink = By.linkText("New");
	public static By chooseaType = By.xpath("//td[contains(text(),'Choose a Type')]");
	//public static By fabricType = By.xpath("//a[contains(text(),'Fabric')and @class='LABEL']");
	//public static By trimType = By.xpath("//a[contains(text(),'Trip ')and @class='LABEL']");
	//Variable for Create Page:
	public static By materialName= By.xpath("//td[contains(text(),'*Name')]//following::input[1]");
	public static By constructionType= By.xpath("//td[contains(text(),'Construction Type')]//following::select[1]");
	public static By parentRefNumber= By.xpath("//td[contains(text(),'Parent Reference Number')]//following::input[1]");
	public static By construction = By.id("ptc_str_20"); 
	public static By secondaryConstruction= By.id("ptc_str_11");
	//public static By secondaryConstruction= By.xpath("//td[contains(text(),'Secondary Construction')]//following::select[1]");
	public static By cBlack= By.xpath("//*[@id='ptc_str_17Options']/option[1]");
	public static By crADD= By.xpath("//*[@class='LABEL']/a[text()='Add']");
	public static By content = By.xpath("//td[contains(text(),'*Content Percent')]//following::select[1]"); 
	public static By percent = By.xpath("//td[contains(text(),'*Content Percent')]//following::input[2]"); 
	public static By cpADD = By.xpath("//*[@class='LABEL']/a[text()='Add']/following::a[4]"); 
	public static By weightGYD= By.xpath("//td[contains(text(),'*Weight (g/yd)')]//following::input[1]");
	public static By pileHeightmm= By.xpath("//td[contains(text(),'Pile Height (mm)')]//following::input[1]");
	public static By coatingPU= By.xpath("//*[@id='ptc_str_19Options']/option[1]");  
	public static By coatingADD= By.xpath("//*[@class='LABEL']/a[text()='Add']/following::a[6]");  
	public static By finishesAntiPilling= By.xpath("//option[@value='hbAntiPilling']");  
	public static By finishesADD= By.xpath("//*[@class='LABEL']/a[text()='Add']/following::a[8]");
	public static By printTypeBacksidePrint= By.xpath("//option[@value='hbBacksidePrint']");  
	public static By printTypeADD= By.xpath("//*[@class='LABEL']/a[text()='Add']/following::a[10]");
	public static By description = By.id("ptc_str_10");
	public static By comments = By.id("ptc_str_12");
	public static By crateMaterial= By.xpath("//a[text()='Create']");
	
	// For Trim Types: 
	public static By trimType= By.xpath("//td[contains(text(),'*Trim Type')]//following::select[1]");
	public static By size= By.xpath("//td[contains(text(),'Size')]//following::input[1]");
	public static By coatings= By.xpath("//td[contains(text(),'Coatings')]//following::select[1]");
	public static By sizeUOM= By.xpath("//td[contains(text(),'Size UOM')]//following::select[1]");
	public static By construction1Type= By.xpath("//*[@id='ptc_str_19Options']/option[1]");  
	public static By constructionADD= By.xpath("//*[@class='LABEL']/a[text()='Add']/following::a[4]"); 
	public static By content1 = By.xpath("//td[contains(text(),'*Content Percent')]//following::select[1]"); 
	public static By percent1 = By.xpath("//td[contains(text(),'*Content Percent')]//following::input[2]"); 
	public static By cpADD1 = By.xpath("//*[@class='LABEL']/a[text()='Add']/following::a[6]"); 
	public static By printType= By.xpath("//*[@id='ptc_str_16Options']/option[1]");  
	public static By printADD= By.xpath("//*[@class='LABEL']/a[text()='Add']/following::a[8]");
	public static By finishes= By.xpath("//*[@id='ptc_str_15Options']/option[1]");  
	public static By finishes1ADD= By.xpath("//*[@class='LABEL']/a[text()='Add']/following::a[10]");
	
	// materialSupplierActions
	public static By materialAction = By.id("materialSupplierActions");
	public static By inputBoxName = By.xpath("//td[contains(text(),'Name')]//following::input[1]");
	public static By btnSearch = By.id("SearchButton2");
	public static By materialIdentification = By.xpath("//td[contains(text(),'Material Details:')]");
	
	// Read and Update Attribute
	public static By labelGeneralAttribute = By.xpath("//td[contains(text(),'General Attributes')]");
	//public static By labelMaterialDeatils = By.xpath("//a[@name='Material Details']");
	public static By labelMaterialDeatils = By.xpath("//*[@id='materialSpace']/table/tbody/tr/td/div[2]/div[2]/div[1]/table/tbody/tr/td[2]");
	
	public static By linkUpdate= By.xpath("//a[text()='Update']");
	public static By systemTablink= By.xpath("//a[contains(text(),'System')]");
	public static By Editable_UpdateLifecycleState= By.id("lcstate");
	public static By RO_MaterialUpdateLifecycleState = By.xpath("//td[contains(text(),'Lifecycle State')]//following::td[1]");
	public static By pricingChart = By.xpath("//td[contains(text(),'Pricing Chart:')]");
	public static By viewMOA = By.xpath("//a[contains(@href,'hbResinPricingChart') and contains(text(),'View')]");
	public static By editMOA = By.xpath("//a[contains(@href,'hbResinPricingChart') and contains(text(),'Edit')]");
	
	int y=0;
	String loginVal;
	Boolean flaglogin=false;
	static String valULCS;
	static String valULCSAfterChange;

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
			log.debug("Inside testcase for  Material Security");
			//	log.debug(login+"--"+pwd+"--"+AttributeGroup+"--"+Verification+"--"+Create+"--"+SetState+"--"+ReadView+"--"+UpdateProduct+"--"+UpdateProductSeason+"--"+Delete);
			log.info("User Name     :" + data[0]);
			log.info("Material Type :" + data[2]);
			log.info("Verification  :" + data[3]);
			log.info("Action        :" + data[4]);
			//	driver.manage().timeouts().pageLoadTimeout(myAutomationWait, TimeUnit.SECONDS);
			if(flaglogin==true)
			{
				if(!loginVal.equalsIgnoreCase(data[0])){
					y=0;
					flaglogin=false;
					//log.info("user is abt to logged out...");
					CommonProjectFunctions.logOut();
					//log.info("user is logged out.");
					driver.quit();
				}
			}
			if(runmodes[count].equalsIgnoreCase("y")){
				if(y==0){
					//log.info("new user is coming..");
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					log.info("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}

			//Create Material From Libraries
			if(data[3].equalsIgnoreCase("Create"))
			{ Create_Material(data); }
			//SetState
			if(data[3].equalsIgnoreCase("SetState"))
			{ SetState_Materail(data); }
			//Read view verification For General Attributes
			if(data[3].equalsIgnoreCase("GeneralAttirbutesRead_View"))
			{  verifyGeneralAttributesReadView(data); }
			//Update Verification For General Attributes
			if(data[3].equalsIgnoreCase("GeneralAttirbutesUpdate"))
			{  verifyGeneralAttributesUpdate(data); }
			if(data[3].equalsIgnoreCase("MaterialDeatilsRead_View"))
			{  verifyMaterialDtlsReadView(data); }
			//Update Verification For General Attributes
			if(data[3].equalsIgnoreCase("MaterialDeatilsUpdate"))
			{  verifyMaterialDtlsUpdate(data); }
			//Delete Product
			if(data[3].equalsIgnoreCase("Delete"))
			{ delete_Material(data); }
			//Read view verification For Pricing Chart
			if(data[3].equalsIgnoreCase("PricingChart_View"))
			{  verifyPricingChartView(data); }
			//Update verification For Pricing Chart
			if(data[3].equalsIgnoreCase("PricingChart_Edit"))
			{  verifyPricingChartUpdate(data); }

		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}	
	}

	//Prerequisite: Create Product
	public static boolean Create_Material(String[] data) throws Exception{
		try{
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.switchTo().frame("sidebarframe");
			// Click on Libraries
			CommonFunctions.clickButtonOrLink(libraryLink, "Link", "Library Link");
			//Click on Color link
			driver.findElement(materialLink).click();
			//Switch frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			wait = new WebDriverWait(driver, 10);
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(materialHeadning));
			//Checking and Validating New Button on Material Search Page
			boolean newType=false;
			Packaging.isPresent(newLink, newType);
			if(data[3].contains("Create")&& data[4].equalsIgnoreCase("Yes")){
				//Click on new
				CommonFunctions.clickButtonOrLink(newLink, "link", "New Link");
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(chooseaType));
				//Click on color type
				CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']"), "link", "Material Type");
				System.out.println(By.xpath("//a[contains(text(),'"+data[2]+"')and @class='LABEL']"));
				
				switch (data[2]) {
			    case "Fabric":
								    	fillMaterailMandatory(data);
								    	fillMatFabric(data);
										createMaterial(data);
										break;
			    case "Trim":
			    						fillMaterailMandatory(data);
										fillMatTrim(data);
										createMaterial(data);
										break;
			    case "Resin":
										mandatoryData(data);
										createResin(data);
										createMaterial(data);
										break;
			    case "Labor":
										mandatoryData(data);
										createLabor(data);
										createMaterial(data);
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
			log.error("Exception in Create_Material()", e);
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
	// Method is used for Fill Mandatory Data for Resin and Labor Material Type.
	private static boolean mandatoryData(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			Date date = new Date();
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(materialName));
			CommonFunctions.enterTextInTextbox(materialName,data[5]+date.getTime());
			//Search for Season from POPUP Page
			CommonFunctions.clickButtonOrLink(Season.seasonLink, "link", "Season");
			Set set1 = driver.getWindowHandles();
			Iterator iter1 = set1.iterator();
			String parent1 = (java.lang.String) iter1.next();
			String child1 = (java.lang.String) iter1.next();
			driver.switchTo().window(child1);
		//	CommonFunctions.enterTextInTextbox(PartMgmtFunctional.seasonNumber, data[9]);
		//	CommonFunctions.clickButtonOrLink(PartMgmtFunctional.search, "Search For Season");
		//	CommonFunctions.clickButtonOrLink(PartMgmtFunctional.choose, "Season selected");
			driver.switchTo().window(parent1);
			driver.switchTo().frame("contentframe");
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in mandatoryData()", e);
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
	private static boolean createResin(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			//Select processing Method Type From drop down
		//	CommonFunctions.selectFromDropDownByVisibleText(PartMgmtFunctional.processingMethod, data[6]);
			
			// Send Resin Description Description Details
		//	CommonFunctions.enterTextInTextbox(PartMgmtFunctional.resinDescription, data[7]);
			
			// Send Density (gr/gm2) Description Details
		//	CommonFunctions.enterTextInTextbox(PartMgmtFunctional.density, data[8]);
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
		//	CommonFunctions.selectFromDropDownByVisibleText(PartMgmtFunctional.operationType, data[6]);
			
			// Send Labor Description  Details
		//	CommonFunctions.enterTextInTextbox(PartMgmtFunctional.laborDescription, data[7]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in createLabor()", e);
				return false;
			}
			return true;
		}
	
	static void fillMaterailMandatory(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			Date date = new Date();
			wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(materialName));
			CommonFunctions.enterTextInTextbox(materialName,data[5]+date.getTime());
			
			CommonFunctions.enterTextInTextbox(parentRefNumber, data[6]);
			driver.findElement(cBlack).click();
			log.info("cBlack is Selected!!!");
			driver.findElement(crADD).click();
			log.info("crADD is Selected!!!");
			// Send Material Description Details
			CommonFunctions.enterTextInTextbox(description, data[19]);
			// Send Comments for Creating Materials Details
			CommonFunctions.enterTextInTextbox(comments, data[20]);
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillMaterailMandatory()", e);
			}
		}
	
	private static void fillMatFabric(String[] data) throws Exception {
		// TODO Auto-generated method stub
		try{
			//Select Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleText(constructionType, data[7]);
			//Select Secondary Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleText(secondaryConstruction, data[8]);
			//Select Construction Type From drop down
			CommonFunctions.selectFromDropDownByVisibleText(construction, data[9]);
			//Select Content From drop down
			CommonFunctions.selectFromDropDownByVisibleText(content, data[10]);
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(percent, data[11]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(cpADD, "btn", "cpADD");
			//Select Content From drop down
			CommonFunctions.selectFromDropDownByVisibleText(content, data[12]);
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(percent, data[13]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(cpADD, "btn", "cpADD");
			// Send Weight
			CommonFunctions.enterTextInTextbox(weightGYD, data[14]);
			//Send Pile Height
			CommonFunctions.enterTextInTextbox(pileHeightmm, data[15]);
			//Caoting PU
			driver.findElement(coatingPU).click();
			log.info("coatingPU is Selected!!!");
			CommonFunctions.clickButtonOrLink(coatingADD, "btn", "coatingADD");
			//Finishes Anti Pilling
			driver.findElement(finishesAntiPilling).click();
			log.info("finishesAntiPilling is Selected!!!");
			CommonFunctions.clickButtonOrLink(finishesADD, "btn", "finishesADD");
			//Print Type Back Side Print
			driver.findElement(printTypeBacksidePrint).click();
			log.info("printTypeBacksidePrint is Selected!!!");
			CommonFunctions.clickButtonOrLink(printTypeADD, "btn", "printTypeADD");
			
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
			CommonFunctions.selectFromDropDownByVisibleText(trimType, data[16]);
			//Send Size
			CommonFunctions.enterTextInTextbox(size, data[15]);
			//Select Coating
			CommonFunctions.selectFromDropDownByVisibleText(coatings, data[17]);
			CommonFunctions.selectFromDropDownByVisibleText(sizeUOM, data[18]);
			//Construction Type
			driver.findElement(construction1Type).click();
			log.info("construction Type is Selected!!!");
			CommonFunctions.clickButtonOrLink(constructionADD, "btn", "constructionADD");
			//Select Content From drop down
			CommonFunctions.selectFromDropDownByVisibleText(content1, data[10]);
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(percent1, data[11]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(cpADD1, "btn", "cpADD1");
			//Select Content From drop down
			CommonFunctions.selectFromDropDownByVisibleText(content1, data[12]);
			// Send Content Percent values
			CommonFunctions.enterTextInTextbox(percent1, data[13]);
			//Click On Add button
			CommonFunctions.clickButtonOrLink(cpADD1, "btn", "cpADD1");
			//Print Type
			driver.findElement(printType).click();
			log.info("printType is Selected!!!");
			CommonFunctions.clickButtonOrLink(printADD, "btn", "printADD");
			//Finishes
			driver.findElement(finishes).click();
			log.info("finishes is Selected!!!");
			CommonFunctions.clickButtonOrLink(finishes1ADD, "btn", "finishes1ADD");
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in fillMatTrim()", e);
			}
		}
	
	public static void createMaterial(String[] data) {
		// TODO Auto-generated method stub
		try{
			CommonFunctions.clickButtonOrLink(crateMaterial, "btn", "CreateMaterial");
			}
			catch(Exception e){
				fail=true;
				log.error("Exception in createMaterial()", e);
			}
		}


	public static boolean SetState_Materail(String[] data) throws Exception{
		try{
			
			searchMaterial(data);
			driver.findElement(systemTablink).click();
			log.info("System Tab is selecteda is Selected!!!");
			//Current LifeCycle State 
			String initial_LifecycleState = driver.findElement(RO_MaterialUpdateLifecycleState).getText();
			log.info("Initial LIfeCycle State of Material   :"+initial_LifecycleState);
			// Select Change State:  Sourcing Configuration from Action dropdown
			String str = driver.findElement(materialAction).getText();
			//log.info("STR is---------"+str);
			CommonFunctions.selectFromDropDownByVisibleText(materialAction, data[22]);
			String valULCSAfterChange=selectUpdateLifecycleState(data);
			Thread.sleep(1000);
			//Click on Update
			CommonFunctions.clickButtonOrLink(linkUpdate, "link", "Update");
			//Verification
			String updated_LifecysleState = driver.findElement(RO_MaterialUpdateLifecycleState).getText();
			log.info("Initial LIfeCycle State of Source   :"+initial_LifecycleState);
			log.info("Updated LIfeCycle State of Source   :"+updated_LifecysleState);
			log.info("Lifecycle State return from method  :"+valULCSAfterChange);

			if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("Yes")){
				Assert.assertEquals(updated_LifecysleState, valULCSAfterChange);
				log.info("User has the permission and able to change the Lifecycle State..");
			}
			else if(data[3].contains("SetState")&& data[4].equalsIgnoreCase("No")){
				Assert.assertEquals(updated_LifecysleState, initial_LifecycleState);
				log.info("User don't have the permission hence not able to change the Lifecycle State !!!");
			}
			else
				log.info("SetState or chageState is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in SetState_Materail()", e);
			return false;
		}
		return true;
	}

	public static boolean delete_Material(String[] data) throws Exception{
		try{
			searchMaterial(data);
			if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("Yes")){
				CommonFunctions.selectFromDropDownByVisibleText(materialAction, data[22]);
				CommonFunctions.waitForVisibilityOfElement(Product.headerDeleteObject);
				//Click on delete button
				CommonFunctions.clickButtonOrLink(Product.deleteButton,"btn", "Delete");
				//Accept AletPopup
				log.info("about to delete the sourcing Config-----");
				Thread.sleep(1000);
				//Accept AletPopup
				String msg= driver.switchTo().alert().getText(); 
				log.info("msg is -------"+msg);
				driver.switchTo().alert().dismiss();
				//driver.switchTo().alert().accept();
				Assert.assertEquals(msg, "This action will completely delete this object from the system.  Are you sure you want to do this?");
				log.info("User has the permission to delete Material...");
			}
			else if(data[3].contains("Delete")&& data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(materialAction).getText();
				log.info(strPrimary);
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[22]);
				Assert.assertFalse(val);
				log.info("User don't have the permission to Delete the Material !!!");
			}
			else
				log.info("Delete is not applicable(NA)");
		}catch(Exception e){
			fail=true;
			log.error("Exception in delete_Material()", e);
			return false;
		}
		return true;
	}
	//This funcion is to select Update Lifecycle State	
	public static String selectUpdateLifecycleState(String[] productData) throws Exception{
		try{
			valULCS = new Select(driver.findElement(Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
			//	System.out.println("valULCS: "+valULCS);
			if(valULCS.contains("In Work")){
				//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
				CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released");
			}
			else if(valULCS.contains("Under Review")){
				//	CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released ");
				CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "Released");
			}
			else if(valULCS.contains("Released")){
				//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
				CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work");
			}
			else if(valULCS.contains("Invalid")){
				//CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work ");
				CommonFunctions.selectFromDropDownByVisibleText(Editable_UpdateLifecycleState, "In Work");
			}
			valULCSAfterChange = new Select(driver.findElement(Editable_UpdateLifecycleState)).getFirstSelectedOption().getText();
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
			searchMaterial(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
			if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					log.info("Code is in side---------");
					String GALabel=driver.findElement(labelGeneralAttribute).getText();
					log.info(" General Attributes Value is    :"+GALabel);
					Assert.assertEquals(GALabel.trim(), "General Attributes:");
					log.info("General Attributes label is Present");
				}else{
					fail=true;
					log.error("General Attributes label is Absent");
				}
			}
			else if(data[3].contains("GeneralAttirbutesRead_View")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelGeneralAttribute).size() != 0){
					fail=true;
					log.error("General Attirbutes label is Present");
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
			if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("Yes")){//Update
				log.info("Code is in side Update General Attribute !!");
				String strPrimary =driver.findElement(materialAction).getText();
				//wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(detailsTablink));
				Assert.assertEquals(SourcingConfig.findString(strPrimary.trim(), data[22]), true);
				log.info("General Attribute is Updatable .....");

			}
			else if(data[3].contains("GeneralAttirbutesUpdate")&& data[4].equalsIgnoreCase("No")){
				String strPrimary =driver.findElement(materialAction).getText();
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[22]);
				Assert.assertFalse(val);
				log.info("General Attribute is Not Updatable !!!");
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
	//Function consist scenario : Material Details: Attributes:Read_View
	public static boolean verifyMaterialDtlsReadView(String[] data) throws Exception{
		try{
			searchMaterial(data);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("contentframe");
			
			if(data[3].contains("MaterialDeatilsRead_View")&& data[4].equalsIgnoreCase("Yes")){
				//Read_View
				if(driver.findElements(labelMaterialDeatils).size() != 0){
					log.info("Code is in side Materials Details ..");
					String MDLabel=driver.findElement(labelMaterialDeatils).getText();
					log.info(" Material Detailsral Attributes Value is    :"+MDLabel);
					Assert.assertEquals(MDLabel.trim(), "Material Details:");
					log.info("Material Details: Attributes label is Present");
				}else{
					fail=true;
					log.error("Material Details: Attributes label is Absent");
				}
			}
			else if(data[3].contains("MaterialDeatilsRead_View")&& data[4].equalsIgnoreCase("No")){
				if(driver.findElements(labelMaterialDeatils).size() != 0){
					fail=true;
					log.error("Material Details: is Present");
				}else{
					log.info("Material Details: label is Absent");
				}
			}
			else
			{
				log.info("For this Material Details: is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyMaterialDtlsReadView()", e);
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
	//Function consist scenario : Material Details: Attributes://Update
	public static boolean verifyMaterialDtlsUpdate(String[] data) throws Exception{
		try{
			if(data[3].contains("MaterialDeatilsUpdate")&& data[4].equalsIgnoreCase("Yes")){
				//Update
				log.info("Code is in side Update General Attribute !!");
				String strPrimary =driver.findElement(materialAction).getText();
				Assert.assertEquals(SourcingConfig.findString(strPrimary.trim(), data[22]), true);
				log.info("Material Details: Attribute is Updatable .....");
			}
			else if(data[3].contains("MaterialDeatilsUpdate")&& data[4].equalsIgnoreCase("No")){

				String strPrimary =driver.findElement(materialAction).getText();
				boolean val= SourcingConfig.findString(strPrimary.trim(), data[22]);
				Assert.assertFalse(val);
				log.info("Material Details Attribute is Not Updatable !!!");
			}	
			else
			{
				log.info("For this Material Details: is not applicable(NA)");
			}
		}catch(Exception e){
			fail=true;
			log.error("Exception in verifyMaterailDtlsUpdate()", e);
			return false;
		}
		return true;
	}
	

		//Function consist scenario : searchMaterial
		public static boolean searchMaterial(String[] data) throws Exception{
			try{
				driver.navigate().refresh();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("sidebarframe");
				// Click on Libraries
				CommonFunctions.clickButtonOrLink(libraryLink, "Link", "Library Link");
				//Click on Color link
				driver.findElement(materialLink).click();
				//Switch frame
				driver.switchTo().defaultContent();
				driver.switchTo().frame("contentframe");
				wait = new WebDriverWait(driver, 10);
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(materialHeadning));
				//Add name
				CommonFunctions.enterTextInTextbox(inputBoxName, data[21]);//Fab1471802331675  //data[28]
				//Click on Search
				CommonFunctions.clickButtonOrLink(btnSearch, "Btn", "Search");
				wait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(materialIdentification));
				
			}catch(Exception e){
				fail=true;
				log.error("Exception in searchMaterial()", e);
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
		//Function consist scenario : Pricing Chart :Read_View
		public static boolean verifyPricingChartView(String[] data) throws Exception{
			try{

				searchMaterial(data);
				String pcView = driver.findElement(viewMOA).getText();
				log.info("Pricing Chart View Value is    :"+pcView);
				String pcLbl = driver.findElement(pricingChart).getText();
				log.info("Varient Label Value is    :"+pcLbl);
				if(data[3].contains("PricingChart_View")&& data[4].equalsIgnoreCase("Yes")){//Read_View
					if((driver.findElements(pricingChart).size() != 0) && pcView.contains("View")){
						log.info("Pricing Chart value is    :"+pcLbl);
						Assert.assertEquals(pcLbl, " Pricing Chart:");
						log.info("Pricing Chart: label View is Present");
					}else{
						log.error("Pricing Chart: label View is Absent");
						fail=true;
					}
				}
				else if(data[3].contains("PricingChart_View")&& data[4].equalsIgnoreCase("No")){
					if((driver.findElements(pricingChart).size() != 0) && pcView.contains("View")){
						log.error("Pricing Chart: label View is Present");
						fail=true;
					}else{
						log.info("Pricing Chart: label View is Absent");
					}
				}
				else
				{
					log.info("For this Pricing Chart: label is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyPricingChartView()", e);
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
		//Function consist scenario : Pricing Chart //Update
		public static boolean verifyPricingChartUpdate(String[] data) throws Exception{
			try{
				
				boolean ve=false;
				ve=isPresent(editMOA, ve);
				log.info("Calling 1");
				if(data[3].contains("PricingChart_Edit")&& data[4].equalsIgnoreCase("Yes")){//Update
					log.info("Calling 2");
					Assert.assertTrue(ve, "Edit Is Present");
					log.info("Pricing Chart: label has Edit option");
				
				}
				else if(data[3].contains("PricingChart_Edit")&& data[4].equalsIgnoreCase("No")){
					Assert.assertFalse(ve, "Pricing Chart: is Not Editable");
					log.info("Pricing Chart: label has No Edit option");
				}	
				else
				{
					log.info("For this Pricing Chart: is not applicable(NA)");
				}
			}catch(Exception e){
				fail=true;
				log.error("Exception in verifyPricingChartUpdate()", e);
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
