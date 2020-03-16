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
import pages.ShoppingBagPage;

public class AddToShoppingBagSteps {

    private WebDriver webDriver;
    private HomePage homePage;
    private ShoppingBagPage shoppingBagPage;
    private ProductPage productPage;

    public AddToShoppingBagSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @And("^the user searches for \"([^\"]*)\" in search bar and selects first match$")
    public void theUserSearchesForInSearchBarAndSelectsFirstMatch(String searchProduct)
    {
        homePage = new HomePage(webDriver);
        productPage = new ProductPage(webDriver);
        shoppingBagPage = new ShoppingBagPage(webDriver);
        homePage.typeInSearchBar(searchProduct);
        productPage.confirmSearchPage(searchProduct);
        productPage.clickFirstMatch();
    }

    @Given("^the user is in a product page$")
    public void theUserIsInAProductPage()
    {
        homePage = new HomePage(webDriver);
        productPage = new ProductPage(webDriver);
        shoppingBagPage = new ShoppingBagPage(webDriver);
        Assert.assertTrue("Successfully applied price filter from", productPage.confirmFirstMatchPage());
    }

    @When("^the user clicks button to add the product to the bag$")
    public void theUserClicksButtonToAddTheProductToTheBag()
    {
        productPage.clickAddToCartButton();
    }

    @Then("^the web page displays pop up message to user with the confirmation$")
    public void theWebPageDisplaysPopUpMessageToUserWithTheConfirmation()
    {
        Assert.assertTrue("Successfully added item to shopping cart", productPage.productAddedToCart());
    }

    @And("^the user goes to shopping bag to delete the product from it and message \"([^\"]*)\" is displayed$")
    public void theUserGoesToShoppingBagToDeleteTheProductFromItAndMessageIsDisplayed(String emptyMessage)
    {
        productPage.clickGoToShoppingBag();
        shoppingBagPage.clickDeleteProduct();
        Assert.assertTrue("Successfully deleted item from shopping cart", shoppingBagPage.emptyShoppingBag(emptyMessage));
    }
}
