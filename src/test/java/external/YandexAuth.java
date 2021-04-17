package external;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;


public class YandexAuth {

    private final static SelenideElement
            yandexEmailInput = $("#passp-field-login"),
            yandexPasswordInput = $("#passp-field-passwd"),
            yandexNextButton = $(by("type","submit"));

    @Step("Authorization with google")
    public static void loginya (String username, String password) {
        yandexEmailInput.setValue(username);
        yandexNextButton.click();
        yandexPasswordInput.setValue(password);
        yandexNextButton.click();
    }
}