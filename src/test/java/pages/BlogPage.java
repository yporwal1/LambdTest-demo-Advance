package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class BlogPage extends BasePage {

	@FindBy(css = "li[id*='menu-item'] a[href*='community']")
	private WebElement communityLink;
	
	
	public BlogPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void openCommunity(){
		clickJS(communityLink);
	}
}
