import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;

public class FrontendTestsMiguel {
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
    public void buttonShowAChangeWhenHovered() throws InterruptedException {
        driver.get("http://localhost:3000");
        WebElement ele = driver.findElement(new By.ById(("loginSubmit")));

        //Creating object of an Actions class
        Actions action = new Actions(driver);

        //Performing the mouse hover action on the target element.
        action.moveToElement(ele).pause(1).perform();

        Thread.sleep(3000);

        ele.click();
        Thread.sleep(3000);

        Thread.sleep(3000);
        Assertions.assertTrue(Arrays.asList(ele.getAttribute("class").split(" ")).contains("btn-submit:hover"));
    }
    @Test
    public void notEnterIfEmailInputIsInvalid() throws InterruptedException {
        driver.get("http://localhost:3000/");
        WebElement ele = driver.findElement(new By.ById(("loginSubmit")));
        WebElement emailInput = driver.findElement(new By.ById(("emailInput")));
        emailInput.sendKeys("test@test.com");
        Thread.sleep(2000);
        ele.click();
        Thread.sleep(2000);

        Assertions.assertTrue(driver.getCurrentUrl().endsWith("/"));
    }
    @Test
    public void notEnterIfEmailInputIsEmpty() throws InterruptedException {
        driver.get("http://localhost:3000");
        WebElement ele = driver.findElement(new By.ById(("loginSubmit")));
        WebElement emailInput = driver.findElement(new By.ById(("emailInput")));
        emailInput.sendKeys("");
        Thread.sleep(2000);
        ele.click();
        Thread.sleep(2000);

        Assertions.assertTrue(driver.getCurrentUrl().endsWith("/"));
    }

    @Test
    public void changeToQuestionPageIfEmailIsCorrect() throws InterruptedException {
        driver.get("http://localhost:3000/");
        WebElement ele = driver.findElement(new By.ById(("loginSubmit")));
        WebElement emailInput = driver.findElement(new By.ById(("emailInput")));
        emailInput.sendKeys("test123@test.com");
        Thread.sleep(2000);
        ele.click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.getCurrentUrl().endsWith("/question"));
        Thread.sleep(2000);
    }
}
