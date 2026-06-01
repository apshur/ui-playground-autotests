package ru.example.playground.tests;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.example.playground.support.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("UI Testing Playground")
@Feature("Text Input")
class TextInputTest extends BaseTest {

    @Test
    @Story("Dynamic button name")
    @DisplayName("Text Input: текст кнопки меняется после ввода значения")
    void buttonNameChangesAfterTextInput() {
        String newButtonName = "Run UI test";

        Allure.step("Открыть страницу Text Input", () -> open("/textinput"));

        Allure.step("Ввести новый текст кнопки", () ->
                page.locator("#newButtonName").fill(newButtonName)
        );

        Locator button = page.locator("#updatingButton");

        Allure.step("Нажать кнопку", button::click);

        Allure.step("Проверить, что текст кнопки изменился", () ->
                assertThat(button).hasText(newButtonName)
        );
    }
}
