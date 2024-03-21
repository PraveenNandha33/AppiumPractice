package MobileBrowser;

import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileBrowsersTestCase extends Base{
    @Test
    public void testCase(){
        driver.get("https://www.google.com/");
    }

}
