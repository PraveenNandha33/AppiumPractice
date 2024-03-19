package Learning;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBase {
    AndroidDriver driver;
    AppiumDriverLocalService service;
    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void Before() throws MalformedURLException {
        //toStarttheServerprogrammatically
        service=new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Praveennandha\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        //We can get  this ip address locally .and portnumber also
        service.start();
        UiAutomator2Options uiAutomator2Options=new UiAutomator2Options();
        uiAutomator2Options.setDeviceName("PraveensPhone");
        uiAutomator2Options.setApp("C:\\Users\\Praveennandha\\IDEAProjects\\AppiumPractice\\src\\test\\resources\\ApiDemos-debug.apk");
        driver=new AndroidDriver(new URL("http://127.0.0.1:4723/"),uiAutomator2Options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        javascriptExecutor=(JavascriptExecutor) driver;
    }

    @AfterClass
    public void after() throws InterruptedException {
        driver.quit();
        service.stop();
        System.out.println("KII");
    }
}
