package com.spring.game.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "admin_game")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gameid", updatable = false, nullable = false)
    private UUID gameid;

    @Column(name = "gametitle")
    private String gametitle;

    @Column(name = "gamedescription", columnDefinition = "text")
    private String gamedescription;

    @Column(name = "gameprice")
    private Double gameprice; 

    @Column(name = "gamediscount")
    private Double gamediscount;

    @Column(name = "gameimage")
    private String gameimage;

    @Column(name = "gamecategory")
    private String gamecategory;

    @Column(name = "gamerating")
    private String gamerating;

    @Column(name = "releasedate")
    private Date releasedate; 

    @Column(name = "gamepublisher")
    private String gamepublisher;

    @Column(name = "gameplatforms")
    private String gameplatforms;

    @Column(name = "minsystemrequirements", columnDefinition = "text")
    private String minsystemrequirements;

    @Column(name = "recsystemrequirements", columnDefinition = "text")
    private String recsystemrequirements;  

    @Column(name = "gamegenres")
    private String gamegenres;

    @Column(name = "gametrailerurl")
    private String gametrailerurl; 

    @Column(name = "agerating")
    private String agerating; 

    @Column(name = "gamefeatures", columnDefinition = "text")
    private String gamefeatures; 

    @Column(name = "supportedlanguages")
    private String supportedlanguages; 

    @Column(name = "gameachievements", columnDefinition = "text")
    private String gameachievements;

    @Column(name = "communitylinks")
    private String communitylinks; 

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trending_id", referencedColumnName = "id")
    @JsonManagedReference
    private Trending trending;

    // Getters and Setters

    public UUID getGameid() {
        return gameid;
    }

    public void setGameid(UUID gameid) {
        this.gameid = gameid;
    }

    public String getGametitle() {
        return gametitle;
    }

    public void setGametitle(String gametitle) {
        this.gametitle = gametitle;
    }

    public String getGamedescription() {
        return gamedescription;
    }

    public void setGamedescription(String gamedescription) {
        this.gamedescription = gamedescription;
    }

    public Double getGameprice() {
        return gameprice;
    }

    public void setGameprice(Double gameprice) {
        this.gameprice = gameprice;
    }

    public Double getGamediscount() {
        return gamediscount;
    }

    public void setGamediscount(Double gamediscount) {
        this.gamediscount = gamediscount;
    }

    public String getGameimage() {
        return gameimage;
    }

    public void setGameimage(String gameimage) {
        this.gameimage = gameimage;
    }

    public String getGamecategory() {
        return gamecategory;
    }

    public void setGamecategory(String gamecategory) {
        this.gamecategory = gamecategory;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Trending getTrending() {
        return trending;
    }

    public void setTrending(Trending trending) {
        this.trending = trending;
    }

    public String getGamerating() {
        return gamerating;
    }

    public void setGamerating(String gamerating) {
        this.gamerating = gamerating;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public String getGamepublisher() {
        return gamepublisher;
    }

    public void setGamepublisher(String gamepublisher) {
        this.gamepublisher = gamepublisher;
    }

    public String getGameplatforms() {
        return gameplatforms;
    }

    public void setGameplatforms(String gameplatforms) {
        this.gameplatforms = gameplatforms;
    }

    public String getMinsystemrequirements() {
        return minsystemrequirements;
    }

    public void setMinsystemrequirements(String minsystemrequirements) {
        this.minsystemrequirements = minsystemrequirements;
    }

    public String getRecsystemrequirements() {
        return recsystemrequirements;
    }

    public void setRecsystemrequirements(String recsystemrequirements) {
        this.recsystemrequirements = recsystemrequirements;
    }

    public String getGamegenres() {
        return gamegenres;
    }

    public void setGamegenres(String gamegenres) {
        this.gamegenres = gamegenres;
    }

    public String getGametrailerurl() {
        return gametrailerurl;
    }

    public void setGametrailerurl(String gametrailerurl) {
        this.gametrailerurl = gametrailerurl;
    }

    public String getAgerating() {
        return agerating;
    }

    public void setAgerating(String agerating) {
        this.agerating = agerating;
    }

    public String getGamefeatures() {
        return gamefeatures;
    }

    public void setGamefeatures(String gamefeatures) {
        this.gamefeatures = gamefeatures;
    }

    public String getSupportedlanguages() {
        return supportedlanguages;
    }

    public void setSupportedlanguages(String supportedlanguages) {
        this.supportedlanguages = supportedlanguages;
    }

    public String getGameachievements() {
        return gameachievements;
    }

    public void setGameachievements(String gameachievements) {
        this.gameachievements = gameachievements;
    }

    public String getCommunitylinks() {
        return communitylinks;
    }

    public void setCommunitylinks(String communitylinks) {
        this.communitylinks = communitylinks;
    }

    
}
