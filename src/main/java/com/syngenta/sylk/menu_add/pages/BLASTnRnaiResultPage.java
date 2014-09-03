package com.syngenta.sylk.menu_add.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.syngenta.sylk.main.pages.MenuPage;

public class BLASTnRnaiResultPage extends MenuPage {

	protected BLASTnRnaiResultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "input[value='Add as New RNAi Trigger']")
	private WebElement AddASaNewRnaiTrigger;

	@FindBy(css = "input[className='btn'][value='Cancel']")
	private WebElement cancel;

	@FindBy(className = "pointer")
	private WebElement rnaiMatches;

	public void clickRnaiMatchesandGetText(String nameOfRnai) {

	}

}
