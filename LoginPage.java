package LoginPageAutomation;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    //Constructor
    LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //Locators
    By txt_username = By.xpath(" //input[@id='formEmail']");   //locate the username field
    By txt_password = By.xpath("//input[@id='formPassword']");  //locate the password field
    By btn_login = By.xpath("//button[@type='submit']");    //locate the login button
    By btn_eyeIcon = By.xpath("//img[@class='passowrd-visible' and @alt='Password Not Visible']");  //locate the eye icon
    By errorMsg = By.xpath("//div[@class='invalid-credential-div']");   //locate the invalid credentials warning

    //Action Methods
    //Method to clear and send keys to username field
    public void setUsername(String user){
        WebElement userName = driver.findElement(txt_username);
        userName.clear();
        userName.sendKeys(user);
    }
    //Method to clear and send keys to password field
    public void setPassword(String pwd){
        WebElement password = driver.findElement(txt_password);
        password.clear();
        password.sendKeys(pwd);
    }
    //Click on the login button
    public void ClickLogin() {
        driver.findElement(btn_login).click();
    }
    //click on the eye icon
    public void setBtn_eyeIcon(){
        driver.findElement(btn_eyeIcon);
    }
    //check whether login button is enabled or disabled
    public boolean loginButtonIsEnabled(){
        return driver.findElement(btn_login).isEnabled();
    }
    //Check the data visibility entered into password field
    public String getPasswordFieldType(){
        return driver.findElement(txt_password).getAttribute("type");
    }
    //Check the toggle password visibility
    public void togglePasswordVisibility(){
        driver.findElement(btn_eyeIcon).click();
    }
    //Check the error message visibilty
    public boolean errorMessageISDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
            return errorElement.isDisplayed();
    }
    //Check whether title page is correct
    public boolean isTitleCorrect(){
        String expTitle = "Janitri";
        return driver.getTitle().equals(expTitle);
    }
    //Check whether the username field is present or not
    public boolean isUserNameFieldPresent(){
        return driver.findElement(txt_username).isDisplayed();
    }
    //Check whether the password field is present or not
    public boolean isPasswordFieldPresent(){
        return driver.findElement(txt_password).isDisplayed();
    }
    //Check whether login button is present or not
    public boolean isLoginButtonPresent(){
        return driver.findElement(btn_login).isDisplayed();
    }
    //Check whether eye icon is present or not
    public boolean isToggleEyeIconPresent(){
        return driver.findElement(btn_eyeIcon).isDisplayed();
    }
}
