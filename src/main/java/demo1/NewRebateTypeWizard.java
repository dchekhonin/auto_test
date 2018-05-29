package demo1;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewRebateTypeWizard {

    private WebDriver driver;

    @FindBy(xpath = "(//span[@class=\"slds-radio--faux\"]) [1]")
    private WebElement selectNewRebateType;

    @FindBy(xpath = "//button[2]/span[text()='Next']")
    private WebElement nextButton;


    public NewRebateTypeWizard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public NewRebateTypeWizard selectRebateType () {

        Utils.click(driver,selectNewRebateType);
        return new NewRebateTypeWizard(driver);

    }

    public CreateNewRebatePage nextButtonClickNewRebate(){

        Utils.click(driver,nextButton);
        return new CreateNewRebatePage(driver);

    }

}


