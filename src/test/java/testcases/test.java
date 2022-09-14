package testcases;

import common.BaseTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

import java.util.logging.Logger;


public class test extends BaseTest {


    public static void main(String[] args) {



    }
    @Test
    public void abc()
    {
        System.out.println("hello world");
        //Log log = LogFactory.getLog(getClass());
        Log log = LogFactory.getLog(getClass());
        log.info("adfdfd");
    }

}
