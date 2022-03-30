package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {
    static WebDriver driver;
    ExtentHtmlReporter reporter;
    ExtentReports extent;
    ExtentTest logger;

    //This functions set the test environmen
    @Parameters({"browser"})
    @BeforeSuite
    void setupTheEnvironment(String browser) {
        if (browser.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.contains("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\resources\\drivers\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        //Maximizing the window of the driver if not maximized
        driver.manage().window().maximize();

        //Building the report configurations
        String reportDir = System.getProperty("user.dir") + "\\Reports\\";
        reporter = new ExtentHtmlReporter(reportDir + "\\\\"
                + this.getClass().getSimpleName() + "_" + browser + System.currentTimeMillis() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    //This function is called before every test
    @BeforeMethod
    public void testLog(Method method) {
        logger = extent.createTest(method.getName());
        driver.navigate().to("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

    //This function is used to log the cases of failed test cases in the report and its relevant screenshot
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = "";
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File photo = takesScreenshot.getScreenshotAs(OutputType.FILE);
            try {
                path = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName()
                        + System.currentTimeMillis() + ".jpg";
                FileUtils.copyFile(photo, new File(path));
            } catch (IOException e) {
                System.out.println("Can't Take The Screenshot of " + result.getName());
            }

            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder
                    .createScreenCaptureFromPath(path).build());

        }
        extent.flush();
    }

    //This function is called after the suite
    @AfterSuite
    public void closeBrowser() {
        //This function close any instance of the WebDriver
        driver.quit();
    }
}