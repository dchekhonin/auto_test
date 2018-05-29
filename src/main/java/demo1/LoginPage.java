package demo1;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement usernameId;

    @FindBy(id = "password")
    private WebElement passwordId;

    @FindBy(id = "Login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void Username() {

        Utils.typeInto(driver,usernameId,System.getenv("Username"));
    }


    public void Password() {

        Utils.typeInto(driver,passwordId,System.getenv("Password"));
    }


    public void LoginButton() {

        loginButton.click();

    }



}
