package ru.example.playground.tests;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.example.playground.support.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Testing Playground")
@Feature("Frames")
class FramesTest extends BaseTest {

    @Test
    @Story("Button inside iframe")
    @DisplayName("Frames: кнопка внутри frame доступна для клика")
    void clickButtonInsideFrame() {
        Allure.step("Открыть страницу Frames", () -> open("/frames"));

        boolean clicked = Allure.step("Найти кнопку Button внутри фреймов и кликнуть", this::clickButtonInAnyFrame);

        Allure.step("Проверить, что кнопка была найдена и клик выполнен", () ->
                assertTrue(clicked, "Не удалось найти кнопку 'Button' внутри iframe на странице Frames")
        );
    }

    private boolean clickButtonInAnyFrame() {
        for (Frame frame : page.frames()) {
            Locator button = frame.getByRole(AriaRole.BUTTON, new Frame.GetByRoleOptions().setName("Button"));
            if (button.count() > 0) {
                button.first().click();
                return true;
            }
        }
        return false;
    }
}
