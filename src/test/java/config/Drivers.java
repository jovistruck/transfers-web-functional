package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Drivers {
    private static boolean initialized = false;
    private static WebDriver driver;

    protected void initializeWebdriver(){
        if (!initialized){
            initialized = true;
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    driver.quit();
                }
            });
        }
        System.out.println("Initialized driver. Loading site...");
        driver.get(Environment.getBaseUrl());
    }

    protected static WebDriver getDriver(){
        return driver;
    }

    protected static String getBaseUrl(){
        return Environment.getBaseUrl();
    }


    protected void clickWhenReady(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);

        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        element.click();
    }

//    protected void clickWhenVisible(WebElement webElement) {
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
//
//        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
//        element.click();
//    }


//    fun getElementWhenAvailable(webElement: WebElement, timeout: Long, webdriver: WebDriver?): WebElement? {
//        val wait = WebDriverWait(webdriver, timeout)
//        val element = wait.until(ExpectedConditions.visibilityOf(webElement))
//        return element
//    }
}
