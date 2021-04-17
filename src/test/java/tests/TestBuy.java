package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class TestBuy {

    @Tag("web")
    @Test
    @DisplayName("Добавление в корзину")
    void AddtoCart() {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Вводим в поиск", () -> {
            $("#header-search").val("Monge для собак");
            $(by("type","submit")).click();
        });
        step("Сортируем по рейтингу", () -> {
            $(by("data-autotest-id","opinions")).click();
        });
        step("Выбираем корм и добавлям в корзину", () -> {
            SelenideElement snippet = $$(by("data-autotest-id","product-snippet")).findBy(text("Сухой корм для щенков Monge Speciality line, ягненок, с рисом, с картофелем 800 г"));
            snippet.scrollTo();
            snippet.shouldHave(text("4.8"));
            snippet.$(by("data-zone-name","cartButton") ).click();

        });
        step("Проверка", () -> {
            $(by("data-auto","content")).shouldHave(text("Товар успешно добавлен в корзину"));

        });
    }

    @Tag("web")
    @Test
    @DisplayName("Добавление в избранное")
    void AddtoWishList () {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Вводим в поиск", () -> {
            $("#header-search").val("Monge для собак");
            $(by("type","submit")).click();
        });
        step("Сортируем по отзывам", () -> {
            $(by("data-autotest-id","opinions")).click();
        });
        step("Выбираем корм и добавлям в корзину", () -> {
            SelenideElement snippet = $$(by("data-autotest-id","product-snippet")).findBy(text("Сухой корм для собак Monge Speciality line Hypoallergenic, лосось, тунец 12 кг"));
            snippet.shouldHave(text("4.8"));
            snippet.$(linkText("Сухой корм для собак Monge Speciality line Hypoallergenic, лосось, тунец 12 кг")).click();
        });
        step("Открылась страница с кормом", () -> {
            switchTo().window(1);
            $x("//h1[contains(text(),'Сухой корм для собак Monge Speciality line Hypoall')]").shouldHave(text("Сухой корм для собак Monge Speciality line Hypoallergenic, лосось, тунец 12 кг"));
        });
        step("Добавить в избраное", () -> {
            $(by("data-tid","64a067c1")).click();
            $(".nMEoEKZaF-").shouldHave(text("Сухой корм для собак Monge Speciality line Hypoallergenic, лосось, тунец 12 кг добавлен в избранное"));
        });
        step("У значка 'Избранное' +1", () -> {
            $(linkText("Перейти в избранное")).click();
            $(by("data-zone-name","wishlistPage")).shouldHave(text("Избранное"));
            $(by("data-apiary-widget-id","/content/wishlist")).shouldHave(text("Сухой корм для собак Monge Speciality line Hypoallergenic, лосось, тунец 12 кг"));
            $(by("data-apiary-widget-id","/header/wishlistButton")).shouldHave(text("1"));
        });
    }

    @Tag("web")
    @Test
    @DisplayName("Добавление в корзину. Оформление заказа.")
    void AddToBuy () {
        step("Открываем страницу", () -> {
            open("https://market.yandex.ru/");
        });
        step("Вводим в поиск", () -> {
            $("#header-search").val("Кофе в зернах");
            $(by("type","submit")).click();
        });
        step("Сортируем по отзывам", () -> {
            $(by("data-autotest-id","dpop")).click();
        });
        step("Выбираем кофе и добавлям в корзину", () -> {
            SelenideElement snippet = $$(by("data-autotest-id","product-snippet")).findBy(text("Кофе в зернах Lavazza Qualita Oro, 250 г"));
            snippet.shouldHave(text("4.6"));
            snippet.$(linkText("Кофе в зернах Lavazza Qualita Oro, 250 г")).click();
        });
        step("Открылась страница с кофе", () -> {
            switchTo().window(1);
            $x("//h1[contains(text(),'Кофе в зернах Lavazza Qualita Oro, 250 г')]").shouldHave(text("Кофе в зернах Lavazza Qualita Oro, 250 г"));
        });
        step("Добавить в корзину", () -> {
            $(by("data-apiary-widget-id","/content/reactProductSummary/recommendedOffers")).$(by("data-zone-name","cartButton")).click();
            $(by("data-auto","content")).shouldHave(text("Товар успешно добавлен в корзину"));
            $(linkText("Перейти в корзину")).click();
        });
        step("Товар успешно добавлен", () -> {
            $(by("data-apiary-widget-name","@marketplace/CartHeader")).shouldHave(text("1 товар в корзине"));
            $(by("data-apiary-widget-name","@marketplace/CounterCart")).shouldHave(text("1"));
        });
        step("Оформление заказа", () -> {
            $(by("data-apiary-widget-name","@marketplace/CartTotalInformation")).$(by("type","button")).click();
            $(by("data-zone-name","cartPageLoginPicturePopup")).$(linkText("Продолжить, как гость")).click();
        });
    }
}
