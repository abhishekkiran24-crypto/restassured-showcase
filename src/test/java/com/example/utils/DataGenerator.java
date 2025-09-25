package com.example.utils;

import com.github.javafaker.Faker;

public class DataGenerator {

	private static final Faker faker = new Faker();

    public static String getName() {
        return faker.name().firstName();
    }

    public static String getJob() {
        return faker.job().title();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }
}
