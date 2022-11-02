package Order;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test_Order extends WriteExcel{
	public static final String PATH = "C:\\Users\\Admin\\eclipse-workspace\\Automation testing_ Amazon\\chromedriver.exe";
	public static final String DRIVER = "webdriver.chrome.driver";
	public static final String URL = "https://www.amazon.com";
	
	String fileInputPath = "src/Order/data_order.xlsx";
	String fileOutputPath = "src/Order/output_order.xlsx";
			
	WebDriver driver = null;
    ReadExcel readExcel = new ReadExcel();
    List<Address> listAddress;
    List<HashMap<String,Object>> testResultExport = new ArrayList<>();
    Map<String, Object> testResult = new HashMap<String, Object>();
    WriteExcel writeExcel = new WriteExcel();
    ArrayList<String> arrResult = new ArrayList<>();
	
	@Test(priority = 0)
	public void importTestData() throws IOException {
		listAddress = readExcel.readDataExcel(fileInputPath);
		
	}
	
	@DataProvider(name = "data_order")
	public Object[][] dataOrder() {
		int numberCases = listAddress.size();
		Object[][] Cred = new Object[numberCases][7];
		for (int i = 0; i < numberCases; i++) {
			Address x = (Address) listAddress.get(i);
			Cred[i][0] = x.getIDtc();			
			Cred[i][1] = x.getfullName();
			Cred[i][2] = x.getstreetAddress();
			Cred[i][3] = x.getcity();
			Cred[i][4] = x.getzipCode();
			Cred[i][5] = x.getphoneNumber();
			Cred[i][6] = x.getmessage();
			}
		System.out.println("object data" + Cred);
		return Cred;
	}
	@Test(dataProvider = "data_order", priority = 1)
	public void Order(String IDtc, String fullName, String streetAddress, String city, String zipCode, String phoneNumber, String message)throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='address-ui-widgets-countryCode-dropdown-nativeId']")));
		
		Select dropCountry = new Select(driver.findElement(By.xpath("//select[@id='address-ui-widgets-countryCode-dropdown-nativeId']")));		
		dropCountry.selectByVisibleText("Vietnam");;
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']")));
		
		WebElement FullName = driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']"));
				
		WebElement StreetAddress = driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressLine1']"));
		
		WebElement City = driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressCity']"));
		
		WebElement ZipCode = driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPostalCode']"));
	
		WebElement PhoneNumber = driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPhoneNumber']"));
		try {
		FullName.clear();
		StreetAddress.clear();
		City.clear();
		ZipCode.clear();
		PhoneNumber.clear();
		
		FullName.sendKeys(fullName);
		StreetAddress.sendKeys(streetAddress);
		City.sendKeys(city);
		ZipCode.sendKeys(zipCode);
		PhoneNumber.sendKeys(phoneNumber);
		
		} catch(org.openqa.selenium.StaleElementReferenceException ex) {
			
			FullName.clear();
			StreetAddress.clear();
			City.clear();
			ZipCode.clear();
			PhoneNumber.clear();
			
			FullName.sendKeys(fullName);
			StreetAddress.sendKeys(streetAddress);
			City.sendKeys(city);
			ZipCode.sendKeys(zipCode);
			PhoneNumber.sendKeys(phoneNumber);
		}
		
		WebElement button1 = driver.findElement(By.xpath("//span[@id='address-ui-widgets-form-submit-button']"));
		
	    button1.click();
		
		switch(IDtc) {
		case "01":
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please enter a name.')]")));
				String actualResult1 = driver.findElement(By.xpath("//div[contains(text(),'Please enter a name.')]")).getText();
				String status = "";
				if (actualResult1.contentEquals(message)) {
					testResult.put("result", "PASS");
					status = "PASS";
				} else {
					testResult.put("result", "FAIL");
					status = "FAIL";
				}
				testResult.put("IDtc", IDtc);
				testResult.put("Full name", fullName );
				testResult.put("Street address", streetAddress);
				testResult.put("City", city);
				testResult.put("Zip code", zipCode);
				testResult.put("Phone number", phoneNumber);
				testResult.put("Message", message);
				String rst = IDtc + ";" + fullName + ";" + streetAddress + ";" + city + ";" + zipCode + ";" + phoneNumber + ";" + message + ";" + status;
                arrResult.add(rst);
      			} catch (Exception  e) {
					e.printStackTrace ();
				}
			//driver.navigate().refresh();
			break;
		case "02":
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please enter an address.')]")));
				String actualResult2 = driver.findElement(By.xpath("//div[contains(text(),'Please enter an address.')]")).getText();
				String status = "";
				if (actualResult2.contentEquals(message)) {
					testResult.put("result", "PASS");
					status = "PASS";
				} else {
					testResult.put("result", "FAIL");
					status = "FAIL";
				}
				
				testResult.put("IDtc", IDtc);
				testResult.put("Full name", fullName );
				testResult.put("Street address", streetAddress);
				testResult.put("City", city);
				testResult.put("Zip code", zipCode);
				testResult.put("Phone number", phoneNumber);
				testResult.put("Message", message);
				String rst = IDtc + ";" + fullName + ";" + streetAddress + ";" + city + ";" + zipCode + ";" + phoneNumber + ";" + message + ";" + status;
                arrResult.add(rst);
				} catch (Exception  e) {
					e.printStackTrace ();
				}
			//driver.navigate().refresh();
			break;
	
		case "03":
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please enter a ZIP or postal code.')]")));
				String actualResult3 = driver.findElement(By.xpath("//div[contains(text(),'Please enter a ZIP or postal code.')]")).getText();
				String status = "";
				if (actualResult3.contentEquals(message)) {
					testResult.put("result", "PASS");
					status = "PASS";
				} else {
					testResult.put("result", "FAIL");
					status = "FAIL";
				}
				
				testResult.put("IDtc", IDtc);
				testResult.put("Full name", fullName );
				testResult.put("Street address", streetAddress);
				testResult.put("City", city);
				testResult.put("Zip code", zipCode);
				testResult.put("Phone number", phoneNumber);
				testResult.put("Message", message);
				String rst = IDtc + ";" + fullName + ";" + streetAddress + ";" + city + ";" + zipCode + ";" + phoneNumber + ";" + message + ";" + status;
                arrResult.add(rst);
				} catch (Exception  e) {
					e.printStackTrace ();
				}
			//driver.navigate().refresh();
			break;
		case "04":
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please enter a phone number so we can call if ther')]")));
				String actualResult4 = driver.findElement(By.xpath("//div[contains(text(),'Please enter a phone number so we can call if ther')]")).getText();
				String status = "";
				if (actualResult4.contentEquals(message)) {
					testResult.put("result", "PASS");
					status = "PASS";
				} else {
					testResult.put("result", "FAIL");
					status = "FAIL";
				}
				
				testResult.put("IDtc", IDtc);
				testResult.put("Full name", fullName );
				testResult.put("Street address", streetAddress);
				testResult.put("City", city);
				testResult.put("Zip code", zipCode);
				testResult.put("Phone number", phoneNumber);
				testResult.put("Message", message);
				String rst = IDtc + ";" + fullName + ";" + streetAddress + ";" + city + ";" + zipCode + ";" + phoneNumber + ";" + message + ";" + status;
                arrResult.add(rst);
				} catch (Exception  e) {
					e.printStackTrace ();
				}
			//driver.navigate().refresh();
			break;
		case "05":
			try {
				WebElement button2 = driver.findElement(By.xpath("//span[@id='address-ui-widgets-form-submit-button']"));
				button2.click();
			    String status = "";
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please provide a valid phone number')]")));
				String actualResult5 = driver.findElement(By.xpath("//div[contains(text(),'Please provide a valid phone number')]")).getText();
				
				if (actualResult5.contentEquals(message)) {
					testResult.put("result", "PASS");
					status = "PASS";
					} 
				else {
					testResult.put("result", "FAIL");
					status = "FAIL";
				}
								
				testResult.put("IDtc", IDtc);
				testResult.put("Full name", fullName );
				testResult.put("Street address", streetAddress);
				testResult.put("City", city);
				testResult.put("Zip code", zipCode);
				testResult.put("Phone number", phoneNumber);
				testResult.put("Message", message);
				String rst = IDtc + ";" + fullName + ";" + streetAddress + ";" + city + ";" + zipCode + ";" + phoneNumber + ";" + message + ";" + status;
                arrResult.add(rst);
				} catch (Exception  e) {
					e.printStackTrace ();
				}
			//driver.navigate().refresh();
			break;
		case "06":
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),\"We couldn't verify your address. Please make sure \")]")));
				String actualResult6 = driver.findElement(By.xpath("//div[contains(text(),\"We couldn't verify your address. Please make sure \")]")).getText();
				String status = "";
				if (actualResult6.contentEquals(message)) {
					testResult.put("result", "PASS");
					status = "PASS";
					
				} else {
					testResult.put("result", "FAIL");
					status = "FAIL";
				}
							
				testResult.put("IDtc", IDtc);
				testResult.put("Full name", fullName );
				testResult.put("Street address", streetAddress);
				testResult.put("City", city);
				testResult.put("Zip code", zipCode);
				testResult.put("Phone number", phoneNumber);
				testResult.put("Message", message);
				String rst = IDtc + ";" + fullName + ";" + streetAddress + ";" + city + ";" + zipCode + ";" + phoneNumber + ";" + message + ";" + status;
                arrResult.add(rst);
				} catch (Exception  e) {
					e.printStackTrace ();
				}
			break;
		
		}
		 for (int i=0; i<arrResult.size(); i++) {
	            System.out.println("test == " + arrResult.get(i));
	        }
	
	}
	@Test(priority = 2)
	public void export() throws IOException {
		 writeExcel.writeData(arrResult, fileOutputPath);
		
	}
	@BeforeTest
	public void openBrowser() {
		System.setProperty(DRIVER, PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		driver.get(URL);
		
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//header/div[@id='navbar']/div[@id='nav-belt']/div[3]/div[1]/a[2]/span[1]"))).perform();
		WebElement li = driver.findElement(By.xpath("//header/div[@id='navbar']/div[@id='nav-belt']/div[3]/div[1]/a[2]/span[1]"));
		actions.moveToElement(li.findElement(By.xpath("//header/div[@id='navbar']/div[@id='nav-flyout-anchor']/div[@id='nav-flyout-accountList']/div[2]/div[1]/div[1]/div[1]/a[1]/span[1]"))).click().perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120,1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ap_email']")));
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("ngocvu1541@gmail.com");
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ap_password']")));
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Ngoc12345@");
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-cart']")));
		driver.findElement(By.xpath("//a[@id='nav-cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='proceedToRetailCheckout']")));
		driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
		
	}

	@AfterTest
	public void closebrowser() {
	}
}
