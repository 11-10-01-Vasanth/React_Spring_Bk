package com.spring.game.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.game.model.Admin;
import com.spring.game.model.Trending;
import com.spring.game.repo.AdminRepo;
import com.spring.game.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private static final String uploadDir = null;
	@Autowired
	private AdminRepo adminRepo;

	@Override
	public ResponseEntity<String> addgames(String gametitle, String gamedescription, Double gameprice,
			Double gamediscount,
			String gamecategory, String gamerating, Date releasedate, String gamepublisher, String gameplatforms,
			String minsystemrequirements, String recsystemrequirements, String gamegenres, String gametrailerurl,
			String agerating, String gamefeatures, String supportedlanguages, String gameachievements,
			String communitylinks, MultipartFile gameimage) {
		if (gameimage.isEmpty()) {
			return new ResponseEntity<>("Please select a file!", HttpStatus.OK);
		}

		try {
			byte[] bytes = gameimage.getBytes();
			UUID uuid = UUID.randomUUID();
			String uploadsLocation = "/home/kernelogy/Vasanth/React_Spring_Bk/src/main/resources/resources/uploads/";
			// String uploadsLocation =
			// "D:/SpringWorkspace/Game_World/src/main/resources/resources/uploads/";
			String imageUrl = uuid + "_" + gameimage.getOriginalFilename();
			String fileLocation = uploadsLocation + imageUrl;
			Path path = Paths.get(fileLocation);

			// Ensure the directory exists
			if (!Files.exists(path.getParent())) {
				Files.createDirectories(path.getParent());
			}

			Files.write(path, bytes);

			Admin game = new Admin();
			game.setGametitle(gametitle);
			game.setGamedescription(gamedescription);
			game.setGameprice(gameprice);
			game.setGamediscount(gamediscount);
			game.setGamecategory(gamecategory);
			game.setAgerating(agerating);
			game.setReleasedate(releasedate);
			game.setGamepublisher(gamepublisher);
			game.setGameplatforms(gameplatforms);
			game.setMinsystemrequirements(minsystemrequirements);
			game.setRecsystemrequirements(recsystemrequirements);
			game.setGamegenres(gamegenres);
			game.setGamerating(gamerating);
			game.setGametrailerurl(gametrailerurl);
			game.setGamefeatures(gamefeatures);
			game.setSupportedlanguages(supportedlanguages);
			game.setGameachievements(gameachievements);
			game.setCommunitylinks(communitylinks);
			game.setGameimage(imageUrl);
			game.setCreatedAt(new Date(System.currentTimeMillis()));

			adminRepo.save(game);

			return ResponseEntity.ok("Game added successfully with image URL: " + imageUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Page<Admin>> getAllGames(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Admin> pagedTokens = adminRepo.findAll(pageable);
		return ResponseEntity.ok(pagedTokens);
	}

	@Override
	public String deleteGame(UUID gameid) {
		if (adminRepo.existsById(gameid)) {
			adminRepo.deleteById(gameid);
			return "Deleted Successfully";
		} else {
			return "Game not found";
		}
	}

	@Override
	public List<Admin> getSearchedGame(String gametitle) {
		// TODO Auto-generated method stub
		return adminRepo.findBygametitleContainingIgnoreCase(gametitle);
	}

	@Override
	public List<Admin> getGameByCategory(String gamecategory) {
		return adminRepo.findAllBygamecategoryContainingIgnoreCase(gamecategory);
	}

	@Override
	public List<String> getAllGameCategory() {
		return adminRepo.findDistinctGameCategories();
	}

	@Override
	public List<Admin> getAllGameCategory(UUID gameid) {
		return adminRepo.findBygameid(gameid);
	}

	@Override
	public ResponseEntity<?> updategames(UUID gameid, Admin updatedAdmin, MultipartFile gameimage,
			MultipartFile video1Url, MultipartFile video2Url, MultipartFile video3Url, MultipartFile video4Url) {
		try {
			Optional<Admin> existingGameOpt = adminRepo.findById(gameid);
			if (!existingGameOpt.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
			}

			Admin entity = existingGameOpt.get();
			entity.setGametitle(updatedAdmin.getGametitle());
			entity.setGamedescription(updatedAdmin.getGamedescription());
			entity.setGamediscount(updatedAdmin.getGamediscount());
			entity.setGameprice(updatedAdmin.getGameprice());
			entity.setGamecategory(updatedAdmin.getGamecategory());
			entity.setAgerating(updatedAdmin.getAgerating());
			entity.setReleasedate(updatedAdmin.getReleasedate());
			entity.setGamepublisher(updatedAdmin.getGamepublisher());
			entity.setGameplatforms(updatedAdmin.getGameplatforms());
			entity.setMinsystemrequirements(updatedAdmin.getMinsystemrequirements());
			entity.setRecsystemrequirements(updatedAdmin.getRecsystemrequirements());
			entity.setGamegenres(updatedAdmin.getGamegenres());
			entity.setGamerating(updatedAdmin.getGamerating());
			entity.setGametrailerurl(updatedAdmin.getGametrailerurl());
			entity.setGamefeatures(updatedAdmin.getGamefeatures());
			entity.setSupportedlanguages(updatedAdmin.getAgerating());
			entity.setGameachievements(updatedAdmin.getGameachievements());
			entity.setCommunitylinks(updatedAdmin.getCommunitylinks());

			// Update the game image
			if (gameimage != null && !gameimage.isEmpty()) {
				String imageUrl = saveFile(gameimage);
				entity.setGameimage(imageUrl);
			}

			// Update the Trending entity
			Trending trending = entity.getTrending();
			if (trending == null) {
				trending = new Trending();
				entity.setTrending(trending);
			}

			if (video1Url != null && !video1Url.isEmpty()) {
				String video1Path = saveFile(video1Url);
				trending.setVideo1Url(video1Path);
			}
			if (video2Url != null && !video2Url.isEmpty()) {
				String video2Path = saveFile(video2Url);
				trending.setVideo2Url(video2Path);
			}
			if (video3Url != null && !video3Url.isEmpty()) {
				String video3Path = saveFile(video3Url);
				trending.setVideo3Url(video3Path);
			}
			if (video4Url != null && !video4Url.isEmpty()) {
				String video4Path = saveFile(video4Url);
				trending.setVideo4Url(video4Path);
			}

			entity.setUpdatedAt(new Date(System.currentTimeMillis()));

			adminRepo.save(entity);

			return ResponseEntity.ok("Game updated successfully.");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while updating game: " + e.getMessage());
		}
	}

	// private String saveFile(MultipartFile file) throws IOException {
	// 	UUID uuid = UUID.randomUUID();
	// 	String uploadsLocation = "/home/kernelogy/Vasanth/React_Spring_Bk/src/main/resources/resources/uploads/";
	// 	// Replace spaces with underscores
	// 	String originalFileName = file.getOriginalFilename().replace(" ", "_");
	// 	String fileName = uuid + "_" + originalFileName;
	// 	Path path = Paths.get(uploadsLocation + fileName);

	// 	if (!Files.exists(path.getParent())) {
	// 		Files.createDirectories(path.getParent());
	// 	}

	// 	Files.write(path, file.getBytes());
	// 	return fileName;
	// }

	private String saveFile(MultipartFile file) throws IOException {
		UUID uuid = UUID.randomUUID();
		String uploadsLocation = "/home/kernelogy/Vasanth/React_Spring_Bk/src/main/resources/resources/uploads/";
		String fileName = uuid + "_" + file.getOriginalFilename();
		Path path = Paths.get(uploadsLocation + fileName);

		if (!Files.exists(path.getParent())) {
			Files.createDirectories(path.getParent());
		}

		Files.write(path, file.getBytes());
		return fileName;
	}


}
