import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class HomePage extends BasePage {
    private By composeButton = By.xpath("//a[@data-name='compose']");
    private By logoutButton = By.xpath("//a[@id='PH_logoutLink']");

    HomePage() {
        System.out.println("Home page");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(composeButton));
        } catch (Exception e) {
            throw new IllegalStateException("This is not the home page");
        }
    }

    ComposePage newLetter() {
        System.out.println("New letter click");
        driver.findElement(composeButton).click();
        return new ComposePage();
    }

    void logout() throws InterruptedException {
        System.out.println("Log out");
        driver.findElement(logoutButton).click();
        Thread.sleep(2000);
        closeDriver();
    }
}
