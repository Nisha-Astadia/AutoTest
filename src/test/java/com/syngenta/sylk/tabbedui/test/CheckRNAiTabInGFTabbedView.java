package com.syngenta.sylk.tabbedui.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.syngenta.sylk.libraries.CommonLibrary;
import com.syngenta.sylk.libraries.SyngentaException;
import com.syngenta.sylk.main.pages.HomePage;
import com.syngenta.sylk.main.pages.LandingPage;
import com.syngenta.sylk.main.pages.SyngentaReporter;
import com.syngenta.sylk.menu_add.pages.AddNewGeneticFeaturePage;
import com.syngenta.sylk.menu_add.pages.AddRNAiTriggerPage;
import com.syngenta.sylk.menu_add.pages.SearchTargetGenePage;

public class CheckRNAiTabInGFTabbedView {
	private String seqData = "MGEAAAAVAASKRGGGPAPFLTKTHQMVEERGTDEVISWAEQGRSFVVWKPVELARDLLPLHFKHCNFSSFVRQLNTYGFRKVVPDRWEFANDNFRRGEQ"
			+ "GLLSGIRRRKSTALQMSKSGSGGSGGVNATFPPPLPPPPPASATTSGVHERSSSSASSPPRAPDLASENEQLKKDNHTLSAELAQARRHCEELLGFLSRFLDVRQLDLRLLMQEDVRAGASDDGAQRRAHAVASQLERGGGEEGKSVKLFGVLLKDAARKRGRCEEAAASERPIKMIRVGEPWVGVPSSGPGRCGGEN";
	private String GFdata = "AA";

	private AddNewGeneticFeaturePage addNewGFPage;
	private AddRNAiTriggerPage addNewRNAiTriggerPage;
	private LandingPage lp;
	@AfterClass(alwaysRun = true)
	public void quitDriverIfLeftOpen() {
		if (this.lp != null) {
			this.lp.driverQuit();
		}
	}
	@BeforeTest(alwaysRun = true)
	public void testSetUp() {
		this.lp = LandingPage.getLandingPage();
		HomePage homepage = this.lp.goToHomePage();
		this.addNewGFPage = homepage.goToAddGeneticFeaturePage();
		this.addNewRNAiTriggerPage = homepage.goToRNAiTriggerPage();
	}

	@AfterTest(alwaysRun = true)
	public void cleanUp() {
		if (this.addNewGFPage != null) {
			this.addNewGFPage.driverQuit();
		}
	}

	@Test(enabled = true, description = "Chek RNAi tab in GF Tabbed view ", groups = {
			"CheckRNAiTabInGFTabbedView", "TabbedUi", "regression"})
	public void checkRNAiTabInGFTabbedView() {
		CommonLibrary common = new CommonLibrary();
		SyngentaReporter reporter = new SyngentaReporter();
		try {
			this.addNewRNAiTriggerPage.enterSequence(this.seqData);
			SearchTargetGenePage searchTargetGenePage = this.addNewRNAiTriggerPage
					.clickSelectTargetGene();
			searchTargetGenePage.enterSearchTargetGene(this.GFdata);
			searchTargetGenePage = searchTargetGenePage.clickSearch();
			this.addNewRNAiTriggerPage = searchTargetGenePage
					.clickAddGene(this.GFdata);
			boolean selected = this.addNewRNAiTriggerPage
					.checkGFIsSelected(this.GFdata);

			reporter.verifyTrue(selected,
					"GF does not show as selected in Add RNAI Trigger Page.",
					"GF selected successfully in Add RNAI Trigger Page.");

		} catch (Exception e) {
			throw new SyngentaException("Test Failed:"
					+ new CommonLibrary().getStackTrace(e));
		} finally {
			reporter.assertAll();
		}
	}
}
