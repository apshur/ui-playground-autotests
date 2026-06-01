package ru.example.playground.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.example.playground.support.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("UI Testing Playground")
@Feature("AJAX Data")
class AjaxDataTest extends BaseTest {

    @Test
    @Story("AJAX loading")
    @DisplayName("AJAX Data: после запроса отображается сообщение об успешной загрузке")
    void ajaxDataMessageIsShownAfterRequest() {
        Allure.step("Открыть страницу AJAX Data", () -> open("/ajax"));

        Allure.step("Запустить AJAX-запрос", () ->
                page.getByRole(
                        AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName("Button Triggering AJAX Request")
                ).click()
        );

        Allure.step("Дождаться появления результата AJAX-запроса", () -> {
            Locator successMessage = page.locator(".bg-success");
            assertThat(successMessage).hasText(
                    "Data loaded with AJAX get request.",
                    new LocatorAssertions.HasTextOptions().setTimeout(20_000)
            );
        });
    }
}
