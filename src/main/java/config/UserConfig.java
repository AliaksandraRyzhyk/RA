package config;

import org.apache.commons.lang3.RandomStringUtils;

public class UserConfig {
    public static final String BASE_URL = "https://apig.unisender.com";
    public static final String URL = "https://api.unisender.com/ru/api";
    public static final String BASE_PATH = "/auth/token";
    public static final String USER_NAME = "ryzhyk.aliaksandra@yandex.by";
    public static final String USER_PASSWORD = "Password!1";
    public static String getFormat() {
        return "json";
    }

    public static String getApiKey() {
        return "6arqjmbzhyo8q9p9szoeqbbjnufzsb5pzjxktade";
    }

    public static String getTitle() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        return generatedString;
    }

    public static String getListId() {
        //String generatedString = RandomStringUtils.randomNumeric(1);
        return "197";
    }

}
