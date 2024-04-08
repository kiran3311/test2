package CKYC.ICICI;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class ImageService extends BaseClass {
	Logger log = LogManager.getLogger("ImageService");
	
	//@Test
	public void runImageService() throws HeadlessException, IOException, AWTException {
		log.info("Image Upload Service exe file executed");
		Runtime.getRuntime().exec("D:\\KiranG\\ICICI Ckyc\\ICICI_Patch_1_app_server\\ICICI_Patch_1_app_server\\ImageServiceExe\\ECKYCImageUpload.exe");
		System.out.println("Image service executed");
		
		ss();
	}
	
	

}
