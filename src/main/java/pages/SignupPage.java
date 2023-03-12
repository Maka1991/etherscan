package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {
    private final WebDriver driver;


    // konstruktor, zove se isto kao i klasa 'SignupPage' i nema return type
    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.navigate().to("https://etherscan.io/register");
    }

    public void register(User user) throws InterruptedException {

        if (!user.getUsername().isEmpty()) {
            usernameInput().sendKeys(user.getUsername());
        }

        if (!user.getEmail().isEmpty()) {
            emailInput().sendKeys(user.getEmail());
        }

        if (!user.getConfirmEmail().isEmpty()) {
            confirmEmailInput().sendKeys(user.getConfirmEmail());
        }

        if (!user.getPassword().isEmpty()) {
            passwordInput().sendKeys(user.getPassword());
        }

        if (!user.getConfirmPassword().isEmpty()) {
            confirmPasswordInput().sendKeys(user.getConfirmPassword());
        }


        // scroll to middle of the page - password input field
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.id("ContentPlaceHolder1_txtPassword")));
        Thread.sleep(1000);

        if (user.getTermsAndConditions() != null && user.getTermsAndConditions()) {
            WebElement termsAndConditionsInput = termsAndConditionsInput();
            Actions act = new Actions(driver);
            act.click(termsAndConditionsInput).build().perform();
        }

        if (user.getNewsletter() != null && user.getNewsletter()) {
            WebElement newsletterInput = newsletterInput();
            Actions act = new Actions(driver);
            act.click(newsletterInput).build().perform();
        }

        Thread.sleep(1000);


        // click on captcha checkbox
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name,'a-')]")));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark")));

        Actions act = new Actions(driver);
        act.click(element).build().perform();

        Thread.sleep(20000);



        // wait for captcaha modal to close

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.
                until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='recaptcha challenge expires in two minutes']")));
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait1.until(
                ExpectedConditions.invisibilityOfElementLocated(By.id("rc-imageselect")));




        WebElement createAccountButton = createAccountButton();
        createAccountButton.click();
    }


    public WebElement usernameInput() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtUserName"));
    }
    public WebElement emailInput() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtEmail"));
    }
    public WebElement confirmEmailInput() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtConfirmEmail"));
    }
    public WebElement passwordInput() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtPassword"));
    }
    public WebElement confirmPasswordInput() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtPassword2"));
    }

    public WebElement termsAndConditionsInput() {
        return driver.findElement(By.id("ContentPlaceHolder1_MyCheckBox"));
    }

    public WebElement newsletterInput() {
        return driver.findElement(By.id("ContentPlaceHolder1_SubscribeNewsletter"));
    }

    public WebElement createAccountButton() {
        return driver.findElement(By.id("ContentPlaceHolder1_btnRegister"));
    }

}
