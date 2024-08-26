package com.spring.game.model;

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

    @OneToOne(mappedBy = "trending")
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
}
