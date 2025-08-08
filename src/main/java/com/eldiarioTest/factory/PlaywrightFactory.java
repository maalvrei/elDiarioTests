package com.eldiarioTest.factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;

    // Método para inicializar el navegador
    public static void initBrowser() {
        playwright = Playwright.create();
        // Por ahora, el navegador está fijo, pero luego lo haremos configurable
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        System.out.println("Navegador Chromium iniciado.");
    }

    // Método para obtener una nueva página con el estado cargado
    public static Page getPage() {
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setStorageStatePath(Paths.get("state.json")));
        return context.newPage();
    }

    // Método para cerrar todo
    public static void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            System.out.println("Playwright cerrado.");
        }
    }
}