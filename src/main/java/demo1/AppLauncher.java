package demo1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AppLauncher {

    private WebDriver driver;

//    @FindBy(css = "a[title *= 'Rebate Programs']")
//    private WebElement rebatesLink;

    @FindBy(css = ".appTileTitle[title = 'Rebate Management']")
    private WebElement rebateManagementLink;

    public AppLauncher(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public Homepage rebateManagementLink() {
        Utils.click(driver,rebateManagementLink);
        return new Homepage(driver);

    }

//    public RebateListPage clickRbatesLink() {
//        rebatesLink.click();
//        return new RebateListPage(driver);
//
//    }
}