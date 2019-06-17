package ru.testing.api.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.testing.api.entities.translate.TranslateResponse;

import static io.restassured.RestAssured.given;

@Slf4j
@Component
public class YandexTranslateService {

    private static final String basePath = "https://translate.yandex.net/api";
    private static final String TOKEN = ""; //TODO generate and insert your yandex token here, https://tech.yandex.ru/translate/doc/dg/concepts/api-keys-docpage/

    public TranslateResponse getTranslateText(String textToTranslate){
        RestAssured.basePath = basePath;
        TranslateResponse response = given()

                .contentType(ContentType.URLENC)
                .accept(ContentType.URLENC)
                .param("key", TOKEN)
                .param("text", textToTranslate)
                .param("lang", "en-ru")
                .param("format", "plain")
                .param("options", "1")
                .post(basePath.concat(YandexTranslateManager.translate))

                .then()
                .extract().body().as(TranslateResponse.class);
        return response;
    }
}