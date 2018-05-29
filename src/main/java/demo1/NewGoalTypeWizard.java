package demo1;

import demo1.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewGoalTypeWizard {

    private WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Draft')]/..//span[contains(text(),'New Goal')]")
    private WebElement newGoalLink;

    @FindBy(xpath = ".//label[text()='Qualification Type']/following-sibling::div/div/button")
    private WebElement selectNewGoalTypeField;

    @FindBy(css = "[title=\"Single Qualification\"]")
    private WebElement selectNewGoalType;


    @FindBy(xpath = ".//label[text()='DataSource']/following-sibling::div/div/button")
    private WebElement selectDatasourceField;

    @FindBy(css = "[title=\"Sales\"]")
    private WebElement selectDatasource;

    @FindBy(xpath = "//div[@class = 'slds-modal__footer']/button[. = 'Save']")
    private WebElement saveNewGoalWizardButton;



    public NewGoalTypeWizard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public NewGoalTypeWizard selectGoalType () {

        Utils.click(driver,newGoalLink);

        driver.switchTo().defaultContent();
        Utils.sleep(5000);
        driver.switchTo().frame(0);

        Utils.click(driver,selectNewGoalTypeField);
        Utils.click(driver,selectNewGoalType);

        Utils.click(driver,selectDatasourceField);
        Utils.click(driver,selectDatasource);


        return this;

    }

    public GoalPage saveNewGoalWizardButton(){

        Utils.click(driver,saveNewGoalWizardButton);
        return new GoalPage(driver);

    }



}


