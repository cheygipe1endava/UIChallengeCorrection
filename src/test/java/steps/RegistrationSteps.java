package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.HookHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.List;

public class RegistrationSteps{

    private WebDriver webDriver;
    private RegistrationPage registrationPage;

    public RegistrationSteps(HookHelper hookHelper)
    {
        webDriver = hookHelper.getWebDriver();
    }

    @Given("^the user goes to the registration page from Falabella's home page$")
    public void theUserGoesToTheRegistrationPageFromFalabellaSHomePage()
    {
        registrationPage = new RegistrationPage(webDriver);
        registrationPage.searchRegister();
    }

    @When("^the user fills all fields with corresponding data excepting cellphone$")
    public void theUserFillsAllWithCorrespondingExceptingCellphone(DataTable fields)
    {
        registrationPage.processDataTable(fields);
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
