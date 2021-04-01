package com.company.main;

import com.company.entity.config.ConfigValue;
import com.company.ultils.FileUltil;

public class Reset {
    public static void main(String[] args) {
        // reset data
        FileUltil.writeFile("src/main/resources/data/url.json", "[]");
        FileUltil.writeFile("src/main/resources/data/item.json", "[]");
        FileUltil.writeFile("src/main/resources/data/question.json", "[]");

        // reset saved id
        ConfigValue config = new ConfigValue();
        config.saveCurrentUrlId(0);
        config.saveCurrentCrawledUrlId(0);
        config.saveCurrentCrawledItemId(0);
    }
}
