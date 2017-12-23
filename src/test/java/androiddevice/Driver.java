package androiddevice;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static AndroidDriver<AndroidElement> driverInstance;
    private Driver(){}

    public static AndroidDriver<AndroidElement> getAndroidDriver() {
        if(driverInstance == null){
            try {
                driverInstance = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
                        getCapabilitiesAndroidDeviceAliexpressApp());

            /*driverInstance = new AndroidDriver<>(new URL("https://eu1.appium.testobject.com/wd/hub"),
                    getCapabilitiesAndroidDeviceAliexpressAppSauseLabs());*/

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            driverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driverInstance;
    }


    public static void quitAndroidDriver(){
        if(driverInstance != null){
            getAndroidDriver().quit();
        }
    }

    public static void startAliexpressApp(){
        if(!driverInstance.currentActivity().contains("home.MainActivity")) {
            driverInstance.launchApp();
        }
    }

    public static void startBuzzfeedApp(){
            driverInstance.launchApp();
    }

    public static void closeApp(){
        driverInstance.closeApp();
    }

    public static void resetApp(){
        driverInstance.closeApp();
        driverInstance.launchApp();
    }


    private static DesiredCapabilities getCapabilitiesAndroidDeviceAliexpressApp(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Galaxy S5");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability(  "appPackage","com.alibaba.aliexpresshd");
        capabilities.setCapability(  "appActivity", "com.alibaba.aliexpresshd.module.home.MainActivity");
        return capabilities;

    }


    public static AndroidDriver<AndroidElement> getAndroidEmulatorDriver() {
        if(driverInstance == null){
            try {
                driverInstance = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
                        getCapabilitiesAndroidEmulatorBuzzfeedApp());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            driverInstance.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

    private static DesiredCapabilities getCapabilitiesAndroidDeviceAliexpressAppSauseLabs(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("deviceName", "LG Nexus 5X Free");
        capabilities.setCapability(  "appPackage","com.alibaba.aliexpresshd");
        capabilities.setCapability(  "appActivity", "com.alibaba.aliexpresshd.module.home.MainActivity");
        capabilities.setCapability(  "testobject_api_key", "3E123CC9AD3940339C33724CA98E8F01");
        capabilities.setCapability(  "phoneOnly", "false");
        capabilities.setCapability(  "tabletOnly", "false");
        capabilities.setCapability(  "privateDevicesOnly", "false");
        return capabilities;
    }

}
