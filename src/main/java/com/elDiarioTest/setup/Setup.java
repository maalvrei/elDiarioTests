package com.elDiarioTest.setup;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;

public class Setup {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.eldiario.es/");

            System.out.println("Buscando el botón de cookies directamente en la página...");

            // 1. Localizamos el botón usando el ID que encontraste, pero esta vez
            // lo buscamos directamente en el objeto 'page'.
            Locator acceptButton = page.locator("#didomi-notice-agree-button");

            // 2. Esperamos a que el botón sea visible y hacemos clic.
            // Si el botón no aparece en 10 segundos, el test fallará con un TimeoutError.
            acceptButton.waitFor(new Locator.WaitForOptions().setTimeout(10000));

            if (acceptButton.isVisible()) {
                acceptButton.click();
                System.out.println("✅ Cookies aceptadas con éxito.");
            } else {
                // Este mensaje probablemente no se vea si el test falla por timeout,
                // pero lo dejamos por si acaso.
                System.out.println("❌ No se encontró el botón de aceptar en la página principal.");
            }

            context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
            System.out.println("Archivo 'state.json' creado con éxito.");

            browser.close();
        }
    }
}
