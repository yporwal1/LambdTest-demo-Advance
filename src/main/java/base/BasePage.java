package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver localDriver;
	public WebDriverWait wait = null;

	public static final String CURRENT_DIR = System.getProperty("user.dir");

	public enum LOCATOR {
		xpath, css, linkText
	}

	public BasePage(WebDriver localDriver) {
		this.localDriver = localDriver;
		wait = new WebDriverWait(this.localDriver, 20);
	}

	// Click a web element
	public void click(WebElement element) {

		try {
			waitForElementToBeClickable(element);
			element.click();
			// mouseOverAndClick(element);

		} catch (StaleElementReferenceException sere) {

			// simply retry finding the element in the refreshed DOM

			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}
	}

	// Click Web element using Javascript
	public void clickJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) localDriver;
		executor.executeScript("arguments[0].click();", element);
	}

	// Enter text in a Text box
	public void enterText(WebElement element, String text) {
		waitForElementToBePresent(element);
		element.clear();
		element.sendKeys(text);
		System.out.println("Text box element identified by " + element + " and " + text + " entered in the same");
	}

	public void clearText(WebElement element) {
		waitForElementToBePresent(element);
		element.clear();
		System.out.println("Text box element identified by " + element + "has been cleared");
	}

	public void waitForElementToBePresent(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	private Object _executeJavaScript(String jsCode) {
		return ((JavascriptExecutor) localDriver).executeScript(jsCode);
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) localDriver;
		executor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});",
				element);
	}
}
