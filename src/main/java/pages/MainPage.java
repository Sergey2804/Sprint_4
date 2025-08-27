package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class MainPage {

    private final WebDriver driver;

    // Локатор для кнопки "Куки"
    private final By cookiesButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");


    // Локатор верхней кнопки заказа
    public By UpButtonOrder =
            By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    // Локатор нижней кнопки заказа самоката
    public By DownButtonOrder =
         By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    // Конструктор для вебдрайвера
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    // Метод для открытия страница
    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    // Метод для закрытия куки
    public void clickSignInButton() {
        driver.findElement(cookiesButton).click();
    }
    public void clickOrderUpButton() {

        driver.findElement(UpButtonOrder).click();

    }
    // Метод кликает по верхней или нижней кнопке заказа самоката
    public void clickOrderDownButton() {

            WebElement lowerOrderButton = driver.findElement(DownButtonOrder);
            ((JavascriptExecutor)driver).
                    executeScript("arguments[0].scrollIntoView();", lowerOrderButton);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(DownButtonOrder));
            lowerOrderButton.click();

    }

    // Метод скролит до вопроса и кликает на стрелку
    public String clickOnQuestion(int index) {
        By question = By.id(String.format("accordion__heading-%s", index));
        WebElement questionElement = driver.findElement(question);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(question));
        questionElement.click();
        return  questionElement.getText();
    }

    // Метод возвращает ответ на вопрос из списка на Главной странице
    public String answerDisplayed(int index) {
        WebElement answerElement = driver.findElement(By.id(String.format("accordion__panel-%s", index)));
        return answerElement.getText();
    }

}