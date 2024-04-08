package CKYC.ICICI;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.apache.logging.log4j.*;

public class CentralUser extends BaseClass {
	Logger log = LogManager.getLogger("CentralUser");

// Login to the central user
	@Test(priority = -1, groups = { "New dump Upload and Process", "Upload Response", "Pending Cust Report",
			"CKYC Id Generated Report" })
	public void loginCentralUser() throws InterruptedException, IOException, HeadlessException, AWTException {

		driver.get(prd.getProperty("URL"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginId_loc")))
				.sendKeys(prd.getProperty("CentralUserLoginId_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginPass_loc")))
				.sendKeys(prd.getProperty("CentralUserLoginPass_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginBtn_loc"))).click();
		WebElement redirectDrop = driver.findElement(By.xpath(pr.getProperty("CentralUserRedirectDrop_loc")));
		Select selectRedirectDrop = new Select(redirectDrop);
		selectRedirectDrop.selectByVisibleText(prd.getProperty("CentralUserLoginRedirectDropOpt_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserRedirectBtn_loc"))).click();
		log.info("Central user logged in successfully");
		Reporter.log(resultTime + " - " + "Central user logged in successfully");
		System.out.println(resultTime + " - " + "Central Loggedin");
		test = extent.createTest("loginCentralUser", "Trying to logedIn with central user")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("New dump Upload and Process",
						"Upload Response", "Pending Cust Report", "CKYC Id Generated Report");
		Thread.sleep(500);
		backupScreenshot();
		test.addScreenCaptureFromPath(ss());
		test.info("Central User LoggedIn");

	}

//Upload New Dump File -->
	@Test(priority = 2, groups = { "New dump Upload and Process" })
	public void uploadDumpFile() throws InterruptedException, IOException, HeadlessException, AWTException {
		test = extent.createTest("uploadDumpFile", "Validate CBS Dump file upload functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("New dump Upload and Process");
		WebElement importTab = driver.findElement(By.xpath(pr.getProperty("CentralUserImportTab_loc")));
		js.executeScript("arguments[0].click();", importTab);
		// Actions importHover = new Actions(driver);
		// importHover.moveToElement(importTab);

		List<WebElement> importOpt = driver.findElements(By.xpath(pr.getProperty("CentralUserImportDropOPt_loc")));
		for (WebElement x : importOpt) {
			String dropText = x.getText();
			if (dropText.equals("CBS Dump File")) {
				x.click();
				break;
			}

		}

		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			test.fail(title);
			test.addScreenCaptureFromPath(ss());
			Assert.fail();
			resetTest();

		}
		test.info("Page Title : " + title);

		driver.findElement(By.xpath(pr.getProperty("CentralUserCBSDumpFileUploadBtn_loc"))).click();
		Thread.sleep(1000);
		log.info("Dump file upload started");
		Reporter.log(resultTime + " - " + "Dump file upload started");
		System.out.println(resultTime + " - " + "Dump file upload started");
		WebElement fileInput = driver.findElement(By.xpath(pr.getProperty("CentralUserSelectDumpFileBtn_loc")));
		fileInput.sendKeys(prd.getProperty("CentralUserDumpFilePath_val"));
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("CentralUserCBSDumpFileFinalUploadBtn_loc"))).click();

		Thread.sleep(1000);
		String alertMsg = null;

		if (driver.switchTo().alert().getText() != null) {
			// System.out.println(driver.switchTo().alert().getText());

			alertMsg = driver.switchTo().alert().getText();

			// System.out.println("entered in block");
			if (alertMsg.equals("File successfully uploaded.")) {
				log.info("Dump " + alertMsg);
				Reporter.log(resultTime + " - " + "Dump " + alertMsg);
				System.out.println(resultTime + " - " + "Dump " + alertMsg);
				test.pass("Dump " + alertMsg);
			} else {
				log.info("Dump " + alertMsg);
				Reporter.log(resultTime + " - " + "Dump " + alertMsg);
				System.out.println(resultTime + " - " + "Dump " + alertMsg);
				test.warning("Dump " + alertMsg);
			}
			Thread.sleep(1000);
			backupScreenshot();
			test.addScreenCaptureFromPath(ss());
			Thread.sleep(500);
			driver.switchTo().alert().accept();

		}
		
		
	}

	// Run Image Service .exe file
	// @Test(priority = 3, groups = { "New dump Upload and Process" })
	public void runImageService() throws HeadlessException, IOException, AWTException, InterruptedException {
		test = extent.createTest("runImageService", "Execute image service exe file")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("New dump Upload and Process");
		log.info("Image Upload Service exe file executed");
		Reporter.log(resultTime + " - " + "Image Upload Service exe file executed");
		System.out.println(resultTime + " - " + "Image Upload Service exe file executed");
		Runtime.getRuntime().exec(prd.getProperty("CKYCImageUploadExeFilePath_val"));
		Thread.sleep(900);
		backupScreenshot();
		test.addScreenCaptureFromPath(ss());
		test.info("Image Upload Service exe file executed");
	}

	// Generate Bulk File
	@Test(priority = 4, groups = { "New dump Upload and Process" })
	public void generateCkycBulkFile() throws InterruptedException, IOException, HeadlessException, AWTException {
		test = extent.createTest("generateCkycBulkFile", "Validate functionality of generating CKYC Bulk file")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("New dump Upload and Process");
		log.info("CKYC Bulk File Generate Test Started.");
		Reporter.log(resultTime + " - " + "CKYC Bulk File Generate Test Started");
		System.out.println(resultTime + " - " + "CKYC Bulk File Generate Test Started");
		WebElement exportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserExportTab_loc")));
		js.executeScript("arguments[0].click()", exportTab);
		List<WebElement> exportDropOpt = driver.findElements(By.xpath(pr.getProperty("CentralUserExportDropOpt_loc")));
		for (WebElement x : exportDropOpt) {
			String dropText = x.getText();
			if (dropText.equals("CKYC Bulk File")) {
				x.click();
				break;
			}
		}

		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		test.info("Page Title : " + title);
		driver.findElement(By.xpath(pr.getProperty("CentralUserGenerateCKYCBulkViewRecordBtn_loc"))).click();
		driver.findElement(By.xpath(pr.getProperty("CentralUserGenerateBulkBtn_loc"))).click();

		Thread.sleep(1000);
		if (driver.switchTo().alert().getText() != null) {
			String alertText = driver.switchTo().alert().getText();
			System.out.println(resultTime + " - " + "CKYC Bulk File --> " + alertText);
			log.info("CKYC Bulk " + alertText);
			Reporter.log(resultTime + " - " + "CKYC Bulk " + alertText);
			test.info("CKYC Bulk " + alertText);
			Thread.sleep(1000);
			backupScreenshot();
			test.addScreenCaptureFromPath(ss());
			Thread.sleep(500);
			driver.switchTo().alert().accept();
		}

	}

