package org.example.login.entity;

import javax.persistence.*;

@Entity
@Table(name = "用户材料")
public class UserMaterials {

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

    @Column(name = "黄票")
    private int yellowTicket;

    @Column(name = "绿票")
    private int greenTicket;

    @Column(name = "公招券")
    private int publicRecruitmentTicket;

    public UserMaterials() {}

    // getters and setters

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

    public int getYellowTicket() {
        return yellowTicket;
    }

    public void setYellowTicket(int yellowTicket) {
        this.yellowTicket = yellowTicket;
    }

    public int getGreenTicket() {
        return greenTicket;
    }

    public void setGreenTicket(int greenTicket) {
        this.greenTicket = greenTicket;
    }

    public int getPublicRecruitmentTicket() {
        return publicRecruitmentTicket;
    }

    public void setPublicRecruitmentTicket(int publicRecruitmentTicket) {
        this.publicRecruitmentTicket = publicRecruitmentTicket;
    }
}
