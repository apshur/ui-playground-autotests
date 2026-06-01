package ru.example.playground.support;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(AllureScreenshotExtension.class)
public abstract class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected static final String BASE_URL = System.getProperty(
            "baseUrl",
            "https://www.uitestingplayground.com"
    );

    @BeforeAll
    static void startBrowser() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        int slowMo = Integer.parseInt(System.getProperty("slowMo", "0"));

        log.info("Start Playwright. Browser=chromium, headless={}, baseUrl={}", headless, BASE_URL);
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMo));
    }

    @BeforeEach
    void createContext() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1440, 900));
        page = context.newPage();
        page.setDefaultTimeout(10_000);
    }

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    @AfterAll
    static void stopBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        log.info("Playwright stopped");
    }

    protected void open(String path) {
        String url = BASE_URL + path;
        log.info("Open page: {}", url);
        Allure.parameter("URL", url);
        page.navigate(url);
    }

    public Page getPage() {
        return page;
    }
}
