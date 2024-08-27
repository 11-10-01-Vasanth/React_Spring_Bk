package com.spring.game.serviceimpl;

import com.spring.game.model.Trending;
import com.spring.game.repo.TrendingGameRepo;
import com.spring.game.service.TrendingGameService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrendingGameServiceImpl implements TrendingGameService {

    @Autowired
    private TrendingGameRepo trendingGameRepo;

    @Override
    public Trending getVideo(Long id) {
        // TODO Auto-generated method stub
        Trending entity = trendingGameRepo.findById(id).get();
        return entity;
    }

}
