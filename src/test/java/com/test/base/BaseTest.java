package com.test.base;
import com.elDiarioTest.factory.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    // Hacemos la página accesible para las clases que hereden de esta
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
        // Antes de cada test, obtenemos una nueva página de nuestra fábrica
        page = PlaywrightFactory.getPage();
    }
}