// Upload CKYC Response file
	@Test(priority = 5, groups = { "Upload Response" })
	public void uploadCkycResponce() throws InterruptedException, IOException, HeadlessException, AWTException {
		test = extent.createTest("uploadCkycResponce", "Validating CKYC Response file upload functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Upload Response");
		log.info("CKYC Responce upload started");
		Reporter.log(resultTime + " - " + "CKYC Responce upload started");
		System.out.println(resultTime + " - " + "CKYC Responce upload started");
		WebElement imporTab = driver.findElement(By.xpath(pr.getProperty("CentralUserImportTab_loc")));
		js.executeScript("arguments[0].click()", imporTab);
		List<WebElement> importDropOpt = driver.findElements(By.xpath(pr.getProperty("CentralUserImportDropOPt_loc")));
		for (WebElement x : importDropOpt) {
			String dropText = x.getText();
			if (dropText.equals("CKYC Response")) {
				x.click();
				break;
			}
		}

		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		test.info("Page Title : " + title);
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("CentralUserCKYCResponseUploadImportBtn_loc"))).click();
		WebElement ckycReponseFile = driver
				.findElement(By.xpath(pr.getProperty("CentralUserCKYCResponseFileSelectBtn_loc")));
		ckycReponseFile.sendKeys(prd.getProperty("CentralUserCKYCResponseFile_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserCKYCResponseFileFinalUploadBtn_loc"))).click();

		String alertMsg = null;
		Thread.sleep(1000);
		if (driver.switchTo().alert().getText() != null) {
			alertMsg = driver.switchTo().alert().getText();

			System.out.println(resultTime + " - " + alertMsg);
			log.info("Response " + alertMsg);
			Reporter.log(resultTime + " - " + "Response " + alertMsg);
			test.info("Response " + alertMsg);
			Thread.sleep(1000);
			backupScreenshot();
			test.addScreenCaptureFromPath(ss());
			Thread.sleep(500);
			driver.switchTo().alert().accept();
		}
	}

