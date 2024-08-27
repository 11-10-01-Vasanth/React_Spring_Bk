package com.spring.game.controller;

import com.spring.game.model.Trending;
import com.spring.game.service.TrendingGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/trending")
public class TrendingGameController {

    @Autowired
    private TrendingGameService trendingGameService;

    @GetMapping("/get/{id}")
    public Trending getVideo(@PathVariable Long id) {
        Trending entity = trendingGameService.getVideo(id);
        return entity;
    }
    

}
