package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.HookHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class SearchSteps {

    private HomePage homePage;
    private WebDriver webDriver;

    public SearchSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @Given("^the user is in Falabella's home page$")
    public void theUserIsInFalabellaSHomePage()
    {
        homePage = new HomePage(webDriver);
    }

    @When("^the user types \"([^\"]*)\" in the search bar and press enter$")
    public void theUserTypesInTheSearchBarAndPressEnter(String searchProduct)
    {
        homePage.typeInSearchBar(searchProduct);
    }

    @Then("^the user should be redirected to \"([^\"]*)\" resulting matches page$")
    public void theUserShouldBeRedirectedToResultingMatchesPage(String searchProduct)
    {
        Assert.assertTrue("User redirected to" + searchProduct + "page", homePage.confirmSearchPage(searchProduct));
    }
}
