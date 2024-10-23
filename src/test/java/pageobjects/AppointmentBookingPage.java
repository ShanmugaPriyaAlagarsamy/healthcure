package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AppointmentBookingPage {

    WebDriver driver;

    public AppointmentBookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@name='facility']")
    WebElement facilityDropDown;
    @FindBy(xpath = "//input[@id='chk_hospotal_readmission']")
    WebElement readmissionCheckBox;
    @FindBy(xpath = "//input[@id='radio_program_medicare']")
    WebElement healthCareProgramOptionMedicare;
    @FindBy(xpath = "//input[@id='radio_program_medicaid']")
    WebElement healthCareProgramOptionMedicaid;
    @FindBy(xpath = "//input[@id='radio_program_none']")
    WebElement healthCareProgramOptionNone;
    @FindBy(xpath = "//input[@id='txt_visit_date']")
    WebElement visitDate;
    @FindBy(xpath = "//textarea[@id='txt_comment']")
    WebElement comment;
    @FindBy(xpath = "//button[@id='btn-book-appointment']")
    WebElement bookAppointmentButton;
    @FindBy(xpath = "//p[@class='lead']")
    WebElement appointmentConfirmationMessage;
    @FindBy(xpath = "//a[text()='Go to Homepage']")
    WebElement goToHomepage;

    public void selectFacility(String facility) {
        Select facilityDropdown = new Select(facilityDropDown);

        facilityDropdown.selectByVisibleText(facility);
    }

    public void clickReadmissionCheckBox() {
        readmissionCheckBox.click();
    }

    public void clickHealthCareProgramOption(String option) {

        if (option.equalsIgnoreCase("Medicare"))
            healthCareProgramOptionMedicare.click();

        else if (option.equalsIgnoreCase("Medicaid"))
            healthCareProgramOptionMedicaid.click();

        else if (option.equalsIgnoreCase("None"))
            healthCareProgramOptionNone.click();

    }

    public void enterVisitDate(String date) {
        visitDate.sendKeys(date);
    }

    public void enterComment(String commentText) {
        comment.sendKeys(commentText);
    }

    public void clickBookAppointmentButton() {
        bookAppointmentButton.click();
    }

    public String getAppointmentConfirmationMessage() {
        return appointmentConfirmationMessage.getText();
    }

    public void clickGoToHomepage() {
        goToHomepage.click();
    }

}
