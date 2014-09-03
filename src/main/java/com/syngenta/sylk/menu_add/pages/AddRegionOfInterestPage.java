package com.syngenta.sylk.menu_add.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.syngenta.sylk.main.pages.MenuPage;

public class AddRegionOfInterestPage extends MenuPage {

	public AddRegionOfInterestPage(WebDriver driver) {
		super(driver);
	}

	// Select Information popup
	@FindBy(id = "findOrganismDiv_autoComplete")
	private WebElement selectInformationEnterText;

	@FindBy(css = "button[type='button'][value='Ok']")
	private WebElement selectInformationOk;

	@FindBy(id = "findOrganismDiv_findButton")
	private WebElement sourceSpecies;

	@FindBy(id = "line")
	private WebElement line;

	@FindBy(id = "refType")
	private WebElement ReferenceGenome;

	@FindBy(id = "chromosome")
	private WebElement chromosome;

	@FindBy(id = "from")
	private WebElement from;

	@FindBy(id = "to")
	private WebElement to;

	@FindBy(id = "synonyms")
	private WebElement synonyms;

	@FindBy(id = "type")
	private WebElement RegionOfInteresttype;

	@FindBy(id = "descriptions")
	private WebElement descriptions;

	@FindBy(id = "snps")
	private WebElement SNPs;

	@FindBy(id = "markers")
	private WebElement markers;

	@FindBy(css = "input[class='btn'][value='Add Genes to ROI']")
	private WebElement addGeneToROI;

	@FindBy(css = "input[class='btn'][value='Add Region Of Interest']")
	private WebElement addRegionOfInterest;

	@FindBy(css = "input[class='btn'][value='Clear']")
	private WebElement clear;

	// error messages
	@FindBy(id = "organism_error")
	private WebElement sourceSpeciesError;

	@FindBy(id = "line_error")
	private WebElement lineError;

	@FindBy(id = "genome_error")
	private WebElement referenceGenomeError;

	@FindBy(id = "chromosome_error")
	private WebElement chromosomeError;

	@FindBy(id = "from_error")
	private WebElement fromError;

	@FindBy(id = "to_error")
	private WebElement toError;

	@FindBy(id = "snps_error")
	private WebElement SNPsError;

	public void enterSourceSpecies(String data) {
		this.sourceSpecies.click();
		this.selectInformationEnterText.sendKeys(data);
		this.selectInformationOk.click();
	}

	public void enterLine(String data) {
		this.line.sendKeys(data);

	}

	public void selectReferenceGenome(String selection) {
		List<WebElement> elements = this.ReferenceGenome.findElements(By
				.tagName("options"));
		for (WebElement e : elements) {
			if (e.getText().equalsIgnoreCase(selection)) {
				e.click();
			}
		}
	}

	public void enterChromosome(String data) {
		this.chromosome.sendKeys(data);

	}

	public void enterFrom(String data) {
		this.from.sendKeys(data);
	}

	public void enterTo(String data) {
		this.to.sendKeys(data);
	}

	public void enterSynonyms(String data) {
		this.synonyms.sendKeys(data);
	}

	public void selectRegionOfInterestType(String selection) {
		List<WebElement> elements = this.RegionOfInteresttype.findElements(By
				.tagName("option"));
		for (WebElement e : elements) {
			if (e.getText().equalsIgnoreCase(selection)) {
				e.click();
			}
		}
	}

	public void enterDescription(String data) {
		this.descriptions.sendKeys(data);
	}

	public void enterSNPs(String data) {
		this.SNPs.sendKeys(data);
	}

	public void enterMarkers(String data) {
		this.markers.sendKeys(data);
	}

	public SearchTargetGenePage clickAddGeneToROI() {
		this.addGeneToROI.click();
		SearchTargetGenePage page = new SearchTargetGenePage(this.driver);
		PageFactory.initElements(this.driver, page);
		return page;
	}

	public RegionOfInterestDetailsPage ClickAddRegionOfInterest() {
		this.addRegionOfInterest.click();
		RegionOfInterestDetailsPage page = new RegionOfInterestDetailsPage(
				this.driver);
		PageFactory.initElements(this.driver, page);
		return page;

	}

	public void clickClear() {
		this.clear.click();
	}

}
