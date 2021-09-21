package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;

public class homescreen extends base  {
	
	//public static Logger log =LogManager.getLogger(homescreen.class.getName());
	public static Logger log = LogManager.getLogger(homescreen.class.getName());
	
	WebDriver driver;
	//Properties pro;
	public homescreen(WebDriver driver) throws IOException {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		/*Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Properties.properties");
		pro.load(fis);*/
		
	}
	
	
	

	By login = By.linkText("Login");
	public By email = By.cssSelector("[type='email']");
	By pwd = By.cssSelector("[type='password']");
	By signin = By.name("commit");
	By failmsg = By.cssSelector("[class*='alert']");
	
	/*@FindBy (css="[type='password']")
	public WebElement pwd;*/
	
	
	public void clicklogin() {
		clickElement(login);
	}
	
	public void sendemail() {
		sendData(email, prop.getProperty("emailid"));
		//System.out.println(prop.get("url"));
		//System.out.println(pro.getProperty("email").equalsIgnoreCase("ha"));
	}
	
	public void sendpwd() {
		sendData(pwd, prop.getProperty("pwd"));
	}
	
	public courselist signin() {
		clickElement(signin);
		courselist cs = new courselist(driver);
		return cs;
	}
	
	public boolean faildisplayed() {
		boolean status= isdisplayed(failmsg);
		return status;
	}
	
	public boolean pwddisplayed() {
		boolean status= isdisplayed(pwd);
		return status;
	}
	
}

//9986200111