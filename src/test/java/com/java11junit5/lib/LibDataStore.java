package com.java11junit5.lib;

import io.restassured.response.Response;

import java.util.HashMap;

/**
 * Глобальное хранилище данных для тестов
 */
public class LibDataStore {
    private static HashMap<String, Object> globalStore = new HashMap<>();

    public static void putLastResponse(Response response) {
        globalStore.put("lastResponse", response);
    }

    public static void  put(String key, Object value) {
        globalStore.put(key, value);
    }

    public static HashMap<String, Object> getGlobalStore() {
        return globalStore;
    }
}
