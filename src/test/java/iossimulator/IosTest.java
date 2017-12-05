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

    @BeforeGroups(groups = "TestData:CreateFourPlayers")
    public void testDataCreateFourPlayers(){
        dashboardPage.tapAddPlayerUsingXCTest();
        newPlayerPage.addUser("Player1");
        dashboardPage.addPlayer.click();
        newPlayerPage.addUser("Player2");
        dashboardPage.addPlayer.click();
        newPlayerPage.addUser("PlayerForRename");
        dashboardPage.addPlayer.click();
        newPlayerPage.addUser("PlayerForDelete");
    }

    @AfterTest
    public void deleteAllPlayers(){
        dashboardPage.deleteAllPlayers(dashboardPage.playersCounter());
    }


    /***** TESTS *****/

    @Test(priority = 0)
    public void createPlayerTest(){
        dashboardPage.addPlayer.click();
        newPlayerPage.playerNameField.sendKeys("PlayerCreatedByTest");
        newPlayerPage.playerBalanceField.clear();
        newPlayerPage.playerBalanceField.sendKeys("1.000");
        newPlayerPage.categoryItemDog.click();
        newPlayerPage.addPlayerButton.click();

        Assert.assertEquals(dashboardPage.firstPlayerOnBoardName.getText(), "PlayerCreatedByTest");
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardMoneyAmmount.getText(), "$1.000");

        dashboardPage.deleteAllPlayers(dashboardPage.playersCounter());
    }

    @Test
    public void switchSoundToggleTest(){
        dashboardPage.settingsButton.click();
        Assert.assertEquals(settingsPage.getSoundToggleValue(), "1");
        settingsPage.switchSoundToggle();
        Assert.assertEquals(settingsPage.getSoundToggleValue(), "0");
        settingsPage.cancelButton.click();
    }

    @Test(groups = "TestData:CreateFourPlayers")
    public void renamePlayerTest() {
        dashboardPage.getPlayerByName("PlayerForRename").click();
        dashboardPage.userMenuRenamePlayerButton.click();
        dashboardPage.userMenuRenameField.sendKeys("RenamedPlayer");
        dashboardPage.userMenuOkButton.click();
        dashboardPage.waitForDashboardToBeVisible();

        Assert.assertEquals(dashboardPage.isPlayerPresent("PlayerForRename"), false);
        Assert.assertEquals(dashboardPage.getPlayerByName("RenamedPlayer").getText(), "RenamedPlayer");
    }

    @Test(groups = "TestData:CreateFourPlayers")
    public void deletePlayerTest() {
        int playersNumberBeforeDelete = dashboardPage.playersCounter();

        dashboardPage.getPlayerByName("PlayerForDelete").click();
        dashboardPage.userMenuDeletePlayerButton.click();

        int playersNumberAfterDelete = dashboardPage.playersCounter();

        Assert.assertEquals(dashboardPage.isPlayerPresent("PlayerForDelete"), false);
        Assert.assertEquals(playersNumberBeforeDelete-1, playersNumberAfterDelete);
    }

    @Test(groups = "TestData:CreateFourPlayers")
    public void moneyTransferTest(){
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardMoneyAmmount.getText(), "$1.500");
        dashboardPage.moneyTransfer("Player1", "Player2", "500");
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardMoneyAmmount.getText(), "$1.000");
    }

    @Test(groups = "TestData:CreateFourPlayers")
    public void userRearrangeTest(){
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardName.getText(), "Player1");
        dashboardPage.userRearrange("Player1", "Player2");
        Assert.assertEquals(dashboardPage.firstPlayerOnBoardName.getText(), "Player2");
    }





}
