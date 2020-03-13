package pages;

import helper.PropertiesHelper;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.awaitility.Awaitility.await;

public class RegistrationPage extends BasePage{
    private WebDriver webDriver;
    private WebElement termsCheckbox;
    private WebDriverWait wait;
    private By user = By.id("user");
    private By fatherLastName = By.id("apellidopaterno");
    private By motherLastName = By.id("apellidomaterno");
    private By mail = By.id("mail");
    private By password = By.id("clave1");
    private By confirmPass = By.id("clave2");
    private By documentNum = By.id("cedula_colombia");
    private By findFemaleButton = By.xpath("//*[@id='tipodireccion_0' and @value='female']");

    public RegistrationPage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,Long.parseLong("5"));
    }

    public void searchRegister ()
    {
        webDriver.findElement(By.className("fb-masthead-login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Modal__modalcontent__2yJz6")));
        webDriver.findElement(By.className("Login__createAccount__38c2o")).findElement(By.tagName("a")).click();
    }

    public void insertName()
    {
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
        new Select(webDriver.findElement(By.id("status"))).selectByVisibleText("Colombia");
        new Select(webDriver.findElement(By.id("cedula"))).selectByValue("CEDULA_DE_CIUDADANIA");
        new Select(webDriver.findElement(By.id("day"))).selectByVisibleText("5");
        new Select(webDriver.findElement(By.id("month"))).selectByVisibleText("May");
        new Select(webDriver.findElement(By.id("year"))).selectByVisibleText("1990");
    }

    public void documentNumInsert()
    {
        clickAndSendData(documentNum, PropertiesHelper.getValueByKey("documentNumber"));
    }

    public void genderSelection()
    {
        webDriver.findElement(findFemaleButton).click();
    }

    public void checkAgreedTerms()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("agreelegaleId")));
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

    public void saveButtonClick()
    {
        webDriver.findElement(By.id("boton_Ar")).click();
    }

    public boolean registrationCellphoneNotFound()
    {
        boolean cellphoneEmptyMessage = false;
        if(webDriver.findElement(By.id("mensajeCelVacio")).isDisplayed())
        {
            cellphoneEmptyMessage = true;

        }
        return cellphoneEmptyMessage;
    }
}
