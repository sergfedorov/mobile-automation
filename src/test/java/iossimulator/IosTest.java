package iossimulator;

import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class IosTest {

    DashboardPage dashboardPage;
    NewPlayerPage newPlayerPage;
    SettingsPage settingsPage;

    @BeforeTest
    public void pageObjectsInit() throws MalformedURLException{
        dashboardPage = new DashboardPage();
        newPlayerPage = new NewPlayerPage();
        settingsPage = new SettingsPage();
    }

    @BeforeGroups(groups = "TestData:TwoPlayers")
    public void testDataCreateTwoPlayers(){
        dashboardPage.addPlayer.click();
        newPlayerPage.addUser("Player1");
        dashboardPage.addPlayer.click();
        newPlayerPage.addUser("Player2");
    }

    @AfterGroups(groups = "TestData:TwoPlayers")
    public void testDataDeleteTwoPlayers(){
        dashboardPage.firstPlayerOnBoard.click();
        dashboardPage.userMenuDeletePlayerButton.click();
        dashboardPage.firstPlayerOnBoard.click();
        dashboardPage.userMenuDeletePlayerButton.click();
    }


    /* TESTS */

    @Test(priority = 0)
    public void createPlayerTest(){
        String playerName = "Player1";
        String playerBalance = "1.000";

        dashboardPage.addPlayer.click();
        newPlayerPage.playerNameField.sendKeys(playerName);
        newPlayerPage.playerBalanceField.clear();
        newPlayerPage.playerBalanceField.sendKeys(playerBalance);
        newPlayerPage.categoryItemDog.click();
        newPlayerPage.addPlayerButton.click();

        Assert.assertEquals(dashboardPage.firstPlayerOnBoardName.getText(), playerName);
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardMoneyAmmount.getText(), "$"+playerBalance);
    }

    @Test(priority = 1)
    public void renamePlayerTest(){
        String newPlayerName = "Player Renamed";

        dashboardPage.firstPlayerOnBoard.click();
        dashboardPage.userMenuRenamePlayerButton.click();
        dashboardPage.userMenuRenameField.sendKeys(newPlayerName);
        dashboardPage.userMenuOkButton.click();

        Assert.assertEquals(dashboardPage.firstPlayerOnBoardName.getText(), newPlayerName);
    }

    @Test(priority = 2)
    public void deletePlayerTest() throws InterruptedException {
        int playersNumberBeforeDelete = dashboardPage.playersNumber();

        dashboardPage.firstPlayerOnBoard.click();
        dashboardPage.userMenuDeletePlayerButton.click();
        dashboardPage.waitForDashboardToBeVisible();

        int playersNumberAfterDelete = dashboardPage.playersNumber();

        Assert.assertEquals(playersNumberBeforeDelete-1, playersNumberAfterDelete);
    }

    @Test(priority = 3)
    public void switchSoundToggleTest(){
        dashboardPage.settingsButton.click();
        Assert.assertEquals(settingsPage.getSoundToggleValue(), "1");
        settingsPage.switchSoundToggle();
        Assert.assertEquals(settingsPage.getSoundToggleValue(), "0");
        settingsPage.cancelButton.click();
    }

    @Test(groups="TestData:TwoPlayers", priority = 4)
    public void moneyTransferTest(){
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardMoneyAmmount.getText(), "$1.500");
        dashboardPage.moneyTransfer("500");
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardMoneyAmmount.getText(), "$1.000");
    }

    @Test(groups="TestData:TwoPlayers", priority = 5)
    public void userRearrangeTest(){
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardName.getText(), "Player1");
        dashboardPage.userRearrange();
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardName.getText(), "Player2");
    }





}
