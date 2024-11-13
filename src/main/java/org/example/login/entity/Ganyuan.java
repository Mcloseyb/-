package org.example.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "干员信息")
public class Ganyuan {

    @Id
    @Column(name = "干员姓名", nullable = false, unique = true)
    private String ganyuanName;

    @Column(name = "稀有度", nullable = false)
    private int rarity;

    @Column(name = "职业", nullable = false)
    private String profession;

    @Column(name = "阵营", nullable = false)
    private String faction;

    @OneToOne(mappedBy = "ganyuan")
    private GanyuanImage ganyuanImage;

    // Getters and Setters
    public String getGanyuanName() {
        return ganyuanName;
    }

    public void setGanyuanName(String ganyuanName) {
        this.ganyuanName = ganyuanName;
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

    public GanyuanImage getGanyuanImage() {
        return ganyuanImage;
    }

    public void setGanyuanImage(GanyuanImage ganyuanImage) {
        this.ganyuanImage = ganyuanImage;
    }
}
