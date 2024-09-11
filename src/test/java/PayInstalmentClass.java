import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayInstalmentClass {

    public WebDriver driver;

    public PayInstalmentClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='score-instalment']")
    private WebElement phoneInstalment;

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='instalment-sum']")
    private WebElement sumInstalment;

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='instalment-email']")
    private WebElement emailInstalment;

    public String getScoreInstalment() {
        return phoneInstalment.getAttribute("placeholder");
    }

    public String getSumInstalment() {
        return sumInstalment.getAttribute("placeholder");
    }

    public String getEmailInstalment() {
        return emailInstalment.getAttribute("placeholder");
    }
}
