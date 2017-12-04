package iossimulator;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class DashboardPage {

    private int player;

    IOSDriver<IOSElement> driver;
    public DashboardPage() throws MalformedURLException {
        this.driver = Driver.getIOSDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
    }

    /* Top Bar */
    //@iOSFindBy(id = "Add")
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name == 'Add'")
    IOSElement addPlayer;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeNavigationBar[`name==\"BoardBank\"`]/XCUIElementTypeButton[1]")
    IOSElement settingsButton;

    /* Dashboard */
    //@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[1]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Bank\"`]")
    IOSElement bankOnBoard;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[2]")
    IOSElement firstPlayerOnBoard;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[2]/*/XCUIElementTypeStaticText[1]")
    IOSElement firstPlayerOnBoardName;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[2]/*/XCUIElementTypeStaticText[2]")
    IOSElement firstPlayerOnBoardMoneyAmmount;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[3]")
    IOSElement secondPlayerOnBoard;

    /* User Menu */
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[1]")
    IOSElement userMenuUserNameAndBalance;
    @iOSFindBy(id = "Add $200")
    IOSElement userMenuAddMoneyButton;
    @iOSFindBy(id = "Rename")
    IOSElement userMenuRenamePlayerButton;
    @iOSFindBy(id = "Delete")
    IOSElement userMenuDeletePlayerButton;
    @iOSFindBy(id = "Cancel")
    IOSElement userMenuCancelButton;

    /* Transfer Money dialog */
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
    IOSElement transferMoneyField;
    @iOSFindBy(id = "OK")
    IOSElement transferMoneyDialogOkButton;
    @iOSFindBy(id = "Cancel")
    IOSElement transferMoneyDialogCancelButton;



    public void tapAddPlayer(){
        addPlayer.click();
    }

    public void tapSettingsButton(){
        settingsButton.click();
    }

    public void moneyTransfer(String money){
        TouchAction action = new TouchAction(driver);
        action.press(firstPlayerOnBoard).waitAction(Duration.ofMillis(250)).moveTo(secondPlayerOnBoard).release().perform();
        transferMoneyField.sendKeys(money);
        transferMoneyDialogOkButton.click();
    }

    public void userRearrange(){
        TouchAction action = new TouchAction(driver);
        action.press(firstPlayerOnBoard).waitAction(Duration.ofMillis(500)).moveTo(secondPlayerOnBoard).release().perform();
        /* Does not work with longPress for some reason */
        /* appium Original error: -[NSNull doubleValue]: unrecognized selector sent to instance */
        //action.longPress(firstPlayerOnBoard).moveTo(secondPlayerOnBoard).release().perform();
    }

    public String getPlayerMoneyAmount(){
        return firstPlayerOnBoardMoneyAmmount.getText();
    }

    public String getFirstPlayerName(){
        return firstPlayerOnBoardName.getText();
    }

    public void tapAddPlayerUsingXCTest(){
        JavascriptExecutor js = driver;
        Map<String, Object> params = new HashMap<>();
        params.put("x", 330);
        params.put("y", 20);
        js.executeScript("mobile: tap", params);
    }





}
