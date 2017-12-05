package androidemulator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AndroidTest {

    @Test
    public void firstTest() throws MalformedURLException {
        Page page = new Page();

        Assert.assertTrue(page.isLatestTabSelected());
        page.swipeLeft();
        Assert.assertFalse(page.isLatestTabSelected());

        //page.searchString("Test");

    }


}
