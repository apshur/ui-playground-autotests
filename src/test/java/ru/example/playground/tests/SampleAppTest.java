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
@Feature("Sample App")
class SampleAppTest extends BaseTest {

    @Test
    @Story("Positive login")
    @DisplayName("Sample App: успешная авторизация с валидным паролем")
    void successfulLoginWithValidCredentials() {
        Allure.step("Открыть страницу Sample App", () -> open("/sampleapp"));

        Allure.step("Заполнить имя пользователя и пароль", () -> {
            page.getByPlaceholder("User Name").fill("Vladislav");
            page.getByPlaceholder("********").fill("pwd");
        });

        Allure.step("Нажать Log In", () ->
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In")).click()
        );

        Allure.step("Проверить приветствие пользователя", () -> {
            Locator loginStatus = page.locator("#loginstatus");
            assertThat(loginStatus).hasText("Welcome, Vladislav!");
        });
    }
}
