package pages;

import cucumber.api.DataTable;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.awaitility.Awaitility.await;

public class HomePage extends BasePage{

    private WebDriver webDriver;
    private WebDriverWait wait;
    private List<List<String>> dataTable;

    private By emailInput = By.id("emailAddress");
    private By loginDiv = By.className("fb-masthead-login");
    private By loginFields = By.className("Modal__modalcontent__2yJz6");
    private By invalidLoginMessage = By.className("Login__message__3fDqw");
    private By accountDropDown = By.className("fb-masthead__dropdown__menu");
    private By passwordField = By.xpath("//input[@type='password']");
    private By findLoginButton = By.xpath("//*[contains(text(), 'Iniciar')]/.");
    private By loginText = By.xpath("//*[@class='fb-masthead-login__name re-design-cl__name']");
    private By findLogoutElement = By.xpath
            ("//*[@class='fb-filter-header__list']/li[@class='fb-filter-header__list-item']/a[text()='Cerrar sesión']");
    private By findLoginDivText = By.xpath
            ("//*[@class='Login__mobileValidations__2b6z- fb-masthead-login__user-info__logged']/div[@class='fb-masthead-login__user-info']");

    public HomePage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Long.parseLong("10"));
    }

    public void processDataTable(DataTable fields)
    {
        dataTable = fields.raw();
    }

    public void openLoginFormOverlay()
    {
        webDriver.findElement(loginDiv).click();
    }

    public void invalidEmailInsert()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginFields));
        clickAndSendData(emailInput, dataTable.get(0).get(1));
    }

    public void invalidPasswordInsert()
    {
        clickAndSendData(passwordField, dataTable.get(1).get(1));
    }

    public void validEmailInsert()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginFields));
        clickAndSendData(emailInput, dataTable.get(0).get(1));
    }

    public void validPasswordInsert()
    {
        clickAndSendData(passwordField, dataTable.get(1).get(1));
    }

    public void loginButton()
    {
        webDriver.findElement(findLoginButton).click();
    }

    public boolean invalidLogin()
    {
        WebDriverWait waitInvalidMessage = new WebDriverWait(webDriver,Long.parseLong("10"));
        WebElement incorrectCredentials = waitInvalidMessage.until(ExpectedConditions.
                visibilityOfElementLocated(invalidLoginMessage));
        return incorrectCredentials.isEnabled();
    }

    public boolean userLoggedIn()
    {
        boolean loggedIn = false;
        wait.until(ExpectedConditions.presenceOfElementLocated(loginText));
        String getLoginText = webDriver.findElement(loginText).getText();
        if (getLoginText.contains("Bienvenid"))
        {
            loggedIn = true;
        }
        return loggedIn;
    }

    public void loginProcess()
    {
        openLoginFormOverlay();
        validEmailInsert();
        validPasswordInsert();
        loginButton();
    }

    public void hoverAccountOptions()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginDiv));
        WebElement hoverAction = webDriver.findElement(loginDiv);
        Actions builder = new Actions(webDriver);
        builder.moveToElement(hoverAction).perform();
    }

    public void clickLogout()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountDropDown));
        webDriver.findElement(findLogoutElement).click();
    }

    public boolean verifyLogout()
    {
        boolean loggedOut = false;
        wait.until(ExpectedConditions.presenceOfElementLocated(findLoginDivText));
        String getVerifyLogoutText = webDriver.findElement(findLoginDivText).getText();
        if (getVerifyLogoutText.contains("Inicia"))
        {
            loggedOut = true;
        }
        return loggedOut;
    }
}
