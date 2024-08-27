package com.spring.game.service;

import com.spring.game.model.Trending;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TrendingGameService {

    Trending getVideo(Long id);

}