// CKYC ID generated Report

	@Test(priority = 6, groups = { "CKYC Id Generated Report" })
	public void ckycIdGeneretedReport() throws InterruptedException, IOException, HeadlessException, AWTException {
		test = extent
				.createTest("ckycIdGeneretedReport",
						"Validating Functionality of generating report for CKYC ID generated customer")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("CKYC Id Generated Report");
		log.info("CKYC ID Grnerate Report Test Start");
		test.info("CKYC ID Grnerate Report Test Start");
		Reporter.log(resultTime + " - " + "CKYC ID Grnerate Report Test Start");
		System.out.println(resultTime + " - " + "CKYC ID Grnerate Report Test Start");
		WebElement exportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserExportTab_loc")));
		js.executeScript("arguments[0].click()", exportTab);
		List<WebElement> exportDroplist = driver.findElements(By.xpath(pr.getProperty("CentralUserExportDropOpt_loc")));
		for (WebElement x : exportDroplist) {
			String dropText = x.getText();
			if (dropText.equals("CKYC Id")) {
				x.click();
				break;
			}

		}

		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		test.info("Page Title : " + title);

//Start Date Picker---->

	
		WebElement startDate = driver
				.findElement(By.xpath(pr.getProperty("CentralUserCKYCIdGeneratedReportStartDate_loc")));
		startDate.click();
		Thread.sleep(1000);
		// Pick year
				WebElement year = driver.findElement(By.xpath(pr.getProperty("CentralUserCKYCIdGenerateReportYearSelect_loc")));
				Select yearSelect = new Select(year);
				yearSelect.selectByVisibleText(prd.getProperty("CentralUserCKYCGeneratedIdReport_StartDateYear_val"));
		// Pick month
		WebElement month = driver
				.findElement(By.xpath(pr.getProperty("CentralUserCKYCIdGenerateReportMonthSelect_loc")));
		Select monthSelect = new Select(month);
		monthSelect.selectByVisibleText(prd.getProperty("CentralUserCKYCGeneratedIdReport_StartDateMonth_val"));

		

		// Pick Date
		List<WebElement> dates = driver
				.findElements(By.xpath(pr.getProperty("CentralUserCKYCIdGenerateReportTotalDates_loc")));
		for (WebElement x : dates) {
			String dateText = x.getText();
			if (dateText.equals(prd.getProperty("CentralUserCKYCGeneratedIdReport_StartDateDate_val"))) {
				x.click();

				break;
			}
		}

		String stratDt = "Start Date Selected : "
				+ prd.getProperty("CentralUserCKYCGeneratedIdReport_StartDateDate_val") + " "
				+ prd.getProperty("CentralUserCKYCGeneratedIdReport_StartDateMonth_val") + " "
				+ prd.getProperty("CentralUserCKYCGeneratedIdReport_StartDateYear_val");
		test.info(stratDt);
		String endDt = "End Date Selected as :" + prd.getProperty("CentralUserCKYCGeneratedIdReport_EndDateDate_val")
				+ " " + prd.getProperty("CentralUserCKYCGeneratedIdReport_EndDateMonth_val") + " "
				+ prd.getProperty("CentralUserCKYCGeneratedIdReport_EndDateYear_val");
		test.info(endDt);
		Thread.sleep(1000);

		// End Date picker

		
		WebElement endDate = driver
				.findElement(By.xpath(pr.getProperty("CentralUserCKYCIdGeneratedReportEndDate_loc")));
		endDate.click();
		Thread.sleep(1000);
		
		// Pick year
				WebElement endYear = driver
						.findElement(By.xpath(pr.getProperty("CentralUserCKYCIdGenerateReportEndYearSelect_loc")));
				Select endYearSelect = new Select(endYear);
				endYearSelect.selectByVisibleText(prd.getProperty("CentralUserCKYCGeneratedIdReport_EndDateYear_val"));

		// Pick month
		WebElement endMonth = driver
				.findElement(By.xpath(pr.getProperty("CentralUserCKYCIdGenerateReportEndMonthSelect_loc")));
		Select endMonthSelect = new Select(endMonth);
		endMonthSelect.selectByVisibleText(prd.getProperty("CentralUserCKYCGeneratedIdReport_EndDateMonth_val"));

		
		// Pick Date
		List<WebElement> endDates = driver
				.findElements(By.xpath(pr.getProperty("CentralUserCKYCIdGenerateReportEndTotalDates_loc")));
		for (WebElement x : endDates) {
			String dateText = x.getText();
			if (dateText.equals(prd.getProperty("CentralUserCKYCGeneratedIdReport_EndDateDate_val"))) {
				x.click();

				break;
			}
		}

		try {
			if (driver.switchTo().alert().getText() != null) {
				String alertText = driver.switchTo().alert().getText();
				System.out.println(resultTime + " - " + "CKYC Generated ID " + alertText);
				log.info("CKYC Generated ID " + alertText);
				Reporter.log(resultTime + " - " + "CKYC Generated ID " + alertText);
				if (alertText.contains("should be greater than")) {
					test.error("CKYC Generated ID " + alertText);
				}

				Thread.sleep(1000);
				backupScreenshot();
				test.addScreenCaptureFromPath(ss());
				Thread.sleep(500);
				driver.switchTo().alert().accept();
			}
		} catch (Exception e) {
			System.out.println(resultTime + " - " + "CKYC Generated ID " + e);
		}

		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("CentralUserCkycGenerateReportGenerateBtn_loc"))).click();

		Thread.sleep(1000);
		if (driver.switchTo().alert().getText() != null) {
			String alertText = driver.switchTo().alert().getText();
			System.out.println(resultTime + " - " + "CKYC Generated ID " + alertText);
			log.info("CKYC Generated ID " + alertText);
			Reporter.log(resultTime + " - " + "CKYC Generated ID " + alertText);
			if (alertText.contains("should be greater than")) {
				test.error("CKYC Generated ID " + alertText);
			} else {
				test.info("CKYC Generated ID " + alertText);
			}

			Thread.sleep(1000);
			backupScreenshot();
			test.addScreenCaptureFromPath(ss());
			Thread.sleep(500);
			driver.switchTo().alert().accept();
		}

	}

