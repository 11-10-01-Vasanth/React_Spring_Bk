package com.spring.game.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.game.model.Admin;
import com.spring.game.service.AdminService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
			@RequestParam("gameimage") MultipartFile gameimage) {
		ResponseEntity<String> savedEntity = adminService.addgames(gametitle, gamedescription, gameprice, gamediscount,
				gamecategory, gameimage);
		return ResponseEntity.status(HttpStatus.OK).body(savedEntity);
	}

	@PostMapping("/updategames/{gameid}")
	public ResponseEntity<?> updategames(@PathVariable UUID gameid, @RequestBody Admin admin,
			@RequestParam("gameimage") MultipartFile gameimage, @RequestParam("gameimage1") MultipartFile gameimage1,
			@RequestParam("gameimage2") MultipartFile gameimage2,
			@RequestParam("gameimage3") MultipartFile gameimage3) {

		ResponseEntity<?> savedEntity = adminService.updategames(gameid, admin, gameimage, gameimage1, gameimage2,
				gameimage3);
		return ResponseEntity.status(HttpStatus.OK).body(savedEntity);
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
