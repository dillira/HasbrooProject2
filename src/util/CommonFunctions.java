package util;
/**
 * @author Anjali Gupta
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;






import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import base.SeleniumDriver;

import org.apache.commons.io.FileUtils;

import com.hasbro.ci2018.CI260;

import util.MyException;



public class CommonFunctions {
	public static String screenshotFilename="";
	public static String lastMonthDate;
	public static String lastMonDate;
	public static String todayDate;
	public static String tomorrowDate;
	public static String m_testName;
	public static String selectedOption;
	public static int decimalLength;
	public static boolean fail=false;
	public static String parent;
	public static String child;
	public static Set<String> AllWindows;
	public static int windowCount;
	public static String alertMsg;
	public static String alertmessage;
    public static boolean error=false;
	public static SoftAssert S_ASSERT = new SoftAssert();
	public static Assertion ASSERT = new Assertion();
	
	//public static WebDriver driver=null;

	

	/*
	 * This function will return default visible value present in dropdown box
	 */
	public static String getDefaultSelectedValueFromDropdown(By by) throws Exception{
		try{
			return new Select(SeleniumDriver.driver.findElement(by)).getFirstSelectedOption().getText();
		}
		catch(Exception e){
			fail=true;
			e.printStackTrace();
			return "";
		}

	}
	public void pause(Integer milliseconds){
	    try {
	        TimeUnit.MILLISECONDS.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	
	/*
	 * @by:dropdown value
	 * 
	 */
	public static Boolean dropDownValueVerification(By by,List<WebElement> exp,String[] a) throws Exception{
		try{
			int count = 0;
			WebElement dropdown = SeleniumDriver.driver.findElement(by);
			Select select = new Select(dropdown);

			List<WebElement> options = select.getOptions();
			for (WebElement we : options) {
				for (int i = 0; i < 6; i++) {
					if (we.getText().equals(a[i])) {
						count++;
					}
				}
			}
			if (count == a.length) {
				System.out.println("matched");
			} else {
				System.out.println("Not matched");
			}
		}
		catch(Exception e){
			fail=true;
			e.printStackTrace();
			return false;
		}
		return true;  
	}


	/*
	 * This function is to check digits after decimals 
	 * 
	 */
	public static boolean checkDecimalDigit(BigDecimal digit,int deciLength) throws Exception{
		try{
			String[] splitter = digit.toString().split("\\.");
			splitter[0].length();   // Before Decimal Count
			decimalLength = splitter[1].length();  // After Decimal Count
			if (decimalLength==deciLength){
				SeleniumDriver.log.info("Decimal length is:"+ decimalLength);
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e){
			fail=true;
			e.printStackTrace();
		}
		return true;
	}


	public static boolean isElementEmpty(By by){
		try{
			String textInsideInputBox = SeleniumDriver.driver.findElement(by).getAttribute("value");
			// Check whether input field is blank
			if(textInsideInputBox.isEmpty()||textInsideInputBox.contains(""))
			{
				System.out.println("Input field is empty");
				return true;
			}
		}
		catch(Exception e){
			fail=true;
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	public static String getTodayDate() {
		try{	
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date today = Calendar.getInstance().getTime();
			System.out.println(today);
			todayDate = df.format(today);
			System.out.println(todayDate);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in getTodayDate()", e);
		}
		return todayDate;
	}
	public static String getTomorrowDate() {
		try{	
			Date today = new Date();               
			SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");            
			Calendar c = Calendar.getInstance();        
			c.add(Calendar.DATE, 1);  // number of days to add      
			tomorrowDate = (String)(formattedDate.format(c.getTime()));
			System.out.println("Tomorrows date is " + tomorrowDate);
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in getTodayDate()", e);
		}
		return tomorrowDate;
	}

	public static String lastDayofMonth() {
		try{	
			Date today = new Date();  
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 

			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(today);  

			calendar.add(Calendar.MONTH, 1);  
			calendar.set(Calendar.DAY_OF_MONTH, 1);  
			calendar.add(Calendar.DATE, -1);  

			Date lastDayOfMonth = calendar.getTime();  
			lastMonthDate=df.format(lastDayOfMonth);
			//   lastMonthDate = df.format(today);
			System.out.println(lastMonthDate);


		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in lastDayofMonth()", e);
			throw e;
		}
		return lastMonthDate;
	}

	public static boolean dropDownOptionVerificationActions(String a,List<WebElement> b) {
		try{	
			for (WebElement option : b) {
				System.out.println("b: "+option.getText());
				System.out.println("a: "+a);
				if (option.getText().trim().contains(a.trim())) {
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
	
	public static boolean AssertEqualsVerification(String Actual,String expected,String ErrorMessage){
		//This Function will Verify the expected and Actual Value .if fails error message will print and exception will throw
		  try{
			ASSERT.assertEquals(Actual, expected);
		    SeleniumDriver.log.info("Actual as "+Actual+" and Expected as "+expected+".Both are Matched.Assertion verified Successfully");
			}
		catch(AssertionError er)
		{
		error=true;
		SeleniumDriver.log.error(ErrorMessage);
		SeleniumDriver.log.error(er);
	    throw er;
	}
		return true;
	}
	
	public static boolean AssertNotEqualsVerification(String Actual,String expected,String ErrorMessage){
		//This Function will Verify the expected and Actual Value .if fails error message will print and exception will throw
		  try{
			ASSERT.assertNotEquals(Actual, expected);
			SeleniumDriver.log.info("Actual as "+Actual+" and Expected as "+expected+".Both are not Matched.Assertion verified Successfully");
			}
		catch(AssertionError er)
		{
		error=true;
		SeleniumDriver.log.error(ErrorMessage);
		SeleniumDriver.log.error(er);
	    throw er;
	}
		return true;
	}
	public static boolean AssertNotNullVerification(By by,String ErrorMessage){
		//This Function will Verify the expected and Actual Value .if fails error message will print and exception will throw
		  try{
			ASSERT.assertNotNull(by);
			SeleniumDriver.log.info("WebElement Have Some values and is Not Null.Condition satsfied.Assertion Passed");
			}
		catch(AssertionError er)
		{
		error=true;
		SeleniumDriver.log.error(ErrorMessage);
		SeleniumDriver.log.error(er);
	    throw er;
	}
		return true;
	}
	
	public static boolean AssertNotEqualsVerificationforIntergers(int Actual,int expected,String ErrorMessage){
		//This Function will Verify the expected and Actual Value .if fails error message will print and exception will throw
		  try{
			ASSERT.assertNotEquals(Actual, expected);
			SeleniumDriver.log.info("Actual as "+Actual+" and Expected as "+expected+".Both are not Matched.Assertion verified Successfully");
			}
		catch(AssertionError er)
		{
		error=true;
		SeleniumDriver.log.error(ErrorMessage);
		SeleniumDriver.log.error(er);
	    throw er;
	}
		return true;
	}
	
	public static boolean AssertFalseVerification(boolean condition,String ErrorMessage){
		try{
			ASSERT.assertFalse(condition);
			SeleniumDriver.log.info("Condition Passed.Assertion verified Successfully");
		}
		catch(AssertionError er)
		{
		error=true;
		SeleniumDriver.log.error(ErrorMessage);
		SeleniumDriver.log.error(er);
		return false;
	}
		return true;
	}
	public static boolean AssertTrueVerification(boolean condition,String ErrorMessage){
		//This Functions will erify the condition.if it is not true then it will print the error message and exception will throw.
		try{
			ASSERT.assertTrue(condition,"Condition is Passed>assertion is Passed");
			SeleniumDriver.log.info("Condition Passed.Assertion verified Successfully");
		}
		catch(AssertionError er)
		{
		error=true;
		SeleniumDriver.log.error(ErrorMessage);
		SeleniumDriver.log.error(er);
		throw er;
	}
		return true;
	}

	/*
	 *This function will verify dropdown all values 
	 */
	/*public static String varifyDropdownValues(String a,List<WebElement> b) throws Exception{
		try{
			String[] exp = {"--Title--","Mr","Mrs","Miss","Ms","Dr","Prof"};
			 WebElement dropdown = driver.findElement(By.id("ddlNights"));  
			 Select select = new Select(dropdown);  

			 List<WebElement> options = select.getOptions();  
			 for(WebElement we:options)  
			 {  
			  boolean match = false;
			  for (int i=0; i<exp.length(); i++){
			      if (we.getText().equals(exp[i]){
			        match = true;
			      }
			    }
			  Assert.assertTrue(match);
			 } 
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}

	}*/

	public static String getRandomString(int length) {
		try{	
		final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJLMNOPQRSTUVWXYZ1234567890";
		StringBuilder result = new StringBuilder();
		while(length > 0) {
			Random rand = new Random();
			result.append(characters.charAt(rand.nextInt(characters.length())));
			length--;
		}
		System.out.println(result.toString());
		return result.toString();
		
	}catch(Exception e){
		fail=true;
		SeleniumDriver.log.error("Exception in dropDownOptionVerification()", e);
		return "";
	}
	}

	/*
	 * This function will return true\false value for drop down options.If String a is present in drop down options it will return true else false
	 * @String a is verification drop down option
	 * @List<WebElement> b is list of all options in drop down
	 */
	public static boolean dropDownOptionVerification(String a,List<WebElement> b) {
		String optiontext;
		try{	
			for (WebElement we:b) {
				a=a.trim();
			//	System.out.println(we.getText());
				optiontext=we.getText().trim();
			//	System.out.println(a);
				System.out.println(optiontext);
				SeleniumDriver.log.info("Expected value: "+ a);
				SeleniumDriver.log.info("Observed value: "+ optiontext);

				if (optiontext.equalsIgnoreCase(a)) {
					return true;
				}
			}
			return false;

		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in dropDownOptionVerification()", e);
			return false;
		}
	}

	/*public static void switchToChildWindow() {
		try{	
			Set<String> windowId = SeleniumDriver.driver.getWindowHandles();    //get window id of current window
			Iterator<String> itererator = windowId.iterator();   

			String mainWinID = itererator.next();
			String firstNewWinID = itererator.next();

			System.out.println("Main Win Id******:" + " " + mainWinID);
			System.out.println("1st New Win Id*******:" + " " + firstNewWinID);

			SeleniumDriver.driver.switchTo().window(firstNewWinID);
			//SeleniumDriver.driver.manage().window().maximize();
		}catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in switchToChildWindow()", e);
			//	return false;
		}
	}*/

	public static boolean clickButtonOrLink(By by, String type,String elementNameForLog ) throws Exception{
		boolean result=false;
		Thread.sleep(100);

	//	try{
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				if(SeleniumDriver.driver.findElement(by).isEnabled()){
					SeleniumDriver.driver.findElement(by).click();

					if(type.equalsIgnoreCase("btn")){
						SeleniumDriver.log.debug("Clicking on " +  elementNameForLog +" button.");
					}
					else{
						SeleniumDriver.log.debug("Clicking on " +  elementNameForLog +" button.");

					}
					result=true;
				}	
				else{
					if(type.equalsIgnoreCase("btn")){
						SeleniumDriver.log.debug(elementNameForLog+" button is present but not clickable.");
					}
					else{
						SeleniumDriver.log.debug(elementNameForLog+" link is present but not clickable.");
					}
				}
			}
			else{
				if(type.equalsIgnoreCase("btn")){
					SeleniumDriver.log.debug(elementNameForLog+" button not present.");
				}
				else{
					SeleniumDriver.log.debug(elementNameForLog+" button not present.");
				}
			}

			return result;
	//	}
		/*catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in clickButton()", e);
			SeleniumDriver.log.debug(elementNameForLog + " " + "is not able to Click.");
			return false;
		}*/
	}


	public static boolean clickButtonOrLink(By by, String elementNameForLog) throws Exception{
		boolean result=false;
		Thread.sleep(100);
	//	try{
			//check the presence of web element if available then check the check box if not checked
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				if(SeleniumDriver.driver.findElement(by).isEnabled()){
					SeleniumDriver.driver.findElement(by).click();
					SeleniumDriver.log.info(elementNameForLog+" is clicked.");
				}
				else{
					SeleniumDriver.log.info(elementNameForLog+" is present but not clickable.");
				}
				result=true;

			}

			return result;

	/*	}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in clickButton()", e);
			SeleniumDriver.log.info(elementNameForLog + " " + "is not able to Click.");
			return false;
		}*/
	}
	public static boolean clearTextBox(By by, String elementNameForLog) throws Exception{
		boolean result=false;
		Thread.sleep(100);
  		if(CommonFunctions.isElementPresentWithoutLog(by)){
				if(SeleniumDriver.driver.findElement(by).isEnabled()){
					SeleniumDriver.driver.findElement(by).clear();
					SeleniumDriver.log.info(elementNameForLog+" is cleared.");
				}
				else{
					fail=true;
					SeleniumDriver.log.info(elementNameForLog+" is present but not clickable.");
					
				}
				result=true;

			}

			return result;

}


	public static boolean enterTextInTextbox(By by, String inputValue) throws Exception{
		boolean result=false;
		Thread.sleep(100);
	try{
			//check the presence of web element if available then select the value from drop down
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				//SeleniumDriver.driver.findElement(by).clear();
				SeleniumDriver.driver.findElement(by).sendKeys(inputValue);
				SeleniumDriver.log.info("Entered" + " " + inputValue + " " + "in the text field.");
				result=true;
			}
			return result;
	}
		
		
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in enterTextInTextbox()", e);
			SeleniumDriver.log.info(inputValue + " " + "is not able to select.");
			return false;
		}
	}
	
	public static boolean isElementNotChecked(By by, String elementForLog){
		boolean ifElementNotchecked=true;
	
		try{
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 5);
			if(!(SeleniumDriver.driver.findElement(by).isSelected())){
				SeleniumDriver.log.info(elementForLog+" is Not Checked");
			}
		}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in isElementNotChecked"+e);
			throw e;
			
		}
		return ifElementNotchecked;
	}

	
	
	public static boolean enterTextInTextboxUpdated(By by, String inputValue ,String ObjName) throws Exception{
		boolean result=false;
		Thread.sleep(100);
		
		try{
			//check the presence of web element if available then select the value from drop down
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				//SeleniumDriver.driver.findElement(by).clear();
				SeleniumDriver.driver.findElement(by).sendKeys(inputValue);
				SeleniumDriver.driver.findElement(by).getAttribute("give the attribute value here");
				SeleniumDriver.screenCaptureInfo = "Entered" + " " + inputValue + " " + "in the " + ObjName + " text field successfully.";
				SeleniumDriver.log.info("Entered" + " " + inputValue + " " + "in the " + ObjName + " text field successfully.");
				//CommonFunctions.captureScreenShot();
				result=true;
			}
			return result;
		}
		catch(Exception e){
			
			SeleniumDriver.screenCaptureInfo = "Unable to Entered" + " " + inputValue + " " + "in the " + ObjName + " text field successfully.";
			SeleniumDriver.log.info("Unable to Entered" + " " + inputValue + " " + "in the " + ObjName + " text field successfully.");
			SeleniumDriver.log.error("Exception in enterTextInTextbox()", e);
			//CommonFunctions.captureScreenShot();
			//SeleniumDriver.log.info(inputValue + " " + "is not able to select.");
		    throw e;
		}
	}

	public static boolean selectCheckbox(By by) throws Exception{
		boolean result=false;
		Thread.sleep(100);
	//	try{
			//check the presence of web element if available then check the check box if not checked
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				if(SeleniumDriver.driver.findElement(by).isSelected()){
					SeleniumDriver.log.info(by+ " is present but Check Box already checked");
				}
				else{
					SeleniumDriver.driver.findElement(by).click();
					SeleniumDriver.log.info(by+" is present and is checked.");
				}
				result=true;
			}

			return result;
		/*}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in selectCheckbox()", e);
			SeleniumDriver.log.info(by + " " + "is not able to check.");
			return false;
		}*/
	}

	public static boolean selectFromDropDownByValue(By by, String inputValue) throws Exception{
		boolean result=false;
		Thread.sleep(100);
		//try{
			//check the presence of web element if available then select the value from drop down
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				new Select(SeleniumDriver.driver.findElement(by)).selectByValue(inputValue);
				SeleniumDriver.log.info("Selected" + " " + inputValue + " " + "option from drop down.");
				result=true;

			}
			return result;
		/*}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in selectFromDropDownByValue()", e);
			SeleniumDriver.log.info(inputValue + " " + "is not able to select.");
			return false;
		}*/
	}

	public static boolean selectFromDropDownByVisibleText(By by, String inputValue) throws Exception {
		boolean result=false;
		//Thread.sleep(100);
		//try{
			//check the presence of web element if available then select the value from drop down
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				new Select(SeleniumDriver.driver.findElement(by)).selectByVisibleText(inputValue);
				SeleniumDriver.log.info("Selected" + " " + inputValue + " " + "option from drop down.");
				result=true;
			}
			return result;
		/*}
		catch(Throwable t) {
			fail=true;
			SeleniumDriver.log.error("Exception in selectFromDropDownByVisibleText()", t);
			SeleniumDriver.log.info(inputValue + " " + "is not able to select.");
			ErrorUtil.addVerificationFailure(t);
			//throw new MyException();
			return false;
		}*/
	}
	
	public static boolean selectFromDropDownByVisibleTextUpdated(By by, String inputValue,String objName) throws Exception{
		boolean result=false;

		Thread.sleep(100);

		//try{
			//check the presence of web element if				new Select(SeleniumDriver.driver.findElement(by)).selectByVisibleText(inputValue); available then select the value from drop down
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				new Select(SeleniumDriver.driver.findElement(by)).selectByVisibleText(inputValue);
				SeleniumDriver.screenCaptureInfo = "Selected" + " " + inputValue + " " + "option from " + objName +" drop down successfully.";
				SeleniumDriver.log.info("Selected" + " " + inputValue + " " + "option from " + objName +" drop down successfully.");
				//CommonFunctions.captureScreenShot();
				result=true;
			}

			return result;
		/*}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.info("Unable to select" + " " + inputValue + " " + "option from " + objName +" drop down.");
			SeleniumDriver.screenCaptureInfo = "Unable to select" + " " + inputValue + " " + "option from " + objName +" drop down.";
			SeleniumDriver.log.error("Exception in selectFromDropDownByVisibleText()", e);
			throw new MyException();
			//return false;
		}*/
	}

	public static boolean selectFromDropDownByIndex(By by, int strIndex) throws Exception{
		boolean result=false;
		Thread.sleep(100);
	//	try{
			//check the presence of web element if available then select the value from drop down
			if(CommonFunctions.isElementPresentWithoutLog(by)){
				new Select(SeleniumDriver.driver.findElement(by)).selectByIndex(strIndex);
				SeleniumDriver.log.info("Selected" + " " + strIndex + " " + "option from drop down.");
				result=true;

			}
			return result;
		/*}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in selectFromDropDownByIndex()", e);
			SeleniumDriver.log.info(intindex + " " + "is not able to select.");
			//return false;
			throw new MyException();
		}*/
	}

	public static boolean isElementPresentWithoutLog(By by) throws Exception{
	//	try{
			SeleniumDriver.driver.findElement(by).isDisplayed();
			return true;
	/*	}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.info(by + " " + "is not present");
			throw new MyException();
			//return false;
		}*/
	}

	public static boolean waitForVisibilityOfElement(By by){
		boolean ifElementFound = false;
		for(int i = 0; i < 180; i ++){
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 60);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				ifElementFound = true;
				break;
			} catch (TimeoutException t) {
				fail=true;
			//	log.error("-- Testcase fail due to timeout issue --");
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				fail=true;
			//	log.error("-- Testcase fail due to timeout issue --");
				e.printStackTrace();
			}
		}
		return ifElementFound;
	}

	/*public static String handleAlertPopUp() throws Exception{
		String alertMsg="";
		try{
			if(isAlertPresent()){
				Alert alt=SeleniumDriver.driver.switchTo().alert();
				Thread.sleep(1000);
				alertmsg = alt.getText();
				alt.accept();
				waitForPageLoaded();
			}
			else
				SeleniumDriver.log.info("Alert is not present");
			
		}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in handleAlertPopUp()", e);
			alertMsg = null;
			throw new MyException();
		}
		return alertmessage;
	}*/
	
	public static String handleAlertPopUp() throws Exception{
		
		try{
			if(isAlertPresent()){
				Alert alt=SeleniumDriver.driver.switchTo().alert();
				Thread.sleep(1000);
				alertmessage = alt.getText();
				System.out.println("The message present in alert is"+alertmessage);
				alt.accept();
			}
			else
				SeleniumDriver.log.info("Alert is not present");
		}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in handleAlertPopUp()", e);
			alertmessage = null;
			throw new MyException();
		}
		return alertmessage;
		
		
	}

	public static String handleAlertPopUp1(){
		WebDriverWait wait= new WebDriverWait(SeleniumDriver.driver,2);
		try{
			if(wait.until(ExpectedConditions.alertIsPresent()) != null)
			{
			try{
				Alert alt=SeleniumDriver.driver.switchTo().alert();
			     alertMsg = alt.getText();
				SeleniumDriver.log.info(alertMsg);
				alt.accept();
				}
			catch(Exception e)
			{
				fail=true;
				System.out.println("Error While handling Alert"+e);
				throw e;
			}
			}}
		catch(Exception e){
			SeleniumDriver.log.info("No Alert is Present");
	}
		return alertMsg;
	}
		
	
	
	public static boolean isAlertPresent() 
	{ 
		try 
		{ 
		
			SeleniumDriver.driver.switchTo().alert(); 
			SeleniumDriver.log.info("Alert is present");
		
		   }
		 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}  
		return true;
	}  



	public static boolean AlertPopUpPresent() throws Exception{
		WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 10 );
		try{
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alt=SeleniumDriver.driver.switchTo().alert();
			String Test=alt.getText();
			alt.dismiss();
			return true;
		}
		catch(Exception e){
			fail=true;
			//return false;
			throw new MyException();
		}
	}

	public static boolean waitForElementTobeClickable(By by){
		WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 60);
		try{
			wait.until(ExpectedConditions.elementToBeClickable(by));
		}
		catch(TimeoutException t){
			fail=true;
			SeleniumDriver.log.error("Exception in waitForElementTobeClicable()", t);
			return false;
		}
		return true;
		}
	

	
	
	public static boolean waitForInvisibilityOfElement(By by){
		WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 60);
		try{
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}
		catch(TimeoutException t){
			fail=true;
			SeleniumDriver.log.error("Exception in waitForInvisibilityOfElement()", t);
			return false;
		}
		return true;
	}

	
	public static boolean isElementNotPresent(By by,String elementNameForLog){
		boolean ifElementNotFound=false;
		try{
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 5);
			if(wait.until(ExpectedConditions.invisibilityOfElementLocated(by))){
				ifElementNotFound=true;
				SeleniumDriver.log.info(elementNameForLog +"is not found");
			}
		}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in isElementNotPresent"+e);
			throw e;
		}
		return ifElementNotFound;
	}
	
	public static boolean isElementDisplayed(By by, String elementNameForLog){
		boolean ifElementFound=false;
		try{
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 5);
			if(wait.until(ExpectedConditions.visibilityOfElementLocated(by)) != null){
				ifElementFound=true;
				SeleniumDriver.log.info(elementNameForLog +" is found");
			}
		}
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in isElementNotPresent"+e);
			throw e;
		}
		return ifElementFound;
	}
	
	public static boolean isElementPresent(By by){
		boolean ifElementFound = false;

		for(int i=0; i<10; i++){
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 1);
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				ifElementFound = true;
				break;
			}
			catch (TimeoutException t) {	
				fail=true;
				SeleniumDriver.log.error("-- Testcase fail due to timeout issue --");	
			}

			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				fail=true;
				SeleniumDriver.log.error("-- Testcase fail due to timeout issue --");
				e.printStackTrace();
			}
		}
		return ifElementFound;
	}

	public static boolean isElementPresentModified(By by,int waitTime){
		boolean ifElementFound = false;

		for(int i=0; i<waitTime; i++){
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 1);

			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				ifElementFound = true;
				break;
			}
			catch (TimeoutException t) {	
				fail=true;
				SeleniumDriver.log.error("-- Testcase fail due to timeout issue --");	
			}

			try {
				Thread.sleep(500);
			} 
			catch (InterruptedException e) {
				fail=true;
				SeleniumDriver.log.error("-- Testcase fail due to timeout issue --");
				e.printStackTrace();
			}
		}
		return ifElementFound;
	}

	public static boolean isElementPresentWithWaitTime(By by){
		boolean ifElementFound = false;

		for(int i=0; i<10; i++){
			WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 1);

			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				ifElementFound = true;
				break;
			}
			catch (TimeoutException t) {	
				fail=true;
				SeleniumDriver.log.error("-- Testcase fail due to timeout issue --");	
			}

			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				fail=true;
				SeleniumDriver.log.error("-- Testcase fail due to timeout issue --");
				e.printStackTrace();
			}
		}
		return ifElementFound;
	}


	public static String switchChildWindow() throws Exception{
		String alertMsg="";
		try{
			Alert alt=SeleniumDriver.driver.switchTo().alert();
			Set set = SeleniumDriver.driver.getWindowHandles();
			Iterator iter = set.iterator();
			parent = (java.lang.String) iter.next();
			SeleniumDriver.log.info("The parent window is "+parent);
		    child = (java.lang.String) iter.next();
		    SeleniumDriver.log.info("The parent window is "+child);
			SeleniumDriver.driver.switchTo().window(child);
		}
		
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in handleAlertPopUp()", e);
			alertMsg = null;
		}
		return alertMsg;
	}
	
	public static int WindowsCount(){
		
		try{
			
			 AllWindows=SeleniumDriver.driver.getWindowHandles();
				windowCount=AllWindows.size();
		}
		catch(Exception e){
			SeleniumDriver.log.error("Error While Counting the Windows ");
		}
		
		return windowCount;
	}
	
	
	public static boolean switchingChildWindow() throws Exception{
		boolean value=false;
		try{
		     AllWindows=SeleniumDriver.driver.getWindowHandles();
			int count=AllWindows.size();
			System.out.println("Count is "+count);
			System.out.println(AllWindows);
			 for(String AllWindowHandles:AllWindows){
				 if(!AllWindowHandles.equals(parent));
				 {
					SeleniumDriver.driver.switchTo().window(AllWindowHandles);
					SeleniumDriver.driver.manage().window().maximize();
				}
			   }
			        child=SeleniumDriver.driver.getWindowHandle();
					System.out.println(child);
					
			 }
		catch(Exception e){
			fail=true;
			SeleniumDriver.log.error("Exception in switching into childWindow", e);
			throw e;
		}
		return true;
	}
		
	
	
	public static boolean switchParentWindow(){
		boolean value=false;
	try{
		System.out.println(AllWindows);
		 for(String AllWindowHandles:AllWindows){
			 if(AllWindowHandles.equals(parent));
			 {
				SeleniumDriver.driver.switchTo().window(parent); 
			 }
	}
	}
	catch(Exception e){
		fail=true;
		SeleniumDriver.log.error("Exception in switching into ParentWindow", e);
		return value;
	}
	return true;
	}
	
	public static void gettingParentWindow(){
		try{
		parent = SeleniumDriver.driver.getWindowHandle();
		System.out.println(parent);
		 }
		catch(Exception e){
			SeleniumDriver.log.error("Unable to get a Parent Window" +e);	
		}
	}
	
	public static void wait(int seconds) {

		SeleniumDriver.log.debug("Waiting for " + seconds + " seconds");

		try {
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e) {
			fail=true;
			SeleniumDriver.log.error("-- Testcase fail due to timeout issue --");
			// Never, ever, happens
		}
	}

	public static void takeScreenshot(String username,String testname) {

		wait(4);
		//		Logger.log_Debug("Attempting to take screenshot");

		try {
			File scrFile = ((TakesScreenshot)SeleniumDriver.driver).getScreenshotAs(OutputType.FILE);
			java.util.Date date= new java.util.Date();
			String fileName = new Timestamp(date.getTime()) + ".png";
			System.out.println(fileName);
			fileName = fileName.replace(':', ' ');
			System.out.println(fileName);
			fileName = fileName.replace('-', ' ');
			System.out.println(fileName);
			String page = SeleniumDriver.driver.getTitle();
			fileName = username+testname+fileName.trim();
			System.out.println(fileName);
			FileUtils.copyFile(scrFile, new File("C:\\Hasbro\\FlexPLM\\screenshots\\" + fileName));

			SeleniumDriver.log.info("Taking screenshot: "+ "<a href='screenshots\\" + fileName + "'><img src='screenshots\\" + fileName + "' /></a>");
		}
		catch (IOException e) {
		//	fail=true;
			SeleniumDriver.log.error("IOException, cannot take screenshot", e);
		}
		catch (Exception e) {
		//	fail=true;
			SeleniumDriver.log.error("Error taking screenshot", e);
		}
	}

	public static void clearAllCookies() {

		SeleniumDriver.log.debug("Clearing cookies");

		if (SeleniumDriver.driver != null) {
			SeleniumDriver.driver.manage().deleteAllCookies();
		}
	}
	// method is for checking the variable.
	public static boolean isPresent(By by, boolean p) throws Exception{
		try {
			p = SeleniumDriver.driver.findElement(by).isDisplayed();
			p = true;
		} catch (NoSuchElementException e) {
			p = false;
		}
		return p;
	}
	 public static void waitForPageLoaded() {
	        ExpectedCondition<Boolean> expectation = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	                    }
	                };
	        try {
	            Thread.sleep(1000);
	            WebDriverWait wait = new WebDriverWait(SeleniumDriver.driver, 240);
	            wait.until(expectation);
	        } catch (Throwable error) {
	        	SeleniumDriver.log.error("Page is Taking too much time To Load");
	            Assert.fail("Timeout waiting for Page Load Request to complete.");
	        }
	    }
	
		
	
		
	}

	/*public static String parentAutomationFolder(){
		  String [] arrPath = currentPath().split("Selenium");
		       String strFolder = arrPath[0];
		       return strFolder;
	}*/

	/*public static String currentPath(){
		  String currPath = System.getProperty("user.dir");
		  return currPath;
	}*/

	/*public static String dateTimeStamp(){
	  int day, month, year;
	       int second, minute, hour;
	       GregorianCalendar date = new GregorianCalendar();

	       day = date.get(Calendar.DAY_OF_MONTH);
	       month = date.get(Calendar.MONTH);
	       year = date.get(Calendar.YEAR);

	       second = date.get(Calendar.SECOND);
	       minute = date.get(Calendar.MINUTE);
	       hour = date.get(Calendar.HOUR_OF_DAY);

	       String currentTimeStamp = (day+"_"+(month+1)+"_"+year+"_"+hour+"_"+minute+"_"+second);
	       return currentTimeStamp;
	 }*/

	/*public static void makeDirectory(File targetLocation){
		 if (!targetLocation.exists()) {
	           targetLocation.mkdir();
	     }
	 }*/

	/*public static void copyDirectory(File sourceLocation , File targetLocation)
	      throws IOException {

	        if (sourceLocation.isDirectory()) {
	            if (!targetLocation.exists()) {
	                targetLocation.mkdir();
	            }

	            String[] children = sourceLocation.list();
	            for (int i=0; i<children.length; i++) {
	                copyDirectory(new File(sourceLocation, children[i]),
	                        new File(targetLocation, children[i]));
	            }
	        } else {

	            InputStream in = new FileInputStream(sourceLocation);
	            OutputStream out = new FileOutputStream(targetLocation);

	            // Copy the bits from instream to outstream
	            byte[] buf = new byte[1024];
	            int len;
	            while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	            in.close();
	            out.close();
	        }
	    }*/

	/*public static void takeScreenshot() throws WebDriverException, IOException {
		 String myTimeStamp = CommonFunctions.dateTimeStamp();
		 FileUtils.forceMkdir(new File(SaveResult.resultFolder+"\\Screenshots"));
		 screenshotFilename = ScriptDriver.currTestCase+"_Iteration"+ScriptDriver.tcDataRow+"_"+myTimeStamp + ".png";
		 FileOutputStream file = new FileOutputStream(SaveResult.resultFolder+"\\Screenshots\\" +screenshotFilename);
		 file.write(((TakesScreenshot) TestSuite.SeleniumDriver.driver).getScreenshotAs(OutputType.BYTES));
		 file.close();
	 }*/

	/*public static int GetColumnIndex(String arrExcelData[][], String columnName) throws IOException {
	  int columnIndex = 0;

	  CurrentLoop: for (int iColumn=0; iColumn<arrExcelData[0].length; iColumn++) {
		  if(arrExcelData[0][iColumn].equals(columnName)) {
		     columnIndex = iColumn;
		     break CurrentLoop;
		  }
	  }

	  return columnIndex;
	 } */


	/*public static void writeData(String filePath,String value)  {
			 BufferedWriter bw = null;

			 try {

			     bw = new BufferedWriter(new FileWriter(filePath, true));
			     bw.write(value);	
			     bw.newLine();
			     bw.flush();
			 } catch (IOException ioe) {

			     ioe.printStackTrace();
			     System.out.println("Error in opening file");
			 } catch (Exception e) {

			     e.printStackTrace();
			     System.out.println("Error in opening file");
			 } finally {

			     if (bw != null) {
			         try {

			             bw.close();
			         } catch (IOException ioe2) {

			             System.out.println("Error in closing file");
			         } catch (Exception e) {

					     e.printStackTrace();
					     System.out.println("Error in closing file");
					 }
			     }
			 }
		   }*/

	



