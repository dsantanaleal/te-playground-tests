package com.dsantanaleal.teplayground.config;

import com.microsoft.playwright.BrowserType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExecutionConfig {

    public BrowserType.LaunchOptions getLaunchOptions() {
        boolean headless = Boolean.parseBoolean(System.getenv("headless"));
        double slowMo = System.getenv("slowMo") == null ? 0 : Double.parseDouble(System.getenv("slowMo"));
        return new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMo);
    }
}
