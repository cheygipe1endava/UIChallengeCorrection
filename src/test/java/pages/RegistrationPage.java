package pages;

import cucumber.api.DataTable;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import static org.awaitility.Awaitility.await;

public class RegistrationPage extends BasePage{

    private WebDriver webDriver;
    private WebElement termsCheckbox;
    private WebDriverWait wait;
    private List<List<String>> dataTable;
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
    private By findFemaleButton = By.xpath("//*[@id='tipodireccion_0' and @value='female']");

    public RegistrationPage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,Long.parseLong("5"));
    }

    public void processDataTable(DataTable fields)
    {
        dataTable = fields.raw();
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
        clickAndSendData(user, dataTable.get(0).get(1));
    }

    public void insertFatherLastName()
    {
        clickAndSendData(fatherLastName, dataTable.get(1).get(1));
    }

    public void insertMotherLastName()
    {
        clickAndSendData(motherLastName, dataTable.get(2).get(1));
    }

    public void emailInsert()
    {
        clickAndSendData(mail, dataTable.get(3).get(1));
    }

    public void passwordAndConfirmationInsert()
    {
        clickAndSendData(password, dataTable.get(4).get(1));
        clickAndSendData(confirmPass, dataTable.get(5).get(1));
    }

    public void selectDropDowns()
    {
        new Select(webDriver.findElement(status)).selectByVisibleText(dataTable.get(6).get(1));
        new Select(webDriver.findElement(document)).selectByValue(dataTable.get(7).get(1));
        new Select(webDriver.findElement(day)).selectByVisibleText(dataTable.get(9).get(1));
        new Select(webDriver.findElement(month)).selectByVisibleText(dataTable.get(10).get(1));
        new Select(webDriver.findElement(year)).selectByVisibleText(dataTable.get(11).get(1));
    }

    public void documentNumInsert()
    {
        clickAndSendData(documentNum, dataTable.get(8).get(1));
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

    public boolean registrationCellphoneNotFound()
    {
        boolean cellphoneEmptyMessage = false;
        if(webDriver.findElement(emptyCellphoneMessage).isDisplayed())
        {
            cellphoneEmptyMessage = true;

        }
        return cellphoneEmptyMessage;
    }
}
