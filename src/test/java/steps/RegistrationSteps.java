package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
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

    @Then("^web page displays error message to the user with empty cellphone field$")
    public void webPageDisplaysErrorMessageToTheUserWithEmptyCellphoneField()
    {
        Assert.assertTrue("Error: Cellphone field is empty",
                registrationPage.registrationCellphoneNotFound());
    }

    @Then("^web page displays error message for not matching passwords and empty cellphone field to the user$")
    public void webPageDisplaysErrorMessageForNotMatchingPasswordsAndEmptyCellphoneFieldToTheUser()
    {
        Assert.assertTrue("Error: Cellphone field is empty",
                registrationPage.registrationCellphoneNotFound());
        Assert.assertTrue("Error: Cellphone field is empty",
                registrationPage.notMatchingPasswordsMessage());
    }

    @And("^name and last names does not have any number$")
    public void nameAndLastNamesDoesNotHaveAnyNumber()
    {
        Assert.assertFalse("Error: Name/Last name cannot have any number on it",
                registrationPage.verifyNumbersInNameAndLastNames());
    }
}
