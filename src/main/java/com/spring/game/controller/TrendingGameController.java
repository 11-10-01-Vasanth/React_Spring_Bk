package com.spring.game.controller;

import com.spring.game.model.Trending;
import com.spring.game.service.TrendingGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RestController
@RequestMapping("/addtrending")
public class TrendingGameController {

    @Autowired
    private TrendingGameService trendingGameService;

    @PostMapping("/video")
    public ResponseEntity<String> uploadVideos(
            @RequestParam("video1") MultipartFile video1Url,
            @RequestParam("video2") MultipartFile video2Url,
            @RequestParam("video3") MultipartFile video3Url,
            @RequestParam("video4") MultipartFile video4Url) {

        try {
            Trending trending = trendingGameService.uploadVideos(video1Url, video2Url, video3Url, video4Url);
            return ResponseEntity.ok("Videos uploaded successfully with ID: " + trending.getId());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading videos: " + e.getMessage());
        }
    }
}
