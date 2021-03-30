package com.company.Main;

import com.company.entity.ConfigValue;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class GetBaseUrl {
    private static final ConfigValue config = new ConfigValue();

    public static List<String> all() {
        System.setProperty(config.getDriver(), config.getDriverPath());
        WebDriver driver = new ChromeDriver();

        // store
        List<String> baseUrlList = new ArrayList<>();

        // get to quizzes url
        driver.get("https://www.funtrivia.com/quizzes");

        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/a"));

        for (WebElement element : elements) {
            try {
                baseUrlList.add(element.getAttribute("href"));
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        driver.close();

        return baseUrlList;
    }
}