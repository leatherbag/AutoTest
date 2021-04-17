package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class TestPage {
    @Tag("web")
    @Test
    @DisplayName("Открытие страницы")
    void OpenPage() {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Проверяем что открылась нужная страница", () -> {
            $(by("data-apiary-widget-id", "/header/logo")).shouldBe(visible);
            $("#logoPartMarket").shouldHave(text("Маркет"));
        });

    }

    @Tag("web")
    @Test
    @DisplayName("Проверка вкладок на  странице")
    void CheckingTabs() {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Проверяем что есть Поиск", () -> {
            $("#header-search").shouldBe(visible);
        });
        step("Проверяем что присутствуют кнопки", () -> {
            $(by("data-apiary-widget-id", "/header/plusBalance")).shouldHave(text("Баллы"));
            $(by("data-apiary-widget-id", "/header/ordersButton")).shouldHave(text("Заказы"));
            $(by("data-apiary-widget-id", "/header/wishlistButton")).shouldHave(text("Избранное"));
            $(by("data-apiary-widget-id", "/header/cartEntryPoint")).shouldHave(text("Корзина"));
        });
    }

    @Tag("web")
    @Test
    @DisplayName("Каталог")
    void Catalog() {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Выбираем категорию: Электроника", () -> {
            $(by("data-apiary-widget-id", "/header/catalogEntrypoint")).click();

            $(by("role", "tablist")).shouldHave(text("Покупки"));
            $(by("data-zone-name", "category-link")).$(linkText("Электроника")).click();
        });
    }
}
