package com.intw.mentorapi.status;

public enum RoleStatus {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_USER("ROLE_USER"),
    ROLE_ALL("ROLE_ALL");

    String value;

    RoleStatus(String value) {
        this.value = value;
    }
}
