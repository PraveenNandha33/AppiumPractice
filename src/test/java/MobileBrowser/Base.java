package MobileBrowser;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Base {
    public AppiumDriverLocalService service ;
    public UiAutomator2Options uiAutomator2Options;
    public AndroidDriver driver;

    @BeforeClass
    public void before() throws MalformedURLException {
        service=new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Praveennandha\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        //We can get  this ip address locally .and portnumber also
        service.start();
        UiAutomator2Options uiAutomator2Options=new UiAutomator2Options();
        uiAutomator2Options.setDeviceName("PraveensPhone");
        uiAutomator2Options.setCapability("browserName","Chrome");

        uiAutomator2Options.setChromedriverExecutable("C:\\Users\\Praveennandha\\IDEAProjects\\AppiumPractice\\src\\test\\resources\\Drivers\\chromedriver.exe");
        driver=new AndroidDriver(new URL("http://127.0.0.1:4723/"),uiAutomator2Options);

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
        service.stop();
    }
}
