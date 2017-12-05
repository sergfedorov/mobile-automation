package androidemulator;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Page {

    AndroidDriver androidDriver;

    public Page() throws MalformedURLException {
        androidDriver = Driver.getAndroidDriver();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver, 5, TimeUnit.SECONDS), this);
    }

    @AndroidFindBy(id = "com.buzzfeed.android:id/menu_search")
    private AndroidElement searchButton;

    @AndroidFindBy(id = "com.buzzfeed.android:id/search_src_text")
    private AndroidElement searchField;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout/android.support.v7.app.ActionBar.b[1]")
    private AndroidElement latestTab;




    public void tapSearchButton(){
        searchButton.click();

        androidDriver.pressKeyCode(66);
    }

    public void searchString(String search){
        searchButton.click();
        searchField.setValue(search);
        androidDriver.pressKeyCode(66);
    }

    public boolean isLatestTabSelected(){
        return latestTab.isSelected();
    }

    public void swipeLeft(){
        TouchAction action = new TouchAction(androidDriver);
        action.press(800, 500).waitAction(Duration.ofMillis(250)).moveTo(200, 500).release().perform();
    }




}
