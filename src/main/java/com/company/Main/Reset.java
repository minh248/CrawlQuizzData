package com.company.Main;

import com.company.entity.ConfigValue;
import com.company.method.FileUltil;

public class Reset {
    public static void main(String[] args) {
        FileUltil.writeFile("src/main/resources/data/url.json", "[]");
        FileUltil.writeFile("src/main/resources/data/item.json", "[]");
        FileUltil.writeFile("src/main/resources/data/question.json", "[]");

        ConfigValue config = new ConfigValue();
        config.saveCurrentUrlId(0);
        config.saveCurrentCrawledUrlId(0);
        config.saveCurrentCrawledItemId(0);
    }
}
