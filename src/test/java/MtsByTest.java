import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MtsByTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[contains(text(), 'Принять')]"));
            closeButton.click();
        } catch (Exception e) {
            System.out.println("Окно с файлами cookies не найдено или уже закрыто.");
        }

        WebDriverWait wait = new WebDriverWait(driver, 10);
        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        for (String pay : services) {
            WebElement serviceInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '" + pay + "')]")));
            assert serviceInput != null : "Элемент с текстом " + pay + " не найден";
            assert serviceInput.getAttribute("placeholder") != null : "Надпись в незаполненном поле " + pay + " не найдена";
        }

        WebElement phoneDisplay = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("connection-phone")));
        assert phoneDisplay.getText().equals("Номер телефона: 297777777") : "Некорректное отображение номера телефона";

        WebElement sumDisplay = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("connection-sum")));
        assert sumDisplay.getText().equals("Сумма: 1000 руб.") : "Некорректное отображение суммы";

        WebElement cardDetails = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='app-wrapper__content-container app-wrapper__content-container_fulls']")));
        assert cardDetails.getAttribute("placeholder") != null : "Надпись в незаполненном поле для ввода реквизитов карты не найдена";

        WebElement paymentLogos = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='pay__partners']")));
        assert paymentLogos.findElements(By.tagName("img")).size() > 0 : "Логотипы платежных систем не найдены";

        driver.quit();
    }
}
