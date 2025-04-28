package com.dsantanaleal.teplayground.config;

import com.dsantanaleal.teplayground.utils.BrowserResolver;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Playwright;

public class PlaywrightConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(BrowserResolver.class);
    }

    @Provides
    @Singleton
    public Playwright playwright() {
        return Playwright.create();
    }

    @Provides
    @Singleton
    public ExecutionConfig executionConfig() {
        return new ExecutionConfig();
    }
}
