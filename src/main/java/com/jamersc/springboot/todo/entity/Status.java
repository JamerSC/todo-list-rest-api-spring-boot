package com.jamersc.springboot.todo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sun.jdi.request.InvalidRequestStateException;

public enum Status {
    OPEN("Open"),
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    CLOSED("Closed"),
    ARCHIVED("Archived");

    @JsonValue
    private final String statusValue;

    Status(String statusValue) {
        this.statusValue = statusValue;
    }

    @JsonCreator
    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(value) || status.statusValue.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new InvalidRequestStateException("Unknown status: " + value);
    }
}
