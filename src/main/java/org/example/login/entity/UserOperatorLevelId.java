package org.example.login.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserOperatorLevelId implements Serializable {
    private String username;
    private String operatorName;

    public UserOperatorLevelId() {}

    public UserOperatorLevelId(String username, String operatorName) {
        this.username = username;
        this.operatorName = operatorName;
    }

    // getters, setters, hashCode, equals methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOperatorLevelId that = (UserOperatorLevelId) o;
        return username.equals(that.username) && operatorName.equals(that.operatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, operatorName);
    }
}
