package org.example.login.entity;

import javax.persistence.*;

@Entity
@Table(name = "用户个人商店")
public class UserShop {

    @Id
    @Column(name = "用户名", nullable = false, unique = true)
    private String username;

    @Column(name = "合成玉")
    private int syntheticJade;

    @Column(name = "龙门币")
    private int dragonGateCoin;

    @Column(name = "作战录像")
    private int battleRecord;

    @Column(name = "加速券")
    private int accelerationTicket;

    @Column(name = "公招券")
    private int publicRecruitmentTicket;

    public UserShop() {}

    public UserShop(String username, int syntheticJade, int dragonGateCoin, int battleRecord, int accelerationTicket, int publicRecruitmentTicket) {
        this.username = username;
        this.syntheticJade = syntheticJade;
        this.dragonGateCoin = dragonGateCoin;
        this.battleRecord = battleRecord;
        this.accelerationTicket = accelerationTicket;
        this.publicRecruitmentTicket = publicRecruitmentTicket;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSyntheticJade() {
        return syntheticJade;
    }

    public void setSyntheticJade(int syntheticJade) {
        this.syntheticJade = syntheticJade;
    }

    public int getDragonGateCoin() {
        return dragonGateCoin;
    }

    public void setDragonGateCoin(int dragonGateCoin) {
        this.dragonGateCoin = dragonGateCoin;
    }

    public int getBattleRecord() {
        return battleRecord;
    }

    public void setBattleRecord(int battleRecord) {
        this.battleRecord = battleRecord;
    }

    public int getAccelerationTicket() {
        return accelerationTicket;
    }

    public void setAccelerationTicket(int accelerationTicket) {
        this.accelerationTicket = accelerationTicket;
    }

    public int getPublicRecruitmentTicket() {
        return publicRecruitmentTicket;
    }

    public void setPublicRecruitmentTicket(int publicRecruitmentTicket) {
        this.publicRecruitmentTicket = publicRecruitmentTicket;
    }
}
