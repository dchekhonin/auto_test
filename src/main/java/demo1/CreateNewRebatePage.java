package demo1;



import demo1.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import java.util.List;


public class CreateNewRebatePage {

    private WebDriver driver;

    @FindBy(xpath = "//span[text() = 'Name']/../following-sibling::input")
    private WebElement inputRebateName;

    @FindBy(xpath = "((//div [@class='form-element']) [1])/input")
    private WebElement setStartDate;

    @FindBy(xpath = "(//a[@class='datePicker-openIcon display']) [1]")
    private WebElement startDatePicker;

    @FindBy(xpath = ".//a[@title='Go to previous month']")
    private WebElement startDatePickerPrevMonthLink;

    @FindBy(xpath = "(//td[contains(@class, 'uiDayInMonthCell')]) [7]")
    private WebElement selectStartDateDayInMonth;

    @FindBy(xpath = "(//div/div[1]/div[1]/h2) [3]")
    private WebElement monthName;

    @FindBy(xpath = "//select[contains(@class,'slds-select picklist__label')]")
    private WebElement yearDropdown;

    @FindBy(xpath = "//span[contains(@class,'slds-day weekday DESKTOP uiDayInMonthCell--default') + contains(@class,'slds-day weekend DESKTOP uiDayInMonthCell--default')]")
    private WebElement datesInMonth;

    @FindBy(xpath = "(//a[@class='datePicker-openIcon display']) [2]")
    private WebElement endDatePicker;

//    @FindBy(css = "input[placeholder *= 'Search Accounts']")
    @FindBy(xpath = ".//*[@title='Search Accounts']")
    private WebElement payeeInput;

    @FindBy(xpath = "//span[contains(text(),'Payment Period')]/../..//a")
    private WebElement selectPaymentPeriodMenu;

    @FindBy(css = "[title=\"Save\"]")
    private WebElement saveNewRebateButton;


    public CreateNewRebatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public CreateNewRebatePage setRebateName() {
        Utils.typeInto(driver,inputRebateName,"Dimatest");
        return this;
    }


    public CreateNewRebatePage setRebateStartDate() {
//        Utils.click(driver,setStartDate);
        Utils.typeInto(driver,setStartDate,"12/04/2018");
        return this;
    }


    public CreateNewRebatePage setRebateStartMonthFromPicker(String mname) {

        Utils.click(driver, startDatePicker);
//        System.out.println(monthName.getText());
        do{
            Utils.click(driver, startDatePickerPrevMonthLink);
            monthName.getText() ;
            System.out.println(monthName.getText());
        }
        while (!monthName.getText().equals(mname));


        return this;
    }


    public CreateNewRebatePage setYear(String YearValue) {

        Utils.setDropDownValue(yearDropdown,YearValue);
        return this;
    }

//    public CreateNewRebatePage setDate() {
//
//        List<WebElement> myDatesWebElementsList = driver.findElements(By.xpath("//span[contains(@class,'slds-day weekday DESKTOP uiDayInMonthCell--default') + contains(@class,'slds-day weekend DESKTOP uiDayInMonthCell--default')]"));
//
//        List<String> allDatesWebElementsText=new ArrayList<>();
//
//
//        for(int i=0; i<myDatesWebElementsList.size(); i++){
//
//            allDatesWebElementsText.add(myDatesWebElementsList.get(i).getText());
//
//            System.out.println(myDatesWebElementsList.get(i).getText());
//
//        }
//        return this;
//    }

    public CreateNewRebatePage setExactDate(String ExactDateValue) {

        List<WebElement> myDatesWebElementsList = driver.findElements(By.xpath("//span[contains(@class,'slds-day weekday DESKTOP uiDayInMonthCell--default') + contains(@class,'slds-day weekend DESKTOP uiDayInMonthCell--default')]"));


        for(int i=0; i<myDatesWebElementsList.size(); i++){

            myDatesWebElementsList.get(i).getText();


//            System.out.println(myDatesWebElementsList.get(i).getText());

            if (myDatesWebElementsList.get(i).getText().equals(ExactDateValue)){
                myDatesWebElementsList.get(i).click();
                break;
            }


        }
        return this;
    }

    public CreateNewRebatePage setRebateEndMonthFromPicker(String mname) {

        Utils.click(driver, endDatePicker);
//        System.out.println(monthName.getText());
        do{
            Utils.click(driver, startDatePickerPrevMonthLink);
            monthName.getText() ;
            System.out.println(monthName.getText());
        }
        while (!monthName.getText().equals(mname));


        return this;
    }

    public CreateNewRebatePage setPayee(String payeeName) {

        Utils.typeInto(driver, payeeInput, payeeName);
        Utils.click(driver,By.xpath("//li//div[@title='Avnet']"));
        return this;
    }

    public CreateNewRebatePage setPaymentPeriod() {

        Utils.click(driver,selectPaymentPeriodMenu);
        Utils.click(driver,By.cssSelector(".uiMenuItem.uiRadioMenuItem>a[title=\"Weekly\"] "));

        return this;
    }

    public CreateNewRebatePage clickSave() {

        Utils.click(driver,saveNewRebateButton);

        return this;
    }




}




