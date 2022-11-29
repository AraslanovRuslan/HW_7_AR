package com.rus;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class AtaberkestateTest {


    @BeforeEach
    void setUp() {
        open("https://www.ataberkestate.com/");
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }


    static Stream<Arguments> searchButTest(){
        return Stream.of(
                Arguments.of("EN", List.of("Home", "All objects", "Online property request form", "About us", "Services", "Testimonials", "Contacts")),
                Arguments.of("RU", List.of("Главная", "Все объекты", "Онлайн подбор вариантов", "О нас", "Услуги", "Отзывы", "Контакты"))
                        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок из списка {1} на сайте ataberkestate в локали {0}")
    void searchButTest(String locale, String buttons){
       $$("#block-language-switcher").find(text(locale)).click();
       $$("#block-osnovnayanavigaciya a").filter(visible)
                .shouldHave(CollectionCondition.texts(buttons));

    }
}


