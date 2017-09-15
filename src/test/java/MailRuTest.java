import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MailRuTest {
    private static LoginPage loginPage;
    private static HomePage homePage;
    private static ComposePage newLetterPage;
    private static SendSuccessfulPage sendSuccessfulPage;

    @Test
    public void a_login() throws InterruptedException {
        try {
            loginPage = new LoginPage();
            homePage = loginPage.loginAs("john", "superhugepassword");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertNotNull(homePage);
        }
    }

    @Test
    public void b_Compose() throws InterruptedException {
        Assert.assertNotNull(homePage);
        try {
            newLetterPage = homePage.newLetter();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertNotNull(newLetterPage);
        }
    }

    @Test
    public void c_makeLetter() throws InterruptedException {
        Assert.assertNotNull(newLetterPage);
        try {
            sendSuccessfulPage = newLetterPage.newMessage("v@kreml.com", "Проверка Selenium", "Test Selenium!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertNotNull(sendSuccessfulPage);
        }
    }

    @Test
    public void d_logout() throws InterruptedException {
        Assert.assertNotNull(homePage);
        try {
            homePage.logout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
