package com.example.demo.selenium;
import lombok.Value;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetsPageTest {
    private WebDriver driver;

    @LocalServerPort
    private int port;

    private String base;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "G:\\JProjects\\chromedrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        base = "http://localhost:" + port + "/planets";
    }

    @Test
    public void getPage(){
        driver.get(base);
        assertEquals("Планеты", driver.getTitle());
    }

    @Test
    public void addingPlanet(){
        driver.get(base);
        String stringSizeBefore = driver.findElement(By.id("size")).getText();

        WebElement nameElement = driver.findElement(By.xpath("//input[@id='name']"));
        nameElement.sendKeys("test_planet");

        WebElement buttonElement = driver.findElement(By.xpath("//button[text()='Добавить']"));
        buttonElement.click();

        String stringSizeAfter = driver.findElement(By.id("size")).getText();

        assertNotEquals(stringSizeBefore, stringSizeAfter);
    }

    @Test
    public void destroyPlanet(){
        driver.get(base);
        List<WebElement> trListBefore = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr"));
        WebElement button = driver.findElement(By.xpath("//button[text()='Уничтожить']"));
        button.click();

        List<WebElement> trListAfter = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr"));
        assertNotEquals(trListAfter.size(), trListBefore.size());
    }

    @After
    public void end(){
        driver.close();
    }

}
