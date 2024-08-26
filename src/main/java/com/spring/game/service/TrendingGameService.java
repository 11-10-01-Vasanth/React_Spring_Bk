package com.spring.game.service;

import com.spring.game.model.Trending;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TrendingGameService {

    Trending uploadVideos(MultipartFile video1Url, MultipartFile video2Url, MultipartFile video3Url, MultipartFile video4Url) throws IOException;
}
