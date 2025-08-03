package LoginPageAutomation;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    @BeforeMethod
    public void gotologinpage(){
        // Set preferences to auto-allow notifications
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();

        // 1 = Allow, 2 = Block
        contentSettings.put("notifications", 1); // Allow notifications
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver.get("https://dev-dash.janitri.in/");
        loginPage = new LoginPage(driver);
    }
    @Test
    //Calling the methods
    void Login(){
        loginPage.setUsername("User");
        loginPage.setPassword("Password");
        loginPage.ClickLogin();
        loginPage.setBtn_eyeIcon();
        loginPage.getPasswordFieldType();
        loginPage.errorMessageISDisplayed();
    }

    @Test(priority = 1)
    //Validating the login button
    void testLoginButtonDisabledWhenFieldSAreEmpty() throws InterruptedException {
        loginPage.setUsername(" ");
        loginPage.setPassword(" ");
        Thread.sleep(3000);
        Assert.assertFalse(loginPage.loginButtonIsEnabled(),"Login button should be disabled when fields are empty");
    }

    @Test(priority = 2)
    //Validating the masked eye icon
    void testPasswordMaskedbutton(){
        loginPage.setPassword("Password123");
        Assert.assertEquals(loginPage.getPasswordFieldType(),"password");

        loginPage.togglePasswordVisibility();
        Assert.assertEquals(loginPage.getPasswordFieldType(),"text");
    }

    @Test(priority = 3)
    //Validating whether error message is visible or not visible
    void testInvalidLoginErrMsg(){
        loginPage.setUsername("InavlidCredentials@test.com");
        loginPage.setPassword("WrongPassword");
        loginPage.ClickLogin();

        Assert.assertTrue(loginPage.errorMessageISDisplayed(),"Error Message is displayed");
    }
    @Test(priority = 4)
    //Validating whether page elements present or not
    void testPageElementsPresent(){
        Assert.assertTrue(loginPage.isTitleCorrect(),"Page title is incorrect");
        Assert.assertTrue(loginPage.isUserNameFieldPresent(),"UserName field is not visible");
        Assert.assertTrue(loginPage.isPasswordFieldPresent(),"Password field is not visible");
        Assert.assertTrue(loginPage.isLoginButtonPresent(),"Login button is not present");
        Assert.assertTrue(loginPage.isToggleEyeIconPresent(),"Eye icon is not present");
    }
}
