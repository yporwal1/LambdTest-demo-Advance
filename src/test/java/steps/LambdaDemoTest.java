package steps;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BlogPage;
import pages.HomePage;
import pages.IntegrationPage;

public class LambdaDemoTest extends BaseTest{
	
	@Test
	public void lambdaTestDemo() throws InterruptedException {
		getDriver().get("https://www.lambdatest.com");
		
		new WebDriverWait(BaseTest.getDriver(), 20).until(
			      driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
		
		HomePage home = new HomePage(getDriver());
		
		//opening integration page in new tab.
		IntegrationPage integrationPage = home.openIntegrationLinkInNewTab();
		
		//getting window handels and switching to new opened window.
		String windows[] = getDriver().getWindowHandles().toArray(new String[0]);
		System.out.println("new window handel::"+ windows[1]);
		getDriver().switchTo().window(windows[1]);
		
		//Asserting current url to "https://www.lambdatest.com/integrations/".
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.lambdatest.com/integrations/", "current url does not match.");
		
		//opening TestingWhiz Integration page in the same window.
		integrationPage.openTestingWhizIntegration();
		
		//asserting current window title to "TestingWhiz Integration | LambdaTest".
		Assert.assertEquals(getDriver().getTitle(), "TestingWhiz Integration | LambdaTest", "title of the page does not match.");
		
		//closing current window and switching to old window.
		getDriver().close();
		getDriver().switchTo().window(windows[0]);
		
		//printing current window count.
		System.out.println(getDriver().getWindowHandles().size());
		
		//setting url to "https://www.lambdatest.com/blog" in current window.
		getDriver().navigate().to("https://www.lambdatest.com/blog");
		BlogPage blog = new BlogPage(getDriver());
		
		//opening commuity page by clicking community nav menu.
		blog.openCommunity();
		
		//Asserting current url to "https://community.lambdatest.com/".
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://community.lambdatest.com/", "current url does not match.");
		
		//closing current window tab
		//getDriver().close();
	}
}
