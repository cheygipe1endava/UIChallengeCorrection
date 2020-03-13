package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);

    }

    public void clickAndSendData(By by, String data){
        WebElement element = webDriver.findElement(by);
        element.click();
        element.clear();
        element.sendKeys(data);
    }
}
