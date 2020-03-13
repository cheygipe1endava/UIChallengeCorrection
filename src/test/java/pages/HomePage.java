package pages;

import helper.PropertiesHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

    private WebDriver webDriver;
    private WebElement passwordInput, loginButton, incorrectCredentials, logOut, verifyLogout, loggedInDiv, hoverAction;
    private By wrongPasswordField = By.xpath("//input[@type='password']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By findLoginButton = By.xpath("//*[contains(text(), 'Iniciar')]/.");
    private By waitLoginStatus = By.xpath("//*[@id='header-login-modal']/div/div/div");
    private By findLogoutElement = By.xpath("//*[@class='fb-filter-header__list']/li/a[@href='#']");
    private By emailInput = By.id("emailAddress");
    private WebDriverWait wait;

    @FindBy(className = "fb-masthead-login")
    private WebElement loginDiv;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    /*
    public void clickAndSendData(By by, String data){
        WebElement element = webDriver.findElement(by);
        element.click();
        element.clear();
        element.sendKeys(data);
    }
*/
    public void openLoginFormOverlay()
    {
        loginDiv.click();
    }

    public void invalidEmailInsert()
    {
        wait = new WebDriverWait(webDriver, Long.parseLong("5"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Modal__modalcontent__2yJz6")));
        clickAndSendData(emailInput, PropertiesHelper.getValueByKey("invalidEmail"));
    }

    public void invalidPasswordInsert()
    {
        clickAndSendData(wrongPasswordField, PropertiesHelper.getValueByKey("invalidPassword"));
    }

    public void emailInsert()
    {
        wait = new WebDriverWait(webDriver, Long.parseLong("5"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Modal__modalcontent__2yJz6")));
        clickAndSendData(emailInput, PropertiesHelper.getValueByKey("validEmail"));
    }

    public void passwordInsert()
    {
        clickAndSendData(passwordField, PropertiesHelper.getValueByKey("validPassword"));
    }

    public void invalidDataInsert()
    {
        invalidEmailInsert();
        invalidPasswordInsert();
    }

    public void validDataInsert()
    {
        emailInsert();
        passwordInsert();
    }

    public void loginButton()
    {
        loginButton = webDriver.findElement(findLoginButton);
        loginButton.click();
    }


    public boolean invalidLogin()
    {
        WebDriverWait waitInvalidMessage = new WebDriverWait(webDriver,Long.parseLong("15"));
        incorrectCredentials = waitInvalidMessage.until(ExpectedConditions.visibilityOfElementLocated(By.className("Login__message__3fDqw")));
        boolean invalidLoginMessage = incorrectCredentials.isEnabled();
        return invalidLoginMessage;
    }

    public boolean userLoggedIn()
    {
        boolean loggedIn = false;
        wait = new WebDriverWait(webDriver, Long.parseLong("5"));
        loggedInDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(waitLoginStatus));
        String getloginText = loggedInDiv.getText();
        if (getloginText.contains("Bienvenid@,"))
        {
            loggedIn = true;
        }
        return loggedIn;
    }

    public void hoverAccountOptions()
    {
        wait = new WebDriverWait(webDriver, Long.parseLong("5"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitLoginStatus));
        hoverAction = webDriver.findElement(waitLoginStatus);
        Actions builder = new Actions(webDriver);
        builder.moveToElement(hoverAction).perform();
    }

    public void clickLogout()
    {
        wait = new WebDriverWait(webDriver, Long.parseLong("5"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fb-filter-header__list")));
        logOut = webDriver.findElement(findLogoutElement);
        logOut.click();
    }

    public boolean verifyLogout()
    {
        boolean loggedOut = false;
        wait = new WebDriverWait(webDriver, Long.parseLong("5"));
        verifyLogout = wait.until(ExpectedConditions.elementToBeClickable(loginDiv));
        String getVerifyLogoutText = verifyLogout.getText();
        if (getVerifyLogoutText.contains("Inicia"))
        {
            loggedOut = true;
        }
        return loggedOut;

    }
}
