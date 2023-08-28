package com.happiest.minds.usermanagement.enums;

public enum Constants {

    BAD_CREDENTIALS("Bad credentials");

    private final String value;
    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
