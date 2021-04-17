package tests;

import io.restassured.http.Cookie;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class TestApi {

    @Test
    void loginWithCookieTest() {

        Map<String, String> cookiesMap =
                given()
                        .contentType("application/json; charset=utf-8")
                        .formParam("email", "testtestsurname@yandex.ru")
                        .formParam("password", "Qwerty123Qb!")
                        .when()
                        .post("auth/accounts")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().cookies();


        open("");
        $(".account").shouldHave(text("qaguru@qa.guru"));
    }

}
