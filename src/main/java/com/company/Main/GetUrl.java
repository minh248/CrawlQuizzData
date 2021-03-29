package com.company.Main;

import com.company.entity.ConfigValue;
import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static com.company.method.FileUltil.writeFile;

public class GetUrl {
    private static final ConfigValue config = new ConfigValue();

    public static void main(String[] args) {
        System.setProperty(config.getDriver(), config.getDriverPath());
        WebDriver driver = new ChromeDriver();

        // set base url, max url can request
        String baseUrl = "https://www.funtrivia.com/quizzes/animals/";
        final int MAX_REQUEST = 200;

        // create list to store url
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add(baseUrl);

        for (int i = 0; i < MAX_REQUEST; i++) {
            try {
                String currentUrl = urlList.get(i);

                driver.get(currentUrl);
                List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[5]/div/div/div[2]/div/b/a"));
                for (WebElement element : elements) {
                    urlList.add(element.getAttribute("href"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        // remove base url if there're random questions in base url
        urlList.remove(baseUrl);

        Gson gson = new Gson();
        String urlListJson = gson.toJson(urlList);

        // save to json file
        writeFile("src/main/resources/data/url.json", urlListJson);

        // close drive
        driver.close();
    }
}
