import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TestMTS {

    public static WebDriver driver;
    public static PayConnectionClass payConnectionClass;
    public static PayInternetClass payInternetClass;
    public static PayInstalmentClass payInstalmentClass;
    public static PayArrearsClass payArrearsClass;

    public static PayWrapperClass payWrapperClass;

    static final String DEFAULT_URL = "https://mts.by";

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
        payConnectionClass = new PayConnectionClass(driver);
        payInternetClass = new PayInternetClass(driver);
        payInstalmentClass = new PayInstalmentClass(driver);
        payArrearsClass = new PayArrearsClass(driver);
        payWrapperClass = new PayWrapperClass(driver);
        driver.get(DEFAULT_URL);
        closeCookie();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    private void closeCookie() {
        payWrapperClass.timeDelaySeconds(3);
        try {
            payWrapperClass.clickPathObject(".//div[@class='cookie__wrapper']/*/button[text()='Принять']");
        } catch (Exception e) {
        }
    }

    @DisplayName("Проверка заголовка")
    @Test
    void testCheckTitleName() {
        String title = "Онлайн пополнение без комиссии";
        assertEquals(title, payWrapperClass.getPayWrapperHeaderText());
    }

    @DisplayName("Проверка наличия логотипов платежных систем")
    @Test
    void testCheckLogo() {
        String[] imageSrc = payWrapperClass.findImageAppWrapper(6);
        for (int i = 1; i < 6; i++) {
            assertNotEquals(null, imageSrc[i - 1]);
        }
    }

    @DisplayName("Проверка работы ссылки")
    @Test
    void testCheckLink() throws IOException {
        String truePage = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String currentPage = driver.getCurrentUrl();
        payWrapperClass.clickLink();
        String newPage = driver.getCurrentUrl();
        assertNotEquals(currentPage, newPage);
        assertEquals(truePage, newPage);
    }
    @DisplayName("Проверка полей у раздела 'Услуги связи'")
    @Test
    void checkPlaceHolder1() {
        assertAll(
                () -> assertEquals("Номер телефона", payConnectionClass.getPhoneConnection()),
                () -> assertEquals("Сумма", payConnectionClass.getSumConnection()),
                () -> assertEquals("E-mail для отправки чека", payConnectionClass.getEmailConnection())
        );
    }
    @DisplayName("Проверка полей у раздела 'Домашний интернет'")
    @Test
    void checkPlaceHolder2() {
        payWrapperClass.clickPathObject(".//div[@class='pay__wrapper']//span[text()='Услуги связи']");
        payWrapperClass.clickPathObject(".//div[@class='pay__wrapper']//p[text()='Домашний интернет']");
        assertAll(
                () -> assertEquals("Номер абонента", payInternetClass.getPhoneInternet()),
                () -> assertEquals("Сумма", payInternetClass.getSumInternet()),
                () -> assertEquals("E-mail для отправки чека", payInternetClass.getEmailInternet())
        );
    }
    @DisplayName("Проверка полей у раздела 'Рассрочка'")
    @Step
    @Test
    void checkPlaceHolder3() {
        payWrapperClass.clickPathObject(".//div[@class='pay__wrapper']//span[text()='Услуги связи']");
        payWrapperClass.clickPathObject(".//div[@class='pay__wrapper']//p[text()='Рассрочка']");
        assertAll(
                () -> assertEquals("Номер счета на 44", payInstalmentClass.getScoreInstalment()),
                () -> assertEquals("Сумма", payInstalmentClass.getSumInstalment()),
                () -> assertEquals("E-mail для отправки чека", payInstalmentClass.getEmailInstalment())
        );
    }
    @DisplayName("Проверка полей у раздела 'Задолженность'")
    @Test
    void checkPlaceHolder4() {
        payWrapperClass.clickPathObject(".//div[@class='pay__wrapper']//span[text()='Услуги связи']");
        payWrapperClass.clickPathObject(".//div[@class='pay__wrapper']//p[text()='Задолженность']");
        assertAll(
                () -> assertEquals("Номер счета на 2073", payArrearsClass.getScoreArrears()),
                () -> assertEquals("Сумма", payArrearsClass.getSumArrears()),
                () -> assertEquals("E-mail для отправки чека", payArrearsClass.getEmailArrears())
        );
    }
    @DisplayName("Проверка корректности фрейма")
    @Test
    void testCheckIframe() {
        payConnectionClass.inputPhone("(29)777-77-77");
        payConnectionClass.inputSum("100");
        payConnectionClass.clickBtn();
        payWrapperClass.timeDelaySeconds(5);
        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@class='bepaid-iframe']")));
        String correctSum = "100";
        String correctPhone = "375297777777";
        assertAll(
                () -> assertTrue(payConnectionClass.getSumTitleText().contains(correctSum)),
                () -> assertTrue(payConnectionClass.getSumButtonText().contains(correctSum)),
                () -> assertTrue(payConnectionClass.getPhoneTitleText().contains(correctPhone)),
                () -> assertEquals("Номер карты", payConnectionClass.getCardNumberText()),
                () -> assertEquals("Срок действия", payConnectionClass.getDateOutText()),
                () -> assertEquals("CVC", payConnectionClass.getCvcCodeText()),
                () -> assertEquals("Имя держателя (как на карте)", payConnectionClass.getNameOwnerText())
        );
        String[] imageSrc = payWrapperClass.findImageIframe(1, 4);
        for (int i = 1; i < 4; i++) {
            assertNotEquals(null, imageSrc[i - 1]);
        }
        imageSrc = payWrapperClass.findImageIframe(2, 3);
        for (int i = 1; i < 3; i++) {
            assertNotEquals(null, imageSrc[i - 1]);
        }
    }

}
