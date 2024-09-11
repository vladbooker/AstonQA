import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayInternetClass {
    public WebDriver driver;

    public PayInternetClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='internet-phone']")
    private WebElement phoneInternet;

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='internet-sum']")
    private WebElement sumInternet;

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='internet-email']")
    private WebElement emailInternet;

    public String getPhoneInternet() {
        return phoneInternet.getAttribute("placeholder");
    }

    public String getSumInternet() {
        return sumInternet.getAttribute("placeholder");
    }

    public String getEmailInternet() {
        return emailInternet.getAttribute("placeholder");
    }
}
