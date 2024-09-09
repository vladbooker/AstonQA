import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.PaymentPage;

public class MtsByTest {

    private WebDriver driver;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        homePage.open();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Проверка заголовка блока")
    public void testCheckBlockTitle() {
        log("Проверяем заголовок блока");
        Assert.assertNotNull("Блок не найден", homePage.getBlockTitle());
        String blockTitle = homePage.getBlockTitle().getText();
        Assert.assertEquals("Название блока не соответствует", "Онлайн пополнение\n"+"без комиссии", blockTitle);
        attachBlockTitle(blockTitle);
    }

    @Test
    @Description("Проверка ссылки на онлайн пополнение")
    public void testOnlineReplenishmentLink() {
        log("Проверяем ссылку на онлайн пополнение");
        Assert.assertTrue("Ссылка 'Подробнее о сервисе' недоступна",
                homePage.getOnlineReplenishmentLink().isDisplayed());
        homePage.getOnlineReplenishmentLink().click();

        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("URL страницы не совпадает с ожидаемым", expectedUrl, actualUrl);
    }

    @Test
    @Description("Проверка функциональности кнопки 'Продолжить'")
    public void testContinueButtonFunctionality() {
        log("Заполняем данные подключения");
        paymentPage = homePage.fillConnectionDetails("297777777", "100");

        Assert.assertTrue("Iframe с платежной формой должен быть видим", paymentPage.getFrame().isDisplayed());
        paymentPage.checkPaymentLogos();
        paymentPage.checkEmptyFields();
    }

    @Test
    @Description("Проверка ссылки 'Подробнее о сервисе'")
    public void testCheckReadMoreLink() {
        WebElement readMoreLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        Assert.assertTrue("Ссылка 'Подробнее о сервисе' не активна", readMoreLink.isDisplayed());
        readMoreLink.click();
        Assert.assertEquals("URL не соответствует",
                "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                driver.getCurrentUrl());
        driver.navigate().back();
    }

    @Step("Логирование: {message}")
    private void log(String message) {
        System.out.println(message);
    }

    @Attachment(value = "Заголовок блока", type = "text/plain")
    private String attachBlockTitle(String blockTitle) {
        return blockTitle;
    }
}


