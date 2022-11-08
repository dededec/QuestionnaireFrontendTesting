import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrontendTestsDavid {
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
}