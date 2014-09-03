package com.syngenta.sylk.extendtabbedui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.syngenta.sylk.libraries.CommonLibrary;
import com.syngenta.sylk.libraries.PageTitles;
import com.syngenta.sylk.libraries.SyngentaException;
import com.syngenta.sylk.main.pages.HomePage;
import com.syngenta.sylk.main.pages.LandingPage;
import com.syngenta.sylk.main.pages.SyngentaReporter;
import com.syngenta.sylk.menu_add.pages.CreateLiteratureEvidenceDetailsForRNAiPage;
import com.syngenta.sylk.menu_add.pages.LiteratureSearchPage;
import com.syngenta.sylk.menu_add.pages.PopUpAddRNAiTrigger;
import com.syngenta.sylk.menu_add.pages.PopUpAddTraitComponent;
import com.syngenta.sylk.menu_find.pages.RNAiPage;
import com.syngenta.sylk.menu_find.pages.SearchSylkPage;

public class CheckEvidenceMagnifyingGlassVerticallyAlignedRNAi {

	private List<Object[]> testData = new ArrayList<Object[]>();
	private LandingPage lp;
	private HomePage homepage;
	private SearchSylkPage searchSylkpage;

	@BeforeClass(alwaysRun = true)
	public void loadData() {
		this.testData = new CommonLibrary()
				.getTestDataAsObjectArray("EvidenceMagnifyingGlassVerticallyAlignedRNAi.xlsx");
	}

	@AfterClass(alwaysRun = true)
	public void quitDriverIfLeftOpen() {

		if (this.lp != null) {
			this.lp.driverQuit();
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void testSetUp() {
		this.lp = LandingPage.getLandingPage();
		this.homepage = this.lp.goToHomePage();
	}

	@DataProvider(name = "gfAccessionNoadd", parallel = false)
	public Iterator<Object[]> loadTestData() {

		return this.testData.iterator();
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp() {

		if (this.lp != null) {
			this.lp.driverQuit();
		}
	}
	@Test(enabled = true, description = "Check vertically align magnifying glass on literature evidence tab details and trait components", dataProvider = "gfAccessionNoadd", groups = {
			"rNaiConstructTabInTabbedView", "RNAI", "regression"})
	public void rNAiConstructTabInTabbedView(String testDescription,
			String row_num, HashMap<String, String> columns) {

		SyngentaReporter reporter = new SyngentaReporter();

		try {
			String user = "Elkomy,Mohamed";

			// step1

			reporter.reportPass("Login to SyLK");

			// Step 2
			this.searchSylkpage = this.homepage.goToGFRNAiTriggerROIpromoter();

			reporter.verifyEqual(this.searchSylkpage.getPageTitle(),
					PageTitles.search_sylk_page_title,
					"Select menu item 'GF/RNAi Triggers/ROI/Promoter' and open Search page.");
			// step 3
			this.searchSylkpage.selectAddedBy(user);

			this.searchSylkpage.selectType("RNAi");

			this.searchSylkpage = this.searchSylkpage.clickSearch();

			int count = this.searchSylkpage.getTotalResultCount();
			if (count == 0) {
				reporter.assertThisAsFail("When searched for RNAi for this user="
						+ user + " resulted in zero results");
			} else {
				reporter.reportPass("When searched for RNAi for this user="
						+ user + " displays " + count + " results");
			}

			RNAiPage rnaiPage = (RNAiPage) this.searchSylkpage
					.clickAndOpenRNAiWithLiteratureEvidence();
			reporter.verifyEqual(
					rnaiPage.getPageTitle(),
					PageTitles.rnai_page_title_page,
					"RNAi Trigger Tabbed Page appears when clicked on one of the RNAi displayed in the search results.");

			rnaiPage = rnaiPage.clickOnEvidenceTab();
			reporter.reportPass("Click on evidence tab open up the evidence tab.");

			int evidenceCount = rnaiPage.getEvidenceCountOnTab();
			// add only if the count == 1
			if (evidenceCount == 1) {
				LiteratureSearchPage litSearchPage = rnaiPage
						.selectAddEvidence("literature");
				CreateLiteratureEvidenceDetailsForRNAiPage createLitPage = (CreateLiteratureEvidenceDetailsForRNAiPage) litSearchPage
						.searchThis("Bharitkar S, Mendel");

				createLitPage.enterRationale("test");
				createLitPage.enterObservation("test observation");
				PopUpAddRNAiTrigger popupTrigger = createLitPage.clickAddRNAi();
				popupTrigger.enterText("ath-MIR156a");
				createLitPage = popupTrigger.clickAdd();

				PopUpAddTraitComponent popupTriat = createLitPage
						.clickAddTraitComponent();
				createLitPage = (CreateLiteratureEvidenceDetailsForRNAiPage) popupTriat
						.addTrait("biomass yield [en;XX;1]");

				rnaiPage = createLitPage.clickSave();
				rnaiPage.clickOnEvidenceTab();

				reporter.reportPass("Created a new evidence as the count for this selected RNAi was one.");
			}

			boolean allIsGood = rnaiPage
					.checkIfMagnifyingGlassImageIsVerticallyAlligned();

			reporter.assertTrue(allIsGood,
					"All evidence view magnifying classes should be alligned vertically.");

		} catch (SkipException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SyngentaException("Test Failed:"
					+ new CommonLibrary().getStackTrace(e));
		} finally {
			reporter.assertAll();
		}
	}
}
