package EcommerceAPP;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EcommerceTestCases extends EcommerceTestBase{

    @Test(groups = "Ecommerce")
    public void test(){

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Praveen");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Cuba\"));"));
        WebElement countyrName=driver.findElement(By.xpath("//android.widget.TextView[@text='Cuba']"));
        countyrName.click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

    }

    @Test(groups = "Ecommerce")
    public void errorMessageTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String ToastMessage=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(ToastMessage,"Please enter your name");
    }

    @Test(groups = "Ecommerce")
    public void testCartTotalFunc() throws InterruptedException {
        String count;
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Praveen");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        //AddingFirstTwoItems in Car
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
       //Validating the cart number
         count=driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getAttribute("text");
         Assert.assertEquals(count,"1");
         driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD TO CART\"))"));
         WebElement secondAddtoCart=driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0);
        secondAddtoCart.click();
        count=driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();

        Assert.assertEquals(count,"2");

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);

        List<WebElement> prodPrice=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        double sum=0;
        for(WebElement e:prodPrice)
        {
            sum+=Double.parseDouble((e.getText()).substring(1));
        }
        WebElement totalAmount=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));
        Double totalSum=Double.parseDouble((totalAmount.getText()).substring(1));
        Assert.assertEquals(totalSum,sum);
    }

    @Test(groups = "Ecommerce")
    public void longPress() throws InterruptedException {
        String count;
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Praveen");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        //AddingFirstTwoItems in Car
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        //Validating the cart number
        count=driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getAttribute("text");
        Assert.assertEquals(count,"1");
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD TO CART\"))"));
        WebElement secondAddtoCart=driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0);
        secondAddtoCart.click();
        count=driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();

        Assert.assertEquals(count,"2");

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
         WebElement termsandconditions=driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
         javascriptExecutor.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)termsandconditions),"Duration",2000));

         Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText(),"Terms Of Conditions");
         driver.findElement(By.id("android:id/button1")).click();


    }

    @Test(groups = "Ecommerce")
    public void purchaseHappyPath() throws InterruptedException {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Praveen");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        //AddingFirstTwoItems in Car
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        //Validating the cart number
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD TO CART\"))"));
        WebElement secondAddtoCart=driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0);
        secondAddtoCart.click();

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
        WebElement termsandconditions=driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        javascriptExecutor.executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((RemoteWebElement)termsandconditions),"Duration",2000));

        Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText(),"Terms Of Conditions");
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.xpath("//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(5000);
        //To get the native and WebView names.This may vary from dev to dev
        System.out.println(driver.getContextHandles());
        driver.context("WEBVIEW_com.androidsample.generalstore");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Hello");
        Thread.sleep(10000);


    }


    }
