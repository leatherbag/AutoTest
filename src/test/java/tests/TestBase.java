package tests;

import com.codeborne.selenide.Configuration;
import external.GoogleAuth;
import external.YandexAuth;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

        GoogleAuth GoogleAuth = new GoogleAuth();
        YandexAuth YandexAuth = new YandexAuth();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://passport.yandex.ru/registration-validations";
        Configuration.baseUrl = "https://passport.yandex.ru/registration-validations";
    }
    }

