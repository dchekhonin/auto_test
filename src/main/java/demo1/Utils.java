
package demo1;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * A collection of methods useful when writing Web Test classes and tests.
 * Provides safe wrappers for Sikuli and Selenium methods that don't barf when things go missing.
 * Provides a single way to call Sikuli and Selenium methods even though they differ in the way
 * they handle missing elements.
 */
public class Utils {
    private static final int ELEMENT_WAIT_TIMEOUT_IN_SECONDS = 30;



    /**
     * Same as FindElement only returns null when not found instead of an exception.<br>
     * from http://stackoverflow.com/questions/6521270/webdriver-check-if-an-element-exists<br>
     * Corrected here so it will actually work.
     *
     * @param driver WebDriver in which to search
     * @param by how to search for the thing
     * @return WebElement or Null
     */
    public static WebElement findElementSafe(SearchContext driver, By by) {
        WebElement result = null;
        try {
            result = driver.findElement(by);
        }
        catch (Exception e) {
            result = null;
        }
        return result;
    }


//    /***
//     * Wait for the web element to appear.
//     * If it appears before the timeout, returns the webElement.
//     * If it fails to appear, returns null.
//     *
//     * @param driver WebDriver where to search
//     * @param by Selenium selector
//     * @param timeout seconds to wait before giving up
//     * @return webElement or null
//     */

//    public static WebElement waitForElement(SearchContext driver, By by, Integer timeout) {
////        LOGGER.debug("waitForElement({})",by);
//        WebElement element = null;
//        Long targetTime = System.currentTimeMillis() + timeout * 1000;
//        element = findElementSafe(driver, by);
//        while (element == null) {
//            Long currentTime = System.currentTimeMillis();
//            if (currentTime > targetTime) {
//                break;
//            }
//            sleep(100);
//            element = findElementSafe(driver, by);
//        }
//        if (element == null)
////            LOGGER.debug("waitForElement '{}' failed", by);
//        return element;
//
//    }
    public static WebElement waitForElement(WebDriver driver, By by)
    {
        return (new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }


//    /***
//     * Waits for element in the DOM using Selenium and handles its exceptions.
//     * Waits up to ELEMENT_WAIT_TIMEOUT_IN_SECONDS seconds for the element to appear.
//     * If it appears before the timeout, returns true.
//     * If it fails to appear, returns null.
//     *
//     * @param driver WebDriver where to search
//     * @param by how to search for the thing
//     * @return webElement or null
//     */
//    public static WebElement waitForElement(SearchContext driver, By by) {
//        return waitForElement(driver, by, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
//    }

    /***
     * Wait for the web element to appear.
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, returns exception
     *
     * @param driver WebDriver
     * @param element WebElement
     * @param timeout time to wait before giving up
     * @return webElement or null
     */
    public static WebElement waitForElementVisibility(WebDriver driver, WebElement element, Integer timeout) {
        WebElement webElement = null;
//        try {
            webElement = (new WebDriverWait(driver, timeout))
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
//        } catch (Exception e){
//            throw e;
//        }
        return webElement;
    }


    /**
     * Method checks is element is displayed without any waiting for elements
     * Main goal - avoid NoSuchElementException
     * @param element web element
     * @return
     */
    public static boolean isElementDisplayed(final WebElement element){
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    /***
     * Wait for the web element to appear.
     * If it appears before 5 seconds, returns the webElement.
     * If it fails to appear, returns exception
     *
     * @param driver WebDriver
     * @param element WebElement
     * @return webElement or null
     */
    public static WebElement waitForElementVisibility(WebDriver driver, WebElement element) {
        return waitForElementVisibility(driver, element, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
    }


    /***
     * Wait for the web element to visible
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, throws exception
     *
     * @param driver WebDriver
     * @param element By
     * @param timeout seconds to wait before giving up
     * @return webElement
     */

    public static WebElement waitForElementVisibility(WebDriver driver, By element, Integer timeout) {
        WebElement webElement = null;
//        try {
            webElement = (new WebDriverWait(driver, timeout))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
//        }
//        catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            throw e;
//        }
        return webElement;
    }


    /***
     * Wait for presence of the web element
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, throws exception
     *
     * @param driver WebDriver
     * @param element By
     * @param timeout seconds to wait before giving up
     * @return webElement
     */

    public static WebElement waitForElementPresence(WebDriver driver, By element, Integer timeout) {
        WebElement webElement = null;
            webElement = (new WebDriverWait(driver, timeout))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
        return webElement;
    }

    public static boolean isElementSelected(WebDriver driver, WebElement element){
        waitForElementVisibility(driver, element);
        return element.isSelected();
    }

//    /**
//     * Switches to detault content, waits 10 seconds. <bnr>
//     * Checks all elements identified by element, switches to the first one that's not null.
//     * @param driver
//     * @param element
//     */
//    public static  void switchToFrameWithWebElement(WebDriver driver, By element){
//        driver.switchTo().defaultContent();
//        sleep(10000);
//        List<WebElement> webElementList = driver.findElements(By.cssSelector("iframe"));
//        for(WebElement webElement1: webElementList){
//            driver.switchTo().frame(webElement1);
//            if(waitForElement(driver, element, 5) != null){
//                return;
//            } else {
//                driver.switchTo().defaultContent();
//            }
//        }
//    }

    public static void switchToFrameBySelector(WebDriver driver, By by){
        driver.switchTo().defaultContent();
        try {
            new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS).until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(by));
        } catch (Exception ex){
//            LOGGER.debug("{} \nPage source: {}", ex.getMessage(), driver.getPageSource());
            sleep(5000);
            throw new RuntimeException("Could not find iframe '" + by + "'");
        }

    }


    /***
     * Wait for the web element to be clickable and click
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, returns null.
     *  @param driver WebDriver
     * @param by By
     * @param timeout seconds to wait before giving up
     */

    public static void click(WebDriver driver, By by, Integer timeout) {
        try {
            (new WebDriverWait(driver, timeout))
//                    .until(ExpectedConditions.elementToBeClickable(by));
                    .until(ExpectedConditions.or(
                            ExpectedConditions.elementToBeClickable(by),
                            ExpectedConditions.visibilityOfElementLocated(by)));
            new Actions(driver).moveToElement(driver.findElement(by)).click().build().perform();
        }
        catch (Exception e) {
//            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /***
     * Wait for the web element to be clickable and click
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, returns null.
     *  @param driver WebDriver
     * @param element By
     */

    public static void click(WebDriver driver, By element) {
        click(driver, element, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
    }

    /***
     * Wait for the web element to be clickable and click
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, returns null.
     *  @param driver WebDriver
     * @param element WebElement
     */

    public static void click(WebDriver driver, WebElement element) {
        click(driver, element, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
    }


    /***
     * Wait for the web element to be clickable and click
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, returns null.
     *  @param driver WebDriver
     * @param element By
     * @param timeout seconds to wait before giving up
     */

    public static void click(WebDriver driver, WebElement element, Integer timeout) {
        try {
            (new WebDriverWait(driver, timeout, 100)
                    .ignoring(StaleElementReferenceException.class, NoSuchElementException.class))
                    .until(ExpectedConditions.or(
                            ExpectedConditions.elementToBeClickable(element),
                            ExpectedConditions.visibilityOf(element)));
            new Actions(driver).moveToElement(element).perform();
            try {
                element.click();
            } catch (WebDriverException we){
                if (we.getMessage().contains("Other element would receive the click")){
                    clickOnElementLikeJavascript(driver, element);
                } else {
                    throw new WebDriverException(we.getMessage());
                }
            }

        }
        catch (Exception e) {
//            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /***
     * Wait for the web element to be clickable and click
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, returns null.
     *  @param driver WebDriver
     * @param element WebElement
     * @param text Text as java.util.String
     */
    public static void typeInto(WebDriver driver, By element, String text) {
        WebElement element1 = waitForElementVisibility(driver, element, ELEMENT_WAIT_TIMEOUT_IN_SECONDS);
        typeInto(element1, text);
    }

    public static void typeInto(final WebDriver driver, final SearchContext searchElement, final By by, final String text) {
        WebElement element = click(driver, searchElement, by);
        typeInto(element, text);
    }

    private static void typeInto(final WebElement element, final String text){
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Click by element inside in other element
     * @param searchElement
     * @param by
     */
    public static WebElement click(WebDriver driver, SearchContext searchElement, By by) {
        Wait<SearchContext> wait = new FluentWait<>(searchElement)
                .withTimeout(ELEMENT_WAIT_TIMEOUT_IN_SECONDS, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        WebElement element = wait.until(new Function<SearchContext, WebElement>() {
            public WebElement apply(SearchContext searchContext ) {
                return searchElement.findElement(by);
            }
        });

        new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS).until(ExpectedConditions.visibilityOf(element));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));

        new Actions(driver).moveToElement(element).perform();
        element.click();
        return element;

    }


    /***
     * Wait for the web element to be clickable and click
     * If it appears before the timeout, returns the webElement.
     * If it fails to appear, returns null.
     *  @param driver WebDriver
     * @param element WebElement
     * @param text time to wait before giving up
     */
    public static WebElement typeInto(WebDriver driver, WebElement element, String text) {
        if(text != null) {
            click(driver, element);
            typeInto(element, text);
        }
        return element;
    }


    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        new Actions(driver).dragAndDrop(source, target).perform();
    }





//    /**
//     * Waits for the element to appear, then disappear.<br>
//     * If element does not appear, returns false.<br>
//     * Waits up to seconds for element to appear and then again to disappear.
//     * @param driver Selenium web driver
//     * @param by element to search for
//     * @param timeout seconds for element to disappear after being seen
//     * @return
//     */
//    public static boolean waitForElementToDisappear(WebDriver driver, By by, Integer timeout) {
//        boolean found = false;
//        found = waitForElement(driver, by, timeout) != null;
//        Long targetTime = System.currentTimeMillis() + timeout * 1000;
//        while (found) {
//            Long currentTime = System.currentTimeMillis();
//            if (currentTime > targetTime) {
//                break;
//            }
//            sleep(200);
//            try {
//                driver.findElement(by);
//                found = true;
//            } catch (Exception e) {
//                found = false;
//            }
//        }
//        return found;
//    }


    public static void waitForElementToDisappear(WebDriver driver, WebElement element) {
        WebElement element1 = null;
        try {
            element1 = waitForElementVisibility(driver, element, 15);
        } catch (Exception e){
        }
        if(element1 != null) {
            new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS).until(ExpectedConditions.invisibilityOf(element1));
        }
    }



    /**
     * Waits one second for spinny cursor to appear, then up to 60 seconds for it to disappear.
     * Watches for span[style="cursor:wait"]
     * @param driver for Selenium to search
     *
     */
    public static void waitForSpinnerToDisappear(WebDriver driver) {
        String selector = ".slds-spinner_container, .inputSpinner, .refreshSpinner, .forceInlineSpinner";
        try {
            WebElement spinner = waitForElementVisibility(driver, By.cssSelector(selector), 5);
            if (spinner != null) {
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(selector))
                );
            }
        }
        catch (NoSuchElementException | TimeoutException e) {
        }
    }

    /**
     * Waits one second for spinny cursor to appear, then up to 60 seconds for it to disappear.
     * Watches for span[style="cursor:wait"]
     * @param driver for Selenium to search
     *
     */
    public static void waitForSearchSpinnerToDisappear(WebDriver driver) {
        By bySpinnyCursor = By.cssSelector(".rounded-search-spinner");
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(bySpinnyCursor));
    }

//    /**
//     * <img src="../../../../../../doc-screenshots/LoadingSpinner1.png" alt="Loading Sppinner 2"><br>
//     * Waits up to 5 seconds for loading spinner to appear<br>
//     * Then waits up to 120 seconds for it to disappear.
//     * @param driver for Selenium to search
//     *
//     */
//    public static void waitForLoadingSpinnerToDisappear(WebDriver driver) {
//        /*
//         * Watches [div class="slds-spinner_container"]
//         * to become [div class="slds-spinner_container"].
//         * The web page can be in three states: none(before) - block - none(after).
//         * If we land in block, we wait for block to disappear and none to appear.
//         * If we land in none, we don't know if we haven't seen block yet or if we missed it.
//         * So we have to wait some timeout for it  to appear and then assume we missed it.
//         */
//        By byBlockSelector = By.cssSelector("div.slds-spinner_container.slds-show");
//        if (waitForElement(driver, byBlockSelector, 5) != null) {
//            waitForElementToDisappear(driver, byBlockSelector, 120);
//        }
//    }

    /**
     * Sleep
     * @param time time in ms
     */
    public static void sleep(Integer time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
        }
    }


