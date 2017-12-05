package iossimulator;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class SettingsPage {

    IOSDriver driver;

    public SettingsPage() throws MalformedURLException {
        this.driver = Driver.getIOSDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);

    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`name=\"ENABLE SOUNDS\"`]")
    private IOSElement soundToggle;

    @iOSFindBy(id = "Cancel")
    IOSElement cancelButton;


    public void switchSoundToggle(){
        soundToggle.click();

        /* Tap using TouchAction */
        /*TouchAction action = new TouchAction(driver);
        action.tap(soundToggle).perform();*/
    }

    public String getSoundToggleValue(){
        return soundToggle.getAttribute("value");
    }



}
