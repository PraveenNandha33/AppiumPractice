package Learning;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase {
    @Test
    public void changeWifiName() {


        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String popUpTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(popUpTitle, "WiFi settings");
        driver.findElement(By.id("android:id/edit")).sendKeys("NewWifiName");
        driver.findElement(By.id("android:id/button1")).click();

    }

    @Test
    public void LongPressGesture() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement peopleNameElement = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        javascriptExecutor.executeScript("mobile:longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) peopleNameElement).getId(),
                        "duration", 2000));
        String menuTitle = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(menuTitle, "Sample menu");
        Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
    }

    //where to scroll is known prior
    @Test
    public void scrollGestureUsingUIAutomator() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));
        Thread.sleep(2000);
    }

    //Where to scroll is not known clearly
    @Test
    public void scrollGestureUsingJSE() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        boolean canScrollMore;
        do {
            canScrollMore = (boolean) javascriptExecutor.executeScript("mobile: scrollGesture",
                    ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
        } while (canScrollMore);
    }


    @Test
    public void swipeGesture() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement image1 = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(image1.getAttribute("focusable"), "true");
        javascriptExecutor.executeScript("mobile: swipeGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) image1).getId(),
                        "direction", "left",
                        "percent", 0.1
                ));
        Assert.assertEquals(image1.getAttribute("focusable"), "false");
        Thread.sleep(3000);
        WebElement image2 = driver.findElement(By.xpath("(//android.widget.ImageView)[2]"));
        javascriptExecutor.executeScript("mobile: swipeGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) image2).getId(),
                        "direction", "right",
                        "percent", 0.75
                ));
        Thread.sleep(3000);
    }

    @Test
    public void dragAndDropGesture() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement sourceElement = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        javascriptExecutor.executeScript("mobile: dragGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) sourceElement).getId(),
                        "endX", 476,
                        "endY", 422));
        Thread.sleep(3000);
    }

    @Test
    public void assignment() {
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();

        driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with a message")).click();
        driver.findElement(By.id("android:id/button1")).click();

        driver.findElement(By.id("io.appium.android.apis:id/radio_button")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Traffic']")).click();
        driver.findElement(By.id("android:id/button1")).click();


        driver.findElement(AppiumBy.accessibilityId("Text Entry dialog")).click();
        driver.findElement(By.id("io.appium.android.apis:id/username_edit")).sendKeys("Praveen");
        driver.findElement(By.id("io.appium.android.apis:id/password_edit")).sendKeys("HJello");
        driver.findElement(By.id("android:id/button1")).click();

        driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with traditional theme")).click();
        driver.findElement(By.id("android:id/button1")).click();

        driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with Holo Light theme")).click();
        driver.findElement(By.id("android:id/button2")).click();

        driver.findElement(AppiumBy.accessibilityId("Repeat alarm")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Every Wednesday']")).click();
        driver.findElement(By.id("android:id/button1")).click();

        driver.findElement(AppiumBy.accessibilityId("Repeat alarm")).click();
        for (int i = 1; i <= 7; i++) {
            driver.findElement(By.xpath("(//android.widget.CheckedTextView[@resource-id='android:id/text1'])" + "[" + i + "]")).click();
        }
        driver.findElement(By.id("android:id/button1")).click();

    }


    @Test
    public void rotateDeviceAndCopyPasting() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        DeviceRotation landScape=new DeviceRotation(0,0,90);
        driver.rotate(landScape);
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String popUpTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(popUpTitle, "WiFi settings");
        driver.setClipboardText("WifiName");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(3000);
        driver.findElement(By.id("android:id/button1")).click();

    }

    @Test
    public void appiumActivity(){

        javascriptExecutor.executeScript("mobile: startActivity",ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String popUpTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        driver.findElement(By.id("android:id/edit")).sendKeys("HelloAp");
        driver.findElement(By.id("android:id/button1")).click();


    }

}