    /**
     * @deprecated - If we need to move, then click, let the page object do this.
     * @param webElement
     * @param driver
     */
//    public static void safeClickOnElementLikeAction(WebElement webElement, WebDriver driver) {
////        LOGGER.debug("safeClickOnElementLikeAction");
//        if (webElement==null) {
//            Assert.assertFalse(true, "Failed click on element: webElement is NULL.");
//        }
//        else
//            {
//            new Actions(driver).moveToElement(webElement).click(webElement).build().perform();
//        }
//    }


    /**
     * Click on element like javascript
     */
    public static void clickOnElementLikeJavascript(WebDriver driver, WebElement element) {
        waitForElementVisibility(driver, element);
        String code = "arguments[0].click();";
        ((JavascriptExecutor)driver).executeScript(code, element);
    }

    /**
     * Clear an element
     */
    public static void clearElementJavascript(WebElement element, WebDriver driver) {
        String code = "arguments[0].value='';";
        ((JavascriptExecutor)driver).executeScript(code, element);
    }

    /**
     * Temp solution for new EUC page, we need to use old one
     * @param driver
     * @deprecated
     */
    public static void switchToOldConfig(WebDriver driver) {
        ((JavascriptExecutor)driver).executeScript("window.localStorage.setItem('oldConfig', 'true')");
    }

