import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class PayWrapperClass {

    public WebDriver driver;

    public PayWrapperClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[@class='pay__wrapper']/a")
    private WebElement linkService;

    @FindBy(xpath = ".//h2[contains(text(), 'Онлайн пополнение')]")
    private WebElement payWrapperHeader;

    public String[] findImageAppWrapper(int n) {
        String[] imageSrc = new String[n];
        for (int i = 1; i < n; i++) {
            WebElement logo = driver.findElement(By.xpath(String.format(".//div[@class='pay__partners']/ul/li[%s]/img", i)));
            imageSrc[i - 1] = logo.getAttribute("src");
        }
        return imageSrc;
    }

    public String[] findImageIframe(int position, int n) {
        String[] imageSrc = new String[n];
        if (position == 1) {
            for (int i = 1; i < n; i++) {
                WebElement logo = driver.findElement(By.xpath(String.format(".//div[@class='cards-brands ng-tns-c46-1']/div/img[%s]", i)));
                imageSrc[i - 1] = logo.getAttribute("src");
            }
        } else {
            for (int i = 1; i < n; i++) {
                WebElement logo = driver.findElement(By.xpath(String.format(".//div[@class='cards-brands ng-tns-c46-1']/div/div/img[%s]", i)));
                imageSrc[i - 1] = logo.getAttribute("src");
            }
        }
        return imageSrc;
    }

    public void clickLink() {
        linkService.click();
    }

    public void clickPathObject(String object) {
        driver.findElement(By.xpath(object)).click();
    }

    public void timeDelaySeconds(int delay) {
        driver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);
    }

    public String getPayWrapperHeaderText() {
        String innerText = payWrapperHeader.getAttribute("innerHTML");
        String[] lines = innerText.split("<br>");
        String testTitle = "";
        testTitle += lines[0] + lines[1];
        return testTitle;
    }
}
