package androiddevice;

import org.testng.Assert;
import org.testng.annotations.*;

public class AndroidTest {

    GeneralActions generalActions;
    GeneralPage generalPage;
    LoginRegisterPage loginRegisterPage;

    @BeforeTest
    public void pageObjectsInit() {
        generalActions = new GeneralActions();
        generalPage = new GeneralPage();
        loginRegisterPage = new LoginRegisterPage();
    }

    @AfterTest
    public void quitDriver(){
        Driver.quitAndroidDriver();
    }

    @BeforeMethod
    public void launch(){
        Driver.startAliexpressApp();
        generalPage.closePopUpIfDisplayed();
    }

    @AfterMethod
    public void stop(){
        Driver.closeApp();
    }


    /***** TESTS*****/

    @Test
    public void loginTest() {

        generalActions.swipeRight();  // Opens navigation sidebar

        Assert.assertEquals(generalPage.sidebarProfileName.getText(), "Sign In | Join Free");

        generalPage.sidebarProfileName.click();
        loginRegisterPage.signInOptionBtn.click();
        loginRegisterPage.signInEmailField.sendKeys("fedorovbuzzfeed@gmail.com");
        loginRegisterPage.signInPasswordField.sendKeys("Qwerty123");
        loginRegisterPage.signInBtn.click();

        Assert.assertEquals(generalPage.myAccountProfileMail.getText(), "fedorovbuzzfeed@gmail.com");
    }

    @Test
    public void searchTest() {

        String searchProduct = "Apple iphone 8 4.7 inch 64 GB";

        generalPage.search(searchProduct);

        Assert.assertEquals(generalPage.isSearchResultEmpty(),
                false,
                "Search result is empty for " + searchProduct);

        Assert.assertEquals(generalPage.searchResultTitleOfFirstItemFromList.getText().contains(searchProduct),
                true,
                "First result does not match the search criteria. Actual: " + generalPage.searchResultTitleOfFirstItemFromList.getText());

    }

    @Test
    public void addToCartTest() {

        String searchProduct = "Apple iphone 8 4.7 inch 64GB";

        generalPage.search(searchProduct);
        generalPage.searchResultFirstItemFromList.click();

        Assert.assertEquals(generalActions.isElementDisplayed(generalPage.productPageCartCounter),
                false,
                "Cart is not empty");

        generalPage.scrollDownToElement(generalPage.productPageAddToCartBtn);
        generalPage.productPageAddToCartBtn.click();
        generalPage.addToCartSelectDefaultOptions();
        generalPage.addToCartContinueBtn.click();
        generalPage.tapAddToCartPopUpContinueShoppingBtn();
        generalPage.scrollUpToElement(generalPage.productPageCartCounter);

        Assert.assertEquals(generalPage.productPageCartCounter.getText(), "1");

        generalPage.productPageCartCounter.click();
        generalPage.tapNewFeaturePopUpGotItBtn();

        Assert.assertEquals(
                generalPage.shoppingCartProductTitle.getText().contains(searchProduct),
                true,
                "Title of product from the cart does not match the search criteria. Actual title: " + generalPage.shoppingCartProductTitle.getText());

    }

    @Test
    public void productImagesTest() throws InterruptedException {

        // Thread.sleep() is using for touch gestures demonstration

        String searchProduct = "Apple iphone 8 4.7 inch 64 GB";

        generalPage.search(searchProduct);

        generalPage.longTap(generalPage.searchResultImageOfFirstItemFromList);

        generalPage.doubleTap(generalPage.productImageFull);
        Thread.sleep(2000);

        generalPage.zoomOut(generalPage.productImageFull);
        Thread.sleep(2000);

        generalPage.swipeLeft();
        Thread.sleep(2000);

        generalPage.swipeLeft();
        Thread.sleep(2000);

        generalPage.swipeRight();
        Thread.sleep(2000);

        // Zoom In works like Zoom Out for some reasons!
        generalPage.zoomIn(generalPage.productImageFull);
        Thread.sleep(2000);
    }

}