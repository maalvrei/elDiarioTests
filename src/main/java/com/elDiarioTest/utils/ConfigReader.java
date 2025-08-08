package com.elDiarioTest.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try (FileInputStream ip = new FileInputStream("./src/main/resources/config.properties")) {
            properties.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar el archivo de configuración config.properties");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}