package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class HomePage extends BasePage {

	@FindBy(xpath = ".//a[text()[contains(.,'See All Integrations')]]")
	private WebElement allIntegrationLink;
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public IntegrationPage openIntegrationLinkInNewTab(){
		scrollToElement(allIntegrationLink);
		
		//allIntegrationLink.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		Actions actions = new Actions(localDriver);
		
		CharSequence ctrl = (((RemoteWebDriver)localDriver).getCapabilities().getCapability("platform").toString().contains("MAC")) ? Keys.COMMAND : Keys.CONTROL;
		System.out.println(((RemoteWebDriver)localDriver).getCapabilities().getCapability("platform").toString());
		actions.keyDown(ctrl)
		    .click(allIntegrationLink)
		    .keyUp(ctrl)
		    .build()
		    .perform();
		
		return new IntegrationPage(localDriver);
	}

}
