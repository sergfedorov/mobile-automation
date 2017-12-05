package iossimulator;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class NewPlayerPage {

    IOSDriver driver;

    public NewPlayerPage() throws MalformedURLException {
        this.driver = Driver.getIOSDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);

    }


    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[1]/XCUIElementTypeTextField[1]")
    IOSElement playerNameField;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[2]/XCUIElementTypeTextField[1]")
    IOSElement playerBalanceField;

    /* Categories */
    @iOSFindBy(id = "cannon_filled")
    IOSElement categoryItemCannon;
    @iOSFindBy(id = "car")
    IOSElement categoryItemCar;
    @iOSFindBy(id = "cat")
    IOSElement categoryItemCat;
    @iOSFindBy(id = "dog")
    IOSElement categoryItemDog;
    @iOSFindBy(id = "hat")
    IOSElement categoryItemHat;
    @iOSFindBy(id = "horse")
    IOSElement categoryItemHorse;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Add Player\"`]")
    IOSElement addPlayerButton;


    public void addUser(String playerName){
        playerNameField.sendKeys(playerName);
        addPlayerButton.click();
    }

}
