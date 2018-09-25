package demo1;

import demo1.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class RebateListPage {

    private WebDriver driver;

    @FindBy(css = ".slds-context-bar [title = 'Rebate Programs']")
    private WebElement rebatesLink;

    @FindBy(css = ".forceActionLink [title = New]")
    private WebElement newRebateButton;

    @FindBy(css = "[title='Select List View']")
    private WebElement selectRebateListViewButton;

    @FindBy(xpath = "//*[text()='All']")
    private WebElement selectAllRebateListView;

//    @FindBy(xpath = ".//*[contains(@class,'slds-cell-edit')]/../..//div[contains (@class,'forceVirtualActionMarker')]")
    private WebElement actionButton;

//    @FindBy(xpath = "//div[@class = 'branding-actions actionMenu']/a[. = 'Delete'] ")
//    private WebElement deleteButton;

    @FindBy(xpath = "//div[@class = 'branding-actions actionMenu']//li[2]")
    private WebElement deleteButton;

    @FindBy(xpath = ("//tr[contains(., 'Dimatest')]" ))
    private WebElement rebateLineInList;

    public RebateListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
//    public RebateListPage clickRebatesLink() {
////        rebatesLink = (new WebDriverWait(driver, 10)) .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".slds-context-bar [title = 'Rebate Programs']")));
////        rebatesLink.click();
////        new Actions(driver).moveToElement(rebatesLink).perform();
//        driver.navigate().refresh();
//        Utils.click(driver,rebatesLink,10000);
//        return new RebateListPage(driver);
//
//    }

    public NewRebateTypeWizard newRebateButton(){

        Utils.click(driver,newRebateButton);
        return new NewRebateTypeWizard(driver);

    }

    public RebateListPage  selectRebateView(){

        Utils.click(driver,selectRebateListViewButton);
        Utils.click(driver,selectAllRebateListView);
        return this;


    }


    public RebateListPage deleteRebatesWithName(String name) {

        driver.switchTo().defaultContent();
        Utils.sleep(5000);


//        List<WebElement> myRebatesWebElementsList = driver.findElements(By.xpath("//span[contains(text(),'Dimatest')]/../../..//div[contains (@class,'forceVirtualActionMarker')]"));

        List<WebElement> myRebatesLineWebElementsList = driver.findElements(By.xpath("//tr[contains(.,'"+name+"')]"));
        int i = 0;
        do{

            myRebatesLineWebElementsList.get(i).findElement(By.xpath("//div[contains (@class,'forceVirtualActionMarker')]")).click();

            Utils.click(driver,deleteButton);
            System.out.println(myRebatesLineWebElementsList.get(i).getText());
        }
        while (rebateLineInList.getText().isEmpty());


            return this;


        }
    }

