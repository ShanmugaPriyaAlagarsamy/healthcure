package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@id='btn-make-appointment']")
    WebElement makeAppointmentButton;

    @FindBy(xpath = "//a[@id='btn-login']")
    WebElement loginButton;

    @FindBy(xpath = "//a[@id='btn-logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//h2[text()='Login']")
    WebElement loginHeader;

    public void clickMakeAppointmentButton() {
        makeAppointmentButton.click();
    }

    public String getLoginHeader() {
        return loginHeader.getText();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

}
