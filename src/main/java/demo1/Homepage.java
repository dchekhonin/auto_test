package demo1;

import demo1.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Homepage {

    private WebDriver driver;
    @FindBy(css = ".slds-icon-waffle_container button")
    private WebElement appLauncherButton;

//    @FindBy(css = "div[title *= 'Rebate Management']")
//    private WebElement rebateManagementLink;

//    @FindBy(css = "a[title *= 'Rebate Programs']")
//    private WebElement RebatesLink;

    @FindBy(css = ".slds-context-bar [title = 'Rebate Programs']")
    private WebElement rebatesLink;


    public Homepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public AppLauncher openAppLauncher()
    {
        Utils.click(driver,appLauncherButton);
        return new AppLauncher(driver);
    }

    public RebateListPage clickRebatesLink() {

        driver.navigate().refresh();
        Utils.click(driver, rebatesLink, 10000);
        return new RebateListPage(driver);


    }

//    public RebateListPage  selectRebateView(){
//
//        Utils.click(driver,selectRebateListViewButton);
//        Utils.click(driver,selectRebateListViewButton);
//        return new RebateListPage(driver);
//
//
//    }

//    public Homepage deleteRebatesWithName(String RebatesToDelName) {
//
//
//        List<WebElement> myRebatesWebElementsList = driver.findElements(By.xpath(".//*[contains(@class,'slds-cell-edit')]/../..//tr/th/span"));
//
//
//        for(int i=0; i<myRebatesWebElementsList.size(); i++){
//
//            myRebatesWebElementsList.get(i).getText();
//
//
////            System.out.println(myDatesWebElementsList.get(i).getText());
//
//            if (myRebatesWebElementsList.get(i).getText().equals(RebatesToDelName)){
//                myRebatesWebElementsList.get(i).click();
//                break;
//            }
//
//
//        }
//        return this;
//    }
}





