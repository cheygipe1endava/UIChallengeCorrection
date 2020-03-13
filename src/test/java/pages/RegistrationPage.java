package pages;

import helper.PropertiesHelper;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.awaitility.Awaitility.await;

public class RegistrationPage extends BasePage{
    private WebDriver webDriver;
    private WebElement searchRegister, genderRadioButton, termsCheckbox, saveButton, findCellhponeAlert;
    private Select dropDownCountry, dropdownDocument, dropDownDay, dropDownMonth, dropDownYear;
    private By findFemaleButton = By.xpath("//*[@id='tipodireccion_0' and @value='female']");
    private By user = By.id("user");
    private By fatherLastName = By.id("apellidopaterno");
    private By motherLastName = By.id("apellidomaterno");
    private By mail = By.id("mail");
    private By password = By.id("clave1");
    private By confirmPass = By.id("clave2");
    private By documentNum = By.id("cedula_colombia");

    private boolean cellphoneEmptyMessage = false;

    @FindBy(className = "fb-masthead-login")
    private WebElement loginDiv;

    @FindBy(className = "Login__createAccount__38c2o")
    private WebElement searchRegisterParent;


    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }


    public void searchRegister ()
    {
        loginDiv.click();
        WebDriverWait wait = new WebDriverWait(webDriver,Long.parseLong("5"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Modal__modalcontent__2yJz6")));
        searchRegister = searchRegisterParent.findElement(By.tagName("a"));
        searchRegister.click();
    }

    public void insertName()
    {
        WebDriverWait wait = new WebDriverWait(webDriver, Long.parseLong("5"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contRegistro")));
        clickAndSendData(user, PropertiesHelper.getValueByKey("name"));
    }

    public void insertFatherLastName()
    {
        clickAndSendData(fatherLastName, PropertiesHelper.getValueByKey("fatherLastName"));
    }

    public void insertMotherLastName()
    {
        clickAndSendData(motherLastName, PropertiesHelper.getValueByKey("motherLastName"));
    }

    public void emailInsert()
    {
        clickAndSendData(mail, PropertiesHelper.getValueByKey("email"));
    }

    public void passwordAndConfirmationInsert()
    {
        clickAndSendData(password, PropertiesHelper.getValueByKey("password"));
        clickAndSendData(confirmPass, PropertiesHelper.getValueByKey("password"));
    }

    public void selectDropDowns()
    {
        dropDownCountry = new Select(webDriver.findElement(By.id("status")));
        dropDownCountry.selectByVisibleText("Colombia");
        dropdownDocument = new Select(webDriver.findElement(By.id("cedula")));
        dropdownDocument.selectByValue("CEDULA_DE_CIUDADANIA");
        dropDownDay = new Select(webDriver.findElement(By.id("day")));
        dropDownDay.selectByVisibleText("5");
        dropDownMonth = new Select(webDriver.findElement(By.id("month")));
        dropDownMonth.selectByVisibleText("May");
        dropDownYear = new Select(webDriver.findElement(By.id("year")));
        dropDownYear.selectByVisibleText("1990");
    }

    public void documentNumInsert()
    {
        clickAndSendData(documentNum, PropertiesHelper.getValueByKey("documentNumber"));

    }

    public void genderSelection()
    {
        genderRadioButton = webDriver.findElement(findFemaleButton);
        genderRadioButton.click();
    }

    public void checkAgreedTerms()
    {
        WebDriverWait wait = new WebDriverWait(webDriver, Long.parseLong("5"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("agreelegaleId")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("agreelegaleId")));
        termsCheckbox = webDriver.findElement(By.id("agreelegaleId"));
        await().atMost(Duration.TEN_SECONDS)
                .pollInterval(Duration.ONE_SECOND)
                .until(() -> {
                            termsCheckbox.click();
                            return termsCheckbox.isSelected();
                        }
                );
    }

    public void insertData() {
        insertName();
        insertFatherLastName();
        insertMotherLastName();
        emailInsert();
        passwordAndConfirmationInsert();
        selectDropDowns();
        documentNumInsert();
        genderSelection();
        checkAgreedTerms();
    }

    public void saveButtonClick() {
        saveButton = webDriver.findElement(By.id("boton_Ar"));
        saveButton.click();
    }

    public boolean registrationCellphoneNotFound() {

        this.findCellhponeAlert = webDriver.findElement(By.id("mensajeCelVacio"));
        if(findCellhponeAlert.isDisplayed())
        {
            cellphoneEmptyMessage = true;

        }
        return cellphoneEmptyMessage;

    }
}
