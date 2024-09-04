import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Epic("MTS Tests")
@Feature("MTS Website Tests")
@Owner("My")
@ExtendWith(AllureJunit5.class)
public class MtsByTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
    }

    @DisplayName("Verify services on MTS.by homepage")
    @Test
    @Step("Проверка доступных услуг на главной странице MTS.by")
    public void testServices() {
        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        for (String service : services) {
            verifyServicePresence(service);
        }
    }

    @Step("Проверьте наличие услуги: {service}")
    private void verifyServicePresence(String service) {
        WebElement serviceInput = driver.findElement(By.xpath("//*[contains(text(), '" + service + "')]"));
        Assertions.assertNotNull(serviceInput, "Элемент с текстом " + service + " не найден");
        String placeholder = serviceInput.getAttribute("placeholder");
        Assertions.assertNotNull(placeholder, "Надпись в незаполненном поле " + service + " не найдена");
    }

    @Test
    @Step("Проверка отображения номера телефона")
    public void testPhoneDisplay() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement phoneDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
        Assertions.assertNotNull(phoneDisplay, "Элемент для отображения номера телефона не найден");
        String displayedPhone = phoneDisplay.getText().trim();
        System.out.println("Отображаемый номер телефона: " + displayedPhone);
        Assertions.assertEquals("Номер телефона: 297777777", displayedPhone, "Некорректное отображение номера телефона");
    }

    @Test
    @Step("Проверка отображения суммы")
    public void testSumDisplay() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement sumDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        Assertions.assertNotNull(sumDisplay, "Элемент для отображения суммы не найден");
        String displayedSum = sumDisplay.getText().trim();
        System.out.println("Отображаемая сумма: " + displayedSum);
        Assertions.assertEquals("Сумма: 1000 руб.", displayedSum, "Некорректное отображение суммы");
    }

    @Test
    @Step("Проверка деталей карты")
    public void testCardDetails() {
        WebElement cardDetails = driver.findElement(By.xpath("//div[@class='app-wrapper__content-container app-wrapper__content-container_fulls']"));
        Assertions.assertNotNull(cardDetails, "Элемент для ввода реквизитов карты не найден");
        Assertions.assertNotNull(cardDetails.getAttribute("placeholder"), "Надпись в незаполненном поле для ввода реквизитов карты не найдена");
    }

    @Test
    @Step("Проверка логотипов платежных систем")
    public void testPaymentLogos() {
        WebElement paymentLogos = driver.findElement(By.xpath("//div[@class='pay__partners']"));
        Assertions.assertNotNull(paymentLogos, "Элемент для логотипов платежных систем не найден");
        List<WebElement> logos = paymentLogos.findElements(By.tagName("img"));
        Assertions.assertTrue(logos.size() > 0, "Логотипы платежных систем не найдены");
        for (WebElement logo : logos) {
            Assertions.assertTrue(logo.isDisplayed(), "Логотип платежной системы не отображается");
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
