package com.syngenta.sylk.menu_add.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.syngenta.sylk.main.pages.BasePage;

public class PopUpAddRNAiTrigger extends BasePage {

	protected PopUpAddRNAiTrigger(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "dialogRNAIName")
	private WebElement TextBox;

	@FindBy(id = "dialogRNAIAdd")
	private WebElement add;

	public void enterText(String data) {
		this.TextBox.sendKeys(data);
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
		}
		Actions action = new Actions(this.driver);
		action.sendKeys(this.TextBox, Keys.ARROW_DOWN).sendKeys(Keys.ENTER);
		action.perform();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}

	public CreateLiteratureEvidenceDetailsForRNAiPage clickAdd() {
		this.add.click();
		this.waitForPageToLoad();
		CreateLiteratureEvidenceDetailsForRNAiPage page = new CreateLiteratureEvidenceDetailsForRNAiPage(
				this.driver);
		PageFactory.initElements(this.driver, page);
		return page;

	}
}
