package com.dsantanaleal.teplayground.tests;

import com.dsantanaleal.teplayground.config.BrowserSupport;
import com.dsantanaleal.teplayground.config.PlaywrightConfig;
import com.dsantanaleal.teplayground.utils.BrowserResolver;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class BaseTest {

    private BrowserResolver browserResolver;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod(alwaysRun = true)
    public void setupPlaywright() {
        log.info("Setting up the test environment");
        Injector injector = Guice.createInjector(new PlaywrightConfig());
        browserResolver = injector.getInstance(BrowserResolver.class);
        browser = browserResolver.getBrowser(BrowserSupport.CHROME);
        context = browser.newContext();
        page = context.newPage();

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                saveScreenshot();
                saveTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (browser != null) {
                browser.close();
            }
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] saveScreenshot() {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }

    @Attachment(value = "Playwright Trace", type = "application/zip")
    public byte[] saveTrace() {
        try {
            Path tracePath = Paths.get("build", "allure-results", "trace-" + System.currentTimeMillis() + ".zip");
            context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
            return Files.readAllBytes(tracePath);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            return new byte[0];
        }
    }

    public void navigate(String url) {
        page.navigate(url);
    }
}
