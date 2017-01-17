package pages;

import config.Drivers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jovianodias on 04/10/2016.
 */
public class BaseActions extends Drivers {

    protected void clickWhenReady(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 10);

        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        element.click();
    }

    protected void clickDynamicElement(WebElement webElement){
        //http://stackoverflow.com/questions/11908249/debugging-element-is-not-clickable-at-point-error
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", webElement);
    }

}
