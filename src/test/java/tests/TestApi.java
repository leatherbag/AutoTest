package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class TestApi {
    public static String token;

    @Test
    void loginWithCookieTest() {

        Map<String, String> cookiesMap =
                given()
                        .contentType("application/json; charset=utf-8")
                        .formParam("email", "admin@admin.com")
                        .formParam("password", "Admin")
                        .when()
                        .post("http://192.168.128.215/acapi/auth")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().cookies();
        open("http://192.168.128.215/acui/dashboard");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookiesMap.get("Nop.customer")));
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookiesMap.get("NOPCOMMERCE.AUTH")));
        getWebDriver().manage().addCookie(new Cookie("ARRAffinity", cookiesMap.get("ARRAffinity")));

        open("http://192.168.128.215/acui/settings/service_providers");
        $(".md-title").shouldHave(text("Поставщики услуг"));
    }

    @Test
    public void postmanFirstGetTest() {
        token = given()
                .auth()
                .basic("admin@admin.com", "Admin")
                .when()
                .get("http://192.168.128.215/acapi/auth")
                .then()
                .statusCode(200)
                .log().body()
                .extract().path("token");
    }
}
