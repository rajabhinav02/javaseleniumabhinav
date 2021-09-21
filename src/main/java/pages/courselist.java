package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import resources.base;

public class courselist extends base {
	
	WebDriver driver;

	public courselist(WebDriver driver) {
		this.driver = driver;
	}
	
	By searchbox = By.id("search-courses");
	String coursetitle = "//div[contains(@class,'listing-title') and (@title=%s)]";
	//div[contains(@class,'listing-title') and (@title='JavaScript for beginners')]
	
	
	public void sendcoursename(String data) {
		sendData(searchbox, data);
		log.info("Sending course name "+data);
	}
	
	public void clickcourse(String data) {
		String updatedtitle = coursetitle.format(data);
		By updtitle = By.xpath(updatedtitle);
		clickElement(updtitle);
		
	}
}
