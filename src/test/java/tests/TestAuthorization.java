package tests;

import config.AuthorizationConfig;
import config.ConfigFile;
import external.GoogleAuth;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestAuthorization extends TestBase {

    @Tag("web")
    @Test
    @DisplayName("Авторизация. Неверный пароль")
    void GoogleAuthNegative() {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Нажать кнопку войти", () -> {
            $(by("data-apiary-widget-id", "/header/nav")).click();
        });
        step("Авторизация через google", () -> {
            switchTo().window(1);
            $(".AuthSocialBlock-providers").$("button", 2).click();
        });

        step("Авторизация в google невернные данные", () -> {
            switchTo().window(2);
            GoogleAuth.unlogin(ConfigFile.getGoogleUsername(),
                    ConfigFile.getGooglePasswordNegative());
        });
    }

    @Tag("web")
    @Test
    @DisplayName("Авторизация Google")
    void GoogleAuth() {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Нажать кнопку войти", () -> {
            $(by("data-apiary-widget-id", "/header/nav")).click();
        });
        step("Авторизация через google", () -> {
            switchTo().window(1);
            $(".AuthSocialBlock-providers").$("button", 2).click();
        });
        step("Авторизация в google", () -> {
            switchTo().window(2);
            GoogleAuth.login(ConfigFile.getGoogleUsername(),
                    ConfigFile.getGooglePassword());

        });
        step("Проверили что авторизовались", () -> {
            switchTo().window(0);
            sleep(1000);
            refresh();
            $(by("data-apiary-widget-id", "/header/nav")).click();
            $(by("data-autotest-id", "profile-menu-user")).shouldHave(text("Test Selenide"), text(ConfigFile.getGoogleUsername()));
        });
    }

    @Tag("web")
    @Test
    @DisplayName("Авторизация Yandex")
    void YandexAuth() {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Нажать кнопку войти", () -> {
            $(by("data-apiary-widget-id", "/header/nav")).click();
        });
        step("Авторизация в yandex", () -> {
            switchTo().window(1);
            YandexAuth.loginya(ConfigFile.getYandexUsername(),
                    ConfigFile.getYandexPassword());
        });
        step("Проверили что авторизовались", () -> {
            switchTo().window(0);
            sleep(1000);
            refresh();
            $(by("data-apiary-widget-id", "/header/nav")).click();
            $(by("data-autotest-id", "profile-menu-user")).shouldHave((text("Test TestSurName")), text(ConfigFile.getYandexUsername()));
        });
    }
}
