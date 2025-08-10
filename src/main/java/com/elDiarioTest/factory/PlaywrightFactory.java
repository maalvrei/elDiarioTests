package com.elDiarioTest.factory;

import com.elDiarioTest.utils.ConfigReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static ConfigReader configReader; // <-- AÑADIMOS EL LECTOR


    // Método para inicializar el navegador
    public static void initBrowser() {
        configReader = new ConfigReader(); // <-- LO INICIALIZAMOS
        playwright = Playwright.create();

        String browserName = configReader.getProperty("browser");
        boolean headless = Boolean.parseBoolean(configReader.getProperty("headless"));

        System.out.println("Iniciando navegador: " + browserName);

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            default:
                throw new IllegalArgumentException("Navegador no soportado: " + browserName);
        }
    }

    // Método para obtener una nueva página con el estado cargado
    public static Page getPage() {
        BrowserContext context = browser.newContext();
        return context.newPage();
    }

    // Método para cerrar todo
    public static void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            System.out.println("Playwright cerrado.");
        }
    }

    public static String getBaseUrl() {
        return configReader.getProperty("baseUrl");
    }
}