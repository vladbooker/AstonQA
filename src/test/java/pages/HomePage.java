package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://mts.by");
    }

    public WebElement getBlockTitle() {
        return driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]"));
    }

    public WebElement getOnlineReplenishmentLink() {
        return driver.findElement(By.linkText("Подробнее о сервисе"));
    }

    public PaymentPage fillConnectionDetails(String phone, String amount) {
        WebElement phoneNumberInput = driver.findElement(By.id("connection-phone"));
        phoneNumberInput.sendKeys(phone);

        WebElement amountInput = driver.findElement(By.id("connection-sum"));
        amountInput.sendKeys(amount);

        // Закрываем перекрывающий элемент
        try {
            WebElement closeButton = driver.findElement(By.cssSelector("button.btn.btn_gray.cookie__cancel"));
            closeButton.click();
        } catch(Exception e) {
            System.err.println("Не удалось закрыть перекрывающий элемент: " + e.getMessage());
        }


        // Ждем кнопку Продолжить
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.button__default")));
        if (continueButton.isDisplayed() && continueButton.isEnabled()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        } else {
            System.err.println("Кнопка не доступна для клика: " + continueButton);
        }

        // Используем JavaScript для клика на кнопку
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        } catch (ElementNotInteractableException e) {
            System.err.println("Кнопка не доступна для клика: " + e.getMessage());
            // возможное логирование или дополнительные действия
        }

        return new PaymentPage(driver);
    }

}
