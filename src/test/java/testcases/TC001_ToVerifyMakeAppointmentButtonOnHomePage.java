package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseclass.BaseClass;
import pageobjects.HomePage;
import utilities.ContextManager;

public class TC001_ToVerifyMakeAppointmentButtonOnHomePage extends BaseClass {

    @Test
    public void testToverifymakeAppointmentButton() {

        try {

            HomePage homePage = new HomePage(driver);
            homePage.clickMakeAppointmentButton();

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://katalon-demo-cura.herokuapp.com/profile.php#login";

            Assert.assertEquals(actualUrl, expectedUrl, "makeappointment button is not working");
            ContextManager.setMessage(
                    "makeappointment button is working, Clicking on makeappointment button is redirecting to login page Url: "
                            + actualUrl + "as expected Url: " + expectedUrl);
        }

        catch (Exception e) {

            Assert.fail("Test failed because of exception" + e.getMessage());

        }

    }
}
