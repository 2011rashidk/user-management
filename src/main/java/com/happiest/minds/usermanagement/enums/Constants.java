package com.happiest.minds.usermanagement.enums;

public enum Constants {

    BAD_CREDENTIALS("Bad credentials"),
    JWT_TOKEN_EXPIRED("JWT token expired!"),
    NO_DATA_FOUND("Not found data with id: "),
    RECORD_DELETED_FOR_ID("Record deleted for id: ");
    private final String value;
    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
