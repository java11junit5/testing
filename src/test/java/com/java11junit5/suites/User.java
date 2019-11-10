package com.java11junit5.suites;

import com.java11junit5.TestConstants;
import com.java11junit5.lib.LibRestApi;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class User {

    private static LibRestApi qaLibRestApi = new LibRestApi();

    @Test
    @Tag("positive")
    @DisplayName("Get all users")
    void getAllUsers() {
        Response response = qaLibRestApi.get(TestConstants.HOST + "/users");
        ResponseBody body = response.getBody();
        assertEquals(200, response.getStatusCode());
        assertAll("Должен вернуть имена всех пользователей, проверяем некоторых",
                () -> assertThat(body.jsonPath().get(""), hasSize(30)),
                () -> assertEquals("mojombo", body.jsonPath().get("[0].login")),
                () -> assertEquals("defunkt", body.jsonPath().get("[1].login")),
                () -> assertEquals("bmizerany", body.jsonPath().get("[29].login"))
        );

    }

    @Test
    @Tag("positive")
    @DisplayName("Get single users")
    void getASingleUser() {
        Response response = qaLibRestApi.get(TestConstants.HOST + "/users/bmizerany");
        ResponseBody body = response.getBody();
        assertEquals(200, response.getStatusCode());
        assertAll("Должен вернуть пользователя 'bmizerany', проверяем его данные",
                () -> assertEquals("bmizerany", body.jsonPath().get("login")),
                () -> assertEquals("Blake Mizerany", body.jsonPath().get("name")),
                () -> assertEquals("https://api.github.com/users/bmizerany", body.jsonPath().get("url"))
                );
    }

    @Test
    @Tag("negative")
    @DisplayName("Get single empty users")
    void getASingleIncorrectUser() {
        Response response = qaLibRestApi.get(TestConstants.HOST + "/users/''");
        assertEquals(404, response.getStatusCode());
    }

    @Test
    @Tag("positive")
    @DisplayName("Get the authenticated user")
    void getTheAuthUsers() {
        // Response response = qaLibRestApi.get(TestConstants.HOST + "/user");
        Response response = RestAssured
                .given().auth().preemptive().basic(TestConstants.USERNAME, TestConstants.TOKEN)
                .when().get("https://api.github.com/user");
        ResponseBody body = response.getBody();

        assertEquals(200, response.getStatusCode());
        assertAll("Должен вернуть данные текущего пользователя",
                () -> assertEquals(TestConstants.USERNAME, body.jsonPath().get("login")),
                () -> assertEquals("https://api.github.com/users/java11junit5", body.jsonPath().get("url")));
    }

    @Test
    @Tag("negative")
    @DisplayName("Get the unauthenticated user")
    void getTheUnauthenticatedUsers() {
        Response response = RestAssured.given().auth().preemptive().basic(TestConstants.USERNAME, "").get(TestConstants.HOST + "/user");
        assertEquals(401, response.getStatusCode());
    }

}
