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

    private By loginDiv = By.className("fb-masthead-login");
    private By logoutDiv = By.className("fb-filter-header__list");
    private By loginField = By.className("Modal__modalcontent__2yJz6");
    private By passwordField = By.xpath("//input[@type='password']");
    private By findLoginButton = By.xpath("//*[contains(text(), 'Iniciar')]/.");
    private By invalidLoginMessage = By.className("Login__message__3fDqw");
    private By waitLoginStatus = By.xpath("//*[@id='header-login-modal']/div/div/div");
    private By findLogoutElement = By.xpath("//*[@class='fb-filter-header__list']/li/a[@href='#']");
    private By findLoggedDivClick = By.className("Login__mobileValidations__2b6z-");
    private By findLoginStatus = By.xpath("//*[@class='fb-masthead-login__user-info__logged']/*[@class='fb-masthead-login__name re-design-cl__name']");
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
        webDriver.findElement(loginDiv).click();
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
        await().atMost(Duration.TEN_SECONDS)
                .pollInterval(Duration.ONE_SECOND)
                .until(() -> {
                            webDriver.findElement(findLoggedDivClick).click();
                            return webDriver.findElement(findLoginStatus).isEnabled();
                        }
                );
        String getLoginText = webDriver.findElement(findLoginStatus).getText();
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitLoginStatus));
        WebElement hoverAction = webDriver.findElement(waitLoginStatus);
        Actions builder = new Actions(webDriver);
        builder.moveToElement(hoverAction).perform();
    }

    public void clickLogout()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutDiv));
        webDriver.findElement(findLogoutElement).click();
    }

    public boolean verifyLogout()
    {
        boolean loggedOut = false;
        await().atMost(Duration.TEN_SECONDS)
                .pollInterval(Duration.ONE_SECOND)
                .until(() -> {
                    webDriver.findElement(loginDiv).click();
                            return webDriver.findElement(loginDiv).isDisplayed();
                        }
                );
        String getVerifyLogoutText = webDriver.findElement(loginDiv).getText();
        if (getVerifyLogoutText.contains("Inicia"))
        {
            loggedOut = true;
        }
        return loggedOut;
    }
}
