package com.elDiarioTest.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.elDiarioTest.factory.PlaywrightFactory; // <-- IMPORTAMOS LA FÁBRICA


public class ElDiarioHomePage {

    private final Page page;

    // 1. Localizamos primero el contenedor del menú principal
    private final Locator menuPrincipal;

    // 2. Luego buscamos el enlace DENTRO de ese contenedor
    private final Locator botonPolitica;

    public ElDiarioHomePage(Page page) {
        this.page = page;
        this.menuPrincipal = page.locator("#nav-carrousel");
        this.botonPolitica = menuPrincipal.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Política"));
    }

    public void navegar() {
        page.navigate(PlaywrightFactory.getBaseUrl());
    }

    public void clicPolitica() {
        botonPolitica.click();
    }

    public String getTitulo() {
        return page.title();
    }

    public String getUrlActual() {
        return page.url();
    }
}