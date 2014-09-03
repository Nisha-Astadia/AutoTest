package com.syngenta.sylk.menu_add.pages;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.syngenta.sylk.main.pages.MenuPage;

public class SearchTargetGenePage extends MenuPage {

	protected SearchTargetGenePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(className = "f12")
	private WebElement searchTargetGene;
	@FindBy(css = "input[class='btn'][value='Search']")
	private WebElement search;
	@FindBy(css = "input[class='btn'][value='Clear']")
	private WebElement clear;
	@FindBy(id = "createdBy")
	private WebElement addedBy;
	@FindBy(id = "cbs")
	private WebElement fromDate;
	@FindBy(id = "cbe")
	private WebElement toDate;
	@FindBy(className = "w50")
	private WebElement view;
	@FindBy(id = "results")
	private WebElement addGene;

	public void enterSearchTargetGene(String data) {
		this.searchTargetGene.sendKeys(data);
	}

	public SearchTargetGenePage clickSearch() {
		this.search.click();
		this.waitForPageToLoad();

		SearchTargetGenePage searchPage = new SearchTargetGenePage(this.driver);
		PageFactory.initElements(this.driver, searchPage);

		return searchPage;

	}

	public void clickClear() {
		this.clear.click();
	}

	public AddRNAiTriggerPage clickAddGene(String data) {
		List<WebElement> trs = this.addGene.findElements(By
				.cssSelector("tr.bg"));
		for (WebElement tr : trs) {
			WebElement input = tr.findElement(By.tagName("input"));
			WebElement aTag = tr.findElement(By.tagName("a"));
			if (StringUtils.equalsIgnoreCase(aTag.getText(), data)) {
				input.click();
				break;
			}
		}
		this.waitForPageToLoad();
		AddRNAiTriggerPage page = new AddRNAiTriggerPage(this.driver);
		PageFactory.initElements(this.driver, page);

		return page;
	}

}
