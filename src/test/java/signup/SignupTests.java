package signup;

import factories.UserFactory;
import graphql.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.User;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignupPage;

import java.time.Duration;

public class SignupTests {
    private WebDriver driver;

    private SignupPage signupPage;

    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        signupPage = new SignupPage(driver);
    }

//    @AfterEach
//    public void closeDriver() {
//        driver.quit();
//    }

    // happy path
    @Test
    public void userCreatedSuccessfully() throws InterruptedException {
        User user = UserFactory.createDefault();

        signupPage.open();
        signupPage.register(user);

        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Your account registration has been submitted"));


//        registrationPage.register(user, false);
//
//        accountSuccessPage.assertAccountCreatedSuccessfully();
    }


}
