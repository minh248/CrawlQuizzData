package com.company.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    public static File configFile = new File("src/main/resources/properties/config.properties");

    public static void main(String[] args) {
        System.out.println(GetBaseUrl.getAll().size());
    }
}