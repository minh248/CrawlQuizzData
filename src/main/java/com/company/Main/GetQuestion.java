package com.company.Main;

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

    public static void main(String[] args) {
        List<Question> questionList = new ArrayList<>();

        // set max request in each item
        // 1 request take at least 12.5 second so 100 request take about 3h30
        final int MAX_REQUEST = 100;
        final int START_REQUEST_INDEX = 0;

        // get item list
        String itemJson = FileUltil.readFile("src/main/resources/data/item.json");
        Item[] itemList = gson.fromJson(itemJson, Item[].class);

        // crawl loop
        for (int i = START_REQUEST_INDEX; i < START_REQUEST_INDEX + MAX_REQUEST; i++) {
            questionList.addAll(crawl.questionsFrom(itemList[i].getUrl(), itemList[i].getId()));
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