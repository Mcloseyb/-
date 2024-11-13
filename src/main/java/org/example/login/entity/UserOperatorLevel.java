package org.example.login.entity;

import javax.persistence.*;

@Entity
@Table(name = "用户干员练度")
@IdClass(UserOperatorLevelId.class)
public class UserOperatorLevel {

    @Id
    @Column(name = "用户名", nullable = false)
    private String username;

    @Id
    @Column(name = "干员名", nullable = false)
    private String operatorName;

    @Column(name = "等级", nullable = false)
    private int level;

    @Column(name = "潜能", nullable = false)
    private int potential;

    @Column(name = "精英化等级", nullable = false)
    private int eliteLevel;

    @ManyToOne
    @JoinColumn(name = "干员名", referencedColumnName = "干员姓名", insertable = false, updatable = false)
    private Operator operator;

    public UserOperatorLevel() {}

    public UserOperatorLevel(String username, String operatorName, int level, int potential, int eliteLevel) {
        this.username = username;
        this.operatorName = operatorName;
        this.level = level;
        this.potential = potential;
        this.eliteLevel = eliteLevel;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPotential() {
        return potential;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    public int getEliteLevel() {
        return eliteLevel;
    }

    public void setEliteLevel(int eliteLevel) {
        this.eliteLevel = eliteLevel;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
