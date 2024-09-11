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

	ResponseEntity<?> updategames(UUID gameid, Admin admin, MultipartFile gameimage, MultipartFile video1Url, MultipartFile video2Url, 
	MultipartFile video3Url, MultipartFile video4Url,
	MultipartFile img_vid1, MultipartFile img_vid2,MultipartFile img_vid3, MultipartFile img_vid4,
	MultipartFile img_vid5, MultipartFile img_vid6,MultipartFile img_vid7, MultipartFile img_vid8,
	MultipartFile img_vid9, MultipartFile img_vid10,MultipartFile img_vid11, MultipartFile img_vid12,
	MultipartFile img_vid13, MultipartFile img_vid14,MultipartFile img_vid15, MultipartFile img_vid16,
	MultipartFile img_vid17, MultipartFile img_vid18) throws IOException;

}
