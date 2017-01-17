package steps;

import config.Drivers;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.support.PageFactory;
import pages.SearchPage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by jovianodias on 04/10/2016.
 */
public class SearchPageSteps extends StepBase{

    SearchPage searchPage = PageFactory.initElements(getDriver(), SearchPage.class);

    @And("^I search for a manuscript$")
    public void iSearchForAManuscript() throws Throwable {

        pickedManuscript = dataProvider.getDoiList().get(ThreadLocalRandom.current().nextInt(0, dataProvider.getDoiList().size()));
        System.out.println(pickedManuscript);
        searchPage.fillAndSubmitSearchForm(dataProvider.getManuscriptInfo(pickedManuscript,"title"),dataProvider.getManuscriptInfo(pickedManuscript,"body"));
    }

    @Then("^the results displayed contain a journal matching the manuscript$")
    public void theResultsDisplayedContainAJournalMatchingTheManuscript() throws Throwable {

        assertThat(searchPage.getResultJournalTitles()).hasSize(10)
                .contains(dataProvider.getManuscriptInfo(pickedManuscript,"journal_name"));
    }

    @Then("^the following columns have the correct data$")
    public void theFollowingFieldsAreDisplayedCorrectly(DataTable table) throws Throwable {

        //the data table columns passed here should match exactly the fields in manuscript.json and the results table columns
        List<List<String>> data = table.raw();

        for (List<String> aData : data) {
            assertThat(getResultsTableEntryForPickedManuscript(aData.get(0))).isNotNull();
            assertThat(dataProvider.getManuscriptInfo(pickedManuscript, aData.get(0))).isNotNull();
            assertThat(getResultsTableEntryForPickedManuscript(aData.get(0))).isEqualToIgnoringCase(dataProvider.getManuscriptInfo(pickedManuscript, aData.get(0)));
        }
    }

    private String getResultsTableEntryForPickedManuscript(String keyColumn){

        return searchPage.getResultsTable().get(dataProvider.getManuscriptInfo(pickedManuscript,"journal_name"),keyColumn);
    }

    @And("^the select link is correct$")
    public void theSelectLinkIsCorrect() throws Throwable {
        assertThat(getResultsTableEntryForPickedManuscript("select_link")).isEqualToIgnoringCase(Drivers.getBaseUrl()+"/select-journal?journalId="+dataProvider.getManuscriptInfo(pickedManuscript, "journal_id"));
    }

    @And("^I select a journal to submit to$")
    public void iSelectAJournalToSubmitTo() throws Throwable {
        searchPage.selectJournal(dataProvider.getManuscriptInfo(pickedManuscript,"journal_name"));
        assertThat(getDriver().getCurrentUrl()).endsWith("select-journal?journalId=" + dataProvider.getManuscriptInfo(pickedManuscript,"journal_id"));
    }
}
