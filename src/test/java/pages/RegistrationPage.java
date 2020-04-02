package pages;

import io.cucumber.datatable.DataTable;
import utils.RegisterUtil;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.awaitility.Awaitility.await;

public class RegistrationPage extends BasePage{

    private RegisterUtil registerUtil;
    private WebDriver webDriver;
    private WebElement termsCheckbox;
    private WebDriverWait wait;
    private By divLogin = By.className("fb-masthead-login");
    private By loginOverlay = By.className("Modal__modalcontent__2yJz6");
    private By registerText = By.className("Login__createAccount__38c2o");
    private By registerForm = By.id("contRegistro");
    private By user = By.id("user");
    private By fatherLastName = By.id("apellidopaterno");
    private By motherLastName = By.id("apellidomaterno");
    private By mail = By.id("mail");
    private By password = By.id("clave1");
    private By confirmPass = By.id("clave2");
    private By documentNum = By.id("cedula_colombia");
    private By status = By.id("status");
    private By document = By.id("cedula");
    private By day = By.id("day");
    private By month = By.id("month");
    private By year = By.id("year");
    private By agreeTermsCheckBox = By.id("agreelegaleId");
    private By saveButton = By.id("boton_Ar");
    private By emptyCellphoneMessage = By.id("mensajeCelVacio");
    private By notMatchingPasswords = By.id("mensajeClave2Incorrecto2");
    private By findFemaleButton = By.xpath("//*[@class='sexo' and text()='Mujer']");

    public RegistrationPage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,Long.parseLong("5"));
    }

    public void processDataTable(DataTable fields)
    {
        registerUtil = new RegisterUtil(fields);
    }

    public void searchRegister ()
    {
        webDriver.findElement(divLogin).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginOverlay));
        webDriver.findElement(registerText).findElement(By.tagName("a")).click();
    }

    public void insertName()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerForm));
        clickAndSendData(user, registerUtil.getName());

    }

    public void insertFatherLastName()
    {
        clickAndSendData(fatherLastName, registerUtil.getFirstLastName());
    }

    public void insertMotherLastName()
    {
        clickAndSendData(motherLastName, registerUtil.getSecondLastName());
    }

    public void emailInsert()
    {
        clickAndSendData(mail, registerUtil.getEmail());
    }

    public void passwordAndConfirmationInsert()
    {
        clickAndSendData(password, registerUtil.getPassword());
        clickAndSendData(confirmPass, registerUtil.getConfirmPassword());
    }

    public void selectDropDowns()
    {
        new Select(webDriver.findElement(status)).selectByVisibleText(registerUtil.getCountry());
        new Select(webDriver.findElement(document)).selectByValue(registerUtil.getDocumentType());
        new Select(webDriver.findElement(day)).selectByVisibleText(registerUtil.getDay());
        new Select(webDriver.findElement(month)).selectByVisibleText(registerUtil.getMonth());
        new Select(webDriver.findElement(year)).selectByVisibleText(registerUtil.getYear());
    }

    public void documentNumInsert()
    {
        clickAndSendData(documentNum, registerUtil.getDocumentNum());
    }

    public void genderSelection()
    {
        webDriver.findElement(findFemaleButton).click();
    }

    public void checkAgreedTerms()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(agreeTermsCheckBox));
        termsCheckbox = webDriver.findElement(agreeTermsCheckBox);
        await().atMost(Duration.TEN_SECONDS)
                .pollInterval(Duration.ONE_SECOND)
                .until(() -> {
                            termsCheckbox.click();
                            return termsCheckbox.isSelected();
                        }
                );
    }

    public void insertData()
    {
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

    public void saveButtonClick()
    {
        webDriver.findElement(saveButton).click();
    }

    public boolean warningFieldsDisplayed(By emptyMessageLocator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyMessageLocator));
        boolean emptyField = false;
        if(webDriver.findElement(emptyMessageLocator).isDisplayed())
        {
            emptyField = true;

        }
        return emptyField;
    }

    public boolean verifyNumbersInNameAndLastNames()
    {
        boolean verifyNumsInNameAndLastName = false;
        String nameText = webDriver.findElement(user).getAttribute("value");
        String fatherLastNameText = webDriver.findElement(fatherLastName).getAttribute("value");
        String motherLastNameText = webDriver.findElement(motherLastName).getAttribute("value");
        if(nameText.matches(".*\\d.*") || fatherLastNameText.matches(".*\\d.*")
                || motherLastNameText.matches(".*\\d.*"))
        {
            verifyNumsInNameAndLastName = true;
        }
        return verifyNumsInNameAndLastName;
    }

    public boolean registrationCellphoneNotFound()
    {
        return warningFieldsDisplayed(emptyCellphoneMessage);
    }

    public boolean notMatchingPasswordsMessage()
    {
        return warningFieldsDisplayed(notMatchingPasswords);
    }
}
