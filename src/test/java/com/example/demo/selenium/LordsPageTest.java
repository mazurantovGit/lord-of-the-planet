package com.example.demo.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LordsPageTest {

    private WebDriver driver;

    @LocalServerPort
    private int port;

    private String base;


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "G:\\JProjects\\chromedrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        base = "http://localhost:" + port + "/lords";
    }

    @Test
    public void getPage(){
        driver.get(base);
        assertEquals("Повелители", driver.getTitle());
    }

    @Test
    public void addingLord(){
        driver.get(base);
        String stringSizeBefore = driver.findElement(By.id("size")).getText(); //Поиск строки с размером листа

        WebElement nameElement = driver.findElement(By.xpath("//input[@name='name']")); //Поиск строки ввода имени
        WebElement ageElement = driver.findElement(By.xpath("//input[@name='age']")); //Поиск строки ввода возраста"
        WebElement buttonAddElement = driver.findElement(By.xpath("//button[text()='Добавить']")); //Поиск кнопки лобавления

        nameElement.sendKeys("test_name"); //Ввод имени
        ageElement.sendKeys(Keys.BACK_SPACE,"15"); //Ввод возраста
        buttonAddElement.click();   //клац, добавляем еще один элемент

        String stringSizeAfter = driver.findElement(By.id("size")).getText(); //Поиск строки с размером листа

        assertNotEquals(stringSizeBefore, stringSizeAfter); // изменился ли текст с размерностю листа
    }

    @Test
    public void capturePlanet(){
        driver.get(base);
        List<WebElement> optionListBefore = driver.findElements(By.xpath("//form[@action='lords/rule/4']/select/option")); //список элементов в выпадающем списке

        Select freePlanets = new Select(driver.findElement(By.xpath("//form[@action='lords/rule/4']/select")));
        freePlanets.selectByIndex(1); //выбираем первый

        WebElement button = driver.findElement(By.xpath("//button[text()='Захватить планету']"));
        button.click(); //клац, выбранная планета перестала быть свободной

        List<WebElement> optionListAfter = driver.findElements(By.xpath("//form[@action='lords/rule/4']/select/option"));
        assertNotEquals(optionListAfter.size(), optionListBefore.size()); //проверяем размерность списков после-до
    }

    @Test
    public void getFilterLonelyLords(){
        driver.get(base);
        WebElement button = driver.findElement(By.xpath("//button[text()='Без планет']"));
        button.click();
        assertEquals(base + "/lonelylords?", driver.getCurrentUrl());
    }

    @Test
    public void getFilterYoungestLords(){
        driver.get(base);
        WebElement button = driver.findElement(By.xpath("//button[text()='Самые молодые']"));
        button.click();
        assertEquals(base + "/youngestlords?", driver.getCurrentUrl());
    }


    @After
    public void end(){
        driver.close();
    }

}
