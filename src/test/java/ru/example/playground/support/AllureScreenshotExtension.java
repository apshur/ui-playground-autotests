package ru.example.playground.support;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.ByteArrayInputStream;

public class AllureScreenshotExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseTest baseTest) {
            attachScreenshot(baseTest.getPage());
        }
        throw throwable;
    }

    private void attachScreenshot(Page page) {
        if (page == null || page.isClosed()) {
            return;
        }

        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true));
        Allure.addAttachment(
                "Screenshot on failure",
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
        );
    }
}
