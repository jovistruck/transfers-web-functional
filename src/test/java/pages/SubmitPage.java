package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jovianodias on 04/10/2016.
 */
public class SubmitPage extends BaseActions {

    // See https://github.com/SeleniumHQ/selenium/wiki/PageFactory
    @FindBy(css = ".general-button")
    private WebElement submitButton;

    public void submitManuscript() {

        submitButton.click();
    }

    public String getSubmitManuscriptLink() {

        System.out.println(submitButton.getText());
        return submitButton.getAttribute("href");
    }


}
