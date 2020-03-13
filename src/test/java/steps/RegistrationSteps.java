package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.HookHelper;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;

public class RegistrationSteps {

    private WebDriver webDriver;

    public RegistrationSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    RegistrationPage registrationPage = new RegistrationPage(webDriver);

    @Given("the user is in Falabella's home page")
    public void theUserIsInFalabellaSHomePage()
    {

    }

    @And("goes to the registration page")
    public void goesToTheRegistrationPage()
    {

    }

    @When("the user fills all fields excepting cellphone")
    public void theUserFillsAllFieldsExceptingCellphone() {
    }

    @And("tries to save its data")
    public void triesToSaveItsData() {
    }

    @Then("web page displays error message to the user")
    public void webPageDisplaysErrorMessageToTheUser() {
    }
}
