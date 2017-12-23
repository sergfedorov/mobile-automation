package androidemulator;

import androiddevice.GeneralActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.HashMap;

public class BuzzfeedPage{


    /*@AndroidFindBy(id = "com.buzzfeed.android:id/menu_search")
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
        action.
                press(1000, 500).
                waitAction(Duration.ofMillis(250)).
                moveTo(50, 500).
                release().perform();
    }

    public void swipeRight(){
        TouchAction action = new TouchAction(androidDriver);
        action.
                press(50, 500).
                waitAction(Duration.ofMillis(250)).
                moveTo(1000, 500).
                release().perform();
    }

    public void scrollDown(){
        JavascriptExecutor js = androidDriver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down");
        //scrollObject.put("element", ((AndroidDriver) element).getId());
        js.executeScript("mobile: scroll", scrollObject);
    }*/


}
