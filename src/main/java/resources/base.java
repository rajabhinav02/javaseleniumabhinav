package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class base {

static public WebDriver driver;
static public Properties prop;
public static Logger log = LogManager.getLogger(base.class.getName());
	
@SuppressWarnings("deprecation")
public WebDriver setup() throws IOException {
	

	prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Properties.properties");
	prop.load(fis);
	
	if (prop.get("browser").equals("chrome")) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver92\\chromedriver_win32 (5)\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	else if (prop.get("browser").equals("IE")) {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver92\\chromedriver_win32 (5)\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	return driver;
}

//getting element
public WebElement getElement(By locator) {
	//WebElement element =null;
	try {
	WebElement element = driver.findElement(locator);
	log.info("element returned with locator "+locator);
	return element;
	}
	catch(Exception e){
		log.error("no element returned with locator "+locator);
		return null;
	}
	
	
	
}

//getting elements
public List<WebElement> getElements(By locator) {
	try {
	List<WebElement>elements=driver.findElements(locator);
	log.info("elements returned");
	return elements;
	}catch(Exception e){
		log.error("no elements returned");
		return null;
	}
}

//clicking element
public void clickElement(By locator) {
	try {
	getElement(locator).click();
	log.info("Element with locator "+locator+" clicked");
	}catch(Exception e) {
		log.error("no element clicked with locator "+locator);
	}
}

//sending data
public void sendData(By locator, String data) {
	try {
	getElement(locator).sendKeys(data);
	log.info("Data "+data+ " sent to element with locator "+locator);
	}catch(Exception e) {
		log.error("No data sent to locator "+locator);
	}
}

//switch browser window
public void switchbrowserwindow() {
	try {
	String parent=driver.getWindowHandle();
	Set<String> windowhandles = driver.getWindowHandles();
	
	for (String ws:windowhandles) {
		if (ws!=parent) {
			driver.switchTo().window(ws);
			log.info("switched to child window");
		}
	}
	}catch(Exception e) {
		log.error("switch to window failed");
	}
}

//switch frame
public void switchFrame(By locator) {
	try {
		driver.switchTo().frame(driver.findElement(locator));
		log.info("switced to frame with locator "+locator);
	}catch(Exception e) {
		log.error("switch to frame failed");
	}
}

//send data
public String getData(By locator) {
	try {
		String text=driver.findElement(locator).getText();
		log.info("Text returned fr element with locator "+locator);
		return text;
	}catch(Exception e) {
		log.error("No text returne for locator "+locator);
		return null;
	}
}

//switch to alert
public void switchAlert() {
	try {
		Alert al=driver.switchTo().alert();
		log.info("switched to alert");
		//driver.findElement(null).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
	}catch(Exception e) {
		log.error("switch to alert failed");
		
		
	}
}

//explicit wait
public void explicitwait(int time, By locator) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		log.info("waiting for presence of element");
	}catch(Exception e) {
		log.error("issue with waiting");
	}
}

//scroll 
public void webscroll(String direction) {
	try {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		if (direction.equalsIgnoreCase("up")) {
			js.executeScript("window.scroll(0,-1000)");
			log.info("scrolled up");
		}else if (direction.equalsIgnoreCase("down")) {
			js.executeScript("window.scroll(0,1000)");
			log.info("scrolled down");
		}
	}catch (Exception e) {
		log.error("issue with scroling");
	}
}
// scroll into element
public void scrolltoelement(By locator) {
	try {
		WebElement element = getElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		log.info("scrolled to element");
	}catch(Exception e) {
		log.error("scroll to element failed");
	}
}
//mousehover

public void getmousehover(By locator) {
	try {
		WebElement element = getElement(locator);
		Actions ac = new Actions(driver); 
		ac.moveToElement(element).build().perform();
		
		log.info("hover over completed");
	}catch (Exception e) {
		log.error("hover over failed");
	}
}
//drag and drop
public void draganddrop(By fromlocator, By tolocator) {
	try {
	WebElement drag = getElement(fromlocator);
	WebElement dest = getElement(tolocator);
	Actions as = new Actions(driver);
	as.dragAndDrop(drag, dest);
	log.info("drag and drop");
	}catch(Exception e) {
	log.error("drag and drop failed");	
	}
}

public void sendcaps(By locator, String data) {
	try {
	Actions a = new Actions(driver);
	a.moveToElement(driver.findElement(locator)).click().keyDown("SHIFT").sendKeys(data).doubleClick().build().perform();
	log.info("data sent in capital");
	}catch(Exception e) {
		log.error("issue with capital letters");
	}
}

public void rightclick(By locator) {
	try {
		WebElement element = getElement(locator);
		Actions a = new Actions(driver);
		a.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		log.info("right clicked and second value clicked");
		
		
		
	}catch(Exception e) {
		log.error("no right click");
	}
}

public void selectdropdown(By locator, String data) {
	try {
		Select se = new Select(getElement(locator));
		se.deselectByVisibleText(data);
		log.info("value selected from dropdown");
	}catch (Exception e){
		log.error("no value selected from dropdown");
	}
}

public boolean checkpresence(By locator) {
	List<WebElement> Elements = getElements(locator);
	if (Elements.size()>0) {
		log.info("Element present");
		return true;
	}else {
		log.error("element not present");
		return false;
	}
	}

public boolean isdisplayed(By locator) {
	try {
		if (driver.findElement(locator).isDisplayed() == true) {
			log.info("element displayed");
			return true;
		}else {
			log.error("element not displayed");
			return false;
		}
	}catch (Exception e) {
		log.error("issue with element displayed");
		return false;
	}
}
}


