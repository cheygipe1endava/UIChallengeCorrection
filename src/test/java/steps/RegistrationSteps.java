package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.HookHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;

public class RegistrationSteps {

    private WebDriver webDriver;
    private RegistrationPage registrationPage;

    public RegistrationSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @Given("the user is in Falabella's home page")
    public void theUserIsInFalabellaSHomePage()
    {
        registrationPage = new RegistrationPage(webDriver);
    }

    @And("goes to the registration page")
    public void goesToTheRegistrationPage()
    {
        registrationPage.searchRegister();
    }

    @When("the user fills all fields excepting cellphone")
    public void theUserFillsAllFieldsExceptingCellphone()
    {
        registrationPage.insertData();
    }

    @And("tries to save its data")
    public void triesToSaveItsData()
    {
        registrationPage.saveButtonClick();
    }

    @Then("web page displays error message to the user")
    public void webPageDisplaysErrorMessageToTheUser()
    {
        Assert.assertTrue("Error: Cellphone field is empty",
                registrationPage.registrationCellphoneNotFound());
    }
}
