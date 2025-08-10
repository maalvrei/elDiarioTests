package com.elDiarioTest.setup;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class Setup {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.eldiario.es/");

            System.out.println("Intentando aceptar las cookies si el banner aparece...");

            try {
                // Localizamos el botón de aceptar cookies.
                Locator acceptButton = page.locator("#didomi-notice-agree-button");
                
                // Intentamos hacer clic con un timeout corto (ej. 5 segundos).
                // Si el botón no está, esta línea lanzará una excepción, que es lo que queremos.
                acceptButton.click(new Locator.ClickOptions().setTimeout(5000));
                
                System.out.println("✅ Cookies aceptadas con éxito.");

            } catch (TimeoutError e) {
                // Si el botón no se encuentra después de 5 segundos, asumimos que no hay banner.
                // El error es esperado en este caso, así que lo ignoramos y continuamos.
                System.out.println("ℹ️ No se encontró el banner de cookies. Se continúa sin aceptar.");
            }

            // Guardamos el estado, independientemente de si se hizo clic o no.
            context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
            System.out.println("Archivo 'state.json' creado con éxito.");

            browser.close();
        }
    }
}
