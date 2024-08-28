package com.spring.game.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.game.model.Admin;

public interface AdminService {

	ResponseEntity<String> addgames(String gametitle, String gamedescription, Double gameprice, Double gamediscount,
			String gamecategory, String gamerating, Date releasedate, String gamepublisher, String gameplatforms, String minsystemrequirements, String recsystemrequirements, String gamegenres, String gametrailerurl, String agerating, String gamefeatures, String supportedlanguages, String gameachievements, String communitylinks, MultipartFile gameimage);

	ResponseEntity<?> getAllGames(int page, int size);

	String deleteGame(UUID gameid);

	List<Admin> getSearchedGame(String gametitle);

	List<Admin> getGameByCategory(String gamecategory);

	List<String> getAllGameCategory();

	List<Admin> getAllGameCategory(UUID gameid);

	ResponseEntity<?> updategames(UUID gameid, Admin admin, MultipartFile gameimage, MultipartFile video1Url, MultipartFile video2Url, MultipartFile video3Url, MultipartFile video4Url) throws IOException;

}
