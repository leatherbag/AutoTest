package external;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;


public class GoogleAuth {

    private final static SelenideElement
            googleMainDiv = $(by("aria-live", "assertive")),
            googleEmailInput = $("#identifierId"),
            googlePasswordInput = $("[name='password']"),
            googleNextButton = $("[id$='Next']");

    @Step("Authorization with google negative")
    public static void unlogin(String username, String password) {
        googleEmailInput.setValue(username);
        googleNextButton.click();
        googlePasswordInput.setValue(password);
        googleNextButton.click();

        googleMainDiv.shouldHave(text("Неверный пароль. Повторите попытку или нажмите на ссылку \"Забыли пароль?\", чтобы сбросить его."));
    }

    @Step("Authorization with google")
    public static void login(String username, String password) {
        googleEmailInput.setValue(username);
        googleNextButton.click();
        googlePasswordInput.setValue(password);
        googleNextButton.click();
    }
}