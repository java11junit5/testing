package com.java11junit5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConstants {

    private static Properties props = getProps();

    public static final String HOST = props.getProperty("HOST");
    public static final String PORT = props.getProperty("PORT");

    public static final String USERNAME = props.getProperty("DEFAULT_USERNAME");
    public static final String PASSWORD = props.getProperty("DEFAULT_PASSWORD");
    public static final String TOKEN = props.getProperty("DEFAULT_TOKEN");

    private static Properties getProps() {

        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

}

