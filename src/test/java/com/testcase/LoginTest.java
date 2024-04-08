package com.testcase;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import loginpagobj.LoginPagObj;

public class LoginTest {
	private WebDriver driver;
    private LoginPagObj lp;

    @BeforeClass
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		driver = new ChromeDriver(options);
        lp = PageFactory.initElements(driver, LoginPagObj.class);
        driver.manage().window().maximize();
        driver.get("https://cloud-test.vendolite.com/home/login");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            { "loginuser@riota", "12345678" },
            { "loginuser@riota.in", "123456" },
            { "loginuser@riota.in", "12345678" }
            
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String Password) {
        lp.setUsername(username);
        lp.setPwd(Password);
        lp.clickSubmit();
        }
}
