import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookMyShowAutomation {

    public static void main(String[] args) throws AWTException {
        
        WebDriver driver = new FirefoxDriver();
        

        // 1.Open the specified URL
        driver.get("https://in.bookmyshow.com/explore/home/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));

        // 2.Select Bengaluru as the city
        WebElement cityDropdown = driver.findElement(By.xpath(".//input[@class =\"sc-ijtseF yJmud\"]"));
        cityDropdown.sendKeys("Bengaluru");
        cityDropdown.sendKeys(Keys.ENTER);
        
        // 3.Click on Sign In
        WebElement signInButton = driver.findElement(By.xpath(".//div[text()=\"Sign in\"]"));
        signInButton.click();

        // 4.Click on Continue with Email
        WebElement continueWithEmailButton = driver.findElement(By.xpath(".//div[text()=\"Continue with Email\"]"));
        continueWithEmailButton.click();

        // 5.Enter email and click continue
        WebElement emailInput = driver.findElement(By.id("emailId"));
        emailInput.sendKeys("selauto@yopmail.com");
        WebElement continueButton = driver.findElement(By.xpath(".//button[text()='Continue']"));
        continueButton.click();

        // 6.Open Yopmail
        driver.get("http://www.yopmail.com");

        // 7.Type email and access inbox
        WebElement emailInputYopmail = driver.findElement(By.id("login"));
        emailInputYopmail.sendKeys("selauto@yopmail.com");
        WebElement checkInboxButton = driver.findElement(By.cssSelector(".sbut"));
        checkInboxButton.click();

        // 8.Locate the latest email and fetch OTP
        WebElement latestEmail = driver.findElement(By.cssSelector(".slientabs"));
        latestEmail.click();
        driver.switchTo().frame("ifmail");
        WebElement otpElement = driver.findElement(By.xpath("//td[contains(text(),'Your OTP is')]"));
        String otpText = otpElement.getText();
        String otp = otpText.split(" ")[3];

        // 9.Switch back to default content
        driver.switchTo().defaultContent();

        // 10.Go back to BookMyShow and enter OTP
        driver.get("https://in.bookmyshow.com/explore/home/");
        WebElement otpInput = driver.findElement(By.id("otp"));
        otpInput.sendKeys(otp);
        WebElement validateButton = driver.findElement(By.id("otp-email-submit"));
        validateButton.click();

        // 11.Wait for a moment to ensure successful sign in
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Hi, Guest')]")));

        // 12.Validate if user is signed in
        WebElement greetingElement = driver.findElement(By.xpath("//span[contains(text(),'Hi, Guest')]"));
        if (greetingElement.isDisplayed()) {
            System.out.println("User is successfully signed in.");
        } else {
            System.out.println("Sign in was not successful.");
        }

        // Close the browser
        driver.quit();
    }
}
