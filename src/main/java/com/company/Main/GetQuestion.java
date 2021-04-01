package com.company.Main;

import com.company.entity.ConfigValue;
import com.company.entity.Item;
import com.company.entity.Question;
import com.company.method.Crawl;
import com.company.method.FileUltil;
import com.google.gson.Gson;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GetQuestion {
    private static Gson gson = new Gson();
    private static Crawl crawl = new Crawl();
    private static final ConfigValue config = new ConfigValue();

    public static void all() {
        List<Question> questionList = new ArrayList<>();

        // get item list
        String itemJson = FileUltil.readFile("src/main/resources/data/item.json");
        Item[] itemList = gson.fromJson(itemJson, Item[].class);

        // crawl loop
        for (int i = config.getCurrentCrawledItemId(); i < itemList.length; i++) {
            try{
                List<Question> questionListTemporary = crawl.questionsFrom(itemList[i].getUrl(), itemList[i].getId());
                if (questionListTemporary != null) {
                    questionList.addAll(questionListTemporary);
                }
            } catch (Exception e){
                e.printStackTrace();
                break;
            } finally {
                config.saveCurrentCrawledItemId(i);
            }
        }

        // convert to json
        String questionListJson = gson.toJson(questionList);

        // save json data into question.json
        FileUltil.writeFile("src/main/resources/data/question.json", questionListJson);

        //close driver
        crawl.closeDriver();

        // check runtime
        System.out.println(LocalTime.now());
    }
}
