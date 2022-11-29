package com.rus;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Ee24Test {


    @BeforeEach
    void setUp() {
        open("https://ee24.com/");
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }


    static Stream<Arguments> searchButTest(){
        return Stream.of(
                Arguments.of("EN", List.of("Add listing", "Articles", "Select country", "En", "Ру", "Log in")),
                Arguments.of("Ру", List.of("Добавить объявления", "Статьи", "Выбрать страну", "En", "Ру", "Войти"))
                        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок из списка {1} на сайте ee24 в локали {0}")
    void searchButTest(String locale, List<String> buttons){
       $$(".language-links a").find(text(locale)).click();
       $$("[class=\"nav navbar-nav navbar-right\"] a").filter(visible)
               .shouldHave(CollectionCondition.texts(buttons));
    }
}


