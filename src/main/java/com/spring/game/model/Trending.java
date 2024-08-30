package com.spring.game.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "trending")
public class Trending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video1_url")
    private String video1Url;

    @Column(name = "video2_url")
    private String video2Url;

    @Column(name = "video3_url")
    private String video3Url;

    @Column(name = "video4_url")
    private String video4Url;

    @Column(name = "gametrailer")
    private String gametrailer;

    @Column(name = "img_vid1")
    private String img_vid1;

    @Column(name = "img_vid2")
    private String img_vid2;

    @Column(name = "img_vid3")
    private String img_vid3;

    @Column(name = "img_vid4")
    private String img_vid4;

    @Column(name = "img_vid5")
    private String img_vid5;

    @Column(name = "img_vid6")
    private String img_vid6;

    @Column(name = "img_vid7")
    private String img_vid7;

    @Column(name = "img_vid8")
    private String img_vid8;

    @Column(name = "img_vid9")
    private String img_vid9;

    @Column(name = "img_vid10")
    private String img_vid10;

    @Column(name = "img_vid11")
    private String img_vid11;

    @Column(name = "img_vid12")
    private String img_vid12;

    @Column(name = "img_vid13")
    private String img_vid13;

    @Column(name = "img_vid14")
    private String img_vid14;

    @Column(name = "img_vid15")
    private String img_vid15;

    @Column(name = "img_vid16")
    private String img_vid16;

    @Column(name = "img_vid17")
    private String img_vid17;

    @Column(name = "img_vid18")
    private String img_vid18;

    @OneToOne(mappedBy = "trending")
    @JsonBackReference
    private Admin admin;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideo1Url() {
        return video1Url;
    }

    public void setVideo1Url(String video1Url) {
        this.video1Url = video1Url;
    }

    public String getVideo2Url() {
        return video2Url;
    }

    public void setVideo2Url(String video2Url) {
        this.video2Url = video2Url;
    }

    public String getVideo3Url() {
        return video3Url;
    }

    public void setVideo3Url(String video3Url) {
        this.video3Url = video3Url;
    }

    public String getVideo4Url() {
        return video4Url;
    }

    public void setVideo4Url(String video4Url) {
        this.video4Url = video4Url;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getGametrailer() {
        return gametrailer;
    }

    public void setGametrailer(String gametrailer) {
        this.gametrailer = gametrailer;
    }

    public String getImg_vid1() {
        return img_vid1;
    }

    public void setImg_vid1(String img_vid1) {
        this.img_vid1 = img_vid1;
    }

    public String getImg_vid2() {
        return img_vid2;
    }

    public void setImg_vid2(String img_vid2) {
        this.img_vid2 = img_vid2;
    }

    public String getImg_vid3() {
        return img_vid3;
    }

    public void setImg_vid3(String img_vid3) {
        this.img_vid3 = img_vid3;
    }

    public String getImg_vid4() {
        return img_vid4;
    }

    public void setImg_vid4(String img_vid4) {
        this.img_vid4 = img_vid4;
    }

    public String getImg_vid5() {
        return img_vid5;
    }

    public void setImg_vid5(String img_vid5) {
        this.img_vid5 = img_vid5;
    }

    public String getImg_vid6() {
        return img_vid6;
    }

    public void setImg_vid6(String img_vid6) {
        this.img_vid6 = img_vid6;
    }

    public String getImg_vid7() {
        return img_vid7;
    }

    public void setImg_vid7(String img_vid7) {
        this.img_vid7 = img_vid7;
    }

    public String getImg_vid8() {
        return img_vid8;
    }

    public void setImg_vid8(String img_vid8) {
        this.img_vid8 = img_vid8;
    }

    public String getImg_vid9() {
        return img_vid9;
    }

    public void setImg_vid9(String img_vid9) {
        this.img_vid9 = img_vid9;
    }

    public String getImg_vid10() {
        return img_vid10;
    }

    public void setImg_vid10(String img_vid10) {
        this.img_vid10 = img_vid10;
    }

    public String getImg_vid11() {
        return img_vid11;
    }

    public void setImg_vid11(String img_vid11) {
        this.img_vid11 = img_vid11;
    }

    public String getImg_vid12() {
        return img_vid12;
    }

    public void setImg_vid12(String img_vid12) {
        this.img_vid12 = img_vid12;
    }

    public String getImg_vid13() {
        return img_vid13;
    }

    public void setImg_vid13(String img_vid13) {
        this.img_vid13 = img_vid13;
    }

    public String getImg_vid14() {
        return img_vid14;
    }

    public void setImg_vid14(String img_vid14) {
        this.img_vid14 = img_vid14;
    }

    public String getImg_vid15() {
        return img_vid15;
    }

    public void setImg_vid15(String img_vid15) {
        this.img_vid15 = img_vid15;
    }

    public String getImg_vid16() {
        return img_vid16;
    }

    public void setImg_vid16(String img_vid16) {
        this.img_vid16 = img_vid16;
    }

    public String getImg_vid17() {
        return img_vid17;
    }

    public void setImg_vid17(String img_vid17) {
        this.img_vid17 = img_vid17;
    }

    public String getImg_vid18() {
        return img_vid18;
    }

    public void setImg_vid18(String img_vid18) {
        this.img_vid18 = img_vid18;
    }

    
}
