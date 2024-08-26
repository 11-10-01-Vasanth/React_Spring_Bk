package com.spring.game.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.game.model.Trending;

public interface TrendingGameRepo extends JpaRepository<Trending, Long> {

}
