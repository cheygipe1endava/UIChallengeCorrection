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

public class LoginSteps {

    private WebDriver webDriver;
    private HomePage homePage;

    public LoginSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @Given("^the user opens login form overlay from Falabella's home page$")
    public void theUserOpensLoginFormOverlayFromFalabellaSHomePage()
    {
        homePage = new HomePage(webDriver);
        homePage.openLoginFormOverlay();
    }

    @When("^the user types in its credentials for username and password$")
    public void theUserTypesInItsCredentialsForUsernameAndPassword(DataTable fields)
    {
        homePage.processDataTable(fields);
        homePage.EmailInsert();
        homePage.PasswordInsert();
    }

    @And("^clicks login button$")
    public void clicksLoginButton()
    {
        homePage.loginButton();
    }

    @Then("^the page shows error with email/password to the user$")
    public void thePageShowsErrorWithEmailPasswordToTheUser()
    {
        Assert.assertTrue("Error: Invalid email or password, please check your credentials", homePage.invalidLogin());
    }

    @Then("^the user should be signed in$")
    public void theUserShouldBeSignedIn()
    {
        Assert.assertTrue("Successfully logged in", homePage.userLoggedIn());
    }

    @Then("^user cannot click disabled login button$")
    public void userCannotClickDisabledLoginButton()
    {
        Assert.assertFalse("Error: Invalid email or password, please check your credentials", homePage.disabledLoginButton());
    }

    @And("^the page shows error with blank email field$")
    public void thePageShowsErrorWithBlankEmailField()
    {
        Assert.assertTrue("Error: Blank email field, please enter your email ", homePage.emptyEmailWarning());
    }

    @And("^the page shows error with wrong password format$")
    public void thePageShowsErrorWithWrongPasswordFormat()
    {
        Assert.assertTrue("Error: Blank email field, please enter your email ", homePage.wrongPasswordFormat());
    }
}