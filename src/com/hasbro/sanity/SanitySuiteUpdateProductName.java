package com.hasbro.sanity;

import java.util.List;

import org.openqa.selenium.By;
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

import com.hasbro.security.Product;

import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

public class SanitySuiteUpdateProductName extends TestsuiteBase {

	String runmodes[]=null;
	static int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	String loginVal;
	Boolean flaglogin=false;
	int y=0;
	
	public static String AssortmentProductNumber;
	
	public static By Segment                     = By.xpath("//td[contains(text(),'Segment')]//following::select[1]"); 
	public static By LowerAge                    = By.xpath("//td[contains(text(),'Lower Age')]//following::select[1]");
	public static By Gender                      = By.xpath("//td[contains(text(),'Gender')]//following::select[1]");
	public static By ISO                         = By.xpath("//td[contains(text(),'ISO')]//following::select[1]");
	public static By IndustryShortDescription    = By.xpath("//td[contains(text(),'Industry Short Description')]//following::input[1]");
	public static By InnovationType              = By.xpath("//td[contains(text(),'Innovation Type')]//following::select[1]");
	public static By IntroTiming                 = By.xpath("//td[contains(text(),'Intro Timing')]//following::select[1]");
	public static By OnShelfDate                 = By.xpath("//td[contains(text(),'Ast./Solid - On Shelf Date')]//following::input[1]");
	public static By ProjectType                 = By.xpath("//td[contains(text(),'Project Type')]//following::select[1]");
	public static By TVAd                        = By.xpath("//td[contains(text(),'TV Ad')]//following::select[1]");
	public static By Movie                       = By.xpath("//td[contains(text(),'Movie')]//following::select[1]");
	public static By DigitalProduct              = By.xpath("//td[contains(text(),'Digital Product')]//following::select[1]");
	public static By GSPlush                     = By.xpath("//td[contains(text(),'GS Plush')]//following::select[1]");
	public static By ShowAll                     = By.xpath("//a[text()='Show All']");
	public static By AS0Colorway                 = By.xpath("//a[text()='AS0']//preceding::a[1][text()='(choose)']");
	public static By NewSpring                   = By.xpath("//td[contains(text(),'New Spring')]//following::input[1]");
	public static By SourceCheckBox              = By.xpath("//div[@id='sourcesOptionsdiv_plus']/input");
	public static By ProductName                 = By.xpath("//td[contains(text(),'*Product Name')]//following::input[1]");            
	public static By UpdateActionDropDown        = By.xpath("//select[@id='prodseasonActions']");
	public static By SaveButton                  = By.xpath("//a[text()='Save']");
	
	
	@Test(dataProvider="testDataTest")
	//public void tcProduct(String login, String pwd, String AttributeGroup, String Verification,String Create, String SetState, String ReadView, String UpdateProduct,String UpdateProductSeason, String Delete,String SeasonYear,String LSAction,String LSViews) throws Exception{
	public void tcSCFunctional(String[] data) throws Exception{
		count++;
		System.out.println(runmodes[count]);
		if(!runmodes[count].equalsIgnoreCase("y")){
			skip=true;
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
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
					openBrowser();
					launchApp(data[0],data[1]);
					y++;
					System.out.println("y: "+y);
					loginVal=data[0];
					flaglogin=true;
				}
			}
	
