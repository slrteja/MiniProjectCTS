package miniProject.SearchForSchools;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MiniProject_2263291_SearchForSchools {
    private static final Logger LOGGER = Logger.getLogger(MiniProject_2263291_SearchForSchools.class.getName());
    private static String browser;
    private static String driverPath;

    public static WebDriver chooseBrowser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Browser available: Chrome / Edge");
        System.out.print("Enter the Browser :" );
        browser = sc.nextLine();
        switch (browser.toLowerCase()) {
            case "edge":
                driverPath = "C:\\Users\\2263291\\Downloads\\workspace\\SearchForSchools\\resource\\msedgedriver.exe";
                System.setProperty("webdriver.edge.driver", driverPath);
                return new EdgeDriver();
            case "chrome":
                driverPath = "C:\\Users\\2263291\\Downloads\\workspace\\SearchForSchools\\resource\\chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", driverPath);
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    	public static void closeAdvertisement(WebDriver driver) {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    try {
    	        WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dismiss-button")));
    	        closeButton.click();
    	    } catch (TimeoutException e) {
    	        // If the element is not found within the timeout, do nothing and continue with the rest of the code
    	        LOGGER.info("No advertisement popup found");
    	    }
    	}

public static void searchForSchools(WebDriver driver) {
    // Click on the "Schools" link in the menu bar
    WebElement schoolsLink = driver.findElement(By.xpath("//*[@id='cssmenu']/ul/li[4]/a"));
    schoolsLink.click();
    LOGGER.info("Clicked on Schools link");

    // Select "CBSE" from the "Course-Type" dropdown
    WebElement courseTypeDropdown = driver.findElement(By.id("ddl_Category"));
    Select courseTypeSelect = new Select(courseTypeDropdown);
    courseTypeSelect.selectByVisibleText("CBSE");
    LOGGER.info("Selected CBSE from Course-Type dropdown");

    // Select "Pune" from the "City" dropdown
    WebElement cityDropdown = driver.findElement(By.id("ddl_City"));
    Select citySelect = new Select(cityDropdown);
    citySelect.selectByVisibleText("Pune");
    LOGGER.info("Selected Pune from City dropdown");

    // Click on the "Search" button
    WebElement searchButton = driver.findElement(By.id("btnSearch"));
    searchButton.click();
    LOGGER.info("Clicked on Search button");

    // Verify that the search results are displayed
    WebElement searchResults = driver.findElement(By.className("detail-list"));
    assert searchResults.isDisplayed();
    LOGGER.info("Search results are displayed: " + searchResults.getText());
}

public static void main(String[] args) throws InterruptedException {
    // Set the path for the browser driver executable
    // Choose the browser to use
    WebDriver driver = chooseBrowser();
    LOGGER.info("Using browser: " + driver.getClass().getSimpleName());

    // Navigate to the Eduvidya website
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get("https://www.eduvidya.com/");
    

    // Close advertisement popup
    closeAdvertisement(driver);

    // Search for schools
    searchForSchools(driver);

    // Close the browser
    driver.quit();
    LOGGER.info("Browser closed successfully");
}
}
