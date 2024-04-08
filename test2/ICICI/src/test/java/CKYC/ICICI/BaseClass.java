package CKYC.ICICI;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.mail.*;
import javax.imageio.ImageIO;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;

public class BaseClass {

	Logger log = LogManager.getLogger("BaseClass");
	public static java.util.Date date = new java.util.Date();
	public static Date resultTime = Calendar.getInstance().getTime();
	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	

	public static WebDriver driver;
	public static Properties pr = new Properties();
	FileReader fr;

	public static Properties prd = new Properties();
	FileReader frd;

	public static JavascriptExecutor js;
	
	public static WebDriverWait wait ;


	@BeforeSuite(groups = { "New dump Upload and Process", "Upload Response", "Pending Cust Report",
			"CKYC Id Generated Report", "Check Draft Cust", "Search and Download", "MultiSearch" })
	public void invokeBrowser() {
		log.info("Test Start --------------------------------->>");
		Reporter.log(resultTime + " - " + "Test Start");
		System.out.println(resultTime + " - " + "Test Start");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		log.info("Browser Open");
		Reporter.log(resultTime + " - " + "Browser Open");
		System.out.println(resultTime + " - " + "Browser Open");
		// Properties for locators
		try {
			fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\java\\utilities\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pr.load(fr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Properties for Input Values
		try {
			frd = new FileReader(System.getProperty("user.dir") + "\\src\\test\\java\\utilities\\data.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			prd.load(frd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		js = (JavascriptExecutor) driver;
		// BasicConfigurator.configure();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	//@AfterSuite(groups = { "New dump Upload and Process", "Upload Response", "Pending Cust Report","CKYC Id Generated Report", "Check Draft Cust", "Search and Download", "MultiSearch" })
	public void closure() {

		driver.close();
		log.info("Browser closed");
		Reporter.log(resultTime + " - " + "Browser closed");
		System.out.println(resultTime + " - " + "Browser closed");
		log.info("Test End --------------------------------->>" + '\n');
		Reporter.log(resultTime + " - " + "Test End");
		System.out.println(resultTime + " - " + "Test End");

	}

	// @AfterMethod
	public void takeScreenshot() throws IOException {
		String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.png'").format(new Date());
		String nm = date.toString();
		TakesScreenshot screenshot = ((TakesScreenshot) driver);
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "\\screenshot\\" + "EndTest_ss" + out);
		FileUtils.copyFile(src, dest);
	}

	@BeforeTest
	public void startReport() {
		// initialize the HtmlReporter
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/report/extendedTestReport.html");
		String projectName = prd.getProperty("projectName");

		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// configuration items to change the look and feel
		// add content, manage tests etc
		// htmlReporter.config().setChartVisibilityOnOpen(true);
		// htmlReporter.config().setDocumentTitle("Simple Automation Report");
		htmlReporter.config().setReportName(projectName+" CKYC Test Report");
		// htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}

	@AfterMethod
	public void getREsult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());

			// test.fail(result.getTestName()+" Faild");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getTestName());
			// test.pass(result.getTestName() +" Passed");

		} else {
			test.log(Status.SKIP, result.getTestName());
			// test.skip(result.getTestName() + " Skiped");

		}
		extent.flush();
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
	}

	public static String ss() throws IOException, HeadlessException, AWTException {
		String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.png'").format(new Date());
		BufferedImage image = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		File destPath = new File(System.getProperty("user.dir") + "\\report\\liveScreenshot\\" + out);
		
		

		ImageIO.write(image, "png", destPath);
		//String absPath = System.getProperty("user.dir") + "\\report\\liveScreenshot\\" + out;
		String absPath =  "liveScreenshot\\" + out;
		return absPath;
	}

	public static void backupScreenshot() throws IOException, HeadlessException, AWTException {
		String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.png'").format(new Date());
		BufferedImage image = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File(System.getProperty("user.dir") + "\\screenshot\\" + out));
	}
	
	public static void resetTest() {
		driver.get(prd.getProperty("URL"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginId_loc"))).sendKeys(prd.getProperty("CentralUserLoginId_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginPass_loc"))).sendKeys(prd.getProperty("CentralUserLoginPass_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginBtn_loc"))).click();
		WebElement redirectDrop = driver.findElement(By.xpath(pr.getProperty("CentralUserRedirectDrop_loc")));
		Select selectRedirectDrop = new Select(redirectDrop);
		selectRedirectDrop.selectByVisibleText(prd.getProperty("CentralUserLoginRedirectDropOpt_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserRedirectBtn_loc"))).click();
		
		
	}
	
	
}
