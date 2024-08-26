import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsByTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mts.by");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCheckBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]"));
        Assert.assertNotNull("Блок не найден", blockTitle);
        Assert.assertEquals("Название блока не соответствует", "Онлайн пополнение\n" + "без комиссии", blockTitle.getText());
    }


    @Test
    public void testPaymentLogosDisplay() {
        List<WebElement> paymentLogos = driver.findElements(By.cssSelector("img[alt='Visa'], img[alt='Verified By Visa'], img[alt='MasterCard'], img[alt='MasterCard Secure Code'], img[alt='Белкарт'].payment-logo-class"));
        Assert.assertEquals("Не найдено 5 логотипов платёжных систем", 5, paymentLogos.size());
        for (WebElement logo : paymentLogos) {
            Assert.assertTrue("Логотип не отображается", logo.isDisplayed());
        }
    }

    @Test
    public void testOnlineReplenishmentLink() {
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));

        Assert.assertTrue("Ссылка 'Подробнее о сервисе' недоступна", moreInfoLink.isDisplayed());
        moreInfoLink.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("URL страницы не совпадает с ожидаемым", expectedUrl, actualUrl);
    }

    @Test
    public void testContinueButtonFunctionality() {

        WebElement phoneNumberInput = driver.findElement(By.id("connection-phone"));
        phoneNumberInput.sendKeys("297777777");

        WebElement amountInput = driver.findElement(By.id("connection-sum"));
        amountInput.sendKeys("100");

        WebElement continueButton = driver.findElement(By.cssSelector("button.button.button__default"));
        Assert.assertTrue("Кнопка 'Продолжить' должна быть активной", continueButton.isEnabled());

        continueButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe.bepaid-iframe")));

        WebElement iframe = driver.findElement(By.cssSelector("iframe.bepaid-iframe"));
        Assert.assertTrue("Iframe с платежной формой должен быть видим", iframe.isDisplayed());
    }
}


