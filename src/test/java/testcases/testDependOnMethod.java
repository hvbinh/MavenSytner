package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testDependOnMethod {

    @Test(groups = "user")
    public void tc1_createUser()
    {
        System.out.println("create user");
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = {"tc1_createUser"}, groups = {"user"})
    public void tc2_editUser()
    {
        System.out.println("edit user");
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = {"tc1_createUser","tc2_editUser"})
    public void tc3_deleteUser()
    {
        System.out.println("delete user");
        Assert.assertTrue(true);
    }
}
