package com.company.entity;

import java.io.*;
import java.util.Properties;

public class ConfigValue {
    // value
    private String driver;
    private String driverPath;
    // save index
    private int currentUrlId;
    private int currentCrawledUrlId;
    private int currentCrawledItemId;

    // file config
    private Properties properties;
    private static final File configFile = new File("src/main/resources/properties/config.properties");

    public ConfigValue() {
        try {
            InputStream input = new FileInputStream(configFile);
            Properties props = new Properties();
            props.load(input);

            // set value
            this.driver = props.getProperty("driver");
            this.driverPath = props.getProperty("driverPath");
            this.currentUrlId = Integer.parseInt(props.getProperty("current.url.id"));
            this.currentCrawledUrlId = Integer.parseInt(props.getProperty("current.crawled.url.id"));
            this.currentCrawledItemId = Integer.parseInt(props.getProperty("current.crawled.item.id"));

            // close
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCurrentUrlId(int i) {
        try {
            InputStream in = new FileInputStream(configFile);
            Properties props = new Properties();

            props.load(in);
            props.setProperty("current.url.id",String.valueOf(i));

            in.close();


            OutputStream out = new FileOutputStream(configFile);
            props.store(out, null);

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCurrentCrawledUrlId(int i) {
        try {
            InputStream in = new FileInputStream(configFile);
            Properties props = new Properties();

            props.load(in);
            props.setProperty("current.crawled.url.id",String.valueOf(i));

            in.close();


            OutputStream out = new FileOutputStream(configFile);
            props.store(out, null);

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCurrentCrawledItemId(int i) {
        try {
            InputStream in = new FileInputStream(configFile);
            Properties props = new Properties();

            props.load(in);
            props.setProperty("current.crawled.item.id",String.valueOf(i));

            in.close();


            OutputStream out = new FileOutputStream(configFile);
            props.store(out, null);

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public int getCurrentUrlId() {
        return currentUrlId;
    }

    public void setCurrentUrlId(int currentUrlId) {
        this.currentUrlId = currentUrlId;
    }

    public int getCurrentCrawledUrlId() {
        return currentCrawledUrlId;
    }

    public void setCurrentCrawledUrlId(int currentCrawledUrlId) {
        this.currentCrawledUrlId = currentCrawledUrlId;
    }

    public int getCurrentCrawledItemId() {
        return currentCrawledItemId;
    }

    public void setCurrentCrawledItemId(int currentCrawledItemId) {
        this.currentCrawledItemId = currentCrawledItemId;
    }
}
