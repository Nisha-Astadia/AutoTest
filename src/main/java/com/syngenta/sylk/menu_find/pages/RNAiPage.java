package com.syngenta.sylk.menu_find.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.syngenta.sylk.libraries.CommonLibrary;
import com.syngenta.sylk.main.pages.MenuPage;
import com.syngenta.sylk.menu_add.pages.LiteratureSearchPage;

public class RNAiPage extends MenuPage {

	public RNAiPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "ul#RNAi_tabs li:nth-child(4) a")
	private WebElement rNAIConstructs;

	@FindBy(css = "input[id='edit'][value='Edit']")
	private WebElement edit;

	@FindBy(css = "@FindBy(css=ul#RNAi_tabs ")
	private WebElement detailActive;

	@FindBy(css = "select[id='type'][name='typeCode']")
	private WebElement rNAiTriggerType;

	@FindBy(css = "span#ro_typeCode")
	private WebElement getrNAiTriggerType;

	@FindBy(css = "input[id='name'][name='name']")
	private WebElement name;

	@FindBy(css = "textarea[name='description']")
	private WebElement description;

	@FindBy(css = "input[name='genieId']")
	private WebElement genieID;

	@FindBy(css = "input[name='synonyms']")
	private WebElement synonyms;

	@FindBy(css = "input[id='save']")
	private WebElement save;

	@FindBy(css = "label[id='name_error']")
	private WebElement errorMessage;

	public String getValidationError() {
		String error = this.errorMessage.getText();
		return error;
	}

	public boolean isEditButtonUnderDetailTabEnabled() {
		return this.edit.isEnabled();

	}

	public String getRNAiTriggerType() {
		String text = this.getrNAiTriggerType.getText();
		return text;
	}

	public RNAiPage clickSave() {
		// this.save.click();
		WebElement save = this.driver.findElement(By.id("save"));
		Actions actions = new Actions(this.driver);
		actions.sendKeys(save, Keys.ENTER);
		actions.perform();
		this.waitForPageToLoad();
		this.waitForAjax();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RNAiPage page = new RNAiPage(this.driver);
		PageFactory.initElements(this.driver, page);
		return page;

	}
	public void enterSynonyms(String text) {
		this.synonyms.sendKeys(text);
	}

	public String getSynonyms() {
		String text = this.synonyms.getText();
		return text;
	}

	public void enterGenieID(String text) {
		this.genieID.sendKeys(text);

	}

	public String getGenieID() {
		String text = this.genieID.getText();
		return text;
	}

	public void enterDescription(String text) {
		this.description.sendKeys(text);
	}

	public String getDescription() {
		String text = this.description.getText();
		return text;
	}

	public void clearText() {
		this.name.clear();

	}

	public void enterName(String text) {
		this.name.sendKeys(text);
	}

	public String getName() {
		String text = this.name.getText();
		return text;

	}

	public void selectRNAiTriggerType(String type) {
		List<WebElement> elements = this.rNAiTriggerType.findElements(By
				.tagName("option"));
		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(type)) {
				element.click();

			}

		}

	}

	public RNAiPage clickEdit() {
		this.edit.click();
		this.waitForPageToLoad();
		RNAiPage page = new RNAiPage(this.driver);
		PageFactory.initElements(this.driver, page);
		return page;

	}
	private void waitForEvidenceSequenceToLoad() {
		try {
			if (this.getEvidenceCountOnTab() > 0) {
				this.waitForWebElement(By.cssSelector("div#ui-tabs-3 div.clip"));
			} else {
				new CommonLibrary().slowDown();
			}
		} catch (Exception e) {

		}
	}

	public void clickRNAiConstruct() {
		this.rNAIConstructs.click();
		this.waitForPageToLoad();
		this.waitForAjax();
	}

	public int getEvidenceCountOnTab() {

		WebElement evidenceTab = this.driver.findElement(By
				.cssSelector("ul#RNAi_tabs li:nth-child(3)"));
		WebElement evi = evidenceTab.findElement(By.tagName("a"));
		String text = evi.getText();
		text = StringUtils.substringBetween(text, "(", ")");
		if (StringUtils.isBlank(text)) {
			return 0;
		} else {
			return Integer.parseInt(text);
		}

	}

	public RNAiPage clickOnEvidenceTab() {
		WebElement evidenceTab = this.driver.findElement(By
				.cssSelector("ul#RNAi_tabs li:nth-child(3)"));
		if (!StringUtils.containsIgnoreCase(evidenceTab.getAttribute("class"),
				"ui-state-active")) {
			WebElement evi = evidenceTab.findElement(By.tagName("a"));
			evi.click();
			this.waitForPageToLoad();
			this.waitForAjax();
			this.waitForEvidenceSequenceToLoad();
		}
		RNAiPage page = new RNAiPage(this.driver);
		PageFactory.initElements(this.driver, page);
		return page;

	}

	public RNAiPage clickOnDetailTab() {
		WebElement evidenceTab = this.driver.findElement(By
				.cssSelector("ul#RNAi_tabs li:nth-child(1)"));
		if (!StringUtils.containsIgnoreCase(evidenceTab.getAttribute("class"),
				"ui-state-active")) {
			WebElement evi = evidenceTab.findElement(By.tagName("a"));
			evi.click();
			this.waitForPageToLoad();
			this.waitForAjax();
			this.waitForEvidenceSequenceToLoad();
		}
		RNAiPage page = new RNAiPage(this.driver);
		PageFactory.initElements(this.driver, page);
		return page;
	}

	public LiteratureSearchPage selectAddEvidence(String selection) {

		if (StringUtils.containsIgnoreCase(selection, "literature")) {
			selection = "literature";
		} else if (StringUtils.containsIgnoreCase(selection, "other")) {
			selection = "other";
		}
		WebElement div = this.driver.findElement(By.id("ui-tabs-3"));
		List<WebElement> elements = div.findElements(By.tagName("option"));
		for (WebElement element : elements) {
			String value = StringUtils.lowerCase(element.getAttribute("value"));
			if (StringUtils.equals(StringUtils.deleteWhitespace(value),
					selection)) {
				element.click();
				break;
			}
		}
		this.waitForPageToLoad();
		this.waitForAjax();
		LiteratureSearchPage page = new LiteratureSearchPage(this.driver);
		PageFactory.initElements(this.driver, page);
		return page;

	}

	public boolean checkIfMagnifyingGlassImageIsVerticallyAlligned() {
		WebElement mainDiv = this.driver.findElement(By.id("ui-tabs-3"));
		List<WebElement> tables = mainDiv.findElements(By.tagName("table"));

		boolean flag = true;
		for (WebElement table : tables) {
			List<WebElement> trs = table.findElements(By.tagName("tr"));
			int a = 0;
			for (WebElement tr : trs) {
				a++;
				if (a == 1) {
					continue;
				}

				try {
					WebElement view = tr.findElement(By
							.cssSelector("td:nth-child(1) span.view"));
				} catch (Exception e) {
					flag = false;
					break;
				}
			}

			if (!flag) {
				break;
			}
		}

		return flag;
	}

}
