package Sign_in;

import org.testng.annotations.Test;

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileOutputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;s
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
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
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class Test_Signin extends WriteExcel {
	public static final String PATH = "C:\\Users\\Admin\\eclipse-workspace\\Automation testing_ Amazon\\chromedriver.exe";
	public static final String DRIVER = "webdriver.chrome.driver";
	public static final String URL = "https://www.amazon.com";
	
	String fileInputPath = "src/Sign_in/data_signin.xlsx";
	String fileOutputPath = "src/Sign_in/output_signin.xlsx";
			
	WebDriver driver = null;
	ReadExcel readExcel = new ReadExcel();
	List<User> listUsers;
	List<HashMap<String,Object>> testResultExport = new ArrayList<>();
	Map<String, Object> testResult = new HashMap<String, Object>(); 
	
	
	@Test(priority = 0)
	public void importTestData() throws IOException {
		listUsers = readExcel.readDataExcel(fileInputPath);
		
	}
	
	@DataProvider(name = "data_signin")
	public Object[][] dataSignIn() {
		int numberCases = listUsers.size();
		Object[][] Cred = new Object[numberCases][4];
		for (int i = 0; i < numberCases; i++) {
			User x = (User) listUsers.get(i);
			Cred[i][0] = x.getIDtc();			
			Cred[i][1] = x.getUsername();
			Cred[i][2] = x.getPassword();
			Cred[i][3] = x.getMessage();
		}
		System.out.println("object data" + Cred);
		return Cred;
	}
	@Test(dataProvider = "data_signin", priority = 1)
	public void SignIn(String IDtc, String username, String password, String message) throws IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120,1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ap_email']")));
		
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		if(!driver.findElements(By.xpath("//input[@id='ap_password']")).isEmpty()){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ap_password']")));
			driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys(password);
			driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();	
			} else {
				openBrowser();
			}

		testResult.put("IDtc", IDtc);
		testResult.put("username", username);
		testResult.put("password", password);
		testResult.put("message", message);

		
		switch(IDtc) {
		
		case "01":				
			try {
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/div[3]/div[1]/a[2]/div[1]/span[1]"))).perform();
			WebElement li = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/div[3]/div[1]/a[2]/div[1]/span[1]"));
			if(!li.findElements(By.xpath("//span[contains(text(),'Sign Out')]")).isEmpty()) {
				testResult.put("result", "PASS");
				System.out.println("Test case 01: PASS");
			} else {
				testResult.put("result", "FAIL");
				System.out.println("Test case 01: FAIL");
			}
			openBrowser();
			} catch (Exception  e) {
				e.printStackTrace ();
			}
			
			break;
		
		case "02":
			try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Your password is incorrect')]")));
			String actualResult2 = driver.findElement(By.xpath("//span[contains(text(),'Your password is incorrect')]")).getText();
			if (actualResult2.contentEquals(message)) {
				testResult.put("result", "PASS");
				System.out.println("Test case 02: PASS");
			} else {
				testResult.put("result", "FAIL");
				System.out.println("Test case 02: FAIL");
			}
			openBrowser();
			} catch (Exception  e) {
				e.printStackTrace ();
			}
			break;
		
		case "03":
			try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'We cannot find an account with that email address')]")));
			String actualResult3 = driver.findElement(By.xpath("//span[contains(text(),'We cannot find an account with that email address')]")).getText();
			if (actualResult3.contentEquals(message)) {
				testResult.put("result", "PASS");
				System.out.println("Test case 03: PASS");
			} else {
				testResult.put("result", "FAIL");
				System.out.println("Test case 03: FAIL");
			}
			} catch (Exception  e) {
				e.printStackTrace ();
			}
			break;	
	
		case "04":
			try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'We cannot find an account with that email address')]")));
			String actualResult4 = driver.findElement(By.xpath("//span[contains(text(),'We cannot find an account with that email address')]")).getText();
			if (actualResult4.contentEquals(message)) {
				testResult.put("result", "PASS");
				System.out.println("Test case 04: PASS");
			} else {
				testResult.put("result", "FAIL");
				System.out.println("Test case 04: FAIL");
			}
		
			} catch (Exception  e) {
				e.printStackTrace ();
			}
			
			break;	
		
		
		case "05":
			try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'We cannot find an account with that mobile number')]")));
			String actualResult5 = driver.findElement(By.xpath("//span[contains(text(),'We cannot find an account with that mobile number')]")).getText();
			
			if (actualResult5.contentEquals(message)) {
				
				testResult.put("result", "PASS");
				System.out.println("Test case 05: PASS");
			
			} else {
				
				testResult.put("result", "FAIL");
				System.out.println("Test case 05: FAIL");
				
			}
			
			} catch (Exception  e) {
				e.printStackTrace ();
			}
			
			break;	
		
		case "06":
			try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Enter your email or mobile phone number')]")));
			String actualResult6 = driver.findElement(By.xpath("//div[contains(text(),'Enter your email or mobile phone number')]")).getText();
			
			if (actualResult6.contentEquals(message)) {
				testResult.put("result", "PASS");
				System.out.println("Test case 06: PASS");
			} else {
				testResult.put("result", "FAIL");
				System.out.println("Test case 06: FAIL");
			}
			
			} catch (Exception  e) {
				e.printStackTrace ();
			}
			
			break;	
			
		case "07":
		
					try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Enter your password')]")));
					String actualResult7 = driver.findElement(By.xpath("//div[contains(text(),'Enter your password')]")).getText();
					
					if (actualResult7.contentEquals(message)) {
						testResult.put("result", "PASS");
						System.out.println("Test case 07: PASS");
						} else {
							testResult.put("result", "FAIL");
							System.out.println("Test case 07: FAIL");
						}
					} catch (Exception  e) {
						e.printStackTrace ();
					}
			
					break;
		}
		testResultExport.add((HashMap<String, Object>) testResult);
		}
				
		
	
	@Test(priority = 2)
	public void export() throws IOException {
		export(testResultExport, fileOutputPath);
		
	}

	/*public void logout() {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/div[3]/div[1]/a[2]/div[1]/span[1]"))).perform();
		WebElement li = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/div[3]/div[1]/a[2]/div[1]/span[1]"));
		actions.moveToElement(li.findElement(By.xpath("//span[contains(text(),'Sign Out')]"))).click().perform();
	}*/



	@BeforeTest
	public void openBrowser() {
		System.setProperty(DRIVER, PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//header/div[@id='navbar']/div[@id='nav-belt']/div[3]/div[1]/a[2]/span[1]"))).perform();
		WebElement li = driver.findElement(By.xpath("//header/div[@id='navbar']/div[@id='nav-belt']/div[3]/div[1]/a[2]/span[1]"));
		actions.moveToElement(li.findElement(By.xpath("//header/div[@id='navbar']/div[@id='nav-flyout-anchor']/div[@id='nav-flyout-accountList']/div[2]/div[1]/div[1]/div[1]/a[1]/span[1]"))).click().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120,1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ap_email']")));
	}

	@AfterTest
	public void closebrowser() {
	}

}
