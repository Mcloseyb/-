package org.example.login.entity;

import javax.persistence.*;

@Entity
@Table(name = "干员图像信息")
public class GanyuanImage {

    @Id
    @Column(name = "干员姓名", nullable = false, unique = true)
    private String ganyuanName;

    @Lob
    @Column(name = "普通立绘", nullable = false)
    private byte[] normalImage;

    @Lob
    @Column(name = "精二立绘")
    private byte[] eliteImage;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "干员姓名", nullable = false)
    private Ganyuan ganyuan;

    // Getters and Setters
    public String getGanyuanName() {
        return ganyuanName;
    }

    public void setGanyuanName(String ganyuanName) {
        this.ganyuanName = ganyuanName;
    }

    public byte[] getNormalImage() {
        return normalImage;
    }

    public void setNormalImage(byte[] normalImage) {
        this.normalImage = normalImage;
    }

    public byte[] getEliteImage() {
        return eliteImage;
    }

    public void setEliteImage(byte[] eliteImage) {
        this.eliteImage = eliteImage;
    }

    public Ganyuan getGanyuan() {
        return ganyuan;
    }

    public void setGanyuan(Ganyuan ganyuan) {
        this.ganyuan = ganyuan;
    }
}
