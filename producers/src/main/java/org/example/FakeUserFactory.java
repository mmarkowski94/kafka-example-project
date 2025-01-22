package org.example;

import com.github.javafaker.Faker;

public class FakeUserFactory {

    private static final Faker faker = new Faker();

    public static User createUser() {
        return User.builder()
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .build();
    }
}
