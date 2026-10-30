package org.example.module4_1;

import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.example.module4_1.CustomWait.customWait;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        String input = "Selenium";
        By linkSite = By.xpath("//div[@class='tpmeta']/div/cite"); //заменил локатор, т.к. изначально задание выполнялось при включенном впн и bing видимо настроился под страну их впн
        WebElement searchField = customWait(driver, 10).clickable(By.xpath("//input[@id = 'sb_form_q']"));
        searchField.sendKeys(input);
        searchField.submit();
        List<WebElement> results = driver.findElements(linkSite);
        results.set(0, customWait(driver, 10).visible(linkSite));
        clickSearch(results, 0);
        ArrayList tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) driver.switchTo().window(tabs.get(1).toString());
        customWait(driver, 5).visible(By.xpath("//h2[contains(text(),'Install Selenium and run your first script')]"));
        Assertions.assertEquals(driver.getCurrentUrl(), "https://www.selenium.dev/", "URL не совпадает");
    }

    public void clickSearch(List<WebElement> results, int num) {
        results.get(num).click();
    }
}
