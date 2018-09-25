import demo1.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;

public class test1 {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/dchekhonin/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com/");
    }

    @Test
    public void userLogin() {

        LoginPage LoginPage = new LoginPage(driver);
        LoginPage.Username();
        LoginPage.Password();
        LoginPage.LoginButton();

        Homepage homePage = new Homepage(driver);
        homePage.openAppLauncher()
                .rebateManagementLink()
                .clickRebatesLink()
                .newRebateButton()
                .selectRebateType()
                .nextButtonClickNewRebate()
                .setRebateName()
                .setRebateStartMonthFromPicker("JUNE")
                .setYear("2017")
                .setExactDate("6")
                .setRebateEndMonthFromPicker("AUGUST")
                .setYear("2017")
                .setExactDate("9")
                .setPayee("Avnet")
                .setPaymentPeriod()
                .clickSave();

        RebateDetailsPage rebatePage = new RebateDetailsPage(driver);
        rebatePage.checkLifeCycleLinksForDraft();

        NewGoalTypeWizard newGoal = new NewGoalTypeWizard(driver);
        newGoal.selectGoalType()
                .saveNewGoalWizardButton()
                .finalSaveGoalButton();





    }




//    @AfterClass
//    public static void tearDown() {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        WebElement menuProfile = driver.findElement(By.cssSelector(".branding-userProfile-button"));
//        menuProfile.click();
//        WebElement logoutButton = driver.findElement(By.cssSelector(".profile-link-label.logout.uiOutputURL"));
//        logoutButton.click();
//        driver.quit();
//
//}
}




