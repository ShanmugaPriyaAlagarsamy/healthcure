package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseclass.BaseClass;
import pageobjects.AppointmentBookingPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utilities.ContextManager;

public class TC003_ToVerifySuccessfulAppointmentBooking extends BaseClass

{

    @Test(dataProvider = "appointmentBookingData", dataProviderClass = utilities.DataProviders.class)
    public void testToVerifySuccessfullBooking(String facility, String healthcareprogram, String visitdate,
            String comment) throws InterruptedException {

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMakeAppointmentButton();

            String currentUrl = driver.getCurrentUrl();

            String loginUrl = "https://katalon-demo-cura.herokuapp.com/profile.php#login";

            if (currentUrl.equalsIgnoreCase(loginUrl) && homePage.getLoginHeader().equalsIgnoreCase("Login")) {
                LoginPage loginPage = new LoginPage(driver);
                loginPage.enterUsername(loginPage.getDemoUsername());
                loginPage.enterPassword(loginPage.getDemoPassword());
                loginPage.clickLoginButton();
            }

            AppointmentBookingPage appointmentBookingPage = new AppointmentBookingPage(driver);
            appointmentBookingPage.selectFacility(facility);
            Thread.sleep(2000);
            appointmentBookingPage.clickReadmissionCheckBox();
            appointmentBookingPage.clickHealthCareProgramOption(healthcareprogram);
            appointmentBookingPage.enterVisitDate(visitdate);
            appointmentBookingPage.enterComment(comment);

            Thread.sleep(7000);

            appointmentBookingPage.clickBookAppointmentButton();
            String actualConfirmationMessage = appointmentBookingPage.getAppointmentConfirmationMessage();

            String expectedConfirmationMessage = "Please be informed that your appointment has been booked as following:";

            Assert.assertEquals(actualConfirmationMessage, expectedConfirmationMessage,
                    "Appointment is not booked successfully");

            appointmentBookingPage.clickGoToHomepage();
            Thread.sleep(2000);

            ContextManager.setMessage("Booking is created successfully with following data:" + "Facility: " + facility
                    + " Healthcare Program: " + healthcareprogram + " Visit Date: " + visitdate + " Comment: "
                    + comment);

        }

        catch (Exception e) {

            Assert.fail();

        }
    }
}