    /**
     * Initializes customized Fluent Wait.
     * @param driver
     * @param timeoutInSec
     * @param pollingIntervalInSec
     * @author yvasilyev
     */
    public static FluentWait<WebDriver> buildFluentWait(WebDriver driver, int timeoutInSec, int pollingIntervalInSec) {
        return
                new FluentWait<>(driver)
                        .withTimeout(timeoutInSec, SECONDS)
                        .pollingEvery(pollingIntervalInSec, SECONDS)
                        .ignoring(StaleElementReferenceException.class);
    }

    /**
     * If we are in an iframe, then get out.
     * @param driver
     * @author yvasilyev
     *
     */
    @Deprecated
    public static void switchToDefaultContent(WebDriver driver) {
        // Firsly executes javascript to detect if current page is parent or not
        // If not - it means that we are on iframe and should switch to default content
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        Object result = jsExec
                .executeScript(
                        "if(parent.location == self.location)" +
                                "{return 'false'}" +
                                "else{return 'true';}");
        if (Boolean.valueOf(result.toString())) {
            driver.switchTo().defaultContent();
        }
    }


    /***
     * Check is WebElement clickable
     *
     * @param driver WebDriver where to search
     * @param element Selenium selector
     * @return boolean
     */
    public static boolean isElementClickable(WebDriver driver, WebElement element) {
        boolean clickable = false;
        try {
            (new WebDriverWait(driver, 5))
                    .until(ExpectedConditions.elementToBeClickable(element));
            clickable = true;
        }
        catch (Exception e) {
//            LOGGER.warn(e.getMessage());
        }
        return clickable;
    }

