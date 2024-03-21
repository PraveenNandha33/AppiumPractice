package EcommerceAPP;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class EcommerceTestBase {
    AndroidDriver driver;
    AppiumDriverLocalService service;
    JavascriptExecutor javascriptExecutor;

    @BeforeClass(groups = "Ecommerce")
    public void Before() throws MalformedURLException {
        //toStarttheServerprogrammatically
        service=new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Praveennandha\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        //We can get  this ip address locally .and portnumber also
        service.start();
        UiAutomator2Options uiAutomator2Options=new UiAutomator2Options();
        uiAutomator2Options.setDeviceName("PraveensPhone");
        uiAutomator2Options.setChromedriverExecutable("C:\\Users\\Praveennandha\\IDEAProjects\\AppiumPractice\\src\\test\\resources\\Drivers\\chromedriver.exe");
        uiAutomator2Options.setApp("C:\\Users\\Praveennandha\\IDEAProjects\\AppiumPractice\\src\\test\\resources\\General-Store.apk");
        driver=new AndroidDriver(new URL("http://127.0.0.1:4723/"),uiAutomator2Options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        javascriptExecutor=(JavascriptExecutor) driver;
    }

    @AfterClass
    public void after() {
        driver.quit();
        service.stop();
    }
}
