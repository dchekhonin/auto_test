package demo1;

import demo1.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RebateDetailsPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Draft')]/..//span[contains(text(),'Submit for Approval')]")
    private WebElement submitForApprovalLink;

    @FindBy(xpath = "//div[contains(text(),'Draft')]/..//span[contains(text(),'Clone Program')]")
    private WebElement cloneProgramLink;

    @FindBy(xpath = "//div[contains(text(),'Draft')]/..//span[contains(text(),'Generate Program Letter')]")
    private WebElement generateProgramLetterlLink;

    @FindBy(xpath = "//div[contains(text(),'Draft')]/..//span[contains(text(),'Generate Payee Report')]")
    private WebElement generatePayeeReportLink;

    @FindBy(xpath = "//div[contains(text(),'Draft')]/..//span[contains(text(),'New Goal')]")
    private WebElement newGoalLink;

//    @FindBy(xpath = "(//div[contains(text(),'Draft')]/..//a) [5]")
//    private WebElement newGoalLink;

    @FindBy(xpath = "//div[contains(text(),'Draft')]")
    private WebElement lifecycleDraft;


    public RebateDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RebateDetailsPage checkLifeCycleLinksForDraft() {

        driver.switchTo().defaultContent();
        Utils.sleep(15000);
        driver.switchTo().frame(0);

        if (lifecycleDraft.isDisplayed()){



            assert submitForApprovalLink.isDisplayed();
            assert cloneProgramLink.isDisplayed();
            assert generateProgramLetterlLink.isDisplayed();
            assert generatePayeeReportLink.isDisplayed();
            assert newGoalLink.isDisplayed();

            System.out.println("All 5 links are presented");

        }


        return this;
    }


}
