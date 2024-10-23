package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='txt-username'] ")
    WebElement username;
    @FindBy(xpath = "//input[@id='txt-password']")
    WebElement password;
    @FindBy(xpath = "//button[@id='btn-login']")
    WebElement loginButton;

    @FindBy(xpath = "//input[@value='John Doe']")
    WebElement demoUsername;
    @FindBy(xpath = "//input[@value='ThisIsNotAPassword']")
    WebElement demoPassword;
    @FindBy(xpath = "//h2[text()='Login']")
    WebElement loginPageHeader;

    public String getDemoUsername() {
        return demoUsername.getAttribute("value");
    }

    public String getDemoPassword() {
        return demoPassword.getAttribute("value");
    }

    public void enterUsername(String uname) {
        username.clear();
        username.sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        password.clear();
        password.sendKeys(pwd);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getLoginPageHeader() {
        return loginPageHeader.getText();
    }
}
