package com.company.entity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigValue {
    // value
    private String url;
    private String driver;
    private String driverPath;

    // file config
    private Properties properties;
    private static final File configFile = new File("src/main/resources/properties/config.properties");

    public ConfigValue() {
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            // set url
            try {
                this.url = props.getProperty("url");
                this.driver = props.getProperty("driver");
                this.driverPath = props.getProperty("driverPath");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // close
                reader.close();

            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
