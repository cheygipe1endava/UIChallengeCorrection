package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.HookHelper;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class AddToShoppingCartSteps {

    private WebDriver webDriver;
    private HomePage homePage;

    public AddToShoppingCartSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @And("^the user searches for \"([^\"]*)\" in search bar and selects first match$")
    public void theUserSearchesForInSearchBarAndSelectsFirstMatch(String searchProduct)
    {
        homePage = new HomePage(webDriver);
    }

    @Given("^the user is in a product page$")
    public void theUserIsInAProductPage()
    {

    }

    @When("^the user tries to add the product to the cart$")
    public void theUserTriesToAddTheProductToTheCart()
    {

    }

    @Then("^the web page displays pop up message to user with the confirmation$")
    public void theWebPageDisplaysPopUpMessageToUserWithTheConfirmation()
    {

    }


}
