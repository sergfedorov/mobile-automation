package iossimulator;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class DashboardPage {

    IOSDriver<IOSElement> driver;

    public DashboardPage() throws MalformedURLException {
        this.driver = Driver.getIOSDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
    }


    /***** Top Bar *****/
    //@iOSFindBy(id = "Add")
    /* Using iOS Predicate*/
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name == 'Add'")
    IOSElement addPlayer;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeNavigationBar[`name==\"BoardBank\"`]/XCUIElementTypeButton[1]")
    IOSElement settingsButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name==\"BoardBank\"`]")
    IOSElement dashBoardLabel;


    /***** Dashboard *****/
    //@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[1]")
    //@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Bank\"`]")
    @iOSFindBy(id = "Bank")
    IOSElement bankOnBoard;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    IOSElement playerContainer;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[2]")
    IOSElement firstPlayerOnBoard;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[2]/*/XCUIElementTypeStaticText[1]")
    IOSElement firstPlayerOnBoardName;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[2]/*/XCUIElementTypeStaticText[2]")
    IOSElement firstPlayerOnBoardMoneyAmmount;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[3]")
    IOSElement secondPlayerOnBoard;


    /***** User Menu *****/
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
    @iOSFindBy(id = "OK")
    IOSElement userMenuOkButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
    IOSElement userMenuRenameField;


    /***** Transfer Money dialog *****/
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
    IOSElement transferMoneyField;
    @iOSFindBy(id = "OK")
    IOSElement transferMoneyDialogOkButton;
    @iOSFindBy(id = "Cancel")
    IOSElement transferMoneyDialogCancelButton;


    public void moneyTransfer(String playerFrom, String playerTo, String money){
        TouchAction action = new TouchAction(driver);
        action.
                press(getPlayerByName(playerFrom)).
                waitAction(Duration.ofMillis(250)).
                moveTo(getPlayerByName(playerTo)).
                release().perform();
        transferMoneyField.sendKeys(money);
        transferMoneyDialogOkButton.click();
    }


    public void userRearrange(String playerFrom, String playerTo){
        TouchAction action = new TouchAction(driver);
        action.
                press(getPlayerByName(playerFrom)).
                waitAction(Duration.ofMillis(500)).
                moveTo(getPlayerByName(playerTo)).
                release().perform();
        /* Does not work with longPress for some reason */
        /* appium Original error: -[NSNull doubleValue]: unrecognized selector sent to instance */
        //action.longPress(firstPlayerOnBoard).moveTo(secondPlayerOnBoard).release().perform();
    }


    /* Using XCTest*/
    public void tapAddPlayerUsingXCTest(){
        JavascriptExecutor js = driver;
        Map<String, Object> params = new HashMap<>();
        params.put("x", 330);
        params.put("y", 20);
        js.executeScript("mobile: tap", params);
    }


    public int playersCounter(){
        waitForDashboardToBeVisible();
        List<IOSElement> players = driver.findElementsByIosClassChain("**/XCUIElementTypeCell");
        return players.size() - 1;
    }


    public void waitForDashboardToBeVisible(){
        (new WebDriverWait(driver, 5)).
                until(ExpectedConditions.attributeContains(bankOnBoard, "visible", "true"));
    }


    public IOSElement getPlayerByName(String playerName){
        String string = String.format("**/XCUIElementTypeStaticText[`name==\"%s\"`]", playerName);
        return driver.findElementByIosClassChain(string);
    }


    public void deleteAllPlayers(int playersNumber){
        for(int i=0; i<playersNumber; i++){
            waitForDashboardToBeVisible();
            firstPlayerOnBoard.click();
            userMenuDeletePlayerButton.click();
        }
    }

    public boolean isPlayerPresent(String playerName){
        String string = String.format("**/XCUIElementTypeStaticText[`name==\"%s\"`]", playerName);
        return !driver.findElementsByIosClassChain(string).isEmpty();
    }

}
