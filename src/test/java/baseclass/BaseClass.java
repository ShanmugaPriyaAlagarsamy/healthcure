package baseclass;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.time.Duration;
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;

public class BaseClass {

    public static WebDriver driver;
    Properties prop;

    @BeforeClass
    @Parameters("browser")
    public void setup(String br) throws IOException {

        FileReader propfile = new FileReader(
                System.getProperty("user.dir") + "//src//main//resources//configuration.properties");
        prop = new Properties();
        prop.load(propfile);

        switch (br.toLowerCase()) {

            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("No browser is defined in xml file");
                return;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("applicationUrl"));

    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    public String takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "//Screenshots// " + testName + "_failurescreenshot"
                + ".png";
        File targetFile = new File(targetFilePath);
        screenshotFile.renameTo(targetFile);
        return targetFilePath;
    }

}