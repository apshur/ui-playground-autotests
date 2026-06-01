package ru.example.playground.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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
@Feature("Dynamic ID")
class DynamicIdTest extends BaseTest {

    @Test
    @Story("Stable locator without dynamic id")
    @DisplayName("Dynamic ID: кнопка находится стабильным локатором без привязки к id")
    void buttonWithDynamicIdCanBeClickedByRoleAndName() {
        Allure.step("Открыть страницу Dynamic ID", () -> open("/dynamicid"));

        Locator button = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Button with Dynamic ID")
        );

        Allure.step("Проверить, что кнопка отображается", () ->
                assertThat(button).isVisible()
        );

        Allure.step("Кликнуть по кнопке через стабильный role/name локатор", button::click);
    }
}
