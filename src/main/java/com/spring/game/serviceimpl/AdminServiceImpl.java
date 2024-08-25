package com.spring.game.serviceimpl;

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
import com.spring.game.repo.AdminRepo;
import com.spring.game.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public ResponseEntity<String> addgames(String gametitle, String gamedescription, Double gameprice,
			Double gamediscount, String gamecategory, MultipartFile gameimage) {
		if (gameimage.isEmpty()) {
			return new ResponseEntity<>("Please select a file!", HttpStatus.OK);
		}

		try {
			byte[] bytes = gameimage.getBytes();
			UUID uuid = UUID.randomUUID();
//            String uploadsLocation = "/home/kernelogy/Vasanth/React_Spring_bk/src/main/resources/resources/uploads/";
			String uploadsLocation = "D:/SpringWorkspace/Game_World/src/main/resources/resources/uploads/";
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
		// TODO Auto-generated method stub
		return adminRepo.findBygameid(gameid);
	}

	@Override
	public ResponseEntity<?> updategames(UUID gameid, Admin updatedAdmin, MultipartFile gameimage,MultipartFile gameimage1, MultipartFile gameimage2, MultipartFile gameimage3) {
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

	        // Update the main game image
	        if (updatedAdmin.getGameimage() != null && !updatedAdmin.getGameimage().isEmpty()) {
	            entity.setGameimage(updatedAdmin.getGameimage());
	        }

	        // Update additional game images
	        if (gameimage1 != null && !gameimage1.isEmpty()) {
	            String imageUrl1 = saveFile(gameimage1, "image1");
	            entity.setGameimage1(imageUrl1);
	        }

	        if (gameimage2 != null && !gameimage2.isEmpty()) {
	            String imageUrl2 = saveFile(gameimage2, "image2");
	            entity.setGameimage2(imageUrl2);
	        }

	        if (gameimage3 != null && !gameimage3.isEmpty()) {
	            String imageUrl3 = saveFile(gameimage3, "image3");
	            entity.setGameimage3(imageUrl3);
	        }

	        entity.setUpdatedAt(new Date());  // Update the timestamp

	        adminRepo.save(entity);

	        return ResponseEntity.ok("Game updated successfully.");
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}

	private String saveFile(MultipartFile file, String type) throws IOException {
	    UUID uuid = UUID.randomUUID();
	    String uploadsLocation = "D:/SpringWorkspace/Game_World/src/main/resources/resources/uploads/";
	    String fileName = uuid + "_" + file.getOriginalFilename();
	    String fileLocation = uploadsLocation + fileName;
	    Path path = Paths.get(fileLocation);

	    // Ensure the directory exists
	    if (!Files.exists(path.getParent())) {
	        Files.createDirectories(path.getParent());
	    }

	    Files.write(path, file.getBytes());

	    return fileName;
	}


}
