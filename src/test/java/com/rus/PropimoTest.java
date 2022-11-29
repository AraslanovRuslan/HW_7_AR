package com.rus;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PropimoTest {


    @BeforeEach
    void setUp() {
        open("https://propimo.com/");
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

        static Stream<Arguments> searchButTest(){
        return Stream.of(
                Arguments.of("EN", List.of("Residential", "Commercial", "Land", "Agencies","Articles")),
                Arguments.of("RU", List.of("Жилая", "Коммерческая", "Земельные участки", "Агентства", "Статьи"))
                        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок из списка {1} на сайте propimo в локали {0}")
    void searchButTest(String locale, String buttons){
       $$("[class=header-widget a]").find(text(locale)).click();
       $$("#responsive a").filter(visible)
                .shouldHave(CollectionCondition.texts(buttons));

    }
}


