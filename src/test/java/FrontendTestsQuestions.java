import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class FrontendTestsQuestions {
    private static ChromeDriver driver;

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void UserSeesOneQuestionAtATimeWhenLoggedIn(){
        driver.get("http:localhost:3000");
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='emailInput']"));
        emailInput.sendKeys("questiontest1@gmail.com");
        WebElement loginButton = driver.findElement(By.xpath("//button[@id='loginSubmit']"));
        loginButton.click();

        boolean isUrlReached = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("http://localhost:3000/question"));

        List<WebElement> cards = driver.findElements(By.xpath("//div[contains(@class, 'card ')]"));

        Assertions.assertEquals(1, cards.size());
    }

    @Test
    public void UserSeesNextQuestionWhenAnswerIsSubmitted(){
        driver.get("http:localhost:3000");
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='emailInput']"));
        emailInput.sendKeys("questiontest2@gmail.com");
        WebElement loginButton = driver.findElement(By.xpath("//button[@id='loginSubmit']"));
        loginButton.click();

        boolean isUrlReached = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("http://localhost:3000/question"));

        Select answerSelect = new Select(new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("answerSelect"))));

        answerSelect.selectByValue("1");

        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'Siguiente')]"));
        submitButton.click();

        boolean isUrlReachedTwice = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("http://localhost:3000/question"));

        List<WebElement> cards = driver.findElements(By.xpath("//div[contains(@class, 'card ')]"));

        Assertions.assertTrue(isUrlReachedTwice && cards.size() == 1);
    }

//   After the last one, they get a new button that lets them now
//	 this is the last question and they are about to send answers to DB
//   (use same button but change text and color)

    public void SentToConfirmationScreenOnTestEnd(){
        Assertions.assertTrue(driver.getCurrentUrl().endsWith("/testend"));
    }
}