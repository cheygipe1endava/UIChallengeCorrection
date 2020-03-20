package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import helper.HookHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.ProductPage;
import pages.ShoppingBagPage;

import java.util.List;

public class GeneralSteps
{
    private WebDriver webDriver;
    private HomePage homePage;
    private ProductPage productPage;
    private ShoppingBagPage shoppingBagPage;

    public GeneralSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @Given("^the user logs in Falabella's web page$")
    public void theUserLogsInFalabellaSWebPage(DataTable fields)
    {
        homePage = new HomePage(webDriver);
        homePage.processDataTable(fields);
        homePage.loginProcess();
        Assert.assertTrue("Successfully logged in", homePage.userLoggedIn());
    }

    @And("^the user closes the session$")
    public void theUserClosesTheSession()
    {
        homePage = new HomePage(webDriver);
        homePage.hoverAccountOptions();
        homePage.clickLogout();
        Assert.assertTrue("Successfully logged out", homePage.verifyLogout());
    }
}
