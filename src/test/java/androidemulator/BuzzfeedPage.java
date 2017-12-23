package androidemulator;

import androiddevice.Driver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BuzzfeedPage{

    private int screenHeight;
    private int screenWidth;
    private WebDriverWait customWait;
    private TouchAction touchAction;

    protected AndroidDriver androidEmulatorDriver;

    public BuzzfeedPage(){
        this.androidEmulatorDriver = Driver.getAndroidEmulatorDriver();
        PageFactory.initElements(new AppiumFieldDecorator(androidEmulatorDriver, 5, TimeUnit.SECONDS), this);
        this.screenHeight = androidEmulatorDriver.manage().window().getSize().getHeight();
        this.screenWidth = androidEmulatorDriver.manage().window().getSize().getWidth();
        customWait = new WebDriverWait(androidEmulatorDriver, 10);
        touchAction = new TouchAction(androidEmulatorDriver);
    }


    @AndroidFindBy(id = "com.buzzfeed.android:id/menu_search")
    AndroidElement searchButton;

    @AndroidFindBy(id = "com.buzzfeed.android:id/search_src_text")
    AndroidElement searchField;

    @AndroidFindBy(uiAutomator = "new UiSelector().packageName(\"com.buzzfeed.android\").className(\"android.widget.TextView\").text(\"Latest\")")
    AndroidElement latestTab;

    @AndroidFindBy(uiAutomator = "new UiSelector().packageName(\"com.buzzfeed.android\").className(\"android.widget.TextView\").text(\"Trending\")")
    AndroidElement trendingTab;

    @AndroidFindBy(id = "com.buzzfeed.android:id/featured_card_dek")
    AndroidElement firstPost;

    @AndroidFindBy(id = "android:id/button1")
    AndroidElement confirmLogoutBtn;


    /****** Bookmarks *****/
    @AndroidFindBy(id = "com.buzzfeed.android:id/actionbar_title_text")
    AndroidElement bookmarksTitle;

    @AndroidFindBy(id = "com.buzzfeed.android:id/menu_bookmark")
    AndroidElement bookMarkIcon;

    @AndroidFindBy(accessibility = "Navigate up")
    AndroidElement backIcon;

    @AndroidFindBy(id = "com.buzzfeed.android:id/post_card_text")
    AndroidElement bookmarkedPost;

    @AndroidFindBy(id = "com.buzzfeed.android:id/bm_empty_body_text")
    AndroidElement noBookmarksLabel;

    @AndroidFindBy(id = "com.buzzfeed.android:id/bm_signin_body_text")
    AndroidElement signinToAccessBookmarksLabel;


    @AndroidFindBy(accessibility = "More options")
    AndroidElement moreOptions;

    /****** More list *****/
    @AndroidFindBy(uiAutomator = "new UiSelector().packageName(\"com.buzzfeed.android\").className(\"android.widget.TextView\").textContains(\"Log\")")
    AndroidElement moreListFirstItem;

    @AndroidFindBy(uiAutomator = "new UiSelector().packageName(\"com.buzzfeed.android\").className(\"android.widget.TextView\").text(\"Log In Or Sign Up\")")
    AndroidElement moreListLoginSignupItem;

    @AndroidFindBy(uiAutomator = "new UiSelector().packageName(\"com.buzzfeed.android\").className(\"android.widget.TextView\").text(\"Bookmarks\")")
    AndroidElement moreListBookmarksItem;


    /****** Login page *****/
    @AndroidFindBy(id = "com.buzzfeed.android:id/buzzlogin_username_field")
    AndroidElement usernameField;

    @AndroidFindBy(id = "com.buzzfeed.android:id/buzzlogin_password_field")
    AndroidElement passwordField;

    @AndroidFindBy(id = "com.buzzfeed.android:id/buzzlogin_submit_button")
    AndroidElement loginBtn;


    /****** Search results *****/
    @AndroidFindBy(id = "com.buzzfeed.android:id/recyclerView")
    AndroidElement searchResultsFeed;

    @AndroidFindBy(id = "com.buzzfeed.android:id/no_search_text")
    AndroidElement searchResultsEmty;


    public void login(String name, String password){
        moreOptions.click();
        moreListLoginSignupItem.click();
        usernameField.sendKeys(name);
        hideKeyboardIfDisplayed();
        passwordField.sendKeys(password);
        hideKeyboardIfDisplayed();
        loginBtn.click();

        customWait.until(ExpectedConditions.visibilityOf(moreOptions));
    }

    public void logout(){
        moreOptions.click();
        moreListFirstItem.click();
        confirmLogoutBtn.click();
    }

    public boolean isLoggedIn() {
        moreOptions.click();
        if(moreListFirstItem.getText().contains("Log In Or Sign Up")){

            return false;
        } else {
            return true;
        }
    }

    public String removeNewLines(String text){
        return text.replace("\n", " ").replace("\r", " ");
    }

    public void hideMoreMenu(){
        androidEmulatorDriver.pressKeyCode(AndroidKeyCode.BACK);
    }

    public void hideKeyboardIfDisplayed(){
        try {
            androidEmulatorDriver.hideKeyboard();
        } catch (Exception e){

        }
    }

    public void openBookmarks(){
        moreOptions.click();
        moreListBookmarksItem.click();
        customWait.until(ExpectedConditions.visibilityOf(bookmarksTitle));
    }

    public void removeFromBookMarks(){
        bookmarkedPost.click();
        bookMarkIcon.click();
        backIcon.click();
    }

    public void search(String searchQuery){
        searchButton.click();
        searchField.setValue(searchQuery);
        androidEmulatorDriver.pressKeyCode(AndroidKeyCode.ENTER);
    }

    public void tapSearchButton(){
        searchButton.click();
        androidEmulatorDriver.pressKeyCode(AndroidKeyCode.ENTER);
    }

    public void swipeToTrending(){
        swipeLeft();
        customWait.until(ExpectedConditions.elementSelectionStateToBe(trendingTab, true));
    }

    public void swipeLeft(){
        touchAction.
                press(screenWidth-50, 500).
                waitAction(Duration.ofMillis(250)).
                moveTo(50, 500).
                release().
                perform();
    }

    public void swipeRight(){
        touchAction.
                press(50, 500).
                waitAction(Duration.ofMillis(250)).
                moveTo(1000, 500).
                release().perform();
    }

    public void swipeUp(){
        touchAction.
                press(screenWidth/2, screenHeight/2).
                waitAction(Duration.ofMillis(250)).
                moveTo(screenWidth/2, 100).
                release().
                perform();
    }

}
