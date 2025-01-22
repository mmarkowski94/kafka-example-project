package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Builder
@Data
public class UserInfo {
    String name;
    String surname;

    UserInfo(@JsonProperty("name") String name,
             @JsonProperty("surname") String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User: " + name + " " + surname;
    }
}
