package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import util.CommonFunctions;
import util.ErrorUtil;
import util.Xls_Reader;

public class SeleniumDriver {

	public static final String Param = null;
	public static Logger log=null;
	public static Properties prop=null;
	public static Xls_Reader suiteXls=null;
//	public static Xls_Reader suiteLibrariesXls=null;
	public static Xls_Reader suiteSecurityXls=null;
//	public static Xls_Reader suiteR2CostXls=null;
//	public static Xls_Reader suiteCartXls=null;
//	public static Xls_Reader suiteProductDisplayXls=null;
//	public static Xls_Reader suiteR2PMFunctionalXls=null;
	public static Xls_Reader suiteSmokeTestXls=null;
	public static Xls_Reader suiteCascadingXls=null;
	public static Xls_Reader suiteViewsXls=null;
	public static Xls_Reader suiteCIExcelXls=null;
	public static Xls_Reader suiteSanityXls=null;
	
	
	
	//For costing functional flow
	public static Xls_Reader suiteR2CostFunctionalXls=null;
	//Added
	public static String screenCaptureInfo = null;
	public static WebDriverWait wait;
	public static Actions action;
	
	public static boolean isInitialized=false;
	public static WebDriver driver=null;
	public static int myAutomationWait = 100;
	static String Url;
	public static String baseurl= "hasbroplm-dev.ptcmanaged.com/Windchill/rfa/jsp/main/Main.jsp";
	public static String Sandboxurl= "hasbroplm-sandbox.ptcmanaged.com/Windchill/rfa/jsp/main/Main.jsp";
	public static String dev2= "hasbroplm-dev2.ptcmanaged.com/Windchill/rfa/jsp/main/Main.jsp";
	public static String testURL= "hasbroplm-test.ptcmanaged.com/Windchill/rfa/jsp/main/Main.jsp";
	public static String devURL= "hasbroplm-dev.ptcmanaged.com/Windchill/rfa/jsp/main/Main.jsp";
	public static String qaURL= "hasbroplm-qa.ptcmanaged.com/Windchill/rfa/jsp/main/Main.jsp";
	public static String TrainingServer= "hasbroplm-training.ptcmanaged.com/Windchill/rfa/jsp/main/Main.jsp";
	public static String vmURL= "p2m-julian-dev.na.hasbro.com/Windchill/";
	
	public static By welcomeMsg = By.xpath("//td[contains(text(),'Welcome to FlexPLM')]");
	
	public static void initialize() throws Exception{

		if(!isInitialized){
			//initialize log4j logging
			log=Logger.getLogger("devpinoyLogger");

			//initialize properties file
			prop=new Properties();
			FileInputStream ip1=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\config.properties");
			prop.load(ip1);
			log.debug(prop.get("propFile"));
			//initialize xls files
			suiteXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Suite.xlsx");
			suiteSecurityXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Security.xlsx");
			suiteSmokeTestXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\WorkflowTC.xlsx");
			suiteCascadingXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Cascading.xlsx");
			suiteViewsXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Views.xlsx");
			suiteCIExcelXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\CIExcel.xlsx");
			suiteSanityXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\SanitySuite2018.xlsx");
			isInitialized=true;
		}
	}

	public static void openBrowser(){
		
		if(prop.getProperty("browserName").equalsIgnoreCase("mozilla")){
			ProfilesIni pr=new ProfilesIni();
			FirefoxProfile p=pr.getProfile("default");
			driver=new FirefoxDriver(p);
			/*FirefoxProfile fp = new FirefoxProfile();
			fp.setPreference("browser.download.folderList", 2);
			fp.setPreference("browser.download.manager.showWhenStarting", false);
			fp.setPreference("browser.download.dir", "C:\\TestAutomation\\AutomationSuites\\Selenium\\SPT\\SPTSIT2\\SPT_Automation_Framework\\download");
			fp.setPreference("browser.helperApps.neverAsk.openFile","application/pdf");
			fp.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
			fp.setPreference("browser.helperApps.alwaysAsk.force", false);
			fp.setPreference("browser.download.manager.alertOnEXEOpen", false);
			fp.setPreference("browser.download.manager.focusWhenStarting", false);
			fp.setPreference("browser.download.manager.useWindow", false);
			fp.setPreference("browser.download.manager.showAlertOnComplete", false);
			fp.setPreference("browser.download.manager.closeWhenDone", false);
	    	driver = new FirefoxDriver(fp);*/
		}
		else if(prop.getProperty("browserName").equalsIgnoreCase("IE")){
			/*System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();*/
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			cap.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "https://testvmm6.partnersonline.com/vmm");
			File file = new File("C:\\Selenium\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			cap.setCapability("IE.binary", "C:/Program Files (x86)/Internet Explorer/iexplore.exe");
			cap.setJavascriptEnabled(true);
			driver = new InternetExplorerDriver(cap); 
			System.out.println("IE browser launched sucessfully");
		}
		else if(prop.getProperty("browserName").equalsIgnoreCase("Chrome")){
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
		    driver = new ChromeDriver(options); 
			//driver.manage().window().maximize();
			//driver.get("chrome://extensions-frame");
		}
		
		
	}

	public static boolean launchApp(String username,String password) {
		try{
			//driver.manage().deleteAllCookies();
			//log.info("Driver Cookies has been deleted");
		//String Url = "https://"+username+":"+password+"@"+testURL;
		//String Url = "https://"+username+":"+password+"@"+devURL;
		//	String Url = "https://"+username+":"+password+"@"+Sandboxurl;
		Url = "https://"+username+":"+password+"@"+Sandboxurl;
		log.info("Url: "+Url);
		//driver.manage().timeouts().pageLoadTimeout(myAutomationWait, TimeUnit.SECONDS);
		driver.get(Url);
		CommonFunctions.waitForPageLoaded();
		driver.manage().window().maximize();
		verifyWelcome();
		wait=new WebDriverWait(driver,60);
		action = new Actions(driver);
		return true;
		}catch(Throwable t){
			log.info("Login error");
			driver.get(Url);
			ErrorUtil.addVerificationFailure(t);
			return false;
		}
	}
	
	public static boolean verifyWelcome() {
		try{
			SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			CommonFunctions.waitForPageLoaded();
			//wait=new WebDriverWait(driver,90);
			//wait.until(ExpectedConditions.titleIs("FlexPLM"));
		    System.out.println(driver.findElements(welcomeMsg).size());
			Assert.assertEquals(driver.findElements(welcomeMsg).size(),1);
			log.info("Home page is present");
		return true;
		}catch(Throwable t){
			log.info("*************************************");
			log.info("** Page not uploaded.Network Error **");
			log.info("*************************************");
			driver.get(Url);
			ErrorUtil.addVerificationFailure(t);
			return false;
		}
	}
	
	
	
	public static void closeBrowser(){
		driver.quit();
	}

	public static boolean verifyTitle(String ExpectedValue){
		try{
			Assert.assertEquals(ExpectedValue, driver.getTitle());
			System.out.println("Assert Verification");
		}catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			log.debug("Title do not match");
			return false;
		}
		return true;
	}
}
