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
import pages.ProductPage;
import pages.ShoppingCartPage;

public class DeleteFromShoppingCartSteps
{
    private HomePage homePage;
    private ProductPage productPage;
    private ShoppingCartPage shoppingCartPage;
    private WebDriver webDriver;

    public DeleteFromShoppingCartSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @And("^adds the product to the shopping bag and closes pop up$")
    public void addsTheProductToTheShoppingBagAndClosesPopUp()
    {
        productPage = new ProductPage(webDriver);
        productPage.clickAddToCartButton();
    }

    @Given("^the user added a product and clicks on go to shopping bag button$")
    public void theUserAddedAProductAndClicksOnGoToShoppingBagButton()
    {
        homePage = new HomePage(webDriver);
        shoppingCartPage = new ShoppingCartPage(webDriver);
        productPage.clickGoToShoppingBag();
    }

    @When("^the user clicks to delete the product from the shopping bag$")
    public void theUserClicksToDeleteTheProductFromTheShoppingBag()
    {
        shoppingCartPage.clickDeleteProduct();
    }

    @Then("^the web page displays message \"([^\"]*)\" to user saying the bag is empty$")
    public void theWebPageDisplaysMessageToUserSayingTheBagIsEmpty(String emptyMessage)
    {
        Assert.assertTrue("Successfully deleted item from shopping cart", shoppingCartPage.emptyShoppingBag(emptyMessage));
    }
}
