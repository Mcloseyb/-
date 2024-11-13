package org.example.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "干员信息")
public class Operator {

    @Id
    @Column(name = "干员姓名", nullable = false, unique = true)
    private String operatorName;

    @Column(name = "稀有度", nullable = false)
    private int rarity;

    @Column(name = "职业", nullable = false)
    private String profession;

    @Column(name = "阵营", nullable = false)
    private String faction;

    // Getters and Setters
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }
}
