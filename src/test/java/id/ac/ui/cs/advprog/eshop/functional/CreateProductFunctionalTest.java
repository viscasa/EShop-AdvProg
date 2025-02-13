package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
class CreateProductFunctionalTest {

    /**
     * The port number assigned to the running application during test execution.
     * Automatically assigned during each test run by Spring's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL used for testing. Defaults to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_andVerifyInProductList(ChromeDriver driver) throws Exception {
        // Navigate to the "Create Product" page
        driver.get(baseUrl + "/product/create");

        // Verify we are on the correct page
        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);

        // Fill in the form fields
        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys("New Test Product"); // Example product name

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys("10"); // Example quantity

        // Submit the form
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Verify redirect happens to the product list page
        String redirectedPageTitle = driver.getTitle();
        assertEquals("Product List", redirectedPageTitle);

        // Verify the new product appears in the list
        List<WebElement> productNames = driver.findElements(By.cssSelector("table tbody tr td:nth-child(1)")); // First column is product name
        List<WebElement> productQuantities = driver.findElements(By.cssSelector("table tbody tr td:nth-child(2)")); // Second column is quantity

        boolean newProductFound = false;
        for (int i = 0; i < productNames.size(); i++) {
            System.out.println(productNames.get(i).getText());
            System.out.println(productQuantities.get(i).getText());
            if (productNames.get(i).getText().equals("New Test Product") &&
                    productQuantities.get(i).getText().equals("10")) {
                newProductFound = true;
                break;
            }
        }

        assertTrue(newProductFound, "Newly created product should appear in the product list.");
    }
}