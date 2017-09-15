import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class BasePage {
    static WebDriver driver;

    BasePage() {
        if(driver == null) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
            System.out.println("Load Driver...");
        }
    }

    void closeDriver(){
        if (driver != null) {
            driver.quit();
            System.out.println("Close Driver");
        }
    }
}
