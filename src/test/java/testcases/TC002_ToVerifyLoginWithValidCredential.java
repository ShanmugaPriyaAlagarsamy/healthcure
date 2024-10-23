package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseclass.BaseClass;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utilities.ContextManager;

public class TC002_ToVerifyLoginWithValidCredential extends BaseClass {

    @Test
    public void loginTestWithValidCredential() {

        try {

            HomePage homePage = new HomePage(driver);
            homePage.clickMakeAppointmentButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterUsername(loginPage.getDemoUsername());
            loginPage.enterPassword(loginPage.getDemoPassword());

            loginPage.clickLoginButton();

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://katalon-demo-cura.herokuapp.com/#appointment";

            Assert.assertEquals(actualUrl, expectedUrl, "Login with valid credential is not working");
            System.out.println(driver.getTitle() + " - Login with valid credential is working");

            ContextManager.setMessage(
                    "Login with valid credential is working, after successful user is redirecting to appointment page Url: "
                            + actualUrl + "as expected");
        }

        catch (Exception e) {

            Assert.fail();
        }

    }

}