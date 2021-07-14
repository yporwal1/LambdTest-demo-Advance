package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class IntegrationPage extends BasePage {

	@FindBy(id = "codeless_row")
	private WebElement codelessAutomationSection;
	
	@FindBy(css = "a[href*='testingwhiz']")
	private WebElement testingWhizLearnMore;
	
	public IntegrationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void openTestingWhizIntegration(){
		scrollToElement(codelessAutomationSection);
		click(testingWhizLearnMore);
	}
}
