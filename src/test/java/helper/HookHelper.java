package helper;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HookHelper {

    private WebDriver webDriver;

    @Before
    public void setUp()
    {
        if (webDriver == null) {
            webDriver = new ChromeDriver();
        }
        webDriver.manage().window().maximize();
        webDriver.get("https://www.falabella.com.co/falabella-co/");
    }

    public WebDriver getWebDriver()
    {
        return webDriver;
    }

    @After
    public void tearDown()
    {
        webDriver.quit();
    }
}

