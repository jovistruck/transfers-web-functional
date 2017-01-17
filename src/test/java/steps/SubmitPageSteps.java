package steps;

import cucumber.api.java.en.Then;
import org.openqa.selenium.support.PageFactory;
import pages.SubmitPage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jovianodias on 12/10/2016.
 */
public class SubmitPageSteps extends StepBase{

    SubmitPage submitPage = PageFactory.initElements(getDriver(), SubmitPage.class);

    @Then("^the submit page is displayed with the correct submit link$")
    public void theSubmitPageIsDisplayedWithTheCorrectSubmitLink() throws Throwable {
        assertThat(submitPage.getSubmitManuscriptLink()).isEqualToIgnoringCase(dataProvider.getManuscriptInfo(pickedManuscript,"journal_link"));
    }
}