//Generate Pending Customer Report
	@Test(priority = 7, groups = { "Pending Cust Report" })
	public void reportCBSImportError() throws InterruptedException, IOException, HeadlessException, AWTException {

		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateCBSImportErrorReport",
						"Validate CBS Import Error report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("GeneratingCBS Import report test start");
		log.info("Generating CBS Import Report");
		Reporter.log(resultTime + " - " + "Generating CBS Import Report");
		System.out.println(resultTime + " - " + "Generating CBS Import Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}

			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("CBS Import Error")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();

					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		

		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}

		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if (tableSize == 0) {
				test.info("No Record Found");
			}
		} catch (Exception e) {
		}

		test.info("Page Title : " + title);

	}

	@Test(priority = 8)
	public void reportDraftSave() throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent.createTest("gernerateDraftSaveReport", "Validate Draft Save report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating Draft Save report test start");
		log.info("Generating Draft Save Report");
		Reporter.log(resultTime + " - " + "Generating Draft Save Report");
		System.out.println(resultTime + " - " + "Generating Draft Save Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Draft Saved")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if (tableSize == 0) {
				test.info("No Record Found");
			}
		} catch (Exception e) {
		}
		test.info("Page Title : " + title);

	}

	@Test(priority = 9)
	public void reportImageUploadPending() throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateImageUploadPendingReport",
						"Validate Image Upload Pending report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating Image Upload Pending report test start");
		log.info("Generating Draft Save Report");
		Reporter.log(resultTime + " - " + "Generating Draft Save Report");
		System.out.println(resultTime + " - " + "Generating Draft Save Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Image Upload Pending")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
	
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);

	}

	@Test(priority = 10)
	public void reportPendingCheckerApproval()
			throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gerneratePendingCheckerApprovalReport",
						"Validate Pending checker Approval report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating Pending checker Approval report test start");
		log.info("Generating Pending checker Approval Report");
		Reporter.log(resultTime + " - " + "GeneratingPending checker Approval Report");
		System.out.println(resultTime + " - " + "Generating Pending checker Approval Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Pending Checker Approval")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 11)
	public void reportRejectedByChecker() throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateRejectedByCheckerReport",
						"Validate Rejected By Checker report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating Rejected By Checker report test start");
		log.info("Generating Rejected By Checker Report");
		Reporter.log(resultTime + " - " + "Generating Rejected By Checker Report");
		System.out.println(resultTime + " - " + "Generating Rejected By Checker Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Rejected By Checker")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();
		}
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);

	}

	@Test(priority = 12)
	public void reportApprovedByChecker() throws HeadlessException, IOException, AWTException, InterruptedException {

		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateApprovedByCheckerReport",
						"Validate Approved By Checker report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating Approved By Checker report test start");
		log.info("Generating Approved By Checker Report");
		Reporter.log(resultTime + " - " + "Generating Approved By Checker Report");
		System.out.println(resultTime + " - " + "Generating Approved By Checker Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Approved By Checker")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			Assert.fail();
			test.fail(title);

		}
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 13)
	public void reportBulkFileGenerated() throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateBulkFileGeneratedReport",
						"Validate Bulk File Generated report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating Bulk File Generated report test start");
		log.info("Generating Bulk File Generated Report");
		Reporter.log(resultTime + " - " + "Generating Bulk File Generated Report");
		System.out.println(resultTime + " - " + "Generating Bulk File Generated Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Bulk File Generated")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 14)
	public void reportFiReconcilationPending()
			throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateFiReconcilationPendingReport",
						"Validate FI Reconcilation Pending report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating FI Reconcilation Pending report test start");
		log.info("Generating FI Reconcilation Pending Report");
		Reporter.log(resultTime + " - " + "Generating FI Reconcilation Pending Report");
		System.out.println(resultTime + " - " + "Generating FI Reconcilation Pending Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("FI Reconcilation Pending")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 15)
	public void reportUploadedToCkycPortal() throws InterruptedException, HeadlessException, IOException, AWTException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateUploadedToCkycPortalReport",
						"Validate Uploaded to CKYC Portal report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating Uploaded to CKYC Portal report test start");
		log.info("Generating Uploaded to CKYC Portal Report");
		Reporter.log(resultTime + " - " + "Generating Uploaded to CKYC Portal Report");
		System.out.println(resultTime + " - " + "Generating Uploaded to CKYC Portal Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Uploaded To CKYC Portal")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			resetTest();
			test.fail(title);
			Assert.fail();
			

		}
		
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 16)
	public void reportCkycIdExisting() throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateCkycIdExistingReport",
						"Validate CKYC ID existing report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating CKYC ID existing report test start");
		log.info("Generating CKYC ID existing Report");
		Reporter.log(resultTime + " - " + "Generating CKYC ID existing Report");
		System.out.println(resultTime + " - " + "Generating CKYC ID existing Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("CKYC ID Existing")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 17)
	public void reportRjectedCkycPortal() throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateRjectedCkycPortalReport",
						"Validate Rejected CKYC Portal report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating  Rejected CKYC Portal report test start");
		log.info("Generating  Rejected CKYC Portal Report");
		Reporter.log(resultTime + " - " + "Generating  Rejected CKYC Portal Report");
		System.out.println(resultTime + " - " + "Generating  Rejected CKYC Portal Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Rejected CKYC Portal")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 18)
	public void reportStageOneResponce() throws InterruptedException, HeadlessException, IOException, AWTException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateStageOneResponceReport",
						"Validate Stage One Responce report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating  Stage One Responce report test start");
		log.info("Generating  Stage One Responce Report");
		Reporter.log(resultTime + " - " + "Generating  Stage One Responce Report");
		System.out.println(resultTime + " - " + "Generating Stage One Responce Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("Stage One Response")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 19)
	public void reportCkycIdGenerated() throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateCkycIdGeneratedReport",
						"Validate CKYC ID Generated report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating CKYC ID Generated report test start");
		log.info("Generating  CKYC ID Generated Report");
		Reporter.log(resultTime + " - " + "Generating  CKYC ID Generated Report");
		System.out.println(resultTime + " - " + "Generating CKYC ID Generated Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("CKYC ID Generated")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
	
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
		
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 20)
	public void reportFiReconResponse() throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateFiReconResponseReport",
						"Validate FI Recon Response report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating FI Recon Response report test start");
		log.info("Generating  FI Recon Response Report");
		Reporter.log(resultTime + " - " + "Generating  FI Recon Response Report");
		System.out.println(resultTime + " - " + "Generating FI Recon Response Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("FI Recon Response")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
		
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 21)
	public void reportCkycRecordSuccUpdated()
			throws HeadlessException, IOException, AWTException, InterruptedException {
		// driver.navigate().refresh();
		test = extent
				.createTest("gernerateCKYCIDSuccessfullyUpdatedReport",
						"Validate CKYC ID Successfully Updated report generation functionality")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Report");

		test.info("Generating CKYC ID Successfully Updated report test start");
		log.info("Generating  CKYC ID Successfully Updated Report");
		Reporter.log(resultTime + " - " + "Generating  CKYC ID Successfully Updated Report");
		System.out.println(resultTime + " - " + "Generating CKYC ID Successfully Updated Report");
		Thread.sleep(500);

		try {
			WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
			js.executeScript("arguments[0].click()", reportTab);
			List<WebElement> reportTabOptions = driver
					.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
			for (WebElement x : reportTabOptions) {

				String dropText = x.getText();
				if (dropText.contains("Status")) {

					System.out.println(dropText);
					// x.click();
					Actions action = new Actions(driver);
					action.moveToElement(x).perform();
					// js.executeScript("arguments[0].click()", x);
					break;
				}
			}
			Thread.sleep(1000);

			List<WebElement> statusReportsLi = driver
					.findElements(By.xpath(pr.getProperty("CentralUserStatusReportList_loc")));
			for (WebElement x : statusReportsLi) {

				if (x.getText().contains("CKYC Record Successfully Updated")) {
					js.executeScript("arguments[0].click()", x);
					// x.click();
					System.out.println("Report --" + x.getText());
					test.info(x.getText());
					Thread.sleep(1000);
					test.addScreenCaptureFromPath(ss());
					Thread.sleep(1000);
					
					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ElementReference Exception");
			test.addScreenCaptureFromPath(ss());
		}
		
		driver.findElement(By.xpath(pr.getProperty("CentralUserExcelDownloadBtn_loc"))).click();
		

		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			
			resetTest();
			test.fail(title);
			Assert.fail();

		}
		
		try {
			List<WebElement> table = driver.findElements(By.xpath(pr.getProperty("CentralUserRecordsTableRows_loc")));
			int tableSize = table.size();
			if(tableSize == 0) {
				test.info("No Record Found");
			}
		}
		catch(Exception e) {}
		test.info("Page Title : " + title);
	}

	@Test(priority = 22)
	public void custSearchDetailRecord() throws InterruptedException, HeadlessException, IOException, AWTException {
		test = extent
				.createTest("SearchdetailrecordwithcustomerID",
						"Validate if user able to search detail record with customer ID")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Detail Search");
		WebElement reportTab = driver.findElement(By.xpath(pr.getProperty("CentralUserReportTab_loc")));
		js.executeScript("arguments[0].click()", reportTab);
		List<WebElement> reportTabOptions = driver
				.findElements(By.xpath(pr.getProperty("CentralUserReportDropOpts_loc")));
		for (WebElement x : reportTabOptions) {

			String dropText = x.getText();
			if (dropText.contains("Customer Record Detail Report")) {

				System.out.println(dropText);
				x.click();

				// js.executeScript("arguments[0].click()", x);
				break;
			}
		}

		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
		
			resetTest();
			Assert.fail();
			test.fail(title);

		}
		test.info("Page Title : " + title);

		test.info("Searching Detail Record for " + prd.getProperty("CustomerId_val"));

		driver.findElement(By.xpath(pr.getProperty("CentralUserCustRecordDetailedReportInput_loc")))
				.sendKeys(prd.getProperty("CustomerId_val"));
		Thread.sleep(1000);
		test.addScreenCaptureFromPath(ss());
		driver.findElement(By.xpath(pr.getProperty("CentralUserCustDetailRecordSearchBtn_loc"))).click();

		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (alertText.contains("not Exist")) {
				test.error(alertText);
			} else {
				test.info(alertText);
			}

			System.out.println(alertText);
			Thread.sleep(1000);
			test.addScreenCaptureFromPath(ss());
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println(e);
		}
	}

	@Test(priority = 23)
	public void helpTab() throws HeadlessException, IOException, AWTException, InterruptedException {
		test = extent.createTest("helpTab", "Validate Help Tab").assignAuthor(prd.getProperty(" author_val"))
				.assignCategory("sanity");
		test.info("Checking Help tab");
		WebElement helpTab = driver.findElement(By.xpath(pr.getProperty("CentralUserHelpTab_loc")));
		js.executeScript("arguments[0].click()", helpTab);
		Thread.sleep(1000);
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			
			test.addScreenCaptureFromPath(ss());
			resetTest();
			test.fail(title);
			Assert.fail();
			

		}

		else {
			test.info("Help tab working");
			test.addScreenCaptureFromPath(ss());
		}
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
