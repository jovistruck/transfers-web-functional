package pages;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jovianodias on 04/10/2016.
 */
public class SearchPage extends BaseActions {

    // See https://github.com/SeleniumHQ/selenium/wiki/PageFactory
    @FindBy(css = "#manuscript-title")
    @CacheLookup
    private WebElement manuscriptTitle;

    @FindBy(css = "#manuscript-body")
    @CacheLookup
    private WebElement manuscriptBody;

    @FindBy(css = "#manuscript-keywords")
    @CacheLookup
    private WebElement manuscriptKeywords;

    @FindBy(css = "#suggest")
    @CacheLookup
    private WebElement suggestButton;

    @FindBy(xpath = ".//*[@id='results-table']/tbody/tr[*]/td[1]/a")
    private List<WebElement> journalLinks;

    @FindBy(xpath = ".//*[@id='results-table']/tbody/tr[*]/td[2]")
    private List<WebElement> impactFactor;

    @FindBy(xpath = ".//*[@id='results-table']/tbody/tr[*]/td[3]")
    private List<WebElement> isOpenAccess;

    @FindBy(xpath = ".//*[@id='results-table']/tbody/tr[*]/td[4]/a")
    private List<WebElement> selectButton;


    public void fillAndSubmitSearchForm(String title, String body) {

        manuscriptTitle.sendKeys(title);
        manuscriptBody.sendKeys(body);
        suggestButton.click();
    }

    public List<String> getResultJournalTitles() {

        List<String> journalTitles = new ArrayList<String>();
        for (WebElement journalLink : journalLinks) {
            journalTitles.add(journalLink.getText());
        }
        return journalTitles;
    }

    public void selectJournal(String journalName) throws InterruptedException {
        System.out.println("Looking to click: " + journalName);

        for (int entryCount=0;entryCount < journalLinks.size();entryCount++) {
            System.out.println(journalLinks);
            System.out.println("Found:" + journalLinks.get(entryCount).getText());
            if(journalLinks.get(entryCount).getText().equalsIgnoreCase(journalName)){
                clickDynamicElement(selectButton.get(entryCount));
            }
        }
    }

    public Table<String, String, String> getResultsTable() {

        //the results table column headers here should match fields in manuscript.json
        // the key is the journal name
        Table<String, String, String> resultsTable = HashBasedTable.create();

        for (int entryCount=0;entryCount < journalLinks.size();entryCount++) {
            resultsTable.put(journalLinks.get(entryCount).getText(), "journal_link", journalLinks.get(entryCount).getAttribute("href"));
            resultsTable.put(journalLinks.get(entryCount).getText(), "impact_factor", impactFactor.get(entryCount).getText());
            resultsTable.put(journalLinks.get(entryCount).getText(), "open_access", isOpenAccess.get(entryCount).getText());
            resultsTable.put(journalLinks.get(entryCount).getText(), "select_link", selectButton.get(entryCount).getAttribute("href"));
        }
        return resultsTable;
    }

}
