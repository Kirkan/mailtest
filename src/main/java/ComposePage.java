import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class ComposePage extends BasePage{
    private By toField = By.xpath("//textarea[@data-original-name='To']");
    private By subjectField = By.xpath("//input[@name='Subject']");
    private By frameLocator = By.xpath("//iframe[starts-with(@id,'toolkit')]");
    private By bodyField = By.xpath("//body[starts-with(@id,'tinymce')]");
    private By sendButton = By.xpath("//div[@data-name='send']");
    private By isSuccess = By.xpath("//div[@id='b-compose__sent']");

    ComposePage() {
        System.out.println("Compose page");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(toField));
        }catch(Exception e){
            throw new IllegalStateException("This is not the compose page");
        }
    }

    private ComposePage typeTo(String to) {
        System.out.println("Type: To " + to);
        driver.switchTo().defaultContent();
        driver.findElement(toField).sendKeys(to);
        return this;
    }

    private ComposePage typeSubject(String subject) {
        System.out.println("Type: Subject " + subject);
        driver.switchTo().defaultContent();
        driver.findElement(subjectField).sendKeys(subject);
        return this;
    }

    private ComposePage typeBody(String body) {
        System.out.println("Type: Body " + body);
        driver.switchTo().frame(driver.findElement(frameLocator));
        driver.findElement(bodyField).sendKeys(body);
        return this;
    }

    private SendSuccessfulPage submitMessage() {
        System.out.println("Submit message");
        driver.switchTo().defaultContent();
        driver.findElement(sendButton).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(isSuccess));
        }catch(Exception e){
            throw new IllegalStateException("Send failed");
        }
        return new SendSuccessfulPage();
    }

    SendSuccessfulPage newMessage(String to, String subject, String body) {
        typeTo(to);
        typeSubject(subject);
        typeBody(body);
        return submitMessage();
    }
}
