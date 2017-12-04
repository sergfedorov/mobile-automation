import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FirstTestIos {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("platformVersion", "11.1");
        capabilities.setCapability(  "app",
                "/Users/sergeyfedorov/Documents/BoardBank/Build/Products/Debug-iphonesimulator/BoardBank.app");
        capabilities.setCapability(  "automationName", "XCUITest");


        WebDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //WebDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        driver.findElement(MobileBy.name("Add")).click();
        MobileElement playerName1 = driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeTextField[1]"));
        playerName1.setValue("Player1");
        driver.findElement(By.name("cat")).click();
        driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeCell[4]")).click();

        driver.findElement(MobileBy.name("Add")).click();
        MobileElement playerName2 = driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeTextField[1]"));
        playerName2.setValue("Player1");
        driver.findElement(By.name("cat")).click();
        driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeCell[4]")).click();


        //driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Player']")).click();
        //driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Delete']")).click();

        WebElement player1 = driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeCell[2]"));
        WebElement player2 = driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeCell[3]"));

        TouchAction action = new TouchAction((MobileDriver) driver);
        action.press(player1).waitAction(Duration.ofMillis(250)).moveTo(player2).release().perform();



        //MobileElement playerName123 = ((MobileDriver) driver).findElementByXPath()

    }


}

