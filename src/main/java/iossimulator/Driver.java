package iossimulator;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static IOSDriver<IOSElement> driverInstance;
    private Driver(){}

    public static IOSDriver<IOSElement> getIOSDriver() throws MalformedURLException {
        if(driverInstance == null){
            driverInstance = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilitiesIOS6sBoardBankApp());
            driverInstance.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
        return driverInstance;
    }


    private static DesiredCapabilities getCapabilitiesIOS6sBoardBankApp(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 6s");
        capabilities.setCapability("platformVersion", "11.1");
        capabilities.setCapability(  "app",
                "/Users/sergeyfedorov/Documents/BoardBank/Build/Products/Debug-iphonesimulator/BoardBank.app");
        capabilities.setCapability(  "automationName", "XCUITest");
        return capabilities;

    }

}
