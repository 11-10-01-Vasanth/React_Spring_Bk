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
import java.util.function.Consumer;

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

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public ResponseEntity<String> addgames(String gametitle, String gamedescription, Double gameprice,
			Double gamediscount,
			String gamecategory, String agerating, Date releasedate, String gamepublisher, String gameplatforms,
			String minsystemrequirements, String recsystemrequirements, String gamegenres, String gamerating,
			String gametrailerurl,
			String gamefeatures, String supportedlanguages, String gameachievements,
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
			MultipartFile video1Url, MultipartFile video2Url, MultipartFile video3Url, MultipartFile video4Url,
			MultipartFile img_vid1, MultipartFile img_vid2, MultipartFile img_vid3,
			MultipartFile img_vid4, MultipartFile img_vid5, MultipartFile img_vid6, MultipartFile img_vid7,
			MultipartFile img_vid8, MultipartFile img_vid9, MultipartFile img_vid10, MultipartFile img_vid11,
			MultipartFile img_vid12, MultipartFile img_vid13, MultipartFile img_vid14, MultipartFile img_vid15,
			MultipartFile img_vid16, MultipartFile img_vid17, MultipartFile img_vid18) {
		try {
			Optional<Admin> existingGameOpt = adminRepo.findById(gameid);
			if (!existingGameOpt.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with ID: " + gameid);
			}

			Admin entity = existingGameOpt.get();

			// Update fields of Admin entity only if provided
			updateAdminFields(entity, updatedAdmin);

			// Update the game image only if a new image is provided
			if (gameimage != null && !gameimage.isEmpty()) {
				String imageUrl = saveFile(gameimage);
				entity.setGameimage(imageUrl);
			}

			// Ensure Trending entity exists and update files
			updateTrendingFields(entity, video1Url, video2Url, video3Url, video4Url,
					img_vid1, img_vid2, img_vid3, img_vid4, img_vid5, img_vid6, img_vid7, img_vid8,
					img_vid9, img_vid10, img_vid11, img_vid12, img_vid13, img_vid14, img_vid15,
					img_vid16, img_vid17, img_vid18);

			// Set updated timestamp and save the entity
			entity.setUpdatedAt(new Date(System.currentTimeMillis()));
			adminRepo.save(entity);

			return ResponseEntity.ok(entity);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while updating game: " + e.getMessage());
		}
	}

	private void updateAdminFields(Admin entity, Admin updatedAdmin) {
		if (updatedAdmin.getGametitle() != null) {
			entity.setGametitle(updatedAdmin.getGametitle());
		}
		if (updatedAdmin.getGamedescription() != null) {
			entity.setGamedescription(updatedAdmin.getGamedescription());
		}
		if (updatedAdmin.getGamediscount() != null) {
			entity.setGamediscount(updatedAdmin.getGamediscount());
		}
		if (updatedAdmin.getGameprice() != null) {
			entity.setGameprice(updatedAdmin.getGameprice());
		}
		if (updatedAdmin.getGamecategory() != null) {
			entity.setGamecategory(updatedAdmin.getGamecategory());
		}
		if (updatedAdmin.getAgerating() != null) {
			entity.setAgerating(updatedAdmin.getAgerating());
		}
		if (updatedAdmin.getReleasedate() != null) {
			entity.setReleasedate(updatedAdmin.getReleasedate());
		}
		if (updatedAdmin.getGamepublisher() != null) {
			entity.setGamepublisher(updatedAdmin.getGamepublisher());
		}
		if (updatedAdmin.getGameplatforms() != null) {
			entity.setGameplatforms(updatedAdmin.getGameplatforms());
		}
		if (updatedAdmin.getMinsystemrequirements() != null) {
			entity.setMinsystemrequirements(updatedAdmin.getMinsystemrequirements());
		}
		if (updatedAdmin.getRecsystemrequirements() != null) {
			entity.setRecsystemrequirements(updatedAdmin.getRecsystemrequirements());
		}
		if (updatedAdmin.getGamegenres() != null) {
			entity.setGamegenres(updatedAdmin.getGamegenres());
		}
		if (updatedAdmin.getGamerating() != null) {
			entity.setGamerating(updatedAdmin.getGamerating());
		}
		if (updatedAdmin.getGametrailerurl() != null) {
			entity.setGametrailerurl(updatedAdmin.getGametrailerurl());
		}
		if (updatedAdmin.getGamefeatures() != null) {
			entity.setGamefeatures(updatedAdmin.getGamefeatures());
		}
		if (updatedAdmin.getSupportedlanguages() != null) {
			entity.setSupportedlanguages(updatedAdmin.getSupportedlanguages());
		}
		if (updatedAdmin.getGameachievements() != null) {
			entity.setGameachievements(updatedAdmin.getGameachievements());
		}
		if (updatedAdmin.getCommunitylinks() != null) {
			entity.setCommunitylinks(updatedAdmin.getCommunitylinks());
		}
	}

	private void updateTrendingFields(Admin entity, MultipartFile video1Url, MultipartFile video2Url,
			MultipartFile video3Url, MultipartFile video4Url, MultipartFile img_vid1,
			MultipartFile img_vid2, MultipartFile img_vid3, MultipartFile img_vid4, MultipartFile img_vid5,
			MultipartFile img_vid6, MultipartFile img_vid7, MultipartFile img_vid8, MultipartFile img_vid9,
			MultipartFile img_vid10, MultipartFile img_vid11, MultipartFile img_vid12, MultipartFile img_vid13,
			MultipartFile img_vid14, MultipartFile img_vid15, MultipartFile img_vid16, MultipartFile img_vid17,
			MultipartFile img_vid18) throws IOException {

		Trending trending = entity.getTrending();
		if (trending == null) {
			trending = new Trending();
			entity.setTrending(trending);
		}

		if (video1Url != null && !video1Url.isEmpty()) {
			String newFilePath = saveFile(video1Url);
			trending.setVideo1Url(newFilePath);
		}

		if (video2Url != null && !video2Url.isEmpty()) {
			String newFilePath = saveFile(video2Url);
			trending.setVideo2Url(newFilePath);
		}

		if (video3Url != null && !video3Url.isEmpty()) {
			String newFilePath = saveFile(video3Url);
			trending.setVideo3Url(newFilePath);
		}

		if (video4Url != null && !video4Url.isEmpty()) {
			String newFilePath = saveFile(video4Url);
			trending.setVideo4Url(newFilePath);
		}

		if (img_vid1 != null && !img_vid1.isEmpty()) {
			String newFilePath = saveFile(img_vid1);
			trending.setImg_vid1(newFilePath);
		}

		if (img_vid2 != null && !img_vid2.isEmpty()) {
			String newFilePath = saveFile(img_vid2);
			trending.setImg_vid2(newFilePath);
		}

		if (img_vid3 != null && !img_vid3.isEmpty()) {
			String newFilePath = saveFile(img_vid3);
			trending.setImg_vid3(newFilePath);
		}

		if (img_vid4 != null && !img_vid4.isEmpty()) {
			String newFilePath = saveFile(img_vid4);
			trending.setImg_vid4(newFilePath);
		}

		if (img_vid5 != null && !img_vid5.isEmpty()) {
			String newFilePath = saveFile(img_vid5);
			trending.setImg_vid5(newFilePath);
		}

		if (img_vid6 != null && !img_vid6.isEmpty()) {
			String newFilePath = saveFile(img_vid6);
			trending.setImg_vid6(newFilePath);
		}

		if (img_vid7 != null && !img_vid7.isEmpty()) {
			String newFilePath = saveFile(img_vid7);
			trending.setImg_vid7(newFilePath);
		}

		if (img_vid8 != null && !img_vid8.isEmpty()) {
			String newFilePath = saveFile(img_vid8);
			trending.setImg_vid8(newFilePath);
		}

		if (img_vid9 != null && !img_vid9.isEmpty()) {
			String newFilePath = saveFile(img_vid9);
			trending.setImg_vid9(newFilePath);
		}

		if (img_vid10 != null && !img_vid10.isEmpty()) {
			String newFilePath = saveFile(img_vid10);
			trending.setImg_vid10(newFilePath);
		}

		if (img_vid11 != null && !img_vid11.isEmpty()) {
			String newFilePath = saveFile(img_vid11);
			trending.setImg_vid11(newFilePath);
		}

		if (img_vid12 != null && !img_vid12.isEmpty()) {
			String newFilePath = saveFile(img_vid12);
			trending.setImg_vid12(newFilePath);
		}

		if (img_vid13 != null && !img_vid13.isEmpty()) {
			String newFilePath = saveFile(img_vid13);
			trending.setImg_vid13(newFilePath);
		}

		if (img_vid14 != null && !img_vid14.isEmpty()) {
			String newFilePath = saveFile(img_vid14);
			trending.setImg_vid14(newFilePath);
		}

		if (img_vid15 != null && !img_vid15.isEmpty()) {
			String newFilePath = saveFile(img_vid15);
			trending.setImg_vid15(newFilePath);
		}

		if (img_vid16 != null && !img_vid16.isEmpty()) {
			String newFilePath = saveFile(img_vid16);
			trending.setImg_vid16(newFilePath);
		}

		if (img_vid17 != null && !img_vid17.isEmpty()) {
			String newFilePath = saveFile(img_vid17);
			trending.setImg_vid17(newFilePath);
		}

		if (img_vid18 != null && !img_vid18.isEmpty()) {
			String newFilePath = saveFile(img_vid18);
			trending.setImg_vid18(newFilePath);
		}

	}

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
