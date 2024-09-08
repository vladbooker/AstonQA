package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class PaymentPage {

    private WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        waitForPaymentForm();
    }

    public void waitForPaymentForm() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            // Ждем, пока iframe станет видимым
            WebElement paymentIFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']")));
            // Переключаемся на фрейм
            driver.switchTo().frame(paymentIFrame);
        } catch (TimeoutException e) {
            System.err.println("Фрейм не доступен или не загружен вовремя: " + e.getMessage());
        } catch (NoSuchFrameException e) {
            System.err.println("Не удалось переключиться на фрейм: " + e.getMessage());
        }
    }

    public WebElement getFrame() {
        try {
            // Переключаемся на основной контекст перед поиском фрейма
            driver.switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(driver, 15);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']")));
        } catch (Exception e) {
            System.err.println("Не удалось найти фрейм: " + e.getMessage());
            return null; // Или можно выбросить исключение
        }
    }


    public String getPhoneNumber() {
        // Найти элемент, который содержит текст с номером телефона
        WebElement phoneNumberElement = driver.findElement(By.xpath("//div[contains(@class, 'pay-description__text')]//span"));
        try {
            WebElement cookieWrapper = driver.findElement(By.className("cookie__wrapper"));
            if (cookieWrapper.isDisplayed()) {
                cookieWrapper.click(); // или использовать другой способ закрытия, если возможно
            }
        } catch (NoSuchElementException e) {
            // Элемент не найден, пропускаем
        }

        // Извлечь текст из элемента и отформатировать его для получения только номера
        String fullText = phoneNumberElement.getText();

        // Предполагая, что текст имеет фиксированный формат ("Оплата: Услуги связи Номер:375297777777")
        // мы можем разбить строку и извлечь только номер телефона
        String[] parts = fullText.split("Номер:");
        if (parts.length > 1) {
            return parts[1].trim(); // возвращаем номер без лишних пробелов
        }

        return null; // если номер не найден
    }




    public String getAmount() {
        // Находим элемент с нужным классом
        WebElement amountField = driver.findElement(By.cssSelector(".pay-description__cost span"));
        // Возвращаем текст элемента, который содержит сумму
        return amountField.getText();
    }



    public void checkEmptyFields() {
        // Необходимо дождаться, пока поля станут видимыми и интерактивными
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement cardNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cc-number")));
        WebElement expiryDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expiry-date")));
        WebElement cvvField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cvv")));

        // Проверка полей карты
        Assert.assertTrue("Поле для номера карты не должно быть заполнено", cardNumberField.getAttribute("value").isEmpty());
        Assert.assertTrue("Поле для даты окончания срока действия карты не должно быть заполнено", expiryDateField.getAttribute("value").isEmpty());
        Assert.assertTrue("Поле для CVV-кода не должно быть заполнено", cvvField.getAttribute("value").isEmpty());
    }

    public void checkPaymentLogos() {
        List<WebElement> paymentLogos = driver.findElements(By.cssSelector("img[alt='Visa'], img[alt='Verified By Visa'], img[alt='MasterCard'], img[alt='MasterCard Secure Code'], img[alt='Белкарт']"));
        Assert.assertEquals("Не найдено 7 логотипов платёжных систем", 7, paymentLogos.size());
        for (WebElement logo : paymentLogos) {
            Assert.assertTrue("Логотип не отображается", logo.isDisplayed());
        }
    }
}