package CKYC.ICICI;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AllSearch extends BaseClass {
	WebElement isErrorDisplay;

	 @Test(priority = -1)
	public void makerUserLogin() throws HeadlessException, IOException, AWTException, InterruptedException {
		 test=extent.createTest( "Maker Login", "Validate Maker user login functionality");

		driver.get(prd.getProperty("URL"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginId_loc")))
				.sendKeys(prd.getProperty("MakerUserLoginId_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginPass_loc")))
				.sendKeys(prd.getProperty("MakerUserLoginPas_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserLoginBtn_loc"))).click();
		WebElement redirectDrop = driver.findElement(By.xpath(pr.getProperty("CentralUserRedirectDrop_loc")));
		Select selectRedirectDrop = new Select(redirectDrop);
		selectRedirectDrop.selectByVisibleText(prd.getProperty("CentralUserLoginRedirectDropOpt_val"));
		driver.findElement(By.xpath(pr.getProperty("CentralUserRedirectBtn_loc"))).click();
		System.out.println(resultTime + " - Maker loggedin");

		log.info("Maker User LoggedIn");
		test.info("Maker User LoggedIn");
		Reporter.log(resultTime + " - " + "Maker User LoggedIn");
		Thread.sleep(500);
		backupScreenshot();
		test.addScreenCaptureFromPath(ss());
		// System.out.println(d);

	}

	@Test(priority = 2, groups = { "MultiSearch" })
	public void allSearchDownload() throws InterruptedException, HeadlessException, IOException, AWTException {
		test = extent
				.createTest("allSearchDownload",
						"Validate seach and download functionality with multiple input options")
				.assignAuthor(prd.getProperty("author_val")).assignCategory("MultiSearch");
		test.info("Multi search and download started");

		log.info("Multi Search and Download Started");
		Reporter.log(resultTime + " - " + " Multi Search and Download Started");
		System.out.println(resultTime + " - " + " Multi Search and Download Started");
		WebElement ckycManagment = driver.findElement(By.xpath(pr.getProperty("MakerUserManageCustomerDataTab_loc")));
		js.executeScript("arguments[0].click()", ckycManagment);
		List<WebElement> ckycMangList = driver
				.findElements(By.xpath(pr.getProperty("MakerUserManageCustomerDataTabDropList_loc")));
		for (WebElement x : ckycMangList) {
			String dropText = x.getText();
			if (dropText.contains("Search Customer Data in CKYC")) {
				x.click();
				break;
			}
		}

		String inputIds = prd.getProperty("MultiSearchByField_val");

		if (inputIds.contains("/")) {
			String[] inputArr = inputIds.split("/");
			int size = inputArr.length;
			for (int x = 0; x < size; x++) {

				if (inputArr[x].trim().equalsIgnoreCase("Aadhaar")) {
					Thread.sleep(500);
					driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownAdhar_loc")))
							.sendKeys(prd.getProperty("SearchByAdharId_val"));
					log.info("Searching with Aadhar no");
					Reporter.log(resultTime + " - " + "Searching with Aadhar no");
					System.out.println(resultTime + " - " + "Searching with Aadhar no");
					test.info("Searching with Aadhar no");

				}

				if (inputArr[x].trim().equalsIgnoreCase("PAN")) {
					Thread.sleep(500);
					driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownPan_loc")))
							.sendKeys(prd.getProperty("SearchByPanId_val"));
					log.info("Searching with PAN no");
					Reporter.log(resultTime + " - " + "Searching with PAN no");
					System.out.println(resultTime + " - " + "Searching with PAN no");
					test.info("Searching with PAN no");

				}

				if (inputArr[x].trim().equalsIgnoreCase("VoterID")) {
					driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownVoter_loc")))
							.sendKeys(prd.getProperty("SearchByVoterId_val"));
					log.info("Searching with VoterID");
					Reporter.log(resultTime + " - " + "Searching with VoterID");
					System.out.println(resultTime + " - " + "Searching with VoterID");
					test.info("Searching with VoterID");

				}

				if (inputArr[x].trim().equalsIgnoreCase("PassportNO")) {
					driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownPassportNo_loc")))
							.sendKeys(prd.getProperty("SearchByPassportId_val"));
					log.info("Searching with PassportNO");
					Reporter.log(resultTime + " - " + "Searching with PassportNO");
					System.out.println(resultTime + " - " + "Searching with PassportNO");
					test.info("Searching with PassportNO");

				}

				if (inputArr[x].trim().equalsIgnoreCase("CKYCID")) {
					driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownCkycId_loc")))
							.sendKeys(prd.getProperty("SearchByCkycId_val"));
					log.info("Searching with CKYCID");
					Reporter.log(resultTime + " - " + "Searching with CKYCID");
					System.out.println(resultTime + " - " + "Searching with CKYCID");
					test.info("Searching with CKYCID");

				}

				if (inputArr[x].trim().equalsIgnoreCase("DrivingLicense")) {
					driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownDirvLic_loc")))
							.sendKeys(prd.getProperty("SearchByDrivingLicId_val"));
					log.info("Searching with DrivingLicense");
					Reporter.log(resultTime + " - " + "Searching with DrivingLicense");
					System.out.println(resultTime + " - " + "Searching with DrivingLicense");
					test.info("Searching with DrivingLicense");

				}

				if (inputArr[x].trim().equalsIgnoreCase("NREGA")) {
					driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownNREGA_loc")))
							.sendKeys(prd.getProperty("SearchByNREGA_val"));
					log.info("Searching with NREGA");
					Reporter.log(resultTime + " - " + "Searching with NREGA");
					System.out.println(resultTime + " - " + "Searching with NREGA");
					test.info("Searching with NREGA");

				}

			}

		}

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
			// System.out.println(resultTime + " - No error element found");
		}

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
			// System.out.println(resultTime + " - No error element found");
		}

		try {
			Thread.sleep(1000);
			isErrorDisplay = driver
					.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorVoterId_loc")));
			if (isErrorDisplay.isDisplayed() == true) {
				log.info(isErrorDisplay.getText());
				Reporter.log(resultTime + " - " + isErrorDisplay.getText());
				System.out.println(resultTime + " - " + isErrorDisplay.getText());
				test.warning(isErrorDisplay.getText());

			}
		} catch (Exception e) {
			// System.out.println(resultTime + " - No error element found");
		}

		try {
			Thread.sleep(1000);
			isErrorDisplay = driver
					.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorPassport_loc")));
			if (isErrorDisplay.isDisplayed() == true) {
				log.info(isErrorDisplay.getText());
				Reporter.log(resultTime + " - " + isErrorDisplay.getText());
				System.out.println(resultTime + " - " + isErrorDisplay.getText());
				test.warning(isErrorDisplay.getText());

			}
		} catch (Exception e) {
			// System.out.println(resultTime + " - No error element found");
		}

		try {
			Thread.sleep(1000);
			isErrorDisplay = driver
					.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorDrivingLic_loc")));
			if (isErrorDisplay.isDisplayed() == true) {
				log.error(isErrorDisplay.getText());
				Reporter.log(resultTime + " - " + isErrorDisplay.getText());
				System.out.println(resultTime + " - " + isErrorDisplay.getText());
				test.warning(isErrorDisplay.getText());

			}
		} catch (Exception e) {
			// System.out.println(resultTime + " - No error element found");
		}

		try {
			Thread.sleep(1000);
			isErrorDisplay = driver
					.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownInputErrorCkyc_loc")));
			if (isErrorDisplay.isDisplayed() == true) {
				log.info(isErrorDisplay.getText());
				Reporter.log(resultTime + " - " + isErrorDisplay.getText());
				System.out.println(resultTime + " - " + isErrorDisplay.getText());
				test.warning(isErrorDisplay.getText());

			}
		} catch (Exception e) {
			// System.out.println(resultTime + " - No error element found");
		}

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
			// System.out.println(resultTime + " - No error element found");
		}

		try {

			driver.findElement(By.xpath(pr.getProperty("MakerUserCKYCManagementSearchDownFinalSearchBtn_loc"))).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			log.info(alertText);
			Reporter.log(resultTime + " - " + alertText);
			System.out.println(resultTime + " - Alert data: " + alertText);
			Thread.sleep(500);
			// ss();
			test.addScreenCaptureFromPath(ss());
			alert.accept();
			test.error(alertText);
			Assert.fail(
					"Test failed : An error occurred: There was no endpoint listening at http://192.168.100.101:9000/CkycApiService.svc");

		} catch (Exception e) {
			// System.out.println(resultTime + " - No Alert Present");

		}
		System.out.println(resultTime + " - End Search and Download Test");
		Thread.sleep(800);
		backupScreenshot();
		test.addScreenCaptureFromPath(ss());

	}

}
