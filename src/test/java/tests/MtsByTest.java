package tests;

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

import java.util.List;

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
    public void testCheckBlockTitle() {
        Assert.assertNotNull("Блок не найден", homePage.getBlockTitle());
        Assert.assertEquals("Название блока не соответствует", "Онлайн пополнение\n"+"без комиссии", homePage.getBlockTitle().getText());
    }

    @Test
    public void testOnlineReplenishmentLink() {
        Assert.assertTrue("Ссылка 'Подробнее о сервисе' недоступна", homePage.getOnlineReplenishmentLink().isDisplayed());
        homePage.getOnlineReplenishmentLink().click();
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
        paymentPage = homePage.fillConnectionDetails("297777777", "100");

        Assert.assertTrue("Iframe с платежной формой должен быть видим", paymentPage.getFrame().isDisplayed());
        paymentPage.checkPaymentLogos(); // Проверка логотипов платежных систем
        paymentPage.checkEmptyFields(); // Проверка пустых полей ввода карты
    }



    @Test
    public void testCheckReadMoreLink() {
        WebElement readMoreLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        Assert.assertTrue("Ссылка 'Подробнее о сервисе' не активна", readMoreLink.isDisplayed());
        readMoreLink.click();

        // Проверяем, что открылась нужная страница (примерный URL, уточните)
        Assert.assertEquals("URL не соответствует", "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl());

        // Возврат обратно после проверки
        driver.navigate().back();
    }

}



