package androidemulator;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static AndroidDriver<AndroidElement> driverInstance;
    private Driver(){}

    public static AndroidDriver<AndroidElement> getAndroidDriver() throws MalformedURLException {
        if(driverInstance == null){
            driverInstance = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),
                    getCapabilitiesAndroidEmulatorBuzzfeedApp());
            driverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driverInstance;
    }

    private static DesiredCapabilities getCapabilitiesAndroidEmulatorBuzzfeedApp(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "dev123");
        capabilities.setCapability(  "appPackage","com.buzzfeed.android");
        capabilities.setCapability(  "appActivity", "com.buzzfeed.android.activity.PagerActivity");
        return capabilities;

    }

}
