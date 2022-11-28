package com.rus;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TrudvsemTestVacanci {


    @BeforeEach
    void setUp() {
        open("https://trudvsem.ru");
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }
    @CsvSource({
            "менеджер, Вакансии на должность «м»",
            "продавец, Вакансии на должность «п»"

})

    @ParameterizedTest(name = "Проверка наличия вакансий {1}" +
            "в поисковой выдаче по запросу {0}")
    void searchVacanciTest(String searchVacanci, String expectedVacanci){
        $("[href=\"/vacancy/search\"]").click();
        $("[class=\"search-content__input-control input__control\"]")
                .setValue(searchVacanci).pressEnter();
        $("[class=\"content__title\"]").shouldHave(text(expectedVacanci));

    }

}


