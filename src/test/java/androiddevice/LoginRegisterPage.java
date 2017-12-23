package androiddevice;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class LoginRegisterPage extends GeneralActions {

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/btn_sign_in")
    AndroidElement signInOptionBtn;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/et_email")
    AndroidElement signInEmailField;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/et_password")
    AndroidElement signInPasswordField;

    @AndroidFindBy(id = "com.alibaba.aliexpresshd:id/rl_ali_sign_in_btn")
    AndroidElement signInBtn;


    /***** Native alert *****/
    @AndroidFindBy(id = "android:id/message")
    AndroidElement nativeAlertMessage;
    @AndroidFindBy(id = "android:id/button1")
    AndroidElement nativeAlertOkBtn;

    public void loginWithEmail(String userEmail, String userPassword){
        signInOptionBtn.click();
        signInEmailField.sendKeys(userEmail);
        signInPasswordField.sendKeys(userPassword);
        signInBtn.click();
    }
}
