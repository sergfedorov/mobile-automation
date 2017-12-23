package androidemulator;

import androiddevice.Driver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AndroidEmulatorTest {

    BuzzfeedPage buzzfeedPage;

    @BeforeTest
    public void pageObjectsInit(){
        buzzfeedPage = new BuzzfeedPage();
    }

    @AfterTest
    public void quitDriver(){
        Driver.quitAndroidDriver();
    }

    @BeforeMethod
    public void launch(){
        Driver.startBuzzfeedApp();
    }

    @AfterMethod
    public void stop() {
        Driver.closeApp();
    }


    /***** TESTS*****/

    @Test
    public void searchTest() {

        // Swipe test
        buzzfeedPage.swipeToTrending();
        Assert.assertEquals(buzzfeedPage.trendingTab.isSelected(), true);

        // Results present test
        buzzfeedPage.search("Test");
        Assert.assertEquals(buzzfeedPage.searchResultsFeed.isDisplayed(), true);

        // No results test
        buzzfeedPage.search("QazWsx");
        Assert.assertEquals(buzzfeedPage.searchResultsEmty.isDisplayed(), true);
    }

    @Test
    public void loginLogoutTest(){

        // Verify that user is logged in successfully
        buzzfeedPage.login("fedorovbuzzfeed@gmail.com", "Qwerty123");
        Assert.assertEquals(buzzfeedPage.isLoggedIn(), true);

        buzzfeedPage.hideMoreMenu();

        // Verify that user is logged out successfully
        buzzfeedPage.logout();
        Assert.assertEquals(buzzfeedPage.isLoggedIn(), false);
    }

    @Test
    public void notLoggedUserBookmarksTest(){

        // Verify that that user is not logged in
        Assert.assertEquals(buzzfeedPage.isLoggedIn(), false);

        buzzfeedPage.hideMoreMenu();

        // Verify that there is no access to bookmarks for not logged user
        buzzfeedPage.openBookmarks();
        Assert.assertEquals(buzzfeedPage.removeNewLines(
                buzzfeedPage.signinToAccessBookmarksLabel.getText()), "Please Sign In To Access Bookmarks");
    }

    @Test
    public void loggedUserBookmarksTest(){

        buzzfeedPage.login("fedorovbuzzfeed@gmail.com", "Qwerty123");

        // Verify that there are no bookmarks
        buzzfeedPage.openBookmarks();
        Assert.assertEquals(buzzfeedPage.removeNewLines(
                buzzfeedPage.noBookmarksLabel.getText()), "You Don't Have Any Bookmarks Yet");

        buzzfeedPage.backIcon.click();

        // Add post to bookmarks
        String postToBeBookmarkedText = buzzfeedPage.firstPost.getText();
        buzzfeedPage.firstPost.click();
        buzzfeedPage.bookMarkIcon.click();
        buzzfeedPage.backIcon.click();

        // Go to bookmarks
        buzzfeedPage.openBookmarks();
        String bookmarkedPostText = buzzfeedPage.bookmarkedPost.getText();

        // Verify that correct post has been bookmarked
        Assert.assertEquals(postToBeBookmarkedText, bookmarkedPostText);
    }

}
