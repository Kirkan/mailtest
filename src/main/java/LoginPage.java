import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

class LoginPage extends BasePage {
    private By username = By.id("mailbox__login");
    private By password = By.id("mailbox__password");
    private By loginButton = By.id("mailbox__auth__button");
    private By plaintLoginSuccess = By.id("js-mailbox-exit");
    private By isSWANeed = By.xpath("//div[starts-with(@class,'page page_swa device-pc')]");
    private By inputSWA = By.xpath("//input[@name='AuthCode']");

    LoginPage() {
        System.out.println("Login page");
        driver.get("https://mail.ru");
        if (driver.findElement(this.username) == null) {
            throw new IllegalStateException("This is not the login page");
        }
    }

    private LoginPage typeUsername(String username) {
        System.out.println("Type: Username " + username);
        driver.findElement(this.username).sendKeys(username);
        return this;
    }

    private LoginPage typePassword(String password) {
        System.out.println("Type: Password " + password);
        driver.findElement(this.password).sendKeys(password);
        return this;
    }

    private HomePage submitLogin() {
        System.out.println("Submit login");
        driver.findElement(loginButton).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.presenceOfElementLocated(plaintLoginSuccess));
        } catch (Exception e) {
            throw new IllegalStateException("Login failed");
        }
        return new HomePage();
    }

    private HomePage submit2FALogin() {
        System.out.println("Submit 2FA login");
        driver.findElement(loginButton).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(isSWANeed));
            String swaCode = JOptionPane.showInputDialog("Please enter your auth key:");
            WebElement swaInput = driver.findElement(inputSWA);
            swaInput.sendKeys(swaCode);
            swaInput.submit();
            System.out.println("Submit 2FA key:" + swaCode);
        } catch (Exception e) {
            throw new IllegalStateException("Login SWA failed");
        }
        return new HomePage();
    }

    private LoginPage submitLoginExpectingFailure() {
        driver.findElement(loginButton).click();
        return new LoginPage();
    }

    HomePage loginAs(String username, String password) {
        typeUsername(username);
        typePassword(password);
        return submit2FALogin();
    }
}
