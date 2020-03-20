package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
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
        productPage = new ProductPage(webDriver);
        homePage.typeInSearchBar(searchProduct);
        Assert.assertTrue("User redirected to" + searchProduct + "page", productPage.confirmSearchPage(searchProduct));
    }

    @Given("^the user is in \"([^\"]*)\" product page$")
    public void theUserIsInProductPage(String searchProduct)
    {
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

    @Then("^the page shows error with wrong price range$")
    public void thePageShowsErrorWithWrongPriceRange()
    {
        Assert.assertTrue("Error: Wrong price range, please type a valid one", productPage.wrongPriceFilterRange());
    }

    @Then("^the user cannot click a disabled apply filter button$")
    public void theUserCannotClickADisabledApplyFilterButton()
    {
        Assert.assertTrue("Error: Empty price ranges", productPage.disabledApplyFilterButton());
    }
}
