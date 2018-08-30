package excelutil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.SeleniumDriver;

public class OpenBrowserTest extends SeleniumDriver{

	public static void main(String[] args) throws Exception {
		initialize();
		openBrowser();
		driver.get("http://demo.virtuemart.net/");
		WebElement element1=driver.findElement(By.xpath("//div[@class='featured-view']//div[@class='row']"));
		List<WebElement> no_of_image=element1.findElements(By.xpath("//div[@class='featured-view']//div[@class='row']//img[contains(@src,'0x90')]"));
		List<WebElement> no_of_tooltipimage=element1.findElements(By.xpath("//div[@class='vm3pr-2' or @class='vm3pr-1']//img[contains(@src,'tooltip')]"));
		List<WebElement> no_of_product_details=element1.findElements(By.xpath("//div[@class='featured-view']//div[@class='row']//a[contains(text(),'Product details')]"));
		System.out.println("no of images: "+no_of_image.size());
		System.out.println("no_of_tooltipimage: "+no_of_tooltipimage.size());
		System.out.println("no_of_product_details button:"+no_of_product_details.size());
		for(int i=0;i<no_of_image.size();i++){
			System.out.println(no_of_image.get(i).getAttribute("src"));
		}
		for(int i=0;i<no_of_tooltipimage.size();i++){
			System.out.println(no_of_tooltipimage.get(i).getAttribute("src"));
		}
	}
}
