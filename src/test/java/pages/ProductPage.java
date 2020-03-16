package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage extends BasePage
{
    private boolean verifyProductPageRedirection = false;
    private WebDriver webDriver;
    private WebDriverWait wait;
    private List<WebElement> productResults;
    private By productsCatalog = By.id("testId-searchResults-products");
    private By priceButton = By.id("testId-Accordion-Precio");
    private By filtersField = By.className("jsx-4207689641");
    private By applyFilterButton = By.className("jsx-3084763500");
    private By minPrice = By.xpath("//*[@id='testId-range-from']");
    private By maxPrice = By.xpath("//*[@id='testId-range-to']");
    private By appliedFiltersField = By.xpath("//*[@class='jsx-2293612498 selected-filters']");
    private By priceFilterFields = By.xpath("//*[@class='jsx-3109751039 pod-group--filters']");
    private By appliedPriceFilterText = By.xpath("//*[@class='jsx-2293612498 chips']");
    private By productResultList = By.xpath("//*[@class='jsx-1395131234 search-results-4-grid']");

    public ProductPage(WebDriver webDriver)
    {
        super(webDriver);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,Long.parseLong("5"));
    }

    public boolean confirmSearchPage(String searchProduct)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsCatalog));
        productResults = webDriver.findElements(productResultList);
        if(productResults.get(0).getText().toLowerCase().contains(searchProduct))
        {
            verifyProductPageRedirection = true;
        }
        return verifyProductPageRedirection;
    }

    public void clickFirstMatch()
    {
        if(verifyProductPageRedirection) {productResults.get(0).click();}
    }

    public void priceButtonClick()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceFilterFields));
        webDriver.findElement(priceButton).click();
    }

    public void priceFilter(String minimumPrice, String maximumPrice)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(filtersField));
        clickAndSendData(minPrice, minimumPrice);
        clickAndSendData(maxPrice, maximumPrice);
        webDriver.findElement(applyFilterButton).findElement(By.tagName("i")).click();
    }

    public boolean priceFilterApplied(String minimumPrice, String maximumPrice)
    {
        boolean priceFilterApplied = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(appliedFiltersField));
        String getPriceFilterText = webDriver.findElement(appliedPriceFilterText).getText();
        if (getPriceFilterText.contains(minimumPrice) && getPriceFilterText.contains(maximumPrice))
        {
            priceFilterApplied = true;
        }
        return priceFilterApplied;
    }
}
