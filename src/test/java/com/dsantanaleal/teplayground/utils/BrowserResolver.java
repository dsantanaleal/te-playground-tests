package com.dsantanaleal.teplayground.utils;

import com.dsantanaleal.teplayground.config.BrowserSupport;
import com.dsantanaleal.teplayground.config.ExecutionConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

@Singleton
public class BrowserResolver {

    private Playwright playwright;
    private ExecutionConfig executionConfig;

    @Inject
    BrowserResolver(Playwright playwright, ExecutionConfig executionConfig) {
        this.playwright = playwright;
        this.executionConfig = executionConfig;
    }

    public Browser getBrowser(BrowserSupport browserSupport) {
        BrowserType.LaunchOptions launchOptions = executionConfig.getLaunchOptions();
        return switch (browserSupport) {
            case CHROME -> playwright.chromium().launch(launchOptions);
            case FIREFOX -> playwright.firefox().launch(launchOptions);
            case WEBKIT -> playwright.webkit().launch(launchOptions);
        };
    }
}
