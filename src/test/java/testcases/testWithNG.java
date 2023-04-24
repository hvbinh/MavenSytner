package testcases;

import org.testng.annotations.*;

public class testWithNG {
    @BeforeSuite
    void beforeSuite()
    {
        System.out.println("before suite");
    }
    @AfterSuite
    void afterSuite()
    {
        System.out.println("after suite");
    }
    @BeforeTest
    void beforeTest()
    {
        System.out.println("before test");
    }
    @AfterTest
    void afterTest()
    {
        System.out.println("after test");
    }
    @BeforeClass
    void beforeClass()
    {
        System.out.println("before class");
    }
    @AfterClass
    void afterClass()
    {
        System.out.println("after class");
    }
    @BeforeMethod
    void beforeMethod()
    {
        System.out.println("before method");
    }
    @AfterMethod
    void afterMethod()
    {
        System.out.println("after method");
    }
    @Test(groups = "a")
    void TC_01()
    {
        System.out.println("my TC 01");
    }
    @Test(groups = "a")
    void TC_02()
    {
        System.out.println("my TC 02");
    }
    @Test
    void TC_03()
    {
        System.out.println("my TC 03");
    }
}
