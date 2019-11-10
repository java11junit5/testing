package com.java11junit5.lib;

import com.java11junit5.TestConstants;
import com.java11junit5.TestLauncher;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;


/**
 * Класс для работы restApi через RestAssured
 */
public class LibRestApi {

    private static Response response;

    public LibRestApi() {
        RestAssured.given().auth().preemptive().basic(TestConstants.USERNAME, TestConstants.TOKEN);
    }

    /**
     * <b>Отправляем Post запрос</b>
     *
     * @param uri     ресурса в который отправляется запрос
     * @param content body запроса
     * @return Обьект restApi.response который содержит ответ на запрос
     */
    public Response post(String uri, String content) {
        response =
                given().
                        contentType(JSON).
                        body(content).
                        when().
                        post(uri).
                        then().
                        extract().response();
        LibDataStore.putLastResponse(response);
        return response;
    }


    /**
     * <b>Отправляем File в теле Post запроса</b>
     *
     * @param uri  ресурса в который отправляется запрос
     * @param file объект File который будем отправлять в Body запроса
     * @return Обьект restApi.response который содержит ответ на запрос
     */
    public Response post(String uri, File file) {
        response =
                given().
                        contentType(JSON).
                        body(file).
                        when().
                        post(uri).
                        then().
                        extract().response();
        LibDataStore.putLastResponse(response);
        return response;
    }


    /**
     * <b>Отправляем пустой Post запрос</b>
     *
     * @param uri ресурса в который отправляется запрос
     * @return Обьект restApi.response который содержит ответ на запрос
     */
    public Response post(String uri) {
        response =
                given().
                        when().
                        post(uri).
                        then().
                        extract().response();
        LibDataStore.putLastResponse(response);
        return response;
    }


    /**
     * <b>Отправляем Get запрос</b>
     *
     * @param uri ресурса в который отправляется запрос
     * @return Обьект restApi.response который содержит ответ на запрос
     */
    public Response get(String uri) {
        response =
                given().
                        contentType(JSON).
                        when().
                        get(uri).
                        then().
                        extract().response();
        LibDataStore.putLastResponse(response);
        return response;
    }


    /**
     * <b>Отправляем Del запрос</b>
     *
     * @param uri ресурса в который отправляется запрос
     * @return Обьект restApi.response который содержит ответ на запрос
     */
    public Response del(String uri) {
        response =
                when().
                        delete(uri).
                        then().
                        extract().response();
        LibDataStore.putLastResponse(response);
        return response;
    }


    /**
     * <b>Отправляем Put запрос</b>
     *
     * @param uri ресурса в который отправляется запрос
     * @return Обьект restApi.response который содержит ответ на запрос
     */
    public Response put(String uri) {
        response =
                given().
                        contentType(JSON).
                        when().
                        put(uri).
                        then().
                        extract().response();
        LibDataStore.putLastResponse(response);
        return response;
    }

    /**
     * <b>Отправляем Patch запрос</b>
     *
     * @param uri ресурса в который отправляется запрос
     * @return Обьект restApi.response который содержит ответ на запрос
     */
    public Response patch(String uri) {
        response =
                given().
                        contentType(JSON).
                        when().
                        patch(uri).
                        then().
                        extract().response();
        LibDataStore.putLastResponse(response);
        return response;
    }

}

