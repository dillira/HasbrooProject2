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

import com.hasbro.security.InternalBOMSoftG;
import com.hasbro.security.Product;

import util.CommonFunctions;
import util.CommonProjectFunctions;
import util.ErrorUtil;
import util.Utility;

public class SanitySuite_AssortmentRetail extends TestsuiteBase {

	String runmodes[]=null;
	static int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	String loginVal;
	Boolean flaglogin=false;
	int y=0;

	public static By ManualInputWaveForeCast     =By.xpath("//td[contains(text(),'Manually Input Wave Forecasts')]//following::input[2]");
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
	public static By CoBrand                     = By.xpath("//td[contains(text(),'Co-Brand')]//following::select[1]");
	public static By ThirdpartyIndicator         = By.xpath("//td[contains(text(),'Third Party Indicator')]//following::select[1]");
	public static By Description                 = By.xpath("//td[contains(text(),'Description')]//following::textarea[1]");
	
	
	
	//Variables
	public static String AssortmentProductNumber;
	public static String CopyLinkRetailItem3;
	
	
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
	
			switch (data[3]) {
			
	        case "ProductSuite1":
			CreateProductSuite(data);
			break;
			
	        case "ProductSuite2":
	        CreateProductSuite2(data);
			break;
			
	    /*    case "ProductSuite3":
			CreateProductSuite(data);
			break;
			
	        case "ProductSuite4":
			CreateProductSuite(data);
			break;
			
	        case "ProductSuite5":
			CreateProductSuite(data);
			break;
			
	        case "ProductSuite6":
			CreateProductSuite(data);
			break;
			
	        case "ProductSuite7":
			CreateProductSuite(data);
			break;	
			
	        case "ProductSuite8":
			CreateProductSuite(data);
			break;
			
	        case "ProductSuite9":
			CreateProductSuite(data);
			break;
			
	        case "ProductSuite10":
				CreateProductSuite(data);
				break;
	        case "ProductSuite11":
				CreateProductSuite(data);
				break;
	        case "ProductSuite12":
				CreateProductSuite(data);
				break;
	        case "ProductSuite13":
				CreateProductSuite(data);
				break;
			
	        case "ProductSuite14":
				CreateProductSuite(data);
				break;
			
	        case "ProductSuite15":
				CreateProductSuite(data);
				break;
			
	        case "ProductSuite16":
				CreateProductSuite(data);
				break;
			
	        case "ProductSuite17":
				CreateProductSuite(data);
				break;
			
	        case "ProductSuite18":
				CreateProductSuite(data);
				break;
			
	        case "ProductSuite19":
				CreateProductSuite(data);
				break;
				
	        case "ProductSuite20":
				CreateProductSuite(data);
				break;	
				
				
	        case "ProductSuite21":
				CreateProductSuite(data);
				break;	
				
	        case "ProductSuite22":
				CreateProductSuite(data);
				break;	
				
	        case "ProductSuite23":
				CreateProductSuite(data);
				break;
				
	        case "ProductSuite24":
				CreateProductSuite(data);
				break;
			
	        case "ProductSuite25":
				CreateProductSuite(data);
				break;	
				
	        case "ProductSuite26":
				CreateProductSuite(data);
				break;
				
		   case "ProductSuite27":
				CreateProductSuite(data);
				break;
				
		   case "ProductSuite28":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite29":
				CreateProductSuite(data);
				break;		
				
		   case "ProductSuite30":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite31":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite32":
				CreateProductSuite(data);
				break;	
			
		   case "ProductSuite33":
				CreateProductSuite(data);
				break;
				
		   case "ProductSuite34":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite35":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite36":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite37":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite38":
				CreateProductSuite(data);
				break;	
			
		   case "ProductSuite39":
				CreateProductSuite(data);
				break;
				
		   case "ProductSuite40":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite41":
				CreateProductSuite(data);
				break;	
				
		   case "ProductSuite42":
				CreateProductSuite(data);
				break;	
			
		   case "ProductSuite43":
				CreateProductSuite(data);
				break;
			
		   case "ProductSuite44":
				CreateProductSuite(data);
				break;		
			
		   case "ProductSuite45":
				CreateProductSuite(data);
				break;	
			
		   case "ProductSuite46":
				CreateProductSuite(data);
				break;
			
		   case "ProductSuite47":
				CreateProductSuite(data);
				break;	
			
		   case "ProductSuite48":
				CreateProductSuite(data);
				break;
			
		   case "ProductSuite49":
				CreateProductSuite(data);
				break;
			
		   case "ProductSuite50":
				CreateProductSuite(data);
				break;
			
		   case "ProductSuite51":
				CreateProductSuite(data);
				break;
				
		        case "ProductSuite52":
				CreateProductSuite(data);
				break;
				
		        case "ProductSuite53":
				CreateProductSuite(data);
				break;
				
		        case "ProductSuite54":
				CreateProductSuite(data);
				break;
				
		        case "ProductSuite55":
				CreateProductSuite(data);
				break;
				
		        case "ProductSuite56":
				CreateProductSuite(data);
				break;
				
		        case "ProductSuite57":
				CreateProductSuite(data);
				break;	
				
		        case "ProductSuite58":
				CreateProductSuite(data);
				break;
				
		        case "ProductSuite59":
				CreateProductSuite(data);
				break;
				
		        case "ProductSuite60":
					CreateProductSuite(data);
					break;
		        case "ProductSuite61":
					CreateProductSuite(data);
					break;
		        case "ProductSuite62":
					CreateProductSuite(data);
					break;
		        case "ProductSuite63":
					CreateProductSuite(data);
					break;
				
		        case "ProductSuite64":
					CreateProductSuite(data);
					break;
				
		        case "ProductSuite65":
					CreateProductSuite(data);
					break;
				
		        case "ProductSuite66":
					CreateProductSuite(data);
					break;
				
		        case "ProductSuite67":
					CreateProductSuite(data);
					break;
				
		        case "ProductSuite68":
					CreateProductSuite(data);
					break;
				
		        case "ProductSuite69":
					CreateProductSuite(data);
					break;
					
		        case "ProductSuite70":
					CreateProductSuite(data);
					break;	
					
					
		        case "ProductSuite71":
					CreateProductSuite(data);
					break;	
					
		        case "ProductSuite72":
					CreateProductSuite(data);
					break;	
					
		        case "ProductSuite73":
					CreateProductSuite(data);
					break;
					
		        case "ProductSuite74":
					CreateProductSuite(data);
					break;
				
		        case "ProductSuite75":
					CreateProductSuite(data);
					break;	
					
		        case "ProductSuite76":
					CreateProductSuite(data);
					break;
					
			   case "ProductSuite77":
					CreateProductSuite(data);
					break;
					
			   case "ProductSuite78":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite79":
					CreateProductSuite(data);
					break;		
					
			   case "ProductSuite80":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite81":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite82":
					CreateProductSuite(data);
					break;	
				
			   case "ProductSuite83":
					CreateProductSuite(data);
					break;
					
			   case "ProductSuite84":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite85":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite86":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite87":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite88":
					CreateProductSuite(data);
					break;	
				
			   case "ProductSuite89":
					CreateProductSuite(data);
					break;
					
			   case "ProductSuite90":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite91":
					CreateProductSuite(data);
					break;	
					
			   case "ProductSuite92":
					CreateProductSuite(data);
					break;	
				
			   case "ProductSuite93":
					CreateProductSuite(data);
					break;
				
			   case "ProductSuite94":
					CreateProductSuite(data);
					break;		
				
			   case "ProductSuite95":
					CreateProductSuite(data);
					break;	
				
			   case "ProductSuite96":
					CreateProductSuite(data);
					break;
				
			   case "ProductSuite97":
					CreateProductSuite(data);
					break;	
				
			   case "ProductSuite98":
					CreateProductSuite(data);
					break;
				
			   case "ProductSuite99":
					CreateProductSuite(data);
					break;
				
			   case "ProductSuite100":
					CreateProductSuite(data);
					break;
				
			   case "ProductSuite101":
					CreateProductSuite(data);
					break;
					
			        case "ProductSuite102":
					CreateProductSuite(data);
					break;
					
			        case "ProductSuite103":
					CreateProductSuite(data);
					break;
					
			        case "ProductSuite104":
					CreateProductSuite(data);
					break;
					
			        case "ProductSuite105":
					CreateProductSuite(data);
					break;
					
			        case "ProductSuite106":
					CreateProductSuite(data);
					break;
					
			        case "ProductSuite107":
					CreateProductSuite(data);
					break;	
					
			        case "ProductSuite108":
					CreateProductSuite(data);
					break;
					
			        case "ProductSuite109":
					CreateProductSuite(data);
					break;
					
			        case "ProductSuite110":
						CreateProductSuite(data);
						break;
			        case "ProductSuite111":
						CreateProductSuite(data);
						break;
			        case "ProductSuite112":
						CreateProductSuite(data);
						break;
			        case "ProductSuite113":
						CreateProductSuite(data);
						break;
					
			        case "ProductSuite114":
						CreateProductSuite(data);
						break;
					
			        case "ProductSuite115":
						CreateProductSuite(data);
						break;
					
			        case "ProductSuite116":
						CreateProductSuite(data);
						break;
					
			        case "ProductSuite117":
						CreateProductSuite(data);
						break;
					
			        case "ProductSuite118":
						CreateProductSuite(data);
						break;
					
			        case "ProductSuite119":
						CreateProductSuite(data);
						break;
						
			        case "ProductSuite120":
						CreateProductSuite(data);
						break;	
						
						
			        case "ProductSuite121":
						CreateProductSuite(data);
						break;	
						
			        case "ProductSuite122":
						CreateProductSuite(data);
						break;	
						
			        case "ProductSuite123":
						CreateProductSuite(data);
						break;
						
			        case "ProductSuite124":
						CreateProductSuite(data);
						break;
					
			        case "ProductSuite125":
						CreateProductSuite(data);
						break;	
						
			        case "ProductSuite126":
						CreateProductSuite(data);
						break;
						
				   case "ProductSuite127":
						CreateProductSuite(data);
						break;
						
				   case "ProductSuite128":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite129":
						CreateProductSuite(data);
						break;		
						
				   case "ProductSuite130":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite131":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite132":
						CreateProductSuite(data);
						break;	
					
				   case "ProductSuite133":
						CreateProductSuite(data);
						break;
						
				   case "ProductSuite134":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite135":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite136":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite137":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite138":
						CreateProductSuite(data);
						break;	
					
				   case "ProductSuite139":
						CreateProductSuite(data);
						break;
						
				   case "ProductSuite140":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite141":
						CreateProductSuite(data);
						break;	
						
				   case "ProductSuite142":
						CreateProductSuite(data);
						break;	
					
				   case "ProductSuite143":
						CreateProductSuite(data);
						break;
					
				   case "ProductSuite144":
						CreateProductSuite(data);
						break;		
					
				   case "ProductSuite145":
						CreateProductSuite(data);
						break;	
					
				   case "ProductSuite146":
						CreateProductSuite(data);
						break;
					
				   case "ProductSuite147":
						CreateProductSuite(data);
						break;	
					
				   case "ProductSuite148":
						CreateProductSuite(data);
						break;
					
				   case "ProductSuite149":
						CreateProductSuite(data);
						break;
					
				   case "ProductSuite150":
						CreateProductSuite(data);
						break;
					
				   case "ProductSuite151":
						CreateProductSuite(data);
						break;
						
				        case "ProductSuite152":
						CreateProductSuite(data);
						break;
						
				        case "ProductSuite153":
						CreateProductSuite(data);
						break;
						
				        case "ProductSuite154":
						CreateProductSuite(data);
						break;
						
				        case "ProductSuite155":
						CreateProductSuite(data);
						break;
						
				        case "ProductSuite156":
						CreateProductSuite(data);
						break;
						
				        case "ProductSuite157":
						CreateProductSuite(data);
						break;	
						
				        case "ProductSuite158":
						CreateProductSuite(data);
						break;
						
				        case "ProductSuite159":
						CreateProductSuite(data);
						break;
						
				        case "ProductSuite160":
							CreateProductSuite(data);
							break;
				        case "ProductSuite161":
							CreateProductSuite(data);
							break;
				        case "ProductSuite162":
							CreateProductSuite(data);
							break;
				        case "ProductSuite163":
							CreateProductSuite(data);
							break;
						
				        case "ProductSuite164":
							CreateProductSuite(data);
							break;
						
				        case "ProductSuite165":
							CreateProductSuite(data);
							break;
						
				        case "ProductSuite166":
							CreateProductSuite(data);
							break;
						
				        case "ProductSuite167":
							CreateProductSuite(data);
							break;
						
				        case "ProductSuite168":
							CreateProductSuite(data);
							break;
						
				        case "ProductSuite169":
							CreateProductSuite(data);
							break;
							
				        case "ProductSuite170":
							CreateProductSuite(data);
							break;	
							
							
				        case "ProductSuite171":
							CreateProductSuite(data);
							break;	
							
				        case "ProductSuite172":
							CreateProductSuite(data);
							break;	
							
				        case "ProductSuite173":
							CreateProductSuite(data);
							break;
							
				        case "ProductSuite174":
							CreateProductSuite(data);
							break;
						
				        case "ProductSuite175":
							CreateProductSuite(data);
							break;	
							
				        case "ProductSuite176":
							CreateProductSuite(data);
							break;
							
					   case "ProductSuite177":
							CreateProductSuite(data);
							break;
							
					   case "ProductSuite178":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite179":
							CreateProductSuite(data);
							break;		
							
					   case "ProductSuite180":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite181":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite182":
							CreateProductSuite(data);
							break;	
						
					   case "ProductSuite183":
							CreateProductSuite(data);
							break;
							
					   case "ProductSuite184":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite185":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite186":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite187":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite188":
							CreateProductSuite(data);
							break;	
						
					   case "ProductSuite189":
							CreateProductSuite(data);
							break;
							
					   case "ProductSuite190":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite191":
							CreateProductSuite(data);
							break;	
							
					   case "ProductSuite192":
							CreateProductSuite(data);
							break;	
						
					   case "ProductSuite193":
							CreateProductSuite(data);
							break;
						
					   case "ProductSuite194":
							CreateProductSuite(data);
							break;		
						
					   case "ProductSuite195":
							CreateProductSuite(data);
							break;	
						
					   case "ProductSuite196":
							CreateProductSuite(data);
							break;
						
					   case "ProductSuite197":
							CreateProductSuite(data);
							break;	
						
					   case "ProductSuite198":
							CreateProductSuite(data);
							break;
						
					   case "ProductSuite199":
							CreateProductSuite(data);
							break;
						
					   case "ProductSuite200":
							CreateProductSuite(data);
							break;	
					   case "ProductSuite201":
							CreateProductSuite(data);
							break;
							
					        case "ProductSuite202":
							CreateProductSuite(data);
							break;
							
					        case "ProductSuite203":
							CreateProductSuite(data);
							break;
							
					        case "ProductSuite204":
							CreateProductSuite(data);
							break;
							
					        case "ProductSuite205":
							CreateProductSuite(data);
							break;
							
					        case "ProductSuite206":
							CreateProductSuite(data);
							break;
							
					        case "ProductSuite207":
							CreateProductSuite(data);
							break;	
							
					        case "ProductSuite208":
							CreateProductSuite(data);
							break;
							
					        case "ProductSuite209":
							CreateProductSuite(data);
							break;
							
					        case "ProductSuite210":
								CreateProductSuite(data);
								break;
					        case "ProductSuite211":
								CreateProductSuite(data);
								break;
					        case "ProductSuite212":
								CreateProductSuite(data);
								break;
					        case "ProductSuite213":
								CreateProductSuite(data);
								break;
							
					        case "ProductSuite214":
								CreateProductSuite(data);
								break;
							
					        case "ProductSuite215":
								CreateProductSuite(data);
								break;
							
					        case "ProductSuite216":
								CreateProductSuite(data);
								break;
							
					        case "ProductSuite217":
								CreateProductSuite(data);
								break;
							
					        case "ProductSuite218":
								CreateProductSuite(data);
								break;
							
					        case "ProductSuite219":
								CreateProductSuite(data);
								break;
								
					        case "ProductSuite220":
								CreateProductSuite(data);
								break;	
								
								
					        case "ProductSuite221":
								CreateProductSuite(data);
								break;	
								
					        case "ProductSuite222":
								CreateProductSuite(data);
								break;	
								
					        case "ProductSuite223":
								CreateProductSuite(data);
								break;
								
					        case "ProductSuite224":
								CreateProductSuite(data);
								break;
							
					        case "ProductSuite225":
								CreateProductSuite(data);
								break;	
								
					        case "ProductSuite226":
								CreateProductSuite(data);
								break;
								
						   case "ProductSuite227":
								CreateProductSuite(data);
								break;
								
						   case "ProductSuite228":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite229":
								CreateProductSuite(data);
								break;		
								
						   case "ProductSuite230":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite231":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite232":
								CreateProductSuite(data);
								break;	
							
						   case "ProductSuite233":
								CreateProductSuite(data);
								break;
								
						   case "ProductSuite234":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite235":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite236":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite237":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite238":
								CreateProductSuite(data);
								break;	
							
						   case "ProductSuite239":
								CreateProductSuite(data);
								break;
								
						   case "ProductSuite240":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite241":
								CreateProductSuite(data);
								break;	
								
						   case "ProductSuite242":
								CreateProductSuite(data);
								break;	
							
						   case "ProductSuite243":
								CreateProductSuite(data);
								break;
							
						   case "ProductSuite244":
								CreateProductSuite(data);
								break;		
							
						   case "ProductSuite245":
								CreateProductSuite(data);
								break;	
							
						   case "ProductSuite246":
								CreateProductSuite(data);
								break;
							
						   case "ProductSuite247":
								CreateProductSuite(data);
								break;	
							
						   case "ProductSuite248":
								CreateProductSuite(data);
								break;
							
						   case "ProductSuite249":
								CreateProductSuite(data);
								break;
							
						   case "ProductSuite250":
								CreateProductSuite(data);
								break;*/
							
						    
						
						
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
	
	
	public static boolean CreateProductSuite(String [] data) throws Exception{
		try{
			CreateAssortmentProdFromLineSheetForSanitySuite(data);
			//Creating a Product
			CreateSpecificationAndColorway(data);
			//Create a Specification and Color way
			CopyLinkProductRetailItem1(data);
			//Copy Retail Item 1
			SearchAssortmentProduct(data);
			//Searching Assortment or Solid Product
			CopyLinkProductRetailItem2(data);
			//Copy Retail Item 2
			CopyLinkProductRetailItem3(data);
			//Copy Retail Item 2
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateProductSuite" +e);
			throw e;
		}
		return true;
	}
	
	public static boolean CreateProductSuite2(String [] data) throws Exception{
		try{
			CreateAssortmentProdFromLineSheetForSanitySuite1(data);
			//Creating a Product
			CreateSpecificationAndColorway(data);
			//Create a Specification and Color way
			CopyLinkProductRetailItem1(data);
			//Copy Retail Item 1
			SearchAssortmentProduct(data);
			//Searching Assortment or Solid Product
			CopyLinkProductRetailItem2(data);
			//Copy Retail Item 2
			SearchAssortmentProduct(data);
			//Searching Assortment or Solid Product
			CopyLinkProductRetailItem3(data);
			//Copy Retail Item 3
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateProductSuite" +e);
			throw e;
		}
		return true;
	}
	
	public static String CreateAssortmentProdFromLineSheetForSanitySuite(String [] data) throws Exception{
		try{
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			
			CommonFunctions.selectFromDropDownByVisibleText(SanitySuite1.mySeasonYear, data[4]);
			//throw new Exception("No Such Element Exception");
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
		    SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			wait.until(ExpectedConditions.titleIs(data[5]));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetView, data[7]);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(Product.lineSheetAction);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetAction, data[6]);
			//Click on Assortment/Solid
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(By.xpath("//td[contains(text(),'Choose a Type')]"));
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"), "link", "Product Type");
			CommonFunctions.waitForPageLoaded();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(ProductName));
	        CommonFunctions.clearTextBox(ProductName, "ProductName");
			CommonFunctions.enterTextInTextboxUpdated(ProductName, data[58], "ProductName");
			if(!data[6].equalsIgnoreCase("Trademark Display")){
				//Select Class
				CommonFunctions.waitForElementTobeClickable(Product.Class);
				CommonFunctions.enterTextInTextbox(Product.Class, data[8]);
				CommonFunctions.waitForElementTobeClickable(Product.Division);
				CommonFunctions.enterTextInTextbox(Product.Division, data[9]);
				CommonFunctions.enterTextInTextbox(Product.Brand, data[10]);
				CommonFunctions.waitForElementTobeClickable(Segment);
				CommonFunctions.clickButtonOrLink(Segment,"Drop-Down", "Segment");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Segment, data[11], "Segment");
				if(!data[6].equalsIgnoreCase("Trade Marketing Pallet")){
					CommonFunctions.waitForElementTobeClickable(Product.InternalClassification);
					CommonFunctions.enterTextInTextbox(Product.InternalClassification,data[12]);
					//	CommonFunctions.enterTextInTextbox(AstSolid, productData[14]);
					CommonFunctions.waitForElementTobeClickable(Product.AstSolid);
					CommonFunctions.selectFromDropDownByVisibleText(Product.AstSolid, data[16]);
					//	CommonFunctions.enterTextInTextbox(IPSensitive,productData[16]);
					CommonFunctions.selectFromDropDownByVisibleText(Product.IPSensitive,data[17]);
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.SuperCategoryDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SuperCategoryDropDown, data[18], "Super category Drop Down");
					//Selecting the Super category Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.CategoryDropDown);
					CommonFunctions.clickButtonOrLink(SanitySuite1.CategoryDropDown, "DropDown", "CategoryDropDown");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CategoryDropDown, data[19], "Category Drop Down");
					//Selecting the  category Drop down value
		            CommonFunctions.waitForElementTobeClickable(SanitySuite1.LicensorDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.LicensorDropDown, data[20], "Licensor Drop Down");
					//Selecting the Licensor Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.PropertyDropDown);
					CommonFunctions.clickButtonOrLink(SanitySuite1.PropertyDropDown, "Drop-Down", "PropertyDropDown");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.PropertyDropDown, data[21], "Property Drop Down");
					//Selecting the Property Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.FamilyBrandDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.FamilyBrandDropDown, data[22], "Family Drop Down");
					//Selecting the Family Brand Drop down value
					/*CommonFunctions.waitForElementTobeClickable(SanitySuite1.CoBrandDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CoBrandDropDown, data[329], "Family Drop Down");*/
					//Selecting the Co-Brand Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.LowerAgeDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.LowerAgeDropDown, data[13], "Lower Age Drop Down");
					//Selecting the Lower age Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.UpperAgeDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.UpperAgeDropDown, data[23], "Upper Age Drop Down");
					//Selecting the Upper age Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.GenderDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.GenderDropDown, data[14], " Gender  Drop Down");
					//Selecting the gender Drop down value
					/*CommonFunctions.waitForElementTobeClickable(SanitySuite1.ThirdPartyIndicatorDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ThirdPartyIndicatorDropDown, data[331], "Third party Indicator  Drop Down");
					//Selecting the Third Party Indicator Drop down value*/
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.ISODropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ISODropDown, data[15], "ISO  Drop Down");
					//Selecting the Third Party Indicator Drop down value
					CommonFunctions.waitForElementTobeClickable(IndustryShortDescription);
					CommonFunctions.enterTextInTextboxUpdated(IndustryShortDescription, data[24], "IndustryShortDescription");
					//Selecting Innovation Type
				
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
			wait.until(ExpectedConditions.titleIs(data[25]));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.lebelProdNum);
			AssortmentProductNumber=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
			log.info("Product Number is "+AssortmentProductNumber);
		    CommonFunctions.waitForElementTobeClickable(Product.distributionChannel);
				CommonFunctions.enterTextInTextbox(Product.distributionChannel,data[26]);
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.TotalCost);
				CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.TotalCost, data[27], "Total Cost");
				CommonFunctions.waitForElementTobeClickable(InnovationType);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(InnovationType, data[28], "InnovationType");
				//Selecting a Innovation Type
				CommonFunctions.waitForElementTobeClickable(IntroTiming);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(IntroTiming, data[29], "IntroTiming");
				//Selecting a Intro Timing
				CommonFunctions.waitForElementTobeClickable(OnShelfDate);
				CommonFunctions.enterTextInTextboxUpdated(OnShelfDate, data[30], "OnShelfDate");
				CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,data[31]);
				CommonFunctions.enterTextInTextbox(Product.USDomestic,data[32]);
				CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,data[33]);
				CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,data[34]);
				CommonFunctions.enterTextInTextbox(Product.GlobalLamForecast,data[35]);
				CommonFunctions.enterTextInTextbox(Product.GlobalNAForeCast,data[36]);
				CommonFunctions.enterTextInTextbox(Product.GlobalEuForeCast,data[37]);
				CommonFunctions.enterTextInTextbox(Product.GlobalAsiaForeCast,data[38]);
				CommonFunctions.enterTextInTextbox(Product.GlobalPacificForecast,data[39]);
				CommonFunctions.waitForElementTobeClickable(ProjectType);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(ProjectType, data[40], "ProjectType");
				CommonFunctions.waitForElementTobeClickable(TVAd);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(TVAd, data[41], "TVAd");
				CommonFunctions.waitForElementTobeClickable(Movie);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Movie, data[42], "Movie");
				CommonFunctions.waitForElementTobeClickable(DigitalProduct);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(DigitalProduct, data[43], "DigitalProduct");
				CommonFunctions.waitForElementTobeClickable(GSPlush);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(GSPlush, data[44], "GSPlush");
			    CommonFunctions.waitForElementTobeClickable(NewSpring);
			    CommonFunctions.enterTextInTextboxUpdated(NewSpring, data[57], "NewSpring");
				/*if(data[6].equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.TotalCost);
					CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.TotalCost, data[27], "Total Cost");
					CommonFunctions.waitForElementTobeClickable(InnovationType);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(InnovationType, data[28], "InnovationType");
					//Selecting a Innovation Type
					CommonFunctions.waitForElementTobeClickable(IntroTiming);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(IntroTiming, data[29], "IntroTiming");
					//Selecting a Intro Timing
					CommonFunctions.waitForElementTobeClickable(OnShelfDate);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(OnShelfDate, data[30], "OnShelfDate");
					CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,data[31]);
					CommonFunctions.enterTextInTextbox(Product.USDomestic,data[32]);
					CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,data[33]);
					CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,data[34]);
					CommonFunctions.enterTextInTextbox(Product.GlobalLamForecast,data[35]);
					CommonFunctions.enterTextInTextbox(Product.GlobalNAForeCast,data[36]);
					CommonFunctions.enterTextInTextbox(Product.GlobalEuForeCast,data[37]);
					CommonFunctions.enterTextInTextbox(Product.GlobalAsiaForeCast,data[38]);
					CommonFunctions.enterTextInTextbox(Product.GlobalPacificForecast,data[39]);
					CommonFunctions.waitForElementTobeClickable(ProjectType);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(ProjectType, data[40], "ProjectType");
					CommonFunctions.waitForElementTobeClickable(TVAd);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(TVAd, data[41], "TVAd");
					CommonFunctions.waitForElementTobeClickable(Movie);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(Movie, data[42], "Movie");
					CommonFunctions.waitForElementTobeClickable(DigitalProduct);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(DigitalProduct, data[43], "DigitalProduct");
					CommonFunctions.waitForElementTobeClickable(GSPlush);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(GSPlush, data[44], "GSPlush");
				}
				if(data[6].equalsIgnoreCase("Retail")|| data[6].equalsIgnoreCase("Bundle Pack")){
					CommonFunctions.enterTextInTextbox(Product.targetCostUSD, strDOMPriceUSD);
				}
		}*/
			//Click View Product Button
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			wait.until(ExpectedConditions.titleIs(data[25]));
			CommonFunctions.waitForPageLoaded();
			log.info("View Season Product Information apge successfully appears");
			//	log.info(prodNum);
		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in CreateAssortmentProdFromLineSheetForSanitySuite()", e);
			throw e;
			
			 
		}
		return AssortmentProductNumber;
	}
	public static String CreateAssortmentProdFromLineSheetForSanitySuite1(String [] data) throws Exception{
		try{
			CommonProjectFunctions.clickMySeasonLink();
			//Select Season Year
			
			CommonFunctions.selectFromDropDownByVisibleText(SanitySuite1.mySeasonYear, data[4]);
			//throw new Exception("No Such Element Exception");
			//Click on Line Sheet link
			CommonFunctions.clickButtonOrLink(Product.lineSheet, "link", "Line Sheet");
		    SeleniumDriver.driver.switchTo().defaultContent();
			SeleniumDriver.driver.switchTo().frame("contentframe");
			wait.until(ExpectedConditions.titleIs(data[5]));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(Product.lineSheetView);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetView, data[7]);
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForVisibilityOfElement(Product.lineSheetAction);
			CommonFunctions.selectFromDropDownByVisibleText(Product.lineSheetAction, data[6]);
			//Click on Assortment/Solid
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(By.xpath("//td[contains(text(),'Choose a Type')]"));
			CommonFunctions.clickButtonOrLink(By.xpath("//a[contains(text(),'"+data[2]+"')]"), "link", "Product Type");
			CommonFunctions.waitForPageLoaded();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(ProductName));
	        CommonFunctions.clearTextBox(ProductName, "ProductName");
			CommonFunctions.enterTextInTextboxUpdated(ProductName, data[58], "ProductName");
			if(!data[6].equalsIgnoreCase("Trademark Display")){
				//Select Class
				CommonFunctions.waitForElementTobeClickable(Product.Class);
				CommonFunctions.enterTextInTextbox(Product.Class, data[8]);
				CommonFunctions.waitForElementTobeClickable(Product.Division);
				CommonFunctions.enterTextInTextbox(Product.Division, data[9]);
				CommonFunctions.enterTextInTextbox(Product.Brand, data[10]);
				CommonFunctions.waitForElementTobeClickable(Segment);
				CommonFunctions.clickButtonOrLink(Segment,"Drop-Down", "Segment");
				CommonFunctions.waitForPageLoaded();
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Segment, data[11], "Segment");
				if(!data[6].equalsIgnoreCase("Trade Marketing Pallet")){
					CommonFunctions.waitForElementTobeClickable(Product.InternalClassification);
					CommonFunctions.enterTextInTextbox(Product.InternalClassification,data[12]);
					//	CommonFunctions.enterTextInTextbox(AstSolid, productData[14]);
					CommonFunctions.waitForElementTobeClickable(Product.AstSolid);
					CommonFunctions.selectFromDropDownByVisibleText(Product.AstSolid, data[16]);
					//	CommonFunctions.enterTextInTextbox(IPSensitive,productData[16]);
					CommonFunctions.selectFromDropDownByVisibleText(Product.IPSensitive,data[17]);
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.SuperCategoryDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SuperCategoryDropDown, data[18], "Super category Drop Down");
					//Selecting the Super category Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.CategoryDropDown);
					CommonFunctions.clickButtonOrLink(SanitySuite1.CategoryDropDown, "DropDown", "CategoryDropDown");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CategoryDropDown, data[19], "Category Drop Down");
					//Selecting the  category Drop down value
		            CommonFunctions.waitForElementTobeClickable(SanitySuite1.LicensorDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.LicensorDropDown, data[20], "Licensor Drop Down");
					//Selecting the Licensor Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.PropertyDropDown);
					CommonFunctions.clickButtonOrLink(SanitySuite1.PropertyDropDown, "Drop-Down", "PropertyDropDown");
					CommonFunctions.waitForPageLoaded();
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.PropertyDropDown, data[21], "Property Drop Down");
					//Selecting the Property Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.FamilyBrandDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.FamilyBrandDropDown, data[22], "Family Drop Down");
					//Selecting the Family Brand Drop down value
					/*CommonFunctions.waitForElementTobeClickable(SanitySuite1.CoBrandDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.CoBrandDropDown, data[329], "Family Drop Down");*/
					//Selecting the Co-Brand Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.LowerAgeDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.LowerAgeDropDown, data[13], "Lower Age Drop Down");
					//Selecting the Lower age Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.UpperAgeDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.UpperAgeDropDown, data[23], "Upper Age Drop Down");
					//Selecting the Upper age Drop down value
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.GenderDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.GenderDropDown, data[14], " Gender  Drop Down");
					//Selecting the gender Drop down value
					/*CommonFunctions.waitForElementTobeClickable(SanitySuite1.ThirdPartyIndicatorDropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ThirdPartyIndicatorDropDown, data[331], "Third party Indicator  Drop Down");
					//Selecting the Third Party Indicator Drop down value*/
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.ISODropDown);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ISODropDown, data[15], "ISO  Drop Down");
					//Selecting the Third Party Indicator Drop down value
					CommonFunctions.waitForElementTobeClickable(IndustryShortDescription);
					CommonFunctions.enterTextInTextboxUpdated(IndustryShortDescription, data[24], "IndustryShortDescription");
					//Selecting Innovation Type
					CommonFunctions.waitForElementTobeClickable(CoBrand);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(CoBrand, data[65], "Co-Brand");
					//Selecting Co-Brand
					CommonFunctions.waitForElementTobeClickable(ThirdpartyIndicator);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(ThirdpartyIndicator, data[66], "ThirdpartyIndicator");
					//Selecting Co-Brand
				
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
			wait.until(ExpectedConditions.titleIs(data[25]));
			CommonFunctions.waitForPageLoaded();
			CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.lebelProdNum);
			AssortmentProductNumber=SeleniumDriver.driver.findElement(CommonProjectFunctions.lebelProdNum).getText();
			log.info("Product Number is "+AssortmentProductNumber);
		    CommonFunctions.waitForElementTobeClickable(Product.distributionChannel);
		    CommonFunctions.selectFromDropDownByIndex(Product.distributionChannel, 1);
				//CommonFunctions.enterTextInTextbox(Product.distributionChannel,data[26]);
				CommonFunctions.waitForElementTobeClickable(Description);
				CommonFunctions.enterTextInTextboxUpdated(Description, data[67], "Description");
				CommonFunctions.waitForElementTobeClickable(SanitySuite1.TotalCost);
				CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.TotalCost, data[27], "Total Cost");
				CommonFunctions.waitForElementTobeClickable(InnovationType);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(InnovationType, data[28], "InnovationType");
				//Selecting a Innovation Type
				CommonFunctions.waitForElementTobeClickable(IntroTiming);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(IntroTiming, data[29], "IntroTiming");
				//Selecting a Intro Timing
				CommonFunctions.waitForElementTobeClickable(OnShelfDate);
				CommonFunctions.enterTextInTextboxUpdated(OnShelfDate, data[30], "OnShelfDate");
				CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,data[31]);
				CommonFunctions.enterTextInTextbox(Product.USDomestic,data[32]);
				CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,data[33]);
				CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,data[34]);
				CommonFunctions.enterTextInTextbox(Product.GlobalLamForecast,data[35]);
				CommonFunctions.enterTextInTextbox(Product.GlobalNAForeCast,data[36]);
				CommonFunctions.enterTextInTextbox(Product.GlobalEuForeCast,data[37]);
				CommonFunctions.enterTextInTextbox(Product.GlobalAsiaForeCast,data[38]);
				CommonFunctions.enterTextInTextbox(Product.GlobalPacificForecast,data[39]);
				CommonFunctions.waitForElementTobeClickable(ProjectType);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(ProjectType, data[40], "ProjectType");
				CommonFunctions.waitForElementTobeClickable(TVAd);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(TVAd, data[41], "TVAd");
				CommonFunctions.waitForElementTobeClickable(Movie);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(Movie, data[42], "Movie");
				CommonFunctions.waitForElementTobeClickable(DigitalProduct);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(DigitalProduct, data[43], "DigitalProduct");
				/*CommonFunctions.waitForElementTobeClickable(GSPlush);
				CommonFunctions.selectFromDropDownByVisibleTextUpdated(GSPlush, data[44], "GSPlush");*/
			    CommonFunctions.waitForElementTobeClickable(NewSpring);
			    CommonFunctions.enterTextInTextboxUpdated(NewSpring, data[57], "NewSpring");
			    CommonFunctions.waitForElementTobeClickable(ManualInputWaveForeCast);
				CommonFunctions.clickButtonOrLink(ManualInputWaveForeCast, "checkbox", "Manual Input Wave Forcast");
				/*if(data[6].equalsIgnoreCase("Assortment/Solid")){
					CommonFunctions.waitForElementTobeClickable(SanitySuite1.TotalCost);
					CommonFunctions.enterTextInTextboxUpdated(SanitySuite1.TotalCost, data[27], "Total Cost");
					CommonFunctions.waitForElementTobeClickable(InnovationType);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(InnovationType, data[28], "InnovationType");
					//Selecting a Innovation Type
					CommonFunctions.waitForElementTobeClickable(IntroTiming);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(IntroTiming, data[29], "IntroTiming");
					//Selecting a Intro Timing
					CommonFunctions.waitForElementTobeClickable(OnShelfDate);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(OnShelfDate, data[30], "OnShelfDate");
					CommonFunctions.enterTextInTextbox(Product.SRPPriceUSD,data[31]);
					CommonFunctions.enterTextInTextbox(Product.USDomestic,data[32]);
					CommonFunctions.enterTextInTextbox(Product.LCPriceUSD,data[33]);
					CommonFunctions.enterTextInTextbox(Product.DOMPriceUSD,data[34]);
					CommonFunctions.enterTextInTextbox(Product.GlobalLamForecast,data[35]);
					CommonFunctions.enterTextInTextbox(Product.GlobalNAForeCast,data[36]);
					CommonFunctions.enterTextInTextbox(Product.GlobalEuForeCast,data[37]);
					CommonFunctions.enterTextInTextbox(Product.GlobalAsiaForeCast,data[38]);
					CommonFunctions.enterTextInTextbox(Product.GlobalPacificForecast,data[39]);
					CommonFunctions.waitForElementTobeClickable(ProjectType);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(ProjectType, data[40], "ProjectType");
					CommonFunctions.waitForElementTobeClickable(TVAd);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(TVAd, data[41], "TVAd");
					CommonFunctions.waitForElementTobeClickable(Movie);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(Movie, data[42], "Movie");
					CommonFunctions.waitForElementTobeClickable(DigitalProduct);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(DigitalProduct, data[43], "DigitalProduct");
					CommonFunctions.waitForElementTobeClickable(GSPlush);
					CommonFunctions.selectFromDropDownByVisibleTextUpdated(GSPlush, data[44], "GSPlush");
				}
				if(data[6].equalsIgnoreCase("Retail")|| data[6].equalsIgnoreCase("Bundle Pack")){
					CommonFunctions.enterTextInTextbox(Product.targetCostUSD, strDOMPriceUSD);
				}
		}*/
			//Click View Product Button
			CommonFunctions.clickButtonOrLink(Product.viewProductBtn, "Btn", "View Product");
			wait.until(ExpectedConditions.titleIs(data[25]));
			CommonFunctions.waitForPageLoaded();
			log.info("View Season Product Information apge successfully appears");
			//	log.info(prodNum);
		}
		catch(Exception e){ 
			fail=true;
			log.error("Exception in CreateAssortmentProdFromLineSheetForSanitySuite()", e);
			throw e;
			
			 
		}
		return AssortmentProductNumber;
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
	public static boolean SearchAssortmentProduct(String [] data) throws Exception{
		try{
			  driver.switchTo().defaultContent();
		      driver.switchTo().frame("headerframe");
		      //Switching into Header Frame
		      CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchProduct);
			  SeleniumDriver.driver.findElement(CommonProjectFunctions.searchProduct).clear();
			  CommonFunctions.enterTextInTextbox(CommonProjectFunctions.searchProduct,AssortmentProductNumber);
			  CommonFunctions.waitForElementTobeClickable(CommonProjectFunctions.searchIcon);
			  CommonFunctions.clickButtonOrLink(CommonProjectFunctions.searchIcon, "Btn", "SearchButton");
			  //Clicking Search Button
			  driver.switchTo().defaultContent();
		      driver.switchTo().frame("contentframe");
		      CommonFunctions.waitForPageLoaded();
			 NavigateToDetailsTab(data);
				//Navigating to details Tab
			  CommonFunctions.waitForElementTobeClickable(Product.detailPageSeasonDD);
	          CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[4],"Season year");
	          //CommonFunctions.handleAlertPopUp1();
	          CommonFunctions.waitForPageLoaded();
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in SearchAssortmentProduct"+e);
			throw e;
		}
		return true;
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
		     if(RequiredValue.contains(data[50]))
		     {
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQPageActionDropDown, RequiredValue, "Source DropDown Selection");
		     wait.until(ExpectedConditions.titleIs(data[51]));
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
	     if(RequiredValue.contains(data[52]))
	     {
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ProductTypeInCopyLink, data[52], "product Type");
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
	     if(RequiredValue.contains(data[53]))
	     {
	      CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RelationShipType, data[53], "RelationShipType");
		   //Selecting a RelationShip Type
	     break;
	     }
         }
	
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorwayCheckBox);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.ColorwayCheckBox, "Check Box", "ColorWay Check Box");
	     //Selecting the Colorway Check box
	     CommonFunctions.waitForElementTobeClickable(SourceCheckBox);
	     CommonFunctions.clickButtonOrLink(SourceCheckBox, "CheckBox", "SourceCheckBox");
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
	     //Clicking Next Button
	     wait.until(ExpectedConditions.visibilityOfElementLocated(ProductName));
	        CommonFunctions.clearTextBox(ProductName, "ProductName");
			CommonFunctions.enterTextInTextboxUpdated(ProductName, data[59], "ProductName");
			CommonFunctions.waitForElementTobeClickable(IndustryShortDescription);
		    CommonFunctions.clearTextBox(IndustryShortDescription, "IndustryShortDescription");
			CommonFunctions.enterTextInTextboxUpdated(IndustryShortDescription, data[61], "IndustryShortDescription");
			//Selecting Innovation Type
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SoftGoodsIncluded));
	     CommonFunctions.waitForPageLoaded();
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SoftGoodsIncluded, data[54], "SoftGoodIncluded");
	     //Selecting the SoftGoods Value
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ElectronicsIncluded));
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ElectronicsIncluded, data[55], "ElectronicsIncluded");
	     //Selecting the Electronics Included Value
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
	     //Clicking Next Button
	     wait.until(ExpectedConditions.titleIs(data[25]));
	     CommonFunctions.waitForPageLoaded();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ViewProductButton));
	     CommonFunctions.clickButtonOrLink(SanitySuite1.ViewProductButton, "Button", "view Product");
	    CommonFunctions.waitForPageLoaded();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.RetailItemIdentification));
	     SanitySuite1.CopyLinkRetailItem1=driver.findElement(SanitySuite1.RetailItemIdentification).getText();
	     log.info("Created Copy/Link Product RetailItem Value is "+SanitySuite1.CopyLinkRetailItem1);
           
	}
		catch(Exception e){
			fail=true;
			log.error("Exception in CopyLinkProductRetailItem1"+e);
			throw e;
		}
	return SanitySuite1.CopyLinkRetailItem1;
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
		     if(RequiredValue.contains(data[50]))
		     {
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQPageActionDropDown, RequiredValue, "Source DropDown Selection");
		     wait.until(ExpectedConditions.titleIs(data[51]));
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
	     if(RequiredValue.contains(data[52]))
	     {
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ProductTypeInCopyLink, data[52], "product Type");
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
	     if(RequiredValue.contains(data[53]))
	     {
	      CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RelationShipType, data[53], "RelationShipType");
		   //Selecting a RelationShip Type
	     break;
	     }
         }
	
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorwayCheckBox);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.ColorwayCheckBox, "Check Box", "ColorWay Check Box");
	     //Selecting the Colorway Check box
	     CommonFunctions.waitForElementTobeClickable(SourceCheckBox);
	     CommonFunctions.clickButtonOrLink(SourceCheckBox, "CheckBox", "SourceCheckBox");
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
	     //Clicking Next Button
	     wait.until(ExpectedConditions.visibilityOfElementLocated(ProductName));
	      CommonFunctions.clearTextBox(ProductName, "ProductName");
		CommonFunctions.enterTextInTextboxUpdated(ProductName, data[60], "ProductName");
		CommonFunctions.waitForElementTobeClickable(IndustryShortDescription);
	    CommonFunctions.clearTextBox(IndustryShortDescription, "IndustryShortDescription");
		CommonFunctions.enterTextInTextboxUpdated(IndustryShortDescription, data[62], "IndustryShortDescription");
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SoftGoodsIncluded));
	     CommonFunctions.waitForPageLoaded();
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SoftGoodsIncluded, data[54], "SoftGoodIncluded");
	     //Selecting the SoftGoods Value
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ElectronicsIncluded));
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ElectronicsIncluded, data[55], "ElectronicsIncluded");
	     //Selecting the Electronics Included Value
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
	     //Clicking Next Button
	     wait.until(ExpectedConditions.titleIs(data[25]));
	     CommonFunctions.waitForPageLoaded();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ViewProductButton));
	     CommonFunctions.clickButtonOrLink(SanitySuite1.ViewProductButton, "Button", "view Product");
	    CommonFunctions.waitForPageLoaded();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.RetailItemIdentification));
	     SanitySuite1.CopyLinkRetailItem2=driver.findElement(SanitySuite1.RetailItemIdentification).getText();
	     log.info("Created Copy/Link Product RetailItem Value is "+SanitySuite1.CopyLinkRetailItem2);
           
	}
		catch(Exception e){
			fail=true;
			log.error("Exception in CopyLinkProductRetailItem1"+e);
			throw e;
		}
	 return SanitySuite1.CopyLinkRetailItem2;
	}
	
	public static String CopyLinkProductRetailItem3(String [] data) throws Exception{
		try{
			UnSelectSource(data);
			CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQPageActionDropDown);
			Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
		     List<WebElement> allOptions=dropdDown.getOptions();
	      for(int i=0;i<allOptions.size();i++){
		     String RequiredValue=allOptions.get(i).getText();
		    // System.out.println(RequiredValue);
		     if(RequiredValue.contains(data[50]))
		     {
		     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RFQPageActionDropDown, RequiredValue, "Source DropDown Selection");
		     wait.until(ExpectedConditions.titleIs(data[51]));
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
	     if(RequiredValue.contains(data[52]))
	     {
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ProductTypeInCopyLink, data[52], "product Type");
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
	     if(RequiredValue.contains(data[53]))
	     {
	      CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.RelationShipType, data[53], "RelationShipType");
		   //Selecting a RelationShip Type
	     break;
	     }
         }
	
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.ColorwayCheckBox);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.ColorwayCheckBox, "Check Box", "ColorWay Check Box");
	     //Selecting the Colorway Check box
	     CommonFunctions.waitForElementTobeClickable(SourceCheckBox);
	     CommonFunctions.clickButtonOrLink(SourceCheckBox, "CheckBox", "SourceCheckBox");
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
	     //Clicking Next Button
	     wait.until(ExpectedConditions.visibilityOfElementLocated(ProductName));
	      CommonFunctions.clearTextBox(ProductName, "ProductName");
		CommonFunctions.enterTextInTextboxUpdated(ProductName, data[63], "ProductName");
		CommonFunctions.waitForElementTobeClickable(IndustryShortDescription);
	    CommonFunctions.clearTextBox(IndustryShortDescription, "IndustryShortDescription");
		CommonFunctions.enterTextInTextboxUpdated(IndustryShortDescription, data[64], "IndustryShortDescription");
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SoftGoodsIncluded));
	     CommonFunctions.waitForPageLoaded();
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SoftGoodsIncluded, data[54], "SoftGoodIncluded");
	     //Selecting the SoftGoods Value
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ElectronicsIncluded));
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ElectronicsIncluded, data[55], "ElectronicsIncluded");
	     //Selecting the Electronics Included Value
	     CommonFunctions.waitForElementTobeClickable(SanitySuite1.CopyLinkNextButton);
	     CommonFunctions.clickButtonOrLink(SanitySuite1.CopyLinkNextButton, "Button", "Next");
	     //Clicking Next Button
	     wait.until(ExpectedConditions.titleIs(data[25]));
	     CommonFunctions.waitForPageLoaded();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.ViewProductButton));
	     CommonFunctions.clickButtonOrLink(SanitySuite1.ViewProductButton, "Button", "view Product");
	    CommonFunctions.waitForPageLoaded();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.RetailItemIdentification));
	     SanitySuite1.CopyLinkRetailItem2=driver.findElement(SanitySuite1.RetailItemIdentification).getText();
	     log.info("Created Copy/Link Product RetailItem Value is "+SanitySuite1.CopyLinkRetailItem2);
           
	}
		catch(Exception e){
			fail=true;
			log.error("Exception in CopyLinkProductRetailItem1"+e);
			throw e;
		}
	 return CopyLinkRetailItem3;
	}
	public static boolean UnSelectSource(String [] data) throws Exception{
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
            //CommonFunctions.waitForElementTobeClickable(SourcingDropDown);
            Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Source')]//following::select[1]")));
		     List<WebElement> allOptions=dropdDown.getOptions();
            for(int i=0;i<allOptions.size();i++){
   		     String RequiredValue=allOptions.get(i).getText();
   		    // System.out.println(RequiredValue);
   		     if(RequiredValue.contains(data[56]))
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
		return true;
	}
	public static boolean CreateSpecificationAndColorway(String [] data) throws Exception{
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.Specification));
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.clickButtonOrLink(SanitySuite1.Specification, "HyperLink", "Specification");
		
		//Clicking Specification Tab
		ClickSeasonAndSource(data);
		//Sdding Source and Season
		CreateColorway(data);
		//Creating a Colorway
		CreateSpecification(data);
		//Creating a Specification
		
			
		}
		catch(Exception e){
			fail=true;
			log.error("Exception in CreateSpecification"+e);
			throw e;
		}
		return true;
	}
	
	public static String CreateSpecification(String [] data) throws Exception{
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.CreateNewSpecification));
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.clickButtonOrLink(SanitySuite1.CreateNewSpecification, "Button", "Create New Specification");
		wait.until(ExpectedConditions.titleIs(data[48]));
		CommonFunctions.waitForPageLoaded();
		CommonFunctions.waitForElementTobeClickable(SanitySuite1.WaveDropDownInSpecificaton);
		CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.WaveDropDownInSpecificaton, data[49], "wave value");
		//Selecting wave value
		/*CommonFunctions.waitForElementTobeClickable(SanitySuite1.SpecificationStatus);
		CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.SpecificationStatus, data[139], "Specification Status");*/
		//Selecting the Specification Status
		CommonFunctions.waitForElementTobeClickable(SanitySuite1.RFQCreateButton);
		CommonFunctions.clickButtonOrLink(SanitySuite1.RFQCreateButton, "Button", "Create");
		 //Clicking Create Button
		wait.until(ExpectedConditions.visibilityOfElementLocated(InternalBOMSoftG.selectSpecification));
		wait.until(ExpectedConditions.titleIs(data[25]));
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
		return InternalBOMSoftG.strSpec;
	}
	
	
	public static boolean ClickSeasonAndSource(String [] data) throws Exception{
		try{
			/* wait.until(ExpectedConditions.visibilityOfElementLocated(Product.detailPageSeasonDD));
			 CommonFunctions.waitForPageLoaded();
             CommonFunctions.selectFromDropDownByVisibleTextUpdated(Product.detailPageSeasonDD, data[3],"Season year");
             //CommonFunctions.handleAlertPopUp1();*/
             wait.until(ExpectedConditions.visibilityOfElementLocated(SanitySuite1.SourcingDropDown));
             CommonFunctions.waitForPageLoaded();
             //CommonFunctions.waitForElementTobeClickable(SourcingDropDown);
             Select dropdDown = new Select(driver.findElement(By.xpath("//td[contains(text(),'Source')]//following::select[1]")));
		     List<WebElement> allOptions=dropdDown.getOptions();
             for(int i=0;i<allOptions.size();i++){
    		     String RequiredValue=allOptions.get(i).getText();
    		    // System.out.println(RequiredValue);
    		     if(RequiredValue.contains(data[45]))
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
			log.error("Exception in ClickSeasonAndSource"+e);
			throw e;
		}
		return true;
	}
	
	public static String CreateColorway(String [] data) throws Exception{
		try{
		CommonFunctions.waitForElementTobeClickable(SanitySuite1.ActionDropDownOfSpecicationTab);
		Select dropdDown = new Select(driver.findElement(By.xpath("//select[@id='prodseasonActions']")));
	     List<WebElement> allOptions=dropdDown.getOptions();
	    
      for(int i=0;i<allOptions.size();i++){
	     String RequiredValue=allOptions.get(i).getText();
	    // System.out.println(RequiredValue);
	     if(RequiredValue.contains(data[46]))
	     {
	     CommonFunctions.selectFromDropDownByVisibleTextUpdated(SanitySuite1.ActionDropDownOfSpecicationTab, RequiredValue, "Source DropDown Selection");
	     wait.until(ExpectedConditions.titleIs(data[47]));
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
	    CommonFunctions.waitForElementTobeClickable(ShowAll);
	    CommonFunctions.clickButtonOrLink(ShowAll, "Hyper-Link", "ShowAll");
	    CommonFunctions.waitForPageLoaded();
	    //click Show-All HyperLink
	    wait.until(ExpectedConditions.visibilityOfElementLocated(AS0Colorway));
	    CommonFunctions.clickButtonOrLink(AS0Colorway, "Hyper-Link", "AS0Colorway");
	    //Choosing the AS0 Colorway
	    CommonFunctions.switchParentWindow();
	    driver.switchTo().defaultContent();
		driver.switchTo().frame("contentframe");
	    //Switching Frame
	    CommonFunctions.waitForPageLoaded();	
		CommonFunctions.waitForElementTobeClickable(SanitySuite1.SaveButtonOfCostSheet);
		CommonFunctions.clickButtonOrLink(SanitySuite1.SaveButtonOfCostSheet, "Button", "Save");
		//Clicking Save Button
		wait.until(ExpectedConditions.titleIs(data[25]));
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
		return SanitySuite1.ColorwayDropdownValue;
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
					Utility.dataSetResult(suiteSanityXls, this.getClass().getSimpleName(), count+2, "PASS."+"Assortment ProductNumber is: "+AssortmentProductNumber+","+"Associated RetailItemProductNumber1 is: "+SanitySuite1.CopyLinkRetailItem1+","+"Associated RetailItemProductNumber2 is: "+SanitySuite1.CopyLinkRetailItem2+" and "+"Assortment ProductNumber is: "+CopyLinkRetailItem3);
				  
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