    public static <T> T getPageClass(Class<T> clazz, WebDriver driver) {
        T t = null;
        try {
            t = clazz.getConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException e) {
//            LOGGER.error(e.getMessage());
            throw e;
        } finally {
            return t;
        }
    }

    public static <T> T getPageClass(Class<T> clazz, WebDriver driver, String str) {
        T t = null;
        try {
            t = clazz.getConstructor(WebDriver.class, String.class).newInstance(driver, str);
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException e) {
//            LOGGER.error(e.getMessage());
            throw e;
        } finally {
            return t;
        }
    }

    public static boolean waitForList(WebDriver driver, By by){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(500, TimeUnit.MILLISECONDS);

        new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_IN_SECONDS).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(by).size() > 0;
            }
        });
        return true;
    }

    @Deprecated
    public static void checkAlert(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            //exception handling
        }
    }

    /**
     *
     * @param driver
     * @param accept accept - true, dismiss - false
     */

    public static void alert(WebDriver driver, boolean accept) {
        try {
            sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            if(accept) {
                System.out.println("Alert accept");
                alert.accept();
            } else {
                alert.dismiss();
            }
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            //exception handling
        }
    }

    /**
     * Select by ignore case text
     * @param element
     * @param fieldValue
     */
    public static void setDropDownValue(WebElement element, String fieldValue) {
        Select dropDown = new Select(element);
        int index = 0;
        for (WebElement option : dropDown.getOptions()) {
            if (option.getText().equalsIgnoreCase(fieldValue))
                break;
            index++;
        }
        dropDown.selectByIndex(index);
    }


    public static void switchToWindowById(WebDriver driver, int id) {
        Set<String> handles = driver.getWindowHandles();
        driver.switchTo().window(handles.toArray()[id].toString());
    }

    /**
     * Get web element value
     * @param element
     * @return value
     */
    public static String getAttributeValue(WebElement element) {
        return element.getAttribute("value");
    }


}