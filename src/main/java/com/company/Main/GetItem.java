package com.company.Main;

import com.company.entity.Item;
import com.company.method.Crawl;
import com.company.method.FileUltil;
import com.google.gson.Gson;

import java.time.LocalTime;
import java.util.ArrayList;

import static com.company.method.Crawl.*;

public class GetItem {
    private static Gson gson = new Gson();
    private static Crawl crawl = new Crawl();

    public static void main(String[] args) {
        ArrayList<Item> itemsList = new ArrayList<>();

        // get urlList
        String urlListJson = FileUltil.readFile("src/main/resources/data/url.json");
        String[] urlList = gson.fromJson(urlListJson, String[].class);

        // crawl items
        for (String url : urlList) {
            // add to itemsList
            itemsList.addAll(crawl.itemsFrom(url));
        }

        // save items to json file
        String itemsListJson = gson.toJson(itemsList);
        FileUltil.writeFile("src/main/resources/data/item.json", itemsListJson);

        // close driver
        crawl.closeDriver();
    }
}
