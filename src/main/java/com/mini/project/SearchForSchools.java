package com.mini.project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

//import java.util.*;
//import java.util.concurrent.TimeUnit;

public class SearchForSchools {

//    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
        // Set the path for the browser driver executable
        String driverPath = "S:\\Eclipse\\project\\project\\resource\\chromedriver.exe";

        // Choose the browser to use
        WebDriver driver = chooseBrowser("chrome", driverPath);

        // Navigate to the Eduvidya website
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.eduvidya.com/");
        
        WebElement menuBtn = driver.findElement(By.xpath("//*[@id='menu-button']"));
        menuBtn.click();
        Thread.sleep(3000);
        
        // Click on the "Schools" link in the menu bar
        WebElement schoolsLink = driver.findElement(By.linkText("Schools"));
        schoolsLink.click();

        // Select "CBSE" from the "Course-Type" dropdown
        WebElement courseTypeDropdown = driver.findElement(By.id("s2id_autogen2"));
        courseTypeDropdown.click();

        Select courseTypeSelect = new Select(courseTypeDropdown.findElement(By.xpath("//input[@class='select2-input select2-default']")));
        courseTypeSelect.selectByVisibleText("CBSE");

        // Select "Pune" from the "City" dropdown
        WebElement cityDropdown = driver.findElement(By.id("s2id_autogen3"));
        cityDropdown.click();

        Select citySelect = new Select(cityDropdown.findElement(By.xpath("//input[@class='select2-input select2-default']")));
        citySelect.selectByVisibleText("Pune");

        // Click on the "Search" button
        WebElement searchButton = driver.findElement(By.id("search_schools"));
        searchButton.click();

        // Verify that the search results are displayed
        WebElement searchResults = driver.findElement(By.xpath("//div[@class='search-results-title']"));
        assert searchResults.isDisplayed();

        // Close the browser
//        driver.quit();
    }

    public static WebDriver chooseBrowser(String browser, String driverPath) {
//    	Scanner sc= new Scanner(System.in);
//    	System.out.println("Enter the Browser name");
//    	browser=sc.nextLine();
        switch (browser.toLowerCase()) {
//            case "edge":
//                System.setProperty("webdriver.edge.driver", driverPath);
//                return new EdgeDriver();
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driverPath);
                return new ChromeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", driverPath);
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

}
