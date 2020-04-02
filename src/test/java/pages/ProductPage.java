package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ProductPage extends BasePage
{
    private boolean verifyProductPageRedirection = false;
    private ExpectedCondition<Boolean> pageLoadCondition;
    private String firstMatchText, objectNameText;
    private WebDriver webDriver;
    private WebDriverWait wait;
    private List<WebElement> productResults;
    private By productsCatalog = By.id("testId-searchResults-products");
    private By priceButton = By.id("testId-Accordion-Precio");
    private By applyFilterButton = By.className("jsx-3084763500");
    private By minPrice = By.id("testId-range-from");
    private By maxPrice = By.id("testId-range-to");
    private By disabledApplyFilterButton = By.xpath("//*[@class='jsx-3084763500' and @disabled]");
    private By productPageLoad = By.xpath("//*[@class='jsx-1987097504 main']");
    private By appliedFiltersField = By.xpath("//*[@class='jsx-2293612498 selected-filters']");
    private By wrongPriceRange = By.xpath("//*[@class='jsx-3084763500 error-text']");
    private By productResultList = By.xpath("//*[@class='jsx-1395131234 search-results-4-grid']");
    private By sellingBrand = By.xpath("//*[@class='jsx-3572928369 product-brand fa--brand']");
    private By objectName = By.xpath("//*[@class='jsx-3686231685 product-name fa--product-name']");
    private By addToCartButton = By.xpath("//*[@id='buttonForCustomers']//button[@class]");
    private By popUpAddedToCart = By.xpath("//*[@class='jsx-3049166186 popup small']");
    private By confirmProductAdded = By.xpath("//*[@class='jsx-351245194 item-info']");
    private By goToShoppingBagButton = By.xpath("//*[@class='jsx-3049166186 sub-footer']//div[@class='jsx-3049166186 addToCart-btn']");
    private By shoppingCart = By.xpath("//*[@id='testId-userActions-basket']" +
            "//div[@class='jsx-2422992112 content-wrapper']//a[@class='jsx-2422992112']//i[@data-count]");

    public ProductPage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,Long.parseLong("15"));
    }

    public boolean confirmSearchPage(String searchProduct)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsCatalog));
        productResults = webDriver.findElements(productResultList);
        firstMatchText = productResults.get(0).getText().toLowerCase();
        if(firstMatchText.contains(searchProduct))
        {
            verifyProductPageRedirection = true;
        }
        return verifyProductPageRedirection;
    }

    public boolean apply(WebDriver driver)
    {
        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
    }

    public void priceButtonClick()
    {
        wait.until(this::apply);
        webDriver.findElement(priceButton).click();
    }

    public void priceFilter(String minimumPrice, String maximumPrice)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(minPrice));
        wait.until(ExpectedConditions.visibilityOfElementLocated(maxPrice));
        clickAndSendData(minPrice, minimumPrice);
        clickAndSendData(maxPrice, maximumPrice);
        webDriver.findElement(applyFilterButton).findElement(By.tagName("i")).click();
    }

    public boolean priceFilterApplied()
    {
        boolean priceFilterApplied = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(appliedFiltersField));
        if (webDriver.findElement(appliedFiltersField).isDisplayed())
        {
            priceFilterApplied = true;
        }
        return priceFilterApplied;
    }

    public boolean wrongPriceFilterRange()
    {
        boolean verifyPriceRange = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(wrongPriceRange));
        if(webDriver.findElement(wrongPriceRange).isDisplayed())
        {
            verifyPriceRange = true;
        }
        return verifyPriceRange;
    }

    public boolean disabledApplyFilterButton()
    {
        boolean verifyDisabledApplyFilterButton = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(disabledApplyFilterButton));
        if(webDriver.findElement(disabledApplyFilterButton).isDisplayed())
        {
            verifyDisabledApplyFilterButton = true;
        }
        return verifyDisabledApplyFilterButton;
    }

    public void clickFirstMatch()
    {
        if(verifyProductPageRedirection) {productResults.get(0).click();}
    }

    public boolean confirmFirstMatchPage()
    {
        boolean verifyInsideTheProductPage = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPageLoad));
        String sellingBrandText = webDriver.findElement(sellingBrand).getText().toLowerCase();
        objectNameText = webDriver.findElement(objectName).getText().toLowerCase();
        if(firstMatchText.contains(sellingBrandText) && firstMatchText.contains(objectNameText))
        {
            verifyInsideTheProductPage = true;
        }
        return verifyInsideTheProductPage;
    }

    public void clickAddToCartButton()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        webDriver.findElement(addToCartButton).click();
    }

    public boolean productAddedToCart()
    {
        boolean verifyProductAddedToCart = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUpAddedToCart));
        String confirmProductAddedText = webDriver.findElement(confirmProductAdded).getText().toLowerCase();
        String shoppingCartIcon = webDriver.findElement(shoppingCart).getAttribute("data-count");
        if(confirmProductAddedText.contains(objectNameText) && !shoppingCartIcon.equals("0"))
        {
            verifyProductAddedToCart = true;
        }
        return verifyProductAddedToCart;
    }

    public void clickGoToShoppingBag()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUpAddedToCart));
        webDriver.findElement(goToShoppingBagButton).click();
    }
}
