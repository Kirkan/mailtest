import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SendSuccessfulPage extends BasePage {
    private By isSuccess = By.xpath("//div[@id='b-compose__sent']");

    SendSuccessfulPage() {
        System.out.println("Send successful page");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(isSuccess));
        }catch(Exception e){
            throw new IllegalStateException("This is not the successful send page");
        }
    }

}
