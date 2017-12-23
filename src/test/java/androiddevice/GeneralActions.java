package androiddevice;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GeneralActions {

    public int screenHeight;
    public int screenWidth;
    protected WebDriverWait customWait;
    protected TouchAction touchAction;

    protected AndroidDriver androidDriver;

    public GeneralActions(){
        this.androidDriver = Driver.getAndroidDriver();
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver, 5, TimeUnit.SECONDS), this);
        this.screenHeight = androidDriver.manage().window().getSize().getHeight();
        this.screenWidth = androidDriver.manage().window().getSize().getWidth();
        customWait = new WebDriverWait(androidDriver, 5);
        touchAction = new TouchAction(androidDriver);
    }


    public void clickIfDisplayed(AndroidElement element){
        try{
            element.click();
        } catch (NoSuchElementException e){

        }
    }

    public void waitToBeVisibleAndTap(AndroidElement element){
        customWait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void swipeLeft(){
        touchAction.
                press(screenWidth-10, screenHeight/2).
                waitAction(Duration.ofMillis(250)).
                moveTo(screenWidth/2, screenHeight/2).
                release().perform();
    }

    public void swipeRight(){
        touchAction.
                press(10, screenHeight/2).
                waitAction(Duration.ofMillis(250)).
                moveTo(screenWidth/2, screenHeight/2).
                release().perform();
    }

    public void swipeUp(){
        touchAction.
                press(screenWidth/2, screenHeight-100).
                waitAction(Duration.ofMillis(250)).
                moveTo(screenWidth/2, screenHeight-550).
                release().perform();
    }

    public void swipeDown(){
        touchAction.
                press(screenWidth/2, 100).
                waitAction(Duration.ofMillis(250)).
                moveTo(screenWidth/2, 550).
                release().perform();
    }

    public boolean isElementDisplayed(AndroidElement element){
        boolean isDisplayed;
        try{
            isDisplayed = element.isDisplayed();
        } catch (NoSuchElementException e){
            isDisplayed = false;
        }
        return isDisplayed;
    }
}
