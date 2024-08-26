package com.spring.game.serviceimpl;

import com.spring.game.model.Trending;
import com.spring.game.repo.TrendingGameRepo;
import com.spring.game.service.TrendingGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TrendingGameServiceImpl implements TrendingGameService {

    @Autowired
    private TrendingGameRepo trendingGameRepo;

    private static final String VIDEO_DIR = "/home/kernelogy/Vasanth/React_Spring_Bk/src/main/resources/resources/uploads/videos/"; // Directory to save video files

    @Override
    public Trending uploadVideos(MultipartFile video1Url, MultipartFile video2Url, MultipartFile video3Url, MultipartFile video4Url) throws IOException {
        Trending trending = new Trending();

        trending.setVideo1Url(saveVideoFile(video1Url, "video1.mp4"));
        trending.setVideo2Url(saveVideoFile(video2Url, "video2.mp4"));
        trending.setVideo3Url(saveVideoFile(video3Url, "video3.mp4"));
        trending.setVideo4Url(saveVideoFile(video4Url, "video4.mp4"));

        return trendingGameRepo.save(trending);
    }

    private String saveVideoFile(MultipartFile file, String fileName) throws IOException {
        File directory = new File(VIDEO_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = VIDEO_DIR + fileName;
        Files.write(Paths.get(filePath), file.getBytes());

        return filePath;
    }
}
