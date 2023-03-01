package com.juaracoding.stepdefinition;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin {

    public static WebDriver driver;
    public LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage();
//        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(priority = 1)
    public void testUrl(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        //step verify
        Assert.assertEquals(loginPage.getLoginTitle(),"Login");
    }

    @Test(priority = 3)
    public void testValidLogin(){
        loginPage.login("Admin","admin123");
        Assert.assertEquals(loginPage.getTxtDashboard(),"Dashboard");
    }

    @Test(priority = 2)
    public void testInvalidLogin(){
        loginPage.login("Admin","admin1234");
        Assert.assertEquals(loginPage.getTxtInvalidCredentials(),"Invalid credentials");
    }

    @AfterClass
    public void quitBrowser(){
        delay(3);
        DriverSingleton.closeObjectInstance();
    }

    static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
