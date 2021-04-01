package com.company.crawl.get;

import com.company.entity.config.ConfigValue;
import com.company.entity.Item;
import com.company.crawl.Crawl;
import com.company.ultils.FileUltil;
import com.google.gson.Gson;

import java.util.ArrayList;

public class GetItem {
    private static Gson gson = new Gson();
    private static Crawl crawl = new Crawl();
    private static final ConfigValue config = new ConfigValue();

    public static void all() {
        ArrayList<Item> itemsList = new ArrayList<>();

        // get urlList
        String urlListJson = FileUltil.readFile("src/main/resources/data/url.json");
        String[] urlList = gson.fromJson(urlListJson, String[].class);

        // crawl items
        for (int i = config.getCurrentCrawledUrlId(); i < urlList.length; i++) {
            try{
                String url = urlList[i];
                // add to itemsList
                itemsList.addAll(crawl.itemsFrom(url));
            } catch (Exception e){
                e.printStackTrace();
                break;
            } finally {
                config.saveCurrentCrawledUrlId(i);
            }
        }

        // convert to json
        String itemsListJson = gson.toJson(itemsList);

        // save json data into item.json
        FileUltil.writeFile("src/main/resources/data/item.json", itemsListJson);

        // close driver
        crawl.closeDriver();

        System.out.println("Rerun to continue crawl from the previous data \n");
    }
}
