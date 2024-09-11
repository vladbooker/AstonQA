import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayArrearsClass {
    public WebDriver driver;

    public PayArrearsClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='score-arrears']")
    private WebElement phoneArrears;

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='arrears-sum']")
    private WebElement sumArrears;

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='arrears-email']")
    private WebElement emailArrears;

    public String getScoreArrears() {
        return phoneArrears.getAttribute("placeholder");
    }

    public String getSumArrears() {
        return sumArrears.getAttribute("placeholder");
    }

    public String getEmailArrears() {
        return emailArrears.getAttribute("placeholder");
    }
}
