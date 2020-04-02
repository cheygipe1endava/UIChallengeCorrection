package pages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class HomePage extends BasePage{

    private WebDriver webDriver;
    private WebDriverWait wait;
    private List<List<String>> dataTable;
    private By emailInput = By.id("emailAddress");
    private By searchBar = By.id("searchQuestionSolr");
    private By loginDiv = By.className("fb-masthead-login");
    private By loginFields = By.className("Modal__modalcontent__2yJz6");
    private By invalidLoginMessage = By.className("Login__message__3fDqw");
    private By accountDropDown = By.className("fb-masthead__dropdown__menu");
    private By passwordField = By.xpath("//*[@type='password']");
    private By findLoginButton = By.xpath("//*[@class='Button__main__1NDc9 Button__green__1fhy5']");
    private By findDisabledLoginButton = By.xpath("//*[@class='Button__main__1NDc9 Button__disabled__RnNv9']");
    private By loginText = By.xpath("//*[@class='fb-masthead-login__name re-design-cl__name']");
    private By emptyEmailField = By.xpath("//*[@class='InputText__message__2FAtZ']");
    private By wrongPassFormat = By.xpath("//*[@class='InputPassword__message__3ELVm']");
    private By findLogoutElement = By.xpath
            ("//*[@class='fb-filter-header__list-item']//a[@href='#']");
    private By findLoginDivText = By.xpath
            ("//*[@class='Login__mobileValidations__2b6z- fb-masthead-login__user-info__logged']//div[@class='fb-masthead-login__user-info']");

    public HomePage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Long.parseLong("15"));
    }

    public void processDataTable(DataTable fields)
    {
        dataTable = fields.cells();
    }

    public void openLoginFormOverlay()
    {
        webDriver.findElement(loginDiv).click();
    }

    public void EmailInsert()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginFields));
        if(dataTable.get(0).get(1) != null)
        {
            clickAndSendData(emailInput, dataTable.get(0).get(1));
        }
        clickAndSendData(emailInput, "");
    }

    public void PasswordInsert()
    {
        if(dataTable.get(1).get(1) != null)
        {
            clickAndSendData(passwordField, dataTable.get(1).get(1));
        }
        clickAndSendData(passwordField, "");
    }

    public void loginButton()
    {
        webDriver.findElement(findLoginButton).click();
    }

    public boolean disabledLoginButton()
    {
        boolean checkEnabledLoginButton = false;
        if(webDriver.findElement(findDisabledLoginButton).isEnabled())
        {
            checkEnabledLoginButton = true;
        }
        return checkEnabledLoginButton;
    }

    public boolean emptyEmailWarning()
    {
        boolean verifyEmptyEmailWarning = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyEmailField));
        if(webDriver.findElement(emptyEmailField).isDisplayed())
        {
            verifyEmptyEmailWarning = true;
        }
        return verifyEmptyEmailWarning;
    }

    public boolean wrongPasswordFormat()
    {
        boolean verifyPasswordLength = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(wrongPassFormat));
        if(webDriver.findElement(wrongPassFormat).isDisplayed())
        {
            verifyPasswordLength = true;
        }
        return verifyPasswordLength;
    }

    public boolean invalidLogin()
    {
        WebElement incorrectCredentials = wait.until(ExpectedConditions.
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

    public void typeInSearchBar(String searchProduct)
    {
        clickAndSendData(searchBar, searchProduct + Keys.ENTER);
    }

    public void loginProcess()
    {
        openLoginFormOverlay();
        EmailInsert();
        PasswordInsert();
        loginButton();
    }
}
