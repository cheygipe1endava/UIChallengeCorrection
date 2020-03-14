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

    @When("^the user types in invalid credentials for username and password$")
    public void theUserTypesInInvalidCredentialsForUsernameAndPassword(DataTable fields)
    {
        homePage.processDataTable(fields);
        homePage.invalidEmailInsert();
        homePage.invalidPasswordInsert();
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

    @When("^the user types in valid credentials for username and password$")
    public void theUserTypesInValidCredentialsForUsernameAndPassword(DataTable fields)
    {
        homePage.processDataTable(fields);
        homePage.validEmailInsert();
        homePage.validPasswordInsert();
    }

    @Then("^the user should be signed in$")
    public void theUserShouldBeSignedIn()
    {
        Assert.assertEquals("Successfully logged in", true, homePage.userLoggedIn());
    }
}
