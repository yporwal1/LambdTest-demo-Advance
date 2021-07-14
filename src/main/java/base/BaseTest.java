package base;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseTest {

	public static String executionEnv;
	public static ThreadLocal<WebDriver> wdriver = new ThreadLocal<WebDriver>();
	
	public static final String CURRENT_DIR = System.getProperty("user.dir");
	private static final String CHROME_DRIVER_KEY = null;
	
	private HashMap<String, String> getCapabilitiesfromParams(String params) {
		HashMap<String, String> cap = new HashMap<String, String>();

		for (String pair : params.split(";")) {
			String[] entry = pair.split(":");
			cap.put(entry[0], entry[1]);
		}

		return cap;
	}
	
	@Parameters({ "remoteurl", "Execute", "capabilities" })
	@BeforeClass
	public void launchBrowser(String remoteurl, String Execute, String capabilities) throws Exception {
		System.setProperty("ExectionPlatform", Execute);
		
		BaseTest.executionEnv = Execute.toLowerCase();

		HashMap<String, String> capMap = this.getCapabilitiesfromParams(capabilities);
		
		if (Execute.equalsIgnoreCase("cloud")) {
			DesiredCapabilities caps = new DesiredCapabilities(capMap);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			BaseTest.setDriver(new RemoteWebDriver(new URL(remoteurl), caps));
		}else {
			try {
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				DesiredCapabilities caps = new DesiredCapabilities();

				ChromeOptions options = new ChromeOptions();
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
				
				
				BaseTest.setDriver(new ChromeDriver(options));
				
			} catch (Throwable exp) {
				System.err.println("Exception in browser initialization!!! : " + exp.getMessage());
			}
		}
		BaseTest.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		BaseTest.getDriver().manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		if (BaseTest.getDriver() != null) {
			BaseTest.getDriver().quit();
		}
	}
	
	public static void setDriver(WebDriver wdriver) {
		BaseTest.wdriver.set(wdriver);
	}

	public static WebDriver getDriver() {
		return wdriver.get();
	}
}
