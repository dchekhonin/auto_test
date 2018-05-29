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


public class TestDeleteRebates {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/dchekhonin/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com/");
    }


    @Test
    public void userLoginAndDeleteRebates() {

        LoginPage LoginPage = new LoginPage(driver);
        LoginPage.Username();
        LoginPage.Password();
        LoginPage.LoginButton();

        Homepage rebatesToDel = new Homepage(driver);
        rebatesToDel.openAppLauncher()
                .rebateManagementLink()
                .clickRebatesLink()
                .selectRebateView()
                .deleteRebatesWithName("Dimatest");



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
