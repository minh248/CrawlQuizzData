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
    private static final int MAX_REQUEST = 200;

    public static void all() {
        System.setProperty(config.getDriver(), config.getDriverPath());
        WebDriver driver = new ChromeDriver();


        List<String> baseUrlList = GetBaseUrl.all();
        List<String> urlList = new ArrayList<>();

        // get url from base url
        for (String baseUrl : baseUrlList) {
            driver.get(baseUrl);
            List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[5]/div/div/div[2]/div/b/a"));
            for (WebElement element : elements) {
                String newUrl = element.getAttribute("href");
                if (!urlList.contains(newUrl)){
                    urlList.add(newUrl);
                }
            }
        }

        // continue crawl inside
        for (int i = 0; i < MAX_REQUEST; i++) {
            try {
                String url = urlList.get(i);

                driver.get(url);
                List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[5]/div/div/div[2]/div/b/a"));
                for (WebElement element : elements) {
                    String newUrl = element.getAttribute("href");
                    if (!urlList.contains(newUrl)){
                        urlList.add(newUrl);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        Gson gson = new Gson();
        String urlListJson = gson.toJson(urlList);

        // save to json file
        writeFile("src/main/resources/data/url.json", urlListJson);

        // close drive
        driver.close();
    }
}