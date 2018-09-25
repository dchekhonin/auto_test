package demo1;


import demo1.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoalPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class = 'slds-button-group']/button[. = 'Save & Close']")
    private WebElement finalSaveNewGoalButton;


    public GoalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    public void finalSaveGoalButton(){

        driver.switchTo().defaultContent();
        Utils.sleep(5000);
        driver.switchTo().frame(1);

        Utils.click(driver,finalSaveNewGoalButton);

    }
}
