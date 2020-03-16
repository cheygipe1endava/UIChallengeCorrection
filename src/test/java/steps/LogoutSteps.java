package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.HookHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class LogoutSteps {

    private WebDriver webDriver;
    private HomePage homePage;

    public LogoutSteps(HookHelper hookHelper)
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

    @Given("^the user is currently logged in Falabella's home page$")
    public void theUserIsCurrentlyLoggedInFalabellaSHomePage()
    {

    }

    @When("^the user hover on account div$")
    public void theUserHoverOnAccountDiv()
    {
        homePage.hoverAccountOptions();
    }

    @And("^clicks logout option$")
    public void clicksLogoutOption()
    {
        homePage.clickLogout();
    }

    @Then("^the user should be signed out$")
    public void theUserShouldBeSignedOut()
    {
        Assert.assertTrue("Successfully logged out", homePage.verifyLogout());
    }
}