package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.HookHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.ShoppingCartPage;

public class DeleteFromShoppingCartSteps
{
    private HomePage homePage;
    private ShoppingCartPage shoppingCartPage;
    private WebDriver webDriver;

    public DeleteFromShoppingCartSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @Given("^the user clicks on shopping bag icon with at least \"([^\"]*)\" product on it$")
    public void theUserClicksOnShoppingBagIconWithAtLeastProductOnIt(String numberOfObjects)
    {
        homePage = new HomePage(webDriver);
        shoppingCartPage = new ShoppingCartPage(webDriver);
        homePage.clickShoppingBag();
    }

    @When("^the user click to delete the product from the shopping bag$")
    public void theUserClickToDeleteTheProductFromTheShoppingBag()
    {
        shoppingCartPage.clickDeleteProduct();
    }

    @Then("^the web page displays message \"([^\"]*)\" to user saying the cart is empty$")
    public void theWebPageDisplaysMessageToUserSayingTheCartIsEmpty(String emptyMessage)
    {
        Assert.assertTrue("Successfully deleted item from shopping cart", shoppingCartPage.emptyShoppingBag(emptyMessage));
    }
}
