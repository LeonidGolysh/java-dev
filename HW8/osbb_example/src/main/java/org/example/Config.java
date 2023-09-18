package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try(FileInputStream fis = new FileInputStream(
                "C:\\Users\\Leonid\\OneDrive\\Рабочий стол\\New folder (2)\\GoIt\\java_dev\\HW8\\osbb_example" +
                        "\\src\\main\\java\\org\\example\\properties\\config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String jdbcUrl = properties.getProperty("jdbcUrl");
    public static String username = properties.getProperty("username");
    public static String password = properties.getProperty("password");
}
