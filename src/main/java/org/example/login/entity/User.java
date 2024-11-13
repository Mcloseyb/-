package org.example.login.entity;

import javax.persistence.*;

@Entity
@Table(name = "用户")
public class User {

    @Id
    @Column(name = "用户名", nullable = false, unique = true)
    private String username;

    @Column(name = "账号", nullable = false)
    private String account;

    @Column(name = "密码", nullable = false)
    private String password;

    public User() {}

    public User(String username, String account, String password) {
        this.username = username;
        this.account = account;
        this.password = password;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
