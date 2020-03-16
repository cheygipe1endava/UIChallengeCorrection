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

public class FilterSteps {

    private HomePage homePage;
    private ProductPage productPage;
    private WebDriver webDriver;

    public FilterSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @Given("^the user searches for \"([^\"]*)\" in search bar$")
    public void theUserSearchesForInSearchBar(String searchProduct)
    {
        homePage = new HomePage(webDriver);
        homePage.typeInSearchBar(searchProduct);
    }

    @Given("^the user is in \"([^\"]*)\" product page$")
    public void theUserIsInProductPage(String searchProduct)
    {
        Assert.assertTrue("User redirected to" + searchProduct + "page", homePage.confirmSearchPage(searchProduct));
        productPage = new ProductPage(webDriver);
    }

    @When("^the user clicks price button$")
    public void theUserClicksPriceButton()
    {
        productPage.priceButtonClick();
    }

    @And("^types prices between \"([^\"]*)\" to \"([^\"]*)\" in price fields and applies the price filter$")
    public void typesPricesBetweenToInPriceFieldsAndAppliesThePriceFilter(String minimumPrice, String maximumPrice)
    {
        productPage.priceFilter(minimumPrice, maximumPrice);
    }

    @Then("^the user should only see products with prices \"([^\"]*)\" and \"([^\"]*)\" in this range$")
    public void theUserShouldOnlySeeProductsWithPricesAndInThisRange(String minimumPrice, String maximumPrice)
    {
        Assert.assertTrue("Successfully applied price filter from " + minimumPrice + "to " + maximumPrice,
                productPage.priceFilterApplied(minimumPrice, maximumPrice));
    }
}
