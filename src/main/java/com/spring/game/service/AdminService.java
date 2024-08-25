package com.spring.game.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.game.model.Admin;

public interface AdminService {

	ResponseEntity<String> addgames(String gametitle, String gamedescription, Double gameprice, Double gamediscount,
			String gamecategory, MultipartFile gameimage);

	ResponseEntity<?> getAllGames(int page, int size);

	String deleteGame(UUID gameid);

	List<Admin> getSearchedGame(String gametitle);

	List<Admin> getGameByCategory(String gamecategory);

	List<String> getAllGameCategory();

	List<Admin> getAllGameCategory(UUID gameid);

	ResponseEntity<?> updategames(UUID gameid, Admin admin, MultipartFile gameimage, MultipartFile gameimage1,
			MultipartFile gameimage2, MultipartFile gameimage3);

}
