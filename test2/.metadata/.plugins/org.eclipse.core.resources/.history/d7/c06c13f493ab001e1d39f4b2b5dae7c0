package CKYC.ICICI;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.*;

public class MakerUser extends BaseClass {
	Logger log = LogManager.getLogger("MakerUser");
	WebElement isErrorDisplay;

	@Test(priority = 0, groups = { "Check Draft Cust", "Search and Download", "MultiSearch" })
	public void makerUserLogin() throws HeadlessException, IOException, AWTException, InterruptedException {
		test = extent.createTest("makerUserLogin", "Trying to logged in with Maker User")
				.assignAuthor(prd.getProperty("author_val"))
				.assignCategory("Check Draft Cust", "Search and Download", "MultiSearch");
		// test = extent.createTest("makerUserLogin");
		driver.get(prd.getProperty("URL"));
		catchBrokenPage();
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginId_loc")))
				.sendKeys(prd.getProperty("MakerUserLoginId_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginPass_loc")))
				.sendKeys(prd.getProperty("MakerUserLoginPas_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginBtn_loc"))).click();
		WebElement redirectDrop = driver.findElement(By.xpath(pr.getProperty("CentralUserRedirectDrop_loc")));
		Select selectRedirectDrop = new Select(redirectDrop);
		selectRedirectDrop.selectByVisibleText(prd.getProperty("CentralUserLoginRedirectDropOpt_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserRedirectBtn_loc"))).click();
		catchBrokenPage();
		System.out.println(resultTime + " - Maker loggedin");

		log.info("Maker User LoggedIn");
		Reporter.log(resultTime + " - " + "Maker User LoggedIn");
		Thread.sleep(500);
		backupScreenshot();
		// System.out.println(d);
		test.addScreenCaptureFromPath(ss());
		// System.out.println(getSs());
		test.info("Maker User Logged in");

	}

	@Test(priority = 1, groups = { "Check Draft Cust" })
	public void pendingCustomerDetails() throws HeadlessException, IOException, AWTException, InterruptedException {
		test = extent.createTest("pendingCustomerDetails", "Checking Total Number of Pending Customer")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Check Draft Cust");
		log.info("Checking Pending customer count");
		Reporter.log(resultTime + " - " + "Checking Pending customer count");
		WebElement ckycManagment = driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementTab_loc")));
		js.executeScript("arguments[0].click()", ckycManagment);
		List<WebElement> ckycMangList = driver
				.findElements(By.xpath(pr.getProperty("MakerUserCKYCManagementDropList_loc")));
		for (WebElement x : ckycMangList) {
			String dropText = x.getText();
			if (dropText.equals("Pending Customer List")) {
				x.click();
				break;
			}
		}
		
		catchBrokenPage();

		List<WebElement> pendingCustTableRow = driver
				.findElements(By.xpath(pr.getProperty("MakerUserCKYCManagementTableRowList_loc")));
		WebElement nextBtn = driver
				.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementPendingCustNextPgArrow_loc")));
		Boolean isNext = nextBtn.isDisplayed();

		int sizeLi;
		int count = 1;
		for (int i = 0; i <= count; i++) {
			if (isNext == true) {
				nextBtn.click();
				pendingCustTableRow.addAll(
						driver.findElements(By.xpath(pr.getProperty("MakerUserCKYCManagementTableRowList_loc"))));
				catchBrokenPage();

				break;
			}

			count = count + 1;
		}
		sizeLi = pendingCustTableRow.size();
		Boolean isListEmpty = pendingCustTableRow.isEmpty();
		// System.out.println(isListEmpty);
		if (isListEmpty == true) {
			log.info("No Pending Record Found For Maker Entry");
			Reporter.log(resultTime + " - " + "No Pending Record Found For Maker Entry");
			test.info("No Pending Record Found For Maker Entry");
		} else if (isListEmpty == false) {
			log.info("Total " + sizeLi + " Pending customer found for makers Entry");
			Reporter.log(resultTime + " - " + "Total " + sizeLi + " Pending customer found for makers Entry");
			System.out.println(resultTime + " - Total " + sizeLi + " Pending customer found for makers Entry");
			test.info("Total " + sizeLi + " Pending customer found for makers Entry");
		}

		sizeLi = 0;
		Thread.sleep(1000);
		backupScreenshot();
		test.addScreenCaptureFromPath(ss());

	}

	@Test(priority = 2, groups = { "Check Draft Cust" })
	public void pendingCustIsImgUpload() throws HeadlessException, IOException, AWTException, InterruptedException {

		test = extent
				.createTest("pendingCustIsImgUpload",
						"Validating if pending customer images are uploaded or not for recent customer ID")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Check Draft Cust");
		log.info("Checking images for recent pending customer");
		Reporter.log(resultTime + " - " + "Checking images for recent pending customer");
		WebElement ckycManagment = driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementTab_loc")));
		js.executeScript("arguments[0].click()", ckycManagment);
		List<WebElement> ckycMangList = driver
				.findElements(By.xpath(pr.getProperty("MakerUserCKYCManagementDropList_loc")));
		for (WebElement x : ckycMangList) {
			String dropText = x.getText();
			if (dropText.equals("Pending Customer List")) {
				x.click();
				break;
			}
		}
		
		catchBrokenPage();

		List<WebElement> pendingCustTableRow = driver
				.findElements(By.xpath(pr.getProperty("MakerUserCKYCManagementTableRowList_loc")));
		Boolean isListEmpty = pendingCustTableRow.isEmpty();
		List<WebElement> editList = driver
				.findElements(By.xpath(pr.getProperty("MakerUserCKYCManagementEditPendingCust_loc")));
		for (WebElement x : editList) {
			if (isListEmpty == false) {
				x.click();
				break;
			}
		}
		test.info("Checking Personal details Link");
		catchBrokenPage();
	
		
		test.addScreenCaptureFromPath(ss());
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("MakerUserPendingCustPOILink_loc"))).click();
		test.info("Checking Proof of identity Link");
		test.addScreenCaptureFromPath(ss());
		catchBrokenPage();
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("MakerUserPendingCustPOALink_loc"))).click();
		test.info("Checking Proof of Address Link");
		test.addScreenCaptureFromPath(ss());
		catchBrokenPage();
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("MakerUserPendingCustRelatedPersonLink_loc"))).click();
		test.info("Checking Related person details Link");
		test.addScreenCaptureFromPath(ss());
		catchBrokenPage();
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("MakerUserPendingCustOtherDetailsLink_loc"))).click();
		test.info("Checking Other Details Link");
		test.addScreenCaptureFromPath(ss());
		catchBrokenPage();
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("MakerUserPendingCustAttestationLink_loc"))).click();
		test.info("Checking Attestation Link");
		test.addScreenCaptureFromPath(ss());
		catchBrokenPage();
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCDetailsLink_loc"))).click();
		test.info("Checking CKYC details Link");
		test.addScreenCaptureFromPath(ss());
		catchBrokenPage();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath(pr.getProperty("MakerUserPendingCustUploadImagesLink_loc"))).click();
		test.info("Checking Upload Image Details Link");
		catchBrokenPage();
		List<WebElement> custImg = driver
				.findElements(By.xpath(pr.getProperty("MakerUserCKYCManagementPendingCustImg_loc")));
		Boolean isImgEmpty = custImg.isEmpty();
		// System.out.println(isImgEmpty);
		if (isImgEmpty == false) {
			log.info("Img uploaded for select customer");
			Reporter.log(resultTime + " - " + "Img uploaded for select customer");
			System.out.println(resultTime + " - Imag uploaded for select customer");
			test.info("Images uploaded for recent customer ID");
		} else if (isImgEmpty == true) {
			log.info("Images are not uploaded for recent customer");
			Reporter.log(resultTime + " - " + "Img not uploaded for select customer");
			System.out.println(resultTime + " - Img not uploaded for select customer");
			test.error("Images are not uploaded for recent customer");
		}
		Thread.sleep(1000);
		backupScreenshot();
		test.addScreenCaptureFromPath(ss());

	}

	@Test(priority = 3, groups = { "Search and Download" })
	public void searchAndDownload() throws InterruptedException, HeadlessException, IOException, AWTException {
		test = extent.createTest("searchAndDownload", "Validating Search and Download with single input")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("Search and Download");

		log.info("Search and Download Started");
		Reporter.log(resultTime + " - " + "Search and Download Started");
		WebElement ckycManagment = driver.findElement(By.xpath(pr.getProperty("MakerUserManageCustomerDataTab_loc")));
		js.executeScript("arguments[0].click()", ckycManagment);
		Thread.sleep(1000);
		List<WebElement> ckycMangList = driver
				.findElements(By.xpath(pr.getProperty("MakerUserManageCustomerDataTabDropList_loc")));
		for (WebElement x : ckycMangList) {
			String dropText = x.getText();
			if (dropText.contains("Search Customer Data in CKYC")) {
				x.click();
				break;
			}
		}

		String searchBy = prd.getProperty("MakerUserCKYCManagementSearchBy_val");
		switch (searchBy) {
		case "Aadhaar":
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownAdhar_loc")))
					.sendKeys(prd.getProperty("MakerUserCKYCManagementSearchByIdNo_val"));
			log.info("Searching with Aadhar no");
			test.info("Searching with Aadhar no");
			Reporter.log(resultTime + " - " + "Searching with Aadhar no");
			System.out.println(resultTime + " - " + "Searching with Aadhar no");
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			try {
				Thread.sleep(1000);
				isErrorDisplay = driver
						.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorAdhar_loc")));
				if (isErrorDisplay.isDisplayed() == true) {
					log.info(isErrorDisplay.getText());
					Reporter.log(resultTime + " - " + isErrorDisplay.getText());
					System.out.println(resultTime + " - " + isErrorDisplay.getText());
					test.warning(isErrorDisplay.getText());

				}
			} catch (Exception e) {
				System.out.println(resultTime + " - No error element found");

			}
			break;

		case "PAN":
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownPan_loc")))
					.sendKeys(prd.getProperty("MakerUserCKYCManagementSearchByIdNo_val"));
			log.info("Searching with PAN no");
			test.info("Searching with PAN no");
			Reporter.log(resultTime + " - " + "Searching with PAN no");
			System.out.println(resultTime + " - " + "Searching with PAN no");
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			try {
				Thread.sleep(1000);
				isErrorDisplay = driver
						.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorPAN_loc")));
				if (isErrorDisplay.isDisplayed() == true) {
					log.info(isErrorDisplay.getText());
					Reporter.log(resultTime + " - " + isErrorDisplay.getText());
					System.out.println(resultTime + " - " + isErrorDisplay.getText());
					test.warning(isErrorDisplay.getText());

				}
			} catch (Exception e) {
				System.out.println(resultTime + " - No error element found");
			}
			break;

		case "VoterID":
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownVoter_loc")))
					.sendKeys(prd.getProperty("MakerUserCKYCManagementSearchByIdNo_val"));
			log.info("Searching with VoterID");
			Reporter.log(resultTime + " - " + "Searching with VoterID");
			System.out.println(resultTime + " - " + "Searching with VoterID");
			test.info("Searching with VoterID");
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			try {
				Thread.sleep(1000);
				isErrorDisplay = driver.findElement(
						By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorVoterId_loc")));
				if (isErrorDisplay.isDisplayed() == true) {
					log.info(isErrorDisplay.getText());
					Reporter.log(resultTime + " - " + isErrorDisplay.getText());
					System.out.println(resultTime + " - " + isErrorDisplay.getText());
					test.warning(isErrorDisplay.getText());

				}
			} catch (Exception e) {
				System.out.println(resultTime + " - No error element found");
			}
			break;

		case "PassportNO":
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownPassportNo_loc")))
					.sendKeys(prd.getProperty("MakerUserCKYCManagementSearchByIdNo_val"));
			log.info("Searching with PassportNO");
			Reporter.log(resultTime + " - " + "Searching with PassportNO");
			System.out.println(resultTime + " - " + "Searching with PassportNO");
			test.info("Searching with PassportNO");
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			try {
				Thread.sleep(1000);
				isErrorDisplay = driver.findElement(
						By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorPassport_loc")));
				if (isErrorDisplay.isDisplayed() == true) {
					log.info(isErrorDisplay.getText());
					Reporter.log(resultTime + " - " + isErrorDisplay.getText());
					System.out.println(resultTime + isErrorDisplay.getText());
					test.warning(isErrorDisplay.getText());

				}
			} catch (Exception e) {
				System.out.println(resultTime + " - No error element found");
			}
			break;

		case "DrivingLicense":
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownDirvLic_loc")))
					.sendKeys(prd.getProperty("MakerUserCKYCManagementSearchByIdNo_val"));
			log.info("Searching with DrivingLicense");
			Reporter.log(resultTime + " - " + "Searching with DrivingLicense");
			System.out.println(resultTime + " - " + "Searching with DrivingLicense");
			test.info("Searching with DrivingLicense");
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			try {
				Thread.sleep(1000);
				isErrorDisplay = driver.findElement(
						By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorDrivingLic_loc")));
				if (isErrorDisplay.isDisplayed() == true) {
					log.error(isErrorDisplay.getText());
					Reporter.log(resultTime + " - " + isErrorDisplay.getText());
					System.out.println(resultTime + " - " + isErrorDisplay.getText());
					test.warning(isErrorDisplay.getText());

				}
			} catch (Exception e) {
				System.out.println(resultTime + " - No error element found");
			}
			break;
		case "CKYCID":
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownCkycId_loc")))
					.sendKeys(prd.getProperty("MakerUserCKYCManagementSearchByIdNo_val"));
			log.info("Searching with CKYCID");
			Reporter.log(resultTime + " - " + "Searching with CKYCID");
			System.out.println(resultTime + " - " + "Searching with CKYCID");
			test.info("Searching with CKYCID");
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			try {
				Thread.sleep(1000);
				isErrorDisplay = driver
						.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorCkyc_loc")));
				if (isErrorDisplay.isDisplayed() == true) {
					log.error(isErrorDisplay.getText());
					Reporter.log(resultTime + " - " + isErrorDisplay.getText());
					System.out.println(resultTime + isErrorDisplay.getText());
					test.warning(isErrorDisplay.getText());

				}
			} catch (Exception e) {
				System.out.println(resultTime + " - No error element found");
			}
			break;

		case "NREGA":
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownNREGA_loc")))
					.sendKeys(prd.getProperty("MakerUserCKYCManagementSearchByIdNo_val"));
			log.info("Searching with NREGA");
			Reporter.log(resultTime + " - " + "Searching with NREGA");
			System.out.println(resultTime + " - " + "Searching with NREGA");
			test.info("Searching with NREGA");
			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			try {
				Thread.sleep(1000);
				isErrorDisplay = driver
						.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorNREGA_loc")));
				if (isErrorDisplay.isDisplayed() == true) {
					log.error(isErrorDisplay.getText());
					Reporter.log(resultTime + " - " + isErrorDisplay.getText());
					System.out.println(resultTime + isErrorDisplay.getText());
					test.warning(isErrorDisplay.getText());
					// System.out.println("Block 3");
				}
			} catch (Exception e) {
				System.out.println(resultTime + " - No error element found");
			}
			break;

		}

		try {

			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			log.info(alertText);
			Reporter.log(resultTime + " - " + alertText);
			System.out.println(resultTime + " - Alert data: " + alertText);
			Thread.sleep(500);
			backupScreenshot();
			test.addScreenCaptureFromPath(ss());
			alert.accept();
			Assert.fail("Test failed:" + alertText);
			test.fail(alertText);

		} catch (Exception e) {
			System.out.println(resultTime + " - No Alert Present");

		}
		System.out.println(resultTime + " - End Search and Download Test");

		Thread.sleep(500);
		backupScreenshot();
		test.addScreenCaptureFromPath(ss());

	}
	
	public static void catchBrokenPage() throws HeadlessException, IOException, AWTException {
		String title = driver.getTitle();
		if (title.contains("cannot be found")) {
			test.fail(title);
			test.addScreenCaptureFromPath(ss());
			test.info("Page Title : " + title);
			Assert.fail();
			BaseClass.resetTest();

		}
		
	}

}
