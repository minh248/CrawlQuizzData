package com.company.Main;

import com.company.entity.ConfigValue;
import com.company.method.FileUltil;
import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.company.method.FileUltil.writeFile;

public class GetUrl {
    private static final ConfigValue config = new ConfigValue();
    private static final int MAX_REQUEST = 100000000;
    private static final Gson gson = new Gson();
    private static final List<String> baseUrlList = GetBaseUrl.all();

    public static void all() {
        System.setProperty(config.getDriver(), config.getDriverPath());
        WebDriver driver = new ChromeDriver();
        Gson gson = new Gson();

        // store
        List<String> urlList = new ArrayList<>();

        String urlListJson = FileUltil.readFile("src/main/resources/data/url.json");

        // check previous data
        if (urlListJson == null || urlListJson.equals("[]")) {
            // get url from base url
            for (String baseUrl : baseUrlList) {
                driver.get(baseUrl);
                List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[5]/div/div/div[2]/div/b/a"));
                for (WebElement element : elements) {
                    String newUrl = element.getAttribute("href");
                    // check exist
                    if (!urlList.contains(newUrl)){
                        urlList.add(newUrl);
                    }
                }
            }
        }
        else {
            // load save data
            Collections.addAll(urlList, gson.fromJson(urlListJson, String[].class));
        }

        // continue crawl inside
        for (int i = config.getCurrentUrlId(); i < MAX_REQUEST; i++) {
            try {
                String url = urlList.get(i);

                driver.navigate().to(url);
                List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[5]/div/div/div[2]/div/b/a"));
                for (WebElement element : elements) {
                    String newUrl = element.getAttribute("href");
                    // check existing
                    if (urlList.contains(newUrl)){
                        continue;
                    }
                    urlList.add(newUrl);
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            } finally {
                // save current id
                config.saveCurrentUrlId(i);
            }
        }

        urlListJson = gson.toJson(urlList);

        // save to json file
        writeFile("src/main/resources/data/url.json", urlListJson);

        // close drive
        driver.close();
    }
}