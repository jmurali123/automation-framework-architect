package tests.api;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JSONUtils;

public class UtilsTest {

    @Test
    public void testReadJsonFile(){
        String content = JSONUtils.readJsonFile("users-schema.json");
        System.out.println(content);
        Assert.assertNotNull(content);
    }
}
