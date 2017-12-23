package androiddevice;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class GeneralPage extends GeneralActions {

    /***** Top menu *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/left_action")
    AndroidElement menuBtn;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/search_hint")
    AndroidElement globalSearchFiled;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/abs__search_src_text")
    AndroidElement globalSearchFiledAfterClick;


    /***** Navigation sidebar *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/profile_name_text")
    AndroidElement sidebarProfileName;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.alibaba.aliexpresshd:id/navdrawer_items_list']/android.widget.RelativeLayout[1]")
    AndroidElement sidebarHomeItem;


    /***** Promo popup *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/iv_close_poplayer")
    AndroidElement closePromoPopUp;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/tv_tv2")
    AndroidElement photoPopUpOkBtn;

    @AndroidFindBy(xpath = "//*[contains(@resource-id,'close')]")
    AndroidElement closeAnyPopUpBtn;


    /***** My Account *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/tv_profile_name")
    AndroidElement myAccountProfileName;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/tv_profile_mail")
    AndroidElement myAccountProfileMail;

    @AndroidFindBy(id = "Navigate up")
    AndroidElement myAccountNavigationBtn;


    /***** Page content *****/
    @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.alibaba.aliexpresshd:id/recyclerView']/android.widget.FrameLayout[2]")
    AndroidElement categorySection;


    /***** Search result page *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/search_result_list")
    AndroidElement searcResultContainer;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/user_hint_close")
    AndroidElement searchResultUserHintCloseBtn;

    //@AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.alibaba.aliexpresshd:id/search_result_list']/android.widget.RelativeLayout[1]")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.alibaba.aliexpresshd:id/ll_productlist_container\").className(\"android.widget.RelativeLayout\").index(0)")
    AndroidElement searchResultFirstItemFromList;

    @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.alibaba.aliexpresshd:id/search_result_list']/android.widget.RelativeLayout[1]//android.widget.ImageView")
    AndroidElement searchResultImageOfFirstItemFromList;

    @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.alibaba.aliexpresshd:id/search_result_list']/android.widget.RelativeLayout[1]//android.widget.FrameLayout/android.widget.TextView")
    AndroidElement searchResultTitleOfFirstItemFromList;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/user_hint_container")
    AndroidElement searchResultListUserHint;


    /***** Product page *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/tv_product_subject")
    AndroidElement productPageProductTitle;

     @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/tv_addToCart")
    AndroidElement productPageAddToCartBtn;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/fake_actionbar_title")
    AndroidElement productOptionTitleBar;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/menu_overflow")
    AndroidElement productPageMoreButton;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/menu_badge")
    AndroidElement productPageCartCounter;


    /***** Product images *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/ll_add_wish_container")
    AndroidElement productImageAddToWishListBtn;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/vp_imgs")
    AndroidElement productImageFull;


    /***** Add to cart page *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/ll_sku_area")
    AndroidElement addToCartOptionsContainer;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/tv_apply_options")
    AndroidElement addToCartContinueBtn;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/buttonDefaultPositive")
    AndroidElement addToCartPopUpContinueShoppingBtn;


    /***** Shopping cart page *****/
    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/bt_got_it")
    AndroidElement shoppingCartNewFeaturePopUpGotItBtn;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/tv_product_title")
    AndroidElement shoppingCartProductTitle;



    public void goToHomePage(){
        productPageMoreButton.click();

    }

    public void longTap(AndroidElement element){
        touchAction.
                longPress(element).
                release().perform();

        customWait.until(ExpectedConditions.visibilityOf(productImageAddToWishListBtn));
    }


    public void tapAddToCartPopUpContinueShoppingBtn(){
        waitToBeVisibleAndTap(addToCartPopUpContinueShoppingBtn);
    }

    public void tapNewFeaturePopUpGotItBtn(){
        waitToBeVisibleAndTap(shoppingCartNewFeaturePopUpGotItBtn);
    }


    public void search(String searchText){
        globalSearchFiled.click();
        if(!androidDriver.findElementsById("com.alibaba.aliexpresshd:id/tv_tv2").isEmpty()){
            photoPopUpOkBtn.click();
        }
        globalSearchFiledAfterClick.sendKeys(searchText);
        androidDriver.pressKeyCode(66);

        customWait.until(ExpectedConditions.visibilityOf(searcResultContainer));

        super.clickIfDisplayed(searchResultUserHintCloseBtn);
    }


    public boolean isSearchResultEmpty(){
        return searcResultContainer.findElementsByXPath("//android.widget.LinearLayout").isEmpty();
    }


    public void closePopUpIfDisplayed(){
        if(!androidDriver.findElementsByXPath("//*[contains(@resource-id,'close')]").isEmpty()){
            closeAnyPopUpBtn.click();
        }
    }

    public void swipeAndClick(){

        boolean isTargetElementPresentOnScreen = !androidDriver.findElementsById("com.alibaba.aliexpresshd:id/tv_addToCart").isEmpty();
        int swipeCount = 5;

        while (isTargetElementPresentOnScreen == false){
            super.swipeUp();
            isTargetElementPresentOnScreen = !androidDriver.findElementsById("com.alibaba.aliexpresshd:id/tv_addToCart").isEmpty();

            swipeCount--;
            if(swipeCount == 1){
                throw new NoSuchElementException("Target element was not found after 5 swipe attempts");
            }
        }
        productPageAddToCartBtn.click();
    }


    public void scrollDownToElement(AndroidElement element){

        int cycles = 5;
        int counter = 0;

        while(counter < cycles){
            try{
                //element.click();
                element.isDisplayed();
                break;
            } catch (NoSuchElementException e){

            }
            swipeUp();
            counter++;
            if(counter == cycles){
                throw new NoSuchElementException(String.format("Target element was not found after %d swipe attempts", cycles));
            }
        }
    }

    public void scrollUpToElement(AndroidElement element){

        int cycles = 5;
        int counter = 0;

        while(counter < cycles){
            try{
                //element.click();
                element.isDisplayed();
                break;
            } catch (NoSuchElementException e){

            }
            super.swipeDown();
            counter++;
            if(counter == cycles){
                throw new NoSuchElementException(String.format("Target element was not found after %d swipe attempts", cycles));
            }
        }
    }

    public void addToCartSelectDefaultOptions(){

        int s = addToCartOptionsContainer.findElementsByXPath("//android.widget.RadioGroup").size();

        for (int i = 1; i <= s; i++) {
            String xpathString = String.format("//android.widget.RadioGroup[%d]/android.widget.CompoundButton[1]", i);
            addToCartOptionsContainer.findElementByXPath(xpathString).click();
        }
    }

     public void zoomIn(AndroidElement element){

        Point elementCenter = element.getCenter();

        TouchAction finger1 = new TouchAction(androidDriver);
        finger1.press(elementCenter.x, elementCenter.y).waitAction(Duration.ofMillis(250)).moveTo(0, -300).waitAction(Duration.ofMillis(500)).release();

        TouchAction finger2 = new TouchAction(androidDriver);
        finger2.press(elementCenter.x, elementCenter.y).waitAction(Duration.ofMillis(250)).moveTo(0, 300).waitAction(Duration.ofMillis(500)).release();

        MultiTouchAction action = new MultiTouchAction(androidDriver);
        action.add(finger1).add(finger2).perform();
    }

    public void zoomOut(AndroidElement element){

        Point elementCenter = element.getCenter();

        TouchAction finger1 = new TouchAction(androidDriver);
        finger1.press(elementCenter.x, elementCenter.y-300).waitAction(Duration.ofMillis(250)).moveTo(0, 300).waitAction(Duration.ofMillis(500)).release();

        TouchAction finger2 = new TouchAction(androidDriver);
        finger2.press(elementCenter.x, elementCenter.y+300).waitAction(Duration.ofMillis(250)).moveTo(0, -300).waitAction(Duration.ofMillis(500)).release();

        MultiTouchAction action = new MultiTouchAction(androidDriver);
        action.add(finger1).add(finger2).perform();
    }

    public void doubleTap(AndroidElement element){

        Point elementCenter = element.getCenter();

        touchAction.press(elementCenter.x, elementCenter.y).release().perform().waitAction(Duration.ofMillis(10)).
                press(elementCenter.x, elementCenter.y).release().perform();
    }
}
