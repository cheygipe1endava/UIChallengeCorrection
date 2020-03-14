package pages;

import cucumber.api.DataTable;
import helper.PropertiesHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage{

    private WebDriver webDriver;
    private WebElement passwordInput, loginButton, incorrectCredentials, logOut, verifyLogout, loggedInDiv, hoverAction;
    private WebDriverWait wait;
    private List<List<String>> dataTable;

    private By divLogin = By.className("fb-masthead-login");
    private By loginField = By.className("Modal__modalcontent__2yJz6");
    private By passwordField = By.xpath("//input[@type='password']");
    private By findLoginButton = By.xpath("//*[contains(text(), 'Iniciar')]/.");
    private By waitLoginStatus = By.xpath("//*[@id='header-login-modal']/div/div/div");
    private By findLogoutElement = By.xpath("//*[@class='fb-filter-header__list']/li/a[@href='#']");
    private By emailInput = By.id("emailAddress");

    public HomePage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Long.parseLong("5"));
    }

    public void processDataTable(DataTable fields)
    {
        dataTable = fields.raw();
    }

    public void openLoginFormOverlay()
    {
        webDriver.findElement(divLogin).click();
    }

    public void invalidEmailInsert()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginField));
        clickAndSendData(emailInput, dataTable.get(0).get(1));
    }

    public void invalidPasswordInsert()
    {
        clickAndSendData(passwordField, dataTable.get(1).get(1));
    }

    public void validEmailInsert()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginField));
        clickAndSendData(emailInput, dataTable.get(0).get(1));
    }

    public void validPasswordInsert()
    {
        clickAndSendData(passwordField, dataTable.get(1).get(1));
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
        loggedInDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(waitLoginStatus));
        String getloginText = loggedInDiv.getText();
        if (getloginText.contains("Bienvenid"))
        {
            loggedIn = true;
        }
        return loggedIn;
    }

    public void hoverAccountOptions()
    {
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
        verifyLogout = wait.until(ExpectedConditions.elementToBeClickable(divLogin));
        String getVerifyLogoutText = verifyLogout.getText();
        if (getVerifyLogoutText.contains("Inicia"))
        {
            loggedOut = true;
        }
        return loggedOut;

    }
}
