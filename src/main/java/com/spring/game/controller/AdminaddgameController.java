package com.spring.game.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.game.model.Admin;
import com.spring.game.service.AdminService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/admin")

public class AdminaddgameController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/addgames")
	public ResponseEntity<ResponseEntity<String>> addGames(@RequestParam("gametitle") String gametitle,
			@RequestParam("gamedescription") String gamedescription, @RequestParam("gameprice") Double gameprice,
			@RequestParam("gamediscount") Double gamediscount, @RequestParam("gamecategory") String gamecategory,
			@RequestParam("gamerating") String gamerating,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date releasedate,
			@RequestParam("gamepublisher") String gamepublisher, @RequestParam("gameplatforms") String gameplatforms,
			@RequestParam("minsystemrequirements") String minsystemrequirements,
			@RequestParam("recsystemrequirements") String recsystemrequirements,
			@RequestParam("gamegenres") String gamegenres, @RequestParam("gametrailerurl") String gametrailerurl,
			@RequestParam("agerating") String agerating, @RequestParam("gamefeatures") String gamefeatures,
			@RequestParam("supportedlanguages") String supportedlanguages,
			@RequestParam("gameachievements") String gameachievements,
			@RequestParam("communitylinks") String communitylinks, @RequestParam("gameimage") MultipartFile gameimage) {
		ResponseEntity<String> savedEntity = adminService.addgames(gametitle, gamedescription, gameprice, gamediscount,
				gamecategory, gamerating, releasedate, gamepublisher, gameplatforms, minsystemrequirements,
				recsystemrequirements, gamegenres,
				gametrailerurl, agerating, gamefeatures, supportedlanguages, gameachievements, communitylinks,
				gameimage);
		return ResponseEntity.status(HttpStatus.OK).body(savedEntity);
	}

	@PutMapping("/updategames/{gameid}")
	public ResponseEntity<?> updategames(
			@PathVariable UUID gameid,
			@RequestParam("gametitle") String gametitle,
			@RequestParam("gamecategory") String gamecategory,
			@RequestParam("gamedescription") String gamedescription,
			@RequestParam("gameprice") Double gameprice,
			@RequestParam("gamediscount") Double gamediscount,
			@RequestParam("gamerating") String gamerating,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date releasedate,
			@RequestParam("gamepublisher") String gamepublisher, @RequestParam("gameplatforms") String gameplatforms,
			@RequestParam("minsystemrequirements") String minsystemrequirements,
			@RequestParam("recsystemrequirements") String recsystemrequirements,
			@RequestParam("gamegenres") String gamegenres, @RequestParam("gametrailerurl") String gametrailerurl,
			@RequestParam("agerating") String agerating, @RequestParam("gamefeatures") String gamefeatures,
			@RequestParam("supportedlanguages") String supportedlanguages,
			@RequestParam("gameachievements") String gameachievements,
			@RequestParam("communitylinks") String communitylinks,
			@RequestParam(value = "gameimage", required = false) MultipartFile gameimage,
			@RequestParam(value = "video1Url", required = false) MultipartFile video1Url,
			@RequestParam(value = "video2Url", required = false) MultipartFile video2Url,
			@RequestParam(value = "video3Url", required = false) MultipartFile video3Url,
			@RequestParam(value = "video4Url", required = false) MultipartFile video4Url) {

		// Create an Admin object with the updated information
		Admin updatedAdmin = new Admin();
		updatedAdmin.setGametitle(gametitle);
		updatedAdmin.setGamecategory(gamecategory);
		updatedAdmin.setGamedescription(gamedescription);
		updatedAdmin.setGameprice(gameprice);
		updatedAdmin.setGamediscount(gamediscount);
		updatedAdmin.setAgerating(agerating);
		updatedAdmin.setReleasedate(releasedate);
		updatedAdmin.setGamepublisher(gamepublisher);
		updatedAdmin.setGameplatforms(gameplatforms);
		updatedAdmin.setMinsystemrequirements(minsystemrequirements);
		updatedAdmin.setRecsystemrequirements(recsystemrequirements);
		updatedAdmin.setGamegenres(gamegenres);
		updatedAdmin.setGamerating(gamerating);
		updatedAdmin.setGametrailerurl(gametrailerurl);
		updatedAdmin.setGamefeatures(gamefeatures);
		updatedAdmin.setSupportedlanguages(supportedlanguages);
		updatedAdmin.setGameachievements(gameachievements);
		updatedAdmin.setCommunitylinks(communitylinks);

		// Call the service method to handle the update
		try {
			ResponseEntity<?> response = adminService.updategames(gameid, updatedAdmin, gameimage, video1Url, video2Url,
					video3Url, video4Url);
			return response;
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while updating game: " + e.getMessage());
		}
	}

	@GetMapping("/getAll/{page}/{size}")
	public ResponseEntity<?> getAllGames(@PathVariable int page, @PathVariable int size) {
		return adminService.getAllGames(page, size);
	}

	@DeleteMapping("/deletegame/{gameid}")
	public String deleteGame(@PathVariable UUID gameid) {
		return adminService.deleteGame(gameid);

	}

	@GetMapping("/getsearchedgame/{gametitle}")
	public List<Admin> getSearchedGame(@PathVariable String gametitle) {
		return adminService.getSearchedGame(gametitle);
	}

	@GetMapping("/getGameByCategory/{gamecategory}")
	public List<Admin> getGameByCategory(@PathVariable String gamecategory) {
		return adminService.getGameByCategory(gamecategory);

	}

	@GetMapping("/getAllGameCategory")
	public List<String> getAllGameCategory() {
		return adminService.getAllGameCategory();
	}

	@GetMapping("/getGameById/{gameid}")
	public List<Admin> getGameById(@PathVariable UUID gameid) {
		return adminService.getAllGameCategory(gameid);
	}
}
