package com.syngenta.sylk.menu_find.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.syngenta.sylk.main.pages.BasePage;
import com.syngenta.sylk.main.pages.MenuPage;
import com.syngenta.sylk.menu_add.pages.GeneticFeaturePage;

public class SearchSylkPage extends MenuPage {

	public SearchSylkPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "searchText")
	private WebElement sylkSearch;
	@FindBy(css = "input[type='button'][value='Search']")
	private WebElement search;
	@FindBy(css = "input[type='button'][value='Clear']")
	private WebElement clear;

	@FindBy(id = "createdBy")
	private WebElement addedBy;
	@FindBy(id = "createdByStart")
	private WebElement fromDate;
	@FindBy(id = "createdByEnd")
	private WebElement toDate;
	@FindBy(id = "searchDocType")
	private WebElement type;
	@FindBy(className = "w50")
	private WebElement view;
	@FindBy(id = "sortBy")
	private WebElement sort;
	@FindBy(id = "sortAsc")
	private WebElement direction;
	@FindBy(id = "search")
	private WebElement searchResultText;
	public void enterSylkSearch(String data) {
		this.sylkSearch.sendKeys(data);
	}

	public SearchSylkPage clickSearch() {
		this.search.click();
		this.waitForPopUpToLoad();
		SearchSylkPage page = new SearchSylkPage(this.driver);
		PageFactory.initElements(this.driver, page);
		return page;
	}

	public void clickClear() {
		this.clear.click();
	}

	public void selectAddedBy(String name) {
		name = StringUtils.deleteWhitespace(name);
		List<WebElement> elements = this.addedBy.findElements(By
				.tagName("option"));
		for (WebElement e : elements) {
			System.out.println(e.getText());
			if (StringUtils.equalsIgnoreCase(
					StringUtils.deleteWhitespace(e.getText()), name)) {
				e.click();
				break;
			}
		}
	}

	public void selectType(String data) {
		data = StringUtils.deleteWhitespace(data);
		List<WebElement> elements = this.type
				.findElements(By.tagName("option"));
		for (WebElement e : elements) {
			if (StringUtils.equalsIgnoreCase(
					StringUtils.deleteWhitespace(e.getText()), data)) {
				e.click();
				break;
			}
		}

	}

	public void selectView(String no) {
		no = StringUtils.deleteWhitespace(no);
		List<WebElement> elements = this.view
				.findElements(By.tagName("option"));
		for (WebElement e : elements) {
			if (StringUtils.equalsIgnoreCase(
					StringUtils.deleteWhitespace(e.getText()), no)) {
				e.click();
			}
		}

	}

	public void SelectSort(String data) {
		data = StringUtils.deleteWhitespace(data);
		List<WebElement> elements = this.sort
				.findElements(By.tagName("option"));
		for (WebElement e : elements) {
			if (StringUtils.equalsIgnoreCase(
					StringUtils.deleteWhitespace(e.getText()), data)) {
				e.click();
			}
		}
	}

	public BasePage clickAndOpenAGFWithConstruct() {
		int totalcount = this.getTotalResultCount();
		if (totalcount == 0) {
			SearchSylkPage spage = new SearchSylkPage(this.driver);
			PageFactory.initElements(this.driver, spage);
			return spage;
		}
		GeneticFeaturePage page = null;
		// List<WebElement> divs = this.driver.findElements(By
		// .cssSelector("div.hit"));
		for (int a = 0; a < totalcount; a++) {
			WebElement span = this.driver.findElement(By.cssSelector("div#hit_"
					+ a + " a.pointer.f12.underline span"));
			span.click();
			GeneticFeaturePage gfPage = new GeneticFeaturePage(this.driver);
			PageFactory.initElements(this.driver, gfPage);
			int count = gfPage.getConstructCountOnTab();
			if (count == 0) {
				this.browserBack();
			} else {
				page = gfPage;
				PageFactory.initElements(this.driver, page);
				break;
			}
		}
		return page;
	}

	public int getTotalResultCount() {
		WebElement span;
		try {
			span = this.searchResultText.findElement(By
					.cssSelector("span.count.centered"));
		} catch (Exception e) {
			return 0;
		}
		String rawResultText = span.getText();
		System.out.println("The search result text is:" + rawResultText);
		String totalResultCount = StringUtils.substringAfterLast(rawResultText,
				"of");
		totalResultCount = StringUtils.deleteWhitespace(totalResultCount);
		return Integer.parseInt(totalResultCount);
	}

	public BasePage clickAndOpenRNAiWithLiteratureEvidence() {
		int totalcount = this.getTotalResultCount();
		if (totalcount == 0) {
			SearchSylkPage spage = new SearchSylkPage(this.driver);
			PageFactory.initElements(this.driver, spage);
			return spage;
		}
		RNAiPage page = null;
		for (int a = 0; a < totalcount; a++) {
			WebElement span = this.driver.findElement(By.cssSelector("div#hit_"
					+ a + " a.pointer.f12.underline span"));
			span.click();
			RNAiPage rnaiPage = new RNAiPage(this.driver);
			PageFactory.initElements(this.driver, rnaiPage);
			int count = rnaiPage.getEvidenceCountOnTab();
			if (count == 0) {
				this.browserBack();
			} else {
				page = rnaiPage;
				PageFactory.initElements(this.driver, page);
				break;
			}
		}

		return page;
	}

	public BasePage clickAndOpenRNAiSearch() {

		int totalcount = this.getTotalResultCount();
		if (totalcount == 0) {

			SearchSylkPage spage = new SearchSylkPage(this.driver);
			PageFactory.initElements(this.driver, spage);
			return spage;
		}

		// for (int a = 0; a < totalcount; a++) {
		WebElement span = this.driver
				.findElement(By.cssSelector("div#hit_0 a"));
		span.click();
		RNAiPage page = new RNAiPage(this.driver);
		PageFactory.initElements(this.driver, page);

		// }
		return page;
	}
}
