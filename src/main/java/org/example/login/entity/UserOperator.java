package org.example.login.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "用户干员")
@IdClass(UserOperatorId.class)
public class UserOperator {

    @Id
    @Column(name = "用户名", nullable = false)
    private String username;

    @Id
    @Column(name = "干员姓名", nullable = false)
    private String operatorName;

    @Column(name = "干员信物", nullable = false)
    private int operatorToken;

    @ManyToOne
    @JoinColumn(name = "干员姓名", referencedColumnName = "干员姓名", insertable = false, updatable = false)
    private Operator operator;

    public UserOperator() {}

    public UserOperator(String username, String operatorName, int operatorToken) {
        this.username = username;
        this.operatorName = operatorName;
        this.operatorToken = operatorToken;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getOperatorToken() {
        return operatorToken;
    }

    public void setOperatorToken(int operatorToken) {
        this.operatorToken = operatorToken;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", this.username);
        map.put("operatorName", this.operatorName);
        map.put("operatorToken", this.operatorToken);
        map.put("operator", this.operator);
        return map;
    }
}
