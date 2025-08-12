package com.elDiarioTest.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.elDiarioTest.factory.PlaywrightFactory; // <-- IMPORTAMOS LA FÁBRICA


public class ElDiarioHomePage {

    private final Page page;
    private final Locator menuPrincipal;
    private final Locator botonPolitica;
    private final Locator botonEconomia;

    public ElDiarioHomePage(Page page) {
        this.page = page;
        this.menuPrincipal = page.locator("#nav-carrousel");
        this.botonEconomia = menuPrincipal.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Economía"));;
        this.botonPolitica = menuPrincipal.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Política"));
    }

    public void navegar() {
        page.navigate(PlaywrightFactory.getBaseUrl());
    }

    public void clicPolitica() {
        botonPolitica.click();
    }

    public void clicEconomia() {
        botonEconomia.click();
    }

    public String getTitulo() {
        return page.title();
    }

    public String getUrlActual() {
        return page.url();
    }
}