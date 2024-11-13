package org.example.login.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserOperatorId implements Serializable {
    private String username;
    private String operatorName;

    public UserOperatorId() {}

    public UserOperatorId(String username, String operatorName) {
        this.username = username;
        this.operatorName = operatorName;
    }

    // getters, setters, hashCode, equals methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOperatorId that = (UserOperatorId) o;
        return username.equals(that.username) && operatorName.equals(that.operatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, operatorName);
    }
}
