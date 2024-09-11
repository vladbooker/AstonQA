import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayConnectionClass {

    public WebDriver driver;

    public PayConnectionClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='connection-phone']")
    private WebElement phoneConnection;

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='connection-sum']")
    private WebElement sumConnection;

    @FindBy(xpath = ".//div[@class='pay__forms']//input[@id='connection-email']")
    private WebElement emailConnection;

    @FindBy(xpath = ".//form[@id='pay-connection']//button")
    private WebElement nextButton;

    @FindBy(xpath = ".//div[@class='content ng-tns-c46-3']//label")
    private WebElement nameOwnerText;

    @FindBy(xpath = ".//span[text()='100.00 BYN']")
    private WebElement sumTitleText;

    @FindBy(xpath = ".//div[@class='card-page__card']//button")
    private WebElement sumButtonText;

    @FindBy(xpath = ".//div[@class='pay-description__text']/span")
    private WebElement phoneTitleText;

    @FindBy(xpath = ".//div[@class='content ng-tns-c46-1']//label")
    private WebElement cardNumberText;

    @FindBy(xpath = ".//div[@class='content ng-tns-c46-4']//label")
    private WebElement dateOutText;

    @FindBy(xpath = ".//div[@class='content ng-tns-c46-5']//label")
    private WebElement cvcCodeText;

    public String getSumTitleText() {
        return sumTitleText.getAttribute("innerHTML");
    }

    public String getSumButtonText() {
        return sumButtonText.getAttribute("innerHTML");
    }

    public String getPhoneTitleText() {
        return phoneTitleText.getAttribute("innerHTML");
    }

    public String getCardNumberText() {
        return cardNumberText.getAttribute("innerHTML");
    }

    public String getDateOutText() {
        return dateOutText.getAttribute("innerHTML");
    }

    public String getCvcCodeText() {
        return cvcCodeText.getAttribute("innerHTML");
    }

    public String getNameOwnerText() {
        return nameOwnerText.getAttribute("innerHTML");
    }

    public String getPhoneConnection() {
        return phoneConnection.getAttribute("placeholder");
    }

    public String getSumConnection() {
        return sumConnection.getAttribute("placeholder");
    }

    public String getEmailConnection() {
        return emailConnection.getAttribute("placeholder");
    }

    public void inputPhone(String phone) {
        phoneConnection.click();
        phoneConnection.sendKeys(phone);
    }

    public void inputSum(String sum) {
        sumConnection.click();
        sumConnection.sendKeys(sum);
    }

    public void inputEmail(String email) {
        emailConnection.click();
        emailConnection.sendKeys(email);
    }

    public void clickBtn() {
        nextButton.click();
    }
}
