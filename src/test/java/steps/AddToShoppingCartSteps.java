package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.HookHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.ProductPage;

public class AddToShoppingCartSteps {

    private WebDriver webDriver;
    private HomePage homePage;
    private ProductPage productPage;

    public AddToShoppingCartSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @And("^the user searches for \"([^\"]*)\" in search bar and selects first match$")
    public void theUserSearchesForInSearchBarAndSelectsFirstMatch(String searchProduct)
    {
        homePage = new HomePage(webDriver);
        productPage = new ProductPage(webDriver);
        homePage.typeInSearchBar(searchProduct);
        productPage.confirmSearchPage(searchProduct);
        productPage.clickFirstMatch();
    }

    @Given("^the user is in a product page$")
    public void theUserIsInAProductPage()
    {
        Assert.assertTrue("Successfully applied price filter from", productPage.confirmFirstMatchPage());
    }

    @When("^the user clicks button to add the product to the cart$")
    public void theUserClicksButtonToAddTheProductToTheCart()
    {
        productPage.clickAddToCartButton();
    }

    @Then("^the web page displays pop up message to user with the confirmation$")
    public void theWebPageDisplaysPopUpMessageToUserWithTheConfirmation()
    {
        Assert.assertTrue("Successfully added item to shopping cart", productPage.productAddedToCart());
    }
}
