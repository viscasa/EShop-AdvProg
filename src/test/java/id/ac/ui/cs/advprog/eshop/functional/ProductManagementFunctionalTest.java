package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class ProductManagementFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void fullProductManagementFlow(ChromeDriver driver) {
        // Step 1: Verify product list is empty
        driver.get(baseUrl + "/product/list");
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector(".btn-danger"));
        for (WebElement deleteButton : deleteButtons) {
            deleteButton.click();

            // Handle confirmation dialog (if there is one)
            driver.switchTo().alert().accept();
        }

        String pageTitle = driver.getTitle();
        assertEquals("Product List", pageTitle);

        List<WebElement> productRows = driver.findElements(By.cssSelector("table tbody tr"));
        assertTrue(productRows.isEmpty(), "Product list should be empty initially.");

        // Step 2: Add first product
        driver.get(baseUrl + "/product/create");
        addProduct(driver, "Product One", "50");
        driver.get(baseUrl + "/product/list");
        assertTrue(isProductPresent(driver, "Product One", "50"), "Product One should appear in the list.");

        // Step 3: Add second product
        driver.get(baseUrl + "/product/create");
        addProduct(driver, "Product Two", "100");
        driver.get(baseUrl + "/product/list");
        assertTrue(isProductPresent(driver, "Product Two", "100"), "Product Two should appear in the list.");

        // Step 4: Edit first product
        editProduct(driver, "Product One", "Updated Product One", "60");
        driver.get(baseUrl + "/product/list");
        assertTrue(isProductPresent(driver, "Updated Product One", "60"), "First product should be updated.");

        // Step 5: Edit second product
        editProduct(driver, "Product Two", "Updated Product Two", "150");
        driver.get(baseUrl + "/product/list");
        assertTrue(isProductPresent(driver, "Updated Product Two", "150"), "Second product should be updated.");

        // Step 6: Delete first product
        deleteProduct(driver, "Updated Product One");
        driver.get(baseUrl + "/product/list");
        assertTrue(!isProductPresent(driver, "Updated Product One", "60"), "First product should be deleted.");
    }

    private void addProduct(ChromeDriver driver, String name, String quantity) {
        // Fill out the create product form
        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys(name);

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys(quantity);

        // Submit the form
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
    }

    private void editProduct(ChromeDriver driver, String currentName, String newName, String newQuantity) {
        driver.get(baseUrl + "/product/list");

        // Find the row with the current product name
        WebElement productRow = findProductRow(driver, currentName);
        WebElement editButton = productRow.findElement(By.cssSelector(".btn-warning"));
        editButton.click();

        // Edit the product
        WebElement productNameInput = driver.findElement(By.id("productName"));
        productNameInput.clear();
        productNameInput.sendKeys(newName);

        WebElement productQuantityInput = driver.findElement(By.id("productQuantity"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys(newQuantity);

        // Submit the form
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
    }

    private void deleteProduct(ChromeDriver driver, String name) {
        driver.get(baseUrl + "/product/list");

        // Find the row with the product name and click the delete button
        WebElement productRow = findProductRow(driver, name);
        WebElement deleteButton = productRow.findElement(By.cssSelector(".btn-danger"));
        deleteButton.click();

        // Confirm deletion (if there's a confirmation dialog)
        driver.switchTo().alert().accept();
    }

    private WebElement findProductRow(ChromeDriver driver, String name) {
        List<WebElement> productRows = driver.findElements(By.cssSelector("table tbody tr"));
        for (WebElement row : productRows) {
            WebElement nameColumn = row.findElement(By.cssSelector("td:nth-child(1)"));
            if (nameColumn.getText().equals(name)) {
                return row; // Return the row if name matches
            }
        }
        throw new RuntimeException("Product with name '" + name + "' not found.");
    }

    private boolean isProductPresent(ChromeDriver driver, String name, String quantity) {
        List<WebElement> productRows = driver.findElements(By.cssSelector("table tbody tr"));
        for (WebElement row : productRows) {
            String nameText = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            String quantityText = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            if (nameText.equals(name) && quantityText.equals(quantity)) {
                return true;
            }
        }
        return false;
    }
}