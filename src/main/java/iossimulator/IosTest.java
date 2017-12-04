package iossimulator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class IosTest {

    DashboardPage dashboardPage;
    NewPlayerPage newPlayerPage;
    SettingsPage settingsPage;

    @BeforeClass
    public void PageObjectsInit() throws MalformedURLException{
        dashboardPage = new DashboardPage();
        newPlayerPage = new NewPlayerPage();
        settingsPage = new SettingsPage();

        dashboardPage.tapAddPlayer();
        newPlayerPage.addUser("Player1");
        dashboardPage.tapAddPlayer();
        newPlayerPage.addUser("Player2");
    }

    @Test
    public void moneyTransferTest(){
        Assert.assertEquals(dashboardPage.getPlayerMoneyAmount(), "$1.500");
        dashboardPage.moneyTransfer("500");
        Assert.assertEquals(dashboardPage.getPlayerMoneyAmount(), "$1.000");
    }

    @Test
    public void userRearrangeTest(){
        Assert.assertEquals(dashboardPage.getFirstPlayerName(), "Player1");
        dashboardPage.userRearrange();
        Assert.assertEquals(dashboardPage.getFirstPlayerName(), "Player2");
    }

    @Test
    public void switchSoundToggleTest(){
        Assert.assertEquals(settingsPage.getSoundToggleValue(), "1");
        dashboardPage.tapSettingsButton();
        settingsPage.switchSoundToggle();
        Assert.assertEquals(settingsPage.getSoundToggleValue(), "0");
    }

}
