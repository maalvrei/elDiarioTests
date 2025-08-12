package com.test.base;

import com.elDiarioTest.factory.PlaywrightFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected Page page;

    @BeforeAll
    public static void setup() {
        PlaywrightFactory.initBrowser();
    }

    @AfterAll
    public static void tearDown() {
        PlaywrightFactory.closePlaywright();
    }

    @BeforeEach
    public void setupTest() {
        page = PlaywrightFactory.getPage();
        page.navigate(PlaywrightFactory.getBaseUrl());
        handleCookies();
    }

    private void handleCookies() {
        try {
            // Localizamos el botón de aceptar cookies directamente por su ID.
            Locator acceptButton = page.locator("#didomi-notice-agree-button");

            // Intentamos hacer clic con un timeout corto.
            acceptButton.click(new Locator.ClickOptions().setTimeout(5000));

            System.out.println("✅ Banner de cookies aceptado en BaseTest.");

        } catch (TimeoutError e) {
            // Si el botón no se encuentra, asumimos que no hay banner y continuamos.
            System.out.println("ℹ️ No se encontró el banner de cookies en BaseTest, se continúa.");
        }
    }
}