			switch (data[2]) {
			
	        case "ProductSuite1":
			UpdateNameOfAssortment_RetailItems(data);
			break;
			
	        case "ProductSuite2":
			UpdateNameOfAssortment_RetailItems(data);
			break;
			
	        case "ProductSuite3":
			UpdateNameOfAssortment_RetailItems(data);
			break;
			
	        case "ProductSuite4":
			UpdateNameOfAssortment_RetailItems(data);
			break;
			
	        case "ProductSuite5":
			UpdateNameOfAssortment_RetailItems(data);
			break;
			
	        case "ProductSuite6":
			UpdateNameOfAssortment_RetailItems(data);
			break;
			
	        case "ProductSuite7":
			UpdateNameOfAssortment_RetailItems(data);
			break;	
			
	        case "ProductSuite8":
			UpdateNameOfAssortment_RetailItems(data);
			break;
			
	        case "ProductSuite9":
			UpdateNameOfAssortment_RetailItems(data);
			break;
			
	        case "ProductSuite10":
				UpdateNameOfAssortment_RetailItems(data);
				break;
	        case "ProductSuite11":
				UpdateNameOfAssortment_RetailItems(data);
				break;
	        case "ProductSuite12":
				UpdateNameOfAssortment_RetailItems(data);
				break;
	        case "ProductSuite13":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
	        case "ProductSuite14":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
	        case "ProductSuite15":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
	        case "ProductSuite16":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
	        case "ProductSuite17":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
	        case "ProductSuite18":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
	        case "ProductSuite19":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
	        case "ProductSuite20":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
				
	        case "ProductSuite21":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
	        case "ProductSuite22":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
	        case "ProductSuite23":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
	        case "ProductSuite24":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
	        case "ProductSuite25":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
	        case "ProductSuite26":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		   case "ProductSuite27":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		   case "ProductSuite28":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite29":
				UpdateNameOfAssortment_RetailItems(data);
				break;		
				
		   case "ProductSuite30":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite31":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite32":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
			
		   case "ProductSuite33":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		   case "ProductSuite34":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite35":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite36":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite37":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite38":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
			
		   case "ProductSuite39":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		   case "ProductSuite40":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite41":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		   case "ProductSuite42":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
			
		   case "ProductSuite43":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
		   case "ProductSuite44":
				UpdateNameOfAssortment_RetailItems(data);
				break;		
			
		   case "ProductSuite45":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
			
		   case "ProductSuite46":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
		   case "ProductSuite47":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
			
		   case "ProductSuite48":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
		   case "ProductSuite49":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
		   case "ProductSuite50":
				UpdateNameOfAssortment_RetailItems(data);
				break;
			
		   case "ProductSuite51":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		        case "ProductSuite52":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		        case "ProductSuite53":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		        case "ProductSuite54":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		        case "ProductSuite55":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		        case "ProductSuite56":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		        case "ProductSuite57":
				UpdateNameOfAssortment_RetailItems(data);
				break;	
				
		        case "ProductSuite58":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		        case "ProductSuite59":
				UpdateNameOfAssortment_RetailItems(data);
				break;
				
		        case "ProductSuite60":
					UpdateNameOfAssortment_RetailItems(data);
					break;
		        case "ProductSuite61":
					UpdateNameOfAssortment_RetailItems(data);
					break;
		        case "ProductSuite62":
					UpdateNameOfAssortment_RetailItems(data);
					break;
		        case "ProductSuite63":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
		        case "ProductSuite64":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
		        case "ProductSuite65":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
		        case "ProductSuite66":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
		        case "ProductSuite67":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
		        case "ProductSuite68":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
		        case "ProductSuite69":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
		        case "ProductSuite70":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
					
		        case "ProductSuite71":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
		        case "ProductSuite72":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
		        case "ProductSuite73":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
		        case "ProductSuite74":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
		        case "ProductSuite75":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
		        case "ProductSuite76":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			   case "ProductSuite77":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			   case "ProductSuite78":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite79":
					UpdateNameOfAssortment_RetailItems(data);
					break;		
					
			   case "ProductSuite80":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite81":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite82":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
				
			   case "ProductSuite83":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			   case "ProductSuite84":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite85":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite86":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite87":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite88":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
				
			   case "ProductSuite89":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			   case "ProductSuite90":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite91":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			   case "ProductSuite92":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
				
			   case "ProductSuite93":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
			   case "ProductSuite94":
					UpdateNameOfAssortment_RetailItems(data);
					break;		
				
			   case "ProductSuite95":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
				
			   case "ProductSuite96":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
			   case "ProductSuite97":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
				
			   case "ProductSuite98":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
			   case "ProductSuite99":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
			   case "ProductSuite100":
					UpdateNameOfAssortment_RetailItems(data);
					break;
				
			   case "ProductSuite101":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			        case "ProductSuite102":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			        case "ProductSuite103":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			        case "ProductSuite104":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			        case "ProductSuite105":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			        case "ProductSuite106":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			        case "ProductSuite107":
					UpdateNameOfAssortment_RetailItems(data);
					break;	
					
			        case "ProductSuite108":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			        case "ProductSuite109":
					UpdateNameOfAssortment_RetailItems(data);
					break;
					
			        case "ProductSuite110":
						UpdateNameOfAssortment_RetailItems(data);
						break;
			        case "ProductSuite111":
						UpdateNameOfAssortment_RetailItems(data);
						break;
			        case "ProductSuite112":
						UpdateNameOfAssortment_RetailItems(data);
						break;
			        case "ProductSuite113":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
			        case "ProductSuite114":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
			        case "ProductSuite115":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
			        case "ProductSuite116":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
			        case "ProductSuite117":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
			        case "ProductSuite118":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
			        case "ProductSuite119":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
			        case "ProductSuite120":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
						
			        case "ProductSuite121":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
			        case "ProductSuite122":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
			        case "ProductSuite123":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
			        case "ProductSuite124":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
			        case "ProductSuite125":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
			        case "ProductSuite126":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				   case "ProductSuite127":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				   case "ProductSuite128":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite129":
						UpdateNameOfAssortment_RetailItems(data);
						break;		
						
				   case "ProductSuite130":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite131":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite132":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
					
				   case "ProductSuite133":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				   case "ProductSuite134":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite135":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite136":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite137":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite138":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
					
				   case "ProductSuite139":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				   case "ProductSuite140":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite141":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				   case "ProductSuite142":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
					
				   case "ProductSuite143":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
				   case "ProductSuite144":
						UpdateNameOfAssortment_RetailItems(data);
						break;		
					
				   case "ProductSuite145":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
					
				   case "ProductSuite146":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
				   case "ProductSuite147":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
					
				   case "ProductSuite148":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
				   case "ProductSuite149":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
				   case "ProductSuite150":
						UpdateNameOfAssortment_RetailItems(data);
						break;
					
				   case "ProductSuite151":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				        case "ProductSuite152":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				        case "ProductSuite153":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				        case "ProductSuite154":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				        case "ProductSuite155":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				        case "ProductSuite156":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				        case "ProductSuite157":
						UpdateNameOfAssortment_RetailItems(data);
						break;	
						
				        case "ProductSuite158":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				        case "ProductSuite159":
						UpdateNameOfAssortment_RetailItems(data);
						break;
						
				        case "ProductSuite160":
							UpdateNameOfAssortment_RetailItems(data);
							break;
				        case "ProductSuite161":
							UpdateNameOfAssortment_RetailItems(data);
							break;
				        case "ProductSuite162":
							UpdateNameOfAssortment_RetailItems(data);
							break;
				        case "ProductSuite163":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
				        case "ProductSuite164":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
				        case "ProductSuite165":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
				        case "ProductSuite166":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
				        case "ProductSuite167":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
				        case "ProductSuite168":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
				        case "ProductSuite169":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
				        case "ProductSuite170":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
							
				        case "ProductSuite171":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
				        case "ProductSuite172":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
				        case "ProductSuite173":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
				        case "ProductSuite174":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
				        case "ProductSuite175":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
				        case "ProductSuite176":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					   case "ProductSuite177":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					   case "ProductSuite178":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite179":
							UpdateNameOfAssortment_RetailItems(data);
							break;		
							
					   case "ProductSuite180":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite181":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite182":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
						
					   case "ProductSuite183":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					   case "ProductSuite184":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite185":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite186":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite187":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite188":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
						
					   case "ProductSuite189":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					   case "ProductSuite190":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite191":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					   case "ProductSuite192":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
						
					   case "ProductSuite193":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
					   case "ProductSuite194":
							UpdateNameOfAssortment_RetailItems(data);
							break;		
						
					   case "ProductSuite195":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
						
					   case "ProductSuite196":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
					   case "ProductSuite197":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
						
					   case "ProductSuite198":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
					   case "ProductSuite199":
							UpdateNameOfAssortment_RetailItems(data);
							break;
						
					   case "ProductSuite200":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
					   case "ProductSuite201":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					        case "ProductSuite202":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					        case "ProductSuite203":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					        case "ProductSuite204":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					        case "ProductSuite205":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					        case "ProductSuite206":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					        case "ProductSuite207":
							UpdateNameOfAssortment_RetailItems(data);
							break;	
							
					        case "ProductSuite208":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					        case "ProductSuite209":
							UpdateNameOfAssortment_RetailItems(data);
							break;
							
					        case "ProductSuite210":
								UpdateNameOfAssortment_RetailItems(data);
								break;
					        case "ProductSuite211":
								UpdateNameOfAssortment_RetailItems(data);
								break;
					        case "ProductSuite212":
								UpdateNameOfAssortment_RetailItems(data);
								break;
					        case "ProductSuite213":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
					        case "ProductSuite214":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
					        case "ProductSuite215":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
					        case "ProductSuite216":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
					        case "ProductSuite217":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
					        case "ProductSuite218":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
					        case "ProductSuite219":
								UpdateNameOfAssortment_RetailItems(data);
								break;
								
					        case "ProductSuite220":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
								
					        case "ProductSuite221":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
					        case "ProductSuite222":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
					        case "ProductSuite223":
								UpdateNameOfAssortment_RetailItems(data);
								break;
								
					        case "ProductSuite224":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
					        case "ProductSuite225":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
					        case "ProductSuite226":
								UpdateNameOfAssortment_RetailItems(data);
								break;
								
						   case "ProductSuite227":
								UpdateNameOfAssortment_RetailItems(data);
								break;
								
						   case "ProductSuite228":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite229":
								UpdateNameOfAssortment_RetailItems(data);
								break;		
								
						   case "ProductSuite230":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite231":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite232":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
							
						   case "ProductSuite233":
								UpdateNameOfAssortment_RetailItems(data);
								break;
								
						   case "ProductSuite234":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite235":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite236":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite237":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite238":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
							
						   case "ProductSuite239":
								UpdateNameOfAssortment_RetailItems(data);
								break;
								
						   case "ProductSuite240":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite241":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
								
						   case "ProductSuite242":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
							
						   case "ProductSuite243":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
						   case "ProductSuite244":
								UpdateNameOfAssortment_RetailItems(data);
								break;		
							
						   case "ProductSuite245":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
							
						   case "ProductSuite246":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
						   case "ProductSuite247":
								UpdateNameOfAssortment_RetailItems(data);
								break;	
							
						   case "ProductSuite248":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
						   case "ProductSuite249":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
						   case "ProductSuite250":
								UpdateNameOfAssortment_RetailItems(data);
								break;
							
				
						
						
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
	
	
	public static boolean UpdateNameOfAssortment_RetailItems(String [] data) throws Exception{
		try{
			SearchAssortmentProduct(data);
			//Searching the Assortment Product
			updateProductNameandDescriptionforAssortmentProduct(data);
			//Updating the Name And Industry Description
			SearchReatilItemProduct1(data);
			//Search Retail Item Product1
			updateProductNameandDescriptionforRetailItem1Product(data);
			//Updating the Name And Industry Description
			SearchReatilItemProduct2(data);
			//Search Retail Item Product2
			updateProductNameandDescriptionforRetailItem2Product(data);
			//Updating the Name And Industry Description
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in UpdateNameOfAssortment_RetailItems"+e);
			throw e;
			}
		return true;
	}
	
	
	public static boolean updateProductNameandDescriptionforAssortmentProduct(String [] data) throws Exception{
		try{
			   wait.until(ExpectedConditions.visibilityOfElementLocated(UpdateActionDropDown));
			   Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
			     List<WebElement> allOptions=dropdDown.getOptions();
		      for(int i=0;i<allOptions.size();i++){
			     String RequiredValue=allOptions.get(i).getText();
			    // System.out.println(RequiredValue);
			     if(RequiredValue.contains(data[7]))
			     {
			     CommonFunctions.selectFromDropDownByVisibleTextUpdated(UpdateActionDropDown, RequiredValue, "Source DropDown Selection");
			     wait.until(ExpectedConditions.titleIs(data[7]));
			    CommonFunctions.waitForPageLoaded();
			     break;
			     }
		      }
		        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductName));
		        CommonFunctions.clearTextBox(ProductName, "ProductName");
				CommonFunctions.enterTextInTextboxUpdated(ProductName, data[8], "ProductName");
				CommonFunctions.waitForElementTobeClickable(IndustryShortDescription);
			    CommonFunctions.clearTextBox(IndustryShortDescription, "IndustryShortDescription");
				CommonFunctions.enterTextInTextboxUpdated(IndustryShortDescription, data[9], "IndustryShortDescription");
				CommonFunctions.waitForElementTobeClickable(SaveButton);
				CommonFunctions.clickButtonOrLink(SaveButton, "Button", "SaveButton");
			    wait.until(ExpectedConditions.titleIs(data[14]));
			    CommonFunctions.waitForPageLoaded();
				
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in updateProductNameandDescription"+e);
			throw e;
		}
		return true;
	}
	public static boolean updateProductNameandDescriptionforRetailItem1Product(String [] data) throws Exception{
		try{
			   wait.until(ExpectedConditions.visibilityOfElementLocated(UpdateActionDropDown));
			   Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
			     List<WebElement> allOptions=dropdDown.getOptions();
		      for(int i=0;i<allOptions.size();i++){
			     String RequiredValue=allOptions.get(i).getText();
			    // System.out.println(RequiredValue);
			     if(RequiredValue.contains(data[7]))
			     {
			     CommonFunctions.selectFromDropDownByVisibleTextUpdated(UpdateActionDropDown, RequiredValue, "Source DropDown Selection");
			     wait.until(ExpectedConditions.titleIs(data[7]));
			    CommonFunctions.waitForPageLoaded();
			     break;
			     }
		      }
		        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductName));
		        CommonFunctions.clearTextBox(ProductName, "ProductName");
				CommonFunctions.enterTextInTextboxUpdated(ProductName, data[10], "ProductName");
				CommonFunctions.waitForElementTobeClickable(IndustryShortDescription);
			    CommonFunctions.clearTextBox(IndustryShortDescription, "IndustryShortDescription");
				CommonFunctions.enterTextInTextboxUpdated(IndustryShortDescription, data[11], "IndustryShortDescription");
				CommonFunctions.waitForElementTobeClickable(SaveButton);
				CommonFunctions.clickButtonOrLink(SaveButton, "Button", "SaveButton");
			    wait.until(ExpectedConditions.titleIs(data[14]));
			    CommonFunctions.waitForPageLoaded();
				
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in updateProductNameandDescription"+e);
			throw e;
		}
		return true;
	}
	public static boolean updateProductNameandDescriptionforRetailItem2Product(String [] data) throws Exception{
		try{
			   wait.until(ExpectedConditions.visibilityOfElementLocated(UpdateActionDropDown));
			   Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
			     List<WebElement> allOptions=dropdDown.getOptions();
		      for(int i=0;i<allOptions.size();i++){
			     String RequiredValue=allOptions.get(i).getText();
			    // System.out.println(RequiredValue);
			     if(RequiredValue.contains(data[7]))
			     {
			     CommonFunctions.selectFromDropDownByVisibleTextUpdated(UpdateActionDropDown, RequiredValue, "Source DropDown Selection");
			     wait.until(ExpectedConditions.titleIs(data[7]));
			    CommonFunctions.waitForPageLoaded();
			     break;
			     }
		      }
		        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductName));
		        CommonFunctions.clearTextBox(ProductName, "ProductName");
				CommonFunctions.enterTextInTextboxUpdated(ProductName, data[12], "ProductName");
				CommonFunctions.waitForElementTobeClickable(IndustryShortDescription);
			    CommonFunctions.clearTextBox(IndustryShortDescription, "IndustryShortDescription");
				CommonFunctions.enterTextInTextboxUpdated(IndustryShortDescription, data[13], "IndustryShortDescription");
				CommonFunctions.waitForElementTobeClickable(SaveButton);
				CommonFunctions.clickButtonOrLink(SaveButton, "Button", "SaveButton");
			    wait.until(ExpectedConditions.titleIs(data[14]));
			    CommonFunctions.waitForPageLoaded();
				
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in updateProductNameandDescription"+e);
			throw e;
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
			  CommonFunctions.enterTextInTextbox(CommonProjectFunctions.searchProduct,data[3]);
			  CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchIcon);
			  CommonFunctions.clickButtonOrLink(CommonProjectFunctions.searchIcon, "Btn", "SearchButton");
			  //Clicking Search Button
			  driver.switchTo().defaultContent();
		      driver.switchTo().frame("contentframe");
		      CommonFunctions.waitForPageLoaded();
			 NavigateToDetailsTab(data);
				//Navigating to details Tab
	       CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in SearchAssortmentProduct"+e);
			throw e;
		}
		return true;
	}
	
	
	public static boolean SearchReatilItemProduct1(String [] data) throws Exception{
		try{
			  driver.switchTo().defaultContent();
		      driver.switchTo().frame("headerframe");
		      //Switching into Header Frame
		      CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchProduct);
			  SeleniumDriver.driver.findElement(CommonProjectFunctions.searchProduct).clear();
			  CommonFunctions.enterTextInTextbox(CommonProjectFunctions.searchProduct,data[5]);
			  CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchIcon);
			  CommonFunctions.clickButtonOrLink(CommonProjectFunctions.searchIcon, "Btn", "SearchButton");
			  //Clicking Search Button
			  driver.switchTo().defaultContent();
		      driver.switchTo().frame("contentframe");
		      CommonFunctions.waitForPageLoaded();
			 NavigateToDetailsTab(data);
				//Navigating to details Tab
		    CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in SearchAssortmentProduct"+e);
			throw e;
		}
		return true;
	}
	
	
	public static boolean SearchReatilItemProduct2(String [] data) throws Exception{
		try{
			  driver.switchTo().defaultContent();
		      driver.switchTo().frame("headerframe");
		      //Switching into Header Frame
		      CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchProduct);
			  SeleniumDriver.driver.findElement(CommonProjectFunctions.searchProduct).clear();
			  CommonFunctions.enterTextInTextbox(CommonProjectFunctions.searchProduct,data[6]);
			  CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchIcon);
			  CommonFunctions.clickButtonOrLink(CommonProjectFunctions.searchIcon, "Btn", "SearchButton");
			  //Clicking Search Button
			  driver.switchTo().defaultContent();
		      driver.switchTo().frame("contentframe");
		      CommonFunctions.waitForPageLoaded();
			 NavigateToDetailsTab(data);
				//Navigating to details Tab
			 CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in SearchAssortmentProduct"+e);
			throw e;
		}
		return true;
	}
	public static boolean NavigateToDetailsTab(String[] data) throws Exception{
		try{
			 wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.Details));
			 CommonFunctions.waitForPageLoaded();
			CommonFunctions.clickButtonOrLink(SanitySuite1.Details, "link", "Details");
			 //Click on Details Tab
			// if(wait.until(ExpectedConditions.titleIs(data[144]))||wait.until(ExpectedConditions.titleIs(data[222])))
			 //{
				 CommonFunctions.waitForPageLoaded();
				 log.info("Product Details Page Appears");
			// }
			
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in NavigateToDetailsTab"+e);
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
			if(skip)
				Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "SKIP");
			
			else if(fail||CommonFunctions.error){
				Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "FAIL");
				isTestPass=false;
		}
			else
				Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "PASS");
			  
			skip=false;
			fail=false;
			CommonFunctions.error=false;	
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
		public Object[] testDataTest(){
			return Utility.getData(suiteSanityXls, this.getClass().getSimpleName());
		}

	
		
	}

